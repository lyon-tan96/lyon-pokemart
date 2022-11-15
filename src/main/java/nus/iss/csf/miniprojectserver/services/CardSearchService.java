package nus.iss.csf.miniprojectserver.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.csf.miniprojectserver.models.CardSummary;

@Service
public class CardSearchService {

    public static final String CARD_SEARCH = "https://api.pokemontcg.io/v2/cards";
    
    public List<CardSummary> searchCardList(String searchTerm) {

        List<CardSummary> cardsList = new LinkedList<>();

        String url = UriComponentsBuilder.fromUriString(CARD_SEARCH)
                       .queryParam("q", "name:%s*".formatted(searchTerm))
                       .queryParam("pageSize", 20)
                       .toUriString();
        
        System.out.println(url);

        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.printf(">>>>> status: %s", resp.getStatusCode());
        String priceRounded = "";
        
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject cards = reader.readObject();
        JsonArray data = cards.getJsonArray("data");
        for (int i = 0; i < data.size(); i++) {
            JsonObject card = data.getJsonObject(i);
            String image = card.getJsonObject("images").getString("large");
            String cardName = card.getString("name");
            if (card.containsKey("cardmarket")) {
                if (!card.getJsonObject("cardmarket").getJsonObject("prices").containsKey("averageSellPrice")) {
                    priceRounded = "0";
                } else {
                    String price = card.getJsonObject("cardmarket").getJsonObject("prices").get("averageSellPrice").toString();
                    priceRounded = String.format("%s", price);
                }
            } else {
                priceRounded = "0";
            }
            String id = card.getString("id");
            System.out.println(priceRounded);
            // Double roundedPrice = Math.round(price * 100.0)/100.0;
            CardSummary cardDetail = new CardSummary();
            cardDetail.setImage(image);
            cardDetail.setCardName(cardName);
            cardDetail.setPrice(priceRounded);
            cardDetail.setId(id);
            cardsList.add(cardDetail);
            
        }

        if (cardsList.isEmpty()) {
//            throw new IllegalArgumentException("Results not found for %s.".formatted(searchTerm));
            throw new IllegalArgumentException("Results not found for %s.");
        }

        return cardsList;
    }
}

package nus.iss.csf.miniprojectserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import nus.iss.csf.miniprojectserver.models.CardSummary;
import nus.iss.csf.miniprojectserver.services.CardSearchService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardSearchRestController {
    
    @Autowired
    private CardSearchService cardSearchSvc;

    @GetMapping(path = "/search")
    public ResponseEntity<String> searchCards(@RequestParam String q) {

        List<CardSummary> cardsList = cardSearchSvc.searchCardList(q);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (CardSummary card: cardsList) {
           arrBuilder.add(card.toJson());
        }

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

}

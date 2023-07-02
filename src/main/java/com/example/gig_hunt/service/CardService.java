package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Card;

public interface CardService extends DefaultService<Card> {

    String checkTimeToCardExpiration(Long cardId);

}

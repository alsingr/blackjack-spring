package blackjack.services;

import blackjack.model.Card;
import blackjack.model.Response;
import blackjack.model.SignOfCard;
import blackjack.model.ValueOfCard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alsingr on 13/03/15.
 */

@RestController
public class ResponseService {


    @RequestMapping("/alvyn")
    Response help()
    {
        Response response = new Response();
        response.setErrorCode("200000");
        response.setErrorMessage("Pas bon");

        Card card = new Card(SignOfCard.Club, ValueOfCard.As);
        response.getItems().add(card);

        return response ;
    }
}

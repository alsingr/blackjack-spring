package blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alsingr on 13/03/15.
 */
public class Response {
    String errorCode ;
    String errorMessage ;
    List<ItemValue> items = new ArrayList<ItemValue>();




    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ItemValue> getItems() {
        return items;
    }

    public void setItems(List<ItemValue> items) {
        this.items = items;
    }
}



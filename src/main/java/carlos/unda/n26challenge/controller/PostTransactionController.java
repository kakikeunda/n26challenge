package carlos.unda.n26challenge.controller;

import carlos.unda.n26challenge.exception.InvalidInputException;
import carlos.unda.n26challenge.utils.Constants;
import carlos.unda.n26challenge.exception.TransactionOvertimeException;
import carlos.unda.n26challenge.model.Transaction;
import carlos.unda.n26challenge.model.TransactionsListSingleton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping(Constants.POST_TRANSACTIONS_PATH)
public class PostTransactionController {

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void postTransaction(Model model,
                                  @RequestBody JsonObject jTransaction) {

        validateInput(jTransaction);
        Gson gson = new Gson();

        Transaction transaction = gson.fromJson(jTransaction, Transaction.class);

        TransactionsListSingleton.getInstance().addToList(transaction);
        if (new Date().getTime() - transaction.getTimestamp() > 60000){
            throw new TransactionOvertimeException();
        }

    }

    private void validateInput(JsonObject jTransaction){
        if (!jTransaction.has(Constants.INPUT_FIELD_AMOUNT)){
            throw new InvalidInputException("missing amount on transaction");
        }
        if (!jTransaction.has(Constants.INPUT_FIELD_TIMESTAMP)){
            throw new InvalidInputException("missing amount on transaction");
        }

        try {
            double amount = jTransaction.get(Constants.INPUT_FIELD_AMOUNT).getAsDouble();
        } catch (NumberFormatException e){
            throw new InvalidInputException("invalid amount format");
        }

        try {
            long timestamp = jTransaction.get(Constants.INPUT_FIELD_TIMESTAMP).getAsLong();
            Date date = new Date(timestamp);
        } catch (NumberFormatException e){
            throw new InvalidInputException("invalid timestamp format");
        }
    }
}

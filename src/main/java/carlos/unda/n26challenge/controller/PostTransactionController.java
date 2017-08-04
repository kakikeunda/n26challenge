package carlos.unda.n26challenge.controller;

import carlos.unda.n26challenge.Utils.Constants;
import carlos.unda.n26challenge.exception.TransactionOvertimeException;
import carlos.unda.n26challenge.model.Transaction;
import carlos.unda.n26challenge.model.TransactionsListSingleton;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Date;

@Controller
public class PostTransactionController {
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = Constants.POST_TRANSACTIONS_PATH, method = RequestMethod.POST)
    public String postTransaction(Model model, @RequestBody Transaction transaction) {

        TransactionsListSingleton.getInstance().addToList(transaction);

        if (new Date().getTime() - transaction.getTimestamp() > 60000){
            throw new TransactionOvertimeException();
        }
        return Constants.POST_TRANSACTION_VIEW_NAME;
    }

}

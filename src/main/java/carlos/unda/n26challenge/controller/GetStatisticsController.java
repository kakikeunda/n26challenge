package carlos.unda.n26challenge.controller;

import carlos.unda.n26challenge.Utils.Constants;
import carlos.unda.n26challenge.model.Statistics;
import carlos.unda.n26challenge.model.Transaction;
import carlos.unda.n26challenge.model.TransactionsListSingleton;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class GetStatisticsController {

    @RequestMapping(value = Constants.GET_STATISTICS_PATH, method = RequestMethod.GET)
    public String getStatistics(Model model){

        model.addAttribute(Constants.MODEL_JSON_ATTRIBUTE, new Gson().toJson(TransactionsListSingleton.getInstance().getStatistics()));

        return Constants.GET_STATISTICS_VIEW_NAME;
    }

}

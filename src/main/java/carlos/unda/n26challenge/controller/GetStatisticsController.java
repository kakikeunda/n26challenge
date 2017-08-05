package carlos.unda.n26challenge.controller;

import carlos.unda.n26challenge.utils.Constants;
import carlos.unda.n26challenge.model.TransactionsListSingleton;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GetStatisticsController {

    @RequestMapping(value = Constants.GET_STATISTICS_PATH, method = RequestMethod.GET)
    public String getStatistics(Model model){
        Gson gson = new Gson();

        model.addAttribute(Constants.MODEL_JSON_ATTRIBUTE, gson.toJson(TransactionsListSingleton.getInstance()
                .calculateStatistics()));

        return Constants.GET_STATISTICS_VIEW_NAME;
    }

}

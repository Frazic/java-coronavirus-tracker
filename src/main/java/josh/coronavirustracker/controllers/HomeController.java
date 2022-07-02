package josh.coronavirustracker.controllers;

import josh.coronavirustracker.models.DataModel;
import josh.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class HomeController {

    @Autowired // Autowired works because we declared it as a spring service
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<DataModel> data = coronavirusDataService.getDataList();
        // Sum total cases and change
        int totalCases = data.stream().mapToInt(DataModel::getLatestTotalCases).sum();
        int totalChange = data.stream().mapToInt(DataModel::oneDayChange).sum();

        // This makes our data accessible from inside the html template
        model.addAttribute("data", data);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalChange", totalChange);

        // Returns the 'home' template defined in resources/templates
        return "home";
    }
}

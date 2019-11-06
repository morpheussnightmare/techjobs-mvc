package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value="results")
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            // search in the findByColumnAndValue by key = searchType and value = searchTerm
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchTerm);
        return "search";
    }
}

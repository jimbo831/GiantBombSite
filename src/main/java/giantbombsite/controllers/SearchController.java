package giantbombsite.controllers;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import giantbombsite.objects.Result;
import giantbombsite.services.SearchService;

@RestController
public class SearchController {

  @Autowired
  SearchService searchService;

  @RequestMapping("search")
  public @ResponseBody String search(@RequestParam("query") String query) {
    List<Result> results = searchService.search(query);
    Gson gson = new Gson();
    String jsonReturn = gson.toJson(results);
    return jsonReturn;
  }
}

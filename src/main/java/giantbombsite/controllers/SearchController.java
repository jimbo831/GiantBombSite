package giantbombsite.controllers;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import giantbombsite.objects.SearchResponse;
import giantbombsite.services.SearchService;

@RestController
public class SearchController {

  @Autowired
  SearchService searchService;

  @Autowired
  Gson gson;

  @GetMapping("gameSearch")
  public @ResponseBody SearchResponse gameSearch(@RequestParam String query,
                       @RequestParam(required = false, defaultValue = "1") int page) {
    return searchService.gameSearch(query, page);
  }
}

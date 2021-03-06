package giantbombsite.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import giantbombsite.objects.SearchResponse;

@Service
@PropertySource("application.properties")
public class SearchServiceImpl implements SearchService {

  @Autowired
  GiantBombApiService giantBombApiService;

  public SearchResponse gameSearch(String query, int page) {
    SearchResponse searchResponse = null;
    String searchUrl = null;
    try {
      searchUrl = "search/?format=json&query=" +
          URLEncoder.encode(query, "UTF-8") + "&resources=game&page=" + page;
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
    String jsonResponse = giantBombApiService.getGiantBombResponse(searchUrl);
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    searchResponse = gson.fromJson(jsonResponse, SearchResponse.class);

    if (searchResponse == null || searchResponse.getResults() == null) {
      return null;
    }
    else {
      return searchResponse;
    }
  }
}

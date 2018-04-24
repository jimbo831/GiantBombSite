package giantbombsite.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import giantbombsite.objects.Result;
import giantbombsite.objects.SearchResponse;

@Service
@PropertySource("giantbombsite.properties")
public class SearchServiceImpl implements SearchService {

  @Value("${base.url}")
  private String baseUrl;

  @Value("${api.key}")
  private String apiKey;

  @Value("${user.agent}")
  private String userAgent;

  public List<Result> search(String query) {
    SearchResponse searchResponse = null;
    String jsonResponse = null;

    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = null;
    try {
      request = new HttpGet(baseUrl + "search/?api_key=" + apiKey + "&format=json&query=" +
          URLEncoder.encode(query, "UTF-8") + "&resources=game");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
    request.setHeader("User-Agent", userAgent);

    try {
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      jsonResponse = EntityUtils.toString(entity);
      GsonBuilder builder = new GsonBuilder();
      Gson gson = builder.create();
      searchResponse = gson.fromJson(jsonResponse, SearchResponse.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    if (searchResponse == null || searchResponse.getResults() == null) {
      return null;
    }
    else {
      return searchResponse.getResults();
    }
  }

}

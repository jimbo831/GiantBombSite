package giantbombsite.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GiantBombApiServiceImpl implements GiantBombApiService {

  @Value("${base.url}")
  private String baseUrl;

  @Value("${api.key}")
  private String apiKey;

  @Value("${user.agent}")
  private String userAgent;

  public String getGiantBombResponse(String url) {
    String jsonResponse;
    url = baseUrl + url + "&api_key=" + apiKey;

    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = null;
    request = new HttpGet(url);
    request.setHeader("User-Agent", userAgent);

    try {
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      jsonResponse = EntityUtils.toString(entity);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return jsonResponse;
  }
}

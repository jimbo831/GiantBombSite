package giantbombsite.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import giantbombsite.objects.SearchResponse;

public class SearchServiceImplTest {

  @Mock
  GiantBombApiService giantBombApiService;

  @InjectMocks
  private SearchServiceImpl searchService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getSearchResults() throws UnsupportedEncodingException {
    String query = "test";
    int page = 0;
    String searchResultsJson = getJsonStringFromFile("/sampleSearchResults.json");
    when(giantBombApiService.getGiantBombResponse("search/?format=json&query=" +
        URLEncoder.encode(query, "UTF-8") + "&resources=game&page=" + page))
        .thenReturn(searchResultsJson);
    SearchResponse searchResponse = searchService.gameSearch(query, page);
    assertNotNull(searchResponse);
    assertEquals(searchResponse.getOffset(), 0);
    assertEquals(searchResponse.getNumber_of_page_results(), 9);
    assertEquals(searchResponse.getNumber_of_total_results(), 9);
    assertEquals(searchResponse.getResults().get(0).getName(), "Metroid Prime");
  }

  private String getJsonStringFromFile(String filename) {
    String jsonString = null;
    try {
      jsonString = new String(Files.readAllBytes(Paths.get(getClass().getResource(filename).toURI())));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unable to read JSON from file " + filename);
    }
    return jsonString;
  }
}

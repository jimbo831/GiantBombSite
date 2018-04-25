package giantbombsite.services;

import giantbombsite.objects.SearchResponse;

public interface SearchService {

  public SearchResponse gameSearch(String query, int page);
}

package giantbombsite.services;

import java.util.List;

import giantbombsite.objects.Result;

public interface SearchService {

  public List<Result> search(String query);
}

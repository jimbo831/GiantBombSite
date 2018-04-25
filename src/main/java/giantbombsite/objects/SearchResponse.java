package giantbombsite.objects;

import java.util.List;

public class SearchResponse {
  private String error;
  private int limit;
  private int offset;
  private int number_of_page_results;
  private int number_of_total_results;
  private int status_code;
  private List<Result> results;
  private String version;

  public List<Result> getResults() {
    return results;
  }

  public int getOffset() {
    return offset;
  }

  public int getNumber_of_page_results() {
    return number_of_page_results;
  }

  public int getNumber_of_total_results() {
    return number_of_total_results;
  }
}

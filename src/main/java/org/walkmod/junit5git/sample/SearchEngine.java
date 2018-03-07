package org.walkmod.junit5git.sample;

import java.util.List;
import java.util.stream.Collectors;

public class SearchEngine {

  private MesssagesDAO messsagesDAO;

  public SearchEngine(MesssagesDAO messsagesDAO) {
    this.messsagesDAO = messsagesDAO;
  }

  public List<String> search(String word) throws Exception {
    return messsagesDAO.getAll().stream()
            .filter(message -> message.contains(word))
            .collect(Collectors.toList());
  }
}

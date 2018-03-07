package org.junit4git.dao;

import java.util.List;

public interface MesssagesDAO {

  void write(String message) throws Exception;

  List<String> getAll() throws Exception;
}

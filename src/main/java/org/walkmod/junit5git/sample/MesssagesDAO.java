package org.walkmod.junit5git.sample;

import java.util.List;

public interface MesssagesDAO {

  void write(String message) throws Exception;

  List<String> getAll() throws Exception;
}

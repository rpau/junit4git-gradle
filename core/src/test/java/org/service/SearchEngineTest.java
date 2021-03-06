package org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.dao.MesssagesDAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

public class SearchEngineTest {

  @Test
  public void testSearch() throws Exception {
    MesssagesDAO dao = mock(MesssagesDAO.class);

    String message = "hello everybody";

    when(dao.getAll()).thenReturn(Arrays.asList(message, "bye bye"));
    SearchEngine searchEngine = new SearchEngine(dao);
    assertEquals(message, searchEngine.search("hello").get(0));
  }

}

package org.walkmod.junit5git.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Tag("fast")
public class SearchEngineTest {

  @Test
  @DisplayName("Messages can be found! 😎")
  void testSearch() throws Exception {
    MesssagesDAO dao = mock(MesssagesDAO.class);

    String message = "hello everybody";

    when(dao.getAll()).thenReturn(Arrays.asList(message, "bye bye"));
    SearchEngine searchEngine = new SearchEngine(dao);
    assertEquals(message, searchEngine.search("hello").get(0));
  }

}

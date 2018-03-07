package org.walkmod.junit5git.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MessagesDAOImpl implements MesssagesDAO {

  private Connection connection;

  public MessagesDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void write(String message) throws Exception {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO MESSAGES(CONTENT) VALUES(?)");
    statement.setString(1, message);
    statement.execute();
  }

  @Override
  public List<String> getAll() throws Exception {
      List<String> result = new LinkedList<>();

      try(Statement statement = connection.createStatement()) {
        statement.execute("SELECT CONTENT FROM MESSAGES");
        try (ResultSet rs = statement.getResultSet()) {
          while (rs.next()) {
            result.add(rs.getString(1));
          }
        }
      }

    return result;
  }
}

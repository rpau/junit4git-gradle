/*
 * Copyright (c) 2017 Schibsted Media Group. All rights reserved.
 */
package org.junit4git.sample;

import javax.sql.DataSource;

import org.arquillian.cube.HostIp;
import org.arquillian.cube.HostPort;
import org.flywaydb.core.Flyway;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.walkmod.junit5git.sample.MessagesDAOImpl;

@RunWith(Arquillian.class)
public class MessagesDAOImplTest {

  @HostPort(containerName = "postgres*", value = 5432)
  int POSTGRES_PORT = 0;

  @HostIp
  String POSTGRES_HOST = "localhost";

  DataSource dataSource = null;

  @Before
  public void createTables() throws Exception {

    prepareDatasource();
    Flyway flyway = new Flyway();
    flyway.setDataSource(dataSource);
    flyway.migrate();
  }

  private void prepareDatasource() throws Exception {
    PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setDatabaseName("root");
    dataSource.setPortNumber(POSTGRES_PORT);
    dataSource.setUser("postgres");
    dataSource.setServerName(POSTGRES_HOST);
    dataSource.setPassword("postgres");

    this.dataSource = dataSource;
  }



  @Test
  public void  testWhenMessagesAreInsertedThenAreRecovered() throws Exception {
    String message = "hello voxxed days!";
    MessagesDAOImpl dao = new MessagesDAOImpl(dataSource.getConnection());
    dao.write(message);
    Assert.assertEquals(message, dao.getAll().get(0));
    Assert.assertEquals(1, dao.getAll().size());
  }

}

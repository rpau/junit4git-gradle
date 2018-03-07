/*
 * Copyright (c) 2017 Schibsted Media Group. All rights reserved.
 */
package org.junit4git.sample;

import javax.sql.DataSource;

import org.arquillian.cube.HostIp;
import org.arquillian.cube.HostPort;
import org.flywaydb.core.Flyway;
import org.jboss.arquillian.junit.ArquillianTest;
import org.jboss.arquillian.junit.ArquillianTestClass;
import org.junit.*;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.walkmod.junit4git.junit4.Junit4GitRunner;
import org.walkmod.junit5git.sample.MessagesDAOImpl;

@RunWith(Junit4GitRunner.class)
public class MessagesDAOImplTest {

  @ClassRule
  public static ArquillianTestClass arquillianTestClass = new ArquillianTestClass();
  @Rule
  public ArquillianTest arquillianTest = new ArquillianTest();

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

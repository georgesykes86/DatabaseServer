package com.georgesykes.databaseserver.feature;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.georgesykes.databaseserver.Server;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void appRunsTest(){
    Server server = new Server();
    try {
      server.start();
      server.stop();
    } catch(Exception e){
      assertFalse(true);
    }
  }

}

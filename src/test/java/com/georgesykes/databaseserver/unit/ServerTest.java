package com.georgesykes.databaseserver.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.georgesykes.databaseserver.Server;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServerTest {

  private Server server;
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    server = new Server();
    System.setOut(new PrintStream(output));
  }

  @Test
  public void serverStartsTest(){
    try {
      server.start();
    } catch(Exception e) {
      assertTrue(false);
    }
    assertEquals("Application started.......\n", output.toString());
  }

  @Test
  public void serverStopsTest(){
    try {
      server.start();
    } catch(Exception e){}
    server.stop();
    assertEquals("Stopping server.....", output.toString().split("\n")[1]);
  }

}

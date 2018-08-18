package com.georgesykes.databaseserver;

import com.georgesykes.databaseserver.Controllers.GetRootHandler;
import com.georgesykes.databaseserver.Controllers.SetRootHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

  private HttpServer server;

  public void start() throws IOException {
    server = HttpServer.create(new InetSocketAddress(4000), 0);
    System.out.println("Application started.......");
    KeyValueStore db = new KeyValueStore();
    server.createContext("/get", new GetRootHandler(db));
    server.createContext("/set", new SetRootHandler(db));
    server.setExecutor(null);
    server.start();
  }

  public void stop(){
    server.stop(0);
  }

}

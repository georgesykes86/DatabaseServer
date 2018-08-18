package com.georgesykes.databaseserver;

import com.georgesykes.databaseserver.Controllers.GetRootHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

  public static void main(String[] args) throws IOException{
    HttpServer server = HttpServer.create(new InetSocketAddress(4000), 0);
    System.out.println("Application started.......");
    server.createContext("/get", new GetRootHandler());
    server.setExecutor(null);
    server.start();
  }

}

package com.georgesykes.databaseserver.Controllers;

import com.georgesykes.databaseserver.DbInterface;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetRootHandler implements HttpHandler {

  private DbInterface db;

  public SetRootHandler(DbInterface db){
    this.db = db;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String query = httpExchange.getRequestURI().getQuery();
    this.updateDb(query);
    String response = "";
    httpExchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = httpExchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  private void updateDb(String query) {
    HashMap<String, String> map = this.parseQuery(query);
    for(String key : map.keySet()) {
      this.db.setValue(key, map.get(key));
    }
  }

  private HashMap<String, String> parseQuery(String query) {
    Pattern pattern = Pattern.compile("([^,]*)=([^,]*)");
    Matcher matcher = pattern.matcher(query);
    HashMap<String, String> map = new HashMap<>();
    while(matcher.find()) {
      map.put(matcher.group(1), matcher.group(2));
    }
    return map;
  }

}

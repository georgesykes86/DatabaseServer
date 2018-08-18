package com.georgesykes.databaseserver.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetRootHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    URI requestURI = exchange.getRequestURI();
    String query = requestURI.getQuery();
    String key = this.parseQuery(query);
    String response = (key != null) ? this.getValue(key) : null;
    exchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  private String parseQuery(String query) {
    Pattern pattern = Pattern.compile("key=([^,]*)");
    Matcher matcher = pattern.matcher(query);
    if(matcher.find()){
      return matcher.group(1);
    }
    return null;
  }

  private String getValue(String key){
    return "value";
  }

}

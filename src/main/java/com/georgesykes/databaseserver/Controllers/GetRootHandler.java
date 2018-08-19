package com.georgesykes.databaseserver.Controllers;

import com.georgesykes.databaseserver.DbInterface;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetRootHandler implements HttpHandler {

  private DbInterface db;

  public GetRootHandler(DbInterface db){
    this.db = db;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String query = httpExchange.getRequestURI().getQuery();
    String key = this.parseQuery(query);
    String response = (key != null) ? this.getValue(key) : "";
    httpExchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = httpExchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  private String parseQuery(String query) {
    Pattern pattern = Pattern.compile("(?:^|[, ])key=([^,]*)");
    Matcher matcher = pattern.matcher(query);
    if(matcher.find()){
      return matcher.group(1);
    }
    return null;
  }

  private String getValue(String key){
    String value = this.db.getValue(key);
    return (value != null) ? value : "";
  }

}

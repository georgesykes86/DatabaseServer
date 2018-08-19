package com.georgesykes.databaseserver.unit;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgesykes.databaseserver.Controllers.GetRootHandler;
import com.georgesykes.databaseserver.DbInterface;
import com.sun.net.httpserver.HttpExchange;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetRootHandlerTest {

  private GetRootHandler handler;

  @Mock
  DbInterface db;

  @Mock
  HttpExchange exchange;

  @Mock
  URI uri;

  @Mock
  OutputStream os;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    handler = new GetRootHandler(db);
    when(exchange.getRequestURI()).thenReturn(uri);
    when(exchange.getResponseBody()).thenReturn(os);
  }

  @Test
  public void handleRequestTest() throws IOException {
    when(uri.getQuery()).thenReturn("key=newKey");
    when(db.getValue("newKey")).thenReturn("value");
    handler.handle(exchange);
    verify(exchange).sendResponseHeaders(200, "value".getBytes().length);
    verify(os).write("value".getBytes());
  }


  @Test
  public void handleRequestWithoutKeyTest() throws IOException {
    when(uri.getQuery()).thenReturn("other=value");
    handler.handle(exchange);
    verify(exchange).sendResponseHeaders(200, "".getBytes().length);
    verify(os).write("".getBytes());
  }

  @Test
  public void handleRequestWithIncorrectKeyTest() throws IOException {
    when(uri.getQuery()).thenReturn("newkey=wrongKey");
    handler.handle(exchange);
    verify(db, never()).getValue("wrongKey");
  }

  @Test
  public void handleRequestOnlyUsesFirstKey() throws IOException {
    when(uri.getQuery()).thenReturn("key=firstKey,key=secondKey");
    handler.handle(exchange);
    verify(db, times(1)).getValue("firstKey");
  }

}

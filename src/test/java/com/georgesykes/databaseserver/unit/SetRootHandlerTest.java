package com.georgesykes.databaseserver.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgesykes.databaseserver.Controllers.SetRootHandler;
import com.georgesykes.databaseserver.DbInterface;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetRootHandlerTest {

  private SetRootHandler handler;

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
    handler = new SetRootHandler(db);
    when(exchange.getResponseBody()).thenReturn(os);
    when(exchange.getRequestURI()).thenReturn(uri);
  }

  @Test
  public void handleSetRequest() throws IOException {
    when(uri.getQuery()).thenReturn("key=value");
    handler.handle(exchange);
    verify(db).setValue("key", "value");
  }

  @Test
  public void handleRequestNoQueryString() throws IOException {
    when(uri.getQuery()).thenReturn("");
    handler.handle(exchange);
    verify(db, never()).setValue(any(), any());
  }

  @Test
  public void handleRequestMultipleKeyValues() throws IOException {
    when(uri.getQuery()).thenReturn("key1=value1&key2=value2");
    handler.handle(exchange);
    verify(db).setValue("key1", "value1");
    verify(db).setValue("key2", "value2");
  }

}

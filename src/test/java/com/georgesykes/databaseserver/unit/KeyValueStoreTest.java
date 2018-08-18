package com.georgesykes.databaseserver.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.georgesykes.databaseserver.KeyValueStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeyValueStoreTest {

  private KeyValueStore store;

  @BeforeEach
  public void setUp(){
    store = new KeyValueStore();
    store.setValue("test", "testValue");
    store.setValue("otherTest", "differentValue");
  }

  @Test
  public void getsValueTest() {
    assertEquals("testValue", store.getValue("test"));
  }

  @Test
  public void getsOtherValueTest() {
    assertEquals("differentValue", store.getValue("otherTest"));
  }

  @Test
  public void returnNullTest(){
    assertEquals(null, store.getValue("randomKey"));
  }

}

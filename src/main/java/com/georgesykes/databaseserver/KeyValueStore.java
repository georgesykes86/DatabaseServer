package com.georgesykes.databaseserver;

import java.util.HashMap;

public class KeyValueStore implements DbInterface {

  private static HashMap<String, String> db;

  public KeyValueStore(){
    this.db = new HashMap<>();
  }

  @Override
  public String getValue(String key) {
    return this.db.get(key);
  }

  @Override
  public void setValue(String key, String value){
    this.db.put(key, value);
  }
}

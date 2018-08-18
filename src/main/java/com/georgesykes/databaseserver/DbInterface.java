package com.georgesykes.databaseserver;

public interface DbInterface {

  String getValue(String key);

  void setValue(String key, String value);

}

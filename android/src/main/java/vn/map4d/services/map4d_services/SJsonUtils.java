package vn.map4d.services.map4d_services;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class SJsonUtils {
  static Map<String, Object> jsonToMap(@NonNull JSONObject json) throws JSONException {
    Map<String, Object> retMap = new HashMap<>();

    if(json != JSONObject.NULL) {
      retMap = toMap(json);
    }
    return retMap;
  }

  @NonNull
  private static Map<String, Object> toMap(@NonNull JSONObject object) throws JSONException {
    Map<String, Object> map = new HashMap<>();

    Iterator<String> keysItr = object.keys();
    while(keysItr.hasNext()) {
      String key = keysItr.next();
      Object value = object.get(key);

      if(value instanceof JSONArray) {
        value = toList((JSONArray) value);
      }

      else if(value instanceof JSONObject) {
        value = toMap((JSONObject) value);
      }
      map.put(key, value);
    }
    return map;
  }

  @NonNull
  private static List<Object> toList(@NonNull JSONArray array) throws JSONException {
    List<Object> list = new ArrayList<>();
    for(int i = 0; i < array.length(); i++) {
      Object value = array.get(i);
      if(value instanceof JSONArray) {
        value = toList((JSONArray) value);
      }

      else if(value instanceof JSONObject) {
        value = toMap((JSONObject) value);
      }
      list.add(value);
    }
    return list;
  }
}

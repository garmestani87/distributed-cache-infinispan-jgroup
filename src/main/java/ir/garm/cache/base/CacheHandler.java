package ir.garm.cache.base;


import java.util.Map;

public interface CacheHandler {

    String get(String key);

    Map<String, Map<String, String>> getAll();

    boolean put(String key, String object);

    boolean put(String key, String object, long timeout);

    void remove(String key);

}
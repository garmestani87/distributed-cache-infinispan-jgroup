package ir.garm.cache.base;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class CacheHandlerImpl implements CacheHandler {

    private final Cache<String, String> cacheToEvict;
    private Collection<EmbeddedCacheManager> cacheManagers;

    public CacheHandlerImpl(Cache<String, String> cacheToEvict) {
        this.cacheToEvict = cacheToEvict;
    }

    public CacheHandlerImpl(Cache<String, String> cacheToEvict,
                            Collection<EmbeddedCacheManager> cacheManagers) {
        this(cacheToEvict);
        this.cacheManagers = cacheManagers;

    }

    @Override
    public String get(String key) {
        String ans = null;
        try {
            ans = this.cacheToEvict.get(key);
        } catch (Exception e) {
            log.info("this key is not available in local cache.");
        }
        return ans;
    }

    @SneakyThrows
    @Override
    public boolean put(String key, String object) {
        boolean ok = true;
        try {
            this.cacheToEvict.put(key, object);
        } catch (Exception e) {
            log.error("error in put key to cache", e);
            ok = false;
        }
        return ok;
    }

    @Override
    public void remove(String key) {
        try {
            this.cacheToEvict.remove(key);
        } catch (Exception ex) {
            log.info("error in remove key from cache", ex);
        }
    }

    @Override
    public boolean put(String key, String object, long timeout) {
        boolean ok = true;
        try {
            this.cacheToEvict.put(key, object, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("error in put key to cache with timeout", e);
            ok = false;
        }
        return ok;
    }

    @Override
    public Map<String, Map<String, String>> getAll() {
        final Map<String, Map<String, String>> allCaches = new HashMap<>();
        for (final EmbeddedCacheManager cacheManager : cacheManagers) {
            for (final String cacheName : cacheManager.getCacheNames()) {
                allCaches.put(cacheName, new HashMap<>(cacheManager.getCache(cacheName)));
            }
        }
        return allCaches;
    }
}
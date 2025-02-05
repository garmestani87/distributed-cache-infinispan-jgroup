package ir.garm.cache.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.garm.cache.base.CacheHandlerImpl;
import ir.garm.cache.domain.dto.AccessTokenDto;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class TokenCache<T> extends CacheHandlerImpl {

    private static final Logger log = LoggerFactory.getLogger(TokenCache.class);

    @Autowired
    public TokenCache(Collection<EmbeddedCacheManager> cacheManagers,
                      @Qualifier("getEvictCache") Cache<String, String> cacheToEvict) {

        super(cacheToEvict, cacheManagers);
    }

    public T get(String key, Class<T> objectClass) {
        String val = super.get(key);
        T ans = null;
        try {
            ans = new ObjectMapper().readValue(val, objectClass);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return ans;
    }

    public boolean put(String key, T object, Class<T> objectClass) {
        boolean ok = true;
        try {
            super.put(key, new ObjectMapper().writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            ok = false;
        }
        return ok;
    }

    public void remove(String key) {
        super.remove(key);
    }

    public boolean put(String key, T object, Class<T> objectClass, long timeout) {
        boolean ok = true;
        try {
            super.put(key, new ObjectMapper().writeValueAsString(object), timeout);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            ok = false;
        }
        return ok;
    }

    public Map<String, Map<String, String>> getAll() {
        return super.getAll();
    }
}

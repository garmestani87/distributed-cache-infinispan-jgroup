package ir.garm.cache.config;

import ir.garm.cache.domain.dto.AccessTokenDto;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Cache<String, SimpleKey> cacheSimpleKey(
            @Qualifier("defaultClusteredInfinispanCacheManager") EmbeddedCacheManager defaultCacheManager) {

        return defaultCacheManager.getCache();
    }

    @Bean
    public Cache<String, String> getEvictCache(
            @Qualifier("defaultClusteredInfinispanCacheManager") EmbeddedCacheManager defaultCacheManager) {

        return defaultCacheManager.getCache();
    }

    @Bean
    @Primary
    public Cache<String, String> hitCache(
            @Qualifier("defaultClusteredInfinispanCacheManager") EmbeddedCacheManager defaultCacheManager) {

        return defaultCacheManager.getCache();
    }

}

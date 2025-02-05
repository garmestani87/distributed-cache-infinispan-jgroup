package ir.garm.cache.config;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JGroupsConfig {

    private static final Logger log = LoggerFactory.getLogger(JGroupsConfig.class);
    private final CacheProperties properties;

    public JGroupsConfig(CacheProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public CacheManager clusteredInfinispanCacheManager(
            @Qualifier("defaultClusteredInfinispanCacheManager") DefaultCacheManager defaultCacheManager) {

        return new SpringEmbeddedCacheManager(defaultCacheManager);
    }

    @Bean
    @Qualifier("defaultClusteredInfinispanCacheManager")
    @Primary
    EmbeddedCacheManager defaultClusteredInfinispanCacheManager() throws Exception {

        log.info("************Infinispan Config File {}*******", properties.getInfinispan().getClustered());
        return new DefaultCacheManager(properties.getInfinispan().getClustered());

    }

}

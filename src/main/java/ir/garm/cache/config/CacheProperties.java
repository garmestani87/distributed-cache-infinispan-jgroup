package ir.garm.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cache", ignoreUnknownFields = false)
public class CacheProperties {

    private final Infinispan infinispan = new Infinispan();

    @Data
    public static class Infinispan {
        private String clustered;
    }

}

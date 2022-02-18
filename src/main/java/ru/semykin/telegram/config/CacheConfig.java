package ru.semykin.telegram.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private final Environment env;

    public CacheConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(String name) {

                String propertyName;

                propertyName = String.format("cache.%s.ttl", name);
                final long ttl = env.getProperty(propertyName, Long.class, 0L);

                propertyName = String.format("cache.%s.max-size", name);
                final long maxSize = env.getProperty(propertyName, Long.class, 10L);

                final ConcurrentMap map = CacheBuilder.newBuilder()
                        .expireAfterWrite(ttl, TimeUnit.SECONDS)
                        .maximumSize(maxSize)
                        .build()
                        .asMap();
                return new ConcurrentMapCache(name, map, true);
            }
        };
    }
}

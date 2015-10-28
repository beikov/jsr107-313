package com.github.jsr107;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class CacheManagerProducer {
    
    @Produces
    @ApplicationScoped
    public CacheManager getCacheManager() {
        return Caching.getCachingProvider().getCacheManager();
    }
}

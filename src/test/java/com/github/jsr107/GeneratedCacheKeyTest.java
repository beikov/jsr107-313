package com.github.jsr107;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.annotation.CacheResult;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.junit.Assert;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jsr107.ri.annotations.DefaultCacheKeyGenerator;
import org.jsr107.ri.annotations.DefaultGeneratedCacheKey;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class GeneratedCacheKeyTest {
    
    private static final String CACHE_NAME = "test";
    
    @Inject
    private CacheManager cacheManager;
    @Inject
    private Instance<GeneratedCacheKeyTest> self;
    
    @CacheResult(cacheName = CACHE_NAME)
    public Object getObject(String param) {
        return param;
    }
    
    @Test
    public void test() {
        String key = "abc";
        
        Object result = self.get().getObject(key);
        
        Cache<Object, Object> cache = cacheManager.getCache(CACHE_NAME);
        Object actualResult = cache.get(createActualKey(key));
        
        Assert.assertEquals(result, actualResult);
    }

    private Object createActualKey(String key) {
        // NOTE: we have to use the cache key implementation that is used by the
        // cdi annotation integration module, because of the equals-hashCode implementation
        return new DefaultGeneratedCacheKey(new Object[]{ key });
    }
}

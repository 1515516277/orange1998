package com.ming.shiro.cache;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.ehcache.EhCache;

public class realms extends EhCache {


    public realms(Ehcache cache) {
        super(cache);
    }

}

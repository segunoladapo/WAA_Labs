package edu.mum.waa.springsecuritylab.service;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.UUID;

@Service
public class EhTokenService {

    @Autowired
    private CacheManager cacheManager;

    public boolean contains(String key){
        Cache cache = cacheManager.getCache("tokenCache");
        return cache.get(key) != null;
    }

    public String setToken(Authentication auth){
        Cache cache =  cacheManager.getCache("tokenCache");
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        Element element = new Element(token, auth);
        cache.put(element);
        return token;
    }

    public Authentication retrieve(String token){
        return (Authentication) cacheManager.getCache("tokenCache").get(token).getObjectValue();
    }
}

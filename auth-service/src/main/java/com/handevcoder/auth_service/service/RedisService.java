package com.handevcoder.auth_service.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redis;

    public void blackListToken(String token) {
        redis.opsForValue().set("blacklist:" + token, "", Duration.ofMinutes(5));
    }

    public boolean isTokenBlacklisted(String token) {
        return redis.hasKey("blacklist:" + token);
    }
}

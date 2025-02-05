package ir.garm.cache.service;

import ir.garm.cache.domain.dto.AccessTokenDto;

public interface TokenService {
    AccessTokenDto loadToken(String key);
}

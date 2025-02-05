package ir.garm.cache.service.impl;

import ir.garm.cache.domain.dto.AccessTokenDto;
import ir.garm.cache.service.TokenService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Cacheable("TOKEN_CACHE")
    @Override
    public AccessTokenDto loadToken(String key) {

        final AccessTokenDto tokenDto = new AccessTokenDto();
        tokenDto.setCreateAt(10L);
        tokenDto.setAccess_token("access_token");
        tokenDto.setExpires_in("expires_in");
        tokenDto.setRefresh_token("refresh_token");
        tokenDto.setToken_type("token_type");

        return tokenDto;
    }
}

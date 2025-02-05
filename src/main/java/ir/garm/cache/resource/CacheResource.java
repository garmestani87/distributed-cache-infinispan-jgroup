package ir.garm.cache.resource;

import ir.garm.cache.domain.dto.AccessTokenDto;
import ir.garm.cache.service.TokenService;
import ir.garm.cache.service.cache.TokenCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/cache")
public class CacheResource {

    private final TokenCache<AccessTokenDto> tokenCache;
    private final TokenService tokenService;

    public CacheResource(TokenCache<AccessTokenDto> tokenCache, TokenService tokenService) {
        this.tokenCache = tokenCache;
        this.tokenService = tokenService;
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllCaches() {
        return ResponseEntity.ok(tokenCache.getAll().toString());
    }

    @GetMapping("/hit/{key}")
    public ResponseEntity<AccessTokenDto> hit(@PathVariable String key) {
        return ResponseEntity.ok(tokenService.loadToken(key));
    }

    @GetMapping("/get/{key}")
    public ResponseEntity<AccessTokenDto> get(@PathVariable String key) {
        return ResponseEntity.ok(tokenCache.get(key, AccessTokenDto.class));
    }

    @GetMapping("/put/{key}")
    public ResponseEntity<Boolean> put(@PathVariable String key) throws URISyntaxException {

        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCreateAt(10L);
        accessTokenDto.setAccess_token("access_token");
        accessTokenDto.setExpires_in("expires_in");
        accessTokenDto.setRefresh_token("refresh_token");
        accessTokenDto.setToken_type("token_type");

        return ResponseEntity.ok(tokenCache.put(key, accessTokenDto, AccessTokenDto.class));
    }

    @GetMapping("/put/{key}/{expire}")
    public ResponseEntity<Boolean> putWithExpireTime(@PathVariable String key, @PathVariable Long expire) throws URISyntaxException {

        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCreateAt(10L);
        accessTokenDto.setAccess_token("access_token");
        accessTokenDto.setExpires_in("expires_in");
        accessTokenDto.setRefresh_token("refresh_token");
        accessTokenDto.setToken_type("token_type");

        return ResponseEntity.ok(tokenCache.put(key, accessTokenDto, AccessTokenDto.class, expire));
    }
}

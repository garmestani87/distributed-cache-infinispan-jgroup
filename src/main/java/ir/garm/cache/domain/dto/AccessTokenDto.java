package ir.garm.cache.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AccessTokenDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String access_token;
    private Long createAt;
}

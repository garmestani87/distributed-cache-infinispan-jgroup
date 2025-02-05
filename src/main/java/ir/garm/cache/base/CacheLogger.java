package ir.garm.cache.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class CacheLogger {

    @AfterReturning(pointcut = "execution(* ir.garm.cache.base.CacheHandler.get(..))", returning = "object")
    public void find(Object object) {
        log.info("Infinispan >> {}", object);
    }

    @AfterReturning(pointcut = "execution(* ir.garm.cache.base.CacheHandler.put(..))", returning = "object")
    public void save(Object object) {
        log.info("Infinispan [Cached] >> {}", object);
    }


}
package ynu.edu.consumer.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-provider1")
public interface Service1FeignClient {
    
    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "fallback")
    @GetMapping("/service1/test")
    String test();
    
    default String fallback(Throwable t) {
        return "服务1降级响应：" + t.getMessage();
    }
} 
package ynu.edu.consumer.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-provider2")
public interface Service2FeignClient {
    
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "fallback")
    @GetMapping("/service2/test")
    String test();
    
    default String fallback(Throwable t) {
        return "服务2降级响应：" + t.getMessage();
    }
} 
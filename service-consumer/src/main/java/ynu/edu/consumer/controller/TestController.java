package ynu.edu.consumer.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.edu.consumer.feign.Service1FeignClient;
import ynu.edu.consumer.feign.Service2FeignClient;

@RestController
public class TestController {

    @Autowired
    private Service1FeignClient service1FeignClient;

    @Autowired
    private Service2FeignClient service2FeignClient;

    @GetMapping("/test/service1")
    public String testService1() {
        return service1FeignClient.test();
    }

    @GetMapping("/test/service2")
    public String testService2() {
        return service2FeignClient.test();
    }

    @Bulkhead(name = "defaultBulkhead", fallbackMethod = "bulkheadFallback")
    @GetMapping("/test/bulkhead")
    public String testBulkhead() {
        return service1FeignClient.test();
    }

    @RateLimiter(name = "defaultRateLimiter", fallbackMethod = "rateLimiterFallback")
    @GetMapping("/test/ratelimiter")
    public String testRateLimiter() {
        return "RateLimiter测试成功";
    }

    public String bulkheadFallback(Throwable t) {
        return "Bulkhead降级响应：" + t.getMessage();
    }

    public String rateLimiterFallback(Throwable t) {
        return "RateLimiter降级响应：" + t.getMessage();
    }
} 
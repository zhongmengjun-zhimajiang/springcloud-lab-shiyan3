package ynu.edu.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/service1/test")
    public String test() {
        // 模拟随机失败
        if (Math.random() < 0.4) {  // 40%的概率失败
            throw new RuntimeException("服务1随机失败");
        }
        return "服务1正常响应";
    }
} 
package ynu.edu.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/service2/test")
    public String test() {
        // 模拟随机慢调用
        if (Math.random() < 0.4) {  // 40%的概率慢调用
            try {
                Thread.sleep(3000);  // 延迟3秒
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return "服务2正常响应";
    }
} 
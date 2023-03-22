package top.meethigher.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 模拟接口
 *
 * @author chenchuancheng
 * @since 2023/3/22 15:21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String get() {
        return "This is a get method";
    }

    @PostMapping("/post")
    public String post() {
        return "This is a post method";
    }

    @PostMapping("/annotation")
    @CrossOrigin
    public String annotation() {
        return "注解实现跨域";
    }

}

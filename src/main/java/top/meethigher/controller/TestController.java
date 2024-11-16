package top.meethigher.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file) {
        return file.getOriginalFilename();
    }

    @GetMapping("/wait")
    public String wait(@RequestParam(value = "page", required = false) String test) {
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return test;
    }

    @GetMapping("/headers")
    public Map<String, String> headers(HttpServletRequest httpServletRequest) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}

SpringBoot配置跨域三种方式，启动项目进行测试。访问http://127.0.0.1:4321/

# 局部注解

```java
@PostMapping("/annotation")
@CrossOrigin
public String annotation() {
    return "注解实现跨域";
}
```

# 第一种全局

```java
/**
 * 方式一
 *
 * @see <a href="https://blog.csdn.net/qq_37651252/article/details/106630443">跨域配置方式一</a>
 */
@Bean
public CorsFilter first() {
    CorsConfiguration config = new CorsConfiguration();
    //允许所有域名进行跨域调用
    //config.addAllowedOrigin("*");//springboot2+不适用该方法
    config.addAllowedOriginPattern("*");
    //允许跨越发送cookie
    config.setAllowCredentials(true);
    //放行全部原始头信息
    config.addAllowedHeader("*");
    //允许所有请求方法跨域调用，使用大写的方可
    config.addAllowedMethod("GET");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}
```

# 第二种全局

```java
/**
 * 方法二
 */
@Bean
public FilterRegistrationBean second() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    //注入过滤器
    registrationBean.setFilter((servletRequest, servletResponse, filterChain) -> {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //响应头设置
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        //响应类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        //允许跨越发送cookie
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    });
    //过滤器名称
    registrationBean.setName("CrossOrigin");
    //拦截规则
    registrationBean.addUrlPatterns("/*");
    //过滤器顺序
    registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

    return registrationBean;
}
```

# 第三种全局

```java
@Component
public class SystemWebMvcConfigurer extends WebMvcConfigurationSupport {
    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
```


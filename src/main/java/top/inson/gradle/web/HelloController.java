package top.inson.gradle.web;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.inson.gradle.client.CustomSocketClient;

/**
 * @author jingjitree
 * @description
 * @date 2023/4/20 17:32
 */
@Slf4j
@RestController
@RequestMapping(value = "/hello")
@RequiredArgsConstructor
public class HelloController {
    private final CustomSocketClient javaWebSocketClient;


    @GetMapping("/test")
    public String test() {

        return "您好呀,hello world";
    }

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestBody String msg) {
        JSONObject msgObj = JSONUtil.parseObj(msg);
        log.info("请求内容msg：{}，msgObj：{}", msg, msgObj);


//        javaWebSocketClient.getWebSocketClient()


        return "success";
    }



}

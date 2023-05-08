package top.inson.gradle.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author jingjitree
 * @description
 * @date 2023/4/20 11:35
 */
@Slf4j
@Component
@Deprecated
public class CustomSocketClient {

    /**
     * 连接websocket
     * @param url
     * @return
     */
    @SneakyThrows
    public WebSocketClient getWebSocketClient(String url){
        URI uri = new URI(url);

        //创建客户端连接对象
        WebSocketClient client = new WebSocketClient(uri){
            @Override
            public void onOpen(ServerHandshake serverHandshake) {

                log.info("建立连接");
            }

            @Override
            public void onMessage(String message) {

                log.info("接收到服务端消息：{}", message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

                log.info("关闭连接");
            }

            @Override
            public void onError(Exception ex) {

                log.warn("异常情况, {}", ex.getMessage());
            }
        };
        //请求与服务器建立连接
        client.connect();
        //判断连接状态，0.请求中，1.已建立，其它都是连接失败
        while (client.getReadyState().ordinal() == 0){
            log.info("正在连接中");
            Thread.sleep(500);
        }

        if (client.getReadyState().ordinal() == 1){
            log.info("成功建立连接");
            return client;
        }

        return null;
    }

}

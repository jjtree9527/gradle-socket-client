package top.inson.gradle.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author jingjitree
 * @description socket同步客户端
 * @date 2023/4/21 16:42
 */
@Slf4j
public class CustomSyncSocketClient extends WebSocketClient {


    public CustomSyncSocketClient(URI serverUri) throws Exception{
        super(serverUri);
        log.info("连接地址uri：{}", serverUri);
        //请求与服务器建立连接
        this.connect();
        //判断连接状态，0.请求中，1.已建立，其它都是连接失败
        while (this.getReadyState().ordinal() == 0){
            log.info("正在连接中");
            Thread.sleep(500);
        }
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        log.info("连接成功");
    }

    @Override
    public void onMessage(String message) {
        log.info("收到消息：{}", message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("连接关闭");
    }

    @Override
    public void connect() {
        log.info("开始连接");
        super.connect();
    }

    @Override
    public void onError(Exception e) {
        log.warn("异常情况：", e);
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMsg(String message){
        log.info("发送消息内容msg：{}", message);
        //回调
        super.send(message);
    }

}

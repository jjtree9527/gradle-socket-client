package top.inson.gradle;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import org.junit.jupiter.api.Test;
import top.inson.gradle.client.CustomSyncSocketClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jingjitree
 * @description
 * @date 2023/4/19 18:11
 */
public class TestWebSocket {

    @Test
    public void connectDev(){
        String socketUri = "ws://192.168.0.104:9001/websocket/server";
        /*
        URI uri = null;
        try {
            uri = new URI(socketUri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CustomSyncSocketClient socketClient = new CustomSyncSocketClient(uri);
        socketClient.connect();
        int ordinal = socketClient.getReadyState().ordinal();
        Console.log("状态：" + ordinal);
        if (socketClient.hasConn.get()){

            socketClient.sendMsg("你好，请问收到啦", null);
        }
        */

        try {
            URI uri = new URI(socketUri);
            CustomSyncSocketClient socketClient = new CustomSyncSocketClient(uri);
            if (ObjUtil.isNotNull(socketClient)){
                socketClient.sendMsg("你好呀");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void connectSocket() throws Exception{
        String ip = "119.23.51.70";
        String port = "9503";
        String token = "c8302352b442b0d36a316e6f9deb9a88";
        String apiUrl = "http%3A%2F%2Fgzf.jixiangwulian.cn";

        Map<String, Object> parMap = MapUtil.builder(new HashMap<String, Object>())
                .put("token", token)
                .put("apiUrl", apiUrl)
                .build();

        String params = StrUtil.format("?token={token}&apiUrl={apiUrl}", parMap);
        String socketUri = StrUtil.builder().append("ws://")
                .append(ip).append(":").append(port)
                .append(params).toString();
        Console.log("socket连接地址：{}", socketUri);
        URI uri = new URI(socketUri);
        CustomSyncSocketClient socketClient = new CustomSyncSocketClient(uri);
        socketClient.sendMsg("你好");

        /*
        CustomSocketClient client = new CustomSocketClient();
        WebSocketClient socketClient = client.getWebSocketClient(socketUri);
        if (ObjUtil.isNotNull(socketClient)) {
            socketClient.send("你好，收到消息了吗？");
        }
        */
    }

    @Test
    public void decodeAes() {
        String key = "JXWL_GZF_c8302352b442b0d36a316e6f9deb9a88";
        key = StrUtil.subPre(DigestUtil.md5Hex(key), 16);
        Console.log(key);
        String code = "16Zk6uW6Beg+6sV8Z644wsPr7RNj6eqivMGD47oyPttHAYDI7DIfjew8kjS2eOQLRrPufynJ1/sO6DNcwqxU7A==";

        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(CharsetUtil.CHARSET_UTF_8));
        String source = aes.decryptStr(Base64.decode(code));

        Console.log("原文：{}", source);
    }

}

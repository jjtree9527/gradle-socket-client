package top.inson.gradle.callback;

/**
 * @author jingjitree
 * @description websock 同步回调接口
 * @date 2023/4/21 16:52
 */
public interface ISocketClientSyncCallback {

    /**
     * 消息回调
     * @param message
     */
    void callback(String message);

}

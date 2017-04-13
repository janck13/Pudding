package org.pudding.transport.api;

import org.pudding.common.protocol.Message;

/**
 * 由其它模块实现.
 *
 * @author Yohann.
 */
public interface Processor {

    /**
     * 读取网络数据.
     *
     * @param channel
     * @param holder
     */
    void handleMessage(Channel channel, Message holder);
}

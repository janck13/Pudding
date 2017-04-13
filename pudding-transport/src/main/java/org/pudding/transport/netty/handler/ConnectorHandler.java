package org.pudding.transport.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;
import org.pudding.common.protocol.Message;
import org.pudding.transport.api.Channel;
import org.pudding.transport.api.Processor;
import org.pudding.transport.netty.NettyChannelFactory;

/**
 * After decoding the message.
 *
 * @author Yohann.
 */
@ChannelHandler.Sharable
public class ConnectorHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ConnectorHandler.class);

    private Processor processor;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Message) {
            Channel channel = NettyChannelFactory.newChannel(ctx.channel());
            processor.handleMessage(channel, (Message) msg);
        } else {
            logger.warn("unexpected msg type received: " + msg.getClass());
            ReferenceCountUtil.release(msg);
        }
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}

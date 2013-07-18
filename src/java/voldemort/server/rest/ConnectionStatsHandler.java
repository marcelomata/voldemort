package voldemort.server.rest;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Handler that monitors active connections to the Voldemort Rest Service
 * 
 */
public class ConnectionStatsHandler extends SimpleChannelHandler {

    private final ConnectionStats connectionStats;

    public ConnectionStatsHandler(ConnectionStats connectionStats) {
        this.connectionStats = connectionStats;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        connectionStats.reportChannelConnect();
        ctx.sendUpstream(e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        connectionStats.reportChannelDisconnet();
        ctx.sendUpstream(e);
    }

}

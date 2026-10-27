package com.amazon.alexa.voice.core.channel;

import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ChannelCoordinator {
    private final Channel.Listener channelListener = new Channel.SimpleListener() { // from class: com.amazon.alexa.voice.core.channel.ChannelCoordinator.1
        @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBecameActive(Channel channel) {
            if (ChannelCoordinator.this.findPriorityChannel(null) == channel) {
                channel.bringToForeground();
            }
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToForeground(Channel channel) {
            if (channel == ChannelCoordinator.this.current) {
                return;
            }
            Channel channel2 = ChannelCoordinator.this.current;
            ChannelCoordinator.this.current = channel;
            if (channel2 != null) {
                channel2.bringToBackground();
            }
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.SimpleListener, com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToBackground(Channel channel) {
            if (channel != ChannelCoordinator.this.current) {
                return;
            }
            ChannelCoordinator channelCoordinator = ChannelCoordinator.this;
            channelCoordinator.current = channelCoordinator.findPriorityChannel(channelCoordinator.current);
            if (ChannelCoordinator.this.current != null) {
                ChannelCoordinator.this.current.bringToForeground();
            }
        }
    };
    private final List<Channel> channels = new ArrayList();
    private volatile Channel current;

    public void attach(Channel... channelArr) {
        attach(Arrays.asList(channelArr));
    }

    public void attach(Collection<? extends Channel> collection) {
        Preconditions.notNull(collection, "channels == null");
        Iterator<? extends Channel> it = collection.iterator();
        while (it.hasNext()) {
            attach(it.next());
        }
    }

    public void attach(Channel channel) {
        Preconditions.notNull(channel, "channel == null");
        synchronized (this.channels) {
            this.channels.add(channel);
        }
        channel.addListener(this.channelListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Channel findPriorityChannel(Channel channel) {
        Channel channel2;
        synchronized (this.channels) {
            int size = this.channels.size();
            channel2 = null;
            for (int i = 0; i < size; i++) {
                Channel channel3 = this.channels.get(i);
                if (channel != channel3 && !channel3.isInactive() && (channel2 == null || channel3.getPriority() > channel2.getPriority())) {
                    channel2 = channel3;
                }
            }
        }
        return channel2;
    }
}

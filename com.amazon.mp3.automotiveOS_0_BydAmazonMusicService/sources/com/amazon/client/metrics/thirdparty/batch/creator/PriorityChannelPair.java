package com.amazon.client.metrics.thirdparty.batch.creator;

import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;

/* JADX INFO: loaded from: classes2.dex */
public class PriorityChannelPair {
    final Channel mChannel;
    final Priority mPriority;

    public PriorityChannelPair(Priority priority, Channel channel) {
        this.mPriority = priority;
        this.mChannel = channel;
    }

    public Channel getChannel() {
        return this.mChannel;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        Channel channel = this.mChannel;
        int iHashCode = ((channel == null ? 0 : channel.hashCode()) + 31) * 31;
        Priority priority = this.mPriority;
        return iHashCode + (priority != null ? priority.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PriorityChannelPair priorityChannelPair = (PriorityChannelPair) obj;
        return this.mChannel == priorityChannelPair.mChannel && this.mPriority == priorityChannelPair.mPriority;
    }
}

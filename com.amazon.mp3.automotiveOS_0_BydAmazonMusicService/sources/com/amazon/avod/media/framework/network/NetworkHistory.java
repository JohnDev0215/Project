package com.amazon.avod.media.framework.network;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkHistory {
    private final int mContentAccessorAverageBandwidthBps;
    private final long mHeuristicAverageBandwidthBps;
    private final long mHeuristicStdevBandwidthBps;
    private final ByteBuffer mHeuristicsState;
    private final String mNetworkName;

    public NetworkHistory(@Nonnull String str, int i, @Nullable ByteBuffer byteBuffer, long j, long j2) {
        this.mNetworkName = (String) Preconditions.checkNotNull(str);
        this.mContentAccessorAverageBandwidthBps = i;
        this.mHeuristicsState = byteBuffer;
        this.mHeuristicAverageBandwidthBps = j;
        this.mHeuristicStdevBandwidthBps = j2;
    }

    @Nonnull
    public String getNetworkName() {
        return this.mNetworkName;
    }

    public int getAverageBandwidthBps() {
        return this.mContentAccessorAverageBandwidthBps;
    }

    @Nullable
    public ByteBuffer getHeuristicsState() {
        return this.mHeuristicsState;
    }

    public long getHeuristicAverageBandwidthBps() {
        return this.mHeuristicAverageBandwidthBps;
    }

    public long getHeuristicStdevBandwidthBps() {
        return this.mHeuristicStdevBandwidthBps;
    }
}

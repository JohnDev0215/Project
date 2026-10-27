package com.android.car.stats;

import android.car.vms.VmsLayer;

/* JADX INFO: loaded from: classes3.dex */
class VmsClientStats {
    private long mDroppedBytes;
    private long mDroppedPackets;
    private final int mLayerChannel;
    private final int mLayerType;
    private final int mLayerVersion;
    private final Object mLock = new Object();
    private long mRxBytes;
    private long mRxPackets;
    private long mTxBytes;
    private long mTxPackets;
    private final int mUid;

    VmsClientStats(int uid, VmsLayer layer) {
        this.mUid = uid;
        this.mLayerType = layer.getType();
        this.mLayerChannel = layer.getSubtype();
        this.mLayerVersion = layer.getVersion();
    }

    VmsClientStats(VmsClientStats other) {
        synchronized (other.mLock) {
            this.mUid = other.mUid;
            this.mLayerType = other.mLayerType;
            this.mLayerChannel = other.mLayerChannel;
            this.mLayerVersion = other.mLayerVersion;
            this.mTxBytes = other.mTxBytes;
            this.mTxPackets = other.mTxPackets;
            this.mRxBytes = other.mRxBytes;
            this.mRxPackets = other.mRxPackets;
            this.mDroppedBytes = other.mDroppedBytes;
            this.mDroppedPackets = other.mDroppedPackets;
        }
    }

    void packetSent(long size) {
        synchronized (this.mLock) {
            this.mTxBytes += size;
            this.mTxPackets++;
        }
    }

    void packetReceived(long size) {
        synchronized (this.mLock) {
            this.mRxBytes += size;
            this.mRxPackets++;
        }
    }

    void packetDropped(long size) {
        synchronized (this.mLock) {
            this.mDroppedBytes += size;
            this.mDroppedPackets++;
        }
    }

    int getUid() {
        return this.mUid;
    }

    int getLayerType() {
        return this.mLayerType;
    }

    int getLayerChannel() {
        return this.mLayerChannel;
    }

    int getLayerVersion() {
        return this.mLayerVersion;
    }

    long getTxBytes() {
        long j;
        synchronized (this.mLock) {
            j = this.mTxBytes;
        }
        return j;
    }

    long getTxPackets() {
        long j;
        synchronized (this.mLock) {
            j = this.mTxPackets;
        }
        return j;
    }

    long getRxBytes() {
        long j;
        synchronized (this.mLock) {
            j = this.mRxBytes;
        }
        return j;
    }

    long getRxPackets() {
        long j;
        synchronized (this.mLock) {
            j = this.mRxPackets;
        }
        return j;
    }

    long getDroppedBytes() {
        long j;
        synchronized (this.mLock) {
            j = this.mDroppedBytes;
        }
        return j;
    }

    long getDroppedPackets() {
        long j;
        synchronized (this.mLock) {
            j = this.mDroppedPackets;
        }
        return j;
    }
}

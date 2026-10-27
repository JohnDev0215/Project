package com.ts.lib.car.hardware.cloudmessage;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes3.dex */
public class CloudMessageEventChangeValue extends CarBaseProperty {
    private static final int MAX_SIZE = 50;
    private static CloudMessageEventChangeValue mPool;
    private static CloudMessageEventChangeValue next;
    private byte[] mCloudMessage;
    private static int flags = 0;
    private static final Object S_POOL_SYNC = new Object();
    private static int sPoolSize = 0;

    public static CloudMessageEventChangeValue obtain() {
        synchronized (S_POOL_SYNC) {
            if (mPool != null) {
                CloudMessageEventChangeValue value = mPool;
                mPool = next;
                next = null;
                flags = 0;
                sPoolSize--;
                return value;
            }
            return new CloudMessageEventChangeValue();
        }
    }

    public void recycle() {
        this.mCloudMessage = null;
        flags = 1;
        synchronized (S_POOL_SYNC) {
            if (sPoolSize < 50) {
                next = mPool;
                mPool = this;
                sPoolSize++;
            }
        }
    }

    public int getPoolSize() {
        int i;
        synchronized (S_POOL_SYNC) {
            i = sPoolSize;
        }
        return i;
    }

    public byte[] getCloudMessage() {
        return this.mCloudMessage;
    }

    public void setCloudMessage(byte[] cloudMessage) {
        this.mCloudMessage = cloudMessage;
    }
}

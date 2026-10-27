package com.engine.tbox.core.data;

import com.engine.tbox.core.utils.LogUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class CountSyncMessage {
    private static final String TAG = "CountSyncMessage";
    private static final long TIMEOUT = 600;
    private CountDownLatch countDownLatch;
    private CountSyncMessage respone;
    private final AtomicInteger count = new AtomicInteger(0);
    private long commonTimeOut = 0;

    public abstract int identify();

    public abstract int maxRetry();

    public void addCount() {
        this.count.getAndIncrement();
    }

    public boolean needSend() {
        return this.count.get() <= maxRetry();
    }

    public void setTimeOut(long timeout) {
        this.commonTimeOut = timeout;
    }

    public boolean waitForSignal() {
        long timeout = TIMEOUT;
        if (this.commonTimeOut != 0) {
            timeout = this.commonTimeOut;
        }
        this.countDownLatch = new CountDownLatch(1);
        LogUtils.d(TAG, "start and wait " + timeout + " for message:" + this.countDownLatch + " msg:" + this, new Object[0]);
        try {
            return this.countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void notifyTheSignal() {
        LogUtils.d("TAG", "notify the signal:" + this.countDownLatch, new Object[0]);
        CountDownLatch countDownLatch = this.countDownLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void addResponse(CountSyncMessage msg) {
        this.respone = msg;
    }

    public CountSyncMessage getRespone() {
        return this.respone;
    }
}

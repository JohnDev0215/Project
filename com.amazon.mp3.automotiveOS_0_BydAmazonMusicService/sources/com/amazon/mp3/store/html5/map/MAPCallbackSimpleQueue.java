package com.amazon.mp3.store.html5.map;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes4.dex */
public class MAPCallbackSimpleQueue extends Observable implements Callback {
    private MAPFuture<Bundle> mFuture;
    private BlockingQueue<Callback> mQueue;

    public MAPCallbackSimpleQueue() {
        this.mQueue = new LinkedBlockingQueue();
    }

    public MAPCallbackSimpleQueue(BlockingQueue<Callback> queue) {
        this.mQueue = queue;
    }

    public void setFuture(MAPFuture<Bundle> future) {
        this.mFuture = future;
    }

    public MAPFuture<Bundle> getFuture() {
        return this.mFuture;
    }

    public boolean add(Callback callback) {
        if (this.mQueue.contains(callback)) {
            return false;
        }
        return this.mQueue.add(callback);
    }

    public MAPCallbackSimpleQueue drainToNewQueue() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        this.mQueue.drainTo(linkedBlockingQueue);
        return new MAPCallbackSimpleQueue(linkedBlockingQueue);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle result) {
        internalEventHandler(result, false);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle result) {
        internalEventHandler(result, true);
    }

    private void internalEventHandler(Bundle result, boolean success) {
        Callback callbackPoll = this.mQueue.poll();
        while (callbackPoll != null) {
            if (success) {
                callbackPoll.onSuccess(result);
            } else {
                callbackPoll.onError(result);
            }
            callbackPoll = this.mQueue.poll();
        }
        this.mFuture = null;
        setChanged();
        notifyObservers(result);
    }
}

package com.engine.tbox.item;

import android.util.ArrayMap;

/* JADX INFO: loaded from: classes.dex */
public class MessageCache {
    private ArrayMap<Integer, TboxMessage> cache = new ArrayMap<>();

    public void addMessage(TboxMessage msg) {
        if (msg == null) {
            return;
        }
        synchronized (this) {
            this.cache.put(Integer.valueOf(msg.identify()), msg);
        }
    }

    public TboxMessage removeMessage(int identify) {
        TboxMessage tboxMessage;
        synchronized (this) {
            tboxMessage = this.cache.get(Integer.valueOf(identify));
        }
        return tboxMessage;
    }

    public void clear() {
        synchronized (this) {
            this.cache.clear();
        }
    }
}

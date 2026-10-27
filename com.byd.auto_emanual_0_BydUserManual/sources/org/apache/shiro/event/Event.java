package org.apache.shiro.event;

import java.util.EventObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class Event extends EventObject {
    private final long timestamp;

    public Event(Object obj) {
        super(obj);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

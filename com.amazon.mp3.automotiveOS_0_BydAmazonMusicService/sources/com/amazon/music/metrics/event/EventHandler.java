package com.amazon.music.metrics.event;

import com.amazon.music.metrics.event.Event;
import java.lang.Exception;

/* JADX INFO: loaded from: classes4.dex */
public abstract class EventHandler<T extends Event, X extends Exception> {
    private Class<T> mEventClass;

    public abstract void handleEvent(T t) throws Exception;

    public void handleSignout() {
    }

    public EventHandler(Class<T> cls) {
        this.mEventClass = cls;
    }

    public Class<T> getEventType() {
        return this.mEventClass;
    }
}

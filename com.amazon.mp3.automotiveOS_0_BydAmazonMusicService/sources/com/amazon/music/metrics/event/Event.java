package com.amazon.music.metrics.event;

import com.amazon.music.metrics.event.Event;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Event<T extends Event> {
    private Class<T> mEventClass;

    public Event(Class<T> cls) {
        this.mEventClass = cls;
    }

    public Class<? extends Event> getType() {
        return this.mEventClass;
    }
}

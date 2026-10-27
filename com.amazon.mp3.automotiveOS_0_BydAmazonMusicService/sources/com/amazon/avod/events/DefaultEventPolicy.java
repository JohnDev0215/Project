package com.amazon.avod.events;

import com.amazon.avod.events.perf.EventManagerProfiler;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultEventPolicy implements EventPolicy {
    private final int mMaxRetries;
    protected final EventManagerProfiler mProfiler;

    public DefaultEventPolicy() {
        this(3);
    }

    public DefaultEventPolicy(int i) {
        this(EventManagerProfiler.getInstance(), i);
    }

    DefaultEventPolicy(@Nonnull EventManagerProfiler eventManagerProfiler, int i) {
        this.mProfiler = (EventManagerProfiler) Preconditions.checkNotNull(eventManagerProfiler);
        this.mMaxRetries = i;
    }

    @Override // com.amazon.avod.events.EventPolicy
    public boolean persistEvent(Event event, EventPersistance eventPersistance) {
        return eventPersistance.persistEvent(event);
    }
}

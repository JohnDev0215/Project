package com.amazon.avod.events.batch;

import com.amazon.avod.events.DefaultEventPolicy;
import com.amazon.avod.events.EventType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BatchedEventPolicy extends DefaultEventPolicy {
    private final EventType mChildEventType;

    public BatchedEventPolicy(@Nonnull EventType eventType, int i) {
        super(i);
        this.mChildEventType = (EventType) Preconditions.checkNotNull(eventType, "childEventType");
    }
}

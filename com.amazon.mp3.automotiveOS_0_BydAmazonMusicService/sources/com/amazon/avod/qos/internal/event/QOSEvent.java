package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.AbstractEvent;
import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.events.batch.BatchedEventHelper;

/* JADX INFO: loaded from: classes2.dex */
public class QOSEvent extends AbstractEvent {
    private final BatchedEventHelper mBatchedEventHelper;

    public QOSEvent(EventData eventData, EventPolicy eventPolicy, BatchedEventHelper batchedEventHelper) {
        super(eventData, eventPolicy);
        this.mBatchedEventHelper = batchedEventHelper;
    }
}

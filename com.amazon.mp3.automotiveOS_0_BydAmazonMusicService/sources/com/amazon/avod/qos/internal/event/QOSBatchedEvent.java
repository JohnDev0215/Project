package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.events.batch.BatchedEvent;
import com.amazon.avod.events.batch.BatchedEventHelper;
import com.amazon.avod.qos.internal.QOSEventConfig;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QOSBatchedEvent extends QOSBaseEvent implements BatchedEvent {
    private final BatchedEventHelper mBatchHelper;

    public QOSBatchedEvent(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy, @Nonnull BatchedEventHelper batchedEventHelper, @Nonnull QOSEventConfig qOSEventConfig) {
        super(eventData, eventPolicy, qOSEventConfig);
        this.mBatchHelper = batchedEventHelper;
    }
}

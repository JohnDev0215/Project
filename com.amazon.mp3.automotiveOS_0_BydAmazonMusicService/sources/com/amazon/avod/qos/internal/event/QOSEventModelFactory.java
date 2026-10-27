package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.DefaultEventPolicy;
import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventModelFactory;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.events.batch.BatchedEventHelper;
import com.amazon.avod.qos.internal.QOSEventConfig;

/* JADX INFO: loaded from: classes2.dex */
public class QOSEventModelFactory implements EventModelFactory {
    private final EventPolicy mEventPolicy = new DefaultEventPolicy(0);
    private final BatchedEventHelper mBatchedEventHelper = new BatchedEventHelper(QOSEventConfig.INSTANCE);

    @Override // com.amazon.avod.events.EventModelFactory
    public QOSEvent createEvent(EventData eventData) {
        return new QOSEvent(eventData, this.mEventPolicy, this.mBatchedEventHelper);
    }
}

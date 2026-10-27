package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventModelFactory;
import com.amazon.avod.events.PlayerEventType;
import com.amazon.avod.events.batch.BatchedEventHelper;
import com.amazon.avod.events.batch.BatchedEventPolicy;
import com.amazon.avod.qos.internal.QOSEventConfig;

/* JADX INFO: loaded from: classes2.dex */
public class QOSBatchedEventModelFactory implements EventModelFactory {
    private final BatchedEventHelper mBatchedEventHelper;
    private final QOSEventConfig mEventConfig;
    private final BatchedEventPolicy mEventPolicy;

    public QOSBatchedEventModelFactory() {
        QOSEventConfig qOSEventConfig = QOSEventConfig.INSTANCE;
        this.mEventConfig = qOSEventConfig;
        this.mEventPolicy = new BatchedEventPolicy(PlayerEventType.QOS, qOSEventConfig.getMaxRetries());
        this.mBatchedEventHelper = new BatchedEventHelper(qOSEventConfig);
    }

    @Override // com.amazon.avod.events.EventModelFactory
    public QOSBatchedEvent createEvent(EventData eventData) {
        return new QOSBatchedEvent(eventData, this.mEventPolicy, this.mBatchedEventHelper, this.mEventConfig);
    }
}

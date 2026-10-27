package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.DefaultEventPolicy;
import com.amazon.avod.events.Event;
import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventModelFactory;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.qos.internal.QOSEventConfig;

/* JADX INFO: loaded from: classes2.dex */
public class QOSImmediateEventModelFactory implements EventModelFactory {
    private final QOSEventConfig mEventConfig;
    private final EventPolicy mImmediateEventPolicy;

    public QOSImmediateEventModelFactory() {
        QOSEventConfig qOSEventConfig = QOSEventConfig.INSTANCE;
        this.mEventConfig = qOSEventConfig;
        this.mImmediateEventPolicy = new DefaultEventPolicy(qOSEventConfig.getMaxRetries());
    }

    @Override // com.amazon.avod.events.EventModelFactory
    public Event createEvent(EventData eventData) {
        return new QOSImmediateEvent(eventData, this.mImmediateEventPolicy, this.mEventConfig);
    }
}

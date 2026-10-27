package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.events.ServiceCallV2Event;
import com.amazon.avod.qos.internal.QOSEventConfig;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class QOSBaseEvent extends ServiceCallV2Event<Void> {
    protected final QOSEventConfig mEventConfig;

    public QOSBaseEvent(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy, @Nonnull QOSEventConfig qOSEventConfig) {
        super(eventData, eventPolicy);
        this.mEventConfig = qOSEventConfig;
    }
}

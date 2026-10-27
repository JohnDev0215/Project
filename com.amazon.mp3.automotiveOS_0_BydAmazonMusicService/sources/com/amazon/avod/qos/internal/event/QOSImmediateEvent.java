package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPolicy;
import com.amazon.avod.qos.internal.QOSEventConfig;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QOSImmediateEvent extends QOSBaseEvent {
    public QOSImmediateEvent(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy, @Nonnull QOSEventConfig qOSEventConfig) {
        super(eventData, eventPolicy, qOSEventConfig);
    }
}

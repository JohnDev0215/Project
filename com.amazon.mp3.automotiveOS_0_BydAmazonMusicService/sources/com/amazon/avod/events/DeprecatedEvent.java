package com.amazon.avod.events;

/* JADX INFO: loaded from: classes2.dex */
public class DeprecatedEvent extends AbstractEvent {
    public DeprecatedEvent(EventData eventData) {
        this(eventData, new DefaultEventPolicy(0));
    }

    DeprecatedEvent(EventData eventData, EventPolicy eventPolicy) {
        super(eventData, eventPolicy);
    }
}

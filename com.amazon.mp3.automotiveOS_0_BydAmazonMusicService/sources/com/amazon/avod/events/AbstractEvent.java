package com.amazon.avod.events;

import com.amazon.avod.events.data.ForwardingEventData;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractEvent extends ForwardingEventData implements Event {
    private final EventPolicy mEventPolicy;

    public AbstractEvent(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy) {
        super(eventData);
        this.mEventPolicy = (EventPolicy) Preconditions.checkNotNull(eventPolicy, "eventPolicy");
    }

    @Override // com.amazon.avod.events.Event
    public boolean queue(EventPersistance eventPersistance) {
        return this.mEventPolicy.persistEvent(this, eventPersistance);
    }
}

package com.amazon.avod.events;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class CompositeEventModelFactory implements EventModelFactory {
    private final Map<EventType, EventModelFactory> mMap = Maps.newConcurrentMap();

    public void registerEventType(@Nonnull EventType eventType, @Nonnull EventModelFactory eventModelFactory) {
        Preconditions.checkNotNull(eventType);
        Preconditions.checkNotNull(eventModelFactory);
        Preconditions.checkState(this.mMap.put(eventType, eventModelFactory) == null, "Event type %s was already registered.", eventType);
    }

    @Override // com.amazon.avod.events.EventModelFactory
    public Event createEvent(@Nonnull EventData eventData) {
        Preconditions.checkNotNull(eventData);
        EventModelFactory eventModelFactory = this.mMap.get(eventData.getType());
        if (eventData.getType() == DeprecatedEventType.DEPRECATED) {
            return new DeprecatedEvent(eventData);
        }
        if (eventModelFactory != null) {
            return eventModelFactory.createEvent(eventData);
        }
        return null;
    }
}

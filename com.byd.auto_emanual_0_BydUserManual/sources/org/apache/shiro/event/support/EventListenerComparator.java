package org.apache.shiro.event.support;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class EventListenerComparator implements Comparator<EventListener> {
    private static final EventClassComparator EVENT_CLASS_COMPARATOR = new EventClassComparator();

    @Override // java.util.Comparator
    public int compare(EventListener eventListener, EventListener eventListener2) {
        if (eventListener == null) {
            return eventListener2 == null ? 0 : -1;
        }
        if (eventListener2 == null) {
            return 1;
        }
        if (eventListener != eventListener2 && !eventListener.equals(eventListener2)) {
            if (eventListener instanceof TypedEventListener) {
                TypedEventListener typedEventListener = (TypedEventListener) eventListener;
                if (eventListener2 instanceof TypedEventListener) {
                    return EVENT_CLASS_COMPARATOR.compare(typedEventListener.getEventType(), ((TypedEventListener) eventListener2).getEventType());
                }
                return -1;
            }
            if (eventListener2 instanceof TypedEventListener) {
                return 1;
            }
        }
        return 0;
    }
}

package com.amazon.avod.events;

/* JADX INFO: loaded from: classes2.dex */
public interface EventPersistance {
    void cleanUpEvents();

    boolean persistEvent(EventData eventData);
}

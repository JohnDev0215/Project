package com.amazon.avod.events.batch;

import com.amazon.avod.events.Event;

/* JADX INFO: loaded from: classes2.dex */
public interface BatchedEvent extends Event {
    @Override // com.amazon.avod.events.Event, com.amazon.avod.events.EventData
    /* synthetic */ int getRetryCount();
}

package com.amazon.avod.events;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface Event extends EventData {
    @Override // com.amazon.avod.events.EventData
    /* synthetic */ int getRetryCount();

    boolean queue(@Nonnull EventPersistance eventPersistance);
}

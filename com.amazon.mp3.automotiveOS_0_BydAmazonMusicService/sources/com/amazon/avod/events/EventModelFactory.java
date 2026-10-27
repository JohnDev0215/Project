package com.amazon.avod.events;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface EventModelFactory {
    @Nullable
    Event createEvent(@Nonnull EventData eventData);
}

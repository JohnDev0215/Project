package com.amazon.avod.events;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface EventPolicy {
    boolean persistEvent(@Nonnull Event event, @Nonnull EventPersistance eventPersistance);
}

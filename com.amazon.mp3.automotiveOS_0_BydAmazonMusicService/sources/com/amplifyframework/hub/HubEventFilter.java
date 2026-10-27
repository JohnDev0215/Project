package com.amplifyframework.hub;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public interface HubEventFilter {
    boolean filter(@NonNull HubEvent<?> hubEvent);
}

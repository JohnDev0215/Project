package com.bugsnag.android.internal;

import androidx.annotation.NonNull;
import com.bugsnag.android.StateEvent;

/* JADX INFO: loaded from: classes5.dex */
public interface StateObserver {
    void onStateChange(@NonNull StateEvent stateEvent);
}

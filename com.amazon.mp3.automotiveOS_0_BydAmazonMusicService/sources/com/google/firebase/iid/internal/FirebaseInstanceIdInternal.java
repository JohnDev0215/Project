package com.google.firebase.iid.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid-interop@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public interface FirebaseInstanceIdInternal {

    /* JADX INFO: compiled from: com.google.firebase:firebase-iid-interop@@17.1.0 */
    @KeepForSdk
    public interface NewTokenListener {
    }

    @KeepForSdk
    void addNewTokenListener(NewTokenListener newTokenListener);

    @Nullable
    @KeepForSdk
    String getToken();

    @NonNull
    @KeepForSdk
    Task<String> getTokenTask();
}

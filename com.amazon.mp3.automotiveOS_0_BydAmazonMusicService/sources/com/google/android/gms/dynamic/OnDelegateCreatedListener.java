package com.google.android.gms.dynamic;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.LifecycleDelegate;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public interface OnDelegateCreatedListener<T extends LifecycleDelegate> {
    @KeepForSdk
    void onDelegateCreated(@NonNull T t);
}

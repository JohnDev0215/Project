package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public interface Predicate<T> {
    @KeepForSdk
    boolean apply(@NonNull T t);
}

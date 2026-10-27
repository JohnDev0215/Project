package com.google.android.gms.cast.framework;

import androidx.annotation.NonNull;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@KeepForSdk
public abstract class TransferCallback {

    @KeepForSdk
    public static final int TRANSFER_TYPE_FROM_CAST_TO_LOCAL = 1;

    @KeepForSdk
    public static final int TRANSFER_TYPE_UNKNOWN = 0;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    @KeepForSdk
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransferType {
    }

    @KeepForSdk
    public void onTransfer(int i, @NonNull SessionState sessionState) {
    }
}

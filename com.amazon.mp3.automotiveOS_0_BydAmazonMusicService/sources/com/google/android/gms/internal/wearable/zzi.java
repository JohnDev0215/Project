package com.google.android.gms.internal.wearable;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CallSuper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class zzi extends Handler {
    public zzi(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        zza(message);
    }

    @CallSuper
    protected void zza(Message message) {
        super.dispatchMessage(message);
    }
}

package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class wd implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ de f651a;
    public final /* synthetic */ Callback b;

    public wd(de deVar, Callback callback) {
        this.f651a = deVar;
        this.b = callback;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        this.f651a.c();
        Callback callback = this.b;
        if (callback != null) {
            callback.onError(bundle);
        }
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        this.f651a.c();
        Callback callback = this.b;
        if (callback != null) {
            callback.onSuccess(bundle);
        }
    }
}

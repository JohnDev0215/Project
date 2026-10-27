package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class DefaultCallback implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final DefaultCallback f203a = new DefaultCallback();

    public static Callback nullToDefault(Callback callback) {
        return callback == null ? f203a : callback;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    @FireOsSdk
    public void onError(Bundle bundle) {
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    @FireOsSdk
    public void onSuccess(Bundle bundle) {
    }
}

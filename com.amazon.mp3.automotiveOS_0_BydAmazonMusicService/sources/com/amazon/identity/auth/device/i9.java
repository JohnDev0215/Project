package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i9 implements Callback {
    public i9(h9 h9Var) {
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        Log.e(ga.a(h9.k), "Registration check failed. This does not mean the device deregistered, this can happen if the network call failed.  Also this will not ever be raised to an application calling one of our apis as this is a background task to check the serverside registration state.");
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a(h9.k), "Registration check succeeded.");
    }
}

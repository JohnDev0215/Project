package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class w6 implements Callback {
    public w6(v6 v6Var) {
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        Log.e(ga.a("MAPJavaScriptBridge"), "Token upgrade failed.");
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a("MAPJavaScriptBridge"), "Token upgrade succeeds.");
    }
}

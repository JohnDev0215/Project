package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class y6 implements Callback {
    public y6(v6 v6Var) {
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        Log.e(ga.a("MAPJavaScriptBridge"), "Switch actor failed.");
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a("MAPJavaScriptBridge"), "Switch actor succeeds.");
    }
}

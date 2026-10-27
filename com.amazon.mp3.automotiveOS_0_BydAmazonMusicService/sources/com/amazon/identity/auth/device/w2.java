package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.DefaultCallback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class w2 extends DefaultCallback {
    public final /* synthetic */ String b;

    public w2(z2 z2Var, String str) {
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.api.DefaultCallback, com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
        String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
        Log.i(ga.a(z2.f), "Error while deregistering account in " + this.b + " flow. ErrorCode:" + i + " ErrorMessage:" + string);
    }

    @Override // com.amazon.identity.auth.device.api.DefaultCallback, com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a(z2.f), "Successfully completed deregistering account in " + this.b + " flow");
    }
}

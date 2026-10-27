package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.token.OAuthTokenManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u8 implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f8 f612a;
    public final /* synthetic */ String b;
    public final /* synthetic */ OAuthTokenManager c;

    public u8(OAuthTokenManager oAuthTokenManager, f8 f8Var, String str) {
        this.c = oAuthTokenManager;
        this.f612a = f8Var;
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        Log.i(ga.a("OAuthTokenManager"), "Failed to get transferred account credential due to " + bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
        OAuthTokenManager.a(this.c, this.f612a, this.b, false);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a("OAuthTokenManager"), "Successfully get transferred account credential");
        OAuthTokenManager.a(this.c, this.f612a, this.b, true);
    }
}

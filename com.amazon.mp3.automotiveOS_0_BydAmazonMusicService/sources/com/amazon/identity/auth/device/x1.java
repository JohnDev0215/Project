package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x1 implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f653a;
    public final /* synthetic */ x3 b;
    public final /* synthetic */ AuthPortalUIActivity c;

    public x1(AuthPortalUIActivity authPortalUIActivity, String str, x3 x3Var) {
        this.c = authPortalUIActivity;
        this.f653a = str;
        this.b = x3Var;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        Log.e(ga.a(AuthPortalUIActivity.Q), "Token upgrade for ConfirmCredential failed.");
        AuthPortalUIActivity.a(this.c, bundle, this.c.b());
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Log.i(ga.a(AuthPortalUIActivity.Q), "Token upgrade for ConfirmCredential succeeded.");
        this.c.a(this.b, this.c.m.c(this.f653a, "com.amazon.dcp.sso.token.oauth.amazon.access_token"));
    }
}

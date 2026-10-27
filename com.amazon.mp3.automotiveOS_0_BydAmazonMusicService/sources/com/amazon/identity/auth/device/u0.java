package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.q0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u0 implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f597a;
    public final /* synthetic */ q0.d b;

    public u0(q0.d dVar, String str) {
        this.b = dVar;
        this.f597a = str;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        String str = q0.f;
        bundle.getString("com.amazon.dcp.sso.ErrorMessage");
        ga.a(str);
        this.b.h.b("FailDeregisterDelegatedAccount");
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        ga.a(q0.f);
    }
}

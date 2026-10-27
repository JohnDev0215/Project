package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.b9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c9 implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b9.c f265a;

    public c9(b9.c cVar) {
        this.f265a = cVar;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        ga.a("TokenJobQueue");
        this.f265a.a();
        this.f265a.f244a.onError(bundle);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        ga.a("TokenJobQueue");
        this.f265a.a();
        this.f265a.f244a.onSuccess(bundle);
    }
}

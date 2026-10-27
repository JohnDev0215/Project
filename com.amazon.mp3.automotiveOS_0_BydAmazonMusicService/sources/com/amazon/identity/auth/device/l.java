package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.c;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RegistrationType f412a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ y5 c;
    public final /* synthetic */ g d;

    public l(g gVar, RegistrationType registrationType, Bundle bundle, y5 y5Var) {
        this.d = gVar;
        this.f412a = registrationType;
        this.b = bundle;
        this.c = y5Var;
    }

    @Override // com.amazon.identity.auth.device.c.b
    public Bundle a(Callback callback) {
        g.a(this.d, this.f412a, this.b, callback, this.c);
        return null;
    }
}

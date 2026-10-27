package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.c;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f466a;
    public final /* synthetic */ String b;
    public final /* synthetic */ y5 c;
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ g e;

    /* JADX INFO: compiled from: DCP */
    public class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Callback f467a;

        public a(Callback callback) {
            this.f467a = callback;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            o oVar = o.this;
            g.a(oVar.e, oVar.f466a, oVar.b);
            this.f467a.onError(bundle);
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            o oVar = o.this;
            g.a(oVar.e, oVar.f466a, oVar.b);
            this.f467a.onSuccess(bundle);
        }
    }

    public o(g gVar, String str, String str2, y5 y5Var, Bundle bundle) {
        this.e = gVar;
        this.f466a = str;
        this.b = str2;
        this.c = y5Var;
        this.d = bundle;
    }

    @Override // com.amazon.identity.auth.device.c.b
    public Bundle a(Callback callback) {
        this.e.b(this.f466a, new a(callback), this.c, this.d);
        return null;
    }
}

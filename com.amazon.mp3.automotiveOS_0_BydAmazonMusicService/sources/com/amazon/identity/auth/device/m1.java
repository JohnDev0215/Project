package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.EnumSet;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m1 implements t1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t1 f426a = null;
    public t1 b = null;
    public final t5 c;

    public m1(t5 t5Var) {
        this.c = t5Var;
    }

    public final t1 a() {
        if (this.f426a == null) {
            t5 t5Var = this.c;
            this.f426a = new u1(t5Var, t5Var.a());
        }
        return this.f426a;
    }

    public final t1 b() {
        if (this.b == null) {
            t5 t5Var = this.c;
            this.b = new n1(t5Var, (i5) t5Var.getSystemService("sso_platform"), new s5(t5Var), true);
        }
        return this.b;
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, y5 y5Var) {
        if (e.a(fa.a(str2))) {
            return b().a(str, fa.a(str2).c, callback, bundle, enumSet, y5Var);
        }
        return a().a(str, str2, callback, bundle, enumSet, y5Var);
    }

    @Override // com.amazon.identity.auth.device.t1
    public Bundle a(String str, String str2) {
        if (e.a(fa.a(str2))) {
            return b().a(str, fa.a(str2).c);
        }
        return a().a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, String str3, Callback callback) {
        if (e.a(fa.a(str2))) {
            return b().a(str, fa.a(str2).c, str3, callback);
        }
        return a().a(str, str2, str3, callback);
    }
}

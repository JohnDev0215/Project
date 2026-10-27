package com.amazon.identity.auth.device;

import android.accounts.Account;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.u7;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l8 extends u7 {
    public static final String e = "com.amazon.identity.auth.device.l8";
    public static l8 f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public u7 f420a;
    public u7 b;
    public final boolean c;
    public final t5 d;

    public l8(t5 t5Var) {
        String str = e;
        ga.c(str, "Constructing RuntimeSwitchableDataStorage");
        this.d = t5Var;
        this.f420a = r7.a(t5Var);
        if (!(la.a("com.amazon.fv", t5Var) && qe.e(t5Var))) {
            if (!(la.a("com.amazon.canary", t5Var) && qe.e(t5Var))) {
                if (t9.d(t5Var)) {
                    ga.b(str, "Runtime isolated mode currently only supported on 3P devices. Please remove runtime isolated entry in manifest if your app is running on 1P devices.");
                    throw new IllegalStateException("Runtime isolated mode currently only supported on 3P devices. Please remove runtime isolated entry in manifest if your app is running on 1P devices.");
                }
                ga.c(str, "Using DistributedDataStorage as SSO storage");
                this.b = y7.a(t5Var);
                this.c = true;
                return;
            }
        }
        ga.c(str, "Using CentralAccountManagerDataStorage as SSO storage");
        this.b = q7.a(t5Var);
        this.c = false;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        return g().a();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        return g().b();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        return g().c(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d() {
        g().d();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
        g().e();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
        this.b.f();
    }

    public final u7 g() {
        return this.d.b().a(Feature.IsolateApplication) ? this.f420a : this.b;
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        return g().a(str, o7Var, aVar);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        return g().b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        return g().c(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        return g().d(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        g().e(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        g().a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        g().b(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void c(String str, String str2, String str3) {
        g().c(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        g().d(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        g().a(o7Var);
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        return g().b(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        return g().c();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        return g().d(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        return g().a(str, o7Var, aVar, list);
    }
}

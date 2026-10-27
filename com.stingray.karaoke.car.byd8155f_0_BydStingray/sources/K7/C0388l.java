package K7;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: K7.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0388l extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Method f3802m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Method f3803n;

    public C0388l(Method method, Method method2) {
        A7.m.f(method, "getterMethod");
        this.f3802m = method;
        this.f3803n = method2;
    }

    @Override // K7.v0
    public final String i() {
        return v0.h(this.f3802m);
    }
}

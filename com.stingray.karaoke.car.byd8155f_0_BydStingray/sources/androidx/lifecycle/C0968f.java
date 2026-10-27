package androidx.lifecycle;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: androidx.lifecycle.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0968f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f11984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f11985b;

    public C0968f(int i7, Method method) {
        this.f11984a = i7;
        this.f11985b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0968f)) {
            return false;
        }
        C0968f c0968f = (C0968f) obj;
        return this.f11984a == c0968f.f11984a && this.f11985b.getName().equals(c0968f.f11985b.getName());
    }

    public final int hashCode() {
        return this.f11985b.getName().hashCode() + (this.f11984a * 31);
    }
}

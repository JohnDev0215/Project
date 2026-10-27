package com.airbnb.lottie;

import java.util.Arrays;

/* JADX INFO: compiled from: LottieResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final V f2885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Throwable f2886b;

    public l(V v) {
        this.f2885a = v;
        this.f2886b = null;
    }

    public Throwable a() {
        return this.f2886b;
    }

    public V b() {
        return this.f2885a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        if (b() != null && b().equals(lVar.b())) {
            return true;
        }
        if (a() == null || lVar.a() == null) {
            return false;
        }
        return a().toString().equals(a().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{b(), a()});
    }

    public l(Throwable th) {
        this.f2886b = th;
        this.f2885a = null;
    }
}

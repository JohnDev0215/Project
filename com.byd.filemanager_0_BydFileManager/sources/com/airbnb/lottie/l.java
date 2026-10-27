package com.airbnb.lottie;

import java.util.Arrays;

/* JADX INFO: compiled from: LottieResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final V f2501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Throwable f2502b;

    public l(V v) {
        this.f2501a = v;
        this.f2502b = null;
    }

    public Throwable a() {
        return this.f2502b;
    }

    public V b() {
        return this.f2501a;
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
        this.f2502b = th;
        this.f2501a = null;
    }
}

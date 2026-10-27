package com.airbnb.lottie;

import androidx.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class l<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final V f712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final Throwable f713b;

    public l(V v) {
        this.f712a = v;
        this.f713b = null;
    }

    public l(Throwable th) {
        this.f713b = th;
        this.f712a = null;
    }

    @Nullable
    public Throwable a() {
        return this.f713b;
    }

    @Nullable
    public V b() {
        return this.f712a;
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
}

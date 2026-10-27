package com.airbnb.lottie.t;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final g f796a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LruCache<String, com.airbnb.lottie.d> f797b = new LruCache<>(20);

    @VisibleForTesting
    g() {
    }

    public static g b() {
        return f796a;
    }

    @Nullable
    public com.airbnb.lottie.d a(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return this.f797b.get(str);
    }

    public void c(@Nullable String str, com.airbnb.lottie.d dVar) {
        if (str == null) {
            return;
        }
        this.f797b.put(str, dVar);
    }
}

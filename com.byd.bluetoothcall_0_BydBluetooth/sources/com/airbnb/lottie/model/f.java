package com.airbnb.lottie.model;

/* JADX INFO: compiled from: LottieCompositionCache.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final f f2987a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.d.e<String, com.airbnb.lottie.d> f2988b = new b.d.e<>(20);

    f() {
    }

    public static f b() {
        return f2987a;
    }

    public com.airbnb.lottie.d a(String str) {
        if (str == null) {
            return null;
        }
        return this.f2988b.c(str);
    }

    public void c(String str, com.airbnb.lottie.d dVar) {
        if (str == null) {
            return;
        }
        this.f2988b.d(str, dVar);
    }
}

package com.airbnb.lottie.model;

/* JADX INFO: compiled from: LottieCompositionCache.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final f f2603a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.d.e<String, com.airbnb.lottie.d> f2604b = new b.d.e<>(20);

    f() {
    }

    public static f b() {
        return f2603a;
    }

    public com.airbnb.lottie.d a(String str) {
        if (str == null) {
            return null;
        }
        return this.f2604b.c(str);
    }

    public void c(String str, com.airbnb.lottie.d dVar) {
        if (str == null) {
            return;
        }
        this.f2604b.d(str, dVar);
    }
}

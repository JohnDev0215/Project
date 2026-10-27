package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.i.l;
import com.airbnb.lottie.r.b.p;

/* JADX INFO: compiled from: Repeater.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final l f2584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2585e;

    public g(String str, com.airbnb.lottie.model.i.b bVar, com.airbnb.lottie.model.i.b bVar2, l lVar, boolean z) {
        this.f2581a = str;
        this.f2582b = bVar;
        this.f2583c = bVar2;
        this.f2584d = lVar;
        this.f2585e = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new p(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f2582b;
    }

    public String c() {
        return this.f2581a;
    }

    public com.airbnb.lottie.model.i.b d() {
        return this.f2583c;
    }

    public l e() {
        return this.f2584d;
    }

    public boolean f() {
        return this.f2585e;
    }
}

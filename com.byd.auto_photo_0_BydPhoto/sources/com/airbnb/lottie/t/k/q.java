package com.airbnb.lottie.t.k;

import com.airbnb.lottie.r.b.s;

/* JADX INFO: loaded from: classes.dex */
public class q implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f875b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.b f876c;
    private final com.airbnb.lottie.t.j.b d;
    private final com.airbnb.lottie.t.j.b e;
    private final boolean f;

    public enum a {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static a a(int i) {
            if (i == 1) {
                return SIMULTANEOUSLY;
            }
            if (i == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException("Unknown trim path type " + i);
        }
    }

    public q(String str, a aVar, com.airbnb.lottie.t.j.b bVar, com.airbnb.lottie.t.j.b bVar2, com.airbnb.lottie.t.j.b bVar3, boolean z) {
        this.f874a = str;
        this.f875b = aVar;
        this.f876c = bVar;
        this.d = bVar2;
        this.e = bVar3;
        this.f = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new s(aVar, this);
    }

    public com.airbnb.lottie.t.j.b b() {
        return this.d;
    }

    public String c() {
        return this.f874a;
    }

    public com.airbnb.lottie.t.j.b d() {
        return this.e;
    }

    public com.airbnb.lottie.t.j.b e() {
        return this.f876c;
    }

    public a f() {
        return this.f875b;
    }

    public boolean g() {
        return this.f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f876c + ", end: " + this.d + ", offset: " + this.e + "}";
    }
}

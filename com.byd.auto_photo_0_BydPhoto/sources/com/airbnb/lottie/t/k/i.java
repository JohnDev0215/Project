package com.airbnb.lottie.t.k;

import android.graphics.PointF;

/* JADX INFO: loaded from: classes.dex */
public class i implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.b f841c;
    private final com.airbnb.lottie.t.j.m<PointF, PointF> d;
    private final com.airbnb.lottie.t.j.b e;
    private final com.airbnb.lottie.t.j.b f;
    private final com.airbnb.lottie.t.j.b g;
    private final com.airbnb.lottie.t.j.b h;
    private final com.airbnb.lottie.t.j.b i;
    private final boolean j;

    public enum a {
        STAR(1),
        POLYGON(2);

        private final int d;

        a(int i) {
            this.d = i;
        }

        public static a a(int i) {
            for (a aVar : values()) {
                if (aVar.d == i) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public i(String str, a aVar, com.airbnb.lottie.t.j.b bVar, com.airbnb.lottie.t.j.m<PointF, PointF> mVar, com.airbnb.lottie.t.j.b bVar2, com.airbnb.lottie.t.j.b bVar3, com.airbnb.lottie.t.j.b bVar4, com.airbnb.lottie.t.j.b bVar5, com.airbnb.lottie.t.j.b bVar6, boolean z) {
        this.f839a = str;
        this.f840b = aVar;
        this.f841c = bVar;
        this.d = mVar;
        this.e = bVar2;
        this.f = bVar3;
        this.g = bVar4;
        this.h = bVar5;
        this.i = bVar6;
        this.j = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.n(fVar, aVar, this);
    }

    public com.airbnb.lottie.t.j.b b() {
        return this.f;
    }

    public com.airbnb.lottie.t.j.b c() {
        return this.h;
    }

    public String d() {
        return this.f839a;
    }

    public com.airbnb.lottie.t.j.b e() {
        return this.g;
    }

    public com.airbnb.lottie.t.j.b f() {
        return this.i;
    }

    public com.airbnb.lottie.t.j.b g() {
        return this.f841c;
    }

    public com.airbnb.lottie.t.j.m<PointF, PointF> h() {
        return this.d;
    }

    public com.airbnb.lottie.t.j.b i() {
        return this.e;
    }

    public a j() {
        return this.f840b;
    }

    public boolean k() {
        return this.j;
    }
}

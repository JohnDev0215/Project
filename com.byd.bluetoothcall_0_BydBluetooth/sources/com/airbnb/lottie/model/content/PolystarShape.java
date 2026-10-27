package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.i.m;
import com.airbnb.lottie.r.b.n;

/* JADX INFO: loaded from: classes.dex */
public class PolystarShape implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Type f2923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final m<PointF, PointF> f2925d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2926e;
    private final com.airbnb.lottie.model.i.b f;
    private final com.airbnb.lottie.model.i.b g;
    private final com.airbnb.lottie.model.i.b h;
    private final com.airbnb.lottie.model.i.b i;
    private final boolean j;

    public enum Type {
        STAR(1),
        POLYGON(2);

        private final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type forValue(int i) {
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, com.airbnb.lottie.model.i.b bVar, m<PointF, PointF> mVar, com.airbnb.lottie.model.i.b bVar2, com.airbnb.lottie.model.i.b bVar3, com.airbnb.lottie.model.i.b bVar4, com.airbnb.lottie.model.i.b bVar5, com.airbnb.lottie.model.i.b bVar6, boolean z) {
        this.f2922a = str;
        this.f2923b = type;
        this.f2924c = bVar;
        this.f2925d = mVar;
        this.f2926e = bVar2;
        this.f = bVar3;
        this.g = bVar4;
        this.h = bVar5;
        this.i = bVar6;
        this.j = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new n(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f;
    }

    public com.airbnb.lottie.model.i.b c() {
        return this.h;
    }

    public String d() {
        return this.f2922a;
    }

    public com.airbnb.lottie.model.i.b e() {
        return this.g;
    }

    public com.airbnb.lottie.model.i.b f() {
        return this.i;
    }

    public com.airbnb.lottie.model.i.b g() {
        return this.f2924c;
    }

    public m<PointF, PointF> h() {
        return this.f2925d;
    }

    public com.airbnb.lottie.model.i.b i() {
        return this.f2926e;
    }

    public Type j() {
        return this.f2923b;
    }

    public boolean k() {
        return this.j;
    }
}

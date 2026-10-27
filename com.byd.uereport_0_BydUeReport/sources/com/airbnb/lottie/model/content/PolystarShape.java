package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import o1.g;
import q1.c;
import q1.n;
import u1.m;
import v1.b;
import w1.a;

/* JADX INFO: loaded from: classes.dex */
public class PolystarShape implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2464a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f2465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.b f2466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m<PointF, PointF> f2467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u1.b f2468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u1.b f2469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u1.b f2470g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u1.b f2471h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u1.b f2472i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f2473j;

    public enum Type {
        STAR(1),
        POLYGON(2);

        private final int value;

        Type(int i8) {
            this.value = i8;
        }

        public static Type forValue(int i8) {
            for (Type type : values()) {
                if (type.value == i8) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, u1.b bVar, m<PointF, PointF> mVar, u1.b bVar2, u1.b bVar3, u1.b bVar4, u1.b bVar5, u1.b bVar6, boolean z7) {
        this.f2464a = str;
        this.f2465b = type;
        this.f2466c = bVar;
        this.f2467d = mVar;
        this.f2468e = bVar2;
        this.f2469f = bVar3;
        this.f2470g = bVar4;
        this.f2471h = bVar5;
        this.f2472i = bVar6;
        this.f2473j = z7;
    }

    @Override // v1.b
    public c a(g gVar, a aVar) {
        return new n(gVar, aVar, this);
    }

    public u1.b b() {
        return this.f2469f;
    }

    public u1.b c() {
        return this.f2471h;
    }

    public String d() {
        return this.f2464a;
    }

    public u1.b e() {
        return this.f2470g;
    }

    public u1.b f() {
        return this.f2472i;
    }

    public u1.b g() {
        return this.f2466c;
    }

    public m<PointF, PointF> h() {
        return this.f2467d;
    }

    public u1.b i() {
        return this.f2468e;
    }

    public Type j() {
        return this.f2465b;
    }

    public boolean k() {
        return this.f2473j;
    }
}

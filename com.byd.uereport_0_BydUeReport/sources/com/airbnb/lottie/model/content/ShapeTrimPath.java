package com.airbnb.lottie.model.content;

import o1.g;
import q1.c;
import q1.s;
import v1.b;
import w1.a;

/* JADX INFO: loaded from: classes.dex */
public class ShapeTrimPath implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f2487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.b f2488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.b f2489d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u1.b f2490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2491f;

    public enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type forId(int i8) {
            if (i8 == 1) {
                return SIMULTANEOUSLY;
            }
            if (i8 == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException("Unknown trim path type " + i8);
        }
    }

    public ShapeTrimPath(String str, Type type, u1.b bVar, u1.b bVar2, u1.b bVar3, boolean z7) {
        this.f2486a = str;
        this.f2487b = type;
        this.f2488c = bVar;
        this.f2489d = bVar2;
        this.f2490e = bVar3;
        this.f2491f = z7;
    }

    @Override // v1.b
    public c a(g gVar, a aVar) {
        return new s(aVar, this);
    }

    public u1.b b() {
        return this.f2489d;
    }

    public String c() {
        return this.f2486a;
    }

    public u1.b d() {
        return this.f2490e;
    }

    public u1.b e() {
        return this.f2488c;
    }

    public Type f() {
        return this.f2487b;
    }

    public boolean g() {
        return this.f2491f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f2488c + ", end: " + this.f2489d + ", offset: " + this.f2490e + "}";
    }
}

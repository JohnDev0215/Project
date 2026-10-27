package com.airbnb.lottie.model.content;

import com.airbnb.lottie.r.b.s;

/* JADX INFO: loaded from: classes.dex */
public class ShapeTrimPath implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Type f2554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2556d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2557e;
    private final boolean f;

    public enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type forId(int i) {
            if (i == 1) {
                return SIMULTANEOUSLY;
            }
            if (i == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException("Unknown trim path type " + i);
        }
    }

    public ShapeTrimPath(String str, Type type, com.airbnb.lottie.model.i.b bVar, com.airbnb.lottie.model.i.b bVar2, com.airbnb.lottie.model.i.b bVar3, boolean z) {
        this.f2553a = str;
        this.f2554b = type;
        this.f2555c = bVar;
        this.f2556d = bVar2;
        this.f2557e = bVar3;
        this.f = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new s(aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f2556d;
    }

    public String c() {
        return this.f2553a;
    }

    public com.airbnb.lottie.model.i.b d() {
        return this.f2557e;
    }

    public com.airbnb.lottie.model.i.b e() {
        return this.f2555c;
    }

    public Type f() {
        return this.f2554b;
    }

    public boolean g() {
        return this.f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f2555c + ", end: " + this.f2556d + ", offset: " + this.f2557e + "}";
    }
}

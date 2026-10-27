package com.airbnb.lottie.model.content;

import com.airbnb.lottie.r.b.s;

/* JADX INFO: loaded from: classes.dex */
public class ShapeTrimPath implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Type f2938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2941e;
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
        this.f2937a = str;
        this.f2938b = type;
        this.f2939c = bVar;
        this.f2940d = bVar2;
        this.f2941e = bVar3;
        this.f = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new s(aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f2940d;
    }

    public String c() {
        return this.f2937a;
    }

    public com.airbnb.lottie.model.i.b d() {
        return this.f2941e;
    }

    public com.airbnb.lottie.model.i.b e() {
        return this.f2939c;
    }

    public Type f() {
        return this.f2938b;
    }

    public boolean g() {
        return this.f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f2939c + ", end: " + this.f2940d + ", offset: " + this.f2941e + "}";
    }
}

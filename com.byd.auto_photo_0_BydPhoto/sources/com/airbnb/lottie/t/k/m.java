package com.airbnb.lottie.t.k;

import android.graphics.Path;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class m implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path.FillType f855b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f856c;

    @Nullable
    private final com.airbnb.lottie.t.j.a d;

    @Nullable
    private final com.airbnb.lottie.t.j.d e;
    private final boolean f;

    public m(String str, boolean z, Path.FillType fillType, @Nullable com.airbnb.lottie.t.j.a aVar, @Nullable com.airbnb.lottie.t.j.d dVar, boolean z2) {
        this.f856c = str;
        this.f854a = z;
        this.f855b = fillType;
        this.d = aVar;
        this.e = dVar;
        this.f = z2;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.g(fVar, aVar, this);
    }

    @Nullable
    public com.airbnb.lottie.t.j.a b() {
        return this.d;
    }

    public Path.FillType c() {
        return this.f855b;
    }

    public String d() {
        return this.f856c;
    }

    @Nullable
    public com.airbnb.lottie.t.j.d e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f854a + '}';
    }
}

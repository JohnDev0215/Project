package com.airbnb.lottie.t.k;

import android.graphics.Path;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path.FillType f819b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.c f820c;
    private final com.airbnb.lottie.t.j.d d;
    private final com.airbnb.lottie.t.j.f e;
    private final com.airbnb.lottie.t.j.f f;
    private final String g;

    @Nullable
    private final com.airbnb.lottie.t.j.b h;

    @Nullable
    private final com.airbnb.lottie.t.j.b i;
    private final boolean j;

    public d(String str, f fVar, Path.FillType fillType, com.airbnb.lottie.t.j.c cVar, com.airbnb.lottie.t.j.d dVar, com.airbnb.lottie.t.j.f fVar2, com.airbnb.lottie.t.j.f fVar3, com.airbnb.lottie.t.j.b bVar, com.airbnb.lottie.t.j.b bVar2, boolean z) {
        this.f818a = fVar;
        this.f819b = fillType;
        this.f820c = cVar;
        this.d = dVar;
        this.e = fVar2;
        this.f = fVar3;
        this.g = str;
        this.h = bVar;
        this.i = bVar2;
        this.j = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.h(fVar, aVar, this);
    }

    public com.airbnb.lottie.t.j.f b() {
        return this.f;
    }

    public Path.FillType c() {
        return this.f819b;
    }

    public com.airbnb.lottie.t.j.c d() {
        return this.f820c;
    }

    public f e() {
        return this.f818a;
    }

    public String f() {
        return this.g;
    }

    public com.airbnb.lottie.t.j.d g() {
        return this.d;
    }

    public com.airbnb.lottie.t.j.f h() {
        return this.e;
    }

    public boolean i() {
        return this.j;
    }
}

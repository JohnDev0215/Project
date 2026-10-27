package com.airbnb.lottie.t.j;

import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.o;

/* JADX INFO: loaded from: classes.dex */
public class l implements com.airbnb.lottie.t.k.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final e f809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final m<PointF, PointF> f810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final g f811c;

    @Nullable
    private final b d;

    @Nullable
    private final d e;

    @Nullable
    private final b f;

    @Nullable
    private final b g;

    @Nullable
    private final b h;

    @Nullable
    private final b i;

    public l() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public l(@Nullable e eVar, @Nullable m<PointF, PointF> mVar, @Nullable g gVar, @Nullable b bVar, @Nullable d dVar, @Nullable b bVar2, @Nullable b bVar3, @Nullable b bVar4, @Nullable b bVar5) {
        this.f809a = eVar;
        this.f810b = mVar;
        this.f811c = gVar;
        this.d = bVar;
        this.e = dVar;
        this.h = bVar2;
        this.i = bVar3;
        this.f = bVar4;
        this.g = bVar5;
    }

    @Override // com.airbnb.lottie.t.k.b
    @Nullable
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return null;
    }

    public o b() {
        return new o(this);
    }

    @Nullable
    public e c() {
        return this.f809a;
    }

    @Nullable
    public b d() {
        return this.i;
    }

    @Nullable
    public d e() {
        return this.e;
    }

    @Nullable
    public m<PointF, PointF> f() {
        return this.f810b;
    }

    @Nullable
    public b g() {
        return this.d;
    }

    @Nullable
    public g h() {
        return this.f811c;
    }

    @Nullable
    public b i() {
        return this.f;
    }

    @Nullable
    public b j() {
        return this.g;
    }

    @Nullable
    public b k() {
        return this.h;
    }
}

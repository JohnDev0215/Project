package com.airbnb.lottie.r.b;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TrimPathContent.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements c, a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f2721b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<a.b> f2722c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ShapeTrimPath.Type f2723d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, Float> f2724e;
    private final com.airbnb.lottie.r.c.a<?, Float> f;
    private final com.airbnb.lottie.r.c.a<?, Float> g;

    public s(com.airbnb.lottie.model.layer.a aVar, ShapeTrimPath shapeTrimPath) {
        this.f2720a = shapeTrimPath.c();
        this.f2721b = shapeTrimPath.g();
        this.f2723d = shapeTrimPath.f();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = shapeTrimPath.e().a();
        this.f2724e = aVarA;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = shapeTrimPath.b().a();
        this.f = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = shapeTrimPath.d().a();
        this.g = aVarA3;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        for (int i = 0; i < this.f2722c.size(); i++) {
            this.f2722c.get(i).b();
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
    }

    void d(a.b bVar) {
        this.f2722c.add(bVar);
    }

    public com.airbnb.lottie.r.c.a<?, Float> e() {
        return this.f;
    }

    public com.airbnb.lottie.r.c.a<?, Float> f() {
        return this.g;
    }

    public com.airbnb.lottie.r.c.a<?, Float> j() {
        return this.f2724e;
    }

    ShapeTrimPath.Type k() {
        return this.f2723d;
    }

    public boolean l() {
        return this.f2721b;
    }
}

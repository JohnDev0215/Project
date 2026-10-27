package com.airbnb.lottie.r.b;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TrimPathContent.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements c, a.InterfaceC0077a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f3107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<a.InterfaceC0077a> f3108c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ShapeTrimPath.Type f3109d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, Float> f3110e;
    private final com.airbnb.lottie.r.c.a<?, Float> f;
    private final com.airbnb.lottie.r.c.a<?, Float> g;

    public s(com.airbnb.lottie.model.layer.a aVar, ShapeTrimPath shapeTrimPath) {
        this.f3106a = shapeTrimPath.c();
        this.f3107b = shapeTrimPath.g();
        this.f3109d = shapeTrimPath.f();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = shapeTrimPath.e().a();
        this.f3110e = aVarA;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = shapeTrimPath.b().a();
        this.f = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = shapeTrimPath.d().a();
        this.g = aVarA3;
        aVar.j(aVarA);
        aVar.j(aVarA2);
        aVar.j(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
    public void b() {
        for (int i = 0; i < this.f3108c.size(); i++) {
            this.f3108c.get(i).b();
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
    }

    void d(a.InterfaceC0077a interfaceC0077a) {
        this.f3108c.add(interfaceC0077a);
    }

    public com.airbnb.lottie.r.c.a<?, Float> e() {
        return this.f;
    }

    public com.airbnb.lottie.r.c.a<?, Float> f() {
        return this.g;
    }

    public com.airbnb.lottie.r.c.a<?, Float> j() {
        return this.f3110e;
    }

    ShapeTrimPath.Type k() {
        return this.f3109d;
    }

    public boolean l() {
        return this.f3107b;
    }
}

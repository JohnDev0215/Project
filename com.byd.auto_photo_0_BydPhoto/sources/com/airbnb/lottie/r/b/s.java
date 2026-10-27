package com.airbnb.lottie.r.b;

import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.t.k.q;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class s implements c, a.InterfaceC0025a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<a.InterfaceC0025a> f763c = new ArrayList();
    private final q.a d;
    private final com.airbnb.lottie.r.c.a<?, Float> e;
    private final com.airbnb.lottie.r.c.a<?, Float> f;
    private final com.airbnb.lottie.r.c.a<?, Float> g;

    public s(com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.q qVar) {
        this.f761a = qVar.c();
        this.f762b = qVar.g();
        this.d = qVar.f();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = qVar.e().a();
        this.e = aVarA;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = qVar.b().a();
        this.f = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = qVar.d().a();
        this.g = aVarA3;
        aVar.h(aVarA);
        aVar.h(aVarA2);
        aVar.h(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        for (int i = 0; i < this.f763c.size(); i++) {
            this.f763c.get(i).b();
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
    }

    void d(a.InterfaceC0025a interfaceC0025a) {
        this.f763c.add(interfaceC0025a);
    }

    public com.airbnb.lottie.r.c.a<?, Float> e() {
        return this.f;
    }

    public com.airbnb.lottie.r.c.a<?, Float> g() {
        return this.g;
    }

    public com.airbnb.lottie.r.c.a<?, Float> h() {
        return this.e;
    }

    q.a i() {
        return this.d;
    }

    public boolean j() {
        return this.f762b;
    }
}

package com.airbnb.lottie.r.c;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<K, A> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<? extends com.airbnb.lottie.v.a<K>> f3113c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected com.airbnb.lottie.v.c<A> f3115e;
    private com.airbnb.lottie.v.a<K> f;
    private com.airbnb.lottie.v.a<K> g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<InterfaceC0077a> f3111a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3112b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3114d = 0.0f;
    private float h = -1.0f;
    private A i = null;
    private float j = -1.0f;
    private float k = -1.0f;

    /* JADX INFO: renamed from: com.airbnb.lottie.r.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public interface InterfaceC0077a {
        void b();
    }

    a(List<? extends com.airbnb.lottie.v.a<K>> list) {
        this.f3113c = list;
    }

    private float g() {
        if (this.j == -1.0f) {
            this.j = this.f3113c.isEmpty() ? 0.0f : this.f3113c.get(0).e();
        }
        return this.j;
    }

    public void a(InterfaceC0077a interfaceC0077a) {
        this.f3111a.add(interfaceC0077a);
    }

    protected com.airbnb.lottie.v.a<K> b() {
        com.airbnb.lottie.c.a("BaseKeyframeAnimation#getCurrentKeyframe");
        com.airbnb.lottie.v.a<K> aVar = this.f;
        if (aVar != null && aVar.a(this.f3114d)) {
            com.airbnb.lottie.c.b("BaseKeyframeAnimation#getCurrentKeyframe");
            return this.f;
        }
        com.airbnb.lottie.v.a<K> aVar2 = this.f3113c.get(r1.size() - 1);
        if (this.f3114d < aVar2.e()) {
            for (int size = this.f3113c.size() - 1; size >= 0; size--) {
                aVar2 = this.f3113c.get(size);
                if (aVar2.a(this.f3114d)) {
                    break;
                }
            }
        }
        this.f = aVar2;
        com.airbnb.lottie.c.b("BaseKeyframeAnimation#getCurrentKeyframe");
        return aVar2;
    }

    float c() {
        float fB;
        if (this.k == -1.0f) {
            if (this.f3113c.isEmpty()) {
                fB = 1.0f;
            } else {
                fB = this.f3113c.get(r0.size() - 1).b();
            }
            this.k = fB;
        }
        return this.k;
    }

    protected float d() {
        com.airbnb.lottie.v.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return aVarB.f3200d.getInterpolation(e());
    }

    float e() {
        if (this.f3112b) {
            return 0.0f;
        }
        com.airbnb.lottie.v.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return (this.f3114d - aVarB.e()) / (aVarB.b() - aVarB.e());
    }

    public float f() {
        return this.f3114d;
    }

    public A h() {
        com.airbnb.lottie.v.a<K> aVarB = b();
        float fD = d();
        if (this.f3115e == null && aVarB == this.g && this.h == fD) {
            return this.i;
        }
        this.g = aVarB;
        this.h = fD;
        A aI = i(aVarB, fD);
        this.i = aI;
        return aI;
    }

    abstract A i(com.airbnb.lottie.v.a<K> aVar, float f);

    public void j() {
        for (int i = 0; i < this.f3111a.size(); i++) {
            this.f3111a.get(i).b();
        }
    }

    public void k() {
        this.f3112b = true;
    }

    public void l(float f) {
        if (this.f3113c.isEmpty()) {
            return;
        }
        com.airbnb.lottie.v.a<K> aVarB = b();
        if (f < g()) {
            f = g();
        } else if (f > c()) {
            f = c();
        }
        if (f == this.f3114d) {
            return;
        }
        this.f3114d = f;
        com.airbnb.lottie.v.a<K> aVarB2 = b();
        if (aVarB == aVarB2 && aVarB2.h()) {
            return;
        }
        j();
    }

    public void m(com.airbnb.lottie.v.c<A> cVar) {
        com.airbnb.lottie.v.c<A> cVar2 = this.f3115e;
        if (cVar2 != null) {
            cVar2.c(null);
        }
        this.f3115e = cVar;
        if (cVar != null) {
            cVar.c(this);
        }
    }
}

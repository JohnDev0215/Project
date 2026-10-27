package com.airbnb.lottie.r.c;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<K, A> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<? extends com.airbnb.lottie.x.a<K>> f766c;

    @Nullable
    protected com.airbnb.lottie.x.c<A> e;

    @Nullable
    private com.airbnb.lottie.x.a<K> f;

    @Nullable
    private com.airbnb.lottie.x.a<K> g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<InterfaceC0025a> f764a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f765b = false;
    private float d = 0.0f;
    private float h = -1.0f;

    @Nullable
    private A i = null;
    private float j = -1.0f;
    private float k = -1.0f;

    /* JADX INFO: renamed from: com.airbnb.lottie.r.c.a$a, reason: collision with other inner class name */
    public interface InterfaceC0025a {
        void b();
    }

    a(List<? extends com.airbnb.lottie.x.a<K>> list) {
        this.f766c = list;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float g() {
        if (this.j == -1.0f) {
            this.j = this.f766c.isEmpty() ? 0.0f : this.f766c.get(0).e();
        }
        return this.j;
    }

    public void a(InterfaceC0025a interfaceC0025a) {
        this.f764a.add(interfaceC0025a);
    }

    protected com.airbnb.lottie.x.a<K> b() {
        com.airbnb.lottie.c.a("BaseKeyframeAnimation#getCurrentKeyframe");
        com.airbnb.lottie.x.a<K> aVar = this.f;
        if (aVar != null && aVar.a(this.d)) {
            com.airbnb.lottie.c.b("BaseKeyframeAnimation#getCurrentKeyframe");
            return this.f;
        }
        com.airbnb.lottie.x.a<K> aVar2 = this.f766c.get(r1.size() - 1);
        if (this.d < aVar2.e()) {
            for (int size = this.f766c.size() - 1; size >= 0; size--) {
                aVar2 = this.f766c.get(size);
                if (aVar2.a(this.d)) {
                    break;
                }
            }
        }
        this.f = aVar2;
        com.airbnb.lottie.c.b("BaseKeyframeAnimation#getCurrentKeyframe");
        return aVar2;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    float c() {
        float fB;
        if (this.k == -1.0f) {
            if (this.f766c.isEmpty()) {
                fB = 1.0f;
            } else {
                fB = this.f766c.get(r0.size() - 1).b();
            }
            this.k = fB;
        }
        return this.k;
    }

    protected float d() {
        com.airbnb.lottie.x.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return aVarB.d.getInterpolation(e());
    }

    float e() {
        if (this.f765b) {
            return 0.0f;
        }
        com.airbnb.lottie.x.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return (this.d - aVarB.e()) / (aVarB.b() - aVarB.e());
    }

    public float f() {
        return this.d;
    }

    public A h() {
        com.airbnb.lottie.x.a<K> aVarB = b();
        float fD = d();
        if (this.e == null && aVarB == this.g && this.h == fD) {
            return this.i;
        }
        this.g = aVarB;
        this.h = fD;
        A aI = i(aVarB, fD);
        this.i = aI;
        return aI;
    }

    abstract A i(com.airbnb.lottie.x.a<K> aVar, float f);

    public void j() {
        for (int i = 0; i < this.f764a.size(); i++) {
            this.f764a.get(i).b();
        }
    }

    public void k() {
        this.f765b = true;
    }

    public void l(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.f766c.isEmpty()) {
            return;
        }
        com.airbnb.lottie.x.a<K> aVarB = b();
        if (f < g()) {
            f = g();
        } else if (f > c()) {
            f = c();
        }
        if (f == this.d) {
            return;
        }
        this.d = f;
        com.airbnb.lottie.x.a<K> aVarB2 = b();
        if (aVarB == aVarB2 && aVarB2.h()) {
            return;
        }
        j();
    }

    public void m(@Nullable com.airbnb.lottie.x.c<A> cVar) {
        com.airbnb.lottie.x.c<A> cVar2 = this.e;
        if (cVar2 != null) {
            cVar2.c(null);
        }
        this.e = cVar;
        if (cVar != null) {
            cVar.c(this);
        }
    }
}

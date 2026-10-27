package com.airbnb.lottie.r.c;

import java.util.Collections;

/* JADX INFO: compiled from: ValueCallbackKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class p<K, A> extends a<K, A> {
    private final com.airbnb.lottie.v.b<A> l;
    private final A m;

    public p(com.airbnb.lottie.v.c<A> cVar) {
        this(cVar, null);
    }

    @Override // com.airbnb.lottie.r.c.a
    float c() {
        return 1.0f;
    }

    @Override // com.airbnb.lottie.r.c.a
    public A h() {
        com.airbnb.lottie.v.c<A> cVar = this.f3115e;
        A a2 = this.m;
        return cVar.b(0.0f, 0.0f, a2, a2, f(), f(), f());
    }

    @Override // com.airbnb.lottie.r.c.a
    A i(com.airbnb.lottie.v.a<K> aVar, float f) {
        return h();
    }

    @Override // com.airbnb.lottie.r.c.a
    public void j() {
        if (this.f3115e != null) {
            super.j();
        }
    }

    public p(com.airbnb.lottie.v.c<A> cVar, A a2) {
        super(Collections.emptyList());
        this.l = new com.airbnb.lottie.v.b<>();
        m(cVar);
        this.m = a2;
    }
}

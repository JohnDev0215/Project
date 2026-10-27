package com.airbnb.lottie.r.c;

import java.util.Collections;

/* JADX INFO: compiled from: ValueCallbackKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class p<K, A> extends a<K, A> {
    private final com.airbnb.lottie.v.b<A> i;
    private final A j;

    public p(com.airbnb.lottie.v.c<A> cVar) {
        this(cVar, null);
    }

    @Override // com.airbnb.lottie.r.c.a
    float c() {
        return 1.0f;
    }

    @Override // com.airbnb.lottie.r.c.a
    public A h() {
        com.airbnb.lottie.v.c<A> cVar = this.f2729e;
        A a2 = this.j;
        return cVar.b(0.0f, 0.0f, a2, a2, f(), f(), f());
    }

    @Override // com.airbnb.lottie.r.c.a
    A i(com.airbnb.lottie.v.a<K> aVar, float f) {
        return h();
    }

    @Override // com.airbnb.lottie.r.c.a
    public void j() {
        if (this.f2729e != null) {
            super.j();
        }
    }

    @Override // com.airbnb.lottie.r.c.a
    public void l(float f) {
        this.f2728d = f;
    }

    public p(com.airbnb.lottie.v.c<A> cVar, A a2) {
        super(Collections.emptyList());
        this.i = new com.airbnb.lottie.v.b<>();
        m(cVar);
        this.j = a2;
    }
}

package com.airbnb.lottie.r.c;

import androidx.annotation.Nullable;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class p<K, A> extends a<K, A> {
    private final com.airbnb.lottie.x.b<A> l;
    private final A m;

    public p(com.airbnb.lottie.x.c<A> cVar) {
        this(cVar, null);
    }

    public p(com.airbnb.lottie.x.c<A> cVar, @Nullable A a2) {
        super(Collections.emptyList());
        this.l = new com.airbnb.lottie.x.b<>();
        m(cVar);
        this.m = a2;
    }

    @Override // com.airbnb.lottie.r.c.a
    float c() {
        return 1.0f;
    }

    @Override // com.airbnb.lottie.r.c.a
    public A h() {
        com.airbnb.lottie.x.c<A> cVar = this.e;
        A a2 = this.m;
        return cVar.b(0.0f, 0.0f, a2, a2, f(), f(), f());
    }

    @Override // com.airbnb.lottie.r.c.a
    A i(com.airbnb.lottie.x.a<K> aVar, float f) {
        return h();
    }

    @Override // com.airbnb.lottie.r.c.a
    public void j() {
        if (this.e != null) {
            super.j();
        }
    }
}

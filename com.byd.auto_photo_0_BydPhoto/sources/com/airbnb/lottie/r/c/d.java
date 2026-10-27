package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends f<com.airbnb.lottie.t.k.c> {
    private final com.airbnb.lottie.t.k.c l;

    public d(List<com.airbnb.lottie.x.a<com.airbnb.lottie.t.k.c>> list) {
        super(list);
        com.airbnb.lottie.t.k.c cVar = list.get(0).f976b;
        int iC = cVar != null ? cVar.c() : 0;
        this.l = new com.airbnb.lottie.t.k.c(new float[iC], new int[iC]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.t.k.c i(com.airbnb.lottie.x.a<com.airbnb.lottie.t.k.c> aVar, float f) {
        this.l.d(aVar.f976b, aVar.f977c, f);
        return this.l;
    }
}

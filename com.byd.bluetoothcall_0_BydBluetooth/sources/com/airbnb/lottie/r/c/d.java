package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: compiled from: GradientColorKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends f<com.airbnb.lottie.model.content.c> {
    private final com.airbnb.lottie.model.content.c l;

    public d(List<com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.c>> list) {
        super(list);
        com.airbnb.lottie.model.content.c cVar = list.get(0).f3198b;
        int iC = cVar != null ? cVar.c() : 0;
        this.l = new com.airbnb.lottie.model.content.c(new float[iC], new int[iC]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.model.content.c i(com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.c> aVar, float f) {
        this.l.d(aVar.f3198b, aVar.f3199c, f);
        return this.l;
    }
}

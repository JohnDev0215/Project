package com.bumptech.glide.load.engine;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Jobs.java */
/* JADX INFO: loaded from: classes.dex */
final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<com.bumptech.glide.load.c, j<?>> f2822a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<com.bumptech.glide.load.c, j<?>> f2823b = new HashMap();

    p() {
    }

    private Map<com.bumptech.glide.load.c, j<?>> b(boolean z) {
        return z ? this.f2823b : this.f2822a;
    }

    j<?> a(com.bumptech.glide.load.c cVar, boolean z) {
        return b(z).get(cVar);
    }

    void c(com.bumptech.glide.load.c cVar, j<?> jVar) {
        b(jVar.p()).put(cVar, jVar);
    }

    void d(com.bumptech.glide.load.c cVar, j<?> jVar) {
        Map<com.bumptech.glide.load.c, j<?>> mapB = b(jVar.p());
        if (jVar.equals(mapB.get(cVar))) {
            mapB.remove(cVar);
        }
    }
}

package com.bumptech.glide.load.n;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<com.bumptech.glide.load.g, l<?>> f2184a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<com.bumptech.glide.load.g, l<?>> f2185b = new HashMap();

    s() {
    }

    private Map<com.bumptech.glide.load.g, l<?>> b(boolean z) {
        return z ? this.f2185b : this.f2184a;
    }

    l<?> a(com.bumptech.glide.load.g gVar, boolean z) {
        return b(z).get(gVar);
    }

    void c(com.bumptech.glide.load.g gVar, l<?> lVar) {
        b(lVar.p()).put(gVar, lVar);
    }

    void d(com.bumptech.glide.load.g gVar, l<?> lVar) {
        Map<com.bumptech.glide.load.g, l<?>> mapB = b(lVar.p());
        if (lVar.equals(mapB.get(gVar))) {
            mapB.remove(gVar);
        }
    }
}

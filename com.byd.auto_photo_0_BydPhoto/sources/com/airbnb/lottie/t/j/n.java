package com.airbnb.lottie.t.j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class n<V, O> implements m<V, O> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<com.airbnb.lottie.x.a<V>> f812a;

    n(V v) {
        this(Collections.singletonList(new com.airbnb.lottie.x.a(v)));
    }

    n(List<com.airbnb.lottie.x.a<V>> list) {
        this.f812a = list;
    }

    @Override // com.airbnb.lottie.t.j.m
    public List<com.airbnb.lottie.x.a<V>> b() {
        return this.f812a;
    }

    @Override // com.airbnb.lottie.t.j.m
    public boolean c() {
        return this.f812a.isEmpty() || (this.f812a.size() == 1 && this.f812a.get(0).h());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f812a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f812a.toArray()));
        }
        return sb.toString();
    }
}

package com.airbnb.lottie.model.i;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: BaseAnimatableValue.java */
/* JADX INFO: loaded from: classes.dex */
abstract class n<V, O> implements m<V, O> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<com.airbnb.lottie.v.a<V>> f3007a;

    n(V v) {
        this(Collections.singletonList(new com.airbnb.lottie.v.a(v)));
    }

    @Override // com.airbnb.lottie.model.i.m
    public List<com.airbnb.lottie.v.a<V>> b() {
        return this.f3007a;
    }

    @Override // com.airbnb.lottie.model.i.m
    public boolean c() {
        return this.f3007a.isEmpty() || (this.f3007a.size() == 1 && this.f3007a.get(0).h());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f3007a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f3007a.toArray()));
        }
        return sb.toString();
    }

    n(List<com.airbnb.lottie.v.a<V>> list) {
        this.f3007a = list;
    }
}

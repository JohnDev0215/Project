package com.airbnb.lottie.model.i;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: BaseAnimatableValue.java */
/* JADX INFO: loaded from: classes.dex */
abstract class n<V, O> implements m<V, O> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<com.airbnb.lottie.v.a<V>> f2623a;

    n(V v) {
        this(Collections.singletonList(new com.airbnb.lottie.v.a(v)));
    }

    @Override // com.airbnb.lottie.model.i.m
    public List<com.airbnb.lottie.v.a<V>> b() {
        return this.f2623a;
    }

    @Override // com.airbnb.lottie.model.i.m
    public boolean c() {
        return this.f2623a.isEmpty() || (this.f2623a.size() == 1 && this.f2623a.get(0).h());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f2623a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f2623a.toArray()));
        }
        return sb.toString();
    }

    n(List<com.airbnb.lottie.v.a<V>> list) {
        this.f2623a = list;
    }
}

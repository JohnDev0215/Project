package com.airbnb.lottie.t.k;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class n implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f857a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<b> f858b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f859c;

    public n(String str, List<b> list, boolean z) {
        this.f857a = str;
        this.f858b = list;
        this.f859c = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.d(fVar, aVar, this);
    }

    public List<b> b() {
        return this.f858b;
    }

    public String c() {
        return this.f857a;
    }

    public boolean d() {
        return this.f859c;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f857a + "' Shapes: " + Arrays.toString(this.f858b.toArray()) + '}';
    }
}

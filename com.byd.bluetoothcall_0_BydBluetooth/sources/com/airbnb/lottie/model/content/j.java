package com.airbnb.lottie.model.content;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: ShapeGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<b> f2979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2980c;

    public j(String str, List<b> list, boolean z) {
        this.f2978a = str;
        this.f2979b = list;
        this.f2980c = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.d(fVar, aVar, this);
    }

    public List<b> b() {
        return this.f2979b;
    }

    public String c() {
        return this.f2978a;
    }

    public boolean d() {
        return this.f2980c;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f2978a + "' Shapes: " + Arrays.toString(this.f2979b.toArray()) + '}';
    }
}

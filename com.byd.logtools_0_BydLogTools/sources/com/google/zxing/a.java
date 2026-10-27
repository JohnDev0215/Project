package com.google.zxing;

/* JADX INFO: compiled from: Binarizer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f4178a;

    protected a(d dVar) {
        this.f4178a = dVar;
    }

    public abstract a a(d dVar);

    public abstract com.google.zxing.common.b b() throws NotFoundException;

    public abstract com.google.zxing.common.a c(int i, com.google.zxing.common.a aVar) throws NotFoundException;

    public final int d() {
        return this.f4178a.a();
    }

    public final d e() {
        return this.f4178a;
    }

    public final int f() {
        return this.f4178a.d();
    }
}

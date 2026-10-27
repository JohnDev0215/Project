package io.sentry;

import java.io.File;

/* JADX INFO: renamed from: io.sentry.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1487n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E f16620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K f16621b;
    public final long c;
    public final Y1 d;

    public AbstractC1487n(E e10, K k10, long j3, int i7) {
        this.f16620a = e10;
        this.f16621b = k10;
        this.c = j3;
        this.d = new Y1(new C1463f(i7));
    }

    public abstract boolean a(String str);

    public abstract void b(File file, C1523x c1523x);
}

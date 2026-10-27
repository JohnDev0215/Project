package io.sentry;

/* JADX INFO: loaded from: classes.dex */
public final class X1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f15991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile B8.t f15992b;
    public volatile N0 c;

    public X1(F1 f12, B8.t tVar, N0 n02) {
        this.f15992b = tVar;
        this.c = n02;
        this.f15991a = f12;
    }

    public X1(X1 x12) {
        this.f15991a = x12.f15991a;
        this.f15992b = x12.f15992b;
        N0 n02 = x12.c;
        n02.getClass();
        this.c = new N0(n02);
    }
}

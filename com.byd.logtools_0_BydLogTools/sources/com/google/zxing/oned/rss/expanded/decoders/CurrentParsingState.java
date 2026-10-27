package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: classes.dex */
final class CurrentParsingState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4398a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private State f4399b = State.NUMERIC;

    private enum State {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    CurrentParsingState() {
    }

    int a() {
        return this.f4398a;
    }

    void b(int i) {
        this.f4398a += i;
    }

    boolean c() {
        return this.f4399b == State.ALPHA;
    }

    boolean d() {
        return this.f4399b == State.ISO_IEC_646;
    }

    void e() {
        this.f4399b = State.ALPHA;
    }

    void f() {
        this.f4399b = State.ISO_IEC_646;
    }

    void g() {
        this.f4399b = State.NUMERIC;
    }

    void h(int i) {
        this.f4398a = i;
    }
}

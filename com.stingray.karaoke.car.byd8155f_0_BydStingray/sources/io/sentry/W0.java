package io.sentry;

/* JADX INFO: loaded from: classes.dex */
public final class W0 implements InterfaceC1456c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1456c1 f15977a;

    public W0() {
        if (io.sentry.util.f.f16921a || !io.sentry.util.f.f16922b) {
            this.f15977a = new C1486m1(1);
        } else {
            this.f15977a = new C1486m1(0);
        }
    }

    @Override // io.sentry.InterfaceC1456c1
    public final AbstractC1453b1 a() {
        return this.f15977a.a();
    }
}

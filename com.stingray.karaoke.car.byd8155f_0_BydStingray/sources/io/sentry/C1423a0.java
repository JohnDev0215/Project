package io.sentry;

/* JADX INFO: renamed from: io.sentry.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1423a0 implements P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runtime f16001a = Runtime.getRuntime();

    @Override // io.sentry.P
    public final void a(E0 e02) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Runtime runtime = this.f16001a;
        e02.f15835a = new C1506r0(jCurrentTimeMillis, runtime.totalMemory() - runtime.freeMemory(), -1L);
    }

    @Override // io.sentry.P
    public final void b() {
    }
}

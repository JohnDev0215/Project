package io.sentry;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class CallableC1465f1 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ io.sentry.internal.debugmeta.c f16535b;

    public /* synthetic */ CallableC1465f1(io.sentry.internal.debugmeta.c cVar, int i7) {
        this.f16534a = i7;
        this.f16535b = cVar;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f16534a) {
            case 0:
                return Integer.valueOf(this.f16535b.G().length);
            case 1:
                return this.f16535b.G();
            case 2:
                return Integer.valueOf(this.f16535b.G().length);
            case 3:
                return this.f16535b.G();
            case 4:
                return Integer.valueOf(this.f16535b.G().length);
            case 5:
                return this.f16535b.G();
            case 6:
                return Integer.valueOf(this.f16535b.G().length);
            case 7:
                return Integer.valueOf(this.f16535b.G().length);
            case 8:
                return this.f16535b.G();
            case 9:
                return this.f16535b.G();
            case 10:
                return Integer.valueOf(this.f16535b.G().length);
            case 11:
                return this.f16535b.G();
            case 12:
                return Integer.valueOf(this.f16535b.G().length);
            default:
                return this.f16535b.G();
        }
    }
}

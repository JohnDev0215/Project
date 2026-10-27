package io.sentry.android.core;

import io.sentry.N0;
import io.sentry.O0;

/* JADX INFO: renamed from: io.sentry.android.core.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1428d implements O0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f16158m;

    public /* synthetic */ C1428d(String str, int i7) {
        this.l = i7;
        this.f16158m = str;
    }

    @Override // io.sentry.O0
    public final void i(N0 n02) {
        switch (this.l) {
            case 0:
                n02.c(this.f16158m);
                break;
            default:
                A7.m.f(n02, "it");
                n02.c(this.f16158m);
                break;
        }
    }
}

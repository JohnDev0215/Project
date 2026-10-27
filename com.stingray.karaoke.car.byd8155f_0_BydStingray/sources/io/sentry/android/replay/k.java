package io.sentry.android.replay;

import io.sentry.N0;
import io.sentry.O0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements O0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.z f16414m;

    public /* synthetic */ k(A7.z zVar, int i7) {
        this.l = i7;
        this.f16414m = zVar;
    }

    @Override // io.sentry.O0
    public final void i(N0 n02) {
        int i7 = this.l;
        A7.z zVar = this.f16414m;
        switch (i7) {
            case 0:
                A7.m.f(n02, "it");
                String str = n02.c;
                zVar.l = str != null ? Q8.h.a1('.', str, str) : null;
                break;
            default:
                A7.m.f(n02, "scope");
                zVar.l = new ArrayList(n02.f15896f);
                break;
        }
    }
}

package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.operators.observable.c5;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Runnable {
    public final /* synthetic */ int c = 1;
    public final long e;
    public final Object k;

    public u0(long j, tg2 tg2Var) {
        this.k = tg2Var;
        this.e = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        long j = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                ((tg2) obj).a(j);
                break;
            default:
                ((c5) obj).b(j);
                break;
        }
    }

    public u0(long j, c5 c5Var) {
        this.e = j;
        this.k = c5Var;
    }
}

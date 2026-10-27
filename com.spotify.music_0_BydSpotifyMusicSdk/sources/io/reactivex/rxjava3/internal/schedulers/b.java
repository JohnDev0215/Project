package io.reactivex.rxjava3.internal.schedulers;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final int a;
    public final c[] b;
    public long c;

    public b(int i, ThreadFactory threadFactory) {
        this.a = i;
        this.b = new c[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.b[i2] = new c(threadFactory);
        }
    }

    public final c a() {
        int i = this.a;
        if (i == 0) {
            return d.g;
        }
        long j = this.c;
        this.c = 1 + j;
        return this.b[(int) (j % ((long) i))];
    }
}

package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.g;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public final class b extends CountDownLatch implements g, io.reactivex.rxjava3.functions.a {
    public Throwable c;

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        this.c = (Throwable) obj;
        countDown();
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        countDown();
    }
}

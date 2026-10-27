package com.byd.data.collect.io;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public abstract class StringCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ExecutorService f3162a = Executors.newSingleThreadExecutor();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract void c(byte[] bArr, int i2);

    public void d(final byte[] bArr, final int i2) {
        this.f3162a.execute(new Runnable() { // from class: com.byd.data.collect.io.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f3176b.c(bArr, i2);
            }
        });
    }
}

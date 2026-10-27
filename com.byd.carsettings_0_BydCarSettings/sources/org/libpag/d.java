package org.libpag;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* JADX INFO: loaded from: classes3.dex */
public class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Runnable f4056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ HandlerThread f4057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ HardwareDecoder f4058c;

    public d(HardwareDecoder hardwareDecoder, Runnable runnable, HandlerThread handlerThread) {
        this.f4058c = hardwareDecoder;
        this.f4056a = runnable;
        this.f4057b = handlerThread;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4056a.run();
        HardwareDecoder.f4009a.getAndDecrement();
        new Handler(Looper.getMainLooper()).post(new c(this));
    }
}

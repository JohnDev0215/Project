package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h5 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f360a;
    public final /* synthetic */ MAPSmsReceiver b;

    public h5(MAPSmsReceiver mAPSmsReceiver, String str) {
        this.b = mAPSmsReceiver;
        this.f360a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.b) {
            Log.i(ga.a("MAPSmsReceiver"), "check if we can submit code: " + this.b.f);
            if (this.b.f != null) {
                Log.i(ga.a("MAPSmsReceiver"), "Start submit code");
                this.b.e = true;
                this.b.b.loadUrl("javascript:submitVerificationCode('" + this.f360a + "')");
            }
        }
    }
}

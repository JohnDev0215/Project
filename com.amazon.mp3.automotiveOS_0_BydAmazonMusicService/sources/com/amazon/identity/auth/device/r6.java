package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.v6;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class r6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f528a;
    public MAPSmsReceiver b;
    public a<q6> c = null;
    public boolean d;

    /* JADX INFO: compiled from: DCP */
    public interface a<T> {
    }

    public r6(t5 t5Var, MAPSmsReceiver mAPSmsReceiver) {
        this.f528a = t5Var;
        this.b = mAPSmsReceiver;
        if (mAPSmsReceiver != null) {
            this.d = mAPSmsReceiver.a(t5Var);
        }
        ga.c("SmsRetrieverManager", "SmsRetriever supporting: " + this.d);
    }

    public final void a(q6 q6Var) {
        a<q6> aVar = this.c;
        if (aVar == null) {
            Log.w(ga.a("SmsRetrieverManager"), "Got null consumer callback, there may be errors when consuming sms");
            return;
        }
        ((v6.f.a) aVar).a(q6Var);
        this.c = null;
        MAPSmsReceiver mAPSmsReceiver = this.b;
        if (mAPSmsReceiver == null || !this.d) {
            return;
        }
        mAPSmsReceiver.b(this.f528a);
    }
}

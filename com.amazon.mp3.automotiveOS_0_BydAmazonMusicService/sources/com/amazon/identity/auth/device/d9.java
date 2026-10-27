package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.b9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d9 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Callback f280a;
    public final /* synthetic */ b9.c b;

    public d9(b9.c cVar, Callback callback) {
        this.b = cVar;
        this.f280a = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.b.b.a(this.f280a);
        } catch (Exception e) {
            Log.e(ga.a("TokenJobQueue"), "MAP didn't handle exception correctly. This should never happen!", e);
            yd.b("MAPTokenJobQueueUnhandledException:" + e.getMessage(), new String[0]);
            MAPErrorCallbackHelper.onError(this.f280a, MAPError.CommonError.INTERNAL_ERROR);
            this.b.a();
        }
    }
}

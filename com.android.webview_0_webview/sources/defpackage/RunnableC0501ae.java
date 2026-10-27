package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Collections;

/* JADX INFO: renamed from: ae, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0501ae implements Runnable {
    public final /* synthetic */ ConnectionResult s;
    public final /* synthetic */ C0451Zc t;

    public RunnableC0501ae(C0451Zc c0451Zc, ConnectionResult connectionResult) {
        this.t = c0451Zc;
        this.s = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC0502af interfaceC0502af;
        C0451Zc c0451Zc = this.t;
        C0415Xc c0415Xc = (C0415Xc) c0451Zc.f.i.get(c0451Zc.b);
        if (c0415Xc == null) {
            return;
        }
        if (!this.s.f()) {
            c0415Xc.h(this.s, null);
            return;
        }
        C0451Zc c0451Zc2 = this.t;
        c0451Zc2.e = true;
        if (c0451Zc2.a.requiresSignIn()) {
            C0451Zc c0451Zc3 = this.t;
            if (!c0451Zc3.e || (interfaceC0502af = c0451Zc3.c) == null) {
                return;
            }
            ((BaseGmsClient) c0451Zc3.a).h(interfaceC0502af, c0451Zc3.d);
            return;
        }
        try {
            Object obj = this.t.a;
            AbstractC0399We abstractC0399We = (AbstractC0399We) this.t.a;
            ((BaseGmsClient) obj).h(null, abstractC0399We.requiresSignIn() ? abstractC0399We.A : Collections.emptySet());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            c0415Xc.h(new ConnectionResult(10), null);
        }
    }
}

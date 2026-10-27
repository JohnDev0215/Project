package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Yd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0434Yd implements Runnable {
    public final /* synthetic */ ConnectionResult s;
    public final /* synthetic */ C0415Xc t;

    public RunnableC0434Yd(C0415Xc c0415Xc, ConnectionResult connectionResult) {
        this.t = c0415Xc;
        this.s = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.t.h(this.s, null);
    }
}

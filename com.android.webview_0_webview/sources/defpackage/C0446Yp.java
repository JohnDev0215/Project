package defpackage;

import android.content.Context;

/* JADX INFO: renamed from: Yp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0446Yp extends Thread {
    public final /* synthetic */ C0464Zp s;
    public final /* synthetic */ Context t;
    public final /* synthetic */ String u;

    public C0446Yp(C0464Zp c0464Zp, Context context, String str) {
        this.s = c0464Zp;
        this.t = context;
        this.u = str;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        C0464Zp c0464Zp = this.s;
        Context context = this.t;
        String str = this.u;
        synchronized (c0464Zp.c) {
            if (c0464Zp.b) {
                return;
            }
            c0464Zp.c(context, str);
            c0464Zp.b = true;
            c0464Zp.c.notifyAll();
        }
    }
}

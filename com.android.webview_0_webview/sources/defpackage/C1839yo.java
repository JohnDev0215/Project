package defpackage;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: renamed from: yo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1839yo {
    public long a;
    public boolean b;
    public final AbstractC0511ao c;
    public final Handler d;
    public InterfaceC1345po e;

    public C1839yo(Looper looper, AbstractC0511ao abstractC0511ao) {
        this.d = new HandlerC1564to(this, looper, null);
        this.c = abstractC0511ao;
    }

    public void a(String str) {
        Handler handler = this.d;
        handler.sendMessage(handler.obtainMessage(9, str));
    }

    public void b(String str) {
        Handler handler = this.d;
        handler.sendMessage(handler.obtainMessage(2, str));
    }
}

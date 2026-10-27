package defpackage;

import android.os.Handler;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class T2 implements Runnable {
    public final /* synthetic */ Callable s;
    public final /* synthetic */ Handler t;
    public final /* synthetic */ V2 u;

    public T2(W2 w2, Callable callable, Handler handler, V2 v2) {
        this.s = callable;
        this.t = handler;
        this.u = v2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object objCall;
        try {
            objCall = this.s.call();
        } catch (Exception unused) {
            objCall = null;
        }
        this.t.post(new S2(this, objCall));
    }
}

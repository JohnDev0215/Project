package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: renamed from: rp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC1455rp extends Handler {
    public final /* synthetic */ C1510sp a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC1455rp(C1510sp c1510sp, Looper looper) {
        super(looper);
        this.a = c1510sp;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 0) {
            return;
        }
        synchronized (this.a.c.f) {
            if (this.a.c.l0 != 0) {
                ((Runnable) message.obj).run();
            }
            this.a.b = false;
            this.a.c.f.notifyAll();
        }
    }
}

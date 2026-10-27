package defpackage;

import android.os.Handler;
import android.os.Message;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class R2 implements Handler.Callback {
    public final /* synthetic */ W2 a;

    public R2(W2 w2) {
        this.a = w2;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            W2 w2 = this.a;
            synchronized (w2.a) {
                if (!w2.c.hasMessages(1)) {
                    w2.b.quit();
                    w2.b = null;
                    w2.c = null;
                }
            }
            return true;
        }
        if (i != 1) {
            return true;
        }
        W2 w22 = this.a;
        Runnable runnable = (Runnable) message.obj;
        Objects.requireNonNull(w22);
        runnable.run();
        synchronized (w22.a) {
            w22.c.removeMessages(0);
            w22.c.sendMessageDelayed(w22.c.obtainMessage(0), w22.f);
        }
        return true;
    }
}

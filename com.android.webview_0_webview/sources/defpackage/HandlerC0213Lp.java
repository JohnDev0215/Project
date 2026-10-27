package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: Lp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC0213Lp extends Handler {
    public HandlerC0213Lp(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            TraceEvent.x("CleanupReference.LazyHolder.handleMessage", null);
            C0248Np c0248Np = (C0248Np) message.obj;
            int i = message.what;
            if (i == 1) {
                C0248Np.e.add(c0248Np);
            } else if (i != 2) {
                Ts.a("CleanupReference", "Bad message=%d", Integer.valueOf(message.what));
            } else {
                C0248Np.a(c0248Np);
            }
        } finally {
        }
        synchronized (C0248Np.c) {
            while (true) {
                C0248Np c0248Np2 = (C0248Np) C0248Np.b.poll();
                if (c0248Np2 != null) {
                    C0248Np.e.remove(c0248Np2);
                    Runnable runnable = c0248Np2.a;
                    c0248Np2.a = null;
                    if (runnable != null) {
                        runnable.run();
                    }
                    c0248Np2.clear();
                } else {
                    C0248Np.c.notifyAll();
                }
                TraceEvent.A("CleanupReference.LazyHolder.handleMessage");
            }
        }
    }
}

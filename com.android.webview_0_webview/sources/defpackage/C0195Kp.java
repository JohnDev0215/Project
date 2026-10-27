package defpackage;

import android.os.Message;

/* JADX INFO: renamed from: Kp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0195Kp extends Thread {
    public C0195Kp(String str) {
        super(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                C0248Np c0248Np = (C0248Np) C0248Np.b.remove();
                synchronized (C0248Np.c) {
                    Message.obtain(AbstractC0230Mp.a, 2, c0248Np).sendToTarget();
                    C0248Np.c.wait(500L);
                }
            } catch (Exception e) {
                Ts.a("CleanupReference", "Queue remove exception:", e);
            }
        }
    }
}

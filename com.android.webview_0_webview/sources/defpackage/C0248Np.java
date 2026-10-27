package defpackage;

import android.os.Looper;
import android.os.Message;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: Np, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0248Np extends WeakReference {
    public static ReferenceQueue b = new ReferenceQueue();
    public static Object c = new Object();
    public static final Thread d;
    public static Set e;
    public Runnable a;

    static {
        C0195Kp c0195Kp = new C0195Kp("CleanupReference");
        d = c0195Kp;
        c0195Kp.setDaemon(true);
        d.start();
        e = new HashSet();
    }

    public C0248Np(Object obj, Runnable runnable) {
        super(obj, b);
        this.a = runnable;
        b(1);
    }

    public static void a(C0248Np c0248Np) {
        Objects.requireNonNull(c0248Np);
        e.remove(c0248Np);
        Runnable runnable = c0248Np.a;
        c0248Np.a = null;
        if (runnable != null) {
            runnable.run();
        }
        c0248Np.clear();
    }

    public final void b(int i) {
        Message messageObtain = Message.obtain(AbstractC0230Mp.a, i, this);
        if (Looper.myLooper() != messageObtain.getTarget().getLooper()) {
            messageObtain.sendToTarget();
        } else {
            messageObtain.getTarget().handleMessage(messageObtain);
            messageObtain.recycle();
        }
    }
}

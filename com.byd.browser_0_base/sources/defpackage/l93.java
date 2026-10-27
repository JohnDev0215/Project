package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import org.chromium.content.browser.input.ThreadedInputConnectionProxyView;

/* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l93 {
    public final h01 a;
    public ThreadedInputConnectionProxyView b;
    public f93 c;
    public a d;
    public boolean e;
    public int g = 0;
    public boolean f = true;

    /* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
    public static class a {
        public boolean a;
    }

    /* JADX INFO: compiled from: ThreadedInputConnectionFactory.java */
    public static class b {
        public static final Handler a;

        static {
            HandlerThread handlerThread = new HandlerThread("InputConnectionHandlerThread", 5);
            handlerThread.start();
            a = new Handler(handlerThread.getLooper());
        }
    }

    public l93(h01 h01Var) {
        this.a = h01Var;
    }
}

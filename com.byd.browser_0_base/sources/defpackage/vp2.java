package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class vp2 {
    public static vp2 b;
    public final Object a = new Object();

    /* JADX INFO: compiled from: SnackbarManager.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            vp2 vp2Var = vp2.this;
            b bVar = (b) message.obj;
            synchronized (vp2Var.a) {
                if (bVar == null || bVar == null) {
                    bVar.getClass();
                    throw null;
                }
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class b {
    }

    public vp2() {
        new Handler(Looper.getMainLooper(), new a());
    }

    public static vp2 a() {
        if (b == null) {
            b = new vp2();
        }
        return b;
    }
}

package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public class wk {
    public static wk a;
    public final Object b = new Object();
    public final Handler c = new Handler(Looper.getMainLooper(), new a());
    public c d;
    public c e;

    /* JADX INFO: compiled from: SnackbarManager.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            wk.this.c((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {
        public final WeakReference<b> a;
        public int b;
        public boolean c;

        public boolean a(b bVar) {
            return bVar != null && this.a.get() == bVar;
        }
    }

    public static wk b() {
        if (a == null) {
            a = new wk();
        }
        return a;
    }

    public final boolean a(c cVar, int i) {
        b bVar = cVar.a.get();
        if (bVar == null) {
            return false;
        }
        this.c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    public void c(c cVar) {
        synchronized (this.b) {
            if (this.d == cVar || this.e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public final boolean d(b bVar) {
        c cVar = this.d;
        return cVar != null && cVar.a(bVar);
    }

    public void e(b bVar) {
        synchronized (this.b) {
            if (d(bVar)) {
                c cVar = this.d;
                if (!cVar.c) {
                    cVar.c = true;
                    this.c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.b) {
            if (d(bVar)) {
                c cVar = this.d;
                if (cVar.c) {
                    cVar.c = false;
                    g(cVar);
                }
            }
        }
    }

    public final void g(c cVar) {
        int i = cVar.b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.c.removeCallbacksAndMessages(cVar);
        Handler handler = this.c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }
}

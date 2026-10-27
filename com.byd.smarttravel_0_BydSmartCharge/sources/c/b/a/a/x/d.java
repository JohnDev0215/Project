package c.b.a.a.x;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f1900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1901b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f1902c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f1903d;
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
            d.this.c((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f1905a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1906b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1907c;

        public boolean a(b bVar) {
            return bVar != null && this.f1905a.get() == bVar;
        }
    }

    public static d b() {
        if (f1900a == null) {
            f1900a = new d();
        }
        return f1900a;
    }

    public final boolean a(c cVar, int i) {
        b bVar = cVar.f1905a.get();
        if (bVar == null) {
            return false;
        }
        this.f1902c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    public void c(c cVar) {
        synchronized (this.f1901b) {
            if (this.f1903d == cVar || this.e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public final boolean d(b bVar) {
        c cVar = this.f1903d;
        return cVar != null && cVar.a(bVar);
    }

    public void e(b bVar) {
        synchronized (this.f1901b) {
            if (d(bVar)) {
                c cVar = this.f1903d;
                if (!cVar.f1907c) {
                    cVar.f1907c = true;
                    this.f1902c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f1901b) {
            if (d(bVar)) {
                c cVar = this.f1903d;
                if (cVar.f1907c) {
                    cVar.f1907c = false;
                    g(cVar);
                }
            }
        }
    }

    public final void g(c cVar) {
        int i = cVar.f1906b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f1902c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f1902c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }
}

package p2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f4529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f4530b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f4531c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f4532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f4533e;

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
        void a(int i3);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f4535a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4536b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4537c;

        public boolean a(b bVar) {
            return bVar != null && this.f4535a.get() == bVar;
        }
    }

    public static d b() {
        if (f4529a == null) {
            f4529a = new d();
        }
        return f4529a;
    }

    public final boolean a(c cVar, int i3) {
        b bVar = cVar.f4535a.get();
        if (bVar == null) {
            return false;
        }
        this.f4531c.removeCallbacksAndMessages(cVar);
        bVar.a(i3);
        return true;
    }

    public void c(c cVar) {
        synchronized (this.f4530b) {
            if (this.f4532d == cVar || this.f4533e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public final boolean d(b bVar) {
        c cVar = this.f4532d;
        return cVar != null && cVar.a(bVar);
    }

    public void e(b bVar) {
        synchronized (this.f4530b) {
            if (d(bVar)) {
                c cVar = this.f4532d;
                if (!cVar.f4537c) {
                    cVar.f4537c = true;
                    this.f4531c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f4530b) {
            if (d(bVar)) {
                c cVar = this.f4532d;
                if (cVar.f4537c) {
                    cVar.f4537c = false;
                    g(cVar);
                }
            }
        }
    }

    public final void g(c cVar) {
        int i3 = cVar.f4536b;
        if (i3 == -2) {
            return;
        }
        if (i3 <= 0) {
            i3 = i3 == -1 ? 1500 : 2750;
        }
        this.f4531c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f4531c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i3);
    }
}

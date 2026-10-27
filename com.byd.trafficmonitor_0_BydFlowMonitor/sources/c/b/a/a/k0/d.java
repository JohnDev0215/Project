package c.b.a.a.k0;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f3941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3942b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f3943c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3944d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f3945e;

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
        void a(int i2);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f3947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3948b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3949c;

        public boolean a(b bVar) {
            return bVar != null && this.f3947a.get() == bVar;
        }
    }

    public static d b() {
        if (f3941a == null) {
            f3941a = new d();
        }
        return f3941a;
    }

    public final boolean a(c cVar, int i2) {
        b bVar = cVar.f3947a.get();
        if (bVar == null) {
            return false;
        }
        this.f3943c.removeCallbacksAndMessages(cVar);
        bVar.a(i2);
        return true;
    }

    public void c(c cVar) {
        synchronized (this.f3942b) {
            if (this.f3944d == cVar || this.f3945e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public final boolean d(b bVar) {
        c cVar = this.f3944d;
        return cVar != null && cVar.a(bVar);
    }

    public void e(b bVar) {
        synchronized (this.f3942b) {
            if (d(bVar)) {
                c cVar = this.f3944d;
                if (!cVar.f3949c) {
                    cVar.f3949c = true;
                    this.f3943c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f3942b) {
            if (d(bVar)) {
                c cVar = this.f3944d;
                if (cVar.f3949c) {
                    cVar.f3949c = false;
                    g(cVar);
                }
            }
        }
    }

    public final void g(c cVar) {
        int i2 = cVar.f3948b;
        if (i2 == -2) {
            return;
        }
        if (i2 <= 0) {
            i2 = i2 == -1 ? 1500 : 2750;
        }
        this.f3943c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f3943c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i2);
    }
}

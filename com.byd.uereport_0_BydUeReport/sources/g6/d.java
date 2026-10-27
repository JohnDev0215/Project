package g6;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f5090a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5091b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f5092c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f5093d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f5094e;

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
        void a(int i8);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f5096a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5097b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5098c;

        public boolean a(b bVar) {
            return bVar != null && this.f5096a.get() == bVar;
        }
    }

    public static d b() {
        if (f5090a == null) {
            f5090a = new d();
        }
        return f5090a;
    }

    public final boolean a(c cVar, int i8) {
        b bVar = cVar.f5096a.get();
        if (bVar == null) {
            return false;
        }
        this.f5092c.removeCallbacksAndMessages(cVar);
        bVar.a(i8);
        return true;
    }

    public void c(c cVar) {
        synchronized (this.f5091b) {
            if (this.f5093d == cVar || this.f5094e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public final boolean d(b bVar) {
        c cVar = this.f5093d;
        return cVar != null && cVar.a(bVar);
    }

    public void e(b bVar) {
        synchronized (this.f5091b) {
            if (d(bVar)) {
                c cVar = this.f5093d;
                if (!cVar.f5098c) {
                    cVar.f5098c = true;
                    this.f5092c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f5091b) {
            if (d(bVar)) {
                c cVar = this.f5093d;
                if (cVar.f5098c) {
                    cVar.f5098c = false;
                    g(cVar);
                }
            }
        }
    }

    public final void g(c cVar) {
        int i8 = cVar.f5097b;
        if (i8 == -2) {
            return;
        }
        if (i8 <= 0) {
            i8 = i8 == -1 ? 1500 : 2750;
        }
        this.f5092c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f5092c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i8);
    }
}

package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f4231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f4232b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f4233c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f4234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f4235e;

    /* JADX INFO: compiled from: SnackbarManager.java */
    class a implements Handler.Callback {
        a() {
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
    interface b {
        void a(int i2);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<b> f4237a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4238b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4239c;

        boolean a(b bVar) {
            return bVar != null && this.f4237a.get() == bVar;
        }
    }

    private d() {
    }

    private boolean a(c cVar, int i2) {
        b bVar = cVar.f4237a.get();
        if (bVar == null) {
            return false;
        }
        this.f4233c.removeCallbacksAndMessages(cVar);
        bVar.a(i2);
        return true;
    }

    static d b() {
        if (f4231a == null) {
            f4231a = new d();
        }
        return f4231a;
    }

    private boolean d(b bVar) {
        c cVar = this.f4234d;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i2 = cVar.f4238b;
        if (i2 == -2) {
            return;
        }
        if (i2 <= 0) {
            i2 = i2 == -1 ? 1500 : 2750;
        }
        this.f4233c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f4233c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i2);
    }

    void c(c cVar) {
        synchronized (this.f4232b) {
            if (this.f4234d == cVar || this.f4235e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f4232b) {
            if (d(bVar)) {
                c cVar = this.f4234d;
                if (!cVar.f4239c) {
                    cVar.f4239c = true;
                    this.f4233c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f4232b) {
            if (d(bVar)) {
                c cVar = this.f4234d;
                if (cVar.f4239c) {
                    cVar.f4239c = false;
                    g(cVar);
                }
            }
        }
    }
}

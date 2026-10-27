package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f2489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2490b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f2491c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f2492d;
    private c e;

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
        void a(int i);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<b> f2494a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2495b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2496c;

        boolean a(b bVar) {
            return bVar != null && this.f2494a.get() == bVar;
        }
    }

    private d() {
    }

    private boolean a(c cVar, int i) {
        b bVar = cVar.f2494a.get();
        if (bVar == null) {
            return false;
        }
        this.f2491c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    static d b() {
        if (f2489a == null) {
            f2489a = new d();
        }
        return f2489a;
    }

    private boolean d(b bVar) {
        c cVar = this.f2492d;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i = cVar.f2495b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f2491c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f2491c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    void c(c cVar) {
        synchronized (this.f2490b) {
            if (this.f2492d == cVar || this.e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f2490b) {
            if (d(bVar)) {
                c cVar = this.f2492d;
                if (!cVar.f2496c) {
                    cVar.f2496c = true;
                    this.f2491c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f2490b) {
            if (d(bVar)) {
                c cVar = this.f2492d;
                if (cVar.f2496c) {
                    cVar.f2496c = false;
                    g(cVar);
                }
            }
        }
    }
}

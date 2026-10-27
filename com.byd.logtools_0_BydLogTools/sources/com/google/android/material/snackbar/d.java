package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f3807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3808b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f3809c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f3810d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f3811e;

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
        final WeakReference<b> f3813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3814b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3815c;

        boolean a(b bVar) {
            return bVar != null && this.f3813a.get() == bVar;
        }
    }

    private d() {
    }

    private boolean a(c cVar, int i) {
        b bVar = cVar.f3813a.get();
        if (bVar == null) {
            return false;
        }
        this.f3809c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    static d b() {
        if (f3807a == null) {
            f3807a = new d();
        }
        return f3807a;
    }

    private boolean d(b bVar) {
        c cVar = this.f3810d;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i = cVar.f3814b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f3809c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f3809c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    void c(c cVar) {
        synchronized (this.f3808b) {
            if (this.f3810d == cVar || this.f3811e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f3808b) {
            if (d(bVar)) {
                c cVar = this.f3810d;
                if (!cVar.f3815c) {
                    cVar.f3815c = true;
                    this.f3809c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f3808b) {
            if (d(bVar)) {
                c cVar = this.f3810d;
                if (cVar.f3815c) {
                    cVar.f3815c = false;
                    g(cVar);
                }
            }
        }
    }
}

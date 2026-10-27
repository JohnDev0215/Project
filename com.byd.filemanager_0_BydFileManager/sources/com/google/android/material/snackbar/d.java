package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f4378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f4379b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f4380c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f4381d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f4382e;

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
        final WeakReference<b> f4384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4385b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4386c;

        boolean a(b bVar) {
            return bVar != null && this.f4384a.get() == bVar;
        }
    }

    private d() {
    }

    private boolean a(c cVar, int i) {
        b bVar = cVar.f4384a.get();
        if (bVar == null) {
            return false;
        }
        this.f4380c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    static d b() {
        if (f4378a == null) {
            f4378a = new d();
        }
        return f4378a;
    }

    private boolean d(b bVar) {
        c cVar = this.f4381d;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i = cVar.f4385b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f4380c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f4380c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    void c(c cVar) {
        synchronized (this.f4379b) {
            if (this.f4381d == cVar || this.f4382e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f4379b) {
            if (d(bVar)) {
                c cVar = this.f4381d;
                if (!cVar.f4386c) {
                    cVar.f4386c = true;
                    this.f4380c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f4379b) {
            if (d(bVar)) {
                c cVar = this.f4381d;
                if (cVar.f4386c) {
                    cVar.f4386c = false;
                    g(cVar);
                }
            }
        }
    }
}

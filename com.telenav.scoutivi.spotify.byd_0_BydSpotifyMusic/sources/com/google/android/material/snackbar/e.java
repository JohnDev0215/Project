package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static e f4699e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f4700a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f4701b = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f4702c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f4703d;

    /* JADX INFO: compiled from: SnackbarManager.java */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            e.this.c((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    interface b {
        void a(int i7);
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<b> f4705a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4706b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4707c;

        boolean a(b bVar) {
            return bVar != null && this.f4705a.get() == bVar;
        }
    }

    private e() {
    }

    private boolean a(c cVar, int i7) {
        b bVar = cVar.f4705a.get();
        if (bVar == null) {
            return false;
        }
        this.f4701b.removeCallbacksAndMessages(cVar);
        bVar.a(i7);
        return true;
    }

    static e b() {
        if (f4699e == null) {
            f4699e = new e();
        }
        return f4699e;
    }

    private boolean d(b bVar) {
        c cVar = this.f4702c;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i7 = cVar.f4706b;
        if (i7 == -2) {
            return;
        }
        if (i7 <= 0) {
            i7 = i7 == -1 ? 1500 : 2750;
        }
        this.f4701b.removeCallbacksAndMessages(cVar);
        Handler handler = this.f4701b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i7);
    }

    void c(c cVar) {
        synchronized (this.f4700a) {
            if (this.f4702c == cVar || this.f4703d == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f4700a) {
            if (d(bVar)) {
                c cVar = this.f4702c;
                if (!cVar.f4707c) {
                    cVar.f4707c = true;
                    this.f4701b.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f4700a) {
            if (d(bVar)) {
                c cVar = this.f4702c;
                if (cVar.f4707c) {
                    cVar.f4707c = false;
                    g(cVar);
                }
            }
        }
    }
}

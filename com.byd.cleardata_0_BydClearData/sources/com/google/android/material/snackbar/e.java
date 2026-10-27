package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f3169a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3170b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f3171c = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f3172d;
    private c e;

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

    interface b {
        void a(int i);
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final WeakReference<b> f3174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3176c;

        boolean a(b bVar) {
            return bVar != null && this.f3174a.get() == bVar;
        }
    }

    private e() {
    }

    private boolean a(c cVar, int i) {
        b bVar = cVar.f3174a.get();
        if (bVar == null) {
            return false;
        }
        this.f3171c.removeCallbacksAndMessages(cVar);
        bVar.a(i);
        return true;
    }

    static e b() {
        if (f3169a == null) {
            f3169a = new e();
        }
        return f3169a;
    }

    private boolean d(b bVar) {
        c cVar = this.f3172d;
        return cVar != null && cVar.a(bVar);
    }

    private void g(c cVar) {
        int i = cVar.f3175b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f3171c.removeCallbacksAndMessages(cVar);
        Handler handler = this.f3171c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    void c(c cVar) {
        synchronized (this.f3170b) {
            if (this.f3172d == cVar || this.e == cVar) {
                a(cVar, 2);
            }
        }
    }

    public void e(b bVar) {
        synchronized (this.f3170b) {
            if (d(bVar)) {
                c cVar = this.f3172d;
                if (!cVar.f3176c) {
                    cVar.f3176c = true;
                    this.f3171c.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void f(b bVar) {
        synchronized (this.f3170b) {
            if (d(bVar)) {
                c cVar = this.f3172d;
                if (cVar.f3176c) {
                    cVar.f3176c = false;
                    g(cVar);
                }
            }
        }
    }
}

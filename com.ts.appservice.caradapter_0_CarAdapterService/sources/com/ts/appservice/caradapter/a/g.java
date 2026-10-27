package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import com.ts.appservice.caradapter.a.k;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DoCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<E extends IInterface> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f9a;
    HandlerThread d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Map<IBinder, Boolean> f10b = new HashMap();
    protected k<E> c = new k<>();
    protected Handler e = f();

    /* JADX INFO: compiled from: DoCallback.java */
    class a implements k.a {
        a() {
        }

        @Override // com.ts.appservice.caradapter.a.k.a
        public void a(IInterface iInterface) {
            LogUtil.verbose("do callback Died binder:", iInterface.asBinder().toString());
            g.this.f10b.remove(iInterface.asBinder());
        }

        @Override // com.ts.appservice.caradapter.a.k.a
        public void b(int i) {
            LogUtil.verbose("do callback register callback count:", i + "");
        }
    }

    /* JADX INFO: compiled from: DoCallback.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ IInterface f12a;

        b(IInterface iInterface) {
            this.f12a = iInterface;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.c.unregister(this.f12a);
            g.this.f10b.remove(this.f12a.asBinder());
        }
    }

    /* JADX INFO: compiled from: DoCallback.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ IInterface f14a;

        c(IInterface iInterface) {
            this.f14a = iInterface;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.c.register(this.f14a);
            g.this.f10b.put(this.f14a.asBinder(), Boolean.FALSE);
        }
    }

    /* JADX INFO: compiled from: DoCallback.java */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.c.kill();
            g gVar = g.this;
            gVar.c = null;
            gVar.c = new k<>();
        }
    }

    /* JADX INFO: compiled from: DoCallback.java */
    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            g.this.a(message);
        }
    }

    protected g(Context context) {
        this.f9a = "";
        this.f9a = d();
        this.d = com.ts.appservice.caradapter.e.b.a(this.f9a);
        this.c.a(new a());
    }

    private Handler f() {
        Handler handler = this.e;
        if (handler != null) {
            return handler;
        }
        if (this.d != null) {
            return new e(this.d.getLooper());
        }
        LogUtil.error(this.f9a, "mHandlerThread is null !!");
        return null;
    }

    protected abstract void a(Message message);

    protected void b(Message message) {
        this.e.sendMessage(message);
    }

    protected void c(int i) {
        Handler handler = this.e;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    protected abstract String d();

    protected Message e(int i) {
        return this.e.obtainMessage(i);
    }

    public void g() {
        this.e.post(new d());
    }

    public void h(E e2) {
        this.e.post(new c(e2));
    }

    public void i(E e2) {
        this.e.post(new b(e2));
    }
}

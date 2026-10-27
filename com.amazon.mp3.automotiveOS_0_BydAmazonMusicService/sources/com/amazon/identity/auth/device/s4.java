package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class s4<T> implements ServiceConnection {
    public static final long j = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);
    public static final String k = s4.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f538a;
    public final Executor b;
    public final String d;
    public ComponentName f;
    public T g;
    public List<b> h;
    public boolean i;
    public final List<b<T>> e = new LinkedList();
    public final Handler c = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (s4.this) {
                if (s4.this.g != null) {
                    return;
                }
                Log.e(ga.a(s4.k), "Application timed out trying to bind to " + s4.this.f);
                s4 s4Var = s4.this;
                List<b> list = s4Var.h;
                s4Var.h = null;
                if (list != null) {
                    yd.a("BindTimeout", new String[0]);
                    Iterator<b> it = list.iterator();
                    while (it.hasNext()) {
                        s4.this.b.execute(new c(it.next()));
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static abstract class b<T> implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final s4<T> f540a;

        public b(s4<T> s4Var) {
            this.f540a = s4Var;
        }

        public abstract void a();

        public abstract void a(T t) throws RemoteException;

        @Override // java.lang.Runnable
        public void run() {
            T t;
            s4<T> s4Var = this.f540a;
            synchronized (s4Var) {
                t = s4Var.g;
            }
            if (t == null) {
                Log.w(ga.a(s4.k), "Service was disconnected before task could execute; re-enqueuing task to run after service re-connects.");
                s4Var.a(this);
            } else {
                try {
                    a(t);
                } catch (RemoteException unused) {
                    a();
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b<?> f541a;

        public c(b<?> bVar) {
            this.f541a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f541a.a();
        }
    }

    public s4(Context context, String str, Executor executor) {
        this.f538a = context.getApplicationContext();
        this.d = str;
        this.b = executor;
    }

    public abstract T a(IBinder iBinder);

    public final synchronized void a(b<T> bVar) {
        if (this.g == null) {
            if (this.h == null) {
                this.h = new ArrayList();
                this.c.postDelayed(new a(), j);
            }
            this.h.add(bVar);
        } else {
            this.b.execute(bVar);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<b> list;
        synchronized (this) {
            String str = k;
            Objects.toString(this.f);
            ga.a(str);
            this.g = a(iBinder);
            list = this.h;
            this.h = null;
        }
        if (list != null) {
            Iterator<b> it = list.iterator();
            while (it.hasNext()) {
                this.b.execute(it.next());
            }
        }
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceDisconnected(ComponentName componentName) {
        String str = k;
        Objects.toString(this.f);
        ga.a(str);
        this.g = null;
        Iterator<b<T>> it = this.e.iterator();
        while (it.hasNext()) {
            this.b.execute(new c(it.next()));
        }
        this.e.clear();
    }
}

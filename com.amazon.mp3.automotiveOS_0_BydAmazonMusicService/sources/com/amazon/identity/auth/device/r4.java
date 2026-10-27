package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class r4 {
    public static final String g = "com.amazon.identity.auth.device.r4";
    public static final long h = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f524a = new Object[0];
    public final Context b;
    public final Intent c;
    public final int d;
    public ServiceConnection e;
    public boolean f;

    /* JADX INFO: compiled from: DCP */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String str = r4.g;
            String.format("Connected to service: %s", componentName.toString());
            ga.a(str);
            r4.this.f = true;
            try {
                r4.this.useService(componentName, iBinder);
            } catch (RemoteException unused) {
                Log.e(ga.a(r4.g), String.format("Service died: %s", componentName.toString()));
                r4.this.unbind();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            r4 r4Var;
            synchronized (r4.this.f524a) {
                r4Var = r4.this;
                r4Var.e = null;
            }
            r4Var.serviceDisconnected();
            String str = r4.g;
            String.format("Disconnected from service: %s", componentName.toString());
            ga.a(str);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (r4.this.f524a) {
                r4 r4Var = r4.this;
                if (r4Var.f) {
                    return;
                }
                Log.e(ga.a(r4.g), String.format("Application timed out trying to bind to %s", r4Var.c.getComponent().getPackageName()));
                r4 r4Var2 = r4.this;
                r4Var2.e = null;
                r4Var2.serviceTimedOut();
            }
        }
    }

    public r4(Context context, Intent intent, int i) {
        if (context == null || intent == null) {
            throw new IllegalArgumentException();
        }
        this.b = context;
        this.c = intent;
        this.f = false;
        this.e = new a();
        this.d = a(i);
    }

    @TargetApi(14)
    public final int a(int i) {
        return i | 4 | 16;
    }

    public final boolean call() {
        boolean zBindService;
        synchronized (this.f524a) {
            ServiceConnection serviceConnection = this.e;
            if (serviceConnection == null) {
                throw new IllegalStateException("Attempted to reuse a BoundServiceCaller.  Call method can only be executed once.");
            }
            zBindService = this.b.bindService(this.c, serviceConnection, this.d);
        }
        if (zBindService) {
            new Handler(Looper.getMainLooper()).postDelayed(new b(), h);
            return zBindService;
        }
        Log.e(ga.a(g), "Failed to bind to service.");
        return false;
    }

    public void serviceDisconnected() {
    }

    public void serviceTimedOut() {
    }

    public void unbind() {
        synchronized (this.f524a) {
            ServiceConnection serviceConnection = this.e;
            if (serviceConnection != null) {
                try {
                    this.b.unbindService(serviceConnection);
                } catch (IllegalArgumentException unused) {
                    Log.w(ga.a(g), String.format("IllegalArgumentException is received during unbinding from %s. Ignored.", this.c.getComponent().getPackageName()));
                }
                this.e = null;
            }
        }
    }

    public void useService(ComponentName componentName, IBinder iBinder) throws RemoteException {
        useService(iBinder);
    }

    public abstract void useService(IBinder iBinder) throws RemoteException;
}

package p;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class ai2 implements ComponentCallbacks2, bn1 {
    public final Context c;
    public final WeakReference e;
    public final cn1 k;
    public volatile boolean l;
    public final AtomicBoolean m;

    public ai2(coil.b bVar, Context context, boolean z) {
        cn1 zb0Var;
        this.c = context;
        this.e = new WeakReference(bVar);
        if (z) {
            bVar.getClass();
            Object obj = sz.a;
            ConnectivityManager connectivityManager = (ConnectivityManager) pz.b(context, ConnectivityManager.class);
            if (connectivityManager == null || (Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", "android.permission.ACCESS_NETWORK_STATE") ? context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0 : !lo1.a(new mo1(context).a))) {
                zb0Var = new zb0();
            } else {
                try {
                    zb0Var = new c12(connectivityManager, this);
                } catch (Exception unused) {
                    zb0Var = new zb0();
                }
            }
        } else {
            zb0Var = new zb0();
        }
        this.k = zb0Var;
        this.l = zb0Var.isOnline();
        this.m = new AtomicBoolean(false);
    }

    public final void a() {
        if (this.m.getAndSet(true)) {
            return;
        }
        this.c.unregisterComponentCallbacks(this);
        this.k.shutdown();
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        if (((coil.b) this.e.get()) == null) {
            a();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        onTrimMemory(80);
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        lq2 lq2Var;
        b12 b12Var;
        coil.b bVar = (coil.b) this.e.get();
        if (bVar != null) {
            v81 v81Var = bVar.b;
            if (v81Var != null && (b12Var = (b12) v81Var.getValue()) != null) {
                b12Var.a.a(i);
                b12Var.b.a(i);
            }
            lq2Var = lq2.a;
        } else {
            lq2Var = null;
        }
        if (lq2Var == null) {
            a();
        }
    }
}

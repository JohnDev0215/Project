package p;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class g03 {
    public static final HashMap o = new HashMap();
    public final Context a;
    public final tz2 b;
    public final String c;
    public final ArrayList d;
    public final HashSet e;
    public final Object f;
    public boolean g;
    public final Intent h;
    public final d03 i;
    public final WeakReference j;
    public final xz2 k;
    public final AtomicInteger l;
    public zf1 m;
    public IInterface n;

    /* JADX WARN: Type inference failed for: r1v3, types: [p.xz2] */
    public g03(Context context, tz2 tz2Var, String str, Intent intent) {
        qo qoVar = qo.t;
        this.d = new ArrayList();
        this.e = new HashSet();
        this.f = new Object();
        this.k = new IBinder.DeathRecipient() { // from class: p.xz2
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                g03 g03Var = this.a;
                g03Var.b.e("reportBinderDeath", new Object[0]);
                u4.z(g03Var.j.get());
                String str2 = g03Var.c;
                g03Var.b.e("%s : Binder has died.", str2);
                ArrayList<vz2> arrayList = g03Var.d;
                for (vz2 vz2Var : arrayList) {
                    RemoteException remoteException = new RemoteException(String.valueOf(str2).concat(" : Binder has died."));
                    j23 j23Var = vz2Var.c;
                    if (j23Var != null) {
                        j23Var.a(remoteException);
                    }
                }
                arrayList.clear();
                g03Var.d();
            }
        };
        this.l = new AtomicInteger(0);
        this.a = context;
        this.b = tz2Var;
        this.c = str;
        this.h = intent;
        this.i = qoVar;
        this.j = new WeakReference(null);
    }

    public final Handler a() {
        Handler handler;
        HashMap map = o;
        synchronized (map) {
            try {
                if (!map.containsKey(this.c)) {
                    HandlerThread handlerThread = new HandlerThread(this.c, 10);
                    handlerThread.start();
                    map.put(this.c, new Handler(handlerThread.getLooper()));
                }
                handler = (Handler) map.get(this.c);
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    public final void b(vz2 vz2Var, j23 j23Var) {
        synchronized (this.f) {
            this.e.add(j23Var);
            wc0 wc0Var = j23Var.a;
            ay2 ay2Var = new ay2(this, j23Var);
            wc0Var.getClass();
            ((s23) wc0Var.c).a(new i03(cj2.a, ay2Var));
            wc0Var.i();
        }
        synchronized (this.f) {
            try {
                if (this.l.getAndIncrement() > 0) {
                    this.b.b("Already connected to the service.", new Object[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        a().post(new zz2(this, vz2Var.c, vz2Var, 1));
    }

    public final void c(j23 j23Var) {
        synchronized (this.f) {
            this.e.remove(j23Var);
        }
        synchronized (this.f) {
            try {
                int i = 0;
                if (this.l.get() > 0 && this.l.decrementAndGet() > 0) {
                    this.b.e("Leaving the connection open for other ongoing calls.", new Object[0]);
                } else {
                    a().post(new a03(i, this));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d() {
        synchronized (this.f) {
            try {
                Iterator it = this.e.iterator();
                while (it.hasNext()) {
                    ((j23) it.next()).a(new RemoteException(String.valueOf(this.c).concat(" : Binder has died.")));
                }
                this.e.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

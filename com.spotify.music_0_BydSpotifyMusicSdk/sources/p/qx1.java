package p;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.SystemForegroundService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class qx1 implements gw0 {
    public static final String l = vc1.d("Processor");
    public final Context b;
    public final sw c;
    public final yx2 d;
    public final WorkDatabase e;
    public final HashMap g = new HashMap();
    public final HashMap f = new HashMap();
    public final HashSet i = new HashSet();
    public final ArrayList j = new ArrayList();
    public PowerManager.WakeLock a = null;
    public final Object k = new Object();
    public final HashMap h = new HashMap();

    public qx1(Context context, sw swVar, yx2 yx2Var, WorkDatabase workDatabase) {
        this.b = context;
        this.c = swVar;
        this.d = yx2Var;
        this.e = workDatabase;
    }

    public static boolean d(vy2 vy2Var, int i) {
        if (vy2Var == null) {
            vc1.c().getClass();
            return false;
        }
        vy2Var.y = i;
        vy2Var.h();
        vy2Var.x.cancel(true);
        if (vy2Var.l == null || !(vy2Var.x.c instanceof d1)) {
            Objects.toString(vy2Var.k);
            vc1.c().getClass();
        } else {
            vy2Var.l.e(i);
        }
        vc1.c().getClass();
        return true;
    }

    public final void a(uk0 uk0Var) {
        synchronized (this.k) {
            this.j.add(uk0Var);
        }
    }

    public final vy2 b(String str) {
        vy2 vy2Var = (vy2) this.f.remove(str);
        boolean z = vy2Var != null;
        if (!z) {
            vy2Var = (vy2) this.g.remove(str);
        }
        this.h.remove(str);
        if (z) {
            synchronized (this.k) {
                try {
                    if (!(true ^ this.f.isEmpty())) {
                        Context context = this.b;
                        String str2 = ci2.r;
                        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
                        intent.setAction("ACTION_STOP_FOREGROUND");
                        try {
                            this.b.startService(intent);
                        } catch (Throwable th) {
                            vc1.c().b(l, "Unable to stop foreground service", th);
                        }
                        PowerManager.WakeLock wakeLock = this.a;
                        if (wakeLock != null) {
                            wakeLock.release();
                            this.a = null;
                        }
                    }
                } finally {
                }
            }
        }
        return vy2Var;
    }

    public final vy2 c(String str) {
        vy2 vy2Var = (vy2) this.f.get(str);
        return vy2Var == null ? (vy2) this.g.get(str) : vy2Var;
    }

    public final void e(uk0 uk0Var) {
        synchronized (this.k) {
            this.j.remove(uk0Var);
        }
    }

    public final void f(String str, ew0 ew0Var) {
        synchronized (this.k) {
            try {
                vc1.c().getClass();
                vy2 vy2Var = (vy2) this.g.remove(str);
                if (vy2Var != null) {
                    if (this.a == null) {
                        PowerManager.WakeLock wakeLockA = sv2.a(this.b, "ProcessorForegroundLck");
                        this.a = wakeLockA;
                        wakeLockA.acquire();
                    }
                    this.f.put(str, vy2Var);
                    Intent intentD = ci2.d(this.b, ci1.u(vy2Var.k), ew0Var);
                    Context context = this.b;
                    Object obj = sz.a;
                    rz.b(context, intentD);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean g(xf2 xf2Var, kk0 kk0Var) {
        boolean z;
        final rx2 rx2Var = xf2Var.a;
        String str = rx2Var.a;
        ArrayList arrayList = new ArrayList();
        ky2 ky2Var = (ky2) this.e.n(new ox1(this, arrayList, str, 0));
        if (ky2Var == null) {
            vc1 vc1VarC = vc1.c();
            rx2Var.toString();
            vc1VarC.getClass();
            this.d.d.execute(new Runnable() { // from class: p.px1
                public final /* synthetic */ boolean k = false;

                @Override // java.lang.Runnable
                public final void run() {
                    qx1 qx1Var = this.c;
                    rx2 rx2Var2 = rx2Var;
                    boolean z2 = this.k;
                    synchronized (qx1Var.k) {
                        try {
                            Iterator it = qx1Var.j.iterator();
                            while (it.hasNext()) {
                                ((uk0) it.next()).b(rx2Var2, z2);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            });
            return false;
        }
        synchronized (this.k) {
            try {
                synchronized (this.k) {
                    z = c(str) != null;
                }
                if (z) {
                    Set set = (Set) this.h.get(str);
                    if (((xf2) set.iterator().next()).a.b == rx2Var.b) {
                        set.add(xf2Var);
                        vc1 vc1VarC2 = vc1.c();
                        rx2Var.toString();
                        vc1VarC2.getClass();
                    } else {
                        this.d.d.execute(new Runnable() { // from class: p.px1
                            public final /* synthetic */ boolean k = false;

                            @Override // java.lang.Runnable
                            public final void run() {
                                qx1 qx1Var = this.c;
                                rx2 rx2Var2 = rx2Var;
                                boolean z2 = this.k;
                                synchronized (qx1Var.k) {
                                    try {
                                        Iterator it = qx1Var.j.iterator();
                                        while (it.hasNext()) {
                                            ((uk0) it.next()).b(rx2Var2, z2);
                                        }
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                }
                            }
                        });
                    }
                    return false;
                }
                if (ky2Var.t != rx2Var.b) {
                    this.d.d.execute(new Runnable() { // from class: p.px1
                        public final /* synthetic */ boolean k = false;

                        @Override // java.lang.Runnable
                        public final void run() {
                            qx1 qx1Var = this.c;
                            rx2 rx2Var2 = rx2Var;
                            boolean z2 = this.k;
                            synchronized (qx1Var.k) {
                                try {
                                    Iterator it = qx1Var.j.iterator();
                                    while (it.hasNext()) {
                                        ((uk0) it.next()).b(rx2Var2, z2);
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    });
                    return false;
                }
                Context context = this.b;
                sw swVar = this.c;
                yx2 yx2Var = this.d;
                WorkDatabase workDatabase = this.e;
                uy2 uy2Var = new uy2();
                uy2Var.i = new kk0(7);
                uy2Var.a = context.getApplicationContext();
                uy2Var.d = yx2Var;
                uy2Var.c = this;
                uy2Var.e = swVar;
                uy2Var.f = workDatabase;
                uy2Var.g = ky2Var;
                uy2Var.h = arrayList;
                if (kk0Var != null) {
                    uy2Var.i = kk0Var;
                }
                vy2 vy2Var = new vy2(uy2Var);
                androidx.work.impl.utils.futures.b bVar = vy2Var.w;
                bVar.a(new kc0(this, bVar, vy2Var, 5), this.d.d);
                this.g.put(str, vy2Var);
                HashSet hashSet = new HashSet();
                hashSet.add(xf2Var);
                this.h.put(str, hashSet);
                this.d.a.execute(vy2Var);
                vc1 vc1VarC3 = vc1.c();
                rx2Var.toString();
                vc1VarC3.getClass();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

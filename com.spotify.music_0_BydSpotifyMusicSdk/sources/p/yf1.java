package p;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import androidx.work.Worker;
import com.google.android.gms.tasks.RuntimeExecutionException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class yf1 implements Runnable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ yf1(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    private void a() {
        ky2 ky2Var;
        qx1 qx1Var = ((ci2) this.k).c.l;
        String str = (String) this.e;
        synchronized (qx1Var.k) {
            try {
                vy2 vy2VarC = qx1Var.c(str);
                ky2Var = vy2VarC != null ? vy2VarC.k : null;
            } finally {
            }
        }
        if (ky2Var == null || !ky2Var.b()) {
            return;
        }
        synchronized (((ci2) this.k).k) {
            ((ci2) this.k).n.put(ci1.u(ky2Var), ky2Var);
            Object obj = this.k;
            ((ci2) this.k).o.put(ci1.u(ky2Var), androidx.work.impl.constraints.b.a(((ci2) obj).f112p, ky2Var, ((ci2) obj).e.b, (ci2) obj));
        }
    }

    private void b() {
        try {
            ((Runnable) this.k).run();
            synchronized (((ob2) this.e).l) {
                ((ob2) this.e).a();
            }
        } catch (Throwable th) {
            synchronized (((ob2) this.e).l) {
                ((ob2) this.e).a();
                throw th;
            }
        }
    }

    private final void c() {
        synchronized (((i23) this.k).k) {
            try {
                Object obj = ((i23) this.k).l;
                if (((xp1) obj) != null) {
                    Exception excB = ((aj2) this.e).b();
                    he1.g(excB);
                    ((xp1) obj).d(excB);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void d() {
        synchronized (((i23) this.k).k) {
            try {
                Object obj = ((i23) this.k).l;
                if (((eq1) obj) != null) {
                    ((eq1) obj).onSuccess(((aj2) this.e).c());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v37, types: [p.vy2] */
    @Override // java.lang.Runnable
    public final void run() {
        qi1 qi1Var;
        Exception exc;
        switch (this.c) {
            case 0:
                boolean z = cg1.b;
                Object obj = this.k;
                if (!z) {
                    ((zf1) obj).a();
                    throw null;
                }
                Objects.toString((ComponentName) this.e);
                u4.z(((zf1) obj).b);
                throw null;
            case 1:
                g4 g4Var = (g4) this.k;
                si1 si1Var = g4Var.k;
                if (si1Var != null && (qi1Var = si1Var.e) != null) {
                    qi1Var.e(si1Var);
                }
                View view = (View) g4Var.f122p;
                if (view != null && view.getWindowToken() != null) {
                    c4 c4Var = (c4) this.e;
                    if (c4Var.b()) {
                        g4Var.A = c4Var;
                    } else if (c4Var.f != null) {
                        c4Var.d(0, 0, false, false);
                        g4Var.A = c4Var;
                    }
                }
                g4Var.C = null;
                return;
            case 2:
                ((p4) this.e).c = this.k;
                return;
            case 3:
                ((Application) this.e).unregisterActivityLifecycleCallbacks((p4) this.k);
                return;
            case 4:
                try {
                    Method method = q4.d;
                    Object obj2 = this.k;
                    Object obj3 = this.e;
                    if (method != null) {
                        method.invoke(obj3, obj2, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        q4.e.invoke(obj3, obj2, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e) {
                    if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                        throw e;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            case 5:
                ((df2) this.e).c();
                return;
            case 6:
                try {
                    ((Worker) this.k).getClass();
                    throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for \n `getForegroundInfo()`");
                } catch (Throwable th2) {
                    ((androidx.work.impl.utils.futures.b) this.e).k(th2);
                    return;
                }
            case 7:
                if (((vy2) this.k).x.c instanceof d1) {
                    return;
                }
                try {
                    ((lb1) this.e).get();
                    vc1 vc1VarC = vc1.c();
                    String str = vy2.z;
                    String str2 = ((vy2) this.k).k.c;
                    vc1VarC.getClass();
                    Object obj4 = this.k;
                    ((vy2) obj4).x.l(((vy2) obj4).l.d());
                    return;
                } catch (Throwable th3) {
                    ((vy2) this.k).x.k(th3);
                    return;
                }
            case 8:
                ?? r0 = this.k;
                try {
                    try {
                        pb1 pb1Var = (pb1) ((vy2) r0).x.get();
                        if (pb1Var == null) {
                            vc1.c().a(vy2.z, ((vy2) r0).k.c + " returned a null result. Treating it as a failure.");
                        } else {
                            vc1 vc1VarC2 = vc1.c();
                            String str3 = vy2.z;
                            String str4 = ((vy2) r0).k.c;
                            pb1Var.toString();
                            vc1VarC2.getClass();
                            ((vy2) r0).n = pb1Var;
                        }
                        break;
                    } catch (InterruptedException e2) {
                        e = e2;
                        vc1.c().b(vy2.z, ((String) this.e) + " failed because it threw an exception/error", e);
                    } catch (CancellationException unused) {
                        vc1 vc1VarC3 = vc1.c();
                        String str5 = vy2.z;
                        vc1VarC3.getClass();
                    } catch (ExecutionException e3) {
                        e = e3;
                        vc1.c().b(vy2.z, ((String) this.e) + " failed because it threw an exception/error", e);
                    }
                    r0 = (vy2) r0;
                    r0.b();
                    return;
                } catch (Throwable th4) {
                    ((vy2) r0).b();
                    throw th4;
                }
            case 9:
                vc1 vc1VarC4 = vc1.c();
                int i = r80.e;
                ky2 ky2Var = (ky2) this.e;
                String str6 = ky2Var.a;
                vc1VarC4.getClass();
                ((r80) this.k).a.e(ky2Var);
                return;
            case 10:
                a();
                return;
            case 11:
                b();
                return;
            case 12:
                if (((ox2) this.k).c.c instanceof d1) {
                    return;
                }
                try {
                    ew0 ew0Var = (ew0) ((androidx.work.impl.utils.futures.b) this.e).get();
                    if (ew0Var == null) {
                        throw new IllegalStateException("Worker was marked important (" + ((ox2) this.k).k.c + ") but did not provide ForegroundInfo");
                    }
                    vc1 vc1VarC5 = vc1.c();
                    int i2 = ox2.o;
                    String str7 = ((ox2) this.k).k.c;
                    vc1VarC5.getClass();
                    Object obj5 = this.k;
                    androidx.work.impl.utils.futures.b bVar = ((ox2) obj5).c;
                    kw0 kw0Var = ((ox2) obj5).m;
                    Context context = ((ox2) obj5).e;
                    UUID uuid = ((ox2) obj5).l.e.a;
                    qx2 qx2Var = (qx2) kw0Var;
                    qx2Var.getClass();
                    androidx.work.impl.utils.futures.b bVar2 = new androidx.work.impl.utils.futures.b();
                    qx2Var.a.a(new px2(qx2Var, bVar2, uuid, ew0Var, context));
                    bVar.l(bVar2);
                    return;
                } catch (Throwable th5) {
                    ((ox2) this.k).c.k(th5);
                    return;
                }
            case 13:
                if (((b33) ((aj2) this.e)).d) {
                    ((m13) this.k).l.h();
                    return;
                }
                try {
                    ((m13) this.k).l.g(((m13) this.k).k.j((aj2) this.e));
                    return;
                } catch (RuntimeExecutionException e4) {
                    if (e4.getCause() instanceof Exception) {
                        ((m13) this.k).l.f((Exception) e4.getCause());
                        return;
                    } else {
                        ((m13) this.k).l.f(e4);
                        return;
                    }
                } catch (Exception e5) {
                    ((m13) this.k).l.f(e5);
                    return;
                }
            case 14:
                Object obj6 = this.k;
                try {
                    aj2 aj2Var = (aj2) ((m13) obj6).k.j((aj2) this.e);
                    if (aj2Var == null) {
                        ((m13) obj6).d(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    wa waVar = dj2.b;
                    m13 m13Var = (m13) obj6;
                    b33 b33Var = (b33) aj2Var;
                    b33Var.b.b(new i23(waVar, (eq1) m13Var));
                    b33Var.j();
                    b33Var.b.b(new i23(waVar, (xp1) m13Var));
                    b33Var.j();
                    b33Var.b.b(new i23(waVar, (pp1) m13Var));
                    b33Var.j();
                    return;
                } catch (RuntimeExecutionException e6) {
                    if (e6.getCause() instanceof Exception) {
                        ((m13) obj6).l.f((Exception) e6.getCause());
                        return;
                    } else {
                        ((m13) obj6).l.f(e6);
                        return;
                    }
                } catch (Exception e7) {
                    ((m13) obj6).l.f(e7);
                    return;
                }
            case 15:
                c();
                return;
            case 16:
                d();
                return;
            case 17:
                Object obj7 = this.k;
                try {
                    b33 b33VarJ = ((ah2) ((i23) obj7).k).j(((aj2) this.e).c());
                    if (b33VarJ == null) {
                        ((i23) obj7).d(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    wa waVar2 = dj2.b;
                    i23 i23Var = (i23) obj7;
                    b33VarJ.b.b(new i23(waVar2, (eq1) i23Var));
                    b33VarJ.j();
                    b33VarJ.b.b(new i23(waVar2, (xp1) i23Var));
                    b33VarJ.j();
                    b33VarJ.b.b(new i23(waVar2, (pp1) i23Var));
                    b33VarJ.j();
                    return;
                } catch (RuntimeExecutionException e8) {
                    if (e8.getCause() instanceof Exception) {
                        ((i23) obj7).d((Exception) e8.getCause());
                        return;
                    } else {
                        ((i23) obj7).d(e8);
                        return;
                    }
                } catch (CancellationException unused2) {
                    ((i23) obj7).g();
                    return;
                } catch (Exception e9) {
                    ((i23) obj7).d(e9);
                    return;
                }
            case 18:
                Object obj8 = this.e;
                try {
                    ((b33) obj8).g(((Callable) this.k).call());
                    return;
                } catch (Exception e10) {
                    ((b33) obj8).f(e10);
                    return;
                } catch (Throwable th6) {
                    ((b33) obj8).f(new RuntimeException(th6));
                    return;
                }
            case 19:
                ((EditText) this.e).removeTextChangedListener(((et) ((bt) this.k).b).e);
                return;
            case 20:
                boolean zIsPopupShowing = ((AutoCompleteTextView) this.e).isPopupShowing();
                lb0 lb0Var = (lb0) this.k;
                ((pb0) lb0Var.e).f(zIsPopupShowing);
                ((pb0) lb0Var.e).j = zIsPopupShowing;
                return;
            case 21:
                ((AutoCompleteTextView) this.e).removeTextChangedListener(((pb0) ((bt) this.k).b).e);
                return;
            case 22:
                ((EditText) this.e).removeTextChangedListener(((ms1) ((bt) this.k).b).e);
                return;
            case 23:
                ((com.google.android.play.core.assetpacks.b) this.e).c((Bundle) this.k);
                return;
            case 24:
                com.google.android.play.core.assetpacks.b bVar3 = (com.google.android.play.core.assetpacks.b) this.e;
                synchronized (bVar3) {
                    Iterator it = new HashSet(bVar3.d).iterator();
                    if (it.hasNext()) {
                        u4.z(it.next());
                        throw null;
                    }
                }
                return;
            case 25:
                ((t13) this.e).b.a((Intent) this.k);
                return;
            case 26:
                com.google.android.play.core.assetpacks.m mVar = (com.google.android.play.core.assetpacks.m) this.e;
                y13 y13Var = (y13) this.k;
                mVar.getClass();
                mVar.a.a(y13Var.c, y13Var.d, y13Var.b);
                return;
            case 27:
                com.google.android.play.core.assetpacks.o oVar = (com.google.android.play.core.assetpacks.o) this.e;
                e23 e23Var = (e23) this.k;
                oVar.getClass();
                oVar.a.a(e23Var.d, e23Var.e, e23Var.b);
                return;
            case 28:
                synchronized (((i03) this.k).c) {
                    Object obj9 = ((i03) this.k).d;
                    if (((ay2) obj9) != null) {
                        ay2 ay2Var = (ay2) obj9;
                        g03 g03Var = (g03) ay2Var.c;
                        j23 j23Var = (j23) ay2Var.e;
                        synchronized (g03Var.f) {
                            g03Var.e.remove(j23Var);
                        }
                    }
                    break;
                }
                return;
            default:
                synchronized (((i03) this.k).c) {
                    Object obj10 = ((i03) this.k).d;
                    if (((wp1) obj10) != null) {
                        wp1 wp1Var = (wp1) obj10;
                        wc0 wc0Var = (wc0) this.e;
                        synchronized (wc0Var.b) {
                            exc = (Exception) wc0Var.e;
                            break;
                        }
                        wp1Var.d(exc);
                    }
                }
                return;
        }
    }

    public /* synthetic */ yf1(Object obj, Object obj2, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }
}

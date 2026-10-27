package p;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle$Event;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nv implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ nv(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        ki kiVarJ;
        int i = 6;
        switch (this.c) {
            case 0:
                ((androidx.activity.a) this.e).invalidateOptionsMenu();
                return;
            case 1:
                vv vvVar = (vv) this.e;
                Runnable runnable = vvVar.e;
                if (runnable != null) {
                    runnable.run();
                    vvVar.e = null;
                    return;
                }
                return;
            case 2:
                yv.b((yv) this.e);
                return;
            case 3:
            default:
                ((Disposable) this.e).dispose();
                return;
            case 4:
                Activity activity = (Activity) this.e;
                int i2 = o4.b;
                if (activity.isFinishing()) {
                    return;
                }
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 28) {
                    Class cls = q4.a;
                    activity.recreate();
                    return;
                }
                Class cls2 = q4.a;
                boolean z = i3 == 26 || i3 == 27;
                Method method = q4.f;
                if ((!z || method != null) && (q4.e != null || q4.d != null)) {
                    try {
                        Object obj2 = q4.c.get(activity);
                        if (obj2 != null && (obj = q4.b.get(activity)) != null) {
                            Application application = activity.getApplication();
                            p4 p4Var = new p4(activity);
                            application.registerActivityLifecycleCallbacks(p4Var);
                            Handler handler = q4.g;
                            handler.post(new yf1(p4Var, obj2, 2));
                            int i4 = 3;
                            try {
                                if (i3 == 26 || i3 == 27) {
                                    Boolean bool = Boolean.FALSE;
                                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                                } else {
                                    activity.recreate();
                                }
                                handler.post(new yf1(application, p4Var, i4));
                                return;
                            } finally {
                                handler.post(new yf1(application, p4Var, i4));
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                activity.recreate();
                return;
            case 5:
                ArrayList arrayList = (ArrayList) this.e;
                v03.j(arrayList, "$transitioningViews");
                wx0.a(4, arrayList);
                return;
            case 6:
                ww0 ww0Var = (ww0) this.e;
                ww0Var.V.m.b(ww0Var.l);
                ww0Var.l = null;
                return;
            case 7:
                lx1 lx1Var = (lx1) this.e;
                lx1 lx1Var2 = lx1.q;
                v03.j(lx1Var, "this$0");
                if (lx1Var.e == 0) {
                    lx1Var.k = true;
                    lx1Var.n.f(Lifecycle$Event.ON_PAUSE);
                }
                if (lx1Var.c == 0 && lx1Var.k) {
                    lx1Var.n.f(Lifecycle$Event.ON_STOP);
                    lx1Var.l = true;
                    return;
                }
                return;
            case 8:
                c61 c61Var = (c61) this.e;
                synchronized (c61Var.l) {
                    c61Var.g = false;
                    a61 a61Var = c61Var.i;
                    synchronized (a61Var) {
                        Arrays.fill(a61Var.b, false);
                        a61Var.d = true;
                    }
                    th2 th2Var = c61Var.h;
                    if (th2Var != null) {
                        th2Var.close();
                    }
                }
                return;
            case 9:
                CoroutineWorker coroutineWorker = (CoroutineWorker) this.e;
                v03.j(coroutineWorker, "this$0");
                if (coroutineWorker.n.c instanceof d1) {
                    coroutineWorker.m.i(null);
                    return;
                }
                return;
            case 10:
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.e;
                v03.j(constraintTrackingWorker, "this$0");
                if (constraintTrackingWorker.f24p.c instanceof d1) {
                    return;
                }
                Object obj3 = constraintTrackingWorker.e.b.a.get("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
                String str = obj3 instanceof String ? (String) obj3 : null;
                vc1 vc1VarC = vc1.c();
                v03.i(vc1VarC, "get()");
                if (str == null || str.length() == 0) {
                    vc1VarC.a(sy.a, "No worker to delegate to.");
                    androidx.work.impl.utils.futures.b bVar = constraintTrackingWorker.f24p;
                    v03.i(bVar, "future");
                    bVar.j(new mb1());
                    return;
                }
                ty2 ty2Var = constraintTrackingWorker.e.e;
                Context context = constraintTrackingWorker.c;
                WorkerParameters workerParameters = constraintTrackingWorker.m;
                ty2Var.getClass();
                qb1 qb1VarA = ty2.a(context, str, workerParameters);
                constraintTrackingWorker.q = qb1VarA;
                if (qb1VarA == null) {
                    String str2 = sy.a;
                    androidx.work.impl.utils.futures.b bVar2 = constraintTrackingWorker.f24p;
                    v03.i(bVar2, "future");
                    bVar2.j(new mb1());
                    return;
                }
                wx2 wx2VarE = wx2.E(constraintTrackingWorker.c);
                my2 my2VarV = wx2VarE.i.v();
                String string = constraintTrackingWorker.e.a.toString();
                v03.i(string, "id.toString()");
                ky2 ky2VarH = my2VarV.h(string);
                if (ky2VarH == null) {
                    androidx.work.impl.utils.futures.b bVar3 = constraintTrackingWorker.f24p;
                    v03.i(bVar3, "future");
                    String str3 = sy.a;
                    bVar3.j(new mb1());
                    return;
                }
                nn2 nn2Var = wx2VarE.f206p;
                v03.i(nn2Var, "workManagerImpl.trackers");
                androidx.work.impl.constraints.a aVar = new androidx.work.impl.constraints.a(nn2Var);
                wk0 wk0Var = wx2VarE.j.b;
                v03.i(wk0Var, "workManagerImpl.workTask…r.taskCoroutineDispatcher");
                constraintTrackingWorker.f24p.a(new nv(11, androidx.work.impl.constraints.b.a(aVar, ky2VarH, wk0Var, constraintTrackingWorker)), new wa(1));
                if (!aVar.a(ky2VarH)) {
                    String str4 = sy.a;
                    androidx.work.impl.utils.futures.b bVar4 = constraintTrackingWorker.f24p;
                    v03.i(bVar4, "future");
                    bVar4.j(new nb1());
                    return;
                }
                String str5 = sy.a;
                try {
                    qb1 qb1Var = constraintTrackingWorker.q;
                    v03.h(qb1Var);
                    androidx.work.impl.utils.futures.b bVarD = qb1Var.d();
                    v03.i(bVarD, "delegate!!.startWork()");
                    bVarD.a(new ua(constraintTrackingWorker, 12, bVarD), constraintTrackingWorker.e.c);
                    return;
                } catch (Throwable unused2) {
                    String str6 = sy.a;
                    synchronized (constraintTrackingWorker.n) {
                        try {
                            if (constraintTrackingWorker.o) {
                                androidx.work.impl.utils.futures.b bVar5 = constraintTrackingWorker.f24p;
                                v03.i(bVar5, "future");
                                bVar5.j(new nb1());
                                return;
                            } else {
                                androidx.work.impl.utils.futures.b bVar6 = constraintTrackingWorker.f24p;
                                v03.i(bVar6, "future");
                                bVar6.j(new mb1());
                                return;
                            }
                        } finally {
                        }
                    }
                }
            case 11:
                p61 p61Var = (p61) this.e;
                v03.j(p61Var, "$job");
                p61Var.i(null);
                return;
            case 12:
                sx2 sx2Var = (sx2) this.e;
                sx2Var.getClass();
                ((j62) sx2Var.d).J(new af2(i, sx2Var));
                return;
            case 13:
                final com.google.firebase.installations.a aVar2 = (com.google.firebase.installations.a) this.e;
                Object obj4 = com.google.firebase.installations.a.m;
                aVar2.getClass();
                synchronized (com.google.firebase.installations.a.m) {
                    try {
                        ru0 ru0Var = aVar2.a;
                        ru0Var.a();
                        x42 x42VarB = x42.b(ru0Var.a);
                        try {
                            kiVarJ = aVar2.c.j();
                            PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus = PersistedInstallation$RegistrationStatus.e;
                            PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus2 = kiVarJ.b;
                            if (persistedInstallation$RegistrationStatus2 == persistedInstallation$RegistrationStatus || persistedInstallation$RegistrationStatus2 == PersistedInstallation$RegistrationStatus.c) {
                                String strB = aVar2.b(kiVarJ);
                                g90 g90Var = aVar2.c;
                                jm2 jm2VarA = kiVarJ.a();
                                jm2VarA.a = strB;
                                jm2VarA.j(PersistedInstallation$RegistrationStatus.k);
                                kiVarJ = jm2VarA.h();
                                g90Var.i(kiVarJ);
                            }
                            if (x42VarB != null) {
                                x42VarB.h();
                            }
                        } catch (Throwable th) {
                            if (x42VarB != null) {
                                x42VarB.h();
                            }
                            throw th;
                        }
                    } finally {
                    }
                    break;
                }
                aVar2.e(kiVarJ);
                aVar2.i.execute(new Runnable() { // from class: p.vu0
                    public final /* synthetic */ boolean e = false;

                    /* JADX WARN: Finally extract failed */
                    @Override // java.lang.Runnable
                    public final void run() {
                        ki kiVarJ2;
                        ki kiVarA;
                        com.google.firebase.installations.a aVar3 = aVar2;
                        boolean z2 = this.e;
                        aVar3.getClass();
                        Object obj5 = com.google.firebase.installations.a.m;
                        synchronized (obj5) {
                            try {
                                ru0 ru0Var2 = aVar3.a;
                                ru0Var2.a();
                                x42 x42VarB2 = x42.b(ru0Var2.a);
                                try {
                                    kiVarJ2 = aVar3.c.j();
                                    if (x42VarB2 != null) {
                                        x42VarB2.h();
                                    }
                                } catch (Throwable th2) {
                                    if (x42VarB2 != null) {
                                        x42VarB2.h();
                                    }
                                    throw th2;
                                }
                            } finally {
                            }
                        }
                        try {
                            PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus3 = PersistedInstallation$RegistrationStatus.m;
                            PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus4 = kiVarJ2.b;
                            if (persistedInstallation$RegistrationStatus4 == persistedInstallation$RegistrationStatus3 || persistedInstallation$RegistrationStatus4 == PersistedInstallation$RegistrationStatus.k) {
                                kiVarA = aVar3.c(kiVarJ2);
                            } else {
                                if (!z2) {
                                    is2 is2Var = aVar3.d;
                                    is2Var.getClass();
                                    if (!TextUtils.isEmpty(kiVarJ2.c)) {
                                        long j = kiVarJ2.f + kiVarJ2.e;
                                        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                                        is2Var.a.getClass();
                                        if (j >= timeUnit.toSeconds(System.currentTimeMillis()) + is2.b) {
                                            return;
                                        }
                                    }
                                }
                                kiVarA = aVar3.a(kiVarJ2);
                            }
                            synchronized (obj5) {
                                try {
                                    ru0 ru0Var3 = aVar3.a;
                                    ru0Var3.a();
                                    x42 x42VarB3 = x42.b(ru0Var3.a);
                                    try {
                                        aVar3.c.i(kiVarA);
                                        if (x42VarB3 != null) {
                                            x42VarB3.h();
                                        }
                                    } catch (Throwable th3) {
                                        if (x42VarB3 != null) {
                                            x42VarB3.h();
                                        }
                                        throw th3;
                                    }
                                } finally {
                                }
                            }
                            synchronized (aVar3) {
                                if (aVar3.k.size() != 0 && !TextUtils.equals(kiVarJ2.a, kiVarA.a)) {
                                    Iterator it = aVar3.k.iterator();
                                    if (it.hasNext()) {
                                        u4.z(it.next());
                                        throw null;
                                    }
                                }
                            }
                            if (kiVarA.b == PersistedInstallation$RegistrationStatus.l) {
                                String str7 = kiVarA.a;
                                synchronized (aVar3) {
                                    aVar3.j = str7;
                                }
                            }
                            PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus5 = kiVarA.b;
                            if (persistedInstallation$RegistrationStatus5 == persistedInstallation$RegistrationStatus3) {
                                new FirebaseInstallationsException();
                                aVar3.d();
                            } else if (persistedInstallation$RegistrationStatus5 != PersistedInstallation$RegistrationStatus.e && persistedInstallation$RegistrationStatus5 != PersistedInstallation$RegistrationStatus.c) {
                                aVar3.e(kiVarA);
                            } else {
                                new IOException("Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request.");
                                aVar3.d();
                            }
                        } catch (FirebaseInstallationsException unused3) {
                            aVar3.d();
                        }
                    }
                });
                return;
            case 14:
                pn pnVar = (pn) ((io.reactivex.rxjava3.android.a) this.e);
                pnVar.k.unregisterReceiver(pnVar.l);
                return;
        }
    }
}

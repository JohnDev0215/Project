package p;

import android.app.job.JobParameters;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import androidx.fragment.app.strictmode.Violation;
import androidx.profileinstaller.ProfileInstallerInitializer;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ua implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ ua(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        va vaVar;
        i80 i80Var;
        int i = 1;
        switch (this.c) {
            case 0:
                vaVar = (va) this.e;
                Runnable runnable = (Runnable) this.k;
                vaVar.getClass();
                try {
                    runnable.run();
                    return;
                } finally {
                }
            case 1:
                ((zu) this.e).v0((Typeface) this.k);
                return;
            case 2:
                x70 x70Var = (x70) this.e;
                bf2 bf2Var = (bf2) this.k;
                v03.j(x70Var, "$transitionInfo");
                v03.j(bf2Var, "$operation");
                x70Var.b();
                if (Log.isLoggable("FragmentManager", 2)) {
                    bf2Var.toString();
                    return;
                }
                return;
            case 3:
            case 16:
            default:
                yp ypVar = (yp) this.e;
                e72 e72Var = (e72) this.k;
                int i2 = e72.l;
                ((zp) ypVar).B(e72Var);
                return;
            case 4:
                String str = (String) this.e;
                Violation violation = (Violation) this.k;
                tx0 tx0Var = ux0.a;
                v03.j(violation, "$violation");
                Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + str, violation);
                throw violation;
            case 5:
                ProfileInstallerInitializer profileInstallerInitializer = (ProfileInstallerInitializer) this.e;
                Context context = (Context) this.k;
                profileInstallerInitializer.getClass();
                (Build.VERSION.SDK_INT >= 28 ? iy1.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new b8(context, i), new Random().nextInt(Math.max(1000, 1)) + 5000);
                return;
            case 6:
                ql1 ql1Var = (ql1) this.e;
                String[] strArr = (String[]) this.k;
                int i3 = pl1.c;
                v03.j(ql1Var, "this$0");
                v03.j(strArr, "$tables");
                c61 c61Var = ql1Var.b;
                String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
                c61Var.getClass();
                v03.j(strArr2, "tables");
                synchronized (c61Var.j) {
                    Iterator it = c61Var.j.iterator();
                    while (true) {
                        o62 o62Var = (o62) it;
                        if (o62Var.hasNext()) {
                            Map.Entry entry = (Map.Entry) o62Var.next();
                            v03.i(entry, "(observer, wrapper)");
                            ol1 ol1Var = (ol1) entry.getKey();
                            ol1Var.getClass();
                        }
                    }
                }
                return;
            case 7:
                Runnable runnable2 = (Runnable) this.k;
                vaVar = (va) this.e;
                v03.j(runnable2, "$command");
                v03.j(vaVar, "this$0");
                try {
                    runnable2.run();
                    return;
                } finally {
                }
            case 8:
                vy2 vy2Var = (vy2) this.e;
                lb1 lb1Var = (lb1) this.k;
                if (vy2Var.x.c instanceof d1) {
                    lb1Var.cancel(true);
                    return;
                }
                return;
            case 9:
                uk2 uk2Var = (uk2) this.e;
                xf2 xf2Var = (xf2) this.k;
                v03.j(uk2Var, "this$0");
                v03.j(xf2Var, "$token");
                uk2Var.b.a(xf2Var, 3);
                return;
            case 10:
                List list = (List) this.e;
                qy qyVar = (qy) this.k;
                v03.j(list, "$listenersList");
                v03.j(qyVar, "this$0");
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((zx) it2.next()).a(qyVar.e);
                }
                return;
            case 11:
                ox2 ox2Var = (ox2) this.e;
                androidx.work.impl.utils.futures.b bVar = (androidx.work.impl.utils.futures.b) this.k;
                if (ox2Var.c.c instanceof d1) {
                    bVar.cancel(true);
                    return;
                } else {
                    bVar.l(ox2Var.l.a());
                    return;
                }
            case 12:
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.e;
                lb1 lb1Var2 = (lb1) this.k;
                v03.j(constraintTrackingWorker, "this$0");
                v03.j(lb1Var2, "$innerFuture");
                synchronized (constraintTrackingWorker.n) {
                    try {
                        if (constraintTrackingWorker.o) {
                            androidx.work.impl.utils.futures.b bVar2 = constraintTrackingWorker.f24p;
                            v03.i(bVar2, "future");
                            String str2 = sy.a;
                            bVar2.j(new nb1());
                        } else {
                            constraintTrackingWorker.f24p.l(lb1Var2);
                        }
                    } finally {
                    }
                }
                return;
            case 13:
                JobInfoSchedulerService jobInfoSchedulerService = (JobInfoSchedulerService) this.e;
                JobParameters jobParameters = (JobParameters) this.k;
                int i4 = JobInfoSchedulerService.c;
                jobInfoSchedulerService.jobFinished(jobParameters, false);
                return;
            case 14:
                zq1 zq1Var = (zq1) this.e;
                yy1 yy1Var = (yy1) this.k;
                if (zq1Var.b != zq1.d) {
                    throw new IllegalStateException("provide() can be called only once.");
                }
                synchronized (zq1Var) {
                    i80Var = zq1Var.a;
                    zq1Var.a = null;
                    zq1Var.b = yy1Var;
                    break;
                }
                i80Var.e(yy1Var);
                return;
            case 15:
                y81 y81Var = (y81) this.e;
                yy1 yy1Var2 = (yy1) this.k;
                synchronized (y81Var) {
                    try {
                        if (y81Var.b == null) {
                            y81Var.a.add(yy1Var2);
                        } else {
                            y81Var.b.add(yy1Var2.get());
                        }
                    } finally {
                    }
                }
                return;
            case 17:
                a50 a50Var = (a50) this.e;
                Runnable runnable3 = (Runnable) this.k;
                Process.setThreadPriority(a50Var.k);
                StrictMode.ThreadPolicy threadPolicy = a50Var.l;
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                runnable3.run();
                return;
            case 18:
                Callable callable = (Callable) this.e;
                oh1 oh1Var = (oh1) this.k;
                try {
                    Object objCall = callable.call();
                    z80 z80Var = (z80) oh1Var.e;
                    int i5 = z80.q;
                    z80Var.getClass();
                    if (objCall == null) {
                        objCall = g2.o;
                    }
                    if (g2.n.g(z80Var, null, objCall)) {
                        g2.d(z80Var);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    oh1Var.A(e);
                    return;
                }
            case 19:
                l32 l32Var = (l32) this.e;
                CountDownLatch countDownLatch = (CountDownLatch) this.k;
                l32Var.getClass();
                try {
                    fo2 fo2Var = l32Var.h;
                    Priority priority = Priority.k;
                    if (fo2Var instanceof fo2) {
                        ho2.a().d.a(fo2Var.a.c(priority), 1);
                    } else if (Log.isLoggable("TRuntime.".concat("ForcedSender"), 5)) {
                        String.format("Expected instance of `TransportImpl`, got `%s`.", fo2Var);
                    }
                    break;
                } catch (SQLException unused) {
                }
                countDownLatch.countDown();
                return;
        }
    }

    public /* synthetic */ ua(va vaVar, Runnable runnable) {
        this.c = 7;
        this.k = runnable;
        this.e = vaVar;
    }
}

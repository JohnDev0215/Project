package p;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class li2 implements b72 {
    public static final String n = vc1.d("SystemJobScheduler");
    public final Context c;
    public final JobScheduler e;
    public final ki2 k;
    public final WorkDatabase l;
    public final sw m;

    public li2(Context context, WorkDatabase workDatabase, sw swVar) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        ki2 ki2Var = new ki2(context, swVar.c);
        this.c = context;
        this.e = jobScheduler;
        this.k = ki2Var;
        this.l = workDatabase;
        this.m = swVar;
    }

    public static void b(JobScheduler jobScheduler, int i) {
        try {
            jobScheduler.cancel(i);
        } catch (Throwable th) {
            vc1.c().b(n, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(i)), th);
        }
    }

    public static ArrayList c(Context context, JobScheduler jobScheduler) {
        List<JobInfo> allPendingJobs;
        try {
            allPendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            vc1.c().b(n, "getAllPendingJobs() is not reliable on this device.", th);
            allPendingJobs = null;
        }
        if (allPendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allPendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : allPendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    public static rx2 f(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (!extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new rx2(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // p.b72
    public final void a(String str) {
        ArrayList arrayList;
        Context context = this.c;
        JobScheduler jobScheduler = this.e;
        ArrayList<JobInfo> arrayListC = c(context, jobScheduler);
        if (arrayListC == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(2);
            for (JobInfo jobInfo : arrayListC) {
                rx2 rx2VarF = f(jobInfo);
                if (rx2VarF != null && str.equals(rx2VarF.a)) {
                    arrayList2.add(Integer.valueOf(jobInfo.getId()));
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b(jobScheduler, ((Integer) it.next()).intValue());
        }
        cy2 cy2Var = (cy2) this.l.s();
        i52 i52Var = cy2Var.a;
        i52Var.b();
        androidx.room.a aVar = cy2Var.d;
        th2 th2VarA = aVar.a();
        if (str == null) {
            th2VarA.K(1);
        } else {
            th2VarA.x(1, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            aVar.c(th2VarA);
        }
    }

    @Override // p.b72
    public final boolean d() {
        return true;
    }

    @Override // p.b72
    public final void e(ky2... ky2VarArr) {
        int iIntValue;
        sw swVar = this.m;
        WorkDatabase workDatabase = this.l;
        final oh1 oh1Var = new oh1(workDatabase);
        for (ky2 ky2Var : ky2VarArr) {
            workDatabase.c();
            try {
                ky2 ky2VarH = workDatabase.v().h(ky2Var.a);
                if (ky2VarH != null && ky2VarH.b == WorkInfo$State.c) {
                    rx2 rx2VarU = ci1.u(ky2Var);
                    hi2 hi2VarA = ((cy2) workDatabase.s()).a(rx2VarU);
                    if (hi2VarA != null) {
                        iIntValue = hi2VarA.c;
                    } else {
                        swVar.getClass();
                        final int i = swVar.h;
                        Object objN = ((WorkDatabase) oh1Var.e).n(new Callable() { // from class: p.d31
                            public final /* synthetic */ int e = 0;

                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                oh1 oh1Var2 = oh1Var;
                                v03.j(oh1Var2, "this$0");
                                WorkDatabase workDatabase2 = (WorkDatabase) oh1Var2.e;
                                Long lV = workDatabase2.r().v("next_job_scheduler_id");
                                int iLongValue = lV != null ? (int) lV.longValue() : 0;
                                workDatabase2.r().y(new vw1("next_job_scheduler_id", Long.valueOf(iLongValue != Integer.MAX_VALUE ? iLongValue + 1 : 0)));
                                int i2 = this.e;
                                if (i2 > iLongValue || iLongValue > i) {
                                    ((WorkDatabase) oh1Var2.e).r().y(new vw1("next_job_scheduler_id", Long.valueOf(i2 + 1)));
                                    iLongValue = i2;
                                }
                                return Integer.valueOf(iLongValue);
                            }
                        });
                        v03.i(objN, "workDatabase.runInTransa…            id\n        })");
                        iIntValue = ((Number) objN).intValue();
                    }
                    if (hi2VarA == null) {
                        ((cy2) workDatabase.s()).b(new hi2(rx2VarU.a, rx2VarU.b, iIntValue));
                    }
                    g(ky2Var, iIntValue);
                    workDatabase.o();
                } else {
                    vc1.c().getClass();
                    workDatabase.o();
                }
            } finally {
                workDatabase.k();
            }
        }
    }

    public final void g(ky2 ky2Var, int i) {
        int i2;
        int i3;
        String str = n;
        JobScheduler jobScheduler = this.e;
        ki2 ki2Var = this.k;
        ki2Var.getClass();
        wy wyVar = ky2Var.j;
        PersistableBundle persistableBundle = new PersistableBundle();
        String str2 = ky2Var.a;
        persistableBundle.putString("EXTRA_WORK_SPEC_ID", str2);
        persistableBundle.putInt("EXTRA_WORK_SPEC_GENERATION", ky2Var.t);
        persistableBundle.putBoolean("EXTRA_IS_PERIODIC", ky2Var.c());
        JobInfo.Builder requiresCharging = new JobInfo.Builder(i, ki2Var.a).setRequiresCharging(wyVar.b);
        boolean z = wyVar.c;
        JobInfo.Builder extras = requiresCharging.setRequiresDeviceIdle(z).setExtras(persistableBundle);
        int i4 = Build.VERSION.SDK_INT;
        NetworkType networkType = wyVar.a;
        if (i4 < 30 || networkType != NetworkType.n) {
            int iOrdinal = networkType.ordinal();
            if (iOrdinal == 0) {
                i2 = 0;
            } else if (iOrdinal != 1) {
                i2 = 2;
                if (iOrdinal != 2) {
                    i2 = 3;
                    if (iOrdinal != 3) {
                        i2 = 4;
                        if (iOrdinal != 4) {
                            vc1 vc1VarC = vc1.c();
                            networkType.toString();
                            vc1VarC.getClass();
                            i2 = 1;
                        }
                    }
                }
            } else {
                i2 = 1;
            }
            extras.setRequiredNetworkType(i2);
        } else {
            extras.setRequiredNetwork(new NetworkRequest.Builder().addCapability(25).build());
        }
        if (!z) {
            extras.setBackoffCriteria(ky2Var.m, ky2Var.l == BackoffPolicy.e ? 0 : 1);
        }
        long jA = ky2Var.a();
        ki2Var.b.getClass();
        long jMax = Math.max(jA - System.currentTimeMillis(), 0L);
        if (i4 <= 28 || jMax > 0) {
            extras.setMinimumLatency(jMax);
        } else if (!ky2Var.q) {
            extras.setImportantWhileForeground(true);
        }
        Set<vy> set = wyVar.h;
        if (!set.isEmpty()) {
            for (vy vyVar : set) {
                extras.addTriggerContentUri(new JobInfo.TriggerContentUri(vyVar.a, vyVar.b ? 1 : 0));
            }
            extras.setTriggerContentUpdateDelay(wyVar.f);
            extras.setTriggerContentMaxDelay(wyVar.g);
        }
        extras.setPersisted(false);
        int i5 = Build.VERSION.SDK_INT;
        extras.setRequiresBatteryNotLow(wyVar.d);
        extras.setRequiresStorageNotLow(wyVar.e);
        boolean z2 = ky2Var.k > 0;
        boolean z3 = jMax > 0;
        if (i5 >= 31 && ky2Var.q && !z2 && !z3) {
            extras.setExpedited(true);
        }
        JobInfo jobInfoBuild = extras.build();
        vc1.c().getClass();
        try {
            try {
                if (jobScheduler.schedule(jobInfoBuild) != 0) {
                    return;
                }
                vc1.c().getClass();
                if (!ky2Var.q) {
                    return;
                }
                if (ky2Var.r != OutOfQuotaPolicy.c) {
                    return;
                }
                i3 = 0;
                try {
                    ky2Var.q = false;
                    String.format("Scheduling a non-expedited job (work ID %s)", str2);
                    vc1.c().getClass();
                    g(ky2Var, i);
                    return;
                } catch (IllegalStateException e) {
                    e = e;
                }
            } catch (Throwable th) {
                vc1.c().b(str, "Unable to schedule " + ky2Var, th);
                return;
            }
        } catch (IllegalStateException e2) {
            e = e2;
            i3 = 0;
        }
        ArrayList arrayListC = c(this.c, jobScheduler);
        String str3 = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(arrayListC != null ? arrayListC.size() : i3), Integer.valueOf(this.l.v().e().size()), Integer.valueOf(this.m.j));
        vc1.c().a(str, str3);
        throw new IllegalStateException(str3, e);
    }
}

package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import java.util.Arrays;
import java.util.HashMap;
import p.g90;
import p.kk0;
import p.mi2;
import p.ni2;
import p.oi2;
import p.qx1;
import p.rx2;
import p.tx2;
import p.uk0;
import p.vc1;
import p.wx2;
import p.xf1;
import p.xf2;

/* JADX INFO: loaded from: classes.dex */
public class SystemJobService extends JobService implements uk0 {
    public static final String m = vc1.d("SystemJobService");
    public wx2 c;
    public final HashMap e = new HashMap();
    public final g90 k = new g90(3);
    public tx2 l;

    public static rx2 a(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new rx2(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION"));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // p.uk0
    public final void b(rx2 rx2Var, boolean z) {
        JobParameters jobParameters;
        vc1 vc1VarC = vc1.c();
        String str = rx2Var.a;
        vc1VarC.getClass();
        synchronized (this.e) {
            jobParameters = (JobParameters) this.e.remove(rx2Var);
        }
        this.k.l(rx2Var);
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        try {
            wx2 wx2VarE = wx2.E(getApplicationContext());
            this.c = wx2VarE;
            qx1 qx1Var = wx2VarE.l;
            this.l = new tx2(qx1Var, wx2VarE.j);
            qx1Var.a(this);
        } catch (IllegalStateException e) {
            if (!Application.class.equals(getApplication().getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
            }
            vc1.c().getClass();
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        wx2 wx2Var = this.c;
        if (wx2Var != null) {
            wx2Var.l.e(this);
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        if (this.c == null) {
            vc1.c().getClass();
            jobFinished(jobParameters, true);
            return false;
        }
        rx2 rx2VarA = a(jobParameters);
        if (rx2VarA == null) {
            vc1.c().a(m, "WorkSpec id not found!");
            return false;
        }
        synchronized (this.e) {
            try {
                if (this.e.containsKey(rx2VarA)) {
                    vc1 vc1VarC = vc1.c();
                    rx2VarA.toString();
                    vc1VarC.getClass();
                    return false;
                }
                vc1 vc1VarC2 = vc1.c();
                rx2VarA.toString();
                vc1VarC2.getClass();
                this.e.put(rx2VarA, jobParameters);
                int i = Build.VERSION.SDK_INT;
                kk0 kk0Var = new kk0(7);
                if (mi2.b(jobParameters) != null) {
                    kk0Var.k = Arrays.asList(mi2.b(jobParameters));
                }
                if (mi2.a(jobParameters) != null) {
                    kk0Var.e = Arrays.asList(mi2.a(jobParameters));
                }
                if (i >= 28) {
                    kk0Var.l = ni2.a(jobParameters);
                }
                tx2 tx2Var = this.l;
                tx2Var.b.a(new xf1(tx2Var.a, this.k.m(rx2VarA), kk0Var));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        boolean zContains;
        if (this.c == null) {
            vc1.c().getClass();
            return true;
        }
        rx2 rx2VarA = a(jobParameters);
        if (rx2VarA == null) {
            vc1.c().a(m, "WorkSpec id not found!");
            return false;
        }
        vc1 vc1VarC = vc1.c();
        rx2VarA.toString();
        vc1VarC.getClass();
        synchronized (this.e) {
            this.e.remove(rx2VarA);
        }
        xf2 xf2VarL = this.k.l(rx2VarA);
        if (xf2VarL != null) {
            int iA = Build.VERSION.SDK_INT >= 31 ? oi2.a(jobParameters) : -512;
            tx2 tx2Var = this.l;
            tx2Var.getClass();
            tx2Var.a(xf2VarL, iA);
        }
        qx1 qx1Var = this.c.l;
        String str = rx2VarA.a;
        synchronized (qx1Var.k) {
            zContains = qx1Var.i.contains(str);
        }
        return !zContains;
    }
}

package p;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class wx2 extends ux2 {
    public static wx2 q;
    public static wx2 r;
    public static final Object s;
    public final Context g;
    public final sw h;
    public final WorkDatabase i;
    public final yx2 j;
    public final List k;
    public final qx1 l;
    public final p01 m;
    public boolean n = false;
    public BroadcastReceiver.PendingResult o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final nn2 f206p;

    static {
        vc1.d("WorkManagerImpl");
        q = null;
        r = null;
        s = new Object();
    }

    public wx2(Context context, final sw swVar, yx2 yx2Var, final WorkDatabase workDatabase, final List list, qx1 qx1Var, nn2 nn2Var) {
        Context applicationContext = context.getApplicationContext();
        if (vx2.a(applicationContext)) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        vc1 vc1Var = new vc1(swVar.g);
        synchronized (vc1.b) {
            vc1.c = vc1Var;
        }
        this.g = applicationContext;
        this.j = yx2Var;
        this.i = workDatabase;
        this.l = qx1Var;
        this.f206p = nn2Var;
        this.h = swVar;
        this.k = list;
        this.m = new p01(12, workDatabase);
        final ob2 ob2Var = yx2Var.a;
        int i = h72.a;
        qx1Var.a(new uk0() { // from class: p.f72
            @Override // p.uk0
            public final void b(rx2 rx2Var, boolean z) {
                ob2Var.execute(new g72(list, rx2Var, swVar, workDatabase, 0));
            }
        });
        yx2Var.a(new dw0(applicationContext, this));
    }

    public static wx2 E(Context context) {
        wx2 wx2Var;
        Object obj = s;
        synchronized (obj) {
            try {
                synchronized (obj) {
                    wx2Var = q;
                    if (wx2Var == null) {
                        wx2Var = r;
                    }
                }
                return wx2Var;
            } catch (Throwable th) {
                throw th;
            } finally {
            }
        }
        if (wx2Var != null) {
            return wx2Var;
        }
        context.getApplicationContext();
        throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
    }

    public final void F() {
        synchronized (s) {
            try {
                this.n = true;
                BroadcastReceiver.PendingResult pendingResult = this.o;
                if (pendingResult != null) {
                    pendingResult.finish();
                    this.o = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void G() {
        ArrayList arrayListC;
        String str = li2.n;
        Context context = this.g;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && (arrayListC = li2.c(context, jobScheduler)) != null && !arrayListC.isEmpty()) {
            Iterator it = arrayListC.iterator();
            while (it.hasNext()) {
                li2.b(jobScheduler, ((JobInfo) it.next()).getId());
            }
        }
        WorkDatabase workDatabase = this.i;
        my2 my2VarV = workDatabase.v();
        i52 i52Var = my2VarV.a;
        i52Var.b();
        ly2 ly2Var = my2VarV.m;
        th2 th2VarA = ly2Var.a();
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
            i52Var.k();
            ly2Var.c(th2VarA);
            h72.b(this.h, workDatabase, this.k);
        } catch (Throwable th) {
            i52Var.k();
            ly2Var.c(th2VarA);
            throw th;
        }
    }
}

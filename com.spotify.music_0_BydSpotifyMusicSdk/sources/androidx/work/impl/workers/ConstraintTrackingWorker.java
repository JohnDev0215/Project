package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.futures.b;
import p.az;
import p.bz;
import p.ky2;
import p.nv;
import p.qb1;
import p.rp1;
import p.sy;
import p.v03;
import p.vc1;

/* JADX INFO: loaded from: classes.dex */
public final class ConstraintTrackingWorker extends qb1 implements rp1 {
    public final WorkerParameters m;
    public final Object n;
    public volatile boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b f24p;
    public qb1 q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "appContext");
        v03.j(workerParameters, "workerParameters");
        this.m = workerParameters;
        this.n = new Object();
        this.f24p = new b();
    }

    @Override // p.qb1
    public final void b() {
        qb1 qb1Var = this.q;
        if (qb1Var == null || qb1Var.k != -256) {
            return;
        }
        qb1Var.e(Build.VERSION.SDK_INT >= 31 ? this.k : 0);
    }

    @Override // p.rp1
    public final void c(ky2 ky2Var, bz bzVar) {
        v03.j(ky2Var, "workSpec");
        v03.j(bzVar, "state");
        vc1 vc1VarC = vc1.c();
        String str = sy.a;
        ky2Var.toString();
        vc1VarC.getClass();
        if (bzVar instanceof az) {
            synchronized (this.n) {
                this.o = true;
            }
        }
    }

    @Override // p.qb1
    public final b d() {
        this.e.c.execute(new nv(10, this));
        b bVar = this.f24p;
        v03.i(bVar, "future");
        return bVar;
    }
}

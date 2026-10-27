package androidx.work;

import android.content.Context;
import androidx.work.impl.utils.futures.b;
import p.ha0;
import p.lb1;
import p.nv;
import p.q30;
import p.qb1;
import p.r61;
import p.s70;
import p.v03;
import p.vr2;
import p.yz;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutineWorker extends qb1 {
    public final r61 m;
    public final b n;
    public final s70 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "appContext");
        v03.j(workerParameters, "params");
        this.m = zu.a();
        b bVar = new b();
        this.n = bVar;
        bVar.a(new nv(9, this), workerParameters.d.a);
        this.o = ha0.a;
    }

    @Override // p.qb1
    public final lb1 a() {
        r61 r61VarA = zu.a();
        s70 s70Var = this.o;
        s70Var.getClass();
        yz yzVarB = q30.b(kotlin.coroutines.a.a(s70Var, r61VarA));
        a aVar = new a(r61VarA);
        vr2.l(yzVarB, null, null, new CoroutineWorker$getForegroundInfoAsync$1(aVar, this, null), 3);
        return aVar;
    }

    @Override // p.qb1
    public final void b() {
        this.n.cancel(false);
    }

    @Override // p.qb1
    public final b d() {
        vr2.l(q30.b(this.o.o0(this.m)), null, null, new CoroutineWorker$startWork$1(this, null), 3);
        return this.n;
    }

    public abstract Object f();
}

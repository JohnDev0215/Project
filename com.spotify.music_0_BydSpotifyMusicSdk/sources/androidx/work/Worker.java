package androidx.work;

import android.content.Context;
import androidx.work.impl.utils.futures.b;
import p.lb1;
import p.ob1;
import p.qb1;
import p.rv;
import p.yf1;

/* JADX INFO: loaded from: classes.dex */
public abstract class Worker extends qb1 {
    public b m;

    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // p.qb1
    public final lb1 a() {
        b bVar = new b();
        this.e.c.execute(new yf1(this, 6, bVar));
        return bVar;
    }

    @Override // p.qb1
    public final b d() {
        this.m = new b();
        this.e.c.execute(new rv(10, this));
        return this.m;
    }

    public abstract ob1 f();
}

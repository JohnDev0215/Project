package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import p.ob1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CombineContinuationsWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineContinuationsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "context");
        v03.j(workerParameters, "workerParams");
    }

    @Override // androidx.work.Worker
    public final ob1 f() {
        return new ob1(this.e.b);
    }
}

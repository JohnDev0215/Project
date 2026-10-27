package p;

import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class t10 extends Scheduler {
    public final /* synthetic */ u10 c;

    public t10(u10 u10Var) {
        this.c = u10Var;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new s10(this.c);
    }
}

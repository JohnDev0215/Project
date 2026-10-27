package p;

import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class te2 {
    public final re2 a;
    public final Scheduler b;

    public te2(re2 re2Var, Scheduler scheduler) {
        v03.j(re2Var, "webgateService");
        v03.j(scheduler, "mainThread");
        this.a = re2Var;
        this.b = scheduler;
    }
}

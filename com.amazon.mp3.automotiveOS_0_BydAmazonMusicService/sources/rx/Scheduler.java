package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulePeriodicHelper;

/* JADX INFO: loaded from: classes6.dex */
public abstract class Scheduler {
    public abstract Worker createWorker();

    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(Action0 action0, long j, long j2, TimeUnit timeUnit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action0, j, j2, timeUnit, null);
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public long now() {
        return System.currentTimeMillis();
    }
}

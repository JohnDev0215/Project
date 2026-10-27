package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.subscriptions.SequentialSubscription;

/* JADX INFO: loaded from: classes4.dex */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public interface NowNanoSupplier {
        long nowNanos();
    }

    private SchedulePeriodicHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription schedulePeriodically(Scheduler.Worker worker, Action0 action0, long j, long j2, TimeUnit timeUnit, NowNanoSupplier nowNanoSupplier) {
        long nanos = timeUnit.toNanos(j2);
        long jNowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        long nanos2 = timeUnit.toNanos(j) + jNowNanos;
        SequentialSubscription sequentialSubscription = new SequentialSubscription();
        SequentialSubscription sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
        sequentialSubscription.replace(worker.schedule(new Action0(jNowNanos, nanos2, action0, sequentialSubscription2, nowNanoSupplier, worker, nanos) { // from class: rx.internal.schedulers.SchedulePeriodicHelper.1
            long count;
            long lastNowNanos;
            long startInNanos;
            final /* synthetic */ Action0 val$action;
            final /* synthetic */ long val$firstNowNanos;
            final /* synthetic */ long val$firstStartInNanos;
            final /* synthetic */ SequentialSubscription val$mas;
            final /* synthetic */ NowNanoSupplier val$nowNanoSupplier;
            final /* synthetic */ long val$periodInNanos;
            final /* synthetic */ Scheduler.Worker val$worker;

            {
                this.val$firstNowNanos = jNowNanos;
                this.val$firstStartInNanos = nanos2;
                this.val$action = action0;
                this.val$mas = sequentialSubscription2;
                this.val$nowNanoSupplier = nowNanoSupplier;
                this.val$worker = worker;
                this.val$periodInNanos = nanos;
                this.lastNowNanos = jNowNanos;
                this.startInNanos = nanos2;
            }

            @Override // rx.functions.Action0
            public void call() {
                long j3;
                this.val$action.call();
                if (this.val$mas.isUnsubscribed()) {
                    return;
                }
                NowNanoSupplier nowNanoSupplier2 = this.val$nowNanoSupplier;
                long jNowNanos2 = nowNanoSupplier2 != null ? nowNanoSupplier2.nowNanos() : TimeUnit.MILLISECONDS.toNanos(this.val$worker.now());
                long j4 = SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + jNowNanos2;
                long j5 = this.lastNowNanos;
                if (j4 < j5 || jNowNanos2 >= j5 + this.val$periodInNanos + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                    long j6 = this.val$periodInNanos;
                    long j7 = jNowNanos2 + j6;
                    long j8 = this.count + 1;
                    this.count = j8;
                    this.startInNanos = j7 - (j6 * j8);
                    j3 = j7;
                } else {
                    long j9 = this.startInNanos;
                    long j10 = this.count + 1;
                    this.count = j10;
                    j3 = j9 + (j10 * this.val$periodInNanos);
                }
                this.lastNowNanos = jNowNanos2;
                this.val$mas.replace(this.val$worker.schedule(this, j3 - jNowNanos2, TimeUnit.NANOSECONDS));
            }
        }, j, timeUnit));
        return sequentialSubscription2;
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class t3 implements p3 {
    public final int a;
    public final long b;
    public final TimeUnit c;
    public final Scheduler d;
    public final boolean e;

    public t3(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.a = i;
        this.b = j;
        this.c = timeUnit;
        this.d = scheduler;
        this.e = z;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.p3
    public final q3 call() {
        final int i = this.a;
        final long j = this.b;
        final TimeUnit timeUnit = this.c;
        final Scheduler scheduler = this.d;
        final boolean z = this.e;
        return new ObservableReplay$BoundedReplayBuffer<T>(i, j, timeUnit, scheduler, z) { // from class: io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$SizeAndTimeBoundReplayBuffer
            private static final long serialVersionUID = 3457957419649567404L;
            final int limit;
            final long maxAge;
            final Scheduler scheduler;
            final TimeUnit unit;

            {
                super(z);
                this.scheduler = scheduler;
                this.limit = i;
                this.maxAge = j;
                this.unit = timeUnit;
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final Object b(Object obj) {
                Scheduler scheduler2 = this.scheduler;
                TimeUnit timeUnit2 = this.unit;
                scheduler2.getClass();
                return new io.reactivex.rxjava3.schedulers.f(obj, Scheduler.a(timeUnit2), this.unit);
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final ObservableReplay$Node c() {
                ObservableReplay$Node observableReplay$Node;
                Scheduler scheduler2 = this.scheduler;
                TimeUnit timeUnit2 = this.unit;
                scheduler2.getClass();
                long jA = Scheduler.a(timeUnit2) - this.maxAge;
                ObservableReplay$Node observableReplay$Node2 = get();
                ObservableReplay$Node observableReplay$Node3 = observableReplay$Node2.get();
                while (true) {
                    ObservableReplay$Node observableReplay$Node4 = observableReplay$Node3;
                    observableReplay$Node = observableReplay$Node2;
                    observableReplay$Node2 = observableReplay$Node4;
                    if (observableReplay$Node2 == null) {
                        break;
                    }
                    io.reactivex.rxjava3.schedulers.f fVar = (io.reactivex.rxjava3.schedulers.f) observableReplay$Node2.value;
                    if (NotificationLite.h(fVar.a) || NotificationLite.i(fVar.a) || fVar.b > jA) {
                        break;
                    }
                    observableReplay$Node3 = observableReplay$Node2.get();
                }
                return observableReplay$Node;
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final Object f(Object obj) {
                return ((io.reactivex.rxjava3.schedulers.f) obj).a;
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final void i() {
                ObservableReplay$Node observableReplay$Node;
                Scheduler scheduler2 = this.scheduler;
                TimeUnit timeUnit2 = this.unit;
                scheduler2.getClass();
                long jA = Scheduler.a(timeUnit2) - this.maxAge;
                ObservableReplay$Node observableReplay$Node2 = get();
                ObservableReplay$Node observableReplay$Node3 = observableReplay$Node2.get();
                int i2 = 0;
                while (true) {
                    ObservableReplay$Node observableReplay$Node4 = observableReplay$Node3;
                    observableReplay$Node = observableReplay$Node2;
                    observableReplay$Node2 = observableReplay$Node4;
                    int i3 = this.size;
                    if (i3 > 1) {
                        if (i3 <= this.limit) {
                            if (((io.reactivex.rxjava3.schedulers.f) observableReplay$Node2.value).b > jA) {
                                break;
                            }
                            i2++;
                            this.size = i3 - 1;
                            observableReplay$Node3 = observableReplay$Node2.get();
                        } else {
                            i2++;
                            this.size = i3 - 1;
                            observableReplay$Node3 = observableReplay$Node2.get();
                        }
                    } else {
                        break;
                    }
                }
                if (i2 != 0) {
                    g(observableReplay$Node);
                }
            }

            @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay$BoundedReplayBuffer
            public final void j() {
                ObservableReplay$Node observableReplay$Node;
                Scheduler scheduler2 = this.scheduler;
                TimeUnit timeUnit2 = this.unit;
                scheduler2.getClass();
                long jA = Scheduler.a(timeUnit2) - this.maxAge;
                ObservableReplay$Node observableReplay$Node2 = get();
                ObservableReplay$Node observableReplay$Node3 = observableReplay$Node2.get();
                int i2 = 0;
                while (true) {
                    ObservableReplay$Node observableReplay$Node4 = observableReplay$Node3;
                    observableReplay$Node = observableReplay$Node2;
                    observableReplay$Node2 = observableReplay$Node4;
                    int i3 = this.size;
                    if (i3 <= 1 || ((io.reactivex.rxjava3.schedulers.f) observableReplay$Node2.value).b > jA) {
                        break;
                    }
                    i2++;
                    this.size = i3 - 1;
                    observableReplay$Node3 = observableReplay$Node2.get();
                }
                if (i2 != 0) {
                    g(observableReplay$Node);
                }
            }
        };
    }
}

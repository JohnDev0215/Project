package p;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class t00 {
    public final gw2 b;
    public final Scheduler c;
    public final io.reactivex.rxjava3.processors.b a = new io.reactivex.rxjava3.processors.b();
    public final AtomicBoolean d = new AtomicBoolean(false);

    public t00(Scheduler scheduler, gw2 gw2Var) {
        this.c = scheduler;
        this.b = gw2Var;
    }

    public final Optional a(int i, boolean z) {
        if (this.d.get()) {
            return Optional.a();
        }
        int i2 = 20;
        return (Optional) this.b.a(z ? 1 : 0).map(new ed1(19)).takeUntil(this.a).onErrorReturn(new ed1(i2)).doOnSuccess(new ed1(i2)).timeout(i, TimeUnit.MILLISECONDS, this.c, Single.just(Optional.a())).blockingGet();
    }
}

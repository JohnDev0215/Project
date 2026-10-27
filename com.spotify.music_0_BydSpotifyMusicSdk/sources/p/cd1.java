package p;

import com.spotify.automotive.auth.model.CoreNativeResponseException;
import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cd1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.automotive.auth.b e;
    public final /* synthetic */ AtomicInteger k;

    public /* synthetic */ cd1(com.spotify.automotive.auth.b bVar, AtomicInteger atomicInteger, int i) {
        this.c = i;
        this.e = bVar;
        this.k = atomicInteger;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = 1;
        int i2 = this.c;
        AtomicInteger atomicInteger = this.k;
        com.spotify.automotive.auth.b bVar = this.e;
        switch (i2) {
            case 0:
                bVar.getClass();
                return ((Flowable) obj).f(new cd1(bVar, atomicInteger, i));
            default:
                Throwable th = (Throwable) obj;
                bVar.getClass();
                long jIncrementAndGet = atomicInteger.incrementAndGet();
                if (jIncrementAndGet >= 3 || ((th instanceof CoreNativeResponseException) && "74".equals(((CoreNativeResponseException) th).a().get("error")))) {
                    int i3 = Flowable.c;
                    Objects.requireNonNull(th, "throwable is null");
                    return new io.reactivex.rxjava3.internal.operators.flowable.s(1, new io.reactivex.rxjava3.internal.functions.e(th));
                }
                Logger.g("LoginControllerImpl - login attempt $attempt/3 error: " + th, new Object[0]);
                TimeUnit timeUnit = TimeUnit.SECONDS;
                int i4 = Flowable.c;
                Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.b;
                Objects.requireNonNull(timeUnit, "unit is null");
                Objects.requireNonNull(scheduler, "scheduler is null");
                return new io.reactivex.rxjava3.internal.operators.flowable.x0(Math.max(0L, jIncrementAndGet * jIncrementAndGet), timeUnit, scheduler);
        }
    }
}

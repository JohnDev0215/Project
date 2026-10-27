package d.a;

import io.reactivex.BackpressureStrategy;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableInternalHelper;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableRedo;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<T> implements h<T> {

    /* JADX INFO: compiled from: Observable.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3162a;

        static {
            int[] iArr = new int[BackpressureStrategy.values().length];
            f3162a = iArr;
            try {
                iArr[BackpressureStrategy.DROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3162a[BackpressureStrategy.LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3162a[BackpressureStrategy.MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3162a[BackpressureStrategy.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int b() {
        return c.a();
    }

    public static <T> g<T> e() {
        return d.a.r.a.l(d.a.p.e.b.c.f3199c);
    }

    public static <T> g<T> f(Throwable th) {
        d.a.p.b.b.d(th, "e is null");
        return g(d.a.p.b.a.a(th));
    }

    public static <T> g<T> g(Callable<? extends Throwable> callable) {
        d.a.p.b.b.d(callable, "errorSupplier is null");
        return d.a.r.a.l(new d.a.p.e.b.d(callable));
    }

    public static <T> g<T> m(T t) {
        d.a.p.b.b.d(t, "The item is null");
        return d.a.r.a.l(new d.a.p.e.b.f(t));
    }

    @Override // d.a.h
    public final void a(i<? super T> iVar) {
        d.a.p.b.b.d(iVar, "observer is null");
        try {
            i<? super T> iVarR = d.a.r.a.r(this, iVar);
            d.a.p.b.b.d(iVarR, "Plugin returned null Observer");
            t(iVarR);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            d.a.n.a.a(th);
            d.a.r.a.o(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final g<T> c(long j, TimeUnit timeUnit) {
        return d(j, timeUnit, d.a.s.a.a(), false);
    }

    public final g<T> d(long j, TimeUnit timeUnit, j jVar, boolean z) {
        d.a.p.b.b.d(timeUnit, "unit is null");
        d.a.p.b.b.d(jVar, "scheduler is null");
        return d.a.r.a.l(new d.a.p.e.b.b(this, j, timeUnit, jVar, z));
    }

    public final <R> g<R> h(d.a.o.e<? super T, ? extends h<? extends R>> eVar) {
        return i(eVar, false);
    }

    public final <R> g<R> i(d.a.o.e<? super T, ? extends h<? extends R>> eVar, boolean z) {
        return j(eVar, z, Integer.MAX_VALUE);
    }

    public final <R> g<R> j(d.a.o.e<? super T, ? extends h<? extends R>> eVar, boolean z, int i2) {
        return k(eVar, z, i2, b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> g<R> k(d.a.o.e<? super T, ? extends h<? extends R>> eVar, boolean z, int i2, int i3) {
        d.a.p.b.b.d(eVar, "mapper is null");
        d.a.p.b.b.e(i2, "maxConcurrency");
        d.a.p.b.b.e(i3, "bufferSize");
        if (!(this instanceof d.a.p.c.c)) {
            return d.a.r.a.l(new ObservableFlatMap(this, eVar, z, i2, i3));
        }
        Object objCall = ((d.a.p.c.c) this).call();
        return objCall == null ? e() : ObservableScalarXMap.a(objCall, eVar);
    }

    public final d.a.a l() {
        return d.a.r.a.i(new d.a.p.e.b.e(this));
    }

    public final <R> g<R> n(d.a.o.e<? super T, ? extends R> eVar) {
        d.a.p.b.b.d(eVar, "mapper is null");
        return d.a.r.a.l(new d.a.p.e.b.g(this, eVar));
    }

    public final g<T> o(j jVar) {
        return p(jVar, false, b());
    }

    public final g<T> p(j jVar, boolean z, int i2) {
        d.a.p.b.b.d(jVar, "scheduler is null");
        d.a.p.b.b.e(i2, "bufferSize");
        return d.a.r.a.l(new ObservableObserveOn(this, jVar, z, i2));
    }

    public final g<T> q(d.a.o.e<? super g<Throwable>, ? extends h<?>> eVar) {
        d.a.p.b.b.d(eVar, "handler is null");
        return d.a.r.a.l(new ObservableRedo(this, ObservableInternalHelper.a(eVar), true));
    }

    public final d<T> r() {
        return d.a.r.a.k(new d.a.p.e.b.h(this));
    }

    public final k<T> s() {
        return d.a.r.a.m(new d.a.p.e.b.i(this, null));
    }

    public abstract void t(i<? super T> iVar);

    public final g<T> u(j jVar) {
        d.a.p.b.b.d(jVar, "scheduler is null");
        return d.a.r.a.l(new ObservableSubscribeOn(this, jVar));
    }

    public final g<T> v(d.a.o.g<? super T> gVar) {
        d.a.p.b.b.d(gVar, "predicate is null");
        return d.a.r.a.l(new d.a.p.e.b.j(this, gVar));
    }

    public final c<T> w(BackpressureStrategy backpressureStrategy) {
        d.a.p.e.a.b bVar = new d.a.p.e.a.b(this);
        int i2 = a.f3162a[backpressureStrategy.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? bVar.b() : d.a.r.a.j(new d.a.p.e.a.e(bVar)) : bVar : bVar.e() : bVar.d();
    }
}

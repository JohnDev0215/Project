package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends Single {
    public final /* synthetic */ int c;
    public final boolean e;
    public final Object k;
    public final Object l;
    public final Object m;

    public /* synthetic */ n0(Object obj, Object obj2, Object obj3, boolean z, int i) {
        this.c = i;
        this.k = obj;
        this.l = obj2;
        this.m = obj3;
        this.e = z;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        boolean z = this.e;
        Object obj = this.m;
        Object obj2 = this.l;
        Object obj3 = this.k;
        switch (i) {
            case 0:
                EmptyDisposable emptyDisposable = EmptyDisposable.c;
                try {
                    Object obj4 = ((io.reactivex.rxjava3.functions.q) obj3).get();
                    try {
                        Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj4);
                        Objects.requireNonNull(objApply, "The singleFunction returned a null SingleSource");
                        ((SingleSource) objApply).subscribe(new SingleUsing$UsingSingleObserver(singleObserver, obj4, z, (io.reactivex.rxjava3.functions.g) obj));
                    } catch (Throwable th) {
                        th = th;
                        he1.R(th);
                        if (z) {
                            try {
                                ((io.reactivex.rxjava3.functions.g) obj).accept(obj4);
                            } catch (Throwable th2) {
                                he1.R(th2);
                                th = new CompositeException(th, th2);
                            }
                        }
                        singleObserver.onSubscribe(emptyDisposable);
                        singleObserver.onError(th);
                        if (z) {
                            return;
                        }
                        try {
                            ((io.reactivex.rxjava3.functions.g) obj).accept(obj4);
                            return;
                        } catch (Throwable th3) {
                            he1.R(th3);
                            RxJavaPlugins.b(th3);
                            return;
                        }
                    }
                } catch (Throwable th4) {
                    he1.R(th4);
                    singleObserver.onSubscribe(emptyDisposable);
                    singleObserver.onError(th4);
                    return;
                }
                break;
            default:
                ((SingleSource) obj3).subscribe(new i0(singleObserver, (TimeUnit) obj2, (Scheduler) obj, z));
                break;
        }
    }
}

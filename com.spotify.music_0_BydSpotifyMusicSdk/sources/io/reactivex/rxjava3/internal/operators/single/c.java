package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import java.util.Objects;
import p.he1;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
public final class c implements SingleObserver {
    public final /* synthetic */ int c;
    public final SingleObserver e;
    public final Object k;

    public /* synthetic */ c(Single single, SingleObserver singleObserver, int i) {
        this.c = i;
        this.k = single;
        this.e = singleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        Object objApply;
        int i = this.c;
        Object obj = this.k;
        SingleObserver singleObserver = this.e;
        switch (i) {
            case 0:
                singleObserver.onError(th);
                break;
            case 1:
                try {
                    ((o) obj).k.accept(th);
                } catch (Throwable th2) {
                    he1.R(th2);
                    th = new CompositeException(th, th2);
                }
                singleObserver.onError(th);
                break;
            case 2:
                try {
                    ((io.reactivex.rxjava3.functions.b) ((h) obj).k).accept(null, th);
                } catch (Throwable th3) {
                    he1.R(th3);
                    th = new CompositeException(th, th3);
                }
                singleObserver.onError(th);
                break;
            case 3:
                singleObserver.onError(th);
                break;
            case 4:
                try {
                    ((q) obj).k.run();
                } catch (Throwable th4) {
                    he1.R(th4);
                    th = new CompositeException(th, th4);
                }
                singleObserver.onError(th);
                break;
            case 5:
                singleObserver.onError(th);
                break;
            default:
                c0 c0Var = (c0) obj;
                if (((io.reactivex.rxjava3.functions.o) c0Var.k) != null) {
                    try {
                        objApply = ((io.reactivex.rxjava3.functions.o) ((c0) obj).k).apply(th);
                    } catch (Throwable th5) {
                        he1.R(th5);
                        singleObserver.onError(new CompositeException(th, th5));
                        return;
                    }
                } else {
                    objApply = c0Var.l;
                }
                if (objApply != null) {
                    singleObserver.onSuccess(objApply);
                } else {
                    NullPointerException nullPointerException = new NullPointerException("Value supplied was null");
                    nullPointerException.initCause(th);
                    singleObserver.onError(nullPointerException);
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        SingleObserver singleObserver = this.e;
        switch (i) {
            case 0:
                singleObserver.onSubscribe(disposable);
                break;
            case 1:
                singleObserver.onSubscribe(disposable);
                break;
            case 2:
                singleObserver.onSubscribe(disposable);
                break;
            case 3:
                singleObserver.onSubscribe(disposable);
                break;
            case 4:
                singleObserver.onSubscribe(disposable);
                break;
            case 5:
                singleObserver.onSubscribe(disposable);
                break;
            default:
                singleObserver.onSubscribe(disposable);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        int i = this.c;
        Object obj2 = this.k;
        SingleObserver singleObserver = this.e;
        switch (i) {
            case 0:
                try {
                    io.reactivex.rxjava3.functions.d dVar = (io.reactivex.rxjava3.functions.d) ((c0) obj2).l;
                    Object obj3 = ((c0) obj2).k;
                    ((u11) dVar).getClass();
                    singleObserver.onSuccess(Boolean.valueOf(Objects.equals(obj, obj3)));
                } catch (Throwable th) {
                    he1.R(th);
                    singleObserver.onError(th);
                    return;
                }
                break;
            case 1:
                singleObserver.onSuccess(obj);
                break;
            case 2:
                try {
                    ((io.reactivex.rxjava3.functions.b) ((h) obj2).k).accept(obj, null);
                    singleObserver.onSuccess(obj);
                } catch (Throwable th2) {
                    he1.R(th2);
                    singleObserver.onError(th2);
                    return;
                }
                break;
            case 3:
                try {
                    ((o) obj2).k.accept(obj);
                    singleObserver.onSuccess(obj);
                } catch (Throwable th3) {
                    he1.R(th3);
                    singleObserver.onError(th3);
                    return;
                }
                break;
            case 4:
                try {
                    ((q) obj2).k.run();
                    singleObserver.onSuccess(obj);
                } catch (Throwable th4) {
                    he1.R(th4);
                    singleObserver.onError(th4);
                    return;
                }
                break;
            case 5:
                try {
                    Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                    Objects.requireNonNull(objApply, "The mapper function returned a null value.");
                    singleObserver.onSuccess(objApply);
                } catch (Throwable th5) {
                    he1.R(th5);
                    onError(th5);
                    return;
                }
                break;
            default:
                singleObserver.onSuccess(obj);
                break;
        }
    }

    public c(SingleObserver singleObserver, io.reactivex.rxjava3.functions.o oVar) {
        this.c = 5;
        this.e = singleObserver;
        this.k = oVar;
    }
}

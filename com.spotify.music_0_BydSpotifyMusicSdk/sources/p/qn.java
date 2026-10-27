package p;

import android.content.Context;
import android.content.IntentFilter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class qn extends Observable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;
    public final Object l;

    public /* synthetic */ qn(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Disposable disposable = EmptyDisposable.c;
        switch (i) {
            case 0:
                v03.j(observer, "observer");
                observer.onSubscribe(new pn(observer, (Context) this.e, (IntentFilter) this.k, (Boolean) this.l));
                break;
            case 1:
                try {
                    Object obj = ((io.reactivex.rxjava3.functions.q) this.e).get();
                    io.reactivex.rxjava3.functions.c cVar = (io.reactivex.rxjava3.functions.c) this.k;
                    io.reactivex.rxjava3.internal.operators.observable.y1 y1Var = new io.reactivex.rxjava3.internal.operators.observable.y1(observer, cVar, (io.reactivex.rxjava3.functions.g) this.l, obj);
                    observer.onSubscribe(y1Var);
                    Object objK = y1Var.k;
                    if (!y1Var.l) {
                        while (!y1Var.l) {
                            y1Var.n = false;
                            try {
                                objK = cVar.k(objK, y1Var);
                                if (y1Var.m) {
                                    y1Var.l = true;
                                    y1Var.k = null;
                                    y1Var.a(objK);
                                    break;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                y1Var.k = null;
                                y1Var.l = true;
                                y1Var.onError(th);
                                y1Var.a(objK);
                                return;
                            }
                        }
                        y1Var.k = null;
                        y1Var.a(objK);
                    } else {
                        y1Var.k = null;
                        y1Var.a(objK);
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    observer.onSubscribe(disposable);
                    observer.onError(th2);
                    return;
                }
                break;
            default:
                try {
                    Iterator it = ((Iterable) this.k).iterator();
                    Objects.requireNonNull(it, "The iterator returned by other is null");
                    try {
                        if (!it.hasNext()) {
                            observer.onSubscribe(disposable);
                            observer.onComplete();
                        } else {
                            ((Observable) this.e).subscribe(new io.reactivex.rxjava3.internal.operators.observable.u5(observer, it, (io.reactivex.rxjava3.functions.c) this.l));
                        }
                    } catch (Throwable th3) {
                        he1.R(th3);
                        observer.onSubscribe(disposable);
                        observer.onError(th3);
                        return;
                    }
                } catch (Throwable th4) {
                    he1.R(th4);
                    observer.onSubscribe(disposable);
                    observer.onError(th4);
                    return;
                }
                break;
        }
    }

    public qn(Context context, IntentFilter intentFilter) {
        this.c = 0;
        this.e = context;
        this.k = intentFilter;
        this.l = null;
    }
}

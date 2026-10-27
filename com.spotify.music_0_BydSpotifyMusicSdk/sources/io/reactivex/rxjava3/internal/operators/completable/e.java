package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import p.az1;
import p.he1;
import p.mc0;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Completable {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ e(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        mc0 mc0Var = io.reactivex.rxjava3.internal.functions.k.b;
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                completableObserver.onSubscribe(EmptyDisposable.c);
                completableObserver.onError((Throwable) obj);
                break;
            case 1:
                Disposable disposableE = Disposable.e(mc0Var);
                completableObserver.onSubscribe(disposableE);
                if (!disposableE.isDisposed()) {
                    try {
                        ((io.reactivex.rxjava3.functions.a) obj).run();
                        if (!disposableE.isDisposed()) {
                            completableObserver.onComplete();
                        }
                        break;
                    } catch (Throwable th) {
                        he1.R(th);
                        if (disposableE.isDisposed()) {
                            RxJavaPlugins.b(th);
                            return;
                        } else {
                            completableObserver.onError(th);
                            return;
                        }
                    }
                }
                break;
            case 2:
                Disposable disposableE2 = Disposable.e(mc0Var);
                completableObserver.onSubscribe(disposableE2);
                try {
                    ((Callable) obj).call();
                    if (!disposableE2.isDisposed()) {
                        completableObserver.onComplete();
                    }
                    break;
                } catch (Throwable th2) {
                    he1.R(th2);
                    if (disposableE2.isDisposed()) {
                        RxJavaPlugins.b(th2);
                        return;
                    } else {
                        completableObserver.onError(th2);
                        return;
                    }
                }
                break;
            case 3:
                ((az1) obj).subscribe(new f(completableObserver));
                break;
            case 4:
                Disposable disposableE3 = Disposable.e(mc0Var);
                completableObserver.onSubscribe(disposableE3);
                if (!disposableE3.isDisposed()) {
                    try {
                        ((Runnable) obj).run();
                        if (!disposableE3.isDisposed()) {
                            completableObserver.onComplete();
                        }
                        break;
                    } catch (Throwable th3) {
                        he1.R(th3);
                        if (disposableE3.isDisposed()) {
                            RxJavaPlugins.b(th3);
                            return;
                        } else {
                            completableObserver.onError(th3);
                            return;
                        }
                    }
                }
                break;
            case 5:
                ((SingleSource) obj).subscribe(new g(0, completableObserver));
                break;
            default:
                ((CompletableSource) obj).subscribe(completableObserver);
                break;
        }
    }
}

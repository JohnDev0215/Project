package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import p.az1;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Single {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ d(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                SingleCreate$Emitter singleCreate$Emitter = new SingleCreate$Emitter(singleObserver);
                singleObserver.onSubscribe(singleCreate$Emitter);
                try {
                    ((SingleOnSubscribe) obj).subscribe(singleCreate$Emitter);
                } catch (Throwable th) {
                    he1.R(th);
                    singleCreate$Emitter.onError(th);
                    return;
                }
                break;
            case 1:
                Disposable disposableE = Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);
                singleObserver.onSubscribe(disposableE);
                if (!disposableE.isDisposed()) {
                    try {
                        Object objCall = ((Callable) obj).call();
                        Objects.requireNonNull(objCall, "The callable returned a null value");
                        if (!disposableE.isDisposed()) {
                            singleObserver.onSuccess(objCall);
                        }
                        break;
                    } catch (Throwable th2) {
                        he1.R(th2);
                        if (disposableE.isDisposed()) {
                            RxJavaPlugins.b(th2);
                            return;
                        } else {
                            singleObserver.onError(th2);
                            return;
                        }
                    }
                }
                break;
            case 2:
                ((az1) obj).subscribe(new d0(singleObserver));
                break;
            case 3:
                singleObserver.onSubscribe(EmptyDisposable.c);
                singleObserver.onSuccess(obj);
                break;
            default:
                ((Single) obj).subscribe(new io.reactivex.rxjava3.internal.operators.mixed.e(singleObserver));
                break;
        }
    }
}

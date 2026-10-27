package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class w0 extends Observable {
    public final /* synthetic */ int c;
    public final io.reactivex.rxjava3.functions.q e;

    public /* synthetic */ w0(int i, io.reactivex.rxjava3.functions.q qVar) {
        this.c = i;
        this.e = qVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        Object obj;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        int i = this.c;
        io.reactivex.rxjava3.functions.q qVar = this.e;
        switch (i) {
            case 0:
                try {
                    Object obj2 = qVar.get();
                    Objects.requireNonNull(obj2, "The supplier returned a null ObservableSource");
                    ((ObservableSource) obj2).subscribe(observer);
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(th);
                    return;
                }
            default:
                try {
                    obj = qVar.get();
                } catch (Throwable th2) {
                    th = th2;
                    he1.R(th);
                }
                if (obj == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("Supplier returned a null Throwable.");
                }
                Throwable th3 = io.reactivex.rxjava3.internal.util.c.a;
                th = (Throwable) obj;
                observer.onSubscribe(emptyDisposable);
                observer.onError(th);
                return;
        }
    }
}

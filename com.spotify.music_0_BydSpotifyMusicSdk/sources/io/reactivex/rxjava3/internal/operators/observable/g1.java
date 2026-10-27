package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class g1 extends io.reactivex.rxjava3.internal.observers.a {
    public final /* synthetic */ int n;
    public final Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g1(Observer observer, Object obj, int i) {
        super(observer);
        this.n = i;
        this.o = obj;
    }

    @Override // io.reactivex.rxjava3.internal.observers.a, io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        switch (this.n) {
        }
        return c(i);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        int i = this.n;
        Object obj2 = this.o;
        Observer observer = this.c;
        switch (i) {
            case 0:
                observer.onNext(obj);
                if (this.m == 0) {
                    try {
                        ((io.reactivex.rxjava3.functions.g) obj2).accept(obj);
                    } catch (Throwable th) {
                        a(th);
                        return;
                    }
                }
                break;
            default:
                if (this.m != 0) {
                    observer.onNext(null);
                } else {
                    try {
                        if (((io.reactivex.rxjava3.functions.p) obj2).test(obj)) {
                            observer.onNext(obj);
                        }
                    } catch (Throwable th2) {
                        a(th2);
                        return;
                    }
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Object objPoll;
        int i = this.n;
        Object obj = this.o;
        switch (i) {
            case 0:
                Object objPoll2 = this.k.poll();
                if (objPoll2 != null) {
                    ((io.reactivex.rxjava3.functions.g) obj).accept(objPoll2);
                }
                return objPoll2;
        }
        do {
            objPoll = this.k.poll();
            if (objPoll != null) {
            }
            return objPoll;
        } while (!((io.reactivex.rxjava3.functions.p) obj).test(objPoll));
        return objPoll;
    }
}

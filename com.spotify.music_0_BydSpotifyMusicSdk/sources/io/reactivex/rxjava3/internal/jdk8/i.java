package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import java.util.Objects;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class i extends io.reactivex.rxjava3.internal.observers.a {
    public final /* synthetic */ int n;
    public final io.reactivex.rxjava3.functions.o o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i) {
        super(observer);
        this.n = i;
        this.o = oVar;
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
        io.reactivex.rxjava3.functions.o oVar = this.o;
        Observer observer = this.c;
        switch (i) {
            case 0:
                if (!this.l) {
                    if (this.m != 0) {
                        observer.onNext(null);
                    } else {
                        try {
                            Object objApply = oVar.apply(obj);
                            Objects.requireNonNull(objApply, "The mapper returned a null Optional");
                            Optional optional = (Optional) objApply;
                            if (optional.isPresent()) {
                                observer.onNext(optional.get());
                            }
                        } catch (Throwable th) {
                            a(th);
                            return;
                        }
                    }
                    break;
                }
                break;
            default:
                if (!this.l) {
                    if (this.m != 0) {
                        observer.onNext(null);
                    } else {
                        try {
                            Object objApply2 = oVar.apply(obj);
                            Objects.requireNonNull(objApply2, "The mapper function returned a null value.");
                            observer.onNext(objApply2);
                        } catch (Throwable th2) {
                            a(th2);
                        }
                    }
                    break;
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Optional optional;
        int i = this.n;
        io.reactivex.rxjava3.functions.o oVar = this.o;
        switch (i) {
            case 0:
                break;
            default:
                Object objPoll = this.k.poll();
                if (objPoll == null) {
                    return null;
                }
                Object objApply = oVar.apply(objPoll);
                Objects.requireNonNull(objApply, "The mapper function returned a null value.");
                return objApply;
        }
        do {
            Object objPoll2 = this.k.poll();
            if (objPoll2 == null) {
                return null;
            }
            Object objApply2 = oVar.apply(objPoll2);
            Objects.requireNonNull(objApply2, "The mapper returned a null Optional");
            optional = (Optional) objApply2;
        } while (!optional.isPresent());
        return optional.get();
    }
}

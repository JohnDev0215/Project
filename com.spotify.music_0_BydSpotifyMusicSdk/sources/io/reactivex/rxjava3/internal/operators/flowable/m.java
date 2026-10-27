package io.reactivex.rxjava3.internal.operators.flowable;

import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class m extends io.reactivex.rxjava3.internal.subscribers.a {
    public final /* synthetic */ int n;
    public final Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(io.reactivex.rxjava3.operators.a aVar, Object obj, int i) {
        super(aVar);
        this.n = i;
        this.o = obj;
    }

    @Override // io.reactivex.rxjava3.internal.subscribers.a, io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        switch (this.n) {
        }
        return c(i);
    }

    @Override // io.reactivex.rxjava3.operators.a
    public final boolean d(Object obj) {
        int i = this.n;
        Object obj2 = this.o;
        io.reactivex.rxjava3.operators.a aVar = this.c;
        switch (i) {
            case 0:
                if (!this.l) {
                    if (this.m != 0) {
                        break;
                    } else {
                        try {
                            if (((io.reactivex.rxjava3.functions.p) obj2).test(obj) && aVar.d(obj)) {
                            }
                        } catch (Throwable th) {
                            he1.R(th);
                            this.e.cancel();
                            onError(th);
                            return true;
                        }
                    }
                }
                break;
            default:
                if (!this.l) {
                    if (this.m != 0) {
                        aVar.d(null);
                    } else {
                        try {
                            Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                            Objects.requireNonNull(objApply, "The mapper function returned a null value.");
                        } catch (Throwable th2) {
                            he1.R(th2);
                            this.e.cancel();
                            onError(th2);
                            return true;
                        }
                    }
                    break;
                }
                break;
        }
        return true;
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        switch (this.n) {
            case 0:
                if (!d(obj)) {
                    this.e.a(1L);
                }
                break;
            default:
                if (!this.l) {
                    int i = this.m;
                    io.reactivex.rxjava3.operators.a aVar = this.c;
                    if (i != 0) {
                        aVar.onNext(null);
                    } else {
                        try {
                            Object objApply = ((io.reactivex.rxjava3.functions.o) this.o).apply(obj);
                            Objects.requireNonNull(objApply, "The mapper function returned a null value.");
                            aVar.onNext(objApply);
                        } catch (Throwable th) {
                            he1.R(th);
                            this.e.cancel();
                            onError(th);
                        }
                    }
                    break;
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        int i = this.n;
        Object obj = this.o;
        switch (i) {
            case 0:
                io.reactivex.rxjava3.operators.d dVar = this.k;
                io.reactivex.rxjava3.functions.p pVar = (io.reactivex.rxjava3.functions.p) obj;
                while (true) {
                    Object objPoll = dVar.poll();
                    if (objPoll == null) {
                        return null;
                    }
                    if (pVar.test(objPoll)) {
                        return objPoll;
                    }
                    if (this.m == 2) {
                        dVar.a(1L);
                    }
                }
                break;
            default:
                Object objPoll2 = this.k.poll();
                if (objPoll2 == null) {
                    return null;
                }
                Object objApply = ((io.reactivex.rxjava3.functions.o) obj).apply(objPoll2);
                Objects.requireNonNull(objApply, "The mapper function returned a null value.");
                return objApply;
        }
    }
}

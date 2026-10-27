package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class j2 implements io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.c {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ j2(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public ObservableSource a(Object obj) {
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null Iterable");
                return new r0(3, (Iterable) objApply);
            default:
                Object objApply2 = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                Objects.requireNonNull(objApply2, "The itemDelay returned a null ObservableSource");
                return new n3((ObservableSource) objApply2, 1L, 2).map(new io.reactivex.rxjava3.internal.functions.e(obj)).defaultIfEmpty(obj);
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return a(obj);
            case 1:
                return a(obj);
            default:
                Object objApply = ((u) this.e).l.apply(new Object[]{obj});
                Objects.requireNonNull(objApply, "The combiner returned a null value");
                return objApply;
        }
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        Object obj3 = this.e;
        int i = this.c;
        switch (i) {
            case 0:
                Emitter emitter = (Emitter) obj2;
                switch (i) {
                    case 0:
                        ((io.reactivex.rxjava3.functions.b) obj3).accept(obj, emitter);
                        break;
                    default:
                        ((io.reactivex.rxjava3.functions.g) obj3).accept(emitter);
                        break;
                }
                break;
            default:
                Emitter emitter2 = (Emitter) obj2;
                switch (i) {
                    case 0:
                        ((io.reactivex.rxjava3.functions.b) obj3).accept(obj, emitter2);
                        break;
                    default:
                        ((io.reactivex.rxjava3.functions.g) obj3).accept(emitter2);
                        break;
                }
                break;
        }
        return obj;
    }
}

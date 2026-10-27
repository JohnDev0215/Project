package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class k2 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final io.reactivex.rxjava3.functions.c e;
    public final Object k;

    public /* synthetic */ k2(io.reactivex.rxjava3.functions.c cVar, Object obj, int i) {
        this.c = i;
        this.e = cVar;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        io.reactivex.rxjava3.functions.c cVar = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                return cVar.k(obj2, obj);
            default:
                Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null ObservableSource");
                return new h3((ObservableSource) objApply, new k2(cVar, obj, 0), 4);
        }
    }
}

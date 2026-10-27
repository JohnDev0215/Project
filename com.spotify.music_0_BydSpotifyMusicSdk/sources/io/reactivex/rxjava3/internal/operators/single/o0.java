package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class o0 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ Single e;

    public /* synthetic */ o0(Single single, int i) {
        this.c = i;
        this.e = single;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        Single single = this.e;
        switch (i) {
            case 0:
                Object objApply = ((io.reactivex.rxjava3.functions.o) ((h) single).k).apply(new Object[]{obj});
                Objects.requireNonNull(objApply, "The zipper returned a null value");
                return objApply;
            default:
                Object objApply2 = ((io.reactivex.rxjava3.functions.o) ((h) single).k).apply(new Object[]{obj});
                Objects.requireNonNull(objApply2, "The zipper returned a null value");
                return objApply2;
        }
    }
}

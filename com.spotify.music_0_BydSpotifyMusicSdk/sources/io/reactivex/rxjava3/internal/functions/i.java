package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.o;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class i implements io.reactivex.rxjava3.functions.b {
    public final o c;
    public final o e;

    public i(o oVar, o oVar2) {
        this.c = oVar;
        this.e = oVar2;
    }

    @Override // io.reactivex.rxjava3.functions.b
    public final void accept(Object obj, Object obj2) {
        ((Map) obj).put(this.e.apply(obj2), this.c.apply(obj2));
    }
}

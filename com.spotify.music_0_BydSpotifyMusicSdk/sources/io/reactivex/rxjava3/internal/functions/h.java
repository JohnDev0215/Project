package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.o;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h implements io.reactivex.rxjava3.functions.b {
    public final o c;

    public h(o oVar) {
        this.c = oVar;
    }

    @Override // io.reactivex.rxjava3.functions.b
    public final void accept(Object obj, Object obj2) {
        ((Map) obj).put(this.c.apply(obj2), obj2);
    }
}

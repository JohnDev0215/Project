package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.o;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j implements io.reactivex.rxjava3.functions.b {
    public final o c;
    public final o e;
    public final o k;

    public j(o oVar, o oVar2, o oVar3) {
        this.c = oVar;
        this.e = oVar2;
        this.k = oVar3;
    }

    @Override // io.reactivex.rxjava3.functions.b
    public final void accept(Object obj, Object obj2) {
        Map map = (Map) obj;
        Object objApply = this.k.apply(obj2);
        Collection collection = (Collection) map.get(objApply);
        if (collection == null) {
            collection = (Collection) this.c.apply(objApply);
            map.put(objApply, collection);
        }
        collection.add(this.e.apply(obj2));
    }
}

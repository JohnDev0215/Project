package p;

import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class j02 implements io.reactivex.rxjava3.functions.o {
    public static final j02 c = new j02();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        List<d02> list = (List) obj;
        v03.i(list, "eventsEntities");
        ArrayList arrayList = new ArrayList(lu.W(list));
        for (d02 d02Var : list) {
            arrayList.add(new Pair(d02Var.a, Integer.valueOf(d02Var.b)));
        }
        return kotlin.collections.c.k0(arrayList);
    }
}

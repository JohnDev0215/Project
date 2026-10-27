package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShoppingCart.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tl2 {
    public g71 a;
    public final List<g71> b;
    public List<g71> c;

    public tl2(g71 g71Var, List<g71> list) {
        this.a = g71Var;
        this.b = list;
    }

    public final List<g71> a() {
        List<g71> list = this.b;
        if (list == null && this.c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        List<g71> list2 = this.c;
        if (list2 != null) {
            arrayList.addAll(list2);
        }
        return Collections.unmodifiableList(arrayList);
    }
}

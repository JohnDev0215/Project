package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class qi2 extends zb1 {
    public static final List k0(li2 li2Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = li2Var.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        int size = arrayList.size();
        if (size == 0) {
            return gh0.e;
        }
        if (size != 1) {
            return arrayList;
        }
        List listSingletonList = Collections.singletonList(arrayList.get(0));
        i21.d(listSingletonList, "singletonList(element)");
        return listSingletonList;
    }
}

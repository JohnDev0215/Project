package androidx.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import p.s41;
import p.v03;
import p.v50;
import p.w50;

/* JADX INFO: loaded from: classes.dex */
public final class OverwritingInputMerger extends s41 {
    @Override // p.s41
    public final w50 a(ArrayList arrayList) throws Throwable {
        v50 v50Var = new v50();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Map mapUnmodifiableMap = Collections.unmodifiableMap(((w50) it.next()).a);
            v03.i(mapUnmodifiableMap, "input.keyValueMap");
            linkedHashMap.putAll(mapUnmodifiableMap);
        }
        v50Var.a(linkedHashMap);
        w50 w50Var = new w50(v50Var.a);
        w50.b(w50Var);
        return w50Var;
    }
}

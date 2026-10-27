package S0;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import n7.AbstractC2004s;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7862a;

    public z(y... yVarArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (yVarArr.length > 0) {
            y yVar = yVarArr[0];
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.size() != 1) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.k(AbstractC1347d.j("'", str, "' must be unique. Actual [ ["), AbstractC1998m.f0(list, null, null, null, null, 63), ']').toString());
            }
            AbstractC2004s.L(arrayList, list);
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        this.f7862a = arrayList2;
        if (arrayList2.size() <= 0) {
            return;
        }
        arrayList2.get(0).getClass();
        throw new ClassCastException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof z) && A7.m.a(this.f7862a, ((z) obj).f7862a);
    }

    public final int hashCode() {
        return this.f7862a.hashCode();
    }
}

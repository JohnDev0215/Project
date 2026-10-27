package p;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public final class z4 {
    public final oh1 a;
    public final Set b;

    public z4(oh1 oh1Var, Set set) {
        this.a = oh1Var;
        this.b = set;
    }

    public final Set a() {
        oh1 oh1Var = this.a;
        if (oh1Var == null) {
            return EmptySet.c;
        }
        Map map = (Map) oh1Var.e;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (v03.b(entry.getValue(), Boolean.TRUE)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap.keySet();
    }
}

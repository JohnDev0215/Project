package p;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public final class tx0 {
    public static final tx0 c = new tx0(EmptySet.c, kotlin.collections.c.h0());
    public final Set a;
    public final LinkedHashMap b;

    public tx0(EmptySet emptySet, Map map) {
        v03.j(emptySet, "flags");
        this.a = emptySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ((EmptySet) map.entrySet()).getClass();
        this.b = linkedHashMap;
    }
}

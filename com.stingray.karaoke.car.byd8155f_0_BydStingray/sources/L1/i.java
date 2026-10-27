package l1;

import h2.AbstractC1347d;
import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static final i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f18434m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f18435n;
    public static final HashMap o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ i[] f18436p;

    static {
        i iVar = new i("SPREAD", 0);
        l = iVar;
        i iVar2 = new i("SPREAD_INSIDE", 1);
        f18434m = iVar2;
        i iVar3 = new i("PACKED", 2);
        f18435n = iVar3;
        f18436p = new i[]{iVar, iVar2, iVar3};
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        o = map2;
        map.put("packed", iVar3);
        map.put("spread_inside", iVar2);
        map.put("spread", iVar);
        AbstractC1347d.m(2, map2, "packed", 1, "spread_inside");
        map2.put("spread", 0);
    }

    public static int a(String str) {
        HashMap map = o;
        if (map.containsKey(str)) {
            return ((Integer) map.get(str)).intValue();
        }
        return -1;
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f18436p.clone();
    }
}

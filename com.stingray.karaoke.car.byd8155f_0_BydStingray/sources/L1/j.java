package l1;

import h2.AbstractC1347d;
import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final HashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ j[] f18437m;

    /* JADX INFO: Fake field, exist only in values array */
    j EF0;

    static {
        j jVar = new j("NONE", 0);
        j jVar2 = new j("CHAIN", 1);
        j jVar3 = new j("ALIGNED", 2);
        f18437m = new j[]{jVar, jVar2, jVar3};
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        l = map2;
        map.put("none", jVar);
        map.put("chain", jVar2);
        map.put("aligned", jVar3);
        AbstractC1347d.m(0, map2, "none", 3, "chain");
        map2.put("aligned", 2);
    }

    public static j valueOf(String str) {
        return (j) Enum.valueOf(j.class, str);
    }

    public static j[] values() {
        return (j[]) f18437m.clone();
    }
}

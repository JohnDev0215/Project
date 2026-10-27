package B3;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static final i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ i[] f631m;

    static {
        i iVar = new i("IGNORE", 0);
        i iVar2 = new i("RESPECT_PERFORMANCE", 1);
        l = iVar2;
        i[] iVarArr = {iVar, iVar2, new i("RESPECT_ALL", 2)};
        f631m = iVarArr;
        r.e(iVarArr);
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f631m.clone();
    }
}

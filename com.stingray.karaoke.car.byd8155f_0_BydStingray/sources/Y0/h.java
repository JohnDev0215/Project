package Y0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f10616m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ h[] f10617n;

    static {
        h hVar = new h("Ltr", 0);
        l = hVar;
        h hVar2 = new h("Rtl", 1);
        f10616m = hVar2;
        f10617n = new h[]{hVar, hVar2};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f10617n.clone();
    }
}

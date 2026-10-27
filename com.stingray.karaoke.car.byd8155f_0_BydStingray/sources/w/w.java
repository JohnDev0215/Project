package w;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static final w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final w f23145m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final w f23146n;
    public static final /* synthetic */ w[] o;

    static {
        w wVar = new w("PreEnter", 0);
        l = wVar;
        w wVar2 = new w("Visible", 1);
        f23145m = wVar2;
        w wVar3 = new w("PostExit", 2);
        f23146n = wVar3;
        o = new w[]{wVar, wVar2, wVar3};
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) o.clone();
    }
}

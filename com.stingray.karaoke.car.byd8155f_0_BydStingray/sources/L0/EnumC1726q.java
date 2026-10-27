package l0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: l0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC1726q {
    public static final EnumC1726q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC1726q f18351m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC1726q f18352n;
    public static final /* synthetic */ EnumC1726q[] o;

    static {
        EnumC1726q enumC1726q = new EnumC1726q("Active", 0);
        l = enumC1726q;
        EnumC1726q enumC1726q2 = new EnumC1726q("ActiveParent", 1);
        f18351m = enumC1726q2;
        EnumC1726q enumC1726q3 = new EnumC1726q("Captured", 2);
        EnumC1726q enumC1726q4 = new EnumC1726q("Inactive", 3);
        f18352n = enumC1726q4;
        o = new EnumC1726q[]{enumC1726q, enumC1726q2, enumC1726q3, enumC1726q4};
    }

    public static EnumC1726q valueOf(String str) {
        return (EnumC1726q) Enum.valueOf(EnumC1726q.class, str);
    }

    public static EnumC1726q[] values() {
        return (EnumC1726q[]) o.clone();
    }

    public final boolean a() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return true;
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                return true;
            }
            if (iOrdinal != 3) {
                throw new A9.k();
            }
        }
        return false;
    }
}

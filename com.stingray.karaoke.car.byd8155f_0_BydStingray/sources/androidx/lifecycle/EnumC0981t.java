package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: androidx.lifecycle.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0981t {
    public static final EnumC0981t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0981t f12005m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0981t f12006n;
    public static final EnumC0981t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final EnumC0981t f12007p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ EnumC0981t[] f12008q;

    static {
        EnumC0981t enumC0981t = new EnumC0981t("DESTROYED", 0);
        l = enumC0981t;
        EnumC0981t enumC0981t2 = new EnumC0981t("INITIALIZED", 1);
        f12005m = enumC0981t2;
        EnumC0981t enumC0981t3 = new EnumC0981t("CREATED", 2);
        f12006n = enumC0981t3;
        EnumC0981t enumC0981t4 = new EnumC0981t("STARTED", 3);
        o = enumC0981t4;
        EnumC0981t enumC0981t5 = new EnumC0981t("RESUMED", 4);
        f12007p = enumC0981t5;
        f12008q = new EnumC0981t[]{enumC0981t, enumC0981t2, enumC0981t3, enumC0981t4, enumC0981t5};
    }

    public static EnumC0981t valueOf(String str) {
        return (EnumC0981t) Enum.valueOf(EnumC0981t.class, str);
    }

    public static EnumC0981t[] values() {
        return (EnumC0981t[]) f12008q.clone();
    }
}

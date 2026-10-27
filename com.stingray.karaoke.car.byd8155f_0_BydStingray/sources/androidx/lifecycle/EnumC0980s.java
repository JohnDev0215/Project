package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: androidx.lifecycle.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0980s {
    private static final /* synthetic */ EnumC0980s[] $VALUES;
    public static final C0979q Companion;
    public static final EnumC0980s ON_ANY;
    public static final EnumC0980s ON_CREATE;
    public static final EnumC0980s ON_DESTROY;
    public static final EnumC0980s ON_PAUSE;
    public static final EnumC0980s ON_RESUME;
    public static final EnumC0980s ON_START;
    public static final EnumC0980s ON_STOP;

    static {
        EnumC0980s enumC0980s = new EnumC0980s("ON_CREATE", 0);
        ON_CREATE = enumC0980s;
        EnumC0980s enumC0980s2 = new EnumC0980s("ON_START", 1);
        ON_START = enumC0980s2;
        EnumC0980s enumC0980s3 = new EnumC0980s("ON_RESUME", 2);
        ON_RESUME = enumC0980s3;
        EnumC0980s enumC0980s4 = new EnumC0980s("ON_PAUSE", 3);
        ON_PAUSE = enumC0980s4;
        EnumC0980s enumC0980s5 = new EnumC0980s("ON_STOP", 4);
        ON_STOP = enumC0980s5;
        EnumC0980s enumC0980s6 = new EnumC0980s("ON_DESTROY", 5);
        ON_DESTROY = enumC0980s6;
        EnumC0980s enumC0980s7 = new EnumC0980s("ON_ANY", 6);
        ON_ANY = enumC0980s7;
        $VALUES = new EnumC0980s[]{enumC0980s, enumC0980s2, enumC0980s3, enumC0980s4, enumC0980s5, enumC0980s6, enumC0980s7};
        Companion = new C0979q();
    }

    public static EnumC0980s valueOf(String str) {
        return (EnumC0980s) Enum.valueOf(EnumC0980s.class, str);
    }

    public static EnumC0980s[] values() {
        return (EnumC0980s[]) $VALUES.clone();
    }

    public final EnumC0981t a() {
        switch (r.f12004a[ordinal()]) {
            case 1:
            case 2:
                return EnumC0981t.f12006n;
            case 3:
            case 4:
                return EnumC0981t.o;
            case 5:
                return EnumC0981t.f12007p;
            case 6:
                return EnumC0981t.l;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}

package androidx.lifecycle;

/* JADX INFO: renamed from: androidx.lifecycle.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0979q {
    public static EnumC0980s a(EnumC0981t enumC0981t) {
        A7.m.f(enumC0981t, "state");
        int iOrdinal = enumC0981t.ordinal();
        if (iOrdinal == 2) {
            return EnumC0980s.ON_DESTROY;
        }
        if (iOrdinal == 3) {
            return EnumC0980s.ON_STOP;
        }
        if (iOrdinal != 4) {
            return null;
        }
        return EnumC0980s.ON_PAUSE;
    }

    public static EnumC0980s b(EnumC0981t enumC0981t) {
        A7.m.f(enumC0981t, "state");
        int iOrdinal = enumC0981t.ordinal();
        if (iOrdinal == 1) {
            return EnumC0980s.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return EnumC0980s.ON_START;
        }
        if (iOrdinal != 3) {
            return null;
        }
        return EnumC0980s.ON_RESUME;
    }
}

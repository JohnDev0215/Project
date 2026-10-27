package com.fasterxml.jackson.annotation;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Nulls {
    public static final Nulls c;
    public static final Nulls e;
    public static final Nulls k;
    public static final Nulls l;
    public static final /* synthetic */ Nulls[] m;

    /* JADX INFO: Fake field, exist only in values array */
    Nulls EF0;

    static {
        Nulls nulls = new Nulls("SET", 0);
        Nulls nulls2 = new Nulls("SKIP", 1);
        c = nulls2;
        Nulls nulls3 = new Nulls("FAIL", 2);
        e = nulls3;
        Nulls nulls4 = new Nulls("AS_EMPTY", 3);
        k = nulls4;
        Nulls nulls5 = new Nulls("DEFAULT", 4);
        l = nulls5;
        m = new Nulls[]{nulls, nulls2, nulls3, nulls4, nulls5};
    }

    public static Nulls valueOf(String str) {
        return (Nulls) Enum.valueOf(Nulls.class, str);
    }

    public static Nulls[] values() {
        return (Nulls[]) m.clone();
    }
}

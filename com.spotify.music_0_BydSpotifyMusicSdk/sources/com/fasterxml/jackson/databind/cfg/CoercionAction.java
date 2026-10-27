package com.fasterxml.jackson.databind.cfg;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoercionAction {
    public static final CoercionAction c;
    public static final CoercionAction e;
    public static final CoercionAction k;
    public static final CoercionAction l;
    public static final /* synthetic */ CoercionAction[] m;

    static {
        CoercionAction coercionAction = new CoercionAction("Fail", 0);
        c = coercionAction;
        CoercionAction coercionAction2 = new CoercionAction("TryConvert", 1);
        e = coercionAction2;
        CoercionAction coercionAction3 = new CoercionAction("AsNull", 2);
        k = coercionAction3;
        CoercionAction coercionAction4 = new CoercionAction("AsEmpty", 3);
        l = coercionAction4;
        m = new CoercionAction[]{coercionAction, coercionAction2, coercionAction3, coercionAction4};
    }

    public static CoercionAction valueOf(String str) {
        return (CoercionAction) Enum.valueOf(CoercionAction.class, str);
    }

    public static CoercionAction[] values() {
        return (CoercionAction[]) m.clone();
    }
}

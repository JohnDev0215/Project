package com.fasterxml.jackson.databind.util;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AccessPattern {
    public static final AccessPattern c;
    public static final AccessPattern e;
    public static final AccessPattern k;
    public static final /* synthetic */ AccessPattern[] l;

    static {
        AccessPattern accessPattern = new AccessPattern("ALWAYS_NULL", 0);
        c = accessPattern;
        AccessPattern accessPattern2 = new AccessPattern("CONSTANT", 1);
        e = accessPattern2;
        AccessPattern accessPattern3 = new AccessPattern("DYNAMIC", 2);
        k = accessPattern3;
        l = new AccessPattern[]{accessPattern, accessPattern2, accessPattern3};
    }

    public static AccessPattern valueOf(String str) {
        return (AccessPattern) Enum.valueOf(AccessPattern.class, str);
    }

    public static AccessPattern[] values() {
        return (AccessPattern[]) l.clone();
    }
}

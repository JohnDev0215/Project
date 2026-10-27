package com.google.firebase.crashlytics.internal.common;

import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class CommonUtils$Architecture {
    public static final CommonUtils$Architecture c;
    public static final HashMap e;
    public static final /* synthetic */ CommonUtils$Architecture[] k;

    /* JADX INFO: Fake field, exist only in values array */
    CommonUtils$Architecture EF10;

    static {
        CommonUtils$Architecture commonUtils$Architecture = new CommonUtils$Architecture("X86_32", 0);
        CommonUtils$Architecture commonUtils$Architecture2 = new CommonUtils$Architecture("X86_64", 1);
        CommonUtils$Architecture commonUtils$Architecture3 = new CommonUtils$Architecture("ARM_UNKNOWN", 2);
        CommonUtils$Architecture commonUtils$Architecture4 = new CommonUtils$Architecture("PPC", 3);
        CommonUtils$Architecture commonUtils$Architecture5 = new CommonUtils$Architecture("PPC64", 4);
        CommonUtils$Architecture commonUtils$Architecture6 = new CommonUtils$Architecture("ARMV6", 5);
        CommonUtils$Architecture commonUtils$Architecture7 = new CommonUtils$Architecture("ARMV7", 6);
        CommonUtils$Architecture commonUtils$Architecture8 = new CommonUtils$Architecture("UNKNOWN", 7);
        c = commonUtils$Architecture8;
        CommonUtils$Architecture commonUtils$Architecture9 = new CommonUtils$Architecture("ARMV7S", 8);
        CommonUtils$Architecture commonUtils$Architecture10 = new CommonUtils$Architecture("ARM64", 9);
        k = new CommonUtils$Architecture[]{commonUtils$Architecture, commonUtils$Architecture2, commonUtils$Architecture3, commonUtils$Architecture4, commonUtils$Architecture5, commonUtils$Architecture6, commonUtils$Architecture7, commonUtils$Architecture8, commonUtils$Architecture9, commonUtils$Architecture10};
        HashMap map = new HashMap(4);
        e = map;
        map.put("armeabi-v7a", commonUtils$Architecture7);
        map.put("armeabi", commonUtils$Architecture6);
        map.put("arm64-v8a", commonUtils$Architecture10);
        map.put("x86", commonUtils$Architecture);
    }

    public static CommonUtils$Architecture valueOf(String str) {
        return (CommonUtils$Architecture) Enum.valueOf(CommonUtils$Architecture.class, str);
    }

    public static CommonUtils$Architecture[] values() {
        return (CommonUtils$Architecture[]) k.clone();
    }
}

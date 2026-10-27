package com.google.gson;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class LongSerializationPolicy {
    public static final LongSerializationPolicy c;
    public static final /* synthetic */ LongSerializationPolicy[] e;

    static {
        LongSerializationPolicy longSerializationPolicy = new LongSerializationPolicy() { // from class: com.google.gson.LongSerializationPolicy.1
        };
        c = longSerializationPolicy;
        e = new LongSerializationPolicy[]{longSerializationPolicy, new LongSerializationPolicy() { // from class: com.google.gson.LongSerializationPolicy.2
        }};
    }

    public static LongSerializationPolicy valueOf(String str) {
        return (LongSerializationPolicy) Enum.valueOf(LongSerializationPolicy.class, str);
    }

    public static LongSerializationPolicy[] values() {
        return (LongSerializationPolicy[]) e.clone();
    }
}

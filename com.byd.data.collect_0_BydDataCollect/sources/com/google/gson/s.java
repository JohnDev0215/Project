package com.google.gson;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: LongSerializationPolicy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s f4599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f4600c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ s[] f4601d;

    /* JADX INFO: compiled from: LongSerializationPolicy.java */
    enum a extends s {
        a(String str, int i2) {
            super(str, i2, null);
        }
    }

    static {
        a aVar = new a("DEFAULT", 0);
        f4599b = aVar;
        s sVar = new s("STRING", 1) { // from class: com.google.gson.s.b
            {
                a aVar2 = null;
            }
        };
        f4600c = sVar;
        f4601d = new s[]{aVar, sVar};
    }

    private s(String str, int i2) {
    }

    public static s valueOf(String str) {
        return (s) Enum.valueOf(s.class, str);
    }

    public static s[] values() {
        return (s[]) f4601d.clone();
    }

    /* synthetic */ s(String str, int i2, a aVar) {
        this(str, i2);
    }
}

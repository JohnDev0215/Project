package com.bumptech.glide;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f12684m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f12685n;
    public static final f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ f[] f12686p;

    static {
        f fVar = new f("IMMEDIATE", 0);
        l = fVar;
        f fVar2 = new f("HIGH", 1);
        f12684m = fVar2;
        f fVar3 = new f("NORMAL", 2);
        f12685n = fVar3;
        f fVar4 = new f("LOW", 3);
        o = fVar4;
        f12686p = new f[]{fVar, fVar2, fVar3, fVar4};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f12686p.clone();
    }
}

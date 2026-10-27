package a9;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: a9.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0936f {
    public static final EnumC0936f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0936f f11233m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0936f f11234n;
    public static final EnumC0936f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ EnumC0936f[] f11235p;

    static {
        EnumC0936f enumC0936f = new EnumC0936f("SUCCESSFUL", 0);
        l = enumC0936f;
        EnumC0936f enumC0936f2 = new EnumC0936f("REREGISTER", 1);
        f11233m = enumC0936f2;
        EnumC0936f enumC0936f3 = new EnumC0936f("CANCELLED", 2);
        f11234n = enumC0936f3;
        EnumC0936f enumC0936f4 = new EnumC0936f("ALREADY_SELECTED", 3);
        o = enumC0936f4;
        EnumC0936f[] enumC0936fArr = {enumC0936f, enumC0936f2, enumC0936f3, enumC0936f4};
        f11235p = enumC0936fArr;
        r.e(enumC0936fArr);
    }

    public static EnumC0936f valueOf(String str) {
        return (EnumC0936f) Enum.valueOf(EnumC0936f.class, str);
    }

    public static EnumC0936f[] values() {
        return (EnumC0936f[]) f11235p.clone();
    }
}

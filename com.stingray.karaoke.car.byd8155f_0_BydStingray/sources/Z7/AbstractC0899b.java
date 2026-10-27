package Z7;

import g8.C1319i;
import g8.EnumC1318h;
import h8.AbstractC1387d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1996k;
import n7.AbstractC1999n;
import o8.C2058c;

/* JADX INFO: renamed from: Z7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0899b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2058c f10927a = new C2058c("javax.annotation.meta.TypeQualifierNickname");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2058c f10928b = new C2058c("javax.annotation.meta.TypeQualifier");
    public static final C2058c c = new C2058c("javax.annotation.meta.TypeQualifierDefault");
    public static final C2058c d = new C2058c("kotlin.annotations.jvm.UnderMigration");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f10929e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final LinkedHashMap f10930f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Set f10931g;

    static {
        EnumC0898a enumC0898a = EnumC0898a.FIELD;
        EnumC0898a enumC0898a2 = EnumC0898a.METHOD_RETURN_TYPE;
        EnumC0898a enumC0898a3 = EnumC0898a.VALUE_PARAMETER;
        List listD = AbstractC1999n.D(enumC0898a, enumC0898a2, enumC0898a3, EnumC0898a.TYPE_PARAMETER_BOUNDS, EnumC0898a.TYPE_USE);
        C2058c c2058c = y.c;
        EnumC1318h enumC1318h = EnumC1318h.f15008n;
        Map mapS = n7.z.s(new m7.i(c2058c, new n(new C1319i(enumC1318h), listD, false)), new m7.i(y.f10979f, new n(new C1319i(enumC1318h), listD, false)));
        f10929e = mapS;
        f10930f = n7.z.u(n7.z.s(new m7.i(new C2058c("javax.annotation.ParametersAreNullableByDefault"), new n(new C1319i(EnumC1318h.f15007m), AbstractC1387d.j(enumC0898a3))), new m7.i(new C2058c("javax.annotation.ParametersAreNonnullByDefault"), new n(new C1319i(enumC1318h), AbstractC1387d.j(enumC0898a3)))), mapS);
        f10931g = AbstractC1996k.c0(new C2058c[]{y.f10981h, y.f10982i});
    }
}

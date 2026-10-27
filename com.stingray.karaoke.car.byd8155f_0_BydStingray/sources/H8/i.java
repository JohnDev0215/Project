package H8;

import A7.k;
import A7.m;
import F8.K;
import Q7.InterfaceC0657j;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import n7.AbstractC1985B;
import n7.u;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f2895a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f2896b = c.l;
    public static final a c = new a(C2061f.g(String.format("<Error class: %s>", Arrays.copyOf(new Object[]{"unknown class"}, 1))));
    public static final f d = c(h.f2886s, new String[0]);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f2897e = c(h.f2870F, new String[0]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Set f2898f = AbstractC1985B.t(new d());

    public static final e a(int i7, boolean z6, String... strArr) {
        k.p(i7, "kind");
        m.f(strArr, "formatParams");
        if (!z6) {
            return new e(i7, (String[]) Arrays.copyOf(strArr, strArr.length));
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        m.f(strArr2, "formatParams");
        return new j(i7, (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public static final e b(int i7, String... strArr) {
        k.p(i7, "kind");
        return a(i7, false, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final f c(h hVar, String... strArr) {
        m.f(hVar, "kind");
        u uVar = u.l;
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        m.f(strArr2, "formatParams");
        return e(hVar, uVar, d(hVar, (String[]) Arrays.copyOf(strArr2, strArr2.length)), (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public static g d(h hVar, String... strArr) {
        m.f(hVar, "kind");
        m.f(strArr, "formatParams");
        return new g(hVar, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static f e(h hVar, List list, K k10, String... strArr) {
        m.f(hVar, "kind");
        m.f(strArr, "formatParams");
        return new f(k10, b(7, k10.toString()), hVar, list, false, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final boolean f(InterfaceC0657j interfaceC0657j) {
        return interfaceC0657j != null && ((interfaceC0657j instanceof a) || (interfaceC0657j.o() instanceof a) || interfaceC0657j == f2896b);
    }
}

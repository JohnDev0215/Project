package h8;

import F8.A;
import F8.AbstractC0287w;
import F8.C0269d;
import e8.C1240e;
import j8.Q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import w8.EnumC2609d;

/* JADX INFO: renamed from: h8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1390g implements B8.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1390g f15610b = new C1390g();
    public static final C1390g c = new C1390g();
    public static final C1390g d = new C1390g();

    public static String[] a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add("<init>(" + str + ")V");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static l c(String str) {
        EnumC2609d enumC2609d;
        l c1393j;
        A7.m.f(str, "representation");
        char cCharAt = str.charAt(0);
        EnumC2609d[] enumC2609dArrValues = EnumC2609d.values();
        int length = enumC2609dArrValues.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                enumC2609d = null;
                break;
            }
            enumC2609d = enumC2609dArrValues[i7];
            if (enumC2609d.c().charAt(0) == cCharAt) {
                break;
            }
            i7++;
        }
        if (enumC2609d != null) {
            return new k(enumC2609d);
        }
        if (cCharAt == 'V') {
            return new k(null);
        }
        if (cCharAt == '[') {
            String strSubstring = str.substring(1);
            A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
            c1393j = new C1392i(c(strSubstring));
        } else {
            if (cCharAt == 'L') {
                Q8.h.F0(str, ';');
            }
            String strSubstring2 = str.substring(1, str.length() - 1);
            A7.m.e(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            c1393j = new C1393j(strSubstring2);
        }
        return c1393j;
    }

    public static C1393j d(String str) {
        A7.m.f(str, "internalName");
        return new C1393j(str);
    }

    public static LinkedHashSet e(String str, String... strArr) {
        A7.m.f(str, "internalName");
        A7.m.f(strArr, "signatures");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            linkedHashSet.add(str + '.' + str2);
        }
        return linkedHashSet;
    }

    public static LinkedHashSet f(String str, String... strArr) {
        A7.m.f(strArr, "signatures");
        return e("java/lang/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static LinkedHashSet g(String str, String... strArr) {
        return e("java/util/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static String h(l lVar) {
        String strC;
        A7.m.f(lVar, "type");
        if (lVar instanceof C1392i) {
            return "[" + h(((C1392i) lVar).f15613i);
        }
        if (lVar instanceof k) {
            EnumC2609d enumC2609d = ((k) lVar).f15615i;
            return (enumC2609d == null || (strC = enumC2609d.c()) == null) ? "V" : strC;
        }
        if (lVar instanceof C1393j) {
            return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("L"), ((C1393j) lVar).f15614i, ';');
        }
        throw new A9.k();
    }

    @Override // B8.m
    public AbstractC0287w b(Q q4, String str, A a10, A a11) {
        A7.m.f(q4, "proto");
        A7.m.f(str, "flexibleId");
        A7.m.f(a10, "lowerBound");
        A7.m.f(a11, "upperBound");
        return !str.equals("kotlin.jvm.PlatformType") ? H8.i.c(H8.h.f2891x, str, a10.toString(), a11.toString()) : q4.l(m8.k.f19331g) ? new C1240e(a10, a11) : C0269d.j(a10, a11);
    }
}

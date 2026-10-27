package v5;

import kotlin.jvm.internal.l;
import v5.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public class h extends g {
    public static int b(int i7, int i8) {
        return i7 < i8 ? i8 : i7;
    }

    public static long c(long j7, long j8) {
        return j7 < j8 ? j8 : j7;
    }

    public static int d(int i7, int i8) {
        return i7 > i8 ? i8 : i7;
    }

    public static long e(long j7, long j8) {
        return j7 > j8 ? j8 : j7;
    }

    public static int f(int i7, int i8, int i9) {
        if (i8 <= i9) {
            return i7 < i8 ? i8 : i7 > i9 ? i9 : i7;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i9 + " is less than minimum " + i8 + '.');
    }

    public static a g(int i7, int i8) {
        return a.f9683i.a(i7, i8, -1);
    }

    public static a h(a aVar, int i7) {
        l.e(aVar, "<this>");
        g.a(i7 > 0, Integer.valueOf(i7));
        a.C0166a c0166a = a.f9683i;
        int iC = aVar.c();
        int iD = aVar.d();
        if (aVar.e() <= 0) {
            i7 = -i7;
        }
        return c0166a.a(iC, iD, i7);
    }

    public static c i(int i7, int i8) {
        return i8 <= Integer.MIN_VALUE ? c.f9691j.a() : new c(i7, i8 - 1);
    }
}

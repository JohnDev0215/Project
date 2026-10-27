package kotlin.o;

import kotlin.jvm.internal.i;
import kotlin.o.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public class f extends e {
    public static int b(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static int c(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static long d(long j, long j2) {
        return j > j2 ? j2 : j;
    }

    public static int e(int i, int i2, int i3) {
        if (i2 <= i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
    }

    public static a f(int i, int i2) {
        return a.f5131b.a(i, i2, -1);
    }

    public static a g(a aVar, int i) {
        i.c(aVar, "$this$step");
        e.a(i > 0, Integer.valueOf(i));
        a.C0095a c0095a = a.f5131b;
        int iA = aVar.a();
        int iB = aVar.b();
        if (aVar.c() <= 0) {
            i = -i;
        }
        return c0095a.a(iA, iB, i);
    }

    public static c h(int i, int i2) {
        return i2 <= Integer.MIN_VALUE ? c.f5140g.a() : new c(i, i2 - 1);
    }
}

package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.q;
import java.util.Comparator;
import p.ik;
import p.mc0;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static final mc0 b;
    public static final ik d;
    public static final d a = new d(0);
    public static final b c = new b();
    public static final ik e = new ik(2);
    public static final ik f = new ik(4);
    public static final c g = new c(1);
    public static final c h = new c(0);
    public static final g i = new g(0);
    public static final u11 j = new u11();

    static {
        int i2 = 1;
        b = new mc0(i2);
        d = new ik(i2);
    }

    public static q a() {
        return Functions$HashSetSupplier.c;
    }

    public static Comparator b() {
        return Functions$NaturalComparator.c;
    }

    public static void c(int i2, String str) {
        if (i2 > 0) {
            return;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i2);
    }

    public static void d(String str, long j2) {
        if (j2 > 0) {
            return;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j2);
    }
}

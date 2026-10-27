package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class a90 implements Iterator, g81 {
    public int c = -1;
    public int e;
    public int k;
    public i51 l;
    public int m;
    public final /* synthetic */ b90 n;

    public a90(b90 b90Var) {
        this.n = b90Var;
        int i = b90Var.b;
        int length = b90Var.a.length();
        if (length < 0) {
            throw new IllegalArgumentException(u4.f("Cannot coerce value to an empty range: maximum ", length, " is less than minimum 0."));
        }
        if (i < 0) {
            i = 0;
        } else if (i > length) {
            i = length;
        }
        this.e = i;
        this.k = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r7 = this;
            int r0 = r7.k
            r1 = 0
            if (r0 >= 0) goto Lc
            r7.c = r1
            r0 = 0
            r7.l = r0
            goto L80
        Lc:
            p.b90 r2 = r7.n
            int r3 = r2.c
            r4 = -1
            r5 = 1
            if (r3 <= 0) goto L1b
            int r6 = r7.m
            int r6 = r6 + r5
            r7.m = r6
            if (r6 >= r3) goto L23
        L1b:
            java.lang.CharSequence r3 = r2.a
            int r3 = r3.length()
            if (r0 <= r3) goto L35
        L23:
            p.i51 r0 = new p.i51
            int r1 = r7.e
            java.lang.CharSequence r2 = r2.a
            int r2 = kotlin.text.b.k0(r2)
            r0.<init>(r1, r2, r5)
            r7.l = r0
            r7.k = r4
            goto L7e
        L35:
            p.fz0 r0 = r2.d
            java.lang.CharSequence r3 = r2.a
            int r6 = r7.k
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r0 = r0.g(r3, r6)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L59
            p.i51 r0 = new p.i51
            int r1 = r7.e
            java.lang.CharSequence r2 = r2.a
            int r2 = kotlin.text.b.k0(r2)
            r0.<init>(r1, r2, r5)
            r7.l = r0
            r7.k = r4
            goto L7e
        L59:
            java.lang.Object r2 = r0.a()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.b()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r7.e
            p.i51 r3 = p.vr2.x(r3, r2)
            r7.l = r3
            int r2 = r2 + r0
            r7.e = r2
            if (r0 != 0) goto L7b
            r1 = r5
        L7b:
            int r2 = r2 + r1
            r7.k = r2
        L7e:
            r7.c = r5
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.a90.a():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.c == -1) {
            a();
        }
        return this.c == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.c == -1) {
            a();
        }
        if (this.c == 0) {
            throw new NoSuchElementException();
        }
        i51 i51Var = this.l;
        v03.g(i51Var, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.l = null;
        this.c = -1;
        return i51Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

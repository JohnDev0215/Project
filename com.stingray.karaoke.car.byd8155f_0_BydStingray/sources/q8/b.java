package Q8;

import java.util.Iterator;
import java.util.NoSuchElementException;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator, B7.a {
    public int l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7213m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7214n;
    public G7.d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7215p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ c f7216q;

    public b(c cVar) {
        this.f7216q = cVar;
        int iV = AbstractC2673a.v(cVar.f7218b, 0, cVar.f7217a.length());
        this.f7213m = iV;
        this.f7214n = iV;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r7 = this;
            int r0 = r7.f7214n
            r1 = 0
            if (r0 >= 0) goto Lb
            r7.l = r1
            r0 = 0
            r7.o = r0
            goto L7b
        Lb:
            Q8.c r2 = r7.f7216q
            int r3 = r2.c
            r4 = -1
            r5 = 1
            if (r3 <= 0) goto L1a
            int r6 = r7.f7215p
            int r6 = r6 + r5
            r7.f7215p = r6
            if (r6 >= r3) goto L22
        L1a:
            java.lang.CharSequence r3 = r2.f7217a
            int r3 = r3.length()
            if (r0 <= r3) goto L34
        L22:
            G7.d r0 = new G7.d
            int r1 = r7.f7213m
            java.lang.CharSequence r2 = r2.f7217a
            int r2 = Q8.h.G0(r2)
            r0.<init>(r1, r2, r5)
            r7.o = r0
            r7.f7214n = r4
            goto L79
        L34:
            z7.n r0 = r2.d
            java.lang.CharSequence r3 = r2.f7217a
            int r6 = r7.f7214n
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r0 = r0.invoke(r3, r6)
            m7.i r0 = (m7.i) r0
            if (r0 != 0) goto L58
            G7.d r0 = new G7.d
            int r1 = r7.f7213m
            java.lang.CharSequence r2 = r2.f7217a
            int r2 = Q8.h.G0(r2)
            r0.<init>(r1, r2, r5)
            r7.o = r0
            r7.f7214n = r4
            goto L79
        L58:
            java.lang.Object r2 = r0.l
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.f19259m
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r7.f7213m
            G7.d r3 = x3.AbstractC2673a.b0(r3, r2)
            r7.o = r3
            int r2 = r2 + r0
            r7.f7213m = r2
            if (r0 != 0) goto L76
            r1 = r5
        L76:
            int r2 = r2 + r1
            r7.f7214n = r2
        L79:
            r7.l = r5
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Q8.b.b():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.l == -1) {
            b();
        }
        return this.l == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.l == -1) {
            b();
        }
        if (this.l == 0) {
            throw new NoSuchElementException();
        }
        G7.d dVar = this.o;
        A7.m.d(dVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.o = null;
        this.l = -1;
        return dVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

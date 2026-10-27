package u;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: u.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2486z implements B7.f, Set, B7.a {
    public final /* synthetic */ C2453A l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2453A f22658m;

    public C2486z(C2453A c2453a) {
        this.f22658m = c2453a;
        this.l = c2453a;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        return this.f22658m.a(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        A7.m.f(collection, "elements");
        C2453A c2453a = this.f22658m;
        int i7 = c2453a.d;
        for (Object obj : collection) {
            c2453a.f22580b[c2453a.d(obj)] = obj;
        }
        return i7 != c2453a.d;
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.f22658m.b();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.c(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        A7.m.f(collection, "elements");
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!this.l.c(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.l.g();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new P8.i(this.f22658m);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        return this.f22658m.j(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        if (((r9 & ((~r9) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0089, code lost:
    
        r15 = -1;
     */
    @Override // java.util.Set, java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean removeAll(java.util.Collection r19) {
        /*
            r18 = this;
            java.lang.String r0 = "elements"
            r1 = r19
            A7.m.f(r1, r0)
            r0 = r18
            u.A r0 = r0.f22658m
            int r2 = r0.d
            java.util.Iterator r1 = r19.iterator()
        L11:
            boolean r3 = r1.hasNext()
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L96
            java.lang.Object r3 = r1.next()
            if (r3 == 0) goto L27
            r0.getClass()
            int r6 = r3.hashCode()
            goto L28
        L27:
            r6 = r5
        L28:
            r7 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r6 = r6 * r7
            int r7 = r6 << 16
            r6 = r6 ^ r7
            r7 = r6 & 127(0x7f, float:1.78E-43)
            int r8 = r0.c
            int r6 = r6 >>> 7
            r6 = r6 & r8
        L36:
            long[] r9 = r0.f22579a
            int r10 = r6 >> 3
            r11 = r6 & 7
            int r11 = r11 << 3
            r12 = r9[r10]
            long r12 = r12 >>> r11
            int r10 = r10 + r4
            r9 = r9[r10]
            int r14 = 64 - r11
            long r9 = r9 << r14
            long r14 = (long) r11
            long r14 = -r14
            r11 = 63
            long r14 = r14 >> r11
            long r9 = r9 & r14
            long r9 = r9 | r12
            long r11 = (long) r7
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r11 = r11 * r13
            long r11 = r11 ^ r9
            long r13 = r11 - r13
            long r11 = ~r11
            long r11 = r11 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
        L60:
            r15 = 0
            int r17 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r17 == 0) goto L80
            int r15 = java.lang.Long.numberOfTrailingZeros(r11)
            int r15 = r15 >> 3
            int r15 = r15 + r6
            r15 = r15 & r8
            java.lang.Object[] r4 = r0.f22580b
            r4 = r4[r15]
            boolean r4 = A7.m.a(r4, r3)
            if (r4 == 0) goto L79
            goto L8a
        L79:
            r15 = 1
            long r15 = r11 - r15
            long r11 = r11 & r15
            r4 = 1
            goto L60
        L80:
            long r11 = ~r9
            r4 = 6
            long r11 = r11 << r4
            long r9 = r9 & r11
            long r9 = r9 & r13
            int r4 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r4 == 0) goto L90
            r15 = -1
        L8a:
            if (r15 < 0) goto L11
            r0.k(r15)
            goto L11
        L90:
            int r5 = r5 + 8
            int r6 = r6 + r5
            r6 = r6 & r8
            r4 = 1
            goto L36
        L96:
            int r0 = r0.d
            if (r2 == r0) goto L9c
            r4 = 1
            goto L9d
        L9c:
            r4 = r5
        L9d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2486z.removeAll(java.util.Collection):boolean");
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        A7.m.f(collection, "elements");
        C2453A c2453a = this.f22658m;
        long[] jArr = c2453a.f22579a;
        int length = jArr.length - 2;
        boolean z6 = false;
        if (length >= 0) {
            int i7 = 0;
            boolean z10 = false;
            while (true) {
                long j3 = jArr[i7];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i10 = 8 - ((~(i7 - length)) >>> 31);
                    for (int i11 = 0; i11 < i10; i11++) {
                        if ((255 & j3) < 128) {
                            int i12 = (i7 << 3) + i11;
                            if (!collection.contains(c2453a.f22580b[i12])) {
                                c2453a.k(i12);
                                z10 = true;
                            }
                        }
                        j3 >>= 8;
                    }
                    if (i10 != 8) {
                        return z10;
                    }
                }
                if (i7 == length) {
                    z6 = z10;
                    break;
                }
                i7++;
            }
        }
        return z6;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.l.d;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        A7.m.f(objArr, "array");
        return A7.l.b(this, objArr);
    }
}

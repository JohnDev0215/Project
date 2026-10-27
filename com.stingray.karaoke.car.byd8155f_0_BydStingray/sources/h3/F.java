package h3;

import B2.C0073m;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class F implements B2.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f15276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f15277b;
    public final h2.t c = new h2.t(0, new byte[9400]);
    public final SparseIntArray d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e7.f f15278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Y2.j f15279f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final SparseArray f15280g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final SparseBooleanArray f15281h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SparseBooleanArray f15282i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C1349A f15283j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public G2.c f15284k;
    public B2.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15285m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f15286n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f15287p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f15288q;

    public F(int i7, Y2.j jVar, h2.y yVar, e7.f fVar) {
        this.f15278e = fVar;
        this.f15276a = i7;
        this.f15279f = jVar;
        this.f15277b = Collections.singletonList(yVar);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        this.f15281h = sparseBooleanArray;
        this.f15282i = new SparseBooleanArray();
        SparseArray sparseArray = new SparseArray();
        this.f15280g = sparseArray;
        this.d = new SparseIntArray();
        this.f15283j = new C1349A(1);
        this.l = B2.t.f599a;
        this.f15288q = -1;
        sparseBooleanArray.clear();
        sparseArray.clear();
        SparseArray sparseArray2 = new SparseArray();
        int size = sparseArray2.size();
        for (int i10 = 0; i10 < size; i10++) {
            sparseArray.put(sparseArray2.keyAt(i10), (I) sparseArray2.valueAt(i10));
        }
        sparseArray.put(0, new E(new io.sentry.internal.debugmeta.c(this)));
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        if ((this.f15276a & 1) == 0) {
            tVar = new P(tVar, this.f15279f);
        }
        this.l = tVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [h3.z] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [int] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.util.SparseArray] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2, types: [h3.I] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [android.util.SparseBooleanArray] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // B2.r
    public final int c(B2.s sVar, B2.v vVar) throws EOFException, InterruptedIOException {
        ?? r32;
        ?? r22;
        long j3;
        boolean z6;
        long j10 = ((C0073m) sVar).f588n;
        if (this.f15286n) {
            long j11 = -9223372036854775807L;
            C1349A c1349a = this.f15283j;
            if (j10 != -1 && !c1349a.d) {
                int i7 = this.f15288q;
                if (i7 <= 0) {
                    c1349a.a((C0073m) sVar);
                    return 0;
                }
                boolean z10 = c1349a.f15254f;
                h2.t tVar = c1349a.c;
                if (!z10) {
                    C0073m c0073m = (C0073m) sVar;
                    long j12 = c0073m.f588n;
                    int iMin = (int) Math.min(112800, j12);
                    long j13 = j12 - ((long) iMin);
                    if (c0073m.o == j13) {
                        tVar.E(iMin);
                        c0073m.f590q = 0;
                        c0073m.k(tVar.f15241a, 0, iMin, false);
                        int i10 = tVar.f15242b;
                        int i11 = tVar.c;
                        int i12 = i11 - 188;
                        while (true) {
                            if (i12 < i10) {
                                break;
                            }
                            byte[] bArr = tVar.f15241a;
                            int i13 = -4;
                            int i14 = 0;
                            while (true) {
                                if (i13 > 4) {
                                    break;
                                }
                                int i15 = (i13 * 188) + i12;
                                if (i15 < i10 || i15 >= i11 || bArr[i15] != 71) {
                                    i14 = 0;
                                } else {
                                    i14++;
                                    if (i14 == 5) {
                                        long jK0 = io.sentry.config.a.k0(tVar, i12, i7);
                                        if (jK0 != -9223372036854775807L) {
                                            j11 = jK0;
                                            break;
                                        }
                                    }
                                }
                                i13++;
                            }
                            i12--;
                        }
                        c1349a.f15256h = j11;
                        c1349a.f15254f = true;
                        return 0;
                    }
                    vVar.f600a = j13;
                } else {
                    if (c1349a.f15256h == -9223372036854775807L) {
                        c1349a.a((C0073m) sVar);
                        return 0;
                    }
                    if (c1349a.f15253e) {
                        long j14 = c1349a.f15255g;
                        if (j14 == -9223372036854775807L) {
                            c1349a.a((C0073m) sVar);
                            return 0;
                        }
                        h2.y yVar = c1349a.f15252b;
                        c1349a.f15257i = yVar.c(c1349a.f15256h) - yVar.b(j14);
                        c1349a.a((C0073m) sVar);
                        return 0;
                    }
                    C0073m c0073m2 = (C0073m) sVar;
                    int iMin2 = (int) Math.min(112800, c0073m2.f588n);
                    long j15 = 0;
                    if (c0073m2.o == j15) {
                        tVar.E(iMin2);
                        c0073m2.f590q = 0;
                        c0073m2.k(tVar.f15241a, 0, iMin2, false);
                        int i16 = tVar.f15242b;
                        int i17 = tVar.c;
                        while (true) {
                            if (i16 >= i17) {
                                break;
                            }
                            if (tVar.f15241a[i16] == 71) {
                                long jK02 = io.sentry.config.a.k0(tVar, i16, i7);
                                if (jK02 != -9223372036854775807L) {
                                    j11 = jK02;
                                    break;
                                }
                            }
                            i16++;
                        }
                        c1349a.f15255g = j11;
                        c1349a.f15253e = true;
                        return 0;
                    }
                    vVar.f600a = j15;
                }
                return 1;
            }
            if (this.o) {
                j3 = j10;
            } else {
                this.o = true;
                long j16 = c1349a.f15257i;
                if (j16 != -9223372036854775807L) {
                    j3 = j10;
                    G2.c cVar = new G2.c(new C8.d(1), new B5.u(this.f15288q, c1349a.f15252b), j16, j16 + 1, 0L, j10, 188L, 940);
                    this.f15284k = cVar;
                    this.l.w(cVar.f2652a);
                } else {
                    j3 = j10;
                    this.l.w(new B2.w(j16));
                }
            }
            if (this.f15287p) {
                z6 = false;
                this.f15287p = false;
                e(0L, 0L);
                if (((C0073m) sVar).o != 0) {
                    vVar.f600a = 0L;
                    return 1;
                }
            } else {
                z6 = false;
            }
            r32 = 1;
            r32 = 1;
            G2.c cVar2 = this.f15284k;
            r22 = z6;
            if (cVar2 != null) {
                r22 = z6;
                if (cVar2.c != null) {
                    return cVar2.b((C0073m) sVar, vVar);
                }
            }
        } else {
            r32 = 1;
            r22 = 0;
            j3 = j10;
        }
        h2.t tVar2 = this.c;
        byte[] bArr2 = tVar2.f15241a;
        if (9400 - tVar2.f15242b < 188) {
            int iA = tVar2.a();
            if (iA > 0) {
                System.arraycopy(bArr2, tVar2.f15242b, bArr2, r22, iA);
            }
            tVar2.F(iA, bArr2);
        }
        while (true) {
            int iA2 = tVar2.a();
            ?? r72 = this.f15280g;
            if (iA2 >= 188) {
                int i18 = tVar2.f15242b;
                int i19 = tVar2.c;
                byte[] bArr3 = tVar2.f15241a;
                while (i18 < i19 && bArr3[i18] != 71) {
                    i18++;
                }
                tVar2.H(i18);
                int i20 = i18 + 188;
                int i21 = tVar2.c;
                if (i20 > i21) {
                    return r22;
                }
                int iH = tVar2.h();
                if ((8388608 & iH) != 0) {
                    tVar2.H(i20);
                    return r22;
                }
                ?? r13 = (4194304 & iH) != 0 ? r32 : r22;
                int i22 = (2096896 & iH) >> 8;
                ?? r92 = (iH & 32) != 0 ? r32 : r22;
                ?? r73 = (iH & 16) != 0 ? (I) r72.get(i22) : 0;
                if (r73 == 0) {
                    tVar2.H(i20);
                    return r22;
                }
                int i23 = iH & 15;
                SparseIntArray sparseIntArray = this.d;
                int i24 = sparseIntArray.get(i22, i23 - 1);
                sparseIntArray.put(i22, i23);
                if (i24 == i23) {
                    tVar2.H(i20);
                    return r22;
                }
                if (i23 != ((i24 + r32) & 15)) {
                    r73.b();
                }
                if (r92 != 0) {
                    int iV = tVar2.v();
                    r13 = (r13 == true ? 1 : 0) | ((tVar2.v() & 64) != 0 ? 2 : r22);
                    tVar2.I(iV - r32);
                }
                boolean z11 = this.f15286n;
                if (z11 || !this.f15282i.get(i22, r22)) {
                    tVar2.G(i20);
                    r73.c(r13, tVar2);
                    tVar2.G(i21);
                }
                if (!z11 && this.f15286n && j3 != -1) {
                    this.f15287p = r32;
                }
                tVar2.H(i20);
                return r22;
            }
            int i25 = tVar2.c;
            int iN = ((C0073m) sVar).n(bArr2, i25, 9400 - i25);
            if (iN == -1) {
                for (?? r132 = r22; r132 < r72.size(); r132++) {
                    I i26 = (I) r72.valueAt(r132);
                    if (i26 instanceof z) {
                        ?? r02 = (z) i26;
                        if (r02.c == 3 && r02.f15508j == -1) {
                            r02.c(r32, new h2.t());
                        }
                    }
                }
                return -1;
            }
            tVar2.G(i25 + iN);
        }
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        G2.c cVar;
        long j11;
        List list = this.f15277b;
        int size = list.size();
        int i7 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            h2.y yVar = (h2.y) list.get(i10);
            synchronized (yVar) {
                j11 = yVar.f15250b;
            }
            boolean z6 = j11 == -9223372036854775807L;
            if (!z6) {
                long jD = yVar.d();
                z6 = (jD == -9223372036854775807L || jD == 0 || jD == j10) ? false : true;
            }
            if (z6) {
                yVar.e(j10);
            }
        }
        if (j10 != 0 && (cVar = this.f15284k) != null) {
            cVar.d(j10);
        }
        this.c.E(0);
        this.d.clear();
        while (true) {
            SparseArray sparseArray = this.f15280g;
            if (i7 >= sparseArray.size()) {
                return;
            }
            ((I) sparseArray.valueAt(i7)).b();
            i7++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
    
        r1 = r1 + 1;
     */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(B2.s r6) {
        /*
            r5 = this;
            h2.t r5 = r5.c
            byte[] r5 = r5.f15241a
            B2.m r6 = (B2.C0073m) r6
            r0 = 0
            r1 = 940(0x3ac, float:1.317E-42)
            r6.k(r5, r0, r1, r0)
            r1 = r0
        Ld:
            r2 = 188(0xbc, float:2.63E-43)
            if (r1 >= r2) goto L29
            r2 = r0
        L12:
            r3 = 5
            if (r2 >= r3) goto L24
            int r3 = r2 * 188
            int r3 = r3 + r1
            r3 = r5[r3]
            r4 = 71
            if (r3 == r4) goto L21
            int r1 = r1 + 1
            goto Ld
        L21:
            int r2 = r2 + 1
            goto L12
        L24:
            r6.i(r1)
            r5 = 1
            return r5
        L29:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.F.f(B2.s):boolean");
    }
}

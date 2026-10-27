package W2;

import B2.AbstractC0062b;
import B2.D;
import B2.N;
import B2.O;
import B2.P;
import d5.I;
import e2.C1207o;
import e2.C1208p;
import e2.K;
import e2.L;
import e2.M;
import h2.AbstractC1346c;
import h2.t;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class k extends i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j f9754n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f9755p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public D f9756q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public O f9757r;

    @Override // W2.i
    public final void a(long j3) {
        this.f9744g = j3;
        this.f9755p = j3 != 0;
        D d = this.f9756q;
        this.o = d != null ? d.f505e : 0;
    }

    @Override // W2.i
    public final long b(t tVar) {
        byte b10 = tVar.f15241a[0];
        if ((b10 & 1) == 1) {
            return -1L;
        }
        j jVar = this.f9754n;
        AbstractC1346c.k(jVar);
        boolean z6 = ((P[]) jVar.f9753q)[(b10 >> 1) & (255 >>> (8 - jVar.f9750m))].l;
        D d = (D) jVar.f9751n;
        int i7 = !z6 ? d.f505e : d.f506f;
        long j3 = this.f9755p ? (this.o + i7) / 4 : 0;
        byte[] bArr = tVar.f15241a;
        int length = bArr.length;
        int i10 = tVar.c + 4;
        if (length < i10) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, i10);
            tVar.F(bArrCopyOf.length, bArrCopyOf);
        } else {
            tVar.G(i10);
        }
        byte[] bArr2 = tVar.f15241a;
        int i11 = tVar.c;
        bArr2[i11 - 4] = (byte) (j3 & 255);
        bArr2[i11 - 3] = (byte) ((j3 >>> 8) & 255);
        bArr2[i11 - 2] = (byte) ((j3 >>> 16) & 255);
        bArr2[i11 - 1] = (byte) ((j3 >>> 24) & 255);
        this.f9755p = true;
        this.o = i7;
        return j3;
    }

    /* JADX WARN: Type inference failed for: r1v46, types: [byte[], java.io.Serializable] */
    @Override // W2.i
    public final boolean c(t tVar, long j3, M.t tVar2) throws M {
        j jVar;
        D d;
        D d10;
        byte[] bArr;
        long jFloor;
        if (this.f9754n != null) {
            ((C1208p) tVar2.l).getClass();
            return false;
        }
        D d11 = this.f9756q;
        int i7 = 4;
        if (d11 == null) {
            AbstractC0062b.w(1, tVar, false);
            tVar.n();
            int iV = tVar.v();
            int iN = tVar.n();
            int iJ = tVar.j();
            if (iJ <= 0) {
                iJ = -1;
            }
            int iJ2 = tVar.j();
            int i10 = iJ2 <= 0 ? -1 : iJ2;
            tVar.j();
            int iV2 = tVar.v();
            int iPow = (int) Math.pow(2.0d, iV2 & 15);
            int iPow2 = (int) Math.pow(2.0d, (iV2 & 240) >> 4);
            tVar.v();
            ?? CopyOf = Arrays.copyOf(tVar.f15241a, tVar.c);
            D d12 = new D();
            d12.f503a = iV;
            d12.f504b = iN;
            d12.c = iJ;
            d12.d = i10;
            d12.f505e = iPow;
            d12.f506f = iPow2;
            d12.f507g = CopyOf;
            this.f9756q = d12;
        } else {
            O o = this.f9757r;
            if (o == null) {
                this.f9757r = AbstractC0062b.u(tVar, true, true);
            } else {
                int i11 = tVar.c;
                byte[] bArr2 = new byte[i11];
                System.arraycopy(tVar.f15241a, 0, bArr2, 0, i11);
                int i12 = 5;
                AbstractC0062b.w(5, tVar, false);
                int iV3 = tVar.v() + 1;
                N n10 = new N(tVar.f15241a);
                n10.u(tVar.f15242b * 8);
                int i13 = 0;
                while (true) {
                    int i14 = 2;
                    int i15 = 16;
                    if (i13 >= iV3) {
                        D d13 = d11;
                        byte[] bArr3 = bArr2;
                        int i16 = 6;
                        int i17 = n10.i(6) + 1;
                        for (int i18 = 0; i18 < i17; i18++) {
                            if (n10.i(16) != 0) {
                                throw M.a(null, "placeholder of time domain transforms not zeroed out");
                            }
                        }
                        int i19 = n10.i(6) + 1;
                        int i20 = 0;
                        while (true) {
                            int i21 = 3;
                            if (i20 < i19) {
                                int i22 = n10.i(i15);
                                if (i22 == 0) {
                                    int i23 = 8;
                                    n10.u(8);
                                    n10.u(16);
                                    n10.u(16);
                                    n10.u(6);
                                    n10.u(8);
                                    int i24 = n10.i(4) + 1;
                                    int i25 = 0;
                                    while (i25 < i24) {
                                        n10.u(i23);
                                        i25++;
                                        i23 = 8;
                                    }
                                } else {
                                    if (i22 != 1) {
                                        throw M.a(null, "floor type greater than 1 not decodable: " + i22);
                                    }
                                    int i26 = n10.i(5);
                                    int[] iArr = new int[i26];
                                    int i27 = -1;
                                    for (int i28 = 0; i28 < i26; i28++) {
                                        int i29 = n10.i(4);
                                        iArr[i28] = i29;
                                        if (i29 > i27) {
                                            i27 = i29;
                                        }
                                    }
                                    int i30 = i27 + 1;
                                    int[] iArr2 = new int[i30];
                                    int i31 = 0;
                                    while (i31 < i30) {
                                        iArr2[i31] = n10.i(i21) + 1;
                                        int i32 = n10.i(i14);
                                        int i33 = 8;
                                        if (i32 > 0) {
                                            n10.u(8);
                                        }
                                        int i34 = 0;
                                        while (i34 < (1 << i32)) {
                                            n10.u(i33);
                                            i34++;
                                            i33 = 8;
                                        }
                                        i31++;
                                        i21 = 3;
                                        i14 = 2;
                                    }
                                    n10.u(i14);
                                    int i35 = n10.i(4);
                                    int i36 = 0;
                                    int i37 = 0;
                                    for (int i38 = 0; i38 < i26; i38++) {
                                        i36 += iArr2[iArr[i38]];
                                        while (i37 < i36) {
                                            n10.u(i35);
                                            i37++;
                                        }
                                    }
                                }
                                i20++;
                                i16 = 6;
                                i15 = 16;
                                i14 = 2;
                            } else {
                                int i39 = n10.i(i16) + 1;
                                int i40 = 0;
                                while (i40 < i39) {
                                    if (n10.i(16) > 2) {
                                        throw M.a(null, "residueType greater than 2 is not decodable");
                                    }
                                    n10.u(24);
                                    n10.u(24);
                                    n10.u(24);
                                    int i41 = n10.i(i16) + 1;
                                    int i42 = 8;
                                    n10.u(8);
                                    int[] iArr3 = new int[i41];
                                    for (int i43 = 0; i43 < i41; i43++) {
                                        iArr3[i43] = ((n10.h() ? n10.i(5) : 0) * 8) + n10.i(3);
                                    }
                                    int i44 = 0;
                                    while (i44 < i41) {
                                        int i45 = 0;
                                        while (i45 < i42) {
                                            if ((iArr3[i44] & (1 << i45)) != 0) {
                                                n10.u(i42);
                                            }
                                            i45++;
                                            i42 = 8;
                                        }
                                        i44++;
                                        i42 = 8;
                                    }
                                    i40++;
                                    i16 = 6;
                                }
                                int i46 = n10.i(i16) + 1;
                                int i47 = 0;
                                while (i47 < i46) {
                                    int i48 = n10.i(16);
                                    if (i48 != 0) {
                                        AbstractC1346c.p("VorbisUtil", "mapping type other than 0 not supported: " + i48);
                                        d = d13;
                                    } else {
                                        int i49 = n10.h() ? n10.i(4) + 1 : 1;
                                        boolean zH = n10.h();
                                        d = d13;
                                        int i50 = d.f503a;
                                        if (zH) {
                                            int i51 = n10.i(8) + 1;
                                            for (int i52 = 0; i52 < i51; i52++) {
                                                int i53 = i50 - 1;
                                                n10.u(AbstractC0062b.l(i53));
                                                n10.u(AbstractC0062b.l(i53));
                                            }
                                        }
                                        if (n10.i(2) != 0) {
                                            throw M.a(null, "to reserved bits must be zero after mapping coupling steps");
                                        }
                                        if (i49 > 1) {
                                            for (int i54 = 0; i54 < i50; i54++) {
                                                n10.u(4);
                                            }
                                        }
                                        for (int i55 = 0; i55 < i49; i55++) {
                                            n10.u(8);
                                            n10.u(8);
                                            n10.u(8);
                                        }
                                    }
                                    i47++;
                                    d13 = d;
                                }
                                D d14 = d13;
                                int i56 = n10.i(6);
                                int i57 = i56 + 1;
                                P[] pArr = new P[i57];
                                for (int i58 = 0; i58 < i57; i58++) {
                                    boolean zH2 = n10.h();
                                    n10.i(16);
                                    n10.i(16);
                                    n10.i(8);
                                    pArr[i58] = new P(zH2);
                                }
                                if (!n10.h()) {
                                    throw M.a(null, "framing bit after modes not set as expected");
                                }
                                jVar = new j(d14, o, bArr3, pArr, AbstractC0062b.l(i56));
                            }
                        }
                    } else {
                        if (n10.i(24) != 5653314) {
                            throw M.a(null, "expected code book to start with [0x56, 0x43, 0x42] at " + ((n10.c * 8) + n10.f526e));
                        }
                        int i59 = n10.i(16);
                        int i60 = n10.i(24);
                        if (n10.h()) {
                            n10.u(i12);
                            for (int i61 = 0; i61 < i60; i61 += n10.i(AbstractC0062b.l(i60 - i61))) {
                            }
                        } else {
                            boolean zH3 = n10.h();
                            for (int i62 = 0; i62 < i60; i62++) {
                                if (!zH3) {
                                    n10.u(i12);
                                } else if (n10.h()) {
                                    n10.u(i12);
                                }
                            }
                        }
                        int i63 = n10.i(i7);
                        if (i63 > 2) {
                            throw M.a(null, "lookup type greater than 2 not decodable: " + i63);
                        }
                        if (i63 == 1 || i63 == 2) {
                            n10.u(32);
                            n10.u(32);
                            int i64 = n10.i(i7) + 1;
                            n10.u(1);
                            if (i63 != 1) {
                                d10 = d11;
                                bArr = bArr2;
                                jFloor = ((long) i60) * ((long) i59);
                            } else if (i59 != 0) {
                                d10 = d11;
                                bArr = bArr2;
                                jFloor = (long) Math.floor(Math.pow(i60, 1.0d / ((double) i59)));
                            } else {
                                d10 = d11;
                                bArr = bArr2;
                                jFloor = 0;
                            }
                            n10.u((int) (jFloor * ((long) i64)));
                        } else {
                            d10 = d11;
                            bArr = bArr2;
                        }
                        i13++;
                        d11 = d10;
                        bArr2 = bArr;
                        i7 = 4;
                        i12 = 5;
                    }
                }
            }
        }
        jVar = null;
        this.f9754n = jVar;
        if (jVar == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        D d15 = (D) jVar.f9751n;
        arrayList.add((byte[]) d15.f507g);
        arrayList.add((byte[]) jVar.f9752p);
        K kR = AbstractC0062b.r(I.x((String[]) ((O) jVar.o).f527m));
        C1207o c1207o = new C1207o();
        c1207o.f14246m = L.l("audio/vorbis");
        c1207o.f14242h = d15.d;
        c1207o.f14243i = d15.c;
        c1207o.B = d15.f503a;
        c1207o.f14228C = d15.f504b;
        c1207o.f14248p = arrayList;
        c1207o.f14245k = kR;
        tVar2.l = new C1208p(c1207o);
        return true;
    }

    @Override // W2.i
    public final void d(boolean z6) {
        super.d(z6);
        if (z6) {
            this.f9754n = null;
            this.f9756q = null;
            this.f9757r = null;
        }
        this.o = 0;
        this.f9755p = false;
    }
}

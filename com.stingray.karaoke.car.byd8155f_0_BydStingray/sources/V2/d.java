package V2;

import B2.AbstractC0062b;
import G.i0;
import android.util.Pair;
import e2.L;
import e2.M;
import h2.AbstractC1343A;
import h2.t;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f9336a;

    static {
        int i7 = AbstractC1343A.f15195a;
        f9336a = "OpusHead".getBytes(StandardCharsets.UTF_8);
    }

    public static i0 a(int i7, t tVar) {
        tVar.H(i7 + 12);
        tVar.I(1);
        b(tVar);
        tVar.I(2);
        int iV = tVar.v();
        if ((iV & 128) != 0) {
            tVar.I(2);
        }
        if ((iV & 64) != 0) {
            tVar.I(tVar.v());
        }
        if ((iV & 32) != 0) {
            tVar.I(2);
        }
        tVar.I(1);
        b(tVar);
        String strD = L.d(tVar.v());
        if ("audio/mpeg".equals(strD) || "audio/vnd.dts".equals(strD) || "audio/vnd.dts.hd".equals(strD)) {
            return new i0(strD, null, -1L, -1L);
        }
        tVar.I(4);
        long jX = tVar.x();
        long jX2 = tVar.x();
        tVar.I(1);
        int iB = b(tVar);
        byte[] bArr = new byte[iB];
        tVar.f(0, iB, bArr);
        return new i0(strD, bArr, jX2 > 0 ? jX2 : -1L, jX > 0 ? jX : -1L);
    }

    public static int b(t tVar) {
        int iV = tVar.v();
        int i7 = iV & 127;
        while ((iV & 128) == 128) {
            iV = tVar.v();
            i7 = (i7 << 7) | (iV & 127);
        }
        return i7;
    }

    public static int c(int i7) {
        return (i7 >> 24) & 255;
    }

    public static i2.f d(t tVar) {
        long jP;
        long jP2;
        tVar.H(8);
        if (c(tVar.h()) == 0) {
            jP = tVar.x();
            jP2 = tVar.x();
        } else {
            jP = tVar.p();
            jP2 = tVar.p();
        }
        return new i2.f(jP, jP2, tVar.x());
    }

    public static Pair e(t tVar, int i7, int i10) throws M {
        Integer num;
        q qVar;
        Pair pairCreate;
        int i11;
        int i12;
        byte[] bArr;
        int i13 = tVar.f15242b;
        while (i13 - i7 < i10) {
            tVar.H(i13);
            int iH = tVar.h();
            AbstractC0062b.c("childAtomSize must be positive", iH > 0);
            if (tVar.h() == 1936289382) {
                int i14 = i13 + 8;
                int i15 = 0;
                int i16 = -1;
                String strT = null;
                Integer numValueOf = null;
                while (i14 - i13 < iH) {
                    tVar.H(i14);
                    int iH2 = tVar.h();
                    int iH3 = tVar.h();
                    if (iH3 == 1718775137) {
                        numValueOf = Integer.valueOf(tVar.h());
                    } else if (iH3 == 1935894637) {
                        tVar.I(4);
                        strT = tVar.t(4, StandardCharsets.UTF_8);
                    } else if (iH3 == 1935894633) {
                        i16 = i14;
                        i15 = iH2;
                    }
                    i14 += iH2;
                }
                if ("cenc".equals(strT) || "cbc1".equals(strT) || "cens".equals(strT) || "cbcs".equals(strT)) {
                    AbstractC0062b.c("frma atom is mandatory", numValueOf != null);
                    AbstractC0062b.c("schi atom is mandatory", i16 != -1);
                    int i17 = i16 + 8;
                    while (true) {
                        if (i17 - i16 >= i15) {
                            num = numValueOf;
                            qVar = null;
                            break;
                        }
                        tVar.H(i17);
                        int iH4 = tVar.h();
                        if (tVar.h() == 1952804451) {
                            int iC = c(tVar.h());
                            tVar.I(1);
                            if (iC == 0) {
                                tVar.I(1);
                                i11 = 0;
                                i12 = 0;
                            } else {
                                int iV = tVar.v();
                                int i18 = (iV & 240) >> 4;
                                i11 = iV & 15;
                                i12 = i18;
                            }
                            boolean z6 = tVar.v() == 1;
                            int iV2 = tVar.v();
                            byte[] bArr2 = new byte[16];
                            tVar.f(0, 16, bArr2);
                            if (z6 && iV2 == 0) {
                                int iV3 = tVar.v();
                                byte[] bArr3 = new byte[iV3];
                                tVar.f(0, iV3, bArr3);
                                bArr = bArr3;
                            } else {
                                bArr = null;
                            }
                            num = numValueOf;
                            qVar = new q(z6, strT, iV2, bArr2, i12, i11, bArr);
                        } else {
                            i17 += iH4;
                        }
                    }
                    AbstractC0062b.c("tenc atom is mandatory", qVar != null);
                    int i19 = AbstractC1343A.f15195a;
                    pairCreate = Pair.create(num, qVar);
                } else {
                    pairCreate = null;
                }
                if (pairCreate != null) {
                    return pairCreate;
                }
            }
            i13 += iH;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:168:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x07c4  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x09b6  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x09cc  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x09fc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static A2.g f(h2.t r63, int r64, int r65, java.lang.String r66, e2.C1204l r67, boolean r68) throws e2.M {
        /*
            Method dump skipped, instruction units count: 3570
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.d.f(h2.t, int, int, java.lang.String, e2.l, boolean):A2.g");
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01b7, code lost:
    
        r25 = -9223372036854775807L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00db, code lost:
    
        r15 = -9223372036854775807L;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x05c9 A[ADDED_TO_REGION, LOOP:14: B:246:0x05c9->B:250:0x05d4, LOOP_START, PHI: r17
  0x05c9: PHI (r17v7 int) = (r17v3 int), (r17v8 int) binds: [B:245:0x05c7, B:250:0x05d4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x05db  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x062f  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0632  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x07bc  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x083a  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x083c  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0840  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0843  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0846  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0851  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0866  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0912  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0956  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0980 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x05bd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:422:0x01d1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x019a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList g(i2.b r75, B2.z r76, long r77, e2.C1204l r79, boolean r80, boolean r81, c5.InterfaceC1027d r82) {
        /*
            Method dump skipped, instruction units count: 2442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.d.g(i2.b, B2.z, long, e2.l, boolean, boolean, c5.d):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:283:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0657  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void h(h2.t r45, int r46, int r47, int r48, int r49, int r50, e2.C1204l r51, A2.g r52, int r53) throws e2.M {
        /*
            Method dump skipped, instruction units count: 2357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.d.h(h2.t, int, int, int, int, int, e2.l, A2.g, int):void");
    }
}

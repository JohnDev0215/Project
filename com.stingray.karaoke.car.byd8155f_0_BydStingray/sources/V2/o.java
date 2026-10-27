package V2;

import B5.u;
import d5.I;
import e2.J;
import e2.K;
import g5.C1300a;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.t;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f9419a = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153, 1769172332, 1885955686};

    public static i2.a a(K k10, String str) {
        int i7 = 0;
        while (true) {
            J[] jArr = k10.l;
            if (i7 >= jArr.length) {
                return null;
            }
            J j3 = jArr[i7];
            if (j3 instanceof i2.a) {
                i2.a aVar = (i2.a) j3;
                if (aVar.l.equals(str)) {
                    return aVar;
                }
            }
            i7++;
        }
    }

    public static boolean b(int i7, boolean z6) {
        if ((i7 >>> 8) == 3368816) {
            return true;
        }
        if (i7 == 1751476579 && z6) {
            return true;
        }
        int[] iArr = f9419a;
        for (int i10 = 0; i10 < 29; i10++) {
            if (iArr[i10] == i7) {
                return true;
            }
        }
        return false;
    }

    public static P2.e c(int i7, t tVar) {
        int iH = tVar.h();
        if (tVar.h() == 1684108385) {
            tVar.I(8);
            String strR = tVar.r(iH - 16);
            return new P2.e("und", strR, strR);
        }
        AbstractC1346c.z("MetadataUtil", "Failed to parse comment attribute: " + i2.d.c(i7));
        return null;
    }

    public static P2.a d(t tVar) {
        int iH = tVar.h();
        if (tVar.h() != 1684108385) {
            AbstractC1346c.z("MetadataUtil", "Failed to parse cover art attribute");
            return null;
        }
        int iH2 = tVar.h();
        byte[] bArr = d.f9336a;
        int i7 = iH2 & 16777215;
        String str = i7 == 13 ? "image/jpeg" : i7 == 14 ? "image/png" : null;
        if (str == null) {
            AbstractC1347d.p("Unrecognized cover art flags: ", i7, "MetadataUtil");
            return null;
        }
        tVar.I(4);
        int i10 = iH - 16;
        byte[] bArr2 = new byte[i10];
        tVar.f(0, i10, bArr2);
        return new P2.a(str, null, 3, bArr2);
    }

    public static P2.n e(int i7, t tVar, String str) {
        int iH = tVar.h();
        if (tVar.h() == 1684108385 && iH >= 22) {
            tVar.I(10);
            int iB = tVar.B();
            if (iB > 0) {
                String strE = android.hardware.bydauto.ac.tsimpl.a.e(iB, "");
                int iB2 = tVar.B();
                if (iB2 > 0) {
                    strE = strE + "/" + iB2;
                }
                return new P2.n(str, null, I.B(strE));
            }
        }
        AbstractC1346c.z("MetadataUtil", "Failed to parse index/count attribute: " + i2.d.c(i7));
        return null;
    }

    public static int f(t tVar) {
        int iH = tVar.h();
        if (tVar.h() == 1684108385) {
            tVar.I(8);
            int i7 = iH - 16;
            if (i7 == 1) {
                return tVar.v();
            }
            if (i7 == 2) {
                return tVar.B();
            }
            if (i7 == 3) {
                return tVar.y();
            }
            if (i7 == 4 && (tVar.f15241a[tVar.f15242b] & 128) == 0) {
                return tVar.z();
            }
        }
        AbstractC1346c.z("MetadataUtil", "Failed to parse data atom to int");
        return -1;
    }

    public static P2.i g(int i7, String str, t tVar, boolean z6, boolean z10) {
        int iF = f(tVar);
        if (z10) {
            iF = Math.min(1, iF);
        }
        if (iF >= 0) {
            return z6 ? new P2.n(str, null, I.B(Integer.toString(iF))) : new P2.e("und", str, Integer.toString(iF));
        }
        AbstractC1346c.z("MetadataUtil", "Failed to parse uint8 attribute: " + i2.d.c(i7));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [byte[], java.io.Serializable] */
    public static u h(byte[] bArr) {
        t tVar = new t(bArr);
        if (tVar.c < 32) {
            return null;
        }
        tVar.H(0);
        int iA = tVar.a();
        int iH = tVar.h();
        if (iH != iA) {
            AbstractC1346c.z("PsshAtomUtil", "Advertised atom size (" + iH + ") does not match buffer size: " + iA);
            return null;
        }
        int iH2 = tVar.h();
        if (iH2 != 1886614376) {
            AbstractC1347d.p("Atom type is not pssh: ", iH2, "PsshAtomUtil");
            return null;
        }
        int iC = d.c(tVar.h());
        if (iC > 1) {
            AbstractC1347d.p("Unsupported pssh version: ", iC, "PsshAtomUtil");
            return null;
        }
        UUID uuid = new UUID(tVar.p(), tVar.p());
        if (iC == 1) {
            int iZ = tVar.z();
            UUID[] uuidArr = new UUID[iZ];
            for (int i7 = 0; i7 < iZ; i7++) {
                uuidArr[i7] = new UUID(tVar.p(), tVar.p());
            }
        }
        int iZ2 = tVar.z();
        int iA2 = tVar.a();
        if (iZ2 == iA2) {
            ?? r22 = new byte[iZ2];
            tVar.f(0, iZ2, r22);
            return new u(uuid, iC, (Serializable) r22, 4);
        }
        AbstractC1346c.z("PsshAtomUtil", "Atom data size (" + iZ2 + ") does not match the bytes left: " + iA2);
        return null;
    }

    public static byte[] i(UUID uuid, byte[] bArr) {
        u uVarH = h(bArr);
        if (uVarH == null) {
            return null;
        }
        UUID uuid2 = (UUID) uVarH.f736n;
        if (uuid.equals(uuid2)) {
            return (byte[]) uVarH.o;
        }
        AbstractC1346c.z("PsshAtomUtil", "UUID mismatch. Expected: " + uuid + ", got: " + uuid2 + ".");
        return null;
    }

    public static P2.n j(int i7, t tVar, String str) {
        int iH = tVar.h();
        if (tVar.h() == 1684108385) {
            tVar.I(8);
            return new P2.n(str, null, I.B(tVar.r(iH - 16)));
        }
        AbstractC1346c.z("MetadataUtil", "Failed to parse text attribute: " + i2.d.c(i7));
        return null;
    }

    public static B2.J k(B2.s sVar, boolean z6, boolean z10) {
        int i7;
        long j3;
        long jP;
        int i10;
        int i11;
        boolean z11;
        int[] iArr;
        long j10;
        boolean z12 = true;
        long jD = sVar.d();
        long j11 = -1;
        long j12 = 4096;
        if (jD != -1 && jD <= 4096) {
            j12 = jD;
        }
        int i12 = (int) j12;
        t tVar = new t(64);
        int i13 = 0;
        int i14 = 0;
        boolean z13 = false;
        while (i14 < i12) {
            tVar.E(8);
            if (!sVar.k(tVar.f15241a, i13, 8, z12)) {
                break;
            }
            long jX = tVar.x();
            int iH = tVar.h();
            if (jX == 1) {
                sVar.a(8, 8, tVar.f15241a);
                i10 = 16;
                tVar.G(16);
                jP = tVar.p();
                j3 = jD;
            } else {
                if (jX == 0) {
                    long jD2 = sVar.d();
                    if (jD2 != j11) {
                        jX = (jD2 - sVar.l()) + ((long) 8);
                    }
                }
                j3 = jD;
                jP = jX;
                i10 = 8;
            }
            long j13 = i10;
            if (jP < j13) {
                return new j();
            }
            i14 += i10;
            if (iH == 1836019574) {
                i12 += (int) jP;
                if (jD == -1 || i12 <= j3) {
                    j10 = j3;
                } else {
                    j10 = j3;
                    i12 = (int) j10;
                }
                jD = j10;
                z12 = true;
            } else {
                if (iH == 1836019558 || iH == 1836475768) {
                    i7 = 1;
                    break;
                }
                if (iH == 1835295092) {
                    z13 = true;
                }
                if ((((long) i14) + jP) - j13 >= i12) {
                    i7 = 0;
                    break;
                }
                int i15 = (int) (jP - j13);
                i14 += i15;
                if (iH != 1718909296) {
                    i11 = 0;
                    z11 = true;
                    if (i15 != 0) {
                        sVar.m(i15);
                    }
                } else {
                    if (i15 < 8) {
                        return new j();
                    }
                    tVar.E(i15);
                    i11 = 0;
                    sVar.a(0, i15, tVar.f15241a);
                    if (b(tVar.h(), z10)) {
                        z13 = true;
                    }
                    tVar.I(4);
                    int iA = tVar.a() / 4;
                    if (!z13 && iA > 0) {
                        iArr = new int[iA];
                        int i16 = 0;
                        while (true) {
                            if (i16 >= iA) {
                                z11 = true;
                                break;
                            }
                            int iH2 = tVar.h();
                            iArr[i16] = iH2;
                            if (b(iH2, z10)) {
                                z11 = true;
                                z13 = true;
                                break;
                            }
                            i16++;
                        }
                    } else {
                        z11 = true;
                        iArr = null;
                    }
                    if (!z13) {
                        j jVar = new j();
                        if (iArr != null) {
                            C1300a c1300a = C1300a.f14976n;
                            if (iArr.length == 0) {
                                C1300a c1300a2 = C1300a.f14976n;
                            } else {
                                new C1300a(Arrays.copyOf(iArr, iArr.length));
                            }
                        } else {
                            C1300a c1300a3 = C1300a.f14976n;
                        }
                        return jVar;
                    }
                }
                i13 = i11;
                z12 = z11;
                jD = j3;
            }
            j11 = -1;
        }
        i7 = i13;
        if (!z13) {
            return j.c;
        }
        if (z6 != i7) {
            return i7 != 0 ? j.f9382a : j.f9383b;
        }
        return null;
    }
}

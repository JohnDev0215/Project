package com.google.protobuf;

import com.google.protobuf.c0;
import com.google.protobuf.e;
import com.google.protobuf.k0;
import com.google.protobuf.n1;
import defpackage.C0175b;
import defpackage.a72;
import defpackage.bm1;
import defpackage.dj3;
import defpackage.hm0;
import defpackage.lk;
import defpackage.nu;
import defpackage.sk3;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: MessageSchema.java */
/* JADX INFO: loaded from: classes.dex */
public final class v0<T> implements b1<T> {
    public static final int[] r = new int[0];
    public static final Unsafe s = dj3.p();
    public final int[] a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final r0 e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int k;
    public final int l;
    public final bm1 m;
    public final h0 n;
    public final i1<?, ?> o;
    public final t<?> p;
    public final n0 q;

    public v0(int[] iArr, Object[] objArr, int i, int i2, r0 r0Var, boolean z, boolean z2, int[] iArr2, int i3, int i4, bm1 bm1Var, h0 h0Var, i1<?, ?> i1Var, t<?> tVar, n0 n0Var) {
        this.a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = r0Var instanceof z;
        this.h = z;
        this.f = tVar != null && tVar.e(r0Var);
        this.i = z2;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = bm1Var;
        this.n = h0Var;
        this.o = i1Var;
        this.p = tVar;
        this.e = r0Var;
        this.q = n0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:161:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0383  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> com.google.protobuf.v0<T> A(defpackage.a92 r34, defpackage.bm1 r35, com.google.protobuf.h0 r36, com.google.protobuf.i1<?, ?> r37, com.google.protobuf.t<?> r38, com.google.protobuf.n0 r39) {
        /*
            Method dump skipped, instruction units count: 1010
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.A(a92, bm1, com.google.protobuf.h0, com.google.protobuf.i1, com.google.protobuf.t, com.google.protobuf.n0):com.google.protobuf.v0");
    }

    public static long B(int i) {
        return i & 1048575;
    }

    public static int C(long j, Object obj) {
        return ((Integer) dj3.o(j, obj)).intValue();
    }

    public static long D(long j, Object obj) {
        return ((Long) dj3.o(j, obj)).longValue();
    }

    public static Field N(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder sbN = C0175b.n("Field ", str, " for ");
            sbN.append(cls.getName());
            sbN.append(" not found. Known fields are ");
            sbN.append(Arrays.toString(declaredFields));
            throw new RuntimeException(sbN.toString());
        }
    }

    public static void U(int i, Object obj, i iVar) {
        if (!(obj instanceof String)) {
            iVar.b(i, (lk) obj);
        } else {
            iVar.a.K0(i, (String) obj);
        }
    }

    public static int l(byte[] bArr, int i, int i2, n1.a aVar, Class cls, e.a aVar2) {
        switch (aVar.ordinal()) {
            case 0:
                aVar2.c = Double.valueOf(Double.longBitsToDouble(e.i(i, bArr)));
                return i + 8;
            case 1:
                aVar2.c = Float.valueOf(Float.intBitsToFloat(e.g(i, bArr)));
                return i + 4;
            case 2:
            case 3:
                int iJ = e.J(bArr, i, aVar2);
                aVar2.c = Long.valueOf(aVar2.b);
                return iJ;
            case 4:
            case 12:
            case 13:
                int iH = e.H(bArr, i, aVar2);
                aVar2.c = Integer.valueOf(aVar2.a);
                return iH;
            case 5:
            case 15:
                aVar2.c = Long.valueOf(e.i(i, bArr));
                return i + 8;
            case 6:
            case 14:
                aVar2.c = Integer.valueOf(e.g(i, bArr));
                return i + 4;
            case 7:
                int iJ2 = e.J(bArr, i, aVar2);
                aVar2.c = Boolean.valueOf(aVar2.b != 0);
                return iJ2;
            case 8:
                return e.E(bArr, i, aVar2);
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                return e.o(a72.c.a(cls), bArr, i, i2, aVar2);
            case 11:
                return e.b(bArr, i, aVar2);
            case 16:
                int iH2 = e.H(bArr, i, aVar2);
                aVar2.c = Integer.valueOf(g.c(aVar2.a));
                return iH2;
            case 17:
                int iJ3 = e.J(bArr, i, aVar2);
                aVar2.c = Long.valueOf(g.d(aVar2.b));
                return iJ3;
        }
    }

    public static k1 q(Object obj) {
        z zVar = (z) obj;
        k1 k1Var = zVar.unknownFields;
        if (k1Var != k1.f) {
            return k1Var;
        }
        k1 k1Var2 = new k1();
        zVar.unknownFields = k1Var2;
        return k1Var2;
    }

    public static List v(long j, Object obj) {
        return (List) dj3.o(j, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x017e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.protobuf.v0 z(defpackage.zg1 r29, defpackage.bm1 r30, com.google.protobuf.h0 r31, com.google.protobuf.i1 r32, com.google.protobuf.t r33, com.google.protobuf.n0 r34) {
        /*
            Method dump skipped, instruction units count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.z(zg1, bm1, com.google.protobuf.h0, com.google.protobuf.i1, com.google.protobuf.t, com.google.protobuf.n0):com.google.protobuf.v0");
    }

    public final <K, V> int E(T t, byte[] bArr, int i, int i2, int i3, long j, e.a aVar) throws d0 {
        Object objO = o(i3);
        Unsafe unsafe = s;
        Object object = unsafe.getObject(t, j);
        n0 n0Var = this.q;
        if (n0Var.g(object)) {
            Object objF = n0Var.f(objO);
            n0Var.a(objF, object);
            unsafe.putObject(t, j, objF);
            object = objF;
        }
        k0.a<?, ?> aVarC = n0Var.c(objO);
        Map<?, ?> mapD = n0Var.d(object);
        int iH = e.H(bArr, i, aVar);
        int i4 = aVar.a;
        if (i4 < 0 || i4 > i2 - iH) {
            throw d0.k();
        }
        int i5 = i4 + iH;
        K k = aVarC.b;
        Object obj = aVarC.d;
        Object obj2 = obj;
        while (iH < i5) {
            int iG = iH + 1;
            int i6 = bArr[iH];
            if (i6 < 0) {
                iG = e.G(i6, bArr, iG, aVar);
                i6 = aVar.a;
            }
            int i7 = iG;
            int i8 = i6 >>> 3;
            int i9 = i6 & 7;
            if (i8 != 1) {
                if (i8 == 2) {
                    n1.a aVar2 = aVarC.c;
                    if (i9 == aVar2.f) {
                        iH = l(bArr, i7, i2, aVar2, obj.getClass(), aVar);
                        obj2 = aVar.c;
                    }
                }
                iH = e.L(i6, bArr, i7, i2, aVar);
            } else {
                n1.a aVar3 = aVarC.a;
                if (i9 == aVar3.f) {
                    iH = l(bArr, i7, i2, aVar3, null, aVar);
                    k = (K) aVar.c;
                } else {
                    iH = e.L(i6, bArr, i7, i2, aVar);
                }
            }
        }
        if (iH != i5) {
            throw d0.h();
        }
        mapD.put(k, obj2);
        return i5;
    }

    public final int F(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, e.a aVar) throws d0 {
        long j2 = this.a[i8 + 2] & 1048575;
        Unsafe unsafe = s;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(e.d(i, bArr)));
                int i9 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i9;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(e.k(i, bArr)));
                int i10 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i10;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iJ = e.J(bArr, i, aVar);
                unsafe.putObject(t, j, Long.valueOf(aVar.b));
                unsafe.putInt(t, j2, i4);
                return iJ;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iH = e.H(bArr, i, aVar);
                unsafe.putObject(t, j, Integer.valueOf(aVar.a));
                unsafe.putInt(t, j2, i4);
                return iH;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(e.i(i, bArr)));
                int i11 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i11;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(e.g(i, bArr)));
                int i12 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i12;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iJ2 = e.J(bArr, i, aVar);
                unsafe.putObject(t, j, Boolean.valueOf(aVar.b != 0));
                unsafe.putInt(t, j2, i4);
                return iJ2;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iH2 = e.H(bArr, i, aVar);
                int i13 = aVar.a;
                if (i13 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & 536870912) != 0 && !sk3.g(bArr, iH2, iH2 + i13)) {
                        throw d0.c();
                    }
                    unsafe.putObject(t, j, new String(bArr, iH2, i13, c0.a));
                    iH2 += i13;
                }
                unsafe.putInt(t, j2, i4);
                return iH2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iO = e.o(p(i8), bArr, i, i2, aVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, aVar.c);
                } else {
                    unsafe.putObject(t, j, c0.c(object, aVar.c));
                }
                unsafe.putInt(t, j2, i4);
                return iO;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iB = e.b(bArr, i, aVar);
                unsafe.putObject(t, j, aVar.c);
                unsafe.putInt(t, j2, i4);
                return iB;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iH3 = e.H(bArr, i, aVar);
                int i14 = aVar.a;
                c0.b bVarN = n(i8);
                if (bVarN == null || bVarN.a(i14)) {
                    unsafe.putObject(t, j, Integer.valueOf(i14));
                    unsafe.putInt(t, j2, i4);
                } else {
                    q(t).b(i3, Long.valueOf(i14));
                }
                return iH3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iH4 = e.H(bArr, i, aVar);
                unsafe.putObject(t, j, Integer.valueOf(g.c(aVar.a)));
                unsafe.putInt(t, j2, i4);
                return iH4;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                int iJ3 = e.J(bArr, i, aVar);
                unsafe.putObject(t, j, Long.valueOf(g.d(aVar.b)));
                unsafe.putInt(t, j2, i4);
                return iJ3;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                int iM = e.m(p(i8), bArr, i, i2, (i3 & (-8)) | 4, aVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, aVar.c);
                } else {
                    unsafe.putObject(t, j, c0.c(object2, aVar.c));
                }
                unsafe.putInt(t, j2, i4);
                return iM;
            default:
                return i;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x00a4. Please report as an issue. */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final int G(T t, byte[] bArr, int i, int i2, int i3, e.a aVar) {
        Unsafe unsafe;
        v0<T> v0Var;
        Object obj;
        int i4;
        int iQ;
        int iQ2;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        byte[] bArr2;
        int i16;
        int iH;
        int iJ;
        byte[] bArr3;
        int iB;
        int iJ2;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        v0<T> v0Var2 = this;
        T t2 = t;
        byte[] bArr4 = bArr;
        int i25 = i2;
        e.a aVar2 = aVar;
        Unsafe unsafe2 = s;
        int iF = i;
        int i26 = i3;
        int i27 = -1;
        int i28 = 0;
        int i29 = 0;
        int i30 = 1048575;
        int i31 = 0;
        while (true) {
            if (iF < i25) {
                int iG = iF + 1;
                int i32 = bArr4[iF];
                if (i32 < 0) {
                    iG = e.G(i32, bArr4, iG, aVar2);
                    i32 = aVar2.a;
                }
                int i33 = i32 >>> 3;
                int i34 = i32 & 7;
                int i35 = v0Var2.d;
                int i36 = i32;
                int i37 = v0Var2.c;
                int i38 = i26;
                if (i33 > i27) {
                    iQ2 = (i33 < i37 || i33 > i35) ? -1 : v0Var2.Q(i33, i28 / 3);
                    i5 = -1;
                    i4 = 0;
                } else {
                    if (i33 < i37 || i33 > i35) {
                        i4 = 0;
                        iQ = -1;
                    } else {
                        i4 = 0;
                        iQ = v0Var2.Q(i33, 0);
                    }
                    iQ2 = iQ;
                    i5 = -1;
                }
                if (iQ2 == i5) {
                    i6 = i36 == true ? 1 : 0;
                    i7 = iG;
                    i8 = i30;
                    i9 = i31;
                    i10 = i33;
                    unsafe = unsafe2;
                    i11 = i38;
                } else {
                    int[] iArr = v0Var2.a;
                    int i39 = iArr[iQ2 + 1];
                    int i40 = (i39 & 267386880) >>> 20;
                    long j = i39 & 1048575;
                    if (i40 <= 17) {
                        int i41 = iArr[iQ2 + 2];
                        int i42 = 1 << (i41 >>> 20);
                        int i43 = i41 & 1048575;
                        if (i43 != i30) {
                            if (i30 != 1048575) {
                                unsafe2.putInt(t2, i30, i31);
                            }
                            i9 = unsafe2.getInt(t2, i43);
                            i12 = i43;
                        } else {
                            i9 = i31;
                            i12 = i30;
                        }
                        switch (i40) {
                            case 0:
                                i13 = i36 == true ? 1 : 0;
                                i14 = i12;
                                i15 = i33;
                                bArr2 = bArr;
                                i16 = iQ2;
                                if (i34 != 1) {
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    dj3.v(j, t2, e.d(iG, bArr2));
                                    iH = iG + 8;
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 1:
                                i13 = i36 == true ? 1 : 0;
                                i14 = i12;
                                i15 = i33;
                                bArr2 = bArr;
                                i16 = iQ2;
                                if (i34 != 5) {
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    dj3.w(e.k(iG, bArr2), j, t2);
                                    iH = iG + 4;
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 2:
                            case 3:
                                i13 = i36 == true ? 1 : 0;
                                i14 = i12;
                                i15 = i33;
                                bArr2 = bArr;
                                i16 = iQ2;
                                if (i34 != 0) {
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iJ = e.J(bArr2, iG, aVar2);
                                    unsafe2.putLong(t, j, aVar2.b);
                                    i19 = i9 | i42;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 4:
                            case 11:
                                i13 = i36 == true ? 1 : 0;
                                i14 = i12;
                                i15 = i33;
                                bArr2 = bArr;
                                i16 = iQ2;
                                if (i34 != 0) {
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iH = e.H(bArr2, iG, aVar2);
                                    unsafe2.putInt(t2, j, aVar2.a);
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 5:
                            case 14:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 1) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    unsafe2.putLong(t, j, e.i(iG, bArr3));
                                    iH = iG + 8;
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 6:
                            case 13:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 5) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    unsafe2.putInt(t2, j, e.g(iG, bArr3));
                                    iB = iG + 4;
                                    iJ2 = iB;
                                    i17 = i9 | i42;
                                    i18 = i17;
                                    iF = iJ2;
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 7:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 0) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iJ2 = e.J(bArr3, iG, aVar2);
                                    dj3.r(j, t2, aVar2.b != 0);
                                    i17 = i9 | i42;
                                    i18 = i17;
                                    iF = iJ2;
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 8:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 2) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iB = (i39 & 536870912) == 0 ? e.B(bArr3, iG, aVar2) : e.E(bArr3, iG, aVar2);
                                    unsafe2.putObject(t2, j, aVar2.c);
                                    iJ2 = iB;
                                    i17 = i9 | i42;
                                    i18 = i17;
                                    iF = iJ2;
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 9:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 2) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iB = e.o(v0Var2.p(i16), bArr3, iG, i2, aVar2);
                                    if ((i9 & i42) == 0) {
                                        unsafe2.putObject(t2, j, aVar2.c);
                                    } else {
                                        unsafe2.putObject(t2, j, c0.c(unsafe2.getObject(t2, j), aVar2.c));
                                    }
                                    iJ2 = iB;
                                    i17 = i9 | i42;
                                    i18 = i17;
                                    iF = iJ2;
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 10:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 2) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iH = e.b(bArr3, iG, aVar2);
                                    unsafe2.putObject(t2, j, aVar2.c);
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 12:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 0) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iH = e.H(bArr3, iG, aVar2);
                                    int i44 = aVar2.a;
                                    c0.b bVarN = v0Var2.n(i16);
                                    if (bVarN == null || bVarN.a(i44)) {
                                        unsafe2.putInt(t2, j, i44);
                                        i14 = i12;
                                        bArr2 = bArr3;
                                        i9 |= i42;
                                        iJ = iH;
                                        i19 = i9;
                                        i18 = i19;
                                        iF = iJ;
                                        i27 = i15;
                                        bArr4 = bArr2;
                                        i28 = i16;
                                        i29 = i13;
                                        i30 = i14;
                                        i25 = i2;
                                        i31 = i18;
                                        i26 = i3;
                                    } else {
                                        q(t).b(i13 == true ? 1 : 0, Long.valueOf(i44));
                                        i14 = i12;
                                        bArr2 = bArr3;
                                        iJ = iH;
                                        i19 = i9;
                                        i18 = i19;
                                        iF = iJ;
                                        i27 = i15;
                                        bArr4 = bArr2;
                                        i28 = i16;
                                        i29 = i13;
                                        i30 = i14;
                                        i25 = i2;
                                        i31 = i18;
                                        i26 = i3;
                                    }
                                }
                                break;
                            case 15:
                                bArr3 = bArr;
                                i13 = i36 == true ? 1 : 0;
                                i15 = i33;
                                i16 = iQ2;
                                if (i34 != 0) {
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iH = e.H(bArr3, iG, aVar2);
                                    unsafe2.putInt(t2, j, g.c(aVar2.a));
                                    i14 = i12;
                                    bArr2 = bArr3;
                                    i9 |= i42;
                                    iJ = iH;
                                    i19 = i9;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 16:
                                i13 = i36 == true ? 1 : 0;
                                int i45 = iQ2;
                                if (i34 != 0) {
                                    i15 = i33;
                                    i16 = i45;
                                    i14 = i12;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    iJ = e.J(bArr, iG, aVar2);
                                    i15 = i33;
                                    i16 = i45;
                                    unsafe2.putLong(t, j, g.d(aVar2.b));
                                    i14 = i12;
                                    bArr2 = bArr;
                                    i19 = i9 | i42;
                                    i18 = i19;
                                    iF = iJ;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            case 17:
                                if (i34 != 3) {
                                    i13 = i36 == true ? 1 : 0;
                                    i14 = i12;
                                    i15 = i33;
                                    i16 = iQ2;
                                    i10 = i15;
                                    i7 = iG;
                                    i4 = i16;
                                    unsafe = unsafe2;
                                    i6 = i13;
                                    i8 = i14;
                                    i11 = i3;
                                } else {
                                    b1 b1VarP = v0Var2.p(iQ2);
                                    i13 = i36 == true ? 1 : 0;
                                    int i46 = iQ2;
                                    iF = e.m(b1VarP, bArr, iG, i2, (i33 << 3) | 4, aVar);
                                    if ((i9 & i42) == 0) {
                                        unsafe2.putObject(t2, j, aVar2.c);
                                    } else {
                                        unsafe2.putObject(t2, j, c0.c(unsafe2.getObject(t2, j), aVar2.c));
                                    }
                                    i18 = i9 | i42;
                                    i14 = i12;
                                    i15 = i33;
                                    i16 = i46;
                                    bArr2 = bArr;
                                    i27 = i15;
                                    bArr4 = bArr2;
                                    i28 = i16;
                                    i29 = i13;
                                    i30 = i14;
                                    i25 = i2;
                                    i31 = i18;
                                    i26 = i3;
                                }
                                break;
                            default:
                                i13 = i36 == true ? 1 : 0;
                                i14 = i12;
                                i15 = i33;
                                i16 = iQ2;
                                i10 = i15;
                                i7 = iG;
                                i4 = i16;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i14;
                                i11 = i3;
                                break;
                        }
                    } else {
                        int i47 = i31;
                        int i48 = iQ2;
                        if (i40 != 27) {
                            i8 = i30;
                            i20 = i47;
                            if (i40 <= 49) {
                                int i49 = iG;
                                i10 = i33;
                                i22 = i48;
                                unsafe = unsafe2;
                                i23 = i36 == true ? 1 : 0;
                                iF = I(t, bArr, iG, i2, i36 == true ? 1 : 0, i33, i34, i22, i39, i40, j, aVar);
                                if (iF != i49) {
                                    v0Var2 = this;
                                    t2 = t;
                                    bArr4 = bArr;
                                    i29 = i23;
                                    i25 = i2;
                                    i26 = i3;
                                    aVar2 = aVar;
                                    i27 = i10;
                                    i28 = i22;
                                    i30 = i8;
                                    i31 = i20;
                                    unsafe2 = unsafe;
                                } else {
                                    i24 = iF;
                                }
                            } else {
                                i10 = i33;
                                i21 = iG;
                                unsafe = unsafe2;
                                i23 = i36 == true ? 1 : 0;
                                i22 = i48;
                                if (i40 != 50) {
                                    iF = F(t, bArr, i21, i2, i23 == true ? 1 : 0, i10, i34, i39, i40, j, i22, aVar);
                                    if (iF != i21) {
                                        v0Var2 = this;
                                        t2 = t;
                                        bArr4 = bArr;
                                        i29 = i23;
                                        i25 = i2;
                                        i26 = i3;
                                        aVar2 = aVar;
                                        i27 = i10;
                                        i28 = i22;
                                        i30 = i8;
                                        i31 = i20;
                                        unsafe2 = unsafe;
                                    } else {
                                        i24 = iF;
                                    }
                                } else if (i34 == 2) {
                                    iF = E(t, bArr, i21, i2, i22, j, aVar);
                                    if (iF != i21) {
                                        v0Var2 = this;
                                        t2 = t;
                                        bArr4 = bArr;
                                        i29 = i23;
                                        i25 = i2;
                                        i26 = i3;
                                        aVar2 = aVar;
                                        i27 = i10;
                                        i28 = i22;
                                        i30 = i8;
                                        i31 = i20;
                                        unsafe2 = unsafe;
                                    } else {
                                        i24 = iF;
                                    }
                                } else {
                                    i24 = i21;
                                }
                            }
                        } else if (i34 == 2) {
                            c0.e eVarH = (c0.e) unsafe2.getObject(t2, j);
                            if (!eVarH.o()) {
                                int size = eVarH.size();
                                eVarH = eVarH.h(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(t2, j, eVarH);
                            }
                            iF = e.p(v0Var2.p(i48), i36 == true ? 1 : 0, bArr, iG, i2, eVarH, aVar);
                            bArr4 = bArr;
                            i27 = i33;
                            i26 = i3;
                            i28 = i48;
                            i29 = i36 == true ? 1 : 0;
                            i30 = i30;
                            i31 = i47;
                            i25 = i2;
                        } else {
                            i8 = i30;
                            i20 = i47;
                            i10 = i33;
                            i21 = iG;
                            i22 = i48;
                            unsafe = unsafe2;
                            i23 = i36 == true ? 1 : 0;
                            i24 = i21;
                        }
                        i6 = i23;
                        i11 = i3;
                        i7 = i24;
                        i4 = i22;
                        i9 = i20;
                    }
                }
                if (i6 != i11 || i11 == 0) {
                    aVar2 = aVar;
                    iF = (!this.f || aVar2.d == s.a()) ? e.F(i6 == true ? 1 : 0, bArr, i7, i2, q(t), aVar) : e.f(i6 == true ? 1 : 0, bArr, i7, i2, t, this.e, aVar);
                    t2 = t;
                    i25 = i2;
                    i26 = i11;
                    i29 = i6;
                    v0Var2 = this;
                    i28 = i4;
                    i31 = i9;
                    i27 = i10;
                    i30 = i8;
                    unsafe2 = unsafe;
                    bArr4 = bArr;
                } else {
                    v0Var = this;
                    iF = i7;
                    i26 = i11;
                    i29 = i6;
                    i31 = i9;
                    i30 = i8;
                }
            } else {
                unsafe = unsafe2;
                v0Var = v0Var2;
            }
        }
        if (i30 != 1048575) {
            long j2 = i30;
            obj = t;
            unsafe.putInt(obj, j2, i31);
        } else {
            obj = t;
        }
        k1 k1Var = null;
        int i50 = v0Var.k;
        while (true) {
            int i51 = v0Var.l;
            i1 i1Var = v0Var.o;
            if (i50 >= i51) {
                if (k1Var != null) {
                    i1Var.n(obj, k1Var);
                }
                if (i26 == 0) {
                    if (iF != i2) {
                        throw d0.h();
                    }
                } else if (iF > i2 || i29 != i26) {
                    throw d0.h();
                }
                return iF;
            }
            k1Var = (k1) v0Var.m(obj, v0Var.j[i50], k1Var, i1Var);
            i50++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x0264, code lost:
    
        if (r0 != r32) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0268, code lost:
    
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x029e, code lost:
    
        if (r0 != r15) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02bd, code lost:
    
        if (r0 != r15) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x02bf, code lost:
    
        r5 = r17;
        r2 = r18;
        r6 = r25;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x00a3. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H(java.lang.Object r30, byte[] r31, int r32, int r33, com.google.protobuf.e.a r34) throws com.google.protobuf.d0 {
        /*
            Method dump skipped, instruction units count: 820
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.H(java.lang.Object, byte[], int, int, com.google.protobuf.e$a):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int I(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, e.a aVar) throws d0 {
        int I;
        Unsafe unsafe = s;
        c0.e eVarH = (c0.e) unsafe.getObject(t, j2);
        if (!eVarH.o()) {
            int size = eVarH.size();
            eVarH = eVarH.h(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j2, eVarH);
        }
        switch (i7) {
            case 18:
            case 35:
                return i5 == 2 ? e.r(bArr, i, eVarH, aVar) : i5 == 1 ? e.e(i3, bArr, i, i2, eVarH, aVar) : i;
            case 19:
            case 36:
                return i5 == 2 ? e.u(bArr, i, eVarH, aVar) : i5 == 5 ? e.l(i3, bArr, i, i2, eVarH, aVar) : i;
            case 20:
            case 21:
            case 37:
            case 38:
                return i5 == 2 ? e.y(bArr, i, eVarH, aVar) : i5 == 0 ? e.K(i3, bArr, i, i2, eVarH, aVar) : i;
            case 22:
            case 29:
            case 39:
            case 43:
                return i5 == 2 ? e.x(bArr, i, eVarH, aVar) : i5 == 0 ? e.I(i3, bArr, i, i2, eVarH, aVar) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                return i5 == 2 ? e.t(bArr, i, eVarH, aVar) : i5 == 1 ? e.j(i3, bArr, i, i2, eVarH, aVar) : i;
            case 24:
            case 31:
            case 41:
            case 45:
                return i5 == 2 ? e.s(bArr, i, eVarH, aVar) : i5 == 5 ? e.h(i3, bArr, i, i2, eVarH, aVar) : i;
            case 25:
            case 42:
                return i5 == 2 ? e.q(bArr, i, eVarH, aVar) : i5 == 0 ? e.a(i3, bArr, i, i2, eVarH, aVar) : i;
            case 26:
                return i5 == 2 ? (j & 536870912) == 0 ? e.C(i3, bArr, i, i2, eVarH, aVar) : e.D(i3, bArr, i, i2, eVarH, aVar) : i;
            case 27:
                return i5 == 2 ? e.p(p(i6), i3, bArr, i, i2, eVarH, aVar) : i;
            case 28:
                return i5 == 2 ? e.c(i3, bArr, i, i2, eVarH, aVar) : i;
            case 30:
            case 44:
                if (i5 == 2) {
                    I = e.x(bArr, i, eVarH, aVar);
                } else {
                    if (i5 != 0) {
                        return i;
                    }
                    I = e.I(i3, bArr, i, i2, eVarH, aVar);
                }
                z zVar = (z) t;
                k1 k1Var = zVar.unknownFields;
                if (k1Var == k1.f) {
                    k1Var = null;
                }
                k1 k1Var2 = (k1) c1.z(i4, eVarH, n(i6), k1Var, this.o);
                if (k1Var2 != null) {
                    zVar.unknownFields = k1Var2;
                }
                return I;
            case 33:
            case 47:
                return i5 == 2 ? e.v(bArr, i, eVarH, aVar) : i5 == 0 ? e.z(i3, bArr, i, i2, eVarH, aVar) : i;
            case 34:
            case 48:
                return i5 == 2 ? e.w(bArr, i, eVarH, aVar) : i5 == 0 ? e.A(i3, bArr, i, i2, eVarH, aVar) : i;
            case 49:
                return i5 == 3 ? e.n(p(i6), i3, bArr, i, i2, eVarH, aVar) : i;
            default:
                return i;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final <E> void J(Object obj, long j, z0 z0Var, b1<E> b1Var, s sVar) {
        z0Var.f(this.n.c(j, obj), b1Var, sVar);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final <E> void K(Object obj, int i, z0 z0Var, b1<E> b1Var, s sVar) {
        z0Var.j(this.n.c(i & 1048575, obj), b1Var, sVar);
    }

    public final void L(Object obj, int i, z0 z0Var) {
        if ((536870912 & i) != 0) {
            dj3.z(i & 1048575, obj, z0Var.M());
        } else if (this.g) {
            dj3.z(i & 1048575, obj, z0Var.x());
        } else {
            dj3.z(i & 1048575, obj, z0Var.D());
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void M(Object obj, int i, z0 z0Var) {
        boolean z = (536870912 & i) != 0;
        h0 h0Var = this.n;
        if (z) {
            z0Var.C(h0Var.c(i & 1048575, obj));
        } else {
            z0Var.z(h0Var.c(i & 1048575, obj));
        }
    }

    public final void O(int i, Object obj) {
        int i2 = this.a[i + 2];
        long j = 1048575 & i2;
        if (j == 1048575) {
            return;
        }
        dj3.x(j, (1 << (i2 >>> 20)) | dj3.m(j, obj), obj);
    }

    public final void P(int i, int i2, Object obj) {
        dj3.x(this.a[i2 + 2] & 1048575, i, obj);
    }

    public final int Q(int i, int i2) {
        int[] iArr = this.a;
        int length = (iArr.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = iArr[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final int R(int i) {
        return this.a[i + 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void S(java.lang.Object r21, com.google.protobuf.i r22) {
        /*
            Method dump skipped, instruction units count: 1412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.S(java.lang.Object, com.google.protobuf.i):void");
    }

    public final void T(i iVar, int i, Object obj, int i2) {
        if (obj != null) {
            Object objO = o(i2);
            n0 n0Var = this.q;
            k0.a<?, ?> aVarC = n0Var.c(objO);
            Map<?, ?> mapH = n0Var.h(obj);
            nu nuVar = iVar.a;
            nuVar.getClass();
            for (Map.Entry<?, ?> entry : mapH.entrySet()) {
                nuVar.M0(i, 2);
                nuVar.O0(k0.a(aVarC, entry.getKey(), entry.getValue()));
                k0.b(nuVar, aVarC, entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // com.google.protobuf.b1
    public final void a(T t, T t2) {
        t2.getClass();
        int i = 0;
        while (true) {
            int[] iArr = this.a;
            if (i >= iArr.length) {
                Class<?> cls = c1.a;
                i1<?, ?> i1Var = this.o;
                i1Var.o(t, i1Var.k(i1Var.g(t), i1Var.g(t2)));
                if (this.f) {
                    t<?> tVar = this.p;
                    x<T> xVarC = tVar.c(t2);
                    if (xVarC.o()) {
                        return;
                    }
                    tVar.d(t).u(xVarC);
                    return;
                }
                return;
            }
            int iR = R(i);
            long j = 1048575 & iR;
            int i2 = iArr[i];
            switch ((iR & 267386880) >>> 20) {
                case 0:
                    if (t(i, t2)) {
                        dj3.v(j, t, dj3.k(j, t2));
                        O(i, t);
                    }
                    break;
                case 1:
                    if (t(i, t2)) {
                        dj3.w(dj3.l(j, t2), j, t);
                        O(i, t);
                    }
                    break;
                case 2:
                    if (t(i, t2)) {
                        dj3.y(j, dj3.n(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 3:
                    if (t(i, t2)) {
                        dj3.y(j, dj3.n(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 4:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 5:
                    if (t(i, t2)) {
                        dj3.y(j, dj3.n(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 6:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 7:
                    if (t(i, t2)) {
                        dj3.r(j, t, dj3.g(j, t2));
                        O(i, t);
                    }
                    break;
                case 8:
                    if (t(i, t2)) {
                        dj3.z(j, t, dj3.o(j, t2));
                        O(i, t);
                    }
                    break;
                case 9:
                    x(t, i, t2);
                    break;
                case 10:
                    if (t(i, t2)) {
                        dj3.z(j, t, dj3.o(j, t2));
                        O(i, t);
                    }
                    break;
                case 11:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 12:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 13:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 14:
                    if (t(i, t2)) {
                        dj3.y(j, dj3.n(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 15:
                    if (t(i, t2)) {
                        dj3.x(j, dj3.m(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 16:
                    if (t(i, t2)) {
                        dj3.y(j, dj3.n(j, t2), t);
                        O(i, t);
                    }
                    break;
                case 17:
                    x(t, i, t2);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.n.b(j, t, t2);
                    break;
                case 50:
                    Class<?> cls2 = c1.a;
                    dj3.z(j, t, this.q.a(dj3.o(j, t), dj3.o(j, t2)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (u(i2, i, t2)) {
                        dj3.z(j, t, dj3.o(j, t2));
                        P(i2, i, t);
                    }
                    break;
                case 60:
                    y(t, i, t2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (u(i2, i, t2)) {
                        dj3.z(j, t, dj3.o(j, t2));
                        P(i2, i, t);
                    }
                    break;
                case 68:
                    y(t, i, t2);
                    break;
            }
            i += 3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    @Override // com.google.protobuf.b1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.lang.Object r18, com.google.protobuf.i r19) {
        /*
            Method dump skipped, instruction units count: 1564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.b(java.lang.Object, com.google.protobuf.i):void");
    }

    @Override // com.google.protobuf.b1
    public final void c(T t) {
        int[] iArr;
        int i;
        int i2 = this.k;
        while (true) {
            iArr = this.j;
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long jR = R(iArr[i2]) & 1048575;
            Object objO = dj3.o(jR, t);
            if (objO != null) {
                dj3.z(jR, t, this.q.b(objO));
            }
            i2++;
        }
        int length = iArr.length;
        while (i < length) {
            this.n.a(iArr[i], t);
            i++;
        }
        this.o.j(t);
        if (this.f) {
            this.p.f(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d2  */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.protobuf.b1] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.google.protobuf.b1] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.google.protobuf.b1] */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20, types: [com.google.protobuf.b1] */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    @Override // com.google.protobuf.b1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(T r14) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.d(java.lang.Object):boolean");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v35 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    @Override // com.google.protobuf.b1
    public final void e(T r20, com.google.protobuf.z0 r21, com.google.protobuf.s r22) {
        /*
            Method dump skipped, instruction units count: 1660
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.e(java.lang.Object, com.google.protobuf.z0, com.google.protobuf.s):void");
    }

    @Override // com.google.protobuf.b1
    public final void f(T t, byte[] bArr, int i, int i2, e.a aVar) throws d0 {
        if (this.h) {
            H(t, bArr, i, i2, aVar);
        } else {
            G(t, bArr, i, i2, 0, aVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01c8  */
    @Override // com.google.protobuf.b1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean g(T r11, T r12) {
        /*
            Method dump skipped, instruction units count: 644
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.g(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.protobuf.b1
    public final int h(T t) {
        return this.h ? s(t) : r(t);
    }

    @Override // com.google.protobuf.b1
    public final T i() {
        return (T) this.m.a(this.e);
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x01d2  */
    @Override // com.google.protobuf.b1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j(T r10) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.v0.j(java.lang.Object):int");
    }

    public final boolean k(Object obj, int i, Object obj2) {
        return t(i, obj) == t(i, obj2);
    }

    public final <UT, UB> UB m(Object obj, int i, UB ub, i1<UT, UB> i1Var) {
        c0.b bVarN;
        int i2 = this.a[i];
        Object objO = dj3.o(R(i) & 1048575, obj);
        if (objO == null || (bVarN = n(i)) == null) {
            return ub;
        }
        n0 n0Var = this.q;
        Map<?, ?> mapD = n0Var.d(objO);
        k0.a<?, ?> aVarC = n0Var.c(o(i));
        Iterator<Map.Entry<?, ?>> it = mapD.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<?, ?> next = it.next();
            if (!bVarN.a(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = i1Var.m();
                }
                int iA = k0.a(aVarC, next.getKey(), next.getValue());
                byte[] bArr = new byte[iA];
                boolean z = nu.l;
                nu.a aVar = new nu.a(bArr, iA);
                try {
                    k0.b(aVar, aVarC, next.getKey(), next.getValue());
                    if (aVar.R0() != 0) {
                        throw new IllegalStateException("Did not write as much data as expected.");
                    }
                    i1Var.d(ub, i2, new lk.f(bArr));
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final c0.b n(int i) {
        return (c0.b) this.b[((i / 3) * 2) + 1];
    }

    public final Object o(int i) {
        return this.b[(i / 3) * 2];
    }

    public final b1 p(int i) {
        int i2 = (i / 3) * 2;
        Object[] objArr = this.b;
        b1 b1Var = (b1) objArr[i2];
        if (b1Var != null) {
            return b1Var;
        }
        b1<T> b1VarA = a72.c.a((Class) objArr[i2 + 1]);
        objArr[i2] = b1VarA;
        return b1VarA;
    }

    public final int r(T t) {
        int i;
        int i2;
        int iM;
        int iK;
        int i3;
        int iI0;
        int iK0;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i6 >= iArr.length) {
                i1<?, ?> i1Var = this.o;
                int iH = i1Var.h(i1Var.g(t)) + i7;
                return this.f ? iH + this.p.c(t).m() : iH;
            }
            int iR = R(i6);
            int i9 = iArr[i6];
            int i10 = (267386880 & iR) >>> 20;
            boolean z = this.i;
            Unsafe unsafe = s;
            if (i10 <= 17) {
                i = iArr[i6 + 2];
                int i11 = i & i4;
                i2 = 1 << (i >>> 20);
                if (i11 != i5) {
                    i8 = unsafe.getInt(t, i11);
                    i5 = i11;
                }
            } else {
                i = (!z || i10 < hm0.Q.a() || i10 > hm0.d0.a()) ? 0 : iArr[i6 + 2] & i4;
                i2 = 0;
            }
            long j = iR & i4;
            switch (i10) {
                case 0:
                    if ((i8 & i2) != 0) {
                        iM = nu.M(i9);
                        i7 += iM;
                    }
                    break;
                case 1:
                    if ((i8 & i2) != 0) {
                        iM = nu.Q(i9);
                        i7 += iM;
                    }
                    break;
                case 2:
                    if ((i8 & i2) != 0) {
                        iM = nu.U(i9, unsafe.getLong(t, j));
                        i7 += iM;
                    }
                    break;
                case 3:
                    if ((i8 & i2) != 0) {
                        iM = nu.m0(i9, unsafe.getLong(t, j));
                        i7 += iM;
                    }
                    break;
                case 4:
                    if ((i8 & i2) != 0) {
                        iM = nu.S(i9, unsafe.getInt(t, j));
                        i7 += iM;
                    }
                    break;
                case 5:
                    if ((i8 & i2) != 0) {
                        iM = nu.P(i9);
                        i7 += iM;
                    }
                    break;
                case 6:
                    if ((i8 & i2) != 0) {
                        iM = nu.O(i9);
                        i7 += iM;
                    }
                    break;
                case 7:
                    if ((i8 & i2) != 0) {
                        iM = nu.J(i9);
                        i7 += iM;
                    }
                    break;
                case 8:
                    if ((i8 & i2) != 0) {
                        Object object = unsafe.getObject(t, j);
                        iK = object instanceof lk ? nu.K(i9, (lk) object) : nu.f0(i9, (String) object);
                        i7 = iK + i7;
                    }
                    break;
                case 9:
                    if ((i8 & i2) != 0) {
                        iM = c1.o(i9, p(i6), unsafe.getObject(t, j));
                        i7 += iM;
                    }
                    break;
                case 10:
                    if ((i8 & i2) != 0) {
                        iM = nu.K(i9, (lk) unsafe.getObject(t, j));
                        i7 += iM;
                    }
                    break;
                case 11:
                    if ((i8 & i2) != 0) {
                        iM = nu.j0(i9, unsafe.getInt(t, j));
                        i7 += iM;
                    }
                    break;
                case 12:
                    if ((i8 & i2) != 0) {
                        iM = nu.N(i9, unsafe.getInt(t, j));
                        i7 += iM;
                    }
                    break;
                case 13:
                    if ((i8 & i2) != 0) {
                        iM = nu.b0(i9);
                        i7 += iM;
                    }
                    break;
                case 14:
                    if ((i8 & i2) != 0) {
                        iM = nu.c0(i9);
                        i7 += iM;
                    }
                    break;
                case 15:
                    if ((i8 & i2) != 0) {
                        iM = nu.d0(i9, unsafe.getInt(t, j));
                        i7 += iM;
                    }
                    break;
                case 16:
                    if ((i8 & i2) != 0) {
                        iM = nu.e0(i9, unsafe.getLong(t, j));
                        i7 += iM;
                    }
                    break;
                case 17:
                    if ((i8 & i2) != 0) {
                        iM = nu.R(i9, (r0) unsafe.getObject(t, j), p(i6));
                        i7 += iM;
                    }
                    break;
                case 18:
                    iM = c1.h(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 19:
                    iM = c1.f(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 20:
                    iM = c1.m(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 21:
                    iM = c1.x(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 22:
                    iM = c1.k(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 23:
                    iM = c1.h(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 24:
                    iM = c1.f(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 25:
                    iM = c1.a(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 26:
                    iM = c1.u(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 27:
                    iM = c1.p(i9, (List) unsafe.getObject(t, j), p(i6));
                    i7 += iM;
                    break;
                case 28:
                    iM = c1.c(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 29:
                    iM = c1.v(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 30:
                    iM = c1.d(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 31:
                    iM = c1.f(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 32:
                    iM = c1.h(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 33:
                    iM = c1.q(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 34:
                    iM = c1.s(i9, (List) unsafe.getObject(t, j));
                    i7 += iM;
                    break;
                case 35:
                    i3 = c1.i((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 36:
                    i3 = c1.g((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 37:
                    i3 = c1.n((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 38:
                    i3 = c1.y((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 39:
                    i3 = c1.l((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 40:
                    i3 = c1.i((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 41:
                    i3 = c1.g((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 42:
                    i3 = c1.b((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 43:
                    i3 = c1.w((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 44:
                    i3 = c1.e((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 45:
                    i3 = c1.g((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 46:
                    i3 = c1.i((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 47:
                    i3 = c1.r((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 48:
                    i3 = c1.t((List) unsafe.getObject(t, j));
                    if (i3 > 0) {
                        if (z) {
                            unsafe.putInt(t, i, i3);
                        }
                        iI0 = nu.i0(i9);
                        iK0 = nu.k0(i3);
                        i7 = iK0 + iI0 + i3 + i7;
                    }
                    break;
                case 49:
                    iM = c1.j(i9, (List) unsafe.getObject(t, j), p(i6));
                    i7 += iM;
                    break;
                case 50:
                    iM = this.q.e(unsafe.getObject(t, j), i9, o(i6));
                    i7 += iM;
                    break;
                case 51:
                    if (u(i9, i6, t)) {
                        iM = nu.M(i9);
                        i7 += iM;
                    }
                    break;
                case 52:
                    if (u(i9, i6, t)) {
                        iM = nu.Q(i9);
                        i7 += iM;
                    }
                    break;
                case 53:
                    if (u(i9, i6, t)) {
                        iM = nu.U(i9, D(j, t));
                        i7 += iM;
                    }
                    break;
                case 54:
                    if (u(i9, i6, t)) {
                        iM = nu.m0(i9, D(j, t));
                        i7 += iM;
                    }
                    break;
                case 55:
                    if (u(i9, i6, t)) {
                        iM = nu.S(i9, C(j, t));
                        i7 += iM;
                    }
                    break;
                case 56:
                    if (u(i9, i6, t)) {
                        iM = nu.P(i9);
                        i7 += iM;
                    }
                    break;
                case 57:
                    if (u(i9, i6, t)) {
                        iM = nu.O(i9);
                        i7 += iM;
                    }
                    break;
                case 58:
                    if (u(i9, i6, t)) {
                        iM = nu.J(i9);
                        i7 += iM;
                    }
                    break;
                case 59:
                    if (u(i9, i6, t)) {
                        Object object2 = unsafe.getObject(t, j);
                        iK = object2 instanceof lk ? nu.K(i9, (lk) object2) : nu.f0(i9, (String) object2);
                        i7 = iK + i7;
                    }
                    break;
                case 60:
                    if (u(i9, i6, t)) {
                        iM = c1.o(i9, p(i6), unsafe.getObject(t, j));
                        i7 += iM;
                    }
                    break;
                case 61:
                    if (u(i9, i6, t)) {
                        iM = nu.K(i9, (lk) unsafe.getObject(t, j));
                        i7 += iM;
                    }
                    break;
                case 62:
                    if (u(i9, i6, t)) {
                        iM = nu.j0(i9, C(j, t));
                        i7 += iM;
                    }
                    break;
                case 63:
                    if (u(i9, i6, t)) {
                        iM = nu.N(i9, C(j, t));
                        i7 += iM;
                    }
                    break;
                case 64:
                    if (u(i9, i6, t)) {
                        iM = nu.b0(i9);
                        i7 += iM;
                    }
                    break;
                case 65:
                    if (u(i9, i6, t)) {
                        iM = nu.c0(i9);
                        i7 += iM;
                    }
                    break;
                case 66:
                    if (u(i9, i6, t)) {
                        iM = nu.d0(i9, C(j, t));
                        i7 += iM;
                    }
                    break;
                case 67:
                    if (u(i9, i6, t)) {
                        iM = nu.e0(i9, D(j, t));
                        i7 += iM;
                    }
                    break;
                case 68:
                    if (u(i9, i6, t)) {
                        iM = nu.R(i9, (r0) unsafe.getObject(t, j), p(i6));
                        i7 += iM;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
    }

    public final int s(T t) {
        int iM;
        int iK;
        int i;
        int iI0;
        int iK0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i2 >= iArr.length) {
                i1<?, ?> i1Var = this.o;
                return i1Var.h(i1Var.g(t)) + i3;
            }
            int iR = R(i2);
            int i4 = (267386880 & iR) >>> 20;
            int i5 = iArr[i2];
            long j = iR & 1048575;
            int i6 = (i4 < hm0.Q.a() || i4 > hm0.d0.a()) ? 0 : iArr[i2 + 2] & 1048575;
            boolean z = this.i;
            Unsafe unsafe = s;
            switch (i4) {
                case 0:
                    if (t(i2, t)) {
                        iM = nu.M(i5);
                        i3 += iM;
                    }
                    break;
                case 1:
                    if (t(i2, t)) {
                        iM = nu.Q(i5);
                        i3 += iM;
                    }
                    break;
                case 2:
                    if (t(i2, t)) {
                        iM = nu.U(i5, dj3.n(j, t));
                        i3 += iM;
                    }
                    break;
                case 3:
                    if (t(i2, t)) {
                        iM = nu.m0(i5, dj3.n(j, t));
                        i3 += iM;
                    }
                    break;
                case 4:
                    if (t(i2, t)) {
                        iM = nu.S(i5, dj3.m(j, t));
                        i3 += iM;
                    }
                    break;
                case 5:
                    if (t(i2, t)) {
                        iM = nu.P(i5);
                        i3 += iM;
                    }
                    break;
                case 6:
                    if (t(i2, t)) {
                        iM = nu.O(i5);
                        i3 += iM;
                    }
                    break;
                case 7:
                    if (t(i2, t)) {
                        iM = nu.J(i5);
                        i3 += iM;
                    }
                    break;
                case 8:
                    if (t(i2, t)) {
                        Object objO = dj3.o(j, t);
                        iK = objO instanceof lk ? nu.K(i5, (lk) objO) : nu.f0(i5, (String) objO);
                        i3 += iK;
                    }
                    break;
                case 9:
                    if (t(i2, t)) {
                        iM = c1.o(i5, p(i2), dj3.o(j, t));
                        i3 += iM;
                    }
                    break;
                case 10:
                    if (t(i2, t)) {
                        iM = nu.K(i5, (lk) dj3.o(j, t));
                        i3 += iM;
                    }
                    break;
                case 11:
                    if (t(i2, t)) {
                        iM = nu.j0(i5, dj3.m(j, t));
                        i3 += iM;
                    }
                    break;
                case 12:
                    if (t(i2, t)) {
                        iM = nu.N(i5, dj3.m(j, t));
                        i3 += iM;
                    }
                    break;
                case 13:
                    if (t(i2, t)) {
                        iM = nu.b0(i5);
                        i3 += iM;
                    }
                    break;
                case 14:
                    if (t(i2, t)) {
                        iM = nu.c0(i5);
                        i3 += iM;
                    }
                    break;
                case 15:
                    if (t(i2, t)) {
                        iM = nu.d0(i5, dj3.m(j, t));
                        i3 += iM;
                    }
                    break;
                case 16:
                    if (t(i2, t)) {
                        iM = nu.e0(i5, dj3.n(j, t));
                        i3 += iM;
                    }
                    break;
                case 17:
                    if (t(i2, t)) {
                        iM = nu.R(i5, (r0) dj3.o(j, t), p(i2));
                        i3 += iM;
                    }
                    break;
                case 18:
                    iM = c1.h(i5, v(j, t));
                    i3 += iM;
                    break;
                case 19:
                    iM = c1.f(i5, v(j, t));
                    i3 += iM;
                    break;
                case 20:
                    iM = c1.m(i5, v(j, t));
                    i3 += iM;
                    break;
                case 21:
                    iM = c1.x(i5, v(j, t));
                    i3 += iM;
                    break;
                case 22:
                    iM = c1.k(i5, v(j, t));
                    i3 += iM;
                    break;
                case 23:
                    iM = c1.h(i5, v(j, t));
                    i3 += iM;
                    break;
                case 24:
                    iM = c1.f(i5, v(j, t));
                    i3 += iM;
                    break;
                case 25:
                    iM = c1.a(i5, v(j, t));
                    i3 += iM;
                    break;
                case 26:
                    iM = c1.u(i5, v(j, t));
                    i3 += iM;
                    break;
                case 27:
                    iM = c1.p(i5, v(j, t), p(i2));
                    i3 += iM;
                    break;
                case 28:
                    iM = c1.c(i5, v(j, t));
                    i3 += iM;
                    break;
                case 29:
                    iM = c1.v(i5, v(j, t));
                    i3 += iM;
                    break;
                case 30:
                    iM = c1.d(i5, v(j, t));
                    i3 += iM;
                    break;
                case 31:
                    iM = c1.f(i5, v(j, t));
                    i3 += iM;
                    break;
                case 32:
                    iM = c1.h(i5, v(j, t));
                    i3 += iM;
                    break;
                case 33:
                    iM = c1.q(i5, v(j, t));
                    i3 += iM;
                    break;
                case 34:
                    iM = c1.s(i5, v(j, t));
                    i3 += iM;
                    break;
                case 35:
                    i = c1.i((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 36:
                    i = c1.g((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 37:
                    i = c1.n((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 38:
                    i = c1.y((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 39:
                    i = c1.l((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 40:
                    i = c1.i((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 41:
                    i = c1.g((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 42:
                    i = c1.b((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 43:
                    i = c1.w((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 44:
                    i = c1.e((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 45:
                    i = c1.g((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 46:
                    i = c1.i((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 47:
                    i = c1.r((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 48:
                    i = c1.t((List) unsafe.getObject(t, j));
                    if (i > 0) {
                        if (z) {
                            unsafe.putInt(t, i6, i);
                        }
                        iI0 = nu.i0(i5);
                        iK0 = nu.k0(i);
                        i3 += iK0 + iI0 + i;
                    }
                    break;
                case 49:
                    iM = c1.j(i5, v(j, t), p(i2));
                    i3 += iM;
                    break;
                case 50:
                    iM = this.q.e(dj3.o(j, t), i5, o(i2));
                    i3 += iM;
                    break;
                case 51:
                    if (u(i5, i2, t)) {
                        iM = nu.M(i5);
                        i3 += iM;
                    }
                    break;
                case 52:
                    if (u(i5, i2, t)) {
                        iM = nu.Q(i5);
                        i3 += iM;
                    }
                    break;
                case 53:
                    if (u(i5, i2, t)) {
                        iM = nu.U(i5, D(j, t));
                        i3 += iM;
                    }
                    break;
                case 54:
                    if (u(i5, i2, t)) {
                        iM = nu.m0(i5, D(j, t));
                        i3 += iM;
                    }
                    break;
                case 55:
                    if (u(i5, i2, t)) {
                        iM = nu.S(i5, C(j, t));
                        i3 += iM;
                    }
                    break;
                case 56:
                    if (u(i5, i2, t)) {
                        iM = nu.P(i5);
                        i3 += iM;
                    }
                    break;
                case 57:
                    if (u(i5, i2, t)) {
                        iM = nu.O(i5);
                        i3 += iM;
                    }
                    break;
                case 58:
                    if (u(i5, i2, t)) {
                        iM = nu.J(i5);
                        i3 += iM;
                    }
                    break;
                case 59:
                    if (u(i5, i2, t)) {
                        Object objO2 = dj3.o(j, t);
                        iK = objO2 instanceof lk ? nu.K(i5, (lk) objO2) : nu.f0(i5, (String) objO2);
                        i3 += iK;
                    }
                    break;
                case 60:
                    if (u(i5, i2, t)) {
                        iM = c1.o(i5, p(i2), dj3.o(j, t));
                        i3 += iM;
                    }
                    break;
                case 61:
                    if (u(i5, i2, t)) {
                        iM = nu.K(i5, (lk) dj3.o(j, t));
                        i3 += iM;
                    }
                    break;
                case 62:
                    if (u(i5, i2, t)) {
                        iM = nu.j0(i5, C(j, t));
                        i3 += iM;
                    }
                    break;
                case 63:
                    if (u(i5, i2, t)) {
                        iM = nu.N(i5, C(j, t));
                        i3 += iM;
                    }
                    break;
                case 64:
                    if (u(i5, i2, t)) {
                        iM = nu.b0(i5);
                        i3 += iM;
                    }
                    break;
                case 65:
                    if (u(i5, i2, t)) {
                        iM = nu.c0(i5);
                        i3 += iM;
                    }
                    break;
                case 66:
                    if (u(i5, i2, t)) {
                        iM = nu.d0(i5, C(j, t));
                        i3 += iM;
                    }
                    break;
                case 67:
                    if (u(i5, i2, t)) {
                        iM = nu.e0(i5, D(j, t));
                        i3 += iM;
                    }
                    break;
                case 68:
                    if (u(i5, i2, t)) {
                        iM = nu.R(i5, (r0) dj3.o(j, t), p(i2));
                        i3 += iM;
                    }
                    break;
            }
            i2 += 3;
        }
    }

    public final boolean t(int i, Object obj) {
        boolean zEquals;
        int i2 = this.a[i + 2];
        long j = i2 & 1048575;
        if (j != 1048575) {
            return ((1 << (i2 >>> 20)) & dj3.m(j, obj)) != 0;
        }
        int iR = R(i);
        long j2 = iR & 1048575;
        switch ((iR & 267386880) >>> 20) {
            case 0:
                return Double.doubleToRawLongBits(dj3.k(j2, obj)) != 0;
            case 1:
                return Float.floatToRawIntBits(dj3.l(j2, obj)) != 0;
            case 2:
                return dj3.n(j2, obj) != 0;
            case 3:
                return dj3.n(j2, obj) != 0;
            case 4:
                return dj3.m(j2, obj) != 0;
            case 5:
                return dj3.n(j2, obj) != 0;
            case 6:
                return dj3.m(j2, obj) != 0;
            case 7:
                return dj3.g(j2, obj);
            case 8:
                Object objO = dj3.o(j2, obj);
                if (objO instanceof String) {
                    zEquals = ((String) objO).isEmpty();
                } else {
                    if (!(objO instanceof lk)) {
                        throw new IllegalArgumentException();
                    }
                    zEquals = lk.f.equals(objO);
                }
                break;
            case 9:
                return dj3.o(j2, obj) != null;
            case 10:
                zEquals = lk.f.equals(dj3.o(j2, obj));
                break;
            case 11:
                return dj3.m(j2, obj) != 0;
            case 12:
                return dj3.m(j2, obj) != 0;
            case 13:
                return dj3.m(j2, obj) != 0;
            case 14:
                return dj3.n(j2, obj) != 0;
            case 15:
                return dj3.m(j2, obj) != 0;
            case 16:
                return dj3.n(j2, obj) != 0;
            case 17:
                return dj3.o(j2, obj) != null;
            default:
                throw new IllegalArgumentException();
        }
        return !zEquals;
    }

    public final boolean u(int i, int i2, Object obj) {
        return dj3.m((long) (this.a[i2 + 2] & 1048575), obj) == i;
    }

    public final <K, V> void w(Object obj, int i, Object obj2, s sVar, z0 z0Var) {
        long jR = R(i) & 1048575;
        Object objO = dj3.o(jR, obj);
        n0 n0Var = this.q;
        if (objO == null) {
            objO = n0Var.f(obj2);
            dj3.z(jR, obj, objO);
        } else if (n0Var.g(objO)) {
            Object objF = n0Var.f(obj2);
            n0Var.a(objF, objO);
            dj3.z(jR, obj, objF);
            objO = objF;
        }
        z0Var.B(n0Var.d(objO), n0Var.c(obj2), sVar);
    }

    public final void x(Object obj, int i, Object obj2) {
        long jR = R(i) & 1048575;
        if (t(i, obj2)) {
            Object objO = dj3.o(jR, obj);
            Object objO2 = dj3.o(jR, obj2);
            if (objO != null && objO2 != null) {
                dj3.z(jR, obj, c0.c(objO, objO2));
                O(i, obj);
            } else if (objO2 != null) {
                dj3.z(jR, obj, objO2);
                O(i, obj);
            }
        }
    }

    public final void y(Object obj, int i, Object obj2) {
        int iR = R(i);
        int i2 = this.a[i];
        long j = iR & 1048575;
        if (u(i2, i, obj2)) {
            Object objO = u(i2, i, obj) ? dj3.o(j, obj) : null;
            Object objO2 = dj3.o(j, obj2);
            if (objO != null && objO2 != null) {
                dj3.z(j, obj, c0.c(objO, objO2));
                P(i2, i, obj);
            } else if (objO2 != null) {
                dj3.z(j, obj, objO2);
                P(i2, i, obj);
            }
        }
    }
}

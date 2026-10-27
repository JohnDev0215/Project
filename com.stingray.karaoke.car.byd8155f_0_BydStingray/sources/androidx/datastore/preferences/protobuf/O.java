package androidx.datastore.preferences.protobuf;

import h2.AbstractC1347d;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class O implements Y {
    public static final int[] o = new int[0];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Unsafe f11801p = r0.m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f11802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f11803b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0937a f11804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f11805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f11806g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int[] f11807h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f11808i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f11809j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Q f11810k;
    public final E l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j0 f11811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final K f11812n;

    public O(int[] iArr, Object[] objArr, int i7, int i10, AbstractC0937a abstractC0937a, boolean z6, int[] iArr2, int i11, int i12, Q q4, E e10, j0 j0Var, C0951n c0951n, K k10) {
        this.f11802a = iArr;
        this.f11803b = objArr;
        this.c = i7;
        this.d = i10;
        this.f11805f = abstractC0937a instanceof AbstractC0957u;
        this.f11806g = z6;
        this.f11807h = iArr2;
        this.f11808i = i11;
        this.f11809j = i12;
        this.f11810k = q4;
        this.l = e10;
        this.f11811m = j0Var;
        this.f11804e = abstractC0937a;
        this.f11812n = k10;
    }

    public static O A(X x8, Q q4, E e10, j0 j0Var, C0951n c0951n, K k10) {
        int i7;
        int iCharAt;
        int iCharAt2;
        int iCharAt3;
        int i10;
        int i11;
        int i12;
        int[] iArr;
        int i13;
        char cCharAt;
        int i14;
        char cCharAt2;
        int i15;
        char cCharAt3;
        int i16;
        char cCharAt4;
        int i17;
        char cCharAt5;
        int i18;
        char cCharAt6;
        int i19;
        char cCharAt7;
        int i20;
        char cCharAt8;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int iObjectFieldOffset;
        int i27;
        int i28;
        int iObjectFieldOffset2;
        int i29;
        Field fieldJ;
        char cCharAt9;
        int i30;
        int i31;
        int i32;
        Field fieldJ2;
        Field fieldJ3;
        int i33;
        char cCharAt10;
        int i34;
        char cCharAt11;
        int i35;
        char cCharAt12;
        int i36;
        char cCharAt13;
        int i37 = 0;
        boolean z6 = x8.d() == 2;
        String strC = x8.c();
        int length = strC.length();
        if (strC.charAt(0) >= 55296) {
            int i38 = 1;
            while (true) {
                i7 = i38 + 1;
                if (strC.charAt(i38) < 55296) {
                    break;
                }
                i38 = i7;
            }
        } else {
            i7 = 1;
        }
        int i39 = i7 + 1;
        int iCharAt4 = strC.charAt(i7);
        if (iCharAt4 >= 55296) {
            int i40 = iCharAt4 & 8191;
            int i41 = 13;
            while (true) {
                i36 = i39 + 1;
                cCharAt13 = strC.charAt(i39);
                if (cCharAt13 < 55296) {
                    break;
                }
                i40 |= (cCharAt13 & 8191) << i41;
                i41 += 13;
                i39 = i36;
            }
            iCharAt4 = i40 | (cCharAt13 << i41);
            i39 = i36;
        }
        if (iCharAt4 == 0) {
            i12 = 0;
            iCharAt = 0;
            iCharAt2 = 0;
            i10 = 0;
            iCharAt3 = 0;
            iArr = o;
            i11 = 0;
        } else {
            int i42 = i39 + 1;
            int iCharAt5 = strC.charAt(i39);
            if (iCharAt5 >= 55296) {
                int i43 = iCharAt5 & 8191;
                int i44 = 13;
                while (true) {
                    i20 = i42 + 1;
                    cCharAt8 = strC.charAt(i42);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt8 & 8191) << i44;
                    i44 += 13;
                    i42 = i20;
                }
                iCharAt5 = i43 | (cCharAt8 << i44);
                i42 = i20;
            }
            int i45 = i42 + 1;
            int iCharAt6 = strC.charAt(i42);
            if (iCharAt6 >= 55296) {
                int i46 = iCharAt6 & 8191;
                int i47 = 13;
                while (true) {
                    i19 = i45 + 1;
                    cCharAt7 = strC.charAt(i45);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt7 & 8191) << i47;
                    i47 += 13;
                    i45 = i19;
                }
                iCharAt6 = i46 | (cCharAt7 << i47);
                i45 = i19;
            }
            int i48 = i45 + 1;
            int iCharAt7 = strC.charAt(i45);
            if (iCharAt7 >= 55296) {
                int i49 = iCharAt7 & 8191;
                int i50 = 13;
                while (true) {
                    i18 = i48 + 1;
                    cCharAt6 = strC.charAt(i48);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt6 & 8191) << i50;
                    i50 += 13;
                    i48 = i18;
                }
                iCharAt7 = i49 | (cCharAt6 << i50);
                i48 = i18;
            }
            int i51 = i48 + 1;
            iCharAt = strC.charAt(i48);
            if (iCharAt >= 55296) {
                int i52 = iCharAt & 8191;
                int i53 = 13;
                while (true) {
                    i17 = i51 + 1;
                    cCharAt5 = strC.charAt(i51);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt5 & 8191) << i53;
                    i53 += 13;
                    i51 = i17;
                }
                iCharAt = i52 | (cCharAt5 << i53);
                i51 = i17;
            }
            int i54 = i51 + 1;
            int iCharAt8 = strC.charAt(i51);
            if (iCharAt8 >= 55296) {
                int i55 = iCharAt8 & 8191;
                int i56 = 13;
                while (true) {
                    i16 = i54 + 1;
                    cCharAt4 = strC.charAt(i54);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i55 |= (cCharAt4 & 8191) << i56;
                    i56 += 13;
                    i54 = i16;
                }
                iCharAt8 = i55 | (cCharAt4 << i56);
                i54 = i16;
            }
            int i57 = i54 + 1;
            iCharAt2 = strC.charAt(i54);
            if (iCharAt2 >= 55296) {
                int i58 = iCharAt2 & 8191;
                int i59 = 13;
                while (true) {
                    i15 = i57 + 1;
                    cCharAt3 = strC.charAt(i57);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i58 |= (cCharAt3 & 8191) << i59;
                    i59 += 13;
                    i57 = i15;
                }
                iCharAt2 = i58 | (cCharAt3 << i59);
                i57 = i15;
            }
            int i60 = i57 + 1;
            int iCharAt9 = strC.charAt(i57);
            if (iCharAt9 >= 55296) {
                int i61 = iCharAt9 & 8191;
                int i62 = 13;
                while (true) {
                    i14 = i60 + 1;
                    cCharAt2 = strC.charAt(i60);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i61 |= (cCharAt2 & 8191) << i62;
                    i62 += 13;
                    i60 = i14;
                }
                iCharAt9 = i61 | (cCharAt2 << i62);
                i60 = i14;
            }
            int i63 = i60 + 1;
            iCharAt3 = strC.charAt(i60);
            if (iCharAt3 >= 55296) {
                int i64 = iCharAt3 & 8191;
                int i65 = i63;
                int i66 = 13;
                while (true) {
                    i13 = i65 + 1;
                    cCharAt = strC.charAt(i65);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i64 |= (cCharAt & 8191) << i66;
                    i66 += 13;
                    i65 = i13;
                }
                iCharAt3 = i64 | (cCharAt << i66);
                i63 = i13;
            }
            int[] iArr2 = new int[iCharAt3 + iCharAt2 + iCharAt9];
            i10 = (iCharAt5 * 2) + iCharAt6;
            i11 = iCharAt7;
            i12 = iCharAt8;
            iArr = iArr2;
            i37 = iCharAt5;
            i39 = i63;
        }
        Unsafe unsafe = f11801p;
        Object[] objArrB = x8.b();
        Class<?> cls = x8.a().getClass();
        int[] iArr3 = new int[i12 * 3];
        Object[] objArr = new Object[i12 * 2];
        int i67 = iCharAt2 + iCharAt3;
        int i68 = i67;
        int i69 = iCharAt3;
        int i70 = 0;
        int i71 = 0;
        while (i39 < length) {
            int i72 = i39 + 1;
            int iCharAt10 = strC.charAt(i39);
            if (iCharAt10 >= 55296) {
                int i73 = iCharAt10 & 8191;
                int i74 = i72;
                int i75 = 13;
                while (true) {
                    i35 = i74 + 1;
                    cCharAt12 = strC.charAt(i74);
                    i21 = length;
                    if (cCharAt12 < 55296) {
                        break;
                    }
                    i73 |= (cCharAt12 & 8191) << i75;
                    i75 += 13;
                    i74 = i35;
                    length = i21;
                }
                iCharAt10 = i73 | (cCharAt12 << i75);
                i22 = i35;
            } else {
                i21 = length;
                i22 = i72;
            }
            int i76 = i22 + 1;
            int iCharAt11 = strC.charAt(i22);
            if (iCharAt11 >= 55296) {
                int i77 = iCharAt11 & 8191;
                int i78 = i76;
                int i79 = 13;
                while (true) {
                    i34 = i78 + 1;
                    cCharAt11 = strC.charAt(i78);
                    i23 = i67;
                    if (cCharAt11 < 55296) {
                        break;
                    }
                    i77 |= (cCharAt11 & 8191) << i79;
                    i79 += 13;
                    i78 = i34;
                    i67 = i23;
                }
                iCharAt11 = i77 | (cCharAt11 << i79);
                i24 = i34;
            } else {
                i23 = i67;
                i24 = i76;
            }
            int i80 = iCharAt11 & 255;
            int i81 = iCharAt3;
            if ((iCharAt11 & 1024) != 0) {
                iArr[i70] = i71;
                i70++;
            }
            if (i80 >= 51) {
                int i82 = i24 + 1;
                int iCharAt12 = strC.charAt(i24);
                if (iCharAt12 >= 55296) {
                    int i83 = iCharAt12 & 8191;
                    int i84 = i82;
                    int i85 = 13;
                    while (true) {
                        i33 = i84 + 1;
                        cCharAt10 = strC.charAt(i84);
                        i25 = iCharAt;
                        if (cCharAt10 < 55296) {
                            break;
                        }
                        i83 |= (cCharAt10 & 8191) << i85;
                        i85 += 13;
                        i84 = i33;
                        iCharAt = i25;
                    }
                    iCharAt12 = i83 | (cCharAt10 << i85);
                    i31 = i33;
                } else {
                    i25 = iCharAt;
                    i31 = i82;
                }
                int i86 = i80 - 51;
                int i87 = i31;
                if (i86 == 9 || i86 == 17) {
                    i32 = 2;
                    objArr[((i71 / 3) * 2) + 1] = objArrB[i10];
                    i10++;
                } else {
                    if (i86 == 12 && !z6) {
                        objArr[((i71 / 3) * 2) + 1] = objArrB[i10];
                        i10++;
                    }
                    i32 = 2;
                }
                int i88 = iCharAt12 * i32;
                Object obj = objArrB[i88];
                if (obj instanceof Field) {
                    fieldJ2 = (Field) obj;
                } else {
                    fieldJ2 = J(cls, (String) obj);
                    objArrB[i88] = fieldJ2;
                }
                int i89 = i10;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldJ2);
                int i90 = i88 + 1;
                Object obj2 = objArrB[i90];
                if (obj2 instanceof Field) {
                    fieldJ3 = (Field) obj2;
                } else {
                    fieldJ3 = J(cls, (String) obj2);
                    objArrB[i90] = fieldJ3;
                }
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldJ3);
                i28 = 0;
                i26 = i11;
                i29 = i89;
                i27 = i87;
            } else {
                i25 = iCharAt;
                int i91 = i10 + 1;
                Field fieldJ4 = J(cls, (String) objArrB[i10]);
                i26 = i11;
                if (i80 == 9 || i80 == 17) {
                    objArr[((i71 / 3) * 2) + 1] = fieldJ4.getType();
                } else {
                    if (i80 == 27 || i80 == 49) {
                        i30 = i10 + 2;
                        objArr[((i71 / 3) * 2) + 1] = objArrB[i91];
                    } else if (i80 == 12 || i80 == 30 || i80 == 44) {
                        if (!z6) {
                            i30 = i10 + 2;
                            objArr[((i71 / 3) * 2) + 1] = objArrB[i91];
                        }
                    } else if (i80 == 50) {
                        int i92 = i69 + 1;
                        iArr[i69] = i71;
                        int i93 = (i71 / 3) * 2;
                        int i94 = i10 + 2;
                        objArr[i93] = objArrB[i91];
                        if ((iCharAt11 & 2048) != 0) {
                            i91 = i10 + 3;
                            objArr[i93 + 1] = objArrB[i94];
                            i69 = i92;
                        } else {
                            i69 = i92;
                            i91 = i94;
                        }
                    }
                    i91 = i30;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldJ4);
                if ((iCharAt11 & 4096) != 4096 || i80 > 17) {
                    iObjectFieldOffset = 1048575;
                    i27 = i24;
                    i28 = 0;
                } else {
                    int i95 = i24 + 1;
                    int iCharAt13 = strC.charAt(i24);
                    if (iCharAt13 >= 55296) {
                        int i96 = iCharAt13 & 8191;
                        int i97 = 13;
                        while (true) {
                            i27 = i95 + 1;
                            cCharAt9 = strC.charAt(i95);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i96 |= (cCharAt9 & 8191) << i97;
                            i97 += 13;
                            i95 = i27;
                        }
                        iCharAt13 = i96 | (cCharAt9 << i97);
                    } else {
                        i27 = i95;
                    }
                    int i98 = (iCharAt13 / 32) + (i37 * 2);
                    Object obj3 = objArrB[i98];
                    if (obj3 instanceof Field) {
                        fieldJ = (Field) obj3;
                    } else {
                        fieldJ = J(cls, (String) obj3);
                        objArrB[i98] = fieldJ;
                    }
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldJ);
                    i28 = iCharAt13 % 32;
                }
                if (i80 >= 18 && i80 <= 49) {
                    iArr[i68] = iObjectFieldOffset3;
                    i68++;
                }
                int i99 = i91;
                iObjectFieldOffset2 = iObjectFieldOffset3;
                i29 = i99;
            }
            int i100 = i71 + 1;
            iArr3[i71] = iCharAt10;
            int i101 = i71 + 2;
            String str = strC;
            iArr3[i100] = ((iCharAt11 & 512) != 0 ? 536870912 : 0) | ((iCharAt11 & 256) != 0 ? 268435456 : 0) | (i80 << 20) | iObjectFieldOffset2;
            i71 += 3;
            iArr3[i101] = (i28 << 20) | iObjectFieldOffset;
            i10 = i29;
            iCharAt3 = i81;
            strC = str;
            i11 = i26;
            length = i21;
            i67 = i23;
            i39 = i27;
            iCharAt = i25;
        }
        return new O(iArr3, objArr, i11, iCharAt, x8.a(), z6, iArr, iCharAt3, i67, q4, e10, j0Var, c0951n, k10);
    }

    public static long B(int i7) {
        return i7 & 1048575;
    }

    public static int C(long j3, Object obj) {
        return ((Integer) r0.c.i(j3, obj)).intValue();
    }

    public static long D(long j3, Object obj) {
        return ((Long) r0.c.i(j3, obj)).longValue();
    }

    public static Field J(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder sbJ = AbstractC1347d.j("Field ", str, " for ");
            sbJ.append(cls.getName());
            sbJ.append(" not found. Known fields are ");
            sbJ.append(Arrays.toString(declaredFields));
            throw new RuntimeException(sbJ.toString());
        }
    }

    public static int O(int i7) {
        return (i7 & 267386880) >>> 20;
    }

    public static void S(int i7, Object obj, G g7) throws IOException {
        if (!(obj instanceof String)) {
            g7.b(i7, (C0944g) obj);
        } else {
            ((C0948k) g7.f11793a).V(i7, (String) obj);
        }
    }

    public static boolean r(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof AbstractC0957u) {
            return ((AbstractC0957u) obj).i();
        }
        return true;
    }

    public static List t(AbstractC0957u abstractC0957u, long j3) {
        return (List) r0.c.i(j3, abstractC0957u);
    }

    public static O z(X x8, Q q4, E e10, j0 j0Var, C0951n c0951n, K k10) {
        if (x8 instanceof X) {
            return A(x8, q4, e10, j0Var, c0951n, k10);
        }
        x8.getClass();
        throw new ClassCastException();
    }

    public final int E(int i7) {
        if (i7 < this.c || i7 > this.d) {
            return -1;
        }
        int[] iArr = this.f11802a;
        int length = (iArr.length / 3) - 1;
        int i10 = 0;
        while (i10 <= length) {
            int i11 = (length + i10) >>> 1;
            int i12 = i11 * 3;
            int i13 = iArr[i12];
            if (i7 == i13) {
                return i12;
            }
            if (i7 < i13) {
                length = i11 - 1;
            } else {
                i10 = i11 + 1;
            }
        }
        return -1;
    }

    public final void F(Object obj, long j3, E5.z zVar, Y y10, C0950m c0950m) throws C0960x {
        int iU;
        List listC = this.l.c(j3, obj);
        int i7 = zVar.f1841b;
        if ((i7 & 7) != 3) {
            throw C0961y.b();
        }
        do {
            AbstractC0957u abstractC0957uH = y10.h();
            zVar.h(abstractC0957uH, y10, c0950m);
            y10.d(abstractC0957uH);
            listC.add(abstractC0957uH);
            AbstractC0947j abstractC0947j = (AbstractC0947j) zVar.f1842e;
            if (abstractC0947j.c() || zVar.d != 0) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == i7);
        zVar.d = iU;
    }

    public final void G(Object obj, int i7, E5.z zVar, Y y10, C0950m c0950m) throws C0961y {
        int iU;
        List listC = this.l.c(i7 & 1048575, obj);
        int i10 = zVar.f1841b;
        if ((i10 & 7) != 2) {
            throw C0961y.b();
        }
        do {
            AbstractC0957u abstractC0957uH = y10.h();
            zVar.i(abstractC0957uH, y10, c0950m);
            y10.d(abstractC0957uH);
            listC.add(abstractC0957uH);
            AbstractC0947j abstractC0947j = (AbstractC0947j) zVar.f1842e;
            if (abstractC0947j.c() || zVar.d != 0) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == i10);
        zVar.d = iU;
    }

    public final void H(Object obj, int i7, E5.z zVar) throws C0960x {
        if ((536870912 & i7) != 0) {
            zVar.D(2);
            r0.s(i7 & 1048575, obj, ((AbstractC0947j) zVar.f1842e).t());
        } else if (!this.f11805f) {
            r0.s(i7 & 1048575, obj, zVar.k());
        } else {
            zVar.D(2);
            r0.s(i7 & 1048575, obj, ((AbstractC0947j) zVar.f1842e).s());
        }
    }

    public final void I(Object obj, int i7, E5.z zVar) throws C0960x {
        boolean z6 = (536870912 & i7) != 0;
        E e10 = this.l;
        if (z6) {
            zVar.y(e10.c(i7 & 1048575, obj), true);
        } else {
            zVar.y(e10.c(i7 & 1048575, obj), false);
        }
    }

    public final void K(int i7, Object obj) {
        int i10 = this.f11802a[i7 + 2];
        long j3 = 1048575 & i10;
        if (j3 == 1048575) {
            return;
        }
        r0.q((1 << (i10 >>> 20)) | r0.c.g(j3, obj), j3, obj);
    }

    public final void L(int i7, int i10, Object obj) {
        r0.q(i7, this.f11802a[i10 + 2] & 1048575, obj);
    }

    public final void M(Object obj, int i7, AbstractC0937a abstractC0937a) {
        f11801p.putObject(obj, P(i7) & 1048575, abstractC0937a);
        K(i7, obj);
    }

    public final void N(Object obj, int i7, int i10, AbstractC0937a abstractC0937a) {
        f11801p.putObject(obj, P(i10) & 1048575, abstractC0937a);
        L(i7, i10, obj);
    }

    public final int P(int i7) {
        return this.f11802a[i7 + 1];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void Q(Object obj, G g7) throws IOException {
        int i7;
        boolean z6;
        int[] iArr = this.f11802a;
        int length = iArr.length;
        Unsafe unsafe = f11801p;
        int i10 = 1048575;
        int i11 = 1048575;
        int i12 = 0;
        int i13 = 0;
        while (i12 < length) {
            int iP = P(i12);
            int i14 = iArr[i12];
            int iO = O(iP);
            if (iO <= 17) {
                int i15 = iArr[i12 + 2];
                int i16 = i15 & i10;
                if (i16 != i11) {
                    i13 = unsafe.getInt(obj, i16);
                    i11 = i16;
                }
                i7 = 1 << (i15 >>> 20);
            } else {
                i7 = 0;
            }
            int i17 = i7;
            long j3 = iP & i10;
            switch (iO) {
                case 0:
                    if ((i13 & i17) != 0) {
                        g7.c(i14, r0.c.e(j3, obj));
                    }
                    break;
                case 1:
                    if ((i13 & i17) != 0) {
                        g7.g(r0.c.f(j3, obj), i14);
                    }
                    break;
                case 2:
                    if ((i13 & i17) != 0) {
                        g7.j(unsafe.getLong(obj, j3), i14);
                    }
                    break;
                case 3:
                    if ((i13 & i17) != 0) {
                        g7.q(unsafe.getLong(obj, j3), i14);
                    }
                    break;
                case 4:
                    if ((i13 & i17) != 0) {
                        g7.i(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 5:
                    if ((i13 & i17) != 0) {
                        g7.f(unsafe.getLong(obj, j3), i14);
                    }
                    break;
                case 6:
                    if ((i13 & i17) != 0) {
                        g7.e(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 7:
                    if ((i13 & i17) != 0) {
                        g7.a(i14, r0.c.c(j3, obj));
                    }
                    break;
                case 8:
                    if ((i13 & i17) != 0) {
                        S(i14, unsafe.getObject(obj, j3), g7);
                    }
                    break;
                case 9:
                    if ((i13 & i17) != 0) {
                        g7.k(i14, unsafe.getObject(obj, j3), n(i12));
                    }
                    break;
                case 10:
                    if ((i13 & i17) != 0) {
                        g7.b(i14, (C0944g) unsafe.getObject(obj, j3));
                    }
                    break;
                case 11:
                    if ((i13 & i17) != 0) {
                        g7.p(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 12:
                    if ((i13 & i17) != 0) {
                        g7.d(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 13:
                    if ((i13 & i17) != 0) {
                        g7.l(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 14:
                    if ((i13 & i17) != 0) {
                        g7.m(unsafe.getLong(obj, j3), i14);
                    }
                    break;
                case 15:
                    if ((i13 & i17) != 0) {
                        g7.n(i14, unsafe.getInt(obj, j3));
                    }
                    break;
                case 16:
                    if ((i13 & i17) != 0) {
                        g7.o(unsafe.getLong(obj, j3), i14);
                    }
                    break;
                case 17:
                    if ((i13 & i17) != 0) {
                        g7.h(i14, unsafe.getObject(obj, j3), n(i12));
                    }
                    break;
                case 18:
                    Z.F(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 19:
                    Z.J(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 20:
                    Z.M(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 21:
                    Z.U(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 22:
                    Z.L(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 23:
                    Z.I(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 24:
                    Z.H(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 25:
                    Z.D(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 26:
                    Z.S(iArr[i12], (List) unsafe.getObject(obj, j3), g7);
                    break;
                case 27:
                    Z.N(iArr[i12], (List) unsafe.getObject(obj, j3), g7, n(i12));
                    break;
                case 28:
                    Z.E(iArr[i12], (List) unsafe.getObject(obj, j3), g7);
                    break;
                case 29:
                    z6 = false;
                    Z.T(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 30:
                    z6 = false;
                    Z.G(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 31:
                    z6 = false;
                    Z.O(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 32:
                    z6 = false;
                    Z.P(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 33:
                    z6 = false;
                    Z.Q(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 34:
                    z6 = false;
                    Z.R(iArr[i12], (List) unsafe.getObject(obj, j3), g7, false);
                    break;
                case 35:
                    Z.F(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 36:
                    Z.J(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 37:
                    Z.M(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 38:
                    Z.U(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 39:
                    Z.L(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 40:
                    Z.I(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 41:
                    Z.H(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 42:
                    Z.D(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 43:
                    Z.T(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 44:
                    Z.G(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 45:
                    Z.O(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 46:
                    Z.P(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 47:
                    Z.Q(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 48:
                    Z.R(iArr[i12], (List) unsafe.getObject(obj, j3), g7, true);
                    break;
                case 49:
                    Z.K(iArr[i12], (List) unsafe.getObject(obj, j3), g7, n(i12));
                    break;
                case 50:
                    R(g7, i14, unsafe.getObject(obj, j3), i12);
                    break;
                case 51:
                    if (s(i14, i12, obj)) {
                        g7.c(i14, ((Double) r0.c.i(j3, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (s(i14, i12, obj)) {
                        g7.g(((Float) r0.c.i(j3, obj)).floatValue(), i14);
                    }
                    break;
                case 53:
                    if (s(i14, i12, obj)) {
                        g7.j(D(j3, obj), i14);
                    }
                    break;
                case 54:
                    if (s(i14, i12, obj)) {
                        g7.q(D(j3, obj), i14);
                    }
                    break;
                case 55:
                    if (s(i14, i12, obj)) {
                        g7.i(i14, C(j3, obj));
                    }
                    break;
                case 56:
                    if (s(i14, i12, obj)) {
                        g7.f(D(j3, obj), i14);
                    }
                    break;
                case 57:
                    if (s(i14, i12, obj)) {
                        g7.e(i14, C(j3, obj));
                    }
                    break;
                case 58:
                    if (s(i14, i12, obj)) {
                        g7.a(i14, ((Boolean) r0.c.i(j3, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (s(i14, i12, obj)) {
                        S(i14, unsafe.getObject(obj, j3), g7);
                    }
                    break;
                case 60:
                    if (s(i14, i12, obj)) {
                        g7.k(i14, unsafe.getObject(obj, j3), n(i12));
                    }
                    break;
                case 61:
                    if (s(i14, i12, obj)) {
                        g7.b(i14, (C0944g) unsafe.getObject(obj, j3));
                    }
                    break;
                case 62:
                    if (s(i14, i12, obj)) {
                        g7.p(i14, C(j3, obj));
                    }
                    break;
                case 63:
                    if (s(i14, i12, obj)) {
                        g7.d(i14, C(j3, obj));
                    }
                    break;
                case 64:
                    if (s(i14, i12, obj)) {
                        g7.l(i14, C(j3, obj));
                    }
                    break;
                case 65:
                    if (s(i14, i12, obj)) {
                        g7.m(D(j3, obj), i14);
                    }
                    break;
                case 66:
                    if (s(i14, i12, obj)) {
                        g7.n(i14, C(j3, obj));
                    }
                    break;
                case 67:
                    if (s(i14, i12, obj)) {
                        g7.o(D(j3, obj), i14);
                    }
                    break;
                case 68:
                    if (s(i14, i12, obj)) {
                        g7.h(i14, unsafe.getObject(obj, j3), n(i12));
                    }
                    break;
            }
            i12 += 3;
            i10 = 1048575;
        }
        this.f11811m.getClass();
        ((AbstractC0957u) obj).unknownFields.d(g7);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0142 A[SYNTHETIC] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R(androidx.datastore.preferences.protobuf.G r22, int r23, java.lang.Object r24, int r25) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.R(androidx.datastore.preferences.protobuf.G, int, java.lang.Object, int):void");
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void a(Object obj, Object obj2) {
        if (!r(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + obj);
        }
        obj2.getClass();
        int i7 = 0;
        while (true) {
            int[] iArr = this.f11802a;
            if (i7 >= iArr.length) {
                Z.B(this.f11811m, obj, obj2);
                return;
            }
            int iP = P(i7);
            long j3 = 1048575 & iP;
            int i10 = iArr[i7];
            switch (O(iP)) {
                case 0:
                    if (q(i7, obj2)) {
                        q0 q0Var = r0.c;
                        q0Var.m(obj, j3, q0Var.e(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 1:
                    if (q(i7, obj2)) {
                        q0 q0Var2 = r0.c;
                        q0Var2.n(obj, j3, q0Var2.f(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 2:
                    if (q(i7, obj2)) {
                        r0.r(obj, j3, r0.c.h(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 3:
                    if (q(i7, obj2)) {
                        r0.r(obj, j3, r0.c.h(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 4:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 5:
                    if (q(i7, obj2)) {
                        r0.r(obj, j3, r0.c.h(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 6:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 7:
                    if (q(i7, obj2)) {
                        q0 q0Var3 = r0.c;
                        q0Var3.k(obj, j3, q0Var3.c(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 8:
                    if (q(i7, obj2)) {
                        r0.s(j3, obj, r0.c.i(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 9:
                    v(obj, i7, obj2);
                    break;
                case 10:
                    if (q(i7, obj2)) {
                        r0.s(j3, obj, r0.c.i(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 11:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 12:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 13:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 14:
                    if (q(i7, obj2)) {
                        r0.r(obj, j3, r0.c.h(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 15:
                    if (q(i7, obj2)) {
                        r0.q(r0.c.g(j3, obj2), j3, obj);
                        K(i7, obj);
                    }
                    break;
                case 16:
                    if (q(i7, obj2)) {
                        r0.r(obj, j3, r0.c.h(j3, obj2));
                        K(i7, obj);
                    }
                    break;
                case 17:
                    v(obj, i7, obj2);
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
                    this.l.b(j3, obj, obj2);
                    break;
                case 50:
                    Class cls = Z.f11823a;
                    q0 q0Var4 = r0.c;
                    Object objI = q0Var4.i(j3, obj);
                    Object objI2 = q0Var4.i(j3, obj2);
                    this.f11812n.getClass();
                    r0.s(j3, obj, K.b(objI, objI2));
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
                    if (s(i10, i7, obj2)) {
                        r0.s(j3, obj, r0.c.i(j3, obj2));
                        L(i10, i7, obj);
                    }
                    break;
                case 60:
                    w(obj, i7, obj2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (s(i10, i7, obj2)) {
                        r0.s(j3, obj, r0.c.i(j3, obj2));
                        L(i10, i7, obj);
                    }
                    break;
                case 68:
                    w(obj, i7, obj2);
                    break;
            }
            i7 += 3;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void b(Object obj, G g7) throws IOException {
        g7.getClass();
        if (!this.f11806g) {
            Q(obj, g7);
            return;
        }
        int[] iArr = this.f11802a;
        int length = iArr.length;
        for (int i7 = 0; i7 < length; i7 += 3) {
            int iP = P(i7);
            int i10 = iArr[i7];
            switch (O(iP)) {
                case 0:
                    if (q(i7, obj)) {
                        g7.c(i10, r0.c.e(iP & 1048575, obj));
                    }
                    break;
                case 1:
                    if (q(i7, obj)) {
                        g7.g(r0.c.f(iP & 1048575, obj), i10);
                    }
                    break;
                case 2:
                    if (q(i7, obj)) {
                        g7.j(r0.c.h(iP & 1048575, obj), i10);
                    }
                    break;
                case 3:
                    if (q(i7, obj)) {
                        g7.q(r0.c.h(iP & 1048575, obj), i10);
                    }
                    break;
                case 4:
                    if (q(i7, obj)) {
                        g7.i(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 5:
                    if (q(i7, obj)) {
                        g7.f(r0.c.h(iP & 1048575, obj), i10);
                    }
                    break;
                case 6:
                    if (q(i7, obj)) {
                        g7.e(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 7:
                    if (q(i7, obj)) {
                        g7.a(i10, r0.c.c(iP & 1048575, obj));
                    }
                    break;
                case 8:
                    if (q(i7, obj)) {
                        S(i10, r0.c.i(iP & 1048575, obj), g7);
                    }
                    break;
                case 9:
                    if (q(i7, obj)) {
                        g7.k(i10, r0.c.i(iP & 1048575, obj), n(i7));
                    }
                    break;
                case 10:
                    if (q(i7, obj)) {
                        g7.b(i10, (C0944g) r0.c.i(iP & 1048575, obj));
                    }
                    break;
                case 11:
                    if (q(i7, obj)) {
                        g7.p(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 12:
                    if (q(i7, obj)) {
                        g7.d(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 13:
                    if (q(i7, obj)) {
                        g7.l(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 14:
                    if (q(i7, obj)) {
                        g7.m(r0.c.h(iP & 1048575, obj), i10);
                    }
                    break;
                case 15:
                    if (q(i7, obj)) {
                        g7.n(i10, r0.c.g(iP & 1048575, obj));
                    }
                    break;
                case 16:
                    if (q(i7, obj)) {
                        g7.o(r0.c.h(iP & 1048575, obj), i10);
                    }
                    break;
                case 17:
                    if (q(i7, obj)) {
                        g7.h(i10, r0.c.i(iP & 1048575, obj), n(i7));
                    }
                    break;
                case 18:
                    Z.F(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 19:
                    Z.J(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 20:
                    Z.M(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 21:
                    Z.U(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 22:
                    Z.L(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 23:
                    Z.I(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 24:
                    Z.H(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 25:
                    Z.D(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 26:
                    Z.S(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7);
                    break;
                case 27:
                    Z.N(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, n(i7));
                    break;
                case 28:
                    Z.E(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7);
                    break;
                case 29:
                    Z.T(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 30:
                    Z.G(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 31:
                    Z.O(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 32:
                    Z.P(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 33:
                    Z.Q(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 34:
                    Z.R(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, false);
                    break;
                case 35:
                    Z.F(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 36:
                    Z.J(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 37:
                    Z.M(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 38:
                    Z.U(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 39:
                    Z.L(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 40:
                    Z.I(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 41:
                    Z.H(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 42:
                    Z.D(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 43:
                    Z.T(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 44:
                    Z.G(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 45:
                    Z.O(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 46:
                    Z.P(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 47:
                    Z.Q(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 48:
                    Z.R(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, true);
                    break;
                case 49:
                    Z.K(iArr[i7], (List) r0.c.i(iP & 1048575, obj), g7, n(i7));
                    break;
                case 50:
                    R(g7, i10, r0.c.i(iP & 1048575, obj), i7);
                    break;
                case 51:
                    if (s(i10, i7, obj)) {
                        g7.c(i10, ((Double) r0.c.i(iP & 1048575, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (s(i10, i7, obj)) {
                        g7.g(((Float) r0.c.i(iP & 1048575, obj)).floatValue(), i10);
                    }
                    break;
                case 53:
                    if (s(i10, i7, obj)) {
                        g7.j(D(iP & 1048575, obj), i10);
                    }
                    break;
                case 54:
                    if (s(i10, i7, obj)) {
                        g7.q(D(iP & 1048575, obj), i10);
                    }
                    break;
                case 55:
                    if (s(i10, i7, obj)) {
                        g7.i(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 56:
                    if (s(i10, i7, obj)) {
                        g7.f(D(iP & 1048575, obj), i10);
                    }
                    break;
                case 57:
                    if (s(i10, i7, obj)) {
                        g7.e(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 58:
                    if (s(i10, i7, obj)) {
                        g7.a(i10, ((Boolean) r0.c.i(iP & 1048575, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (s(i10, i7, obj)) {
                        S(i10, r0.c.i(iP & 1048575, obj), g7);
                    }
                    break;
                case 60:
                    if (s(i10, i7, obj)) {
                        g7.k(i10, r0.c.i(iP & 1048575, obj), n(i7));
                    }
                    break;
                case 61:
                    if (s(i10, i7, obj)) {
                        g7.b(i10, (C0944g) r0.c.i(iP & 1048575, obj));
                    }
                    break;
                case 62:
                    if (s(i10, i7, obj)) {
                        g7.p(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 63:
                    if (s(i10, i7, obj)) {
                        g7.d(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 64:
                    if (s(i10, i7, obj)) {
                        g7.l(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 65:
                    if (s(i10, i7, obj)) {
                        g7.m(D(iP & 1048575, obj), i10);
                    }
                    break;
                case 66:
                    if (s(i10, i7, obj)) {
                        g7.n(i10, C(iP & 1048575, obj));
                    }
                    break;
                case 67:
                    if (s(i10, i7, obj)) {
                        g7.o(D(iP & 1048575, obj), i10);
                    }
                    break;
                case 68:
                    if (s(i10, i7, obj)) {
                        g7.h(i10, r0.c.i(iP & 1048575, obj), n(i7));
                    }
                    break;
            }
        }
        this.f11811m.getClass();
        ((AbstractC0957u) obj).unknownFields.d(g7);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00e1 A[PHI: r3
  0x00e1: PHI (r3v32 int) = (r3v10 int), (r3v33 int) binds: [B:83:0x0216, B:41:0x00df] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.datastore.preferences.protobuf.Y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(androidx.datastore.preferences.protobuf.AbstractC0957u r12) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.c(androidx.datastore.preferences.protobuf.u):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    @Override // androidx.datastore.preferences.protobuf.Y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.d(java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    @Override // androidx.datastore.preferences.protobuf.Y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(androidx.datastore.preferences.protobuf.AbstractC0957u r12, java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.e(androidx.datastore.preferences.protobuf.u, java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00d0  */
    @Override // androidx.datastore.preferences.protobuf.Y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.f(java.lang.Object):boolean");
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final int g(AbstractC0957u abstractC0957u) {
        return this.f11806g ? p(abstractC0957u) : o(abstractC0957u);
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final AbstractC0957u h() {
        this.f11810k.getClass();
        return ((AbstractC0957u) this.f11804e).k();
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void i(Object obj, E5.z zVar, C0950m c0950m) {
        int iO;
        AbstractC0947j abstractC0947j;
        E e10;
        c0950m.getClass();
        if (!r(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + obj);
        }
        j0 j0Var = this.f11811m;
        int[] iArr = this.f11807h;
        int i7 = this.f11809j;
        int i10 = this.f11808i;
        i0 i0VarA = null;
        while (true) {
            try {
                int iE = zVar.e();
                int iE2 = E(iE);
                if (iE2 >= 0) {
                    int iP = P(iE2);
                    try {
                        iO = O(iP);
                        abstractC0947j = (AbstractC0947j) zVar.f1842e;
                        e10 = this.l;
                    } catch (C0960x unused) {
                        j0Var.getClass();
                        if (i0VarA == null) {
                            i0VarA = j0.a(obj);
                        }
                        if (!j0.c(i0VarA, zVar)) {
                            while (i10 < i7) {
                                k(obj, iArr[i10], i0VarA);
                                i10++;
                            }
                            if (i0VarA == null) {
                                return;
                            }
                            ((AbstractC0957u) obj).unknownFields = i0VarA;
                            return;
                        }
                    }
                    switch (iO) {
                        case 0:
                            long jB = B(iP);
                            zVar.D(1);
                            r0.c.m(obj, jB, abstractC0947j.h());
                            K(iE2, obj);
                            continue;
                        case 1:
                            long jB2 = B(iP);
                            zVar.D(5);
                            r0.c.n(obj, jB2, abstractC0947j.l());
                            K(iE2, obj);
                            continue;
                        case 2:
                            long jB3 = B(iP);
                            zVar.D(0);
                            r0.r(obj, jB3, abstractC0947j.n());
                            K(iE2, obj);
                            continue;
                        case 3:
                            long jB4 = B(iP);
                            zVar.D(0);
                            r0.r(obj, jB4, abstractC0947j.w());
                            K(iE2, obj);
                            continue;
                        case 4:
                            long jB5 = B(iP);
                            zVar.D(0);
                            r0.q(abstractC0947j.m(), jB5, obj);
                            K(iE2, obj);
                            continue;
                        case 5:
                            long jB6 = B(iP);
                            zVar.D(1);
                            r0.r(obj, jB6, abstractC0947j.k());
                            K(iE2, obj);
                            continue;
                        case 6:
                            long jB7 = B(iP);
                            zVar.D(5);
                            r0.q(abstractC0947j.j(), jB7, obj);
                            K(iE2, obj);
                            continue;
                        case 7:
                            long jB8 = B(iP);
                            zVar.D(0);
                            r0.c.k(obj, jB8, abstractC0947j.f());
                            K(iE2, obj);
                            continue;
                        case 8:
                            H(obj, iP, zVar);
                            K(iE2, obj);
                            continue;
                        case 9:
                            AbstractC0937a abstractC0937a = (AbstractC0937a) x(iE2, obj);
                            Y yN = n(iE2);
                            zVar.D(2);
                            zVar.i(abstractC0937a, yN, c0950m);
                            M(obj, iE2, abstractC0937a);
                            continue;
                        case 10:
                            r0.s(B(iP), obj, zVar.k());
                            K(iE2, obj);
                            continue;
                        case 11:
                            long jB9 = B(iP);
                            zVar.D(0);
                            r0.q(abstractC0947j.v(), jB9, obj);
                            K(iE2, obj);
                            continue;
                        case 12:
                            zVar.D(0);
                            int i11 = abstractC0947j.i();
                            l(iE2);
                            r0.q(i11, B(iP), obj);
                            K(iE2, obj);
                            continue;
                        case 13:
                            long jB10 = B(iP);
                            zVar.D(5);
                            r0.q(abstractC0947j.o(), jB10, obj);
                            K(iE2, obj);
                            continue;
                        case 14:
                            long jB11 = B(iP);
                            zVar.D(1);
                            r0.r(obj, jB11, abstractC0947j.p());
                            K(iE2, obj);
                            continue;
                        case 15:
                            long jB12 = B(iP);
                            zVar.D(0);
                            r0.q(abstractC0947j.q(), jB12, obj);
                            K(iE2, obj);
                            continue;
                        case 16:
                            long jB13 = B(iP);
                            zVar.D(0);
                            r0.r(obj, jB13, abstractC0947j.r());
                            K(iE2, obj);
                            continue;
                        case 17:
                            AbstractC0937a abstractC0937a2 = (AbstractC0937a) x(iE2, obj);
                            Y yN2 = n(iE2);
                            zVar.D(3);
                            zVar.h(abstractC0937a2, yN2, c0950m);
                            M(obj, iE2, abstractC0937a2);
                            continue;
                        case 18:
                            zVar.m(e10.c(B(iP), obj));
                            continue;
                        case 19:
                            zVar.r(e10.c(B(iP), obj));
                            continue;
                        case 20:
                            zVar.t(e10.c(B(iP), obj));
                            continue;
                        case 21:
                            zVar.A(e10.c(B(iP), obj));
                            continue;
                        case 22:
                            zVar.s(e10.c(B(iP), obj));
                            continue;
                        case 23:
                            zVar.q(e10.c(B(iP), obj));
                            continue;
                        case 24:
                            zVar.p(e10.c(B(iP), obj));
                            continue;
                        case 25:
                            zVar.j(e10.c(B(iP), obj));
                            continue;
                        case 26:
                            I(obj, iP, zVar);
                            continue;
                        case 27:
                            G(obj, iP, zVar, n(iE2), c0950m);
                            continue;
                        case 28:
                            zVar.l(e10.c(B(iP), obj));
                            continue;
                        case 29:
                            zVar.z(e10.c(B(iP), obj));
                            continue;
                        case 30:
                            List listC = e10.c(B(iP), obj);
                            zVar.n(listC);
                            l(iE2);
                            Z.z(obj, iE, listC, i0VarA, j0Var);
                            continue;
                        case 31:
                            zVar.u(e10.c(B(iP), obj));
                            continue;
                        case 32:
                            zVar.v(e10.c(B(iP), obj));
                            continue;
                        case 33:
                            zVar.w(e10.c(B(iP), obj));
                            continue;
                        case 34:
                            zVar.x(e10.c(B(iP), obj));
                            continue;
                        case 35:
                            zVar.m(e10.c(B(iP), obj));
                            continue;
                        case 36:
                            zVar.r(e10.c(B(iP), obj));
                            continue;
                        case 37:
                            zVar.t(e10.c(B(iP), obj));
                            continue;
                        case 38:
                            zVar.A(e10.c(B(iP), obj));
                            continue;
                        case 39:
                            zVar.s(e10.c(B(iP), obj));
                            continue;
                        case 40:
                            zVar.q(e10.c(B(iP), obj));
                            continue;
                        case 41:
                            zVar.p(e10.c(B(iP), obj));
                            continue;
                        case 42:
                            zVar.j(e10.c(B(iP), obj));
                            continue;
                        case 43:
                            zVar.z(e10.c(B(iP), obj));
                            continue;
                        case 44:
                            List listC2 = e10.c(B(iP), obj);
                            zVar.n(listC2);
                            l(iE2);
                            Z.z(obj, iE, listC2, i0VarA, j0Var);
                            continue;
                        case 45:
                            zVar.u(e10.c(B(iP), obj));
                            continue;
                        case 46:
                            zVar.v(e10.c(B(iP), obj));
                            continue;
                        case 47:
                            zVar.w(e10.c(B(iP), obj));
                            continue;
                        case 48:
                            zVar.x(e10.c(B(iP), obj));
                            continue;
                        case 49:
                            F(obj, B(iP), zVar, n(iE2), c0950m);
                            continue;
                        case 50:
                            u(obj, iE2, m(iE2), c0950m, zVar);
                            continue;
                        case 51:
                            long jB14 = B(iP);
                            zVar.D(1);
                            r0.s(jB14, obj, Double.valueOf(abstractC0947j.h()));
                            L(iE, iE2, obj);
                            continue;
                        case 52:
                            long jB15 = B(iP);
                            zVar.D(5);
                            r0.s(jB15, obj, Float.valueOf(abstractC0947j.l()));
                            L(iE, iE2, obj);
                            continue;
                        case 53:
                            long jB16 = B(iP);
                            zVar.D(0);
                            r0.s(jB16, obj, Long.valueOf(abstractC0947j.n()));
                            L(iE, iE2, obj);
                            continue;
                        case 54:
                            long jB17 = B(iP);
                            zVar.D(0);
                            r0.s(jB17, obj, Long.valueOf(abstractC0947j.w()));
                            L(iE, iE2, obj);
                            continue;
                        case 55:
                            long jB18 = B(iP);
                            zVar.D(0);
                            r0.s(jB18, obj, Integer.valueOf(abstractC0947j.m()));
                            L(iE, iE2, obj);
                            continue;
                        case 56:
                            long jB19 = B(iP);
                            zVar.D(1);
                            r0.s(jB19, obj, Long.valueOf(abstractC0947j.k()));
                            L(iE, iE2, obj);
                            continue;
                        case 57:
                            long jB20 = B(iP);
                            zVar.D(5);
                            r0.s(jB20, obj, Integer.valueOf(abstractC0947j.j()));
                            L(iE, iE2, obj);
                            continue;
                        case 58:
                            long jB21 = B(iP);
                            zVar.D(0);
                            r0.s(jB21, obj, Boolean.valueOf(abstractC0947j.f()));
                            L(iE, iE2, obj);
                            continue;
                        case 59:
                            H(obj, iP, zVar);
                            L(iE, iE2, obj);
                            continue;
                        case 60:
                            AbstractC0937a abstractC0937a3 = (AbstractC0937a) y(iE, iE2, obj);
                            Y yN3 = n(iE2);
                            zVar.D(2);
                            zVar.i(abstractC0937a3, yN3, c0950m);
                            N(obj, iE, iE2, abstractC0937a3);
                            continue;
                        case 61:
                            r0.s(B(iP), obj, zVar.k());
                            L(iE, iE2, obj);
                            continue;
                        case 62:
                            long jB22 = B(iP);
                            zVar.D(0);
                            r0.s(jB22, obj, Integer.valueOf(abstractC0947j.v()));
                            L(iE, iE2, obj);
                            continue;
                        case 63:
                            zVar.D(0);
                            int i12 = abstractC0947j.i();
                            l(iE2);
                            r0.s(B(iP), obj, Integer.valueOf(i12));
                            L(iE, iE2, obj);
                            continue;
                        case 64:
                            long jB23 = B(iP);
                            zVar.D(5);
                            r0.s(jB23, obj, Integer.valueOf(abstractC0947j.o()));
                            L(iE, iE2, obj);
                            continue;
                        case 65:
                            long jB24 = B(iP);
                            zVar.D(1);
                            r0.s(jB24, obj, Long.valueOf(abstractC0947j.p()));
                            L(iE, iE2, obj);
                            continue;
                        case 66:
                            long jB25 = B(iP);
                            zVar.D(0);
                            r0.s(jB25, obj, Integer.valueOf(abstractC0947j.q()));
                            L(iE, iE2, obj);
                            continue;
                        case 67:
                            long jB26 = B(iP);
                            zVar.D(0);
                            r0.s(jB26, obj, Long.valueOf(abstractC0947j.r()));
                            L(iE, iE2, obj);
                            continue;
                        case 68:
                            AbstractC0937a abstractC0937a4 = (AbstractC0937a) y(iE, iE2, obj);
                            Y yN4 = n(iE2);
                            zVar.D(3);
                            zVar.h(abstractC0937a4, yN4, c0950m);
                            N(obj, iE, iE2, abstractC0937a4);
                            continue;
                        default:
                            if (i0VarA == null) {
                                j0Var.getClass();
                                i0VarA = j0.a(obj);
                            }
                            j0Var.getClass();
                            if (!j0.c(i0VarA, zVar)) {
                                while (i10 < i7) {
                                    k(obj, iArr[i10], i0VarA);
                                    i10++;
                                }
                                if (i0VarA == null) {
                                    return;
                                }
                            }
                            break;
                    }
                } else if (iE == Integer.MAX_VALUE) {
                    while (i10 < i7) {
                        k(obj, iArr[i10], i0VarA);
                        i10++;
                    }
                    if (i0VarA == null) {
                        return;
                    } else {
                        j0Var.getClass();
                    }
                } else {
                    j0Var.getClass();
                    if (i0VarA == null) {
                        i0VarA = j0.a(obj);
                    }
                    if (!j0.c(i0VarA, zVar)) {
                        while (i10 < i7) {
                            k(obj, iArr[i10], i0VarA);
                            i10++;
                        }
                        if (i0VarA == null) {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                while (i10 < i7) {
                    k(obj, iArr[i10], i0VarA);
                    i10++;
                }
                if (i0VarA != null) {
                    j0Var.getClass();
                    ((AbstractC0957u) obj).unknownFields = i0VarA;
                }
                throw th;
            }
        }
    }

    public final boolean j(AbstractC0957u abstractC0957u, Object obj, int i7) {
        return q(i7, abstractC0957u) == q(i7, obj);
    }

    public final void k(Object obj, int i7, Object obj2) {
        int i10 = this.f11802a[i7];
        if (r0.c.i(P(i7) & 1048575, obj) == null) {
            return;
        }
        l(i7);
    }

    public final void l(int i7) {
        if (this.f11803b[((i7 / 3) * 2) + 1] != null) {
            throw new ClassCastException();
        }
    }

    public final Object m(int i7) {
        return this.f11803b[(i7 / 3) * 2];
    }

    public final Y n(int i7) {
        int i10 = (i7 / 3) * 2;
        Object[] objArr = this.f11803b;
        Y y10 = (Y) objArr[i10];
        if (y10 != null) {
            return y10;
        }
        Y yA = V.c.a((Class) objArr[i10 + 1]);
        objArr[i10] = yA;
        return yA;
    }

    public final int o(AbstractC0957u abstractC0957u) {
        int i7;
        int iO;
        int iM;
        Unsafe unsafe = f11801p;
        int i10 = 0;
        int iB = 0;
        int i11 = 0;
        int i12 = 1048575;
        while (true) {
            int[] iArr = this.f11802a;
            if (i10 >= iArr.length) {
                this.f11811m.getClass();
                return abstractC0957u.unknownFields.b() + iB;
            }
            int iP = P(i10);
            int i13 = iArr[i10];
            int iO2 = O(iP);
            if (iO2 <= 17) {
                int i14 = iArr[i10 + 2];
                int i15 = i14 & 1048575;
                i7 = 1 << (i14 >>> 20);
                if (i15 != i12) {
                    i11 = unsafe.getInt(abstractC0957u, i15);
                    i12 = i15;
                }
            } else {
                i7 = 0;
            }
            long j3 = iP & 1048575;
            switch (iO2) {
                case 0:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.o(i13);
                        iB += iO;
                    }
                    break;
                case 1:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.s(i13);
                        iB += iO;
                    }
                    break;
                case 2:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.w(unsafe.getLong(abstractC0957u, j3), i13);
                        iB += iO;
                    }
                    break;
                case 3:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.G(unsafe.getLong(abstractC0957u, j3), i13);
                        iB += iO;
                    }
                    break;
                case 4:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.u(i13, unsafe.getInt(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 5:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.r(i13);
                        iB += iO;
                    }
                    break;
                case 6:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.q(i13);
                        iB += iO;
                    }
                    break;
                case 7:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.l(i13);
                        iB += iO;
                    }
                    break;
                case 8:
                    if ((i7 & i11) != 0) {
                        Object object = unsafe.getObject(abstractC0957u, j3);
                        iM = object instanceof C0944g ? C0948k.m(i13, (C0944g) object) : C0948k.B(i13, (String) object);
                        iB = iM + iB;
                    }
                    break;
                case 9:
                    if ((i7 & i11) != 0) {
                        iO = Z.o(i13, unsafe.getObject(abstractC0957u, j3), n(i10));
                        iB += iO;
                    }
                    break;
                case 10:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.m(i13, (C0944g) unsafe.getObject(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 11:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.E(i13, unsafe.getInt(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 12:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.p(i13, unsafe.getInt(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 13:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.x(i13);
                        iB += iO;
                    }
                    break;
                case 14:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.y(i13);
                        iB += iO;
                    }
                    break;
                case 15:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.z(i13, unsafe.getInt(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 16:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.A(unsafe.getLong(abstractC0957u, j3), i13);
                        iB += iO;
                    }
                    break;
                case 17:
                    if ((i7 & i11) != 0) {
                        iO = C0948k.t(i13, (AbstractC0937a) unsafe.getObject(abstractC0957u, j3), n(i10));
                        iB += iO;
                    }
                    break;
                case 18:
                    iO = Z.h(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 19:
                    iO = Z.f(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 20:
                    iO = Z.m(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 21:
                    iO = Z.x(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 22:
                    iO = Z.k(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 23:
                    iO = Z.h(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 24:
                    iO = Z.f(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 25:
                    iO = Z.a(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 26:
                    iO = Z.u(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 27:
                    iO = Z.p(i13, (List) unsafe.getObject(abstractC0957u, j3), n(i10));
                    iB += iO;
                    break;
                case 28:
                    iO = Z.c(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 29:
                    iO = Z.v(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 30:
                    iO = Z.d(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 31:
                    iO = Z.f(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 32:
                    iO = Z.h(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 33:
                    iO = Z.q(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 34:
                    iO = Z.s(i13, (List) unsafe.getObject(abstractC0957u, j3));
                    iB += iO;
                    break;
                case 35:
                    int i16 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i16 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i16, C0948k.D(i13), i16, iB);
                    }
                    break;
                case 36:
                    int iG = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG, C0948k.D(i13), iG, iB);
                    }
                    break;
                case 37:
                    int iN = Z.n((List) unsafe.getObject(abstractC0957u, j3));
                    if (iN > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iN, C0948k.D(i13), iN, iB);
                    }
                    break;
                case 38:
                    int iY = Z.y((List) unsafe.getObject(abstractC0957u, j3));
                    if (iY > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iY, C0948k.D(i13), iY, iB);
                    }
                    break;
                case 39:
                    int iL = Z.l((List) unsafe.getObject(abstractC0957u, j3));
                    if (iL > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iL, C0948k.D(i13), iL, iB);
                    }
                    break;
                case 40:
                    int i17 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i17 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i17, C0948k.D(i13), i17, iB);
                    }
                    break;
                case 41:
                    int iG2 = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG2 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG2, C0948k.D(i13), iG2, iB);
                    }
                    break;
                case 42:
                    int iB2 = Z.b((List) unsafe.getObject(abstractC0957u, j3));
                    if (iB2 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iB2, C0948k.D(i13), iB2, iB);
                    }
                    break;
                case 43:
                    int iW = Z.w((List) unsafe.getObject(abstractC0957u, j3));
                    if (iW > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iW, C0948k.D(i13), iW, iB);
                    }
                    break;
                case 44:
                    int iE = Z.e((List) unsafe.getObject(abstractC0957u, j3));
                    if (iE > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iE, C0948k.D(i13), iE, iB);
                    }
                    break;
                case 45:
                    int iG3 = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG3 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG3, C0948k.D(i13), iG3, iB);
                    }
                    break;
                case 46:
                    int i18 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i18 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i18, C0948k.D(i13), i18, iB);
                    }
                    break;
                case 47:
                    int iR = Z.r((List) unsafe.getObject(abstractC0957u, j3));
                    if (iR > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iR, C0948k.D(i13), iR, iB);
                    }
                    break;
                case 48:
                    int iT = Z.t((List) unsafe.getObject(abstractC0957u, j3));
                    if (iT > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iT, C0948k.D(i13), iT, iB);
                    }
                    break;
                case 49:
                    iO = Z.j(i13, (List) unsafe.getObject(abstractC0957u, j3), n(i10));
                    iB += iO;
                    break;
                case 50:
                    Object object2 = unsafe.getObject(abstractC0957u, j3);
                    Object objM = m(i10);
                    this.f11812n.getClass();
                    iO = K.a(object2, i13, objM);
                    iB += iO;
                    break;
                case 51:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.o(i13);
                        iB += iO;
                    }
                    break;
                case 52:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.s(i13);
                        iB += iO;
                    }
                    break;
                case 53:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.w(D(j3, abstractC0957u), i13);
                        iB += iO;
                    }
                    break;
                case 54:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.G(D(j3, abstractC0957u), i13);
                        iB += iO;
                    }
                    break;
                case 55:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.u(i13, C(j3, abstractC0957u));
                        iB += iO;
                    }
                    break;
                case 56:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.r(i13);
                        iB += iO;
                    }
                    break;
                case 57:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.q(i13);
                        iB += iO;
                    }
                    break;
                case 58:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.l(i13);
                        iB += iO;
                    }
                    break;
                case 59:
                    if (s(i13, i10, abstractC0957u)) {
                        Object object3 = unsafe.getObject(abstractC0957u, j3);
                        iM = object3 instanceof C0944g ? C0948k.m(i13, (C0944g) object3) : C0948k.B(i13, (String) object3);
                        iB = iM + iB;
                    }
                    break;
                case 60:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = Z.o(i13, unsafe.getObject(abstractC0957u, j3), n(i10));
                        iB += iO;
                    }
                    break;
                case 61:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.m(i13, (C0944g) unsafe.getObject(abstractC0957u, j3));
                        iB += iO;
                    }
                    break;
                case 62:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.E(i13, C(j3, abstractC0957u));
                        iB += iO;
                    }
                    break;
                case 63:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.p(i13, C(j3, abstractC0957u));
                        iB += iO;
                    }
                    break;
                case 64:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.x(i13);
                        iB += iO;
                    }
                    break;
                case 65:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.y(i13);
                        iB += iO;
                    }
                    break;
                case 66:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.z(i13, C(j3, abstractC0957u));
                        iB += iO;
                    }
                    break;
                case 67:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.A(D(j3, abstractC0957u), i13);
                        iB += iO;
                    }
                    break;
                case 68:
                    if (s(i13, i10, abstractC0957u)) {
                        iO = C0948k.t(i13, (AbstractC0937a) unsafe.getObject(abstractC0957u, j3), n(i10));
                        iB += iO;
                    }
                    break;
            }
            i10 += 3;
        }
    }

    public final int p(AbstractC0957u abstractC0957u) {
        int iO;
        Unsafe unsafe = f11801p;
        int i7 = 0;
        int iB = 0;
        while (true) {
            int[] iArr = this.f11802a;
            if (i7 >= iArr.length) {
                this.f11811m.getClass();
                return abstractC0957u.unknownFields.b() + iB;
            }
            int iP = P(i7);
            int iO2 = O(iP);
            int i10 = iArr[i7];
            long j3 = iP & 1048575;
            if (iO2 >= EnumC0954q.f11877m.a() && iO2 <= EnumC0954q.f11878n.a()) {
                int i11 = iArr[i7 + 2];
            }
            switch (iO2) {
                case 0:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.o(i10);
                    }
                    break;
                case 1:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.s(i10);
                    }
                    break;
                case 2:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.w(r0.k(abstractC0957u, j3), i10);
                    }
                    break;
                case 3:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.G(r0.k(abstractC0957u, j3), i10);
                    }
                    break;
                case 4:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.u(i10, r0.j(abstractC0957u, j3));
                    }
                    break;
                case 5:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.r(i10);
                    }
                    break;
                case 6:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.q(i10);
                    }
                    break;
                case 7:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.l(i10);
                    }
                    break;
                case 8:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        Object objL = r0.l(abstractC0957u, j3);
                        iO = !(objL instanceof C0944g) ? C0948k.B(i10, (String) objL) : C0948k.m(i10, (C0944g) objL);
                    }
                    break;
                case 9:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = Z.o(i10, r0.l(abstractC0957u, j3), n(i7));
                    }
                    break;
                case 10:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.m(i10, (C0944g) r0.l(abstractC0957u, j3));
                    }
                    break;
                case 11:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.E(i10, r0.j(abstractC0957u, j3));
                    }
                    break;
                case 12:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.p(i10, r0.j(abstractC0957u, j3));
                    }
                    break;
                case 13:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.x(i10);
                    }
                    break;
                case 14:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.y(i10);
                    }
                    break;
                case 15:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.z(i10, r0.j(abstractC0957u, j3));
                    }
                    break;
                case 16:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.A(r0.k(abstractC0957u, j3), i10);
                    }
                    break;
                case 17:
                    if (!q(i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.t(i10, (AbstractC0937a) r0.l(abstractC0957u, j3), n(i7));
                    }
                    break;
                case 18:
                    iO = Z.h(i10, t(abstractC0957u, j3));
                    break;
                case 19:
                    iO = Z.f(i10, t(abstractC0957u, j3));
                    break;
                case 20:
                    iO = Z.m(i10, t(abstractC0957u, j3));
                    break;
                case 21:
                    iO = Z.x(i10, t(abstractC0957u, j3));
                    break;
                case 22:
                    iO = Z.k(i10, t(abstractC0957u, j3));
                    break;
                case 23:
                    iO = Z.h(i10, t(abstractC0957u, j3));
                    break;
                case 24:
                    iO = Z.f(i10, t(abstractC0957u, j3));
                    break;
                case 25:
                    iO = Z.a(i10, t(abstractC0957u, j3));
                    break;
                case 26:
                    iO = Z.u(i10, t(abstractC0957u, j3));
                    break;
                case 27:
                    iO = Z.p(i10, t(abstractC0957u, j3), n(i7));
                    break;
                case 28:
                    iO = Z.c(i10, t(abstractC0957u, j3));
                    break;
                case 29:
                    iO = Z.v(i10, t(abstractC0957u, j3));
                    break;
                case 30:
                    iO = Z.d(i10, t(abstractC0957u, j3));
                    break;
                case 31:
                    iO = Z.f(i10, t(abstractC0957u, j3));
                    break;
                case 32:
                    iO = Z.h(i10, t(abstractC0957u, j3));
                    break;
                case 33:
                    iO = Z.q(i10, t(abstractC0957u, j3));
                    break;
                case 34:
                    iO = Z.s(i10, t(abstractC0957u, j3));
                    break;
                case 35:
                    int i12 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i12 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i12, C0948k.D(i10), i12, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 36:
                    int iG = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG, C0948k.D(i10), iG, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 37:
                    int iN = Z.n((List) unsafe.getObject(abstractC0957u, j3));
                    if (iN > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iN, C0948k.D(i10), iN, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 38:
                    int iY = Z.y((List) unsafe.getObject(abstractC0957u, j3));
                    if (iY > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iY, C0948k.D(i10), iY, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 39:
                    int iL = Z.l((List) unsafe.getObject(abstractC0957u, j3));
                    if (iL > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iL, C0948k.D(i10), iL, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 40:
                    int i13 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i13 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i13, C0948k.D(i10), i13, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 41:
                    int iG2 = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG2 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG2, C0948k.D(i10), iG2, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 42:
                    int iB2 = Z.b((List) unsafe.getObject(abstractC0957u, j3));
                    if (iB2 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iB2, C0948k.D(i10), iB2, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 43:
                    int iW = Z.w((List) unsafe.getObject(abstractC0957u, j3));
                    if (iW > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iW, C0948k.D(i10), iW, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 44:
                    int iE = Z.e((List) unsafe.getObject(abstractC0957u, j3));
                    if (iE > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iE, C0948k.D(i10), iE, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 45:
                    int iG3 = Z.g((List) unsafe.getObject(abstractC0957u, j3));
                    if (iG3 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iG3, C0948k.D(i10), iG3, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 46:
                    int i14 = Z.i((List) unsafe.getObject(abstractC0957u, j3));
                    if (i14 > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(i14, C0948k.D(i10), i14, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 47:
                    int iR = Z.r((List) unsafe.getObject(abstractC0957u, j3));
                    if (iR > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iR, C0948k.D(i10), iR, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 48:
                    int iT = Z.t((List) unsafe.getObject(abstractC0957u, j3));
                    if (iT > 0) {
                        iB = android.hardware.bydauto.ac.tsimpl.a.b(iT, C0948k.D(i10), iT, iB);
                    } else {
                        continue;
                    }
                    i7 += 3;
                    break;
                case 49:
                    iO = Z.j(i10, t(abstractC0957u, j3), n(i7));
                    break;
                case 50:
                    Object objL2 = r0.l(abstractC0957u, j3);
                    Object objM = m(i7);
                    this.f11812n.getClass();
                    iO = K.a(objL2, i10, objM);
                    break;
                case 51:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.o(i10);
                    }
                    break;
                case 52:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.s(i10);
                    }
                    break;
                case 53:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.w(D(j3, abstractC0957u), i10);
                    }
                    break;
                case 54:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.G(D(j3, abstractC0957u), i10);
                    }
                    break;
                case 55:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.u(i10, C(j3, abstractC0957u));
                    }
                    break;
                case 56:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.r(i10);
                    }
                    break;
                case 57:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.q(i10);
                    }
                    break;
                case 58:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.l(i10);
                    }
                    break;
                case 59:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        Object objL3 = r0.l(abstractC0957u, j3);
                        iO = !(objL3 instanceof C0944g) ? C0948k.B(i10, (String) objL3) : C0948k.m(i10, (C0944g) objL3);
                    }
                    break;
                case 60:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = Z.o(i10, r0.l(abstractC0957u, j3), n(i7));
                    }
                    break;
                case 61:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.m(i10, (C0944g) r0.l(abstractC0957u, j3));
                    }
                    break;
                case 62:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.E(i10, C(j3, abstractC0957u));
                    }
                    break;
                case 63:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.p(i10, C(j3, abstractC0957u));
                    }
                    break;
                case 64:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.x(i10);
                    }
                    break;
                case 65:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.y(i10);
                    }
                    break;
                case 66:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.z(i10, C(j3, abstractC0957u));
                    }
                    break;
                case 67:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.A(D(j3, abstractC0957u), i10);
                    }
                    break;
                case 68:
                    if (!s(i10, i7, abstractC0957u)) {
                        i7 += 3;
                    } else {
                        iO = C0948k.t(i10, (AbstractC0937a) r0.l(abstractC0957u, j3), n(i7));
                    }
                    break;
                default:
                    i7 += 3;
                    break;
            }
            iB = iO + iB;
            i7 += 3;
        }
    }

    public final boolean q(int i7, Object obj) {
        int i10 = this.f11802a[i7 + 2];
        long j3 = i10 & 1048575;
        if (j3 != 1048575) {
            return ((1 << (i10 >>> 20)) & r0.c.g(j3, obj)) != 0;
        }
        int iP = P(i7);
        long j10 = iP & 1048575;
        switch (O(iP)) {
            case 0:
                return Double.doubleToRawLongBits(r0.c.e(j10, obj)) != 0;
            case 1:
                return Float.floatToRawIntBits(r0.c.f(j10, obj)) != 0;
            case 2:
                return r0.c.h(j10, obj) != 0;
            case 3:
                return r0.c.h(j10, obj) != 0;
            case 4:
                return r0.c.g(j10, obj) != 0;
            case 5:
                return r0.c.h(j10, obj) != 0;
            case 6:
                return r0.c.g(j10, obj) != 0;
            case 7:
                return r0.c.c(j10, obj);
            case 8:
                Object objI = r0.c.i(j10, obj);
                if (objI instanceof String) {
                    return !((String) objI).isEmpty();
                }
                if (objI instanceof C0944g) {
                    return !C0944g.f11844n.equals(objI);
                }
                throw new IllegalArgumentException();
            case 9:
                return r0.c.i(j10, obj) != null;
            case 10:
                return !C0944g.f11844n.equals(r0.c.i(j10, obj));
            case 11:
                return r0.c.g(j10, obj) != 0;
            case 12:
                return r0.c.g(j10, obj) != 0;
            case 13:
                return r0.c.g(j10, obj) != 0;
            case 14:
                return r0.c.h(j10, obj) != 0;
            case 15:
                return r0.c.g(j10, obj) != 0;
            case 16:
                return r0.c.h(j10, obj) != 0;
            case 17:
                return r0.c.i(j10, obj) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean s(int i7, int i10, Object obj) {
        return r0.c.g((long) (this.f11802a[i10 + 2] & 1048575), obj) == i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x009b, code lost:
    
        r9.put(r1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009e, code lost:
    
        r10.d(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a1, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(java.lang.Object r8, int r9, java.lang.Object r10, androidx.datastore.preferences.protobuf.C0950m r11, E5.z r12) throws androidx.datastore.preferences.protobuf.C0960x {
        /*
            r7 = this;
            int r9 = r7.P(r9)
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r0
            long r0 = (long) r9
            androidx.datastore.preferences.protobuf.q0 r9 = androidx.datastore.preferences.protobuf.r0.c
            java.lang.Object r9 = r9.i(r0, r8)
            androidx.datastore.preferences.protobuf.K r7 = r7.f11812n
            if (r9 != 0) goto L20
            r7.getClass()
            androidx.datastore.preferences.protobuf.J r9 = androidx.datastore.preferences.protobuf.J.f11797m
            androidx.datastore.preferences.protobuf.J r9 = r9.c()
            androidx.datastore.preferences.protobuf.r0.s(r0, r8, r9)
            goto L37
        L20:
            r7.getClass()
            r2 = r9
            androidx.datastore.preferences.protobuf.J r2 = (androidx.datastore.preferences.protobuf.J) r2
            boolean r2 = r2.l
            if (r2 != 0) goto L37
            androidx.datastore.preferences.protobuf.J r2 = androidx.datastore.preferences.protobuf.J.f11797m
            androidx.datastore.preferences.protobuf.J r2 = r2.c()
            androidx.datastore.preferences.protobuf.K.b(r2, r9)
            androidx.datastore.preferences.protobuf.r0.s(r0, r8, r2)
            r9 = r2
        L37:
            r7.getClass()
            androidx.datastore.preferences.protobuf.J r9 = (androidx.datastore.preferences.protobuf.J) r9
            androidx.datastore.preferences.protobuf.I r10 = (androidx.datastore.preferences.protobuf.I) r10
            androidx.datastore.preferences.protobuf.H r7 = r10.f11796a
            r8 = 2
            r12.D(r8)
            java.lang.Object r10 = r12.f1842e
            androidx.datastore.preferences.protobuf.j r10 = (androidx.datastore.preferences.protobuf.AbstractC0947j) r10
            int r0 = r10.v()
            int r0 = r10.e(r0)
            java.lang.String r1 = ""
            R1.h r2 = r7.c
            r3 = r2
        L55:
            int r4 = r12.e()     // Catch: java.lang.Throwable -> L79
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L9b
            boolean r5 = r10.c()     // Catch: java.lang.Throwable -> L79
            if (r5 == 0) goto L65
            goto L9b
        L65:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L86
            if (r4 == r8) goto L7b
            boolean r4 = r12.E()     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            if (r4 == 0) goto L73
            goto L55
        L73:
            androidx.datastore.preferences.protobuf.y r4 = new androidx.datastore.preferences.protobuf.y     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            throw r4     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
        L79:
            r7 = move-exception
            goto La2
        L7b:
            androidx.datastore.preferences.protobuf.x0 r4 = r7.f11795b     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            java.lang.Class r5 = r2.getClass()     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            java.lang.Object r3 = r12.o(r4, r5, r11)     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            goto L55
        L86:
            androidx.datastore.preferences.protobuf.v0 r4 = r7.f11794a     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            r5 = 0
            java.lang.Object r1 = r12.o(r4, r5, r5)     // Catch: java.lang.Throwable -> L79 androidx.datastore.preferences.protobuf.C0960x -> L8e
            goto L55
        L8e:
            boolean r4 = r12.E()     // Catch: java.lang.Throwable -> L79
            if (r4 == 0) goto L95
            goto L55
        L95:
            androidx.datastore.preferences.protobuf.y r7 = new androidx.datastore.preferences.protobuf.y     // Catch: java.lang.Throwable -> L79
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L79
            throw r7     // Catch: java.lang.Throwable -> L79
        L9b:
            r9.put(r1, r3)     // Catch: java.lang.Throwable -> L79
            r10.d(r0)
            return
        La2:
            r10.d(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.O.u(java.lang.Object, int, java.lang.Object, androidx.datastore.preferences.protobuf.m, E5.z):void");
    }

    public final void v(Object obj, int i7, Object obj2) {
        if (q(i7, obj2)) {
            long jP = P(i7) & 1048575;
            Unsafe unsafe = f11801p;
            Object object = unsafe.getObject(obj2, jP);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.f11802a[i7] + " is present but null: " + obj2);
            }
            Y yN = n(i7);
            if (!q(i7, obj)) {
                if (r(object)) {
                    AbstractC0957u abstractC0957uH = yN.h();
                    yN.a(abstractC0957uH, object);
                    unsafe.putObject(obj, jP, abstractC0957uH);
                } else {
                    unsafe.putObject(obj, jP, object);
                }
                K(i7, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, jP);
            if (!r(object2)) {
                AbstractC0957u abstractC0957uH2 = yN.h();
                yN.a(abstractC0957uH2, object2);
                unsafe.putObject(obj, jP, abstractC0957uH2);
                object2 = abstractC0957uH2;
            }
            yN.a(object2, object);
        }
    }

    public final void w(Object obj, int i7, Object obj2) {
        int[] iArr = this.f11802a;
        int i10 = iArr[i7];
        if (s(i10, i7, obj2)) {
            long jP = P(i7) & 1048575;
            Unsafe unsafe = f11801p;
            Object object = unsafe.getObject(obj2, jP);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + iArr[i7] + " is present but null: " + obj2);
            }
            Y yN = n(i7);
            if (!s(i10, i7, obj)) {
                if (r(object)) {
                    AbstractC0957u abstractC0957uH = yN.h();
                    yN.a(abstractC0957uH, object);
                    unsafe.putObject(obj, jP, abstractC0957uH);
                } else {
                    unsafe.putObject(obj, jP, object);
                }
                L(i10, i7, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, jP);
            if (!r(object2)) {
                AbstractC0957u abstractC0957uH2 = yN.h();
                yN.a(abstractC0957uH2, object2);
                unsafe.putObject(obj, jP, abstractC0957uH2);
                object2 = abstractC0957uH2;
            }
            yN.a(object2, object);
        }
    }

    public final Object x(int i7, Object obj) {
        Y yN = n(i7);
        long jP = P(i7) & 1048575;
        if (!q(i7, obj)) {
            return yN.h();
        }
        Object object = f11801p.getObject(obj, jP);
        if (r(object)) {
            return object;
        }
        AbstractC0957u abstractC0957uH = yN.h();
        if (object != null) {
            yN.a(abstractC0957uH, object);
        }
        return abstractC0957uH;
    }

    public final Object y(int i7, int i10, Object obj) {
        Y yN = n(i10);
        if (!s(i7, i10, obj)) {
            return yN.h();
        }
        Object object = f11801p.getObject(obj, P(i10) & 1048575);
        if (r(object)) {
            return object;
        }
        AbstractC0957u abstractC0957uH = yN.h();
        if (object != null) {
            yN.a(abstractC0957uH, object);
        }
        return abstractC0957uH;
    }
}

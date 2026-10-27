package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import p.a2;
import p.au;
import p.cr2;
import p.dr2;
import p.gh2;
import p.in1;
import p.j13;
import p.k72;
import p.o02;
import p.oe1;
import p.p01;
import p.p51;
import p.pe1;
import p.rl0;
import p.ry1;
import p.sl0;
import p.t02;
import p.tj1;
import p.u4;
import p.v51;
import p.w51;
import p.wj1;
import p.ya1;
import p.zu;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class k implements k72 {
    public static final int[] o = new int[0];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Unsafe f44p = dr2.m();
    public final int[] a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final wj1 e;
    public final boolean f;
    public final boolean g;
    public final int[] h;
    public final int i;
    public final int j;
    public final in1 k;
    public final ya1 l;
    public final n m;
    public final pe1 n;

    public k(int[] iArr, Object[] objArr, int i, int i2, wj1 wj1Var, boolean z, int[] iArr2, int i3, int i4, in1 in1Var, ya1 ya1Var, n nVar, sl0 sl0Var, pe1 pe1Var) {
        this.a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.f = wj1Var instanceof i;
        this.g = z;
        this.h = iArr2;
        this.i = i3;
        this.j = i4;
        this.k = in1Var;
        this.l = ya1Var;
        this.m = nVar;
        this.e = wj1Var;
        this.n = pe1Var;
    }

    public static k E(tj1 tj1Var, in1 in1Var, ya1 ya1Var, n nVar, sl0 sl0Var, pe1 pe1Var) {
        if (tj1Var instanceof o02) {
            return F((o02) tj1Var, in1Var, ya1Var, nVar, sl0Var, pe1Var);
        }
        u4.z(tj1Var);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x037f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.protobuf.k F(p.o02 r33, p.in1 r34, p.ya1 r35, com.google.protobuf.n r36, p.sl0 r37, p.pe1 r38) {
        /*
            Method dump skipped, instruction units count: 1008
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.F(p.o02, p.in1, p.ya1, com.google.protobuf.n, p.sl0, p.pe1):com.google.protobuf.k");
    }

    public static long G(int i) {
        return i & 1048575;
    }

    public static int H(long j, Object obj) {
        return ((Integer) dr2.c.k(j, obj)).intValue();
    }

    public static long I(long j, Object obj) {
        return ((Long) dr2.c.k(j, obj)).longValue();
    }

    public static Field S(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder sbP = u4.p("Field ", str, " for ");
            sbP.append(cls.getName());
            sbP.append(" not found. Known fields are ");
            sbP.append(Arrays.toString(declaredFields));
            throw new RuntimeException(sbP.toString());
        }
    }

    public static int Y(int i) {
        return (i & 267386880) >>> 20;
    }

    public static void c0(int i, Object obj, p01 p01Var) {
        if (!(obj instanceof String)) {
            p01Var.L(i, (ByteString) obj);
        } else {
            ((h) p01Var.e).e0(i, (String) obj);
        }
    }

    public static void l(Object obj) {
        if (v(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    public static int m(byte[] bArr, int i, int i2, WireFormat$FieldType wireFormat$FieldType, Class cls, j13 j13Var) throws InvalidProtocolBufferException {
        switch (wireFormat$FieldType.ordinal()) {
            case 0:
                j13Var.k = Double.valueOf(Double.longBitsToDouble(zu.z(bArr, i)));
                return i + 8;
            case 1:
                j13Var.k = Float.valueOf(Float.intBitsToFloat(zu.x(bArr, i)));
                return i + 4;
            case 2:
            case 3:
                int iY = zu.Y(bArr, i, j13Var);
                j13Var.k = Long.valueOf(j13Var.e);
                return iY;
            case 4:
            case 12:
            case 13:
                int iW = zu.W(bArr, i, j13Var);
                j13Var.k = Integer.valueOf(j13Var.c);
                return iW;
            case 5:
            case 15:
                j13Var.k = Long.valueOf(zu.z(bArr, i));
                return i + 8;
            case 6:
            case 14:
                j13Var.k = Integer.valueOf(zu.x(bArr, i));
                return i + 4;
            case 7:
                int iY2 = zu.Y(bArr, i, j13Var);
                j13Var.k = Boolean.valueOf(j13Var.e != 0);
                return iY2;
            case 8:
                return zu.T(bArr, i, j13Var);
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                k72 k72VarA = ry1.c.a(cls);
                Object objI = k72VarA.i();
                int iS0 = zu.s0(objI, k72VarA, bArr, i, i2, j13Var);
                k72VarA.c(objI);
                j13Var.k = objI;
                return iS0;
            case 11:
                return zu.t(bArr, i, j13Var);
            case 16:
                int iW2 = zu.W(bArr, i, j13Var);
                j13Var.k = Integer.valueOf(au.b(j13Var.c));
                return iW2;
            case 17:
                int iY3 = zu.Y(bArr, i, j13Var);
                j13Var.k = Long.valueOf(au.c(j13Var.e));
                return iY3;
        }
    }

    public static o r(Object obj) {
        i iVar = (i) obj;
        o oVar = iVar.unknownFields;
        if (oVar != o.f) {
            return oVar;
        }
        o oVar2 = new o();
        iVar.unknownFields = oVar2;
        return oVar2;
    }

    public static boolean v(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof i) {
            return ((i) obj).isMutable();
        }
        return true;
    }

    public static List x(long j, Object obj) {
        return (List) dr2.c.k(j, obj);
    }

    public final void A(Object obj, int i, Object obj2) {
        if (u(i, obj2)) {
            long jZ = Z(i) & 1048575;
            Unsafe unsafe = f44p;
            Object object = unsafe.getObject(obj2, jZ);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.a[i] + " is present but null: " + obj2);
            }
            k72 k72VarQ = q(i);
            if (!u(i, obj)) {
                if (v(object)) {
                    Object objI = k72VarQ.i();
                    k72VarQ.a(objI, object);
                    unsafe.putObject(obj, jZ, objI);
                } else {
                    unsafe.putObject(obj, jZ, object);
                }
                T(i, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, jZ);
            if (!v(object2)) {
                Object objI2 = k72VarQ.i();
                k72VarQ.a(objI2, object2);
                unsafe.putObject(obj, jZ, objI2);
                object2 = objI2;
            }
            k72VarQ.a(object2, object);
        }
    }

    public final void B(Object obj, int i, Object obj2) {
        int[] iArr = this.a;
        int i2 = iArr[i];
        if (w(i2, i, obj2)) {
            long jZ = Z(i) & 1048575;
            Unsafe unsafe = f44p;
            Object object = unsafe.getObject(obj2, jZ);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + iArr[i] + " is present but null: " + obj2);
            }
            k72 k72VarQ = q(i);
            if (!w(i2, i, obj)) {
                if (v(object)) {
                    Object objI = k72VarQ.i();
                    k72VarQ.a(objI, object);
                    unsafe.putObject(obj, jZ, objI);
                } else {
                    unsafe.putObject(obj, jZ, object);
                }
                U(i2, i, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, jZ);
            if (!v(object2)) {
                Object objI2 = k72VarQ.i();
                k72VarQ.a(objI2, object2);
                unsafe.putObject(obj, jZ, objI2);
                object2 = objI2;
            }
            k72VarQ.a(object2, object);
        }
    }

    public final Object C(int i, Object obj) {
        k72 k72VarQ = q(i);
        long jZ = Z(i) & 1048575;
        if (!u(i, obj)) {
            return k72VarQ.i();
        }
        Object object = f44p.getObject(obj, jZ);
        if (v(object)) {
            return object;
        }
        Object objI = k72VarQ.i();
        if (object != null) {
            k72VarQ.a(objI, object);
        }
        return objI;
    }

    public final Object D(int i, int i2, Object obj) {
        k72 k72VarQ = q(i2);
        if (!w(i, i2, obj)) {
            return k72VarQ.i();
        }
        Object object = f44p.getObject(obj, Z(i2) & 1048575);
        if (v(object)) {
            return object;
        }
        Object objI = k72VarQ.i();
        if (object != null) {
            k72VarQ.a(objI, object);
        }
        return objI;
    }

    public final int J(Object obj, byte[] bArr, int i, int i2, int i3, long j, j13 j13Var) throws InvalidProtocolBufferException {
        Object objP = p(i3);
        Unsafe unsafe = f44p;
        Object object = unsafe.getObject(obj, j);
        this.n.getClass();
        if (pe1.d(object)) {
            MapFieldLite mapFieldLiteF = pe1.f();
            pe1.e(mapFieldLiteF, object);
            unsafe.putObject(obj, j, mapFieldLiteF);
            object = mapFieldLiteF;
        }
        gh2 gh2VarA = pe1.a(objP);
        MapFieldLite mapFieldLiteB = pe1.b(object);
        int iW = zu.W(bArr, i, j13Var);
        int i4 = j13Var.c;
        if (i4 < 0 || i4 > i2 - iW) {
            throw InvalidProtocolBufferException.j();
        }
        int i5 = i4 + iW;
        Object obj2 = gh2VarA.k;
        Object obj3 = gh2VarA.m;
        while (iW < i5) {
            int iV = iW + 1;
            int i6 = bArr[iW];
            if (i6 < 0) {
                iV = zu.V(i6, bArr, iV, j13Var);
                i6 = j13Var.c;
            }
            int i7 = iV;
            int i8 = i6 >>> 3;
            int i9 = i6 & 7;
            if (i8 != 1) {
                if (i8 == 2 && i9 == ((WireFormat$FieldType) gh2VarA.l).b()) {
                    iW = m(bArr, i7, i2, (WireFormat$FieldType) gh2VarA.l, gh2VarA.m.getClass(), j13Var);
                    obj3 = j13Var.k;
                } else {
                    iW = zu.E0(i6, bArr, i7, i2, j13Var);
                }
            } else if (i9 == ((WireFormat$FieldType) gh2VarA.e).b()) {
                iW = m(bArr, i7, i2, (WireFormat$FieldType) gh2VarA.e, null, j13Var);
                obj2 = j13Var.k;
            } else {
                iW = zu.E0(i6, bArr, i7, i2, j13Var);
            }
        }
        if (iW != i5) {
            throw InvalidProtocolBufferException.g();
        }
        mapFieldLiteB.put(obj2, obj3);
        return i5;
    }

    public final int K(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, j13 j13Var) throws InvalidProtocolBufferException {
        long j2 = this.a[i8 + 2] & 1048575;
        Unsafe unsafe = f44p;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Double.valueOf(zu.v(bArr, i)));
                int i9 = i + 8;
                unsafe.putInt(obj, j2, i4);
                return i9;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Float.valueOf(zu.B(bArr, i)));
                int i10 = i + 4;
                unsafe.putInt(obj, j2, i4);
                return i10;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iY = zu.Y(bArr, i, j13Var);
                unsafe.putObject(obj, j, Long.valueOf(j13Var.e));
                unsafe.putInt(obj, j2, i4);
                return iY;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iW = zu.W(bArr, i, j13Var);
                unsafe.putObject(obj, j, Integer.valueOf(j13Var.c));
                unsafe.putInt(obj, j2, i4);
                return iW;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Long.valueOf(zu.z(bArr, i)));
                int i11 = i + 8;
                unsafe.putInt(obj, j2, i4);
                return i11;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Integer.valueOf(zu.x(bArr, i)));
                int i12 = i + 4;
                unsafe.putInt(obj, j2, i4);
                return i12;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iY2 = zu.Y(bArr, i, j13Var);
                unsafe.putObject(obj, j, Boolean.valueOf(j13Var.e != 0));
                unsafe.putInt(obj, j2, i4);
                return iY2;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iW2 = zu.W(bArr, i, j13Var);
                int i13 = j13Var.c;
                if (i13 == 0) {
                    unsafe.putObject(obj, j, "");
                } else {
                    if ((i6 & 536870912) != 0 && !r.e(bArr, iW2, iW2 + i13)) {
                        throw InvalidProtocolBufferException.c();
                    }
                    unsafe.putObject(obj, j, new String(bArr, iW2, i13, w51.a));
                    iW2 += i13;
                }
                unsafe.putInt(obj, j2, i4);
                return iW2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                Object objD = D(i4, i8, obj);
                int iS0 = zu.s0(objD, q(i8), bArr, i, i2, j13Var);
                X(i4, i8, obj, objD);
                return iS0;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iT = zu.t(bArr, i, j13Var);
                unsafe.putObject(obj, j, j13Var.k);
                unsafe.putInt(obj, j2, i4);
                return iT;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iW3 = zu.W(bArr, i, j13Var);
                int i14 = j13Var.c;
                p51 p51VarO = o(i8);
                if (p51VarO == null || p51VarO.isInRange(i14)) {
                    unsafe.putObject(obj, j, Integer.valueOf(i14));
                    unsafe.putInt(obj, j2, i4);
                } else {
                    r(obj).f(i3, Long.valueOf(i14));
                }
                return iW3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iW4 = zu.W(bArr, i, j13Var);
                unsafe.putObject(obj, j, Integer.valueOf(au.b(j13Var.c)));
                unsafe.putInt(obj, j2, i4);
                return iW4;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                int iY3 = zu.Y(bArr, i, j13Var);
                unsafe.putObject(obj, j, Long.valueOf(au.c(j13Var.e)));
                unsafe.putInt(obj, j2, i4);
                return iY3;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                Object objD2 = D(i4, i8, obj);
                int iR0 = zu.r0(objD2, q(i8), bArr, i, i2, (i3 & (-8)) | 4, j13Var);
                X(i4, i8, obj, objD2);
                return iR0;
            default:
                return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x03ad, code lost:
    
        if (r0 != r31) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x03af, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r3 = r31;
        r13 = r32;
        r11 = r33;
        r9 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x03c9, code lost:
    
        r7 = r31;
        r6 = r33;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x03f7, code lost:
    
        if (r0 != r15) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0418, code lost:
    
        if (r0 != r15) goto L117;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x00a8. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int L(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, p.j13 r34) {
        /*
            Method dump skipped, instruction units count: 1224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.L(java.lang.Object, byte[], int, int, int, p.j13):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0275, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0277, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r8 = r18;
        r2 = r19;
        r1 = r24;
        r5 = r26;
        r6 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x028f, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02c3, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02e3, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x00a2. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void M(java.lang.Object r30, byte[] r31, int r32, int r33, p.j13 r34) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            Method dump skipped, instruction units count: 846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.M(java.lang.Object, byte[], int, int, p.j13):void");
    }

    public final int N(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, j13 j13Var) throws InvalidProtocolBufferException {
        int iX;
        Unsafe unsafe = f44p;
        a2 a2Var = (a2) ((v51) unsafe.getObject(obj, j2));
        boolean zB = a2Var.b();
        a2 a2Var2 = a2Var;
        if (!zB) {
            int size = a2Var.size();
            v51 v51VarF = a2Var.f(size == 0 ? 10 : size * 2);
            unsafe.putObject(obj, j2, v51VarF);
            a2Var2 = v51VarF;
        }
        switch (i7) {
            case 18:
            case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                return i5 == 2 ? zu.G(bArr, i, a2Var2, j13Var) : i5 == 1 ? zu.w(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 19:
            case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                return i5 == 2 ? zu.J(bArr, i, a2Var2, j13Var) : i5 == 5 ? zu.C(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 20:
            case 21:
            case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                return i5 == 2 ? zu.N(bArr, i, a2Var2, j13Var) : i5 == 0 ? zu.Z(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 22:
            case 29:
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
            case 43:
                return i5 == 2 ? zu.M(bArr, i, a2Var2, j13Var) : i5 == 0 ? zu.X(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 23:
            case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
            case 40:
            case 46:
                return i5 == 2 ? zu.I(bArr, i, a2Var2, j13Var) : i5 == 1 ? zu.A(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 24:
            case 31:
            case 41:
            case 45:
                return i5 == 2 ? zu.H(bArr, i, a2Var2, j13Var) : i5 == 5 ? zu.y(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 25:
            case 42:
                return i5 == 2 ? zu.F(bArr, i, a2Var2, j13Var) : i5 == 0 ? zu.s(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 26:
                return i5 == 2 ? (j & 536870912) == 0 ? zu.R(i3, bArr, i, i2, a2Var2, j13Var) : zu.S(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 27:
                return i5 == 2 ? zu.E(q(i6), i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 28:
                return i5 == 2 ? zu.u(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 30:
            case 44:
                if (i5 == 2) {
                    iX = zu.M(bArr, i, a2Var2, j13Var);
                } else {
                    if (i5 != 0) {
                        return i;
                    }
                    iX = zu.X(i3, bArr, i, i2, a2Var2, j13Var);
                }
                m.z(obj, i4, a2Var2, o(i6), null, this.m);
                return iX;
            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
            case 47:
                return i5 == 2 ? zu.K(bArr, i, a2Var2, j13Var) : i5 == 0 ? zu.O(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
            case 48:
                return i5 == 2 ? zu.L(bArr, i, a2Var2, j13Var) : i5 == 0 ? zu.P(i3, bArr, i, i2, a2Var2, j13Var) : i;
            case 49:
                return i5 == 3 ? zu.D(q(i6), i3, bArr, i, i2, a2Var2, j13Var) : i;
            default:
                return i;
        }
    }

    public final void O(Object obj, long j, t02 t02Var, k72 k72Var, rl0 rl0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iA;
        List listC = this.l.c(j, obj);
        e eVar = (e) t02Var;
        int i = eVar.b;
        if ((i & 7) != 3) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            Object objI = k72Var.i();
            eVar.b(objI, k72Var, rl0Var);
            k72Var.c(objI);
            listC.add(objI);
            au auVar = eVar.a;
            if (auVar.e() || eVar.d != 0) {
                return;
            } else {
                iA = auVar.A();
            }
        } while (iA == i);
        eVar.d = iA;
    }

    public final void P(Object obj, int i, t02 t02Var, k72 k72Var, rl0 rl0Var) throws InvalidProtocolBufferException {
        int iA;
        List listC = this.l.c(i & 1048575, obj);
        e eVar = (e) t02Var;
        int i2 = eVar.b;
        if ((i2 & 7) != 2) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            Object objI = k72Var.i();
            eVar.c(objI, k72Var, rl0Var);
            k72Var.c(objI);
            listC.add(objI);
            au auVar = eVar.a;
            if (auVar.e() || eVar.d != 0) {
                return;
            } else {
                iA = auVar.A();
            }
        } while (iA == i2);
        eVar.d = iA;
    }

    public final void Q(Object obj, int i, t02 t02Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        if ((536870912 & i) != 0) {
            e eVar = (e) t02Var;
            eVar.x(2);
            dr2.v(i & 1048575, obj, eVar.a.z());
        } else {
            if (!this.f) {
                dr2.v(i & 1048575, obj, ((e) t02Var).e());
                return;
            }
            e eVar2 = (e) t02Var;
            eVar2.x(2);
            dr2.v(i & 1048575, obj, eVar2.a.y());
        }
    }

    public final void R(Object obj, int i, t02 t02Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        boolean z = (536870912 & i) != 0;
        ya1 ya1Var = this.l;
        if (z) {
            ((e) t02Var).t(ya1Var.c(i & 1048575, obj), true);
        } else {
            ((e) t02Var).t(ya1Var.c(i & 1048575, obj), false);
        }
    }

    public final void T(int i, Object obj) {
        int i2 = this.a[i + 2];
        long j = 1048575 & i2;
        if (j == 1048575) {
            return;
        }
        dr2.t((1 << (i2 >>> 20)) | dr2.c.i(j, obj), j, obj);
    }

    public final void U(int i, int i2, Object obj) {
        dr2.t(i, this.a[i2 + 2] & 1048575, obj);
    }

    public final int V(int i, int i2) {
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

    public final void W(Object obj, int i, Object obj2) {
        f44p.putObject(obj, Z(i) & 1048575, obj2);
        T(i, obj);
    }

    public final void X(int i, int i2, Object obj, Object obj2) {
        f44p.putObject(obj, Z(i2) & 1048575, obj2);
        U(i, i2, obj);
    }

    public final int Z(int i) {
        return this.a[i + 1];
    }

    @Override // p.k72
    public final void a(Object obj, Object obj2) {
        l(obj);
        obj2.getClass();
        int i = 0;
        while (true) {
            int[] iArr = this.a;
            if (i >= iArr.length) {
                m.B(this.m, obj, obj2);
                return;
            }
            int iZ = Z(i);
            long j = 1048575 & iZ;
            int i2 = iArr[i];
            switch (Y(iZ)) {
                case 0:
                    if (u(i, obj2)) {
                        dr2.r(obj, j, dr2.c.g(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 1:
                    if (u(i, obj2)) {
                        dr2.s(obj, j, dr2.c.h(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 2:
                    if (u(i, obj2)) {
                        dr2.u(obj, j, dr2.c.j(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 3:
                    if (u(i, obj2)) {
                        dr2.u(obj, j, dr2.c.j(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 4:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 5:
                    if (u(i, obj2)) {
                        dr2.u(obj, j, dr2.c.j(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 6:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 7:
                    if (u(i, obj2)) {
                        dr2.n(obj, j, dr2.c.d(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 8:
                    if (u(i, obj2)) {
                        dr2.v(j, obj, dr2.c.k(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 9:
                    A(obj, i, obj2);
                    break;
                case 10:
                    if (u(i, obj2)) {
                        dr2.v(j, obj, dr2.c.k(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 11:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 12:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 13:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 14:
                    if (u(i, obj2)) {
                        dr2.u(obj, j, dr2.c.j(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 15:
                    if (u(i, obj2)) {
                        dr2.t(dr2.c.i(j, obj2), j, obj);
                        T(i, obj);
                    }
                    break;
                case 16:
                    if (u(i, obj2)) {
                        dr2.u(obj, j, dr2.c.j(j, obj2));
                        T(i, obj);
                    }
                    break;
                case 17:
                    A(obj, i, obj2);
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
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
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
                    this.l.b(j, obj, obj2);
                    break;
                case 50:
                    Class cls = m.a;
                    cr2 cr2Var = dr2.c;
                    Object objK = cr2Var.k(j, obj);
                    Object objK2 = cr2Var.k(j, obj2);
                    this.n.getClass();
                    dr2.v(j, obj, pe1.e(objK, objK2));
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
                    if (w(i2, i, obj2)) {
                        dr2.v(j, obj, dr2.c.k(j, obj2));
                        U(i2, i, obj);
                    }
                    break;
                case 60:
                    B(obj, i, obj2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (w(i2, i, obj2)) {
                        dr2.v(j, obj, dr2.c.k(j, obj2));
                        U(i2, i, obj);
                    }
                    break;
                case 68:
                    B(obj, i, obj2);
                    break;
            }
            i += 3;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void a0(Object obj, p01 p01Var) {
        int i;
        boolean z;
        int[] iArr = this.a;
        int length = iArr.length;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int iZ = Z(i4);
            int i6 = iArr[i4];
            int iY = Y(iZ);
            Unsafe unsafe = f44p;
            if (iY <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = i;
            long j = iZ & i2;
            switch (iY) {
                case 0:
                    if ((i5 & i9) != 0) {
                        p01Var.M(i6, dr2.c.g(j, obj));
                    }
                    break;
                case 1:
                    if ((i5 & i9) != 0) {
                        p01Var.Q(dr2.c.h(j, obj), i6);
                    }
                    break;
                case 2:
                    if ((i5 & i9) != 0) {
                        p01Var.T(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 3:
                    if ((i5 & i9) != 0) {
                        p01Var.a0(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 4:
                    if ((i5 & i9) != 0) {
                        p01Var.S(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 5:
                    if ((i5 & i9) != 0) {
                        p01Var.P(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 6:
                    if ((i5 & i9) != 0) {
                        p01Var.O(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 7:
                    if ((i5 & i9) != 0) {
                        p01Var.K(i6, dr2.c.d(j, obj));
                    }
                    break;
                case 8:
                    if ((i5 & i9) != 0) {
                        c0(i6, unsafe.getObject(obj, j), p01Var);
                    }
                    break;
                case 9:
                    if ((i5 & i9) != 0) {
                        p01Var.U(i6, q(i4), unsafe.getObject(obj, j));
                    }
                    break;
                case 10:
                    if ((i5 & i9) != 0) {
                        p01Var.L(i6, (ByteString) unsafe.getObject(obj, j));
                    }
                    break;
                case 11:
                    if ((i5 & i9) != 0) {
                        p01Var.Z(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 12:
                    if ((i5 & i9) != 0) {
                        p01Var.N(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 13:
                    if ((i5 & i9) != 0) {
                        p01Var.V(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 14:
                    if ((i5 & i9) != 0) {
                        p01Var.W(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 15:
                    if ((i5 & i9) != 0) {
                        p01Var.X(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 16:
                    if ((i5 & i9) != 0) {
                        p01Var.Y(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 17:
                    if ((i5 & i9) != 0) {
                        p01Var.R(i6, q(i4), unsafe.getObject(obj, j));
                    }
                    break;
                case 18:
                    m.G(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 19:
                    m.K(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 20:
                    m.N(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 21:
                    m.V(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 22:
                    m.M(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 23:
                    m.J(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 24:
                    m.I(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 25:
                    m.E(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 26:
                    m.T(iArr[i4], (List) unsafe.getObject(obj, j), p01Var);
                    break;
                case 27:
                    m.O(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, q(i4));
                    break;
                case 28:
                    m.F(iArr[i4], (List) unsafe.getObject(obj, j), p01Var);
                    break;
                case 29:
                    z = false;
                    m.U(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 30:
                    z = false;
                    m.H(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case 31:
                    z = false;
                    m.P(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    z = false;
                    m.Q(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    z = false;
                    m.R(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    z = false;
                    m.S(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    m.G(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    m.K(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    m.N(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    m.V(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                    m.M(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 40:
                    m.J(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 41:
                    m.I(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 42:
                    m.E(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 43:
                    m.U(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 44:
                    m.H(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 45:
                    m.P(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 46:
                    m.Q(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 47:
                    m.R(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 48:
                    m.S(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, true);
                    break;
                case 49:
                    m.L(iArr[i4], (List) unsafe.getObject(obj, j), p01Var, q(i4));
                    break;
                case 50:
                    b0(p01Var, i6, unsafe.getObject(obj, j), i4);
                    break;
                case 51:
                    if (w(i6, i4, obj)) {
                        p01Var.M(i6, ((Double) dr2.c.k(j, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (w(i6, i4, obj)) {
                        p01Var.Q(((Float) dr2.c.k(j, obj)).floatValue(), i6);
                    }
                    break;
                case 53:
                    if (w(i6, i4, obj)) {
                        p01Var.T(i6, I(j, obj));
                    }
                    break;
                case 54:
                    if (w(i6, i4, obj)) {
                        p01Var.a0(i6, I(j, obj));
                    }
                    break;
                case 55:
                    if (w(i6, i4, obj)) {
                        p01Var.S(i6, H(j, obj));
                    }
                    break;
                case 56:
                    if (w(i6, i4, obj)) {
                        p01Var.P(i6, I(j, obj));
                    }
                    break;
                case 57:
                    if (w(i6, i4, obj)) {
                        p01Var.O(i6, H(j, obj));
                    }
                    break;
                case 58:
                    if (w(i6, i4, obj)) {
                        p01Var.K(i6, ((Boolean) dr2.c.k(j, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (w(i6, i4, obj)) {
                        c0(i6, unsafe.getObject(obj, j), p01Var);
                    }
                    break;
                case 60:
                    if (w(i6, i4, obj)) {
                        p01Var.U(i6, q(i4), unsafe.getObject(obj, j));
                    }
                    break;
                case 61:
                    if (w(i6, i4, obj)) {
                        p01Var.L(i6, (ByteString) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (w(i6, i4, obj)) {
                        p01Var.Z(i6, H(j, obj));
                    }
                    break;
                case 63:
                    if (w(i6, i4, obj)) {
                        p01Var.N(i6, H(j, obj));
                    }
                    break;
                case 64:
                    if (w(i6, i4, obj)) {
                        p01Var.V(i6, H(j, obj));
                    }
                    break;
                case 65:
                    if (w(i6, i4, obj)) {
                        p01Var.W(i6, I(j, obj));
                    }
                    break;
                case 66:
                    if (w(i6, i4, obj)) {
                        p01Var.X(i6, H(j, obj));
                    }
                    break;
                case 67:
                    if (w(i6, i4, obj)) {
                        p01Var.Y(i6, I(j, obj));
                    }
                    break;
                case 68:
                    if (w(i6, i4, obj)) {
                        p01Var.R(i6, q(i4), unsafe.getObject(obj, j));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        ((p) this.m).getClass();
        ((i) obj).unknownFields.g(p01Var);
    }

    @Override // p.k72
    public final void b(Object obj, byte[] bArr, int i, int i2, j13 j13Var) throws InvalidProtocolBufferException {
        if (this.g) {
            M(obj, bArr, i, i2, j13Var);
        } else {
            L(obj, bArr, i, i2, 0, j13Var);
        }
    }

    public final void b0(p01 p01Var, int i, Object obj, int i2) {
        if (obj != null) {
            Object objP = p(i2);
            pe1 pe1Var = this.n;
            pe1Var.getClass();
            gh2 gh2Var = ((oe1) objP).a;
            pe1Var.getClass();
            ((h) p01Var.e).getClass();
            for (Map.Entry entry : ((MapFieldLite) obj).entrySet()) {
                ((h) p01Var.e).g0(i, 2);
                ((h) p01Var.e).i0(oe1.a(gh2Var, entry.getKey(), entry.getValue()));
                oe1.b((h) p01Var.e, gh2Var, entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    @Override // p.k72
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = v(r8)
            if (r0 != 0) goto L7
            return
        L7:
            boolean r0 = r8 instanceof com.google.protobuf.i
            if (r0 == 0) goto L17
            r0 = r8
            com.google.protobuf.i r0 = (com.google.protobuf.i) r0
            r0.clearMemoizedSerializedSize()
            r0.clearMemoizedHashCode()
            r0.markImmutable()
        L17:
            int[] r0 = r7.a
            int r0 = r0.length
            r1 = 0
        L1b:
            if (r1 >= r0) goto L60
            int r2 = r7.Z(r1)
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r2
            long r3 = (long) r3
            int r2 = Y(r2)
            r5 = 9
            sun.misc.Unsafe r6 = com.google.protobuf.k.f44p
            if (r2 == r5) goto L4c
            switch(r2) {
                case 17: goto L4c;
                case 18: goto L46;
                case 19: goto L46;
                case 20: goto L46;
                case 21: goto L46;
                case 22: goto L46;
                case 23: goto L46;
                case 24: goto L46;
                case 25: goto L46;
                case 26: goto L46;
                case 27: goto L46;
                case 28: goto L46;
                case 29: goto L46;
                case 30: goto L46;
                case 31: goto L46;
                case 32: goto L46;
                case 33: goto L46;
                case 34: goto L46;
                case 35: goto L46;
                case 36: goto L46;
                case 37: goto L46;
                case 38: goto L46;
                case 39: goto L46;
                case 40: goto L46;
                case 41: goto L46;
                case 42: goto L46;
                case 43: goto L46;
                case 44: goto L46;
                case 45: goto L46;
                case 46: goto L46;
                case 47: goto L46;
                case 48: goto L46;
                case 49: goto L46;
                case 50: goto L34;
                default: goto L33;
            }
        L33:
            goto L5d
        L34:
            java.lang.Object r2 = r6.getObject(r8, r3)
            if (r2 == 0) goto L5d
            p.pe1 r5 = r7.n
            r5.getClass()
            p.pe1.g(r2)
            r6.putObject(r8, r3, r2)
            goto L5d
        L46:
            p.ya1 r2 = r7.l
            r2.a(r3, r8)
            goto L5d
        L4c:
            boolean r2 = r7.u(r1, r8)
            if (r2 == 0) goto L5d
            p.k72 r2 = r7.q(r1)
            java.lang.Object r3 = r6.getObject(r8, r3)
            r2.c(r3)
        L5d:
            int r1 = r1 + 3
            goto L1b
        L60:
            com.google.protobuf.n r0 = r7.m
            r0.b(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.c(java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0108  */
    @Override // p.k72
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.d(java.lang.Object):boolean");
    }

    @Override // p.k72
    public final void e(Object obj, p01 p01Var) {
        p01Var.getClass();
        if (!this.g) {
            a0(obj, p01Var);
            return;
        }
        int[] iArr = this.a;
        int length = iArr.length;
        for (int i = 0; i < length; i += 3) {
            int iZ = Z(i);
            int i2 = iArr[i];
            switch (Y(iZ)) {
                case 0:
                    if (u(i, obj)) {
                        p01Var.M(i2, dr2.c.g(iZ & 1048575, obj));
                    }
                    break;
                case 1:
                    if (u(i, obj)) {
                        p01Var.Q(dr2.c.h(iZ & 1048575, obj), i2);
                    }
                    break;
                case 2:
                    if (u(i, obj)) {
                        p01Var.T(i2, dr2.c.j(iZ & 1048575, obj));
                    }
                    break;
                case 3:
                    if (u(i, obj)) {
                        p01Var.a0(i2, dr2.c.j(iZ & 1048575, obj));
                    }
                    break;
                case 4:
                    if (u(i, obj)) {
                        p01Var.S(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 5:
                    if (u(i, obj)) {
                        p01Var.P(i2, dr2.c.j(iZ & 1048575, obj));
                    }
                    break;
                case 6:
                    if (u(i, obj)) {
                        p01Var.O(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 7:
                    if (u(i, obj)) {
                        p01Var.K(i2, dr2.c.d(iZ & 1048575, obj));
                    }
                    break;
                case 8:
                    if (u(i, obj)) {
                        c0(i2, dr2.c.k(iZ & 1048575, obj), p01Var);
                    }
                    break;
                case 9:
                    if (u(i, obj)) {
                        p01Var.U(i2, q(i), dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
                case 10:
                    if (u(i, obj)) {
                        p01Var.L(i2, (ByteString) dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
                case 11:
                    if (u(i, obj)) {
                        p01Var.Z(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 12:
                    if (u(i, obj)) {
                        p01Var.N(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 13:
                    if (u(i, obj)) {
                        p01Var.V(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 14:
                    if (u(i, obj)) {
                        p01Var.W(i2, dr2.c.j(iZ & 1048575, obj));
                    }
                    break;
                case 15:
                    if (u(i, obj)) {
                        p01Var.X(i2, dr2.c.i(iZ & 1048575, obj));
                    }
                    break;
                case 16:
                    if (u(i, obj)) {
                        p01Var.Y(i2, dr2.c.j(iZ & 1048575, obj));
                    }
                    break;
                case 17:
                    if (u(i, obj)) {
                        p01Var.R(i2, q(i), dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
                case 18:
                    m.G(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 19:
                    m.K(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 20:
                    m.N(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 21:
                    m.V(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 22:
                    m.M(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 23:
                    m.J(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 24:
                    m.I(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 25:
                    m.E(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 26:
                    m.T(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var);
                    break;
                case 27:
                    m.O(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, q(i));
                    break;
                case 28:
                    m.F(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var);
                    break;
                case 29:
                    m.U(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 30:
                    m.H(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case 31:
                    m.P(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    m.Q(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    m.R(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    m.S(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, false);
                    break;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    m.G(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    m.K(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    m.N(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    m.V(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                    m.M(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 40:
                    m.J(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 41:
                    m.I(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 42:
                    m.E(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 43:
                    m.U(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 44:
                    m.H(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 45:
                    m.P(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 46:
                    m.Q(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 47:
                    m.R(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 48:
                    m.S(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, true);
                    break;
                case 49:
                    m.L(iArr[i], (List) dr2.c.k(iZ & 1048575, obj), p01Var, q(i));
                    break;
                case 50:
                    b0(p01Var, i2, dr2.c.k(iZ & 1048575, obj), i);
                    break;
                case 51:
                    if (w(i2, i, obj)) {
                        p01Var.M(i2, ((Double) dr2.c.k(iZ & 1048575, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (w(i2, i, obj)) {
                        p01Var.Q(((Float) dr2.c.k(iZ & 1048575, obj)).floatValue(), i2);
                    }
                    break;
                case 53:
                    if (w(i2, i, obj)) {
                        p01Var.T(i2, I(iZ & 1048575, obj));
                    }
                    break;
                case 54:
                    if (w(i2, i, obj)) {
                        p01Var.a0(i2, I(iZ & 1048575, obj));
                    }
                    break;
                case 55:
                    if (w(i2, i, obj)) {
                        p01Var.S(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 56:
                    if (w(i2, i, obj)) {
                        p01Var.P(i2, I(iZ & 1048575, obj));
                    }
                    break;
                case 57:
                    if (w(i2, i, obj)) {
                        p01Var.O(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 58:
                    if (w(i2, i, obj)) {
                        p01Var.K(i2, ((Boolean) dr2.c.k(iZ & 1048575, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (w(i2, i, obj)) {
                        c0(i2, dr2.c.k(iZ & 1048575, obj), p01Var);
                    }
                    break;
                case 60:
                    if (w(i2, i, obj)) {
                        p01Var.U(i2, q(i), dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
                case 61:
                    if (w(i2, i, obj)) {
                        p01Var.L(i2, (ByteString) dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
                case 62:
                    if (w(i2, i, obj)) {
                        p01Var.Z(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 63:
                    if (w(i2, i, obj)) {
                        p01Var.N(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 64:
                    if (w(i2, i, obj)) {
                        p01Var.V(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 65:
                    if (w(i2, i, obj)) {
                        p01Var.W(i2, I(iZ & 1048575, obj));
                    }
                    break;
                case 66:
                    if (w(i2, i, obj)) {
                        p01Var.X(i2, H(iZ & 1048575, obj));
                    }
                    break;
                case 67:
                    if (w(i2, i, obj)) {
                        p01Var.Y(i2, I(iZ & 1048575, obj));
                    }
                    break;
                case 68:
                    if (w(i2, i, obj)) {
                        p01Var.R(i2, q(i), dr2.c.k(iZ & 1048575, obj));
                    }
                    break;
            }
        }
        ((p) this.m).getClass();
        ((i) obj).unknownFields.g(p01Var);
    }

    @Override // p.k72
    public final boolean f(Object obj, Object obj2) {
        boolean zC;
        int[] iArr = this.a;
        int length = iArr.length;
        for (int i = 0; i < length; i += 3) {
            int iZ = Z(i);
            long j = iZ & 1048575;
            switch (Y(iZ)) {
                case 0:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var = dr2.c;
                        if (Double.doubleToLongBits(cr2Var.g(j, obj)) == Double.doubleToLongBits(cr2Var.g(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 1:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var2 = dr2.c;
                        if (Float.floatToIntBits(cr2Var2.h(j, obj)) == Float.floatToIntBits(cr2Var2.h(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 2:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var3 = dr2.c;
                        if (cr2Var3.j(j, obj) == cr2Var3.j(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 3:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var4 = dr2.c;
                        if (cr2Var4.j(j, obj) == cr2Var4.j(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 4:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var5 = dr2.c;
                        if (cr2Var5.i(j, obj) == cr2Var5.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 5:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var6 = dr2.c;
                        if (cr2Var6.j(j, obj) == cr2Var6.j(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 6:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var7 = dr2.c;
                        if (cr2Var7.i(j, obj) == cr2Var7.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 7:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var8 = dr2.c;
                        if (cr2Var8.d(j, obj) == cr2Var8.d(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 8:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var9 = dr2.c;
                        if (m.C(cr2Var9.k(j, obj), cr2Var9.k(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 9:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var10 = dr2.c;
                        if (m.C(cr2Var10.k(j, obj), cr2Var10.k(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 10:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var11 = dr2.c;
                        if (m.C(cr2Var11.k(j, obj), cr2Var11.k(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 11:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var12 = dr2.c;
                        if (cr2Var12.i(j, obj) == cr2Var12.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 12:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var13 = dr2.c;
                        if (cr2Var13.i(j, obj) == cr2Var13.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 13:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var14 = dr2.c;
                        if (cr2Var14.i(j, obj) == cr2Var14.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 14:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var15 = dr2.c;
                        if (cr2Var15.j(j, obj) == cr2Var15.j(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 15:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var16 = dr2.c;
                        if (cr2Var16.i(j, obj) == cr2Var16.i(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 16:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var17 = dr2.c;
                        if (cr2Var17.j(j, obj) == cr2Var17.j(j, obj2)) {
                            continue;
                            break;
                        }
                    }
                    return false;
                case 17:
                    if (k(obj, i, obj2)) {
                        cr2 cr2Var18 = dr2.c;
                        if (m.C(cr2Var18.k(j, obj), cr2Var18.k(j, obj2))) {
                            continue;
                            break;
                        }
                    }
                    return false;
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
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
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
                    cr2 cr2Var19 = dr2.c;
                    zC = m.C(cr2Var19.k(j, obj), cr2Var19.k(j, obj2));
                    break;
                case 50:
                    cr2 cr2Var20 = dr2.c;
                    zC = m.C(cr2Var20.k(j, obj), cr2Var20.k(j, obj2));
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
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long j2 = iArr[i + 2] & 1048575;
                    cr2 cr2Var21 = dr2.c;
                    if (cr2Var21.i(j2, obj) != cr2Var21.i(j2, obj2) || !m.C(cr2Var21.k(j, obj), cr2Var21.k(j, obj2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zC) {
                return false;
            }
        }
        p pVar = (p) this.m;
        pVar.getClass();
        o oVar = ((i) obj).unknownFields;
        pVar.getClass();
        return oVar.equals(((i) obj2).unknownFields);
    }

    @Override // p.k72
    public final void g(Object obj, t02 t02Var, rl0 rl0Var) throws Throwable {
        rl0Var.getClass();
        l(obj);
        y(this.m, obj, t02Var, rl0Var);
    }

    @Override // p.k72
    public final int h(Object obj) {
        return this.g ? t(obj) : s(obj);
    }

    @Override // p.k72
    public final Object i() {
        this.k.getClass();
        return ((i) this.e).newMutableInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00e1 A[PHI: r3
  0x00e1: PHI (r3v32 int) = (r3v10 int), (r3v33 int) binds: [B:83:0x0216, B:41:0x00df] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // p.k72
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 798
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.j(java.lang.Object):int");
    }

    public final boolean k(Object obj, int i, Object obj2) {
        return u(i, obj) == u(i, obj2);
    }

    public final Object n(Object obj, int i, Object obj2, n nVar, Object obj3) {
        p51 p51VarO;
        int i2 = this.a[i];
        Object objK = dr2.c.k(Z(i) & 1048575, obj);
        if (objK == null || (p51VarO = o(i)) == null) {
            return obj2;
        }
        this.n.getClass();
        gh2 gh2Var = ((oe1) p(i)).a;
        Iterator it = ((MapFieldLite) objK).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!p51VarO.isInRange(((Integer) entry.getValue()).intValue())) {
                if (obj2 == null) {
                    obj2 = nVar.a(obj3);
                }
                int iA = oe1.a(gh2Var, entry.getKey(), entry.getValue());
                byte[] bArr = new byte[iA];
                Logger logger = h.h;
                f fVar = new f(bArr, iA);
                try {
                    oe1.b(fVar, gh2Var, entry.getKey(), entry.getValue());
                    if (fVar.l0() != 0) {
                        throw new IllegalStateException("Did not write as much data as expected.");
                    }
                    ByteString.LiteralByteString literalByteString = new ByteString.LiteralByteString(bArr);
                    ((p) nVar).getClass();
                    ((o) obj2).f((i2 << 3) | 2, literalByteString);
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj2;
    }

    public final p51 o(int i) {
        return (p51) this.b[((i / 3) * 2) + 1];
    }

    public final Object p(int i) {
        return this.b[(i / 3) * 2];
    }

    public final k72 q(int i) {
        int i2 = (i / 3) * 2;
        Object[] objArr = this.b;
        k72 k72Var = (k72) objArr[i2];
        if (k72Var != null) {
            return k72Var;
        }
        k72 k72VarA = ry1.c.a((Class) objArr[i2 + 1]);
        objArr[i2] = k72VarA;
        return k72VarA;
    }

    public final int s(Object obj) {
        int i;
        int iW;
        int iV;
        int i2 = 0;
        int iA = 0;
        int i3 = 0;
        int i4 = 1048575;
        while (true) {
            int[] iArr = this.a;
            if (i2 >= iArr.length) {
                ((p) this.m).getClass();
                return ((i) obj).unknownFields.c() + iA;
            }
            int iZ = Z(i2);
            int i5 = iArr[i2];
            int iY = Y(iZ);
            Unsafe unsafe = f44p;
            if (iY <= 17) {
                int i6 = iArr[i2 + 2];
                int i7 = i6 & 1048575;
                i = 1 << (i6 >>> 20);
                if (i7 != i4) {
                    i3 = unsafe.getInt(obj, i7);
                    i4 = i7;
                }
            } else {
                i = 0;
            }
            long j = iZ & 1048575;
            switch (iY) {
                case 0:
                    if ((i & i3) != 0) {
                        iW = h.w(i5);
                        iA += iW;
                    }
                    break;
                case 1:
                    if ((i & i3) != 0) {
                        iW = h.A(i5);
                        iA += iW;
                    }
                    break;
                case 2:
                    if ((i & i3) != 0) {
                        iW = h.E(i5, unsafe.getLong(obj, j));
                        iA += iW;
                    }
                    break;
                case 3:
                    if ((i & i3) != 0) {
                        iW = h.O(i5, unsafe.getLong(obj, j));
                        iA += iW;
                    }
                    break;
                case 4:
                    if ((i & i3) != 0) {
                        iW = h.C(i5, unsafe.getInt(obj, j));
                        iA += iW;
                    }
                    break;
                case 5:
                    if ((i & i3) != 0) {
                        iW = h.z(i5);
                        iA += iW;
                    }
                    break;
                case 6:
                    if ((i & i3) != 0) {
                        iW = h.y(i5);
                        iA += iW;
                    }
                    break;
                case 7:
                    if ((i & i3) != 0) {
                        iW = h.u(i5);
                        iA += iW;
                    }
                    break;
                case 8:
                    if ((i & i3) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        iV = object instanceof ByteString ? h.v(i5, (ByteString) object) : h.J(i5, (String) object);
                        iA = iV + iA;
                    }
                    break;
                case 9:
                    if ((i & i3) != 0) {
                        iW = m.o(i5, q(i2), unsafe.getObject(obj, j));
                        iA += iW;
                    }
                    break;
                case 10:
                    if ((i & i3) != 0) {
                        iW = h.v(i5, (ByteString) unsafe.getObject(obj, j));
                        iA += iW;
                    }
                    break;
                case 11:
                    if ((i & i3) != 0) {
                        iW = h.M(i5, unsafe.getInt(obj, j));
                        iA += iW;
                    }
                    break;
                case 12:
                    if ((i & i3) != 0) {
                        iW = h.x(i5, unsafe.getInt(obj, j));
                        iA += iW;
                    }
                    break;
                case 13:
                    if ((i & i3) != 0) {
                        iW = h.F(i5);
                        iA += iW;
                    }
                    break;
                case 14:
                    if ((i & i3) != 0) {
                        iW = h.G(i5);
                        iA += iW;
                    }
                    break;
                case 15:
                    if ((i & i3) != 0) {
                        iW = h.H(i5, unsafe.getInt(obj, j));
                        iA += iW;
                    }
                    break;
                case 16:
                    if ((i & i3) != 0) {
                        iW = h.I(i5, unsafe.getLong(obj, j));
                        iA += iW;
                    }
                    break;
                case 17:
                    if ((i & i3) != 0) {
                        iW = h.B(i5, (wj1) unsafe.getObject(obj, j), q(i2));
                        iA += iW;
                    }
                    break;
                case 18:
                    iW = m.h(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 19:
                    iW = m.f(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 20:
                    iW = m.m(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 21:
                    iW = m.x(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 22:
                    iW = m.k(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 23:
                    iW = m.h(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 24:
                    iW = m.f(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 25:
                    iW = m.a(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 26:
                    iW = m.u(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 27:
                    iW = m.p(i5, (List) unsafe.getObject(obj, j), q(i2));
                    iA += iW;
                    break;
                case 28:
                    iW = m.c(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 29:
                    iW = m.v(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 30:
                    iW = m.d(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case 31:
                    iW = m.f(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    iW = m.h(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    iW = m.q(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    iW = m.s(i5, (List) unsafe.getObject(obj, j));
                    iA += iW;
                    break;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    int i8 = m.i((List) unsafe.getObject(obj, j));
                    if (i8 > 0) {
                        iA = u4.a(i8, h.L(i5), i8, iA);
                    }
                    break;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    int iG = m.g((List) unsafe.getObject(obj, j));
                    if (iG > 0) {
                        iA = u4.a(iG, h.L(i5), iG, iA);
                    }
                    break;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    int iN = m.n((List) unsafe.getObject(obj, j));
                    if (iN > 0) {
                        iA = u4.a(iN, h.L(i5), iN, iA);
                    }
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    int iY2 = m.y((List) unsafe.getObject(obj, j));
                    if (iY2 > 0) {
                        iA = u4.a(iY2, h.L(i5), iY2, iA);
                    }
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                    int iL = m.l((List) unsafe.getObject(obj, j));
                    if (iL > 0) {
                        iA = u4.a(iL, h.L(i5), iL, iA);
                    }
                    break;
                case 40:
                    int i9 = m.i((List) unsafe.getObject(obj, j));
                    if (i9 > 0) {
                        iA = u4.a(i9, h.L(i5), i9, iA);
                    }
                    break;
                case 41:
                    int iG2 = m.g((List) unsafe.getObject(obj, j));
                    if (iG2 > 0) {
                        iA = u4.a(iG2, h.L(i5), iG2, iA);
                    }
                    break;
                case 42:
                    int iB = m.b((List) unsafe.getObject(obj, j));
                    if (iB > 0) {
                        iA = u4.a(iB, h.L(i5), iB, iA);
                    }
                    break;
                case 43:
                    int iW2 = m.w((List) unsafe.getObject(obj, j));
                    if (iW2 > 0) {
                        iA = u4.a(iW2, h.L(i5), iW2, iA);
                    }
                    break;
                case 44:
                    int iE = m.e((List) unsafe.getObject(obj, j));
                    if (iE > 0) {
                        iA = u4.a(iE, h.L(i5), iE, iA);
                    }
                    break;
                case 45:
                    int iG3 = m.g((List) unsafe.getObject(obj, j));
                    if (iG3 > 0) {
                        iA = u4.a(iG3, h.L(i5), iG3, iA);
                    }
                    break;
                case 46:
                    int i10 = m.i((List) unsafe.getObject(obj, j));
                    if (i10 > 0) {
                        iA = u4.a(i10, h.L(i5), i10, iA);
                    }
                    break;
                case 47:
                    int iR = m.r((List) unsafe.getObject(obj, j));
                    if (iR > 0) {
                        iA = u4.a(iR, h.L(i5), iR, iA);
                    }
                    break;
                case 48:
                    int iT = m.t((List) unsafe.getObject(obj, j));
                    if (iT > 0) {
                        iA = u4.a(iT, h.L(i5), iT, iA);
                    }
                    break;
                case 49:
                    iW = m.j(i5, (List) unsafe.getObject(obj, j), q(i2));
                    iA += iW;
                    break;
                case 50:
                    Object object2 = unsafe.getObject(obj, j);
                    Object objP = p(i2);
                    this.n.getClass();
                    iW = pe1.c(object2, i5, objP);
                    iA += iW;
                    break;
                case 51:
                    if (w(i5, i2, obj)) {
                        iW = h.w(i5);
                        iA += iW;
                    }
                    break;
                case 52:
                    if (w(i5, i2, obj)) {
                        iW = h.A(i5);
                        iA += iW;
                    }
                    break;
                case 53:
                    if (w(i5, i2, obj)) {
                        iW = h.E(i5, I(j, obj));
                        iA += iW;
                    }
                    break;
                case 54:
                    if (w(i5, i2, obj)) {
                        iW = h.O(i5, I(j, obj));
                        iA += iW;
                    }
                    break;
                case 55:
                    if (w(i5, i2, obj)) {
                        iW = h.C(i5, H(j, obj));
                        iA += iW;
                    }
                    break;
                case 56:
                    if (w(i5, i2, obj)) {
                        iW = h.z(i5);
                        iA += iW;
                    }
                    break;
                case 57:
                    if (w(i5, i2, obj)) {
                        iW = h.y(i5);
                        iA += iW;
                    }
                    break;
                case 58:
                    if (w(i5, i2, obj)) {
                        iW = h.u(i5);
                        iA += iW;
                    }
                    break;
                case 59:
                    if (w(i5, i2, obj)) {
                        Object object3 = unsafe.getObject(obj, j);
                        iV = object3 instanceof ByteString ? h.v(i5, (ByteString) object3) : h.J(i5, (String) object3);
                        iA = iV + iA;
                    }
                    break;
                case 60:
                    if (w(i5, i2, obj)) {
                        iW = m.o(i5, q(i2), unsafe.getObject(obj, j));
                        iA += iW;
                    }
                    break;
                case 61:
                    if (w(i5, i2, obj)) {
                        iW = h.v(i5, (ByteString) unsafe.getObject(obj, j));
                        iA += iW;
                    }
                    break;
                case 62:
                    if (w(i5, i2, obj)) {
                        iW = h.M(i5, H(j, obj));
                        iA += iW;
                    }
                    break;
                case 63:
                    if (w(i5, i2, obj)) {
                        iW = h.x(i5, H(j, obj));
                        iA += iW;
                    }
                    break;
                case 64:
                    if (w(i5, i2, obj)) {
                        iW = h.F(i5);
                        iA += iW;
                    }
                    break;
                case 65:
                    if (w(i5, i2, obj)) {
                        iW = h.G(i5);
                        iA += iW;
                    }
                    break;
                case 66:
                    if (w(i5, i2, obj)) {
                        iW = h.H(i5, H(j, obj));
                        iA += iW;
                    }
                    break;
                case 67:
                    if (w(i5, i2, obj)) {
                        iW = h.I(i5, I(j, obj));
                        iA += iW;
                    }
                    break;
                case 68:
                    if (w(i5, i2, obj)) {
                        iW = h.B(i5, (wj1) unsafe.getObject(obj, j), q(i2));
                        iA += iW;
                    }
                    break;
            }
            i2 += 3;
        }
    }

    public final int t(Object obj) {
        int iW;
        int i = 0;
        int iA = 0;
        while (true) {
            int[] iArr = this.a;
            if (i >= iArr.length) {
                ((p) this.m).getClass();
                return ((i) obj).unknownFields.c() + iA;
            }
            int iZ = Z(i);
            int iY = Y(iZ);
            int i2 = iArr[i];
            long j = iZ & 1048575;
            if (iY >= FieldType.c.a() && iY <= FieldType.e.a()) {
                int i3 = iArr[i + 2];
            }
            Unsafe unsafe = f44p;
            switch (iY) {
                case 0:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.w(i2);
                    }
                    break;
                case 1:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.A(i2);
                    }
                    break;
                case 2:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.E(i2, dr2.k(j, obj));
                    }
                    break;
                case 3:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.O(i2, dr2.k(j, obj));
                    }
                    break;
                case 4:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.C(i2, dr2.j(j, obj));
                    }
                    break;
                case 5:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.z(i2);
                    }
                    break;
                case 6:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.y(i2);
                    }
                    break;
                case 7:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.u(i2);
                    }
                    break;
                case 8:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        Object objL = dr2.l(j, obj);
                        iW = !(objL instanceof ByteString) ? h.J(i2, (String) objL) : h.v(i2, (ByteString) objL);
                    }
                    break;
                case 9:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = m.o(i2, q(i), dr2.l(j, obj));
                    }
                    break;
                case 10:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.v(i2, (ByteString) dr2.l(j, obj));
                    }
                    break;
                case 11:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.M(i2, dr2.j(j, obj));
                    }
                    break;
                case 12:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.x(i2, dr2.j(j, obj));
                    }
                    break;
                case 13:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.F(i2);
                    }
                    break;
                case 14:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.G(i2);
                    }
                    break;
                case 15:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.H(i2, dr2.j(j, obj));
                    }
                    break;
                case 16:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.I(i2, dr2.k(j, obj));
                    }
                    break;
                case 17:
                    if (!u(i, obj)) {
                        i += 3;
                    } else {
                        iW = h.B(i2, (wj1) dr2.l(j, obj), q(i));
                    }
                    break;
                case 18:
                    iW = m.h(i2, x(j, obj));
                    break;
                case 19:
                    iW = m.f(i2, x(j, obj));
                    break;
                case 20:
                    iW = m.m(i2, x(j, obj));
                    break;
                case 21:
                    iW = m.x(i2, x(j, obj));
                    break;
                case 22:
                    iW = m.k(i2, x(j, obj));
                    break;
                case 23:
                    iW = m.h(i2, x(j, obj));
                    break;
                case 24:
                    iW = m.f(i2, x(j, obj));
                    break;
                case 25:
                    iW = m.a(i2, x(j, obj));
                    break;
                case 26:
                    iW = m.u(i2, x(j, obj));
                    break;
                case 27:
                    iW = m.p(i2, x(j, obj), q(i));
                    break;
                case 28:
                    iW = m.c(i2, x(j, obj));
                    break;
                case 29:
                    iW = m.v(i2, x(j, obj));
                    break;
                case 30:
                    iW = m.d(i2, x(j, obj));
                    break;
                case 31:
                    iW = m.f(i2, x(j, obj));
                    break;
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    iW = m.h(i2, x(j, obj));
                    break;
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    iW = m.q(i2, x(j, obj));
                    break;
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    iW = m.s(i2, x(j, obj));
                    break;
                case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    int i4 = m.i((List) unsafe.getObject(obj, j));
                    if (i4 > 0) {
                        iA = u4.a(i4, h.L(i2), i4, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    int iG = m.g((List) unsafe.getObject(obj, j));
                    if (iG > 0) {
                        iA = u4.a(iG, h.L(i2), iG, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                    int iN = m.n((List) unsafe.getObject(obj, j));
                    if (iN > 0) {
                        iA = u4.a(iN, h.L(i2), iN, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                    int iY2 = m.y((List) unsafe.getObject(obj, j));
                    if (iY2 > 0) {
                        iA = u4.a(iY2, h.L(i2), iY2, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                    int iL = m.l((List) unsafe.getObject(obj, j));
                    if (iL > 0) {
                        iA = u4.a(iL, h.L(i2), iL, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 40:
                    int i5 = m.i((List) unsafe.getObject(obj, j));
                    if (i5 > 0) {
                        iA = u4.a(i5, h.L(i2), i5, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 41:
                    int iG2 = m.g((List) unsafe.getObject(obj, j));
                    if (iG2 > 0) {
                        iA = u4.a(iG2, h.L(i2), iG2, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 42:
                    int iB = m.b((List) unsafe.getObject(obj, j));
                    if (iB > 0) {
                        iA = u4.a(iB, h.L(i2), iB, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 43:
                    int iW2 = m.w((List) unsafe.getObject(obj, j));
                    if (iW2 > 0) {
                        iA = u4.a(iW2, h.L(i2), iW2, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 44:
                    int iE = m.e((List) unsafe.getObject(obj, j));
                    if (iE > 0) {
                        iA = u4.a(iE, h.L(i2), iE, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 45:
                    int iG3 = m.g((List) unsafe.getObject(obj, j));
                    if (iG3 > 0) {
                        iA = u4.a(iG3, h.L(i2), iG3, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 46:
                    int i6 = m.i((List) unsafe.getObject(obj, j));
                    if (i6 > 0) {
                        iA = u4.a(i6, h.L(i2), i6, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 47:
                    int iR = m.r((List) unsafe.getObject(obj, j));
                    if (iR > 0) {
                        iA = u4.a(iR, h.L(i2), iR, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 48:
                    int iT = m.t((List) unsafe.getObject(obj, j));
                    if (iT > 0) {
                        iA = u4.a(iT, h.L(i2), iT, iA);
                    } else {
                        continue;
                    }
                    i += 3;
                    break;
                case 49:
                    iW = m.j(i2, x(j, obj), q(i));
                    break;
                case 50:
                    Object objL2 = dr2.l(j, obj);
                    Object objP = p(i);
                    this.n.getClass();
                    iW = pe1.c(objL2, i2, objP);
                    break;
                case 51:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.w(i2);
                    }
                    break;
                case 52:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.A(i2);
                    }
                    break;
                case 53:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.E(i2, I(j, obj));
                    }
                    break;
                case 54:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.O(i2, I(j, obj));
                    }
                    break;
                case 55:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.C(i2, H(j, obj));
                    }
                    break;
                case 56:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.z(i2);
                    }
                    break;
                case 57:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.y(i2);
                    }
                    break;
                case 58:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.u(i2);
                    }
                    break;
                case 59:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        Object objL3 = dr2.l(j, obj);
                        iW = !(objL3 instanceof ByteString) ? h.J(i2, (String) objL3) : h.v(i2, (ByteString) objL3);
                    }
                    break;
                case 60:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = m.o(i2, q(i), dr2.l(j, obj));
                    }
                    break;
                case 61:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.v(i2, (ByteString) dr2.l(j, obj));
                    }
                    break;
                case 62:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.M(i2, H(j, obj));
                    }
                    break;
                case 63:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.x(i2, H(j, obj));
                    }
                    break;
                case 64:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.F(i2);
                    }
                    break;
                case 65:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.G(i2);
                    }
                    break;
                case 66:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.H(i2, H(j, obj));
                    }
                    break;
                case 67:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.I(i2, I(j, obj));
                    }
                    break;
                case 68:
                    if (!w(i2, i, obj)) {
                        i += 3;
                    } else {
                        iW = h.B(i2, (wj1) dr2.l(j, obj), q(i));
                    }
                    break;
                default:
                    i += 3;
                    break;
            }
            iA = iW + iA;
            i += 3;
        }
    }

    public final boolean u(int i, Object obj) {
        int i2 = this.a[i + 2];
        long j = i2 & 1048575;
        if (j != 1048575) {
            return ((1 << (i2 >>> 20)) & dr2.c.i(j, obj)) != 0;
        }
        int iZ = Z(i);
        long j2 = iZ & 1048575;
        switch (Y(iZ)) {
            case 0:
                return Double.doubleToRawLongBits(dr2.c.g(j2, obj)) != 0;
            case 1:
                return Float.floatToRawIntBits(dr2.c.h(j2, obj)) != 0;
            case 2:
                return dr2.c.j(j2, obj) != 0;
            case 3:
                return dr2.c.j(j2, obj) != 0;
            case 4:
                return dr2.c.i(j2, obj) != 0;
            case 5:
                return dr2.c.j(j2, obj) != 0;
            case 6:
                return dr2.c.i(j2, obj) != 0;
            case 7:
                return dr2.c.d(j2, obj);
            case 8:
                Object objK = dr2.c.k(j2, obj);
                if (objK instanceof String) {
                    return !((String) objK).isEmpty();
                }
                if (objK instanceof ByteString) {
                    return !ByteString.c.equals(objK);
                }
                throw new IllegalArgumentException();
            case 9:
                return dr2.c.k(j2, obj) != null;
            case 10:
                return !ByteString.c.equals(dr2.c.k(j2, obj));
            case 11:
                return dr2.c.i(j2, obj) != 0;
            case 12:
                return dr2.c.i(j2, obj) != 0;
            case 13:
                return dr2.c.i(j2, obj) != 0;
            case 14:
                return dr2.c.j(j2, obj) != 0;
            case 15:
                return dr2.c.i(j2, obj) != 0;
            case 16:
                return dr2.c.j(j2, obj) != 0;
            case 17:
                return dr2.c.k(j2, obj) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean w(int i, int i2, Object obj) {
        return dr2.c.i((long) (this.a[i2 + 2] & 1048575), obj) == i;
    }

    public final void y(n nVar, Object obj, t02 t02Var, rl0 rl0Var) throws Throwable {
        int[] iArr;
        int i;
        int i2;
        int iY;
        au auVar;
        ya1 ya1Var;
        int[] iArr2 = this.h;
        int i3 = this.j;
        int i4 = this.i;
        Object objA = null;
        while (true) {
            try {
                e eVar = (e) t02Var;
                int iA = eVar.a();
                int iV = (iA < this.c || iA > this.d) ? -1 : V(iA, 0);
                if (iV >= 0) {
                    int iZ = Z(iV);
                    try {
                        try {
                            iY = Y(iZ);
                            auVar = eVar.a;
                            ya1Var = this.l;
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                            iArr = iArr2;
                            i2 = i3;
                        }
                        switch (iY) {
                            case 0:
                                iArr = iArr2;
                                i2 = i3;
                                long jG = G(iZ);
                                eVar.x(1);
                                dr2.r(obj, jG, auVar.m());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 1:
                                iArr = iArr2;
                                i2 = i3;
                                long jG2 = G(iZ);
                                eVar.x(5);
                                dr2.s(obj, jG2, auVar.q());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 2:
                                iArr = iArr2;
                                i2 = i3;
                                long jG3 = G(iZ);
                                eVar.x(0);
                                dr2.u(obj, jG3, auVar.s());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 3:
                                iArr = iArr2;
                                i2 = i3;
                                long jG4 = G(iZ);
                                eVar.x(0);
                                dr2.u(obj, jG4, auVar.C());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 4:
                                iArr = iArr2;
                                i2 = i3;
                                long jG5 = G(iZ);
                                eVar.x(0);
                                dr2.t(auVar.r(), jG5, obj);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 5:
                                iArr = iArr2;
                                i2 = i3;
                                long jG6 = G(iZ);
                                eVar.x(1);
                                dr2.u(obj, jG6, auVar.p());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 6:
                                iArr = iArr2;
                                i2 = i3;
                                long jG7 = G(iZ);
                                eVar.x(5);
                                dr2.t(auVar.o(), jG7, obj);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 7:
                                iArr = iArr2;
                                i2 = i3;
                                long jG8 = G(iZ);
                                eVar.x(0);
                                dr2.n(obj, jG8, auVar.k());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 8:
                                iArr = iArr2;
                                i2 = i3;
                                Q(obj, iZ, eVar);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 9:
                                iArr = iArr2;
                                i2 = i3;
                                Object obj2 = (wj1) C(iV, obj);
                                k72 k72VarQ = q(iV);
                                eVar.x(2);
                                eVar.c(obj2, k72VarQ, rl0Var);
                                W(obj, iV, obj2);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 10:
                                iArr = iArr2;
                                i2 = i3;
                                dr2.v(G(iZ), obj, eVar.e());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 11:
                                iArr = iArr2;
                                i2 = i3;
                                long jG9 = G(iZ);
                                eVar.x(0);
                                dr2.t(auVar.B(), jG9, obj);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 12:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.x(0);
                                int iN = auVar.n();
                                p51 p51VarO = o(iV);
                                if (p51VarO == null || p51VarO.isInRange(iN)) {
                                    dr2.t(iN, G(iZ), obj);
                                    T(iV, obj);
                                } else {
                                    objA = m.D(obj, iA, iN, objA, nVar);
                                }
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 13:
                                iArr = iArr2;
                                i2 = i3;
                                long jG10 = G(iZ);
                                eVar.x(5);
                                dr2.t(auVar.u(), jG10, obj);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 14:
                                iArr = iArr2;
                                i2 = i3;
                                long jG11 = G(iZ);
                                eVar.x(1);
                                dr2.u(obj, jG11, auVar.v());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 15:
                                iArr = iArr2;
                                i2 = i3;
                                long jG12 = G(iZ);
                                eVar.x(0);
                                dr2.t(auVar.w(), jG12, obj);
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 16:
                                iArr = iArr2;
                                i2 = i3;
                                long jG13 = G(iZ);
                                eVar.x(0);
                                dr2.u(obj, jG13, auVar.x());
                                T(iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 17:
                                iArr = iArr2;
                                i2 = i3;
                                Object obj3 = (wj1) C(iV, obj);
                                k72 k72VarQ2 = q(iV);
                                eVar.x(3);
                                eVar.b(obj3, k72VarQ2, rl0Var);
                                W(obj, iV, obj3);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 18:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.g(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 19:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.l(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 20:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.n(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 21:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.v(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 22:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.m(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 23:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.k(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 24:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.j(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 25:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.d(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 26:
                                iArr = iArr2;
                                i2 = i3;
                                R(obj, iZ, eVar);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 27:
                                iArr = iArr2;
                                i2 = i3;
                                P(obj, iZ, eVar, q(iV), rl0Var);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 28:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.f(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 29:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.u(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 30:
                                iArr = iArr2;
                                i2 = i3;
                                List listC = ya1Var.c(G(iZ), obj);
                                eVar.h(listC);
                                objA = m.z(obj, iA, listC, o(iV), objA, nVar);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 31:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.p(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.q(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.r(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.s(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.g(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.l(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.n(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.v(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.m(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 40:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.k(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 41:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.j(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 42:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.d(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 43:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.u(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 44:
                                iArr = iArr2;
                                i2 = i3;
                                List listC2 = ya1Var.c(G(iZ), obj);
                                eVar.h(listC2);
                                objA = m.z(obj, iA, listC2, o(iV), objA, nVar);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 45:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.p(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 46:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.q(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 47:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.r(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 48:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.s(ya1Var.c(G(iZ), obj));
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 49:
                                iArr = iArr2;
                                i2 = i3;
                                O(obj, G(iZ), eVar, q(iV), rl0Var);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 50:
                                iArr = iArr2;
                                i2 = i3;
                                z(obj, iV, p(iV), rl0Var, eVar);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 51:
                                iArr = iArr2;
                                i2 = i3;
                                long jG14 = G(iZ);
                                eVar.x(1);
                                dr2.v(jG14, obj, Double.valueOf(auVar.m()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 52:
                                iArr = iArr2;
                                i2 = i3;
                                long jG15 = G(iZ);
                                eVar.x(5);
                                dr2.v(jG15, obj, Float.valueOf(auVar.q()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 53:
                                iArr = iArr2;
                                i2 = i3;
                                long jG16 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG16, obj, Long.valueOf(auVar.s()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 54:
                                iArr = iArr2;
                                i2 = i3;
                                long jG17 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG17, obj, Long.valueOf(auVar.C()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 55:
                                iArr = iArr2;
                                i2 = i3;
                                long jG18 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG18, obj, Integer.valueOf(auVar.r()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 56:
                                iArr = iArr2;
                                i2 = i3;
                                long jG19 = G(iZ);
                                eVar.x(1);
                                dr2.v(jG19, obj, Long.valueOf(auVar.p()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 57:
                                iArr = iArr2;
                                i2 = i3;
                                long jG20 = G(iZ);
                                eVar.x(5);
                                dr2.v(jG20, obj, Integer.valueOf(auVar.o()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 58:
                                iArr = iArr2;
                                i2 = i3;
                                long jG21 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG21, obj, Boolean.valueOf(auVar.k()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 59:
                                iArr = iArr2;
                                i2 = i3;
                                Q(obj, iZ, eVar);
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 60:
                                iArr = iArr2;
                                i2 = i3;
                                Object obj4 = (wj1) D(iA, iV, obj);
                                k72 k72VarQ3 = q(iV);
                                eVar.x(2);
                                eVar.c(obj4, k72VarQ3, rl0Var);
                                X(iA, iV, obj, obj4);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 61:
                                iArr = iArr2;
                                i2 = i3;
                                dr2.v(G(iZ), obj, eVar.e());
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 62:
                                iArr = iArr2;
                                i2 = i3;
                                long jG22 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG22, obj, Integer.valueOf(auVar.B()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 63:
                                iArr = iArr2;
                                i2 = i3;
                                eVar.x(0);
                                int iN2 = auVar.n();
                                p51 p51VarO2 = o(iV);
                                if (p51VarO2 == null || p51VarO2.isInRange(iN2)) {
                                    dr2.v(G(iZ), obj, Integer.valueOf(iN2));
                                    U(iA, iV, obj);
                                } else {
                                    objA = m.D(obj, iA, iN2, objA, nVar);
                                }
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 64:
                                iArr = iArr2;
                                i2 = i3;
                                long jG23 = G(iZ);
                                eVar.x(5);
                                dr2.v(jG23, obj, Integer.valueOf(auVar.u()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 65:
                                iArr = iArr2;
                                i2 = i3;
                                long jG24 = G(iZ);
                                eVar.x(1);
                                dr2.v(jG24, obj, Long.valueOf(auVar.v()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 66:
                                iArr = iArr2;
                                i2 = i3;
                                long jG25 = G(iZ);
                                eVar.x(0);
                                dr2.v(jG25, obj, Integer.valueOf(auVar.w()));
                                U(iA, iV, obj);
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 67:
                                iArr = iArr2;
                                i2 = i3;
                                try {
                                    try {
                                        long jG26 = G(iZ);
                                        eVar.x(0);
                                        dr2.v(jG26, obj, Long.valueOf(auVar.x()));
                                        U(iA, iV, obj);
                                    } catch (Throwable th) {
                                        th = th;
                                        i = i2;
                                        Object objN = objA;
                                        while (i4 < i) {
                                            objN = n(obj, iArr[i4], objN, nVar, obj);
                                            i4++;
                                        }
                                        if (objN != null) {
                                            nVar.d(obj, objN);
                                        }
                                        throw th;
                                    }
                                } catch (InvalidProtocolBufferException.InvalidWireTypeException unused2) {
                                    nVar.getClass();
                                    if (objA == null) {
                                        objA = nVar.a(obj);
                                    }
                                    if (!nVar.c(objA, eVar)) {
                                        Object objN2 = objA;
                                        int i5 = i2;
                                        while (i4 < i5) {
                                            objN2 = n(obj, iArr[i4], objN2, nVar, obj);
                                            i4++;
                                        }
                                        if (objN2 != null) {
                                            nVar.d(obj, objN2);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            case 68:
                                Object obj5 = (wj1) D(iA, iV, obj);
                                k72 k72VarQ4 = q(iV);
                                eVar.x(3);
                                eVar.b(obj5, k72VarQ4, rl0Var);
                                X(iA, iV, obj, obj5);
                                iArr = iArr2;
                                i2 = i3;
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                            default:
                                if (objA == null) {
                                    objA = nVar.a(obj);
                                }
                                if (!nVar.c(objA, eVar)) {
                                    Object objN3 = objA;
                                    while (i4 < i3) {
                                        objN3 = n(obj, iArr2[i4], objN3, nVar, obj);
                                        i4++;
                                    }
                                    if (objN3 != null) {
                                        nVar.d(obj, objN3);
                                        return;
                                    }
                                    return;
                                }
                                iArr = iArr2;
                                i2 = i3;
                                i3 = i2;
                                iArr2 = iArr;
                                break;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        iArr = iArr2;
                        i2 = i3;
                    }
                } else {
                    if (iA == Integer.MAX_VALUE) {
                        Object objN4 = objA;
                        while (i4 < i3) {
                            objN4 = n(obj, iArr2[i4], objN4, nVar, obj);
                            i4++;
                        }
                        if (objN4 != null) {
                            nVar.d(obj, objN4);
                            return;
                        }
                        return;
                    }
                    nVar.getClass();
                    if (objA == null) {
                        objA = nVar.a(obj);
                    }
                    if (!nVar.c(objA, eVar)) {
                        Object objN5 = objA;
                        while (i4 < i3) {
                            objN5 = n(obj, iArr2[i4], objN5, nVar, obj);
                            i4++;
                        }
                        if (objN5 != null) {
                            nVar.d(obj, objN5);
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                iArr = iArr2;
                i = i3;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:
    
        r9.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a2, code lost:
    
        r0.i(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a5, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z(java.lang.Object r8, int r9, java.lang.Object r10, p.rl0 r11, p.t02 r12) throws com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException {
        /*
            r7 = this;
            int r9 = r7.Z(r9)
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r0
            long r0 = (long) r9
            p.cr2 r9 = p.dr2.c
            java.lang.Object r9 = r9.k(r0, r8)
            p.pe1 r2 = r7.n
            if (r9 != 0) goto L20
            r2.getClass()
            com.google.protobuf.MapFieldLite r9 = com.google.protobuf.MapFieldLite.c
            com.google.protobuf.MapFieldLite r9 = r9.g()
            p.dr2.v(r0, r8, r9)
            goto L36
        L20:
            r2.getClass()
            boolean r3 = p.pe1.d(r9)
            if (r3 == 0) goto L36
            com.google.protobuf.MapFieldLite r3 = com.google.protobuf.MapFieldLite.c
            com.google.protobuf.MapFieldLite r3 = r3.g()
            p.pe1.e(r3, r9)
            p.dr2.v(r0, r8, r3)
            r9 = r3
        L36:
            r2.getClass()
            com.google.protobuf.MapFieldLite r9 = (com.google.protobuf.MapFieldLite) r9
            p.oe1 r10 = (p.oe1) r10
            p.gh2 r8 = r10.a
            com.google.protobuf.e r12 = (com.google.protobuf.e) r12
            r10 = 2
            r12.x(r10)
            p.au r0 = r12.a
            int r1 = r0.B()
            int r1 = r0.j(r1)
            java.lang.Object r2 = r8.k
            java.lang.Object r3 = r8.m
        L53:
            int r4 = r12.a()     // Catch: java.lang.Throwable -> L77
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L9f
            boolean r5 = r0.e()     // Catch: java.lang.Throwable -> L77
            if (r5 == 0) goto L63
            goto L9f
        L63:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L88
            if (r4 == r10) goto L79
            boolean r4 = r12.y()     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            if (r4 == 0) goto L71
            goto L53
        L71:
            com.google.protobuf.InvalidProtocolBufferException r4 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            throw r4     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
        L77:
            r8 = move-exception
            goto La6
        L79:
            java.lang.Object r4 = r8.l     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            com.google.protobuf.WireFormat$FieldType r4 = (com.google.protobuf.WireFormat$FieldType) r4     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            java.lang.Object r5 = r8.m     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            java.lang.Class r5 = r5.getClass()     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            java.lang.Object r3 = r12.i(r4, r5, r11)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            goto L53
        L88:
            java.lang.Object r4 = r8.e     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            com.google.protobuf.WireFormat$FieldType r4 = (com.google.protobuf.WireFormat$FieldType) r4     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            r5 = 0
            java.lang.Object r2 = r12.i(r4, r5, r5)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L92
            goto L53
        L92:
            boolean r4 = r12.y()     // Catch: java.lang.Throwable -> L77
            if (r4 == 0) goto L99
            goto L53
        L99:
            com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L77
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L77
            throw r8     // Catch: java.lang.Throwable -> L77
        L9f:
            r9.put(r2, r3)     // Catch: java.lang.Throwable -> L77
            r0.i(r1)
            return
        La6:
            r0.i(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.k.z(java.lang.Object, int, java.lang.Object, p.rl0, p.t02):void");
    }
}

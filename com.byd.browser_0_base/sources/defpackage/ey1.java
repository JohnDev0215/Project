package defpackage;

import android.graphics.Path;
import android.util.Log;

/* JADX INFO: compiled from: PathParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class ey1 {
    public static boolean a(a[] aVarArr, a[] aVarArr2) {
        if (aVarArr == null || aVarArr2 == null || aVarArr.length != aVarArr2.length) {
            return false;
        }
        for (int i = 0; i < aVarArr.length; i++) {
            a aVar = aVarArr[i];
            char c = aVar.a;
            a aVar2 = aVarArr2[i];
            if (c != aVar2.a || aVar.b.length != aVar2.b.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] b(float[] fArr, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = i - 0;
        int iMin = Math.min(i2, length - 0);
        float[] fArr2 = new float[i2];
        System.arraycopy(fArr, 0, fArr2, 0, iMin);
        return fArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008a, code lost:
    
        if (r13 == 0) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097 A[Catch: NumberFormatException -> 0x00bc, LOOP:3: B:29:0x006c->B:49:0x0097, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00bc, blocks: (B:26:0x0059, B:29:0x006c, B:31:0x0072, B:36:0x0080, B:49:0x0097, B:51:0x009c, B:54:0x00ac, B:56:0x00b1), top: B:71:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009c A[Catch: NumberFormatException -> 0x00bc, TryCatch #0 {NumberFormatException -> 0x00bc, blocks: (B:26:0x0059, B:29:0x006c, B:31:0x0072, B:36:0x0080, B:49:0x0097, B:51:0x009c, B:54:0x00ac, B:56:0x00b1), top: B:71:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ac A[Catch: NumberFormatException -> 0x00bc, TryCatch #0 {NumberFormatException -> 0x00bc, blocks: (B:26:0x0059, B:29:0x006c, B:31:0x0072, B:36:0x0080, B:49:0x0097, B:51:0x009c, B:54:0x00ac, B:56:0x00b1), top: B:71:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0096 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static ey1.a[] c(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ey1.c(java.lang.String):ey1$a[]");
    }

    public static Path d(String str) {
        Path path = new Path();
        a[] aVarArrC = c(str);
        if (aVarArrC == null) {
            return null;
        }
        try {
            a.b(aVarArrC, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException(C0175b.j("Error in parsing ", str), e);
        }
    }

    public static a[] e(a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        a[] aVarArr2 = new a[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            aVarArr2[i] = new a(aVarArr[i]);
        }
        return aVarArr2;
    }

    /* JADX INFO: compiled from: PathParser.java */
    public static class a {
        public char a;
        public final float[] b;

        public a(char c, float[] fArr) {
            this.a = c;
            this.b = fArr;
        }

        public static void a(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double dCos = Math.cos(radians);
            double dSin = Math.sin(radians);
            double d3 = f;
            double d4 = f2;
            double d5 = (d4 * dSin) + (d3 * dCos);
            double d6 = d3;
            double d7 = f5;
            double d8 = d5 / d7;
            double d9 = f6;
            double d10 = ((d4 * dCos) + (((double) (-f)) * dSin)) / d9;
            double d11 = d4;
            double d12 = f4;
            double d13 = ((d12 * dSin) + (((double) f3) * dCos)) / d7;
            double d14 = ((d12 * dCos) + (((double) (-f3)) * dSin)) / d9;
            double d15 = d8 - d13;
            double d16 = d10 - d14;
            double d17 = (d8 + d13) / 2.0d;
            double d18 = (d10 + d14) / 2.0d;
            double d19 = (d16 * d16) + (d15 * d15);
            if (d19 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d20 = (1.0d / d19) - 0.25d;
            if (d20 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d19);
                float fSqrt = (float) (Math.sqrt(d19) / 1.99999d);
                a(path, f, f2, f3, f4, f5 * fSqrt, f6 * fSqrt, f7, z, z2);
                return;
            }
            double dSqrt = Math.sqrt(d20);
            double d21 = d15 * dSqrt;
            double d22 = dSqrt * d16;
            if (z == z2) {
                d = d17 - d22;
                d2 = d18 + d21;
            } else {
                d = d17 + d22;
                d2 = d18 - d21;
            }
            double dAtan2 = Math.atan2(d10 - d2, d8 - d);
            double dAtan22 = Math.atan2(d14 - d2, d13 - d) - dAtan2;
            if (z2 != (dAtan22 >= 0.0d)) {
                dAtan22 = dAtan22 > 0.0d ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
            }
            double d23 = d * d7;
            double d24 = d2 * d9;
            double d25 = (d23 * dCos) - (d24 * dSin);
            double d26 = (d24 * dCos) + (d23 * dSin);
            int iCeil = (int) Math.ceil(Math.abs((dAtan22 * 4.0d) / 3.141592653589793d));
            double dCos2 = Math.cos(radians);
            double dSin2 = Math.sin(radians);
            double dCos3 = Math.cos(dAtan2);
            double dSin3 = Math.sin(dAtan2);
            double d27 = -d7;
            double d28 = d27 * dCos2;
            double d29 = d9 * dSin2;
            double d30 = (d28 * dSin3) - (d29 * dCos3);
            double d31 = d27 * dSin2;
            double d32 = d9 * dCos2;
            double d33 = (dCos3 * d32) + (dSin3 * d31);
            double d34 = d32;
            double d35 = dAtan22 / ((double) iCeil);
            int i = 0;
            while (i < iCeil) {
                double d36 = dAtan2 + d35;
                double dSin4 = Math.sin(d36);
                double dCos4 = Math.cos(d36);
                double d37 = d35;
                double d38 = (((d7 * dCos2) * dCos4) + d25) - (d29 * dSin4);
                double d39 = d34;
                double d40 = d25;
                double d41 = (d39 * dSin4) + (d7 * dSin2 * dCos4) + d26;
                double d42 = (d28 * dSin4) - (d29 * dCos4);
                double d43 = (dCos4 * d39) + (dSin4 * d31);
                double d44 = d36 - dAtan2;
                double dTan = Math.tan(d44 / 2.0d);
                double dSqrt2 = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d44)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) ((d30 * dSqrt2) + d6), (float) ((d33 * dSqrt2) + d11), (float) (d38 - (dSqrt2 * d42)), (float) (d41 - (dSqrt2 * d43)), (float) d38, (float) d41);
                i++;
                dAtan2 = d36;
                d31 = d31;
                dCos2 = dCos2;
                iCeil = iCeil;
                d33 = d43;
                d7 = d7;
                d30 = d42;
                d6 = d38;
                d11 = d41;
                d25 = d40;
                d35 = d37;
                d34 = d39;
            }
        }

        public static void b(a[] aVarArr, Path path) {
            int i;
            int i2;
            char c;
            a aVar;
            int i3;
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            float f14;
            float f15;
            float f16;
            float f17;
            float f18;
            float f19;
            float f20;
            float f21;
            a[] aVarArr2 = aVarArr;
            int i4 = 6;
            float[] fArr = new float[6];
            char c2 = 'm';
            int i5 = 0;
            char c3 = 'm';
            int i6 = 0;
            while (i6 < aVarArr2.length) {
                a aVar2 = aVarArr2[i6];
                char c4 = aVar2.a;
                float f22 = fArr[i5];
                float f23 = fArr[1];
                float f24 = fArr[2];
                float f25 = fArr[3];
                float f26 = fArr[4];
                float f27 = fArr[5];
                switch (c4) {
                    case 'A':
                    case 'a':
                        i = 7;
                        break;
                    case 'C':
                    case 'c':
                        i = i4;
                        break;
                    case 'H':
                    case 'V':
                    case 'h':
                    case 'v':
                        i = 1;
                        break;
                    case 'Q':
                    case 'S':
                    case 'q':
                    case 's':
                        i = 4;
                        break;
                    case 'Z':
                    case 'z':
                        path.close();
                        path.moveTo(f26, f27);
                        f22 = f26;
                        f24 = f22;
                        f23 = f27;
                        f25 = f23;
                    default:
                        i = 2;
                        break;
                }
                float f28 = f26;
                float f29 = f27;
                float f30 = f22;
                float f31 = f23;
                int i7 = i5;
                while (true) {
                    float[] fArr2 = aVar2.b;
                    if (i7 < fArr2.length) {
                        if (c4 != 'A') {
                            if (c4 != 'C') {
                                if (c4 == 'H') {
                                    i2 = i7;
                                    c = c4;
                                    aVar = aVar2;
                                    i3 = i6;
                                    int i8 = i2 + 0;
                                    path.lineTo(fArr2[i8], f31);
                                    f30 = fArr2[i8];
                                } else if (c4 == 'Q') {
                                    i2 = i7;
                                    c = c4;
                                    aVar = aVar2;
                                    i3 = i6;
                                    int i9 = i2 + 0;
                                    int i10 = i2 + 1;
                                    int i11 = i2 + 2;
                                    int i12 = i2 + 3;
                                    path.quadTo(fArr2[i9], fArr2[i10], fArr2[i11], fArr2[i12]);
                                    f = fArr2[i9];
                                    f2 = fArr2[i10];
                                    f30 = fArr2[i11];
                                    f31 = fArr2[i12];
                                } else if (c4 == 'V') {
                                    i2 = i7;
                                    c = c4;
                                    aVar = aVar2;
                                    i3 = i6;
                                    int i13 = i2 + 0;
                                    path.lineTo(f30, fArr2[i13]);
                                    f31 = fArr2[i13];
                                } else if (c4 != 'a') {
                                    if (c4 != 'c') {
                                        if (c4 == 'h') {
                                            i2 = i7;
                                            int i14 = i2 + 0;
                                            path.rLineTo(fArr2[i14], 0.0f);
                                            f30 += fArr2[i14];
                                        } else if (c4 != 'q') {
                                            if (c4 != 'v') {
                                                if (c4 != 'L') {
                                                    if (c4 == 'M') {
                                                        i2 = i7;
                                                        f12 = fArr2[i2 + 0];
                                                        f13 = fArr2[i2 + 1];
                                                        if (i2 > 0) {
                                                            path.lineTo(f12, f13);
                                                        } else {
                                                            path.moveTo(f12, f13);
                                                            f28 = f12;
                                                            f29 = f13;
                                                        }
                                                    } else if (c4 == 'S') {
                                                        i2 = i7;
                                                        float f32 = f31;
                                                        float f33 = f30;
                                                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                                                            f14 = (f32 * 2.0f) - f25;
                                                            f15 = (f33 * 2.0f) - f24;
                                                        } else {
                                                            f15 = f33;
                                                            f14 = f32;
                                                        }
                                                        int i15 = i2 + 0;
                                                        int i16 = i2 + 1;
                                                        int i17 = i2 + 2;
                                                        int i18 = i2 + 3;
                                                        path.cubicTo(f15, f14, fArr2[i15], fArr2[i16], fArr2[i17], fArr2[i18]);
                                                        float f34 = fArr2[i15];
                                                        float f35 = fArr2[i16];
                                                        f9 = fArr2[i17];
                                                        f8 = fArr2[i18];
                                                        f24 = f34;
                                                        f25 = f35;
                                                        f30 = f9;
                                                        f31 = f8;
                                                    } else if (c4 == 'T') {
                                                        i2 = i7;
                                                        float f36 = f31;
                                                        float f37 = f30;
                                                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                                                            f16 = (f37 * 2.0f) - f24;
                                                            f17 = (f36 * 2.0f) - f25;
                                                        } else {
                                                            f16 = f37;
                                                            f17 = f36;
                                                        }
                                                        int i19 = i2 + 0;
                                                        int i20 = i2 + 1;
                                                        path.quadTo(f16, f17, fArr2[i19], fArr2[i20]);
                                                        f25 = f17;
                                                        f24 = f16;
                                                        c = c4;
                                                        aVar = aVar2;
                                                        i3 = i6;
                                                        f30 = fArr2[i19];
                                                        f31 = fArr2[i20];
                                                    } else if (c4 == 'l') {
                                                        i2 = i7;
                                                        f10 = f31;
                                                        int i21 = i2 + 0;
                                                        float f38 = fArr2[i21];
                                                        int i22 = i2 + 1;
                                                        path.rLineTo(f38, fArr2[i22]);
                                                        f30 += fArr2[i21];
                                                        f11 = fArr2[i22];
                                                    } else if (c4 == c2) {
                                                        i2 = i7;
                                                        float f39 = fArr2[i2 + 0];
                                                        f30 += f39;
                                                        float f40 = fArr2[i2 + 1];
                                                        f31 += f40;
                                                        if (i2 > 0) {
                                                            path.rLineTo(f39, f40);
                                                        } else {
                                                            path.rMoveTo(f39, f40);
                                                            f29 = f31;
                                                            f28 = f30;
                                                        }
                                                    } else if (c4 != 's') {
                                                        if (c4 == 't') {
                                                            if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                                                                f20 = f30 - f24;
                                                                f21 = f31 - f25;
                                                            } else {
                                                                f21 = 0.0f;
                                                                f20 = 0.0f;
                                                            }
                                                            int i23 = i7 + 0;
                                                            int i24 = i7 + 1;
                                                            path.rQuadTo(f20, f21, fArr2[i23], fArr2[i24]);
                                                            float f41 = f20 + f30;
                                                            float f42 = f21 + f31;
                                                            f30 += fArr2[i23];
                                                            f31 += fArr2[i24];
                                                            f25 = f42;
                                                            f24 = f41;
                                                        }
                                                        i2 = i7;
                                                    } else {
                                                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                                                            float f43 = f30 - f24;
                                                            f18 = f31 - f25;
                                                            f19 = f43;
                                                        } else {
                                                            f18 = 0.0f;
                                                            f19 = 0.0f;
                                                        }
                                                        int i25 = i7 + 0;
                                                        int i26 = i7 + 1;
                                                        int i27 = i7 + 2;
                                                        int i28 = i7 + 3;
                                                        i2 = i7;
                                                        f3 = f31;
                                                        float f44 = f30;
                                                        path.rCubicTo(f19, f18, fArr2[i25], fArr2[i26], fArr2[i27], fArr2[i28]);
                                                        f4 = fArr2[i25] + f44;
                                                        f5 = fArr2[i26] + f3;
                                                        f6 = f44 + fArr2[i27];
                                                        f7 = fArr2[i28];
                                                    }
                                                    f30 = f28;
                                                    f31 = f29;
                                                } else {
                                                    i2 = i7;
                                                    int i29 = i2 + 0;
                                                    int i30 = i2 + 1;
                                                    path.lineTo(fArr2[i29], fArr2[i30]);
                                                    f12 = fArr2[i29];
                                                    f13 = fArr2[i30];
                                                }
                                                f30 = f12;
                                                f31 = f13;
                                            } else {
                                                i2 = i7;
                                                f10 = f31;
                                                int i31 = i2 + 0;
                                                path.rLineTo(0.0f, fArr2[i31]);
                                                f11 = fArr2[i31];
                                            }
                                            f31 = f10 + f11;
                                        } else {
                                            i2 = i7;
                                            f3 = f31;
                                            float f45 = f30;
                                            int i32 = i2 + 0;
                                            float f46 = fArr2[i32];
                                            int i33 = i2 + 1;
                                            int i34 = i2 + 2;
                                            int i35 = i2 + 3;
                                            path.rQuadTo(f46, fArr2[i33], fArr2[i34], fArr2[i35]);
                                            f4 = fArr2[i32] + f45;
                                            f5 = fArr2[i33] + f3;
                                            float f47 = f45 + fArr2[i34];
                                            float f48 = fArr2[i35];
                                            f6 = f47;
                                            f7 = f48;
                                        }
                                        c = c4;
                                        aVar = aVar2;
                                        i3 = i6;
                                    } else {
                                        i2 = i7;
                                        f3 = f31;
                                        float f49 = f30;
                                        int i36 = i2 + 2;
                                        int i37 = i2 + 3;
                                        int i38 = i2 + 4;
                                        int i39 = i2 + 5;
                                        path.rCubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i36], fArr2[i37], fArr2[i38], fArr2[i39]);
                                        f4 = fArr2[i36] + f49;
                                        f5 = fArr2[i37] + f3;
                                        f6 = f49 + fArr2[i38];
                                        f7 = fArr2[i39];
                                    }
                                    f8 = f3 + f7;
                                    f24 = f4;
                                    f25 = f5;
                                    f9 = f6;
                                    f30 = f9;
                                    f31 = f8;
                                    c = c4;
                                    aVar = aVar2;
                                    i3 = i6;
                                } else {
                                    i2 = i7;
                                    float f50 = f31;
                                    float f51 = f30;
                                    int i40 = i2 + 5;
                                    int i41 = i2 + 6;
                                    c = c4;
                                    aVar = aVar2;
                                    i3 = i6;
                                    a(path, f51, f50, fArr2[i40] + f51, fArr2[i41] + f50, fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                                    f30 = f51 + fArr2[i40];
                                    f31 = f50 + fArr2[i41];
                                }
                                i7 = i2 + i;
                                aVar2 = aVar;
                                c3 = c;
                                c4 = c3;
                                i6 = i3;
                                c2 = 'm';
                                i5 = 0;
                            } else {
                                i2 = i7;
                                c = c4;
                                aVar = aVar2;
                                i3 = i6;
                                int i42 = i2 + 2;
                                int i43 = i2 + 3;
                                int i44 = i2 + 4;
                                int i45 = i2 + 5;
                                path.cubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i42], fArr2[i43], fArr2[i44], fArr2[i45]);
                                float f52 = fArr2[i44];
                                float f53 = fArr2[i45];
                                f = fArr2[i42];
                                f30 = f52;
                                f31 = f53;
                                f2 = fArr2[i43];
                            }
                            f24 = f;
                            f25 = f2;
                            i7 = i2 + i;
                            aVar2 = aVar;
                            c3 = c;
                            c4 = c3;
                            i6 = i3;
                            c2 = 'm';
                            i5 = 0;
                        } else {
                            i2 = i7;
                            c = c4;
                            aVar = aVar2;
                            i3 = i6;
                            int i46 = i2 + 5;
                            int i47 = i2 + 6;
                            a(path, f30, f31, fArr2[i46], fArr2[i47], fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                            f30 = fArr2[i46];
                            f31 = fArr2[i47];
                        }
                        f25 = f31;
                        f24 = f30;
                        i7 = i2 + i;
                        aVar2 = aVar;
                        c3 = c;
                        c4 = c3;
                        i6 = i3;
                        c2 = 'm';
                        i5 = 0;
                    }
                }
                int i48 = i6;
                int i49 = i5;
                fArr[i49] = f30;
                fArr[1] = f31;
                fArr[2] = f24;
                fArr[3] = f25;
                fArr[4] = f28;
                fArr[5] = f29;
                i6 = i48 + 1;
                i4 = 6;
                c2 = 'm';
                i5 = i49;
                c3 = aVarArr[i48].a;
                aVarArr2 = aVarArr;
            }
        }

        public a(a aVar) {
            this.a = aVar.a;
            float[] fArr = aVar.b;
            this.b = ey1.b(fArr, fArr.length);
        }
    }
}

package d0;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: PathParser.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: compiled from: PathParser.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2653b;
    }

    public static void a(ArrayList<b> arrayList, char c3, float[] fArr) {
        arrayList.add(new b(c3, fArr));
    }

    public static boolean b(b[] bVarArr, b[] bVarArr2) {
        if (bVarArr == null || bVarArr2 == null || bVarArr.length != bVarArr2.length) {
            return false;
        }
        for (int i3 = 0; i3 < bVarArr.length; i3++) {
            if (bVarArr[i3].f2654a != bVarArr2[i3].f2654a || bVarArr[i3].f2655b.length != bVarArr2[i3].f2655b.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] c(float[] fArr, int i3, int i4) {
        if (i3 > i4) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i3 < 0 || i3 > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i5 = i4 - i3;
        int iMin = Math.min(i5, length - i3);
        float[] fArr2 = new float[i5];
        System.arraycopy(fArr, i3, fArr2, 0, iMin);
        return fArr2;
    }

    public static b[] d(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 1;
        int i4 = 0;
        while (i3 < str.length()) {
            int i5 = i(str, i3);
            String strTrim = str.substring(i4, i5).trim();
            if (strTrim.length() > 0) {
                a(arrayList, strTrim.charAt(0), h(strTrim));
            }
            i4 = i5;
            i3 = i5 + 1;
        }
        if (i3 - i4 == 1 && i4 < str.length()) {
            a(arrayList, str.charAt(i4), new float[0]);
        }
        return (b[]) arrayList.toArray(new b[arrayList.size()]);
    }

    public static Path e(String str) {
        Path path = new Path();
        b[] bVarArrD = d(str);
        if (bVarArrD == null) {
            return null;
        }
        try {
            b.e(bVarArrD, path);
            return path;
        } catch (RuntimeException e3) {
            throw new RuntimeException("Error in parsing " + str, e3);
        }
    }

    public static b[] f(b[] bVarArr) {
        if (bVarArr == null) {
            return null;
        }
        b[] bVarArr2 = new b[bVarArr.length];
        for (int i3 = 0; i3 < bVarArr.length; i3++) {
            bVarArr2[i3] = new b(bVarArr[i3]);
        }
        return bVarArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003a A[LOOP:0: B:3:0x0007->B:24:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void g(java.lang.String r8, int r9, d0.c.a r10) {
        /*
            r0 = 0
            r10.f2653b = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L7:
            int r5 = r8.length()
            if (r1 >= r5) goto L3d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L35
            r6 = 69
            if (r5 == r6) goto L33
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L33
            switch(r5) {
                case 44: goto L35;
                case 45: goto L2a;
                case 46: goto L22;
                default: goto L21;
            }
        L21:
            goto L31
        L22:
            if (r3 != 0) goto L27
            r2 = r0
            r3 = r7
            goto L37
        L27:
            r10.f2653b = r7
            goto L35
        L2a:
            if (r1 == r9) goto L31
            if (r2 != 0) goto L31
            r10.f2653b = r7
            goto L35
        L31:
            r2 = r0
            goto L37
        L33:
            r2 = r7
            goto L37
        L35:
            r2 = r0
            r4 = r7
        L37:
            if (r4 == 0) goto L3a
            goto L3d
        L3a:
            int r1 = r1 + 1
            goto L7
        L3d:
            r10.f2652a = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: d0.c.g(java.lang.String, int, d0.c$a):void");
    }

    public static float[] h(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            a aVar = new a();
            int length = str.length();
            int i3 = 1;
            int i4 = 0;
            while (i3 < length) {
                g(str, i3, aVar);
                int i5 = aVar.f2652a;
                if (i3 < i5) {
                    fArr[i4] = Float.parseFloat(str.substring(i3, i5));
                    i4++;
                }
                i3 = aVar.f2653b ? i5 : i5 + 1;
            }
            return c(fArr, 0, i4);
        } catch (NumberFormatException e3) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e3);
        }
    }

    public static int i(String str, int i3) {
        while (i3 < str.length()) {
            char cCharAt = str.charAt(i3);
            if (((cCharAt - 'A') * (cCharAt - 'Z') <= 0 || (cCharAt - 'a') * (cCharAt - 'z') <= 0) && cCharAt != 'e' && cCharAt != 'E') {
                return i3;
            }
            i3++;
        }
        return i3;
    }

    public static void j(b[] bVarArr, b[] bVarArr2) {
        for (int i3 = 0; i3 < bVarArr2.length; i3++) {
            bVarArr[i3].f2654a = bVarArr2[i3].f2654a;
            for (int i4 = 0; i4 < bVarArr2[i3].f2655b.length; i4++) {
                bVarArr[i3].f2655b[i4] = bVarArr2[i3].f2655b[i4];
            }
        }
    }

    /* JADX INFO: compiled from: PathParser.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public char f2654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float[] f2655b;

        public b(char c3, float[] fArr) {
            this.f2654a = c3;
            this.f2655b = fArr;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static void a(Path path, float[] fArr, char c3, char c4, float[] fArr2) {
            int i3;
            int i4;
            int i5;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            char c5 = c4;
            boolean z3 = false;
            float f11 = fArr[0];
            float f12 = fArr[1];
            float f13 = fArr[2];
            float f14 = fArr[3];
            float f15 = fArr[4];
            float f16 = fArr[5];
            switch (c5) {
                case 'A':
                case 'a':
                    i3 = 7;
                    i4 = i3;
                    break;
                case 'C':
                case 'c':
                    i3 = 6;
                    i4 = i3;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i4 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                default:
                    i4 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i4 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f15, f16);
                    f11 = f15;
                    f13 = f11;
                    f12 = f16;
                    f14 = f12;
                    i4 = 2;
                    break;
            }
            float f17 = f11;
            float f18 = f12;
            float f19 = f15;
            float f20 = f16;
            int i6 = 0;
            char c6 = c3;
            while (i6 < fArr2.length) {
                if (c5 != 'A') {
                    if (c5 == 'C') {
                        i5 = i6;
                        int i7 = i5 + 2;
                        int i8 = i5 + 3;
                        int i9 = i5 + 4;
                        int i10 = i5 + 5;
                        path.cubicTo(fArr2[i5 + 0], fArr2[i5 + 1], fArr2[i7], fArr2[i8], fArr2[i9], fArr2[i10]);
                        f17 = fArr2[i9];
                        float f21 = fArr2[i10];
                        float f22 = fArr2[i7];
                        float f23 = fArr2[i8];
                        f18 = f21;
                        f14 = f23;
                        f13 = f22;
                    } else if (c5 == 'H') {
                        i5 = i6;
                        int i11 = i5 + 0;
                        path.lineTo(fArr2[i11], f18);
                        f17 = fArr2[i11];
                    } else if (c5 == 'Q') {
                        i5 = i6;
                        int i12 = i5 + 0;
                        int i13 = i5 + 1;
                        int i14 = i5 + 2;
                        int i15 = i5 + 3;
                        path.quadTo(fArr2[i12], fArr2[i13], fArr2[i14], fArr2[i15]);
                        float f24 = fArr2[i12];
                        float f25 = fArr2[i13];
                        f17 = fArr2[i14];
                        f18 = fArr2[i15];
                        f13 = f24;
                        f14 = f25;
                    } else if (c5 == 'V') {
                        i5 = i6;
                        int i16 = i5 + 0;
                        path.lineTo(f17, fArr2[i16]);
                        f18 = fArr2[i16];
                    } else if (c5 != 'a') {
                        if (c5 != 'c') {
                            if (c5 == 'h') {
                                int i17 = i6 + 0;
                                path.rLineTo(fArr2[i17], 0.0f);
                                f17 += fArr2[i17];
                            } else if (c5 != 'q') {
                                if (c5 == 'v') {
                                    int i18 = i6 + 0;
                                    path.rLineTo(0.0f, fArr2[i18]);
                                    f6 = fArr2[i18];
                                } else if (c5 == 'L') {
                                    int i19 = i6 + 0;
                                    int i20 = i6 + 1;
                                    path.lineTo(fArr2[i19], fArr2[i20]);
                                    f17 = fArr2[i19];
                                    f18 = fArr2[i20];
                                } else if (c5 == 'M') {
                                    int i21 = i6 + 0;
                                    f17 = fArr2[i21];
                                    int i22 = i6 + 1;
                                    f18 = fArr2[i22];
                                    if (i6 > 0) {
                                        path.lineTo(fArr2[i21], fArr2[i22]);
                                    } else {
                                        path.moveTo(fArr2[i21], fArr2[i22]);
                                        i5 = i6;
                                        f20 = f18;
                                        f19 = f17;
                                    }
                                } else if (c5 == 'S') {
                                    if (c6 == 'c' || c6 == 's' || c6 == 'C' || c6 == 'S') {
                                        f17 = (f17 * 2.0f) - f13;
                                        f18 = (f18 * 2.0f) - f14;
                                    }
                                    float f26 = f18;
                                    int i23 = i6 + 0;
                                    int i24 = i6 + 1;
                                    int i25 = i6 + 2;
                                    int i26 = i6 + 3;
                                    path.cubicTo(f17, f26, fArr2[i23], fArr2[i24], fArr2[i25], fArr2[i26]);
                                    f3 = fArr2[i23];
                                    f4 = fArr2[i24];
                                    f17 = fArr2[i25];
                                    f18 = fArr2[i26];
                                    f13 = f3;
                                    f14 = f4;
                                } else if (c5 == 'T') {
                                    if (c6 == 'q' || c6 == 't' || c6 == 'Q' || c6 == 'T') {
                                        f17 = (f17 * 2.0f) - f13;
                                        f18 = (f18 * 2.0f) - f14;
                                    }
                                    int i27 = i6 + 0;
                                    int i28 = i6 + 1;
                                    path.quadTo(f17, f18, fArr2[i27], fArr2[i28]);
                                    float f27 = fArr2[i27];
                                    float f28 = fArr2[i28];
                                    i5 = i6;
                                    f14 = f18;
                                    f13 = f17;
                                    f17 = f27;
                                    f18 = f28;
                                } else if (c5 == 'l') {
                                    int i29 = i6 + 0;
                                    int i30 = i6 + 1;
                                    path.rLineTo(fArr2[i29], fArr2[i30]);
                                    f17 += fArr2[i29];
                                    f6 = fArr2[i30];
                                } else if (c5 == 'm') {
                                    int i31 = i6 + 0;
                                    f17 += fArr2[i31];
                                    int i32 = i6 + 1;
                                    f18 += fArr2[i32];
                                    if (i6 > 0) {
                                        path.rLineTo(fArr2[i31], fArr2[i32]);
                                    } else {
                                        path.rMoveTo(fArr2[i31], fArr2[i32]);
                                        i5 = i6;
                                        f20 = f18;
                                        f19 = f17;
                                    }
                                } else if (c5 == 's') {
                                    if (c6 == 'c' || c6 == 's' || c6 == 'C' || c6 == 'S') {
                                        float f29 = f17 - f13;
                                        f7 = f18 - f14;
                                        f8 = f29;
                                    } else {
                                        f8 = 0.0f;
                                        f7 = 0.0f;
                                    }
                                    int i33 = i6 + 0;
                                    int i34 = i6 + 1;
                                    int i35 = i6 + 2;
                                    int i36 = i6 + 3;
                                    path.rCubicTo(f8, f7, fArr2[i33], fArr2[i34], fArr2[i35], fArr2[i36]);
                                    f3 = fArr2[i33] + f17;
                                    f4 = fArr2[i34] + f18;
                                    f17 += fArr2[i35];
                                    f5 = fArr2[i36];
                                } else if (c5 == 't') {
                                    if (c6 == 'q' || c6 == 't' || c6 == 'Q' || c6 == 'T') {
                                        f9 = f17 - f13;
                                        f10 = f18 - f14;
                                    } else {
                                        f10 = 0.0f;
                                        f9 = 0.0f;
                                    }
                                    int i37 = i6 + 0;
                                    int i38 = i6 + 1;
                                    path.rQuadTo(f9, f10, fArr2[i37], fArr2[i38]);
                                    float f30 = f9 + f17;
                                    float f31 = f10 + f18;
                                    f17 += fArr2[i37];
                                    f18 += fArr2[i38];
                                    f14 = f31;
                                    f13 = f30;
                                }
                                f18 += f6;
                            } else {
                                int i39 = i6 + 0;
                                int i40 = i6 + 1;
                                int i41 = i6 + 2;
                                int i42 = i6 + 3;
                                path.rQuadTo(fArr2[i39], fArr2[i40], fArr2[i41], fArr2[i42]);
                                f3 = fArr2[i39] + f17;
                                f4 = fArr2[i40] + f18;
                                f17 += fArr2[i41];
                                f5 = fArr2[i42];
                            }
                            i5 = i6;
                        } else {
                            int i43 = i6 + 2;
                            int i44 = i6 + 3;
                            int i45 = i6 + 4;
                            int i46 = i6 + 5;
                            path.rCubicTo(fArr2[i6 + 0], fArr2[i6 + 1], fArr2[i43], fArr2[i44], fArr2[i45], fArr2[i46]);
                            f3 = fArr2[i43] + f17;
                            f4 = fArr2[i44] + f18;
                            f17 += fArr2[i45];
                            f5 = fArr2[i46];
                        }
                        f18 += f5;
                        f13 = f3;
                        f14 = f4;
                        i5 = i6;
                    } else {
                        int i47 = i6 + 5;
                        int i48 = i6 + 6;
                        i5 = i6;
                        c(path, f17, f18, fArr2[i47] + f17, fArr2[i48] + f18, fArr2[i6 + 0], fArr2[i6 + 1], fArr2[i6 + 2], fArr2[i6 + 3] != 0.0f, fArr2[i6 + 4] != 0.0f);
                        f17 += fArr2[i47];
                        f18 += fArr2[i48];
                    }
                    i6 = i5 + i4;
                    c6 = c4;
                    c5 = c6;
                    z3 = false;
                } else {
                    i5 = i6;
                    int i49 = i5 + 5;
                    int i50 = i5 + 6;
                    c(path, f17, f18, fArr2[i49], fArr2[i50], fArr2[i5 + 0], fArr2[i5 + 1], fArr2[i5 + 2], fArr2[i5 + 3] != 0.0f, fArr2[i5 + 4] != 0.0f);
                    f17 = fArr2[i49];
                    f18 = fArr2[i50];
                }
                f14 = f18;
                f13 = f17;
                i6 = i5 + i4;
                c6 = c4;
                c5 = c6;
                z3 = false;
            }
            fArr[z3 ? 1 : 0] = f17;
            fArr[1] = f18;
            fArr[2] = f13;
            fArr[3] = f14;
            fArr[4] = f19;
            fArr[5] = f20;
        }

        public static void b(Path path, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11) {
            double d12 = d5;
            int iCeil = (int) Math.ceil(Math.abs((d11 * 4.0d) / 3.141592653589793d));
            double dCos = Math.cos(d9);
            double dSin = Math.sin(d9);
            double dCos2 = Math.cos(d10);
            double dSin2 = Math.sin(d10);
            double d13 = -d12;
            double d14 = d13 * dCos;
            double d15 = d6 * dSin;
            double d16 = (d14 * dSin2) - (d15 * dCos2);
            double d17 = d13 * dSin;
            double d18 = d6 * dCos;
            double d19 = (dSin2 * d17) + (dCos2 * d18);
            double d20 = d11 / ((double) iCeil);
            double d21 = d19;
            double d22 = d16;
            int i3 = 0;
            double d23 = d7;
            double d24 = d8;
            double d25 = d10;
            while (i3 < iCeil) {
                double d26 = d25 + d20;
                double dSin3 = Math.sin(d26);
                double dCos3 = Math.cos(d26);
                double d27 = (d3 + ((d12 * dCos) * dCos3)) - (d15 * dSin3);
                double d28 = d4 + (d12 * dSin * dCos3) + (d18 * dSin3);
                double d29 = (d14 * dSin3) - (d15 * dCos3);
                double d30 = (dSin3 * d17) + (dCos3 * d18);
                double d31 = d26 - d25;
                double dTan = Math.tan(d31 / 2.0d);
                double dSin4 = (Math.sin(d31) * (Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d)) / 3.0d;
                double d32 = d23 + (d22 * dSin4);
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) d32, (float) (d24 + (d21 * dSin4)), (float) (d27 - (dSin4 * d29)), (float) (d28 - (dSin4 * d30)), (float) d27, (float) d28);
                i3++;
                d20 = d20;
                dSin = dSin;
                d23 = d27;
                d17 = d17;
                dCos = dCos;
                d25 = d26;
                d21 = d30;
                d22 = d29;
                iCeil = iCeil;
                d24 = d28;
                d12 = d5;
            }
        }

        public static void c(Path path, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z3, boolean z4) {
            double d3;
            double d4;
            double radians = Math.toRadians(f9);
            double dCos = Math.cos(radians);
            double dSin = Math.sin(radians);
            double d5 = f3;
            double d6 = d5 * dCos;
            double d7 = f4;
            double d8 = f7;
            double d9 = (d6 + (d7 * dSin)) / d8;
            double d10 = (((double) (-f3)) * dSin) + (d7 * dCos);
            double d11 = f8;
            double d12 = d10 / d11;
            double d13 = f6;
            double d14 = ((((double) f5) * dCos) + (d13 * dSin)) / d8;
            double d15 = ((((double) (-f5)) * dSin) + (d13 * dCos)) / d11;
            double d16 = d9 - d14;
            double d17 = d12 - d15;
            double d18 = (d9 + d14) / 2.0d;
            double d19 = (d12 + d15) / 2.0d;
            double d20 = (d16 * d16) + (d17 * d17);
            if (d20 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d21 = (1.0d / d20) - 0.25d;
            if (d21 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d20);
                float fSqrt = (float) (Math.sqrt(d20) / 1.99999d);
                c(path, f3, f4, f5, f6, f7 * fSqrt, f8 * fSqrt, f9, z3, z4);
                return;
            }
            double dSqrt = Math.sqrt(d21);
            double d22 = d16 * dSqrt;
            double d23 = dSqrt * d17;
            if (z3 == z4) {
                d3 = d18 - d23;
                d4 = d19 + d22;
            } else {
                d3 = d18 + d23;
                d4 = d19 - d22;
            }
            double dAtan2 = Math.atan2(d12 - d4, d9 - d3);
            double dAtan22 = Math.atan2(d15 - d4, d14 - d3) - dAtan2;
            if (z4 != (dAtan22 >= 0.0d)) {
                dAtan22 = dAtan22 > 0.0d ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
            }
            double d24 = d3 * d8;
            double d25 = d4 * d11;
            b(path, (d24 * dCos) - (d25 * dSin), (d24 * dSin) + (d25 * dCos), d8, d11, d5, d7, radians, dAtan2, dAtan22);
        }

        public static void e(b[] bVarArr, Path path) {
            float[] fArr = new float[6];
            char c3 = 'm';
            for (int i3 = 0; i3 < bVarArr.length; i3++) {
                a(path, fArr, c3, bVarArr[i3].f2654a, bVarArr[i3].f2655b);
                c3 = bVarArr[i3].f2654a;
            }
        }

        public void d(b bVar, b bVar2, float f3) {
            this.f2654a = bVar.f2654a;
            int i3 = 0;
            while (true) {
                float[] fArr = bVar.f2655b;
                if (i3 >= fArr.length) {
                    return;
                }
                this.f2655b[i3] = (fArr[i3] * (1.0f - f3)) + (bVar2.f2655b[i3] * f3);
                i3++;
            }
        }

        public b(b bVar) {
            this.f2654a = bVar.f2654a;
            float[] fArr = bVar.f2655b;
            this.f2655b = c.c(fArr, 0, fArr.length);
        }
    }
}

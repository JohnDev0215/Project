package defpackage;

import android.graphics.Path;
import android.util.Log;

/* JADX INFO: renamed from: p2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1305p2 {
    public char a;
    public float[] b;

    public C1305p2(char c, float[] fArr) {
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
        int i = 0;
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
        double d34 = dAtan22 / ((double) iCeil);
        double d35 = dAtan2;
        while (i < iCeil) {
            double d36 = d35 + d34;
            double dSin4 = Math.sin(d36);
            double dCos4 = Math.cos(d36);
            double d37 = d34;
            double d38 = (((d7 * dCos2) * dCos4) + d25) - (d29 * dSin4);
            double d39 = d25;
            double d40 = (d32 * dSin4) + (d7 * dSin2 * dCos4) + d26;
            double d41 = (d28 * dSin4) - (d29 * dCos4);
            double d42 = (dCos4 * d32) + (dSin4 * d31);
            double d43 = d36 - d35;
            double dTan = Math.tan(d43 / 2.0d);
            double dSqrt2 = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d43)) / 3.0d;
            path.rLineTo(0.0f, 0.0f);
            path.cubicTo((float) ((d30 * dSqrt2) + d6), (float) ((d33 * dSqrt2) + d11), (float) (d38 - (dSqrt2 * d41)), (float) (d40 - (dSqrt2 * d42)), (float) d38, (float) d40);
            i++;
            d32 = d32;
            d31 = d31;
            iCeil = iCeil;
            dCos2 = dCos2;
            d35 = d36;
            d7 = d7;
            d33 = d42;
            d30 = d41;
            d6 = d38;
            d11 = d40;
            d34 = d37;
            d25 = d39;
        }
    }

    public static void b(C1305p2[] c1305p2Arr, Path path) {
        int i;
        int i2;
        float[] fArr;
        char c;
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
        C1305p2[] c1305p2Arr2 = c1305p2Arr;
        float[] fArr2 = new float[6];
        char c2 = 'm';
        char c3 = 0;
        char c4 = 'm';
        int i4 = 0;
        while (i4 < c1305p2Arr2.length) {
            char c5 = c1305p2Arr2[i4].a;
            float[] fArr3 = c1305p2Arr2[i4].b;
            float f20 = fArr2[c3];
            float f21 = fArr2[1];
            float f22 = fArr2[2];
            float f23 = fArr2[3];
            float f24 = fArr2[4];
            float f25 = fArr2[5];
            switch (c5) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
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
                    path.moveTo(f24, f25);
                    f20 = f24;
                    f22 = f20;
                    f21 = f25;
                    f23 = f21;
                default:
                    i = 2;
                    break;
            }
            float f26 = f24;
            float f27 = f25;
            float f28 = f20;
            float f29 = f21;
            int i5 = 0;
            while (i5 < fArr3.length) {
                if (c5 != 'A') {
                    if (c5 != 'C') {
                        if (c5 == 'H') {
                            i2 = i5;
                            fArr = fArr3;
                            c = c5;
                            i3 = i4;
                            int i6 = i2 + 0;
                            path.lineTo(fArr[i6], f29);
                            f28 = fArr[i6];
                        } else if (c5 == 'Q') {
                            i2 = i5;
                            fArr = fArr3;
                            c = c5;
                            i3 = i4;
                            int i7 = i2 + 0;
                            int i8 = i2 + 1;
                            int i9 = i2 + 2;
                            int i10 = i2 + 3;
                            path.quadTo(fArr[i7], fArr[i8], fArr[i9], fArr[i10]);
                            f = fArr[i7];
                            f2 = fArr[i8];
                            f28 = fArr[i9];
                            f29 = fArr[i10];
                        } else if (c5 == 'V') {
                            i2 = i5;
                            fArr = fArr3;
                            c = c5;
                            i3 = i4;
                            int i11 = i2 + 0;
                            path.lineTo(f28, fArr[i11]);
                            f29 = fArr[i11];
                        } else if (c5 != 'a') {
                            if (c5 != 'c') {
                                if (c5 == 'h') {
                                    i2 = i5;
                                    int i12 = i2 + 0;
                                    path.rLineTo(fArr3[i12], 0.0f);
                                    f28 += fArr3[i12];
                                } else if (c5 != 'q') {
                                    if (c5 == 'v') {
                                        i2 = i5;
                                        f10 = f29;
                                        int i13 = i2 + 0;
                                        path.rLineTo(0.0f, fArr3[i13]);
                                        f11 = fArr3[i13];
                                    } else if (c5 == 'L') {
                                        i2 = i5;
                                        int i14 = i2 + 0;
                                        int i15 = i2 + 1;
                                        path.lineTo(fArr3[i14], fArr3[i15]);
                                        f28 = fArr3[i14];
                                        f29 = fArr3[i15];
                                    } else if (c5 == 'M') {
                                        i2 = i5;
                                        int i16 = i2 + 0;
                                        float f30 = fArr3[i16];
                                        int i17 = i2 + 1;
                                        float f31 = fArr3[i17];
                                        if (i2 > 0) {
                                            path.lineTo(fArr3[i16], fArr3[i17]);
                                            f28 = f30;
                                            f29 = f31;
                                        } else {
                                            path.moveTo(fArr3[i16], fArr3[i17]);
                                            f26 = f30;
                                            f27 = f31;
                                            f28 = f26;
                                            f29 = f27;
                                        }
                                    } else if (c5 == 'S') {
                                        i2 = i5;
                                        float f32 = f29;
                                        float f33 = f28;
                                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                            f12 = (f32 * 2.0f) - f23;
                                            f13 = (f33 * 2.0f) - f22;
                                        } else {
                                            f13 = f33;
                                            f12 = f32;
                                        }
                                        int i18 = i2 + 0;
                                        int i19 = i2 + 1;
                                        int i20 = i2 + 2;
                                        int i21 = i2 + 3;
                                        path.cubicTo(f13, f12, fArr3[i18], fArr3[i19], fArr3[i20], fArr3[i21]);
                                        float f34 = fArr3[i18];
                                        float f35 = fArr3[i19];
                                        f9 = fArr3[i20];
                                        f8 = fArr3[i21];
                                        f22 = f34;
                                        f23 = f35;
                                        f28 = f9;
                                        f29 = f8;
                                    } else if (c5 == 'T') {
                                        i2 = i5;
                                        float f36 = f29;
                                        float f37 = f28;
                                        if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                            f14 = (f37 * 2.0f) - f22;
                                            f15 = (f36 * 2.0f) - f23;
                                        } else {
                                            f14 = f37;
                                            f15 = f36;
                                        }
                                        int i22 = i2 + 0;
                                        int i23 = i2 + 1;
                                        path.quadTo(f14, f15, fArr3[i22], fArr3[i23]);
                                        f23 = f15;
                                        f22 = f14;
                                        fArr = fArr3;
                                        c = c5;
                                        i3 = i4;
                                        f28 = fArr3[i22];
                                        f29 = fArr3[i23];
                                    } else if (c5 == 'l') {
                                        i2 = i5;
                                        f10 = f29;
                                        int i24 = i2 + 0;
                                        int i25 = i2 + 1;
                                        path.rLineTo(fArr3[i24], fArr3[i25]);
                                        f28 += fArr3[i24];
                                        f11 = fArr3[i25];
                                    } else if (c5 == c2) {
                                        i2 = i5;
                                        int i26 = i2 + 0;
                                        f28 += fArr3[i26];
                                        int i27 = i2 + 1;
                                        f29 += fArr3[i27];
                                        if (i2 > 0) {
                                            path.rLineTo(fArr3[i26], fArr3[i27]);
                                        } else {
                                            path.rMoveTo(fArr3[i26], fArr3[i27]);
                                            f27 = f29;
                                            f26 = f28;
                                            f28 = f26;
                                            f29 = f27;
                                        }
                                    } else if (c5 != 's') {
                                        if (c5 == 't') {
                                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                f18 = f28 - f22;
                                                f19 = f29 - f23;
                                            } else {
                                                f19 = 0.0f;
                                                f18 = 0.0f;
                                            }
                                            int i28 = i5 + 0;
                                            int i29 = i5 + 1;
                                            path.rQuadTo(f18, f19, fArr3[i28], fArr3[i29]);
                                            float f38 = f18 + f28;
                                            float f39 = f19 + f29;
                                            f28 += fArr3[i28];
                                            f29 += fArr3[i29];
                                            f23 = f39;
                                            f22 = f38;
                                        }
                                        i2 = i5;
                                    } else {
                                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                            f16 = f29 - f23;
                                            f17 = f28 - f22;
                                        } else {
                                            f17 = 0.0f;
                                            f16 = 0.0f;
                                        }
                                        int i30 = i5 + 0;
                                        int i31 = i5 + 1;
                                        int i32 = i5 + 2;
                                        int i33 = i5 + 3;
                                        i2 = i5;
                                        f3 = f29;
                                        float f40 = f28;
                                        path.rCubicTo(f17, f16, fArr3[i30], fArr3[i31], fArr3[i32], fArr3[i33]);
                                        f4 = fArr3[i30] + f40;
                                        f5 = fArr3[i31] + f3;
                                        f6 = f40 + fArr3[i32];
                                        f7 = fArr3[i33];
                                    }
                                    f29 = f10 + f11;
                                } else {
                                    i2 = i5;
                                    f3 = f29;
                                    float f41 = f28;
                                    int i34 = i2 + 0;
                                    int i35 = i2 + 1;
                                    int i36 = i2 + 2;
                                    int i37 = i2 + 3;
                                    path.rQuadTo(fArr3[i34], fArr3[i35], fArr3[i36], fArr3[i37]);
                                    f4 = fArr3[i34] + f41;
                                    f5 = fArr3[i35] + f3;
                                    float f42 = f41 + fArr3[i36];
                                    float f43 = fArr3[i37];
                                    f6 = f42;
                                    f7 = f43;
                                }
                                fArr = fArr3;
                                c = c5;
                                i3 = i4;
                            } else {
                                i2 = i5;
                                f3 = f29;
                                float f44 = f28;
                                int i38 = i2 + 2;
                                int i39 = i2 + 3;
                                int i40 = i2 + 4;
                                int i41 = i2 + 5;
                                path.rCubicTo(fArr3[i2 + 0], fArr3[i2 + 1], fArr3[i38], fArr3[i39], fArr3[i40], fArr3[i41]);
                                f4 = fArr3[i38] + f44;
                                f5 = fArr3[i39] + f3;
                                f6 = f44 + fArr3[i40];
                                f7 = fArr3[i41];
                            }
                            f8 = f3 + f7;
                            f22 = f4;
                            f23 = f5;
                            f9 = f6;
                            f28 = f9;
                            f29 = f8;
                            fArr = fArr3;
                            c = c5;
                            i3 = i4;
                        } else {
                            i2 = i5;
                            float f45 = f29;
                            float f46 = f28;
                            int i42 = i2 + 5;
                            int i43 = i2 + 6;
                            fArr = fArr3;
                            c = c5;
                            i3 = i4;
                            a(path, f46, f45, fArr3[i42] + f46, fArr3[i43] + f45, fArr3[i2 + 0], fArr3[i2 + 1], fArr3[i2 + 2], fArr3[i2 + 3] != 0.0f, fArr3[i2 + 4] != 0.0f);
                            f28 = f46 + fArr[i42];
                            f29 = f45 + fArr[i43];
                        }
                        i5 = i2 + i;
                        c4 = c;
                        c5 = c4;
                        fArr3 = fArr;
                        i4 = i3;
                        c2 = 'm';
                    } else {
                        i2 = i5;
                        fArr = fArr3;
                        c = c5;
                        i3 = i4;
                        int i44 = i2 + 2;
                        int i45 = i2 + 3;
                        int i46 = i2 + 4;
                        int i47 = i2 + 5;
                        path.cubicTo(fArr[i2 + 0], fArr[i2 + 1], fArr[i44], fArr[i45], fArr[i46], fArr[i47]);
                        float f47 = fArr[i46];
                        float f48 = fArr[i47];
                        f = fArr[i44];
                        f28 = f47;
                        f29 = f48;
                        f2 = fArr[i45];
                    }
                    f22 = f;
                    f23 = f2;
                    i5 = i2 + i;
                    c4 = c;
                    c5 = c4;
                    fArr3 = fArr;
                    i4 = i3;
                    c2 = 'm';
                } else {
                    i2 = i5;
                    fArr = fArr3;
                    c = c5;
                    i3 = i4;
                    int i48 = i2 + 5;
                    int i49 = i2 + 6;
                    a(path, f28, f29, fArr[i48], fArr[i49], fArr[i2 + 0], fArr[i2 + 1], fArr[i2 + 2], fArr[i2 + 3] != 0.0f, fArr[i2 + 4] != 0.0f);
                    f28 = fArr[i48];
                    f29 = fArr[i49];
                }
                f23 = f29;
                f22 = f28;
                i5 = i2 + i;
                c4 = c;
                c5 = c4;
                fArr3 = fArr;
                i4 = i3;
                c2 = 'm';
            }
            int i50 = i4;
            fArr2[0] = f28;
            fArr2[1] = f29;
            fArr2[2] = f22;
            fArr2[3] = f23;
            fArr2[4] = f26;
            fArr2[5] = f27;
            i4 = i50 + 1;
            c4 = c1305p2Arr[i50].a;
            c2 = 'm';
            c3 = 0;
            c1305p2Arr2 = c1305p2Arr;
        }
    }

    public C1305p2(C1305p2 c1305p2) {
        this.a = c1305p2.a;
        float[] fArr = c1305p2.b;
        this.b = AbstractC1360q2.b(fArr, 0, fArr.length);
    }
}

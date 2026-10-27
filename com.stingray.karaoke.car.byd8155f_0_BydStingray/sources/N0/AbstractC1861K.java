package n0;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import java.util.Iterator;
import java.util.List;
import m0.AbstractC1792a;
import o0.AbstractC2006b;
import o0.AbstractC2007c;
import o0.C2008d;
import o0.C2016l;
import o0.C2017m;
import o0.C2021q;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: n0.K, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1861K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l9.n f19381a = new l9.n(1);

    public static final Rect A(a1.i iVar) {
        return new Rect(iVar.f11035a, iVar.f11036b, iVar.c, iVar.d);
    }

    public static final Rect B(m0.d dVar) {
        return new Rect((int) dVar.f18882a, (int) dVar.f18883b, (int) dVar.c, (int) dVar.d);
    }

    public static final RectF C(m0.d dVar) {
        return new RectF(dVar.f18882a, dVar.f18883b, dVar.c, dVar.d);
    }

    public static final Shader.TileMode D(int i7) {
        if (u(i7, 0)) {
            return Shader.TileMode.CLAMP;
        }
        if (u(i7, 1)) {
            return Shader.TileMode.REPEAT;
        }
        if (u(i7, 2)) {
            return Shader.TileMode.MIRROR;
        }
        if (u(i7, 3) && Build.VERSION.SDK_INT >= 31) {
            return C1869T.f19421a.b();
        }
        return Shader.TileMode.CLAMP;
    }

    public static final int E(long j3) {
        float[] fArr = C2008d.f20180a;
        return (int) (C1891t.a(j3, C2008d.c) >>> 32);
    }

    public static final Bitmap.Config F(int i7) {
        return C1852B.a(i7, 0) ? Bitmap.Config.ARGB_8888 : C1852B.a(i7, 1) ? Bitmap.Config.ALPHA_8 : C1852B.a(i7, 2) ? Bitmap.Config.RGB_565 : C1852B.a(i7, 3) ? Bitmap.Config.RGBA_F16 : C1852B.a(i7, 4) ? Bitmap.Config.HARDWARE : Bitmap.Config.ARGB_8888;
    }

    public static final m0.d G(RectF rectF) {
        return new m0.d(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public static String H(int i7) {
        return u(i7, 0) ? "Clamp" : u(i7, 1) ? "Repeated" : u(i7, 2) ? "Mirror" : u(i7, 3) ? "Decal" : "Unknown";
    }

    public static final void I(List list, List list2) {
        if (list2 == null) {
            if (list.size() < 2) {
                throw new IllegalArgumentException("colors must have length of at least 2 if colorStops is omitted.");
            }
        } else if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors and colorStops arguments must have equal length.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000d A[PHI: r0
  0x000d: PHI (r0v2 float) = (r0v1 float), (r0v0 float) binds: [B:11:0x001c, B:5:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int J(float r3, float[] r4, int r5) {
        /*
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            r2 = 2143289344(0x7fc00000, float:NaN)
            if (r1 >= 0) goto L11
            r1 = -1251999744(0xffffffffb5600000, float:-8.34465E-7)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 < 0) goto Lf
        Ld:
            r3 = r0
            goto L1f
        Lf:
            r3 = r2
            goto L1f
        L11:
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L1f
            r1 = 1065353223(0x3f800007, float:1.0000008)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 > 0) goto Lf
            goto Ld
        L1f:
            r4[r5] = r3
            boolean r3 = java.lang.Float.isNaN(r3)
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.AbstractC1861K.J(float, float[], int):int");
    }

    public static final C1873b a(C1877f c1877f) {
        Canvas canvas = AbstractC1874c.f19428a;
        C1873b c1873b = new C1873b();
        c1873b.f19426a = new Canvas(m(c1877f));
        return c1873b;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long b(float r20, float r21, float r22, float r23, o0.AbstractC2007c r24) {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.AbstractC1861K.b(float, float, float, float, o0.c):long");
    }

    public static final long c(int i7) {
        long j3 = ((long) i7) << 32;
        int i10 = C1891t.l;
        return j3;
    }

    public static final long d(long j3) {
        long j10 = j3 << 32;
        int i7 = C1891t.l;
        return j10;
    }

    public static long e(int i7, int i10, int i11) {
        return c(((i7 & 255) << 16) | (-16777216) | ((i10 & 255) << 8) | (i11 & 255));
    }

    public static C1877f f(int i7, int i10, int i11) {
        C2021q c2021q = C2008d.c;
        F(i11);
        return new C1877f(AbstractC1882k.b(i7, i10, i11, true, c2021q));
    }

    public static final LinearGradient g(long j3, long j10, List list, List list2, int i7) {
        float[] fArr;
        I(list, list2);
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        float fD2 = m0.c.d(j10);
        float fE2 = m0.c.e(j10);
        int size = list.size();
        int[] iArr = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = E(((C1891t) list.get(i10)).f19453a);
        }
        if (list2 != null) {
            A7.m.f(list2, "<this>");
            fArr = new float[list2.size()];
            Iterator it = list2.iterator();
            int i11 = 0;
            while (it.hasNext()) {
                fArr[i11] = ((Number) it.next()).floatValue();
                i11++;
            }
        } else {
            fArr = null;
        }
        return new LinearGradient(fD, fE, fD2, fE2, iArr, fArr, D(i7));
    }

    public static final C1878g h() {
        return new C1878g(new Paint(7));
    }

    public static final C1880i i() {
        return new C1880i(new Path());
    }

    public static final long j(float f10, float f11) {
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
        int i7 = C1870U.c;
        return jFloatToRawIntBits;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long k(float r18, float r19, float r20, float r21, o0.AbstractC2007c r22) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.AbstractC1861K.k(float, float, float, float, o0.c):long");
    }

    public static final float l(int i7, int i10, float[] fArr, float[] fArr2) {
        int i11 = i7 * 4;
        return (fArr[i11 + 3] * fArr2[12 + i10]) + (fArr[i11 + 2] * fArr2[8 + i10]) + (fArr[i11 + 1] * fArr2[4 + i10]) + (fArr[i11] * fArr2[i10]);
    }

    public static final Bitmap m(C1877f c1877f) {
        if (c1877f instanceof C1877f) {
            return c1877f.f19431a;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    public static final long n(long j3, long j10) {
        float f10;
        float f11;
        long jA = C1891t.a(j3, C1891t.f(j10));
        float fD = C1891t.d(j10);
        float fD2 = C1891t.d(jA);
        float f12 = 1.0f - fD2;
        float f13 = (fD * f12) + fD2;
        float fH = C1891t.h(jA);
        float fH2 = C1891t.h(j10);
        float f14 = 0.0f;
        if (f13 == 0.0f) {
            f10 = 0.0f;
        } else {
            f10 = (((fH2 * fD) * f12) + (fH * fD2)) / f13;
        }
        float fG = C1891t.g(jA);
        float fG2 = C1891t.g(j10);
        if (f13 == 0.0f) {
            f11 = 0.0f;
        } else {
            f11 = (((fG2 * fD) * f12) + (fG * fD2)) / f13;
        }
        float fE = C1891t.e(jA);
        float fE2 = C1891t.e(j10);
        if (f13 != 0.0f) {
            f14 = (((fE2 * fD) * f12) + (fE * fD2)) / f13;
        }
        return k(f10, f11, f14, f13, C1891t.f(j10));
    }

    public static void o(p0.d dVar, AbstractC1858H abstractC1858H, long j3) {
        C1880i c1880i;
        p0.g gVar = p0.g.f20641a;
        if (abstractC1858H instanceof C1856F) {
            m0.d dVar2 = ((C1856F) abstractC1858H).f19376a;
            dVar.c0(j3, h6.e.a(dVar2.f18882a, dVar2.f18883b), h6.j.f(dVar2.c(), dVar2.b()), 1.0f, gVar, null, 3);
            return;
        }
        if (abstractC1858H instanceof C1857G) {
            C1857G c1857g = (C1857G) abstractC1858H;
            c1880i = c1857g.f19378b;
            if (c1880i == null) {
                m0.e eVar = c1857g.f19377a;
                float fB = AbstractC1792a.b(eVar.f18889h);
                dVar.u0(j3, h6.e.a(eVar.f18884a, eVar.f18885b), h6.j.f(eVar.b(), eVar.a()), n7.z.a(fB, fB), gVar, 1.0f, null, 3);
                return;
            }
        } else {
            if (!(abstractC1858H instanceof C1855E)) {
                throw new A9.k();
            }
            c1880i = ((C1855E) abstractC1858H).f19375a;
        }
        dVar.J(c1880i, j3, 1.0f, gVar, null, 3);
    }

    public static final boolean p(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean q(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean r(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean s(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean t(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean u(int i7, int i10) {
        return i7 == i10;
    }

    public static final long v(long j3, long j10, float f10) {
        C2016l c2016l = C2008d.f20195t;
        long jA = C1891t.a(j3, c2016l);
        long jA2 = C1891t.a(j10, c2016l);
        float fD = C1891t.d(jA);
        float fH = C1891t.h(jA);
        float fG = C1891t.g(jA);
        float fE = C1891t.e(jA);
        float fD2 = C1891t.d(jA2);
        float fH2 = C1891t.h(jA2);
        float fG2 = C1891t.g(jA2);
        float fE2 = C1891t.e(jA2);
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        return C1891t.a(k(AbstractC2673a.O(fH, fH2, f10), AbstractC2673a.O(fG, fG2, f10), AbstractC2673a.O(fE, fE2, f10), AbstractC2673a.O(fD, fD2, f10), c2016l), C1891t.f(j10));
    }

    public static final float w(long j3) {
        AbstractC2007c abstractC2007cF = C1891t.f(j3);
        if (!AbstractC2006b.a(abstractC2007cF.f20179b, AbstractC2006b.f20175a)) {
            throw new IllegalArgumentException("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) AbstractC2006b.b(abstractC2007cF.f20179b)));
        }
        double dH = C1891t.h(j3);
        C2017m c2017m = ((C2021q) abstractC2007cF).f20224p;
        double dC = c2017m.c(dH);
        float fC = (float) ((c2017m.c(C1891t.e(j3)) * 0.0722d) + (c2017m.c(C1891t.g(j3)) * 0.7152d) + (dC * 0.2126d));
        if (fC < 0.0f) {
            fC = 0.0f;
        }
        if (fC > 1.0f) {
            return 1.0f;
        }
        return fC;
    }

    public static final void x(Matrix matrix, float[] fArr) {
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = fArr[6];
        float f17 = fArr[7];
        float f18 = fArr[8];
        float f19 = fArr[12];
        float f20 = fArr[13];
        float f21 = fArr[15];
        fArr[0] = f10;
        fArr[1] = f14;
        fArr[2] = f19;
        fArr[3] = f11;
        fArr[4] = f15;
        fArr[5] = f20;
        fArr[6] = f13;
        fArr[7] = f17;
        fArr[8] = f21;
        matrix.setValues(fArr);
        fArr[0] = f10;
        fArr[1] = f11;
        fArr[2] = f12;
        fArr[3] = f13;
        fArr[4] = f14;
        fArr[5] = f15;
        fArr[6] = f16;
        fArr[7] = f17;
        fArr[8] = f18;
    }

    public static final void y(Matrix matrix, float[] fArr) {
        matrix.getValues(fArr);
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = fArr[6];
        float f17 = fArr[7];
        float f18 = fArr[8];
        fArr[0] = f10;
        fArr[1] = f13;
        fArr[2] = 0.0f;
        fArr[3] = f16;
        fArr[4] = f11;
        fArr[5] = f14;
        fArr[6] = 0.0f;
        fArr[7] = f17;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = f12;
        fArr[13] = f15;
        fArr[14] = 0.0f;
        fArr[15] = f18;
    }

    public static final BlendMode z(int i7) {
        return p(i7, 0) ? BlendMode.CLEAR : p(i7, 1) ? BlendMode.SRC : p(i7, 2) ? BlendMode.DST : p(i7, 3) ? BlendMode.SRC_OVER : p(i7, 4) ? BlendMode.DST_OVER : p(i7, 5) ? BlendMode.SRC_IN : p(i7, 6) ? BlendMode.DST_IN : p(i7, 7) ? BlendMode.SRC_OUT : p(i7, 8) ? BlendMode.DST_OUT : p(i7, 9) ? BlendMode.SRC_ATOP : p(i7, 10) ? BlendMode.DST_ATOP : p(i7, 11) ? BlendMode.XOR : p(i7, 12) ? BlendMode.PLUS : p(i7, 13) ? BlendMode.MODULATE : p(i7, 14) ? BlendMode.SCREEN : p(i7, 15) ? BlendMode.OVERLAY : p(i7, 16) ? BlendMode.DARKEN : p(i7, 17) ? BlendMode.LIGHTEN : p(i7, 18) ? BlendMode.COLOR_DODGE : p(i7, 19) ? BlendMode.COLOR_BURN : p(i7, 20) ? BlendMode.HARD_LIGHT : p(i7, 21) ? BlendMode.SOFT_LIGHT : p(i7, 22) ? BlendMode.DIFFERENCE : p(i7, 23) ? BlendMode.EXCLUSION : p(i7, 24) ? BlendMode.MULTIPLY : p(i7, 25) ? BlendMode.HUE : p(i7, 26) ? BlendMode.SATURATION : p(i7, 27) ? BlendMode.COLOR : p(i7, 28) ? BlendMode.LUMINOSITY : BlendMode.SRC_OVER;
    }
}

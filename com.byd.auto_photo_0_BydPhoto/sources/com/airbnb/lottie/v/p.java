package com.airbnb.lottie.v;

import android.graphics.Color;
import android.graphics.PointF;
import androidx.annotation.ColorInt;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f948a = c.a.a("x", "y");

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f949a;

        static {
            int[] iArr = new int[c.b.values().length];
            f949a = iArr;
            try {
                iArr[c.b.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f949a[c.b.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f949a[c.b.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static PointF a(com.airbnb.lottie.v.k0.c cVar, float f) {
        cVar.k();
        float fR = (float) cVar.r();
        float fR2 = (float) cVar.r();
        while (cVar.w() != c.b.END_ARRAY) {
            cVar.A();
        }
        cVar.m();
        return new PointF(fR * f, fR2 * f);
    }

    private static PointF b(com.airbnb.lottie.v.k0.c cVar, float f) {
        float fR = (float) cVar.r();
        float fR2 = (float) cVar.r();
        while (cVar.p()) {
            cVar.A();
        }
        return new PointF(fR * f, fR2 * f);
    }

    private static PointF c(com.airbnb.lottie.v.k0.c cVar, float f) {
        cVar.l();
        float fG = 0.0f;
        float fG2 = 0.0f;
        while (cVar.p()) {
            int iY = cVar.y(f948a);
            if (iY == 0) {
                fG = g(cVar);
            } else if (iY != 1) {
                cVar.z();
                cVar.A();
            } else {
                fG2 = g(cVar);
            }
        }
        cVar.n();
        return new PointF(fG * f, fG2 * f);
    }

    @ColorInt
    static int d(com.airbnb.lottie.v.k0.c cVar) {
        cVar.k();
        int iR = (int) (cVar.r() * 255.0d);
        int iR2 = (int) (cVar.r() * 255.0d);
        int iR3 = (int) (cVar.r() * 255.0d);
        while (cVar.p()) {
            cVar.A();
        }
        cVar.m();
        return Color.argb(255, iR, iR2, iR3);
    }

    static PointF e(com.airbnb.lottie.v.k0.c cVar, float f) {
        int i = a.f949a[cVar.w().ordinal()];
        if (i == 1) {
            return b(cVar, f);
        }
        if (i == 2) {
            return a(cVar, f);
        }
        if (i == 3) {
            return c(cVar, f);
        }
        throw new IllegalArgumentException("Unknown point starts with " + cVar.w());
    }

    static List<PointF> f(com.airbnb.lottie.v.k0.c cVar, float f) {
        ArrayList arrayList = new ArrayList();
        cVar.k();
        while (cVar.w() == c.b.BEGIN_ARRAY) {
            cVar.k();
            arrayList.add(e(cVar, f));
            cVar.m();
        }
        cVar.m();
        return arrayList;
    }

    static float g(com.airbnb.lottie.v.k0.c cVar) {
        c.b bVarW = cVar.w();
        int i = a.f949a[bVarW.ordinal()];
        if (i == 1) {
            return (float) cVar.r();
        }
        if (i != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + bVarW);
        }
        cVar.k();
        float fR = (float) cVar.r();
        while (cVar.p()) {
            cVar.A();
        }
        cVar.m();
        return fR;
    }
}

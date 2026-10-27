package defpackage;

import android.graphics.RectF;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: CascadingStripStacker.java */
/* JADX INFO: loaded from: classes.dex */
public final class rm extends vd1 {
    @Override // defpackage.vd1
    public final void C(int i, fv2[] fv2VarArr, float f) {
        for (int i2 = 1; i2 < fv2VarArr.length; i2++) {
            int i3 = i2 - 1;
            fv2 fv2Var = fv2VarArr[i3];
            fv2 fv2Var2 = fv2VarArr[i2];
            if (((int) fv2Var.t) == ((int) fv2Var2.t) && ((int) fv2Var.s) == ((int) fv2Var2.s)) {
                if (i2 <= i) {
                    fv2Var.l(false);
                } else if (i2 > i) {
                    fv2Var2.l(false);
                }
            } else if (((int) fv2Var.s) != ((int) fv2Var2.s)) {
                if (i2 <= i) {
                    fv2Var.l(true);
                } else if (i2 > i) {
                    fv2Var2.l(true);
                }
            }
            if (i2 == i) {
                fv2Var2.l(true);
            }
            if (i3 == i) {
                fv2Var.l(true);
            }
        }
    }

    @Override // defpackage.vd1
    public final void F(int i, fv2[] fv2VarArr, float f, float f2, float f3, float f4, boolean z, boolean z2, float f5) {
        fv2 fv2Var = i >= 0 ? fv2VarArr[i] : null;
        float f6 = 4.0f;
        float f7 = ((fv2Var != null ? fv2Var.u : 0.0f) - 4.0f) - f;
        int i2 = 0;
        while (i2 < fv2VarArr.length) {
            fv2 fv2Var2 = fv2VarArr[i2];
            float f8 = fv2Var2.o;
            int iMin = i2 < i ? Math.min(i2, 4) : Math.min(4, i) + Math.min(4, i2 - i);
            int iMin2 = i2 >= i ? Math.min((fv2VarArr.length - 1) - i2, 4) : Math.min(i - i2, 4) + Math.min((fv2VarArr.length - 1) - i, 4);
            if (LocalizationUtils.isLayoutRtl()) {
                int i3 = iMin;
                iMin = iMin2;
                iMin2 = i3;
            }
            float f9 = (iMin * f6) + f2;
            float f10 = (f4 - (iMin2 * f6)) - f3;
            float fO = a8.o(fv2Var2.p + f8, f9, f10 - fv2Var2.u);
            fv2Var2.k(fO);
            float f11 = fv2Var2.q;
            ya3 ya3Var = fv2Var2.f;
            ya3Var.f((f11 - fv2Var2.t) + ya3Var.a.top);
            fv2Var2.t = f11;
            RectF rectF = fv2Var2.w;
            rectF.top = f11;
            rectF.bottom = f11 + fv2Var2.v;
            float fO2 = 1.0f;
            if (i2 != i) {
                float fMax = Math.max(fv2Var2.u, 1.0f);
                boolean z3 = !LocalizationUtils.isLayoutRtl() ? i2 >= i : i2 <= i;
                if (!z3) {
                    f9 += f7;
                }
                if (z3) {
                    f10 -= f7;
                }
                fO2 = a8.o((Math.min(f8 + fMax, f10) - Math.max(f8, f9)) / fMax, 0.0f, 1.0f);
            }
            fv2Var2.m = fO2;
            fv2Var2.e(true);
            if (i2 > (z ? i + 1 : i) && i2 > 0) {
                fv2 fv2Var3 = fv2VarArr[i2 - 1];
                float fI = fv2Var3.i() * (fv2Var3.u - f);
                float f12 = fv2Var3.s;
                if (!LocalizationUtils.isLayoutRtl()) {
                    f12 += fI;
                }
                Math.max(f12 - fO, 0.0f);
                LocalizationUtils.isLayoutRtl();
            }
            i2++;
            f6 = 4.0f;
        }
    }
}

package defpackage;

import android.graphics.RectF;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: ScrollingStripStacker.java */
/* JADX INFO: loaded from: classes.dex */
public final class wf2 extends vd1 {
    @Override // defpackage.vd1
    public final void C(int i, fv2[] fv2VarArr, float f) {
        for (fv2 fv2Var : fv2VarArr) {
            float f2 = fv2Var.s;
            fv2Var.l(fv2Var.u + f2 >= 0.0f && f2 <= f);
        }
    }

    @Override // defpackage.vd1
    public final void F(int i, fv2[] fv2VarArr, float f, float f2, float f3, float f4, boolean z, boolean z2, float f5) {
        boolean zA = bq.e0.a();
        for (fv2 fv2Var : fv2VarArr) {
            if (!zA) {
                fv2Var.k(fv2Var.o);
            } else if (!z2) {
                fv2Var.k(fv2Var.o + fv2Var.p);
                fv2Var.u = f5;
                fv2Var.j();
                fv2Var.w.right = fv2Var.s + fv2Var.u;
            }
            float f6 = fv2Var.q;
            ya3 ya3Var = fv2Var.f;
            ya3Var.f((f6 - fv2Var.t) + ya3Var.a.top);
            fv2Var.t = f6;
            RectF rectF = fv2Var.w;
            rectF.top = f6;
            rectF.bottom = f6 + fv2Var.v;
            fv2Var.m = 1.0f;
            fv2Var.e(true);
        }
    }

    @Override // defpackage.vd1
    public final float c(fv2[] fv2VarArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z) {
        if (bq.e0.a()) {
            return super.c(fv2VarArr, f, f2, f3, f4, f5, f6, f7, z);
        }
        if (LocalizationUtils.isLayoutRtl()) {
            float fMin = f4 - f3;
            int length = fv2VarArr.length;
            if (length >= 1) {
                fMin = Math.min(fv2VarArr[length - 1].s, fMin);
            }
            return ((f / 2.0f) + fMin) - f5;
        }
        int length2 = fv2VarArr.length;
        for (int i = length2 > 0 ? length2 >= 2 ? length2 - 2 : length2 - 1 : 0; i < length2; i++) {
            fv2 fv2Var = fv2VarArr[i];
            f2 = Math.max(fv2Var.s + (fv2Var.h ? fv2Var.i() * fv2Var.u : fv2Var.u), f2);
        }
        return f2 - (f / 2.0f);
    }
}

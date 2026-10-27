package p;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes.dex */
public final class lr1 extends qa1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f150p;
    public final /* synthetic */ Object q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ lr1(Object obj, Context context, int i) {
        super(context);
        this.f150p = i;
        this.q = obj;
    }

    @Override // p.qa1
    public final float b(DisplayMetrics displayMetrics) {
        switch (this.f150p) {
        }
        return 100.0f / displayMetrics.densityDpi;
    }

    @Override // p.qa1
    public final int c(int i) {
        switch (this.f150p) {
            case 0:
                return Math.min(100, super.c(i));
            default:
                return super.c(i);
        }
    }

    @Override // p.qa1
    public final void e(View view, k22 k22Var, i22 i22Var) {
        switch (this.f150p) {
            case 0:
                mr1 mr1Var = (mr1) this.q;
                int[] iArrA = mr1Var.a(mr1Var.a.getLayoutManager(), view);
                int i = iArrA[0];
                int i2 = iArrA[1];
                int iCeil = (int) Math.ceil(((double) c(Math.max(Math.abs(i), Math.abs(i2)))) / 0.3356d);
                if (iCeil > 0) {
                    DecelerateInterpolator decelerateInterpolator = this.i;
                    i22Var.a = i;
                    i22Var.b = i2;
                    i22Var.c = iCeil;
                    i22Var.e = decelerateInterpolator;
                    i22Var.f = true;
                }
                break;
            default:
                super.e(view, k22Var, i22Var);
                break;
        }
    }
}

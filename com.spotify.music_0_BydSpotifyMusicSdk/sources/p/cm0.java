package p;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class cm0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ cm0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                gm0 gm0Var = (gm0) obj;
                int i2 = gm0Var.A;
                ValueAnimator valueAnimator = gm0Var.z;
                if (i2 == 1) {
                    valueAnimator.cancel();
                } else if (i2 != 2) {
                }
                gm0Var.A = 3;
                valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
                valueAnimator.setDuration(500);
                valueAnimator.start();
                break;
            default:
                ((StaggeredGridLayoutManager) obj).w0();
                break;
        }
    }
}

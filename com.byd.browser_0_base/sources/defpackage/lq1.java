package defpackage;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class lq1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ mq1 f;

    public /* synthetic */ lq1(mq1 mq1Var, int i) {
        this.e = i;
        this.f = mq1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = 1;
        mq1 mq1Var = this.f;
        switch (i) {
            case 0:
                mq1Var.c.postDelayed(new lq1(mq1Var, i2), 2000L);
                break;
            default:
                ft2 ft2Var = mq1Var.b;
                if (ft2Var.c) {
                    ft2Var.c = false;
                    qt2 qt2Var = ft2Var.a;
                    ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(qt2Var.e.h(rt2.e), qt2Var.i.get().intValue());
                    valueAnimatorOfInt.setEvaluator(new ArgbEvaluator());
                    jk0 jk0Var = h21.e;
                    valueAnimatorOfInt.setInterpolator(jk0Var);
                    valueAnimatorOfInt.setDuration(200L);
                    valueAnimatorOfInt.addUpdateListener(new it2(qt2Var, 1));
                    valueAnimatorOfInt.addListener(new ot2(qt2Var));
                    ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                    valueAnimatorOfFloat.setInterpolator(jk0Var);
                    valueAnimatorOfFloat.setDuration(150L);
                    valueAnimatorOfFloat.addUpdateListener(new jt2(qt2Var, 1));
                    AnimatorSet animatorSet = new AnimatorSet();
                    qt2Var.s = animatorSet;
                    animatorSet.play(valueAnimatorOfInt).with(valueAnimatorOfFloat);
                    qt2Var.s.addListener(new pt2(qt2Var));
                    qt2Var.s.start();
                    break;
                }
                break;
        }
    }
}

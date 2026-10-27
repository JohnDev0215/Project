package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppMenuUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q7 extends AnimatorListenerAdapter {
    public final /* synthetic */ int e;
    public final /* synthetic */ ImageView[] f;

    public q7(int i, ImageView[] imageViewArr) {
        this.e = i;
        this.f = imageViewArr;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        for (int i = 0; i < this.e; i++) {
            this.f[i].setAlpha(0.0f);
        }
    }
}

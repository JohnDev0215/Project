package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Animator.AnimatorListener {
    public final /* synthetic */ CircularProgressDrawable.a e;
    public final /* synthetic */ CircularProgressDrawable f;

    public b(CircularProgressDrawable circularProgressDrawable, CircularProgressDrawable.a aVar) {
        this.f = circularProgressDrawable;
        this.e = aVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        CircularProgressDrawable circularProgressDrawable = this.f;
        CircularProgressDrawable.a aVar = this.e;
        circularProgressDrawable.b(1.0f, aVar, true);
        aVar.k = aVar.e;
        aVar.l = aVar.f;
        aVar.m = aVar.g;
        aVar.a((aVar.j + 1) % aVar.i.length);
        if (!circularProgressDrawable.j) {
            circularProgressDrawable.i += 1.0f;
            return;
        }
        circularProgressDrawable.j = false;
        animator.cancel();
        animator.setDuration(1332L);
        animator.start();
        if (aVar.n) {
            aVar.n = false;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f.i = 0.0f;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }
}

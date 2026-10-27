package org.libpag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import org.libpag.PAGImageView;

/* JADX INFO: loaded from: classes3.dex */
public class g extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4060a;

    public g(PAGImageView pAGImageView) {
        this.f4060a = pAGImageView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        ArrayList arrayList;
        super.onAnimationCancel(animator);
        synchronized (this.f4060a) {
            arrayList = new ArrayList(this.f4060a.B);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGImageView.PAGImageViewListener) it.next()).onAnimationCancel(this.f4060a);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        int repeatCount = ((ValueAnimator) animator).getRepeatCount();
        if (repeatCount < 0 || animator.getDuration() <= 0 || this.f4060a.z / animator.getDuration() <= repeatCount) {
            return;
        }
        this.f4060a.k();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        ArrayList arrayList;
        super.onAnimationRepeat(animator);
        synchronized (this.f4060a) {
            arrayList = new ArrayList(this.f4060a.B);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGImageView.PAGImageViewListener) it.next()).onAnimationRepeat(this.f4060a);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        ArrayList arrayList;
        super.onAnimationStart(animator);
        synchronized (this.f4060a) {
            arrayList = new ArrayList(this.f4060a.B);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGImageView.PAGImageViewListener) it.next()).onAnimationStart(this.f4060a);
        }
    }
}

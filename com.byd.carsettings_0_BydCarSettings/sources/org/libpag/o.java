package org.libpag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import org.libpag.PAGView;

/* JADX INFO: loaded from: classes3.dex */
public class o extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4083a;

    public o(PAGView pAGView) {
        this.f4083a = pAGView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        ArrayList arrayList;
        super.onAnimationCancel(animator);
        synchronized (this.f4083a) {
            arrayList = new ArrayList(this.f4083a.u);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGView.PAGViewListener) it.next()).onAnimationCancel(this.f4083a);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        int repeatCount = ((ValueAnimator) animator).getRepeatCount();
        if (repeatCount < 0 || animator.getDuration() <= 0 || this.f4083a.w / animator.getDuration() <= repeatCount) {
            return;
        }
        this.f4083a.h();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        ArrayList arrayList;
        super.onAnimationRepeat(animator);
        synchronized (this.f4083a) {
            arrayList = new ArrayList(this.f4083a.u);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGView.PAGViewListener) it.next()).onAnimationRepeat(this.f4083a);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        ArrayList arrayList;
        super.onAnimationStart(animator);
        synchronized (this.f4083a) {
            arrayList = new ArrayList(this.f4083a.u);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGView.PAGViewListener) it.next()).onAnimationStart(this.f4083a);
        }
    }
}

package com.amazon.music.voice.ui.transitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.voice.ui.ControllerTransition;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AnimatorTransition implements ControllerTransition {
    private Animator animator;
    private boolean completionRequested;
    private final boolean removeFromView;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    protected abstract Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2);

    public AnimatorTransition(boolean z) {
        this.removeFromView = z;
    }

    public AnimatorTransition() {
        this(false);
    }

    protected AnimatorTransition(Parcel parcel) {
        this.removeFromView = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    @CallSuper
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.removeFromView ? 1 : 0);
    }

    @Override // com.amazon.music.voice.ui.ControllerTransition
    public final void completeTransition() {
        this.completionRequested = true;
        Animator animator = this.animator;
        if (animator != null) {
            animator.end();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completeAnimation(ControllerTransition.CompletionListener completionListener, Animator.AnimatorListener animatorListener) {
        completionListener.completeTransition();
        Animator animator = this.animator;
        if (animator != null) {
            animator.removeListener(animatorListener);
            this.animator.cancel();
            this.animator = null;
        }
    }

    @Override // com.amazon.music.voice.ui.ControllerTransition
    public final void performTransition(@NonNull final ViewGroup viewGroup, @Nullable final View view, @Nullable final View view2, @NonNull final ControllerTransition.CompletionListener completionListener) {
        final View view3 = view2 != null ? view2 : view;
        this.completionRequested = false;
        if (view3 == null) {
            completionListener.completeTransition();
            return;
        }
        if (view2 != null && view2.getParent() == null) {
            int iIndexOfChild = viewGroup.indexOfChild(view);
            if (iIndexOfChild >= 0) {
                viewGroup.addView(view2, iIndexOfChild + 1);
            } else {
                viewGroup.addView(view2);
            }
        }
        if (view3.getHeight() <= 0) {
            view3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.music.voice.ui.transitions.AnimatorTransition.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    ViewTreeObserver viewTreeObserver = view3.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this);
                    }
                    AnimatorTransition.this.performAnimation(viewGroup, view, view2, completionListener);
                    return true;
                }
            });
        } else {
            performAnimation(viewGroup, view, view2, completionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performAnimation(final ViewGroup viewGroup, final View view, View view2, final ControllerTransition.CompletionListener completionListener) {
        Animator animator = getAnimator(viewGroup, view, view2);
        this.animator = animator;
        if (animator == null) {
            completionListener.completeTransition();
            return;
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.music.voice.ui.transitions.AnimatorTransition.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                View view3;
                if (AnimatorTransition.this.removeFromView && (view3 = view) != null) {
                    viewGroup.removeView(view3);
                }
                AnimatorTransition.this.completeAnimation(completionListener, this);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                View view3;
                if (AnimatorTransition.this.removeFromView && (view3 = view) != null) {
                    viewGroup.removeView(view3);
                }
                AnimatorTransition.this.completeAnimation(completionListener, this);
            }
        });
        this.animator.start();
        if (this.completionRequested) {
            this.animator.end();
        }
    }
}

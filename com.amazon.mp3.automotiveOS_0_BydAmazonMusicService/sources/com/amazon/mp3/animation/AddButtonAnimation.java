package com.amazon.mp3.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class AddButtonAnimation {

    public interface AnimationFinishedListener {
        void onAnimationCanceled();

        void onAnimationFinished();
    }

    public static Animator startAnimation(final Context context, final View parentView, final View addButton, final View addSuccessfulView, final AnimationFinishedListener animationFinishedListener) {
        return startAnimation(context, parentView, addButton, addSuccessfulView, animationFinishedListener, true);
    }

    public static Animator startAnimation(final Context context, final View parentView, final View addButton, final View addSuccessfulView, final AnimationFinishedListener animationFinishedListener, final boolean hideOnComplete) {
        return startAnimation(context, parentView, addButton, addSuccessfulView, animationFinishedListener, hideOnComplete, false, R.animator.add_button_animation, R.animator.add_successful_animation);
    }

    public static Animator startAnimation(final Context context, final View parentView, final View addButton, final View addSuccessfulView, final AnimationFinishedListener animationFinishedListener, final boolean hideOnComplete, final boolean keepAddButtonVisible, final int addButtonId, final int addButtonSuccessfulId) {
        addButton.setEnabled(false);
        addSuccessfulView.setVisibility(0);
        addButton.setLayerType(2, null);
        addSuccessfulView.setLayerType(2, null);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, addButtonId);
        animatorSet.setTarget(addButton);
        AnimatorSet animatorSet2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, addButtonSuccessfulId);
        animatorSet2.setTarget(addSuccessfulView);
        final AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.play(animatorSet);
        animatorSet3.play(animatorSet2).after(150L);
        animatorSet3.start();
        animatorSet3.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.mp3.animation.AddButtonAnimation.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(final Animator animator) {
                if (animator == animatorSet3) {
                    addSuccessfulView.setVisibility(8);
                    addButton.setAlpha(1.0f);
                    addButton.setScaleX(1.0f);
                    addButton.setScaleY(1.0f);
                    addButton.setEnabled(true);
                    if (hideOnComplete) {
                        addButton.setVisibility(8);
                        parentView.setVisibility(8);
                    } else if (!keepAddButtonVisible) {
                        addButton.setVisibility(4);
                        parentView.setVisibility(4);
                    }
                    AnimationFinishedListener animationFinishedListener2 = animationFinishedListener;
                    if (animationFinishedListener2 != null) {
                        animationFinishedListener2.onAnimationFinished();
                    }
                    addButton.setLayerType(0, null);
                    addSuccessfulView.setLayerType(0, null);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(final Animator animator) {
                addSuccessfulView.setAlpha(0.0f);
                addSuccessfulView.setVisibility(8);
                addButton.setVisibility(8);
                parentView.setVisibility(8);
                AnimationFinishedListener animationFinishedListener2 = animationFinishedListener;
                if (animationFinishedListener2 != null) {
                    animationFinishedListener2.onAnimationCanceled();
                }
                addButton.setLayerType(0, null);
                addSuccessfulView.setLayerType(0, null);
            }
        });
        return animatorSet3;
    }
}

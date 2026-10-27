package com.byd.music.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.music.R;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class CollectLottieView extends LottieAnimationView {
    private MYAnimatorListener mMyAnimatorListener;

    public interface MYAnimatorListener {
        void onAnimationStarted();
    }

    public CollectLottieView(Context context) {
        super(context);
        init();
    }

    public CollectLottieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CollectLottieView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setAnimation(R.raw.music_collect_animation);
        setVisibility(8);
        addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.byd.music.view.CollectLottieView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                CollectLottieView.this.onDetachedFromWindow();
                LogUtil.e("addAnimatorListener-->onAnimationEnd");
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                LogUtil.e("addAnimatorListener-->onAnimationCancel");
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                super.onAnimationRepeat(animator);
                LogUtil.e("addAnimatorListener-->onAnimationRepeat");
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (CollectLottieView.this.mMyAnimatorListener != null) {
                    CollectLottieView.this.mMyAnimatorListener.onAnimationStarted();
                }
                LogUtil.e("addAnimatorListener-->onAnimationStart");
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
            public void onAnimationPause(Animator animator) {
                super.onAnimationPause(animator);
                LogUtil.e("addAnimatorListener-->onAnimationPause");
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
            public void onAnimationResume(Animator animator) {
                super.onAnimationResume(animator);
                LogUtil.e("addAnimatorListener-->onAnimationResume");
            }
        });
    }

    @Override // com.airbnb.lottie.LottieAnimationView, android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setVisibility(8);
        clearAnimation();
    }

    public void disableAnimation() {
        setVisibility(8);
        clearAnimation();
    }

    public void startAnimation(final boolean z) {
        post(new Runnable() { // from class: com.byd.music.view.CollectLottieView.2
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    CollectLottieView.this.setVisibility(0);
                    if (CollectLottieView.this.isShown()) {
                        CollectLottieView.this.playAnimation();
                    } else {
                        CollectLottieView.this.postDelayed(new Runnable() { // from class: com.byd.music.view.CollectLottieView.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CollectLottieView.this.startAnimation(true);
                            }
                        }, 10L);
                    }
                    CollectLottieView.this.setScale(1.0f);
                    return;
                }
                CollectLottieView.this.onDetachedFromWindow();
            }
        });
    }

    public void startAnimation(final boolean z, final MYAnimatorListener mYAnimatorListener) {
        post(new Runnable() { // from class: com.byd.music.view.CollectLottieView.3
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    CollectLottieView.this.setVisibility(0);
                    if (CollectLottieView.this.isShown()) {
                        CollectLottieView.this.mMyAnimatorListener = mYAnimatorListener;
                        CollectLottieView.this.playAnimation();
                    } else {
                        CollectLottieView.this.postDelayed(new Runnable() { // from class: com.byd.music.view.CollectLottieView.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CollectLottieView.this.startAnimation(true, mYAnimatorListener);
                            }
                        }, 10L);
                    }
                    CollectLottieView.this.setScale(1.0f);
                }
            }
        });
    }
}

package com.amazon.music.explore.views.playback;

import android.animation.Animator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import com.amazon.music.skyfire.ui.managers.ObjectAnimatorManager;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;

/* JADX INFO: loaded from: classes4.dex */
public class IntroAnimations {
    private static final long CAPTION_ANIMATIONS_DURATION_MS = 1000;
    private static final long IMAGE_ZOOM_DURATION_MS = 20000;
    private static final float IMAGE_ZOOM_SCALE = 1.1f;
    private static final float PANNING_PERCENTAGE = 0.1f;
    private static final long PLAYBACK_VIEW_FADE_IN_DURATION_MS = 500;
    private static ObjectAnimatorManager captionAlphaAnimator;
    private static ObjectAnimatorManager captionTranslateAnimator;
    private static ObjectAnimatorManager imageScaleXAnimator;
    private static ObjectAnimatorManager imageScaleYAnimator;
    private static ObjectAnimatorManager panningAnimator;
    private static CountDownTimerManager panningTimer;
    private static ObjectAnimatorManager viewContainerAlphaAnimator;

    public static void start(FrameLayout frameLayout, TextView textView, ImageView imageView) {
        frameLayout.setAlpha(0.0f);
        textView.setAlpha(0.0f);
        startAlphaAnimation(frameLayout, textView, imageView);
    }

    public static void removeAnimators() {
        viewContainerAlphaAnimator.stop();
        captionTranslateAnimator.stop();
        captionAlphaAnimator.stop();
        imageScaleXAnimator.stop();
        imageScaleYAnimator.stop();
        panningAnimator.stop();
        panningTimer.stop();
    }

    public static void resizeBackgroundImage(ImageView imageView) {
        int screenWidth = ScreenUtils.getScreenWidth(imageView.getContext());
        int navbarSize = screenWidth + ((int) (screenWidth * PANNING_PERCENTAGE));
        if (ScreenUtils.isLandscape(imageView.getContext())) {
            navbarSize += ScreenUtils.getNavbarSize(imageView.getContext());
        }
        imageView.setLayoutParams(new FrameLayout.LayoutParams(navbarSize, -1));
    }

    public static void onRotate(ImageView imageView) {
        resizeBackgroundImage(imageView);
        panningTimer.stop();
        panningAnimator.stop();
        int width = imageView.getWidth();
        int i = imageView.getLayoutParams().width;
        float fAbs = Math.abs(imageView.getX() / width);
        float f = -i;
        float f2 = PANNING_PERCENTAGE * f;
        imageView.setX(f * fAbs);
        panningAnimator.setFloatValues(f2);
        panningAnimator.setDuration(panningTimer.getMsLeftUntilFinished());
        if (panningTimer.getMsLeftUntilFinished() != 0) {
            startPanningAnimator();
        }
    }

    private static void startAlphaAnimation(FrameLayout frameLayout, final TextView textView, final ImageView imageView) {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(frameLayout, "alpha");
        viewContainerAlphaAnimator = objectAnimatorManager;
        objectAnimatorManager.setFloatValues(0.0f, 1.0f);
        viewContainerAlphaAnimator.setDuration(PLAYBACK_VIEW_FADE_IN_DURATION_MS);
        viewContainerAlphaAnimator.setListener(new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.playback.IntroAnimations.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                IntroAnimations.createCaptionAnimators(textView);
                IntroAnimations.createZoomAnimators(imageView);
                IntroAnimations.createPanningAnimator(imageView);
                IntroAnimations.startCaptionAnimators();
                IntroAnimations.startZoomAnimators();
                IntroAnimations.startPanningAnimator();
            }
        });
        viewContainerAlphaAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createCaptionAnimators(TextView textView) {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(textView, "y");
        captionTranslateAnimator = objectAnimatorManager;
        objectAnimatorManager.setFloatValues(textView.getY() + textView.getResources().getDimensionPixelSize(R.dimen.playback_caption_y_offset), textView.getY());
        captionTranslateAnimator.setDuration(CAPTION_ANIMATIONS_DURATION_MS);
        ObjectAnimatorManager objectAnimatorManager2 = new ObjectAnimatorManager(textView, "alpha");
        captionAlphaAnimator = objectAnimatorManager2;
        objectAnimatorManager2.setFloatValues(0.0f, 1.0f);
        captionAlphaAnimator.setDuration(CAPTION_ANIMATIONS_DURATION_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createZoomAnimators(ImageView imageView) {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(imageView, "scaleX");
        imageScaleXAnimator = objectAnimatorManager;
        objectAnimatorManager.setFloatValues(IMAGE_ZOOM_SCALE);
        imageScaleXAnimator.setDuration(IMAGE_ZOOM_DURATION_MS);
        ObjectAnimatorManager objectAnimatorManager2 = new ObjectAnimatorManager(imageView, "scaleY");
        imageScaleYAnimator = objectAnimatorManager2;
        objectAnimatorManager2.setFloatValues(IMAGE_ZOOM_SCALE);
        imageScaleYAnimator.setDuration(IMAGE_ZOOM_DURATION_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createPanningAnimator(ImageView imageView) {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(imageView, "x");
        panningAnimator = objectAnimatorManager;
        objectAnimatorManager.setFloatValues((-imageView.getWidth()) * PANNING_PERCENTAGE);
        ObjectAnimatorManager objectAnimatorManager2 = panningAnimator;
        long j = IMAGE_ZOOM_DURATION_MS;
        objectAnimatorManager2.setDuration(IMAGE_ZOOM_DURATION_MS);
        panningTimer = new CountDownTimerManager(j, CAPTION_ANIMATIONS_DURATION_MS) { // from class: com.amazon.music.explore.views.playback.IntroAnimations.2
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long j2) {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startCaptionAnimators() {
        captionTranslateAnimator.start();
        captionAlphaAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startZoomAnimators() {
        imageScaleXAnimator.start();
        imageScaleYAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startPanningAnimator() {
        panningAnimator.start();
        panningTimer.start();
    }
}

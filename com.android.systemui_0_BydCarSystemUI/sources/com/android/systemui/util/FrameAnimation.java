package com.android.systemui.util;

import android.util.Log;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class FrameAnimation {
    private static final int SELECTED_A = 1;
    private static final int SELECTED_B = 2;
    private static final int SELECTED_C = 3;
    private static final int SELECTED_D = 4;
    private static final String TAG = "FrameAnimation";
    private AnimationListener mAnimationListener;
    private int mCurrentFrame;
    private int mCurrentSelect;
    private int mDelay;
    private int mDuration;
    private int[] mDurations;
    private volatile int[] mFrameRess;
    private ImageView mImageView;
    private boolean mIsPlaying;
    private boolean mIsRelease = true;
    private boolean mIsRepeat;
    private int mLastFrame;
    private boolean mNext;
    private boolean mPause;

    public interface AnimationListener {
        void onAnimationEnd(int viewID);

        void onAnimationEnding(int viewID);

        void onAnimationRelease(int viewID);

        void onAnimationRepeat(int viewID);

        void onAnimationStart(int viewID);
    }

    public FrameAnimation(ImageView iv, int[] frameRes, int duration, boolean isRepeat) {
        this.mImageView = iv;
        this.mFrameRess = frameRes;
        this.mDuration = duration;
        this.mLastFrame = frameRes.length - 1;
        this.mIsRepeat = isRepeat;
    }

    public FrameAnimation(ImageView iv, int[] frameRess, int[] durations, boolean isRepeat) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDurations = durations;
        this.mLastFrame = frameRess.length - 1;
        this.mIsRepeat = isRepeat;
    }

    public FrameAnimation(ImageView iv, int[] frameRess, int duration, int delay) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDuration = duration;
        this.mDelay = delay;
        this.mLastFrame = frameRess.length - 1;
    }

    public FrameAnimation(ImageView iv, int[] frameRess, int[] durations, int delay) {
        this.mImageView = iv;
        this.mFrameRess = frameRess;
        this.mDurations = durations;
        this.mDelay = delay;
        this.mLastFrame = frameRess.length - 1;
    }

    public void setAnimationRes(int[] frameRess) {
        pauseAnimation();
        release();
        this.mFrameRess = frameRess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playByDurationsAndDelay(final int i) {
        int i2;
        this.mPause = false;
        this.mIsRelease = false;
        this.mImageView.postDelayed(new Runnable() { // from class: com.android.systemui.util.FrameAnimation.1
            @Override // java.lang.Runnable
            public void run() {
                if (FrameAnimation.this.mPause || !FrameAnimation.this.mImageView.isShown()) {
                    FrameAnimation.this.mCurrentSelect = 1;
                    FrameAnimation.this.mCurrentFrame = i;
                    FrameAnimation.this.mIsPlaying = false;
                    return;
                }
                FrameAnimation.this.mIsPlaying = true;
                if (i == 0 && FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationStart(FrameAnimation.this.mImageView.getId());
                }
                FrameAnimation.this.mImageView.setImageResource(FrameAnimation.this.mFrameRess[i]);
                if (i == FrameAnimation.this.mLastFrame) {
                    if (FrameAnimation.this.mAnimationListener != null) {
                        FrameAnimation.this.mAnimationListener.onAnimationRepeat(FrameAnimation.this.mImageView.getId());
                    }
                    FrameAnimation.this.mNext = true;
                    FrameAnimation.this.playByDurationsAndDelay(0);
                    return;
                }
                FrameAnimation.this.playByDurationsAndDelay(i + 1);
            }
        }, (!this.mNext || (i2 = this.mDelay) <= 0) ? this.mDurations[i] : i2);
    }

    public void playAndDelay(final int i) {
        int i2;
        this.mPause = false;
        this.mIsRelease = false;
        ImageView imageView = this.mImageView;
        Runnable runnable = new Runnable() { // from class: com.android.systemui.util.FrameAnimation.2
            @Override // java.lang.Runnable
            public void run() {
                if (FrameAnimation.this.mPause || !FrameAnimation.this.mImageView.isShown()) {
                    if (FrameAnimation.this.mPause || !FrameAnimation.this.mImageView.isShown()) {
                        FrameAnimation.this.mIsPlaying = false;
                        FrameAnimation.this.mCurrentSelect = 2;
                        FrameAnimation.this.mCurrentFrame = i;
                        return;
                    }
                    return;
                }
                FrameAnimation.this.mIsPlaying = true;
                FrameAnimation.this.mNext = false;
                if (i == 0 && FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationStart(FrameAnimation.this.mImageView.getId());
                }
                FrameAnimation.this.mImageView.setImageResource(FrameAnimation.this.mFrameRess[i]);
                if (i == FrameAnimation.this.mLastFrame) {
                    if (FrameAnimation.this.mAnimationListener != null) {
                        FrameAnimation.this.mAnimationListener.onAnimationRepeat(FrameAnimation.this.mImageView.getId());
                    }
                    FrameAnimation.this.mNext = true;
                    FrameAnimation.this.playAndDelay(0);
                    return;
                }
                FrameAnimation.this.playAndDelay(i + 1);
            }
        };
        if (!this.mNext || (i2 = this.mDelay) <= 0) {
            i2 = this.mDuration;
        }
        imageView.postDelayed(runnable, i2);
    }

    public void playByDurations(final int i) {
        this.mPause = false;
        this.mIsRelease = false;
        this.mImageView.postDelayed(new Runnable() { // from class: com.android.systemui.util.FrameAnimation.3
            @Override // java.lang.Runnable
            public void run() {
                if (FrameAnimation.this.mPause || !FrameAnimation.this.mImageView.isShown()) {
                    if (FrameAnimation.this.mPause) {
                        FrameAnimation.this.mIsPlaying = false;
                        FrameAnimation.this.mCurrentSelect = 3;
                        FrameAnimation.this.mCurrentFrame = i;
                        return;
                    }
                    return;
                }
                FrameAnimation.this.mIsPlaying = true;
                if (i == 0 && FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationStart(FrameAnimation.this.mImageView.getId());
                }
                FrameAnimation.this.mImageView.setImageResource(FrameAnimation.this.mFrameRess[i]);
                if (i == FrameAnimation.this.mLastFrame) {
                    if (FrameAnimation.this.mIsRepeat) {
                        if (FrameAnimation.this.mAnimationListener != null) {
                            FrameAnimation.this.mAnimationListener.onAnimationRepeat(FrameAnimation.this.mImageView.getId());
                        }
                        FrameAnimation.this.playByDurations(0);
                        return;
                    } else {
                        if (FrameAnimation.this.mAnimationListener != null) {
                            FrameAnimation.this.mAnimationListener.onAnimationEnd(FrameAnimation.this.mImageView.getId());
                            return;
                        }
                        return;
                    }
                }
                FrameAnimation.this.playByDurations(i + 1);
            }
        }, this.mDurations[i]);
    }

    public void play(final int i) {
        this.mPause = false;
        this.mIsRelease = false;
        this.mImageView.postDelayed(new Runnable() { // from class: com.android.systemui.util.FrameAnimation.4
            @Override // java.lang.Runnable
            public void run() {
                if (FrameAnimation.this.mPause || !FrameAnimation.this.mImageView.isShown()) {
                    FrameAnimation.this.mIsPlaying = false;
                    FrameAnimation.this.mCurrentFrame = i;
                    FrameAnimation.this.mCurrentSelect = 4;
                    return;
                }
                FrameAnimation.this.mIsPlaying = true;
                int i2 = i;
                if (i2 == 0) {
                    if (FrameAnimation.this.mAnimationListener != null) {
                        FrameAnimation.this.mAnimationListener.onAnimationStart(FrameAnimation.this.mImageView.getId());
                    }
                } else if (i2 == FrameAnimation.this.mLastFrame && FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationEnding(FrameAnimation.this.mImageView.getId());
                }
                FrameAnimation.this.mImageView.setImageResource(FrameAnimation.this.mFrameRess[i]);
                if (i == FrameAnimation.this.mLastFrame) {
                    if (FrameAnimation.this.mIsRepeat) {
                        if (FrameAnimation.this.mAnimationListener != null) {
                            FrameAnimation.this.mAnimationListener.onAnimationRepeat(FrameAnimation.this.mImageView.getId());
                        }
                        FrameAnimation.this.play(0);
                        return;
                    } else {
                        if (FrameAnimation.this.mAnimationListener != null) {
                            FrameAnimation.this.mAnimationListener.onAnimationEnd(FrameAnimation.this.mImageView.getId());
                            return;
                        }
                        return;
                    }
                }
                FrameAnimation.this.play(i + 1);
            }
        }, this.mDuration);
    }

    public void setAnimationListener(AnimationListener listener) {
        this.mAnimationListener = listener;
    }

    public void release() {
        this.mIsRelease = true;
        this.mPause = true;
        this.mIsPlaying = false;
        this.mCurrentFrame = 0;
        this.mImageView.setImageResource(0);
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onAnimationRelease(this.mImageView.getId());
        }
    }

    public void pauseAnimation() {
        this.mPause = true;
        this.mIsPlaying = false;
        Log.d(TAG, "pauseAnimation");
    }

    private boolean isPause() {
        return this.mPause;
    }

    public boolean isPlaying() {
        return this.mIsPlaying;
    }

    public boolean isRelease() {
        return this.mIsRelease;
    }

    public void restartAnimation() {
        if (this.mIsPlaying) {
            Log.w(TAG, "restartAnimation: 当前动画正在播放,return !!");
            return;
        }
        if (this.mIsRelease) {
            Log.w(TAG, "restartAnimation: 当前动画已停止,如需请调play,return !!");
            return;
        }
        if (this.mPause) {
            this.mPause = false;
            int i = this.mCurrentSelect;
            if (i == 1) {
                playByDurationsAndDelay(this.mCurrentFrame);
                return;
            }
            if (i == 2) {
                playAndDelay(this.mCurrentFrame);
            } else if (i == 3) {
                playByDurations(this.mCurrentFrame);
            } else {
                if (i != 4) {
                    return;
                }
                play(this.mCurrentFrame);
            }
        }
    }
}

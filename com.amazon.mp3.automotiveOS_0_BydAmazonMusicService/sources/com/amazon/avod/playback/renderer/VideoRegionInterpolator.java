package com.amazon.avod.playback.renderer;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VideoRegionInterpolator implements ValueAnimator.AnimatorUpdateListener {
    private final int mAnimationTimeMilliseconds;
    private ValueAnimator mAnimator;
    private VideoRegion mCurrentRegion;
    private VideoRegionChangeListener mListener;
    private final Object mSyncObject;
    private VideoRegion mTargetRegion;
    private final Handler mUiHandler;

    public interface VideoRegionChangeListener {
        void onVideoRegionChanged(@Nonnull VideoRegion videoRegion);
    }

    public VideoRegionInterpolator() {
        this(PlaybackConfig.INSTANCE, Handlers.getUIHandler());
    }

    VideoRegionInterpolator(PlaybackConfig playbackConfig, Handler handler) {
        this.mSyncObject = new Object();
        this.mAnimationTimeMilliseconds = (int) playbackConfig.getVideoRegionInterpolationTime().getTotalMilliseconds();
        this.mUiHandler = handler;
    }

    public void setListener(VideoRegionChangeListener videoRegionChangeListener) {
        this.mListener = videoRegionChangeListener;
    }

    public void setInitialRegion(VideoRegion videoRegion) {
        synchronized (this.mSyncObject) {
            this.mCurrentRegion = videoRegion;
            this.mTargetRegion = videoRegion;
        }
    }

    public void setTarget(final VideoRegion videoRegion, boolean z) {
        final long j = z ? this.mAnimationTimeMilliseconds : 0L;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            lambda$setTarget$0(videoRegion, j);
        } else {
            this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.playback.renderer.VideoRegionInterpolator$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setTarget$0(videoRegion, j);
                }
            });
        }
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        VideoRegion videoRegionInterpolate;
        synchronized (this.mSyncObject) {
            videoRegionInterpolate = this.mCurrentRegion.interpolate(this.mTargetRegion, valueAnimator.getAnimatedFraction());
        }
        VideoRegionChangeListener videoRegionChangeListener = this.mListener;
        if (videoRegionChangeListener != null) {
            DLog.logf("AnimationVideoRegion: %s percent to %s", Float.valueOf(valueAnimator.getAnimatedFraction()), videoRegionInterpolate);
            videoRegionChangeListener.onVideoRegionChanged(videoRegionInterpolate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: performSetTarget, reason: merged with bridge method [inline-methods] */
    public void lambda$setTarget$0(VideoRegion videoRegion, long j) {
        float animatedFraction;
        synchronized (this.mSyncObject) {
            this.mCurrentRegion = this.mTargetRegion;
            this.mTargetRegion = videoRegion;
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                animatedFraction = 1.0f - valueAnimator.getAnimatedFraction();
                this.mAnimator.cancel();
                this.mAnimator.removeAllListeners();
            } else {
                animatedFraction = 0.0f;
            }
            int i = (int) (j * (1.0f - animatedFraction));
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(animatedFraction, 1.0f);
            this.mAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(i);
            this.mAnimator.addUpdateListener(this);
            this.mAnimator.start();
        }
    }
}

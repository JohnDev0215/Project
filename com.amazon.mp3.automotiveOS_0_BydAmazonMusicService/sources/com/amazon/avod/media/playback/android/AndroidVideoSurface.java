package com.amazon.avod.media.playback.android;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.upscaler.UpscalerUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoSurface {
    private final Context mContext;
    final Runnable mCreateSurfaceRunnable;
    private CreationListener mCreationListener;
    private boolean mCustomLayoutParamsApplied;
    private boolean mDestroyCalled;
    final Runnable mDestroyRunnable;
    private boolean mIsMediaOverlay;
    private final boolean mIsPlaybackSurfaceParentLayoutBlack;
    private final boolean mIsSecurePlaybackSurfaceEnabled;
    private boolean mIsSuperResolutionUpscalerEnabled;
    private final Object mMutex;
    final SurfaceHolder.Callback mSurfaceCallback;
    private SurfaceView mSurfaceView;
    private final int mSurfaceViewSystemUIFlags;
    private int mSystemUIFlagsOverride;
    private final Handler mUIHandler;
    private ViewGroup mViewGroup;

    public interface CreationListener {
        void onCreated(@Nonnull SurfaceHolder surfaceHolder);

        void onDestroyed(@Nonnull SurfaceHolder surfaceHolder);
    }

    public AndroidVideoSurface(@Nonnull Context context) {
        this(Handlers.getUIHandler(), context, PlaybackConfig.getInstance().isPlaybackBackgroundBlack(), PlaybackConfig.getInstance().isImmersiveModeEnabled(), PlaybackConfig.getInstance().isSecurePlaybackSurfaceEnabled());
    }

    AndroidVideoSurface(@Nonnull Handler handler, @Nonnull Context context, boolean z, boolean z2, boolean z3) {
        this.mMutex = new Object();
        this.mSystemUIFlagsOverride = -1;
        this.mCustomLayoutParamsApplied = false;
        this.mDestroyCalled = false;
        this.mCreateSurfaceRunnable = new Runnable() { // from class: com.amazon.avod.media.playback.android.AndroidVideoSurface.1
            @Override // java.lang.Runnable
            public void run() {
                SurfaceView amazonSurfaceView;
                ViewGroup.LayoutParams layoutParams;
                synchronized (AndroidVideoSurface.this.mMutex) {
                    DLog.logf("AndroidVideoSurface create");
                    if (AndroidVideoSurface.this.mIsSuperResolutionUpscalerEnabled) {
                        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Ticker.systemTicker());
                        amazonSurfaceView = UpscalerUtils.getInstance().createUpscalerSurfaceViewByReflection(AndroidVideoSurface.this.mViewGroup.getContext());
                        Preconditions.checkState(amazonSurfaceView != null, "Failed to create UpscalerSurfaceView");
                        DLog.logf("UpscalerSurfaceView %s create took %s ms", amazonSurfaceView, Long.valueOf(stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS)));
                    } else {
                        amazonSurfaceView = new AmazonSurfaceView(AndroidVideoSurface.this.mViewGroup.getContext());
                    }
                    if (AndroidVideoSurface.this.mIsMediaOverlay) {
                        amazonSurfaceView.setZOrderMediaOverlay(true);
                    }
                    if (AndroidVideoSurface.this.mIsSecurePlaybackSurfaceEnabled && AndroidVideoSurface.this.doesDefaultDisplaySupportSecureSurfaces()) {
                        amazonSurfaceView.setSecure(true);
                    }
                    amazonSurfaceView.getHolder().addCallback(AndroidVideoSurface.this.mSurfaceCallback);
                    if (AndroidVideoSurface.this.mViewGroup instanceof RelativeLayout) {
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                        layoutParams2.addRule(14);
                        layoutParams2.addRule(15);
                        layoutParams = layoutParams2;
                    } else {
                        DLog.warnf("AndroidVideoSurface detected non-RelativeLayout overlayView, this may result in video not being centered. To fix this set LayoutParams for all child Views in your hierarchy.");
                        layoutParams = new ViewGroup.LayoutParams(-2, -2);
                    }
                    AndroidVideoSurface.this.mViewGroup.addView(amazonSurfaceView, 0, layoutParams);
                    if (AndroidVideoSurface.this.mIsPlaybackSurfaceParentLayoutBlack) {
                        AndroidVideoSurface.this.mViewGroup.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                    if (AndroidVideoSurface.this.mSystemUIFlagsOverride != -1) {
                        amazonSurfaceView.setSystemUiVisibility(AndroidVideoSurface.this.mSystemUIFlagsOverride);
                    } else {
                        amazonSurfaceView.setSystemUiVisibility(AndroidVideoSurface.this.mSurfaceViewSystemUIFlags);
                    }
                    AndroidVideoSurface.this.setSurfaceView(amazonSurfaceView);
                }
            }
        };
        this.mDestroyRunnable = new Runnable() { // from class: com.amazon.avod.media.playback.android.AndroidVideoSurface.2
            @Override // java.lang.Runnable
            public void run() {
                DLog.logf("AndroidVideoSurface destroy");
                synchronized (AndroidVideoSurface.this.mMutex) {
                    if (AndroidVideoSurface.this.mViewGroup != null && AndroidVideoSurface.this.mSurfaceView != null) {
                        AndroidVideoSurface.this.mViewGroup.removeView(AndroidVideoSurface.this.mSurfaceView);
                        AndroidVideoSurface.this.mViewGroup.invalidate();
                    }
                    AndroidVideoSurface.this.mViewGroup = null;
                    if (AndroidVideoSurface.this.mSurfaceView != null && AndroidVideoSurface.this.mSurfaceView.getHolder() != null && AndroidVideoSurface.this.mSurfaceView.getHolder().getSurface() != null) {
                        AndroidVideoSurface.this.mSurfaceView.getHolder().getSurface().release();
                        AndroidVideoSurface.this.mSurfaceView.getHolder().removeCallback(AndroidVideoSurface.this.mSurfaceCallback);
                    }
                    AndroidVideoSurface.this.setSurfaceView(null);
                }
            }
        };
        this.mSurfaceCallback = new SurfaceHolder.Callback() { // from class: com.amazon.avod.media.playback.android.AndroidVideoSurface.3
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                DLog.logf("AndroidVideoSurface surface %s size changed: width=%d height=%d", surfaceHolder, Integer.valueOf(i2), Integer.valueOf(i3));
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                DLog.logf("AndroidVideoSurface surface created %s", surfaceHolder);
                if (AndroidVideoSurface.this.mCreationListener != null) {
                    AndroidVideoSurface.this.mCreationListener.onCreated(surfaceHolder);
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                DLog.logf("AndroidVideoSurface surface destroyed %s", surfaceHolder);
                if (AndroidVideoSurface.this.mCreationListener != null) {
                    AndroidVideoSurface.this.mCreationListener.onDestroyed(surfaceHolder);
                }
            }
        };
        this.mUIHandler = handler;
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mIsPlaybackSurfaceParentLayoutBlack = z;
        this.mIsSecurePlaybackSurfaceEnabled = z3;
        if (z2) {
            this.mSurfaceViewSystemUIFlags = 5894;
        } else {
            this.mSurfaceViewSystemUIFlags = 1536;
        }
    }

    public void createAsync(@Nonnull ViewGroup viewGroup, @Nonnull CreationListener creationListener, boolean z, int i) {
        createAsync(viewGroup, creationListener, z, i, false);
    }

    public void createAsync(@Nonnull ViewGroup viewGroup, @Nonnull CreationListener creationListener, boolean z, int i, boolean z2) {
        this.mCreationListener = (CreationListener) Preconditions.checkNotNull(creationListener, "creationListener");
        this.mViewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup, "parentRelativeLayout");
        this.mIsMediaOverlay = z;
        this.mSystemUIFlagsOverride = i;
        this.mIsSuperResolutionUpscalerEnabled = z2;
        this.mUIHandler.post(this.mCreateSurfaceRunnable);
    }

    public void unregisterListener() {
        this.mCreationListener = null;
    }

    @Nullable
    public SurfaceView getSurfaceView() {
        SurfaceView surfaceView;
        synchronized (this.mMutex) {
            surfaceView = this.mDestroyCalled ? null : this.mSurfaceView;
        }
        return surfaceView;
    }

    public void destroy() {
        synchronized (this.mMutex) {
            this.mDestroyCalled = true;
        }
        this.mUIHandler.post(this.mDestroyRunnable);
    }

    public void setDimensions(final int i, final int i2) {
        this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.android.AndroidVideoSurface$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setDimensions$0(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDimensions$0(int i, int i2) {
        synchronized (this.mMutex) {
            SurfaceView surfaceView = this.mSurfaceView;
            if (surfaceView != null && surfaceView.getHolder() != null) {
                DLog.logf("AndroidVideoSurface setDimensions width=%d height=%d", Integer.valueOf(i), Integer.valueOf(i2));
                if (this.mCustomLayoutParamsApplied) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(14);
                    layoutParams.addRule(15);
                    this.mSurfaceView.setLayoutParams(layoutParams);
                    this.mCustomLayoutParamsApplied = false;
                }
                ((FixedDimensionView) this.mSurfaceView).setFixedDimensions(i, i2);
            }
        }
    }

    public void setVideoRegion(@Nonnull final VideoRegion videoRegion) {
        this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.android.AndroidVideoSurface$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setVideoRegion$1(videoRegion);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoRegion$1(VideoRegion videoRegion) {
        synchronized (this.mMutex) {
            SurfaceView surfaceView = this.mSurfaceView;
            if (surfaceView != null && surfaceView.getHolder() != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(videoRegion.getWidth(), videoRegion.getHeight());
                layoutParams.topMargin = videoRegion.getFromTop();
                layoutParams.leftMargin = videoRegion.getFromLeft();
                this.mSurfaceView.setLayoutParams(layoutParams);
                ((FixedDimensionView) this.mSurfaceView).setFixedDimensions(videoRegion.getWidth(), videoRegion.getHeight());
                this.mCustomLayoutParamsApplied = true;
                DLog.logf("AndroidVideoSurface setVideoRegion %s", videoRegion);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doesDefaultDisplaySupportSecureSurfaces() {
        DisplayManager displayManager = (DisplayManager) this.mContext.getSystemService("display");
        Display display = displayManager != null ? displayManager.getDisplay(0) : null;
        return display != null && (display.getFlags() & 2) == 2;
    }

    protected void setSurfaceView(@Nullable SurfaceView surfaceView) {
        this.mSurfaceView = surfaceView;
    }
}

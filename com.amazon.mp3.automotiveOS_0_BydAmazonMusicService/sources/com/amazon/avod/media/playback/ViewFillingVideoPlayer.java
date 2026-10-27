package com.amazon.avod.media.playback;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.platform.Handlers;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ViewFillingVideoPlayer extends VideoPlayerProxy {
    private final View mBackgroundView;
    private final VideoPlayer mDelegate;
    private final RelativeLayout mRelativeLayout;
    private VideoRenderingSettings mRenderingSettings;
    private final Handler mUiHandler;

    public ViewFillingVideoPlayer(VideoPlayerBase videoPlayerBase, Context context) {
        this(videoPlayerBase, new RelativeLayout(context), new View(context), Handlers.getUIHandler());
    }

    ViewFillingVideoPlayer(VideoPlayer videoPlayer, RelativeLayout relativeLayout, View view, Handler handler) {
        this.mDelegate = videoPlayer;
        this.mRelativeLayout = relativeLayout;
        this.mBackgroundView = view;
        this.mUiHandler = handler;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy
    protected VideoPlayer getDelegate() {
        return this.mDelegate;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        Preconditions.checkNotNull(this.mRenderingSettings, "setRenderingSettings must be called before starting playback.");
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.ViewFillingVideoPlayer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$start$0();
            }
        });
        super.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.mBackgroundView.setBackgroundColor(Color.argb(255, 0, 0, 0));
        if (this.mBackgroundView.getParent() == null) {
            this.mRelativeLayout.addView(this.mBackgroundView, 0, layoutParams);
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void terminate(boolean z, @Nullable MediaException mediaException) {
        super.terminate(z, mediaException);
        VideoRenderingSettings videoRenderingSettings = this.mRenderingSettings;
        if (videoRenderingSettings == null || videoRenderingSettings.getSurface() != null || this.mRenderingSettings.getParentView() == null) {
            return;
        }
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.ViewFillingVideoPlayer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$terminate$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$terminate$1() {
        this.mRenderingSettings.getParentView().removeView(this.mRelativeLayout);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        VideoRenderingSettings videoRenderingSettings2 = (VideoRenderingSettings) Preconditions.checkNotNull(videoRenderingSettings, "settings");
        this.mRenderingSettings = videoRenderingSettings2;
        if (videoRenderingSettings2.getSurface() != null) {
            super.setRenderingSettings(this.mRenderingSettings);
        } else {
            if (this.mRenderingSettings.getSurfaceHandlingMode() == SurfaceHandlingMode.AUDIO_ONLY) {
                super.setRenderingSettings(videoRenderingSettings);
                return;
            }
            Preconditions.checkNotNull(this.mRenderingSettings.getParentView(), "parentViewGroup");
            this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.ViewFillingVideoPlayer$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setRenderingSettings$2();
                }
            });
            super.setRenderingSettings(new VideoRenderingSettings(this.mRelativeLayout, this.mRenderingSettings.getSystemUIFlags(), this.mRenderingSettings.isMediaOverlay(), this.mRenderingSettings.isVideoTrackRecreationEnabled()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRenderingSettings$2() {
        this.mRenderingSettings.getParentView().addView(this.mRelativeLayout, 0, new RelativeLayout.LayoutParams(-1, -1));
    }
}

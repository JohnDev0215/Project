package com.amazon.avod.playback.sye;

import android.view.SurfaceView;
import android.view.ViewGroup;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.view.SyeClosedCaptionView;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SyeViewHolder {
    private ViewGroup mParent;
    private SyeClosedCaptionView mSubtitleView;
    private SurfaceView mView;

    SyeViewHolder() {
    }

    @Nullable
    public SurfaceView getVideoView() {
        return this.mView;
    }

    public void setVideoView(@Nonnull SurfaceView surfaceView) {
        this.mView = (SurfaceView) Preconditions.checkNotNull(surfaceView, "view");
    }

    @Nullable
    public ViewGroup getParent() {
        return this.mParent;
    }

    public void setParent(@Nonnull ViewGroup viewGroup) {
        this.mParent = (ViewGroup) Preconditions.checkNotNull(viewGroup, "parent");
    }

    @Nullable
    public SyeClosedCaptionView getSubtitleView() {
        return this.mSubtitleView;
    }

    public void setSubtitleView(@Nonnull SyeClosedCaptionView syeClosedCaptionView) {
        this.mSubtitleView = (SyeClosedCaptionView) Preconditions.checkNotNull(syeClosedCaptionView, "subtitleView");
    }
}

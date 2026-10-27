package com.amazon.avod.media.playback;

import android.view.Surface;
import android.view.ViewGroup;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VideoRenderingSettings {
    private final boolean mIsMediaOverlay;
    private final boolean mIsVideoTrackRecreationEnabled;
    private final ViewGroup mParentViewGroup;
    private final PlaybackZoomLevel mPlaybackZoomLevel;
    private final Surface mSurface;
    private final SurfaceHandlingMode mSurfaceHandlingMode;
    private final int mSystemUIFlags;

    public VideoRenderingSettings(@Nonnull ViewGroup viewGroup, int i, boolean z, boolean z2) {
        this.mParentViewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup, "parentViewGroup");
        this.mSurface = null;
        this.mSurfaceHandlingMode = SurfaceHandlingMode.CREATE;
        this.mPlaybackZoomLevel = PlaybackZoomConfig.getInstance().getPlaybackZoomLevel();
        this.mSystemUIFlags = i;
        this.mIsMediaOverlay = z;
        this.mIsVideoTrackRecreationEnabled = z2;
    }

    public VideoRenderingSettings(boolean z) {
        this.mParentViewGroup = null;
        this.mSurface = null;
        this.mSurfaceHandlingMode = SurfaceHandlingMode.AUDIO_ONLY;
        this.mPlaybackZoomLevel = PlaybackZoomConfig.getInstance().getPlaybackZoomLevel();
        this.mIsMediaOverlay = false;
        this.mSystemUIFlags = -1;
        this.mIsVideoTrackRecreationEnabled = z;
    }

    public VideoRenderingSettings() {
        this(false);
    }

    public VideoRenderingSettings(@Nonnull ViewGroup viewGroup, @Nonnull Surface surface) {
        this.mParentViewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup, "parentViewGroup");
        this.mSurface = (Surface) Preconditions.checkNotNull(surface, "surface");
        this.mSurfaceHandlingMode = SurfaceHandlingMode.ATTACH;
        this.mPlaybackZoomLevel = PlaybackZoomConfig.getInstance().getPlaybackZoomLevel();
        this.mIsMediaOverlay = false;
        this.mSystemUIFlags = -1;
        this.mIsVideoTrackRecreationEnabled = false;
    }

    @Nullable
    public ViewGroup getParentView() {
        return this.mParentViewGroup;
    }

    @Nullable
    public Surface getSurface() {
        return this.mSurface;
    }

    @Nonnull
    public SurfaceHandlingMode getSurfaceHandlingMode() {
        return this.mSurfaceHandlingMode;
    }

    @Nonnull
    public PlaybackZoomLevel getPlaybackZoomLevel() {
        return this.mPlaybackZoomLevel;
    }

    public int getSystemUIFlags() {
        return this.mSystemUIFlags;
    }

    public boolean isMediaOverlay() {
        return this.mIsMediaOverlay;
    }

    public boolean isVideoTrackRecreationEnabled() {
        return this.mIsVideoTrackRecreationEnabled;
    }
}

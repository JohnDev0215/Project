package com.amazon.avod.media.framework.playback.util;

import android.view.ViewGroup;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ZoomCalculator {
    private final boolean mInvisibleZoomHasNonZeroSize;
    private ViewGroup mParentViewGroup;
    private final int mScreenHeight;
    private final VideoRegion mScreenVideoRegion;
    private final int mScreenWidth;
    private int mVideoResolutionHeight;
    private int mVideoResolutionWidth;
    private static final VideoRegion OFF_SCREEN_REGION = new VideoRegion(-1, -1, 0, 0);
    private static final VideoRegion OFF_SCREEN_REGION_NON_ZERO = new VideoRegion(-1, -1, 1, 1);
    private static final Double DELTA = Double.valueOf(0.01d);

    public ZoomCalculator(DeviceConfiguration deviceConfiguration) {
        int screenWidth = deviceConfiguration.getScreenWidth();
        this.mScreenWidth = screenWidth;
        int screenHeight = deviceConfiguration.getScreenHeight();
        this.mScreenHeight = screenHeight;
        this.mScreenVideoRegion = new VideoRegion(0, 0, screenWidth, screenHeight);
        this.mInvisibleZoomHasNonZeroSize = PlaybackZoomConfig.getInstance().doesInvisibleZoomHaveNonZeroSize();
    }

    @Nonnull
    public VideoRegion getVideoRegionForScreenSize() {
        return this.mScreenVideoRegion;
    }

    public void setParentLayoutOverride(@Nonnull ViewGroup viewGroup) {
        this.mParentViewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup, "parentViewGroup");
    }

    public void setVideoResolution(@Nonnull VideoResolution videoResolution) {
        this.mVideoResolutionWidth = videoResolution.getWidth();
        if (Math.abs(videoResolution.getAspectRatio() - (-1.0d)) > DELTA.doubleValue()) {
            this.mVideoResolutionHeight = (int) (((double) this.mVideoResolutionWidth) / videoResolution.getAspectRatio());
        } else {
            this.mVideoResolutionHeight = videoResolution.getHeight();
        }
    }

    private int getUsableScreenWidth() {
        ViewGroup viewGroup = this.mParentViewGroup;
        if (viewGroup != null && viewGroup.getWidth() > 0) {
            return this.mParentViewGroup.getWidth();
        }
        return this.mScreenWidth;
    }

    private int getUsableScreenHeight() {
        ViewGroup viewGroup = this.mParentViewGroup;
        if (viewGroup != null && viewGroup.getHeight() > 0) {
            return this.mParentViewGroup.getHeight();
        }
        return this.mScreenHeight;
    }

    @Nonnull
    public VideoRegion getVideoRegion(@Nonnull PlaybackZoomLevel playbackZoomLevel) {
        double dMax;
        PlaybackZoomLevel playbackZoomLevel2 = PlaybackZoomLevel.NO_ZOOM;
        Preconditions.checkArgument(playbackZoomLevel != playbackZoomLevel2, "No zoom is not supported by the ZoomCalculator.");
        if (playbackZoomLevel.getZoomLevel() == PlaybackZoomLevel.ZoomLevel.INVISIBLE) {
            return this.mInvisibleZoomHasNonZeroSize ? OFF_SCREEN_REGION_NON_ZERO : OFF_SCREEN_REGION;
        }
        if (playbackZoomLevel == PlaybackZoomLevel.FULL_SCREEN) {
            playbackZoomLevel = getZoomLevelForFullScreen();
        }
        double usableScreenWidth = ((double) getUsableScreenWidth()) / ((double) this.mVideoResolutionWidth);
        double usableScreenHeight = getUsableScreenHeight();
        int i = this.mVideoResolutionHeight;
        double d = usableScreenHeight / ((double) i);
        double d2 = ((double) this.mVideoResolutionWidth) / ((double) i);
        if (playbackZoomLevel.getZoomLevel() == PlaybackZoomLevel.ZoomLevel.NATIVE) {
            dMax = Math.min(usableScreenWidth, d);
        } else if (playbackZoomLevel.getZoomLevel() == PlaybackZoomLevel.ZoomLevel.FULL_SCREEN) {
            dMax = Math.max(usableScreenWidth, d);
        } else if (playbackZoomLevel.getZoomRatio() < playbackZoomLevel2.getZoomRatio()) {
            dMax = Math.min(usableScreenWidth, d) * ((double) playbackZoomLevel.getZoomRatio());
        } else {
            dMax = Math.max(usableScreenWidth, d) * (Math.max(playbackZoomLevel.getZoomRatio(), d2) / Math.min(playbackZoomLevel.getZoomRatio(), d2));
        }
        int i2 = (int) (((double) this.mVideoResolutionHeight) * dMax);
        int i3 = (int) (((double) this.mVideoResolutionWidth) * dMax);
        return new VideoRegion((getUsableScreenHeight() - i2) / 2, (getUsableScreenWidth() - i3) / 2, i3, i2);
    }

    private PlaybackZoomLevel getZoomLevelForFullScreen() {
        PlaybackZoomLevel playbackZoomLevel = PlaybackZoomLevel.FULL_SCREEN;
        if (DELTA.doubleValue() <= Math.abs((((double) getUsableScreenWidth()) / ((double) getUsableScreenHeight())) - (((double) this.mVideoResolutionWidth) / ((double) this.mVideoResolutionHeight)))) {
            return playbackZoomLevel;
        }
        PlaybackZoomLevel playbackZoomLevel2 = PlaybackZoomLevel.RATIO_239_1;
        DLog.logf("Adjusting custom zoom for screen %dx%d, and video %dx%d", Integer.valueOf(getUsableScreenWidth()), Integer.valueOf(getUsableScreenHeight()), Integer.valueOf(this.mVideoResolutionWidth), Integer.valueOf(this.mVideoResolutionHeight));
        return playbackZoomLevel2;
    }
}

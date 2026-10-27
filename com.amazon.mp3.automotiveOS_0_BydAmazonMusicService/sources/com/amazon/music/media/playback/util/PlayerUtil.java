package com.amazon.music.media.playback.util;

import android.view.Surface;
import android.view.ViewGroup;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.MediaStreamingStatusProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.video.sdk.player.PlaybackState;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.RenderingConfigData;
import com.amazon.video.sdk.player.SurfaceHandlingMode;
import java.util.Locale;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J(\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/media/playback/util/PlayerUtil;", "", "()V", "createRenderingConfig", "Lcom/amazon/video/sdk/player/RenderingConfig;", "surfaceHandlingMode", "Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "parentView", "Landroid/view/ViewGroup;", "playbackSurface", "Landroid/view/Surface;", "systemUIFlags", "", "enableVideoTrackRecreation", "", "isVideoMediaItem", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "updateStreamingStatus", "", "playbackConfig", "Lcom/amazon/music/media/playback/config/PlaybackConfig;", "currentPlaybackState", "Lcom/amazon/video/sdk/player/PlaybackState;", "deviceName", "", "forceUpdate", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerUtil {
    public static final PlayerUtil INSTANCE = new PlayerUtil();

    private PlayerUtil() {
    }

    public final void updateStreamingStatus(PlaybackConfig playbackConfig, PlaybackState currentPlaybackState, String deviceName, boolean forceUpdate) {
        PlaybackController playbackController;
        Intrinsics.checkNotNullParameter(currentPlaybackState, "currentPlaybackState");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        if (playbackConfig == null || (playbackController = playbackConfig.getPlayback().getPlaybackController(ControlSource.APP_UI)) == null) {
            return;
        }
        long durationMillis = playbackController.getDurationMillis() / 1000;
        long positionMillis = playbackController.getPositionMillis() / 1000;
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        String scheme = type.getScheme();
        Intrinsics.checkNotNullExpressionValue(scheme, "ASIN.scheme");
        String upperCase = scheme.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        MediaItem currentMediaItem = playbackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return;
        }
        String trackAsin = currentMediaItem.getMediaItemId(type).getId();
        MediaStreamingStatusProvider mediaStreamingStatusProvider = playbackConfig.getMediaStreamingStatusProvider();
        Intrinsics.checkNotNullExpressionValue(trackAsin, "trackAsin");
        mediaStreamingStatusProvider.updateCurrentStreamingStatus(trackAsin, upperCase, currentPlaybackState, durationMillis, positionMillis, deviceName, forceUpdate);
    }

    public final boolean isVideoMediaItem(MediaItem mediaItem) {
        return (mediaItem == null || mediaItem.getMediaItemId(MediaItemId.Type.PID) == null) ? false : true;
    }

    public final RenderingConfig createRenderingConfig(@Nonnull SurfaceHandlingMode surfaceHandlingMode, ViewGroup parentView, Surface playbackSurface, int systemUIFlags, boolean enableVideoTrackRecreation) {
        Intrinsics.checkNotNullParameter(surfaceHandlingMode, "surfaceHandlingMode");
        return new RenderingConfigData(surfaceHandlingMode, parentView, playbackSurface, systemUIFlags, enableVideoTrackRecreation);
    }
}

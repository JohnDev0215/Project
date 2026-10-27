package com.amazon.video.sdk.player;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.util.SDKPlayerLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: VolumeFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0010\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u0016\u0010\u0012\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/video/sdk/player/VolumeFeatureImpl;", "Lcom/amazon/video/sdk/player/VolumeFeature;", "", "", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "scaleVolume", "Lcom/amazon/avod/playback/PlaybackExperienceController;", "playbackExperienceController", "setPlaybackExperienceController", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "onPrepared", "", "isDestroyed", "onTerminate", "innerVolume", "F", "innerPlaybackExperienceController", "Lcom/amazon/avod/playback/PlaybackExperienceController;", "getVolume", "()F", "setVolume", "(F)V", "<init>", "()V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class VolumeFeatureImpl implements VolumeFeature {
    private PlaybackExperienceController innerPlaybackExperienceController;
    private float innerVolume = 1.0f;

    public final void setPlaybackExperienceController(PlaybackExperienceController playbackExperienceController) {
        this.innerPlaybackExperienceController = playbackExperienceController;
        scaleVolume(this.innerVolume);
    }

    @Override // com.amazon.video.sdk.player.VolumeFeature
    /* JADX INFO: renamed from: getVolume, reason: from getter */
    public float getInnerVolume() {
        return this.innerVolume;
    }

    @Override // com.amazon.video.sdk.player.VolumeFeature
    public void setVolume(float f) {
        SDKPlayerLogger.log("VolumeFeature.setVolume(%s)", Float.valueOf(f));
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.innerVolume = fCoerceIn;
        scaleVolume(fCoerceIn);
    }

    private final void scaleVolume(float volume) {
        PlaybackExperienceController playbackExperienceController = this.innerPlaybackExperienceController;
        if (playbackExperienceController == null) {
            return;
        }
        playbackExperienceController.scaleVolume(RangesKt___RangesKt.coerceIn(volume, 0.0f, 1.0f));
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        this.innerPlaybackExperienceController = videoPresentation.getPlayer().getPlaybackExperienceController();
    }

    public void onTerminate(boolean isDestroyed) {
        this.innerPlaybackExperienceController = null;
        if (isDestroyed) {
            this.innerVolume = 1.0f;
        }
    }
}

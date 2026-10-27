package com.amazon.video.sdk.player;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackEventListener;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import com.amazon.video.sdk.player.util.UninitializedInstance;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.File;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UninitializedVideoPlayer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b<\u0010=J\u001c\u0010\b\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010 \u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020!H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010%\u001a\u00020\u00072\u0006\u00100\u001a\u00020/H\u0016J\u0012\u0010%\u001a\u00020\u00072\b\u00102\u001a\u0004\u0018\u000101H\u0016J\u0012\u00103\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J\u0012\u00103\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010+H\u0016J\b\u00104\u001a\u00020\u0007H\u0016J\b\u00106\u001a\u000205H\u0016R\u001a\u00108\u001a\u0002078\u0016X\u0096D¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;¨\u0006>"}, d2 = {"Lcom/amazon/video/sdk/player/UninitializedVideoPlayer;", "Lcom/amazon/avod/media/playback/VideoPlayer;", "Lcom/amazon/video/sdk/player/util/UninitializedInstance;", "Lcom/amazon/avod/media/playback/VideoSpecification;", "spec", "Ljava/io/File;", "storagePath", "", "prepareAsync", "", "shouldRetainReusableComponents", "Lcom/amazon/avod/media/framework/error/MediaException;", "terminationCause", "terminate", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "settings", "setRenderingSettings", "notifyUserLaunchedPlayback", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "pause", "", "rawPositionMillis", "seekTo", "positionMillis", "seekToUTC", "getCurrentPosition", "getCurrentAbsolutePosition", "getCurrentPositionUTC", "", "getBufferPercentage", "getDuration", "Lcom/amazon/avod/media/playback/support/PlayerStatistics;", "getPlayerStatistics", "Lcom/amazon/avod/playback/PlaybackExperienceController;", "getPlaybackExperienceController", "Lcom/amazon/avod/playback/AudioTrackChangeListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "Lcom/amazon/avod/playback/PlaybackContentEventListener;", "Lcom/amazon/avod/playback/PlaybackEventListener;", "playerListener", "Lcom/amazon/avod/playback/PlaybackSessionBufferEventListener;", "bufferListener", "Lcom/amazon/avod/playback/PlaybackQualityChangedEventListener;", "qualityChangedListener", "Lcom/amazon/avod/playback/PlaybackStateEventListener;", "stateListener", "Lcom/amazon/avod/playback/PlaybackAdStateChangeListener;", "adStateChangeListener", "Lcom/amazon/avod/playback/PlaybackTimeDataEventListener;", "playbackTimeDataEventListener", "removeListener", "clearAllListeners", "Lcom/amazon/avod/content/ContentSession;", "getContentSession", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class UninitializedVideoPlayer implements VideoPlayer, UninitializedInstance {
    private final String errorMessage = "videoPlayer is in an uninitialized state";

    @Override // com.amazon.video.sdk.player.util.UninitializedInstance
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(VideoSpecification spec, File storagePath) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void terminate(boolean shouldRetainReusableComponents, MediaException terminationCause) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(VideoRenderingSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void notifyUserLaunchedPlayback() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long rawPositionMillis) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long positionMillis) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlayerStatistics getPlayerStatistics() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(AudioTrackChangeListener listener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackContentEventListener listener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackEventListener playerListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackSessionBufferEventListener bufferListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackQualityChangedEventListener qualityChangedListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackStateEventListener stateListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackAdStateChangeListener adStateChangeListener) {
        Intrinsics.checkNotNullParameter(adStateChangeListener, "adStateChangeListener");
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackTimeDataEventListener playbackTimeDataEventListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(AudioTrackChangeListener listener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(PlaybackQualityChangedEventListener qualityChangedListener) {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void clearAllListeners() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public ContentSession getContentSession() {
        throw new UninitializedPropertyAccessException(getErrorMessage());
    }
}

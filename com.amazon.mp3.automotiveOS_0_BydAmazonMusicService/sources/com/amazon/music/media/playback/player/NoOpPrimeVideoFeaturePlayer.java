package com.amazon.music.media.playback.player;

import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.video.sdk.player.AdPlaybackFeature;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.ContentState;
import com.amazon.video.sdk.player.EventListener;
import com.amazon.video.sdk.player.PlaybackState;
import com.amazon.video.sdk.player.PlayerEvent;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.RenderingConfigData;
import com.amazon.video.sdk.player.SurfaceHandlingMode;
import com.amazon.video.sdk.player.VolumeFeature;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.playlist.PlaylistFeature;
import com.amazon.video.sdk.player.timeline.PlayableRange;
import com.amazon.video.sdk.player.timeline.TimeData;
import com.amazon.video.sdk.player.timeline.TimeUnit;
import com.amazon.video.sdk.player.timeline.Timeline;
import com.amazon.video.sdk.player.timeline.TimelineItem;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEvent;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEventListener;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature;
import com.amazon.video.sdk.stream.AudioStreamGroup;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.StreamFeature;
import com.amazon.video.sdk.stream.TimedTextConfig;
import com.amazon.video.sdk.stream.TimedTextStreamGroup;
import com.amazon.video.sdk.stream.TimedTextStreamMatcher;
import com.amazon.video.sdk.stream.VideoStreamGroup;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0007Z[\\]^_`B\u0007¢\u0006\u0004\bX\u0010YJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J.\u0010\n\u001a\u00020\u0002\"\b\b\u0000\u0010\u0005*\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J.\u0010\u000b\u001a\u00020\u0002\"\b\b\u0000\u0010\u0005*\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\b\u0010\f\u001a\u00020\u0002H\u0016J\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\"\u0010\u0014\u001a\u00020\u00138\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010&\u001a\u00020%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010+\u001a\u00020*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001a\u00100\u001a\u00020/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001a\u00105\u001a\u0002048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u001a\u0010:\u001a\u0002098\u0016X\u0096\u0004¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u001a\u0010?\u001a\u00020>8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u001a\u0010D\u001a\u00020C8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010GR\u001a\u0010I\u001a\u00020H8\u0016X\u0096D¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bI\u0010KR\"\u0010M\u001a\u00020L8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001a\u0010T\u001a\u00020S8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W¨\u0006a"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer;", "Lcom/amazon/video/sdk/player/Player;", "", "destroy", "Lcom/amazon/video/sdk/player/PlayerEvent;", "E", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/video/sdk/player/EventListener;", "callback", DebugKt.DEBUG_PROPERTY_VALUE_OFF, DebugKt.DEBUG_PROPERTY_VALUE_ON, "pause", PageUriUtils.DO_PLAY_VALUE, "", "position", "seek", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", "Lcom/amazon/video/sdk/player/RenderingConfig;", "renderingConfig", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "Lcom/amazon/video/sdk/player/error/PlaybackError;", "contentError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getContentError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "Lcom/amazon/video/sdk/player/RenderingConfig;", "getRenderingConfig", "()Lcom/amazon/video/sdk/player/RenderingConfig;", "setRenderingConfig", "(Lcom/amazon/video/sdk/player/RenderingConfig;)V", "Lcom/amazon/video/sdk/player/ContentState;", "contentState", "Lcom/amazon/video/sdk/player/ContentState;", "getContentState", "()Lcom/amazon/video/sdk/player/ContentState;", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "currentTime", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "getCurrentTime", "()Lcom/amazon/video/sdk/player/timeline/TimeData;", "Lcom/amazon/video/sdk/player/PlaybackState;", TetheredMessageKey.playbackState, "Lcom/amazon/video/sdk/player/PlaybackState;", "getPlaybackState", "()Lcom/amazon/video/sdk/player/PlaybackState;", "Lcom/amazon/video/sdk/stream/StreamFeature;", "streamFeature", "Lcom/amazon/video/sdk/stream/StreamFeature;", "getStreamFeature", "()Lcom/amazon/video/sdk/stream/StreamFeature;", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "timeline", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "getTimeline", "()Lcom/amazon/video/sdk/player/timeline/Timeline;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "videoPreviewAssetFeature", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "getVideoPreviewAssetFeature", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "Lcom/amazon/video/sdk/player/VolumeFeature;", "volumeFeature", "Lcom/amazon/video/sdk/player/VolumeFeature;", "getVolumeFeature", "()Lcom/amazon/video/sdk/player/VolumeFeature;", "Lcom/amazon/video/sdk/player/AdPlaybackFeature;", "adPlaybackFeature", "Lcom/amazon/video/sdk/player/AdPlaybackFeature;", "getAdPlaybackFeature", "()Lcom/amazon/video/sdk/player/AdPlaybackFeature;", "", "isVideoTrackRecreationSupported", "Z", "()Z", "", "playbackRate", "F", "getPlaybackRate", "()F", "setPlaybackRate", "(F)V", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeature;", "playlistFeature", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeature;", "getPlaylistFeature", "()Lcom/amazon/video/sdk/player/playlist/PlaylistFeature;", "<init>", "()V", "AdPlaybackFeatureImpl", "NoOpPlayableRange", "NoOpStreamFeature", "NoOpTimeData", "NoOpTimeline", "NoOpVideoPreviewAssetFeature", "NoOpVolumeFeature", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class NoOpPrimeVideoFeaturePlayer implements com.amazon.video.sdk.player.Player {
    private final PlaybackError contentError;
    private final boolean isVideoTrackRecreationSupported;
    private float playbackRate;
    private RenderingConfig renderingConfig = new RenderingConfigData(SurfaceHandlingMode.AUDIO_ONLY, null, null, 0, false, 30, null);
    private final ContentState contentState = ContentState.Unloaded;
    private final TimeData currentTime = new NoOpTimeData(null, 0, 0, null, 15, null);
    private final PlaybackState playbackState = PlaybackState.Paused;
    private final StreamFeature streamFeature = new NoOpStreamFeature(0 == true ? 1 : 0, null, null, null, null, 31, null);
    private final Timeline timeline = new NoOpTimeline(null, 1, 0 == true ? 1 : 0);
    private final VideoPreviewAssetFeature videoPreviewAssetFeature = new NoOpVideoPreviewAssetFeature();
    private final VolumeFeature volumeFeature = new NoOpVolumeFeature();
    private final AdPlaybackFeature adPlaybackFeature = new AdPlaybackFeatureImpl(this);
    private final PlaylistFeature playlistFeature = new NoOpPlaylistFeature();

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\t\u001a\u00020\b\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J.\u0010\n\u001a\u00020\b\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpVideoPreviewAssetFeature;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;", "E", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "callback", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, DebugKt.DEBUG_PROPERTY_VALUE_ON, "", "currentVideoPosition", "requestVideoPreviewAsset", "<init>", "()V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpVideoPreviewAssetFeature implements VideoPreviewAssetFeature {
        @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
        public <E extends VideoPreviewAssetEvent> void off(Class<E> event, VideoPreviewAssetEventListener<E> callback) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(callback, "callback");
        }

        @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
        public <E extends VideoPreviewAssetEvent> void on(Class<E> event, VideoPreviewAssetEventListener<E> callback) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(callback, "callback");
        }

        @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature
        public void requestVideoPreviewAsset(long currentVideoPosition) {
        }
    }

    @Override // com.amazon.video.sdk.player.Player
    public void destroy() {
    }

    @Override // com.amazon.video.sdk.player.Player
    public <E extends PlayerEvent> void off(Class<E> event, EventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.amazon.video.sdk.player.Player
    public <E extends PlayerEvent> void on(Class<E> event, EventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.amazon.video.sdk.player.Player
    public void pause() {
    }

    @Override // com.amazon.video.sdk.player.Player
    public void play() {
    }

    @Override // com.amazon.video.sdk.player.Player
    public void seek(long position) {
    }

    @Override // com.amazon.video.sdk.player.Player
    public void start(ContentConfig content, RenderingConfig renderingConfig) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(renderingConfig, "renderingConfig");
    }

    @Override // com.amazon.video.sdk.player.Player
    public PlaybackError getContentError() {
        return this.contentError;
    }

    @Override // com.amazon.video.sdk.player.Player
    public RenderingConfig getRenderingConfig() {
        return this.renderingConfig;
    }

    @Override // com.amazon.video.sdk.player.Player
    public void setRenderingConfig(RenderingConfig renderingConfig) {
        Intrinsics.checkNotNullParameter(renderingConfig, "<set-?>");
        this.renderingConfig = renderingConfig;
    }

    @Override // com.amazon.video.sdk.player.Player
    public ContentState getContentState() {
        return this.contentState;
    }

    @Override // com.amazon.video.sdk.player.Player
    public PlaybackState getPlaybackState() {
        return this.playbackState;
    }

    @Override // com.amazon.video.sdk.player.Player
    public StreamFeature getStreamFeature() {
        return this.streamFeature;
    }

    @Override // com.amazon.video.sdk.player.Player
    public VideoPreviewAssetFeature getVideoPreviewAssetFeature() {
        return this.videoPreviewAssetFeature;
    }

    @Override // com.amazon.video.sdk.player.Player
    /* JADX INFO: renamed from: isVideoTrackRecreationSupported, reason: from getter */
    public boolean getIsVideoTrackRecreationSupported() {
        return this.isVideoTrackRecreationSupported;
    }

    @Override // com.amazon.video.sdk.player.Player
    public float getPlaybackRate() {
        return this.playbackRate;
    }

    @Override // com.amazon.video.sdk.player.Player
    public void setPlaybackRate(float f) {
        this.playbackRate = f;
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpTimeData;", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "unit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "currentPosition", "", "currentTimelineItemIndex", "playableRange", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "(Lcom/amazon/video/sdk/player/timeline/TimeUnit;JJLcom/amazon/video/sdk/player/timeline/PlayableRange;)V", "getCurrentPosition", "()J", "getCurrentTimelineItemIndex", "getPlayableRange", "()Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "getUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpTimeData implements TimeData {
        private final long currentPosition;
        private final long currentTimelineItemIndex;
        private final PlayableRange playableRange;
        private final TimeUnit unit;

        public NoOpTimeData() {
            this(null, 0L, 0L, null, 15, null);
        }

        public NoOpTimeData(TimeUnit unit, long j, long j2, PlayableRange playableRange) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(playableRange, "playableRange");
            this.unit = unit;
            this.currentPosition = j;
            this.currentTimelineItemIndex = j2;
            this.playableRange = playableRange;
        }

        public /* synthetic */ NoOpTimeData(TimeUnit timeUnit, long j, long j2, PlayableRange playableRange, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? TimeUnit.CONTENT_MILLIS : timeUnit, (i & 2) != 0 ? 0L : j, (i & 4) == 0 ? j2 : 0L, (i & 8) != 0 ? new NoOpPlayableRange(0L, 0L, null, 7, null) : playableRange);
        }

        @Override // com.amazon.video.sdk.player.timeline.TimeData
        public TimeUnit getUnit() {
            return this.unit;
        }

        @Override // com.amazon.video.sdk.player.timeline.TimeData
        public long getCurrentPosition() {
            return this.currentPosition;
        }

        @Override // com.amazon.video.sdk.player.timeline.TimeData
        public long getCurrentTimelineItemIndex() {
            return this.currentTimelineItemIndex;
        }

        @Override // com.amazon.video.sdk.player.timeline.TimeData
        public PlayableRange getPlayableRange() {
            return this.playableRange;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001BK\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b!\u0010\"J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R(\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpStreamFeature;", "Lcom/amazon/video/sdk/stream/StreamFeature;", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "timedTextConfig", "", "setTimedTextConfig", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamPreferences", "Ljava/util/List;", "getAudioStreamPreferences", "()Ljava/util/List;", "setAudioStreamPreferences", "(Ljava/util/List;)V", "Lcom/amazon/video/sdk/stream/AudioStreamGroup;", "audioStreams", "Lcom/amazon/video/sdk/stream/AudioStreamGroup;", "getAudioStreams", "()Lcom/amazon/video/sdk/stream/AudioStreamGroup;", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "timedTextStreamPreferences", "getTimedTextStreamPreferences", "setTimedTextStreamPreferences", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "timedTextStreams", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "getTimedTextStreams", "()Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "Lcom/amazon/video/sdk/stream/VideoStreamGroup;", "videoStreams", "Lcom/amazon/video/sdk/stream/VideoStreamGroup;", "getVideoStreams", "()Lcom/amazon/video/sdk/stream/VideoStreamGroup;", "<init>", "(Ljava/util/List;Lcom/amazon/video/sdk/stream/AudioStreamGroup;Ljava/util/List;Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;Lcom/amazon/video/sdk/stream/VideoStreamGroup;)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpStreamFeature implements StreamFeature {
        private List<AudioStreamMatcher> audioStreamPreferences;
        private final AudioStreamGroup audioStreams;
        private List<TimedTextStreamMatcher> timedTextStreamPreferences;
        private final TimedTextStreamGroup timedTextStreams;
        private final VideoStreamGroup videoStreams;

        public NoOpStreamFeature() {
            this(null, null, null, null, null, 31, null);
        }

        @Override // com.amazon.video.sdk.stream.StreamFeature
        public void setTimedTextConfig(TimedTextConfig timedTextConfig) {
            Intrinsics.checkNotNullParameter(timedTextConfig, "timedTextConfig");
        }

        public NoOpStreamFeature(List<AudioStreamMatcher> audioStreamPreferences, AudioStreamGroup audioStreamGroup, List<TimedTextStreamMatcher> timedTextStreamPreferences, TimedTextStreamGroup timedTextStreamGroup, VideoStreamGroup videoStreamGroup) {
            Intrinsics.checkNotNullParameter(audioStreamPreferences, "audioStreamPreferences");
            Intrinsics.checkNotNullParameter(timedTextStreamPreferences, "timedTextStreamPreferences");
            this.audioStreamPreferences = audioStreamPreferences;
            this.audioStreams = audioStreamGroup;
            this.timedTextStreamPreferences = timedTextStreamPreferences;
            this.timedTextStreams = timedTextStreamGroup;
            this.videoStreams = videoStreamGroup;
        }

        public /* synthetic */ NoOpStreamFeature(List list, AudioStreamGroup audioStreamGroup, List list2, TimedTextStreamGroup timedTextStreamGroup, VideoStreamGroup videoStreamGroup, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : audioStreamGroup, (i & 4) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list2, (i & 8) != 0 ? null : timedTextStreamGroup, (i & 16) == 0 ? videoStreamGroup : null);
        }

        @Override // com.amazon.video.sdk.stream.StreamFeature
        public List<AudioStreamMatcher> getAudioStreamPreferences() {
            return this.audioStreamPreferences;
        }

        @Override // com.amazon.video.sdk.stream.StreamFeature
        public TimedTextStreamGroup getTimedTextStreams() {
            return this.timedTextStreams;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpTimeline;", "Lcom/amazon/video/sdk/player/timeline/Timeline;", ContextMappingConstants.ITEMS, "", "Lcom/amazon/video/sdk/player/timeline/TimelineItem;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpTimeline implements Timeline {
        private final List<TimelineItem> items;

        public NoOpTimeline() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public NoOpTimeline(List<? extends TimelineItem> items) {
            Intrinsics.checkNotNullParameter(items, "items");
            this.items = items;
        }

        public /* synthetic */ NoOpTimeline(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }

        @Override // com.amazon.video.sdk.player.timeline.Timeline
        public List<TimelineItem> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpPlayableRange;", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "", "end", "J", "getEnd", "()Ljava/lang/Long;", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "getStart", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "unit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "getUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "<init>", "(JJLcom/amazon/video/sdk/player/timeline/TimeUnit;)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpPlayableRange implements PlayableRange {
        private final long end;
        private final long start;
        private final TimeUnit unit;

        public NoOpPlayableRange() {
            this(0L, 0L, null, 7, null);
        }

        public NoOpPlayableRange(long j, long j2, TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            this.end = j;
            this.start = j2;
            this.unit = unit;
        }

        @Override // com.amazon.video.sdk.player.timeline.PlayableRange
        public Long getEnd() {
            return Long.valueOf(this.end);
        }

        @Override // com.amazon.video.sdk.player.timeline.PlayableRange
        public Long getStart() {
            return Long.valueOf(this.start);
        }

        public /* synthetic */ NoOpPlayableRange(long j, long j2, TimeUnit timeUnit, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0L : j, (i & 2) == 0 ? j2 : 0L, (i & 4) != 0 ? TimeUnit.CONTENT_MILLIS : timeUnit);
        }
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bR*\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$NoOpVolumeFeature;", "Lcom/amazon/video/sdk/player/VolumeFeature;", "", "value", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "F", "getVolume", "()F", "setVolume", "(F)V", "<init>", "()V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpVolumeFeature implements VolumeFeature {
        private float volume;

        @Override // com.amazon.video.sdk.player.VolumeFeature
        public void setVolume(float f) {
        }

        @Override // com.amazon.video.sdk.player.VolumeFeature
        public float getVolume() {
            return this.volume;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimeVideoFeaturePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer$AdPlaybackFeatureImpl;", "Lcom/amazon/video/sdk/player/AdPlaybackFeature;", "<init>", "(Lcom/amazon/music/media/playback/player/NoOpPrimeVideoFeaturePlayer;)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public final class AdPlaybackFeatureImpl implements AdPlaybackFeature {
        final /* synthetic */ NoOpPrimeVideoFeaturePlayer this$0;

        public AdPlaybackFeatureImpl(NoOpPrimeVideoFeaturePlayer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }
    }
}

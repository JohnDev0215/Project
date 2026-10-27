package com.amazon.video.sdk.stream;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SDKPlayerLogger;
import com.amazon.video.sdk.player.FeatureConfigs;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.stream.StreamEvent;
import com.google.common.base.Optional;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamFeatureImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002:\u0002STB\u0019\b\u0000\u0012\u0006\u0010P\u001a\u00020O\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\bQ\u0010RJI\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u00002\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0016\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u00060\u001fR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u00060\"R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R \u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R \u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010'R \u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010'R \u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010'R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00101\u001a\b\u0012\u0004\u0012\u0002000,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010/R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020-0?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u001c\u0010B\u001a\b\u0012\u0004\u0012\u0002000?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010AR\u0016\u0010C\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010H\u001a\u0004\u0018\u00010E8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR0\u0010N\u001a\b\u0012\u0004\u0012\u00020-0?2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020-0?8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010M¨\u0006U"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamFeatureImpl;", "Lcom/amazon/video/sdk/stream/StreamFeature;", "", "Lcom/amazon/video/sdk/stream/StreamEvent;", "E", NotificationCompat.CATEGORY_EVENT, "", "Lcom/amazon/video/sdk/stream/EventListener;", "listenerSet", "oneTimeListenerSet", "", "onStreamEvent", "(Lcom/amazon/video/sdk/stream/StreamEvent;Ljava/util/Set;Ljava/util/Set;)V", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "onPrepared", "", "isDestroyed", "onTerminate", "changeParentView", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "timedTextConfig", "setTimedTextConfig", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "aloysiusInteractionReporter", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl$AudioTrackChangeListenerImpl;", "audioTrackChangeListener", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl$AudioTrackChangeListenerImpl;", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl$PlaybackQualityChangedEventListenerImpl;", "qualityChangeListener", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl$PlaybackQualityChangedEventListenerImpl;", "Lcom/amazon/video/sdk/stream/StreamEvent$ActiveStreamChange;", "streamChangeEventListenerSet", "Ljava/util/Set;", "streamChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/stream/StreamEvent$ActiveVariantChange;", "variantChangeEventListenerSet", "variantChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamPreferencesProvider", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "timedTextStreamPreferencesProvider", "Lcom/amazon/video/sdk/stream/AudioStreamGroupImpl;", "audioStreamGroup", "Lcom/amazon/video/sdk/stream/AudioStreamGroupImpl;", "Lcom/amazon/video/sdk/stream/VideoStreamGroupImpl;", "videoStreamGroup", "Lcom/amazon/video/sdk/stream/VideoStreamGroupImpl;", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroupImpl;", "timedTextStreamGroup", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroupImpl;", "_videoPresentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "_videoRenderingSettings", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "", "_audioStreamPreferences", "Ljava/util/List;", "_timedTextStreamPreferences", "_timedTextConfig", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "getTimedTextStreams", "()Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "timedTextStreams", "streams", "getAudioStreamPreferences", "()Ljava/util/List;", "setAudioStreamPreferences", "(Ljava/util/List;)V", "audioStreamPreferences", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "<init>", "(Lcom/amazon/video/sdk/player/PlayerConfig;Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;)V", "AudioTrackChangeListenerImpl", "PlaybackQualityChangedEventListenerImpl", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class StreamFeatureImpl implements StreamFeature {
    private List<AudioStreamMatcher> _audioStreamPreferences;
    private TimedTextConfig _timedTextConfig;
    private List<TimedTextStreamMatcher> _timedTextStreamPreferences;
    private VideoPresentation _videoPresentation;
    private VideoRenderingSettings _videoRenderingSettings;
    private final AloysiusInteractionReporter aloysiusInteractionReporter;
    private AudioStreamGroupImpl audioStreamGroup;
    private final StreamPreferencesProvider<AudioStreamMatcher> audioStreamPreferencesProvider;
    private final AudioTrackChangeListenerImpl audioTrackChangeListener;
    private final Context context;
    private final PlaybackQualityChangedEventListenerImpl qualityChangeListener;
    private final Set<EventListener<StreamEvent.ActiveStreamChange>> streamChangeEventListenerSet;
    private final Set<EventListener<StreamEvent.ActiveStreamChange>> streamChangeOneTimeEventListenerSet;
    private TimedTextStreamGroupImpl timedTextStreamGroup;
    private final StreamPreferencesProvider<TimedTextStreamMatcher> timedTextStreamPreferencesProvider;
    private final Set<EventListener<StreamEvent.ActiveVariantChange>> variantChangeEventListenerSet;
    private final Set<EventListener<StreamEvent.ActiveVariantChange>> variantChangeOneTimeEventListenerSet;
    private VideoStreamGroupImpl videoStreamGroup;

    public StreamFeatureImpl(PlayerConfig config, AloysiusInteractionReporter aloysiusInteractionReporter) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(aloysiusInteractionReporter, "aloysiusInteractionReporter");
        this.aloysiusInteractionReporter = aloysiusInteractionReporter;
        this.context = config.getContext();
        this.audioTrackChangeListener = new AudioTrackChangeListenerImpl(this);
        this.qualityChangeListener = new PlaybackQualityChangedEventListenerImpl(this);
        this.streamChangeEventListenerSet = new LinkedHashSet();
        this.streamChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.variantChangeEventListenerSet = new LinkedHashSet();
        this.variantChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.audioStreamPreferencesProvider = new StreamPreferencesProvider<AudioStreamMatcher>() { // from class: com.amazon.video.sdk.stream.StreamFeatureImpl$audioStreamPreferencesProvider$1
            @Override // com.amazon.video.sdk.stream.StreamPreferencesProvider
            public List<AudioStreamMatcher> get() {
                return this.this$0._audioStreamPreferences;
            }
        };
        this.timedTextStreamPreferencesProvider = new StreamPreferencesProvider<TimedTextStreamMatcher>() { // from class: com.amazon.video.sdk.stream.StreamFeatureImpl$timedTextStreamPreferencesProvider$1
            @Override // com.amazon.video.sdk.stream.StreamPreferencesProvider
            public List<TimedTextStreamMatcher> get() {
                return this.this$0._timedTextStreamPreferences;
            }
        };
        this.audioStreamGroup = null;
        this.videoStreamGroup = null;
        this.timedTextStreamGroup = null;
        this._videoPresentation = null;
        this._videoRenderingSettings = null;
        FeatureConfigs features = config.getFeatures();
        StreamFeatureConfig stream = features == null ? null : features.getStream();
        List<AudioStreamMatcher> preferredAudioStreams = stream == null ? null : stream.getPreferredAudioStreams();
        this._audioStreamPreferences = preferredAudioStreams == null ? CollectionsKt__CollectionsKt.emptyList() : preferredAudioStreams;
        List<TimedTextStreamMatcher> preferredTimedTextStreams = stream != null ? stream.getPreferredTimedTextStreams() : null;
        this._timedTextStreamPreferences = preferredTimedTextStreams == null ? CollectionsKt__CollectionsKt.emptyList() : preferredTimedTextStreams;
        this._timedTextConfig = new TimedTextConfigData(null, null, null, null, null, null, null, null, 255, null);
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        TimedTextStreamGroupImpl timedTextStreamGroupImpl;
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        this._videoPresentation = videoPresentation;
        this._videoRenderingSettings = videoRenderingSettings;
        videoPresentation.getPlayer().addListener(this.audioTrackChangeListener);
        videoPresentation.getPlayer().addListener(this.qualityChangeListener);
        VideoStreamGroupImpl videoStreamGroupImpl = new VideoStreamGroupImpl();
        this.videoStreamGroup = videoStreamGroupImpl;
        videoStreamGroupImpl.onPrepared(videoPresentation, videoRenderingSettings);
        AudioStreamGroupImpl audioStreamGroupImpl = new AudioStreamGroupImpl(this.audioStreamPreferencesProvider, null, 2, 0 == true ? 1 : 0);
        this.audioStreamGroup = audioStreamGroupImpl;
        audioStreamGroupImpl.onPrepared(videoPresentation, videoRenderingSettings);
        Context context = this.context;
        StreamPreferencesProvider<TimedTextStreamMatcher> streamPreferencesProvider = this.timedTextStreamPreferencesProvider;
        TimedTextConfig timedTextConfig = this._timedTextConfig;
        AudioStreamGroupImpl audioStreamGroupImpl2 = this.audioStreamGroup;
        Intrinsics.checkNotNull(audioStreamGroupImpl2);
        this.timedTextStreamGroup = new TimedTextStreamGroupImpl(context, streamPreferencesProvider, timedTextConfig, audioStreamGroupImpl2, this.aloysiusInteractionReporter, null, 32, null);
        if (videoRenderingSettings.getParentView() == null || (timedTextStreamGroupImpl = this.timedTextStreamGroup) == null) {
            return;
        }
        timedTextStreamGroupImpl.onPrepared(videoPresentation, videoRenderingSettings);
    }

    public void onTerminate(boolean isDestroyed) {
        VideoPlayer player;
        VideoPlayer player2;
        VideoPresentation videoPresentation = this._videoPresentation;
        if (videoPresentation != null && (player2 = videoPresentation.getPlayer()) != null) {
            player2.removeListener(this.audioTrackChangeListener);
        }
        VideoPresentation videoPresentation2 = this._videoPresentation;
        if (videoPresentation2 != null && (player = videoPresentation2.getPlayer()) != null) {
            player.removeListener(this.qualityChangeListener);
        }
        this._videoPresentation = null;
        AudioStreamGroupImpl audioStreamGroupImpl = this.audioStreamGroup;
        if (audioStreamGroupImpl != null) {
            audioStreamGroupImpl.onTerminate(isDestroyed);
        }
        VideoStreamGroupImpl videoStreamGroupImpl = this.videoStreamGroup;
        if (videoStreamGroupImpl != null) {
            videoStreamGroupImpl.onTerminate(isDestroyed);
        }
        TimedTextStreamGroupImpl timedTextStreamGroupImpl = this.timedTextStreamGroup;
        if (timedTextStreamGroupImpl != null) {
            timedTextStreamGroupImpl.onTerminate(isDestroyed);
        }
        this.audioStreamGroup = null;
        this.videoStreamGroup = null;
        this.timedTextStreamGroup = null;
    }

    public final void changeParentView(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        TimedTextStreamGroupImpl timedTextStreamGroupImpl = this.timedTextStreamGroup;
        if (timedTextStreamGroupImpl != null) {
            timedTextStreamGroupImpl.reset();
        }
        if (videoRenderingSettings.getParentView() != null) {
            TimedTextStreamGroupImpl timedTextStreamGroupImpl2 = this.timedTextStreamGroup;
            if (timedTextStreamGroupImpl2 != null) {
                timedTextStreamGroupImpl2.onPrepared(videoPresentation, videoRenderingSettings);
            }
            TimedTextStreamGroupImpl timedTextStreamGroupImpl3 = this.timedTextStreamGroup;
            if (timedTextStreamGroupImpl3 == null) {
                return;
            }
            timedTextStreamGroupImpl3.notifyStart();
        }
    }

    @Override // com.amazon.video.sdk.stream.StreamFeature
    public TimedTextStreamGroup getTimedTextStreams() {
        return this.timedTextStreamGroup;
    }

    @Override // com.amazon.video.sdk.stream.StreamFeature
    public List<AudioStreamMatcher> getAudioStreamPreferences() {
        return this._audioStreamPreferences;
    }

    @Override // com.amazon.video.sdk.stream.StreamFeature
    public void setTimedTextConfig(TimedTextConfig timedTextConfig) {
        Intrinsics.checkNotNullParameter(timedTextConfig, "timedTextConfig");
        SDKPlayerLogger.log("StreamFeature.setTimedTextConfig(%s)", timedTextConfig);
        this._timedTextConfig = timedTextConfig;
        TimedTextStreamGroupImpl timedTextStreamGroupImpl = this.timedTextStreamGroup;
        if (timedTextStreamGroupImpl == null) {
            return;
        }
        timedTextStreamGroupImpl.setTimedTextConfig(timedTextConfig);
    }

    /* JADX INFO: compiled from: StreamFeatureImpl.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J$\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamFeatureImpl$AudioTrackChangeListenerImpl;", "Lcom/amazon/avod/playback/AudioTrackChangeListener;", "(Lcom/amazon/video/sdk/stream/StreamFeatureImpl;)V", "onAudioTrackChangeCompleted", "", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/avod/playback/AudioTrackChangeListener$Status;", "onAudioTrackChangeStarted", "currentTrackId", "Lcom/google/common/base/Optional;", "", "desiredTrackId", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class AudioTrackChangeListenerImpl implements AudioTrackChangeListener {
        final /* synthetic */ StreamFeatureImpl this$0;

        public AudioTrackChangeListenerImpl(StreamFeatureImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeStarted(Optional<String> currentTrackId, Optional<String> desiredTrackId) {
            Intrinsics.checkNotNullParameter(currentTrackId, "currentTrackId");
            Intrinsics.checkNotNullParameter(desiredTrackId, "desiredTrackId");
            DLog.logf("Started audio track change from %s to %s", currentTrackId.orNull(), desiredTrackId.orNull());
        }

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeCompleted(AudioTrackChangeListener.Status status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (status == AudioTrackChangeListener.Status.SUCCESS) {
                DLog.logf("Audio track change complete");
                AudioStreamGroupImpl audioStreamGroupImpl = this.this$0.audioStreamGroup;
                if (audioStreamGroupImpl == null) {
                    return;
                }
                StreamFeatureImpl streamFeatureImpl = this.this$0;
                audioStreamGroupImpl.updateActiveStream();
                streamFeatureImpl.onStreamEvent(new StreamEvent.ActiveStreamChange(StreamType.Audio, audioStreamGroupImpl.getActiveStream()), streamFeatureImpl.streamChangeEventListenerSet, streamFeatureImpl.streamChangeOneTimeEventListenerSet);
                return;
            }
            DLog.warnf("Failed to change audio track change");
        }
    }

    /* JADX INFO: compiled from: StreamFeatureImpl.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J9\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00122\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamFeatureImpl$PlaybackQualityChangedEventListenerImpl;", "Lcom/amazon/avod/playback/PlaybackQualityChangedEventListener;", "(Lcom/amazon/video/sdk/stream/StreamFeatureImpl;)V", "onAudioQualityChanged", "", "bitrate", "", "audioFormat", "Lcom/amazon/avod/media/AudioFormat;", "playbackEventContext", "Lcom/amazon/avod/playback/PlaybackEventContext;", "onDynamicRangeChange", "isHdr", "", "onVideoQualityChanged", "currentResolution", "Lcom/amazon/avod/media/VideoResolution;", "availableResolutions", "", "(ILcom/amazon/avod/media/VideoResolution;[Lcom/amazon/avod/media/VideoResolution;Lcom/amazon/avod/playback/PlaybackEventContext;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackQualityChangedEventListenerImpl implements PlaybackQualityChangedEventListener {
        final /* synthetic */ StreamFeatureImpl this$0;

        @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
        public void onAudioQualityChanged(int bitrate, AudioFormat audioFormat, PlaybackEventContext playbackEventContext) {
        }

        @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
        public void onDynamicRangeChange(boolean isHdr) {
        }

        public PlaybackQualityChangedEventListenerImpl(StreamFeatureImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
        public void onVideoQualityChanged(int bitrate, VideoResolution currentResolution, VideoResolution[] availableResolutions, PlaybackEventContext playbackEventContext) {
            VideoStreamGroupImpl videoStreamGroupImpl = this.this$0.videoStreamGroup;
            if (videoStreamGroupImpl == null) {
                return;
            }
            StreamFeatureImpl streamFeatureImpl = this.this$0;
            VideoVariant activeVariant = videoStreamGroupImpl.getActiveStream().getActiveVariant();
            if (availableResolutions == null) {
                availableResolutions = new VideoResolution[0];
            }
            videoStreamGroupImpl.updateVariants(currentResolution, availableResolutions);
            VideoVariant videoVariantResolveStreamQuality = videoStreamGroupImpl.resolveStreamQuality(currentResolution);
            if (activeVariant != videoVariantResolveStreamQuality) {
                streamFeatureImpl.onStreamEvent(new StreamEvent.ActiveVariantChange(StreamType.Video, videoVariantResolveStreamQuality), streamFeatureImpl.variantChangeEventListenerSet, streamFeatureImpl.variantChangeOneTimeEventListenerSet);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <E extends StreamEvent> void onStreamEvent(E event, Set<EventListener<E>> listenerSet, Set<EventListener<E>> oneTimeListenerSet) {
        Iterator<T> it = listenerSet.iterator();
        while (it.hasNext()) {
            ((EventListener) it.next()).on(event);
        }
        Iterator<T> it2 = oneTimeListenerSet.iterator();
        while (it2.hasNext()) {
            ((EventListener) it2.next()).on(event);
        }
        oneTimeListenerSet.clear();
    }
}

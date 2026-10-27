package com.amazon.mp3.video.stories.videostoryplayback;

import ExternalActionInterface.v1_0.VideoStoryContent;
import android.content.Context;
import android.view.TextureView;
import com.amazon.mp3.video.stories.TransferSpeedManager;
import com.amazon.mp3.video.stories.VideoChangeReason;
import com.amazon.mp3.video.stories.VideoPlaybackMetricEvent;
import com.amazon.mp3.video.stories.VideoPlaybackState;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.amazon.mp3.video.stories.VideoStoryPlaybackProvider;
import com.amazon.mp3.video.stories.VideoType;
import com.amazon.mp3.video.stories.metrics.UIClickEvents;
import com.amazon.mp3.video.stories.metrics.VideoPlayerMetricsHandler;
import com.amazon.mp3.video.stories.playback.PlaybackRequest;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.metrics.mts.event.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.EntityType;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.ConnectionType;
import com.amazon.music.platform.data.NetworkInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.amazon.music.skyfire.core.template.Handlers;
import com.google.android.exoplayer2.ExoPlaybackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: VideoStoryPlayback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\t*\u0002\u0092\u0001\u0018\u00002\u00020\u0001:\u0006\u0097\u0001\u0098\u0001\u0099\u0001B\u0011\u0012\u0006\u0010V\u001a\u00020U¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J(\u0010\u0012\u001a\u00020\u00062\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00062\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0002J\u0018\u0010\u0014\u001a\u00020\u00062\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\bH\u0002J\b\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0002J\u0006\u0010\u001c\u001a\u00020\nJ\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u0006J\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010 \u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\"\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\bJ\u001e\u0010'\u001a\u00020\u00062\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0006\u0010&\u001a\u00020\nJ\u0006\u0010(\u001a\u00020\nJ\b\u0010)\u001a\u0004\u0018\u00010$J\u0006\u0010*\u001a\u00020\u0002J\u0010\u0010-\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010+J\u0006\u0010.\u001a\u00020\u0002J\u0006\u0010/\u001a\u00020\u0006J\u0010\u00101\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u00010\u000fJ\u0010\u00102\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u00010\u000fJ\u0010\u00105\u001a\u00020\u00062\b\u00104\u001a\u0004\u0018\u000103J\u0010\u00106\u001a\u00020\u00062\b\u00104\u001a\u0004\u0018\u000103J\u0006\u00107\u001a\u00020\u0006J\b\u00108\u001a\u00020\u0006H\u0016J\u0018\u0010<\u001a\u00020\u00062\u000e\u0010;\u001a\n\u0018\u000109j\u0004\u0018\u0001`:H\u0016J\u0018\u0010=\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010>\u001a\u00020\u0006H\u0016J\u0006\u0010?\u001a\u00020\u0006J\u0018\u0010C\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u00162\b\b\u0002\u0010B\u001a\u00020AJ\"\u0010I\u001a\u00020\u00062\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010AJ\b\u0010J\u001a\u00020\u0006H\u0016J\u0006\u0010K\u001a\u00020\fJ\b\u0010L\u001a\u00020\u0006H\u0016J\u0006\u0010M\u001a\u00020\nJ\u000e\u0010N\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u0016J\b\u0010O\u001a\u0004\u0018\u00010\u0016J\u000e\u0010Q\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\fJ\u0006\u0010R\u001a\u00020AJ\u000e\u0010T\u001a\u00020\u00062\u0006\u0010S\u001a\u00020\nR\u0017\u0010V\u001a\u00020U8\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR8\u0010\\\u001a&\u0012\f\u0012\n [*\u0004\u0018\u00010\u000f0\u000f [*\u0012\u0012\f\u0012\n [*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\u000e0Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R8\u0010^\u001a&\u0012\f\u0012\n [*\u0004\u0018\u00010303 [*\u0012\u0012\f\u0012\n [*\u0004\u0018\u00010303\u0018\u00010\u000e0Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010]R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010_R\u001b\u0010e\u001a\u00020`8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\bc\u0010dR\u001b\u0010j\u001a\u00020f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bg\u0010b\u001a\u0004\bh\u0010iR\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020$0k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010lR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\u0014\u0010p\u001a\u00020A8\u0002X\u0082D¢\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010t\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bt\u0010uR\u0016\u0010v\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010uR\u0018\u0010w\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010sR\u0016\u0010z\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{R\u0016\u0010|\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b|\u0010uR\u0018\u0010}\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u0016\u0010\u007f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u007f\u0010{R\u001c\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0018\u0010\u0083\u0001\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010sR\u0018\u0010\u0084\u0001\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0084\u0001\u0010sR\u0018\u0010\u0085\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0085\u0001\u0010uR\u0018\u0010\u0086\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0086\u0001\u0010uR(\u0010\u0087\u0001\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u0087\u0001\u0010{\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0018\u0010\u008c\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u008c\u0001\u0010{R\u0018\u0010\u008d\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u008d\u0001\u0010{R\u001a\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001c\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0091\u0001\u0010]R\u0018\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001¨\u0006\u009a\u0001"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "", "queuePosition", "Landroid/view/TextureView;", "getTextureView", "", "prepareQueue", "Lcom/amazon/mp3/video/stories/VideoChangeReason;", "changeReason", "", "isStoryPlayingInForeground", "", "getDurationPlayed", "", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$PositionCallback;", "callbacks", "playbackPosition", "postPlaybackPositionUpdates", "postPlaybackFinishedUpdates", "postNewPlaybackStartedUpdates", "videoChangeReason", "Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "getTerminationReason", "resetCurrentRequest", "playQueuePosition", "seekTo", "setProgress", "isPlaying", "resumePlaybackAfterConnectionRestored", PageUriUtils.DO_PLAY_VALUE, "playIfInForeground", "pause", "reason", "togglePlaying", "", "Lcom/amazon/mp3/video/stories/playback/PlaybackRequest;", "videoQueue", "firstFetch", "setVideoQueue", "isPlaybackFinished", "getCurrentRequest", "getVideoQueueSize", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$VideoScreenCallback;", "videoScreenCallback", "setVideoScreenCallback", "getDuration", "removeVideoScreenCallback", "positionCallback", "addPositionCallback", "removePositionCallback", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$ContentStateCallback;", "callback", "addContentStateCallback", "removeContentStateCallback", "shutdown", "onPlaybackStarted", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "onPlayerError", "onPlaybackPositionChanged", "onPlaybackFinished", "sendVideoStreamedMetrics", "terminationReason", "", "errorMessage", "sendVideoPlaybackTerminatedMetrics", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "storyId", "sendUIClickEvent", "onContentLoading", "getStoryBufferingTime", "onContentReady", "isRebuffering", "updateTerminationReason", "getTerminationReasonIfAvailable", "storyBufferingTime", "sendVideoStreamingRebufferingEvent", "getConnectionSource", "mute", "toggleMute", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "fragment", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "getFragment", "()Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "", "kotlin.jvm.PlatformType", "positionCallbacks", "Ljava/util/Set;", "contentStateCallbacks", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$VideoScreenCallback;", "Lcom/amazon/mp3/video/stories/metrics/VideoPlayerMetricsHandler;", "metricsHandler$delegate", "Lkotlin/Lazy;", "getMetricsHandler", "()Lcom/amazon/mp3/video/stories/metrics/VideoPlayerMetricsHandler;", "metricsHandler", "Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "audioFocusManager$delegate", "getAudioFocusManager", "()Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "audioFocusManager", "", "Ljava/util/List;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;", "videoPlayer", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;", "MEDIA_PLAYER_NAME", "Ljava/lang/String;", "currentIndex", "I", "playbackRequestedTime", "J", "playbackInitDuration", "playbackStartedTime", "Ljava/lang/Long;", "startingIndex", "newPlayback", "Z", "currentItemProgress", "currentRequest", "Lcom/amazon/mp3/video/stories/playback/PlaybackRequest;", "playbackFinished", "LExternalActionInterface/v1_0/VideoStoryContent;", "content", "LExternalActionInterface/v1_0/VideoStoryContent;", "currentQueuePosition", "currentStoryProgress", "lastPlayedDuration", "loadingStartedTime", "firstPlaybackStarted", "getFirstPlaybackStarted", "()Z", "setFirstPlaybackStarted", "(Z)V", "resetCurrentItemProgress", "isPlaybackPausedDueToError", "Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "transferSpeedManager", "Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "resetProgressTerminationReasons", "com/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$audioFocusListener$1", "audioFocusListener", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$audioFocusListener$1;", "<init>", "(Lcom/amazon/mp3/video/stories/VideoStoryFragment;)V", "ContentStateCallback", "PositionCallback", "VideoScreenCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryPlayback implements VideoPlayer.Callback {
    private final String MEDIA_PLAYER_NAME;
    private final VideoStoryPlayback$audioFocusListener$1 audioFocusListener;

    /* JADX INFO: renamed from: audioFocusManager$delegate, reason: from kotlin metadata */
    private final Lazy audioFocusManager;
    private VideoStoryContent content;
    private final Set<ContentStateCallback> contentStateCallbacks;
    private int currentIndex;
    private long currentItemProgress;
    private int currentQueuePosition;
    private PlaybackRequest currentRequest;
    private int currentStoryProgress;
    private boolean firstPlaybackStarted;
    private final VideoStoryFragment fragment;
    private boolean isPlaybackPausedDueToError;
    private long lastPlayedDuration;
    private long loadingStartedTime;

    /* JADX INFO: renamed from: metricsHandler$delegate, reason: from kotlin metadata */
    private final Lazy metricsHandler;
    private boolean newPlayback;
    private boolean playbackFinished;
    private long playbackInitDuration;
    private long playbackRequestedTime;
    private Long playbackStartedTime;
    private final Set<PositionCallback> positionCallbacks;
    private boolean resetCurrentItemProgress;
    private final Set<TerminationReason> resetProgressTerminationReasons;
    private int startingIndex;
    private TransferSpeedManager transferSpeedManager;
    private final VideoPlayerImpl videoPlayer;
    private List<PlaybackRequest> videoQueue;
    private VideoScreenCallback videoScreenCallback;

    /* JADX INFO: compiled from: VideoStoryPlayback.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$ContentStateCallback;", "", "onContentLoading", "", "onContentReady", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ContentStateCallback {
        void onContentLoading();

        void onContentReady();
    }

    /* JADX INFO: compiled from: VideoStoryPlayback.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$PositionCallback;", "", "onNewPlaybackStarted", "", "onPlaybackFinished", "onPlaybackPositionChanged", "queuePosition", "", "playbackPosition", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface PositionCallback {
        void onNewPlaybackStarted();

        void onPlaybackFinished();

        void onPlaybackPositionChanged(int queuePosition, long playbackPosition);
    }

    /* JADX INFO: compiled from: VideoStoryPlayback.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$VideoScreenCallback;", "", "getTextureView", "Landroid/view/TextureView;", "queuePosition", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface VideoScreenCallback {
        TextureView getTextureView(int queuePosition);
    }

    /* JADX INFO: compiled from: VideoStoryPlayback.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoChangeReason.values().length];
            iArr[VideoChangeReason.PLAYBACK_INITIATED.ordinal()] = 1;
            iArr[VideoChangeReason.VIDEO_FINISHED.ordinal()] = 2;
            iArr[VideoChangeReason.PLAY_PAUSE.ordinal()] = 3;
            iArr[VideoChangeReason.CLOSE_PLAYER.ordinal()] = 4;
            iArr[VideoChangeReason.NEXT.ordinal()] = 5;
            iArr[VideoChangeReason.PREV.ordinal()] = 6;
            iArr[VideoChangeReason.ENTER_BACKGROUND.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$audioFocusListener$1, com.amazon.music.media.playback.impl.AudioFocusManager$Listener] */
    public VideoStoryPlayback(VideoStoryFragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
        this.positionCallbacks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.contentStateCallbacks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.metricsHandler = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlayerMetricsHandler>() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$metricsHandler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPlayerMetricsHandler invoke() {
                Context contextRequireContext = this.this$0.getFragment().requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "fragment.requireContext()");
                return new VideoPlayerMetricsHandler(contextRequireContext);
            }
        });
        this.audioFocusManager = LazyKt__LazyJVMKt.lazy(new Function0<AudioFocusManager>() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$audioFocusManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AudioFocusManager invoke() {
                return new AudioFocusManager(this.this$0.getFragment().requireContext());
            }
        });
        this.videoQueue = new ArrayList();
        VideoStoryPlaybackProvider videoStoryPlaybackProvider = VideoStoryPlaybackProvider.INSTANCE;
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "fragment.requireContext()");
        VideoPlayerImpl videoPlayerImpl = (VideoPlayerImpl) videoStoryPlaybackProvider.getPlayer(contextRequireContext, this);
        this.videoPlayer = videoPlayerImpl;
        this.MEDIA_PLAYER_NAME = "EXO_PLAYER";
        this.currentIndex = -1;
        this.newPlayback = true;
        this.currentQueuePosition = -1;
        TransferSpeedManager.Companion companion = TransferSpeedManager.INSTANCE;
        Context contextRequireContext2 = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "fragment.requireContext()");
        this.transferSpeedManager = companion.get(contextRequireContext2, videoPlayerImpl);
        this.resetProgressTerminationReasons = SetsKt__SetsKt.setOf((Object[]) new TerminationReason[]{TerminationReason.USER_NEXT, TerminationReason.USER_PREV, TerminationReason.TRACK_FINISHED});
        ?? r6 = new AudioFocusManager.Listener() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$audioFocusListener$1
            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemDuck() {
                this.this$0.pause(VideoChangeReason.ENTER_BACKGROUND);
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemNormal() {
                PlaybackRequest currentRequest = this.this$0.getCurrentRequest();
                if ((currentRequest == null ? null : currentRequest.getPlaybackStatus()) != VideoPlaybackState.PAUSED) {
                    this.this$0.playIfInForeground();
                }
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemPause() {
                this.this$0.pause(VideoChangeReason.ENTER_BACKGROUND);
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemStop() {
                PlaybackRequest currentRequest = this.this$0.getCurrentRequest();
                if (currentRequest != null) {
                    currentRequest.setPlaybackStatus(VideoPlaybackState.PAUSED);
                }
                this.this$0.pause(VideoChangeReason.ENTER_BACKGROUND);
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onAudioFocusChanged(boolean hasAudioFocus) {
                this.this$0.pause(VideoChangeReason.ENTER_BACKGROUND);
            }
        };
        this.audioFocusListener = r6;
        getAudioFocusManager().setLocalPlayback(true);
        getAudioFocusManager().setAudioWillPlay(true);
        getAudioFocusManager().addListener(r6);
        this.loadingStartedTime = System.currentTimeMillis();
        this.playbackRequestedTime = System.currentTimeMillis();
        videoPlayerImpl.pause();
        toggleMute(false);
    }

    public final VideoStoryFragment getFragment() {
        return this.fragment;
    }

    private final VideoPlayerMetricsHandler getMetricsHandler() {
        return (VideoPlayerMetricsHandler) this.metricsHandler.getValue();
    }

    private final AudioFocusManager getAudioFocusManager() {
        return (AudioFocusManager) this.audioFocusManager.getValue();
    }

    public final boolean getFirstPlaybackStarted() {
        return this.firstPlaybackStarted;
    }

    public final void setFirstPlaybackStarted(boolean z) {
        this.firstPlaybackStarted = z;
    }

    public final void playQueuePosition(int queuePosition, VideoChangeReason changeReason) {
        String strUri;
        if (getAudioFocusManager().requestAudioFocus()) {
            if (changeReason == VideoChangeReason.NEXT || changeReason == VideoChangeReason.PREV) {
                if (isRebuffering()) {
                    sendVideoStreamingRebufferingEvent(getStoryBufferingTime());
                }
                PlaybackRequest currentRequest = getCurrentRequest();
                if ((currentRequest == null ? null : currentRequest.getPlaybackStatus()) != VideoPlaybackState.PAUSED) {
                    sendVideoPlaybackTerminatedMetrics(getTerminationReason(changeReason), "");
                }
                sendVideoStreamedMetrics();
            }
            this.playbackStartedTime = null;
            this.playbackRequestedTime = System.currentTimeMillis();
            List<PlaybackRequest> list = this.videoQueue;
            PlaybackRequest playbackRequest = list == null ? null : (PlaybackRequest) CollectionsKt___CollectionsKt.getOrNull(list, queuePosition);
            this.currentRequest = playbackRequest;
            this.currentQueuePosition = queuePosition;
            this.content = playbackRequest == null ? null : playbackRequest.getContent();
            this.newPlayback = true;
            this.currentIndex = queuePosition;
            resetCurrentRequest();
            VideoStoryContent videoStoryContent = this.content;
            if (videoStoryContent == null || (strUri = videoStoryContent.uri()) == null) {
                return;
            }
            this.videoPlayer.setContentView(null);
            this.videoPlayer.prepareQueueItem(queuePosition, strUri);
            this.videoPlayer.setContentView(getTextureView(queuePosition));
        }
    }

    public final void setProgress(int seekTo) {
        this.currentStoryProgress = seekTo;
    }

    public final boolean isPlaying() {
        return this.videoPlayer.isPlaying();
    }

    public final void resumePlaybackAfterConnectionRestored() {
        String strUri;
        VideoStoryContent videoStoryContent = this.content;
        if (videoStoryContent == null || (strUri = videoStoryContent.uri()) == null) {
            return;
        }
        this.videoPlayer.setContentView(null);
        this.videoPlayer.prepareQueueItem(this.currentQueuePosition, strUri);
        this.videoPlayer.setContentView(getTextureView(this.currentQueuePosition));
        this.videoPlayer.seekPosition(this.currentStoryProgress);
        this.playbackRequestedTime = System.currentTimeMillis();
        PlaybackRequest playbackRequest = this.currentRequest;
        if (playbackRequest != null) {
            playbackRequest.setResumeReason(ResumeReason.SYSTEM_START);
        }
        if (isPlaying()) {
            play();
        }
    }

    public final void play() {
        if (getAudioFocusManager().requestAudioFocus()) {
            this.videoPlayer.play();
        }
    }

    public final void playIfInForeground() {
        if (this.fragment.isActivityInForeground()) {
            play();
        }
    }

    public final void pause(VideoChangeReason changeReason) {
        Intrinsics.checkNotNullParameter(changeReason, "changeReason");
        if (isStoryPlayingInForeground(changeReason)) {
            sendVideoPlaybackTerminatedMetrics(getTerminationReason(changeReason), "");
        }
        this.videoPlayer.pause();
    }

    public final void togglePlaying(VideoChangeReason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (isPlaying()) {
            if (reason == VideoChangeReason.PLAY_PAUSE) {
                sendUIClickEvent$default(this, ActionType.PAUSE, InteractionType.TAP, null, 4, null);
            }
            sendVideoPlaybackTerminatedMetrics(TerminationReason.USER_STOP, "");
            this.videoPlayer.pause();
            this.fragment.setApiDelay(0L);
            PlaybackRequest playbackRequest = this.currentRequest;
            if (playbackRequest == null) {
                return;
            }
            playbackRequest.setPlaybackStatus(VideoPlaybackState.PAUSED);
            return;
        }
        if (reason == VideoChangeReason.PLAY_PAUSE) {
            sendUIClickEvent$default(this, ActionType.PLAY, InteractionType.TAP, null, 4, null);
        }
        if (getAudioFocusManager().requestAudioFocus()) {
            PlaybackRequest playbackRequest2 = this.currentRequest;
            if (playbackRequest2 != null) {
                playbackRequest2.setResumeReason(ResumeReason.USER_START);
            }
            this.playbackRequestedTime = System.currentTimeMillis();
            play();
            PlaybackRequest playbackRequest3 = this.currentRequest;
            if (playbackRequest3 == null) {
                return;
            }
            playbackRequest3.setPlaybackStatus(VideoPlaybackState.PLAYING);
        }
    }

    private final TextureView getTextureView(int queuePosition) {
        VideoScreenCallback videoScreenCallback = this.videoScreenCallback;
        if (videoScreenCallback == null) {
            return null;
        }
        return videoScreenCallback.getTextureView(queuePosition);
    }

    public final void setVideoQueue(List<PlaybackRequest> videoQueue, boolean firstFetch) {
        if (videoQueue != null) {
            this.videoQueue.addAll(videoQueue);
        }
        if (firstFetch) {
            prepareQueue();
        }
    }

    private final void prepareQueue() {
        String strUri;
        this.playbackRequestedTime = System.currentTimeMillis();
        this.newPlayback = true;
        List<PlaybackRequest> list = this.videoQueue;
        PlaybackRequest playbackRequest = list == null ? null : (PlaybackRequest) CollectionsKt___CollectionsKt.getOrNull(list, this.startingIndex);
        this.currentRequest = playbackRequest;
        VideoStoryContent content = playbackRequest != null ? playbackRequest.getContent() : null;
        if (content == null || (strUri = content.uri()) == null) {
            return;
        }
        this.videoPlayer.prepareQueueItem(this.startingIndex, strUri);
    }

    /* JADX INFO: renamed from: isPlaybackFinished, reason: from getter */
    public final boolean getPlaybackFinished() {
        return this.playbackFinished;
    }

    public final PlaybackRequest getCurrentRequest() {
        List<PlaybackRequest> list = this.videoQueue;
        if (list == null) {
            return null;
        }
        return (PlaybackRequest) CollectionsKt___CollectionsKt.getOrNull(list, this.currentIndex);
    }

    public final int getVideoQueueSize() {
        return this.videoQueue.size();
    }

    public final void setVideoScreenCallback(VideoScreenCallback videoScreenCallback) {
        this.videoScreenCallback = videoScreenCallback;
    }

    public final int getDuration() {
        return this.videoPlayer.getDuration();
    }

    public final void removeVideoScreenCallback() {
        this.videoScreenCallback = null;
    }

    public final void addPositionCallback(PositionCallback positionCallback) {
        this.positionCallbacks.add(positionCallback);
    }

    public final void removePositionCallback(PositionCallback positionCallback) {
        if (positionCallback == null) {
            return;
        }
        this.positionCallbacks.remove(positionCallback);
    }

    public final void addContentStateCallback(ContentStateCallback callback) {
        this.contentStateCallbacks.add(callback);
    }

    public final void removeContentStateCallback(ContentStateCallback callback) {
        if (callback == null) {
            return;
        }
        this.contentStateCallbacks.remove(callback);
    }

    public final void shutdown() {
        this.videoPlayer.removeCallback();
        this.videoPlayer.shutdown();
        getAudioFocusManager().removeListener(this.audioFocusListener);
        getAudioFocusManager().abandonAudioFocus();
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onPlaybackStarted() {
        long jCurrentTimeMillis = (System.currentTimeMillis() - this.playbackRequestedTime) + this.fragment.getApiDelay() + this.playbackInitDuration;
        this.fragment.setApiDelay(0L);
        this.playbackInitDuration = 0L;
        this.playbackStartedTime = Long.valueOf(System.currentTimeMillis());
        PlaybackRequest playbackRequest = this.currentRequest;
        if (playbackRequest != null) {
            playbackRequest.setPlaybackStatus(VideoPlaybackState.PLAYING);
        }
        if (this.newPlayback) {
            this.newPlayback = false;
            this.playbackFinished = false;
            Set<PositionCallback> positionCallbacks = this.positionCallbacks;
            Intrinsics.checkNotNullExpressionValue(positionCallbacks, "positionCallbacks");
            postNewPlaybackStartedUpdates(positionCallbacks);
            PlaybackRequest playbackRequest2 = this.currentRequest;
            if (playbackRequest2 == null) {
                return;
            }
            getMetricsHandler().sendVideoStreamingInitiatedEvent(VideoPlaybackMetricEvent.VIDEO_STREAMING_INITIATED.getMetricValue(), playbackRequest2, jCurrentTimeMillis, VideoType.VIDEO_STORY, this.MEDIA_PLAYER_NAME);
            return;
        }
        PlaybackRequest playbackRequest3 = this.currentRequest;
        if (playbackRequest3 == null) {
            return;
        }
        VideoPlayerMetricsHandler metricsHandler = getMetricsHandler();
        String metricValue = VideoPlaybackMetricEvent.VIDEO_STREAMING_RESUMED.getMetricValue();
        ResumeReason resumeReason = playbackRequest3.getResumeReason();
        if (resumeReason == null) {
            resumeReason = ResumeReason.UNKNOWN_START;
        }
        metricsHandler.sendVideoPlaybackResumedEvent(metricValue, playbackRequest3, resumeReason, jCurrentTimeMillis, this.currentItemProgress, VideoType.VIDEO_STORY);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onPlayerError(Exception exception) {
        if (exception instanceof ExoPlaybackException) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error code ");
            sb.append(Integer.valueOf(((ExoPlaybackException) exception).type));
            sb.append(": ");
            Throwable cause = exception.getCause();
            sb.append((Object) (cause == null ? null : cause.getMessage()));
            sendVideoPlaybackTerminatedMetrics(TerminationReason.ERROR, sb.toString());
            sendVideoStreamedMetrics();
            this.isPlaybackPausedDueToError = true;
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onPlaybackPositionChanged(int queuePosition, long playbackPosition) {
        this.currentItemProgress = playbackPosition;
        Set<PositionCallback> positionCallbacks = this.positionCallbacks;
        Intrinsics.checkNotNullExpressionValue(positionCallbacks, "positionCallbacks");
        postPlaybackPositionUpdates(positionCallbacks, queuePosition, playbackPosition);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onPlaybackFinished() {
        this.playbackFinished = true;
        Set<PositionCallback> positionCallbacks = this.positionCallbacks;
        Intrinsics.checkNotNullExpressionValue(positionCallbacks, "positionCallbacks");
        postPlaybackFinishedUpdates(positionCallbacks);
    }

    public final void sendVideoStreamedMetrics() {
        if (getDurationPlayed() == 0) {
            return;
        }
        PlaybackRequest playbackRequest = this.currentRequest;
        if (playbackRequest != null) {
            getMetricsHandler().sendVideoPlaybackCompletedEvent(VideoPlaybackMetricEvent.VIDEO_STREAMED.getMetricValue(), playbackRequest, this.currentItemProgress, getDurationPlayed(), this.videoPlayer.getDuration(), VideoType.VIDEO_STORY, this.MEDIA_PLAYER_NAME);
        }
        if (this.resetCurrentItemProgress) {
            this.currentItemProgress = 0L;
            this.resetCurrentItemProgress = false;
        }
    }

    public static /* synthetic */ void sendVideoPlaybackTerminatedMetrics$default(VideoStoryPlayback videoStoryPlayback, TerminationReason terminationReason, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        videoStoryPlayback.sendVideoPlaybackTerminatedMetrics(terminationReason, str);
    }

    public final void sendVideoPlaybackTerminatedMetrics(TerminationReason terminationReason, String errorMessage) {
        Intrinsics.checkNotNullParameter(terminationReason, "terminationReason");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        PlaybackRequest currentPlaybackRequest = this.currentRequest;
        if (currentPlaybackRequest == null) {
            currentPlaybackRequest = this.fragment.getCurrentPlaybackRequest();
        }
        PlaybackRequest playbackRequest = currentPlaybackRequest;
        if (playbackRequest != null) {
            getMetricsHandler().sendVideoPlaybackTerminated(VideoPlaybackMetricEvent.VIDEO_STREAMING_TERMINATED.getMetricValue(), getStoryBufferingTime(), this.currentItemProgress, getDurationPlayed(), this.videoPlayer.getDuration(), errorMessage, terminationReason, playbackRequest, VideoType.VIDEO_STORY, this.MEDIA_PLAYER_NAME);
        }
        if (this.resetProgressTerminationReasons.contains(terminationReason)) {
            this.resetCurrentItemProgress = true;
        }
    }

    public static /* synthetic */ void sendUIClickEvent$default(VideoStoryPlayback videoStoryPlayback, ActionType actionType, InteractionType interactionType, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        videoStoryPlayback.sendUIClickEvent(actionType, interactionType, str);
    }

    public final void sendUIClickEvent(ActionType actionType, InteractionType interactionType, String storyId) {
        String asin;
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        PlaybackRequest currentRequest = getCurrentRequest();
        String str = (currentRequest == null || (asin = currentRequest.getAsin()) == null) ? storyId : asin;
        if (str == null) {
            return;
        }
        UIClickEvents.INSTANCE.sendUIClickMetrics(actionType, interactionType, PageType.NOW_PLAYING_VIDEO_STORY.name(), PageSubType.UNAVAILABLE_UNAVAILABLE.name(), str, EntityType.VIDEO.name(), EntityIdType.ASIN.name());
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onContentLoading() {
        this.playbackInitDuration = System.currentTimeMillis() - this.playbackRequestedTime;
        this.playbackRequestedTime = System.currentTimeMillis();
        Iterator<ContentStateCallback> it = this.contentStateCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onContentLoading();
        }
        if (!this.videoPlayer.getIsBuffering()) {
            this.transferSpeedManager.setBufferStartTime(this.playbackRequestedTime);
        }
        this.videoPlayer.setBuffering(true);
    }

    public final long getStoryBufferingTime() {
        if (this.videoPlayer.getIsBuffering()) {
            return System.currentTimeMillis() - this.playbackRequestedTime;
        }
        return 0L;
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
    public void onContentReady() {
        if (isRebuffering()) {
            sendVideoStreamingRebufferingEvent(getStoryBufferingTime());
        }
        Iterator<ContentStateCallback> it = this.contentStateCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onContentReady();
        }
        this.isPlaybackPausedDueToError = false;
        this.videoPlayer.setBuffering(false);
        this.transferSpeedManager.getTransferSpeeds().clear();
    }

    public final boolean isRebuffering() {
        return this.videoPlayer.getIsBuffering() && this.playbackStartedTime != null;
    }

    private final boolean isStoryPlayingInForeground(VideoChangeReason changeReason) {
        return (changeReason == VideoChangeReason.APP_IN_BACKGOUND || this.playbackFinished || !isPlaying()) ? false : true;
    }

    private final long getDurationPlayed() {
        PlaybackRequest currentRequest = getCurrentRequest();
        if ((currentRequest == null ? null : currentRequest.getPlaybackStatus()) == VideoPlaybackState.PAUSED || this.isPlaybackPausedDueToError) {
            return this.lastPlayedDuration;
        }
        Long l = this.playbackStartedTime;
        long jCurrentTimeMillis = l == null ? 0L : System.currentTimeMillis() - l.longValue();
        this.lastPlayedDuration = jCurrentTimeMillis;
        return jCurrentTimeMillis;
    }

    public final void updateTerminationReason(TerminationReason terminationReason) {
        Intrinsics.checkNotNullParameter(terminationReason, "terminationReason");
        PlaybackRequest currentRequest = getCurrentRequest();
        if (currentRequest == null) {
            return;
        }
        currentRequest.setTerminationReason(terminationReason);
    }

    public final TerminationReason getTerminationReasonIfAvailable() {
        PlaybackRequest currentRequest = getCurrentRequest();
        if (currentRequest == null) {
            return null;
        }
        return currentRequest.getTerminationReason();
    }

    private final void postPlaybackPositionUpdates(final Set<? extends PositionCallback> callbacks, final int queuePosition, final long playbackPosition) {
        Handlers.getForeground().post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                VideoStoryPlayback.m1701postPlaybackPositionUpdates$lambda12(callbacks, queuePosition, playbackPosition);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postPlaybackPositionUpdates$lambda-12, reason: not valid java name */
    public static final void m1701postPlaybackPositionUpdates$lambda12(Set callbacks, int i, long j) {
        Intrinsics.checkNotNullParameter(callbacks, "$callbacks");
        Iterator it = callbacks.iterator();
        while (it.hasNext()) {
            PositionCallback positionCallback = (PositionCallback) it.next();
            if (positionCallback != null) {
                positionCallback.onPlaybackPositionChanged(i, j);
            }
        }
    }

    private final void postPlaybackFinishedUpdates(final Set<? extends PositionCallback> callbacks) {
        Handlers.getForeground().post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VideoStoryPlayback.m1700postPlaybackFinishedUpdates$lambda13(callbacks);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postPlaybackFinishedUpdates$lambda-13, reason: not valid java name */
    public static final void m1700postPlaybackFinishedUpdates$lambda13(Set callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "$callbacks");
        Iterator it = callbacks.iterator();
        while (it.hasNext()) {
            PositionCallback positionCallback = (PositionCallback) it.next();
            if (positionCallback != null) {
                positionCallback.onPlaybackFinished();
            }
        }
    }

    private final void postNewPlaybackStartedUpdates(final Set<? extends PositionCallback> callbacks) {
        Handlers.getForeground().post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoStoryPlayback.m1699postNewPlaybackStartedUpdates$lambda15(callbacks);
            }
        });
    }

    private final TerminationReason getTerminationReason(VideoChangeReason videoChangeReason) {
        switch (WhenMappings.$EnumSwitchMapping$0[videoChangeReason.ordinal()]) {
            case 1:
            case 2:
                return TerminationReason.TRACK_FINISHED;
            case 3:
            case 4:
                return TerminationReason.USER_STOP;
            case 5:
                return TerminationReason.USER_NEXT;
            case 6:
                return TerminationReason.USER_PREV;
            case 7:
                return TerminationReason.USER_STOP;
            default:
                return TerminationReason.SYSTEM_STOP;
        }
    }

    private final void resetCurrentRequest() {
        PlaybackRequest playbackRequest = this.currentRequest;
        if (playbackRequest == null) {
            return;
        }
        playbackRequest.setPlaybackStatus(null);
        playbackRequest.setResumeReason(null);
        playbackRequest.setTerminationReason(null);
    }

    public final void sendVideoStreamingRebufferingEvent(long storyBufferingTime) {
        PlaybackRequest currentPlaybackRequest = this.currentRequest;
        if (currentPlaybackRequest == null) {
            currentPlaybackRequest = this.fragment.getCurrentPlaybackRequest();
        }
        PlaybackRequest playbackRequest = currentPlaybackRequest;
        if (playbackRequest == null) {
            return;
        }
        getMetricsHandler().sendVideoRebufferingRequiredEvent(storyBufferingTime, this.currentItemProgress, this.videoPlayer.getDuration(), playbackRequest, this.MEDIA_PLAYER_NAME, this.transferSpeedManager.calculateAverageSpeedOverBufferingTime(), getConnectionSource(), VideoType.VIDEO_STORY);
    }

    public final String getConnectionSource() {
        NetworkInfo networkInfo;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        ConnectionType connectionType = null;
        if (!(interfaceProvider instanceof NetworkInfoProvider)) {
            interfaceProvider = null;
        }
        NetworkInfoProvider networkInfoProvider = (NetworkInfoProvider) interfaceProvider;
        if (networkInfoProvider != null && (networkInfo = networkInfoProvider.getNetworkInfo()) != null) {
            connectionType = networkInfo.getConnectionType();
        }
        return Intrinsics.areEqual(connectionType, ConnectionType.WiFiConnection.INSTANCE) ? "WIFI" : "UNKNOWN";
    }

    public final void toggleMute(boolean mute) {
        this.videoPlayer.toggleMute(mute);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postNewPlaybackStartedUpdates$lambda-15, reason: not valid java name */
    public static final void m1699postNewPlaybackStartedUpdates$lambda15(Set callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "$callbacks");
        Iterator it = callbacks.iterator();
        while (it.hasNext()) {
            PositionCallback positionCallback = (PositionCallback) it.next();
            if (positionCallback != null) {
                positionCallback.onNewPlaybackStarted();
            }
        }
    }
}

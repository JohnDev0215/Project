package com.amazon.podcast.media.playback;

import PlaybackInterface.v1_0.AudioStreamFormat;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.ShareElement;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethod;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientState;
import Podcast.PlaybackInterface.v1_0.MediaEventsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethod;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethod;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResetMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethod;
import Podcast.PlaybackInterface.v1_0.SetMediaMethod;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethod;
import Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethod;
import Podcast.PlaybackInterface.v1_0.SetTranscriptsMethod;
import Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethod;
import SOACoreInterface.v1_0.Method;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.media.playback.OnMediaButtonListener;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.voice.MediaSessionStateProvider;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.NavigationHandler;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.UserPreferences;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.cast.ChromecastMediaProvider;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.skills.PlayDownloadedEpisodesMethods;
import com.amazon.podcast.skills.RestoreDownloadedEpisodesPlaybackSkill;
import com.amazon.podcast.transcription.TranscriptProvider;
import com.amazon.scrublib.TranscriptImplementation;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.amazon.scrublib.models.TranscriptUpdatedListener;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class Playback implements MethodCallback, MethodsDispatcher, CastingConnectionCallback {
    private static Set<DismissFullScreenTranscriptCallback> dismissFullScreenTranscriptCallbacks;
    private static final Logger logger = LoggerFactory.getLogger("PodcastPlayback");
    private Context context;
    private int currentTime;
    private final Engine engine;
    private EpisodeOptionsElement episodeOptionsElement;
    private String exoplayerLocalizedException;
    private boolean finishedEpisodeOnNext;
    private boolean hasAudioFocus;
    private boolean hasFetchedTranscript;
    private boolean ignoreRestorePlaybackMethods;
    private String ingressSource;
    private boolean isRestoringPlayback;
    private boolean isSeekBarTracking;
    private boolean isTranscriptLoaded;
    private boolean isTranscriptSyncing;
    private LiveData<Bookmark> liveData;
    private MediaBrowserCompat mediaBrowser;
    private MediaControllerCompat mediaController;
    private final Object methodQueueLock;
    private boolean playUntilEndOfEpisode;
    private final Set<PlaybackFinishedCallback> playbackFinishedCallbacks;
    private PlaybackPreferences playbackPreferences;
    private PlaybackSpeed playbackSpeed;
    private int previousPlaybackState;
    private String restoreToZeroMediaId;
    private List<ScrubSegment> scrubSegments;
    private SetMediaMethodCallback setMediaMethodCallback;
    private boolean sleepTimerOn;
    private long sleepTimerRemainingDuration;
    private String streamType;
    private TranscriptImplementation transcriptImplementation;
    private List<ScrubSegment> updatedScrubSegment;
    private boolean wasPreviousStateFinished;
    private final String SEC_GPC_HEADER_NAME = "xh-Sec-GPC";
    private Long playOffsetMilliseconds = 0L;
    private boolean isInitialized = false;
    private Long rebufferStartedAt = null;
    private Long rebufferEndedAt = null;
    private boolean shouldShowMiniPlayerInApp = true;
    private boolean shouldDispatchOnStoppedMethods = true;
    private int bitesExperienceStartingIndex = 0;
    private String PODCAST_SCHEMA = "podcast://";
    private String HTTPS_SCHEMA_REGEX = "(?i)https?://";
    private String DOWNLOADED_PODCAST_SCHEMA = "podcast-downloaded://";
    private String HLS_PODCAST_SCHEMA = "podcast-hls://";
    private final Observer<Bookmark> observer = new Observer<Bookmark>() { // from class: com.amazon.podcast.media.playback.Playback.1
        @Override // androidx.lifecycle.Observer
        public void onChanged(final Bookmark bookmark) {
            if (Playback.this.media == null || bookmark == null || !bookmark.getId().equals(Playback.this.media.mediaId())) {
                return;
            }
            long jAbs = Math.abs(bookmark.getProgressMilliseconds() - Playback.this.position);
            if (Playback.this.getPlaybackState() != 2 || jAbs <= 300) {
                return;
            }
            Playback.this.updatePlayerProgress(Long.valueOf(bookmark.getProgressMilliseconds()));
        }
    };
    public OnMediaButtonListener mediaButtonListener = new OnMediaButtonListener() { // from class: com.amazon.podcast.media.playback.Playback.2
        @Override // com.amazon.music.media.playback.OnMediaButtonListener
        public void skipNext() {
            Playback.this.next();
        }

        @Override // com.amazon.music.media.playback.OnMediaButtonListener
        public void skipPrevious() {
            Playback.this.previous();
        }
    };
    private TranscriptUpdatedListener transcriptUpdatedListener = new TranscriptUpdatedListener() { // from class: com.amazon.podcast.media.playback.Playback.13
        @Override // com.amazon.scrublib.models.TranscriptUpdatedListener
        public void onTranscriptUpdated(final List<ScrubSegment> scrubSegmentList) {
            Playback.logger.debug("onTranscriptUpdated");
            Iterator<TranscriptionStatusUpdateCallback> it = Playback.this.transcriptionStatusUpdateCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTranscriptUpdated(scrubSegmentList);
            }
        }

        @Override // com.amazon.scrublib.models.TranscriptUpdatedListener
        public void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType) {
            Playback.logger.debug("onTranscriptLoading");
            Playback.this.setIsTranscriptLoaded(Boolean.FALSE);
            Iterator<TranscriptionStatusUpdateCallback> it = Playback.this.transcriptionStatusUpdateCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTranscriptLoading(transcriptLoadingType);
            }
        }

        @Override // com.amazon.scrublib.models.TranscriptUpdatedListener
        public void onTranscriptLoaded() {
            Playback.logger.debug("onTranscriptLoaded");
            Playback.this.setIsTranscriptLoaded(Boolean.TRUE);
            Iterator<TranscriptionStatusUpdateCallback> it = Playback.this.transcriptionStatusUpdateCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTranscriptLoaded();
            }
        }
    };
    private final Media media = new Media();
    private final PlaybackCapabilities playbackCapabilities = new PlaybackCapabilities(this);
    private final Queue<Method> methodQueue = new ArrayDeque();
    private long position = 0;
    private long bufferedPosition = 0;
    private long duration = 0;
    private boolean isCastingFromPodcast = false;
    private final Set<PositionCallback> positionCallbacks = new CopyOnWriteArraySet();
    private final Set<BufferedPositionCallback> bufferedPositionCallbacks = new CopyOnWriteArraySet();
    private final Set<StateCallback> stateCallbacks = new CopyOnWriteArraySet();
    private final Set<SpeedCallback> playbackSpeedCallbacks = new CopyOnWriteArraySet();
    private final Set<DurationCallback> durationCallbacks = new CopyOnWriteArraySet();
    private final Set<SleepTimerOnOffCallback> sleepTimerOnOffCallbacks = new CopyOnWriteArraySet();
    private final Set<SleepTimerDurationCallback> sleepTimerDurationCallbacks = new CopyOnWriteArraySet();
    public final Set<TranscriptionStatusUpdateCallback> transcriptionStatusUpdateCallbacks = new CopyOnWriteArraySet();
    private final Set<TranscriptsCallback> transcriptsCallbacks = new CopyOnWriteArraySet();
    private final Set<PlaybackEventCallback> playbackEventCallbacks = new CopyOnWriteArraySet();

    public interface BufferedPositionCallback {
        void onBufferedPositionChange(final long position);
    }

    public interface DismissFullScreenTranscriptCallback {
        void onDismissTranscriptView();
    }

    public interface DurationCallback {
        void onPlaybackDurationChange(final long duration);
    }

    public interface PlaybackEventCallback {
        void onPlaybackEventReceived(final String playbackEvent);
    }

    public interface PlaybackFinishedCallback {
        void onPlaybackFinished();
    }

    public interface PositionCallback {
        void onPositionChange(final long position);
    }

    public interface SetMediaMethodCallback {
        void onSetMediaMethodCalled(final EpisodeOptionsElement episodeOptionsElement);
    }

    public interface SleepTimerDurationCallback {
        void onSleepTimerDurationChange(final long duration);
    }

    public interface SleepTimerOnOffCallback {
        void onSleepTimerOff();

        void onSleepTimerOn();
    }

    public interface SpeedCallback {
        void onPlaybackSpeedChange(final PlaybackSpeed speed);
    }

    public interface StateCallback {
        void onPlaybackStateChange(final int state);
    }

    public interface TranscriptionStatusUpdateCallback {
        void onTranscriptLoaded();

        void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType);

        void onTranscriptUpdated(final List<ScrubSegment> scrubSegments);
    }

    public interface TranscriptsCallback {
        void onTranscriptFetched(final List<ScrubSegment> scrubSegments);
    }

    private long playbackActions() {
        return 895L;
    }

    @Override // com.amazon.music.casting.session.CastingConnectionCallback
    public void onConnected(final CastingDevice castingDevice) {
        this.isCastingFromPodcast = true;
        if (getPlaybackState() != 3 || ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
            return;
        }
        ChromecastMediaProvider.loadTrackOnChromecast();
    }

    @Override // com.amazon.music.casting.session.CastingConnectionCallback
    public void onDisconnected(TerminationReason terminationReason) {
        this.isCastingFromPodcast = false;
        ChromecastMediaProvider.setTrackLoadedOnChromecast(false);
    }

    @Override // com.amazon.music.casting.session.CastingConnectionCallback
    public void onError(CastingException e) {
        this.isCastingFromPodcast = false;
        ChromecastMediaProvider.setTrackLoadedOnChromecast(false);
    }

    public Playback(final Engine engine) {
        this.engine = engine;
        dismissFullScreenTranscriptCallbacks = new CopyOnWriteArraySet();
        this.playbackFinishedCallbacks = new CopyOnWriteArraySet();
        this.setMediaMethodCallback = null;
        this.methodQueueLock = new Object();
        this.previousPlaybackState = 0;
        this.playbackSpeed = getPlaybackSpeed();
        this.playUntilEndOfEpisode = false;
        this.finishedEpisodeOnNext = false;
        this.hasAudioFocus = true;
        HashSet hashSet = new HashSet();
        hashSet.add(ClearMediaMethod.class.getCanonicalName());
        hashSet.add(SetMediaMethod.class.getCanonicalName());
        hashSet.add(BufferMediaMethod.class.getCanonicalName());
        hashSet.add(PlayMediaMethod.class.getCanonicalName());
        hashSet.add(ResumeMediaMethod.class.getCanonicalName());
        hashSet.add(PauseMediaMethod.class.getCanonicalName());
        hashSet.add(SetMediaOffsetMethod.class.getCanonicalName());
        hashSet.add(NavigateToPodcastMethod.class.getCanonicalName());
        hashSet.add(SetWriteAndReportFrequenciesMethod.class.getCanonicalName());
        hashSet.add(SetTranscriptsMethod.class.getCanonicalName());
        hashSet.add(PlayLocalEpisodesMethod.class.getCanonicalName());
        hashSet.add(ResetMediaMethod.class.getCanonicalName());
        hashSet.add(SetPlaybackCapabilitiesMethod.class.getCanonicalName());
        hashSet.add(SendPlaybackEventMethod.class.getCanonicalName());
        engine.registerMethods(Collections.EMPTY_SET, hashSet, this);
        if (Podcast.isFireOS()) {
            return;
        }
        CastingSessionManager.getInstance().registerListener(this);
    }

    public void init(final Context context) {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        this.context = context;
        this.isRestoringPlayback = false;
        this.ignoreRestorePlaybackMethods = false;
        this.playbackPreferences = new PlaybackPreferences(context);
        registerVoiceCallbacks();
    }

    public boolean isCasting() {
        return Casting.isCasting();
    }

    public Media getMedia() {
        return this.media;
    }

    public void addPositionCallback(final PositionCallback positionCallback) {
        this.positionCallbacks.add(positionCallback);
    }

    public void removePositionCallback(final PositionCallback positionCallback) {
        this.positionCallbacks.remove(positionCallback);
    }

    public void addPlaybackEventCallback(final PlaybackEventCallback playbackEventCallback) {
        this.playbackEventCallbacks.add(playbackEventCallback);
    }

    public void removePlaybackEventCallback(final PlaybackEventCallback playbackEventCallback) {
        this.playbackEventCallbacks.remove(playbackEventCallback);
    }

    public void addBufferedPositionCallback(final BufferedPositionCallback bufferedPositionCallback) {
        this.bufferedPositionCallbacks.add(bufferedPositionCallback);
    }

    public void removeBufferedPositionCallback(final BufferedPositionCallback bufferedPositionCallback) {
        this.bufferedPositionCallbacks.remove(bufferedPositionCallback);
    }

    public void addStateCallback(final StateCallback callback) {
        this.stateCallbacks.add(callback);
    }

    public void removeStateCallback(final StateCallback callback) {
        this.stateCallbacks.remove(callback);
    }

    public void addDurationCallback(final DurationCallback callback) {
        this.durationCallbacks.add(callback);
    }

    public void removeDurationCallback(final DurationCallback callback) {
        this.durationCallbacks.remove(callback);
    }

    public void addSpeedCallback(final SpeedCallback speedCallback) {
        this.playbackSpeedCallbacks.add(speedCallback);
    }

    public void removeSpeedCallback(final SpeedCallback speedCallback) {
        this.playbackSpeedCallbacks.remove(speedCallback);
    }

    public void addSleepTimerStateCallback(final SleepTimerOnOffCallback sleepTimerOnOffCallback) {
        this.sleepTimerOnOffCallbacks.add(sleepTimerOnOffCallback);
    }

    public void removeSleepTimerStateCallback(final SleepTimerOnOffCallback sleepTimerOnOffCallback) {
        this.sleepTimerOnOffCallbacks.remove(sleepTimerOnOffCallback);
    }

    public void addSleepTimerDurationCallback(final SleepTimerDurationCallback sleepTimerDurationCallback) {
        this.sleepTimerDurationCallbacks.add(sleepTimerDurationCallback);
    }

    public void removeSleepTimerDurationCallback(final SleepTimerDurationCallback sleepTimerDurationCallback) {
        this.sleepTimerDurationCallbacks.remove(sleepTimerDurationCallback);
    }

    public void addTranscriptUpdatedCallback(final TranscriptionStatusUpdateCallback transcriptionStatusUpdateCallback) {
        this.transcriptionStatusUpdateCallbacks.add(transcriptionStatusUpdateCallback);
    }

    public void removeTranscriptUpdatedCallback(final TranscriptionStatusUpdateCallback transcriptionStatusUpdateCallback) {
        this.transcriptionStatusUpdateCallbacks.remove(transcriptionStatusUpdateCallback);
    }

    public void addTranscriptsCallback(final TranscriptsCallback transcriptsCallback) {
        this.transcriptsCallbacks.add(transcriptsCallback);
    }

    public void removeTranscriptsCallback(final TranscriptsCallback transcriptsCallback) {
        this.transcriptsCallbacks.remove(transcriptsCallback);
    }

    public void addDismissTranscriptViewCallback(final DismissFullScreenTranscriptCallback dismissFullScreenTranscriptCallback) {
        dismissFullScreenTranscriptCallbacks.add(dismissFullScreenTranscriptCallback);
    }

    public void removeDismissTranscriptViewCallback(final DismissFullScreenTranscriptCallback dismissFullScreenTranscriptCallback) {
        dismissFullScreenTranscriptCallbacks.remove(dismissFullScreenTranscriptCallback);
    }

    public void addPlaybackFinishedCallback(final PlaybackFinishedCallback playbackFinishedCallback) {
        this.playbackFinishedCallbacks.add(playbackFinishedCallback);
    }

    public void removePlaybackFinishedCallback(final PlaybackFinishedCallback playbackFinishedCallback) {
        this.playbackFinishedCallbacks.remove(playbackFinishedCallback);
    }

    public void setSetMediaMethodCallback(final SetMediaMethodCallback setMediaMethodCallback) {
        this.setMediaMethodCallback = setMediaMethodCallback;
    }

    public void pause() {
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat == null) {
            return;
        }
        mediaControllerCompat.getTransportControls().pause();
    }

    public void playPause() {
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat == null) {
            return;
        }
        this.restoreToZeroMediaId = null;
        int state = mediaControllerCompat.getPlaybackState().getState();
        if (2 == state) {
            this.playOffsetMilliseconds = Long.valueOf(getPlaybackPosition());
            logger.debug("onPlayerStateChanged: state: {}, user clicked on Play ", Integer.valueOf(state));
            this.mediaController.getTransportControls().play();
        } else if (3 == state) {
            logger.debug("onPlayerStateChanged: state: {}, user clicked on Pause ", Integer.valueOf(state));
            this.mediaController.getTransportControls().pause();
        } else if (7 == state) {
            logger.debug("onPlayerStateChanged: state: {}. Dispatching bufferMedia and playMedia", Integer.valueOf(state));
            bufferMedia();
            playMedia();
        }
    }

    public void stop() {
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat == null) {
            return;
        }
        mediaControllerCompat.getTransportControls().stop();
    }

    public void setDispatchOnStoppedMethods(final boolean dispatchOnStoppedMethods) {
        this.shouldDispatchOnStoppedMethods = dispatchOnStoppedMethods;
    }

    public void clear() {
        setTranscriptImplementation(null);
        setCurrentClockTime(0);
        PlaybackPreferences playbackPreferences = this.playbackPreferences;
        if (playbackPreferences != null) {
            playbackPreferences.clear();
        }
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("clearPlayback", null);
            this.mediaController = null;
        }
        MediaBrowserCompat mediaBrowserCompat = this.mediaBrowser;
        if (mediaBrowserCompat == null) {
            return;
        }
        mediaBrowserCompat.disconnect();
        this.mediaBrowser = null;
        this.ignoreRestorePlaybackMethods = true;
        Media media = this.media;
        if (media != null) {
            media.clear();
        }
        this.playbackPreferences.writeRestoreMiniPlayerMethods(null);
        this.playbackPreferences.writeRestoreAndStartMiniPlayerMethods(null);
    }

    public void next() {
        if (this.media.getMediaActionsElement() == null || this.mediaController == null) {
            return;
        }
        Iterator<PlaybackFinishedCallback> it = this.playbackFinishedCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackFinished();
        }
        int state = this.mediaController.getPlaybackState().getState();
        if (10 == state || 9 == state) {
            return;
        }
        logger.debug("dispatching onNext methods");
        dispatchMethods(this.media.getMediaActionsElement().getOnNext());
    }

    public void previous() {
        MediaControllerCompat mediaControllerCompat;
        int state;
        if (this.media.getMediaActionsElement() == null || (mediaControllerCompat = this.mediaController) == null || 10 == (state = mediaControllerCompat.getPlaybackState().getState()) || 9 == state) {
            return;
        }
        logger.debug("dispatching onPrevious methods");
        dispatchMethods(this.media.getMediaActionsElement().getOnPrevious());
    }

    public void seekTo(final int progress) {
        seekTo(progress, true, false);
    }

    public void seekTo(final int progress, final boolean dispatchOnScrubMethods, final boolean isForced) {
        if ((!isForced && !this.playbackCapabilities.isScrubberEnabled()) || this.mediaController == null || this.media.getMediaEventsElement() == null) {
            return;
        }
        TranscriptImplementation transcriptImplementation = this.transcriptImplementation;
        if (!(transcriptImplementation == null)) {
            transcriptImplementation.seek(progress - 1020);
        }
        long j = progress;
        this.playOffsetMilliseconds = Long.valueOf(j);
        PlaybackCache.get(this.media.mediaId()).updatePlaybackStartOffset(this.playOffsetMilliseconds);
        this.mediaController.getTransportControls().seekTo(j);
        if (dispatchOnScrubMethods) {
            dispatchMethods(this.media.getMediaEventsElement().getOnScrubbed());
        }
    }

    public void fastForward() {
        if (!this.playbackCapabilities.isFastForwardEnabled() || this.mediaController == null || this.media.getMediaEventsElement() == null || this.media.getMediaControlsElement() == null) {
            return;
        }
        logger.debug("User is fast-forwarding");
        List<Method> onItemSelected = this.media.getMediaControlsElement().getFastForward().getOnItemSelected();
        long playbackDuration = getPlaybackDuration();
        long playbackPosition = getPlaybackPosition();
        this.playOffsetMilliseconds = Long.valueOf(playbackPosition);
        performTranscriptSeek(playbackPosition + 30000);
        if (playbackDuration - playbackPosition <= 30000) {
            seekTo((int) playbackDuration, false, true);
            return;
        }
        this.mediaController.getTransportControls().fastForward();
        PlaybackCache.get(this.media.mediaId()).updatePlaybackStartOffset(this.playOffsetMilliseconds);
        dispatchMethods(onItemSelected);
        dispatchMethods(this.media.getMediaEventsElement().getOnFastForward());
    }

    public void rewind() {
        if (!this.playbackCapabilities.isRewindEnabled() || this.mediaController == null || this.media.getMediaEventsElement() == null) {
            return;
        }
        logger.debug("User is rewinding");
        List<Method> onItemSelected = this.media.getMediaControlsElement().getRewind().getOnItemSelected();
        long playbackPosition = getPlaybackPosition();
        this.playOffsetMilliseconds = Long.valueOf(playbackPosition);
        performTranscriptSeek(playbackPosition - 15000);
        if (playbackPosition < 15000) {
            seekTo(0, false, true);
            return;
        }
        this.mediaController.getTransportControls().rewind();
        PlaybackCache.get(this.media.mediaId()).updatePlaybackStartOffset(this.playOffsetMilliseconds);
        dispatchMethods(onItemSelected);
        dispatchMethods(this.media.getMediaEventsElement().getOnRewind());
    }

    private void performTranscriptSeek(final long currentDuration) {
        TranscriptImplementation transcriptImplementation = this.transcriptImplementation;
        if (transcriptImplementation == null) {
            return;
        }
        transcriptImplementation.seek(((int) currentDuration) - 1020);
    }

    public int getPlaybackState() {
        PlaybackStateCompat playbackState;
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat == null || (playbackState = mediaControllerCompat.getPlaybackState()) == null) {
            return 0;
        }
        return playbackState.getState();
    }

    public PlaybackSpeed getPlaybackSpeed() {
        PlaybackSpeed playbackSpeed = this.playbackSpeed;
        return playbackSpeed != null ? playbackSpeed : this.context == null ? PlaybackSpeed.ONE : this.playbackPreferences.readPlaybackSpeed();
    }

    public EpisodeOptionsElement getEpisodeOptionsElement() {
        return this.episodeOptionsElement;
    }

    public ShareElement getShareElementFromEpisodeOptions() {
        EpisodeOptionsElement episodeOptionsElement = this.episodeOptionsElement;
        if (episodeOptionsElement == null) {
            return null;
        }
        return episodeOptionsElement.getShare();
    }

    public long getPlaybackPosition() {
        return this.position;
    }

    public long getPlaybackDuration() {
        return this.duration;
    }

    private void setSleepTimerOn() {
        if (this.playbackCapabilities.isSleepTimerEnabled()) {
            this.sleepTimerOn = true;
            Iterator<SleepTimerOnOffCallback> it = this.sleepTimerOnOffCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSleepTimerOn();
            }
        }
    }

    private void setSleepTimerOff() {
        this.sleepTimerOn = false;
        Iterator<SleepTimerOnOffCallback> it = this.sleepTimerOnOffCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onSleepTimerOff();
        }
    }

    public boolean isSleepTimerForEndOfEpisode() {
        return this.playUntilEndOfEpisode;
    }

    public boolean isSleepTimerOn() {
        return this.sleepTimerOn;
    }

    public boolean isPodcastLastPlayed() {
        PlaybackPreferences playbackPreferences = this.playbackPreferences;
        if (playbackPreferences == null) {
            return false;
        }
        return playbackPreferences.isPodcastLastPlayed();
    }

    public List<Method> readRestoreMiniPlayerMethods() {
        PlaybackPreferences playbackPreferences = this.playbackPreferences;
        if (playbackPreferences == null) {
            return null;
        }
        if (isDownloaded(playbackPreferences.downloadedEpisodeId())) {
            return RestoreDownloadedEpisodesPlaybackSkill.prepareInvoke(this.playbackPreferences.downloadedEpisodeId());
        }
        return this.playbackPreferences.readRestoreMiniPlayerMethods();
    }

    public boolean isRestoringPlayback() {
        return this.isRestoringPlayback;
    }

    public void setRestoringPlayback(final boolean restoringPlayback) {
        this.isRestoringPlayback = restoringPlayback;
    }

    public void setPlaybackSpeed(final PlaybackSpeed speed) {
        setPlaybackSpeed(speed, false);
    }

    private void setPlaybackSpeed(final PlaybackSpeed speed, final boolean isForced) {
        if ((isForced || this.playbackCapabilities.isPlaybackSpeedEnabled()) && this.mediaController != null) {
            Bundle bundle = new Bundle();
            bundle.putFloat("speed", speed.getValue());
            PlaybackCache.get(this.media.mediaId()).updatePlaybackSpeed(Float.valueOf(speed.getValue()));
            this.playbackSpeed = speed;
            this.mediaController.getTransportControls().sendCustomAction("setPlaybackSpeed", bundle);
            this.playbackPreferences.writePlaybackSpeed(speed);
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.3
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = Playback.this.playbackSpeedCallbacks.iterator();
                    while (it.hasNext()) {
                        ((SpeedCallback) it.next()).onPlaybackSpeedChange(speed);
                    }
                }
            });
        }
    }

    public void setHasAudioFocus(final boolean hasAudioFocus) {
        this.hasAudioFocus = hasAudioFocus;
    }

    public void setSleepTimer(final long millis) {
        if (this.playbackCapabilities.isSleepTimerEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putLong("timerDuration", millis);
            setSleepTimerOn();
            setSleepTimerRemainingDuration(millis);
            MediaBrowserCompat mediaBrowserCompat = this.mediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                return;
            }
            this.mediaBrowser.sendCustomAction("setSleepTimer", bundle, new MediaBrowserCustomActionCallback());
        }
    }

    public void setSleepTimerToEndOfEpisode() {
        if (this.playbackCapabilities.isSleepTimerEnabled()) {
            setSleepTimerRemainingDuration(0L);
            this.playUntilEndOfEpisode = true;
            setSleepTimerOn();
        }
    }

    public void addSleepTime(final long addMSUntilSleep) {
        if (this.playbackCapabilities.isSleepTimerEnabled()) {
            long j = addMSUntilSleep + this.sleepTimerRemainingDuration;
            setSleepTimerRemainingDuration(j);
            setSleepTimer(j);
        }
    }

    public void subtractSleepTime(final long removeMSUntilSleep) {
        if (this.playbackCapabilities.isSleepTimerEnabled()) {
            long jMax = Math.max(this.sleepTimerRemainingDuration - removeMSUntilSleep, 0L);
            setSleepTimerRemainingDuration(jMax);
            setSleepTimer(jMax);
        }
    }

    public void stopSleepTimer() {
        setSleepTimerOff();
        setSleepTimerRemainingDuration(0L);
        if (this.playUntilEndOfEpisode) {
            this.playUntilEndOfEpisode = false;
        }
        MediaBrowserCompat mediaBrowserCompat = this.mediaBrowser;
        if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
            return;
        }
        this.mediaBrowser.sendCustomAction("stopSleepTimer", null, new MediaBrowserCustomActionCallback());
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (this.mediaBrowser == null) {
            initMediaBrowser(listener, method);
        } else {
            executeMethodInternal(method);
            listener.onMethodExecuted(method);
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    public void registerOwner(String ownerId) {
        this.engine.registerOwner(ownerId);
    }

    public final boolean isDownloaded(final String mediaId) {
        Download download;
        return (mediaId == null || (download = getDownload(mediaId)) == null || DownloadState.lookup(download.getState()) != DownloadState.DOWNLOADED) ? false : true;
    }

    void onSleepTimerElapsed() {
        if (this.media.getMediaEventsElement() == null) {
            return;
        }
        if (this.hasAudioFocus) {
            pause();
        }
        setSleepTimerOff();
        setSleepTimerRemainingDuration(0L);
        dispatchMethods(this.media.getMediaEventsElement().getOnSleepTimerElapsed());
    }

    void setSleepTimerRemainingDuration(final long durationMilliSeconds) {
        this.sleepTimerRemainingDuration = durationMilliSeconds;
        Iterator<SleepTimerDurationCallback> it = this.sleepTimerDurationCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onSleepTimerDurationChange(durationMilliSeconds);
        }
    }

    void setBufferedPosition(final long position) {
        this.bufferedPosition = position;
        postBufferedPositionUpdates(position);
    }

    void setPlaybackPosition(final long position) {
        this.position = position;
        postPositionUpdates(position, true);
    }

    void setPlaybackPosition(final long position, final boolean dispatchCompletedMethodsOnEpisodeCompleted) {
        this.position = position;
        postPositionUpdates(position, dispatchCompletedMethodsOnEpisodeCompleted);
    }

    public void updatePlayerProgress(final Long progress) {
        if (progress != null) {
            seekTo(progress.intValue(), false, false);
            setPlaybackPosition(progress.longValue(), false);
        }
    }

    public void setPlaybackDuration(final long duration) {
        this.duration = duration;
        Iterator<DurationCallback> it = this.durationCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackDurationChange(this.duration);
        }
        PlaybackCache.get(this.media.mediaId()).updateDuration(Long.valueOf(this.duration));
        updateMetadataKeyDuration(this.media.getMediaMetadataElement());
    }

    public void setBitesPlaybackStartingIndex(int index) {
        this.bitesExperienceStartingIndex = Math.max(index, 0);
    }

    public int getBitesPlaybackStartingIndex() {
        return this.bitesExperienceStartingIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchMethods(final List<Method> methods) {
        this.engine.handleMethods(this.media.mediaId(), methods);
    }

    private void initMediaBrowser(final OnMethodExecutedListener listener, final Method method) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.4
            @Override // java.lang.Runnable
            public void run() {
                Playback.this.mediaBrowser = new MediaBrowserCompat(Playback.this.context, new ComponentName(Playback.this.context, (Class<?>) MediaBrowserService.class), Playback.this.new MediaBrowserConnectionCallback(listener, method), null);
                Playback.this.mediaBrowser.connect();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeMethodInternal(final Method method) {
        if (method instanceof SetMediaMethod) {
            setMediaMethod((SetMediaMethod) method);
            return;
        }
        if (method instanceof BufferMediaMethod) {
            bufferMediaMethod((BufferMediaMethod) method);
            return;
        }
        if (method instanceof PlayMediaMethod) {
            playMediaMethod((PlayMediaMethod) method);
            return;
        }
        if (method instanceof PlayLocalEpisodesMethod) {
            playLocalEpisodesMethod((PlayLocalEpisodesMethod) method);
            return;
        }
        if (method instanceof ResumeMediaMethod) {
            resumeMediaMethod();
            return;
        }
        if (method instanceof PauseMediaMethod) {
            pauseMediaMethod();
            return;
        }
        if (method instanceof ClearMediaMethod) {
            clearMediaMethod();
            return;
        }
        if (method instanceof SetMediaOffsetMethod) {
            setMediaOffsetMethod((SetMediaOffsetMethod) method);
            return;
        }
        if (method instanceof NavigateToPodcastMethod) {
            navigateToPodcastMethod();
            return;
        }
        if (method instanceof SetWriteAndReportFrequenciesMethod) {
            setWriteAndReportFrequenciesMethod((SetWriteAndReportFrequenciesMethod) method);
            return;
        }
        if (method instanceof SetTranscriptsMethod) {
            setTranscriptsMethod((SetTranscriptsMethod) method);
            return;
        }
        if (method instanceof ResetMediaMethod) {
            resetMediaMethod();
        } else if (method instanceof SetPlaybackCapabilitiesMethod) {
            setPlaybackCapabilitiesMethod((SetPlaybackCapabilitiesMethod) method);
        } else if (method instanceof SendPlaybackEventMethod) {
            sendPlaybackEventMethod((SendPlaybackEventMethod) method);
        }
    }

    private void resetMediaMethod() {
        clearMediaMethod();
        clearLockScreen();
        setShouldShowMiniPlayerInApp(false);
    }

    public void clearLockScreen() {
        MediaControllerCompat mediaControllerCompat = this.mediaController;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("clearLockScreen", null);
        }
    }

    private void navigateToPodcastMethod() {
        if (this.media.getMediaMetadataElement() == null) {
            return;
        }
        new NavigationHandler(this.context).navigateToDeeplink(this.media.getMediaMetadataElement().getPodcastDeeplink());
    }

    private void setWriteAndReportFrequenciesMethod(final SetWriteAndReportFrequenciesMethod method) {
        Long writeFrequencySeconds = method.getWriteFrequencySeconds();
        Long writeAndReportFrequencySeconds = method.getWriteAndReportFrequencySeconds();
        this.playbackPreferences.writeHeartbeatWriteFrequency(writeFrequencySeconds.longValue());
        this.playbackPreferences.writeHeartbeatWriteAndReportFrequency(writeAndReportFrequencySeconds.longValue());
    }

    private void setTranscriptsMethod(final SetTranscriptsMethod method) {
        if (this.playbackCapabilities.isTranscriptEnabled() && method != null) {
            String transcriptsFileUrl = method.getTranscriptsFileUrl();
            setHasFetchedTranscript(true);
            List<ScrubSegment> scrubSegments = TranscriptProvider.getScrubSegments(transcriptsFileUrl);
            this.scrubSegments = scrubSegments;
            initTranscripts(scrubSegments);
            Iterator<TranscriptsCallback> it = this.transcriptsCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTranscriptFetched(this.scrubSegments);
            }
        }
    }

    private void sendPlaybackEventMethod(final SendPlaybackEventMethod method) {
        if (method == null) {
            return;
        }
        String playbackEvent = method.getPlaybackEvent();
        if (StringUtils.isBlank(playbackEvent)) {
            return;
        }
        Iterator<PlaybackEventCallback> it = this.playbackEventCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackEventReceived(playbackEvent);
        }
    }

    private void setMediaOffsetMethod(final SetMediaOffsetMethod method) {
        if (method.isIgnorable() != null && method.isIgnorable().booleanValue() && this.ignoreRestorePlaybackMethods) {
            this.isRestoringPlayback = false;
            this.ignoreRestorePlaybackMethods = false;
            return;
        }
        long jResumeOffsetMS = resumeOffsetMS(method.getMediaId(), method.getRewindOffsetMilliseconds());
        setPlaybackSpeed(this.playbackSpeed, true);
        setPlayOffset(method.getMediaId(), jResumeOffsetMS);
        setPlaybackPosition(jResumeOffsetMS);
        if (jResumeOffsetMS == 0) {
            this.restoreToZeroMediaId = method.getMediaId();
        }
        this.ignoreRestorePlaybackMethods = false;
    }

    private void clearMediaMethod() {
        this.scrubSegments = null;
        setTranscriptImplementation(null);
        setHasFetchedTranscript(false);
        setCurrentClockTime(0);
        this.ingressSource = null;
        if (this.mediaController == null) {
            return;
        }
        if (this.media != null) {
            if (getPlaybackState() == 3 || getPlaybackState() == 6) {
                PlaybackCache.get(this.media.mediaId()).updateProgress(Long.valueOf(getPlaybackPosition()));
                this.mediaController.getTransportControls().stop();
                if (this.media.getMediaMetadataElement() != null) {
                    dispatchMethods(this.media.getMediaEventsElement().getOnStopped());
                }
            }
            this.media.clear();
        }
        this.playbackPreferences.writeRestoreMiniPlayerMethods(null);
        this.playbackPreferences.writeRestoreAndStartMiniPlayerMethods(null);
    }

    private void postPositionUpdates(final long position, final boolean checkForEpisodeCompletion) {
        if (this.positionCallbacks.isEmpty()) {
            return;
        }
        if (checkForEpisodeCompletion) {
            boolean zIsProgressGreaterThanThreshold = isProgressGreaterThanThreshold(Long.valueOf(position), Long.valueOf(PlaybackCache.get(this.media.mediaId()).getDurationMilliseconds()));
            if (this.media.getMediaEventsElement() != null && zIsProgressGreaterThanThreshold && !isCompleted(this.media.mediaId())) {
                dispatchMethods(this.media.getMediaEventsElement().getOnCompletedThresholdElapsed());
            }
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = Playback.this.positionCallbacks.iterator();
                while (it.hasNext()) {
                    ((PositionCallback) it.next()).onPositionChange(position);
                    PlaybackCache playbackCache = PlaybackCache.get(Playback.this.media.mediaId());
                    playbackCache.updateProgress(Long.valueOf(position));
                    playbackCache.getDurationMilliseconds();
                }
            }
        });
    }

    private void postBufferedPositionUpdates(final long bufferedPosition) {
        if (this.bufferedPositionCallbacks.isEmpty()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = Playback.this.bufferedPositionCallbacks.iterator();
                while (it.hasNext()) {
                    ((BufferedPositionCallback) it.next()).onBufferedPositionChange(bufferedPosition);
                }
            }
        });
    }

    private void setMediaMethod(final SetMediaMethod method) {
        setShouldShowMiniPlayerInApp(this.playbackCapabilities.isMiniPlayerEnabled());
        com.amazon.music.media.playback.Playback.getInstance().setMediaButtonListener(this.mediaButtonListener);
        this.episodeOptionsElement = method.getEpisodeOptions();
        SetMediaMethodCallback setMediaMethodCallback = this.setMediaMethodCallback;
        if (setMediaMethodCallback != null) {
            setMediaMethodCallback.onSetMediaMethodCalled(method.getEpisodeOptions());
        }
        checkAndUpdateIsRestoringFlag(method.isIgnorable());
        if (method.isIgnorable() != null && method.isIgnorable().booleanValue() && this.ignoreRestorePlaybackMethods) {
            return;
        }
        this.ignoreRestorePlaybackMethods = false;
        int playbackState = getPlaybackState();
        this.playbackPreferences.writePodcastLastPlayed(true);
        if (method.getOnRestore() != null || method.getOnRestoreAndStart() != null) {
            this.playbackPreferences.writeEpisodeDownloadedId(null);
            if (method.getOnRestore() != null) {
                this.playbackPreferences.writeRestoreMiniPlayerMethods(method.getOnRestore());
            }
            if (method.getOnRestoreAndStart() != null) {
                this.playbackPreferences.writeRestoreAndStartMiniPlayerMethods(method.getOnRestoreAndStart());
            }
        } else {
            this.playbackPreferences.writeEpisodeDownloadedId(method.getMetadata().getMediaId());
        }
        String strMediaId = this.media.mediaId();
        String mediaId = method.getMetadata().getMediaId();
        if (3 == playbackState && strMediaId != null && !strMediaId.equals(mediaId)) {
            this.mediaController.getTransportControls().stop();
            MediaEventsElement mediaEventsElement = this.media.getMediaEventsElement();
            if (mediaEventsElement != null) {
                dispatchMethods(mediaEventsElement.getOnStopped());
            }
        }
        if (StringUtils.isBlank(this.ingressSource)) {
            this.ingressSource = Podcast.getIngressSource();
        }
        PlaybackCache playbackCache = PlaybackCache.get(mediaId);
        playbackCache.updateIngressSource(this.ingressSource);
        playbackCache.resetRebufferCount();
        playbackCache.resetRebufferDurationMillisecond();
        String strMediaId2 = this.media.mediaId();
        if (strMediaId2 != null) {
            this.engine.deregisterOwner(strMediaId2);
        }
        this.engine.registerOwner(mediaId);
        this.media.setMedia(method);
        playbackCache.updatePlaybackEngineType(getPlaybackEngineType());
        playbackCache.updatePlayButtonTapAtMS(Long.valueOf(System.currentTimeMillis()));
        updateMediaMetadata(method.getMetadata());
        dispatchMethods(method.getMetadata().getOnAdditionalMetadataRequired());
        if (this.playbackCapabilities.isTranscriptEnabled() && TranscriptProvider.isTranscriptSupported()) {
            dispatchMethods(method.getMetadata().getOnTranscriptsRequired());
        }
    }

    private void updateMediaMetadata(final MediaMetadataElement metadataElement) {
        sendCustomAction("setMediaMetadata", metadataElement);
    }

    private void updateMetadataKeyDuration(final MediaMetadataElement metadataElement) {
        sendCustomAction("setMetadataKeyDuration", metadataElement);
    }

    private void sendCustomAction(final String action, final MediaMetadataElement metadataElement) {
        MediaBrowserCompat mediaBrowserCompat = this.mediaBrowser;
        if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected() || metadataElement == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("mediaMetadataElement", Mappers.mapper().writeValueAsString(metadataElement));
            this.mediaController.getTransportControls().sendCustomAction(action, bundle);
        } catch (Exception e) {
            logger.error("onConnected: Error connecting media browser", (Throwable) e);
        }
    }

    private void bufferMediaMethod(final BufferMediaMethod method) {
        String audioUri;
        if (PodcastFeatureGating.PLAYER_BOOKMARK_DB_BINDINGS.isEnabled()) {
            updateBookmarkObserver();
        }
        setHasFetchedTranscript(false);
        checkAndUpdateIsRestoringFlag(method.isIgnorable());
        if (method.isIgnorable() != null && method.isIgnorable().booleanValue() && this.ignoreRestorePlaybackMethods) {
            return;
        }
        this.ignoreRestorePlaybackMethods = false;
        this.wasPreviousStateFinished = false;
        String mediaId = method.getMediaId();
        boolean zIsDownloaded = isDownloaded(mediaId);
        PlaybackCache playbackCache = PlaybackCache.get(method.getMediaId());
        playbackCache.updateIsDownloaded(Boolean.valueOf(zIsDownloaded));
        if (zIsDownloaded) {
            audioUri = getDownload(mediaId).getLocalAudioUri();
        } else {
            audioUri = method.getAudioUri();
        }
        String updatedAudioUriSchema = getUpdatedAudioUriSchema(audioUri, method.getAudioStreamFormat());
        playbackCache.updateAudioUri(updatedAudioUriSchema);
        Bundle bundle = new Bundle();
        if (Casting.isCasting()) {
            setStreamType(AudioStreamFormat.PROGRESSIVE);
            this.media.setMediaUri(audioUri);
        } else {
            this.media.setMediaUri(updatedAudioUriSchema);
        }
        bundle.putString("audioUri", updatedAudioUriSchema);
        bundle.putString("mediaId", method.getMediaId());
        bundle.putLong("playbackActions", playbackActions());
        bundle.putBoolean("isMediaDownloaded", zIsDownloaded);
        bundle.putString("audioStreamFormat", method.getAudioStreamFormat());
        this.mediaController.getTransportControls().prepareFromMediaId(method.getMediaId(), bundle);
    }

    private final String getUpdatedAudioUriSchema(final String audioUri, final String audioStreamFormat) {
        if (!Podcast.isHarleyEnabled()) {
            return audioUri;
        }
        if (isDownloaded(this.media.mediaId())) {
            return this.DOWNLOADED_PODCAST_SCHEMA + audioUri;
        }
        HttpUrl.Builder builderAddQueryParameter = HttpUrl.parse(audioUri).newBuilder().addQueryParameter("xh-User-Agent", getUserAgentValue());
        String optOutHeaderValue = OptOut.getOptOutHeaderValue();
        if (optOutHeaderValue != null) {
            builderAddQueryParameter.addQueryParameter("xh-Sec-GPC", optOutHeaderValue);
        }
        String string = builderAddQueryParameter.build().uri().toString();
        if (audioStreamFormat.equals(AudioStreamFormat.HLS)) {
            setStreamType(AudioStreamFormat.HLS);
            return string.replaceFirst(this.HTTPS_SCHEMA_REGEX, this.HLS_PODCAST_SCHEMA);
        }
        setStreamType(AudioStreamFormat.PROGRESSIVE);
        return string.replaceFirst(this.HTTPS_SCHEMA_REGEX, this.PODCAST_SCHEMA);
    }

    private String getUserAgentValue() {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return "";
        }
        return "AmazonMusic/" + packageInfo.versionName;
    }

    private void bufferMedia() {
        Bundle bundle = new Bundle();
        String mediaUri = this.media.getMediaUri();
        MediaMetadataElement mediaMetadataElement = this.media.getMediaMetadataElement();
        if (mediaMetadataElement == null) {
            return;
        }
        String mediaId = mediaMetadataElement.getMediaId();
        bundle.putString("audioUri", mediaUri);
        bundle.putString("mediaId", mediaId);
        bundle.putLong("playbackActions", playbackActions());
        this.mediaController.getTransportControls().prepareFromMediaId(mediaId, bundle);
    }

    private void playMedia() {
        MediaMetadataElement mediaMetadataElement = this.media.getMediaMetadataElement();
        if (mediaMetadataElement == null) {
            return;
        }
        playFromMediaId(mediaMetadataElement.getMediaId(), this.position);
    }

    private void resumeMediaMethod() {
        this.mediaController.getTransportControls().play();
    }

    private void pauseMediaMethod() {
        this.mediaController.getTransportControls().pause();
    }

    private void playMediaMethod(final PlayMediaMethod method) {
        this.restoreToZeroMediaId = null;
        this.isRestoringPlayback = false;
        long jResumeOffsetMS = (method.isResumeAtBookmark() == null || !method.isResumeAtBookmark().booleanValue()) ? 0L : resumeOffsetMS(method.getMediaId(), method.getRewindOffsetMilliseconds());
        setPlaybackSpeed(this.playbackSpeed, true);
        Media media = this.media;
        if (media != null) {
            media.startedPlaying();
        }
        playFromMediaId(method.getMediaId(), jResumeOffsetMS);
    }

    private void playLocalEpisodesMethod(final PlayLocalEpisodesMethod method) {
        String episodeId = method.getEpisodeId();
        this.engine.registerOwner(episodeId);
        if (isDownloaded(method.getEpisodeId())) {
            dispatch(episodeId, PlayDownloadedEpisodesMethods.playMethods(getDownload(method.getEpisodeId()), this.context.getResources()));
            dispatch(episodeId, method.getOnLocalEpisodeFound());
        } else {
            dispatch(episodeId, method.getOnLocalEpisodeNotFound());
        }
    }

    private void setPlaybackCapabilitiesMethod(final SetPlaybackCapabilitiesMethod method) {
        if (method.isFastForward() != null) {
            this.playbackCapabilities.setFastForwardEnabled(method.isFastForward().booleanValue());
        }
        if (method.isLockScreen() != null) {
            this.playbackCapabilities.setLockScreenEnabled(method.isLockScreen().booleanValue());
        }
        if (method.isMiniplayer() != null) {
            this.playbackCapabilities.setMiniPlayerEnabled(method.isMiniplayer().booleanValue());
        }
        if (method.isPlaybackSpeed() != null) {
            this.playbackCapabilities.setPlaybackSpeedEnabled(method.isPlaybackSpeed().booleanValue());
        }
        if (method.isRewind() != null) {
            this.playbackCapabilities.setRewindEnabled(method.isRewind().booleanValue());
        }
        if (method.isSleepTimer() != null) {
            this.playbackCapabilities.setSleepTimerEnabled(method.isSleepTimer().booleanValue());
        }
        if (method.isScrubber() != null) {
            this.playbackCapabilities.setScrubberEnabled(method.isScrubber().booleanValue());
        }
        if (method.isTranscript() != null) {
            this.playbackCapabilities.setTranscriptEnabled(method.isTranscript().booleanValue());
        }
    }

    public LocalEpisodeAlreadyPlayingClientState getPlayLocalEpisodesClientState(final String name) {
        if (!name.equals(LocalEpisodeAlreadyPlayingClientState.class.getCanonicalName()) || this.media == null) {
            return null;
        }
        return LocalEpisodeAlreadyPlayingClientState.builder().withAlreadyPlaying(Boolean.valueOf(this.media.hasStartedPlaying())).build();
    }

    private final long resumeOffsetMS(final String mediaId, final Long rewindOffsetMS) {
        try {
            Bookmark bookmark = (Bookmark) Executors.newSingleThreadExecutor().submit(new Callable<Bookmark>() { // from class: com.amazon.podcast.media.playback.Playback.7
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Bookmark call() {
                    return Podcast.getAppSync().bookmark().read(Playback.this.context, mediaId);
                }
            }).get();
            if (bookmark == null) {
                return 0L;
            }
            Long lValueOf = Long.valueOf(bookmark.getProgressMilliseconds());
            if (rewindOffsetMS == null) {
                return lValueOf.longValue();
            }
            Long lValueOf2 = Long.valueOf(lValueOf.longValue() - rewindOffsetMS.longValue());
            if (lValueOf2.longValue() <= 0) {
                return lValueOf.longValue();
            }
            return lValueOf2.longValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0L;
        }
    }

    public final long getBufferedPosition(final String mediaId) {
        Long l = 0L;
        try {
            Bookmark bookmark = (Bookmark) Executors.newSingleThreadExecutor().submit(new Callable<Bookmark>() { // from class: com.amazon.podcast.media.playback.Playback.8
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Bookmark call() {
                    return Podcast.getAppSync().bookmark().read(Playback.this.context, mediaId);
                }
            }).get();
            if (bookmark == null) {
                return 0L;
            }
            return Long.valueOf(bookmark.getProgressMilliseconds()).longValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return l.longValue();
        }
    }

    private void setPlayOffset(final String mediaId, final long offset) {
        if (this.mediaController == null) {
            return;
        }
        this.playOffsetMilliseconds = Long.valueOf(offset);
        PlaybackCache.get(mediaId).updatePlaybackStartOffset(this.playOffsetMilliseconds);
        this.mediaController.getTransportControls().seekTo(offset);
    }

    private void playFromMediaId(final String mediaId, final long playOffsetMilliseconds) {
        Bundle bundle;
        PlaybackCache.get(mediaId).updatePlaybackStartOffset(Long.valueOf(playOffsetMilliseconds));
        this.playOffsetMilliseconds = Long.valueOf(playOffsetMilliseconds);
        if (playOffsetMilliseconds == 0) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("playOffsetMS", playOffsetMilliseconds);
            bundle = bundle2;
        }
        this.mediaController.getTransportControls().playFromMediaId(mediaId, bundle);
    }

    private final class MediaBrowserCustomActionCallback extends MediaBrowserCompat.CustomActionCallback {
        private MediaBrowserCustomActionCallback() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.CustomActionCallback
        public void onResult(final String action, final Bundle extras, final Bundle resultData) {
            MediaEventsElement mediaEventsElement;
            super.onResult(action, extras, resultData);
            action.hashCode();
            if (action.equals("setSleepTimer")) {
                if (Playback.this.playbackCapabilities.isSleepTimerEnabled()) {
                    Playback.logger.info("MediaBrowserCustomActionCallback: setSleepTimer");
                    if (extras.getLong("timerDuration", 0L) == 0 || (mediaEventsElement = Playback.this.media.getMediaEventsElement()) == null) {
                        return;
                    }
                    Playback.this.dispatchMethods(mediaEventsElement.getOnSleepTimerSet());
                    return;
                }
                return;
            }
            if (!action.equals("stopSleepTimer")) {
                Playback.logger.error("MediaBrowserCustomActionCallback: Unknown custom action - " + action);
                return;
            }
            Playback.logger.info("MediaBrowserCustomActionCallback: stopSleepTimer");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.CustomActionCallback
        public void onError(final String action, final Bundle extras, final Bundle data) {
            super.onError(action, extras, data);
            Playback.logger.error("MediaBrowserCustomActionCallback: Error while performing custom action - " + action);
        }
    }

    private final class MediaControllerCallback extends MediaControllerCompat.Callback {
        private MediaControllerCallback() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
            sendPlaybackStateUpdates(playbackStateCompat.getState(), playbackStateCompat.getExtras());
            sendBundleUpdates(playbackStateCompat.getExtras());
        }

        private void sendPlaybackStateUpdates(final int state, final Bundle extras) {
            MediaEventsElement mediaEventsElement = Playback.this.media.getMediaEventsElement();
            if (mediaEventsElement == null) {
                return;
            }
            PlaybackCache playbackCache = PlaybackCache.get(Playback.this.media.mediaId());
            if (Playback.this.shouldHandleState(state, extras)) {
                if (2 == state) {
                    if (Playback.this.isRestoringPlayback) {
                        Playback.this.isRestoringPlayback = false;
                    } else if (extras == null) {
                        Playback.logger.debug("dispatching onPaused methods");
                        playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackPosition()));
                        Playback.this.dispatchMethods(mediaEventsElement.getOnPaused());
                    } else if (extras.containsKey("playbackFinished")) {
                        Playback.logger.debug("dispatching onFinished methods");
                        Playback.this.wasPreviousStateFinished = true;
                        Iterator it = Playback.this.playbackFinishedCallbacks.iterator();
                        while (it.hasNext()) {
                            ((PlaybackFinishedCallback) it.next()).onPlaybackFinished();
                        }
                        playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackDuration()));
                        Playback.this.dispatchMethods(mediaEventsElement.getOnFinished());
                        Playback.this.finishedEpisodeOnNext = true;
                    }
                } else if (3 == state && (Playback.this.previousPlaybackState == 2 || extras.containsKey("streamingRebuffered"))) {
                    Playback.logger.debug("dispatching onResumed methods");
                    playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackPosition()));
                    playbackCache.updatePlaybackStartOffset(Playback.this.playOffsetMilliseconds);
                    playbackCache.updatePlaybackStartedAt(Long.valueOf(System.currentTimeMillis()));
                    Playback.this.dispatchMethods(mediaEventsElement.getOnResumed());
                } else if (1 == state) {
                    String string = extras.getString("mediaId");
                    String strMediaId = Playback.this.media.mediaId();
                    if (StringUtils.isNotBlank(Playback.this.restoreToZeroMediaId) && strMediaId.equals(Playback.this.restoreToZeroMediaId)) {
                        Playback.this.restoreToZeroMediaId = null;
                        Playback.logger.debug("resumeOffsetMilliseconds(progress) is 0, do not dispatch onStopped methods");
                        return;
                    }
                    if (StringUtils.isNotBlank(strMediaId) && strMediaId.equals(string)) {
                        Playback.logger.debug("dispatching onStopped methods");
                        playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackPosition()));
                        if (Playback.this.shouldDispatchOnStoppedMethods) {
                            Playback.this.dispatchMethods(mediaEventsElement.getOnStopped());
                        }
                        Playback.this.shouldDispatchOnStoppedMethods = true;
                    }
                    Playback.this.restoreToZeroMediaId = null;
                } else if (7 == state) {
                    playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackPosition()));
                    if (extras.containsKey("playbackError")) {
                        String string2 = extras.getString("playbackError");
                        Playback.this.exoplayerLocalizedException = string2;
                        String errorException = playbackCache.getErrorException();
                        if (StringUtils.isNotBlank(string2)) {
                            playbackCache.updateErrorException(StringUtils.left(string2, 500));
                        }
                        if (string2.equals(errorException)) {
                            return;
                        }
                        Playback.logger.debug("dispatching onError methods");
                        Playback.this.dispatchMethods(mediaEventsElement.getOnError());
                        return;
                    }
                } else if (state == 0) {
                    Playback.logger.debug("Playback state is none");
                }
                Playback.this.previousPlaybackState = state;
                Playback playback = Playback.this;
                playback.postStateUpdates(playback.previousPlaybackState);
            }
        }

        private void sendBundleUpdates(final Bundle bundle) {
            PlaybackCache playbackCache = PlaybackCache.get(Playback.this.media.mediaId());
            if (bundle == null || Playback.this.media.getMediaEventsElement() == null) {
                return;
            }
            if (bundle.containsKey("streamingInitiated")) {
                Playback.logger.debug("dispatching onStarted methods");
                playbackCache.updateProgress(Long.valueOf(Playback.this.getPlaybackPosition()));
                playbackCache.updatePlaybackSpeed(Float.valueOf(Playback.this.getPlaybackSpeed().getValue()));
                playbackCache.updatePlaybackStartedAt(Long.valueOf(System.currentTimeMillis()));
                Playback playback = Playback.this;
                playback.dispatchMethods(playback.media.getMediaEventsElement().getOnStarted());
                if (Playback.this.finishedEpisodeOnNext) {
                    Playback.this.finishedEpisodeOnNext = false;
                    if (Playback.this.playUntilEndOfEpisode) {
                        Playback.this.stopSleepTimer();
                        Playback.this.mediaController.getTransportControls().pause();
                        return;
                    }
                    return;
                }
                return;
            }
            if (bundle.containsKey("streamingRebuffered")) {
                if (!Playback.this.isSeekBarTracking()) {
                    playbackCache.updatePlaybackStartedAt(Long.valueOf(System.currentTimeMillis()));
                }
                playbackCache.addRebufferCount();
                playbackCache.updateRebufferDurationMilliseconds(Long.valueOf(bundle.getLong("rebufferDurationMS")));
                Playback.logger.debug("dispatching onRebuffered methods");
                Playback playback2 = Playback.this;
                playback2.dispatchMethods(playback2.media.getMediaEventsElement().getOnRebuffered());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStateUpdates(final int playbackState) {
        if (this.stateCallbacks.isEmpty()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.9
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = Playback.this.stateCallbacks.iterator();
                while (it.hasNext()) {
                    ((StateCallback) it.next()).onPlaybackStateChange(playbackState);
                }
            }
        });
    }

    private class MediaBrowserConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private final OnMethodExecutedListener listener;
        private final Method method;

        public MediaBrowserConnectionCallback(final OnMethodExecutedListener listener, final Method method) {
            this.listener = listener;
            this.method = method;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            super.onConnected();
            try {
                Playback playback = Playback.this;
                playback.mediaController = new MediaControllerCompat(playback.context, Playback.this.mediaBrowser.getSessionToken());
                Playback.this.mediaController.registerCallback(new MediaControllerCallback());
                Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.MediaBrowserConnectionCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowserConnectionCallback mediaBrowserConnectionCallback = MediaBrowserConnectionCallback.this;
                        Playback.this.executeMethodInternal(mediaBrowserConnectionCallback.method);
                        MediaBrowserConnectionCallback.this.listener.onMethodExecuted(MediaBrowserConnectionCallback.this.method);
                    }
                });
            } catch (Exception e) {
                Playback.logger.error("onConnected: Error connecting media browser", (Throwable) e);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            super.onConnectionSuspended();
            synchronized (Playback.this.methodQueueLock) {
                Playback.this.methodQueue.clear();
            }
            Playback.logger.error("onConnected: Error connecting media browser");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            super.onConnectionFailed();
            synchronized (Playback.this.methodQueueLock) {
                Playback.this.methodQueue.clear();
            }
            Playback.logger.error("onConnected: Error connecting media browser");
        }
    }

    private void registerVoiceCallbacks() {
        try {
            if (VoiceManagerSingleton.isInitialized()) {
                VoiceManagerSingleton.getInstance().setPodcastPlaybackControllerAndMediaSessionStateProvider(new PodcastGUIPlaybackController(), getMediaSessionStateProvider());
            } else {
                logger.info("Podcast Playback is not registered for AOM callbacks.");
            }
        } catch (Exception e) {
            logger.error("PodcastPlayback", "Error setting up AOM callbacks", e);
        }
    }

    private MediaSessionStateProvider getMediaSessionStateProvider() {
        return new MediaSessionStateProvider() { // from class: com.amazon.podcast.media.playback.Playback.10
            @Override // com.amazon.music.voice.MediaSessionStateProvider
            public boolean isActive() {
                if (Playback.this.mediaBrowser == null) {
                    return false;
                }
                return Playback.this.mediaBrowser.isConnected();
            }
        };
    }

    private Download getDownload(final String mediaId) {
        return Podcast.getAppSync().download().read(this.context, mediaId);
    }

    private void checkAndUpdateIsRestoringFlag(final Boolean ignorable) {
        if (ignorable == null || !ignorable.booleanValue()) {
            this.isRestoringPlayback = false;
        }
    }

    public final boolean isCompleted(final String mediaId) {
        try {
            Completed completed = (Completed) Executors.newSingleThreadExecutor().submit(new Callable<Completed>() { // from class: com.amazon.podcast.media.playback.Playback.11
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Completed call() {
                    return Podcast.getAppSync().completed().read(Playback.this.context, mediaId);
                }
            }).get();
            if (completed == null) {
                return false;
            }
            return completed.getCompleted();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    private final boolean isProgressGreaterThanThreshold(final Long progressMilliSeconds, final Long durationMilliSeconds) {
        if (durationMilliSeconds.longValue() == 0) {
            return false;
        }
        return (((double) progressMilliSeconds.longValue()) / durationMilliSeconds.doubleValue()) * 100.0d > ((double) Podcast.getBookmarksUpperBoundPercentage().intValue());
    }

    void heartbeat(final long position) {
        if (PodcastFeatureGating.HEARTBEAT.isEnabled()) {
            final MediaEventsElement mediaEventsElement = this.media.getMediaEventsElement();
            if (mediaEventsElement == null) {
                logger.debug("Unable to emit heartbeat-based bookmarks due to null MediaEventsElement.");
            } else {
                Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.12
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            String strMediaId = Playback.this.media.mediaId();
                            PlaybackCache playbackCache = PlaybackCache.get(strMediaId);
                            long seconds = Playback.this.getSeconds(position);
                            long heartbeatWriteFrequency = Playback.this.playbackPreferences.readHeartbeatWriteFrequency();
                            if (seconds % Playback.this.playbackPreferences.readHeartbeatWriteAndReportFrequency() == 0) {
                                Playback.logger.info("Heartbeat: write and report, position: {}, mediaId: {}", Long.valueOf(position), strMediaId);
                                playbackCache.updateProgress(Long.valueOf(position));
                                Playback.this.dispatchMethods(mediaEventsElement.getOnWriteAndReportFrequencyReached());
                            } else if (seconds % heartbeatWriteFrequency == 0) {
                                Playback.logger.info("Heartbeat: write, position: {}, mediaId: {}", Long.valueOf(position), strMediaId);
                                playbackCache.updateProgress(Long.valueOf(position));
                                Playback.this.dispatchMethods(mediaEventsElement.getOnWriteFrequencyReached());
                            }
                        } catch (Exception e) {
                            Playback.logger.error("Failed to handle heartbeat at position {} for mediaId {}.", new Object[]{Long.valueOf(position), Playback.this.media.mediaId()}, e);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getSeconds(final long milliseconds) {
        return Math.round(milliseconds / 1000.0d);
    }

    public boolean isEpisodePlaying(final String id) {
        return id.equals(this.media.mediaId()) && Podcast.getPlayback().getPlaybackState() == 3;
    }

    public boolean isEpisodePaused(final String id) {
        return id.equals(this.media.mediaId()) && Podcast.getPlayback().getPlaybackState() == 2;
    }

    public void setCastingFromPodcast(boolean castingFromPodcast) {
        this.isCastingFromPodcast = castingFromPodcast;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public void startPlaybackOnChromecast() {
        if (ChromecastMediaProvider.isPlaybackOnChromecastPaused()) {
            ChromecastMediaProvider.startPlaybackOnChromecast();
            ChromecastMediaProvider.setPlaybackOnChromecastPaused(true);
        }
    }

    public void stopPodcastPlayback() {
        if (this.mediaController == null || !Casting.isCasting()) {
            return;
        }
        this.mediaController.getTransportControls().stop();
    }

    public List<ScrubSegment> getScrubSegments() {
        return this.scrubSegments;
    }

    public void initTranscripts(List<ScrubSegment> scrubSegments) {
        this.transcriptImplementation = new TranscriptImplementation(scrubSegments, 44100, TranscriptProvider.getPeakHeight().doubleValue(), this.transcriptUpdatedListener);
        this.transcriptImplementation.seek(((int) PlaybackCache.get(this.media.mediaId()).getPlaybackStartOffsetMilliseconds()) - 1020);
    }

    public boolean getIsTranscriptLoaded() {
        return this.isTranscriptLoaded;
    }

    public void setIsTranscriptLoaded(Boolean isTranscriptLoaded) {
        this.isTranscriptLoaded = isTranscriptLoaded.booleanValue();
    }

    public TranscriptImplementation getTranscriptImplementation() {
        return this.transcriptImplementation;
    }

    public void setTranscriptImplementation(TranscriptImplementation transcriptImplementation) {
        if (transcriptImplementation == null) {
            setIsTranscriptLoaded(Boolean.FALSE);
            setTranscriptSyncing(false);
            setUpdatedScrubSegment(null);
            setHasFetchedTranscript(false);
        }
        this.transcriptImplementation = transcriptImplementation;
    }

    public boolean isHasFetchedTranscript() {
        return this.hasFetchedTranscript;
    }

    public void setHasFetchedTranscript(boolean hasFetchedTranscript) {
        this.hasFetchedTranscript = hasFetchedTranscript;
    }

    public boolean isSeekBarTracking() {
        return this.isSeekBarTracking;
    }

    public void setSeekBarTracking(boolean seekBarTracking) {
        this.isSeekBarTracking = seekBarTracking;
    }

    public int getCurrentClockTime() {
        return this.currentTime;
    }

    public void setCurrentClockTime(final int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isTranscriptSyncing() {
        return this.isTranscriptSyncing;
    }

    public void setTranscriptSyncing(boolean isTranscriptSyncing) {
        this.isTranscriptSyncing = isTranscriptSyncing;
    }

    public boolean getShouldShowMiniPlayerInApp() {
        return this.shouldShowMiniPlayerInApp;
    }

    public void setShouldShowMiniPlayerInApp(final boolean shouldShowMiniPlayerInApp) {
        this.shouldShowMiniPlayerInApp = shouldShowMiniPlayerInApp;
    }

    public static Set<DismissFullScreenTranscriptCallback> getDismissFullScreenTranscriptCallbacks() {
        return dismissFullScreenTranscriptCallbacks;
    }

    public boolean isPodcastTranscriptsDisabledFromSettings() {
        Context context;
        if (!PodcastFeatureGating.SCRUB_TRANSCRIPTION.isEnabled() || (context = this.context) == null) {
            return true;
        }
        return UserPreferences.get(context).readPodcastTranscriptDisabledFromSettings();
    }

    public void setPodcastTranscriptsDisabledFromSettings(final boolean podcastTranscriptsDisabledFromSettings) {
        Context context = this.context;
        if (context == null) {
            return;
        }
        UserPreferences.get(context).writePodcastTranscriptDisabledFromSettings(podcastTranscriptsDisabledFromSettings);
    }

    public List<ScrubSegment> getUpdatedScrubSegment() {
        return this.updatedScrubSegment;
    }

    public void setUpdatedScrubSegment(List<ScrubSegment> updatedScrubSegment) {
        this.updatedScrubSegment = updatedScrubSegment;
    }

    public String getExoplayerLocalizedException() {
        return this.exoplayerLocalizedException;
    }

    private void updateBookmarkObserver() {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Playback.14
            @Override // java.lang.Runnable
            public void run() {
                if (Playback.this.liveData != null) {
                    Playback.this.liveData.removeObserver(Playback.this.observer);
                }
                Playback.this.liveData = Podcast.getAppSync().bookmark().readLive(Playback.this.context, Playback.this.media.mediaId());
                Playback.this.liveData.observeForever(Playback.this.observer);
            }
        });
    }

    public static String formatTime(final Long timeInMillis) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(timeInMillis.longValue());
        long jLongValue = timeInMillis.longValue();
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(jLongValue - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((timeInMillis.longValue() - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (hours > 0) {
            return String.format("%d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds));
        }
        if (seconds < 0) {
            return String.format("-%d:%02d", Long.valueOf(minutes), Long.valueOf(Math.abs(seconds)));
        }
        return String.format("%d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }

    public long getSleepTimerRemainingDuration() {
        return this.sleepTimerRemainingDuration;
    }

    public PlaybackCapabilities getPlaybackCapabilities() {
        return this.playbackCapabilities;
    }

    private final String getPlaybackEngineType() {
        if (Podcast.isHarleyEnabled()) {
            return PlaybackEngineType.HARLEY.getPlaybackEngineType();
        }
        return PlaybackEngineType.EXOPLAYER.getPlaybackEngineType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldHandleState(final int state, final Bundle extras) {
        if (!Podcast.isHarleyEnabled()) {
            return state != this.previousPlaybackState;
        }
        boolean z = extras != null && extras.containsKey("playbackFinished");
        int i = this.previousPlaybackState;
        if (state == i && !z) {
            return false;
        }
        if (state == i && z) {
            return !this.wasPreviousStateFinished;
        }
        return true;
    }
}

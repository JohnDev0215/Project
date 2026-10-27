package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackMetricsHandler {
    private static final long MAX_SPURIOUS_PLAYBACK_MILLIS;
    private static final long MIN_PLAYBACK_DURATION;
    private int bufferingCount;
    private long deletedDuration;
    private final MetricsInfo info;
    private long initialStartDelay;
    private long initialStartTime;
    private long maxPosition;
    private MediaLink mediaLink;
    private long playbackDuration;
    private PlaybackEvent playbackExpectedEvent;
    private PlaybackEvent playbackNotExpectedEvent;
    private PlaybackEvent playbackStartedEvent;
    private PlaybackEvent playbackStoppedEvent;
    private Sender sender;
    private boolean sentStreamingInitiated;
    private boolean terminated;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private float amountCached = -1.0f;
    private boolean canUpdateAmountCached = true;

    public interface Sender {
        void sendStreamingInitiated(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, ChangeReason changeReason);

        void sendStreamingInitiationFailed(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException playbackException);

        void sendStreamingRequiredRebuffering(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, float f);

        void sendStreamingTerminated(MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, ChangeReason changeReason, long j5);

        void sendTrackStreamed(MetricsInfo metricsInfo, long j, ChangeReason changeReason);
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        MIN_PLAYBACK_DURATION = timeUnit.toMillis(1L);
        MAX_SPURIOUS_PLAYBACK_MILLIS = timeUnit.toMillis(10L);
    }

    public PlaybackMetricsHandler(@NonNull MediaItem mediaItem) {
        MetricsInfo metricsInfo = new MetricsInfo();
        this.info = metricsInfo;
        metricsInfo.add(new PlaybackEvent.Builder(PlaybackEventType.EVENT_MEDIA_ITEM_KNOWN).mediaItem((MediaItem) Validate.notNull(mediaItem)).build());
        metricsInfo.set(PlaybackEvent.PROPERTY_PAGE_TYPE, mediaItem.getPlaybackPageType());
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void resetMaxPosition() {
        this.maxPosition = 0L;
    }

    public boolean sentStreamingInitiated() {
        return this.sentStreamingInitiated;
    }

    public void setSentStreamingInitiated(boolean z) {
        this.sentStreamingInitiated = z;
    }

    public void onMetricsEvent(@NonNull MetricsEvent metricsEvent) {
        PlaybackEvent playbackEvent;
        Sender sender;
        this.logger.debug("onMetricsEvent(" + metricsEvent + ")");
        if (this.terminated) {
            StrictMode.crashIfStrict("onMetricsEvent(" + metricsEvent + ") after EVENT_PLAYBACK_TERMINATED received.");
            return;
        }
        if (this.playbackStartedEvent != null && this.playbackExpectedEvent == null && metricsEvent.getEventTimeMillis() - this.playbackStartedEvent.getEventTimeMillis() > MAX_SPURIOUS_PLAYBACK_MILLIS) {
            StrictMode.crashIfStrict("Playback has been started for " + StringUtils.getDurationString(metricsEvent.getEventTimeMillis() - this.playbackStartedEvent.getEventTimeMillis()) + " without being expected! - current event: " + metricsEvent);
        } else if (this.playbackNotExpectedEvent != null && metricsEvent.getEventTimeMillis() - this.playbackNotExpectedEvent.getEventTimeMillis() > MAX_SPURIOUS_PLAYBACK_MILLIS) {
            StrictMode.crashIfStrict("Playback has been continuing for " + StringUtils.getDurationString(metricsEvent.getEventTimeMillis() - this.playbackNotExpectedEvent.getEventTimeMillis()) + " after no longer being expected! - current event: " + metricsEvent);
        }
        this.info.add(metricsEvent);
        MetricsEventType<?> eventType = metricsEvent.getEventType();
        if (eventType instanceof PlaybackEventType) {
            PlaybackEventType playbackEventType = (PlaybackEventType) eventType;
            handleEvent(playbackEventType, (PlaybackEvent) metricsEvent.castFor(playbackEventType));
        }
        if (this.sentStreamingInitiated || this.playbackExpectedEvent == null || (playbackEvent = this.playbackStartedEvent) == null || (sender = this.sender) == null) {
            return;
        }
        this.sentStreamingInitiated = true;
        sender.sendStreamingInitiated(this.info, getMediaLink(playbackEvent), this.initialStartDelay, this.initialStartTime, this.playbackExpectedEvent.getChangeReason());
    }

    private void handleEvent(PlaybackEventType playbackEventType, PlaybackEvent playbackEvent) {
        this.logger.debug("handleEvent(" + playbackEventType + ", " + playbackEvent + ")");
        boolean z = false;
        if (this.canUpdateAmountCached) {
            float amountCached = playbackEvent.getAmountCached();
            this.amountCached = amountCached;
            if (amountCached != -1.0f) {
                this.canUpdateAmountCached = false;
            } else {
                switch (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[playbackEventType.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        this.canUpdateAmountCached = false;
                        break;
                }
            }
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[playbackEventType.ordinal()];
        if (i == 5) {
            if (this.initialStartTime == 0) {
                this.initialStartTime = playbackEvent.getEventTimeMillis();
            }
            if (this.playbackStartedEvent != null) {
                this.logger.error("onMetricsEvent(" + playbackEvent + ") called while already playing");
            } else if (this.playbackExpectedEvent == null) {
                onPlaybackStartedBeforeExpecting(playbackEvent);
            } else if (this.playbackStoppedEvent != null) {
                onPlaybackStartedAgain(playbackEvent);
            } else {
                onPlaybackStartedAfterExpecting(playbackEvent);
            }
            this.mediaLink = (MediaLink) this.info.get(PlaybackEvent.PROPERTY_MEDIA_LINK);
        }
        if (i == 7) {
            if (this.playbackExpectedEvent != null && this.playbackNotExpectedEvent == null && (playbackEvent.getChangeReason() == ChangeReason.TIME_SEEK || playbackEvent.getChangeReason() == ChangeReason.LYRICS_SEEK || playbackEvent.getChangeReason() == ChangeReason.FAST_FORWARD || playbackEvent.getChangeReason() == ChangeReason.REWIND || playbackEvent.getChangeReason() == ChangeReason.SWITCHING_PLAYERS || playbackEvent.getChangeReason() == ChangeReason.TOGGLE)) {
                z = true;
            }
            if (z) {
                handleEvent(PlaybackEventType.EVENT_PLAYBACK_NOT_EXPECTED, playbackEvent);
            }
            if (this.playbackStartedEvent == null) {
                StrictMode.crashIfStrict("onMetricsEvent(" + playbackEvent + ") called while not playing");
            } else if (this.playbackNotExpectedEvent != null) {
                onPlaybackStoppedAfterNotExpecting(playbackEvent);
            } else if (this.playbackExpectedEvent != null) {
                onPlaybackStoppedWhileExpecting(playbackEvent);
            } else {
                onPlaybackNeverExpected(playbackEvent);
            }
            if (z) {
                handleEvent(PlaybackEventType.EVENT_PLAYBACK_EXPECTED, playbackEvent);
                return;
            }
            return;
        }
        switch (i) {
            case 12:
                PlaybackEvent playbackEvent2 = this.playbackExpectedEvent;
                ChangeReason changeReason = playbackEvent2 != null ? playbackEvent2.getChangeReason() : getStartReason();
                if (this.playbackExpectedEvent != null && this.playbackNotExpectedEvent == null) {
                    handleEvent(PlaybackEventType.EVENT_PLAYBACK_NOT_EXPECTED, playbackEvent);
                }
                if (this.playbackStartedEvent != null) {
                    handleEvent(PlaybackEventType.EVENT_PLAYBACK_PAUSED, playbackEvent);
                }
                onPlaybackTerminated(playbackEvent, changeReason);
                break;
            case 13:
                if (this.playbackExpectedEvent != null && this.playbackNotExpectedEvent == null) {
                    StrictMode.crashIfStrict("onMetricsEvent(" + playbackEvent + ") called while already expecting playback.");
                } else if (this.playbackNotExpectedEvent != null) {
                    onPlaybackExpectedAgain(playbackEvent);
                } else if (this.playbackStartedEvent != null) {
                    onPlaybackExpectedAfterPlaying(playbackEvent);
                } else {
                    onPlaybackExpectedWhileStopped(playbackEvent);
                }
                break;
            case 14:
                if (this.playbackExpectedEvent == null || this.playbackNotExpectedEvent != null) {
                    StrictMode.crashIfStrict("onMetricsEvent(" + playbackEvent + ") called while not expecting playback.");
                } else if (this.playbackStartedEvent != null) {
                    onPlaybackNotExpectedWhilePlaying(playbackEvent);
                } else if (this.playbackStoppedEvent != null) {
                    onPlaybackNotExpectedWhileBuffering(playbackEvent);
                } else {
                    onPlaybackDidNotStart(playbackEvent);
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.metrics.PlaybackMetricsHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType;

        static {
            int[] iArr = new int[PlaybackEventType.values().length];
            $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType = iArr;
            try {
                iArr[PlaybackEventType.EVENT_PLAYBACK_ERROR_LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_PREPARING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_PREPARED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_ERROR_PREPARING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_PLAYING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_ERROR_PLAYING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_PAUSED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_SEEK_START.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_SEEK_END.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_ERROR_SEEKING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_ERROR_BUFFERING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_TERMINATED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_EXPECTED.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$metrics$PlaybackEventType[PlaybackEventType.EVENT_PLAYBACK_NOT_EXPECTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private ChangeReason getStartReason() {
        List<MetricsEvent> eventsSnapshot = this.info.getEventsSnapshot();
        ListIterator<MetricsEvent> listIterator = eventsSnapshot.listIterator(eventsSnapshot.size());
        while (listIterator.hasPrevious()) {
            MetricsEvent metricsEventPrevious = listIterator.previous();
            if (metricsEventPrevious.getEventType() == PlaybackEventType.EVENT_PLAYBACK_EXPECTED) {
                return ((PlaybackEvent) metricsEventPrevious).getChangeReason();
            }
        }
        return ChangeReason.ERROR;
    }

    private void reset() {
        this.playbackExpectedEvent = null;
        this.playbackNotExpectedEvent = null;
        this.playbackStartedEvent = null;
        this.playbackStoppedEvent = null;
        this.deletedDuration += this.playbackDuration;
        this.playbackDuration = 0L;
        this.initialStartDelay = 0L;
        this.bufferingCount = 0;
        this.canUpdateAmountCached = true;
    }

    private MediaLink getMediaLink(PlaybackEvent... playbackEventArr) {
        MediaLink mediaLink;
        for (PlaybackEvent playbackEvent : playbackEventArr) {
            if (playbackEvent != null && (mediaLink = playbackEvent.getMediaLink()) != null) {
                return mediaLink;
            }
        }
        return (MediaLink) this.info.get(PlaybackEvent.PROPERTY_MEDIA_LINK);
    }

    private void onPlaybackExpectedWhileStopped(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackExpectedWhileStopped()");
        this.playbackExpectedEvent = playbackEvent;
    }

    private void onPlaybackExpectedAfterPlaying(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackExpectedAfterPlaying()");
        this.playbackExpectedEvent = playbackEvent;
    }

    private void onPlaybackExpectedAgain(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackNotExpectedWhilePlaying()");
        this.playbackNotExpectedEvent = null;
    }

    private void onPlaybackNotExpectedWhileBuffering(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackNotExpectedWhileBuffering()");
        long eventTimeMillis = playbackEvent.getEventTimeMillis() - this.playbackStoppedEvent.getEventTimeMillis();
        if (eventTimeMillis > 100) {
            this.bufferingCount++;
            Sender sender = this.sender;
            if (sender != null) {
                sender.sendStreamingRequiredRebuffering(this.info, this.mediaLink, eventTimeMillis, this.playbackStoppedEvent.getPositionMillis(), this.playbackStoppedEvent.getTransferSpeedBytesPerSecond(), this.amountCached);
            }
        }
        Sender sender2 = this.sender;
        if (sender2 != null) {
            sender2.sendStreamingTerminated(this.info, getMediaLink(this.playbackStoppedEvent, playbackEvent), this.initialStartDelay, this.initialStartTime, this.playbackDuration, this.bufferingCount, this.playbackStoppedEvent.getTransferSpeedBytesPerSecond(), this.amountCached, playbackEvent.getChangeReason(), this.playbackStoppedEvent.getPositionMillis());
        }
        reset();
    }

    private void onPlaybackNotExpectedWhilePlaying(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackNotExpectedWhilePlaying()");
        this.playbackNotExpectedEvent = playbackEvent;
    }

    private void onPlaybackDidNotStart(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackDidNotStart()");
        reset();
    }

    private void onPlaybackStartedBeforeExpecting(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackStartedBeforeExpecting()");
        this.playbackStartedEvent = playbackEvent;
    }

    private void onPlaybackStartedAfterExpecting(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackStartedAfterExpecting()");
        this.playbackStartedEvent = playbackEvent;
        this.initialStartDelay = playbackEvent.getEventTimeMillis() - this.playbackExpectedEvent.getEventTimeMillis();
    }

    private void onPlaybackStartedAgain(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackStartedAgain()");
        long eventTimeMillis = playbackEvent.getEventTimeMillis() - this.playbackStoppedEvent.getEventTimeMillis();
        if (eventTimeMillis > 0) {
            this.bufferingCount++;
            Sender sender = this.sender;
            if (sender != null) {
                sender.sendStreamingRequiredRebuffering(this.info, this.mediaLink, eventTimeMillis, this.playbackStoppedEvent.getPositionMillis(), this.playbackStoppedEvent.getTransferSpeedBytesPerSecond(), this.amountCached);
            }
        }
        this.playbackStartedEvent = playbackEvent;
        this.playbackStoppedEvent = null;
    }

    private void onPlaybackStoppedAfterNotExpecting(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackStoppedAfterNotExpecting()");
        this.playbackDuration += playbackEvent.getPositionMillis() - this.playbackStartedEvent.getPositionMillis();
        this.maxPosition = Math.max(playbackEvent.getPositionMillis(), this.maxPosition);
        Sender sender = this.sender;
        if (sender != null) {
            sender.sendStreamingTerminated(this.info, getMediaLink(this.playbackStartedEvent, playbackEvent), this.initialStartDelay, this.initialStartTime, this.playbackDuration, this.bufferingCount, playbackEvent.getTransferSpeedBytesPerSecond(), this.amountCached, this.playbackNotExpectedEvent.getChangeReason(), playbackEvent.getPositionMillis());
        }
        reset();
    }

    private void onPlaybackStoppedWhileExpecting(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackStoppedWhileExpecting()");
        this.playbackDuration += playbackEvent.getPositionMillis() - this.playbackStartedEvent.getPositionMillis();
        this.maxPosition = Math.max(playbackEvent.getPositionMillis(), this.maxPosition);
        this.playbackStartedEvent = null;
        this.playbackStoppedEvent = playbackEvent;
    }

    private void onPlaybackNeverExpected(PlaybackEvent playbackEvent) {
        this.logger.debug("onPlaybackNeverExpected()");
        reset();
    }

    private void onPlaybackTerminated(PlaybackEvent playbackEvent, ChangeReason changeReason) {
        this.logger.debug("onPlaybackTerminated()");
        long j = this.playbackDuration + this.deletedDuration;
        Sender sender = this.sender;
        if (sender != null) {
            long j2 = this.maxPosition;
            long j3 = MIN_PLAYBACK_DURATION;
            if (j2 > j3 && j > j3) {
                sender.sendTrackStreamed(this.info, j2, playbackEvent.getChangeReason());
            } else if (playbackEvent.getChangeReason() == ChangeReason.ERROR) {
                this.sender.sendStreamingInitiationFailed(this.info, getMediaLink(this.playbackStartedEvent, this.playbackStoppedEvent, playbackEvent), changeReason, playbackEvent.getPlaybackException());
            }
        }
    }

    public MetricsInfo getMetricsInfo() {
        return this.info;
    }
}

package com.amazon.music.media.playback.player.impl;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.metrics.PlaybackEventType;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.util.StrictMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlayRequestStatusHandler {
    private final Logger logger = LoggerFactory.getLogger(PlayRequestStatusHandler.class.getSimpleName());

    /* JADX INFO: renamed from: com.amazon.music.media.playback.player.impl.PlayRequestStatusHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus;

        static {
            int[] iArr = new int[PlayRequestStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus = iArr;
            try {
                iArr[PlayRequestStatus.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.SEEKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.NOT_STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.BUFFERING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.RENDERING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.TERMINATED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    protected PlaybackEventType[] onNotStarted(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_END};
        }
        this.logger.error("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.NOT_STARTED + ".");
        return null;
    }

    protected PlaybackEventType[] onPaused(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 2) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_END};
        }
        if (i == 4) {
            return null;
        }
        if (i == 6) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_PAUSED};
        }
        StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.PAUSED + ".");
        return null;
    }

    protected PlaybackEventType[] onPreparing(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 2) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_END, PlaybackEventType.EVENT_PLAYBACK_PREPARING};
        }
        if (i == 3) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_PREPARING};
        }
        StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.PREPARING + ".");
        return null;
    }

    protected PlaybackEventType[] onSeeking(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 1 || i == 3 || i == 4 || i == 5) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_START};
        }
        if (i == 6) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_PAUSED, PlaybackEventType.EVENT_PLAYBACK_SEEK_START};
        }
        StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.SEEKING + ".");
        return null;
    }

    protected PlaybackEventType[] onBuffering(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 2) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_END};
        }
        if (i == 5) {
            return null;
        }
        if (i == 6) {
            return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_PAUSED};
        }
        StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.BUFFERING + ".");
        return null;
    }

    protected PlaybackEventType[] onRendering(PlayRequest playRequest) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_SEEK_END, PlaybackEventType.EVENT_PLAYBACK_PLAYING};
            }
            if (i != 3 && i != 4 && i != 5) {
                StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.RENDERING + ".");
                return null;
            }
        }
        return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_PLAYING};
    }

    protected PlaybackEventType[] onTerminated(PlayRequest playRequest) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new PlaybackEventType[]{PlaybackEventType.EVENT_PLAYBACK_TERMINATED};
            default:
                StrictMode.crashIfStrict("Cannot transition from " + playRequest.getPlayRequestStatus() + " to " + PlayRequestStatus.TERMINATED + ".");
                return null;
        }
    }

    protected PlaybackEventType onError(PlayRequest playRequest, PlayRequestStatus playRequestStatus, PlaybackException playbackException) {
        if (playRequestStatus.shouldBePlaying()) {
            StrictMode.crashIfStrict("newStatus for error cannot be one where shouldBePlaying() returns true, but is " + playRequestStatus, playbackException);
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequest.getPlayRequestStatus().ordinal()];
        if (i == 1) {
            return PlaybackEventType.EVENT_PLAYBACK_ERROR_PREPARING;
        }
        if (i == 2) {
            return PlaybackEventType.EVENT_PLAYBACK_ERROR_SEEKING;
        }
        if (i == 4) {
            return PlaybackEventType.EVENT_PLAYBACK_ERROR_BUFFERING;
        }
        if (i == 6) {
            return PlaybackEventType.EVENT_PLAYBACK_ERROR_PLAYING;
        }
        this.logger.error("Cannot have an error while in state " + playRequest.getPlayRequestStatus(), (Throwable) playbackException);
        return null;
    }

    public void setMediaLink(@NonNull PlayRequest playRequest, MediaLink mediaLink) {
        if (mediaLink != playRequest.getMediaLink()) {
            playRequest.setMediaLink(mediaLink);
            PlaybackEvent.Builder builder = new PlaybackEvent.Builder(PlaybackEventType.EVENT_MEDIA_LINK_SELECTED);
            builder.mediaItem(playRequest.getMediaItem()).mediaLink(mediaLink);
            playRequest.getMetricsHandler().onMetricsEvent(builder.build());
        }
    }

    public void expectPlayback(@NonNull PlayRequest playRequest, long j, ChangeReason changeReason, ControlSource controlSource) {
        playRequest.getMetricsHandler().onMetricsEvent(new PlaybackEvent.Builder(PlaybackEventType.EVENT_PLAYBACK_EXPECTED).changeReason(changeReason).controlSource(controlSource).eventTimeMillis(j).build());
    }

    public void expectNoPlayback(@NonNull PlayRequest playRequest, long j, ChangeReason changeReason, ControlSource controlSource) {
        if (playRequest.getPlayRequestStatus() != PlayRequestStatus.TERMINATED) {
            playRequest.getMetricsHandler().onMetricsEvent(new PlaybackEvent.Builder(PlaybackEventType.EVENT_PLAYBACK_NOT_EXPECTED).changeReason(changeReason).controlSource(controlSource).eventTimeMillis(j).build());
        }
    }

    public void setPlayRequestStatus(@NonNull PlayRequest playRequest, @NonNull PlayRequestStatus playRequestStatus, long j, long j2, float f, PlaybackException playbackException, ChangeReason changeReason, ControlSource controlSource) {
        PlaybackEventType[] playbackEventTypeArrOnPreparing;
        PlaybackEventType playbackEventTypeOnError;
        ChangeReason changeReason2 = changeReason;
        this.logger.info("setPlayRequestStatus(" + playRequest + ", " + playRequestStatus + ", " + changeReason2 + ")");
        if (playRequestStatus == PlayRequestStatus.RENDERING) {
            playRequest.getPositionTracker().start(j);
        } else {
            playRequest.getPositionTracker().pause(j);
        }
        PlayRequestStatus playRequestStatus2 = playRequest.getPlayRequestStatus();
        PlayRequestStatus playRequestStatus3 = PlayRequestStatus.TERMINATED;
        if (playRequestStatus2 == playRequestStatus3) {
            PlayRequestStatus playRequestStatus4 = PlayRequestStatus.NOT_STARTED;
            if (playRequestStatus == playRequestStatus4) {
                playRequest.resetMetrics();
                playRequest.setPlayRequestStatus(playRequestStatus4, j);
                return;
            } else {
                if (playRequestStatus != playRequestStatus3) {
                    StrictMode.crashIfStrict(this + " is already terminated - cannot set to " + playRequestStatus, playbackException);
                    return;
                }
                return;
            }
        }
        if (changeReason2 == null) {
            if (playbackException == null) {
                changeReason2 = null;
            } else if (playbackException instanceof ConcurrencyException) {
                changeReason2 = ChangeReason.RESOLVING_CONCURRENCY;
            } else {
                changeReason2 = ChangeReason.ERROR;
            }
        }
        if (playbackException != null && (playbackEventTypeOnError = onError(playRequest, playRequestStatus, playbackException)) != null) {
            PlaybackEvent.Builder builder = new PlaybackEvent.Builder(playbackEventTypeOnError);
            builder.playbackException(playbackException).mediaItem(playRequest.getMediaItem()).mediaLink(playRequest.getMediaLink()).positionMillis(j).changeReason(changeReason2).controlSource(controlSource).eventTimeMillis(j2);
            playRequest.getMetricsHandler().onMetricsEvent(builder.build());
        }
        if (playRequest.getPlayRequestStatus() != playRequestStatus) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[playRequestStatus.ordinal()]) {
                case 1:
                    playbackEventTypeArrOnPreparing = onPreparing(playRequest);
                    break;
                case 2:
                    playbackEventTypeArrOnPreparing = onSeeking(playRequest);
                    break;
                case 3:
                    playbackEventTypeArrOnPreparing = onNotStarted(playRequest);
                    break;
                case 4:
                    playbackEventTypeArrOnPreparing = onBuffering(playRequest);
                    break;
                case 5:
                    playbackEventTypeArrOnPreparing = onPaused(playRequest);
                    break;
                case 6:
                    playbackEventTypeArrOnPreparing = onRendering(playRequest);
                    break;
                case 7:
                    playbackEventTypeArrOnPreparing = onTerminated(playRequest);
                    break;
                default:
                    throw new UnsupportedOperationException("A new PlayRequestState was added - need to add handling to PlayRequest.setPlayRequestStatus().");
            }
            if (playbackEventTypeArrOnPreparing != null) {
                for (PlaybackEventType playbackEventType : playbackEventTypeArrOnPreparing) {
                    PlaybackEvent.Builder builder2 = new PlaybackEvent.Builder(playbackEventType);
                    builder2.playbackException(playbackException).mediaItem(playRequest.getMediaItem()).mediaLink(playRequest.getMediaLink()).positionMillis(j).changeReason(changeReason2).controlSource(controlSource).eventTimeMillis(j2).amountCached(f);
                    playRequest.getMetricsHandler().onMetricsEvent(builder2.build());
                }
            }
        }
        playRequest.setPlayRequestStatus(playRequestStatus, j);
    }
}

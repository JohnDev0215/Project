package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackException extends Exception {
    private final boolean autoRetry;
    private final MediaItem mediaItem;
    private final String metricsName;
    private final PlaybackError playbackError;
    private final Recoverability recoverability;

    public enum PlaybackError {
        NetworkFailure(10, "Network error"),
        InvalidFile(1, "Invalid file"),
        SignedOut(3, "Signed out"),
        InsufficientStorage(10, "Insufficient storage"),
        Payment(10, "Payment error"),
        RegistrationError(3, "Registration error"),
        TOU(3, "TOU error"),
        ConnectionLimit(5, "Connection limit reached"),
        Cirrus(1, "Cirrus error"),
        InvalidParameter(1, "Invalid parameter"),
        StreamingPlayback(1, "Streaming playback error"),
        StreamingPlaybackLocal(1, "Streaming playback local error"),
        StreamingPlaybackRemote(1, "Streaming playback remote error"),
        StreamingServiceError(10, "Streaming service error"),
        StationNotFound(7, "Station not found"),
        StationCannotStart(10, "Station cannot start"),
        StationCannotContinue(10, "Station cannot continue"),
        StationEnd(11, "Station end"),
        StationOutOfTracks(11, "Station out of tracks"),
        AudioFocus(10, "Audio focus error"),
        FileNotFound(1, "File not found"),
        LocalFileNotFound(1, "Local file not found"),
        DrmFileNotFound(1, "Drm file not found"),
        DrmLicenseExpired(3, "Drm license expired"),
        StreamingRestriction(2700, "Streaming restriction error"),
        StreamingVideoRestriction(1, "Streaming video restriction"),
        GenericStreaming(1, "Generic streaming error"),
        GenericDrm(1, "Generic Drm error"),
        GenericProgressivePlayer(1, "Generic progressive player error"),
        GenericLocalPlayer(1, "Generic local player error"),
        GenericHLSPlayer(1, "Generic HLS player error"),
        GenericChromecastPlayer(1, "Generic chromecast player error"),
        ChromecastPlayerHttp(1, "Chromecast player http error"),
        PrimeStreamingConcurrency(5, "Prime streaming concurrency limit reached"),
        ContentNotEligible(7, "Content not eligible"),
        CustomerNotEligible(4, "Customer not eligible"),
        DMLS(10, "DMLS error"),
        ExpiredUrl(10, "Expired url"),
        BufferUnderrun(1, "Buffer underrun"),
        SecondaryStorageNotFound(10, "Secondary storage not found"),
        DeviceRooted(10, "Device rooted"),
        ConcurrentStreamLimit(5, "Concurrent stream limit reached"),
        ThrottlingLimitReached(5, "Throttling limit reached"),
        QueueTerminated(1, "Queue terminated"),
        UnknownFailure(0, "Unknown failure"),
        DeviceOffline(10, "Device offline"),
        ReachEndOfQueue(11, "Reached end of queue"),
        InvalidStationSeed(10, "Invalid station seed"),
        VideoPlayerInit(1, "Video player init error"),
        VideoPlayerToken(1, "Video player token error"),
        VideoPlayerContent(10, "Video player content error"),
        VideoPlayerGeneric(1, "Video player generic error");

        private final int mErrorCode;
        private final String mErrorDescription;
        private Exception mException;

        PlaybackError() {
            this.mException = new Exception();
            this.mErrorCode = 1;
            this.mErrorDescription = "Playback error";
        }

        PlaybackError(int i, String str) {
            this.mException = new Exception();
            this.mErrorCode = i;
            this.mErrorDescription = str;
        }

        private void setException(Exception exc) {
            this.mException = exc;
        }

        public Exception getException() {
            return this.mException;
        }

        public boolean shouldNotify() {
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[ordinal()];
            return (i == 1 || i == 2) ? false : true;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }

        public String getErrorDescription() {
            return this.mErrorDescription;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.PlaybackException$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError;

        static {
            int[] iArr = new int[PlaybackError.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError = iArr;
            try {
                iArr[PlaybackError.ExpiredUrl.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackError.BufferUnderrun.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum Recoverability {
        MEDIA_ITEM(true, true, true, true),
        COLLECTION(false, true, true, true),
        PLAYER(false, false, true, true),
        CONTROLLER(true, true, false, true),
        UNRECOVERABLE(false, false, false, false);

        private final boolean collectionRecoverable;
        private final boolean controllerRecoverable;
        private final boolean mediaItemRecoverable;
        private final boolean playerRecoverable;

        Recoverability(boolean z, boolean z2, boolean z3, boolean z4) {
            this.mediaItemRecoverable = z;
            this.collectionRecoverable = z2;
            this.playerRecoverable = z3;
            this.controllerRecoverable = z4;
        }

        public boolean canRecoverMediaItem() {
            return this.mediaItemRecoverable;
        }

        public boolean canRecoverCollection() {
            return this.collectionRecoverable;
        }

        public boolean canRecoverController() {
            return this.controllerRecoverable;
        }

        public boolean canRecoverPlayer() {
            return this.playerRecoverable;
        }
    }

    public static class Config {
        private boolean autoRetry;
        private String description;
        private MediaItem mediaItem;
        private String metricsName;
        private PlaybackError playbackError;
        private final Recoverability recoverability;
        private Throwable source;

        public Config(@NonNull Recoverability recoverability) {
            if (recoverability == null) {
                StrictMode.crashIfStrict("recoverability was not set when constructing PlaybackException.Config");
                this.recoverability = Recoverability.MEDIA_ITEM;
            } else {
                this.recoverability = recoverability;
            }
        }

        public Config mediaItem(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            return this;
        }

        public Config autoRetry(boolean z) {
            this.autoRetry = z;
            return this;
        }

        public Config description(String str) {
            this.description = str;
            return this;
        }

        public Config source(Throwable th) {
            this.source = th;
            return this;
        }

        public Config playbackError(PlaybackError playbackError) {
            this.playbackError = playbackError;
            return this;
        }

        public Config metricsName(String str) {
            this.metricsName = str;
            return this;
        }
    }

    public PlaybackException(@NonNull Config config) {
        super(config.description, config.source);
        this.recoverability = config.recoverability;
        this.mediaItem = config.mediaItem;
        this.autoRetry = config.autoRetry;
        PlaybackError playbackError = config.playbackError;
        this.playbackError = playbackError;
        if (!StringUtils.isEmpty(config.metricsName)) {
            this.metricsName = config.metricsName;
            return;
        }
        if (playbackError != null) {
            this.metricsName = getClass().getSimpleName() + "-" + config.playbackError;
            return;
        }
        this.metricsName = getClass().getSimpleName();
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public boolean canAutoRetry() {
        return this.autoRetry;
    }

    public PlaybackError getPlaybackError() {
        return this.playbackError;
    }

    @NonNull
    public Recoverability getRecoverability() {
        return this.recoverability;
    }

    @NonNull
    public String getMetricsName() {
        return this.metricsName;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + "{message=" + getMessage() + ", recoverability=" + this.recoverability + ", autoRetry=" + this.autoRetry + ", mediaItem=" + this.mediaItem + ", playbackError=" + this.playbackError + ", metricsName=" + this.metricsName + '}';
    }
}

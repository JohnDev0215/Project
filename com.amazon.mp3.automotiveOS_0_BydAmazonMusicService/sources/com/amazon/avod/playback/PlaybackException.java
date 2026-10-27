package com.amazon.avod.playback;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackException extends MediaException {
    private static final long serialVersionUID = -5088581197616114693L;
    private final long mMediaTimeUs;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'RENDERER_DECRYPTION_FAILURE' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class PlaybackError implements MediaInternalErrorCode {
        private static final /* synthetic */ PlaybackError[] $VALUES;
        public static final PlaybackError AUTO_EVAL_CANARY_FAILURE;
        public static final PlaybackError AUTO_EVAL_POOR_PLAYER_PERFORMANCE;
        public static final PlaybackError CONTENT_BUFFERING_NO_DATA_CONNECTION;
        public static final PlaybackError CORRUPT_FRAGMENT_ERROR;
        public static final PlaybackError DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED;
        public static final PlaybackError HARDWARE_ACCELERATION_TOGGLE;
        public static final PlaybackError INTERNAL_FATAL_ERROR;
        public static final PlaybackError NATIVE_PLAYBACK_ERROR;
        public static final PlaybackError PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED;
        public static final PlaybackError RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER;
        public static final PlaybackError RENDERER_AUDIOTRACK_IS_DEAD;
        public static final PlaybackError RENDERER_CONFIGURE_WITH_INVALID_SURFACE;
        public static final PlaybackError RENDERER_DECODER_STALLED;
        public static final PlaybackError RENDERER_DECRYPTION_FAILURE;
        public static final PlaybackError RENDERER_DECRYPTION_FAILURE_EXPIRED_KEY;
        public static final PlaybackError RENDERER_DECRYPTION_FAILURE_INSUFFICIENT_OUTPUT_PROTECTION;
        public static final PlaybackError RENDERER_DECRYPTION_FAILURE_MISSING_KEY;
        public static final PlaybackError RENDERER_DECRYPTION_FAILURE_UNSUPPORTED_OPERATION;
        public static final PlaybackError RENDERER_INITIALIZE_FAILED;
        public static final PlaybackError RENDERER_INITIALIZE_FAILED_CODEC_CONFIG;
        public static final PlaybackError RENDERER_INITIALIZE_INVALID_SURFACE;
        public static final PlaybackError RENDERER_INITIALIZE_NULL_SURFACE;
        public static final PlaybackError RENDERER_INITIALIZE_TIMEOUT;
        public static final PlaybackError RENDERER_VIDEO_SURFACE_RECREATED;
        public static final PlaybackError RENDERER_VIDEO_SURFACE_SWITCH_FAILED;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SRC_BUFFER_SIZE;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_DST_BUFFER_SIZE_TOO_SMALL;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_NAL_SIZE_EMPTY;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_DST_BUFFER_SIZE;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_SAMPLE_SIZE;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_SIZE_AFTER_COPY_GREATER_THAN_DST_BUFFER_SIZE;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_TOO_SMALL;
        public static final PlaybackError SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_ZERO;
        public static final PlaybackError UNEXPECTED_RENDERER_STATE;
        public static final PlaybackError UNHANDLED_SHUTDOWN_ACTION;
        private final MediaErrorCode mErrorCode;

        public static PlaybackError valueOf(String str) {
            return (PlaybackError) Enum.valueOf(PlaybackError.class, str);
        }

        public static PlaybackError[] values() {
            return (PlaybackError[]) $VALUES.clone();
        }

        static {
            PlaybackError playbackError = new PlaybackError("CONTENT_BUFFERING_NO_DATA_CONNECTION", 0, StandardErrorCode.DATA_CONNECTION_UNAVAILABLE);
            CONTENT_BUFFERING_NO_DATA_CONNECTION = playbackError;
            PlaybackError playbackError2 = new PlaybackError("PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED", 1, StandardErrorCode.STREAMING_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT);
            PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED = playbackError2;
            PlaybackError playbackError3 = new PlaybackError("DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED", 2, StandardErrorCode.DOWNLOAD_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT);
            DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED = playbackError3;
            PlaybackError playbackError4 = new PlaybackError("INTERNAL_FATAL_ERROR", 3);
            INTERNAL_FATAL_ERROR = playbackError4;
            PlaybackError playbackError5 = new PlaybackError("UNHANDLED_SHUTDOWN_ACTION", 4);
            UNHANDLED_SHUTDOWN_ACTION = playbackError5;
            PlaybackError playbackError6 = new PlaybackError("NATIVE_PLAYBACK_ERROR", 5, StandardErrorCode.NATIVE_PLAYBACK_ERROR);
            NATIVE_PLAYBACK_ERROR = playbackError6;
            PlaybackError playbackError7 = new PlaybackError("RENDERER_INITIALIZE_FAILED", 6);
            RENDERER_INITIALIZE_FAILED = playbackError7;
            PlaybackError playbackError8 = new PlaybackError("RENDERER_INITIALIZE_FAILED_CODEC_CONFIG", 7);
            RENDERER_INITIALIZE_FAILED_CODEC_CONFIG = playbackError8;
            PlaybackError playbackError9 = new PlaybackError("UNEXPECTED_RENDERER_STATE", 8);
            UNEXPECTED_RENDERER_STATE = playbackError9;
            PlaybackError playbackError10 = new PlaybackError("RENDERER_INITIALIZE_TIMEOUT", 9);
            RENDERER_INITIALIZE_TIMEOUT = playbackError10;
            PlaybackError playbackError11 = new PlaybackError("RENDERER_INITIALIZE_NULL_SURFACE", 10);
            RENDERER_INITIALIZE_NULL_SURFACE = playbackError11;
            PlaybackError playbackError12 = new PlaybackError("RENDERER_INITIALIZE_INVALID_SURFACE", 11);
            RENDERER_INITIALIZE_INVALID_SURFACE = playbackError12;
            StandardErrorCode standardErrorCode = StandardErrorCode.DECRYPTION_FAILURE;
            PlaybackError playbackError13 = new PlaybackError("RENDERER_DECRYPTION_FAILURE", 12, standardErrorCode);
            RENDERER_DECRYPTION_FAILURE = playbackError13;
            PlaybackError playbackError14 = new PlaybackError("RENDERER_DECRYPTION_FAILURE_MISSING_KEY", 13, StandardErrorCode.DECRYPTION_FAILURE_MISSING_KEY);
            RENDERER_DECRYPTION_FAILURE_MISSING_KEY = playbackError14;
            PlaybackError playbackError15 = new PlaybackError("RENDERER_DECRYPTION_FAILURE_EXPIRED_KEY", 14, StandardErrorCode.DECRYPTION_FAILURE_EXPIRED_KEY);
            RENDERER_DECRYPTION_FAILURE_EXPIRED_KEY = playbackError15;
            PlaybackError playbackError16 = new PlaybackError("RENDERER_DECRYPTION_FAILURE_INSUFFICIENT_OUTPUT_PROTECTION", 15, standardErrorCode);
            RENDERER_DECRYPTION_FAILURE_INSUFFICIENT_OUTPUT_PROTECTION = playbackError16;
            PlaybackError playbackError17 = new PlaybackError("RENDERER_DECRYPTION_FAILURE_UNSUPPORTED_OPERATION", 16, standardErrorCode);
            RENDERER_DECRYPTION_FAILURE_UNSUPPORTED_OPERATION = playbackError17;
            PlaybackError playbackError18 = new PlaybackError("RENDERER_CONFIGURE_WITH_INVALID_SURFACE", 17);
            RENDERER_CONFIGURE_WITH_INVALID_SURFACE = playbackError18;
            PlaybackError playbackError19 = new PlaybackError("RENDERER_AUDIOTRACK_IS_DEAD", 18);
            RENDERER_AUDIOTRACK_IS_DEAD = playbackError19;
            PlaybackError playbackError20 = new PlaybackError("RENDERER_VIDEO_SURFACE_RECREATED", 19);
            RENDERER_VIDEO_SURFACE_RECREATED = playbackError20;
            PlaybackError playbackError21 = new PlaybackError("RENDERER_VIDEO_SURFACE_SWITCH_FAILED", 20);
            RENDERER_VIDEO_SURFACE_SWITCH_FAILED = playbackError21;
            PlaybackError playbackError22 = new PlaybackError("RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER", 21);
            RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER = playbackError22;
            PlaybackError playbackError23 = new PlaybackError("RENDERER_DECODER_STALLED", 22);
            RENDERER_DECODER_STALLED = playbackError23;
            StandardErrorCode standardErrorCode2 = StandardErrorCode.SAMPLE_ERROR;
            PlaybackError playbackError24 = new PlaybackError("CORRUPT_FRAGMENT_ERROR", 23, standardErrorCode2);
            CORRUPT_FRAGMENT_ERROR = playbackError24;
            PlaybackError playbackError25 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_DST_BUFFER_SIZE_TOO_SMALL", 24, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_DST_BUFFER_SIZE_TOO_SMALL = playbackError25;
            PlaybackError playbackError26 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_TOO_SMALL", 25, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_TOO_SMALL = playbackError26;
            PlaybackError playbackError27 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_ZERO", 26, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_ZERO = playbackError27;
            PlaybackError playbackError28 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_NAL_SIZE_EMPTY", 27, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_NAL_SIZE_EMPTY = playbackError28;
            PlaybackError playbackError29 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_SAMPLE_SIZE", 28, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_SAMPLE_SIZE = playbackError29;
            PlaybackError playbackError30 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_DST_BUFFER_SIZE", 29, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_DST_BUFFER_SIZE = playbackError30;
            PlaybackError playbackError31 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_SIZE_AFTER_COPY_GREATER_THAN_DST_BUFFER_SIZE", 30, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_SIZE_AFTER_COPY_GREATER_THAN_DST_BUFFER_SIZE = playbackError31;
            PlaybackError playbackError32 = new PlaybackError("SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SRC_BUFFER_SIZE", 31, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SRC_BUFFER_SIZE = playbackError32;
            PlaybackError playbackError33 = new PlaybackError("SAMPLE_ADAPTION_FAILURE", 32, standardErrorCode2);
            SAMPLE_ADAPTION_FAILURE = playbackError33;
            PlaybackError playbackError34 = new PlaybackError("AUTO_EVAL_CANARY_FAILURE", 33, StandardErrorCode.AUTO_EVAL_CANARY_FAILURE);
            AUTO_EVAL_CANARY_FAILURE = playbackError34;
            PlaybackError playbackError35 = new PlaybackError("AUTO_EVAL_POOR_PLAYER_PERFORMANCE", 34, StandardErrorCode.AUTO_EVAL_POOR_PLAYER_PERFORMANCE);
            AUTO_EVAL_POOR_PLAYER_PERFORMANCE = playbackError35;
            PlaybackError playbackError36 = new PlaybackError("HARDWARE_ACCELERATION_TOGGLE", 35);
            HARDWARE_ACCELERATION_TOGGLE = playbackError36;
            $VALUES = new PlaybackError[]{playbackError, playbackError2, playbackError3, playbackError4, playbackError5, playbackError6, playbackError7, playbackError8, playbackError9, playbackError10, playbackError11, playbackError12, playbackError13, playbackError14, playbackError15, playbackError16, playbackError17, playbackError18, playbackError19, playbackError20, playbackError21, playbackError22, playbackError23, playbackError24, playbackError25, playbackError26, playbackError27, playbackError28, playbackError29, playbackError30, playbackError31, playbackError32, playbackError33, playbackError34, playbackError35, playbackError36};
        }

        private PlaybackError(String str, int i) {
            this(str, i, StandardErrorCode.PLAYBACK_RENDERER_ERROR);
        }

        private PlaybackError(String str, int i, MediaErrorCode mediaErrorCode) {
            this.mErrorCode = mediaErrorCode;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
        public MediaErrorCode getExternalCode() {
            return this.mErrorCode;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return String.format(Locale.US, "PlaybackError:%s", name());
        }
    }

    public PlaybackException(String str) {
        this(PlaybackError.INTERNAL_FATAL_ERROR, str);
    }

    public PlaybackException(Throwable th) {
        this(PlaybackError.INTERNAL_FATAL_ERROR, th);
    }

    public PlaybackException(PlaybackError playbackError, String str) {
        this(playbackError, str, null, 0L);
    }

    public PlaybackException(PlaybackError playbackError, String str, long j) {
        this(playbackError, str, null, j);
    }

    public PlaybackException(PlaybackError playbackError, Throwable th) {
        this(playbackError, null, th, 0L);
    }

    public PlaybackException(PlaybackError playbackError, String str, Throwable th) {
        this(playbackError, str, th, 0L);
    }

    public PlaybackException(PlaybackError playbackError, String str, Throwable th, long j) {
        super(playbackError, str, th);
        this.mMediaTimeUs = j;
    }

    public long getMediaTimeUs() {
        return this.mMediaTimeUs;
    }
}

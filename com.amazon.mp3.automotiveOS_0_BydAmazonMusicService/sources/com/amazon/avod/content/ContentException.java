package com.amazon.avod.content;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import java.net.URL;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentException extends MediaException {
    private final DownloadStatistics mDownloadStatistics;
    private final String mFailoverSource;
    private final boolean mIsPreCache;
    private final int mStatusCode;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'SERVICE_ERROR' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class ContentError implements MediaInternalErrorCode {
        private static final /* synthetic */ ContentError[] $VALUES;
        public static final ContentError CANNOT_GET_PROTECTION_HEADER;
        public static final ContentError CANNOT_GET_SAMPLE_ENCRYPTION_INFO;
        public static final ContentError CANNOT_WRITE_ADAPTED_SAMPLE;
        public static final ContentError CDN_ERROR;
        public static final ContentError DATA_CONNECTION_UNAVAILABLE_ERROR;
        public static final ContentError DISK_ERROR;
        public static final ContentError DISK_FULL;
        public static final ContentError DOWNLOAD_NOT_OWNED;
        public static final ContentError ERROR_LOADING_NATIVE_LIBRARIES;
        public static final ContentError EXPIRED_PLAYBACK_ENVELOPE;
        public static final ContentError FAILED_TO_PARSE_MANIFEST;
        public static final ContentError FILE_MISSING;
        public static final ContentError GAME_BLACKOUT;
        public static final ContentError HEURISTICS_CANCELLATION;
        public static final ContentError HTTP_PROXY_ERROR;
        public static final ContentError INVALID_BASE64_DATA;
        public static final ContentError INVALID_CONTENT_LENGTH;
        public static final ContentError INVALID_GEO_IP;
        public static final ContentError INVALID_PLAYBACK_ENVELOPE;
        public static final ContentError LICENSE_MISSING;
        public static final ContentError LIVE_SLOW_MANIFEST;
        public static final ContentError LIVE_STALE_MANIFEST;
        public static final ContentError LOWER_TIER_CONCURRENCY_LIMIT;
        public static final ContentError LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED;
        public static final ContentError LOW_MEMORY_ERROR;
        public static final ContentError MALFORMED_MANIFEST;
        public static final ContentError MEMORY_ACCESS_ERROR;
        public static final ContentError MISSING_PLAYBACK_ENVELOPE;
        public static final ContentError NETWORK_ERROR;
        public static final ContentError NO_AVAILABLE_DOWNLOAD_RIGHTS;
        public static final ContentError NO_AVAILABLE_ONLINE_STREAMS;
        public static final ContentError OVERLAPPING_FRAGMENT;
        public static final ContentError RETRY_STOP;
        public static final ContentError SAMPLE_TOO_LARGE;
        public static final ContentError SERVICE_CALL_LOAD_SHED;
        public static final ContentError SERVICE_ERROR;
        public static final ContentError SESSION_BEGIN_FAILED;
        public static final ContentError STREAMING_NOT_OWNED;
        public static final ContentError TEMPORARILY_UNAVAILABLE;
        public static final ContentError UNKNOWN_AUDIO_TRACK;
        public static final ContentError UNKNOWN_ERROR;
        public static final ContentError VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE;
        private final MediaErrorCode mExternalError;

        public static ContentError valueOf(String str) {
            return (ContentError) Enum.valueOf(ContentError.class, str);
        }

        public static ContentError[] values() {
            return (ContentError[]) $VALUES.clone();
        }

        static {
            ContentError contentError = new ContentError("LICENSE_MISSING", 0, DrmErrorCode.OFFLINE_LICENSE_MISSING);
            LICENSE_MISSING = contentError;
            ContentError contentError2 = new ContentError("CDN_ERROR", 1, StandardErrorCode.CDN_ERROR);
            CDN_ERROR = contentError2;
            ContentError contentError3 = new ContentError("NETWORK_ERROR", 2, StandardErrorCode.NETWORK_ERROR);
            NETWORK_ERROR = contentError3;
            ServiceErrorCode serviceErrorCode = ServiceErrorCode.SERVICE_ERROR;
            ContentError contentError4 = new ContentError("SERVICE_ERROR", 3, serviceErrorCode);
            SERVICE_ERROR = contentError4;
            ContentError contentError5 = new ContentError("MALFORMED_MANIFEST", 4, serviceErrorCode);
            MALFORMED_MANIFEST = contentError5;
            ContentError contentError6 = new ContentError("LIVE_STALE_MANIFEST", 5, serviceErrorCode);
            LIVE_STALE_MANIFEST = contentError6;
            ContentError contentError7 = new ContentError("LIVE_SLOW_MANIFEST", 6, serviceErrorCode);
            LIVE_SLOW_MANIFEST = contentError7;
            ContentError contentError8 = new ContentError("DISK_ERROR", 7, StandardErrorCode.DISK_IO_ERROR);
            DISK_ERROR = contentError8;
            ContentError contentError9 = new ContentError("OVERLAPPING_FRAGMENT", 8, StandardErrorCode.OVERLAPPING_FRAGMENT);
            OVERLAPPING_FRAGMENT = contentError9;
            ContentError contentError10 = new ContentError("LOW_MEMORY_ERROR", 9, StandardErrorCode.LOW_MEMORY_ERROR);
            LOW_MEMORY_ERROR = contentError10;
            ContentError contentError11 = new ContentError("MEMORY_ACCESS_ERROR", 10, StandardErrorCode.MEMORY_ACCESS_ERROR);
            MEMORY_ACCESS_ERROR = contentError11;
            ContentError contentError12 = new ContentError("DISK_FULL", 11, StandardErrorCode.DISK_FULL);
            DISK_FULL = contentError12;
            ContentError contentError13 = new ContentError("FILE_MISSING", 12, StandardErrorCode.FILE_MISSING);
            FILE_MISSING = contentError13;
            ContentError contentError14 = new ContentError("INVALID_CONTENT_LENGTH", 13, ServiceErrorCode.URL_ERROR);
            INVALID_CONTENT_LENGTH = contentError14;
            ContentError contentError15 = new ContentError("DOWNLOAD_NOT_OWNED", 14, ServiceErrorCode.DOWNLOAD_NOT_OWNED);
            DOWNLOAD_NOT_OWNED = contentError15;
            ContentError contentError16 = new ContentError("STREAMING_NOT_OWNED", 15, serviceErrorCode);
            STREAMING_NOT_OWNED = contentError16;
            ContentError contentError17 = new ContentError("NO_AVAILABLE_ONLINE_STREAMS", 16, ServiceErrorCode.NO_AVAILABLE_ONLINE_STREAMS);
            NO_AVAILABLE_ONLINE_STREAMS = contentError17;
            ContentError contentError18 = new ContentError("NO_AVAILABLE_DOWNLOAD_RIGHTS", 17, ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS);
            NO_AVAILABLE_DOWNLOAD_RIGHTS = contentError18;
            ContentError contentError19 = new ContentError("INVALID_GEO_IP", 18, ServiceErrorCode.INVALID_GEO_IP);
            INVALID_GEO_IP = contentError19;
            ContentError contentError20 = new ContentError("TEMPORARILY_UNAVAILABLE", 19, ServiceErrorCode.TEMPORARILY_UNAVAILABLE);
            TEMPORARILY_UNAVAILABLE = contentError20;
            ContentError contentError21 = new ContentError("SERVICE_CALL_LOAD_SHED", 20, serviceErrorCode);
            SERVICE_CALL_LOAD_SHED = contentError21;
            ContentError contentError22 = new ContentError("DATA_CONNECTION_UNAVAILABLE_ERROR", 21, StandardErrorCode.DATA_CONNECTION_UNAVAILABLE);
            DATA_CONNECTION_UNAVAILABLE_ERROR = contentError22;
            ContentError contentError23 = new ContentError("UNKNOWN_ERROR", 22);
            UNKNOWN_ERROR = contentError23;
            ContentError contentError24 = new ContentError("SESSION_BEGIN_FAILED", 23);
            SESSION_BEGIN_FAILED = contentError24;
            StandardErrorCode standardErrorCode = StandardErrorCode.SAMPLE_ERROR;
            ContentError contentError25 = new ContentError("CANNOT_GET_SAMPLE_ENCRYPTION_INFO", 24, standardErrorCode);
            CANNOT_GET_SAMPLE_ENCRYPTION_INFO = contentError25;
            ContentError contentError26 = new ContentError("SAMPLE_TOO_LARGE", 25, standardErrorCode);
            SAMPLE_TOO_LARGE = contentError26;
            ContentError contentError27 = new ContentError("CANNOT_WRITE_ADAPTED_SAMPLE", 26, standardErrorCode);
            CANNOT_WRITE_ADAPTED_SAMPLE = contentError27;
            ContentError contentError28 = new ContentError("INVALID_BASE64_DATA", 27, StandardErrorCode.DECRYPTION_FAILURE);
            INVALID_BASE64_DATA = contentError28;
            StandardErrorCode standardErrorCode2 = StandardErrorCode.MANIFEST_ERROR;
            ContentError contentError29 = new ContentError("UNKNOWN_AUDIO_TRACK", 28, standardErrorCode2);
            UNKNOWN_AUDIO_TRACK = contentError29;
            ContentError contentError30 = new ContentError("ERROR_LOADING_NATIVE_LIBRARIES", 29, StandardErrorCode.NATIVE_PLAYBACK_ERROR);
            ERROR_LOADING_NATIVE_LIBRARIES = contentError30;
            ContentError contentError31 = new ContentError("FAILED_TO_PARSE_MANIFEST", 30, standardErrorCode2);
            FAILED_TO_PARSE_MANIFEST = contentError31;
            ContentError contentError32 = new ContentError("RETRY_STOP", 31, serviceErrorCode);
            RETRY_STOP = contentError32;
            ContentError contentError33 = new ContentError("VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE", 32, standardErrorCode);
            VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE = contentError33;
            ContentError contentError34 = new ContentError("HTTP_PROXY_ERROR", 33, ServiceErrorCode.HTTP_PROXY_ERROR);
            HTTP_PROXY_ERROR = contentError34;
            ContentError contentError35 = new ContentError("GAME_BLACKOUT", 34, ServiceErrorCode.GAME_BLACKOUT);
            GAME_BLACKOUT = contentError35;
            ContentError contentError36 = new ContentError("CANNOT_GET_PROTECTION_HEADER", 35, serviceErrorCode);
            CANNOT_GET_PROTECTION_HEADER = contentError36;
            ContentError contentError37 = new ContentError("HEURISTICS_CANCELLATION", 36, StandardErrorCode.CONTENT_ERROR);
            HEURISTICS_CANCELLATION = contentError37;
            ContentError contentError38 = new ContentError("LOWER_TIER_CONCURRENCY_LIMIT", 37, ServiceErrorCode.LOWER_TIER_CONCURRENCY_LIMIT);
            LOWER_TIER_CONCURRENCY_LIMIT = contentError38;
            ContentError contentError39 = new ContentError("LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED", 38, ServiceErrorCode.LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED);
            LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED = contentError39;
            ContentError contentError40 = new ContentError("MISSING_PLAYBACK_ENVELOPE", 39, ServiceErrorCode.MISSING_PLAYBACK_ENVELOPE);
            MISSING_PLAYBACK_ENVELOPE = contentError40;
            ContentError contentError41 = new ContentError("EXPIRED_PLAYBACK_ENVELOPE", 40, ServiceErrorCode.EXPIRED_PLAYBACK_ENVELOPE);
            EXPIRED_PLAYBACK_ENVELOPE = contentError41;
            ContentError contentError42 = new ContentError("INVALID_PLAYBACK_ENVELOPE", 41, ServiceErrorCode.INVALID_PLAYBACK_ENVELOPE);
            INVALID_PLAYBACK_ENVELOPE = contentError42;
            $VALUES = new ContentError[]{contentError, contentError2, contentError3, contentError4, contentError5, contentError6, contentError7, contentError8, contentError9, contentError10, contentError11, contentError12, contentError13, contentError14, contentError15, contentError16, contentError17, contentError18, contentError19, contentError20, contentError21, contentError22, contentError23, contentError24, contentError25, contentError26, contentError27, contentError28, contentError29, contentError30, contentError31, contentError32, contentError33, contentError34, contentError35, contentError36, contentError37, contentError38, contentError39, contentError40, contentError41, contentError42};
        }

        private ContentError(String str, int i) {
            this(str, i, StandardErrorCode.CONTENT_ERROR);
        }

        private ContentError(String str, int i, MediaErrorCode mediaErrorCode) {
            this.mExternalError = mediaErrorCode;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
        public MediaErrorCode getExternalCode() {
            return this.mExternalError;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return String.format(Locale.US, "ContentError:%s", name());
        }
    }

    public ContentException(ContentError contentError) {
        this(contentError, (String) null);
    }

    public ContentException(String str) {
        this(ContentError.UNKNOWN_ERROR, str);
    }

    public ContentException(ContentError contentError, String str) {
        this(contentError, str, (Throwable) null);
    }

    public ContentException(ContentError contentError, String str, boolean z) {
        this(contentError, str, null, null, null, -1, null, z);
    }

    public ContentException(ContentError contentError, boolean z) {
        this(contentError, null, null, null, null, -1, null, z);
    }

    public ContentException(ContentError contentError, Throwable th) {
        this(contentError, (String) null, th);
    }

    public ContentException(ContentError contentError, Throwable th, @Nullable URL url) {
        this(contentError, null, th, url);
    }

    public ContentException(ContentError contentError, String str, Throwable th) {
        this(contentError, str, th, null);
    }

    public ContentException(ContentError contentError, String str, Throwable th, @Nullable URL url) {
        this(contentError, str, th, url, null, -1, null);
    }

    public ContentException(ContentError contentError, String str, Throwable th, @Nullable URL url, @Nullable String str2, int i, @Nullable DownloadStatistics downloadStatistics) {
        this(contentError, str, th, url, str2, i, downloadStatistics, false);
    }

    public ContentException(ContentError contentError, String str, Throwable th, @Nullable URL url, @Nullable String str2, int i, @Nullable DownloadStatistics downloadStatistics, boolean z) {
        super(contentError, str, th, url);
        this.mFailoverSource = str2;
        this.mStatusCode = i;
        this.mDownloadStatistics = downloadStatistics;
        this.mIsPreCache = z;
    }

    @Override // com.amazon.avod.media.framework.error.MediaException
    public ContentError getErrorCode() {
        return (ContentError) super.getErrorCode();
    }

    @Nullable
    public String getFailoverSource() {
        return this.mFailoverSource;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public boolean isPreCache() {
        return this.mIsPreCache;
    }

    @Nullable
    public DownloadStatistics getDownloadStatistics() {
        return this.mDownloadStatistics;
    }

    @Override // com.amazon.avod.media.framework.error.MediaException, java.lang.Throwable
    public final String toString() {
        return String.format(Locale.US, "%s | URL: %s", super.toString(), getUrl());
    }
}

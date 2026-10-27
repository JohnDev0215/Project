package com.amazon.avod.media.error;

/* JADX INFO: loaded from: classes2.dex */
public enum StandardErrorCode implements MediaErrorCode {
    UNKNOWN_ERROR(0),
    AD_ERROR(0),
    PLAYBACK_RENDERER_ERROR(0),
    CONTENT_ERROR(0),
    SAMPLE_ERROR(0),
    MANIFEST_ERROR(0),
    NATIVE_PLAYBACK_ERROR(0),
    DOWNLOAD_UNKNOWN_ERROR(0),
    DATA_CONNECTION_UNAVAILABLE(0),
    INSUFFICIENT_DISK_SPACE(0),
    NOT_ENTITLED(0),
    ANDROID_MEDIAPLAYER_ERROR(0),
    IMAGE_VIDEO_PLAYER_ERROR(0),
    NETWORK_ERROR(0),
    CDN_ERROR(0),
    DISK_IO_ERROR(0),
    OVERLAPPING_FRAGMENT(0),
    LOW_MEMORY_ERROR(0),
    MEMORY_ACCESS_ERROR(0),
    DISK_FULL(0),
    FILE_MISSING(0),
    DECRYPTION_FAILURE(0),
    DECRYPTION_FAILURE_MISSING_KEY(0),
    DECRYPTION_FAILURE_EXPIRED_KEY(0),
    MEDIA_EJECTED(0),
    DEVICE_REBOOT_REQUIRED(0),
    STREAMING_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT(0),
    DOWNLOAD_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT(0),
    AUTO_EVAL_CANARY_FAILURE(0),
    AUTO_EVAL_POOR_PLAYER_PERFORMANCE(0),
    PREPARE_FAILED_LOCK_NOT_HELD(0),
    PREPARE_FAILED_CACHE_FULL(0),
    MEDIA_EXCEPTION(0);

    private final int mNumber;

    StandardErrorCode(int i) {
        this.mNumber = i;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public String getName() {
        return name();
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public int getNumber() {
        return this.mNumber;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
    public String toReportableString() {
        return name();
    }
}

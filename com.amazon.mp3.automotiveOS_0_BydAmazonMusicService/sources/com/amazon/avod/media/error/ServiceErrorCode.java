package com.amazon.avod.media.error;

/* JADX INFO: loaded from: classes2.dex */
public enum ServiceErrorCode implements MediaErrorCode {
    SERVICE_ERROR(0),
    URL_ERROR(0),
    DOWNLOAD_NOT_OWNED(0),
    DOWNLOAD_NOT_OWNED_RENTAL(4091),
    DOWNLOAD_NOT_OWNED_PURCHASE(4092),
    DOWNLOAD_NOT_OWNED_NON_TVOD(4090),
    NO_AVAILABLE_DOWNLOAD_RIGHTS(0),
    NO_AVAILABLE_DOWNLOAD_RIGHTS_RENTAL(7212),
    NO_AVAILABLE_DOWNLOAD_RIGHTS_PRIME(7224),
    NO_AVAILABLE_DOWNLOAD_RIGHTS_PURCHASE(7213),
    NO_AVAILABLE_DOWNLOAD_RIGHTS_SUBSCRIPTION(7214),
    NO_AVAILABLE_ONLINE_STREAMS(0),
    INVALID_GEO_IP(4602),
    TEMPORARILY_UNAVAILABLE(0),
    CONCURRENCY_OFFLINE_ERROR(0),
    HTTP_PROXY_ERROR(0),
    GAME_BLACKOUT(0),
    LOWER_TIER_CONCURRENCY_LIMIT(7036),
    LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED(7039),
    MISSING_PLAYBACK_ENVELOPE(0),
    EXPIRED_PLAYBACK_ENVELOPE(0),
    INVALID_PLAYBACK_ENVELOPE(0);

    private final int mNumber;

    ServiceErrorCode(int i) {
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

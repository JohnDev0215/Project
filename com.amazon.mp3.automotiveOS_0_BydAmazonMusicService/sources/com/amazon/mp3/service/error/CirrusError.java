package com.amazon.mp3.service.error;

/* JADX INFO: loaded from: classes4.dex */
public enum CirrusError {
    GENERIC_EXCEPTION("anampGenericException"),
    INVALID_DATA_ERROR("anampInvalidDataError"),
    UNEXPECTED_SHUTDOWN("anampUnexpectedShutdownException"),
    PURCHASE_ERROR("anampPurchaseError"),
    SIGNIN_ERROR("anampSignInError"),
    SYNC_UNRECOVERABLE_ERROR("anampSyncUnrecoverableError"),
    MEDIA_PLAYBACK_ERROR("anampMediaPlaybackError"),
    PLAYBACK_BUFFER_ERROR("anampPlaybackBufferError"),
    PREVIEW_ERROR("anampStorePreviewError"),
    STREAMING_DOWNLOAD_ERROR("anampStreamingDownloadError"),
    DOWNLOAD_ERROR("anampDownloadError"),
    DOWNLOAD_EXPIRED("anampDownloadExpired"),
    PARTNER_API_ERROR("anampPartnerAPIError");

    private final String mCirrusErrorKey;

    CirrusError(String cirrusErrorKey) {
        this.mCirrusErrorKey = cirrusErrorKey;
    }

    String getCirrusErrorKey() {
        return this.mCirrusErrorKey;
    }
}

package com.amazon.avod.playbackclient.presentation;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareFailedException extends Exception {
    private final Error mError;

    public enum Error {
        LOCK_NOT_HELD(StandardErrorCode.PREPARE_FAILED_LOCK_NOT_HELD),
        PRESENTATION_CACHE_FULL(StandardErrorCode.PREPARE_FAILED_CACHE_FULL),
        MEDIA_EXCEPTION(StandardErrorCode.MEDIA_EXCEPTION),
        PLAYBACK_UNAVAILABLE_NO_DATA_CONNECTION(StandardErrorCode.DATA_CONNECTION_UNAVAILABLE);

        private final MediaErrorCode mErrorCode;

        Error(@Nonnull MediaErrorCode mediaErrorCode) {
            this.mErrorCode = (MediaErrorCode) Preconditions.checkNotNull(mediaErrorCode, "errorCode");
        }

        @Nonnull
        public MediaErrorCode getErrorCode() {
            return this.mErrorCode;
        }
    }

    public PrepareFailedException(@Nonnull Error error) {
        this.mError = (Error) Preconditions.checkNotNull(error, "error");
    }

    @Nonnull
    public Error getError() {
        return this.mError;
    }

    @Nonnull
    public MediaErrorCode getErrorCode() {
        return this.mError.getErrorCode();
    }
}

package com.amazon.avod.download;

import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackDownloadExecutorTask {
    void cancel();

    @Nonnull
    Result execute();

    PlaybackDownloadJob getItem();

    public static class Result {
        private final Optional<MediaErrorCode> mErrorCode;
        private final Downloadable.DownloadableState mNewState;

        public Result(@Nonnull Downloadable.DownloadableState downloadableState, @Nonnull Optional<MediaErrorCode> optional) {
            this.mNewState = (Downloadable.DownloadableState) Preconditions.checkNotNull(downloadableState, "newState");
            this.mErrorCode = (Optional) Preconditions.checkNotNull(optional, "errorCode");
        }

        @Nonnull
        public Downloadable.DownloadableState getNewState() {
            return this.mNewState;
        }

        @Nonnull
        public Optional<MediaErrorCode> getErrorCode() {
            return this.mErrorCode;
        }
    }
}

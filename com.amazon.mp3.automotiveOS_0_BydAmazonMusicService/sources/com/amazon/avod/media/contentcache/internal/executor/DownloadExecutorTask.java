package com.amazon.avod.media.contentcache.internal.executor;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
interface DownloadExecutorTask {
    void cancel();

    @Nonnull
    Result execute();

    public static class Result {
        private final TimeSpan mDurationCached;
        private final Downloadable.DownloadableState mNewState;
        private final Optional<String> mOfflineKeyId;
        private final ContentSession mSessionData;
        private final long mSizeGainedInKiloBytes;

        public Result(Downloadable.DownloadableState downloadableState, @Nullable ContentSession contentSession, @Nonnull Optional<String> optional, @Nullable TimeSpan timeSpan, long j) {
            this.mNewState = (Downloadable.DownloadableState) Preconditions.checkNotNull(downloadableState, "newState");
            this.mSessionData = contentSession;
            this.mOfflineKeyId = (Optional) Preconditions.checkNotNull(optional, "offlineKeyId");
            this.mDurationCached = timeSpan;
            this.mSizeGainedInKiloBytes = j;
        }

        @Nonnull
        public Downloadable.DownloadableState getNewState() {
            return this.mNewState;
        }

        @Nullable
        public ContentSession getSessionData() {
            return this.mSessionData;
        }

        @Nonnull
        public Optional<String> getOfflineKeyId() {
            return this.mOfflineKeyId;
        }

        @Nullable
        public TimeSpan getDurationCached() {
            return this.mDurationCached;
        }

        public long getSizeGainedInKiloBytes() {
            return this.mSizeGainedInKiloBytes;
        }

        public Result updateOfflineKeyId(@Nonnull Optional<String> optional) {
            Preconditions.checkNotNull(optional, "offlineKeyId");
            return new Result(getNewState(), getSessionData(), optional, getDurationCached(), getSizeGainedInKiloBytes());
        }
    }
}

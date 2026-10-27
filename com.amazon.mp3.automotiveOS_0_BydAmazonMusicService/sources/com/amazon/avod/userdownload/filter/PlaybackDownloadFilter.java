package com.amazon.avod.userdownload.filter;

import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.UserDownloadState;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackDownloadFilter implements Predicate<PlaybackDownload> {
    @Override // com.google.common.base.Predicate
    public abstract boolean apply(PlaybackDownload playbackDownload);

    @Nonnull
    public static PlaybackDownloadFilter newOwnerIdFilter(@Nonnull String str) {
        return new SessionContextFilter(str);
    }

    @Nonnull
    public static PlaybackDownloadFilter newDrmAssetIdFilter(@Nonnull String str) {
        return new DrmAssetIdFilter(str);
    }

    @Nonnull
    public static PlaybackDownloadFilter newMatchingStateFilter(@Nonnull Iterable<UserDownloadState> iterable) {
        return new StateBasedFilter(ImmutableSet.copyOf(iterable));
    }

    @Nonnull
    public static PlaybackDownloadFilter matches(@Nonnull PlaybackDownload playbackDownload) {
        return new DownloadFilter(playbackDownload);
    }

    @Nonnull
    public static PlaybackDownloadFilter acceptAll() {
        return new DownloadPredicateWrapper(Predicates.alwaysTrue());
    }

    private static class SessionContextFilter extends PlaybackDownloadFilter {
        private final String mDownloadOwnerId;

        SessionContextFilter(@Nonnull String str) {
            this.mDownloadOwnerId = (String) Preconditions.checkNotNull(str, "downloadOwnerId");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
        public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
            Preconditions.checkNotNull(playbackDownload, "download");
            return Objects.equal(this.mDownloadOwnerId, playbackDownload.getSessionContext());
        }
    }

    private static class DrmAssetIdFilter extends PlaybackDownloadFilter {
        private final String mDrmAssetId;

        DrmAssetIdFilter(@Nonnull String str) {
            this.mDrmAssetId = (String) Preconditions.checkNotNull(str, "drmAssetId");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
        public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
            Preconditions.checkNotNull(playbackDownload, "download");
            return this.mDrmAssetId.equals(playbackDownload.getDrmAssetId().orNull());
        }
    }

    private static class StateBasedFilter extends PlaybackDownloadFilter {
        private final ImmutableSet<UserDownloadState> mStates;

        StateBasedFilter(@Nonnull ImmutableSet immutableSet) {
            this.mStates = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "states");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
        public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
            Preconditions.checkNotNull(playbackDownload, "download");
            return this.mStates.contains(playbackDownload.getState());
        }
    }

    private static class DownloadFilter extends PlaybackDownloadFilter {
        private final PlaybackDownload mDownload;

        DownloadFilter(@Nonnull PlaybackDownload playbackDownload) {
            this.mDownload = (PlaybackDownload) Preconditions.checkNotNull(playbackDownload, "download");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
        public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
            Preconditions.checkNotNull(playbackDownload, "download");
            return playbackDownload.equals(this.mDownload);
        }
    }

    private static class DownloadPredicateWrapper extends PlaybackDownloadFilter {
        private final Predicate<PlaybackDownload> mPredicate;

        DownloadPredicateWrapper(@Nonnull Predicate<PlaybackDownload> predicate) {
            this.mPredicate = (Predicate) Preconditions.checkNotNull(predicate, "predicate");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.userdownload.filter.PlaybackDownloadFilter, com.google.common.base.Predicate
        public boolean apply(@Nonnull PlaybackDownload playbackDownload) {
            Preconditions.checkNotNull(playbackDownload, "download");
            return this.mPredicate.apply(playbackDownload);
        }
    }
}

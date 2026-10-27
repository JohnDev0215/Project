package com.amazon.avod.userdownload.internal;

import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class PlaybackDownloadMemoryCache {
    private final Map<PlaybackDownload, PlaybackDownload> mDownloadsLookupMemoryCache = Maps.newHashMap();
    private ImmutableSet<PlaybackDownload> mDownloadsMemoryCache;

    public PlaybackDownloadMemoryCache(@Nonnull ImmutableSet<PlaybackDownload> immutableSet) {
        this.mDownloadsMemoryCache = ImmutableSet.of();
        Preconditions.checkNotNull(immutableSet, "downloads");
        this.mDownloadsMemoryCache = immutableSet;
        UnmodifiableIterator<PlaybackDownload> it = immutableSet.iterator();
        while (it.hasNext()) {
            PlaybackDownload next = it.next();
            this.mDownloadsLookupMemoryCache.put(next, next);
        }
    }

    @Nonnull
    void addAndResolve(@Nonnull PlaybackDownload playbackDownload) {
        UnmodifiableIterator<PlaybackDownload> it = this.mDownloadsMemoryCache.iterator();
        while (it.hasNext()) {
            update(it.next());
        }
        this.mDownloadsMemoryCache = ImmutableSet.builder().addAll((Iterable) this.mDownloadsMemoryCache).add(playbackDownload).build();
        this.mDownloadsLookupMemoryCache.put(playbackDownload, playbackDownload);
    }

    void update(@Nonnull PlaybackDownload playbackDownload) {
        updateInternal(playbackDownload, getIfPresent(playbackDownload));
    }

    void updateWithDifferentDownloadKey(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownload playbackDownload2) {
        Preconditions.checkNotNull(playbackDownload, "updatedDownload");
        Preconditions.checkNotNull(playbackDownload2, "oldDownload");
        updateInternal(playbackDownload, Optional.of(playbackDownload2));
    }

    private void updateInternal(@Nonnull PlaybackDownload playbackDownload, @Nonnull Optional<PlaybackDownload> optional) {
        Preconditions2.checkStateWeakly(optional.isPresent(), "DWNLD_SDK Update cannot add a new download: %s", playbackDownload);
        if (optional.isPresent()) {
            PlaybackDownload playbackDownload2 = optional.get();
            ImmutableSet.Builder builderAdd = ImmutableSet.builder().add(playbackDownload);
            UnmodifiableIterator<PlaybackDownload> it = this.mDownloadsMemoryCache.iterator();
            while (it.hasNext()) {
                PlaybackDownload next = it.next();
                if (next != playbackDownload2) {
                    builderAdd.add(next);
                }
            }
            this.mDownloadsMemoryCache = builderAdd.build();
            this.mDownloadsLookupMemoryCache.put(playbackDownload, playbackDownload);
        }
    }

    @Nonnull
    ImmutableSet<PlaybackDownload> getAllDownloads() {
        return this.mDownloadsMemoryCache;
    }

    @Nonnull
    Optional<PlaybackDownload> getIfPresent(@Nonnull PlaybackDownload playbackDownload) {
        return Optional.fromNullable(this.mDownloadsLookupMemoryCache.get(playbackDownload));
    }

    boolean remove(@Nonnull PlaybackDownload playbackDownload) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<PlaybackDownload> it = this.mDownloadsMemoryCache.iterator();
        while (it.hasNext()) {
            PlaybackDownload next = it.next();
            if (!Objects.equal(next, playbackDownload)) {
                builder.add(next);
            } else {
                DLog.logf("DWNLD_SDK Removing %s from memory cache", playbackDownload.toString());
            }
        }
        this.mDownloadsMemoryCache = builder.build();
        this.mDownloadsLookupMemoryCache.remove(playbackDownload);
        return true;
    }
}

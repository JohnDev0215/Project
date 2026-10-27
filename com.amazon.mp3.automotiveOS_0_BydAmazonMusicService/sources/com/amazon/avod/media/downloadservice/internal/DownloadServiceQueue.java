package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
class DownloadServiceQueue {
    private final DownloadServiceStatisticsTracker mDownloadServiceStatsTracker;
    private final float mLivePrimaryBufferFillRatioBeforeSecondaryDownloads;
    private final long mVODThresholdPrimaryBufferLengthInNanoseconds;
    private final ListMultimap<PriorityTier, DownloadRequest> mQueuedRequests = ArrayListMultimap.create(PriorityTier.values().length, 10);
    private final Comparator<DownloadRequest> mRequestComparator = new Comparator<DownloadRequest>() { // from class: com.amazon.avod.media.downloadservice.internal.DownloadServiceQueue.1
        @Override // java.util.Comparator
        public int compare(DownloadRequest downloadRequest, DownloadRequest downloadRequest2) {
            return downloadRequest.getPriority().compareTo(downloadRequest2.getPriority());
        }
    };
    private boolean mRestrictBackgroundDownloads = false;
    private volatile boolean mEnableSecondaryDownloads = true;

    public DownloadServiceQueue(@Nonnull TimeSpan timeSpan, float f, @Nonnull DownloadServiceStatisticsTracker downloadServiceStatisticsTracker) {
        this.mVODThresholdPrimaryBufferLengthInNanoseconds = timeSpan.getTotalNanoSeconds();
        this.mLivePrimaryBufferFillRatioBeforeSecondaryDownloads = f;
        this.mDownloadServiceStatsTracker = downloadServiceStatisticsTracker;
    }

    public void enqueue(@Nonnull DownloadRequest downloadRequest) {
        Preconditions.checkNotNull(downloadRequest);
        synchronized (this.mQueuedRequests) {
            DownloadRequestPriority priority = downloadRequest.getPriority();
            this.mQueuedRequests.put(priority.getTier(), downloadRequest);
            downloadRequest.setEnqueuePriority(priority);
        }
    }

    public boolean remove(@Nonnull DownloadRequest downloadRequest) {
        boolean zRemove;
        Preconditions.checkNotNull(downloadRequest);
        synchronized (this.mQueuedRequests) {
            zRemove = this.mQueuedRequests.remove(downloadRequest.getEnqueuePriority().getTier(), downloadRequest);
        }
        return zRemove;
    }

    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.mQueuedRequests) {
            zIsEmpty = this.mQueuedRequests.isEmpty();
        }
        return zIsEmpty;
    }

    public DownloadRequest dequeue() {
        DownloadRequest downloadRequestFindNextRestrictedRequest;
        synchronized (this.mQueuedRequests) {
            if (isEmpty()) {
                return null;
            }
            if (this.mRestrictBackgroundDownloads) {
                downloadRequestFindNextRestrictedRequest = findNextRestrictedRequest();
            } else {
                downloadRequestFindNextRestrictedRequest = (DownloadRequest) Collections.min(this.mQueuedRequests.values(), this.mRequestComparator);
            }
            if (downloadRequestFindNextRestrictedRequest != null) {
                PriorityTier tier = downloadRequestFindNextRestrictedRequest.getPriority().getTier();
                if (tier == PriorityTier.AUXILIARY || tier == PriorityTier.AUXILIARY_REQUIRED) {
                    DLog.devf("Submitting auxiliary request: %s, Queue: %d, Buffer: %s, Bandwidth(KBps): %s, Restricted: %s, URL: %s", downloadRequestFindNextRestrictedRequest.getPriority(), Integer.valueOf(this.mQueuedRequests.size()), Long.valueOf(this.mDownloadServiceStatsTracker.getPrimaryContentBufferLengthInNanos()), Float.valueOf(DataUnit.BITS.toKiloBytes(this.mDownloadServiceStatsTracker.getBandwidthStatistics().getAverage())), Boolean.valueOf(this.mRestrictBackgroundDownloads), downloadRequestFindNextRestrictedRequest.getUrl());
                }
                this.mQueuedRequests.remove(downloadRequestFindNextRestrictedRequest.getEnqueuePriority().getTier(), downloadRequestFindNextRestrictedRequest);
            }
            return downloadRequestFindNextRestrictedRequest;
        }
    }

    @Nonnull
    public Collection<DownloadRequest> dequeue(@Nonnull PriorityTier priorityTier) {
        List<DownloadRequest> listRemoveAll;
        Preconditions.checkNotNull(priorityTier, "priorityTier");
        synchronized (this.mQueuedRequests) {
            listRemoveAll = this.mQueuedRequests.removeAll((Object) priorityTier);
        }
        return listRemoveAll;
    }

    public void restrictBackgroundDownloads(boolean z) {
        this.mRestrictBackgroundDownloads = z;
    }

    public void enableSecondaryDownloads(boolean z) {
        this.mEnableSecondaryDownloads = z;
    }

    private long getPrimaryContentBufferThresholdInNanoseconds() {
        long maxPrimaryContentBufferLengthInNanoseconds = this.mDownloadServiceStatsTracker.getMaxPrimaryContentBufferLengthInNanoseconds();
        if (maxPrimaryContentBufferLengthInNanoseconds != -1) {
            return Math.min((long) (maxPrimaryContentBufferLengthInNanoseconds * this.mLivePrimaryBufferFillRatioBeforeSecondaryDownloads), this.mVODThresholdPrimaryBufferLengthInNanoseconds);
        }
        return this.mVODThresholdPrimaryBufferLengthInNanoseconds;
    }

    @Nullable
    private DownloadRequest findNextRestrictedRequest() {
        synchronized (this.mQueuedRequests) {
            long jMax = Math.max(this.mDownloadServiceStatsTracker.getPrimaryContentBufferLengthInNanos() - getPrimaryContentBufferThresholdInNanoseconds(), 0L);
            DownloadRequest downloadRequestHighestPriorityDownloadableRequest = this.mEnableSecondaryDownloads ? highestPriorityDownloadableRequest(jMax, PriorityTier.AUXILIARY, PriorityTier.AUXILIARY_REQUIRED) : highestPriorityDownloadableRequest(jMax, PriorityTier.AUXILIARY_REQUIRED);
            if (downloadRequestHighestPriorityDownloadableRequest != null) {
                return downloadRequestHighestPriorityDownloadableRequest;
            }
            List<DownloadRequest> list = this.mQueuedRequests.get(PriorityTier.PRIMARY);
            if (!list.isEmpty()) {
                return (DownloadRequest) Collections.min(list, this.mRequestComparator);
            }
            List<DownloadRequest> list2 = this.mQueuedRequests.get(PriorityTier.SECONDARY);
            if (!list2.isEmpty()) {
                return (DownloadRequest) Collections.min(list2, this.mRequestComparator);
            }
            if (this.mEnableSecondaryDownloads) {
                return highestPriorityDownloadableRequest(jMax, PriorityTier.BACKGROUND);
            }
            return null;
        }
    }

    private boolean requestWillDownloadInTime(DownloadRequest downloadRequest, long j) {
        boolean z;
        synchronized (this.mQueuedRequests) {
            z = j > this.mDownloadServiceStatsTracker.estimateDownloadTimeInNanos(downloadRequest.getPriority().getApprxSizeBytes());
        }
        return z;
    }

    @Nullable
    private DownloadRequest highestPriorityDownloadableRequest(long j, PriorityTier... priorityTierArr) {
        ArrayList<DownloadRequest> arrayList = new ArrayList();
        for (PriorityTier priorityTier : priorityTierArr) {
            arrayList.addAll(this.mQueuedRequests.get(priorityTier));
        }
        if (arrayList.isEmpty() || j <= 0) {
            return null;
        }
        Collections.sort(arrayList, this.mRequestComparator);
        for (DownloadRequest downloadRequest : arrayList) {
            if (requestWillDownloadInTime(downloadRequest, j)) {
                return downloadRequest;
            }
        }
        return null;
    }
}

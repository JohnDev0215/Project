package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Queue implements Comparable<Queue> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.Queue");
    private String pageToken;
    private String queueId;
    private QueueMetadata queueMetadata;
    private Boolean shufflePlayback;
    private Long ttlInSeconds;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Queue queue) {
        if (queue == null) {
            return -1;
        }
        if (queue == this) {
            return 0;
        }
        String queueId = getQueueId();
        String queueId2 = queue.getQueueId();
        if (queueId != queueId2) {
            if (queueId == null) {
                return -1;
            }
            if (queueId2 == null) {
                return 1;
            }
            int iCompareTo = queueId.compareTo(queueId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long ttlInSeconds = getTtlInSeconds();
        Long ttlInSeconds2 = queue.getTtlInSeconds();
        if (ttlInSeconds != ttlInSeconds2) {
            if (ttlInSeconds == null) {
                return -1;
            }
            if (ttlInSeconds2 == null) {
                return 1;
            }
            int iCompareTo2 = ttlInSeconds.compareTo(ttlInSeconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = queue.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo3 = pageToken.compareTo(pageToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsShufflePlayback = isShufflePlayback();
        Boolean boolIsShufflePlayback2 = queue.isShufflePlayback();
        if (boolIsShufflePlayback != boolIsShufflePlayback2) {
            if (boolIsShufflePlayback == null) {
                return -1;
            }
            if (boolIsShufflePlayback2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsShufflePlayback.compareTo(boolIsShufflePlayback2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        QueueMetadata queueMetadata = getQueueMetadata();
        QueueMetadata queueMetadata2 = queue.getQueueMetadata();
        if (queueMetadata != queueMetadata2) {
            if (queueMetadata == null) {
                return -1;
            }
            if (queueMetadata2 == null) {
                return 1;
            }
            int iCompareTo5 = queueMetadata.compareTo(queueMetadata2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Queue)) {
            return false;
        }
        Queue queue = (Queue) obj;
        return internalEqualityCheck(getQueueId(), queue.getQueueId()) && internalEqualityCheck(getTtlInSeconds(), queue.getTtlInSeconds()) && internalEqualityCheck(getPageToken(), queue.getPageToken()) && internalEqualityCheck(isShufflePlayback(), queue.isShufflePlayback()) && internalEqualityCheck(getQueueMetadata(), queue.getQueueMetadata());
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public Long getTtlInSeconds() {
        return this.ttlInSeconds;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQueueId(), getTtlInSeconds(), getPageToken(), isShufflePlayback(), getQueueMetadata());
    }

    public Boolean isShufflePlayback() {
        return this.shufflePlayback;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public void setQueueMetadata(QueueMetadata queueMetadata) {
        this.queueMetadata = queueMetadata;
    }

    public void setShufflePlayback(Boolean bool) {
        this.shufflePlayback = bool;
    }

    public void setTtlInSeconds(Long l) {
        this.ttlInSeconds = l;
    }
}

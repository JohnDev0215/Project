package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetNextTracksResponse implements Comparable<GetNextTracksResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponse");
    private long allowedRetryTimestampMs;
    private String nextPageToken;
    private List<QueuedEntityMetadataBase> queuedEntityMetadataList;
    private String responseStatus;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetNextTracksResponse getNextTracksResponse) {
        if (getNextTracksResponse == null) {
            return -1;
        }
        if (getNextTracksResponse == this) {
            return 0;
        }
        if (getAllowedRetryTimestampMs() < getNextTracksResponse.getAllowedRetryTimestampMs()) {
            return -1;
        }
        if (getAllowedRetryTimestampMs() > getNextTracksResponse.getAllowedRetryTimestampMs()) {
            return 1;
        }
        List<QueuedEntityMetadataBase> queuedEntityMetadataList = getQueuedEntityMetadataList();
        List<QueuedEntityMetadataBase> queuedEntityMetadataList2 = getNextTracksResponse.getQueuedEntityMetadataList();
        if (queuedEntityMetadataList != queuedEntityMetadataList2) {
            if (queuedEntityMetadataList == null) {
                return -1;
            }
            if (queuedEntityMetadataList2 == null) {
                return 1;
            }
            if (queuedEntityMetadataList instanceof Comparable) {
                int iCompareTo = ((Comparable) queuedEntityMetadataList).compareTo(queuedEntityMetadataList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!queuedEntityMetadataList.equals(queuedEntityMetadataList2)) {
                int iHashCode = queuedEntityMetadataList.hashCode();
                int iHashCode2 = queuedEntityMetadataList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String nextPageToken = getNextPageToken();
        String nextPageToken2 = getNextTracksResponse.getNextPageToken();
        if (nextPageToken != nextPageToken2) {
            if (nextPageToken == null) {
                return -1;
            }
            if (nextPageToken2 == null) {
                return 1;
            }
            int iCompareTo2 = nextPageToken.compareTo(nextPageToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String responseStatus = getResponseStatus();
        String responseStatus2 = getNextTracksResponse.getResponseStatus();
        if (responseStatus != responseStatus2) {
            if (responseStatus == null) {
                return -1;
            }
            if (responseStatus2 == null) {
                return 1;
            }
            int iCompareTo3 = responseStatus.compareTo(responseStatus2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetNextTracksResponse)) {
            return false;
        }
        GetNextTracksResponse getNextTracksResponse = (GetNextTracksResponse) obj;
        return internalEqualityCheck(Long.valueOf(getAllowedRetryTimestampMs()), Long.valueOf(getNextTracksResponse.getAllowedRetryTimestampMs())) && internalEqualityCheck(getQueuedEntityMetadataList(), getNextTracksResponse.getQueuedEntityMetadataList()) && internalEqualityCheck(getNextPageToken(), getNextTracksResponse.getNextPageToken()) && internalEqualityCheck(getResponseStatus(), getNextTracksResponse.getResponseStatus());
    }

    public long getAllowedRetryTimestampMs() {
        return this.allowedRetryTimestampMs;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public List<QueuedEntityMetadataBase> getQueuedEntityMetadataList() {
        return this.queuedEntityMetadataList;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Long.valueOf(getAllowedRetryTimestampMs()), getQueuedEntityMetadataList(), getNextPageToken(), getResponseStatus());
    }

    public void setAllowedRetryTimestampMs(long j) {
        this.allowedRetryTimestampMs = j;
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public void setQueuedEntityMetadataList(List<QueuedEntityMetadataBase> list) {
        this.queuedEntityMetadataList = list;
    }

    public void setResponseStatus(String str) {
        this.responseStatus = str;
    }
}

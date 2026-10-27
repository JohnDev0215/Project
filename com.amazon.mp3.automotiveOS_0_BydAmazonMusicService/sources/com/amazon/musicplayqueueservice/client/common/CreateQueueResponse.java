package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueResponse implements Comparable<CreateQueueResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.CreateQueueResponse");
    private Queue queue;
    private String status;
    private List<TrackMetadata> trackMetadataList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CreateQueueResponse createQueueResponse) {
        if (createQueueResponse == null) {
            return -1;
        }
        if (createQueueResponse == this) {
            return 0;
        }
        List<TrackMetadata> trackMetadataList = getTrackMetadataList();
        List<TrackMetadata> trackMetadataList2 = createQueueResponse.getTrackMetadataList();
        if (trackMetadataList != trackMetadataList2) {
            if (trackMetadataList == null) {
                return -1;
            }
            if (trackMetadataList2 == null) {
                return 1;
            }
            if (trackMetadataList instanceof Comparable) {
                int iCompareTo = ((Comparable) trackMetadataList).compareTo(trackMetadataList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!trackMetadataList.equals(trackMetadataList2)) {
                int iHashCode = trackMetadataList.hashCode();
                int iHashCode2 = trackMetadataList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Queue queue = getQueue();
        Queue queue2 = createQueueResponse.getQueue();
        if (queue != queue2) {
            if (queue == null) {
                return -1;
            }
            if (queue2 == null) {
                return 1;
            }
            int iCompareTo2 = queue.compareTo(queue2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String status = getStatus();
        String status2 = createQueueResponse.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo3 = status.compareTo(status2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CreateQueueResponse)) {
            return false;
        }
        CreateQueueResponse createQueueResponse = (CreateQueueResponse) obj;
        return internalEqualityCheck(getTrackMetadataList(), createQueueResponse.getTrackMetadataList()) && internalEqualityCheck(getQueue(), createQueueResponse.getQueue()) && internalEqualityCheck(getStatus(), createQueueResponse.getStatus());
    }

    public Queue getQueue() {
        return this.queue;
    }

    public String getStatus() {
        return this.status;
    }

    public List<TrackMetadata> getTrackMetadataList() {
        return this.trackMetadataList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTrackMetadataList(), getQueue(), getStatus());
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTrackMetadataList(List<TrackMetadata> list) {
        this.trackMetadataList = list;
    }
}

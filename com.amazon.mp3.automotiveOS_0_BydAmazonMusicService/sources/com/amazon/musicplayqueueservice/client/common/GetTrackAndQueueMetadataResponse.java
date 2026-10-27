package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetTrackAndQueueMetadataResponse implements Comparable<GetTrackAndQueueMetadataResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse");
    private QueueMetadata queueMetadata;
    private TrackMetadata trackMetadata;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetTrackAndQueueMetadataResponse getTrackAndQueueMetadataResponse) {
        if (getTrackAndQueueMetadataResponse == null) {
            return -1;
        }
        if (getTrackAndQueueMetadataResponse == this) {
            return 0;
        }
        TrackMetadata trackMetadata = getTrackMetadata();
        TrackMetadata trackMetadata2 = getTrackAndQueueMetadataResponse.getTrackMetadata();
        if (trackMetadata != trackMetadata2) {
            if (trackMetadata == null) {
                return -1;
            }
            if (trackMetadata2 == null) {
                return 1;
            }
            int iCompareTo = trackMetadata.compareTo(trackMetadata2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        QueueMetadata queueMetadata = getQueueMetadata();
        QueueMetadata queueMetadata2 = getTrackAndQueueMetadataResponse.getQueueMetadata();
        if (queueMetadata != queueMetadata2) {
            if (queueMetadata == null) {
                return -1;
            }
            if (queueMetadata2 == null) {
                return 1;
            }
            int iCompareTo2 = queueMetadata.compareTo(queueMetadata2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetTrackAndQueueMetadataResponse)) {
            return false;
        }
        GetTrackAndQueueMetadataResponse getTrackAndQueueMetadataResponse = (GetTrackAndQueueMetadataResponse) obj;
        return internalEqualityCheck(getTrackMetadata(), getTrackAndQueueMetadataResponse.getTrackMetadata()) && internalEqualityCheck(getQueueMetadata(), getTrackAndQueueMetadataResponse.getQueueMetadata());
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public TrackMetadata getTrackMetadata() {
        return this.trackMetadata;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTrackMetadata(), getQueueMetadata());
    }

    public void setQueueMetadata(QueueMetadata queueMetadata) {
        this.queueMetadata = queueMetadata;
    }

    public void setTrackMetadata(TrackMetadata trackMetadata) {
        this.trackMetadata = trackMetadata;
    }
}

package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetTrackAndQueueMetadataRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataRequest");
    private String trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (!(abstractClientRequest instanceof GetTrackAndQueueMetadataRequest)) {
            return 1;
        }
        String trackId = getTrackId();
        String trackId2 = ((GetTrackAndQueueMetadataRequest) abstractClientRequest).getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo = trackId.compareTo(trackId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (obj instanceof GetTrackAndQueueMetadataRequest) {
            return super.equals(obj) && internalEqualityCheck(getTrackId(), ((GetTrackAndQueueMetadataRequest) obj).getTrackId());
        }
        return false;
    }

    public String getTrackId() {
        return this.trackId;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrackId());
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }
}

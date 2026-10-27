package com.amazon.musicplayqueueservice.client.common;

import com.amazon.musicplayqueueservice.client.common.casting.StartAt;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class TransferQueueRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.TransferQueueRequest");
    private String playbackPreference;
    private String queueId;
    private StartAt startAt;
    private String targetId;

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
        if (!(abstractClientRequest instanceof TransferQueueRequest)) {
            return 1;
        }
        TransferQueueRequest transferQueueRequest = (TransferQueueRequest) abstractClientRequest;
        String queueId = getQueueId();
        String queueId2 = transferQueueRequest.getQueueId();
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
        String playbackPreference = getPlaybackPreference();
        String playbackPreference2 = transferQueueRequest.getPlaybackPreference();
        if (playbackPreference != playbackPreference2) {
            if (playbackPreference == null) {
                return -1;
            }
            if (playbackPreference2 == null) {
                return 1;
            }
            int iCompareTo2 = playbackPreference.compareTo(playbackPreference2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String targetId = getTargetId();
        String targetId2 = transferQueueRequest.getTargetId();
        if (targetId != targetId2) {
            if (targetId == null) {
                return -1;
            }
            if (targetId2 == null) {
                return 1;
            }
            int iCompareTo3 = targetId.compareTo(targetId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        StartAt startAt = getStartAt();
        StartAt startAt2 = transferQueueRequest.getStartAt();
        if (startAt != startAt2) {
            if (startAt == null) {
                return -1;
            }
            if (startAt2 == null) {
                return 1;
            }
            int iCompareTo4 = startAt.compareTo(startAt2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof TransferQueueRequest)) {
            return false;
        }
        TransferQueueRequest transferQueueRequest = (TransferQueueRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getQueueId(), transferQueueRequest.getQueueId()) && internalEqualityCheck(getPlaybackPreference(), transferQueueRequest.getPlaybackPreference()) && internalEqualityCheck(getTargetId(), transferQueueRequest.getTargetId()) && internalEqualityCheck(getStartAt(), transferQueueRequest.getStartAt());
    }

    public String getPlaybackPreference() {
        return this.playbackPreference;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public StartAt getStartAt() {
        return this.startAt;
    }

    public String getTargetId() {
        return this.targetId;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQueueId(), getPlaybackPreference(), getTargetId(), getStartAt());
    }

    public void setPlaybackPreference(String str) {
        this.playbackPreference = str;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public void setStartAt(StartAt startAt) {
        this.startAt = startAt;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }
}

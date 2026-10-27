package com.amazon.musicplayqueueservice.client.v2;

import com.amazon.musicplayqueueservice.client.common.AbstractClientRequest;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RateEntityRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.RateEntityRequest");
    private double durationPlayedInMilliseconds;
    private String entityReference;
    private String queueId;
    private String rating;
    private long utcTimeStamp;

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
        if (!(abstractClientRequest instanceof RateEntityRequest)) {
            return 1;
        }
        RateEntityRequest rateEntityRequest = (RateEntityRequest) abstractClientRequest;
        String queueId = getQueueId();
        String queueId2 = rateEntityRequest.getQueueId();
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
        String rating = getRating();
        String rating2 = rateEntityRequest.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo2 = rating.compareTo(rating2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (getDurationPlayedInMilliseconds() < rateEntityRequest.getDurationPlayedInMilliseconds()) {
            return -1;
        }
        if (getDurationPlayedInMilliseconds() > rateEntityRequest.getDurationPlayedInMilliseconds()) {
            return 1;
        }
        if (getUtcTimeStamp() < rateEntityRequest.getUtcTimeStamp()) {
            return -1;
        }
        if (getUtcTimeStamp() > rateEntityRequest.getUtcTimeStamp()) {
            return 1;
        }
        String entityReference = getEntityReference();
        String entityReference2 = rateEntityRequest.getEntityReference();
        if (entityReference != entityReference2) {
            if (entityReference == null) {
                return -1;
            }
            if (entityReference2 == null) {
                return 1;
            }
            int iCompareTo3 = entityReference.compareTo(entityReference2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof RateEntityRequest)) {
            return false;
        }
        RateEntityRequest rateEntityRequest = (RateEntityRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getQueueId(), rateEntityRequest.getQueueId()) && internalEqualityCheck(getRating(), rateEntityRequest.getRating()) && internalEqualityCheck(Double.valueOf(getDurationPlayedInMilliseconds()), Double.valueOf(rateEntityRequest.getDurationPlayedInMilliseconds())) && internalEqualityCheck(Long.valueOf(getUtcTimeStamp()), Long.valueOf(rateEntityRequest.getUtcTimeStamp())) && internalEqualityCheck(getEntityReference(), rateEntityRequest.getEntityReference());
    }

    public double getDurationPlayedInMilliseconds() {
        return this.durationPlayedInMilliseconds;
    }

    public String getEntityReference() {
        return this.entityReference;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getRating() {
        return this.rating;
    }

    public long getUtcTimeStamp() {
        return this.utcTimeStamp;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQueueId(), getRating(), Double.valueOf(getDurationPlayedInMilliseconds()), Long.valueOf(getUtcTimeStamp()), getEntityReference());
    }

    public void setDurationPlayedInMilliseconds(double d) {
        this.durationPlayedInMilliseconds = d;
    }

    public void setEntityReference(String str) {
        this.entityReference = str;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setUtcTimeStamp(long j) {
        this.utcTimeStamp = j;
    }
}

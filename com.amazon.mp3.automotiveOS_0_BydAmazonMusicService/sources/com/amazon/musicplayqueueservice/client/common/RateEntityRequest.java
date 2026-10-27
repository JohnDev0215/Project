package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RateEntityRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.RateEntityRequest");
    private int durationPlayedInMilliseconds;
    private String identifier;
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
        String rating = getRating();
        String rating2 = rateEntityRequest.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo = rating.compareTo(rating2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = rateEntityRequest.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo2 = identifier.compareTo(identifier2);
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
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof RateEntityRequest)) {
            return false;
        }
        RateEntityRequest rateEntityRequest = (RateEntityRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getRating(), rateEntityRequest.getRating()) && internalEqualityCheck(getIdentifier(), rateEntityRequest.getIdentifier()) && internalEqualityCheck(Integer.valueOf(getDurationPlayedInMilliseconds()), Integer.valueOf(rateEntityRequest.getDurationPlayedInMilliseconds())) && internalEqualityCheck(Long.valueOf(getUtcTimeStamp()), Long.valueOf(rateEntityRequest.getUtcTimeStamp()));
    }

    public int getDurationPlayedInMilliseconds() {
        return this.durationPlayedInMilliseconds;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getRating() {
        return this.rating;
    }

    public long getUtcTimeStamp() {
        return this.utcTimeStamp;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getRating(), getIdentifier(), Integer.valueOf(getDurationPlayedInMilliseconds()), Long.valueOf(getUtcTimeStamp()));
    }

    public void setDurationPlayedInMilliseconds(int i) {
        this.durationPlayedInMilliseconds = i;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setUtcTimeStamp(long j) {
        this.utcTimeStamp = j;
    }
}

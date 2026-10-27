package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.musicplayqueueservice.client.v2.common.IndexIdentifier;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class StartAt implements Comparable<StartAt> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.StartAt");
    private IndexIdentifier identifier;
    private int positionInMs;
    private String trackIdentifier;
    private String trackIdentifierType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StartAt startAt) {
        if (startAt == null) {
            return -1;
        }
        if (startAt == this) {
            return 0;
        }
        if (getPositionInMs() < startAt.getPositionInMs()) {
            return -1;
        }
        if (getPositionInMs() > startAt.getPositionInMs()) {
            return 1;
        }
        IndexIdentifier identifier = getIdentifier();
        IndexIdentifier identifier2 = startAt.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo = identifier.compareTo(identifier2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String trackIdentifier = getTrackIdentifier();
        String trackIdentifier2 = startAt.getTrackIdentifier();
        if (trackIdentifier != trackIdentifier2) {
            if (trackIdentifier == null) {
                return -1;
            }
            if (trackIdentifier2 == null) {
                return 1;
            }
            int iCompareTo2 = trackIdentifier.compareTo(trackIdentifier2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String trackIdentifierType = getTrackIdentifierType();
        String trackIdentifierType2 = startAt.getTrackIdentifierType();
        if (trackIdentifierType != trackIdentifierType2) {
            if (trackIdentifierType == null) {
                return -1;
            }
            if (trackIdentifierType2 == null) {
                return 1;
            }
            int iCompareTo3 = trackIdentifierType.compareTo(trackIdentifierType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StartAt)) {
            return false;
        }
        StartAt startAt = (StartAt) obj;
        return internalEqualityCheck(Integer.valueOf(getPositionInMs()), Integer.valueOf(startAt.getPositionInMs())) && internalEqualityCheck(getIdentifier(), startAt.getIdentifier()) && internalEqualityCheck(getTrackIdentifier(), startAt.getTrackIdentifier()) && internalEqualityCheck(getTrackIdentifierType(), startAt.getTrackIdentifierType());
    }

    @Deprecated
    public IndexIdentifier getIdentifier() {
        return this.identifier;
    }

    public int getPositionInMs() {
        return this.positionInMs;
    }

    public String getTrackIdentifier() {
        return this.trackIdentifier;
    }

    public String getTrackIdentifierType() {
        return this.trackIdentifierType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getPositionInMs()), getIdentifier(), getTrackIdentifier(), getTrackIdentifierType());
    }

    @Deprecated
    public void setIdentifier(IndexIdentifier indexIdentifier) {
        this.identifier = indexIdentifier;
    }

    public void setPositionInMs(int i) {
        this.positionInMs = i;
    }

    public void setTrackIdentifier(String str) {
        this.trackIdentifier = str;
    }

    public void setTrackIdentifierType(String str) {
        this.trackIdentifierType = str;
    }
}

package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class TrackEvent implements Comparable<TrackEvent> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.TrackEvent");
    private Integer durationSeconds;
    private String entityId;
    private String entityIdType;
    private Date timestamp;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackEvent trackEvent) {
        if (trackEvent == null) {
            return -1;
        }
        if (trackEvent == this) {
            return 0;
        }
        String entityIdType = getEntityIdType();
        String entityIdType2 = trackEvent.getEntityIdType();
        if (entityIdType != entityIdType2) {
            if (entityIdType == null) {
                return -1;
            }
            if (entityIdType2 == null) {
                return 1;
            }
            int iCompareTo = entityIdType.compareTo(entityIdType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date timestamp = getTimestamp();
        Date timestamp2 = trackEvent.getTimestamp();
        if (timestamp != timestamp2) {
            if (timestamp == null) {
                return -1;
            }
            if (timestamp2 == null) {
                return 1;
            }
            int iCompareTo2 = timestamp.compareTo(timestamp2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer durationSeconds = getDurationSeconds();
        Integer durationSeconds2 = trackEvent.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo3 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String entityId = getEntityId();
        String entityId2 = trackEvent.getEntityId();
        if (entityId != entityId2) {
            if (entityId == null) {
                return -1;
            }
            if (entityId2 == null) {
                return 1;
            }
            int iCompareTo4 = entityId.compareTo(entityId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackEvent)) {
            return false;
        }
        TrackEvent trackEvent = (TrackEvent) obj;
        return internalEqualityCheck(getEntityIdType(), trackEvent.getEntityIdType()) && internalEqualityCheck(getTimestamp(), trackEvent.getTimestamp()) && internalEqualityCheck(getDurationSeconds(), trackEvent.getDurationSeconds()) && internalEqualityCheck(getEntityId(), trackEvent.getEntityId());
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getEntityIdType() {
        return this.entityIdType;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEntityIdType(), getTimestamp(), getDurationSeconds(), getEntityId());
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setEntityId(String str) {
        this.entityId = str;
    }

    public void setEntityIdType(String str) {
        this.entityIdType = str;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }
}

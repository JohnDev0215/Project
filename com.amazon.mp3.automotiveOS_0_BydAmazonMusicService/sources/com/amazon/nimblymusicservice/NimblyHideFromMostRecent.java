package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class NimblyHideFromMostRecent implements Comparable<NimblyHideFromMostRecent> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.NimblyHideFromMostRecent");
    private String entityId;
    private String entityType;
    private Date timestamp;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated NimblyHideFromMostRecent nimblyHideFromMostRecent) {
        if (nimblyHideFromMostRecent == null) {
            return -1;
        }
        if (nimblyHideFromMostRecent == this) {
            return 0;
        }
        Date timestamp = getTimestamp();
        Date timestamp2 = nimblyHideFromMostRecent.getTimestamp();
        if (timestamp != timestamp2) {
            if (timestamp == null) {
                return -1;
            }
            if (timestamp2 == null) {
                return 1;
            }
            int iCompareTo = timestamp.compareTo(timestamp2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String entityType = getEntityType();
        String entityType2 = nimblyHideFromMostRecent.getEntityType();
        if (entityType != entityType2) {
            if (entityType == null) {
                return -1;
            }
            if (entityType2 == null) {
                return 1;
            }
            int iCompareTo2 = entityType.compareTo(entityType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String entityId = getEntityId();
        String entityId2 = nimblyHideFromMostRecent.getEntityId();
        if (entityId != entityId2) {
            if (entityId == null) {
                return -1;
            }
            if (entityId2 == null) {
                return 1;
            }
            int iCompareTo3 = entityId.compareTo(entityId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NimblyHideFromMostRecent)) {
            return false;
        }
        NimblyHideFromMostRecent nimblyHideFromMostRecent = (NimblyHideFromMostRecent) obj;
        return internalEqualityCheck(getTimestamp(), nimblyHideFromMostRecent.getTimestamp()) && internalEqualityCheck(getEntityType(), nimblyHideFromMostRecent.getEntityType()) && internalEqualityCheck(getEntityId(), nimblyHideFromMostRecent.getEntityId());
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTimestamp(), getEntityType(), getEntityId());
    }
}

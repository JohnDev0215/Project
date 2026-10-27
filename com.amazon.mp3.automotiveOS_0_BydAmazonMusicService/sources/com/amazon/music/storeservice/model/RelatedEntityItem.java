package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class RelatedEntityItem implements Comparable<RelatedEntityItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.RelatedEntityItem");
    private TrackItem entity;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RelatedEntityItem relatedEntityItem) {
        if (relatedEntityItem == null) {
            return -1;
        }
        if (relatedEntityItem == this) {
            return 0;
        }
        String type = getType();
        String type2 = relatedEntityItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo = type.compareTo(type2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        TrackItem entity = getEntity();
        TrackItem entity2 = relatedEntityItem.getEntity();
        if (entity != entity2) {
            if (entity == null) {
                return -1;
            }
            if (entity2 == null) {
                return 1;
            }
            int iCompareTo2 = entity.compareTo(entity2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RelatedEntityItem)) {
            return false;
        }
        RelatedEntityItem relatedEntityItem = (RelatedEntityItem) obj;
        return internalEqualityCheck(getType(), relatedEntityItem.getType()) && internalEqualityCheck(getEntity(), relatedEntityItem.getEntity());
    }

    public TrackItem getEntity() {
        return this.entity;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getType(), getEntity());
    }

    public void setEntity(TrackItem trackItem) {
        this.entity = trackItem;
    }

    public void setType(String str) {
        this.type = str;
    }
}

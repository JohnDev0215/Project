package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class CatalogEntity extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.CatalogEntity");
    private String asin;
    private Long expirationTime;
    private String requestedAsin;
    private Map<String, List<String>> tags;

    public static class Builder extends MusicEntity.Builder {
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof CatalogEntity)) {
            return 1;
        }
        CatalogEntity catalogEntity = (CatalogEntity) entity;
        Long expirationTime = getExpirationTime();
        Long expirationTime2 = catalogEntity.getExpirationTime();
        if (expirationTime != expirationTime2) {
            if (expirationTime == null) {
                return -1;
            }
            if (expirationTime2 == null) {
                return 1;
            }
            int iCompareTo = expirationTime.compareTo(expirationTime2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = catalogEntity.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo2 = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String asin = getAsin();
        String asin2 = catalogEntity.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo3 = asin.compareTo(asin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, List<String>> tags = getTags();
        Map<String, List<String>> tags2 = catalogEntity.getTags();
        if (tags != tags2) {
            if (tags == null) {
                return -1;
            }
            if (tags2 == null) {
                return 1;
            }
            if (tags instanceof Comparable) {
                int iCompareTo4 = ((Comparable) tags).compareTo(tags2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!tags.equals(tags2)) {
                int iHashCode = tags.hashCode();
                int iHashCode2 = tags2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogEntity)) {
            return false;
        }
        CatalogEntity catalogEntity = (CatalogEntity) obj;
        return super.equals(obj) && internalEqualityCheck(getExpirationTime(), catalogEntity.getExpirationTime()) && internalEqualityCheck(getRequestedAsin(), catalogEntity.getRequestedAsin()) && internalEqualityCheck(getAsin(), catalogEntity.getAsin()) && internalEqualityCheck(getTags(), catalogEntity.getTags());
    }

    public String getAsin() {
        return this.asin;
    }

    public Long getExpirationTime() {
        return this.expirationTime;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public Map<String, List<String>> getTags() {
        return this.tags;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getExpirationTime(), getRequestedAsin(), getAsin(), getTags());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setExpirationTime(Long l) {
        this.expirationTime = l;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setTags(Map<String, List<String>> map) {
        this.tags = map;
    }
}

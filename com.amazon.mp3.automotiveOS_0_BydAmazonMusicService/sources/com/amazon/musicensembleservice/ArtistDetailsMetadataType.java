package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataType implements Comparable<ArtistDetailsMetadataType> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ArtistDetailsMetadataType");
    private Integer maxCount;
    private String nextToken;
    private String sortBy;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistDetailsMetadataType artistDetailsMetadataType) {
        if (artistDetailsMetadataType == null) {
            return -1;
        }
        if (artistDetailsMetadataType == this) {
            return 0;
        }
        String type = getType();
        String type2 = artistDetailsMetadataType.getType();
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
        Integer maxCount = getMaxCount();
        Integer maxCount2 = artistDetailsMetadataType.getMaxCount();
        if (maxCount != maxCount2) {
            if (maxCount == null) {
                return -1;
            }
            if (maxCount2 == null) {
                return 1;
            }
            int iCompareTo2 = maxCount.compareTo(maxCount2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String sortBy = getSortBy();
        String sortBy2 = artistDetailsMetadataType.getSortBy();
        if (sortBy != sortBy2) {
            if (sortBy == null) {
                return -1;
            }
            if (sortBy2 == null) {
                return 1;
            }
            int iCompareTo3 = sortBy.compareTo(sortBy2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String nextToken = getNextToken();
        String nextToken2 = artistDetailsMetadataType.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo4 = nextToken.compareTo(nextToken2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistDetailsMetadataType)) {
            return false;
        }
        ArtistDetailsMetadataType artistDetailsMetadataType = (ArtistDetailsMetadataType) obj;
        return internalEqualityCheck(getType(), artistDetailsMetadataType.getType()) && internalEqualityCheck(getMaxCount(), artistDetailsMetadataType.getMaxCount()) && internalEqualityCheck(getSortBy(), artistDetailsMetadataType.getSortBy()) && internalEqualityCheck(getNextToken(), artistDetailsMetadataType.getNextToken());
    }

    public Integer getMaxCount() {
        return this.maxCount;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getType(), getMaxCount(), getSortBy(), getNextToken());
    }

    public void setMaxCount(Integer num) {
        this.maxCount = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSortBy(String str) {
        this.sortBy = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}

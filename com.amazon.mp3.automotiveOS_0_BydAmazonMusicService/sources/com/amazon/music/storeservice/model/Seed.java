package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Seed implements Comparable<Seed> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Seed");
    private String seedId;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Seed seed) {
        if (seed == null) {
            return -1;
        }
        if (seed == this) {
            return 0;
        }
        String seedId = getSeedId();
        String seedId2 = seed.getSeedId();
        if (seedId != seedId2) {
            if (seedId == null) {
                return -1;
            }
            if (seedId2 == null) {
                return 1;
            }
            int iCompareTo = seedId.compareTo(seedId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String type = getType();
        String type2 = seed.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Seed)) {
            return false;
        }
        Seed seed = (Seed) obj;
        return internalEqualityCheck(getSeedId(), seed.getSeedId()) && internalEqualityCheck(getType(), seed.getType());
    }

    public String getSeedId() {
        return this.seedId;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSeedId(), getType());
    }

    public void setSeedId(String str) {
        this.seedId = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}

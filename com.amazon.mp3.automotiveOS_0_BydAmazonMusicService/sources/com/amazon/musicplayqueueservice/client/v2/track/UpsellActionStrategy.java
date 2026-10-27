package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class UpsellActionStrategy extends AbstractActionStrategy {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.UpsellActionStrategy");
    private String upsellType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractActionStrategy abstractActionStrategy) {
        if (abstractActionStrategy == null) {
            return -1;
        }
        if (abstractActionStrategy == this) {
            return 0;
        }
        if (!(abstractActionStrategy instanceof UpsellActionStrategy)) {
            return 1;
        }
        String upsellType = getUpsellType();
        String upsellType2 = ((UpsellActionStrategy) abstractActionStrategy).getUpsellType();
        if (upsellType != upsellType2) {
            if (upsellType == null) {
                return -1;
            }
            if (upsellType2 == null) {
                return 1;
            }
            int iCompareTo = upsellType.compareTo(upsellType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(abstractActionStrategy);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public boolean equals(Object obj) {
        if (obj instanceof UpsellActionStrategy) {
            return super.equals(obj) && internalEqualityCheck(getUpsellType(), ((UpsellActionStrategy) obj).getUpsellType());
        }
        return false;
    }

    public String getUpsellType() {
        return this.upsellType;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUpsellType());
    }

    public void setUpsellType(String str) {
        this.upsellType = str;
    }
}

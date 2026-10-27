package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RecentNonMetadataEntityActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentNonMetadataEntityActivity");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.nimblymusicservice.RecentActivityData, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentActivityData recentActivityData) {
        if (recentActivityData == null) {
            return -1;
        }
        if (recentActivityData == this) {
            return 0;
        }
        if (recentActivityData instanceof RecentNonMetadataEntityActivity) {
            return super.compareTo(recentActivityData);
        }
        return 1;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (obj instanceof RecentNonMetadataEntityActivity) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}

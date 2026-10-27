package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CuePoints implements Comparable<CuePoints> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.CuePoints");
    private int endOffsetInMS;
    private int startOffsetInMS;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CuePoints cuePoints) {
        if (cuePoints == null) {
            return -1;
        }
        if (cuePoints == this) {
            return 0;
        }
        if (getStartOffsetInMS() < cuePoints.getStartOffsetInMS()) {
            return -1;
        }
        if (getStartOffsetInMS() > cuePoints.getStartOffsetInMS()) {
            return 1;
        }
        if (getEndOffsetInMS() < cuePoints.getEndOffsetInMS()) {
            return -1;
        }
        return getEndOffsetInMS() > cuePoints.getEndOffsetInMS() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CuePoints)) {
            return false;
        }
        CuePoints cuePoints = (CuePoints) obj;
        return internalEqualityCheck(Integer.valueOf(getStartOffsetInMS()), Integer.valueOf(cuePoints.getStartOffsetInMS())) && internalEqualityCheck(Integer.valueOf(getEndOffsetInMS()), Integer.valueOf(cuePoints.getEndOffsetInMS()));
    }

    public int getEndOffsetInMS() {
        return this.endOffsetInMS;
    }

    public int getStartOffsetInMS() {
        return this.startOffsetInMS;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getStartOffsetInMS()), Integer.valueOf(getEndOffsetInMS()));
    }

    public void setEndOffsetInMS(int i) {
        this.endOffsetInMS = i;
    }

    public void setStartOffsetInMS(int i) {
        this.startOffsetInMS = i;
    }
}

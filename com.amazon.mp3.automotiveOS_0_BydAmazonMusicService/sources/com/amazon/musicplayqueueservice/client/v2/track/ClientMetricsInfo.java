package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ClientMetricsInfo implements Comparable<ClientMetricsInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.ClientMetricsInfo");
    private String contentBlockRef;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ClientMetricsInfo clientMetricsInfo) {
        String contentBlockRef;
        String contentBlockRef2;
        if (clientMetricsInfo == null) {
            return -1;
        }
        if (clientMetricsInfo != this && (contentBlockRef = getContentBlockRef()) != (contentBlockRef2 = clientMetricsInfo.getContentBlockRef())) {
            if (contentBlockRef == null) {
                return -1;
            }
            if (contentBlockRef2 == null) {
                return 1;
            }
            int iCompareTo = contentBlockRef.compareTo(contentBlockRef2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ClientMetricsInfo) {
            return internalEqualityCheck(getContentBlockRef(), ((ClientMetricsInfo) obj).getContentBlockRef());
        }
        return false;
    }

    public String getContentBlockRef() {
        return this.contentBlockRef;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentBlockRef());
    }

    public void setContentBlockRef(String str) {
        this.contentBlockRef = str;
    }
}

package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class TransferQueueResponse implements Comparable<TransferQueueResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.TransferQueueResponse");
    private String status;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TransferQueueResponse transferQueueResponse) {
        String status;
        String status2;
        if (transferQueueResponse == null) {
            return -1;
        }
        if (transferQueueResponse != this && (status = getStatus()) != (status2 = transferQueueResponse.getStatus())) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo = status.compareTo(status2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TransferQueueResponse) {
            return internalEqualityCheck(getStatus(), ((TransferQueueResponse) obj).getStatus());
        }
        return false;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getStatus());
    }

    public void setStatus(String str) {
        this.status = str;
    }
}

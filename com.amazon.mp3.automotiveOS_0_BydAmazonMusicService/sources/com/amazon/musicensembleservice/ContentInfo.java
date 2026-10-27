package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ContentInfo implements Comparable<ContentInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ContentInfo");
    private String contentInfoInstId;
    private String contentSrc;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ContentInfo contentInfo) {
        if (contentInfo == null) {
            return -1;
        }
        if (contentInfo == this) {
            return 0;
        }
        String contentSrc = getContentSrc();
        String contentSrc2 = contentInfo.getContentSrc();
        if (contentSrc != contentSrc2) {
            if (contentSrc == null) {
                return -1;
            }
            if (contentSrc2 == null) {
                return 1;
            }
            int iCompareTo = contentSrc.compareTo(contentSrc2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String contentInfoInstId = getContentInfoInstId();
        String contentInfoInstId2 = contentInfo.getContentInfoInstId();
        if (contentInfoInstId != contentInfoInstId2) {
            if (contentInfoInstId == null) {
                return -1;
            }
            if (contentInfoInstId2 == null) {
                return 1;
            }
            int iCompareTo2 = contentInfoInstId.compareTo(contentInfoInstId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentInfo)) {
            return false;
        }
        ContentInfo contentInfo = (ContentInfo) obj;
        return internalEqualityCheck(getContentSrc(), contentInfo.getContentSrc()) && internalEqualityCheck(getContentInfoInstId(), contentInfo.getContentInfoInstId());
    }

    public String getContentInfoInstId() {
        return this.contentInfoInstId;
    }

    public String getContentSrc() {
        return this.contentSrc;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentSrc(), getContentInfoInstId());
    }

    public void setContentInfoInstId(String str) {
        this.contentInfoInstId = str;
    }

    public void setContentSrc(String str) {
        this.contentSrc = str;
    }
}

package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class ContentInfo implements Comparable<ContentInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.ContentInfo");
    private String contentInfoInstId;
    private String contentSrc;
    private String rsasQueryId;
    private Map<String, String> rsasWeblabs;

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
        String rsasQueryId = getRsasQueryId();
        String rsasQueryId2 = contentInfo.getRsasQueryId();
        if (rsasQueryId != rsasQueryId2) {
            if (rsasQueryId == null) {
                return -1;
            }
            if (rsasQueryId2 == null) {
                return 1;
            }
            int iCompareTo = rsasQueryId.compareTo(rsasQueryId2);
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
        String contentSrc = getContentSrc();
        String contentSrc2 = contentInfo.getContentSrc();
        if (contentSrc != contentSrc2) {
            if (contentSrc == null) {
                return -1;
            }
            if (contentSrc2 == null) {
                return 1;
            }
            int iCompareTo3 = contentSrc.compareTo(contentSrc2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, String> rsasWeblabs = getRsasWeblabs();
        Map<String, String> rsasWeblabs2 = contentInfo.getRsasWeblabs();
        if (rsasWeblabs != rsasWeblabs2) {
            if (rsasWeblabs == null) {
                return -1;
            }
            if (rsasWeblabs2 == null) {
                return 1;
            }
            if (rsasWeblabs instanceof Comparable) {
                int iCompareTo4 = ((Comparable) rsasWeblabs).compareTo(rsasWeblabs2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!rsasWeblabs.equals(rsasWeblabs2)) {
                int iHashCode = rsasWeblabs.hashCode();
                int iHashCode2 = rsasWeblabs2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentInfo)) {
            return false;
        }
        ContentInfo contentInfo = (ContentInfo) obj;
        return internalEqualityCheck(getRsasQueryId(), contentInfo.getRsasQueryId()) && internalEqualityCheck(getContentInfoInstId(), contentInfo.getContentInfoInstId()) && internalEqualityCheck(getContentSrc(), contentInfo.getContentSrc()) && internalEqualityCheck(getRsasWeblabs(), contentInfo.getRsasWeblabs());
    }

    public String getContentInfoInstId() {
        return this.contentInfoInstId;
    }

    public String getContentSrc() {
        return this.contentSrc;
    }

    @Deprecated
    public String getRsasQueryId() {
        return this.rsasQueryId;
    }

    @Deprecated
    public Map<String, String> getRsasWeblabs() {
        return this.rsasWeblabs;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRsasQueryId(), getContentInfoInstId(), getContentSrc(), getRsasWeblabs());
    }

    public void setContentInfoInstId(String str) {
        this.contentInfoInstId = str;
    }

    public void setContentSrc(String str) {
        this.contentSrc = str;
    }

    @Deprecated
    public void setRsasQueryId(String str) {
        this.rsasQueryId = str;
    }

    @Deprecated
    public void setRsasWeblabs(Map<String, String> map) {
        this.rsasWeblabs = map;
    }
}

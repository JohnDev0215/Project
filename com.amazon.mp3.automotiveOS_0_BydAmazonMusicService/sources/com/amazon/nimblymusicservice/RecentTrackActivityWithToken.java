package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RecentTrackActivityWithToken implements Comparable<RecentTrackActivityWithToken> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentTrackActivityWithToken");
    private ContentInfo contentInfo;
    private String nextToken;
    private List<RecentPlayedTrackActivity> recentTrackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentTrackActivityWithToken recentTrackActivityWithToken) {
        if (recentTrackActivityWithToken == null) {
            return -1;
        }
        if (recentTrackActivityWithToken == this) {
            return 0;
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = recentTrackActivityWithToken.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo = contentInfo.compareTo(contentInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<RecentPlayedTrackActivity> recentTrackList = getRecentTrackList();
        List<RecentPlayedTrackActivity> recentTrackList2 = recentTrackActivityWithToken.getRecentTrackList();
        if (recentTrackList != recentTrackList2) {
            if (recentTrackList == null) {
                return -1;
            }
            if (recentTrackList2 == null) {
                return 1;
            }
            if (recentTrackList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) recentTrackList).compareTo(recentTrackList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!recentTrackList.equals(recentTrackList2)) {
                int iHashCode = recentTrackList.hashCode();
                int iHashCode2 = recentTrackList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String nextToken = getNextToken();
        String nextToken2 = recentTrackActivityWithToken.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo3 = nextToken.compareTo(nextToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecentTrackActivityWithToken)) {
            return false;
        }
        RecentTrackActivityWithToken recentTrackActivityWithToken = (RecentTrackActivityWithToken) obj;
        return internalEqualityCheck(getContentInfo(), recentTrackActivityWithToken.getContentInfo()) && internalEqualityCheck(getRecentTrackList(), recentTrackActivityWithToken.getRecentTrackList()) && internalEqualityCheck(getNextToken(), recentTrackActivityWithToken.getNextToken());
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<RecentPlayedTrackActivity> getRecentTrackList() {
        return this.recentTrackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentInfo(), getRecentTrackList(), getNextToken());
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setRecentTrackList(List<RecentPlayedTrackActivity> list) {
        this.recentTrackList = list;
    }
}

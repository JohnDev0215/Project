package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseGetResponse implements Comparable<BaseGetResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.BaseGetResponse");
    private String nextToken;
    private List<Item> resultList;
    private int totalCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseGetResponse baseGetResponse) {
        if (baseGetResponse == null) {
            return -1;
        }
        if (baseGetResponse == this) {
            return 0;
        }
        if (getTotalCount() < baseGetResponse.getTotalCount()) {
            return -1;
        }
        if (getTotalCount() > baseGetResponse.getTotalCount()) {
            return 1;
        }
        String nextToken = getNextToken();
        String nextToken2 = baseGetResponse.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo = nextToken.compareTo(nextToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Item> resultList = getResultList();
        List<Item> resultList2 = baseGetResponse.getResultList();
        if (resultList != resultList2) {
            if (resultList == null) {
                return -1;
            }
            if (resultList2 == null) {
                return 1;
            }
            if (resultList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) resultList).compareTo(resultList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!resultList.equals(resultList2)) {
                int iHashCode = resultList.hashCode();
                int iHashCode2 = resultList2.hashCode();
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
        if (!(obj instanceof BaseGetResponse)) {
            return false;
        }
        BaseGetResponse baseGetResponse = (BaseGetResponse) obj;
        return internalEqualityCheck(Integer.valueOf(getTotalCount()), Integer.valueOf(baseGetResponse.getTotalCount())) && internalEqualityCheck(getNextToken(), baseGetResponse.getNextToken()) && internalEqualityCheck(getResultList(), baseGetResponse.getResultList());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<Item> getResultList() {
        return this.resultList;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getTotalCount()), getNextToken(), getResultList());
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setResultList(List<Item> list) {
        this.resultList = list;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }
}

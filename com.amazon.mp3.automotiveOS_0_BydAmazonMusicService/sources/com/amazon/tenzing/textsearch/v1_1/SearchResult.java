package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SearchResult implements Comparable<SearchResult> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SearchResult");
    private String blockRef;
    private ContentInfo contentInfo;
    private Boolean hasFailure;
    private List<Hit> hits;
    private String label;
    private String nextPage;
    private SearchResultExplanation searchResultExplanation;
    private String searchResultMetricsContext;
    private Long totalHitCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SearchResult searchResult) {
        if (searchResult == null) {
            return -1;
        }
        if (searchResult == this) {
            return 0;
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = searchResult.getContentInfo();
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
        String label = getLabel();
        String label2 = searchResult.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo2 = label.compareTo(label2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        SearchResultExplanation searchResultExplanation = getSearchResultExplanation();
        SearchResultExplanation searchResultExplanation2 = searchResult.getSearchResultExplanation();
        if (searchResultExplanation != searchResultExplanation2) {
            if (searchResultExplanation == null) {
                return -1;
            }
            if (searchResultExplanation2 == null) {
                return 1;
            }
            int iCompareTo3 = searchResultExplanation.compareTo(searchResultExplanation2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Long totalHitCount = getTotalHitCount();
        Long totalHitCount2 = searchResult.getTotalHitCount();
        if (totalHitCount != totalHitCount2) {
            if (totalHitCount == null) {
                return -1;
            }
            if (totalHitCount2 == null) {
                return 1;
            }
            int iCompareTo4 = totalHitCount.compareTo(totalHitCount2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsHasFailure = isHasFailure();
        Boolean boolIsHasFailure2 = searchResult.isHasFailure();
        if (boolIsHasFailure != boolIsHasFailure2) {
            if (boolIsHasFailure == null) {
                return -1;
            }
            if (boolIsHasFailure2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsHasFailure.compareTo(boolIsHasFailure2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Hit> hits = getHits();
        List<Hit> hits2 = searchResult.getHits();
        if (hits != hits2) {
            if (hits == null) {
                return -1;
            }
            if (hits2 == null) {
                return 1;
            }
            if (hits instanceof Comparable) {
                int iCompareTo6 = ((Comparable) hits).compareTo(hits2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!hits.equals(hits2)) {
                int iHashCode = hits.hashCode();
                int iHashCode2 = hits2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = searchResult.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo7 = blockRef.compareTo(blockRef2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String nextPage = getNextPage();
        String nextPage2 = searchResult.getNextPage();
        if (nextPage != nextPage2) {
            if (nextPage == null) {
                return -1;
            }
            if (nextPage2 == null) {
                return 1;
            }
            int iCompareTo8 = nextPage.compareTo(nextPage2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String searchResultMetricsContext = getSearchResultMetricsContext();
        String searchResultMetricsContext2 = searchResult.getSearchResultMetricsContext();
        if (searchResultMetricsContext != searchResultMetricsContext2) {
            if (searchResultMetricsContext == null) {
                return -1;
            }
            if (searchResultMetricsContext2 == null) {
                return 1;
            }
            int iCompareTo9 = searchResultMetricsContext.compareTo(searchResultMetricsContext2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SearchResult)) {
            return false;
        }
        SearchResult searchResult = (SearchResult) obj;
        return internalEqualityCheck(getContentInfo(), searchResult.getContentInfo()) && internalEqualityCheck(getLabel(), searchResult.getLabel()) && internalEqualityCheck(getSearchResultExplanation(), searchResult.getSearchResultExplanation()) && internalEqualityCheck(getTotalHitCount(), searchResult.getTotalHitCount()) && internalEqualityCheck(isHasFailure(), searchResult.isHasFailure()) && internalEqualityCheck(getHits(), searchResult.getHits()) && internalEqualityCheck(getBlockRef(), searchResult.getBlockRef()) && internalEqualityCheck(getNextPage(), searchResult.getNextPage()) && internalEqualityCheck(getSearchResultMetricsContext(), searchResult.getSearchResultMetricsContext());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    @Deprecated
    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public List<Hit> getHits() {
        return this.hits;
    }

    public String getLabel() {
        return this.label;
    }

    public String getNextPage() {
        return this.nextPage;
    }

    public SearchResultExplanation getSearchResultExplanation() {
        return this.searchResultExplanation;
    }

    public String getSearchResultMetricsContext() {
        return this.searchResultMetricsContext;
    }

    public Long getTotalHitCount() {
        return this.totalHitCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentInfo(), getLabel(), getSearchResultExplanation(), getTotalHitCount(), isHasFailure(), getHits(), getBlockRef(), getNextPage(), getSearchResultMetricsContext());
    }

    public Boolean isHasFailure() {
        return this.hasFailure;
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setHasFailure(Boolean bool) {
        this.hasFailure = bool;
    }

    public void setHits(List<Hit> list) {
        this.hits = list;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setNextPage(String str) {
        this.nextPage = str;
    }

    public void setSearchResultExplanation(SearchResultExplanation searchResultExplanation) {
        this.searchResultExplanation = searchResultExplanation;
    }

    public void setSearchResultMetricsContext(String str) {
        this.searchResultMetricsContext = str;
    }

    public void setTotalHitCount(Long l) {
        this.totalHitCount = l;
    }
}

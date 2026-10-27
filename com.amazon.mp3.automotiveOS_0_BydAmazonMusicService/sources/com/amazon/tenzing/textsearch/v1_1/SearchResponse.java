package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SearchResponse implements Comparable<SearchResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SearchResponse");
    private FeaturesResponse features;
    private String queryId;
    private List<SearchResult> results;
    private SearchResponseExplanation searchResponseExplanation;

    public static class Builder {
        protected FeaturesResponse features;
        protected String queryId;
        protected List<SearchResult> results;
        protected SearchResponseExplanation searchResponseExplanation;

        public SearchResponse build() {
            SearchResponse searchResponse = new SearchResponse();
            populate(searchResponse);
            return searchResponse;
        }

        protected void populate(SearchResponse searchResponse) {
            searchResponse.setResults(this.results);
            searchResponse.setQueryId(this.queryId);
            searchResponse.setFeatures(this.features);
            searchResponse.setSearchResponseExplanation(this.searchResponseExplanation);
        }

        public Builder withResults(List<SearchResult> list) {
            this.results = list;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SearchResponse searchResponse) {
        if (searchResponse == null) {
            return -1;
        }
        if (searchResponse == this) {
            return 0;
        }
        List<SearchResult> results = getResults();
        List<SearchResult> results2 = searchResponse.getResults();
        if (results != results2) {
            if (results == null) {
                return -1;
            }
            if (results2 == null) {
                return 1;
            }
            if (results instanceof Comparable) {
                int iCompareTo = ((Comparable) results).compareTo(results2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!results.equals(results2)) {
                int iHashCode = results.hashCode();
                int iHashCode2 = results2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String queryId = getQueryId();
        String queryId2 = searchResponse.getQueryId();
        if (queryId != queryId2) {
            if (queryId == null) {
                return -1;
            }
            if (queryId2 == null) {
                return 1;
            }
            int iCompareTo2 = queryId.compareTo(queryId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        FeaturesResponse features = getFeatures();
        FeaturesResponse features2 = searchResponse.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            int iCompareTo3 = features.compareTo(features2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        SearchResponseExplanation searchResponseExplanation = getSearchResponseExplanation();
        SearchResponseExplanation searchResponseExplanation2 = searchResponse.getSearchResponseExplanation();
        if (searchResponseExplanation != searchResponseExplanation2) {
            if (searchResponseExplanation == null) {
                return -1;
            }
            if (searchResponseExplanation2 == null) {
                return 1;
            }
            int iCompareTo4 = searchResponseExplanation.compareTo(searchResponseExplanation2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SearchResponse)) {
            return false;
        }
        SearchResponse searchResponse = (SearchResponse) obj;
        return internalEqualityCheck(getResults(), searchResponse.getResults()) && internalEqualityCheck(getQueryId(), searchResponse.getQueryId()) && internalEqualityCheck(getFeatures(), searchResponse.getFeatures()) && internalEqualityCheck(getSearchResponseExplanation(), searchResponse.getSearchResponseExplanation());
    }

    public FeaturesResponse getFeatures() {
        return this.features;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public List<SearchResult> getResults() {
        return this.results;
    }

    public SearchResponseExplanation getSearchResponseExplanation() {
        return this.searchResponseExplanation;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getResults(), getQueryId(), getFeatures(), getSearchResponseExplanation());
    }

    public void setFeatures(FeaturesResponse featuresResponse) {
        this.features = featuresResponse;
    }

    public void setQueryId(String str) {
        this.queryId = str;
    }

    public void setResults(List<SearchResult> list) {
        this.results = list;
    }

    public void setSearchResponseExplanation(SearchResponseExplanation searchResponseExplanation) {
        this.searchResponseExplanation = searchResponseExplanation;
    }
}

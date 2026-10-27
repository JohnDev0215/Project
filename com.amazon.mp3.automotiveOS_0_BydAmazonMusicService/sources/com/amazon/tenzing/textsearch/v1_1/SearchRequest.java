package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SearchRequest implements Comparable<SearchRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SearchRequest");
    private CustomerIdentity customerIdentity;
    private Boolean explain;
    private FeaturesRequest features;
    private String locale;
    private String musicTerritory;
    private String query;
    private String queryMetadata;
    private List<ResultSpecification> resultSpecs;

    public static class Builder {
        protected CustomerIdentity customerIdentity;
        protected Boolean explain;
        protected FeaturesRequest features;
        protected String locale;
        protected String musicTerritory;
        protected String query;
        protected String queryMetadata;
        protected List<ResultSpecification> resultSpecs;

        public SearchRequest build() {
            SearchRequest searchRequest = new SearchRequest();
            populate(searchRequest);
            return searchRequest;
        }

        protected void populate(SearchRequest searchRequest) {
            searchRequest.setLocale(this.locale);
            searchRequest.setExplain(this.explain);
            searchRequest.setFeatures(this.features);
            searchRequest.setCustomerIdentity(this.customerIdentity);
            searchRequest.setResultSpecs(this.resultSpecs);
            searchRequest.setMusicTerritory(this.musicTerritory);
            searchRequest.setQuery(this.query);
            searchRequest.setQueryMetadata(this.queryMetadata);
        }

        public Builder withFeatures(FeaturesRequest featuresRequest) {
            this.features = featuresRequest;
            return this;
        }

        public Builder withQuery(String str) {
            this.query = str;
            return this;
        }

        public Builder withQueryMetadata(String str) {
            this.queryMetadata = str;
            return this;
        }

        public Builder withResultSpecs(List<ResultSpecification> list) {
            this.resultSpecs = list;
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
    public int compareTo(@Deprecated SearchRequest searchRequest) {
        if (searchRequest == null) {
            return -1;
        }
        if (searchRequest == this) {
            return 0;
        }
        String locale = getLocale();
        String locale2 = searchRequest.getLocale();
        if (locale != locale2) {
            if (locale == null) {
                return -1;
            }
            if (locale2 == null) {
                return 1;
            }
            int iCompareTo = locale.compareTo(locale2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsExplain = isExplain();
        Boolean boolIsExplain2 = searchRequest.isExplain();
        if (boolIsExplain != boolIsExplain2) {
            if (boolIsExplain == null) {
                return -1;
            }
            if (boolIsExplain2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsExplain.compareTo(boolIsExplain2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        FeaturesRequest features = getFeatures();
        FeaturesRequest features2 = searchRequest.getFeatures();
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
        CustomerIdentity customerIdentity = getCustomerIdentity();
        CustomerIdentity customerIdentity2 = searchRequest.getCustomerIdentity();
        if (customerIdentity != customerIdentity2) {
            if (customerIdentity == null) {
                return -1;
            }
            if (customerIdentity2 == null) {
                return 1;
            }
            int iCompareTo4 = customerIdentity.compareTo(customerIdentity2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<ResultSpecification> resultSpecs = getResultSpecs();
        List<ResultSpecification> resultSpecs2 = searchRequest.getResultSpecs();
        if (resultSpecs != resultSpecs2) {
            if (resultSpecs == null) {
                return -1;
            }
            if (resultSpecs2 == null) {
                return 1;
            }
            if (resultSpecs instanceof Comparable) {
                int iCompareTo5 = ((Comparable) resultSpecs).compareTo(resultSpecs2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!resultSpecs.equals(resultSpecs2)) {
                int iHashCode = resultSpecs.hashCode();
                int iHashCode2 = resultSpecs2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = searchRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo6 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String query = getQuery();
        String query2 = searchRequest.getQuery();
        if (query != query2) {
            if (query == null) {
                return -1;
            }
            if (query2 == null) {
                return 1;
            }
            int iCompareTo7 = query.compareTo(query2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String queryMetadata = getQueryMetadata();
        String queryMetadata2 = searchRequest.getQueryMetadata();
        if (queryMetadata != queryMetadata2) {
            if (queryMetadata == null) {
                return -1;
            }
            if (queryMetadata2 == null) {
                return 1;
            }
            int iCompareTo8 = queryMetadata.compareTo(queryMetadata2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SearchRequest)) {
            return false;
        }
        SearchRequest searchRequest = (SearchRequest) obj;
        return internalEqualityCheck(getLocale(), searchRequest.getLocale()) && internalEqualityCheck(isExplain(), searchRequest.isExplain()) && internalEqualityCheck(getFeatures(), searchRequest.getFeatures()) && internalEqualityCheck(getCustomerIdentity(), searchRequest.getCustomerIdentity()) && internalEqualityCheck(getResultSpecs(), searchRequest.getResultSpecs()) && internalEqualityCheck(getMusicTerritory(), searchRequest.getMusicTerritory()) && internalEqualityCheck(getQuery(), searchRequest.getQuery()) && internalEqualityCheck(getQueryMetadata(), searchRequest.getQueryMetadata());
    }

    public CustomerIdentity getCustomerIdentity() {
        return this.customerIdentity;
    }

    public FeaturesRequest getFeatures() {
        return this.features;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getQuery() {
        return this.query;
    }

    public String getQueryMetadata() {
        return this.queryMetadata;
    }

    public List<ResultSpecification> getResultSpecs() {
        return this.resultSpecs;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLocale(), isExplain(), getFeatures(), getCustomerIdentity(), getResultSpecs(), getMusicTerritory(), getQuery(), getQueryMetadata());
    }

    public Boolean isExplain() {
        return this.explain;
    }

    public void setCustomerIdentity(CustomerIdentity customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public void setExplain(Boolean bool) {
        this.explain = bool;
    }

    public void setFeatures(FeaturesRequest featuresRequest) {
        this.features = featuresRequest;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setQueryMetadata(String str) {
        this.queryMetadata = str;
    }

    public void setResultSpecs(List<ResultSpecification> list) {
        this.resultSpecs = list;
    }
}

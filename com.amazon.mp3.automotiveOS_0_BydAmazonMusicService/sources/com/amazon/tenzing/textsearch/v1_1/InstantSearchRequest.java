package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class InstantSearchRequest implements Comparable<InstantSearchRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantSearchRequest");
    private String activityId;
    private String contentTier;
    private CustomerIdentity customerIdentity;
    private InstantEntitySpecification entitySpec;
    private String locale;
    private Integer maxResults;
    private String musicTerritory;
    private String query;
    private String queryId;
    private InstantTextSpecification textSpec;

    public static class Builder {
        protected String activityId;
        protected String contentTier;
        protected CustomerIdentity customerIdentity;
        protected InstantEntitySpecification entitySpec;
        protected String locale;
        protected Integer maxResults;
        protected String musicTerritory;
        protected String query;
        protected String queryId;
        protected InstantTextSpecification textSpec;

        public InstantSearchRequest build() {
            InstantSearchRequest instantSearchRequest = new InstantSearchRequest();
            populate(instantSearchRequest);
            return instantSearchRequest;
        }

        protected void populate(InstantSearchRequest instantSearchRequest) {
            instantSearchRequest.setLocale(this.locale);
            instantSearchRequest.setCustomerIdentity(this.customerIdentity);
            instantSearchRequest.setMusicTerritory(this.musicTerritory);
            instantSearchRequest.setTextSpec(this.textSpec);
            instantSearchRequest.setQuery(this.query);
            instantSearchRequest.setMaxResults(this.maxResults);
            instantSearchRequest.setContentTier(this.contentTier);
            instantSearchRequest.setEntitySpec(this.entitySpec);
            instantSearchRequest.setActivityId(this.activityId);
            instantSearchRequest.setQueryId(this.queryId);
        }

        public Builder withActivityId(String str) {
            this.activityId = str;
            return this;
        }

        public Builder withContentTier(String str) {
            this.contentTier = str;
            return this;
        }

        public Builder withEntitySpec(InstantEntitySpecification instantEntitySpecification) {
            this.entitySpec = instantEntitySpecification;
            return this;
        }

        public Builder withMaxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public Builder withQuery(String str) {
            this.query = str;
            return this;
        }

        public Builder withQueryId(String str) {
            this.queryId = str;
            return this;
        }

        public Builder withTextSpec(InstantTextSpecification instantTextSpecification) {
            this.textSpec = instantTextSpecification;
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
    public int compareTo(@Deprecated InstantSearchRequest instantSearchRequest) {
        if (instantSearchRequest == null) {
            return -1;
        }
        if (instantSearchRequest == this) {
            return 0;
        }
        String locale = getLocale();
        String locale2 = instantSearchRequest.getLocale();
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
        CustomerIdentity customerIdentity = getCustomerIdentity();
        CustomerIdentity customerIdentity2 = instantSearchRequest.getCustomerIdentity();
        if (customerIdentity != customerIdentity2) {
            if (customerIdentity == null) {
                return -1;
            }
            if (customerIdentity2 == null) {
                return 1;
            }
            int iCompareTo2 = customerIdentity.compareTo(customerIdentity2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = instantSearchRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo3 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        InstantTextSpecification textSpec = getTextSpec();
        InstantTextSpecification textSpec2 = instantSearchRequest.getTextSpec();
        if (textSpec != textSpec2) {
            if (textSpec == null) {
                return -1;
            }
            if (textSpec2 == null) {
                return 1;
            }
            int iCompareTo4 = textSpec.compareTo(textSpec2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String query = getQuery();
        String query2 = instantSearchRequest.getQuery();
        if (query != query2) {
            if (query == null) {
                return -1;
            }
            if (query2 == null) {
                return 1;
            }
            int iCompareTo5 = query.compareTo(query2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = instantSearchRequest.getMaxResults();
        if (maxResults != maxResults2) {
            if (maxResults == null) {
                return -1;
            }
            if (maxResults2 == null) {
                return 1;
            }
            int iCompareTo6 = maxResults.compareTo(maxResults2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String contentTier = getContentTier();
        String contentTier2 = instantSearchRequest.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            int iCompareTo7 = contentTier.compareTo(contentTier2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        InstantEntitySpecification entitySpec = getEntitySpec();
        InstantEntitySpecification entitySpec2 = instantSearchRequest.getEntitySpec();
        if (entitySpec != entitySpec2) {
            if (entitySpec == null) {
                return -1;
            }
            if (entitySpec2 == null) {
                return 1;
            }
            int iCompareTo8 = entitySpec.compareTo(entitySpec2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String activityId = getActivityId();
        String activityId2 = instantSearchRequest.getActivityId();
        if (activityId != activityId2) {
            if (activityId == null) {
                return -1;
            }
            if (activityId2 == null) {
                return 1;
            }
            int iCompareTo9 = activityId.compareTo(activityId2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String queryId = getQueryId();
        String queryId2 = instantSearchRequest.getQueryId();
        if (queryId != queryId2) {
            if (queryId == null) {
                return -1;
            }
            if (queryId2 == null) {
                return 1;
            }
            int iCompareTo10 = queryId.compareTo(queryId2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InstantSearchRequest)) {
            return false;
        }
        InstantSearchRequest instantSearchRequest = (InstantSearchRequest) obj;
        return internalEqualityCheck(getLocale(), instantSearchRequest.getLocale()) && internalEqualityCheck(getCustomerIdentity(), instantSearchRequest.getCustomerIdentity()) && internalEqualityCheck(getMusicTerritory(), instantSearchRequest.getMusicTerritory()) && internalEqualityCheck(getTextSpec(), instantSearchRequest.getTextSpec()) && internalEqualityCheck(getQuery(), instantSearchRequest.getQuery()) && internalEqualityCheck(getMaxResults(), instantSearchRequest.getMaxResults()) && internalEqualityCheck(getContentTier(), instantSearchRequest.getContentTier()) && internalEqualityCheck(getEntitySpec(), instantSearchRequest.getEntitySpec()) && internalEqualityCheck(getActivityId(), instantSearchRequest.getActivityId()) && internalEqualityCheck(getQueryId(), instantSearchRequest.getQueryId());
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getContentTier() {
        return this.contentTier;
    }

    public CustomerIdentity getCustomerIdentity() {
        return this.customerIdentity;
    }

    public InstantEntitySpecification getEntitySpec() {
        return this.entitySpec;
    }

    public String getLocale() {
        return this.locale;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getQuery() {
        return this.query;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public InstantTextSpecification getTextSpec() {
        return this.textSpec;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLocale(), getCustomerIdentity(), getMusicTerritory(), getTextSpec(), getQuery(), getMaxResults(), getContentTier(), getEntitySpec(), getActivityId(), getQueryId());
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public void setContentTier(String str) {
        this.contentTier = str;
    }

    public void setCustomerIdentity(CustomerIdentity customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public void setEntitySpec(InstantEntitySpecification instantEntitySpecification) {
        this.entitySpec = instantEntitySpecification;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setQueryId(String str) {
        this.queryId = str;
    }

    public void setTextSpec(InstantTextSpecification instantTextSpecification) {
        this.textSpec = instantTextSpecification;
    }
}

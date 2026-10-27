package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ResultSpecification implements Comparable<ResultSpecification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.ResultSpecification");
    private ContentRestrictions contentRestrictions;
    private List<DocumentSpecification> documentSpecs;
    private String label;
    private Integer maxResults;
    private String pageToken;
    private TopHitSpecification topHitSpec;

    public static class Builder {
        protected ContentRestrictions contentRestrictions;
        protected List<DocumentSpecification> documentSpecs;
        protected String label;
        protected Integer maxResults;
        protected String pageToken;
        protected TopHitSpecification topHitSpec;

        public ResultSpecification build() {
            ResultSpecification resultSpecification = new ResultSpecification();
            populate(resultSpecification);
            return resultSpecification;
        }

        protected void populate(ResultSpecification resultSpecification) {
            resultSpecification.setMaxResults(this.maxResults);
            resultSpecification.setPageToken(this.pageToken);
            resultSpecification.setTopHitSpec(this.topHitSpec);
            resultSpecification.setContentRestrictions(this.contentRestrictions);
            resultSpecification.setLabel(this.label);
            resultSpecification.setDocumentSpecs(this.documentSpecs);
        }

        public Builder withContentRestrictions(ContentRestrictions contentRestrictions) {
            this.contentRestrictions = contentRestrictions;
            return this;
        }

        public Builder withDocumentSpecs(List<DocumentSpecification> list) {
            this.documentSpecs = list;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withMaxResults(Integer num) {
            this.maxResults = num;
            return this;
        }

        public Builder withPageToken(String str) {
            this.pageToken = str;
            return this;
        }

        public Builder withTopHitSpec(TopHitSpecification topHitSpecification) {
            this.topHitSpec = topHitSpecification;
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
    public int compareTo(@Deprecated ResultSpecification resultSpecification) {
        if (resultSpecification == null) {
            return -1;
        }
        if (resultSpecification == this) {
            return 0;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = resultSpecification.getMaxResults();
        if (maxResults != maxResults2) {
            if (maxResults == null) {
                return -1;
            }
            if (maxResults2 == null) {
                return 1;
            }
            int iCompareTo = maxResults.compareTo(maxResults2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = resultSpecification.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo2 = pageToken.compareTo(pageToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        TopHitSpecification topHitSpec = getTopHitSpec();
        TopHitSpecification topHitSpec2 = resultSpecification.getTopHitSpec();
        if (topHitSpec != topHitSpec2) {
            if (topHitSpec == null) {
                return -1;
            }
            if (topHitSpec2 == null) {
                return 1;
            }
            int iCompareTo3 = topHitSpec.compareTo(topHitSpec2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ContentRestrictions contentRestrictions = getContentRestrictions();
        ContentRestrictions contentRestrictions2 = resultSpecification.getContentRestrictions();
        if (contentRestrictions != contentRestrictions2) {
            if (contentRestrictions == null) {
                return -1;
            }
            if (contentRestrictions2 == null) {
                return 1;
            }
            int iCompareTo4 = contentRestrictions.compareTo(contentRestrictions2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String label = getLabel();
        String label2 = resultSpecification.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo5 = label.compareTo(label2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<DocumentSpecification> documentSpecs = getDocumentSpecs();
        List<DocumentSpecification> documentSpecs2 = resultSpecification.getDocumentSpecs();
        if (documentSpecs != documentSpecs2) {
            if (documentSpecs == null) {
                return -1;
            }
            if (documentSpecs2 == null) {
                return 1;
            }
            if (documentSpecs instanceof Comparable) {
                int iCompareTo6 = ((Comparable) documentSpecs).compareTo(documentSpecs2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!documentSpecs.equals(documentSpecs2)) {
                int iHashCode = documentSpecs.hashCode();
                int iHashCode2 = documentSpecs2.hashCode();
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
        if (!(obj instanceof ResultSpecification)) {
            return false;
        }
        ResultSpecification resultSpecification = (ResultSpecification) obj;
        return internalEqualityCheck(getMaxResults(), resultSpecification.getMaxResults()) && internalEqualityCheck(getPageToken(), resultSpecification.getPageToken()) && internalEqualityCheck(getTopHitSpec(), resultSpecification.getTopHitSpec()) && internalEqualityCheck(getContentRestrictions(), resultSpecification.getContentRestrictions()) && internalEqualityCheck(getLabel(), resultSpecification.getLabel()) && internalEqualityCheck(getDocumentSpecs(), resultSpecification.getDocumentSpecs());
    }

    public ContentRestrictions getContentRestrictions() {
        return this.contentRestrictions;
    }

    public List<DocumentSpecification> getDocumentSpecs() {
        return this.documentSpecs;
    }

    public String getLabel() {
        return this.label;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public TopHitSpecification getTopHitSpec() {
        return this.topHitSpec;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMaxResults(), getPageToken(), getTopHitSpec(), getContentRestrictions(), getLabel(), getDocumentSpecs());
    }

    public void setContentRestrictions(ContentRestrictions contentRestrictions) {
        this.contentRestrictions = contentRestrictions;
    }

    public void setDocumentSpecs(List<DocumentSpecification> list) {
        this.documentSpecs = list;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setTopHitSpec(TopHitSpecification topHitSpecification) {
        this.topHitSpec = topHitSpecification;
    }
}

package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InstantDocumentSpecification implements Comparable<InstantDocumentSpecification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantDocumentSpecification");
    private List<String> fields;
    private List<String> filters;
    private String type;

    public static class Builder {
        protected List<String> fields;
        protected List<String> filters;
        protected String type;

        public InstantDocumentSpecification build() {
            InstantDocumentSpecification instantDocumentSpecification = new InstantDocumentSpecification();
            populate(instantDocumentSpecification);
            return instantDocumentSpecification;
        }

        protected void populate(InstantDocumentSpecification instantDocumentSpecification) {
            instantDocumentSpecification.setFilters(this.filters);
            instantDocumentSpecification.setType(this.type);
            instantDocumentSpecification.setFields(this.fields);
        }

        public Builder withFields(List<String> list) {
            this.fields = list;
            return this;
        }

        public Builder withFilters(List<String> list) {
            this.filters = list;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
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
    public int compareTo(@Deprecated InstantDocumentSpecification instantDocumentSpecification) {
        if (instantDocumentSpecification == null) {
            return -1;
        }
        if (instantDocumentSpecification == this) {
            return 0;
        }
        List<String> filters = getFilters();
        List<String> filters2 = instantDocumentSpecification.getFilters();
        if (filters != filters2) {
            if (filters == null) {
                return -1;
            }
            if (filters2 == null) {
                return 1;
            }
            if (filters instanceof Comparable) {
                int iCompareTo = ((Comparable) filters).compareTo(filters2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!filters.equals(filters2)) {
                int iHashCode = filters.hashCode();
                int iHashCode2 = filters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String type = getType();
        String type2 = instantDocumentSpecification.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> fields = getFields();
        List<String> fields2 = instantDocumentSpecification.getFields();
        if (fields != fields2) {
            if (fields == null) {
                return -1;
            }
            if (fields2 == null) {
                return 1;
            }
            if (fields instanceof Comparable) {
                int iCompareTo3 = ((Comparable) fields).compareTo(fields2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!fields.equals(fields2)) {
                int iHashCode3 = fields.hashCode();
                int iHashCode4 = fields2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InstantDocumentSpecification)) {
            return false;
        }
        InstantDocumentSpecification instantDocumentSpecification = (InstantDocumentSpecification) obj;
        return internalEqualityCheck(getFilters(), instantDocumentSpecification.getFilters()) && internalEqualityCheck(getType(), instantDocumentSpecification.getType()) && internalEqualityCheck(getFields(), instantDocumentSpecification.getFields());
    }

    public List<String> getFields() {
        return this.fields;
    }

    public List<String> getFilters() {
        return this.filters;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getFilters(), getType(), getFields());
    }

    public void setFields(List<String> list) {
        this.fields = list;
    }

    public void setFilters(List<String> list) {
        this.filters = list;
    }

    public void setType(String str) {
        this.type = str;
    }
}

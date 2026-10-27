package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InstantTextSpecification implements Comparable<InstantTextSpecification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantTextSpecification");
    private Boolean enabled;
    private List<String> fields;

    public static class Builder {
        protected Boolean enabled;
        protected List<String> fields;

        public InstantTextSpecification build() {
            InstantTextSpecification instantTextSpecification = new InstantTextSpecification();
            populate(instantTextSpecification);
            return instantTextSpecification;
        }

        protected void populate(InstantTextSpecification instantTextSpecification) {
            instantTextSpecification.setFields(this.fields);
            instantTextSpecification.setEnabled(this.enabled);
        }

        public Builder withEnabled(Boolean bool) {
            this.enabled = bool;
            return this;
        }

        public Builder withFields(List<String> list) {
            this.fields = list;
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
    public int compareTo(@Deprecated InstantTextSpecification instantTextSpecification) {
        if (instantTextSpecification == null) {
            return -1;
        }
        if (instantTextSpecification == this) {
            return 0;
        }
        List<String> fields = getFields();
        List<String> fields2 = instantTextSpecification.getFields();
        if (fields != fields2) {
            if (fields == null) {
                return -1;
            }
            if (fields2 == null) {
                return 1;
            }
            if (fields instanceof Comparable) {
                int iCompareTo = ((Comparable) fields).compareTo(fields2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!fields.equals(fields2)) {
                int iHashCode = fields.hashCode();
                int iHashCode2 = fields2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsEnabled = isEnabled();
        Boolean boolIsEnabled2 = instantTextSpecification.isEnabled();
        if (boolIsEnabled != boolIsEnabled2) {
            if (boolIsEnabled == null) {
                return -1;
            }
            if (boolIsEnabled2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsEnabled.compareTo(boolIsEnabled2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InstantTextSpecification)) {
            return false;
        }
        InstantTextSpecification instantTextSpecification = (InstantTextSpecification) obj;
        return internalEqualityCheck(getFields(), instantTextSpecification.getFields()) && internalEqualityCheck(isEnabled(), instantTextSpecification.isEnabled());
    }

    public List<String> getFields() {
        return this.fields;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getFields(), isEnabled());
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public void setFields(List<String> list) {
        this.fields = list;
    }
}

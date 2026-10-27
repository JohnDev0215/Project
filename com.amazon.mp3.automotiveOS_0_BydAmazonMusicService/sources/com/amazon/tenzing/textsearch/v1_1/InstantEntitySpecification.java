package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InstantEntitySpecification implements Comparable<InstantEntitySpecification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantEntitySpecification");
    private List<InstantDocumentSpecification> documentSpecs;

    public static class Builder {
        protected List<InstantDocumentSpecification> documentSpecs;

        public InstantEntitySpecification build() {
            InstantEntitySpecification instantEntitySpecification = new InstantEntitySpecification();
            populate(instantEntitySpecification);
            return instantEntitySpecification;
        }

        protected void populate(InstantEntitySpecification instantEntitySpecification) {
            instantEntitySpecification.setDocumentSpecs(this.documentSpecs);
        }

        public Builder withDocumentSpecs(List<InstantDocumentSpecification> list) {
            this.documentSpecs = list;
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
    public int compareTo(@Deprecated InstantEntitySpecification instantEntitySpecification) {
        List<InstantDocumentSpecification> documentSpecs;
        List<InstantDocumentSpecification> documentSpecs2;
        if (instantEntitySpecification == null) {
            return -1;
        }
        if (instantEntitySpecification != this && (documentSpecs = getDocumentSpecs()) != (documentSpecs2 = instantEntitySpecification.getDocumentSpecs())) {
            if (documentSpecs == null) {
                return -1;
            }
            if (documentSpecs2 == null) {
                return 1;
            }
            if (documentSpecs instanceof Comparable) {
                int iCompareTo = ((Comparable) documentSpecs).compareTo(documentSpecs2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        if (obj instanceof InstantEntitySpecification) {
            return internalEqualityCheck(getDocumentSpecs(), ((InstantEntitySpecification) obj).getDocumentSpecs());
        }
        return false;
    }

    public List<InstantDocumentSpecification> getDocumentSpecs() {
        return this.documentSpecs;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDocumentSpecs());
    }

    public void setDocumentSpecs(List<InstantDocumentSpecification> list) {
        this.documentSpecs = list;
    }
}

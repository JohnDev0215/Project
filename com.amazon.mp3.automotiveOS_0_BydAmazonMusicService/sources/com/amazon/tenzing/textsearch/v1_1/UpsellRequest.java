package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class UpsellRequest implements Comparable<UpsellRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.UpsellRequest");
    private Boolean allowUpsellForCatalogContent;

    public static class Builder {
        protected Boolean allowUpsellForCatalogContent;

        public UpsellRequest build() {
            UpsellRequest upsellRequest = new UpsellRequest();
            populate(upsellRequest);
            return upsellRequest;
        }

        protected void populate(UpsellRequest upsellRequest) {
            upsellRequest.setAllowUpsellForCatalogContent(this.allowUpsellForCatalogContent);
        }

        public Builder withAllowUpsellForCatalogContent(Boolean bool) {
            this.allowUpsellForCatalogContent = bool;
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
    public int compareTo(@Deprecated UpsellRequest upsellRequest) {
        Boolean boolIsAllowUpsellForCatalogContent;
        Boolean boolIsAllowUpsellForCatalogContent2;
        if (upsellRequest == null) {
            return -1;
        }
        if (upsellRequest != this && (boolIsAllowUpsellForCatalogContent = isAllowUpsellForCatalogContent()) != (boolIsAllowUpsellForCatalogContent2 = upsellRequest.isAllowUpsellForCatalogContent())) {
            if (boolIsAllowUpsellForCatalogContent == null) {
                return -1;
            }
            if (boolIsAllowUpsellForCatalogContent2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAllowUpsellForCatalogContent.compareTo(boolIsAllowUpsellForCatalogContent2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof UpsellRequest) {
            return internalEqualityCheck(isAllowUpsellForCatalogContent(), ((UpsellRequest) obj).isAllowUpsellForCatalogContent());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isAllowUpsellForCatalogContent());
    }

    public Boolean isAllowUpsellForCatalogContent() {
        return this.allowUpsellForCatalogContent;
    }

    public void setAllowUpsellForCatalogContent(Boolean bool) {
        this.allowUpsellForCatalogContent = bool;
    }
}

package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AllowedParentalControls implements Comparable<AllowedParentalControls> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.AllowedParentalControls");
    private Boolean hasExplicitLanguage;

    public static class Builder {
        protected Boolean hasExplicitLanguage;

        public AllowedParentalControls build() {
            AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
            populate(allowedParentalControls);
            return allowedParentalControls;
        }

        protected void populate(AllowedParentalControls allowedParentalControls) {
            allowedParentalControls.setHasExplicitLanguage(this.hasExplicitLanguage);
        }

        public Builder withHasExplicitLanguage(Boolean bool) {
            this.hasExplicitLanguage = bool;
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
    public int compareTo(@Deprecated AllowedParentalControls allowedParentalControls) {
        Boolean boolIsHasExplicitLanguage;
        Boolean boolIsHasExplicitLanguage2;
        if (allowedParentalControls == null) {
            return -1;
        }
        if (allowedParentalControls != this && (boolIsHasExplicitLanguage = isHasExplicitLanguage()) != (boolIsHasExplicitLanguage2 = allowedParentalControls.isHasExplicitLanguage())) {
            if (boolIsHasExplicitLanguage == null) {
                return -1;
            }
            if (boolIsHasExplicitLanguage2 == null) {
                return 1;
            }
            int iCompareTo = boolIsHasExplicitLanguage.compareTo(boolIsHasExplicitLanguage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AllowedParentalControls) {
            return internalEqualityCheck(isHasExplicitLanguage(), ((AllowedParentalControls) obj).isHasExplicitLanguage());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isHasExplicitLanguage());
    }

    public Boolean isHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public void setHasExplicitLanguage(Boolean bool) {
        this.hasExplicitLanguage = bool;
    }
}

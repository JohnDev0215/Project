package com.amazon.cirrus.shared.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUseUrl extends TermsOfUse {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.shared.model.TermsOfUseUrl");
    private String url;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.TermsOfUse, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TermsOfUse termsOfUse) {
        if (termsOfUse == null) {
            return -1;
        }
        if (termsOfUse == this) {
            return 0;
        }
        if (!(termsOfUse instanceof TermsOfUseUrl)) {
            return 1;
        }
        String url = getUrl();
        String url2 = ((TermsOfUseUrl) termsOfUse).getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo = url.compareTo(url2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(termsOfUse);
    }

    @Override // com.amazon.cirrus.shared.model.TermsOfUse
    public boolean equals(Object obj) {
        if (obj instanceof TermsOfUseUrl) {
            return super.equals(obj) && internalEqualityCheck(getUrl(), ((TermsOfUseUrl) obj).getUrl());
        }
        return false;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // com.amazon.cirrus.shared.model.TermsOfUse
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUrl());
    }
}

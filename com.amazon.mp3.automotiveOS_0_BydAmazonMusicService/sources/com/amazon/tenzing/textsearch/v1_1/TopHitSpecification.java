package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class TopHitSpecification implements Comparable<TopHitSpecification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.TopHitSpecification");
    private Boolean shouldUpsellMusicSubscription;

    public static class Builder {
        protected Boolean shouldUpsellMusicSubscription;

        public TopHitSpecification build() {
            TopHitSpecification topHitSpecification = new TopHitSpecification();
            populate(topHitSpecification);
            return topHitSpecification;
        }

        protected void populate(TopHitSpecification topHitSpecification) {
            topHitSpecification.setShouldUpsellMusicSubscription(this.shouldUpsellMusicSubscription);
        }

        public Builder withShouldUpsellMusicSubscription(Boolean bool) {
            this.shouldUpsellMusicSubscription = bool;
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
    public int compareTo(@Deprecated TopHitSpecification topHitSpecification) {
        Boolean boolIsShouldUpsellMusicSubscription;
        Boolean boolIsShouldUpsellMusicSubscription2;
        if (topHitSpecification == null) {
            return -1;
        }
        if (topHitSpecification != this && (boolIsShouldUpsellMusicSubscription = isShouldUpsellMusicSubscription()) != (boolIsShouldUpsellMusicSubscription2 = topHitSpecification.isShouldUpsellMusicSubscription())) {
            if (boolIsShouldUpsellMusicSubscription == null) {
                return -1;
            }
            if (boolIsShouldUpsellMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo = boolIsShouldUpsellMusicSubscription.compareTo(boolIsShouldUpsellMusicSubscription2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TopHitSpecification) {
            return internalEqualityCheck(isShouldUpsellMusicSubscription(), ((TopHitSpecification) obj).isShouldUpsellMusicSubscription());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isShouldUpsellMusicSubscription());
    }

    public Boolean isShouldUpsellMusicSubscription() {
        return this.shouldUpsellMusicSubscription;
    }

    public void setShouldUpsellMusicSubscription(Boolean bool) {
        this.shouldUpsellMusicSubscription = bool;
    }
}

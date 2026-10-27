package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.music.platform.model.ParentalControls;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ContentRestrictions implements Comparable<ContentRestrictions> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.ContentRestrictions");
    private ParentalControls allowedParentalControls;
    private AssetQuality assetQuality;
    private String contentTier;
    private Eligibility eligibility;

    public static class Builder {
        protected ParentalControls allowedParentalControls;
        protected AssetQuality assetQuality;
        protected String contentTier;
        protected Eligibility eligibility;

        public ContentRestrictions build() {
            ContentRestrictions contentRestrictions = new ContentRestrictions();
            populate(contentRestrictions);
            return contentRestrictions;
        }

        protected void populate(ContentRestrictions contentRestrictions) {
            contentRestrictions.setEligibility(this.eligibility);
            contentRestrictions.setAssetQuality(this.assetQuality);
            contentRestrictions.setAllowedParentalControls(this.allowedParentalControls);
            contentRestrictions.setContentTier(this.contentTier);
        }

        public Builder withAllowedParentalControls(ParentalControls parentalControls) {
            this.allowedParentalControls = parentalControls;
            return this;
        }

        public Builder withAssetQuality(AssetQuality assetQuality) {
            this.assetQuality = assetQuality;
            return this;
        }

        public Builder withContentTier(String str) {
            this.contentTier = str;
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
    public int compareTo(@Deprecated ContentRestrictions contentRestrictions) {
        if (contentRestrictions == null) {
            return -1;
        }
        if (contentRestrictions == this) {
            return 0;
        }
        Eligibility eligibility = getEligibility();
        Eligibility eligibility2 = contentRestrictions.getEligibility();
        if (eligibility != eligibility2) {
            if (eligibility == null) {
                return -1;
            }
            if (eligibility2 == null) {
                return 1;
            }
            int iCompareTo = eligibility.compareTo(eligibility2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        AssetQuality assetQuality = getAssetQuality();
        AssetQuality assetQuality2 = contentRestrictions.getAssetQuality();
        if (assetQuality != assetQuality2) {
            if (assetQuality == null) {
                return -1;
            }
            if (assetQuality2 == null) {
                return 1;
            }
            int iCompareTo2 = assetQuality.compareTo(assetQuality2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ParentalControls allowedParentalControls = getAllowedParentalControls();
        ParentalControls allowedParentalControls2 = contentRestrictions.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo3 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String contentTier = getContentTier();
        String contentTier2 = contentRestrictions.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            int iCompareTo4 = contentTier.compareTo(contentTier2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ContentRestrictions)) {
            return false;
        }
        ContentRestrictions contentRestrictions = (ContentRestrictions) obj;
        return internalEqualityCheck(getEligibility(), contentRestrictions.getEligibility()) && internalEqualityCheck(getAssetQuality(), contentRestrictions.getAssetQuality()) && internalEqualityCheck(getAllowedParentalControls(), contentRestrictions.getAllowedParentalControls()) && internalEqualityCheck(getContentTier(), contentRestrictions.getContentTier());
    }

    public ParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public AssetQuality getAssetQuality() {
        return this.assetQuality;
    }

    public String getContentTier() {
        return this.contentTier;
    }

    @Deprecated
    public Eligibility getEligibility() {
        return this.eligibility;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEligibility(), getAssetQuality(), getAllowedParentalControls(), getContentTier());
    }

    public void setAllowedParentalControls(ParentalControls parentalControls) {
        this.allowedParentalControls = parentalControls;
    }

    public void setAssetQuality(AssetQuality assetQuality) {
        this.assetQuality = assetQuality;
    }

    public void setContentTier(String str) {
        this.contentTier = str;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }
}

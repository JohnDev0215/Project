package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class FeaturesRequest implements Comparable<FeaturesRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.FeaturesRequest");
    private SpellCorrectionRequest spellCorrection;
    private SpiritualRequest spiritual;
    private UpsellRequest upsell;

    public static class Builder {
        protected SpellCorrectionRequest spellCorrection;
        protected SpiritualRequest spiritual;
        protected UpsellRequest upsell;

        public FeaturesRequest build() {
            FeaturesRequest featuresRequest = new FeaturesRequest();
            populate(featuresRequest);
            return featuresRequest;
        }

        protected void populate(FeaturesRequest featuresRequest) {
            featuresRequest.setSpellCorrection(this.spellCorrection);
            featuresRequest.setSpiritual(this.spiritual);
            featuresRequest.setUpsell(this.upsell);
        }

        public Builder withSpellCorrection(SpellCorrectionRequest spellCorrectionRequest) {
            this.spellCorrection = spellCorrectionRequest;
            return this;
        }

        public Builder withUpsell(UpsellRequest upsellRequest) {
            this.upsell = upsellRequest;
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
    public int compareTo(@Deprecated FeaturesRequest featuresRequest) {
        if (featuresRequest == null) {
            return -1;
        }
        if (featuresRequest == this) {
            return 0;
        }
        SpellCorrectionRequest spellCorrection = getSpellCorrection();
        SpellCorrectionRequest spellCorrection2 = featuresRequest.getSpellCorrection();
        if (spellCorrection != spellCorrection2) {
            if (spellCorrection == null) {
                return -1;
            }
            if (spellCorrection2 == null) {
                return 1;
            }
            int iCompareTo = spellCorrection.compareTo(spellCorrection2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        SpiritualRequest spiritual = getSpiritual();
        SpiritualRequest spiritual2 = featuresRequest.getSpiritual();
        if (spiritual != spiritual2) {
            if (spiritual == null) {
                return -1;
            }
            if (spiritual2 == null) {
                return 1;
            }
            int iCompareTo2 = spiritual.compareTo(spiritual2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        UpsellRequest upsell = getUpsell();
        UpsellRequest upsell2 = featuresRequest.getUpsell();
        if (upsell != upsell2) {
            if (upsell == null) {
                return -1;
            }
            if (upsell2 == null) {
                return 1;
            }
            int iCompareTo3 = upsell.compareTo(upsell2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturesRequest)) {
            return false;
        }
        FeaturesRequest featuresRequest = (FeaturesRequest) obj;
        return internalEqualityCheck(getSpellCorrection(), featuresRequest.getSpellCorrection()) && internalEqualityCheck(getSpiritual(), featuresRequest.getSpiritual()) && internalEqualityCheck(getUpsell(), featuresRequest.getUpsell());
    }

    public SpellCorrectionRequest getSpellCorrection() {
        return this.spellCorrection;
    }

    public SpiritualRequest getSpiritual() {
        return this.spiritual;
    }

    public UpsellRequest getUpsell() {
        return this.upsell;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSpellCorrection(), getSpiritual(), getUpsell());
    }

    public void setSpellCorrection(SpellCorrectionRequest spellCorrectionRequest) {
        this.spellCorrection = spellCorrectionRequest;
    }

    public void setSpiritual(SpiritualRequest spiritualRequest) {
        this.spiritual = spiritualRequest;
    }

    public void setUpsell(UpsellRequest upsellRequest) {
        this.upsell = upsellRequest;
    }
}

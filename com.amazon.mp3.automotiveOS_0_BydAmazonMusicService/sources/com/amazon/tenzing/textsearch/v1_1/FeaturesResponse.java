package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class FeaturesResponse implements Comparable<FeaturesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.FeaturesResponse");
    private SpellCorrectionResponse spellCorrection;
    private SpiritualResponse spiritual;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated FeaturesResponse featuresResponse) {
        if (featuresResponse == null) {
            return -1;
        }
        if (featuresResponse == this) {
            return 0;
        }
        SpiritualResponse spiritual = getSpiritual();
        SpiritualResponse spiritual2 = featuresResponse.getSpiritual();
        if (spiritual != spiritual2) {
            if (spiritual == null) {
                return -1;
            }
            if (spiritual2 == null) {
                return 1;
            }
            int iCompareTo = spiritual.compareTo(spiritual2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        SpellCorrectionResponse spellCorrection = getSpellCorrection();
        SpellCorrectionResponse spellCorrection2 = featuresResponse.getSpellCorrection();
        if (spellCorrection != spellCorrection2) {
            if (spellCorrection == null) {
                return -1;
            }
            if (spellCorrection2 == null) {
                return 1;
            }
            int iCompareTo2 = spellCorrection.compareTo(spellCorrection2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturesResponse)) {
            return false;
        }
        FeaturesResponse featuresResponse = (FeaturesResponse) obj;
        return internalEqualityCheck(getSpiritual(), featuresResponse.getSpiritual()) && internalEqualityCheck(getSpellCorrection(), featuresResponse.getSpellCorrection());
    }

    public SpellCorrectionResponse getSpellCorrection() {
        return this.spellCorrection;
    }

    public SpiritualResponse getSpiritual() {
        return this.spiritual;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSpiritual(), getSpellCorrection());
    }

    public void setSpellCorrection(SpellCorrectionResponse spellCorrectionResponse) {
        this.spellCorrection = spellCorrectionResponse;
    }

    public void setSpiritual(SpiritualResponse spiritualResponse) {
        this.spiritual = spiritualResponse;
    }
}

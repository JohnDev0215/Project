package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpellCorrection implements Comparable<SpellCorrection> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpellCorrection");
    private AutoCorrection autoCorrect;
    private DidYouMeanCorrection didYouMean;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SpellCorrection spellCorrection) {
        if (spellCorrection == null) {
            return -1;
        }
        if (spellCorrection == this) {
            return 0;
        }
        AutoCorrection autoCorrect = getAutoCorrect();
        AutoCorrection autoCorrect2 = spellCorrection.getAutoCorrect();
        if (autoCorrect != autoCorrect2) {
            if (autoCorrect == null) {
                return -1;
            }
            if (autoCorrect2 == null) {
                return 1;
            }
            int iCompareTo = autoCorrect.compareTo(autoCorrect2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        DidYouMeanCorrection didYouMean = getDidYouMean();
        DidYouMeanCorrection didYouMean2 = spellCorrection.getDidYouMean();
        if (didYouMean != didYouMean2) {
            if (didYouMean == null) {
                return -1;
            }
            if (didYouMean2 == null) {
                return 1;
            }
            int iCompareTo2 = didYouMean.compareTo(didYouMean2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SpellCorrection)) {
            return false;
        }
        SpellCorrection spellCorrection = (SpellCorrection) obj;
        return internalEqualityCheck(getAutoCorrect(), spellCorrection.getAutoCorrect()) && internalEqualityCheck(getDidYouMean(), spellCorrection.getDidYouMean());
    }

    public AutoCorrection getAutoCorrect() {
        return this.autoCorrect;
    }

    public DidYouMeanCorrection getDidYouMean() {
        return this.didYouMean;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAutoCorrect(), getDidYouMean());
    }

    public void setAutoCorrect(AutoCorrection autoCorrection) {
        this.autoCorrect = autoCorrection;
    }

    public void setDidYouMean(DidYouMeanCorrection didYouMeanCorrection) {
        this.didYouMean = didYouMeanCorrection;
    }
}

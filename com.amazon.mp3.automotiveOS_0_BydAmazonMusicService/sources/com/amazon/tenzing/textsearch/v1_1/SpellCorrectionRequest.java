package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpellCorrectionRequest implements Comparable<SpellCorrectionRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpellCorrectionRequest");
    private SpellCorrectionFeedback accepted;
    private Boolean allowCorrection;
    private SpellCorrectionFeedback rejected;

    public static class Builder {
        protected SpellCorrectionFeedback accepted;
        protected Boolean allowCorrection;
        protected SpellCorrectionFeedback rejected;

        public SpellCorrectionRequest build() {
            SpellCorrectionRequest spellCorrectionRequest = new SpellCorrectionRequest();
            populate(spellCorrectionRequest);
            return spellCorrectionRequest;
        }

        protected void populate(SpellCorrectionRequest spellCorrectionRequest) {
            spellCorrectionRequest.setAllowCorrection(this.allowCorrection);
            spellCorrectionRequest.setRejected(this.rejected);
            spellCorrectionRequest.setAccepted(this.accepted);
        }

        public Builder withAccepted(SpellCorrectionFeedback spellCorrectionFeedback) {
            this.accepted = spellCorrectionFeedback;
            return this;
        }

        public Builder withAllowCorrection(Boolean bool) {
            this.allowCorrection = bool;
            return this;
        }

        public Builder withRejected(SpellCorrectionFeedback spellCorrectionFeedback) {
            this.rejected = spellCorrectionFeedback;
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
    public int compareTo(@Deprecated SpellCorrectionRequest spellCorrectionRequest) {
        if (spellCorrectionRequest == null) {
            return -1;
        }
        if (spellCorrectionRequest == this) {
            return 0;
        }
        Boolean boolIsAllowCorrection = isAllowCorrection();
        Boolean boolIsAllowCorrection2 = spellCorrectionRequest.isAllowCorrection();
        if (boolIsAllowCorrection != boolIsAllowCorrection2) {
            if (boolIsAllowCorrection == null) {
                return -1;
            }
            if (boolIsAllowCorrection2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAllowCorrection.compareTo(boolIsAllowCorrection2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        SpellCorrectionFeedback rejected = getRejected();
        SpellCorrectionFeedback rejected2 = spellCorrectionRequest.getRejected();
        if (rejected != rejected2) {
            if (rejected == null) {
                return -1;
            }
            if (rejected2 == null) {
                return 1;
            }
            int iCompareTo2 = rejected.compareTo(rejected2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        SpellCorrectionFeedback accepted = getAccepted();
        SpellCorrectionFeedback accepted2 = spellCorrectionRequest.getAccepted();
        if (accepted != accepted2) {
            if (accepted == null) {
                return -1;
            }
            if (accepted2 == null) {
                return 1;
            }
            int iCompareTo3 = accepted.compareTo(accepted2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SpellCorrectionRequest)) {
            return false;
        }
        SpellCorrectionRequest spellCorrectionRequest = (SpellCorrectionRequest) obj;
        return internalEqualityCheck(isAllowCorrection(), spellCorrectionRequest.isAllowCorrection()) && internalEqualityCheck(getRejected(), spellCorrectionRequest.getRejected()) && internalEqualityCheck(getAccepted(), spellCorrectionRequest.getAccepted());
    }

    public SpellCorrectionFeedback getAccepted() {
        return this.accepted;
    }

    public SpellCorrectionFeedback getRejected() {
        return this.rejected;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isAllowCorrection(), getRejected(), getAccepted());
    }

    public Boolean isAllowCorrection() {
        return this.allowCorrection;
    }

    public void setAccepted(SpellCorrectionFeedback spellCorrectionFeedback) {
        this.accepted = spellCorrectionFeedback;
    }

    public void setAllowCorrection(Boolean bool) {
        this.allowCorrection = bool;
    }

    public void setRejected(SpellCorrectionFeedback spellCorrectionFeedback) {
        this.rejected = spellCorrectionFeedback;
    }
}

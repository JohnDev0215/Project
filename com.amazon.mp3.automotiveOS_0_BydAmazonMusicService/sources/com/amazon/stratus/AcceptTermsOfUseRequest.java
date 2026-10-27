package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.cirrus.shared.model.TermsOfUse;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AcceptTermsOfUseRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AcceptTermsOfUseRequest");
    private TermsOfUse termsOfUse;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof AcceptTermsOfUseRequest)) {
            return 1;
        }
        TermsOfUse termsOfUse = getTermsOfUse();
        TermsOfUse termsOfUse2 = ((AcceptTermsOfUseRequest) cirrusBaseRequestV2).getTermsOfUse();
        if (termsOfUse != termsOfUse2) {
            if (termsOfUse == null) {
                return -1;
            }
            if (termsOfUse2 == null) {
                return 1;
            }
            int iCompareTo = termsOfUse.compareTo(termsOfUse2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof AcceptTermsOfUseRequest) {
            return super.equals(obj) && internalEqualityCheck(getTermsOfUse(), ((AcceptTermsOfUseRequest) obj).getTermsOfUse());
        }
        return false;
    }

    public TermsOfUse getTermsOfUse() {
        return this.termsOfUse;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTermsOfUse());
    }

    public void setTermsOfUse(TermsOfUse termsOfUse) {
        this.termsOfUse = termsOfUse;
    }
}

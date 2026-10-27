package com.amazon.cirrus.shared.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUseAccepted extends TermsOfUse {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.shared.model.TermsOfUseAccepted");
    private Date acceptedDate;

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
        if (!(termsOfUse instanceof TermsOfUseAccepted)) {
            return 1;
        }
        Date acceptedDate = getAcceptedDate();
        Date acceptedDate2 = ((TermsOfUseAccepted) termsOfUse).getAcceptedDate();
        if (acceptedDate != acceptedDate2) {
            if (acceptedDate == null) {
                return -1;
            }
            if (acceptedDate2 == null) {
                return 1;
            }
            int iCompareTo = acceptedDate.compareTo(acceptedDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(termsOfUse);
    }

    @Override // com.amazon.cirrus.shared.model.TermsOfUse
    public boolean equals(Object obj) {
        if (obj instanceof TermsOfUseAccepted) {
            return super.equals(obj) && internalEqualityCheck(getAcceptedDate(), ((TermsOfUseAccepted) obj).getAcceptedDate());
        }
        return false;
    }

    public Date getAcceptedDate() {
        return this.acceptedDate;
    }

    @Override // com.amazon.cirrus.shared.model.TermsOfUse
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAcceptedDate());
    }

    public void setAcceptedDate(Date date) {
        this.acceptedDate = date;
    }
}

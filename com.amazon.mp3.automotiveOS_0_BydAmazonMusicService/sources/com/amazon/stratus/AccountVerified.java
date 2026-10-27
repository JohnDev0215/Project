package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class AccountVerified implements Comparable<AccountVerified> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AccountVerified");
    private String verificationReason;
    private Boolean verified;
    private Date verifiedDate;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AccountVerified accountVerified) {
        if (accountVerified == null) {
            return -1;
        }
        if (accountVerified == this) {
            return 0;
        }
        Date verifiedDate = getVerifiedDate();
        Date verifiedDate2 = accountVerified.getVerifiedDate();
        if (verifiedDate != verifiedDate2) {
            if (verifiedDate == null) {
                return -1;
            }
            if (verifiedDate2 == null) {
                return 1;
            }
            int iCompareTo = verifiedDate.compareTo(verifiedDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsVerified = isVerified();
        Boolean boolIsVerified2 = accountVerified.isVerified();
        if (boolIsVerified != boolIsVerified2) {
            if (boolIsVerified == null) {
                return -1;
            }
            if (boolIsVerified2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsVerified.compareTo(boolIsVerified2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String verificationReason = getVerificationReason();
        String verificationReason2 = accountVerified.getVerificationReason();
        if (verificationReason != verificationReason2) {
            if (verificationReason == null) {
                return -1;
            }
            if (verificationReason2 == null) {
                return 1;
            }
            int iCompareTo3 = verificationReason.compareTo(verificationReason2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountVerified)) {
            return false;
        }
        AccountVerified accountVerified = (AccountVerified) obj;
        return internalEqualityCheck(getVerifiedDate(), accountVerified.getVerifiedDate()) && internalEqualityCheck(isVerified(), accountVerified.isVerified()) && internalEqualityCheck(getVerificationReason(), accountVerified.getVerificationReason());
    }

    public String getVerificationReason() {
        return this.verificationReason;
    }

    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getVerifiedDate(), isVerified(), getVerificationReason());
    }

    public Boolean isVerified() {
        return this.verified;
    }

    public void setVerificationReason(String str) {
        this.verificationReason = str;
    }

    public void setVerified(Boolean bool) {
        this.verified = bool;
    }

    public void setVerifiedDate(Date date) {
        this.verifiedDate = date;
    }
}

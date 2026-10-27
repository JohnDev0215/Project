package com.amazon.stratus;

import com.amazon.cirrus.shared.model.TermsOfUseAccepted;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerAccount implements Comparable<CustomerAccount> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.CustomerAccount");
    private AccountInfo accountInfo;
    private AccountVerified accountVerified;
    private Map<String, String> customerBenefits;
    private List<TermsOfUseAccepted> termsOfUse;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerAccount customerAccount) {
        if (customerAccount == null) {
            return -1;
        }
        if (customerAccount == this) {
            return 0;
        }
        AccountInfo accountInfo = getAccountInfo();
        AccountInfo accountInfo2 = customerAccount.getAccountInfo();
        if (accountInfo != accountInfo2) {
            if (accountInfo == null) {
                return -1;
            }
            if (accountInfo2 == null) {
                return 1;
            }
            int iCompareTo = accountInfo.compareTo(accountInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<TermsOfUseAccepted> termsOfUse = getTermsOfUse();
        List<TermsOfUseAccepted> termsOfUse2 = customerAccount.getTermsOfUse();
        if (termsOfUse != termsOfUse2) {
            if (termsOfUse == null) {
                return -1;
            }
            if (termsOfUse2 == null) {
                return 1;
            }
            if (termsOfUse instanceof Comparable) {
                int iCompareTo2 = ((Comparable) termsOfUse).compareTo(termsOfUse2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!termsOfUse.equals(termsOfUse2)) {
                int iHashCode = termsOfUse.hashCode();
                int iHashCode2 = termsOfUse2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        AccountVerified accountVerified = getAccountVerified();
        AccountVerified accountVerified2 = customerAccount.getAccountVerified();
        if (accountVerified != accountVerified2) {
            if (accountVerified == null) {
                return -1;
            }
            if (accountVerified2 == null) {
                return 1;
            }
            int iCompareTo3 = accountVerified.compareTo(accountVerified2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, String> customerBenefits = getCustomerBenefits();
        Map<String, String> customerBenefits2 = customerAccount.getCustomerBenefits();
        if (customerBenefits != customerBenefits2) {
            if (customerBenefits == null) {
                return -1;
            }
            if (customerBenefits2 == null) {
                return 1;
            }
            if (customerBenefits instanceof Comparable) {
                int iCompareTo4 = ((Comparable) customerBenefits).compareTo(customerBenefits2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!customerBenefits.equals(customerBenefits2)) {
                int iHashCode3 = customerBenefits.hashCode();
                int iHashCode4 = customerBenefits2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerAccount)) {
            return false;
        }
        CustomerAccount customerAccount = (CustomerAccount) obj;
        return internalEqualityCheck(getAccountInfo(), customerAccount.getAccountInfo()) && internalEqualityCheck(getTermsOfUse(), customerAccount.getTermsOfUse()) && internalEqualityCheck(getAccountVerified(), customerAccount.getAccountVerified()) && internalEqualityCheck(getCustomerBenefits(), customerAccount.getCustomerBenefits());
    }

    public AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public AccountVerified getAccountVerified() {
        return this.accountVerified;
    }

    public Map<String, String> getCustomerBenefits() {
        return this.customerBenefits;
    }

    public List<TermsOfUseAccepted> getTermsOfUse() {
        return this.termsOfUse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAccountInfo(), getTermsOfUse(), getAccountVerified(), getCustomerBenefits());
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public void setAccountVerified(AccountVerified accountVerified) {
        this.accountVerified = accountVerified;
    }

    public void setCustomerBenefits(Map<String, String> map) {
        this.customerBenefits = map;
    }

    public void setTermsOfUse(List<TermsOfUseAccepted> list) {
        this.termsOfUse = list;
    }
}

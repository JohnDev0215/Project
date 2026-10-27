package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class IsAccountValidResponse implements Comparable<IsAccountValidResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.IsAccountValidResponse");
    private Boolean accountValid;
    private CustomerAccount customerAccount;
    private Map<String, List<String>> globalBenefits;
    private Boolean shouldInvokeWebview;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated IsAccountValidResponse isAccountValidResponse) {
        if (isAccountValidResponse == null) {
            return -1;
        }
        if (isAccountValidResponse == this) {
            return 0;
        }
        Map<String, List<String>> globalBenefits = getGlobalBenefits();
        Map<String, List<String>> globalBenefits2 = isAccountValidResponse.getGlobalBenefits();
        if (globalBenefits != globalBenefits2) {
            if (globalBenefits == null) {
                return -1;
            }
            if (globalBenefits2 == null) {
                return 1;
            }
            if (globalBenefits instanceof Comparable) {
                int iCompareTo = ((Comparable) globalBenefits).compareTo(globalBenefits2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!globalBenefits.equals(globalBenefits2)) {
                int iHashCode = globalBenefits.hashCode();
                int iHashCode2 = globalBenefits2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsAccountValid = isAccountValid();
        Boolean boolIsAccountValid2 = isAccountValidResponse.isAccountValid();
        if (boolIsAccountValid != boolIsAccountValid2) {
            if (boolIsAccountValid == null) {
                return -1;
            }
            if (boolIsAccountValid2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsAccountValid.compareTo(boolIsAccountValid2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        CustomerAccount customerAccount = getCustomerAccount();
        CustomerAccount customerAccount2 = isAccountValidResponse.getCustomerAccount();
        if (customerAccount != customerAccount2) {
            if (customerAccount == null) {
                return -1;
            }
            if (customerAccount2 == null) {
                return 1;
            }
            int iCompareTo3 = customerAccount.compareTo(customerAccount2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsShouldInvokeWebview = isShouldInvokeWebview();
        Boolean boolIsShouldInvokeWebview2 = isAccountValidResponse.isShouldInvokeWebview();
        if (boolIsShouldInvokeWebview != boolIsShouldInvokeWebview2) {
            if (boolIsShouldInvokeWebview == null) {
                return -1;
            }
            if (boolIsShouldInvokeWebview2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsShouldInvokeWebview.compareTo(boolIsShouldInvokeWebview2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IsAccountValidResponse)) {
            return false;
        }
        IsAccountValidResponse isAccountValidResponse = (IsAccountValidResponse) obj;
        return internalEqualityCheck(getGlobalBenefits(), isAccountValidResponse.getGlobalBenefits()) && internalEqualityCheck(isAccountValid(), isAccountValidResponse.isAccountValid()) && internalEqualityCheck(getCustomerAccount(), isAccountValidResponse.getCustomerAccount()) && internalEqualityCheck(isShouldInvokeWebview(), isAccountValidResponse.isShouldInvokeWebview());
    }

    public CustomerAccount getCustomerAccount() {
        return this.customerAccount;
    }

    public Map<String, List<String>> getGlobalBenefits() {
        return this.globalBenefits;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getGlobalBenefits(), isAccountValid(), getCustomerAccount(), isShouldInvokeWebview());
    }

    public Boolean isAccountValid() {
        return this.accountValid;
    }

    public Boolean isShouldInvokeWebview() {
        return this.shouldInvokeWebview;
    }

    public void setAccountValid(Boolean bool) {
        this.accountValid = bool;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public void setGlobalBenefits(Map<String, List<String>> map) {
        this.globalBenefits = map;
    }

    public void setShouldInvokeWebview(Boolean bool) {
        this.shouldInvokeWebview = bool;
    }
}

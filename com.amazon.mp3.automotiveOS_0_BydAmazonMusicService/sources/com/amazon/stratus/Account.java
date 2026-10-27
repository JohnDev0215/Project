package com.amazon.stratus;

import com.amazon.cirrus.shared.model.TermsOfUseAccepted;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class Account implements Comparable<Account> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Account");
    private Map<String, CustomerBenefit> accountBenefits;
    private AccountHistory accountHistory;
    private AccountInfo accountInfo;
    private AccountLocation accountLocation;
    private AccountVerified accountVerified;
    private Map<String, String> customerBenefits;
    private Map<String, String> debugInfo;
    private Map<String, String> preferences;
    private Map<String, Map<String, String>> preferencesByNamespace;
    private StorageLocation storageLocation;
    private List<TermsOfUseAccepted> termsOfUse;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Account account) {
        if (account == null) {
            return -1;
        }
        if (account == this) {
            return 0;
        }
        StorageLocation storageLocation = getStorageLocation();
        StorageLocation storageLocation2 = account.getStorageLocation();
        if (storageLocation != storageLocation2) {
            if (storageLocation == null) {
                return -1;
            }
            if (storageLocation2 == null) {
                return 1;
            }
            int iCompareTo = storageLocation.compareTo(storageLocation2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, CustomerBenefit> accountBenefits = getAccountBenefits();
        Map<String, CustomerBenefit> accountBenefits2 = account.getAccountBenefits();
        if (accountBenefits != accountBenefits2) {
            if (accountBenefits == null) {
                return -1;
            }
            if (accountBenefits2 == null) {
                return 1;
            }
            if (accountBenefits instanceof Comparable) {
                int iCompareTo2 = ((Comparable) accountBenefits).compareTo(accountBenefits2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!accountBenefits.equals(accountBenefits2)) {
                int iHashCode = accountBenefits.hashCode();
                int iHashCode2 = accountBenefits2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Map<String, Map<String, String>> preferencesByNamespace = getPreferencesByNamespace();
        Map<String, Map<String, String>> preferencesByNamespace2 = account.getPreferencesByNamespace();
        if (preferencesByNamespace != preferencesByNamespace2) {
            if (preferencesByNamespace == null) {
                return -1;
            }
            if (preferencesByNamespace2 == null) {
                return 1;
            }
            if (preferencesByNamespace instanceof Comparable) {
                int iCompareTo3 = ((Comparable) preferencesByNamespace).compareTo(preferencesByNamespace2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!preferencesByNamespace.equals(preferencesByNamespace2)) {
                int iHashCode3 = preferencesByNamespace.hashCode();
                int iHashCode4 = preferencesByNamespace2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        AccountVerified accountVerified = getAccountVerified();
        AccountVerified accountVerified2 = account.getAccountVerified();
        if (accountVerified != accountVerified2) {
            if (accountVerified == null) {
                return -1;
            }
            if (accountVerified2 == null) {
                return 1;
            }
            int iCompareTo4 = accountVerified.compareTo(accountVerified2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Map<String, String> customerBenefits = getCustomerBenefits();
        Map<String, String> customerBenefits2 = account.getCustomerBenefits();
        if (customerBenefits != customerBenefits2) {
            if (customerBenefits == null) {
                return -1;
            }
            if (customerBenefits2 == null) {
                return 1;
            }
            if (customerBenefits instanceof Comparable) {
                int iCompareTo5 = ((Comparable) customerBenefits).compareTo(customerBenefits2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!customerBenefits.equals(customerBenefits2)) {
                int iHashCode5 = customerBenefits.hashCode();
                int iHashCode6 = customerBenefits2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<TermsOfUseAccepted> termsOfUse = getTermsOfUse();
        List<TermsOfUseAccepted> termsOfUse2 = account.getTermsOfUse();
        if (termsOfUse != termsOfUse2) {
            if (termsOfUse == null) {
                return -1;
            }
            if (termsOfUse2 == null) {
                return 1;
            }
            if (termsOfUse instanceof Comparable) {
                int iCompareTo6 = ((Comparable) termsOfUse).compareTo(termsOfUse2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!termsOfUse.equals(termsOfUse2)) {
                int iHashCode7 = termsOfUse.hashCode();
                int iHashCode8 = termsOfUse2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        Map<String, String> debugInfo = getDebugInfo();
        Map<String, String> debugInfo2 = account.getDebugInfo();
        if (debugInfo != debugInfo2) {
            if (debugInfo == null) {
                return -1;
            }
            if (debugInfo2 == null) {
                return 1;
            }
            if (debugInfo instanceof Comparable) {
                int iCompareTo7 = ((Comparable) debugInfo).compareTo(debugInfo2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!debugInfo.equals(debugInfo2)) {
                int iHashCode9 = debugInfo.hashCode();
                int iHashCode10 = debugInfo2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        AccountHistory accountHistory = getAccountHistory();
        AccountHistory accountHistory2 = account.getAccountHistory();
        if (accountHistory != accountHistory2) {
            if (accountHistory == null) {
                return -1;
            }
            if (accountHistory2 == null) {
                return 1;
            }
            int iCompareTo8 = accountHistory.compareTo(accountHistory2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        AccountInfo accountInfo = getAccountInfo();
        AccountInfo accountInfo2 = account.getAccountInfo();
        if (accountInfo != accountInfo2) {
            if (accountInfo == null) {
                return -1;
            }
            if (accountInfo2 == null) {
                return 1;
            }
            int iCompareTo9 = accountInfo.compareTo(accountInfo2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        AccountLocation accountLocation = getAccountLocation();
        AccountLocation accountLocation2 = account.getAccountLocation();
        if (accountLocation != accountLocation2) {
            if (accountLocation == null) {
                return -1;
            }
            if (accountLocation2 == null) {
                return 1;
            }
            int iCompareTo10 = accountLocation.compareTo(accountLocation2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Map<String, String> preferences = getPreferences();
        Map<String, String> preferences2 = account.getPreferences();
        if (preferences != preferences2) {
            if (preferences == null) {
                return -1;
            }
            if (preferences2 == null) {
                return 1;
            }
            if (preferences instanceof Comparable) {
                int iCompareTo11 = ((Comparable) preferences).compareTo(preferences2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!preferences.equals(preferences2)) {
                int iHashCode11 = preferences.hashCode();
                int iHashCode12 = preferences2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account account = (Account) obj;
        return internalEqualityCheck(getStorageLocation(), account.getStorageLocation()) && internalEqualityCheck(getAccountBenefits(), account.getAccountBenefits()) && internalEqualityCheck(getPreferencesByNamespace(), account.getPreferencesByNamespace()) && internalEqualityCheck(getAccountVerified(), account.getAccountVerified()) && internalEqualityCheck(getCustomerBenefits(), account.getCustomerBenefits()) && internalEqualityCheck(getTermsOfUse(), account.getTermsOfUse()) && internalEqualityCheck(getDebugInfo(), account.getDebugInfo()) && internalEqualityCheck(getAccountHistory(), account.getAccountHistory()) && internalEqualityCheck(getAccountInfo(), account.getAccountInfo()) && internalEqualityCheck(getAccountLocation(), account.getAccountLocation()) && internalEqualityCheck(getPreferences(), account.getPreferences());
    }

    public Map<String, CustomerBenefit> getAccountBenefits() {
        return this.accountBenefits;
    }

    public AccountHistory getAccountHistory() {
        return this.accountHistory;
    }

    public AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public AccountLocation getAccountLocation() {
        return this.accountLocation;
    }

    public AccountVerified getAccountVerified() {
        return this.accountVerified;
    }

    public Map<String, String> getCustomerBenefits() {
        return this.customerBenefits;
    }

    public Map<String, String> getDebugInfo() {
        return this.debugInfo;
    }

    public Map<String, String> getPreferences() {
        return this.preferences;
    }

    public Map<String, Map<String, String>> getPreferencesByNamespace() {
        return this.preferencesByNamespace;
    }

    public StorageLocation getStorageLocation() {
        return this.storageLocation;
    }

    public List<TermsOfUseAccepted> getTermsOfUse() {
        return this.termsOfUse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getStorageLocation(), getAccountBenefits(), getPreferencesByNamespace(), getAccountVerified(), getCustomerBenefits(), getTermsOfUse(), getDebugInfo(), getAccountHistory(), getAccountInfo(), getAccountLocation(), getPreferences());
    }

    public void setAccountBenefits(Map<String, CustomerBenefit> map) {
        this.accountBenefits = map;
    }

    public void setAccountHistory(AccountHistory accountHistory) {
        this.accountHistory = accountHistory;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public void setAccountLocation(AccountLocation accountLocation) {
        this.accountLocation = accountLocation;
    }

    public void setAccountVerified(AccountVerified accountVerified) {
        this.accountVerified = accountVerified;
    }

    public void setCustomerBenefits(Map<String, String> map) {
        this.customerBenefits = map;
    }

    public void setDebugInfo(Map<String, String> map) {
        this.debugInfo = map;
    }

    public void setPreferences(Map<String, String> map) {
        this.preferences = map;
    }

    public void setPreferencesByNamespace(Map<String, Map<String, String>> map) {
        this.preferencesByNamespace = map;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public void setTermsOfUse(List<TermsOfUseAccepted> list) {
        this.termsOfUse = list;
    }
}

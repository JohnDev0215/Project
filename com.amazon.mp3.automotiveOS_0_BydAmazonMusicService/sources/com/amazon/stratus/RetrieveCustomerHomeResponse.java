package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCustomerHomeResponse implements Comparable<RetrieveCustomerHomeResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveCustomerHomeResponse");
    private Boolean accountExists;
    private String accountStatus;
    private String customerId;
    private String marketplaceId;
    private String musicTerritory;
    private String storageRealm;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveCustomerHomeResponse retrieveCustomerHomeResponse) {
        if (retrieveCustomerHomeResponse == null) {
            return -1;
        }
        if (retrieveCustomerHomeResponse == this) {
            return 0;
        }
        Boolean boolIsAccountExists = isAccountExists();
        Boolean boolIsAccountExists2 = retrieveCustomerHomeResponse.isAccountExists();
        if (boolIsAccountExists != boolIsAccountExists2) {
            if (boolIsAccountExists == null) {
                return -1;
            }
            if (boolIsAccountExists2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAccountExists.compareTo(boolIsAccountExists2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = retrieveCustomerHomeResponse.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo2 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = retrieveCustomerHomeResponse.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo3 = customerId.compareTo(customerId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String accountStatus = getAccountStatus();
        String accountStatus2 = retrieveCustomerHomeResponse.getAccountStatus();
        if (accountStatus != accountStatus2) {
            if (accountStatus == null) {
                return -1;
            }
            if (accountStatus2 == null) {
                return 1;
            }
            int iCompareTo4 = accountStatus.compareTo(accountStatus2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String storageRealm = getStorageRealm();
        String storageRealm2 = retrieveCustomerHomeResponse.getStorageRealm();
        if (storageRealm != storageRealm2) {
            if (storageRealm == null) {
                return -1;
            }
            if (storageRealm2 == null) {
                return 1;
            }
            int iCompareTo5 = storageRealm.compareTo(storageRealm2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = retrieveCustomerHomeResponse.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo6 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RetrieveCustomerHomeResponse)) {
            return false;
        }
        RetrieveCustomerHomeResponse retrieveCustomerHomeResponse = (RetrieveCustomerHomeResponse) obj;
        return internalEqualityCheck(isAccountExists(), retrieveCustomerHomeResponse.isAccountExists()) && internalEqualityCheck(getMarketplaceId(), retrieveCustomerHomeResponse.getMarketplaceId()) && internalEqualityCheck(getCustomerId(), retrieveCustomerHomeResponse.getCustomerId()) && internalEqualityCheck(getAccountStatus(), retrieveCustomerHomeResponse.getAccountStatus()) && internalEqualityCheck(getStorageRealm(), retrieveCustomerHomeResponse.getStorageRealm()) && internalEqualityCheck(getMusicTerritory(), retrieveCustomerHomeResponse.getMusicTerritory());
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getStorageRealm() {
        return this.storageRealm;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isAccountExists(), getMarketplaceId(), getCustomerId(), getAccountStatus(), getStorageRealm(), getMusicTerritory());
    }

    public Boolean isAccountExists() {
        return this.accountExists;
    }

    public void setAccountExists(Boolean bool) {
        this.accountExists = bool;
    }

    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setStorageRealm(String str) {
        this.storageRealm = str;
    }
}

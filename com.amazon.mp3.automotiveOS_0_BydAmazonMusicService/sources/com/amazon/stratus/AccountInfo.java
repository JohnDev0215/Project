package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class AccountInfo implements Comparable<AccountInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AccountInfo");
    private String accountStatus;
    private Date creationDate;
    private String customerId;
    private String endpointDomain;
    private String marketplaceId;
    private String musicTerritory;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AccountInfo accountInfo) {
        if (accountInfo == null) {
            return -1;
        }
        if (accountInfo == this) {
            return 0;
        }
        Date creationDate = getCreationDate();
        Date creationDate2 = accountInfo.getCreationDate();
        if (creationDate != creationDate2) {
            if (creationDate == null) {
                return -1;
            }
            if (creationDate2 == null) {
                return 1;
            }
            int iCompareTo = creationDate.compareTo(creationDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = accountInfo.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo2 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = accountInfo.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo3 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String accountStatus = getAccountStatus();
        String accountStatus2 = accountInfo.getAccountStatus();
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
        String customerId = getCustomerId();
        String customerId2 = accountInfo.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo5 = customerId.compareTo(customerId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String endpointDomain = getEndpointDomain();
        String endpointDomain2 = accountInfo.getEndpointDomain();
        if (endpointDomain != endpointDomain2) {
            if (endpointDomain == null) {
                return -1;
            }
            if (endpointDomain2 == null) {
                return 1;
            }
            int iCompareTo6 = endpointDomain.compareTo(endpointDomain2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountInfo)) {
            return false;
        }
        AccountInfo accountInfo = (AccountInfo) obj;
        return internalEqualityCheck(getCreationDate(), accountInfo.getCreationDate()) && internalEqualityCheck(getMusicTerritory(), accountInfo.getMusicTerritory()) && internalEqualityCheck(getMarketplaceId(), accountInfo.getMarketplaceId()) && internalEqualityCheck(getAccountStatus(), accountInfo.getAccountStatus()) && internalEqualityCheck(getCustomerId(), accountInfo.getCustomerId()) && internalEqualityCheck(getEndpointDomain(), accountInfo.getEndpointDomain());
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getEndpointDomain() {
        return this.endpointDomain;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCreationDate(), getMusicTerritory(), getMarketplaceId(), getAccountStatus(), getCustomerId(), getEndpointDomain());
    }

    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setEndpointDomain(String str) {
        this.endpointDomain = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }
}

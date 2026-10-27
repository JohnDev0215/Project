package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AccountLocation implements Comparable<AccountLocation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AccountLocation");
    private String currentCountryOfResidence;
    private String currentMarketplaceId;
    private String migrationCandidate;
    private String targetCountryOfResidence;
    private String targetMarketplaceId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AccountLocation accountLocation) {
        if (accountLocation == null) {
            return -1;
        }
        if (accountLocation == this) {
            return 0;
        }
        String migrationCandidate = getMigrationCandidate();
        String migrationCandidate2 = accountLocation.getMigrationCandidate();
        if (migrationCandidate != migrationCandidate2) {
            if (migrationCandidate == null) {
                return -1;
            }
            if (migrationCandidate2 == null) {
                return 1;
            }
            int iCompareTo = migrationCandidate.compareTo(migrationCandidate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String currentCountryOfResidence = getCurrentCountryOfResidence();
        String currentCountryOfResidence2 = accountLocation.getCurrentCountryOfResidence();
        if (currentCountryOfResidence != currentCountryOfResidence2) {
            if (currentCountryOfResidence == null) {
                return -1;
            }
            if (currentCountryOfResidence2 == null) {
                return 1;
            }
            int iCompareTo2 = currentCountryOfResidence.compareTo(currentCountryOfResidence2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String currentMarketplaceId = getCurrentMarketplaceId();
        String currentMarketplaceId2 = accountLocation.getCurrentMarketplaceId();
        if (currentMarketplaceId != currentMarketplaceId2) {
            if (currentMarketplaceId == null) {
                return -1;
            }
            if (currentMarketplaceId2 == null) {
                return 1;
            }
            int iCompareTo3 = currentMarketplaceId.compareTo(currentMarketplaceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String targetMarketplaceId = getTargetMarketplaceId();
        String targetMarketplaceId2 = accountLocation.getTargetMarketplaceId();
        if (targetMarketplaceId != targetMarketplaceId2) {
            if (targetMarketplaceId == null) {
                return -1;
            }
            if (targetMarketplaceId2 == null) {
                return 1;
            }
            int iCompareTo4 = targetMarketplaceId.compareTo(targetMarketplaceId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String targetCountryOfResidence = getTargetCountryOfResidence();
        String targetCountryOfResidence2 = accountLocation.getTargetCountryOfResidence();
        if (targetCountryOfResidence != targetCountryOfResidence2) {
            if (targetCountryOfResidence == null) {
                return -1;
            }
            if (targetCountryOfResidence2 == null) {
                return 1;
            }
            int iCompareTo5 = targetCountryOfResidence.compareTo(targetCountryOfResidence2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountLocation)) {
            return false;
        }
        AccountLocation accountLocation = (AccountLocation) obj;
        return internalEqualityCheck(getMigrationCandidate(), accountLocation.getMigrationCandidate()) && internalEqualityCheck(getCurrentCountryOfResidence(), accountLocation.getCurrentCountryOfResidence()) && internalEqualityCheck(getCurrentMarketplaceId(), accountLocation.getCurrentMarketplaceId()) && internalEqualityCheck(getTargetMarketplaceId(), accountLocation.getTargetMarketplaceId()) && internalEqualityCheck(getTargetCountryOfResidence(), accountLocation.getTargetCountryOfResidence());
    }

    public String getCurrentCountryOfResidence() {
        return this.currentCountryOfResidence;
    }

    public String getCurrentMarketplaceId() {
        return this.currentMarketplaceId;
    }

    public String getMigrationCandidate() {
        return this.migrationCandidate;
    }

    public String getTargetCountryOfResidence() {
        return this.targetCountryOfResidence;
    }

    public String getTargetMarketplaceId() {
        return this.targetMarketplaceId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMigrationCandidate(), getCurrentCountryOfResidence(), getCurrentMarketplaceId(), getTargetMarketplaceId(), getTargetCountryOfResidence());
    }

    public void setCurrentCountryOfResidence(String str) {
        this.currentCountryOfResidence = str;
    }

    public void setCurrentMarketplaceId(String str) {
        this.currentMarketplaceId = str;
    }

    public void setMigrationCandidate(String str) {
        this.migrationCandidate = str;
    }

    public void setTargetCountryOfResidence(String str) {
        this.targetCountryOfResidence = str;
    }

    public void setTargetMarketplaceId(String str) {
        this.targetMarketplaceId = str;
    }
}

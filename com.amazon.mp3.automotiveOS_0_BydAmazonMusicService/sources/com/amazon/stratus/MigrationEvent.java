package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class MigrationEvent implements Comparable<MigrationEvent> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.MigrationEvent");
    private Date migrationDate;
    private String priorMarketplaceId;
    private String targetMarketplaceId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MigrationEvent migrationEvent) {
        if (migrationEvent == null) {
            return -1;
        }
        if (migrationEvent == this) {
            return 0;
        }
        String priorMarketplaceId = getPriorMarketplaceId();
        String priorMarketplaceId2 = migrationEvent.getPriorMarketplaceId();
        if (priorMarketplaceId != priorMarketplaceId2) {
            if (priorMarketplaceId == null) {
                return -1;
            }
            if (priorMarketplaceId2 == null) {
                return 1;
            }
            int iCompareTo = priorMarketplaceId.compareTo(priorMarketplaceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String targetMarketplaceId = getTargetMarketplaceId();
        String targetMarketplaceId2 = migrationEvent.getTargetMarketplaceId();
        if (targetMarketplaceId != targetMarketplaceId2) {
            if (targetMarketplaceId == null) {
                return -1;
            }
            if (targetMarketplaceId2 == null) {
                return 1;
            }
            int iCompareTo2 = targetMarketplaceId.compareTo(targetMarketplaceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date migrationDate = getMigrationDate();
        Date migrationDate2 = migrationEvent.getMigrationDate();
        if (migrationDate != migrationDate2) {
            if (migrationDate == null) {
                return -1;
            }
            if (migrationDate2 == null) {
                return 1;
            }
            int iCompareTo3 = migrationDate.compareTo(migrationDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MigrationEvent)) {
            return false;
        }
        MigrationEvent migrationEvent = (MigrationEvent) obj;
        return internalEqualityCheck(getPriorMarketplaceId(), migrationEvent.getPriorMarketplaceId()) && internalEqualityCheck(getTargetMarketplaceId(), migrationEvent.getTargetMarketplaceId()) && internalEqualityCheck(getMigrationDate(), migrationEvent.getMigrationDate());
    }

    public Date getMigrationDate() {
        return this.migrationDate;
    }

    public String getPriorMarketplaceId() {
        return this.priorMarketplaceId;
    }

    public String getTargetMarketplaceId() {
        return this.targetMarketplaceId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPriorMarketplaceId(), getTargetMarketplaceId(), getMigrationDate());
    }

    public void setMigrationDate(Date date) {
        this.migrationDate = date;
    }

    public void setPriorMarketplaceId(String str) {
        this.priorMarketplaceId = str;
    }

    public void setTargetMarketplaceId(String str) {
        this.targetMarketplaceId = str;
    }
}

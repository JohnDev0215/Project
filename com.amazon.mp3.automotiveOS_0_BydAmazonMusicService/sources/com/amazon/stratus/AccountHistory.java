package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AccountHistory implements Comparable<AccountHistory> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AccountHistory");
    private List<MigrationEvent> migrationHistory;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AccountHistory accountHistory) {
        List<MigrationEvent> migrationHistory;
        List<MigrationEvent> migrationHistory2;
        if (accountHistory == null) {
            return -1;
        }
        if (accountHistory != this && (migrationHistory = getMigrationHistory()) != (migrationHistory2 = accountHistory.getMigrationHistory())) {
            if (migrationHistory == null) {
                return -1;
            }
            if (migrationHistory2 == null) {
                return 1;
            }
            if (migrationHistory instanceof Comparable) {
                int iCompareTo = ((Comparable) migrationHistory).compareTo(migrationHistory2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!migrationHistory.equals(migrationHistory2)) {
                int iHashCode = migrationHistory.hashCode();
                int iHashCode2 = migrationHistory2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AccountHistory) {
            return internalEqualityCheck(getMigrationHistory(), ((AccountHistory) obj).getMigrationHistory());
        }
        return false;
    }

    public List<MigrationEvent> getMigrationHistory() {
        return this.migrationHistory;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMigrationHistory());
    }

    public void setMigrationHistory(List<MigrationEvent> list) {
        this.migrationHistory = list;
    }
}

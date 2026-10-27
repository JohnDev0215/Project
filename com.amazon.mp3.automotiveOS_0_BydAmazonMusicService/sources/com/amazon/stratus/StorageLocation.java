package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class StorageLocation implements Comparable<StorageLocation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.StorageLocation");
    private String storageRealm;
    private String storageService;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StorageLocation storageLocation) {
        if (storageLocation == null) {
            return -1;
        }
        if (storageLocation == this) {
            return 0;
        }
        String storageService = getStorageService();
        String storageService2 = storageLocation.getStorageService();
        if (storageService != storageService2) {
            if (storageService == null) {
                return -1;
            }
            if (storageService2 == null) {
                return 1;
            }
            int iCompareTo = storageService.compareTo(storageService2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String storageRealm = getStorageRealm();
        String storageRealm2 = storageLocation.getStorageRealm();
        if (storageRealm != storageRealm2) {
            if (storageRealm == null) {
                return -1;
            }
            if (storageRealm2 == null) {
                return 1;
            }
            int iCompareTo2 = storageRealm.compareTo(storageRealm2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StorageLocation)) {
            return false;
        }
        StorageLocation storageLocation = (StorageLocation) obj;
        return internalEqualityCheck(getStorageService(), storageLocation.getStorageService()) && internalEqualityCheck(getStorageRealm(), storageLocation.getStorageRealm());
    }

    public String getStorageRealm() {
        return this.storageRealm;
    }

    public String getStorageService() {
        return this.storageService;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getStorageService(), getStorageRealm());
    }

    public void setStorageRealm(String str) {
        this.storageRealm = str;
    }

    public void setStorageService(String str) {
        this.storageService = str;
    }
}

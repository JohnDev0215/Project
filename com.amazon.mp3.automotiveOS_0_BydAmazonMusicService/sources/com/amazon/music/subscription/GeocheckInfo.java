package com.amazon.music.subscription;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class GeocheckInfo implements Comparable<GeocheckInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.GeocheckInfo");
    private List<String> failureReasons;
    private Boolean success;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GeocheckInfo geocheckInfo) {
        if (geocheckInfo == null) {
            return -1;
        }
        if (geocheckInfo == this) {
            return 0;
        }
        Boolean boolIsSuccess = isSuccess();
        Boolean boolIsSuccess2 = geocheckInfo.isSuccess();
        if (boolIsSuccess != boolIsSuccess2) {
            if (boolIsSuccess == null) {
                return -1;
            }
            if (boolIsSuccess2 == null) {
                return 1;
            }
            int iCompareTo = boolIsSuccess.compareTo(boolIsSuccess2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> failureReasons = getFailureReasons();
        List<String> failureReasons2 = geocheckInfo.getFailureReasons();
        if (failureReasons != failureReasons2) {
            if (failureReasons == null) {
                return -1;
            }
            if (failureReasons2 == null) {
                return 1;
            }
            if (failureReasons instanceof Comparable) {
                int iCompareTo2 = ((Comparable) failureReasons).compareTo(failureReasons2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!failureReasons.equals(failureReasons2)) {
                int iHashCode = failureReasons.hashCode();
                int iHashCode2 = failureReasons2.hashCode();
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
        if (!(obj instanceof GeocheckInfo)) {
            return false;
        }
        GeocheckInfo geocheckInfo = (GeocheckInfo) obj;
        return internalEqualityCheck(isSuccess(), geocheckInfo.isSuccess()) && internalEqualityCheck(getFailureReasons(), geocheckInfo.getFailureReasons());
    }

    public List<String> getFailureReasons() {
        return this.failureReasons;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isSuccess(), getFailureReasons());
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public void setFailureReasons(List<String> list) {
        this.failureReasons = list;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }
}

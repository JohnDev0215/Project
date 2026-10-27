package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceEligibility implements Comparable<DeviceEligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.DeviceEligibility");
    private Boolean eligible;
    private String id;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceEligibility deviceEligibility) {
        if (deviceEligibility == null) {
            return -1;
        }
        if (deviceEligibility == this) {
            return 0;
        }
        String id = getId();
        String id2 = deviceEligibility.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsEligible = isEligible();
        Boolean boolIsEligible2 = deviceEligibility.isEligible();
        if (boolIsEligible != boolIsEligible2) {
            if (boolIsEligible == null) {
                return -1;
            }
            if (boolIsEligible2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsEligible.compareTo(boolIsEligible2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String type = getType();
        String type2 = deviceEligibility.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo3 = type.compareTo(type2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceEligibility)) {
            return false;
        }
        DeviceEligibility deviceEligibility = (DeviceEligibility) obj;
        return internalEqualityCheck(getId(), deviceEligibility.getId()) && internalEqualityCheck(isEligible(), deviceEligibility.isEligible()) && internalEqualityCheck(getType(), deviceEligibility.getType());
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), isEligible(), getType());
    }

    public Boolean isEligible() {
        return this.eligible;
    }

    public void setEligible(Boolean bool) {
        this.eligible = bool;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}

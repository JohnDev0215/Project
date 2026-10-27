package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Eligibility implements Comparable<Eligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.Eligibility");
    private List<DeviceEligibility> alternateDevices;
    private Boolean eligible;
    private List<String> reasons;
    private Boolean requiresAlternateDevice;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Eligibility eligibility) {
        if (eligibility == null) {
            return -1;
        }
        if (eligibility == this) {
            return 0;
        }
        Boolean boolIsEligible = isEligible();
        Boolean boolIsEligible2 = eligibility.isEligible();
        if (boolIsEligible != boolIsEligible2) {
            if (boolIsEligible == null) {
                return -1;
            }
            if (boolIsEligible2 == null) {
                return 1;
            }
            int iCompareTo = boolIsEligible.compareTo(boolIsEligible2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> reasons = getReasons();
        List<String> reasons2 = eligibility.getReasons();
        if (reasons != reasons2) {
            if (reasons == null) {
                return -1;
            }
            if (reasons2 == null) {
                return 1;
            }
            if (reasons instanceof Comparable) {
                int iCompareTo2 = ((Comparable) reasons).compareTo(reasons2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!reasons.equals(reasons2)) {
                int iHashCode = reasons.hashCode();
                int iHashCode2 = reasons2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<DeviceEligibility> alternateDevices = getAlternateDevices();
        List<DeviceEligibility> alternateDevices2 = eligibility.getAlternateDevices();
        if (alternateDevices != alternateDevices2) {
            if (alternateDevices == null) {
                return -1;
            }
            if (alternateDevices2 == null) {
                return 1;
            }
            if (alternateDevices instanceof Comparable) {
                int iCompareTo3 = ((Comparable) alternateDevices).compareTo(alternateDevices2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!alternateDevices.equals(alternateDevices2)) {
                int iHashCode3 = alternateDevices.hashCode();
                int iHashCode4 = alternateDevices2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Boolean boolIsRequiresAlternateDevice = isRequiresAlternateDevice();
        Boolean boolIsRequiresAlternateDevice2 = eligibility.isRequiresAlternateDevice();
        if (boolIsRequiresAlternateDevice != boolIsRequiresAlternateDevice2) {
            if (boolIsRequiresAlternateDevice == null) {
                return -1;
            }
            if (boolIsRequiresAlternateDevice2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsRequiresAlternateDevice.compareTo(boolIsRequiresAlternateDevice2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Eligibility)) {
            return false;
        }
        Eligibility eligibility = (Eligibility) obj;
        return internalEqualityCheck(isEligible(), eligibility.isEligible()) && internalEqualityCheck(getReasons(), eligibility.getReasons()) && internalEqualityCheck(getAlternateDevices(), eligibility.getAlternateDevices()) && internalEqualityCheck(isRequiresAlternateDevice(), eligibility.isRequiresAlternateDevice());
    }

    public List<DeviceEligibility> getAlternateDevices() {
        return this.alternateDevices;
    }

    public List<String> getReasons() {
        return this.reasons;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isEligible(), getReasons(), getAlternateDevices(), isRequiresAlternateDevice());
    }

    public Boolean isEligible() {
        return this.eligible;
    }

    public Boolean isRequiresAlternateDevice() {
        return this.requiresAlternateDevice;
    }

    public void setAlternateDevices(List<DeviceEligibility> list) {
        this.alternateDevices = list;
    }

    public void setEligible(Boolean bool) {
        this.eligible = bool;
    }

    public void setReasons(List<String> list) {
        this.reasons = list;
    }

    public void setRequiresAlternateDevice(Boolean bool) {
        this.requiresAlternateDevice = bool;
    }
}

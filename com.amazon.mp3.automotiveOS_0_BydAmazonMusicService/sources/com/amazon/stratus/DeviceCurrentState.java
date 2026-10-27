package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceCurrentState implements Comparable<DeviceCurrentState> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.DeviceCurrentState");
    private String deviceAuthState;
    private List<CapabilityInstance> deviceCapabilities;
    private String deviceName;
    private Date expirationDate;
    private Date lastAccessDate;
    private Date lastAuthorizedDate;
    private Date lastDeauthorizedDate;
    private Date lastTransferredDate;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceCurrentState deviceCurrentState) {
        if (deviceCurrentState == null) {
            return -1;
        }
        if (deviceCurrentState == this) {
            return 0;
        }
        Date lastTransferredDate = getLastTransferredDate();
        Date lastTransferredDate2 = deviceCurrentState.getLastTransferredDate();
        if (lastTransferredDate != lastTransferredDate2) {
            if (lastTransferredDate == null) {
                return -1;
            }
            if (lastTransferredDate2 == null) {
                return 1;
            }
            int iCompareTo = lastTransferredDate.compareTo(lastTransferredDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date lastDeauthorizedDate = getLastDeauthorizedDate();
        Date lastDeauthorizedDate2 = deviceCurrentState.getLastDeauthorizedDate();
        if (lastDeauthorizedDate != lastDeauthorizedDate2) {
            if (lastDeauthorizedDate == null) {
                return -1;
            }
            if (lastDeauthorizedDate2 == null) {
                return 1;
            }
            int iCompareTo2 = lastDeauthorizedDate.compareTo(lastDeauthorizedDate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<CapabilityInstance> deviceCapabilities = getDeviceCapabilities();
        List<CapabilityInstance> deviceCapabilities2 = deviceCurrentState.getDeviceCapabilities();
        if (deviceCapabilities != deviceCapabilities2) {
            if (deviceCapabilities == null) {
                return -1;
            }
            if (deviceCapabilities2 == null) {
                return 1;
            }
            if (deviceCapabilities instanceof Comparable) {
                int iCompareTo3 = ((Comparable) deviceCapabilities).compareTo(deviceCapabilities2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!deviceCapabilities.equals(deviceCapabilities2)) {
                int iHashCode = deviceCapabilities.hashCode();
                int iHashCode2 = deviceCapabilities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String deviceAuthState = getDeviceAuthState();
        String deviceAuthState2 = deviceCurrentState.getDeviceAuthState();
        if (deviceAuthState != deviceAuthState2) {
            if (deviceAuthState == null) {
                return -1;
            }
            if (deviceAuthState2 == null) {
                return 1;
            }
            int iCompareTo4 = deviceAuthState.compareTo(deviceAuthState2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Date lastAuthorizedDate = getLastAuthorizedDate();
        Date lastAuthorizedDate2 = deviceCurrentState.getLastAuthorizedDate();
        if (lastAuthorizedDate != lastAuthorizedDate2) {
            if (lastAuthorizedDate == null) {
                return -1;
            }
            if (lastAuthorizedDate2 == null) {
                return 1;
            }
            int iCompareTo5 = lastAuthorizedDate.compareTo(lastAuthorizedDate2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Date expirationDate = getExpirationDate();
        Date expirationDate2 = deviceCurrentState.getExpirationDate();
        if (expirationDate != expirationDate2) {
            if (expirationDate == null) {
                return -1;
            }
            if (expirationDate2 == null) {
                return 1;
            }
            int iCompareTo6 = expirationDate.compareTo(expirationDate2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String deviceName = getDeviceName();
        String deviceName2 = deviceCurrentState.getDeviceName();
        if (deviceName != deviceName2) {
            if (deviceName == null) {
                return -1;
            }
            if (deviceName2 == null) {
                return 1;
            }
            int iCompareTo7 = deviceName.compareTo(deviceName2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Date lastAccessDate = getLastAccessDate();
        Date lastAccessDate2 = deviceCurrentState.getLastAccessDate();
        if (lastAccessDate != lastAccessDate2) {
            if (lastAccessDate == null) {
                return -1;
            }
            if (lastAccessDate2 == null) {
                return 1;
            }
            int iCompareTo8 = lastAccessDate.compareTo(lastAccessDate2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceCurrentState)) {
            return false;
        }
        DeviceCurrentState deviceCurrentState = (DeviceCurrentState) obj;
        return internalEqualityCheck(getLastTransferredDate(), deviceCurrentState.getLastTransferredDate()) && internalEqualityCheck(getLastDeauthorizedDate(), deviceCurrentState.getLastDeauthorizedDate()) && internalEqualityCheck(getDeviceCapabilities(), deviceCurrentState.getDeviceCapabilities()) && internalEqualityCheck(getDeviceAuthState(), deviceCurrentState.getDeviceAuthState()) && internalEqualityCheck(getLastAuthorizedDate(), deviceCurrentState.getLastAuthorizedDate()) && internalEqualityCheck(getExpirationDate(), deviceCurrentState.getExpirationDate()) && internalEqualityCheck(getDeviceName(), deviceCurrentState.getDeviceName()) && internalEqualityCheck(getLastAccessDate(), deviceCurrentState.getLastAccessDate());
    }

    public String getDeviceAuthState() {
        return this.deviceAuthState;
    }

    public List<CapabilityInstance> getDeviceCapabilities() {
        return this.deviceCapabilities;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public Date getLastAccessDate() {
        return this.lastAccessDate;
    }

    public Date getLastAuthorizedDate() {
        return this.lastAuthorizedDate;
    }

    public Date getLastDeauthorizedDate() {
        return this.lastDeauthorizedDate;
    }

    public Date getLastTransferredDate() {
        return this.lastTransferredDate;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLastTransferredDate(), getLastDeauthorizedDate(), getDeviceCapabilities(), getDeviceAuthState(), getLastAuthorizedDate(), getExpirationDate(), getDeviceName(), getLastAccessDate());
    }

    public void setDeviceAuthState(String str) {
        this.deviceAuthState = str;
    }

    public void setDeviceCapabilities(List<CapabilityInstance> list) {
        this.deviceCapabilities = list;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public void setLastAccessDate(Date date) {
        this.lastAccessDate = date;
    }

    public void setLastAuthorizedDate(Date date) {
        this.lastAuthorizedDate = date;
    }

    public void setLastDeauthorizedDate(Date date) {
        this.lastDeauthorizedDate = date;
    }

    public void setLastTransferredDate(Date date) {
        this.lastTransferredDate = date;
    }
}

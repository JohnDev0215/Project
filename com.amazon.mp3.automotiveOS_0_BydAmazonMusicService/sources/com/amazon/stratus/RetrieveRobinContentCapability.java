package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveRobinContentCapability extends DeviceCapabilityDetailsBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveRobinContentCapability");
    private Device deviceLosingCapabilityNext;
    private List<Device> devicesGrantedCapability;
    private Long offlineContentAvailabilityMillis;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceCapabilityDetailsBase deviceCapabilityDetailsBase) {
        if (deviceCapabilityDetailsBase == null) {
            return -1;
        }
        if (deviceCapabilityDetailsBase == this) {
            return 0;
        }
        if (!(deviceCapabilityDetailsBase instanceof RetrieveRobinContentCapability)) {
            return 1;
        }
        RetrieveRobinContentCapability retrieveRobinContentCapability = (RetrieveRobinContentCapability) deviceCapabilityDetailsBase;
        Long offlineContentAvailabilityMillis = getOfflineContentAvailabilityMillis();
        Long offlineContentAvailabilityMillis2 = retrieveRobinContentCapability.getOfflineContentAvailabilityMillis();
        if (offlineContentAvailabilityMillis != offlineContentAvailabilityMillis2) {
            if (offlineContentAvailabilityMillis == null) {
                return -1;
            }
            if (offlineContentAvailabilityMillis2 == null) {
                return 1;
            }
            int iCompareTo = offlineContentAvailabilityMillis.compareTo(offlineContentAvailabilityMillis2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Device deviceLosingCapabilityNext = getDeviceLosingCapabilityNext();
        Device deviceLosingCapabilityNext2 = retrieveRobinContentCapability.getDeviceLosingCapabilityNext();
        if (deviceLosingCapabilityNext != deviceLosingCapabilityNext2) {
            if (deviceLosingCapabilityNext == null) {
                return -1;
            }
            if (deviceLosingCapabilityNext2 == null) {
                return 1;
            }
            int iCompareTo2 = deviceLosingCapabilityNext.compareTo(deviceLosingCapabilityNext2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Device> devicesGrantedCapability = getDevicesGrantedCapability();
        List<Device> devicesGrantedCapability2 = retrieveRobinContentCapability.getDevicesGrantedCapability();
        if (devicesGrantedCapability != devicesGrantedCapability2) {
            if (devicesGrantedCapability == null) {
                return -1;
            }
            if (devicesGrantedCapability2 == null) {
                return 1;
            }
            if (devicesGrantedCapability instanceof Comparable) {
                int iCompareTo3 = ((Comparable) devicesGrantedCapability).compareTo(devicesGrantedCapability2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!devicesGrantedCapability.equals(devicesGrantedCapability2)) {
                int iHashCode = devicesGrantedCapability.hashCode();
                int iHashCode2 = devicesGrantedCapability2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(deviceCapabilityDetailsBase);
    }

    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase
    public boolean equals(Object obj) {
        if (!(obj instanceof RetrieveRobinContentCapability)) {
            return false;
        }
        RetrieveRobinContentCapability retrieveRobinContentCapability = (RetrieveRobinContentCapability) obj;
        return super.equals(obj) && internalEqualityCheck(getOfflineContentAvailabilityMillis(), retrieveRobinContentCapability.getOfflineContentAvailabilityMillis()) && internalEqualityCheck(getDeviceLosingCapabilityNext(), retrieveRobinContentCapability.getDeviceLosingCapabilityNext()) && internalEqualityCheck(getDevicesGrantedCapability(), retrieveRobinContentCapability.getDevicesGrantedCapability());
    }

    public Device getDeviceLosingCapabilityNext() {
        return this.deviceLosingCapabilityNext;
    }

    public List<Device> getDevicesGrantedCapability() {
        return this.devicesGrantedCapability;
    }

    public Long getOfflineContentAvailabilityMillis() {
        return this.offlineContentAvailabilityMillis;
    }

    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOfflineContentAvailabilityMillis(), getDeviceLosingCapabilityNext(), getDevicesGrantedCapability());
    }

    public void setDeviceLosingCapabilityNext(Device device) {
        this.deviceLosingCapabilityNext = device;
    }

    public void setDevicesGrantedCapability(List<Device> list) {
        this.devicesGrantedCapability = list;
    }

    public void setOfflineContentAvailabilityMillis(Long l) {
        this.offlineContentAvailabilityMillis = l;
    }
}

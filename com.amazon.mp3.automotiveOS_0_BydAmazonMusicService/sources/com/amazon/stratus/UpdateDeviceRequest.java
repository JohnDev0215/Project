package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDeviceRequest extends BaseDeviceRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.UpdateDeviceRequest");
    private List<String> capabilitiesToGrant;
    private List<String> capabilitiesToRevoke;
    private String deviceName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof UpdateDeviceRequest)) {
            return 1;
        }
        UpdateDeviceRequest updateDeviceRequest = (UpdateDeviceRequest) cirrusBaseRequestV2;
        List<String> capabilitiesToGrant = getCapabilitiesToGrant();
        List<String> capabilitiesToGrant2 = updateDeviceRequest.getCapabilitiesToGrant();
        if (capabilitiesToGrant != capabilitiesToGrant2) {
            if (capabilitiesToGrant == null) {
                return -1;
            }
            if (capabilitiesToGrant2 == null) {
                return 1;
            }
            if (capabilitiesToGrant instanceof Comparable) {
                int iCompareTo = ((Comparable) capabilitiesToGrant).compareTo(capabilitiesToGrant2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!capabilitiesToGrant.equals(capabilitiesToGrant2)) {
                int iHashCode = capabilitiesToGrant.hashCode();
                int iHashCode2 = capabilitiesToGrant2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String deviceName = getDeviceName();
        String deviceName2 = updateDeviceRequest.getDeviceName();
        if (deviceName != deviceName2) {
            if (deviceName == null) {
                return -1;
            }
            if (deviceName2 == null) {
                return 1;
            }
            int iCompareTo2 = deviceName.compareTo(deviceName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> capabilitiesToRevoke = getCapabilitiesToRevoke();
        List<String> capabilitiesToRevoke2 = updateDeviceRequest.getCapabilitiesToRevoke();
        if (capabilitiesToRevoke != capabilitiesToRevoke2) {
            if (capabilitiesToRevoke == null) {
                return -1;
            }
            if (capabilitiesToRevoke2 == null) {
                return 1;
            }
            if (capabilitiesToRevoke instanceof Comparable) {
                int iCompareTo3 = ((Comparable) capabilitiesToRevoke).compareTo(capabilitiesToRevoke2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!capabilitiesToRevoke.equals(capabilitiesToRevoke2)) {
                int iHashCode3 = capabilitiesToRevoke.hashCode();
                int iHashCode4 = capabilitiesToRevoke2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof UpdateDeviceRequest)) {
            return false;
        }
        UpdateDeviceRequest updateDeviceRequest = (UpdateDeviceRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getCapabilitiesToGrant(), updateDeviceRequest.getCapabilitiesToGrant()) && internalEqualityCheck(getDeviceName(), updateDeviceRequest.getDeviceName()) && internalEqualityCheck(getCapabilitiesToRevoke(), updateDeviceRequest.getCapabilitiesToRevoke());
    }

    public List<String> getCapabilitiesToGrant() {
        return this.capabilitiesToGrant;
    }

    public List<String> getCapabilitiesToRevoke() {
        return this.capabilitiesToRevoke;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCapabilitiesToGrant(), getDeviceName(), getCapabilitiesToRevoke());
    }

    public void setCapabilitiesToGrant(List<String> list) {
        this.capabilitiesToGrant = list;
    }
}

package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AuthorizeDeviceRequest extends BaseDeviceRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AuthorizeDeviceRequest");
    private List<String> capabilitiesToGrant;
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
        if (!(cirrusBaseRequestV2 instanceof AuthorizeDeviceRequest)) {
            return 1;
        }
        AuthorizeDeviceRequest authorizeDeviceRequest = (AuthorizeDeviceRequest) cirrusBaseRequestV2;
        String deviceName = getDeviceName();
        String deviceName2 = authorizeDeviceRequest.getDeviceName();
        if (deviceName != deviceName2) {
            if (deviceName == null) {
                return -1;
            }
            if (deviceName2 == null) {
                return 1;
            }
            int iCompareTo = deviceName.compareTo(deviceName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> capabilitiesToGrant = getCapabilitiesToGrant();
        List<String> capabilitiesToGrant2 = authorizeDeviceRequest.getCapabilitiesToGrant();
        if (capabilitiesToGrant != capabilitiesToGrant2) {
            if (capabilitiesToGrant == null) {
                return -1;
            }
            if (capabilitiesToGrant2 == null) {
                return 1;
            }
            if (capabilitiesToGrant instanceof Comparable) {
                int iCompareTo2 = ((Comparable) capabilitiesToGrant).compareTo(capabilitiesToGrant2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof AuthorizeDeviceRequest)) {
            return false;
        }
        AuthorizeDeviceRequest authorizeDeviceRequest = (AuthorizeDeviceRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getDeviceName(), authorizeDeviceRequest.getDeviceName()) && internalEqualityCheck(getCapabilitiesToGrant(), authorizeDeviceRequest.getCapabilitiesToGrant());
    }

    public List<String> getCapabilitiesToGrant() {
        return this.capabilitiesToGrant;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDeviceName(), getCapabilitiesToGrant());
    }
}

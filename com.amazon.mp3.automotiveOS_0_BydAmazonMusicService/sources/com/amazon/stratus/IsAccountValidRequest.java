package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class IsAccountValidRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.IsAccountValidRequest");
    private String ipAddress;
    private Boolean verbose;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof IsAccountValidRequest)) {
            return 1;
        }
        IsAccountValidRequest isAccountValidRequest = (IsAccountValidRequest) cirrusBaseRequestV2;
        String ipAddress = getIpAddress();
        String ipAddress2 = isAccountValidRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo = ipAddress.compareTo(ipAddress2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsVerbose = isVerbose();
        Boolean boolIsVerbose2 = isAccountValidRequest.isVerbose();
        if (boolIsVerbose != boolIsVerbose2) {
            if (boolIsVerbose == null) {
                return -1;
            }
            if (boolIsVerbose2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsVerbose.compareTo(boolIsVerbose2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof IsAccountValidRequest)) {
            return false;
        }
        IsAccountValidRequest isAccountValidRequest = (IsAccountValidRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getIpAddress(), isAccountValidRequest.getIpAddress()) && internalEqualityCheck(isVerbose(), isAccountValidRequest.isVerbose());
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIpAddress(), isVerbose());
    }

    public Boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(Boolean bool) {
        this.verbose = bool;
    }
}

package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AttemptAccountCreationRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AttemptAccountCreationRequest");
    private Boolean acceptTermsOfUse;
    private String ipAddress;
    private String musicTerritory;

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
        if (!(cirrusBaseRequestV2 instanceof AttemptAccountCreationRequest)) {
            return 1;
        }
        AttemptAccountCreationRequest attemptAccountCreationRequest = (AttemptAccountCreationRequest) cirrusBaseRequestV2;
        String ipAddress = getIpAddress();
        String ipAddress2 = attemptAccountCreationRequest.getIpAddress();
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
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = attemptAccountCreationRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo2 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsAcceptTermsOfUse = isAcceptTermsOfUse();
        Boolean boolIsAcceptTermsOfUse2 = attemptAccountCreationRequest.isAcceptTermsOfUse();
        if (boolIsAcceptTermsOfUse != boolIsAcceptTermsOfUse2) {
            if (boolIsAcceptTermsOfUse == null) {
                return -1;
            }
            if (boolIsAcceptTermsOfUse2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsAcceptTermsOfUse.compareTo(boolIsAcceptTermsOfUse2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof AttemptAccountCreationRequest)) {
            return false;
        }
        AttemptAccountCreationRequest attemptAccountCreationRequest = (AttemptAccountCreationRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getIpAddress(), attemptAccountCreationRequest.getIpAddress()) && internalEqualityCheck(getMusicTerritory(), attemptAccountCreationRequest.getMusicTerritory()) && internalEqualityCheck(isAcceptTermsOfUse(), attemptAccountCreationRequest.isAcceptTermsOfUse());
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIpAddress(), getMusicTerritory(), isAcceptTermsOfUse());
    }

    public Boolean isAcceptTermsOfUse() {
        return this.acceptTermsOfUse;
    }

    public void setAcceptTermsOfUse(Boolean bool) {
        this.acceptTermsOfUse = bool;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }
}

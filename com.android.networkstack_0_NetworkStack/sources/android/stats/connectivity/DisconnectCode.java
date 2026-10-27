package android.stats.connectivity;

import com.android.networkstack.metrics.NetworkIpProvisioningReported;
import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum DisconnectCode implements Internal.EnumLite {
    DC_NONE(0),
    DC_NORMAL_TERMINATION(1),
    DC_PROVISIONING_FAIL(2),
    DC_ERROR_STARTING_IPV4(4),
    DC_ERROR_STARTING_IPV6(5),
    DC_ERROR_STARTING_IPREACHABILITYMONITOR(6),
    DC_INVALID_PROVISIONING(7),
    DC_INTERFACE_NOT_FOUND(8),
    DC_PROVISIONING_TIMEOUT(9);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static DisconnectCode forNumber(int i) {
        switch (i) {
            case 0:
                return DC_NONE;
            case 1:
                return DC_NORMAL_TERMINATION;
            case 2:
                return DC_PROVISIONING_FAIL;
            case 3:
            default:
                return null;
            case 4:
                return DC_ERROR_STARTING_IPV4;
            case 5:
                return DC_ERROR_STARTING_IPV6;
            case 6:
                return DC_ERROR_STARTING_IPREACHABILITYMONITOR;
            case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return DC_INVALID_PROVISIONING;
            case 8:
                return DC_INTERFACE_NOT_FOUND;
            case 9:
                return DC_PROVISIONING_TIMEOUT;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return DisconnectCodeVerifier.INSTANCE;
    }

    private static final class DisconnectCodeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new DisconnectCodeVerifier();

        private DisconnectCodeVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return DisconnectCode.forNumber(i) != null;
        }
    }

    DisconnectCode(int i) {
        this.value = i;
    }
}

package android.stats.connectivity;

import com.android.networkstack.metrics.NetworkIpProvisioningReported;
import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum TransportType implements Internal.EnumLite {
    TT_UNKNOWN(0),
    TT_CELLULAR(1),
    TT_WIFI(2),
    TT_BLUETOOTH(3),
    TT_ETHERNET(4),
    TT_WIFI_AWARE(5),
    TT_LOWPAN(6),
    TT_CELLULAR_VPN(7),
    TT_WIFI_VPN(8),
    TT_BLUETOOTH_VPN(9),
    TT_ETHERNET_VPN(10),
    TT_WIFI_CELLULAR_VPN(11),
    TT_TEST(12);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static TransportType forNumber(int i) {
        switch (i) {
            case 0:
                return TT_UNKNOWN;
            case 1:
                return TT_CELLULAR;
            case 2:
                return TT_WIFI;
            case 3:
                return TT_BLUETOOTH;
            case 4:
                return TT_ETHERNET;
            case 5:
                return TT_WIFI_AWARE;
            case 6:
                return TT_LOWPAN;
            case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return TT_CELLULAR_VPN;
            case 8:
                return TT_WIFI_VPN;
            case 9:
                return TT_BLUETOOTH_VPN;
            case 10:
                return TT_ETHERNET_VPN;
            case 11:
                return TT_WIFI_CELLULAR_VPN;
            case 12:
                return TT_TEST;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return TransportTypeVerifier.INSTANCE;
    }

    private static final class TransportTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new TransportTypeVerifier();

        private TransportTypeVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return TransportType.forNumber(i) != null;
        }
    }

    TransportType(int i) {
        this.value = i;
    }
}

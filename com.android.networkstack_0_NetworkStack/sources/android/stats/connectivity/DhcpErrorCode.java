package android.stats.connectivity;

import android.R;
import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum DhcpErrorCode implements Internal.EnumLite {
    ET_UNKNOWN(0),
    ET_L2_ERROR(1),
    ET_L3_ERROR(2),
    ET_L4_ERROR(3),
    ET_DHCP_ERROR(4),
    ET_MISC_ERROR(5),
    ET_L2_TOO_SHORT(R.attr.theme),
    ET_L2_WRONG_ETH_TYPE(R.id.background),
    ET_L3_TOO_SHORT(33619968),
    ET_L3_NOT_IPV4(33685504),
    ET_L3_INVALID_IP(33751040),
    ET_L4_NOT_UDP(50397184),
    ET_L4_WRONG_PORT(50462720),
    ET_BOOTP_TOO_SHORT(67174400),
    ET_DHCP_BAD_MAGIC_COOKIE(67239936),
    ET_DHCP_INVALID_OPTION_LENGTH(67305472),
    ET_DHCP_NO_MSG_TYPE(67371008),
    ET_DHCP_UNKNOWN_MSG_TYPE(67436544),
    ET_DHCP_NO_COOKIE(67502080),
    ET_BUFFER_UNDERFLOW(83951616),
    ET_RECEIVE_ERROR(84017152),
    ET_PARSING_ERROR(84082688);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static DhcpErrorCode forNumber(int i) {
        if (i == 0) {
            return ET_UNKNOWN;
        }
        if (i == 1) {
            return ET_L2_ERROR;
        }
        if (i == 2) {
            return ET_L3_ERROR;
        }
        if (i == 3) {
            return ET_L4_ERROR;
        }
        if (i == 4) {
            return ET_DHCP_ERROR;
        }
        if (i == 5) {
            return ET_MISC_ERROR;
        }
        switch (i) {
            case R.attr.theme:
                return ET_L2_TOO_SHORT;
            case R.id.background:
                return ET_L2_WRONG_ETH_TYPE;
            case 33619968:
                return ET_L3_TOO_SHORT;
            case 33685504:
                return ET_L3_NOT_IPV4;
            case 33751040:
                return ET_L3_INVALID_IP;
            case 50397184:
                return ET_L4_NOT_UDP;
            case 50462720:
                return ET_L4_WRONG_PORT;
            case 67174400:
                return ET_BOOTP_TOO_SHORT;
            case 67239936:
                return ET_DHCP_BAD_MAGIC_COOKIE;
            case 67305472:
                return ET_DHCP_INVALID_OPTION_LENGTH;
            case 67371008:
                return ET_DHCP_NO_MSG_TYPE;
            case 67436544:
                return ET_DHCP_UNKNOWN_MSG_TYPE;
            case 67502080:
                return ET_DHCP_NO_COOKIE;
            case 83951616:
                return ET_BUFFER_UNDERFLOW;
            case 84017152:
                return ET_RECEIVE_ERROR;
            case 84082688:
                return ET_PARSING_ERROR;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return DhcpErrorCodeVerifier.INSTANCE;
    }

    private static final class DhcpErrorCodeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new DhcpErrorCodeVerifier();

        private DhcpErrorCodeVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return DhcpErrorCode.forNumber(i) != null;
        }
    }

    DhcpErrorCode(int i) {
        this.value = i;
    }
}

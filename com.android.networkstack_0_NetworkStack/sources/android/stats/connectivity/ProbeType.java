package android.stats.connectivity;

import com.android.networkstack.metrics.NetworkIpProvisioningReported;
import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum ProbeType implements Internal.EnumLite {
    PT_UNKNOWN(0),
    PT_DNS(1),
    PT_HTTP(2),
    PT_HTTPS(3),
    PT_PAC(4),
    PT_FALLBACK(5),
    PT_PRIVDNS(6),
    PT_CAPPORT_API(7);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProbeType forNumber(int i) {
        switch (i) {
            case 0:
                return PT_UNKNOWN;
            case 1:
                return PT_DNS;
            case 2:
                return PT_HTTP;
            case 3:
                return PT_HTTPS;
            case 4:
                return PT_PAC;
            case 5:
                return PT_FALLBACK;
            case 6:
                return PT_PRIVDNS;
            case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return PT_CAPPORT_API;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return ProbeTypeVerifier.INSTANCE;
    }

    private static final class ProbeTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new ProbeTypeVerifier();

        private ProbeTypeVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return ProbeType.forNumber(i) != null;
        }
    }

    ProbeType(int i) {
        this.value = i;
    }
}

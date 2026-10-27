package android.stats.connectivity;

import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum ProbeResult implements Internal.EnumLite {
    PR_UNKNOWN(0),
    PR_SUCCESS(1),
    PR_FAILURE(2),
    PR_PORTAL(3),
    PR_PRIVATE_IP_DNS(4);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProbeResult forNumber(int i) {
        if (i == 0) {
            return PR_UNKNOWN;
        }
        if (i == 1) {
            return PR_SUCCESS;
        }
        if (i == 2) {
            return PR_FAILURE;
        }
        if (i == 3) {
            return PR_PORTAL;
        }
        if (i != 4) {
            return null;
        }
        return PR_PRIVATE_IP_DNS;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return ProbeResultVerifier.INSTANCE;
    }

    private static final class ProbeResultVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new ProbeResultVerifier();

        private ProbeResultVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return ProbeResult.forNumber(i) != null;
        }
    }

    ProbeResult(int i) {
        this.value = i;
    }
}

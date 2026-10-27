package android.stats.connectivity;

import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum HostnameTransResult implements Internal.EnumLite {
    HTR_UNKNOWN(0),
    HTR_SUCCESS(1),
    HTR_FAILURE(2),
    HTR_DISABLE(3);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static HostnameTransResult forNumber(int i) {
        if (i == 0) {
            return HTR_UNKNOWN;
        }
        if (i == 1) {
            return HTR_SUCCESS;
        }
        if (i == 2) {
            return HTR_FAILURE;
        }
        if (i != 3) {
            return null;
        }
        return HTR_DISABLE;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return HostnameTransResultVerifier.INSTANCE;
    }

    private static final class HostnameTransResultVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new HostnameTransResultVerifier();

        private HostnameTransResultVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return HostnameTransResult.forNumber(i) != null;
        }
    }

    HostnameTransResult(int i) {
        this.value = i;
    }
}

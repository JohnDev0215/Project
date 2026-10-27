package android.stats.connectivity;

import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum ValidationResult implements Internal.EnumLite {
    VR_UNKNOWN(0),
    VR_SUCCESS(1),
    VR_FAILURE(2),
    VR_PORTAL(3),
    VR_PARTIAL(4);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ValidationResult forNumber(int i) {
        if (i == 0) {
            return VR_UNKNOWN;
        }
        if (i == 1) {
            return VR_SUCCESS;
        }
        if (i == 2) {
            return VR_FAILURE;
        }
        if (i == 3) {
            return VR_PORTAL;
        }
        if (i != 4) {
            return null;
        }
        return VR_PARTIAL;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return ValidationResultVerifier.INSTANCE;
    }

    private static final class ValidationResultVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new ValidationResultVerifier();

        private ValidationResultVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return ValidationResult.forNumber(i) != null;
        }
    }

    ValidationResult(int i) {
        this.value = i;
    }
}

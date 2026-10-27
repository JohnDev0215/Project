package android.stats.connectivity;

import com.android.networkstack.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum DhcpFeature implements Internal.EnumLite {
    DF_UNKNOWN(0),
    DF_INITREBOOT(1),
    DF_RAPIDCOMMIT(2),
    DF_DAD(3),
    DF_FILS(4);

    private final int value;

    @Override // com.android.networkstack.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static DhcpFeature forNumber(int i) {
        if (i == 0) {
            return DF_UNKNOWN;
        }
        if (i == 1) {
            return DF_INITREBOOT;
        }
        if (i == 2) {
            return DF_RAPIDCOMMIT;
        }
        if (i == 3) {
            return DF_DAD;
        }
        if (i != 4) {
            return null;
        }
        return DF_FILS;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return DhcpFeatureVerifier.INSTANCE;
    }

    private static final class DhcpFeatureVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new DhcpFeatureVerifier();

        private DhcpFeatureVerifier() {
        }

        @Override // com.android.networkstack.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return DhcpFeature.forNumber(i) != null;
        }
    }

    DhcpFeature(int i) {
        this.value = i;
    }
}

package com.android.car.BLEStreamProtos;

import com.android.car.protobuf.ExtensionRegistryLite;
import com.android.car.protobuf.Internal;

/* JADX INFO: loaded from: classes3.dex */
public final class BLEOperationProto {
    private BLEOperationProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum OperationType implements Internal.EnumLite {
        OPERATION_TYPE_UNKNOWN(0),
        ENCRYPTION_HANDSHAKE(2),
        ACK(3),
        CLIENT_MESSAGE(4),
        UNRECOGNIZED(-1);

        public static final int ACK_VALUE = 3;
        public static final int CLIENT_MESSAGE_VALUE = 4;
        public static final int ENCRYPTION_HANDSHAKE_VALUE = 2;
        public static final int OPERATION_TYPE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<OperationType> internalValueMap = new Internal.EnumLiteMap<OperationType>() { // from class: com.android.car.BLEStreamProtos.BLEOperationProto.OperationType.1
            @Override // com.android.car.protobuf.Internal.EnumLiteMap
            public OperationType findValueByNumber(int number) {
                return OperationType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.car.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static OperationType valueOf(int value) {
            return forNumber(value);
        }

        public static OperationType forNumber(int value) {
            if (value == 0) {
                return OPERATION_TYPE_UNKNOWN;
            }
            if (value == 2) {
                return ENCRYPTION_HANDSHAKE;
            }
            if (value == 3) {
                return ACK;
            }
            if (value == 4) {
                return CLIENT_MESSAGE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<OperationType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return OperationTypeVerifier.INSTANCE;
        }

        private static final class OperationTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new OperationTypeVerifier();

            private OperationTypeVerifier() {
            }

            @Override // com.android.car.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return OperationType.forNumber(number) != null;
            }
        }

        OperationType(int value) {
            this.value = value;
        }
    }
}

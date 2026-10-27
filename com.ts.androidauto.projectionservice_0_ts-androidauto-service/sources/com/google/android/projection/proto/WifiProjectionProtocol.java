package com.google.android.projection.proto;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public final class WifiProjectionProtocol {
    private WifiProjectionProtocol() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum TransportType implements Internal.EnumLite {
        TRANSPORT_TYPE_UNKNOWN(0),
        TRANSPORT_TYPE_BLUETOOTH_RFCOMM(1),
        TRANSPORT_TYPE_WIFI_TCP(2);

        public static final int TRANSPORT_TYPE_BLUETOOTH_RFCOMM_VALUE = 1;
        public static final int TRANSPORT_TYPE_UNKNOWN_VALUE = 0;
        public static final int TRANSPORT_TYPE_WIFI_TCP_VALUE = 2;
        private static final Internal.EnumLiteMap<TransportType> internalValueMap = new Internal.EnumLiteMap<TransportType>() { // from class: com.google.android.projection.proto.WifiProjectionProtocol.TransportType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TransportType findValueByNumber(int number) {
                return TransportType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TransportType valueOf(int value) {
            return forNumber(value);
        }

        public static TransportType forNumber(int value) {
            if (value == 0) {
                return TRANSPORT_TYPE_UNKNOWN;
            }
            if (value == 1) {
                return TRANSPORT_TYPE_BLUETOOTH_RFCOMM;
            }
            if (value == 2) {
                return TRANSPORT_TYPE_WIFI_TCP;
            }
            return null;
        }

        public static Internal.EnumLiteMap<TransportType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TransportTypeVerifier.INSTANCE;
        }

        private static final class TransportTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new TransportTypeVerifier();

            private TransportTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return TransportType.forNumber(number) != null;
            }
        }

        TransportType(int value) {
            this.value = value;
        }
    }

    public enum ProjectionStartResult implements Internal.EnumLite {
        PROJECTION_START_RESULT_UNKNOWN(0),
        PROJECTION_START_RESULT_START_REQUEST_SENT(1),
        PROJECTION_START_RESULT_DEVICE_NOT_READY(2);

        public static final int PROJECTION_START_RESULT_DEVICE_NOT_READY_VALUE = 2;
        public static final int PROJECTION_START_RESULT_START_REQUEST_SENT_VALUE = 1;
        public static final int PROJECTION_START_RESULT_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ProjectionStartResult> internalValueMap = new Internal.EnumLiteMap<ProjectionStartResult>() { // from class: com.google.android.projection.proto.WifiProjectionProtocol.ProjectionStartResult.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ProjectionStartResult findValueByNumber(int number) {
                return ProjectionStartResult.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ProjectionStartResult valueOf(int value) {
            return forNumber(value);
        }

        public static ProjectionStartResult forNumber(int value) {
            if (value == 0) {
                return PROJECTION_START_RESULT_UNKNOWN;
            }
            if (value == 1) {
                return PROJECTION_START_RESULT_START_REQUEST_SENT;
            }
            if (value == 2) {
                return PROJECTION_START_RESULT_DEVICE_NOT_READY;
            }
            return null;
        }

        public static Internal.EnumLiteMap<ProjectionStartResult> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ProjectionStartResultVerifier.INSTANCE;
        }

        private static final class ProjectionStartResultVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ProjectionStartResultVerifier();

            private ProjectionStartResultVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return ProjectionStartResult.forNumber(number) != null;
            }
        }

        ProjectionStartResult(int value) {
            this.value = value;
        }
    }

    public enum MessageDirection implements Internal.EnumLite {
        MESSAGE_DIRECTION_UNKNOWN(0),
        MESSAGE_DIRECTION_TO_REMOTE_DEVICE(1),
        MESSAGE_DIRECTION_FROM_REMOTE_DEVICE(2);

        public static final int MESSAGE_DIRECTION_FROM_REMOTE_DEVICE_VALUE = 2;
        public static final int MESSAGE_DIRECTION_TO_REMOTE_DEVICE_VALUE = 1;
        public static final int MESSAGE_DIRECTION_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<MessageDirection> internalValueMap = new Internal.EnumLiteMap<MessageDirection>() { // from class: com.google.android.projection.proto.WifiProjectionProtocol.MessageDirection.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MessageDirection findValueByNumber(int number) {
                return MessageDirection.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static MessageDirection valueOf(int value) {
            return forNumber(value);
        }

        public static MessageDirection forNumber(int value) {
            if (value == 0) {
                return MESSAGE_DIRECTION_UNKNOWN;
            }
            if (value == 1) {
                return MESSAGE_DIRECTION_TO_REMOTE_DEVICE;
            }
            if (value == 2) {
                return MESSAGE_DIRECTION_FROM_REMOTE_DEVICE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<MessageDirection> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return MessageDirectionVerifier.INSTANCE;
        }

        private static final class MessageDirectionVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new MessageDirectionVerifier();

            private MessageDirectionVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return MessageDirection.forNumber(number) != null;
            }
        }

        MessageDirection(int value) {
            this.value = value;
        }
    }
}

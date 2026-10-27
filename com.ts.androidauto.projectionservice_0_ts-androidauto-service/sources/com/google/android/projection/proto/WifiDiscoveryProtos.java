package com.google.android.projection.proto;

import com.google.android.projection.proto.Common;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WifiDiscoveryProtos {

    public interface WifiConnectStatusOrBuilder extends MessageLiteOrBuilder {
        MessageStatus getConnectStatus();

        String getErrorMessageHint();

        ByteString getErrorMessageHintBytes();

        boolean hasConnectStatus();

        boolean hasErrorMessageHint();
    }

    public interface WifiInfoRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public interface WifiInfoResponseOrBuilder extends MessageLiteOrBuilder {
        AccessPointType getAccessPointType();

        String getWifiBssid();

        ByteString getWifiBssidBytes();

        String getWifiPassword();

        ByteString getWifiPasswordBytes();

        WifiSecurityMode getWifiSecurityMode();

        String getWifiSsid();

        ByteString getWifiSsidBytes();

        boolean hasAccessPointType();

        boolean hasWifiBssid();

        boolean hasWifiPassword();

        boolean hasWifiSecurityMode();

        boolean hasWifiSsid();
    }

    public interface WifiPingRequestOrBuilder extends MessageLiteOrBuilder {
        ByteString getData();

        long getTimestampMs();

        boolean hasData();

        boolean hasTimestampMs();
    }

    public interface WifiPingResponseOrBuilder extends MessageLiteOrBuilder {
        ByteString getData();

        long getTimestampMs();

        boolean hasData();

        boolean hasTimestampMs();
    }

    public interface WifiProjectionProtocolInfoOrBuilder extends MessageLiteOrBuilder {
        String getIpAddress();

        ByteString getIpAddressBytes();

        int getPort();

        boolean hasIpAddress();

        boolean hasPort();
    }

    public interface WifiStartRequestOrBuilder extends MessageLiteOrBuilder {
        String getIpAddress();

        ByteString getIpAddressBytes();

        int getPort();

        StartRequestReason getStartRequestReason();

        boolean hasIpAddress();

        boolean hasPort();

        boolean hasStartRequestReason();
    }

    public interface WifiStartResponseOrBuilder extends MessageLiteOrBuilder {
        String getIpAddress();

        ByteString getIpAddressBytes();

        int getPort();

        MessageStatus getStatus();

        boolean hasIpAddress();

        boolean hasPort();

        boolean hasStatus();
    }

    public interface WifiVersionRequestOrBuilder extends MessageLiteOrBuilder {
        Common.HeadUnitInfo getHeadunitInfo();

        int getMajorVersion();

        int getMinorVersion();

        WifiChannelType getSupportedWifiChannelType();

        int getSupportedWifiChannels(int index);

        int getSupportedWifiChannelsCount();

        List<Integer> getSupportedWifiChannelsList();

        WifiProjectionProtocolInfo getWifiProjectionProtocolInfo();

        boolean hasHeadunitInfo();

        boolean hasMajorVersion();

        boolean hasMinorVersion();

        boolean hasSupportedWifiChannelType();

        boolean hasWifiProjectionProtocolInfo();
    }

    public interface WifiVersionResponseOrBuilder extends MessageLiteOrBuilder {
        String getDeviceSerial();

        ByteString getDeviceSerialBytes();

        int getMajorVersion();

        int getMinorVersion();

        Common.PhoneInfo getPhoneInfo();

        WifiChannelType getSelectedWifiChannelType();

        MessageStatus getVersionStatus();

        boolean hasDeviceSerial();

        boolean hasMajorVersion();

        boolean hasMinorVersion();

        boolean hasPhoneInfo();

        boolean hasSelectedWifiChannelType();

        boolean hasVersionStatus();
    }

    private WifiDiscoveryProtos() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum WifiMessageType implements Internal.EnumLite {
        MESSAGE_WIFI_REQUEST_START_BT(1),
        MESSAGE_WIFI_REQUEST_INFO_BT(2),
        MESSAGE_WIFI_RESPONSE_INFO_BT(3),
        MESSAGE_WIFI_VERSION_REQUEST_BT(4),
        MESSAGE_WIFI_VERSION_RESPONSE_BT(5),
        MESSAGE_WIFI_CONNECT_STATUS_BT(6),
        MESSAGE_WIFI_RESPONSE_START_BT(7),
        MESSAGE_WIFI_PING_REQUEST_BT(8),
        MESSAGE_WIFI_PING_RESPONSE_BT(9);

        public static final int MESSAGE_WIFI_CONNECT_STATUS_BT_VALUE = 6;
        public static final int MESSAGE_WIFI_PING_REQUEST_BT_VALUE = 8;
        public static final int MESSAGE_WIFI_PING_RESPONSE_BT_VALUE = 9;
        public static final int MESSAGE_WIFI_REQUEST_INFO_BT_VALUE = 2;
        public static final int MESSAGE_WIFI_REQUEST_START_BT_VALUE = 1;
        public static final int MESSAGE_WIFI_RESPONSE_INFO_BT_VALUE = 3;
        public static final int MESSAGE_WIFI_RESPONSE_START_BT_VALUE = 7;
        public static final int MESSAGE_WIFI_VERSION_REQUEST_BT_VALUE = 4;
        public static final int MESSAGE_WIFI_VERSION_RESPONSE_BT_VALUE = 5;
        private static final Internal.EnumLiteMap<WifiMessageType> internalValueMap = new Internal.EnumLiteMap<WifiMessageType>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.WifiMessageType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public WifiMessageType findValueByNumber(int number) {
                return WifiMessageType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static WifiMessageType valueOf(int value) {
            return forNumber(value);
        }

        public static WifiMessageType forNumber(int value) {
            switch (value) {
                case 1:
                    return MESSAGE_WIFI_REQUEST_START_BT;
                case 2:
                    return MESSAGE_WIFI_REQUEST_INFO_BT;
                case 3:
                    return MESSAGE_WIFI_RESPONSE_INFO_BT;
                case 4:
                    return MESSAGE_WIFI_VERSION_REQUEST_BT;
                case 5:
                    return MESSAGE_WIFI_VERSION_RESPONSE_BT;
                case 6:
                    return MESSAGE_WIFI_CONNECT_STATUS_BT;
                case 7:
                    return MESSAGE_WIFI_RESPONSE_START_BT;
                case 8:
                    return MESSAGE_WIFI_PING_REQUEST_BT;
                case 9:
                    return MESSAGE_WIFI_PING_RESPONSE_BT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<WifiMessageType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return WifiMessageTypeVerifier.INSTANCE;
        }

        private static final class WifiMessageTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new WifiMessageTypeVerifier();

            private WifiMessageTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return WifiMessageType.forNumber(number) != null;
            }
        }

        WifiMessageType(int value) {
            this.value = value;
        }
    }

    public enum WifiSecurityMode implements Internal.EnumLite {
        UNKNOWN_SECURITY_MODE(0),
        OPEN(1),
        WEP_64(2),
        WEP_128(3),
        WPA_PERSONAL(4),
        WPA2_PERSONAL(8),
        WPA_WPA2_PERSONAL(12),
        WPA_ENTERPRISE(20),
        WPA2_ENTERPRISE(24),
        WPA_WPA2_ENTERPRISE(28),
        WPA3_PERSONAL(32),
        WPA2_WPA3_PERSONAL(40);

        public static final int OPEN_VALUE = 1;
        public static final int UNKNOWN_SECURITY_MODE_VALUE = 0;
        public static final int WEP_128_VALUE = 3;
        public static final int WEP_64_VALUE = 2;
        public static final int WPA2_ENTERPRISE_VALUE = 24;
        public static final int WPA2_PERSONAL_VALUE = 8;
        public static final int WPA2_WPA3_PERSONAL_VALUE = 40;
        public static final int WPA3_PERSONAL_VALUE = 32;
        public static final int WPA_ENTERPRISE_VALUE = 20;
        public static final int WPA_PERSONAL_VALUE = 4;
        public static final int WPA_WPA2_ENTERPRISE_VALUE = 28;
        public static final int WPA_WPA2_PERSONAL_VALUE = 12;
        private static final Internal.EnumLiteMap<WifiSecurityMode> internalValueMap = new Internal.EnumLiteMap<WifiSecurityMode>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.WifiSecurityMode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public WifiSecurityMode findValueByNumber(int number) {
                return WifiSecurityMode.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static WifiSecurityMode valueOf(int value) {
            return forNumber(value);
        }

        public static WifiSecurityMode forNumber(int value) {
            if (value == 0) {
                return UNKNOWN_SECURITY_MODE;
            }
            if (value == 1) {
                return OPEN;
            }
            if (value == 2) {
                return WEP_64;
            }
            if (value == 3) {
                return WEP_128;
            }
            if (value == 4) {
                return WPA_PERSONAL;
            }
            if (value == 8) {
                return WPA2_PERSONAL;
            }
            if (value == 12) {
                return WPA_WPA2_PERSONAL;
            }
            if (value == 20) {
                return WPA_ENTERPRISE;
            }
            if (value == 24) {
                return WPA2_ENTERPRISE;
            }
            if (value == 28) {
                return WPA_WPA2_ENTERPRISE;
            }
            if (value == 32) {
                return WPA3_PERSONAL;
            }
            if (value == 40) {
                return WPA2_WPA3_PERSONAL;
            }
            return null;
        }

        public static Internal.EnumLiteMap<WifiSecurityMode> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return WifiSecurityModeVerifier.INSTANCE;
        }

        private static final class WifiSecurityModeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new WifiSecurityModeVerifier();

            private WifiSecurityModeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return WifiSecurityMode.forNumber(number) != null;
            }
        }

        WifiSecurityMode(int value) {
            this.value = value;
        }
    }

    public enum WifiChannelType implements Internal.EnumLite {
        CHANNELS_5GHZ_ONLY(0),
        CHANNELS_24GHZ_ONLY(1),
        CHANNELS_DUAL_BAND(2),
        NO_CHANNELS_SUPPORTED(3);

        public static final int CHANNELS_24GHZ_ONLY_VALUE = 1;
        public static final int CHANNELS_5GHZ_ONLY_VALUE = 0;
        public static final int CHANNELS_DUAL_BAND_VALUE = 2;
        public static final int NO_CHANNELS_SUPPORTED_VALUE = 3;
        private static final Internal.EnumLiteMap<WifiChannelType> internalValueMap = new Internal.EnumLiteMap<WifiChannelType>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.WifiChannelType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public WifiChannelType findValueByNumber(int number) {
                return WifiChannelType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static WifiChannelType valueOf(int value) {
            return forNumber(value);
        }

        public static WifiChannelType forNumber(int value) {
            if (value == 0) {
                return CHANNELS_5GHZ_ONLY;
            }
            if (value == 1) {
                return CHANNELS_24GHZ_ONLY;
            }
            if (value == 2) {
                return CHANNELS_DUAL_BAND;
            }
            if (value == 3) {
                return NO_CHANNELS_SUPPORTED;
            }
            return null;
        }

        public static Internal.EnumLiteMap<WifiChannelType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return WifiChannelTypeVerifier.INSTANCE;
        }

        private static final class WifiChannelTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new WifiChannelTypeVerifier();

            private WifiChannelTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return WifiChannelType.forNumber(number) != null;
            }
        }

        WifiChannelType(int value) {
            this.value = value;
        }
    }

    public enum AccessPointType implements Internal.EnumLite {
        STATIC(0),
        DYNAMIC(1);

        public static final int DYNAMIC_VALUE = 1;
        public static final int STATIC_VALUE = 0;
        private static final Internal.EnumLiteMap<AccessPointType> internalValueMap = new Internal.EnumLiteMap<AccessPointType>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.AccessPointType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AccessPointType findValueByNumber(int number) {
                return AccessPointType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AccessPointType valueOf(int value) {
            return forNumber(value);
        }

        public static AccessPointType forNumber(int value) {
            if (value == 0) {
                return STATIC;
            }
            if (value == 1) {
                return DYNAMIC;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AccessPointType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return AccessPointTypeVerifier.INSTANCE;
        }

        private static final class AccessPointTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new AccessPointTypeVerifier();

            private AccessPointTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return AccessPointType.forNumber(number) != null;
            }
        }

        AccessPointType(int value) {
            this.value = value;
        }
    }

    public enum StartRequestReason implements Internal.EnumLite {
        USER_REQUEST(0),
        AUTO_LAUNCH(1),
        AUTOMATIC_RESTART(2);

        public static final int AUTOMATIC_RESTART_VALUE = 2;
        public static final int AUTO_LAUNCH_VALUE = 1;
        public static final int USER_REQUEST_VALUE = 0;
        private static final Internal.EnumLiteMap<StartRequestReason> internalValueMap = new Internal.EnumLiteMap<StartRequestReason>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.StartRequestReason.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public StartRequestReason findValueByNumber(int number) {
                return StartRequestReason.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static StartRequestReason valueOf(int value) {
            return forNumber(value);
        }

        public static StartRequestReason forNumber(int value) {
            if (value == 0) {
                return USER_REQUEST;
            }
            if (value == 1) {
                return AUTO_LAUNCH;
            }
            if (value == 2) {
                return AUTOMATIC_RESTART;
            }
            return null;
        }

        public static Internal.EnumLiteMap<StartRequestReason> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return StartRequestReasonVerifier.INSTANCE;
        }

        private static final class StartRequestReasonVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new StartRequestReasonVerifier();

            private StartRequestReasonVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return StartRequestReason.forNumber(number) != null;
            }
        }

        StartRequestReason(int value) {
            this.value = value;
        }
    }

    public enum MessageStatus implements Internal.EnumLite {
        STATUS_UNSOLICITED_MESSAGE(1),
        STATUS_SUCCESS(0),
        STATUS_NO_COMPATIBLE_VERSION(-1),
        STATUS_WIFI_INACCESSIBLE_CHANNEL(-2),
        STATUS_WIFI_INCORRECT_CREDENTIALS(-3),
        STATUS_PROJECTION_ALREADY_STARTED(-4),
        STATUS_WIFI_DISABLED(-5),
        STATUS_WIFI_NOT_YET_STARTED(-6),
        STATUS_INVALID_HOST(-7),
        STATUS_NO_SUPPORTED_WIFI_CHANNELS(-8),
        STATUS_INSTRUCT_USER_TO_CHECK_THE_PHONE(-9),
        STATUS_PHONE_WIFI_DISABLED(-10),
        STATUS_WIFI_NETWORK_UNAVAILABLE(-11);

        public static final int STATUS_INSTRUCT_USER_TO_CHECK_THE_PHONE_VALUE = -9;
        public static final int STATUS_INVALID_HOST_VALUE = -7;
        public static final int STATUS_NO_COMPATIBLE_VERSION_VALUE = -1;
        public static final int STATUS_NO_SUPPORTED_WIFI_CHANNELS_VALUE = -8;
        public static final int STATUS_PHONE_WIFI_DISABLED_VALUE = -10;
        public static final int STATUS_PROJECTION_ALREADY_STARTED_VALUE = -4;
        public static final int STATUS_SUCCESS_VALUE = 0;
        public static final int STATUS_UNSOLICITED_MESSAGE_VALUE = 1;
        public static final int STATUS_WIFI_DISABLED_VALUE = -5;
        public static final int STATUS_WIFI_INACCESSIBLE_CHANNEL_VALUE = -2;
        public static final int STATUS_WIFI_INCORRECT_CREDENTIALS_VALUE = -3;
        public static final int STATUS_WIFI_NETWORK_UNAVAILABLE_VALUE = -11;
        public static final int STATUS_WIFI_NOT_YET_STARTED_VALUE = -6;
        private static final Internal.EnumLiteMap<MessageStatus> internalValueMap = new Internal.EnumLiteMap<MessageStatus>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.MessageStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MessageStatus findValueByNumber(int number) {
                return MessageStatus.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static MessageStatus valueOf(int value) {
            return forNumber(value);
        }

        public static MessageStatus forNumber(int value) {
            switch (value) {
                case -11:
                    return STATUS_WIFI_NETWORK_UNAVAILABLE;
                case -10:
                    return STATUS_PHONE_WIFI_DISABLED;
                case -9:
                    return STATUS_INSTRUCT_USER_TO_CHECK_THE_PHONE;
                case -8:
                    return STATUS_NO_SUPPORTED_WIFI_CHANNELS;
                case -7:
                    return STATUS_INVALID_HOST;
                case -6:
                    return STATUS_WIFI_NOT_YET_STARTED;
                case -5:
                    return STATUS_WIFI_DISABLED;
                case -4:
                    return STATUS_PROJECTION_ALREADY_STARTED;
                case -3:
                    return STATUS_WIFI_INCORRECT_CREDENTIALS;
                case -2:
                    return STATUS_WIFI_INACCESSIBLE_CHANNEL;
                case -1:
                    return STATUS_NO_COMPATIBLE_VERSION;
                case 0:
                    return STATUS_SUCCESS;
                case 1:
                    return STATUS_UNSOLICITED_MESSAGE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<MessageStatus> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return MessageStatusVerifier.INSTANCE;
        }

        private static final class MessageStatusVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new MessageStatusVerifier();

            private MessageStatusVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return MessageStatus.forNumber(number) != null;
            }
        }

        MessageStatus(int value) {
            this.value = value;
        }
    }

    public enum WifiConstants implements Internal.EnumLite {
        PROTOCOL_MAJOR_VERSION(4),
        PROTOCOL_MINOR_VERSION(1);

        public static final int PROTOCOL_MAJOR_VERSION_VALUE = 4;
        public static final int PROTOCOL_MINOR_VERSION_VALUE = 1;
        private static final Internal.EnumLiteMap<WifiConstants> internalValueMap = new Internal.EnumLiteMap<WifiConstants>() { // from class: com.google.android.projection.proto.WifiDiscoveryProtos.WifiConstants.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public WifiConstants findValueByNumber(int number) {
                return WifiConstants.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static WifiConstants valueOf(int value) {
            return forNumber(value);
        }

        public static WifiConstants forNumber(int value) {
            if (value == 1) {
                return PROTOCOL_MINOR_VERSION;
            }
            if (value == 4) {
                return PROTOCOL_MAJOR_VERSION;
            }
            return null;
        }

        public static Internal.EnumLiteMap<WifiConstants> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return WifiConstantsVerifier.INSTANCE;
        }

        private static final class WifiConstantsVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new WifiConstantsVerifier();

            private WifiConstantsVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return WifiConstants.forNumber(number) != null;
            }
        }

        WifiConstants(int value) {
            this.value = value;
        }
    }

    public static final class WifiProjectionProtocolInfo extends GeneratedMessageLite<WifiProjectionProtocolInfo, Builder> implements WifiProjectionProtocolInfoOrBuilder {
        private static final WifiProjectionProtocolInfo DEFAULT_INSTANCE;
        public static final int IP_ADDRESS_FIELD_NUMBER = 1;
        private static volatile Parser<WifiProjectionProtocolInfo> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        private int bitField0_;
        private String ipAddress_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private int port_;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiProjectionProtocolInfo() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
        public boolean hasIpAddress() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
        public String getIpAddress() {
            return this.ipAddress_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
        public ByteString getIpAddressBytes() {
            return ByteString.copyFromUtf8(this.ipAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddress(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.ipAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIpAddress() {
            this.bitField0_ &= -2;
            this.ipAddress_ = getDefaultInstance().getIpAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddressBytes(ByteString value) {
            this.ipAddress_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
        public boolean hasPort() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
        public int getPort() {
            return this.port_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPort(int value) {
            this.bitField0_ |= 2;
            this.port_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPort() {
            this.bitField0_ &= -3;
            this.port_ = 0;
        }

        public static WifiProjectionProtocolInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiProjectionProtocolInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiProjectionProtocolInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiProjectionProtocolInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiProjectionProtocolInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiProjectionProtocolInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiProjectionProtocolInfo parseFrom(InputStream input) throws IOException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiProjectionProtocolInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiProjectionProtocolInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiProjectionProtocolInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiProjectionProtocolInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiProjectionProtocolInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiProjectionProtocolInfo parseFrom(CodedInputStream input) throws IOException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiProjectionProtocolInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiProjectionProtocolInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiProjectionProtocolInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiProjectionProtocolInfo, Builder> implements WifiProjectionProtocolInfoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiProjectionProtocolInfo.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
            public boolean hasIpAddress() {
                return ((WifiProjectionProtocolInfo) this.instance).hasIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
            public String getIpAddress() {
                return ((WifiProjectionProtocolInfo) this.instance).getIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
            public ByteString getIpAddressBytes() {
                return ((WifiProjectionProtocolInfo) this.instance).getIpAddressBytes();
            }

            public Builder setIpAddress(String value) {
                copyOnWrite();
                ((WifiProjectionProtocolInfo) this.instance).setIpAddress(value);
                return this;
            }

            public Builder clearIpAddress() {
                copyOnWrite();
                ((WifiProjectionProtocolInfo) this.instance).clearIpAddress();
                return this;
            }

            public Builder setIpAddressBytes(ByteString value) {
                copyOnWrite();
                ((WifiProjectionProtocolInfo) this.instance).setIpAddressBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
            public boolean hasPort() {
                return ((WifiProjectionProtocolInfo) this.instance).hasPort();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiProjectionProtocolInfoOrBuilder
            public int getPort() {
                return ((WifiProjectionProtocolInfo) this.instance).getPort();
            }

            public Builder setPort(int value) {
                copyOnWrite();
                ((WifiProjectionProtocolInfo) this.instance).setPort(value);
                return this;
            }

            public Builder clearPort() {
                copyOnWrite();
                ((WifiProjectionProtocolInfo) this.instance).clearPort();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiProjectionProtocolInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "ipAddress_", "port_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiProjectionProtocolInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiProjectionProtocolInfo.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiProjectionProtocolInfo defaultInstance = new WifiProjectionProtocolInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiProjectionProtocolInfo.class, defaultInstance);
        }

        public static WifiProjectionProtocolInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiProjectionProtocolInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.android.projection.proto.WifiDiscoveryProtos$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static final class WifiVersionRequest extends GeneratedMessageLite<WifiVersionRequest, Builder> implements WifiVersionRequestOrBuilder {
        private static final WifiVersionRequest DEFAULT_INSTANCE;
        public static final int HEADUNIT_INFO_FIELD_NUMBER = 5;
        public static final int MAJOR_VERSION_FIELD_NUMBER = 1;
        public static final int MINOR_VERSION_FIELD_NUMBER = 2;
        private static volatile Parser<WifiVersionRequest> PARSER = null;
        public static final int SUPPORTED_WIFI_CHANNELS_FIELD_NUMBER = 4;
        public static final int SUPPORTED_WIFI_CHANNEL_TYPE_FIELD_NUMBER = 3;
        public static final int WIFI_PROJECTION_PROTOCOL_INFO_FIELD_NUMBER = 6;
        private int bitField0_;
        private Common.HeadUnitInfo headunitInfo_;
        private int majorVersion_;
        private int minorVersion_;
        private int supportedWifiChannelType_;
        private Internal.IntList supportedWifiChannels_ = emptyIntList();
        private WifiProjectionProtocolInfo wifiProjectionProtocolInfo_;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiVersionRequest() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public boolean hasMajorVersion() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public int getMajorVersion() {
            return this.majorVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMajorVersion(int value) {
            this.bitField0_ |= 1;
            this.majorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMajorVersion() {
            this.bitField0_ &= -2;
            this.majorVersion_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public boolean hasMinorVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public int getMinorVersion() {
            return this.minorVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinorVersion(int value) {
            this.bitField0_ |= 2;
            this.minorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinorVersion() {
            this.bitField0_ &= -3;
            this.minorVersion_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public boolean hasSupportedWifiChannelType() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public WifiChannelType getSupportedWifiChannelType() {
            WifiChannelType result = WifiChannelType.forNumber(this.supportedWifiChannelType_);
            return result == null ? WifiChannelType.CHANNELS_5GHZ_ONLY : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedWifiChannelType(WifiChannelType value) {
            this.supportedWifiChannelType_ = value.getNumber();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedWifiChannelType() {
            this.bitField0_ &= -5;
            this.supportedWifiChannelType_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public List<Integer> getSupportedWifiChannelsList() {
            return this.supportedWifiChannels_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public int getSupportedWifiChannelsCount() {
            return this.supportedWifiChannels_.size();
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public int getSupportedWifiChannels(int index) {
            return this.supportedWifiChannels_.getInt(index);
        }

        private void ensureSupportedWifiChannelsIsMutable() {
            Internal.IntList tmp = this.supportedWifiChannels_;
            if (!tmp.isModifiable()) {
                this.supportedWifiChannels_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedWifiChannels(int index, int value) {
            ensureSupportedWifiChannelsIsMutable();
            this.supportedWifiChannels_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedWifiChannels(int value) {
            ensureSupportedWifiChannelsIsMutable();
            this.supportedWifiChannels_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedWifiChannels(Iterable<? extends Integer> values) {
            ensureSupportedWifiChannelsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.supportedWifiChannels_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedWifiChannels() {
            this.supportedWifiChannels_ = emptyIntList();
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public boolean hasHeadunitInfo() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public Common.HeadUnitInfo getHeadunitInfo() {
            Common.HeadUnitInfo headUnitInfo = this.headunitInfo_;
            return headUnitInfo == null ? Common.HeadUnitInfo.getDefaultInstance() : headUnitInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadunitInfo(Common.HeadUnitInfo value) {
            value.getClass();
            this.headunitInfo_ = value;
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeHeadunitInfo(Common.HeadUnitInfo value) {
            value.getClass();
            Common.HeadUnitInfo headUnitInfo = this.headunitInfo_;
            if (headUnitInfo != null && headUnitInfo != Common.HeadUnitInfo.getDefaultInstance()) {
                this.headunitInfo_ = (Common.HeadUnitInfo) Common.HeadUnitInfo.newBuilder(this.headunitInfo_).mergeFrom(value).buildPartial();
            } else {
                this.headunitInfo_ = value;
            }
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadunitInfo() {
            this.headunitInfo_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public boolean hasWifiProjectionProtocolInfo() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
        public WifiProjectionProtocolInfo getWifiProjectionProtocolInfo() {
            WifiProjectionProtocolInfo wifiProjectionProtocolInfo = this.wifiProjectionProtocolInfo_;
            return wifiProjectionProtocolInfo == null ? WifiProjectionProtocolInfo.getDefaultInstance() : wifiProjectionProtocolInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiProjectionProtocolInfo(WifiProjectionProtocolInfo value) {
            value.getClass();
            this.wifiProjectionProtocolInfo_ = value;
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeWifiProjectionProtocolInfo(WifiProjectionProtocolInfo value) {
            value.getClass();
            WifiProjectionProtocolInfo wifiProjectionProtocolInfo = this.wifiProjectionProtocolInfo_;
            if (wifiProjectionProtocolInfo != null && wifiProjectionProtocolInfo != WifiProjectionProtocolInfo.getDefaultInstance()) {
                this.wifiProjectionProtocolInfo_ = (WifiProjectionProtocolInfo) WifiProjectionProtocolInfo.newBuilder(this.wifiProjectionProtocolInfo_).mergeFrom(value).buildPartial();
            } else {
                this.wifiProjectionProtocolInfo_ = value;
            }
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWifiProjectionProtocolInfo() {
            this.wifiProjectionProtocolInfo_ = null;
            this.bitField0_ &= -17;
        }

        public static WifiVersionRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionRequest parseFrom(InputStream input) throws IOException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiVersionRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiVersionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiVersionRequest parseFrom(CodedInputStream input) throws IOException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiVersionRequest prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiVersionRequest, Builder> implements WifiVersionRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiVersionRequest.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public boolean hasMajorVersion() {
                return ((WifiVersionRequest) this.instance).hasMajorVersion();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public int getMajorVersion() {
                return ((WifiVersionRequest) this.instance).getMajorVersion();
            }

            public Builder setMajorVersion(int value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setMajorVersion(value);
                return this;
            }

            public Builder clearMajorVersion() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearMajorVersion();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public boolean hasMinorVersion() {
                return ((WifiVersionRequest) this.instance).hasMinorVersion();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public int getMinorVersion() {
                return ((WifiVersionRequest) this.instance).getMinorVersion();
            }

            public Builder setMinorVersion(int value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setMinorVersion(value);
                return this;
            }

            public Builder clearMinorVersion() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearMinorVersion();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public boolean hasSupportedWifiChannelType() {
                return ((WifiVersionRequest) this.instance).hasSupportedWifiChannelType();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public WifiChannelType getSupportedWifiChannelType() {
                return ((WifiVersionRequest) this.instance).getSupportedWifiChannelType();
            }

            public Builder setSupportedWifiChannelType(WifiChannelType value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setSupportedWifiChannelType(value);
                return this;
            }

            public Builder clearSupportedWifiChannelType() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearSupportedWifiChannelType();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public List<Integer> getSupportedWifiChannelsList() {
                return Collections.unmodifiableList(((WifiVersionRequest) this.instance).getSupportedWifiChannelsList());
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public int getSupportedWifiChannelsCount() {
                return ((WifiVersionRequest) this.instance).getSupportedWifiChannelsCount();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public int getSupportedWifiChannels(int index) {
                return ((WifiVersionRequest) this.instance).getSupportedWifiChannels(index);
            }

            public Builder setSupportedWifiChannels(int index, int value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setSupportedWifiChannels(index, value);
                return this;
            }

            public Builder addSupportedWifiChannels(int value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).addSupportedWifiChannels(value);
                return this;
            }

            public Builder addAllSupportedWifiChannels(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).addAllSupportedWifiChannels(values);
                return this;
            }

            public Builder clearSupportedWifiChannels() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearSupportedWifiChannels();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public boolean hasHeadunitInfo() {
                return ((WifiVersionRequest) this.instance).hasHeadunitInfo();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public Common.HeadUnitInfo getHeadunitInfo() {
                return ((WifiVersionRequest) this.instance).getHeadunitInfo();
            }

            public Builder setHeadunitInfo(Common.HeadUnitInfo value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setHeadunitInfo(value);
                return this;
            }

            public Builder setHeadunitInfo(Common.HeadUnitInfo.Builder builderForValue) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setHeadunitInfo((Common.HeadUnitInfo) builderForValue.build());
                return this;
            }

            public Builder mergeHeadunitInfo(Common.HeadUnitInfo value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).mergeHeadunitInfo(value);
                return this;
            }

            public Builder clearHeadunitInfo() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearHeadunitInfo();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public boolean hasWifiProjectionProtocolInfo() {
                return ((WifiVersionRequest) this.instance).hasWifiProjectionProtocolInfo();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionRequestOrBuilder
            public WifiProjectionProtocolInfo getWifiProjectionProtocolInfo() {
                return ((WifiVersionRequest) this.instance).getWifiProjectionProtocolInfo();
            }

            public Builder setWifiProjectionProtocolInfo(WifiProjectionProtocolInfo value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setWifiProjectionProtocolInfo(value);
                return this;
            }

            public Builder setWifiProjectionProtocolInfo(WifiProjectionProtocolInfo.Builder builderForValue) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).setWifiProjectionProtocolInfo((WifiProjectionProtocolInfo) builderForValue.build());
                return this;
            }

            public Builder mergeWifiProjectionProtocolInfo(WifiProjectionProtocolInfo value) {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).mergeWifiProjectionProtocolInfo(value);
                return this;
            }

            public Builder clearWifiProjectionProtocolInfo() {
                copyOnWrite();
                ((WifiVersionRequest) this.instance).clearWifiProjectionProtocolInfo();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiVersionRequest();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "majorVersion_", "minorVersion_", "supportedWifiChannelType_", WifiChannelType.internalGetVerifier(), "supportedWifiChannels_", "headunitInfo_", "wifiProjectionProtocolInfo_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003ဌ\u0002\u0004\u0016\u0005ဉ\u0003\u0006ဉ\u0004", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiVersionRequest> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiVersionRequest.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiVersionRequest defaultInstance = new WifiVersionRequest();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiVersionRequest.class, defaultInstance);
        }

        public static WifiVersionRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiVersionRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiVersionResponse extends GeneratedMessageLite<WifiVersionResponse, Builder> implements WifiVersionResponseOrBuilder {
        private static final WifiVersionResponse DEFAULT_INSTANCE;
        public static final int DEVICE_SERIAL_FIELD_NUMBER = 3;
        public static final int MAJOR_VERSION_FIELD_NUMBER = 1;
        public static final int MINOR_VERSION_FIELD_NUMBER = 2;
        private static volatile Parser<WifiVersionResponse> PARSER = null;
        public static final int PHONE_INFO_FIELD_NUMBER = 6;
        public static final int SELECTED_WIFI_CHANNEL_TYPE_FIELD_NUMBER = 5;
        public static final int VERSION_STATUS_FIELD_NUMBER = 4;
        private int bitField0_;
        private int majorVersion_;
        private int minorVersion_;
        private Common.PhoneInfo phoneInfo_;
        private int selectedWifiChannelType_;
        private String deviceSerial_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private int versionStatus_ = 1;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiVersionResponse() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasMajorVersion() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public int getMajorVersion() {
            return this.majorVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMajorVersion(int value) {
            this.bitField0_ |= 1;
            this.majorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMajorVersion() {
            this.bitField0_ &= -2;
            this.majorVersion_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasMinorVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public int getMinorVersion() {
            return this.minorVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinorVersion(int value) {
            this.bitField0_ |= 2;
            this.minorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinorVersion() {
            this.bitField0_ &= -3;
            this.minorVersion_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasDeviceSerial() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public String getDeviceSerial() {
            return this.deviceSerial_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public ByteString getDeviceSerialBytes() {
            return ByteString.copyFromUtf8(this.deviceSerial_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSerial(String value) {
            value.getClass();
            this.bitField0_ |= 4;
            this.deviceSerial_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceSerial() {
            this.bitField0_ &= -5;
            this.deviceSerial_ = getDefaultInstance().getDeviceSerial();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSerialBytes(ByteString value) {
            this.deviceSerial_ = value.toStringUtf8();
            this.bitField0_ |= 4;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasVersionStatus() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public MessageStatus getVersionStatus() {
            MessageStatus result = MessageStatus.forNumber(this.versionStatus_);
            return result == null ? MessageStatus.STATUS_UNSOLICITED_MESSAGE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionStatus(MessageStatus value) {
            this.versionStatus_ = value.getNumber();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersionStatus() {
            this.bitField0_ &= -9;
            this.versionStatus_ = 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasSelectedWifiChannelType() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public WifiChannelType getSelectedWifiChannelType() {
            WifiChannelType result = WifiChannelType.forNumber(this.selectedWifiChannelType_);
            return result == null ? WifiChannelType.CHANNELS_5GHZ_ONLY : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectedWifiChannelType(WifiChannelType value) {
            this.selectedWifiChannelType_ = value.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectedWifiChannelType() {
            this.bitField0_ &= -17;
            this.selectedWifiChannelType_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public boolean hasPhoneInfo() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
        public Common.PhoneInfo getPhoneInfo() {
            Common.PhoneInfo phoneInfo = this.phoneInfo_;
            return phoneInfo == null ? Common.PhoneInfo.getDefaultInstance() : phoneInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneInfo(Common.PhoneInfo value) {
            value.getClass();
            this.phoneInfo_ = value;
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePhoneInfo(Common.PhoneInfo value) {
            value.getClass();
            Common.PhoneInfo phoneInfo = this.phoneInfo_;
            if (phoneInfo != null && phoneInfo != Common.PhoneInfo.getDefaultInstance()) {
                this.phoneInfo_ = (Common.PhoneInfo) Common.PhoneInfo.newBuilder(this.phoneInfo_).mergeFrom(value).buildPartial();
            } else {
                this.phoneInfo_ = value;
            }
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPhoneInfo() {
            this.phoneInfo_ = null;
            this.bitField0_ &= -33;
        }

        public static WifiVersionResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiVersionResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiVersionResponse parseFrom(InputStream input) throws IOException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiVersionResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiVersionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiVersionResponse parseFrom(CodedInputStream input) throws IOException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiVersionResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiVersionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiVersionResponse prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiVersionResponse, Builder> implements WifiVersionResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiVersionResponse.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasMajorVersion() {
                return ((WifiVersionResponse) this.instance).hasMajorVersion();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public int getMajorVersion() {
                return ((WifiVersionResponse) this.instance).getMajorVersion();
            }

            public Builder setMajorVersion(int value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setMajorVersion(value);
                return this;
            }

            public Builder clearMajorVersion() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearMajorVersion();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasMinorVersion() {
                return ((WifiVersionResponse) this.instance).hasMinorVersion();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public int getMinorVersion() {
                return ((WifiVersionResponse) this.instance).getMinorVersion();
            }

            public Builder setMinorVersion(int value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setMinorVersion(value);
                return this;
            }

            public Builder clearMinorVersion() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearMinorVersion();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasDeviceSerial() {
                return ((WifiVersionResponse) this.instance).hasDeviceSerial();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public String getDeviceSerial() {
                return ((WifiVersionResponse) this.instance).getDeviceSerial();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public ByteString getDeviceSerialBytes() {
                return ((WifiVersionResponse) this.instance).getDeviceSerialBytes();
            }

            public Builder setDeviceSerial(String value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setDeviceSerial(value);
                return this;
            }

            public Builder clearDeviceSerial() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearDeviceSerial();
                return this;
            }

            public Builder setDeviceSerialBytes(ByteString value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setDeviceSerialBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasVersionStatus() {
                return ((WifiVersionResponse) this.instance).hasVersionStatus();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public MessageStatus getVersionStatus() {
                return ((WifiVersionResponse) this.instance).getVersionStatus();
            }

            public Builder setVersionStatus(MessageStatus value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setVersionStatus(value);
                return this;
            }

            public Builder clearVersionStatus() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearVersionStatus();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasSelectedWifiChannelType() {
                return ((WifiVersionResponse) this.instance).hasSelectedWifiChannelType();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public WifiChannelType getSelectedWifiChannelType() {
                return ((WifiVersionResponse) this.instance).getSelectedWifiChannelType();
            }

            public Builder setSelectedWifiChannelType(WifiChannelType value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setSelectedWifiChannelType(value);
                return this;
            }

            public Builder clearSelectedWifiChannelType() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearSelectedWifiChannelType();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public boolean hasPhoneInfo() {
                return ((WifiVersionResponse) this.instance).hasPhoneInfo();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiVersionResponseOrBuilder
            public Common.PhoneInfo getPhoneInfo() {
                return ((WifiVersionResponse) this.instance).getPhoneInfo();
            }

            public Builder setPhoneInfo(Common.PhoneInfo value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setPhoneInfo(value);
                return this;
            }

            public Builder setPhoneInfo(Common.PhoneInfo.Builder builderForValue) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).setPhoneInfo((Common.PhoneInfo) builderForValue.build());
                return this;
            }

            public Builder mergePhoneInfo(Common.PhoneInfo value) {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).mergePhoneInfo(value);
                return this;
            }

            public Builder clearPhoneInfo() {
                copyOnWrite();
                ((WifiVersionResponse) this.instance).clearPhoneInfo();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiVersionResponse();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "majorVersion_", "minorVersion_", "deviceSerial_", "versionStatus_", MessageStatus.internalGetVerifier(), "selectedWifiChannelType_", WifiChannelType.internalGetVerifier(), "phoneInfo_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဈ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဉ\u0005", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiVersionResponse> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiVersionResponse.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiVersionResponse defaultInstance = new WifiVersionResponse();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiVersionResponse.class, defaultInstance);
        }

        public static WifiVersionResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiVersionResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiStartRequest extends GeneratedMessageLite<WifiStartRequest, Builder> implements WifiStartRequestOrBuilder {
        private static final WifiStartRequest DEFAULT_INSTANCE;
        public static final int IP_ADDRESS_FIELD_NUMBER = 1;
        private static volatile Parser<WifiStartRequest> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int START_REQUEST_REASON_FIELD_NUMBER = 3;
        private int bitField0_;
        private String ipAddress_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private int port_;
        private int startRequestReason_;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiStartRequest() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public boolean hasIpAddress() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public String getIpAddress() {
            return this.ipAddress_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public ByteString getIpAddressBytes() {
            return ByteString.copyFromUtf8(this.ipAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddress(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.ipAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIpAddress() {
            this.bitField0_ &= -2;
            this.ipAddress_ = getDefaultInstance().getIpAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddressBytes(ByteString value) {
            this.ipAddress_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public boolean hasPort() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public int getPort() {
            return this.port_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPort(int value) {
            this.bitField0_ |= 2;
            this.port_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPort() {
            this.bitField0_ &= -3;
            this.port_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public boolean hasStartRequestReason() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
        public StartRequestReason getStartRequestReason() {
            StartRequestReason result = StartRequestReason.forNumber(this.startRequestReason_);
            return result == null ? StartRequestReason.USER_REQUEST : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartRequestReason(StartRequestReason value) {
            this.startRequestReason_ = value.getNumber();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartRequestReason() {
            this.bitField0_ &= -5;
            this.startRequestReason_ = 0;
        }

        public static WifiStartRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartRequest parseFrom(InputStream input) throws IOException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiStartRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiStartRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiStartRequest parseFrom(CodedInputStream input) throws IOException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiStartRequest prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiStartRequest, Builder> implements WifiStartRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiStartRequest.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public boolean hasIpAddress() {
                return ((WifiStartRequest) this.instance).hasIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public String getIpAddress() {
                return ((WifiStartRequest) this.instance).getIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public ByteString getIpAddressBytes() {
                return ((WifiStartRequest) this.instance).getIpAddressBytes();
            }

            public Builder setIpAddress(String value) {
                copyOnWrite();
                ((WifiStartRequest) this.instance).setIpAddress(value);
                return this;
            }

            public Builder clearIpAddress() {
                copyOnWrite();
                ((WifiStartRequest) this.instance).clearIpAddress();
                return this;
            }

            public Builder setIpAddressBytes(ByteString value) {
                copyOnWrite();
                ((WifiStartRequest) this.instance).setIpAddressBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public boolean hasPort() {
                return ((WifiStartRequest) this.instance).hasPort();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public int getPort() {
                return ((WifiStartRequest) this.instance).getPort();
            }

            public Builder setPort(int value) {
                copyOnWrite();
                ((WifiStartRequest) this.instance).setPort(value);
                return this;
            }

            public Builder clearPort() {
                copyOnWrite();
                ((WifiStartRequest) this.instance).clearPort();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public boolean hasStartRequestReason() {
                return ((WifiStartRequest) this.instance).hasStartRequestReason();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartRequestOrBuilder
            public StartRequestReason getStartRequestReason() {
                return ((WifiStartRequest) this.instance).getStartRequestReason();
            }

            public Builder setStartRequestReason(StartRequestReason value) {
                copyOnWrite();
                ((WifiStartRequest) this.instance).setStartRequestReason(value);
                return this;
            }

            public Builder clearStartRequestReason() {
                copyOnWrite();
                ((WifiStartRequest) this.instance).clearStartRequestReason();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiStartRequest();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "ipAddress_", "port_", "startRequestReason_", StartRequestReason.internalGetVerifier()};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003ဌ\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiStartRequest> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiStartRequest.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiStartRequest defaultInstance = new WifiStartRequest();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiStartRequest.class, defaultInstance);
        }

        public static WifiStartRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiStartRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiStartResponse extends GeneratedMessageLite<WifiStartResponse, Builder> implements WifiStartResponseOrBuilder {
        private static final WifiStartResponse DEFAULT_INSTANCE;
        public static final int IP_ADDRESS_FIELD_NUMBER = 1;
        private static volatile Parser<WifiStartResponse> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 3;
        private int bitField0_;
        private int port_;
        private String ipAddress_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private int status_ = 1;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiStartResponse() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public boolean hasIpAddress() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public String getIpAddress() {
            return this.ipAddress_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public ByteString getIpAddressBytes() {
            return ByteString.copyFromUtf8(this.ipAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddress(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.ipAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIpAddress() {
            this.bitField0_ &= -2;
            this.ipAddress_ = getDefaultInstance().getIpAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpAddressBytes(ByteString value) {
            this.ipAddress_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public boolean hasPort() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public int getPort() {
            return this.port_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPort(int value) {
            this.bitField0_ |= 2;
            this.port_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPort() {
            this.bitField0_ &= -3;
            this.port_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
        public MessageStatus getStatus() {
            MessageStatus result = MessageStatus.forNumber(this.status_);
            return result == null ? MessageStatus.STATUS_UNSOLICITED_MESSAGE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(MessageStatus value) {
            this.status_ = value.getNumber();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -5;
            this.status_ = 1;
        }

        public static WifiStartResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiStartResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiStartResponse parseFrom(InputStream input) throws IOException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiStartResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiStartResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiStartResponse parseFrom(CodedInputStream input) throws IOException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiStartResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiStartResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiStartResponse prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiStartResponse, Builder> implements WifiStartResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiStartResponse.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public boolean hasIpAddress() {
                return ((WifiStartResponse) this.instance).hasIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public String getIpAddress() {
                return ((WifiStartResponse) this.instance).getIpAddress();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public ByteString getIpAddressBytes() {
                return ((WifiStartResponse) this.instance).getIpAddressBytes();
            }

            public Builder setIpAddress(String value) {
                copyOnWrite();
                ((WifiStartResponse) this.instance).setIpAddress(value);
                return this;
            }

            public Builder clearIpAddress() {
                copyOnWrite();
                ((WifiStartResponse) this.instance).clearIpAddress();
                return this;
            }

            public Builder setIpAddressBytes(ByteString value) {
                copyOnWrite();
                ((WifiStartResponse) this.instance).setIpAddressBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public boolean hasPort() {
                return ((WifiStartResponse) this.instance).hasPort();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public int getPort() {
                return ((WifiStartResponse) this.instance).getPort();
            }

            public Builder setPort(int value) {
                copyOnWrite();
                ((WifiStartResponse) this.instance).setPort(value);
                return this;
            }

            public Builder clearPort() {
                copyOnWrite();
                ((WifiStartResponse) this.instance).clearPort();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public boolean hasStatus() {
                return ((WifiStartResponse) this.instance).hasStatus();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiStartResponseOrBuilder
            public MessageStatus getStatus() {
                return ((WifiStartResponse) this.instance).getStatus();
            }

            public Builder setStatus(MessageStatus value) {
                copyOnWrite();
                ((WifiStartResponse) this.instance).setStatus(value);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((WifiStartResponse) this.instance).clearStatus();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiStartResponse();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "ipAddress_", "port_", "status_", MessageStatus.internalGetVerifier()};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003ဌ\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiStartResponse> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiStartResponse.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiStartResponse defaultInstance = new WifiStartResponse();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiStartResponse.class, defaultInstance);
        }

        public static WifiStartResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiStartResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiInfoRequest extends GeneratedMessageLite<WifiInfoRequest, Builder> implements WifiInfoRequestOrBuilder {
        private static final WifiInfoRequest DEFAULT_INSTANCE;
        private static volatile Parser<WifiInfoRequest> PARSER;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiInfoRequest() {
        }

        public static WifiInfoRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoRequest parseFrom(InputStream input) throws IOException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiInfoRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiInfoRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiInfoRequest parseFrom(CodedInputStream input) throws IOException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiInfoRequest prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiInfoRequest, Builder> implements WifiInfoRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiInfoRequest.DEFAULT_INSTANCE);
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiInfoRequest();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiInfoRequest> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiInfoRequest.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiInfoRequest defaultInstance = new WifiInfoRequest();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiInfoRequest.class, defaultInstance);
        }

        public static WifiInfoRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiInfoRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiInfoResponse extends GeneratedMessageLite<WifiInfoResponse, Builder> implements WifiInfoResponseOrBuilder {
        public static final int ACCESS_POINT_TYPE_FIELD_NUMBER = 5;
        private static final WifiInfoResponse DEFAULT_INSTANCE;
        private static volatile Parser<WifiInfoResponse> PARSER = null;
        public static final int WIFI_BSSID_FIELD_NUMBER = 3;
        public static final int WIFI_PASSWORD_FIELD_NUMBER = 2;
        public static final int WIFI_SECURITY_MODE_FIELD_NUMBER = 4;
        public static final int WIFI_SSID_FIELD_NUMBER = 1;
        private int accessPointType_;
        private int bitField0_;
        private int wifiSecurityMode_;
        private String wifiSsid_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String wifiPassword_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String wifiBssid_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiInfoResponse() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public boolean hasWifiSsid() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public String getWifiSsid() {
            return this.wifiSsid_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public ByteString getWifiSsidBytes() {
            return ByteString.copyFromUtf8(this.wifiSsid_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiSsid(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.wifiSsid_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWifiSsid() {
            this.bitField0_ &= -2;
            this.wifiSsid_ = getDefaultInstance().getWifiSsid();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiSsidBytes(ByteString value) {
            this.wifiSsid_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public boolean hasWifiPassword() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public String getWifiPassword() {
            return this.wifiPassword_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public ByteString getWifiPasswordBytes() {
            return ByteString.copyFromUtf8(this.wifiPassword_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiPassword(String value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.wifiPassword_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWifiPassword() {
            this.bitField0_ &= -3;
            this.wifiPassword_ = getDefaultInstance().getWifiPassword();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiPasswordBytes(ByteString value) {
            this.wifiPassword_ = value.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public boolean hasWifiBssid() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public String getWifiBssid() {
            return this.wifiBssid_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public ByteString getWifiBssidBytes() {
            return ByteString.copyFromUtf8(this.wifiBssid_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiBssid(String value) {
            value.getClass();
            this.bitField0_ |= 4;
            this.wifiBssid_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWifiBssid() {
            this.bitField0_ &= -5;
            this.wifiBssid_ = getDefaultInstance().getWifiBssid();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiBssidBytes(ByteString value) {
            this.wifiBssid_ = value.toStringUtf8();
            this.bitField0_ |= 4;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public boolean hasWifiSecurityMode() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public WifiSecurityMode getWifiSecurityMode() {
            WifiSecurityMode result = WifiSecurityMode.forNumber(this.wifiSecurityMode_);
            return result == null ? WifiSecurityMode.UNKNOWN_SECURITY_MODE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWifiSecurityMode(WifiSecurityMode value) {
            this.wifiSecurityMode_ = value.getNumber();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWifiSecurityMode() {
            this.bitField0_ &= -9;
            this.wifiSecurityMode_ = 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public boolean hasAccessPointType() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
        public AccessPointType getAccessPointType() {
            AccessPointType result = AccessPointType.forNumber(this.accessPointType_);
            return result == null ? AccessPointType.STATIC : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessPointType(AccessPointType value) {
            this.accessPointType_ = value.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessPointType() {
            this.bitField0_ &= -17;
            this.accessPointType_ = 0;
        }

        public static WifiInfoResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiInfoResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiInfoResponse parseFrom(InputStream input) throws IOException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiInfoResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiInfoResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiInfoResponse parseFrom(CodedInputStream input) throws IOException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiInfoResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiInfoResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiInfoResponse prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiInfoResponse, Builder> implements WifiInfoResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiInfoResponse.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public boolean hasWifiSsid() {
                return ((WifiInfoResponse) this.instance).hasWifiSsid();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public String getWifiSsid() {
                return ((WifiInfoResponse) this.instance).getWifiSsid();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public ByteString getWifiSsidBytes() {
                return ((WifiInfoResponse) this.instance).getWifiSsidBytes();
            }

            public Builder setWifiSsid(String value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiSsid(value);
                return this;
            }

            public Builder clearWifiSsid() {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).clearWifiSsid();
                return this;
            }

            public Builder setWifiSsidBytes(ByteString value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiSsidBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public boolean hasWifiPassword() {
                return ((WifiInfoResponse) this.instance).hasWifiPassword();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public String getWifiPassword() {
                return ((WifiInfoResponse) this.instance).getWifiPassword();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public ByteString getWifiPasswordBytes() {
                return ((WifiInfoResponse) this.instance).getWifiPasswordBytes();
            }

            public Builder setWifiPassword(String value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiPassword(value);
                return this;
            }

            public Builder clearWifiPassword() {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).clearWifiPassword();
                return this;
            }

            public Builder setWifiPasswordBytes(ByteString value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiPasswordBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public boolean hasWifiBssid() {
                return ((WifiInfoResponse) this.instance).hasWifiBssid();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public String getWifiBssid() {
                return ((WifiInfoResponse) this.instance).getWifiBssid();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public ByteString getWifiBssidBytes() {
                return ((WifiInfoResponse) this.instance).getWifiBssidBytes();
            }

            public Builder setWifiBssid(String value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiBssid(value);
                return this;
            }

            public Builder clearWifiBssid() {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).clearWifiBssid();
                return this;
            }

            public Builder setWifiBssidBytes(ByteString value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiBssidBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public boolean hasWifiSecurityMode() {
                return ((WifiInfoResponse) this.instance).hasWifiSecurityMode();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public WifiSecurityMode getWifiSecurityMode() {
                return ((WifiInfoResponse) this.instance).getWifiSecurityMode();
            }

            public Builder setWifiSecurityMode(WifiSecurityMode value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setWifiSecurityMode(value);
                return this;
            }

            public Builder clearWifiSecurityMode() {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).clearWifiSecurityMode();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public boolean hasAccessPointType() {
                return ((WifiInfoResponse) this.instance).hasAccessPointType();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiInfoResponseOrBuilder
            public AccessPointType getAccessPointType() {
                return ((WifiInfoResponse) this.instance).getAccessPointType();
            }

            public Builder setAccessPointType(AccessPointType value) {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).setAccessPointType(value);
                return this;
            }

            public Builder clearAccessPointType() {
                copyOnWrite();
                ((WifiInfoResponse) this.instance).clearAccessPointType();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiInfoResponse();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "wifiSsid_", "wifiPassword_", "wifiBssid_", "wifiSecurityMode_", WifiSecurityMode.internalGetVerifier(), "accessPointType_", AccessPointType.internalGetVerifier()};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဌ\u0003\u0005ဌ\u0004", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiInfoResponse> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiInfoResponse.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiInfoResponse defaultInstance = new WifiInfoResponse();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiInfoResponse.class, defaultInstance);
        }

        public static WifiInfoResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiInfoResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiConnectStatus extends GeneratedMessageLite<WifiConnectStatus, Builder> implements WifiConnectStatusOrBuilder {
        public static final int CONNECT_STATUS_FIELD_NUMBER = 1;
        private static final WifiConnectStatus DEFAULT_INSTANCE;
        public static final int ERROR_MESSAGE_HINT_FIELD_NUMBER = 2;
        private static volatile Parser<WifiConnectStatus> PARSER;
        private int bitField0_;
        private int connectStatus_ = 1;
        private String errorMessageHint_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiConnectStatus() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
        public boolean hasConnectStatus() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
        public MessageStatus getConnectStatus() {
            MessageStatus result = MessageStatus.forNumber(this.connectStatus_);
            return result == null ? MessageStatus.STATUS_UNSOLICITED_MESSAGE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectStatus(MessageStatus value) {
            this.connectStatus_ = value.getNumber();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectStatus() {
            this.bitField0_ &= -2;
            this.connectStatus_ = 1;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
        public boolean hasErrorMessageHint() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
        public String getErrorMessageHint() {
            return this.errorMessageHint_;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
        public ByteString getErrorMessageHintBytes() {
            return ByteString.copyFromUtf8(this.errorMessageHint_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorMessageHint(String value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.errorMessageHint_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorMessageHint() {
            this.bitField0_ &= -3;
            this.errorMessageHint_ = getDefaultInstance().getErrorMessageHint();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorMessageHintBytes(ByteString value) {
            this.errorMessageHint_ = value.toStringUtf8();
            this.bitField0_ |= 2;
        }

        public static WifiConnectStatus parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiConnectStatus parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiConnectStatus parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiConnectStatus parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiConnectStatus parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiConnectStatus parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiConnectStatus parseFrom(InputStream input) throws IOException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiConnectStatus parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiConnectStatus parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiConnectStatus) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiConnectStatus parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiConnectStatus) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiConnectStatus parseFrom(CodedInputStream input) throws IOException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiConnectStatus parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiConnectStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiConnectStatus prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiConnectStatus, Builder> implements WifiConnectStatusOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiConnectStatus.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
            public boolean hasConnectStatus() {
                return ((WifiConnectStatus) this.instance).hasConnectStatus();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
            public MessageStatus getConnectStatus() {
                return ((WifiConnectStatus) this.instance).getConnectStatus();
            }

            public Builder setConnectStatus(MessageStatus value) {
                copyOnWrite();
                ((WifiConnectStatus) this.instance).setConnectStatus(value);
                return this;
            }

            public Builder clearConnectStatus() {
                copyOnWrite();
                ((WifiConnectStatus) this.instance).clearConnectStatus();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
            public boolean hasErrorMessageHint() {
                return ((WifiConnectStatus) this.instance).hasErrorMessageHint();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
            public String getErrorMessageHint() {
                return ((WifiConnectStatus) this.instance).getErrorMessageHint();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiConnectStatusOrBuilder
            public ByteString getErrorMessageHintBytes() {
                return ((WifiConnectStatus) this.instance).getErrorMessageHintBytes();
            }

            public Builder setErrorMessageHint(String value) {
                copyOnWrite();
                ((WifiConnectStatus) this.instance).setErrorMessageHint(value);
                return this;
            }

            public Builder clearErrorMessageHint() {
                copyOnWrite();
                ((WifiConnectStatus) this.instance).clearErrorMessageHint();
                return this;
            }

            public Builder setErrorMessageHintBytes(ByteString value) {
                copyOnWrite();
                ((WifiConnectStatus) this.instance).setErrorMessageHintBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiConnectStatus();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "connectStatus_", MessageStatus.internalGetVerifier(), "errorMessageHint_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiConnectStatus> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiConnectStatus.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiConnectStatus defaultInstance = new WifiConnectStatus();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiConnectStatus.class, defaultInstance);
        }

        public static WifiConnectStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiConnectStatus> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiPingRequest extends GeneratedMessageLite<WifiPingRequest, Builder> implements WifiPingRequestOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        private static final WifiPingRequest DEFAULT_INSTANCE;
        private static volatile Parser<WifiPingRequest> PARSER = null;
        public static final int TIMESTAMP_MS_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString data_ = ByteString.EMPTY;
        private long timestampMs_;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiPingRequest() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
        public boolean hasTimestampMs() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
        public long getTimestampMs() {
            return this.timestampMs_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampMs(long value) {
            this.bitField0_ |= 1;
            this.timestampMs_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampMs() {
            this.bitField0_ &= -2;
            this.timestampMs_ = 0L;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
        public boolean hasData() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setData(ByteString value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.data_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearData() {
            this.bitField0_ &= -3;
            this.data_ = getDefaultInstance().getData();
        }

        public static WifiPingRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingRequest parseFrom(InputStream input) throws IOException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiPingRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiPingRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiPingRequest parseFrom(CodedInputStream input) throws IOException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiPingRequest prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiPingRequest, Builder> implements WifiPingRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiPingRequest.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
            public boolean hasTimestampMs() {
                return ((WifiPingRequest) this.instance).hasTimestampMs();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
            public long getTimestampMs() {
                return ((WifiPingRequest) this.instance).getTimestampMs();
            }

            public Builder setTimestampMs(long value) {
                copyOnWrite();
                ((WifiPingRequest) this.instance).setTimestampMs(value);
                return this;
            }

            public Builder clearTimestampMs() {
                copyOnWrite();
                ((WifiPingRequest) this.instance).clearTimestampMs();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
            public boolean hasData() {
                return ((WifiPingRequest) this.instance).hasData();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingRequestOrBuilder
            public ByteString getData() {
                return ((WifiPingRequest) this.instance).getData();
            }

            public Builder setData(ByteString value) {
                copyOnWrite();
                ((WifiPingRequest) this.instance).setData(value);
                return this;
            }

            public Builder clearData() {
                copyOnWrite();
                ((WifiPingRequest) this.instance).clearData();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiPingRequest();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "timestampMs_", "data_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ည\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiPingRequest> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiPingRequest.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiPingRequest defaultInstance = new WifiPingRequest();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiPingRequest.class, defaultInstance);
        }

        public static WifiPingRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiPingRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WifiPingResponse extends GeneratedMessageLite<WifiPingResponse, Builder> implements WifiPingResponseOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        private static final WifiPingResponse DEFAULT_INSTANCE;
        private static volatile Parser<WifiPingResponse> PARSER = null;
        public static final int TIMESTAMP_MS_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString data_ = ByteString.EMPTY;
        private long timestampMs_;

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return super.newBuilderForType();
        }

        @Override // com.google.protobuf.GeneratedMessageLite, com.google.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return super.toBuilder();
        }

        private WifiPingResponse() {
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
        public boolean hasTimestampMs() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
        public long getTimestampMs() {
            return this.timestampMs_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampMs(long value) {
            this.bitField0_ |= 1;
            this.timestampMs_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampMs() {
            this.bitField0_ &= -2;
            this.timestampMs_ = 0L;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
        public boolean hasData() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setData(ByteString value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.data_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearData() {
            this.bitField0_ &= -3;
            this.data_ = getDefaultInstance().getData();
        }

        public static WifiPingResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WifiPingResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WifiPingResponse parseFrom(InputStream input) throws IOException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiPingResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (WifiPingResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WifiPingResponse parseFrom(CodedInputStream input) throws IOException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WifiPingResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WifiPingResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WifiPingResponse prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WifiPingResponse, Builder> implements WifiPingResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return super.build();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite buildPartial() {
                return super.buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
                return super.clear();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ MessageLite.Builder mo4clone() {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo4clone() throws CloneNotSupportedException {
                return super.mo4clone();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder
            protected /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite message) {
                return super.internalMergeFrom((GeneratedMessageLite) message);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite other) {
                return super.mergeFrom(other);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input) throws IOException {
                return super.mergeFrom(input);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return super.mergeFrom(input, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
                return super.mergeFrom(data);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length);
            }

            @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(input, offset, length, extensionRegistry);
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return super.mergeFrom(data, extensionRegistry);
            }

            private Builder() {
                super(WifiPingResponse.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
            public boolean hasTimestampMs() {
                return ((WifiPingResponse) this.instance).hasTimestampMs();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
            public long getTimestampMs() {
                return ((WifiPingResponse) this.instance).getTimestampMs();
            }

            public Builder setTimestampMs(long value) {
                copyOnWrite();
                ((WifiPingResponse) this.instance).setTimestampMs(value);
                return this;
            }

            public Builder clearTimestampMs() {
                copyOnWrite();
                ((WifiPingResponse) this.instance).clearTimestampMs();
                return this;
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
            public boolean hasData() {
                return ((WifiPingResponse) this.instance).hasData();
            }

            @Override // com.google.android.projection.proto.WifiDiscoveryProtos.WifiPingResponseOrBuilder
            public ByteString getData() {
                return ((WifiPingResponse) this.instance).getData();
            }

            public Builder setData(ByteString value) {
                copyOnWrite();
                ((WifiPingResponse) this.instance).setData(value);
                return this;
            }

            public Builder clearData() {
                copyOnWrite();
                ((WifiPingResponse) this.instance).clearData();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WifiPingResponse();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "timestampMs_", "data_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ည\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WifiPingResponse> parser = PARSER;
                    if (parser == null) {
                        synchronized (WifiPingResponse.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WifiPingResponse defaultInstance = new WifiPingResponse();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WifiPingResponse.class, defaultInstance);
        }

        public static WifiPingResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WifiPingResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

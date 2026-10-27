package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class SecureGcmProto {

    public interface GcmDeviceInfoOrBuilder extends MessageLiteOrBuilder {
        long getAndroidDeviceId();

        ByteString getApnRegistrationId();

        boolean getArcPlusPlus();

        boolean getAutoUnlockScreenlockEnabled();

        boolean getAutoUnlockScreenlockSupported();

        boolean getBleRadioSupported();

        String getBluetoothMacAddress();

        ByteString getBluetoothMacAddressBytes();

        boolean getBluetoothRadioEnabled();

        boolean getBluetoothRadioSupported();

        long getCounter();

        int getDeviceAuthzenVersion();

        int getDeviceDisplayDiagonalMils();

        String getDeviceManufacturer();

        ByteString getDeviceManufacturerBytes();

        ByteString getDeviceMasterKeyHash();

        String getDeviceModel();

        ByteString getDeviceModelBytes();

        String getDeviceOsCodename();

        ByteString getDeviceOsCodenameBytes();

        String getDeviceOsRelease();

        ByteString getDeviceOsReleaseBytes();

        String getDeviceOsVersion();

        ByteString getDeviceOsVersionBytes();

        long getDeviceOsVersionCode();

        String getDeviceSoftwarePackage();

        ByteString getDeviceSoftwarePackageBytes();

        String getDeviceSoftwareVersion();

        ByteString getDeviceSoftwareVersionBytes();

        long getDeviceSoftwareVersionCode();

        DeviceType getDeviceType();

        SoftwareFeature getEnabledSoftwareFeatures(int i);

        int getEnabledSoftwareFeaturesCount();

        List<SoftwareFeature> getEnabledSoftwareFeaturesList();

        ByteString getEnrollmentSessionId();

        ByteString getGcmRegistrationId();

        boolean getIsScreenlockStateFlaky();

        ByteString getKeyHandle();

        String getLocale();

        ByteString getLocaleBytes();

        ByteString getLongDeviceId();

        boolean getMobileDataSupported();

        boolean getNotificationEnabled();

        String getOauthToken();

        ByteString getOauthTokenBytes();

        boolean getPixelExperience();

        SoftwareFeature getSupportedSoftwareFeatures(int i);

        int getSupportedSoftwareFeaturesCount();

        List<SoftwareFeature> getSupportedSoftwareFeaturesList();

        boolean getTetheringSupported();

        ByteString getUserPublicKey();

        boolean getUsingSecureScreenlock();

        boolean hasAndroidDeviceId();

        boolean hasApnRegistrationId();

        boolean hasArcPlusPlus();

        boolean hasAutoUnlockScreenlockEnabled();

        boolean hasAutoUnlockScreenlockSupported();

        boolean hasBleRadioSupported();

        boolean hasBluetoothMacAddress();

        boolean hasBluetoothRadioEnabled();

        boolean hasBluetoothRadioSupported();

        boolean hasCounter();

        boolean hasDeviceAuthzenVersion();

        boolean hasDeviceDisplayDiagonalMils();

        boolean hasDeviceManufacturer();

        boolean hasDeviceMasterKeyHash();

        boolean hasDeviceModel();

        boolean hasDeviceOsCodename();

        boolean hasDeviceOsRelease();

        boolean hasDeviceOsVersion();

        boolean hasDeviceOsVersionCode();

        boolean hasDeviceSoftwarePackage();

        boolean hasDeviceSoftwareVersion();

        boolean hasDeviceSoftwareVersionCode();

        boolean hasDeviceType();

        boolean hasEnrollmentSessionId();

        boolean hasGcmRegistrationId();

        boolean hasIsScreenlockStateFlaky();

        boolean hasKeyHandle();

        boolean hasLocale();

        boolean hasLongDeviceId();

        boolean hasMobileDataSupported();

        boolean hasNotificationEnabled();

        boolean hasOauthToken();

        boolean hasPixelExperience();

        boolean hasTetheringSupported();

        boolean hasUserPublicKey();

        boolean hasUsingSecureScreenlock();
    }

    public interface GcmMetadataOrBuilder extends MessageLiteOrBuilder {
        Type getType();

        int getVersion();

        boolean hasType();

        boolean hasVersion();
    }

    public interface LoginNotificationInfoOrBuilder extends MessageLiteOrBuilder {
        long getCreationTime();

        String getEmail();

        ByteString getEmailBytes();

        String getEventType();

        ByteString getEventTypeBytes();

        String getHost();

        ByteString getHostBytes();

        String getSource();

        ByteString getSourceBytes();

        boolean hasCreationTime();

        boolean hasEmail();

        boolean hasEventType();

        boolean hasHost();

        boolean hasSource();
    }

    public interface TickleOrBuilder extends MessageLiteOrBuilder {
        long getExpiryTime();

        boolean hasExpiryTime();
    }

    private SecureGcmProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum AppleDeviceDiagonalMils implements Internal.EnumLite {
        APPLE_PHONE(APPLE_PHONE_VALUE),
        APPLE_PAD(APPLE_PAD_VALUE);

        public static final int APPLE_PAD_VALUE = 7900;
        public static final int APPLE_PHONE_VALUE = 4000;
        private static final Internal.EnumLiteMap<AppleDeviceDiagonalMils> internalValueMap = new Internal.EnumLiteMap<AppleDeviceDiagonalMils>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.AppleDeviceDiagonalMils.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AppleDeviceDiagonalMils findValueByNumber(int number) {
                return AppleDeviceDiagonalMils.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AppleDeviceDiagonalMils valueOf(int value) {
            return forNumber(value);
        }

        public static AppleDeviceDiagonalMils forNumber(int value) {
            if (value == 4000) {
                return APPLE_PHONE;
            }
            if (value == 7900) {
                return APPLE_PAD;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AppleDeviceDiagonalMils> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return AppleDeviceDiagonalMilsVerifier.INSTANCE;
        }

        private static final class AppleDeviceDiagonalMilsVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new AppleDeviceDiagonalMilsVerifier();

            private AppleDeviceDiagonalMilsVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return AppleDeviceDiagonalMils.forNumber(number) != null;
            }
        }

        AppleDeviceDiagonalMils(int value) {
            this.value = value;
        }
    }

    public enum DeviceType implements Internal.EnumLite {
        UNKNOWN(0),
        ANDROID(1),
        CHROME(2),
        IOS(3),
        BROWSER(4),
        OSX(5);

        public static final int ANDROID_VALUE = 1;
        public static final int BROWSER_VALUE = 4;
        public static final int CHROME_VALUE = 2;
        public static final int IOS_VALUE = 3;
        public static final int OSX_VALUE = 5;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<DeviceType> internalValueMap = new Internal.EnumLiteMap<DeviceType>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.DeviceType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DeviceType findValueByNumber(int number) {
                return DeviceType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DeviceType valueOf(int value) {
            return forNumber(value);
        }

        public static DeviceType forNumber(int value) {
            if (value == 0) {
                return UNKNOWN;
            }
            if (value == 1) {
                return ANDROID;
            }
            if (value == 2) {
                return CHROME;
            }
            if (value == 3) {
                return IOS;
            }
            if (value == 4) {
                return BROWSER;
            }
            if (value == 5) {
                return OSX;
            }
            return null;
        }

        public static Internal.EnumLiteMap<DeviceType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DeviceTypeVerifier.INSTANCE;
        }

        private static final class DeviceTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new DeviceTypeVerifier();

            private DeviceTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return DeviceType.forNumber(number) != null;
            }
        }

        DeviceType(int value) {
            this.value = value;
        }
    }

    public enum SoftwareFeature implements Internal.EnumLite {
        UNKNOWN_FEATURE(0),
        BETTER_TOGETHER_HOST(1),
        BETTER_TOGETHER_CLIENT(2),
        EASY_UNLOCK_HOST(3),
        EASY_UNLOCK_CLIENT(4),
        MAGIC_TETHER_HOST(5),
        MAGIC_TETHER_CLIENT(6),
        SMS_CONNECT_HOST(7),
        SMS_CONNECT_CLIENT(8);

        public static final int BETTER_TOGETHER_CLIENT_VALUE = 2;
        public static final int BETTER_TOGETHER_HOST_VALUE = 1;
        public static final int EASY_UNLOCK_CLIENT_VALUE = 4;
        public static final int EASY_UNLOCK_HOST_VALUE = 3;
        public static final int MAGIC_TETHER_CLIENT_VALUE = 6;
        public static final int MAGIC_TETHER_HOST_VALUE = 5;
        public static final int SMS_CONNECT_CLIENT_VALUE = 8;
        public static final int SMS_CONNECT_HOST_VALUE = 7;
        public static final int UNKNOWN_FEATURE_VALUE = 0;
        private static final Internal.EnumLiteMap<SoftwareFeature> internalValueMap = new Internal.EnumLiteMap<SoftwareFeature>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.SoftwareFeature.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SoftwareFeature findValueByNumber(int number) {
                return SoftwareFeature.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SoftwareFeature valueOf(int value) {
            return forNumber(value);
        }

        public static SoftwareFeature forNumber(int value) {
            switch (value) {
                case 0:
                    return UNKNOWN_FEATURE;
                case 1:
                    return BETTER_TOGETHER_HOST;
                case 2:
                    return BETTER_TOGETHER_CLIENT;
                case 3:
                    return EASY_UNLOCK_HOST;
                case 4:
                    return EASY_UNLOCK_CLIENT;
                case 5:
                    return MAGIC_TETHER_HOST;
                case 6:
                    return MAGIC_TETHER_CLIENT;
                case 7:
                    return SMS_CONNECT_HOST;
                case 8:
                    return SMS_CONNECT_CLIENT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<SoftwareFeature> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SoftwareFeatureVerifier.INSTANCE;
        }

        private static final class SoftwareFeatureVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SoftwareFeatureVerifier();

            private SoftwareFeatureVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return SoftwareFeature.forNumber(number) != null;
            }
        }

        SoftwareFeature(int value) {
            this.value = value;
        }
    }

    public enum InvocationReason implements Internal.EnumLite {
        REASON_UNKNOWN(0),
        REASON_INITIALIZATION(1),
        REASON_PERIODIC(2),
        REASON_SLOW_PERIODIC(3),
        REASON_FAST_PERIODIC(4),
        REASON_EXPIRATION(5),
        REASON_FAILURE_RECOVERY(6),
        REASON_NEW_ACCOUNT(7),
        REASON_CHANGED_ACCOUNT(8),
        REASON_FEATURE_TOGGLED(9),
        REASON_SERVER_INITIATED(10),
        REASON_ADDRESS_CHANGE(11),
        REASON_SOFTWARE_UPDATE(12),
        REASON_MANUAL(13),
        REASON_CUSTOM_KEY_INVALIDATION(14),
        REASON_PROXIMITY_PERIODIC(15);

        public static final int REASON_ADDRESS_CHANGE_VALUE = 11;
        public static final int REASON_CHANGED_ACCOUNT_VALUE = 8;
        public static final int REASON_CUSTOM_KEY_INVALIDATION_VALUE = 14;
        public static final int REASON_EXPIRATION_VALUE = 5;
        public static final int REASON_FAILURE_RECOVERY_VALUE = 6;
        public static final int REASON_FAST_PERIODIC_VALUE = 4;
        public static final int REASON_FEATURE_TOGGLED_VALUE = 9;
        public static final int REASON_INITIALIZATION_VALUE = 1;
        public static final int REASON_MANUAL_VALUE = 13;
        public static final int REASON_NEW_ACCOUNT_VALUE = 7;
        public static final int REASON_PERIODIC_VALUE = 2;
        public static final int REASON_PROXIMITY_PERIODIC_VALUE = 15;
        public static final int REASON_SERVER_INITIATED_VALUE = 10;
        public static final int REASON_SLOW_PERIODIC_VALUE = 3;
        public static final int REASON_SOFTWARE_UPDATE_VALUE = 12;
        public static final int REASON_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<InvocationReason> internalValueMap = new Internal.EnumLiteMap<InvocationReason>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.InvocationReason.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public InvocationReason findValueByNumber(int number) {
                return InvocationReason.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static InvocationReason valueOf(int value) {
            return forNumber(value);
        }

        public static InvocationReason forNumber(int value) {
            switch (value) {
                case 0:
                    return REASON_UNKNOWN;
                case 1:
                    return REASON_INITIALIZATION;
                case 2:
                    return REASON_PERIODIC;
                case 3:
                    return REASON_SLOW_PERIODIC;
                case 4:
                    return REASON_FAST_PERIODIC;
                case 5:
                    return REASON_EXPIRATION;
                case 6:
                    return REASON_FAILURE_RECOVERY;
                case 7:
                    return REASON_NEW_ACCOUNT;
                case 8:
                    return REASON_CHANGED_ACCOUNT;
                case 9:
                    return REASON_FEATURE_TOGGLED;
                case 10:
                    return REASON_SERVER_INITIATED;
                case 11:
                    return REASON_ADDRESS_CHANGE;
                case 12:
                    return REASON_SOFTWARE_UPDATE;
                case 13:
                    return REASON_MANUAL;
                case 14:
                    return REASON_CUSTOM_KEY_INVALIDATION;
                case 15:
                    return REASON_PROXIMITY_PERIODIC;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<InvocationReason> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return InvocationReasonVerifier.INSTANCE;
        }

        private static final class InvocationReasonVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new InvocationReasonVerifier();

            private InvocationReasonVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return InvocationReason.forNumber(number) != null;
            }
        }

        InvocationReason(int value) {
            this.value = value;
        }
    }

    public enum Type implements Internal.EnumLite {
        ENROLLMENT(0),
        TICKLE(1),
        TX_REQUEST(2),
        TX_REPLY(3),
        TX_SYNC_REQUEST(4),
        TX_SYNC_RESPONSE(5),
        TX_PING(6),
        DEVICE_INFO_UPDATE(7),
        TX_CANCEL_REQUEST(8),
        PROXIMITYAUTH_PAIRING(10),
        GCMV1_IDENTITY_ASSERTION(11),
        DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD(12),
        DEVICE_TO_DEVICE_MESSAGE(13),
        DEVICE_PROXIMITY_CALLBACK(14),
        UNLOCK_KEY_SIGNED_CHALLENGE(15),
        LOGIN_NOTIFICATION(101);

        public static final int DEVICE_INFO_UPDATE_VALUE = 7;
        public static final int DEVICE_PROXIMITY_CALLBACK_VALUE = 14;
        public static final int DEVICE_TO_DEVICE_MESSAGE_VALUE = 13;
        public static final int DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD_VALUE = 12;
        public static final int ENROLLMENT_VALUE = 0;
        public static final int GCMV1_IDENTITY_ASSERTION_VALUE = 11;
        public static final int LOGIN_NOTIFICATION_VALUE = 101;
        public static final int PROXIMITYAUTH_PAIRING_VALUE = 10;
        public static final int TICKLE_VALUE = 1;
        public static final int TX_CANCEL_REQUEST_VALUE = 8;
        public static final int TX_PING_VALUE = 6;
        public static final int TX_REPLY_VALUE = 3;
        public static final int TX_REQUEST_VALUE = 2;
        public static final int TX_SYNC_REQUEST_VALUE = 4;
        public static final int TX_SYNC_RESPONSE_VALUE = 5;
        public static final int UNLOCK_KEY_SIGNED_CHALLENGE_VALUE = 15;
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.Type.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Type findValueByNumber(int number) {
                return Type.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int value) {
            return forNumber(value);
        }

        public static Type forNumber(int value) {
            if (value != 101) {
                switch (value) {
                    case 0:
                        return ENROLLMENT;
                    case 1:
                        return TICKLE;
                    case 2:
                        return TX_REQUEST;
                    case 3:
                        return TX_REPLY;
                    case 4:
                        return TX_SYNC_REQUEST;
                    case 5:
                        return TX_SYNC_RESPONSE;
                    case 6:
                        return TX_PING;
                    case 7:
                        return DEVICE_INFO_UPDATE;
                    case 8:
                        return TX_CANCEL_REQUEST;
                    default:
                        switch (value) {
                            case 10:
                                return PROXIMITYAUTH_PAIRING;
                            case 11:
                                return GCMV1_IDENTITY_ASSERTION;
                            case 12:
                                return DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD;
                            case 13:
                                return DEVICE_TO_DEVICE_MESSAGE;
                            case 14:
                                return DEVICE_PROXIMITY_CALLBACK;
                            case 15:
                                return UNLOCK_KEY_SIGNED_CHALLENGE;
                            default:
                                return null;
                        }
                }
            }
            return LOGIN_NOTIFICATION;
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TypeVerifier.INSTANCE;
        }

        private static final class TypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new TypeVerifier();

            private TypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return Type.forNumber(number) != null;
            }
        }

        Type(int value) {
            this.value = value;
        }
    }

    public static final class GcmDeviceInfo extends GeneratedMessageLite<GcmDeviceInfo, Builder> implements GcmDeviceInfoOrBuilder {
        public static final int ANDROID_DEVICE_ID_FIELD_NUMBER = 1;
        public static final int APN_REGISTRATION_ID_FIELD_NUMBER = 202;
        public static final int ARC_PLUS_PLUS_FIELD_NUMBER = 409;
        public static final int AUTO_UNLOCK_SCREENLOCK_ENABLED_FIELD_NUMBER = 402;
        public static final int AUTO_UNLOCK_SCREENLOCK_SUPPORTED_FIELD_NUMBER = 401;
        public static final int BLE_RADIO_SUPPORTED_FIELD_NUMBER = 407;
        public static final int BLUETOOTH_MAC_ADDRESS_FIELD_NUMBER = 302;
        public static final int BLUETOOTH_RADIO_ENABLED_FIELD_NUMBER = 404;
        public static final int BLUETOOTH_RADIO_SUPPORTED_FIELD_NUMBER = 403;
        public static final int COUNTER_FIELD_NUMBER = 12;
        private static final GcmDeviceInfo DEFAULT_INSTANCE;
        public static final int DEVICE_AUTHZEN_VERSION_FIELD_NUMBER = 24;
        public static final int DEVICE_DISPLAY_DIAGONAL_MILS_FIELD_NUMBER = 22;
        public static final int DEVICE_MANUFACTURER_FIELD_NUMBER = 31;
        public static final int DEVICE_MASTER_KEY_HASH_FIELD_NUMBER = 103;
        public static final int DEVICE_MODEL_FIELD_NUMBER = 7;
        public static final int DEVICE_OS_CODENAME_FIELD_NUMBER = 16;
        public static final int DEVICE_OS_RELEASE_FIELD_NUMBER = 15;
        public static final int DEVICE_OS_VERSION_CODE_FIELD_NUMBER = 14;
        public static final int DEVICE_OS_VERSION_FIELD_NUMBER = 13;
        public static final int DEVICE_SOFTWARE_PACKAGE_FIELD_NUMBER = 19;
        public static final int DEVICE_SOFTWARE_VERSION_CODE_FIELD_NUMBER = 18;
        public static final int DEVICE_SOFTWARE_VERSION_FIELD_NUMBER = 17;
        public static final int DEVICE_TYPE_FIELD_NUMBER = 32;
        public static final int ENABLED_SOFTWARE_FEATURES_FIELD_NUMBER = 412;
        public static final int ENROLLMENT_SESSION_ID_FIELD_NUMBER = 1000;
        public static final int GCM_REGISTRATION_ID_FIELD_NUMBER = 102;
        public static final int IS_SCREENLOCK_STATE_FLAKY_FIELD_NUMBER = 410;
        public static final int KEY_HANDLE_FIELD_NUMBER = 9;
        public static final int LOCALE_FIELD_NUMBER = 8;
        public static final int LONG_DEVICE_ID_FIELD_NUMBER = 29;
        public static final int MOBILE_DATA_SUPPORTED_FIELD_NUMBER = 405;
        public static final int NOTIFICATION_ENABLED_FIELD_NUMBER = 203;
        public static final int OAUTH_TOKEN_FIELD_NUMBER = 1001;
        private static volatile Parser<GcmDeviceInfo> PARSER = null;
        public static final int PIXEL_EXPERIENCE_FIELD_NUMBER = 408;
        public static final int SUPPORTED_SOFTWARE_FEATURES_FIELD_NUMBER = 411;
        public static final int TETHERING_SUPPORTED_FIELD_NUMBER = 406;
        public static final int USER_PUBLIC_KEY_FIELD_NUMBER = 4;
        public static final int USING_SECURE_SCREENLOCK_FIELD_NUMBER = 400;
        private long androidDeviceId_;
        private boolean arcPlusPlus_;
        private boolean autoUnlockScreenlockEnabled_;
        private boolean autoUnlockScreenlockSupported_;
        private int bitField0_;
        private int bitField1_;
        private boolean bleRadioSupported_;
        private boolean bluetoothRadioEnabled_;
        private boolean bluetoothRadioSupported_;
        private long counter_;
        private int deviceAuthzenVersion_;
        private int deviceDisplayDiagonalMils_;
        private long deviceOsVersionCode_;
        private long deviceSoftwareVersionCode_;
        private boolean isScreenlockStateFlaky_;
        private boolean mobileDataSupported_;
        private boolean pixelExperience_;
        private boolean tetheringSupported_;
        private boolean usingSecureScreenlock_;
        private static final Internal.ListAdapter.Converter<Integer, SoftwareFeature> supportedSoftwareFeatures_converter_ = new Internal.ListAdapter.Converter<Integer, SoftwareFeature>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfo.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public SoftwareFeature convert(Integer from) {
                SoftwareFeature result = SoftwareFeature.forNumber(from.intValue());
                return result == null ? SoftwareFeature.UNKNOWN_FEATURE : result;
            }
        };
        private static final Internal.ListAdapter.Converter<Integer, SoftwareFeature> enabledSoftwareFeatures_converter_ = new Internal.ListAdapter.Converter<Integer, SoftwareFeature>() { // from class: com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfo.2
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public SoftwareFeature convert(Integer from) {
                SoftwareFeature result = SoftwareFeature.forNumber(from.intValue());
                return result == null ? SoftwareFeature.UNKNOWN_FEATURE : result;
            }
        };
        private byte memoizedIsInitialized = 2;
        private ByteString gcmRegistrationId_ = ByteString.EMPTY;
        private ByteString apnRegistrationId_ = ByteString.EMPTY;
        private boolean notificationEnabled_ = true;
        private String bluetoothMacAddress_ = "";
        private ByteString deviceMasterKeyHash_ = ByteString.EMPTY;
        private ByteString userPublicKey_ = ByteString.EMPTY;
        private String deviceModel_ = "";
        private String locale_ = "";
        private ByteString keyHandle_ = ByteString.EMPTY;
        private String deviceOsVersion_ = "";
        private String deviceOsRelease_ = "";
        private String deviceOsCodename_ = "";
        private String deviceSoftwareVersion_ = "";
        private String deviceSoftwarePackage_ = "";
        private ByteString longDeviceId_ = ByteString.EMPTY;
        private String deviceManufacturer_ = "";
        private int deviceType_ = 1;
        private Internal.IntList supportedSoftwareFeatures_ = emptyIntList();
        private Internal.IntList enabledSoftwareFeatures_ = emptyIntList();
        private ByteString enrollmentSessionId_ = ByteString.EMPTY;
        private String oauthToken_ = "";

        private GcmDeviceInfo() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasAndroidDeviceId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public long getAndroidDeviceId() {
            return this.androidDeviceId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAndroidDeviceId(long value) {
            this.bitField0_ |= 1;
            this.androidDeviceId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAndroidDeviceId() {
            this.bitField0_ &= -2;
            this.androidDeviceId_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasGcmRegistrationId() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getGcmRegistrationId() {
            return this.gcmRegistrationId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGcmRegistrationId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.gcmRegistrationId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGcmRegistrationId() {
            this.bitField0_ &= -3;
            this.gcmRegistrationId_ = getDefaultInstance().getGcmRegistrationId();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasApnRegistrationId() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getApnRegistrationId() {
            return this.apnRegistrationId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setApnRegistrationId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.apnRegistrationId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearApnRegistrationId() {
            this.bitField0_ &= -5;
            this.apnRegistrationId_ = getDefaultInstance().getApnRegistrationId();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasNotificationEnabled() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getNotificationEnabled() {
            return this.notificationEnabled_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationEnabled(boolean value) {
            this.bitField0_ |= 8;
            this.notificationEnabled_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationEnabled() {
            this.bitField0_ &= -9;
            this.notificationEnabled_ = true;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasBluetoothMacAddress() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getBluetoothMacAddress() {
            return this.bluetoothMacAddress_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getBluetoothMacAddressBytes() {
            return ByteString.copyFromUtf8(this.bluetoothMacAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothMacAddress(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16;
            this.bluetoothMacAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothMacAddress() {
            this.bitField0_ &= -17;
            this.bluetoothMacAddress_ = getDefaultInstance().getBluetoothMacAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothMacAddressBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16;
            this.bluetoothMacAddress_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceMasterKeyHash() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceMasterKeyHash() {
            return this.deviceMasterKeyHash_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceMasterKeyHash(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 32;
            this.deviceMasterKeyHash_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceMasterKeyHash() {
            this.bitField0_ &= -33;
            this.deviceMasterKeyHash_ = getDefaultInstance().getDeviceMasterKeyHash();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasUserPublicKey() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getUserPublicKey() {
            return this.userPublicKey_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserPublicKey(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 64;
            this.userPublicKey_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserPublicKey() {
            this.bitField0_ &= -65;
            this.userPublicKey_ = getDefaultInstance().getUserPublicKey();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceModel() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceModel() {
            return this.deviceModel_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceModelBytes() {
            return ByteString.copyFromUtf8(this.deviceModel_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceModel(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 128;
            this.deviceModel_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceModel() {
            this.bitField0_ &= -129;
            this.deviceModel_ = getDefaultInstance().getDeviceModel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceModelBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 128;
            this.deviceModel_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasLocale() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getLocale() {
            return this.locale_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getLocaleBytes() {
            return ByteString.copyFromUtf8(this.locale_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 256;
            this.locale_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocale() {
            this.bitField0_ &= -257;
            this.locale_ = getDefaultInstance().getLocale();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocaleBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 256;
            this.locale_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasKeyHandle() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getKeyHandle() {
            return this.keyHandle_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyHandle(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 512;
            this.keyHandle_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeyHandle() {
            this.bitField0_ &= -513;
            this.keyHandle_ = getDefaultInstance().getKeyHandle();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasCounter() {
            return (this.bitField0_ & 1024) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public long getCounter() {
            return this.counter_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounter(long value) {
            this.bitField0_ |= 1024;
            this.counter_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounter() {
            this.bitField0_ &= -1025;
            this.counter_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceOsVersion() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceOsVersion() {
            return this.deviceOsVersion_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceOsVersionBytes() {
            return ByteString.copyFromUtf8(this.deviceOsVersion_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsVersion(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2048;
            this.deviceOsVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceOsVersion() {
            this.bitField0_ &= -2049;
            this.deviceOsVersion_ = getDefaultInstance().getDeviceOsVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsVersionBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2048;
            this.deviceOsVersion_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceOsVersionCode() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public long getDeviceOsVersionCode() {
            return this.deviceOsVersionCode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsVersionCode(long value) {
            this.bitField0_ |= 4096;
            this.deviceOsVersionCode_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceOsVersionCode() {
            this.bitField0_ &= -4097;
            this.deviceOsVersionCode_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceOsRelease() {
            return (this.bitField0_ & 8192) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceOsRelease() {
            return this.deviceOsRelease_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceOsReleaseBytes() {
            return ByteString.copyFromUtf8(this.deviceOsRelease_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsRelease(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8192;
            this.deviceOsRelease_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceOsRelease() {
            this.bitField0_ &= -8193;
            this.deviceOsRelease_ = getDefaultInstance().getDeviceOsRelease();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsReleaseBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8192;
            this.deviceOsRelease_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceOsCodename() {
            return (this.bitField0_ & 16384) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceOsCodename() {
            return this.deviceOsCodename_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceOsCodenameBytes() {
            return ByteString.copyFromUtf8(this.deviceOsCodename_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsCodename(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16384;
            this.deviceOsCodename_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceOsCodename() {
            this.bitField0_ &= -16385;
            this.deviceOsCodename_ = getDefaultInstance().getDeviceOsCodename();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceOsCodenameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16384;
            this.deviceOsCodename_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceSoftwareVersion() {
            return (this.bitField0_ & 32768) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceSoftwareVersion() {
            return this.deviceSoftwareVersion_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceSoftwareVersionBytes() {
            return ByteString.copyFromUtf8(this.deviceSoftwareVersion_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSoftwareVersion(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 32768;
            this.deviceSoftwareVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceSoftwareVersion() {
            this.bitField0_ &= -32769;
            this.deviceSoftwareVersion_ = getDefaultInstance().getDeviceSoftwareVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSoftwareVersionBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 32768;
            this.deviceSoftwareVersion_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceSoftwareVersionCode() {
            return (this.bitField0_ & 65536) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public long getDeviceSoftwareVersionCode() {
            return this.deviceSoftwareVersionCode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSoftwareVersionCode(long value) {
            this.bitField0_ |= 65536;
            this.deviceSoftwareVersionCode_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceSoftwareVersionCode() {
            this.bitField0_ &= -65537;
            this.deviceSoftwareVersionCode_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceSoftwarePackage() {
            return (this.bitField0_ & 131072) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceSoftwarePackage() {
            return this.deviceSoftwarePackage_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceSoftwarePackageBytes() {
            return ByteString.copyFromUtf8(this.deviceSoftwarePackage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSoftwarePackage(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 131072;
            this.deviceSoftwarePackage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceSoftwarePackage() {
            this.bitField0_ &= -131073;
            this.deviceSoftwarePackage_ = getDefaultInstance().getDeviceSoftwarePackage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSoftwarePackageBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 131072;
            this.deviceSoftwarePackage_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceDisplayDiagonalMils() {
            return (this.bitField0_ & 262144) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public int getDeviceDisplayDiagonalMils() {
            return this.deviceDisplayDiagonalMils_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceDisplayDiagonalMils(int value) {
            this.bitField0_ |= 262144;
            this.deviceDisplayDiagonalMils_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceDisplayDiagonalMils() {
            this.bitField0_ &= -262145;
            this.deviceDisplayDiagonalMils_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceAuthzenVersion() {
            return (this.bitField0_ & 524288) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public int getDeviceAuthzenVersion() {
            return this.deviceAuthzenVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceAuthzenVersion(int value) {
            this.bitField0_ |= 524288;
            this.deviceAuthzenVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceAuthzenVersion() {
            this.bitField0_ &= -524289;
            this.deviceAuthzenVersion_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasLongDeviceId() {
            return (this.bitField0_ & 1048576) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getLongDeviceId() {
            return this.longDeviceId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLongDeviceId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1048576;
            this.longDeviceId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLongDeviceId() {
            this.bitField0_ &= -1048577;
            this.longDeviceId_ = getDefaultInstance().getLongDeviceId();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceManufacturer() {
            return (this.bitField0_ & 2097152) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getDeviceManufacturer() {
            return this.deviceManufacturer_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getDeviceManufacturerBytes() {
            return ByteString.copyFromUtf8(this.deviceManufacturer_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceManufacturer(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2097152;
            this.deviceManufacturer_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceManufacturer() {
            this.bitField0_ &= -2097153;
            this.deviceManufacturer_ = getDefaultInstance().getDeviceManufacturer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceManufacturerBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2097152;
            this.deviceManufacturer_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasDeviceType() {
            return (this.bitField0_ & 4194304) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public DeviceType getDeviceType() {
            DeviceType result = DeviceType.forNumber(this.deviceType_);
            return result == null ? DeviceType.ANDROID : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceType(DeviceType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4194304;
            this.deviceType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceType() {
            this.bitField0_ &= -4194305;
            this.deviceType_ = 1;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasUsingSecureScreenlock() {
            return (this.bitField0_ & 8388608) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getUsingSecureScreenlock() {
            return this.usingSecureScreenlock_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsingSecureScreenlock(boolean value) {
            this.bitField0_ |= 8388608;
            this.usingSecureScreenlock_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsingSecureScreenlock() {
            this.bitField0_ &= -8388609;
            this.usingSecureScreenlock_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasAutoUnlockScreenlockSupported() {
            return (this.bitField0_ & 16777216) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getAutoUnlockScreenlockSupported() {
            return this.autoUnlockScreenlockSupported_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoUnlockScreenlockSupported(boolean value) {
            this.bitField0_ |= 16777216;
            this.autoUnlockScreenlockSupported_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoUnlockScreenlockSupported() {
            this.bitField0_ &= -16777217;
            this.autoUnlockScreenlockSupported_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasAutoUnlockScreenlockEnabled() {
            return (this.bitField0_ & 33554432) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getAutoUnlockScreenlockEnabled() {
            return this.autoUnlockScreenlockEnabled_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoUnlockScreenlockEnabled(boolean value) {
            this.bitField0_ |= 33554432;
            this.autoUnlockScreenlockEnabled_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoUnlockScreenlockEnabled() {
            this.bitField0_ &= -33554433;
            this.autoUnlockScreenlockEnabled_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasBluetoothRadioSupported() {
            return (this.bitField0_ & 67108864) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getBluetoothRadioSupported() {
            return this.bluetoothRadioSupported_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothRadioSupported(boolean value) {
            this.bitField0_ |= 67108864;
            this.bluetoothRadioSupported_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothRadioSupported() {
            this.bitField0_ &= -67108865;
            this.bluetoothRadioSupported_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasBluetoothRadioEnabled() {
            return (this.bitField0_ & 134217728) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getBluetoothRadioEnabled() {
            return this.bluetoothRadioEnabled_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothRadioEnabled(boolean value) {
            this.bitField0_ |= 134217728;
            this.bluetoothRadioEnabled_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothRadioEnabled() {
            this.bitField0_ &= -134217729;
            this.bluetoothRadioEnabled_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasMobileDataSupported() {
            return (this.bitField0_ & 268435456) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getMobileDataSupported() {
            return this.mobileDataSupported_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMobileDataSupported(boolean value) {
            this.bitField0_ |= 268435456;
            this.mobileDataSupported_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMobileDataSupported() {
            this.bitField0_ &= -268435457;
            this.mobileDataSupported_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasTetheringSupported() {
            return (this.bitField0_ & 536870912) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getTetheringSupported() {
            return this.tetheringSupported_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTetheringSupported(boolean value) {
            this.bitField0_ |= 536870912;
            this.tetheringSupported_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTetheringSupported() {
            this.bitField0_ &= -536870913;
            this.tetheringSupported_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasBleRadioSupported() {
            return (this.bitField0_ & 1073741824) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getBleRadioSupported() {
            return this.bleRadioSupported_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBleRadioSupported(boolean value) {
            this.bitField0_ |= 1073741824;
            this.bleRadioSupported_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBleRadioSupported() {
            this.bitField0_ &= -1073741825;
            this.bleRadioSupported_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasPixelExperience() {
            return (this.bitField0_ & Integer.MIN_VALUE) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getPixelExperience() {
            return this.pixelExperience_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPixelExperience(boolean value) {
            this.bitField0_ |= Integer.MIN_VALUE;
            this.pixelExperience_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPixelExperience() {
            this.bitField0_ &= Integer.MAX_VALUE;
            this.pixelExperience_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasArcPlusPlus() {
            return (this.bitField1_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getArcPlusPlus() {
            return this.arcPlusPlus_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArcPlusPlus(boolean value) {
            this.bitField1_ |= 1;
            this.arcPlusPlus_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArcPlusPlus() {
            this.bitField1_ &= -2;
            this.arcPlusPlus_ = false;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasIsScreenlockStateFlaky() {
            return (this.bitField1_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean getIsScreenlockStateFlaky() {
            return this.isScreenlockStateFlaky_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsScreenlockStateFlaky(boolean value) {
            this.bitField1_ |= 2;
            this.isScreenlockStateFlaky_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsScreenlockStateFlaky() {
            this.bitField1_ &= -3;
            this.isScreenlockStateFlaky_ = false;
        }

        static {
            GcmDeviceInfo defaultInstance = new GcmDeviceInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(GcmDeviceInfo.class, defaultInstance);
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public List<SoftwareFeature> getSupportedSoftwareFeaturesList() {
            return new Internal.ListAdapter(this.supportedSoftwareFeatures_, supportedSoftwareFeatures_converter_);
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public int getSupportedSoftwareFeaturesCount() {
            return this.supportedSoftwareFeatures_.size();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public SoftwareFeature getSupportedSoftwareFeatures(int index) {
            return supportedSoftwareFeatures_converter_.convert(Integer.valueOf(this.supportedSoftwareFeatures_.getInt(index)));
        }

        private void ensureSupportedSoftwareFeaturesIsMutable() {
            if (!this.supportedSoftwareFeatures_.isModifiable()) {
                this.supportedSoftwareFeatures_ = GeneratedMessageLite.mutableCopy(this.supportedSoftwareFeatures_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedSoftwareFeatures(int index, SoftwareFeature value) {
            if (value == null) {
                throw null;
            }
            ensureSupportedSoftwareFeaturesIsMutable();
            this.supportedSoftwareFeatures_.setInt(index, value.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedSoftwareFeatures(SoftwareFeature value) {
            if (value == null) {
                throw null;
            }
            ensureSupportedSoftwareFeaturesIsMutable();
            this.supportedSoftwareFeatures_.addInt(value.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedSoftwareFeatures(Iterable<? extends SoftwareFeature> values) {
            ensureSupportedSoftwareFeaturesIsMutable();
            for (SoftwareFeature value : values) {
                this.supportedSoftwareFeatures_.addInt(value.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedSoftwareFeatures() {
            this.supportedSoftwareFeatures_ = emptyIntList();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public List<SoftwareFeature> getEnabledSoftwareFeaturesList() {
            return new Internal.ListAdapter(this.enabledSoftwareFeatures_, enabledSoftwareFeatures_converter_);
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public int getEnabledSoftwareFeaturesCount() {
            return this.enabledSoftwareFeatures_.size();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public SoftwareFeature getEnabledSoftwareFeatures(int index) {
            return enabledSoftwareFeatures_converter_.convert(Integer.valueOf(this.enabledSoftwareFeatures_.getInt(index)));
        }

        private void ensureEnabledSoftwareFeaturesIsMutable() {
            if (!this.enabledSoftwareFeatures_.isModifiable()) {
                this.enabledSoftwareFeatures_ = GeneratedMessageLite.mutableCopy(this.enabledSoftwareFeatures_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnabledSoftwareFeatures(int index, SoftwareFeature value) {
            if (value == null) {
                throw null;
            }
            ensureEnabledSoftwareFeaturesIsMutable();
            this.enabledSoftwareFeatures_.setInt(index, value.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEnabledSoftwareFeatures(SoftwareFeature value) {
            if (value == null) {
                throw null;
            }
            ensureEnabledSoftwareFeaturesIsMutable();
            this.enabledSoftwareFeatures_.addInt(value.getNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllEnabledSoftwareFeatures(Iterable<? extends SoftwareFeature> values) {
            ensureEnabledSoftwareFeaturesIsMutable();
            for (SoftwareFeature value : values) {
                this.enabledSoftwareFeatures_.addInt(value.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEnabledSoftwareFeatures() {
            this.enabledSoftwareFeatures_ = emptyIntList();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasEnrollmentSessionId() {
            return (this.bitField1_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getEnrollmentSessionId() {
            return this.enrollmentSessionId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnrollmentSessionId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField1_ |= 4;
            this.enrollmentSessionId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEnrollmentSessionId() {
            this.bitField1_ &= -5;
            this.enrollmentSessionId_ = getDefaultInstance().getEnrollmentSessionId();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public boolean hasOauthToken() {
            return (this.bitField1_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public String getOauthToken() {
            return this.oauthToken_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
        public ByteString getOauthTokenBytes() {
            return ByteString.copyFromUtf8(this.oauthToken_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOauthToken(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField1_ |= 8;
            this.oauthToken_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOauthToken() {
            this.bitField1_ &= -9;
            this.oauthToken_ = getDefaultInstance().getOauthToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOauthTokenBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField1_ |= 8;
            this.oauthToken_ = value.toStringUtf8();
        }

        public static GcmDeviceInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmDeviceInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmDeviceInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmDeviceInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmDeviceInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmDeviceInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmDeviceInfo parseFrom(InputStream input) throws IOException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmDeviceInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GcmDeviceInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (GcmDeviceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmDeviceInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmDeviceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GcmDeviceInfo parseFrom(CodedInputStream input) throws IOException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmDeviceInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmDeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GcmDeviceInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GcmDeviceInfo, Builder> implements GcmDeviceInfoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GcmDeviceInfo.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasAndroidDeviceId() {
                return ((GcmDeviceInfo) this.instance).hasAndroidDeviceId();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public long getAndroidDeviceId() {
                return ((GcmDeviceInfo) this.instance).getAndroidDeviceId();
            }

            public Builder setAndroidDeviceId(long value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setAndroidDeviceId(value);
                return this;
            }

            public Builder clearAndroidDeviceId() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearAndroidDeviceId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasGcmRegistrationId() {
                return ((GcmDeviceInfo) this.instance).hasGcmRegistrationId();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getGcmRegistrationId() {
                return ((GcmDeviceInfo) this.instance).getGcmRegistrationId();
            }

            public Builder setGcmRegistrationId(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setGcmRegistrationId(value);
                return this;
            }

            public Builder clearGcmRegistrationId() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearGcmRegistrationId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasApnRegistrationId() {
                return ((GcmDeviceInfo) this.instance).hasApnRegistrationId();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getApnRegistrationId() {
                return ((GcmDeviceInfo) this.instance).getApnRegistrationId();
            }

            public Builder setApnRegistrationId(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setApnRegistrationId(value);
                return this;
            }

            public Builder clearApnRegistrationId() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearApnRegistrationId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasNotificationEnabled() {
                return ((GcmDeviceInfo) this.instance).hasNotificationEnabled();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getNotificationEnabled() {
                return ((GcmDeviceInfo) this.instance).getNotificationEnabled();
            }

            public Builder setNotificationEnabled(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setNotificationEnabled(value);
                return this;
            }

            public Builder clearNotificationEnabled() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearNotificationEnabled();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasBluetoothMacAddress() {
                return ((GcmDeviceInfo) this.instance).hasBluetoothMacAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getBluetoothMacAddress() {
                return ((GcmDeviceInfo) this.instance).getBluetoothMacAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getBluetoothMacAddressBytes() {
                return ((GcmDeviceInfo) this.instance).getBluetoothMacAddressBytes();
            }

            public Builder setBluetoothMacAddress(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setBluetoothMacAddress(value);
                return this;
            }

            public Builder clearBluetoothMacAddress() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearBluetoothMacAddress();
                return this;
            }

            public Builder setBluetoothMacAddressBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setBluetoothMacAddressBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceMasterKeyHash() {
                return ((GcmDeviceInfo) this.instance).hasDeviceMasterKeyHash();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceMasterKeyHash() {
                return ((GcmDeviceInfo) this.instance).getDeviceMasterKeyHash();
            }

            public Builder setDeviceMasterKeyHash(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceMasterKeyHash(value);
                return this;
            }

            public Builder clearDeviceMasterKeyHash() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceMasterKeyHash();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasUserPublicKey() {
                return ((GcmDeviceInfo) this.instance).hasUserPublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getUserPublicKey() {
                return ((GcmDeviceInfo) this.instance).getUserPublicKey();
            }

            public Builder setUserPublicKey(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setUserPublicKey(value);
                return this;
            }

            public Builder clearUserPublicKey() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearUserPublicKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceModel() {
                return ((GcmDeviceInfo) this.instance).hasDeviceModel();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceModel() {
                return ((GcmDeviceInfo) this.instance).getDeviceModel();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceModelBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceModelBytes();
            }

            public Builder setDeviceModel(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceModel(value);
                return this;
            }

            public Builder clearDeviceModel() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceModel();
                return this;
            }

            public Builder setDeviceModelBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceModelBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasLocale() {
                return ((GcmDeviceInfo) this.instance).hasLocale();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getLocale() {
                return ((GcmDeviceInfo) this.instance).getLocale();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getLocaleBytes() {
                return ((GcmDeviceInfo) this.instance).getLocaleBytes();
            }

            public Builder setLocale(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setLocale(value);
                return this;
            }

            public Builder clearLocale() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearLocale();
                return this;
            }

            public Builder setLocaleBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setLocaleBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasKeyHandle() {
                return ((GcmDeviceInfo) this.instance).hasKeyHandle();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getKeyHandle() {
                return ((GcmDeviceInfo) this.instance).getKeyHandle();
            }

            public Builder setKeyHandle(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setKeyHandle(value);
                return this;
            }

            public Builder clearKeyHandle() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearKeyHandle();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasCounter() {
                return ((GcmDeviceInfo) this.instance).hasCounter();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public long getCounter() {
                return ((GcmDeviceInfo) this.instance).getCounter();
            }

            public Builder setCounter(long value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setCounter(value);
                return this;
            }

            public Builder clearCounter() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearCounter();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceOsVersion() {
                return ((GcmDeviceInfo) this.instance).hasDeviceOsVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceOsVersion() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceOsVersionBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsVersionBytes();
            }

            public Builder setDeviceOsVersion(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsVersion(value);
                return this;
            }

            public Builder clearDeviceOsVersion() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceOsVersion();
                return this;
            }

            public Builder setDeviceOsVersionBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsVersionBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceOsVersionCode() {
                return ((GcmDeviceInfo) this.instance).hasDeviceOsVersionCode();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public long getDeviceOsVersionCode() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsVersionCode();
            }

            public Builder setDeviceOsVersionCode(long value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsVersionCode(value);
                return this;
            }

            public Builder clearDeviceOsVersionCode() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceOsVersionCode();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceOsRelease() {
                return ((GcmDeviceInfo) this.instance).hasDeviceOsRelease();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceOsRelease() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsRelease();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceOsReleaseBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsReleaseBytes();
            }

            public Builder setDeviceOsRelease(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsRelease(value);
                return this;
            }

            public Builder clearDeviceOsRelease() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceOsRelease();
                return this;
            }

            public Builder setDeviceOsReleaseBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsReleaseBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceOsCodename() {
                return ((GcmDeviceInfo) this.instance).hasDeviceOsCodename();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceOsCodename() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsCodename();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceOsCodenameBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceOsCodenameBytes();
            }

            public Builder setDeviceOsCodename(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsCodename(value);
                return this;
            }

            public Builder clearDeviceOsCodename() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceOsCodename();
                return this;
            }

            public Builder setDeviceOsCodenameBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceOsCodenameBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceSoftwareVersion() {
                return ((GcmDeviceInfo) this.instance).hasDeviceSoftwareVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceSoftwareVersion() {
                return ((GcmDeviceInfo) this.instance).getDeviceSoftwareVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceSoftwareVersionBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceSoftwareVersionBytes();
            }

            public Builder setDeviceSoftwareVersion(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceSoftwareVersion(value);
                return this;
            }

            public Builder clearDeviceSoftwareVersion() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceSoftwareVersion();
                return this;
            }

            public Builder setDeviceSoftwareVersionBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceSoftwareVersionBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceSoftwareVersionCode() {
                return ((GcmDeviceInfo) this.instance).hasDeviceSoftwareVersionCode();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public long getDeviceSoftwareVersionCode() {
                return ((GcmDeviceInfo) this.instance).getDeviceSoftwareVersionCode();
            }

            public Builder setDeviceSoftwareVersionCode(long value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceSoftwareVersionCode(value);
                return this;
            }

            public Builder clearDeviceSoftwareVersionCode() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceSoftwareVersionCode();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceSoftwarePackage() {
                return ((GcmDeviceInfo) this.instance).hasDeviceSoftwarePackage();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceSoftwarePackage() {
                return ((GcmDeviceInfo) this.instance).getDeviceSoftwarePackage();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceSoftwarePackageBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceSoftwarePackageBytes();
            }

            public Builder setDeviceSoftwarePackage(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceSoftwarePackage(value);
                return this;
            }

            public Builder clearDeviceSoftwarePackage() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceSoftwarePackage();
                return this;
            }

            public Builder setDeviceSoftwarePackageBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceSoftwarePackageBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceDisplayDiagonalMils() {
                return ((GcmDeviceInfo) this.instance).hasDeviceDisplayDiagonalMils();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public int getDeviceDisplayDiagonalMils() {
                return ((GcmDeviceInfo) this.instance).getDeviceDisplayDiagonalMils();
            }

            public Builder setDeviceDisplayDiagonalMils(int value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceDisplayDiagonalMils(value);
                return this;
            }

            public Builder clearDeviceDisplayDiagonalMils() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceDisplayDiagonalMils();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceAuthzenVersion() {
                return ((GcmDeviceInfo) this.instance).hasDeviceAuthzenVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public int getDeviceAuthzenVersion() {
                return ((GcmDeviceInfo) this.instance).getDeviceAuthzenVersion();
            }

            public Builder setDeviceAuthzenVersion(int value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceAuthzenVersion(value);
                return this;
            }

            public Builder clearDeviceAuthzenVersion() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceAuthzenVersion();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasLongDeviceId() {
                return ((GcmDeviceInfo) this.instance).hasLongDeviceId();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getLongDeviceId() {
                return ((GcmDeviceInfo) this.instance).getLongDeviceId();
            }

            public Builder setLongDeviceId(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setLongDeviceId(value);
                return this;
            }

            public Builder clearLongDeviceId() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearLongDeviceId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceManufacturer() {
                return ((GcmDeviceInfo) this.instance).hasDeviceManufacturer();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getDeviceManufacturer() {
                return ((GcmDeviceInfo) this.instance).getDeviceManufacturer();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getDeviceManufacturerBytes() {
                return ((GcmDeviceInfo) this.instance).getDeviceManufacturerBytes();
            }

            public Builder setDeviceManufacturer(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceManufacturer(value);
                return this;
            }

            public Builder clearDeviceManufacturer() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceManufacturer();
                return this;
            }

            public Builder setDeviceManufacturerBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceManufacturerBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasDeviceType() {
                return ((GcmDeviceInfo) this.instance).hasDeviceType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public DeviceType getDeviceType() {
                return ((GcmDeviceInfo) this.instance).getDeviceType();
            }

            public Builder setDeviceType(DeviceType value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setDeviceType(value);
                return this;
            }

            public Builder clearDeviceType() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearDeviceType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasUsingSecureScreenlock() {
                return ((GcmDeviceInfo) this.instance).hasUsingSecureScreenlock();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getUsingSecureScreenlock() {
                return ((GcmDeviceInfo) this.instance).getUsingSecureScreenlock();
            }

            public Builder setUsingSecureScreenlock(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setUsingSecureScreenlock(value);
                return this;
            }

            public Builder clearUsingSecureScreenlock() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearUsingSecureScreenlock();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasAutoUnlockScreenlockSupported() {
                return ((GcmDeviceInfo) this.instance).hasAutoUnlockScreenlockSupported();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getAutoUnlockScreenlockSupported() {
                return ((GcmDeviceInfo) this.instance).getAutoUnlockScreenlockSupported();
            }

            public Builder setAutoUnlockScreenlockSupported(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setAutoUnlockScreenlockSupported(value);
                return this;
            }

            public Builder clearAutoUnlockScreenlockSupported() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearAutoUnlockScreenlockSupported();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasAutoUnlockScreenlockEnabled() {
                return ((GcmDeviceInfo) this.instance).hasAutoUnlockScreenlockEnabled();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getAutoUnlockScreenlockEnabled() {
                return ((GcmDeviceInfo) this.instance).getAutoUnlockScreenlockEnabled();
            }

            public Builder setAutoUnlockScreenlockEnabled(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setAutoUnlockScreenlockEnabled(value);
                return this;
            }

            public Builder clearAutoUnlockScreenlockEnabled() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearAutoUnlockScreenlockEnabled();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasBluetoothRadioSupported() {
                return ((GcmDeviceInfo) this.instance).hasBluetoothRadioSupported();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getBluetoothRadioSupported() {
                return ((GcmDeviceInfo) this.instance).getBluetoothRadioSupported();
            }

            public Builder setBluetoothRadioSupported(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setBluetoothRadioSupported(value);
                return this;
            }

            public Builder clearBluetoothRadioSupported() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearBluetoothRadioSupported();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasBluetoothRadioEnabled() {
                return ((GcmDeviceInfo) this.instance).hasBluetoothRadioEnabled();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getBluetoothRadioEnabled() {
                return ((GcmDeviceInfo) this.instance).getBluetoothRadioEnabled();
            }

            public Builder setBluetoothRadioEnabled(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setBluetoothRadioEnabled(value);
                return this;
            }

            public Builder clearBluetoothRadioEnabled() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearBluetoothRadioEnabled();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasMobileDataSupported() {
                return ((GcmDeviceInfo) this.instance).hasMobileDataSupported();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getMobileDataSupported() {
                return ((GcmDeviceInfo) this.instance).getMobileDataSupported();
            }

            public Builder setMobileDataSupported(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setMobileDataSupported(value);
                return this;
            }

            public Builder clearMobileDataSupported() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearMobileDataSupported();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasTetheringSupported() {
                return ((GcmDeviceInfo) this.instance).hasTetheringSupported();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getTetheringSupported() {
                return ((GcmDeviceInfo) this.instance).getTetheringSupported();
            }

            public Builder setTetheringSupported(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setTetheringSupported(value);
                return this;
            }

            public Builder clearTetheringSupported() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearTetheringSupported();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasBleRadioSupported() {
                return ((GcmDeviceInfo) this.instance).hasBleRadioSupported();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getBleRadioSupported() {
                return ((GcmDeviceInfo) this.instance).getBleRadioSupported();
            }

            public Builder setBleRadioSupported(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setBleRadioSupported(value);
                return this;
            }

            public Builder clearBleRadioSupported() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearBleRadioSupported();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasPixelExperience() {
                return ((GcmDeviceInfo) this.instance).hasPixelExperience();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getPixelExperience() {
                return ((GcmDeviceInfo) this.instance).getPixelExperience();
            }

            public Builder setPixelExperience(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setPixelExperience(value);
                return this;
            }

            public Builder clearPixelExperience() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearPixelExperience();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasArcPlusPlus() {
                return ((GcmDeviceInfo) this.instance).hasArcPlusPlus();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getArcPlusPlus() {
                return ((GcmDeviceInfo) this.instance).getArcPlusPlus();
            }

            public Builder setArcPlusPlus(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setArcPlusPlus(value);
                return this;
            }

            public Builder clearArcPlusPlus() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearArcPlusPlus();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasIsScreenlockStateFlaky() {
                return ((GcmDeviceInfo) this.instance).hasIsScreenlockStateFlaky();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean getIsScreenlockStateFlaky() {
                return ((GcmDeviceInfo) this.instance).getIsScreenlockStateFlaky();
            }

            public Builder setIsScreenlockStateFlaky(boolean value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setIsScreenlockStateFlaky(value);
                return this;
            }

            public Builder clearIsScreenlockStateFlaky() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearIsScreenlockStateFlaky();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public List<SoftwareFeature> getSupportedSoftwareFeaturesList() {
                return ((GcmDeviceInfo) this.instance).getSupportedSoftwareFeaturesList();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public int getSupportedSoftwareFeaturesCount() {
                return ((GcmDeviceInfo) this.instance).getSupportedSoftwareFeaturesCount();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public SoftwareFeature getSupportedSoftwareFeatures(int index) {
                return ((GcmDeviceInfo) this.instance).getSupportedSoftwareFeatures(index);
            }

            public Builder setSupportedSoftwareFeatures(int index, SoftwareFeature value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setSupportedSoftwareFeatures(index, value);
                return this;
            }

            public Builder addSupportedSoftwareFeatures(SoftwareFeature value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).addSupportedSoftwareFeatures(value);
                return this;
            }

            public Builder addAllSupportedSoftwareFeatures(Iterable<? extends SoftwareFeature> values) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).addAllSupportedSoftwareFeatures(values);
                return this;
            }

            public Builder clearSupportedSoftwareFeatures() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearSupportedSoftwareFeatures();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public List<SoftwareFeature> getEnabledSoftwareFeaturesList() {
                return ((GcmDeviceInfo) this.instance).getEnabledSoftwareFeaturesList();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public int getEnabledSoftwareFeaturesCount() {
                return ((GcmDeviceInfo) this.instance).getEnabledSoftwareFeaturesCount();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public SoftwareFeature getEnabledSoftwareFeatures(int index) {
                return ((GcmDeviceInfo) this.instance).getEnabledSoftwareFeatures(index);
            }

            public Builder setEnabledSoftwareFeatures(int index, SoftwareFeature value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setEnabledSoftwareFeatures(index, value);
                return this;
            }

            public Builder addEnabledSoftwareFeatures(SoftwareFeature value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).addEnabledSoftwareFeatures(value);
                return this;
            }

            public Builder addAllEnabledSoftwareFeatures(Iterable<? extends SoftwareFeature> values) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).addAllEnabledSoftwareFeatures(values);
                return this;
            }

            public Builder clearEnabledSoftwareFeatures() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearEnabledSoftwareFeatures();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasEnrollmentSessionId() {
                return ((GcmDeviceInfo) this.instance).hasEnrollmentSessionId();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getEnrollmentSessionId() {
                return ((GcmDeviceInfo) this.instance).getEnrollmentSessionId();
            }

            public Builder setEnrollmentSessionId(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setEnrollmentSessionId(value);
                return this;
            }

            public Builder clearEnrollmentSessionId() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearEnrollmentSessionId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public boolean hasOauthToken() {
                return ((GcmDeviceInfo) this.instance).hasOauthToken();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public String getOauthToken() {
                return ((GcmDeviceInfo) this.instance).getOauthToken();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmDeviceInfoOrBuilder
            public ByteString getOauthTokenBytes() {
                return ((GcmDeviceInfo) this.instance).getOauthTokenBytes();
            }

            public Builder setOauthToken(String value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setOauthToken(value);
                return this;
            }

            public Builder clearOauthToken() {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).clearOauthToken();
                return this;
            }

            public Builder setOauthTokenBytes(ByteString value) {
                copyOnWrite();
                ((GcmDeviceInfo) this.instance).setOauthTokenBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new GcmDeviceInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "bitField1_", "androidDeviceId_", "userPublicKey_", "deviceModel_", "locale_", "keyHandle_", "counter_", "deviceOsVersion_", "deviceOsVersionCode_", "deviceOsRelease_", "deviceOsCodename_", "deviceSoftwareVersion_", "deviceSoftwareVersionCode_", "deviceSoftwarePackage_", "deviceDisplayDiagonalMils_", "deviceAuthzenVersion_", "longDeviceId_", "deviceManufacturer_", "deviceType_", DeviceType.internalGetVerifier(), "gcmRegistrationId_", "deviceMasterKeyHash_", "apnRegistrationId_", "notificationEnabled_", "bluetoothMacAddress_", "usingSecureScreenlock_", "autoUnlockScreenlockSupported_", "autoUnlockScreenlockEnabled_", "bluetoothRadioSupported_", "bluetoothRadioEnabled_", "mobileDataSupported_", "tetheringSupported_", "bleRadioSupported_", "pixelExperience_", "arcPlusPlus_", "isScreenlockStateFlaky_", "supportedSoftwareFeatures_", SoftwareFeature.internalGetVerifier(), "enabledSoftwareFeatures_", SoftwareFeature.internalGetVerifier(), "enrollmentSessionId_", "oauthToken_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001&\u0000\u0002\u0001ϩ&\u0000\u0002\u0001\u0001\u0005\u0000\u0004Ԋ\u0006\u0007\b\u0007\b\b\b\t\n\t\f\u0002\n\r\b\u000b\u000e\u0002\f\u000f\b\r\u0010\b\u000e\u0011\b\u000f\u0012\u0002\u0010\u0013\b\u0011\u0016\u0004\u0012\u0018\u0004\u0013\u001d\n\u0014\u001f\b\u0015 \f\u0016f\n\u0001g\n\u0005Ê\n\u0002Ë\u0007\u0003Į\b\u0004Ɛ\u0007\u0017Ƒ\u0007\u0018ƒ\u0007\u0019Ɠ\u0007\u001aƔ\u0007\u001bƕ\u0007\u001cƖ\u0007\u001dƗ\u0007\u001eƘ\u0007\u001fƙ\u0007 ƚ\u0007!ƛ\u001eƜ\u001eϨ\n\"ϩ\b#", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GcmDeviceInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (GcmDeviceInfo.class) {
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
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (arg0 == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static GcmDeviceInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GcmDeviceInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.SecureGcmProto$1, reason: invalid class name */
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

    public static final class GcmMetadata extends GeneratedMessageLite<GcmMetadata, Builder> implements GcmMetadataOrBuilder {
        private static final GcmMetadata DEFAULT_INSTANCE;
        private static volatile Parser<GcmMetadata> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private int type_;
        private int version_;

        private GcmMetadata() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
        public Type getType() {
            Type result = Type.forNumber(this.type_);
            return result == null ? Type.ENROLLMENT : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(Type value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.type_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.bitField0_ &= -2;
            this.type_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
        public int getVersion() {
            return this.version_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int value) {
            this.bitField0_ |= 2;
            this.version_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.bitField0_ &= -3;
            this.version_ = 0;
        }

        public static GcmMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GcmMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GcmMetadata parseFrom(InputStream input) throws IOException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GcmMetadata parseDelimitedFrom(InputStream input) throws IOException {
            return (GcmMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GcmMetadata parseFrom(CodedInputStream input) throws IOException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GcmMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GcmMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GcmMetadata prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GcmMetadata, Builder> implements GcmMetadataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GcmMetadata.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
            public boolean hasType() {
                return ((GcmMetadata) this.instance).hasType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
            public Type getType() {
                return ((GcmMetadata) this.instance).getType();
            }

            public Builder setType(Type value) {
                copyOnWrite();
                ((GcmMetadata) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((GcmMetadata) this.instance).clearType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
            public boolean hasVersion() {
                return ((GcmMetadata) this.instance).hasVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.GcmMetadataOrBuilder
            public int getVersion() {
                return ((GcmMetadata) this.instance).getVersion();
            }

            public Builder setVersion(int value) {
                copyOnWrite();
                ((GcmMetadata) this.instance).setVersion(value);
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((GcmMetadata) this.instance).clearVersion();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new GcmMetadata();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "type_", Type.internalGetVerifier(), "version_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԍ\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GcmMetadata> parser = PARSER;
                    if (parser == null) {
                        synchronized (GcmMetadata.class) {
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
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = (byte) (arg0 == null ? 0 : 1);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            GcmMetadata defaultInstance = new GcmMetadata();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(GcmMetadata.class, defaultInstance);
        }

        public static GcmMetadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GcmMetadata> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Tickle extends GeneratedMessageLite<Tickle, Builder> implements TickleOrBuilder {
        private static final Tickle DEFAULT_INSTANCE;
        public static final int EXPIRY_TIME_FIELD_NUMBER = 1;
        private static volatile Parser<Tickle> PARSER;
        private int bitField0_;
        private long expiryTime_;

        private Tickle() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.TickleOrBuilder
        public boolean hasExpiryTime() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.TickleOrBuilder
        public long getExpiryTime() {
            return this.expiryTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExpiryTime(long value) {
            this.bitField0_ |= 1;
            this.expiryTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExpiryTime() {
            this.bitField0_ &= -2;
            this.expiryTime_ = 0L;
        }

        public static Tickle parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Tickle parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Tickle parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Tickle parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Tickle parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Tickle parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Tickle parseFrom(InputStream input) throws IOException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Tickle parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Tickle parseDelimitedFrom(InputStream input) throws IOException {
            return (Tickle) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Tickle parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Tickle) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Tickle parseFrom(CodedInputStream input) throws IOException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Tickle parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Tickle) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Tickle prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Tickle, Builder> implements TickleOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Tickle.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.TickleOrBuilder
            public boolean hasExpiryTime() {
                return ((Tickle) this.instance).hasExpiryTime();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.TickleOrBuilder
            public long getExpiryTime() {
                return ((Tickle) this.instance).getExpiryTime();
            }

            public Builder setExpiryTime(long value) {
                copyOnWrite();
                ((Tickle) this.instance).setExpiryTime(value);
                return this;
            }

            public Builder clearExpiryTime() {
                copyOnWrite();
                ((Tickle) this.instance).clearExpiryTime();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Tickle();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "expiryTime_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0005\u0000", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Tickle> parser = PARSER;
                    if (parser == null) {
                        synchronized (Tickle.class) {
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
            Tickle defaultInstance = new Tickle();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Tickle.class, defaultInstance);
        }

        public static Tickle getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Tickle> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class LoginNotificationInfo extends GeneratedMessageLite<LoginNotificationInfo, Builder> implements LoginNotificationInfoOrBuilder {
        public static final int CREATION_TIME_FIELD_NUMBER = 2;
        private static final LoginNotificationInfo DEFAULT_INSTANCE;
        public static final int EMAIL_FIELD_NUMBER = 3;
        public static final int EVENT_TYPE_FIELD_NUMBER = 6;
        public static final int HOST_FIELD_NUMBER = 4;
        private static volatile Parser<LoginNotificationInfo> PARSER = null;
        public static final int SOURCE_FIELD_NUMBER = 5;
        private int bitField0_;
        private long creationTime_;
        private String email_ = "";
        private String host_ = "";
        private String source_ = "";
        private String eventType_ = "";

        private LoginNotificationInfo() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public boolean hasCreationTime() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public long getCreationTime() {
            return this.creationTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCreationTime(long value) {
            this.bitField0_ |= 1;
            this.creationTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCreationTime() {
            this.bitField0_ &= -2;
            this.creationTime_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public boolean hasEmail() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public String getEmail() {
            return this.email_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public ByteString getEmailBytes() {
            return ByteString.copyFromUtf8(this.email_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmail(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.email_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEmail() {
            this.bitField0_ &= -3;
            this.email_ = getDefaultInstance().getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmailBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.email_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public boolean hasHost() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public String getHost() {
            return this.host_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public ByteString getHostBytes() {
            return ByteString.copyFromUtf8(this.host_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHost(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.host_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHost() {
            this.bitField0_ &= -5;
            this.host_ = getDefaultInstance().getHost();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHostBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.host_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public boolean hasSource() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public String getSource() {
            return this.source_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public ByteString getSourceBytes() {
            return ByteString.copyFromUtf8(this.source_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSource(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.source_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSource() {
            this.bitField0_ &= -9;
            this.source_ = getDefaultInstance().getSource();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSourceBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.source_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public boolean hasEventType() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public String getEventType() {
            return this.eventType_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
        public ByteString getEventTypeBytes() {
            return ByteString.copyFromUtf8(this.eventType_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventType(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16;
            this.eventType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEventType() {
            this.bitField0_ &= -17;
            this.eventType_ = getDefaultInstance().getEventType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventTypeBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16;
            this.eventType_ = value.toStringUtf8();
        }

        public static LoginNotificationInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoginNotificationInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoginNotificationInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoginNotificationInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoginNotificationInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoginNotificationInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoginNotificationInfo parseFrom(InputStream input) throws IOException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoginNotificationInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoginNotificationInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (LoginNotificationInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LoginNotificationInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoginNotificationInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoginNotificationInfo parseFrom(CodedInputStream input) throws IOException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoginNotificationInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoginNotificationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(LoginNotificationInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LoginNotificationInfo, Builder> implements LoginNotificationInfoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(LoginNotificationInfo.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public boolean hasCreationTime() {
                return ((LoginNotificationInfo) this.instance).hasCreationTime();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public long getCreationTime() {
                return ((LoginNotificationInfo) this.instance).getCreationTime();
            }

            public Builder setCreationTime(long value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setCreationTime(value);
                return this;
            }

            public Builder clearCreationTime() {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).clearCreationTime();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public boolean hasEmail() {
                return ((LoginNotificationInfo) this.instance).hasEmail();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public String getEmail() {
                return ((LoginNotificationInfo) this.instance).getEmail();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public ByteString getEmailBytes() {
                return ((LoginNotificationInfo) this.instance).getEmailBytes();
            }

            public Builder setEmail(String value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setEmail(value);
                return this;
            }

            public Builder clearEmail() {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).clearEmail();
                return this;
            }

            public Builder setEmailBytes(ByteString value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setEmailBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public boolean hasHost() {
                return ((LoginNotificationInfo) this.instance).hasHost();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public String getHost() {
                return ((LoginNotificationInfo) this.instance).getHost();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public ByteString getHostBytes() {
                return ((LoginNotificationInfo) this.instance).getHostBytes();
            }

            public Builder setHost(String value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setHost(value);
                return this;
            }

            public Builder clearHost() {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).clearHost();
                return this;
            }

            public Builder setHostBytes(ByteString value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setHostBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public boolean hasSource() {
                return ((LoginNotificationInfo) this.instance).hasSource();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public String getSource() {
                return ((LoginNotificationInfo) this.instance).getSource();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public ByteString getSourceBytes() {
                return ((LoginNotificationInfo) this.instance).getSourceBytes();
            }

            public Builder setSource(String value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setSource(value);
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).clearSource();
                return this;
            }

            public Builder setSourceBytes(ByteString value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setSourceBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public boolean hasEventType() {
                return ((LoginNotificationInfo) this.instance).hasEventType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public String getEventType() {
                return ((LoginNotificationInfo) this.instance).getEventType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProto.LoginNotificationInfoOrBuilder
            public ByteString getEventTypeBytes() {
                return ((LoginNotificationInfo) this.instance).getEventTypeBytes();
            }

            public Builder setEventType(String value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setEventType(value);
                return this;
            }

            public Builder clearEventType() {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).clearEventType();
                return this;
            }

            public Builder setEventTypeBytes(ByteString value) {
                copyOnWrite();
                ((LoginNotificationInfo) this.instance).setEventTypeBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new LoginNotificationInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "creationTime_", "email_", "host_", "source_", "eventType_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0002\u0006\u0005\u0000\u0000\u0000\u0002\u0005\u0000\u0003\b\u0001\u0004\b\u0002\u0005\b\u0003\u0006\b\u0004", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<LoginNotificationInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (LoginNotificationInfo.class) {
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
            LoginNotificationInfo defaultInstance = new LoginNotificationInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(LoginNotificationInfo.class, defaultInstance);
        }

        public static LoginNotificationInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LoginNotificationInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.security.cryptauth.lib.securegcm.SecureGcmProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class SecureGcmProximityAuthProto {

    public interface CloudToDeviceProximityAuthPairingOrBuilder extends MessageLiteOrBuilder {
        ByteString getAdditionalMetadata();

        ByteString getEphemeralSymmetricKey();

        String getInitiatingDeviceBtAddress();

        ByteString getInitiatingDeviceBtAddressBytes();

        String getInitiatingDeviceName();

        ByteString getInitiatingDeviceNameBytes();

        boolean hasAdditionalMetadata();

        boolean hasEphemeralSymmetricKey();

        boolean hasInitiatingDeviceBtAddress();

        boolean hasInitiatingDeviceName();
    }

    public interface DeviceProximityCallbackOrBuilder extends MessageLiteOrBuilder {
        String getCallbackBluetoothAddress();

        ByteString getCallbackBluetoothAddressBytes();

        int getProtocolVersion();

        SecureGcmProto.DeviceType getSourceDeviceType();

        boolean hasCallbackBluetoothAddress();

        boolean hasProtocolVersion();

        boolean hasSourceDeviceType();
    }

    private SecureGcmProximityAuthProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class CloudToDeviceProximityAuthPairing extends GeneratedMessageLite<CloudToDeviceProximityAuthPairing, Builder> implements CloudToDeviceProximityAuthPairingOrBuilder {
        public static final int ADDITIONAL_METADATA_FIELD_NUMBER = 4;
        private static final CloudToDeviceProximityAuthPairing DEFAULT_INSTANCE;
        public static final int EPHEMERAL_SYMMETRIC_KEY_FIELD_NUMBER = 3;
        public static final int INITIATING_DEVICE_BT_ADDRESS_FIELD_NUMBER = 2;
        public static final int INITIATING_DEVICE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<CloudToDeviceProximityAuthPairing> PARSER;
        private int bitField0_;
        private String initiatingDeviceName_ = "";
        private String initiatingDeviceBtAddress_ = "";
        private ByteString ephemeralSymmetricKey_ = ByteString.EMPTY;
        private ByteString additionalMetadata_ = ByteString.EMPTY;

        private CloudToDeviceProximityAuthPairing() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public boolean hasInitiatingDeviceName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public String getInitiatingDeviceName() {
            return this.initiatingDeviceName_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public ByteString getInitiatingDeviceNameBytes() {
            return ByteString.copyFromUtf8(this.initiatingDeviceName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiatingDeviceName(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.initiatingDeviceName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiatingDeviceName() {
            this.bitField0_ &= -2;
            this.initiatingDeviceName_ = getDefaultInstance().getInitiatingDeviceName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiatingDeviceNameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.initiatingDeviceName_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public boolean hasInitiatingDeviceBtAddress() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public String getInitiatingDeviceBtAddress() {
            return this.initiatingDeviceBtAddress_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public ByteString getInitiatingDeviceBtAddressBytes() {
            return ByteString.copyFromUtf8(this.initiatingDeviceBtAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiatingDeviceBtAddress(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.initiatingDeviceBtAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiatingDeviceBtAddress() {
            this.bitField0_ &= -3;
            this.initiatingDeviceBtAddress_ = getDefaultInstance().getInitiatingDeviceBtAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiatingDeviceBtAddressBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.initiatingDeviceBtAddress_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public boolean hasEphemeralSymmetricKey() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public ByteString getEphemeralSymmetricKey() {
            return this.ephemeralSymmetricKey_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEphemeralSymmetricKey(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.ephemeralSymmetricKey_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEphemeralSymmetricKey() {
            this.bitField0_ &= -5;
            this.ephemeralSymmetricKey_ = getDefaultInstance().getEphemeralSymmetricKey();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public boolean hasAdditionalMetadata() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
        public ByteString getAdditionalMetadata() {
            return this.additionalMetadata_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAdditionalMetadata(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.additionalMetadata_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAdditionalMetadata() {
            this.bitField0_ &= -9;
            this.additionalMetadata_ = getDefaultInstance().getAdditionalMetadata();
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(InputStream input) throws IOException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CloudToDeviceProximityAuthPairing parseDelimitedFrom(InputStream input) throws IOException {
            return (CloudToDeviceProximityAuthPairing) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CloudToDeviceProximityAuthPairing parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CloudToDeviceProximityAuthPairing) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(CodedInputStream input) throws IOException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CloudToDeviceProximityAuthPairing parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CloudToDeviceProximityAuthPairing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CloudToDeviceProximityAuthPairing prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CloudToDeviceProximityAuthPairing, Builder> implements CloudToDeviceProximityAuthPairingOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(CloudToDeviceProximityAuthPairing.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public boolean hasInitiatingDeviceName() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).hasInitiatingDeviceName();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public String getInitiatingDeviceName() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getInitiatingDeviceName();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public ByteString getInitiatingDeviceNameBytes() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getInitiatingDeviceNameBytes();
            }

            public Builder setInitiatingDeviceName(String value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setInitiatingDeviceName(value);
                return this;
            }

            public Builder clearInitiatingDeviceName() {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).clearInitiatingDeviceName();
                return this;
            }

            public Builder setInitiatingDeviceNameBytes(ByteString value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setInitiatingDeviceNameBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public boolean hasInitiatingDeviceBtAddress() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).hasInitiatingDeviceBtAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public String getInitiatingDeviceBtAddress() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getInitiatingDeviceBtAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public ByteString getInitiatingDeviceBtAddressBytes() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getInitiatingDeviceBtAddressBytes();
            }

            public Builder setInitiatingDeviceBtAddress(String value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setInitiatingDeviceBtAddress(value);
                return this;
            }

            public Builder clearInitiatingDeviceBtAddress() {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).clearInitiatingDeviceBtAddress();
                return this;
            }

            public Builder setInitiatingDeviceBtAddressBytes(ByteString value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setInitiatingDeviceBtAddressBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public boolean hasEphemeralSymmetricKey() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).hasEphemeralSymmetricKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public ByteString getEphemeralSymmetricKey() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getEphemeralSymmetricKey();
            }

            public Builder setEphemeralSymmetricKey(ByteString value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setEphemeralSymmetricKey(value);
                return this;
            }

            public Builder clearEphemeralSymmetricKey() {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).clearEphemeralSymmetricKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public boolean hasAdditionalMetadata() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).hasAdditionalMetadata();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.CloudToDeviceProximityAuthPairingOrBuilder
            public ByteString getAdditionalMetadata() {
                return ((CloudToDeviceProximityAuthPairing) this.instance).getAdditionalMetadata();
            }

            public Builder setAdditionalMetadata(ByteString value) {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).setAdditionalMetadata(value);
                return this;
            }

            public Builder clearAdditionalMetadata() {
                copyOnWrite();
                ((CloudToDeviceProximityAuthPairing) this.instance).clearAdditionalMetadata();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new CloudToDeviceProximityAuthPairing();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "initiatingDeviceName_", "initiatingDeviceBtAddress_", "ephemeralSymmetricKey_", "additionalMetadata_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\n\u0002\u0004\n\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CloudToDeviceProximityAuthPairing> parser = PARSER;
                    if (parser == null) {
                        synchronized (CloudToDeviceProximityAuthPairing.class) {
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
            CloudToDeviceProximityAuthPairing defaultInstance = new CloudToDeviceProximityAuthPairing();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(CloudToDeviceProximityAuthPairing.class, defaultInstance);
        }

        public static CloudToDeviceProximityAuthPairing getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CloudToDeviceProximityAuthPairing> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto$1, reason: invalid class name */
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

    public static final class DeviceProximityCallback extends GeneratedMessageLite<DeviceProximityCallback, Builder> implements DeviceProximityCallbackOrBuilder {
        public static final int CALLBACK_BLUETOOTH_ADDRESS_FIELD_NUMBER = 1;
        private static final DeviceProximityCallback DEFAULT_INSTANCE;
        private static volatile Parser<DeviceProximityCallback> PARSER = null;
        public static final int PROTOCOL_VERSION_FIELD_NUMBER = 3;
        public static final int SOURCE_DEVICE_TYPE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String callbackBluetoothAddress_ = "";
        private int protocolVersion_;
        private int sourceDeviceType_;

        private DeviceProximityCallback() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public boolean hasCallbackBluetoothAddress() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public String getCallbackBluetoothAddress() {
            return this.callbackBluetoothAddress_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public ByteString getCallbackBluetoothAddressBytes() {
            return ByteString.copyFromUtf8(this.callbackBluetoothAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallbackBluetoothAddress(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.callbackBluetoothAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCallbackBluetoothAddress() {
            this.bitField0_ &= -2;
            this.callbackBluetoothAddress_ = getDefaultInstance().getCallbackBluetoothAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCallbackBluetoothAddressBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.callbackBluetoothAddress_ = value.toStringUtf8();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public boolean hasSourceDeviceType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public SecureGcmProto.DeviceType getSourceDeviceType() {
            SecureGcmProto.DeviceType result = SecureGcmProto.DeviceType.forNumber(this.sourceDeviceType_);
            return result == null ? SecureGcmProto.DeviceType.UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSourceDeviceType(SecureGcmProto.DeviceType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.sourceDeviceType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSourceDeviceType() {
            this.bitField0_ &= -3;
            this.sourceDeviceType_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public boolean hasProtocolVersion() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
        public int getProtocolVersion() {
            return this.protocolVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocolVersion(int value) {
            this.bitField0_ |= 4;
            this.protocolVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtocolVersion() {
            this.bitField0_ &= -5;
            this.protocolVersion_ = 0;
        }

        public static DeviceProximityCallback parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceProximityCallback parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceProximityCallback parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceProximityCallback parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceProximityCallback parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceProximityCallback parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceProximityCallback parseFrom(InputStream input) throws IOException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceProximityCallback parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceProximityCallback parseDelimitedFrom(InputStream input) throws IOException {
            return (DeviceProximityCallback) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceProximityCallback parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceProximityCallback) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceProximityCallback parseFrom(CodedInputStream input) throws IOException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceProximityCallback parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceProximityCallback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DeviceProximityCallback prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceProximityCallback, Builder> implements DeviceProximityCallbackOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DeviceProximityCallback.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public boolean hasCallbackBluetoothAddress() {
                return ((DeviceProximityCallback) this.instance).hasCallbackBluetoothAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public String getCallbackBluetoothAddress() {
                return ((DeviceProximityCallback) this.instance).getCallbackBluetoothAddress();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public ByteString getCallbackBluetoothAddressBytes() {
                return ((DeviceProximityCallback) this.instance).getCallbackBluetoothAddressBytes();
            }

            public Builder setCallbackBluetoothAddress(String value) {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).setCallbackBluetoothAddress(value);
                return this;
            }

            public Builder clearCallbackBluetoothAddress() {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).clearCallbackBluetoothAddress();
                return this;
            }

            public Builder setCallbackBluetoothAddressBytes(ByteString value) {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).setCallbackBluetoothAddressBytes(value);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public boolean hasSourceDeviceType() {
                return ((DeviceProximityCallback) this.instance).hasSourceDeviceType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public SecureGcmProto.DeviceType getSourceDeviceType() {
                return ((DeviceProximityCallback) this.instance).getSourceDeviceType();
            }

            public Builder setSourceDeviceType(SecureGcmProto.DeviceType value) {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).setSourceDeviceType(value);
                return this;
            }

            public Builder clearSourceDeviceType() {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).clearSourceDeviceType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public boolean hasProtocolVersion() {
                return ((DeviceProximityCallback) this.instance).hasProtocolVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmProximityAuthProto.DeviceProximityCallbackOrBuilder
            public int getProtocolVersion() {
                return ((DeviceProximityCallback) this.instance).getProtocolVersion();
            }

            public Builder setProtocolVersion(int value) {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).setProtocolVersion(value);
                return this;
            }

            public Builder clearProtocolVersion() {
                copyOnWrite();
                ((DeviceProximityCallback) this.instance).clearProtocolVersion();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new DeviceProximityCallback();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "callbackBluetoothAddress_", "sourceDeviceType_", SecureGcmProto.DeviceType.internalGetVerifier(), "protocolVersion_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\f\u0001\u0003\u0004\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DeviceProximityCallback> parser = PARSER;
                    if (parser == null) {
                        synchronized (DeviceProximityCallback.class) {
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
            DeviceProximityCallback defaultInstance = new DeviceProximityCallback();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(DeviceProximityCallback.class, defaultInstance);
        }

        public static DeviceProximityCallback getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceProximityCallback> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

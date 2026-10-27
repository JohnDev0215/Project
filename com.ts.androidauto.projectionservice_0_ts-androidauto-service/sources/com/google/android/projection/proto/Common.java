package com.google.android.projection.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Common {

    public interface HeadUnitInfoOrBuilder extends MessageLiteOrBuilder {
        String getHeadUnitMake();

        ByteString getHeadUnitMakeBytes();

        String getHeadUnitModel();

        ByteString getHeadUnitModelBytes();

        String getHeadUnitSoftwareBuild();

        ByteString getHeadUnitSoftwareBuildBytes();

        String getHeadUnitSoftwareVersion();

        ByteString getHeadUnitSoftwareVersionBytes();

        String getMake();

        ByteString getMakeBytes();

        String getModel();

        ByteString getModelBytes();

        String getVehicleId();

        ByteString getVehicleIdBytes();

        String getYear();

        ByteString getYearBytes();

        boolean hasHeadUnitMake();

        boolean hasHeadUnitModel();

        boolean hasHeadUnitSoftwareBuild();

        boolean hasHeadUnitSoftwareVersion();

        boolean hasMake();

        boolean hasModel();

        boolean hasVehicleId();

        boolean hasYear();
    }

    public interface PhoneInfoOrBuilder extends MessageLiteOrBuilder {
        String getBluetoothAddress();

        ByteString getBluetoothAddressBytes();

        String getConnectivityLifetimeId();

        ByteString getConnectivityLifetimeIdBytes();

        String getDeviceName();

        ByteString getDeviceNameBytes();

        String getInstanceId();

        ByteString getInstanceIdBytes();

        boolean hasBluetoothAddress();

        boolean hasConnectivityLifetimeId();

        boolean hasDeviceName();

        boolean hasInstanceId();
    }

    private Common() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class PhoneInfo extends GeneratedMessageLite<PhoneInfo, Builder> implements PhoneInfoOrBuilder {
        public static final int BLUETOOTH_ADDRESS_FIELD_NUMBER = 4;
        public static final int CONNECTIVITY_LIFETIME_ID_FIELD_NUMBER = 2;
        private static final PhoneInfo DEFAULT_INSTANCE;
        public static final int DEVICE_NAME_FIELD_NUMBER = 3;
        public static final int INSTANCE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<PhoneInfo> PARSER;
        private int bitField0_;
        private String instanceId_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String connectivityLifetimeId_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String deviceName_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String bluetoothAddress_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

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

        private PhoneInfo() {
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public boolean hasInstanceId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public String getInstanceId() {
            return this.instanceId_;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public ByteString getInstanceIdBytes() {
            return ByteString.copyFromUtf8(this.instanceId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInstanceId(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.instanceId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInstanceId() {
            this.bitField0_ &= -2;
            this.instanceId_ = getDefaultInstance().getInstanceId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInstanceIdBytes(ByteString value) {
            this.instanceId_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public boolean hasConnectivityLifetimeId() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public String getConnectivityLifetimeId() {
            return this.connectivityLifetimeId_;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public ByteString getConnectivityLifetimeIdBytes() {
            return ByteString.copyFromUtf8(this.connectivityLifetimeId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectivityLifetimeId(String value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.connectivityLifetimeId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectivityLifetimeId() {
            this.bitField0_ &= -3;
            this.connectivityLifetimeId_ = getDefaultInstance().getConnectivityLifetimeId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectivityLifetimeIdBytes(ByteString value) {
            this.connectivityLifetimeId_ = value.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public boolean hasDeviceName() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public String getDeviceName() {
            return this.deviceName_;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public ByteString getDeviceNameBytes() {
            return ByteString.copyFromUtf8(this.deviceName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceName(String value) {
            value.getClass();
            this.bitField0_ |= 4;
            this.deviceName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceName() {
            this.bitField0_ &= -5;
            this.deviceName_ = getDefaultInstance().getDeviceName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceNameBytes(ByteString value) {
            this.deviceName_ = value.toStringUtf8();
            this.bitField0_ |= 4;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public boolean hasBluetoothAddress() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public String getBluetoothAddress() {
            return this.bluetoothAddress_;
        }

        @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
        public ByteString getBluetoothAddressBytes() {
            return ByteString.copyFromUtf8(this.bluetoothAddress_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothAddress(String value) {
            value.getClass();
            this.bitField0_ |= 8;
            this.bluetoothAddress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothAddress() {
            this.bitField0_ &= -9;
            this.bluetoothAddress_ = getDefaultInstance().getBluetoothAddress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothAddressBytes(ByteString value) {
            this.bluetoothAddress_ = value.toStringUtf8();
            this.bitField0_ |= 8;
        }

        public static PhoneInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneInfo parseFrom(InputStream input) throws IOException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (PhoneInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneInfo parseFrom(CodedInputStream input) throws IOException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PhoneInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PhoneInfo, Builder> implements PhoneInfoOrBuilder {
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
                super(PhoneInfo.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public boolean hasInstanceId() {
                return ((PhoneInfo) this.instance).hasInstanceId();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public String getInstanceId() {
                return ((PhoneInfo) this.instance).getInstanceId();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public ByteString getInstanceIdBytes() {
                return ((PhoneInfo) this.instance).getInstanceIdBytes();
            }

            public Builder setInstanceId(String value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setInstanceId(value);
                return this;
            }

            public Builder clearInstanceId() {
                copyOnWrite();
                ((PhoneInfo) this.instance).clearInstanceId();
                return this;
            }

            public Builder setInstanceIdBytes(ByteString value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setInstanceIdBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public boolean hasConnectivityLifetimeId() {
                return ((PhoneInfo) this.instance).hasConnectivityLifetimeId();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public String getConnectivityLifetimeId() {
                return ((PhoneInfo) this.instance).getConnectivityLifetimeId();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public ByteString getConnectivityLifetimeIdBytes() {
                return ((PhoneInfo) this.instance).getConnectivityLifetimeIdBytes();
            }

            public Builder setConnectivityLifetimeId(String value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setConnectivityLifetimeId(value);
                return this;
            }

            public Builder clearConnectivityLifetimeId() {
                copyOnWrite();
                ((PhoneInfo) this.instance).clearConnectivityLifetimeId();
                return this;
            }

            public Builder setConnectivityLifetimeIdBytes(ByteString value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setConnectivityLifetimeIdBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public boolean hasDeviceName() {
                return ((PhoneInfo) this.instance).hasDeviceName();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public String getDeviceName() {
                return ((PhoneInfo) this.instance).getDeviceName();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public ByteString getDeviceNameBytes() {
                return ((PhoneInfo) this.instance).getDeviceNameBytes();
            }

            public Builder setDeviceName(String value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setDeviceName(value);
                return this;
            }

            public Builder clearDeviceName() {
                copyOnWrite();
                ((PhoneInfo) this.instance).clearDeviceName();
                return this;
            }

            public Builder setDeviceNameBytes(ByteString value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setDeviceNameBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public boolean hasBluetoothAddress() {
                return ((PhoneInfo) this.instance).hasBluetoothAddress();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public String getBluetoothAddress() {
                return ((PhoneInfo) this.instance).getBluetoothAddress();
            }

            @Override // com.google.android.projection.proto.Common.PhoneInfoOrBuilder
            public ByteString getBluetoothAddressBytes() {
                return ((PhoneInfo) this.instance).getBluetoothAddressBytes();
            }

            public Builder setBluetoothAddress(String value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setBluetoothAddress(value);
                return this;
            }

            public Builder clearBluetoothAddress() {
                copyOnWrite();
                ((PhoneInfo) this.instance).clearBluetoothAddress();
                return this;
            }

            public Builder setBluetoothAddressBytes(ByteString value) {
                copyOnWrite();
                ((PhoneInfo) this.instance).setBluetoothAddressBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PhoneInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "instanceId_", "connectivityLifetimeId_", "deviceName_", "bluetoothAddress_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PhoneInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (PhoneInfo.class) {
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
            PhoneInfo defaultInstance = new PhoneInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PhoneInfo.class, defaultInstance);
        }

        public static PhoneInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PhoneInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.android.projection.proto.Common$1, reason: invalid class name */
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

    public static final class HeadUnitInfo extends GeneratedMessageLite<HeadUnitInfo, Builder> implements HeadUnitInfoOrBuilder {
        private static final HeadUnitInfo DEFAULT_INSTANCE;
        public static final int HEAD_UNIT_MAKE_FIELD_NUMBER = 5;
        public static final int HEAD_UNIT_MODEL_FIELD_NUMBER = 6;
        public static final int HEAD_UNIT_SOFTWARE_BUILD_FIELD_NUMBER = 7;
        public static final int HEAD_UNIT_SOFTWARE_VERSION_FIELD_NUMBER = 8;
        public static final int MAKE_FIELD_NUMBER = 1;
        public static final int MODEL_FIELD_NUMBER = 2;
        private static volatile Parser<HeadUnitInfo> PARSER = null;
        public static final int VEHICLE_ID_FIELD_NUMBER = 4;
        public static final int YEAR_FIELD_NUMBER = 3;
        private int bitField0_;
        private String make_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String model_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String year_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String vehicleId_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String headUnitMake_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String headUnitModel_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String headUnitSoftwareBuild_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private String headUnitSoftwareVersion_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

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

        private HeadUnitInfo() {
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasMake() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getMake() {
            return this.make_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getMakeBytes() {
            return ByteString.copyFromUtf8(this.make_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMake(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.make_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMake() {
            this.bitField0_ &= -2;
            this.make_ = getDefaultInstance().getMake();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMakeBytes(ByteString value) {
            this.make_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasModel() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getModel() {
            return this.model_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getModelBytes() {
            return ByteString.copyFromUtf8(this.model_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModel(String value) {
            value.getClass();
            this.bitField0_ |= 2;
            this.model_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModel() {
            this.bitField0_ &= -3;
            this.model_ = getDefaultInstance().getModel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModelBytes(ByteString value) {
            this.model_ = value.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasYear() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getYear() {
            return this.year_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getYearBytes() {
            return ByteString.copyFromUtf8(this.year_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setYear(String value) {
            value.getClass();
            this.bitField0_ |= 4;
            this.year_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearYear() {
            this.bitField0_ &= -5;
            this.year_ = getDefaultInstance().getYear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setYearBytes(ByteString value) {
            this.year_ = value.toStringUtf8();
            this.bitField0_ |= 4;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasVehicleId() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getVehicleId() {
            return this.vehicleId_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getVehicleIdBytes() {
            return ByteString.copyFromUtf8(this.vehicleId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVehicleId(String value) {
            value.getClass();
            this.bitField0_ |= 8;
            this.vehicleId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVehicleId() {
            this.bitField0_ &= -9;
            this.vehicleId_ = getDefaultInstance().getVehicleId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVehicleIdBytes(ByteString value) {
            this.vehicleId_ = value.toStringUtf8();
            this.bitField0_ |= 8;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasHeadUnitMake() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getHeadUnitMake() {
            return this.headUnitMake_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getHeadUnitMakeBytes() {
            return ByteString.copyFromUtf8(this.headUnitMake_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitMake(String value) {
            value.getClass();
            this.bitField0_ |= 16;
            this.headUnitMake_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadUnitMake() {
            this.bitField0_ &= -17;
            this.headUnitMake_ = getDefaultInstance().getHeadUnitMake();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitMakeBytes(ByteString value) {
            this.headUnitMake_ = value.toStringUtf8();
            this.bitField0_ |= 16;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasHeadUnitModel() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getHeadUnitModel() {
            return this.headUnitModel_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getHeadUnitModelBytes() {
            return ByteString.copyFromUtf8(this.headUnitModel_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitModel(String value) {
            value.getClass();
            this.bitField0_ |= 32;
            this.headUnitModel_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadUnitModel() {
            this.bitField0_ &= -33;
            this.headUnitModel_ = getDefaultInstance().getHeadUnitModel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitModelBytes(ByteString value) {
            this.headUnitModel_ = value.toStringUtf8();
            this.bitField0_ |= 32;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasHeadUnitSoftwareBuild() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getHeadUnitSoftwareBuild() {
            return this.headUnitSoftwareBuild_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getHeadUnitSoftwareBuildBytes() {
            return ByteString.copyFromUtf8(this.headUnitSoftwareBuild_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitSoftwareBuild(String value) {
            value.getClass();
            this.bitField0_ |= 64;
            this.headUnitSoftwareBuild_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadUnitSoftwareBuild() {
            this.bitField0_ &= -65;
            this.headUnitSoftwareBuild_ = getDefaultInstance().getHeadUnitSoftwareBuild();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitSoftwareBuildBytes(ByteString value) {
            this.headUnitSoftwareBuild_ = value.toStringUtf8();
            this.bitField0_ |= 64;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public boolean hasHeadUnitSoftwareVersion() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public String getHeadUnitSoftwareVersion() {
            return this.headUnitSoftwareVersion_;
        }

        @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
        public ByteString getHeadUnitSoftwareVersionBytes() {
            return ByteString.copyFromUtf8(this.headUnitSoftwareVersion_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitSoftwareVersion(String value) {
            value.getClass();
            this.bitField0_ |= 128;
            this.headUnitSoftwareVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadUnitSoftwareVersion() {
            this.bitField0_ &= -129;
            this.headUnitSoftwareVersion_ = getDefaultInstance().getHeadUnitSoftwareVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadUnitSoftwareVersionBytes(ByteString value) {
            this.headUnitSoftwareVersion_ = value.toStringUtf8();
            this.bitField0_ |= 128;
        }

        public static HeadUnitInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadUnitInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadUnitInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadUnitInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadUnitInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadUnitInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadUnitInfo parseFrom(InputStream input) throws IOException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadUnitInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeadUnitInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (HeadUnitInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadUnitInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadUnitInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeadUnitInfo parseFrom(CodedInputStream input) throws IOException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadUnitInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadUnitInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(HeadUnitInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HeadUnitInfo, Builder> implements HeadUnitInfoOrBuilder {
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
            /* JADX INFO: renamed from: clone, reason: collision with other method in class */
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
                super(HeadUnitInfo.DEFAULT_INSTANCE);
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasMake() {
                return ((HeadUnitInfo) this.instance).hasMake();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getMake() {
                return ((HeadUnitInfo) this.instance).getMake();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getMakeBytes() {
                return ((HeadUnitInfo) this.instance).getMakeBytes();
            }

            public Builder setMake(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setMake(value);
                return this;
            }

            public Builder clearMake() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearMake();
                return this;
            }

            public Builder setMakeBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setMakeBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasModel() {
                return ((HeadUnitInfo) this.instance).hasModel();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getModel() {
                return ((HeadUnitInfo) this.instance).getModel();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getModelBytes() {
                return ((HeadUnitInfo) this.instance).getModelBytes();
            }

            public Builder setModel(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setModel(value);
                return this;
            }

            public Builder clearModel() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearModel();
                return this;
            }

            public Builder setModelBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setModelBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasYear() {
                return ((HeadUnitInfo) this.instance).hasYear();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getYear() {
                return ((HeadUnitInfo) this.instance).getYear();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getYearBytes() {
                return ((HeadUnitInfo) this.instance).getYearBytes();
            }

            public Builder setYear(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setYear(value);
                return this;
            }

            public Builder clearYear() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearYear();
                return this;
            }

            public Builder setYearBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setYearBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasVehicleId() {
                return ((HeadUnitInfo) this.instance).hasVehicleId();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getVehicleId() {
                return ((HeadUnitInfo) this.instance).getVehicleId();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getVehicleIdBytes() {
                return ((HeadUnitInfo) this.instance).getVehicleIdBytes();
            }

            public Builder setVehicleId(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setVehicleId(value);
                return this;
            }

            public Builder clearVehicleId() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearVehicleId();
                return this;
            }

            public Builder setVehicleIdBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setVehicleIdBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasHeadUnitMake() {
                return ((HeadUnitInfo) this.instance).hasHeadUnitMake();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getHeadUnitMake() {
                return ((HeadUnitInfo) this.instance).getHeadUnitMake();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getHeadUnitMakeBytes() {
                return ((HeadUnitInfo) this.instance).getHeadUnitMakeBytes();
            }

            public Builder setHeadUnitMake(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitMake(value);
                return this;
            }

            public Builder clearHeadUnitMake() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearHeadUnitMake();
                return this;
            }

            public Builder setHeadUnitMakeBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitMakeBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasHeadUnitModel() {
                return ((HeadUnitInfo) this.instance).hasHeadUnitModel();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getHeadUnitModel() {
                return ((HeadUnitInfo) this.instance).getHeadUnitModel();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getHeadUnitModelBytes() {
                return ((HeadUnitInfo) this.instance).getHeadUnitModelBytes();
            }

            public Builder setHeadUnitModel(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitModel(value);
                return this;
            }

            public Builder clearHeadUnitModel() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearHeadUnitModel();
                return this;
            }

            public Builder setHeadUnitModelBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitModelBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasHeadUnitSoftwareBuild() {
                return ((HeadUnitInfo) this.instance).hasHeadUnitSoftwareBuild();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getHeadUnitSoftwareBuild() {
                return ((HeadUnitInfo) this.instance).getHeadUnitSoftwareBuild();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getHeadUnitSoftwareBuildBytes() {
                return ((HeadUnitInfo) this.instance).getHeadUnitSoftwareBuildBytes();
            }

            public Builder setHeadUnitSoftwareBuild(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitSoftwareBuild(value);
                return this;
            }

            public Builder clearHeadUnitSoftwareBuild() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearHeadUnitSoftwareBuild();
                return this;
            }

            public Builder setHeadUnitSoftwareBuildBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitSoftwareBuildBytes(value);
                return this;
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public boolean hasHeadUnitSoftwareVersion() {
                return ((HeadUnitInfo) this.instance).hasHeadUnitSoftwareVersion();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public String getHeadUnitSoftwareVersion() {
                return ((HeadUnitInfo) this.instance).getHeadUnitSoftwareVersion();
            }

            @Override // com.google.android.projection.proto.Common.HeadUnitInfoOrBuilder
            public ByteString getHeadUnitSoftwareVersionBytes() {
                return ((HeadUnitInfo) this.instance).getHeadUnitSoftwareVersionBytes();
            }

            public Builder setHeadUnitSoftwareVersion(String value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitSoftwareVersion(value);
                return this;
            }

            public Builder clearHeadUnitSoftwareVersion() {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).clearHeadUnitSoftwareVersion();
                return this;
            }

            public Builder setHeadUnitSoftwareVersionBytes(ByteString value) {
                copyOnWrite();
                ((HeadUnitInfo) this.instance).setHeadUnitSoftwareVersionBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new HeadUnitInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "make_", "model_", "year_", "vehicleId_", "headUnitMake_", "headUnitModel_", "headUnitSoftwareBuild_", "headUnitSoftwareVersion_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<HeadUnitInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (HeadUnitInfo.class) {
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
            HeadUnitInfo defaultInstance = new HeadUnitInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(HeadUnitInfo.class, defaultInstance);
        }

        public static HeadUnitInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeadUnitInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

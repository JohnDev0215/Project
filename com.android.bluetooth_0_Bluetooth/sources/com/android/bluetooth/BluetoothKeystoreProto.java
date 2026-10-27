package com.android.bluetooth;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothKeystoreProto {

    public interface EncryptedDataOrBuilder extends MessageLiteOrBuilder {
        ByteString getAuthenticationData();

        ByteString getEncryptedData();

        ByteString getInitVector();

        boolean hasAuthenticationData();

        boolean hasEncryptedData();

        boolean hasInitVector();
    }

    private BluetoothKeystoreProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class EncryptedData extends GeneratedMessageLite<EncryptedData, Builder> implements EncryptedDataOrBuilder {
        public static final int AUTHENTICATION_DATA_FIELD_NUMBER = 2;
        private static final EncryptedData DEFAULT_INSTANCE;
        public static final int ENCRYPTED_DATA_FIELD_NUMBER = 3;
        public static final int INIT_VECTOR_FIELD_NUMBER = 1;
        private static volatile Parser<EncryptedData> PARSER;
        private int bitField0_;
        private ByteString initVector_ = ByteString.EMPTY;
        private ByteString authenticationData_ = ByteString.EMPTY;
        private ByteString encryptedData_ = ByteString.EMPTY;

        private EncryptedData() {
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public boolean hasInitVector() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public ByteString getInitVector() {
            return this.initVector_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitVector(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.initVector_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitVector() {
            this.bitField0_ &= -2;
            this.initVector_ = getDefaultInstance().getInitVector();
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public boolean hasAuthenticationData() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public ByteString getAuthenticationData() {
            return this.authenticationData_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthenticationData(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.authenticationData_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthenticationData() {
            this.bitField0_ &= -3;
            this.authenticationData_ = getDefaultInstance().getAuthenticationData();
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public boolean hasEncryptedData() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
        public ByteString getEncryptedData() {
            return this.encryptedData_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEncryptedData(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.encryptedData_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEncryptedData() {
            this.bitField0_ &= -5;
            this.encryptedData_ = getDefaultInstance().getEncryptedData();
        }

        public static EncryptedData parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EncryptedData parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EncryptedData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EncryptedData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EncryptedData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EncryptedData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EncryptedData parseFrom(InputStream input) throws IOException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EncryptedData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EncryptedData parseDelimitedFrom(InputStream input) throws IOException {
            return (EncryptedData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static EncryptedData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EncryptedData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EncryptedData parseFrom(CodedInputStream input) throws IOException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EncryptedData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EncryptedData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(EncryptedData prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<EncryptedData, Builder> implements EncryptedDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(EncryptedData.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public boolean hasInitVector() {
                return ((EncryptedData) this.instance).hasInitVector();
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public ByteString getInitVector() {
                return ((EncryptedData) this.instance).getInitVector();
            }

            public Builder setInitVector(ByteString value) {
                copyOnWrite();
                ((EncryptedData) this.instance).setInitVector(value);
                return this;
            }

            public Builder clearInitVector() {
                copyOnWrite();
                ((EncryptedData) this.instance).clearInitVector();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public boolean hasAuthenticationData() {
                return ((EncryptedData) this.instance).hasAuthenticationData();
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public ByteString getAuthenticationData() {
                return ((EncryptedData) this.instance).getAuthenticationData();
            }

            public Builder setAuthenticationData(ByteString value) {
                copyOnWrite();
                ((EncryptedData) this.instance).setAuthenticationData(value);
                return this;
            }

            public Builder clearAuthenticationData() {
                copyOnWrite();
                ((EncryptedData) this.instance).clearAuthenticationData();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public boolean hasEncryptedData() {
                return ((EncryptedData) this.instance).hasEncryptedData();
            }

            @Override // com.android.bluetooth.BluetoothKeystoreProto.EncryptedDataOrBuilder
            public ByteString getEncryptedData() {
                return ((EncryptedData) this.instance).getEncryptedData();
            }

            public Builder setEncryptedData(ByteString value) {
                copyOnWrite();
                ((EncryptedData) this.instance).setEncryptedData(value);
                return this;
            }

            public Builder clearEncryptedData() {
                copyOnWrite();
                ((EncryptedData) this.instance).clearEncryptedData();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new EncryptedData();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "initVector_", "authenticationData_", "encryptedData_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<EncryptedData> parser = PARSER;
                    if (parser == null) {
                        synchronized (EncryptedData.class) {
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
            EncryptedData defaultInstance = new EncryptedData();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(EncryptedData.class, defaultInstance);
        }

        public static EncryptedData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EncryptedData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.BluetoothKeystoreProto$1, reason: invalid class name */
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
}

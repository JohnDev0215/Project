package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceToDeviceMessagesProto {

    public interface DeviceToDeviceMessageOrBuilder extends MessageLiteOrBuilder {
        ByteString getMessage();

        int getSequenceNumber();

        boolean hasMessage();

        boolean hasSequenceNumber();
    }

    public interface EcPointOrBuilder extends MessageLiteOrBuilder {
        Curve getCurve();

        ByteString getX();

        ByteString getY();

        boolean hasCurve();

        boolean hasX();

        boolean hasY();
    }

    public interface InitiatorHelloOrBuilder extends MessageLiteOrBuilder {
        int getProtocolVersion();

        SecureMessageProto.GenericPublicKey getPublicDhKey();

        boolean hasProtocolVersion();

        boolean hasPublicDhKey();
    }

    public interface ResponderHelloOrBuilder extends MessageLiteOrBuilder {
        int getProtocolVersion();

        SecureMessageProto.GenericPublicKey getPublicDhKey();

        boolean hasProtocolVersion();

        boolean hasPublicDhKey();
    }

    public interface SpakeHandshakeMessageOrBuilder extends MessageLiteOrBuilder {
        EcPoint getEcPoint();

        int getFlowNumber();

        ByteString getHashValue();

        ByteString getPayload();

        boolean hasEcPoint();

        boolean hasFlowNumber();

        boolean hasHashValue();

        boolean hasPayload();
    }

    private DeviceToDeviceMessagesProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum Curve implements Internal.EnumLite {
        ED_25519(1);

        public static final int ED_25519_VALUE = 1;
        private static final Internal.EnumLiteMap<Curve> internalValueMap = new Internal.EnumLiteMap<Curve>() { // from class: com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.Curve.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Curve findValueByNumber(int number) {
                return Curve.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Curve valueOf(int value) {
            return forNumber(value);
        }

        public static Curve forNumber(int value) {
            if (value == 1) {
                return ED_25519;
            }
            return null;
        }

        public static Internal.EnumLiteMap<Curve> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CurveVerifier.INSTANCE;
        }

        private static final class CurveVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CurveVerifier();

            private CurveVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return Curve.forNumber(number) != null;
            }
        }

        Curve(int value) {
            this.value = value;
        }
    }

    public static final class DeviceToDeviceMessage extends GeneratedMessageLite<DeviceToDeviceMessage, Builder> implements DeviceToDeviceMessageOrBuilder {
        private static final DeviceToDeviceMessage DEFAULT_INSTANCE;
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private static volatile Parser<DeviceToDeviceMessage> PARSER = null;
        public static final int SEQUENCE_NUMBER_FIELD_NUMBER = 2;
        private int bitField0_;
        private ByteString message_ = ByteString.EMPTY;
        private int sequenceNumber_;

        private DeviceToDeviceMessage() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
        public boolean hasMessage() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
        public ByteString getMessage() {
            return this.message_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessage(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.message_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessage() {
            this.bitField0_ &= -2;
            this.message_ = getDefaultInstance().getMessage();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
        public boolean hasSequenceNumber() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
        public int getSequenceNumber() {
            return this.sequenceNumber_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSequenceNumber(int value) {
            this.bitField0_ |= 2;
            this.sequenceNumber_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSequenceNumber() {
            this.bitField0_ &= -3;
            this.sequenceNumber_ = 0;
        }

        public static DeviceToDeviceMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceToDeviceMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceToDeviceMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceToDeviceMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceToDeviceMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceToDeviceMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceToDeviceMessage parseFrom(InputStream input) throws IOException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceToDeviceMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceToDeviceMessage parseDelimitedFrom(InputStream input) throws IOException {
            return (DeviceToDeviceMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceToDeviceMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceToDeviceMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceToDeviceMessage parseFrom(CodedInputStream input) throws IOException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceToDeviceMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceToDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DeviceToDeviceMessage prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceToDeviceMessage, Builder> implements DeviceToDeviceMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DeviceToDeviceMessage.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
            public boolean hasMessage() {
                return ((DeviceToDeviceMessage) this.instance).hasMessage();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
            public ByteString getMessage() {
                return ((DeviceToDeviceMessage) this.instance).getMessage();
            }

            public Builder setMessage(ByteString value) {
                copyOnWrite();
                ((DeviceToDeviceMessage) this.instance).setMessage(value);
                return this;
            }

            public Builder clearMessage() {
                copyOnWrite();
                ((DeviceToDeviceMessage) this.instance).clearMessage();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
            public boolean hasSequenceNumber() {
                return ((DeviceToDeviceMessage) this.instance).hasSequenceNumber();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.DeviceToDeviceMessageOrBuilder
            public int getSequenceNumber() {
                return ((DeviceToDeviceMessage) this.instance).getSequenceNumber();
            }

            public Builder setSequenceNumber(int value) {
                copyOnWrite();
                ((DeviceToDeviceMessage) this.instance).setSequenceNumber(value);
                return this;
            }

            public Builder clearSequenceNumber() {
                copyOnWrite();
                ((DeviceToDeviceMessage) this.instance).clearSequenceNumber();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new DeviceToDeviceMessage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "message_", "sequenceNumber_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\n\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DeviceToDeviceMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (DeviceToDeviceMessage.class) {
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
            DeviceToDeviceMessage defaultInstance = new DeviceToDeviceMessage();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(DeviceToDeviceMessage.class, defaultInstance);
        }

        public static DeviceToDeviceMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceToDeviceMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto$1, reason: invalid class name */
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

    public static final class InitiatorHello extends GeneratedMessageLite<InitiatorHello, Builder> implements InitiatorHelloOrBuilder {
        private static final InitiatorHello DEFAULT_INSTANCE;
        private static volatile Parser<InitiatorHello> PARSER = null;
        public static final int PROTOCOL_VERSION_FIELD_NUMBER = 2;
        public static final int PUBLIC_DH_KEY_FIELD_NUMBER = 1;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private int protocolVersion_;
        private SecureMessageProto.GenericPublicKey publicDhKey_;

        private InitiatorHello() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
        public boolean hasPublicDhKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
        public SecureMessageProto.GenericPublicKey getPublicDhKey() {
            SecureMessageProto.GenericPublicKey genericPublicKey = this.publicDhKey_;
            return genericPublicKey == null ? SecureMessageProto.GenericPublicKey.getDefaultInstance() : genericPublicKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicDhKey(SecureMessageProto.GenericPublicKey value) {
            if (value == null) {
                throw null;
            }
            this.publicDhKey_ = value;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicDhKey(SecureMessageProto.GenericPublicKey.Builder builderForValue) {
            this.publicDhKey_ = builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePublicDhKey(SecureMessageProto.GenericPublicKey value) {
            if (value == null) {
                throw null;
            }
            SecureMessageProto.GenericPublicKey genericPublicKey = this.publicDhKey_;
            if (genericPublicKey != null && genericPublicKey != SecureMessageProto.GenericPublicKey.getDefaultInstance()) {
                this.publicDhKey_ = SecureMessageProto.GenericPublicKey.newBuilder(this.publicDhKey_).mergeFrom(value).buildPartial();
            } else {
                this.publicDhKey_ = value;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublicDhKey() {
            this.publicDhKey_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
        public boolean hasProtocolVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
        public int getProtocolVersion() {
            return this.protocolVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocolVersion(int value) {
            this.bitField0_ |= 2;
            this.protocolVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtocolVersion() {
            this.bitField0_ &= -3;
            this.protocolVersion_ = 0;
        }

        public static InitiatorHello parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static InitiatorHello parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static InitiatorHello parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static InitiatorHello parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static InitiatorHello parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static InitiatorHello parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static InitiatorHello parseFrom(InputStream input) throws IOException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static InitiatorHello parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static InitiatorHello parseDelimitedFrom(InputStream input) throws IOException {
            return (InitiatorHello) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static InitiatorHello parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (InitiatorHello) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static InitiatorHello parseFrom(CodedInputStream input) throws IOException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static InitiatorHello parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (InitiatorHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(InitiatorHello prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<InitiatorHello, Builder> implements InitiatorHelloOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(InitiatorHello.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
            public boolean hasPublicDhKey() {
                return ((InitiatorHello) this.instance).hasPublicDhKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
            public SecureMessageProto.GenericPublicKey getPublicDhKey() {
                return ((InitiatorHello) this.instance).getPublicDhKey();
            }

            public Builder setPublicDhKey(SecureMessageProto.GenericPublicKey value) {
                copyOnWrite();
                ((InitiatorHello) this.instance).setPublicDhKey(value);
                return this;
            }

            public Builder setPublicDhKey(SecureMessageProto.GenericPublicKey.Builder builderForValue) {
                copyOnWrite();
                ((InitiatorHello) this.instance).setPublicDhKey(builderForValue);
                return this;
            }

            public Builder mergePublicDhKey(SecureMessageProto.GenericPublicKey value) {
                copyOnWrite();
                ((InitiatorHello) this.instance).mergePublicDhKey(value);
                return this;
            }

            public Builder clearPublicDhKey() {
                copyOnWrite();
                ((InitiatorHello) this.instance).clearPublicDhKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
            public boolean hasProtocolVersion() {
                return ((InitiatorHello) this.instance).hasProtocolVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.InitiatorHelloOrBuilder
            public int getProtocolVersion() {
                return ((InitiatorHello) this.instance).getProtocolVersion();
            }

            public Builder setProtocolVersion(int value) {
                copyOnWrite();
                ((InitiatorHello) this.instance).setProtocolVersion(value);
                return this;
            }

            public Builder clearProtocolVersion() {
                copyOnWrite();
                ((InitiatorHello) this.instance).clearProtocolVersion();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new InitiatorHello();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "publicDhKey_", "protocolVersion_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Љ\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<InitiatorHello> parser = PARSER;
                    if (parser == null) {
                        synchronized (InitiatorHello.class) {
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
            InitiatorHello defaultInstance = new InitiatorHello();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(InitiatorHello.class, defaultInstance);
        }

        public static InitiatorHello getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<InitiatorHello> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ResponderHello extends GeneratedMessageLite<ResponderHello, Builder> implements ResponderHelloOrBuilder {
        private static final ResponderHello DEFAULT_INSTANCE;
        private static volatile Parser<ResponderHello> PARSER = null;
        public static final int PROTOCOL_VERSION_FIELD_NUMBER = 2;
        public static final int PUBLIC_DH_KEY_FIELD_NUMBER = 1;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private int protocolVersion_;
        private SecureMessageProto.GenericPublicKey publicDhKey_;

        private ResponderHello() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
        public boolean hasPublicDhKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
        public SecureMessageProto.GenericPublicKey getPublicDhKey() {
            SecureMessageProto.GenericPublicKey genericPublicKey = this.publicDhKey_;
            return genericPublicKey == null ? SecureMessageProto.GenericPublicKey.getDefaultInstance() : genericPublicKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicDhKey(SecureMessageProto.GenericPublicKey value) {
            if (value == null) {
                throw null;
            }
            this.publicDhKey_ = value;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicDhKey(SecureMessageProto.GenericPublicKey.Builder builderForValue) {
            this.publicDhKey_ = builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePublicDhKey(SecureMessageProto.GenericPublicKey value) {
            if (value == null) {
                throw null;
            }
            SecureMessageProto.GenericPublicKey genericPublicKey = this.publicDhKey_;
            if (genericPublicKey != null && genericPublicKey != SecureMessageProto.GenericPublicKey.getDefaultInstance()) {
                this.publicDhKey_ = SecureMessageProto.GenericPublicKey.newBuilder(this.publicDhKey_).mergeFrom(value).buildPartial();
            } else {
                this.publicDhKey_ = value;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublicDhKey() {
            this.publicDhKey_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
        public boolean hasProtocolVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
        public int getProtocolVersion() {
            return this.protocolVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocolVersion(int value) {
            this.bitField0_ |= 2;
            this.protocolVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtocolVersion() {
            this.bitField0_ &= -3;
            this.protocolVersion_ = 0;
        }

        public static ResponderHello parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ResponderHello parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ResponderHello parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ResponderHello parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ResponderHello parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ResponderHello parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ResponderHello parseFrom(InputStream input) throws IOException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ResponderHello parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ResponderHello parseDelimitedFrom(InputStream input) throws IOException {
            return (ResponderHello) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ResponderHello parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResponderHello) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ResponderHello parseFrom(CodedInputStream input) throws IOException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ResponderHello parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ResponderHello) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ResponderHello prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ResponderHello, Builder> implements ResponderHelloOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ResponderHello.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
            public boolean hasPublicDhKey() {
                return ((ResponderHello) this.instance).hasPublicDhKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
            public SecureMessageProto.GenericPublicKey getPublicDhKey() {
                return ((ResponderHello) this.instance).getPublicDhKey();
            }

            public Builder setPublicDhKey(SecureMessageProto.GenericPublicKey value) {
                copyOnWrite();
                ((ResponderHello) this.instance).setPublicDhKey(value);
                return this;
            }

            public Builder setPublicDhKey(SecureMessageProto.GenericPublicKey.Builder builderForValue) {
                copyOnWrite();
                ((ResponderHello) this.instance).setPublicDhKey(builderForValue);
                return this;
            }

            public Builder mergePublicDhKey(SecureMessageProto.GenericPublicKey value) {
                copyOnWrite();
                ((ResponderHello) this.instance).mergePublicDhKey(value);
                return this;
            }

            public Builder clearPublicDhKey() {
                copyOnWrite();
                ((ResponderHello) this.instance).clearPublicDhKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
            public boolean hasProtocolVersion() {
                return ((ResponderHello) this.instance).hasProtocolVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.ResponderHelloOrBuilder
            public int getProtocolVersion() {
                return ((ResponderHello) this.instance).getProtocolVersion();
            }

            public Builder setProtocolVersion(int value) {
                copyOnWrite();
                ((ResponderHello) this.instance).setProtocolVersion(value);
                return this;
            }

            public Builder clearProtocolVersion() {
                copyOnWrite();
                ((ResponderHello) this.instance).clearProtocolVersion();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new ResponderHello();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "publicDhKey_", "protocolVersion_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Љ\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ResponderHello> parser = PARSER;
                    if (parser == null) {
                        synchronized (ResponderHello.class) {
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
            ResponderHello defaultInstance = new ResponderHello();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ResponderHello.class, defaultInstance);
        }

        public static ResponderHello getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ResponderHello> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class EcPoint extends GeneratedMessageLite<EcPoint, Builder> implements EcPointOrBuilder {
        public static final int CURVE_FIELD_NUMBER = 1;
        private static final EcPoint DEFAULT_INSTANCE;
        private static volatile Parser<EcPoint> PARSER = null;
        public static final int X_FIELD_NUMBER = 2;
        public static final int Y_FIELD_NUMBER = 3;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private int curve_ = 1;
        private ByteString x_ = ByteString.EMPTY;
        private ByteString y_ = ByteString.EMPTY;

        private EcPoint() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public boolean hasCurve() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public Curve getCurve() {
            Curve result = Curve.forNumber(this.curve_);
            return result == null ? Curve.ED_25519 : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurve(Curve value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.curve_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurve() {
            this.bitField0_ &= -2;
            this.curve_ = 1;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public boolean hasX() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public ByteString getX() {
            return this.x_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setX(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.x_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearX() {
            this.bitField0_ &= -3;
            this.x_ = getDefaultInstance().getX();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public boolean hasY() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
        public ByteString getY() {
            return this.y_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setY(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.y_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearY() {
            this.bitField0_ &= -5;
            this.y_ = getDefaultInstance().getY();
        }

        public static EcPoint parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcPoint parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcPoint parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcPoint parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcPoint parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcPoint parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcPoint parseFrom(InputStream input) throws IOException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EcPoint parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EcPoint parseDelimitedFrom(InputStream input) throws IOException {
            return (EcPoint) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static EcPoint parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcPoint) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EcPoint parseFrom(CodedInputStream input) throws IOException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EcPoint parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcPoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(EcPoint prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<EcPoint, Builder> implements EcPointOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(EcPoint.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public boolean hasCurve() {
                return ((EcPoint) this.instance).hasCurve();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public Curve getCurve() {
                return ((EcPoint) this.instance).getCurve();
            }

            public Builder setCurve(Curve value) {
                copyOnWrite();
                ((EcPoint) this.instance).setCurve(value);
                return this;
            }

            public Builder clearCurve() {
                copyOnWrite();
                ((EcPoint) this.instance).clearCurve();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public boolean hasX() {
                return ((EcPoint) this.instance).hasX();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public ByteString getX() {
                return ((EcPoint) this.instance).getX();
            }

            public Builder setX(ByteString value) {
                copyOnWrite();
                ((EcPoint) this.instance).setX(value);
                return this;
            }

            public Builder clearX() {
                copyOnWrite();
                ((EcPoint) this.instance).clearX();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public boolean hasY() {
                return ((EcPoint) this.instance).hasY();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.EcPointOrBuilder
            public ByteString getY() {
                return ((EcPoint) this.instance).getY();
            }

            public Builder setY(ByteString value) {
                copyOnWrite();
                ((EcPoint) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((EcPoint) this.instance).clearY();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new EcPoint();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "curve_", Curve.internalGetVerifier(), "x_", "y_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001Ԍ\u0000\u0002Ԋ\u0001\u0003Ԋ\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<EcPoint> parser = PARSER;
                    if (parser == null) {
                        synchronized (EcPoint.class) {
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
            EcPoint defaultInstance = new EcPoint();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(EcPoint.class, defaultInstance);
        }

        public static EcPoint getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EcPoint> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SpakeHandshakeMessage extends GeneratedMessageLite<SpakeHandshakeMessage, Builder> implements SpakeHandshakeMessageOrBuilder {
        private static final SpakeHandshakeMessage DEFAULT_INSTANCE;
        public static final int EC_POINT_FIELD_NUMBER = 2;
        public static final int FLOW_NUMBER_FIELD_NUMBER = 1;
        public static final int HASH_VALUE_FIELD_NUMBER = 3;
        private static volatile Parser<SpakeHandshakeMessage> PARSER = null;
        public static final int PAYLOAD_FIELD_NUMBER = 4;
        private int bitField0_;
        private EcPoint ecPoint_;
        private int flowNumber_;
        private byte memoizedIsInitialized = 2;
        private ByteString hashValue_ = ByteString.EMPTY;
        private ByteString payload_ = ByteString.EMPTY;

        private SpakeHandshakeMessage() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public boolean hasFlowNumber() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public int getFlowNumber() {
            return this.flowNumber_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlowNumber(int value) {
            this.bitField0_ |= 1;
            this.flowNumber_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFlowNumber() {
            this.bitField0_ &= -2;
            this.flowNumber_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public boolean hasEcPoint() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public EcPoint getEcPoint() {
            EcPoint ecPoint = this.ecPoint_;
            return ecPoint == null ? EcPoint.getDefaultInstance() : ecPoint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEcPoint(EcPoint value) {
            if (value == null) {
                throw null;
            }
            this.ecPoint_ = value;
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEcPoint(EcPoint.Builder builderForValue) {
            this.ecPoint_ = builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEcPoint(EcPoint value) {
            if (value == null) {
                throw null;
            }
            EcPoint ecPoint = this.ecPoint_;
            if (ecPoint != null && ecPoint != EcPoint.getDefaultInstance()) {
                this.ecPoint_ = EcPoint.newBuilder(this.ecPoint_).mergeFrom(value).buildPartial();
            } else {
                this.ecPoint_ = value;
            }
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEcPoint() {
            this.ecPoint_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public boolean hasHashValue() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public ByteString getHashValue() {
            return this.hashValue_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHashValue(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.hashValue_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHashValue() {
            this.bitField0_ &= -5;
            this.hashValue_ = getDefaultInstance().getHashValue();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public boolean hasPayload() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
        public ByteString getPayload() {
            return this.payload_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPayload(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.payload_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayload() {
            this.bitField0_ &= -9;
            this.payload_ = getDefaultInstance().getPayload();
        }

        public static SpakeHandshakeMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SpakeHandshakeMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SpakeHandshakeMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SpakeHandshakeMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SpakeHandshakeMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SpakeHandshakeMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SpakeHandshakeMessage parseFrom(InputStream input) throws IOException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SpakeHandshakeMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SpakeHandshakeMessage parseDelimitedFrom(InputStream input) throws IOException {
            return (SpakeHandshakeMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SpakeHandshakeMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SpakeHandshakeMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SpakeHandshakeMessage parseFrom(CodedInputStream input) throws IOException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SpakeHandshakeMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SpakeHandshakeMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(SpakeHandshakeMessage prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SpakeHandshakeMessage, Builder> implements SpakeHandshakeMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(SpakeHandshakeMessage.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public boolean hasFlowNumber() {
                return ((SpakeHandshakeMessage) this.instance).hasFlowNumber();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public int getFlowNumber() {
                return ((SpakeHandshakeMessage) this.instance).getFlowNumber();
            }

            public Builder setFlowNumber(int value) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).setFlowNumber(value);
                return this;
            }

            public Builder clearFlowNumber() {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).clearFlowNumber();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public boolean hasEcPoint() {
                return ((SpakeHandshakeMessage) this.instance).hasEcPoint();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public EcPoint getEcPoint() {
                return ((SpakeHandshakeMessage) this.instance).getEcPoint();
            }

            public Builder setEcPoint(EcPoint value) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).setEcPoint(value);
                return this;
            }

            public Builder setEcPoint(EcPoint.Builder builderForValue) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).setEcPoint(builderForValue);
                return this;
            }

            public Builder mergeEcPoint(EcPoint value) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).mergeEcPoint(value);
                return this;
            }

            public Builder clearEcPoint() {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).clearEcPoint();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public boolean hasHashValue() {
                return ((SpakeHandshakeMessage) this.instance).hasHashValue();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public ByteString getHashValue() {
                return ((SpakeHandshakeMessage) this.instance).getHashValue();
            }

            public Builder setHashValue(ByteString value) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).setHashValue(value);
                return this;
            }

            public Builder clearHashValue() {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).clearHashValue();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public boolean hasPayload() {
                return ((SpakeHandshakeMessage) this.instance).hasPayload();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto.SpakeHandshakeMessageOrBuilder
            public ByteString getPayload() {
                return ((SpakeHandshakeMessage) this.instance).getPayload();
            }

            public Builder setPayload(ByteString value) {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).setPayload(value);
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((SpakeHandshakeMessage) this.instance).clearPayload();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new SpakeHandshakeMessage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "flowNumber_", "ecPoint_", "hashValue_", "payload_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001\u0004\u0000\u0002Љ\u0001\u0003\n\u0002\u0004\n\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<SpakeHandshakeMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (SpakeHandshakeMessage.class) {
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
            SpakeHandshakeMessage defaultInstance = new SpakeHandshakeMessage();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(SpakeHandshakeMessage.class, defaultInstance);
        }

        public static SpakeHandshakeMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SpakeHandshakeMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

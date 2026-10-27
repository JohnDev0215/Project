package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class UkeyProto {

    public interface Ukey2AlertOrBuilder extends MessageLiteOrBuilder {
        String getErrorMessage();

        ByteString getErrorMessageBytes();

        Ukey2Alert.AlertType getType();

        boolean hasErrorMessage();

        boolean hasType();
    }

    public interface Ukey2ClientFinishedOrBuilder extends MessageLiteOrBuilder {
        ByteString getPublicKey();

        boolean hasPublicKey();
    }

    public interface Ukey2ClientInitOrBuilder extends MessageLiteOrBuilder {
        Ukey2ClientInit.CipherCommitment getCipherCommitments(int i);

        int getCipherCommitmentsCount();

        List<Ukey2ClientInit.CipherCommitment> getCipherCommitmentsList();

        String getNextProtocol();

        ByteString getNextProtocolBytes();

        ByteString getRandom();

        int getVersion();

        boolean hasNextProtocol();

        boolean hasRandom();

        boolean hasVersion();
    }

    public interface Ukey2MessageOrBuilder extends MessageLiteOrBuilder {
        ByteString getMessageData();

        Ukey2Message.Type getMessageType();

        boolean hasMessageData();

        boolean hasMessageType();
    }

    public interface Ukey2ServerInitOrBuilder extends MessageLiteOrBuilder {
        Ukey2HandshakeCipher getHandshakeCipher();

        ByteString getPublicKey();

        ByteString getRandom();

        int getVersion();

        boolean hasHandshakeCipher();

        boolean hasPublicKey();

        boolean hasRandom();

        boolean hasVersion();
    }

    private UkeyProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum Ukey2HandshakeCipher implements Internal.EnumLite {
        RESERVED(0),
        P256_SHA512(100),
        CURVE25519_SHA512(200);

        public static final int CURVE25519_SHA512_VALUE = 200;
        public static final int P256_SHA512_VALUE = 100;
        public static final int RESERVED_VALUE = 0;
        private static final Internal.EnumLiteMap<Ukey2HandshakeCipher> internalValueMap = new Internal.EnumLiteMap<Ukey2HandshakeCipher>() { // from class: com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2HandshakeCipher.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Ukey2HandshakeCipher findValueByNumber(int number) {
                return Ukey2HandshakeCipher.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Ukey2HandshakeCipher valueOf(int value) {
            return forNumber(value);
        }

        public static Ukey2HandshakeCipher forNumber(int value) {
            if (value == 0) {
                return RESERVED;
            }
            if (value == 100) {
                return P256_SHA512;
            }
            if (value == 200) {
                return CURVE25519_SHA512;
            }
            return null;
        }

        public static Internal.EnumLiteMap<Ukey2HandshakeCipher> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return Ukey2HandshakeCipherVerifier.INSTANCE;
        }

        private static final class Ukey2HandshakeCipherVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new Ukey2HandshakeCipherVerifier();

            private Ukey2HandshakeCipherVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return Ukey2HandshakeCipher.forNumber(number) != null;
            }
        }

        Ukey2HandshakeCipher(int value) {
            this.value = value;
        }
    }

    public static final class Ukey2Message extends GeneratedMessageLite<Ukey2Message, Builder> implements Ukey2MessageOrBuilder {
        private static final Ukey2Message DEFAULT_INSTANCE;
        public static final int MESSAGE_DATA_FIELD_NUMBER = 2;
        public static final int MESSAGE_TYPE_FIELD_NUMBER = 1;
        private static volatile Parser<Ukey2Message> PARSER;
        private int bitField0_;
        private ByteString messageData_ = ByteString.EMPTY;
        private int messageType_;

        private Ukey2Message() {
        }

        public enum Type implements Internal.EnumLite {
            UNKNOWN_DO_NOT_USE(0),
            ALERT(1),
            CLIENT_INIT(2),
            SERVER_INIT(3),
            CLIENT_FINISH(4);

            public static final int ALERT_VALUE = 1;
            public static final int CLIENT_FINISH_VALUE = 4;
            public static final int CLIENT_INIT_VALUE = 2;
            public static final int SERVER_INIT_VALUE = 3;
            public static final int UNKNOWN_DO_NOT_USE_VALUE = 0;
            private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2Message.Type.1
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
                if (value == 0) {
                    return UNKNOWN_DO_NOT_USE;
                }
                if (value == 1) {
                    return ALERT;
                }
                if (value == 2) {
                    return CLIENT_INIT;
                }
                if (value == 3) {
                    return SERVER_INIT;
                }
                if (value == 4) {
                    return CLIENT_FINISH;
                }
                return null;
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

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
        public boolean hasMessageType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
        public Type getMessageType() {
            Type result = Type.forNumber(this.messageType_);
            return result == null ? Type.UNKNOWN_DO_NOT_USE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageType(Type value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.messageType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageType() {
            this.bitField0_ &= -2;
            this.messageType_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
        public boolean hasMessageData() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
        public ByteString getMessageData() {
            return this.messageData_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageData(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.messageData_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageData() {
            this.bitField0_ &= -3;
            this.messageData_ = getDefaultInstance().getMessageData();
        }

        public static Ukey2Message parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Message parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Message parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Message parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Message parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Message parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Message parseFrom(InputStream input) throws IOException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Message parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2Message parseDelimitedFrom(InputStream input) throws IOException {
            return (Ukey2Message) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Message parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Message) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2Message parseFrom(CodedInputStream input) throws IOException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Message parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Message) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Ukey2Message prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ukey2Message, Builder> implements Ukey2MessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Ukey2Message.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
            public boolean hasMessageType() {
                return ((Ukey2Message) this.instance).hasMessageType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
            public Type getMessageType() {
                return ((Ukey2Message) this.instance).getMessageType();
            }

            public Builder setMessageType(Type value) {
                copyOnWrite();
                ((Ukey2Message) this.instance).setMessageType(value);
                return this;
            }

            public Builder clearMessageType() {
                copyOnWrite();
                ((Ukey2Message) this.instance).clearMessageType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
            public boolean hasMessageData() {
                return ((Ukey2Message) this.instance).hasMessageData();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2MessageOrBuilder
            public ByteString getMessageData() {
                return ((Ukey2Message) this.instance).getMessageData();
            }

            public Builder setMessageData(ByteString value) {
                copyOnWrite();
                ((Ukey2Message) this.instance).setMessageData(value);
                return this;
            }

            public Builder clearMessageData() {
                copyOnWrite();
                ((Ukey2Message) this.instance).clearMessageData();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Ukey2Message();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "messageType_", Type.internalGetVerifier(), "messageData_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\n\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Ukey2Message> parser = PARSER;
                    if (parser == null) {
                        synchronized (Ukey2Message.class) {
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
            Ukey2Message defaultInstance = new Ukey2Message();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Ukey2Message.class, defaultInstance);
        }

        public static Ukey2Message getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ukey2Message> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.UkeyProto$1, reason: invalid class name */
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

    public static final class Ukey2Alert extends GeneratedMessageLite<Ukey2Alert, Builder> implements Ukey2AlertOrBuilder {
        private static final Ukey2Alert DEFAULT_INSTANCE;
        public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
        private static volatile Parser<Ukey2Alert> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private int type_ = 1;
        private String errorMessage_ = "";

        private Ukey2Alert() {
        }

        public enum AlertType implements Internal.EnumLite {
            BAD_MESSAGE(1),
            BAD_MESSAGE_TYPE(2),
            INCORRECT_MESSAGE(3),
            BAD_MESSAGE_DATA(4),
            BAD_VERSION(100),
            BAD_RANDOM(101),
            BAD_HANDSHAKE_CIPHER(102),
            BAD_NEXT_PROTOCOL(103),
            BAD_PUBLIC_KEY(104),
            INTERNAL_ERROR(200);

            public static final int BAD_HANDSHAKE_CIPHER_VALUE = 102;
            public static final int BAD_MESSAGE_DATA_VALUE = 4;
            public static final int BAD_MESSAGE_TYPE_VALUE = 2;
            public static final int BAD_MESSAGE_VALUE = 1;
            public static final int BAD_NEXT_PROTOCOL_VALUE = 103;
            public static final int BAD_PUBLIC_KEY_VALUE = 104;
            public static final int BAD_RANDOM_VALUE = 101;
            public static final int BAD_VERSION_VALUE = 100;
            public static final int INCORRECT_MESSAGE_VALUE = 3;
            public static final int INTERNAL_ERROR_VALUE = 200;
            private static final Internal.EnumLiteMap<AlertType> internalValueMap = new Internal.EnumLiteMap<AlertType>() { // from class: com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2Alert.AlertType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AlertType findValueByNumber(int number) {
                    return AlertType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static AlertType valueOf(int value) {
                return forNumber(value);
            }

            public static AlertType forNumber(int value) {
                if (value == 1) {
                    return BAD_MESSAGE;
                }
                if (value == 2) {
                    return BAD_MESSAGE_TYPE;
                }
                if (value == 3) {
                    return INCORRECT_MESSAGE;
                }
                if (value == 4) {
                    return BAD_MESSAGE_DATA;
                }
                if (value != 200) {
                    switch (value) {
                        case 100:
                            return BAD_VERSION;
                        case 101:
                            return BAD_RANDOM;
                        case 102:
                            return BAD_HANDSHAKE_CIPHER;
                        case 103:
                            return BAD_NEXT_PROTOCOL;
                        case 104:
                            return BAD_PUBLIC_KEY;
                        default:
                            return null;
                    }
                }
                return INTERNAL_ERROR;
            }

            public static Internal.EnumLiteMap<AlertType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return AlertTypeVerifier.INSTANCE;
            }

            private static final class AlertTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new AlertTypeVerifier();

                private AlertTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return AlertType.forNumber(number) != null;
                }
            }

            AlertType(int value) {
                this.value = value;
            }
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
        public AlertType getType() {
            AlertType result = AlertType.forNumber(this.type_);
            return result == null ? AlertType.BAD_MESSAGE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(AlertType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.type_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.bitField0_ &= -2;
            this.type_ = 1;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
        public boolean hasErrorMessage() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
        public String getErrorMessage() {
            return this.errorMessage_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
        public ByteString getErrorMessageBytes() {
            return ByteString.copyFromUtf8(this.errorMessage_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorMessage(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.errorMessage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorMessage() {
            this.bitField0_ &= -3;
            this.errorMessage_ = getDefaultInstance().getErrorMessage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorMessageBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.errorMessage_ = value.toStringUtf8();
        }

        public static Ukey2Alert parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Alert parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Alert parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Alert parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Alert parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2Alert parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2Alert parseFrom(InputStream input) throws IOException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Alert parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2Alert parseDelimitedFrom(InputStream input) throws IOException {
            return (Ukey2Alert) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Alert parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Alert) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2Alert parseFrom(CodedInputStream input) throws IOException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2Alert parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2Alert) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Ukey2Alert prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ukey2Alert, Builder> implements Ukey2AlertOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Ukey2Alert.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
            public boolean hasType() {
                return ((Ukey2Alert) this.instance).hasType();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
            public AlertType getType() {
                return ((Ukey2Alert) this.instance).getType();
            }

            public Builder setType(AlertType value) {
                copyOnWrite();
                ((Ukey2Alert) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Ukey2Alert) this.instance).clearType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
            public boolean hasErrorMessage() {
                return ((Ukey2Alert) this.instance).hasErrorMessage();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
            public String getErrorMessage() {
                return ((Ukey2Alert) this.instance).getErrorMessage();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2AlertOrBuilder
            public ByteString getErrorMessageBytes() {
                return ((Ukey2Alert) this.instance).getErrorMessageBytes();
            }

            public Builder setErrorMessage(String value) {
                copyOnWrite();
                ((Ukey2Alert) this.instance).setErrorMessage(value);
                return this;
            }

            public Builder clearErrorMessage() {
                copyOnWrite();
                ((Ukey2Alert) this.instance).clearErrorMessage();
                return this;
            }

            public Builder setErrorMessageBytes(ByteString value) {
                copyOnWrite();
                ((Ukey2Alert) this.instance).setErrorMessageBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Ukey2Alert();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "type_", AlertType.internalGetVerifier(), "errorMessage_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Ukey2Alert> parser = PARSER;
                    if (parser == null) {
                        synchronized (Ukey2Alert.class) {
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
            Ukey2Alert defaultInstance = new Ukey2Alert();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Ukey2Alert.class, defaultInstance);
        }

        public static Ukey2Alert getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ukey2Alert> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Ukey2ClientInit extends GeneratedMessageLite<Ukey2ClientInit, Builder> implements Ukey2ClientInitOrBuilder {
        public static final int CIPHER_COMMITMENTS_FIELD_NUMBER = 3;
        private static final Ukey2ClientInit DEFAULT_INSTANCE;
        public static final int NEXT_PROTOCOL_FIELD_NUMBER = 4;
        private static volatile Parser<Ukey2ClientInit> PARSER = null;
        public static final int RANDOM_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 1;
        private int bitField0_;
        private int version_;
        private ByteString random_ = ByteString.EMPTY;
        private Internal.ProtobufList<CipherCommitment> cipherCommitments_ = emptyProtobufList();
        private String nextProtocol_ = "";

        public interface CipherCommitmentOrBuilder extends MessageLiteOrBuilder {
            ByteString getCommitment();

            Ukey2HandshakeCipher getHandshakeCipher();

            boolean hasCommitment();

            boolean hasHandshakeCipher();
        }

        private Ukey2ClientInit() {
        }

        public static final class CipherCommitment extends GeneratedMessageLite<CipherCommitment, Builder> implements CipherCommitmentOrBuilder {
            public static final int COMMITMENT_FIELD_NUMBER = 2;
            private static final CipherCommitment DEFAULT_INSTANCE;
            public static final int HANDSHAKE_CIPHER_FIELD_NUMBER = 1;
            private static volatile Parser<CipherCommitment> PARSER;
            private int bitField0_;
            private ByteString commitment_ = ByteString.EMPTY;
            private int handshakeCipher_;

            private CipherCommitment() {
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
            public boolean hasHandshakeCipher() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
            public Ukey2HandshakeCipher getHandshakeCipher() {
                Ukey2HandshakeCipher result = Ukey2HandshakeCipher.forNumber(this.handshakeCipher_);
                return result == null ? Ukey2HandshakeCipher.RESERVED : result;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setHandshakeCipher(Ukey2HandshakeCipher value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.handshakeCipher_ = value.getNumber();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearHandshakeCipher() {
                this.bitField0_ &= -2;
                this.handshakeCipher_ = 0;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
            public boolean hasCommitment() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
            public ByteString getCommitment() {
                return this.commitment_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setCommitment(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.commitment_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearCommitment() {
                this.bitField0_ &= -3;
                this.commitment_ = getDefaultInstance().getCommitment();
            }

            public static CipherCommitment parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CipherCommitment parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CipherCommitment parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CipherCommitment parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CipherCommitment parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CipherCommitment parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CipherCommitment parseFrom(InputStream input) throws IOException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CipherCommitment parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CipherCommitment parseDelimitedFrom(InputStream input) throws IOException {
                return (CipherCommitment) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static CipherCommitment parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CipherCommitment) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CipherCommitment parseFrom(CodedInputStream input) throws IOException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CipherCommitment parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CipherCommitment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(CipherCommitment prototype) {
                return DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<CipherCommitment, Builder> implements CipherCommitmentOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(CipherCommitment.DEFAULT_INSTANCE);
                }

                @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
                public boolean hasHandshakeCipher() {
                    return ((CipherCommitment) this.instance).hasHandshakeCipher();
                }

                @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
                public Ukey2HandshakeCipher getHandshakeCipher() {
                    return ((CipherCommitment) this.instance).getHandshakeCipher();
                }

                public Builder setHandshakeCipher(Ukey2HandshakeCipher value) {
                    copyOnWrite();
                    ((CipherCommitment) this.instance).setHandshakeCipher(value);
                    return this;
                }

                public Builder clearHandshakeCipher() {
                    copyOnWrite();
                    ((CipherCommitment) this.instance).clearHandshakeCipher();
                    return this;
                }

                @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
                public boolean hasCommitment() {
                    return ((CipherCommitment) this.instance).hasCommitment();
                }

                @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInit.CipherCommitmentOrBuilder
                public ByteString getCommitment() {
                    return ((CipherCommitment) this.instance).getCommitment();
                }

                public Builder setCommitment(ByteString value) {
                    copyOnWrite();
                    ((CipherCommitment) this.instance).setCommitment(value);
                    return this;
                }

                public Builder clearCommitment() {
                    copyOnWrite();
                    ((CipherCommitment) this.instance).clearCommitment();
                    return this;
                }
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                AnonymousClass1 anonymousClass1 = null;
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                    case 1:
                        return new CipherCommitment();
                    case 2:
                        return new Builder(anonymousClass1);
                    case 3:
                        Object[] objects = {"bitField0_", "handshakeCipher_", Ukey2HandshakeCipher.internalGetVerifier(), "commitment_"};
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\n\u0001", objects);
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<CipherCommitment> parser = PARSER;
                        if (parser == null) {
                            synchronized (CipherCommitment.class) {
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
                CipherCommitment defaultInstance = new CipherCommitment();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(CipherCommitment.class, defaultInstance);
            }

            public static CipherCommitment getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CipherCommitment> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public int getVersion() {
            return this.version_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int value) {
            this.bitField0_ |= 1;
            this.version_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.bitField0_ &= -2;
            this.version_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public boolean hasRandom() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public ByteString getRandom() {
            return this.random_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRandom(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.random_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRandom() {
            this.bitField0_ &= -3;
            this.random_ = getDefaultInstance().getRandom();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public List<CipherCommitment> getCipherCommitmentsList() {
            return this.cipherCommitments_;
        }

        public List<? extends CipherCommitmentOrBuilder> getCipherCommitmentsOrBuilderList() {
            return this.cipherCommitments_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public int getCipherCommitmentsCount() {
            return this.cipherCommitments_.size();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public CipherCommitment getCipherCommitments(int index) {
            return this.cipherCommitments_.get(index);
        }

        public CipherCommitmentOrBuilder getCipherCommitmentsOrBuilder(int index) {
            return this.cipherCommitments_.get(index);
        }

        private void ensureCipherCommitmentsIsMutable() {
            if (!this.cipherCommitments_.isModifiable()) {
                this.cipherCommitments_ = GeneratedMessageLite.mutableCopy(this.cipherCommitments_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCipherCommitments(int index, CipherCommitment value) {
            if (value == null) {
                throw null;
            }
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCipherCommitments(int index, CipherCommitment.Builder builderForValue) {
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCipherCommitments(CipherCommitment value) {
            if (value == null) {
                throw null;
            }
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCipherCommitments(int index, CipherCommitment value) {
            if (value == null) {
                throw null;
            }
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCipherCommitments(CipherCommitment.Builder builderForValue) {
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCipherCommitments(int index, CipherCommitment.Builder builderForValue) {
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCipherCommitments(Iterable<? extends CipherCommitment> values) {
            ensureCipherCommitmentsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.cipherCommitments_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCipherCommitments() {
            this.cipherCommitments_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeCipherCommitments(int index) {
            ensureCipherCommitmentsIsMutable();
            this.cipherCommitments_.remove(index);
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public boolean hasNextProtocol() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public String getNextProtocol() {
            return this.nextProtocol_;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
        public ByteString getNextProtocolBytes() {
            return ByteString.copyFromUtf8(this.nextProtocol_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextProtocol(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.nextProtocol_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextProtocol() {
            this.bitField0_ &= -5;
            this.nextProtocol_ = getDefaultInstance().getNextProtocol();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextProtocolBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.nextProtocol_ = value.toStringUtf8();
        }

        public static Ukey2ClientInit parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientInit parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientInit parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientInit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientInit parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientInit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientInit parseFrom(InputStream input) throws IOException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientInit parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ClientInit parseDelimitedFrom(InputStream input) throws IOException {
            return (Ukey2ClientInit) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientInit parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientInit) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ClientInit parseFrom(CodedInputStream input) throws IOException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientInit parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Ukey2ClientInit prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ukey2ClientInit, Builder> implements Ukey2ClientInitOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Ukey2ClientInit.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public boolean hasVersion() {
                return ((Ukey2ClientInit) this.instance).hasVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public int getVersion() {
                return ((Ukey2ClientInit) this.instance).getVersion();
            }

            public Builder setVersion(int value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setVersion(value);
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).clearVersion();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public boolean hasRandom() {
                return ((Ukey2ClientInit) this.instance).hasRandom();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public ByteString getRandom() {
                return ((Ukey2ClientInit) this.instance).getRandom();
            }

            public Builder setRandom(ByteString value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setRandom(value);
                return this;
            }

            public Builder clearRandom() {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).clearRandom();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public List<CipherCommitment> getCipherCommitmentsList() {
                return Collections.unmodifiableList(((Ukey2ClientInit) this.instance).getCipherCommitmentsList());
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public int getCipherCommitmentsCount() {
                return ((Ukey2ClientInit) this.instance).getCipherCommitmentsCount();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public CipherCommitment getCipherCommitments(int index) {
                return ((Ukey2ClientInit) this.instance).getCipherCommitments(index);
            }

            public Builder setCipherCommitments(int index, CipherCommitment value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setCipherCommitments(index, value);
                return this;
            }

            public Builder setCipherCommitments(int index, CipherCommitment.Builder builderForValue) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setCipherCommitments(index, builderForValue);
                return this;
            }

            public Builder addCipherCommitments(CipherCommitment value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).addCipherCommitments(value);
                return this;
            }

            public Builder addCipherCommitments(int index, CipherCommitment value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).addCipherCommitments(index, value);
                return this;
            }

            public Builder addCipherCommitments(CipherCommitment.Builder builderForValue) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).addCipherCommitments(builderForValue);
                return this;
            }

            public Builder addCipherCommitments(int index, CipherCommitment.Builder builderForValue) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).addCipherCommitments(index, builderForValue);
                return this;
            }

            public Builder addAllCipherCommitments(Iterable<? extends CipherCommitment> values) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).addAllCipherCommitments(values);
                return this;
            }

            public Builder clearCipherCommitments() {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).clearCipherCommitments();
                return this;
            }

            public Builder removeCipherCommitments(int index) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).removeCipherCommitments(index);
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public boolean hasNextProtocol() {
                return ((Ukey2ClientInit) this.instance).hasNextProtocol();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public String getNextProtocol() {
                return ((Ukey2ClientInit) this.instance).getNextProtocol();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientInitOrBuilder
            public ByteString getNextProtocolBytes() {
                return ((Ukey2ClientInit) this.instance).getNextProtocolBytes();
            }

            public Builder setNextProtocol(String value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setNextProtocol(value);
                return this;
            }

            public Builder clearNextProtocol() {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).clearNextProtocol();
                return this;
            }

            public Builder setNextProtocolBytes(ByteString value) {
                copyOnWrite();
                ((Ukey2ClientInit) this.instance).setNextProtocolBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Ukey2ClientInit();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "version_", "random_", "cipherCommitments_", CipherCommitment.class, "nextProtocol_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\u001b\u0004\b\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Ukey2ClientInit> parser = PARSER;
                    if (parser == null) {
                        synchronized (Ukey2ClientInit.class) {
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
            Ukey2ClientInit defaultInstance = new Ukey2ClientInit();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Ukey2ClientInit.class, defaultInstance);
        }

        public static Ukey2ClientInit getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ukey2ClientInit> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Ukey2ServerInit extends GeneratedMessageLite<Ukey2ServerInit, Builder> implements Ukey2ServerInitOrBuilder {
        private static final Ukey2ServerInit DEFAULT_INSTANCE;
        public static final int HANDSHAKE_CIPHER_FIELD_NUMBER = 3;
        private static volatile Parser<Ukey2ServerInit> PARSER = null;
        public static final int PUBLIC_KEY_FIELD_NUMBER = 4;
        public static final int RANDOM_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 1;
        private int bitField0_;
        private int handshakeCipher_;
        private int version_;
        private ByteString random_ = ByteString.EMPTY;
        private ByteString publicKey_ = ByteString.EMPTY;

        private Ukey2ServerInit() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public int getVersion() {
            return this.version_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int value) {
            this.bitField0_ |= 1;
            this.version_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.bitField0_ &= -2;
            this.version_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public boolean hasRandom() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public ByteString getRandom() {
            return this.random_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRandom(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.random_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRandom() {
            this.bitField0_ &= -3;
            this.random_ = getDefaultInstance().getRandom();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public boolean hasHandshakeCipher() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public Ukey2HandshakeCipher getHandshakeCipher() {
            Ukey2HandshakeCipher result = Ukey2HandshakeCipher.forNumber(this.handshakeCipher_);
            return result == null ? Ukey2HandshakeCipher.RESERVED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHandshakeCipher(Ukey2HandshakeCipher value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.handshakeCipher_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHandshakeCipher() {
            this.bitField0_ &= -5;
            this.handshakeCipher_ = 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public boolean hasPublicKey() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
        public ByteString getPublicKey() {
            return this.publicKey_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicKey(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.publicKey_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublicKey() {
            this.bitField0_ &= -9;
            this.publicKey_ = getDefaultInstance().getPublicKey();
        }

        public static Ukey2ServerInit parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ServerInit parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ServerInit parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ServerInit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ServerInit parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ServerInit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ServerInit parseFrom(InputStream input) throws IOException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ServerInit parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ServerInit parseDelimitedFrom(InputStream input) throws IOException {
            return (Ukey2ServerInit) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ServerInit parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ServerInit) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ServerInit parseFrom(CodedInputStream input) throws IOException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ServerInit parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ServerInit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Ukey2ServerInit prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ukey2ServerInit, Builder> implements Ukey2ServerInitOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Ukey2ServerInit.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public boolean hasVersion() {
                return ((Ukey2ServerInit) this.instance).hasVersion();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public int getVersion() {
                return ((Ukey2ServerInit) this.instance).getVersion();
            }

            public Builder setVersion(int value) {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).setVersion(value);
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).clearVersion();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public boolean hasRandom() {
                return ((Ukey2ServerInit) this.instance).hasRandom();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public ByteString getRandom() {
                return ((Ukey2ServerInit) this.instance).getRandom();
            }

            public Builder setRandom(ByteString value) {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).setRandom(value);
                return this;
            }

            public Builder clearRandom() {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).clearRandom();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public boolean hasHandshakeCipher() {
                return ((Ukey2ServerInit) this.instance).hasHandshakeCipher();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public Ukey2HandshakeCipher getHandshakeCipher() {
                return ((Ukey2ServerInit) this.instance).getHandshakeCipher();
            }

            public Builder setHandshakeCipher(Ukey2HandshakeCipher value) {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).setHandshakeCipher(value);
                return this;
            }

            public Builder clearHandshakeCipher() {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).clearHandshakeCipher();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public boolean hasPublicKey() {
                return ((Ukey2ServerInit) this.instance).hasPublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ServerInitOrBuilder
            public ByteString getPublicKey() {
                return ((Ukey2ServerInit) this.instance).getPublicKey();
            }

            public Builder setPublicKey(ByteString value) {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).setPublicKey(value);
                return this;
            }

            public Builder clearPublicKey() {
                copyOnWrite();
                ((Ukey2ServerInit) this.instance).clearPublicKey();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Ukey2ServerInit();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "version_", "random_", "handshakeCipher_", Ukey2HandshakeCipher.internalGetVerifier(), "publicKey_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\f\u0002\u0004\n\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Ukey2ServerInit> parser = PARSER;
                    if (parser == null) {
                        synchronized (Ukey2ServerInit.class) {
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
            Ukey2ServerInit defaultInstance = new Ukey2ServerInit();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Ukey2ServerInit.class, defaultInstance);
        }

        public static Ukey2ServerInit getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ukey2ServerInit> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Ukey2ClientFinished extends GeneratedMessageLite<Ukey2ClientFinished, Builder> implements Ukey2ClientFinishedOrBuilder {
        private static final Ukey2ClientFinished DEFAULT_INSTANCE;
        private static volatile Parser<Ukey2ClientFinished> PARSER = null;
        public static final int PUBLIC_KEY_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString publicKey_ = ByteString.EMPTY;

        private Ukey2ClientFinished() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientFinishedOrBuilder
        public boolean hasPublicKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientFinishedOrBuilder
        public ByteString getPublicKey() {
            return this.publicKey_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicKey(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.publicKey_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublicKey() {
            this.bitField0_ &= -2;
            this.publicKey_ = getDefaultInstance().getPublicKey();
        }

        public static Ukey2ClientFinished parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientFinished parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientFinished parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientFinished parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientFinished parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ukey2ClientFinished parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ukey2ClientFinished parseFrom(InputStream input) throws IOException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientFinished parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ClientFinished parseDelimitedFrom(InputStream input) throws IOException {
            return (Ukey2ClientFinished) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientFinished parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientFinished) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ukey2ClientFinished parseFrom(CodedInputStream input) throws IOException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ukey2ClientFinished parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ukey2ClientFinished) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Ukey2ClientFinished prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ukey2ClientFinished, Builder> implements Ukey2ClientFinishedOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Ukey2ClientFinished.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientFinishedOrBuilder
            public boolean hasPublicKey() {
                return ((Ukey2ClientFinished) this.instance).hasPublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.UkeyProto.Ukey2ClientFinishedOrBuilder
            public ByteString getPublicKey() {
                return ((Ukey2ClientFinished) this.instance).getPublicKey();
            }

            public Builder setPublicKey(ByteString value) {
                copyOnWrite();
                ((Ukey2ClientFinished) this.instance).setPublicKey(value);
                return this;
            }

            public Builder clearPublicKey() {
                copyOnWrite();
                ((Ukey2ClientFinished) this.instance).clearPublicKey();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Ukey2ClientFinished();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "publicKey_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n\u0000", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Ukey2ClientFinished> parser = PARSER;
                    if (parser == null) {
                        synchronized (Ukey2ClientFinished.class) {
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
            Ukey2ClientFinished defaultInstance = new Ukey2ClientFinished();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Ukey2ClientFinished.class, defaultInstance);
        }

        public static Ukey2ClientFinished getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ukey2ClientFinished> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

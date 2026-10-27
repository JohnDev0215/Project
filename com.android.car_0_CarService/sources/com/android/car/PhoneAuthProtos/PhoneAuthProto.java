package com.android.car.PhoneAuthProtos;

import com.android.car.protobuf.ByteString;
import com.android.car.protobuf.CodedInputStream;
import com.android.car.protobuf.ExtensionRegistryLite;
import com.android.car.protobuf.GeneratedMessageLite;
import com.android.car.protobuf.InvalidProtocolBufferException;
import com.android.car.protobuf.MessageLiteOrBuilder;
import com.android.car.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class PhoneAuthProto {

    public interface PhoneCredentialsOrBuilder extends MessageLiteOrBuilder {
        ByteString getEscrowToken();

        ByteString getHandle();
    }

    private PhoneAuthProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class PhoneCredentials extends GeneratedMessageLite<PhoneCredentials, Builder> implements PhoneCredentialsOrBuilder {
        private static final PhoneCredentials DEFAULT_INSTANCE;
        public static final int ESCROW_TOKEN_FIELD_NUMBER = 1;
        public static final int HANDLE_FIELD_NUMBER = 2;
        private static volatile Parser<PhoneCredentials> PARSER;
        private ByteString escrowToken_ = ByteString.EMPTY;
        private ByteString handle_ = ByteString.EMPTY;

        private PhoneCredentials() {
        }

        @Override // com.android.car.PhoneAuthProtos.PhoneAuthProto.PhoneCredentialsOrBuilder
        public ByteString getEscrowToken() {
            return this.escrowToken_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEscrowToken(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.escrowToken_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEscrowToken() {
            this.escrowToken_ = getDefaultInstance().getEscrowToken();
        }

        @Override // com.android.car.PhoneAuthProtos.PhoneAuthProto.PhoneCredentialsOrBuilder
        public ByteString getHandle() {
            return this.handle_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHandle(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.handle_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHandle() {
            this.handle_ = getDefaultInstance().getHandle();
        }

        public static PhoneCredentials parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneCredentials parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneCredentials parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneCredentials parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneCredentials parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PhoneCredentials parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PhoneCredentials parseFrom(InputStream input) throws IOException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneCredentials parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneCredentials parseDelimitedFrom(InputStream input) throws IOException {
            return (PhoneCredentials) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneCredentials parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneCredentials) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PhoneCredentials parseFrom(CodedInputStream input) throws IOException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PhoneCredentials parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PhoneCredentials) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PhoneCredentials prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PhoneCredentials, Builder> implements PhoneCredentialsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PhoneCredentials.DEFAULT_INSTANCE);
            }

            @Override // com.android.car.PhoneAuthProtos.PhoneAuthProto.PhoneCredentialsOrBuilder
            public ByteString getEscrowToken() {
                return ((PhoneCredentials) this.instance).getEscrowToken();
            }

            public Builder setEscrowToken(ByteString value) {
                copyOnWrite();
                ((PhoneCredentials) this.instance).setEscrowToken(value);
                return this;
            }

            public Builder clearEscrowToken() {
                copyOnWrite();
                ((PhoneCredentials) this.instance).clearEscrowToken();
                return this;
            }

            @Override // com.android.car.PhoneAuthProtos.PhoneAuthProto.PhoneCredentialsOrBuilder
            public ByteString getHandle() {
                return ((PhoneCredentials) this.instance).getHandle();
            }

            public Builder setHandle(ByteString value) {
                copyOnWrite();
                ((PhoneCredentials) this.instance).setHandle(value);
                return this;
            }

            public Builder clearHandle() {
                copyOnWrite();
                ((PhoneCredentials) this.instance).clearHandle();
                return this;
            }
        }

        @Override // com.android.car.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PhoneCredentials();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"escrowToken_", "handle_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\n\u0002\n", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PhoneCredentials> parser = PARSER;
                    if (parser == null) {
                        synchronized (PhoneCredentials.class) {
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
            PhoneCredentials defaultInstance = new PhoneCredentials();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PhoneCredentials.class, defaultInstance);
        }

        public static PhoneCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PhoneCredentials> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.car.PhoneAuthProtos.PhoneAuthProto$1, reason: invalid class name */
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

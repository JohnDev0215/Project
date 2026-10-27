package com.android.car.BleStreamProtos;

import com.android.car.BLEStreamProtos.BLEOperationProto;
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
public final class BleDeviceMessageProto {

    public interface BleDeviceMessageOrBuilder extends MessageLiteOrBuilder {
        boolean getIsPayloadEncrypted();

        BLEOperationProto.OperationType getOperation();

        int getOperationValue();

        ByteString getPayload();

        ByteString getRecipient();
    }

    private BleDeviceMessageProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class BleDeviceMessage extends GeneratedMessageLite<BleDeviceMessage, Builder> implements BleDeviceMessageOrBuilder {
        private static final BleDeviceMessage DEFAULT_INSTANCE;
        public static final int IS_PAYLOAD_ENCRYPTED_FIELD_NUMBER = 2;
        public static final int OPERATION_FIELD_NUMBER = 1;
        private static volatile Parser<BleDeviceMessage> PARSER = null;
        public static final int PAYLOAD_FIELD_NUMBER = 4;
        public static final int RECIPIENT_FIELD_NUMBER = 3;
        private boolean isPayloadEncrypted_;
        private int operation_;
        private ByteString recipient_ = ByteString.EMPTY;
        private ByteString payload_ = ByteString.EMPTY;

        private BleDeviceMessage() {
        }

        @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
        public int getOperationValue() {
            return this.operation_;
        }

        @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
        public BLEOperationProto.OperationType getOperation() {
            BLEOperationProto.OperationType result = BLEOperationProto.OperationType.forNumber(this.operation_);
            return result == null ? BLEOperationProto.OperationType.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOperationValue(int value) {
            this.operation_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOperation(BLEOperationProto.OperationType value) {
            if (value == null) {
                throw null;
            }
            this.operation_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOperation() {
            this.operation_ = 0;
        }

        @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
        public boolean getIsPayloadEncrypted() {
            return this.isPayloadEncrypted_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsPayloadEncrypted(boolean value) {
            this.isPayloadEncrypted_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsPayloadEncrypted() {
            this.isPayloadEncrypted_ = false;
        }

        @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
        public ByteString getRecipient() {
            return this.recipient_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRecipient(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.recipient_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRecipient() {
            this.recipient_ = getDefaultInstance().getRecipient();
        }

        @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
        public ByteString getPayload() {
            return this.payload_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPayload(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.payload_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayload() {
            this.payload_ = getDefaultInstance().getPayload();
        }

        public static BleDeviceMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BleDeviceMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BleDeviceMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BleDeviceMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BleDeviceMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BleDeviceMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BleDeviceMessage parseFrom(InputStream input) throws IOException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BleDeviceMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BleDeviceMessage parseDelimitedFrom(InputStream input) throws IOException {
            return (BleDeviceMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BleDeviceMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BleDeviceMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BleDeviceMessage parseFrom(CodedInputStream input) throws IOException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BleDeviceMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BleDeviceMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BleDeviceMessage prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BleDeviceMessage, Builder> implements BleDeviceMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BleDeviceMessage.DEFAULT_INSTANCE);
            }

            @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
            public int getOperationValue() {
                return ((BleDeviceMessage) this.instance).getOperationValue();
            }

            public Builder setOperationValue(int value) {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).setOperationValue(value);
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
            public BLEOperationProto.OperationType getOperation() {
                return ((BleDeviceMessage) this.instance).getOperation();
            }

            public Builder setOperation(BLEOperationProto.OperationType value) {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).setOperation(value);
                return this;
            }

            public Builder clearOperation() {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).clearOperation();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
            public boolean getIsPayloadEncrypted() {
                return ((BleDeviceMessage) this.instance).getIsPayloadEncrypted();
            }

            public Builder setIsPayloadEncrypted(boolean value) {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).setIsPayloadEncrypted(value);
                return this;
            }

            public Builder clearIsPayloadEncrypted() {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).clearIsPayloadEncrypted();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
            public ByteString getRecipient() {
                return ((BleDeviceMessage) this.instance).getRecipient();
            }

            public Builder setRecipient(ByteString value) {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).setRecipient(value);
                return this;
            }

            public Builder clearRecipient() {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).clearRecipient();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BleDeviceMessageProto.BleDeviceMessageOrBuilder
            public ByteString getPayload() {
                return ((BleDeviceMessage) this.instance).getPayload();
            }

            public Builder setPayload(ByteString value) {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).setPayload(value);
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((BleDeviceMessage) this.instance).clearPayload();
                return this;
            }
        }

        @Override // com.android.car.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BleDeviceMessage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"operation_", "isPayloadEncrypted_", "recipient_", "payload_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0002\u0007\u0003\n\u0004\n", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BleDeviceMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (BleDeviceMessage.class) {
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
            BleDeviceMessage defaultInstance = new BleDeviceMessage();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BleDeviceMessage.class, defaultInstance);
        }

        public static BleDeviceMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BleDeviceMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.car.BleStreamProtos.BleDeviceMessageProto$1, reason: invalid class name */
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

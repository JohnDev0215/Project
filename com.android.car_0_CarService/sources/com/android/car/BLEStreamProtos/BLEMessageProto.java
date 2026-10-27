package com.android.car.BLEStreamProtos;

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
public final class BLEMessageProto {

    public interface BLEMessageOrBuilder extends MessageLiteOrBuilder {
        boolean getIsPayloadEncrypted();

        BLEOperationProto.OperationType getOperation();

        int getOperationValue();

        int getPacketNumber();

        ByteString getPayload();

        int getTotalPackets();

        int getVersion();
    }

    private BLEMessageProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class BLEMessage extends GeneratedMessageLite<BLEMessage, Builder> implements BLEMessageOrBuilder {
        private static final BLEMessage DEFAULT_INSTANCE;
        public static final int IS_PAYLOAD_ENCRYPTED_FIELD_NUMBER = 5;
        public static final int OPERATION_FIELD_NUMBER = 2;
        public static final int PACKET_NUMBER_FIELD_NUMBER = 3;
        private static volatile Parser<BLEMessage> PARSER = null;
        public static final int PAYLOAD_FIELD_NUMBER = 6;
        public static final int TOTAL_PACKETS_FIELD_NUMBER = 4;
        public static final int VERSION_FIELD_NUMBER = 1;
        private boolean isPayloadEncrypted_;
        private int operation_;
        private int packetNumber_;
        private ByteString payload_ = ByteString.EMPTY;
        private int totalPackets_;
        private int version_;

        private BLEMessage() {
        }

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
        public int getVersion() {
            return this.version_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int value) {
            this.version_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
        public int getOperationValue() {
            return this.operation_;
        }

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
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

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
        public int getPacketNumber() {
            return this.packetNumber_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPacketNumber(int value) {
            this.packetNumber_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPacketNumber() {
            this.packetNumber_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
        public int getTotalPackets() {
            return this.totalPackets_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTotalPackets(int value) {
            this.totalPackets_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTotalPackets() {
            this.totalPackets_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
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

        @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
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

        public static BLEMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEMessage parseFrom(InputStream input) throws IOException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BLEMessage parseDelimitedFrom(InputStream input) throws IOException {
            return (BLEMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BLEMessage parseFrom(CodedInputStream input) throws IOException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BLEMessage prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BLEMessage, Builder> implements BLEMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BLEMessage.DEFAULT_INSTANCE);
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public int getVersion() {
                return ((BLEMessage) this.instance).getVersion();
            }

            public Builder setVersion(int value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setVersion(value);
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearVersion();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public int getOperationValue() {
                return ((BLEMessage) this.instance).getOperationValue();
            }

            public Builder setOperationValue(int value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setOperationValue(value);
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public BLEOperationProto.OperationType getOperation() {
                return ((BLEMessage) this.instance).getOperation();
            }

            public Builder setOperation(BLEOperationProto.OperationType value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setOperation(value);
                return this;
            }

            public Builder clearOperation() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearOperation();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public int getPacketNumber() {
                return ((BLEMessage) this.instance).getPacketNumber();
            }

            public Builder setPacketNumber(int value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setPacketNumber(value);
                return this;
            }

            public Builder clearPacketNumber() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearPacketNumber();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public int getTotalPackets() {
                return ((BLEMessage) this.instance).getTotalPackets();
            }

            public Builder setTotalPackets(int value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setTotalPackets(value);
                return this;
            }

            public Builder clearTotalPackets() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearTotalPackets();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public boolean getIsPayloadEncrypted() {
                return ((BLEMessage) this.instance).getIsPayloadEncrypted();
            }

            public Builder setIsPayloadEncrypted(boolean value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setIsPayloadEncrypted(value);
                return this;
            }

            public Builder clearIsPayloadEncrypted() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearIsPayloadEncrypted();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.BLEMessageProto.BLEMessageOrBuilder
            public ByteString getPayload() {
                return ((BLEMessage) this.instance).getPayload();
            }

            public Builder setPayload(ByteString value) {
                copyOnWrite();
                ((BLEMessage) this.instance).setPayload(value);
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((BLEMessage) this.instance).clearPayload();
                return this;
            }
        }

        @Override // com.android.car.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BLEMessage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"version_", "operation_", "packetNumber_", "totalPackets_", "isPayloadEncrypted_", "payload_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0004\u0002\f\u0003\u0006\u0004\u0006\u0005\u0007\u0006\n", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BLEMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (BLEMessage.class) {
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
            BLEMessage defaultInstance = new BLEMessage();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BLEMessage.class, defaultInstance);
        }

        public static BLEMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BLEMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.car.BLEStreamProtos.BLEMessageProto$1, reason: invalid class name */
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

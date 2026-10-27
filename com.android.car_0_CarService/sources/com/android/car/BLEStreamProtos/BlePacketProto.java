package com.android.car.BleStreamProtos;

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
public final class BlePacketProto {

    public interface BlePacketOrBuilder extends MessageLiteOrBuilder {
        int getMessageId();

        int getPacketNumber();

        ByteString getPayload();

        int getTotalPackets();
    }

    private BlePacketProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class BlePacket extends GeneratedMessageLite<BlePacket, Builder> implements BlePacketOrBuilder {
        private static final BlePacket DEFAULT_INSTANCE;
        public static final int MESSAGE_ID_FIELD_NUMBER = 3;
        public static final int PACKET_NUMBER_FIELD_NUMBER = 1;
        private static volatile Parser<BlePacket> PARSER = null;
        public static final int PAYLOAD_FIELD_NUMBER = 4;
        public static final int TOTAL_PACKETS_FIELD_NUMBER = 2;
        private int messageId_;
        private int packetNumber_;
        private ByteString payload_ = ByteString.EMPTY;
        private int totalPackets_;

        private BlePacket() {
        }

        @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
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

        @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
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

        @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
        public int getMessageId() {
            return this.messageId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageId(int value) {
            this.messageId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessageId() {
            this.messageId_ = 0;
        }

        @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
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

        public static BlePacket parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BlePacket parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BlePacket parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BlePacket parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BlePacket parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BlePacket parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BlePacket parseFrom(InputStream input) throws IOException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BlePacket parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BlePacket parseDelimitedFrom(InputStream input) throws IOException {
            return (BlePacket) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BlePacket parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BlePacket) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BlePacket parseFrom(CodedInputStream input) throws IOException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BlePacket parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BlePacket) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BlePacket prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BlePacket, Builder> implements BlePacketOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BlePacket.DEFAULT_INSTANCE);
            }

            @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
            public int getPacketNumber() {
                return ((BlePacket) this.instance).getPacketNumber();
            }

            public Builder setPacketNumber(int value) {
                copyOnWrite();
                ((BlePacket) this.instance).setPacketNumber(value);
                return this;
            }

            public Builder clearPacketNumber() {
                copyOnWrite();
                ((BlePacket) this.instance).clearPacketNumber();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
            public int getTotalPackets() {
                return ((BlePacket) this.instance).getTotalPackets();
            }

            public Builder setTotalPackets(int value) {
                copyOnWrite();
                ((BlePacket) this.instance).setTotalPackets(value);
                return this;
            }

            public Builder clearTotalPackets() {
                copyOnWrite();
                ((BlePacket) this.instance).clearTotalPackets();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
            public int getMessageId() {
                return ((BlePacket) this.instance).getMessageId();
            }

            public Builder setMessageId(int value) {
                copyOnWrite();
                ((BlePacket) this.instance).setMessageId(value);
                return this;
            }

            public Builder clearMessageId() {
                copyOnWrite();
                ((BlePacket) this.instance).clearMessageId();
                return this;
            }

            @Override // com.android.car.BleStreamProtos.BlePacketProto.BlePacketOrBuilder
            public ByteString getPayload() {
                return ((BlePacket) this.instance).getPayload();
            }

            public Builder setPayload(ByteString value) {
                copyOnWrite();
                ((BlePacket) this.instance).setPayload(value);
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((BlePacket) this.instance).clearPayload();
                return this;
            }
        }

        @Override // com.android.car.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BlePacket();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"packetNumber_", "totalPackets_", "messageId_", "payload_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0006\u0002\u0004\u0003\u0004\u0004\n", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BlePacket> parser = PARSER;
                    if (parser == null) {
                        synchronized (BlePacket.class) {
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
            BlePacket defaultInstance = new BlePacket();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BlePacket.class, defaultInstance);
        }

        public static BlePacket getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BlePacket> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.car.BleStreamProtos.BlePacketProto$1, reason: invalid class name */
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

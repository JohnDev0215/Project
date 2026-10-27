package com.android.car.BLEStreamProtos;

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
public final class VersionExchangeProto {

    public interface BLEVersionExchangeOrBuilder extends MessageLiteOrBuilder {
        int getMaxSupportedMessagingVersion();

        int getMaxSupportedSecurityVersion();

        int getMinSupportedMessagingVersion();

        int getMinSupportedSecurityVersion();
    }

    private VersionExchangeProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class BLEVersionExchange extends GeneratedMessageLite<BLEVersionExchange, Builder> implements BLEVersionExchangeOrBuilder {
        private static final BLEVersionExchange DEFAULT_INSTANCE;
        public static final int MAXSUPPORTEDMESSAGINGVERSION_FIELD_NUMBER = 2;
        public static final int MAXSUPPORTEDSECURITYVERSION_FIELD_NUMBER = 4;
        public static final int MINSUPPORTEDMESSAGINGVERSION_FIELD_NUMBER = 1;
        public static final int MINSUPPORTEDSECURITYVERSION_FIELD_NUMBER = 3;
        private static volatile Parser<BLEVersionExchange> PARSER;
        private int maxSupportedMessagingVersion_;
        private int maxSupportedSecurityVersion_;
        private int minSupportedMessagingVersion_;
        private int minSupportedSecurityVersion_;

        private BLEVersionExchange() {
        }

        @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
        public int getMinSupportedMessagingVersion() {
            return this.minSupportedMessagingVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinSupportedMessagingVersion(int value) {
            this.minSupportedMessagingVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinSupportedMessagingVersion() {
            this.minSupportedMessagingVersion_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
        public int getMaxSupportedMessagingVersion() {
            return this.maxSupportedMessagingVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxSupportedMessagingVersion(int value) {
            this.maxSupportedMessagingVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaxSupportedMessagingVersion() {
            this.maxSupportedMessagingVersion_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
        public int getMinSupportedSecurityVersion() {
            return this.minSupportedSecurityVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinSupportedSecurityVersion(int value) {
            this.minSupportedSecurityVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinSupportedSecurityVersion() {
            this.minSupportedSecurityVersion_ = 0;
        }

        @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
        public int getMaxSupportedSecurityVersion() {
            return this.maxSupportedSecurityVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxSupportedSecurityVersion(int value) {
            this.maxSupportedSecurityVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaxSupportedSecurityVersion() {
            this.maxSupportedSecurityVersion_ = 0;
        }

        public static BLEVersionExchange parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEVersionExchange parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEVersionExchange parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEVersionExchange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEVersionExchange parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BLEVersionExchange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BLEVersionExchange parseFrom(InputStream input) throws IOException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEVersionExchange parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BLEVersionExchange parseDelimitedFrom(InputStream input) throws IOException {
            return (BLEVersionExchange) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEVersionExchange parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEVersionExchange) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BLEVersionExchange parseFrom(CodedInputStream input) throws IOException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BLEVersionExchange parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BLEVersionExchange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BLEVersionExchange prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BLEVersionExchange, Builder> implements BLEVersionExchangeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BLEVersionExchange.DEFAULT_INSTANCE);
            }

            @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
            public int getMinSupportedMessagingVersion() {
                return ((BLEVersionExchange) this.instance).getMinSupportedMessagingVersion();
            }

            public Builder setMinSupportedMessagingVersion(int value) {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).setMinSupportedMessagingVersion(value);
                return this;
            }

            public Builder clearMinSupportedMessagingVersion() {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).clearMinSupportedMessagingVersion();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
            public int getMaxSupportedMessagingVersion() {
                return ((BLEVersionExchange) this.instance).getMaxSupportedMessagingVersion();
            }

            public Builder setMaxSupportedMessagingVersion(int value) {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).setMaxSupportedMessagingVersion(value);
                return this;
            }

            public Builder clearMaxSupportedMessagingVersion() {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).clearMaxSupportedMessagingVersion();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
            public int getMinSupportedSecurityVersion() {
                return ((BLEVersionExchange) this.instance).getMinSupportedSecurityVersion();
            }

            public Builder setMinSupportedSecurityVersion(int value) {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).setMinSupportedSecurityVersion(value);
                return this;
            }

            public Builder clearMinSupportedSecurityVersion() {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).clearMinSupportedSecurityVersion();
                return this;
            }

            @Override // com.android.car.BLEStreamProtos.VersionExchangeProto.BLEVersionExchangeOrBuilder
            public int getMaxSupportedSecurityVersion() {
                return ((BLEVersionExchange) this.instance).getMaxSupportedSecurityVersion();
            }

            public Builder setMaxSupportedSecurityVersion(int value) {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).setMaxSupportedSecurityVersion(value);
                return this;
            }

            public Builder clearMaxSupportedSecurityVersion() {
                copyOnWrite();
                ((BLEVersionExchange) this.instance).clearMaxSupportedSecurityVersion();
                return this;
            }
        }

        @Override // com.android.car.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BLEVersionExchange();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"minSupportedMessagingVersion_", "maxSupportedMessagingVersion_", "minSupportedSecurityVersion_", "maxSupportedSecurityVersion_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\u0004\u0004\u0004", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BLEVersionExchange> parser = PARSER;
                    if (parser == null) {
                        synchronized (BLEVersionExchange.class) {
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
            BLEVersionExchange defaultInstance = new BLEVersionExchange();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BLEVersionExchange.class, defaultInstance);
        }

        public static BLEVersionExchange getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BLEVersionExchange> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.car.BLEStreamProtos.VersionExchangeProto$1, reason: invalid class name */
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

package com.google.security.cryptauth.lib.securegcm;

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

/* JADX INFO: loaded from: classes3.dex */
public final class SecureGcmPasswordlessAuthProto {

    public interface IdentityAssertionOrBuilder extends MessageLiteOrBuilder {
        ByteString getBrowserDataHash();

        long getCounter();

        int getUserApproval();

        boolean hasBrowserDataHash();

        boolean hasCounter();

        boolean hasUserApproval();
    }

    private SecureGcmPasswordlessAuthProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class IdentityAssertion extends GeneratedMessageLite<IdentityAssertion, Builder> implements IdentityAssertionOrBuilder {
        public static final int BROWSER_DATA_HASH_FIELD_NUMBER = 1;
        public static final int COUNTER_FIELD_NUMBER = 2;
        private static final IdentityAssertion DEFAULT_INSTANCE;
        private static volatile Parser<IdentityAssertion> PARSER = null;
        public static final int USER_APPROVAL_FIELD_NUMBER = 3;
        private int bitField0_;
        private ByteString browserDataHash_ = ByteString.EMPTY;
        private long counter_;
        private int userApproval_;

        private IdentityAssertion() {
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public boolean hasBrowserDataHash() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public ByteString getBrowserDataHash() {
            return this.browserDataHash_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBrowserDataHash(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.browserDataHash_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBrowserDataHash() {
            this.bitField0_ &= -2;
            this.browserDataHash_ = getDefaultInstance().getBrowserDataHash();
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public boolean hasCounter() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public long getCounter() {
            return this.counter_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounter(long value) {
            this.bitField0_ |= 2;
            this.counter_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounter() {
            this.bitField0_ &= -3;
            this.counter_ = 0L;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public boolean hasUserApproval() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
        public int getUserApproval() {
            return this.userApproval_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserApproval(int value) {
            this.bitField0_ |= 4;
            this.userApproval_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserApproval() {
            this.bitField0_ &= -5;
            this.userApproval_ = 0;
        }

        public static IdentityAssertion parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IdentityAssertion parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IdentityAssertion parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IdentityAssertion parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IdentityAssertion parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IdentityAssertion parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IdentityAssertion parseFrom(InputStream input) throws IOException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IdentityAssertion parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IdentityAssertion parseDelimitedFrom(InputStream input) throws IOException {
            return (IdentityAssertion) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static IdentityAssertion parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdentityAssertion) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IdentityAssertion parseFrom(CodedInputStream input) throws IOException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IdentityAssertion parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IdentityAssertion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(IdentityAssertion prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<IdentityAssertion, Builder> implements IdentityAssertionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(IdentityAssertion.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public boolean hasBrowserDataHash() {
                return ((IdentityAssertion) this.instance).hasBrowserDataHash();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public ByteString getBrowserDataHash() {
                return ((IdentityAssertion) this.instance).getBrowserDataHash();
            }

            public Builder setBrowserDataHash(ByteString value) {
                copyOnWrite();
                ((IdentityAssertion) this.instance).setBrowserDataHash(value);
                return this;
            }

            public Builder clearBrowserDataHash() {
                copyOnWrite();
                ((IdentityAssertion) this.instance).clearBrowserDataHash();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public boolean hasCounter() {
                return ((IdentityAssertion) this.instance).hasCounter();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public long getCounter() {
                return ((IdentityAssertion) this.instance).getCounter();
            }

            public Builder setCounter(long value) {
                copyOnWrite();
                ((IdentityAssertion) this.instance).setCounter(value);
                return this;
            }

            public Builder clearCounter() {
                copyOnWrite();
                ((IdentityAssertion) this.instance).clearCounter();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public boolean hasUserApproval() {
                return ((IdentityAssertion) this.instance).hasUserApproval();
            }

            @Override // com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto.IdentityAssertionOrBuilder
            public int getUserApproval() {
                return ((IdentityAssertion) this.instance).getUserApproval();
            }

            public Builder setUserApproval(int value) {
                copyOnWrite();
                ((IdentityAssertion) this.instance).setUserApproval(value);
                return this;
            }

            public Builder clearUserApproval() {
                copyOnWrite();
                ((IdentityAssertion) this.instance).clearUserApproval();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new IdentityAssertion();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "browserDataHash_", "counter_", "userApproval_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\u0002\u0001\u0003\u0004\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<IdentityAssertion> parser = PARSER;
                    if (parser == null) {
                        synchronized (IdentityAssertion.class) {
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
            IdentityAssertion defaultInstance = new IdentityAssertion();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(IdentityAssertion.class, defaultInstance);
        }

        public static IdentityAssertion getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IdentityAssertion> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.SecureGcmPasswordlessAuthProto$1, reason: invalid class name */
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

package com.google.security.cryptauth.lib.securemessage;

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

/* JADX INFO: loaded from: classes3.dex */
public final class SecureMessageProto {

    public interface DhPublicKeyOrBuilder extends MessageLiteOrBuilder {
        ByteString getY();

        boolean hasY();
    }

    public interface EcP256PublicKeyOrBuilder extends MessageLiteOrBuilder {
        ByteString getX();

        ByteString getY();

        boolean hasX();

        boolean hasY();
    }

    public interface GenericPublicKeyOrBuilder extends MessageLiteOrBuilder {
        DhPublicKey getDh2048PublicKey();

        EcP256PublicKey getEcP256PublicKey();

        SimpleRsaPublicKey getRsa2048PublicKey();

        PublicKeyType getType();

        boolean hasDh2048PublicKey();

        boolean hasEcP256PublicKey();

        boolean hasRsa2048PublicKey();

        boolean hasType();
    }

    public interface HeaderAndBodyInternalOrBuilder extends MessageLiteOrBuilder {
        ByteString getBody();

        ByteString getHeader();

        boolean hasBody();

        boolean hasHeader();
    }

    public interface HeaderAndBodyOrBuilder extends MessageLiteOrBuilder {
        ByteString getBody();

        Header getHeader();

        boolean hasBody();

        boolean hasHeader();
    }

    public interface HeaderOrBuilder extends MessageLiteOrBuilder {
        int getAssociatedDataLength();

        ByteString getDecryptionKeyId();

        EncScheme getEncryptionScheme();

        ByteString getIv();

        ByteString getPublicMetadata();

        SigScheme getSignatureScheme();

        ByteString getVerificationKeyId();

        boolean hasAssociatedDataLength();

        boolean hasDecryptionKeyId();

        boolean hasEncryptionScheme();

        boolean hasIv();

        boolean hasPublicMetadata();

        boolean hasSignatureScheme();

        boolean hasVerificationKeyId();
    }

    public interface SecureMessageOrBuilder extends MessageLiteOrBuilder {
        ByteString getHeaderAndBody();

        ByteString getSignature();

        boolean hasHeaderAndBody();

        boolean hasSignature();
    }

    public interface SimpleRsaPublicKeyOrBuilder extends MessageLiteOrBuilder {
        int getE();

        ByteString getN();

        boolean hasE();

        boolean hasN();
    }

    private SecureMessageProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum SigScheme implements Internal.EnumLite {
        HMAC_SHA256(1),
        ECDSA_P256_SHA256(2),
        RSA2048_SHA256(3);

        public static final int ECDSA_P256_SHA256_VALUE = 2;
        public static final int HMAC_SHA256_VALUE = 1;
        public static final int RSA2048_SHA256_VALUE = 3;
        private static final Internal.EnumLiteMap<SigScheme> internalValueMap = new Internal.EnumLiteMap<SigScheme>() { // from class: com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SigScheme.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SigScheme findValueByNumber(int number) {
                return SigScheme.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SigScheme valueOf(int value) {
            return forNumber(value);
        }

        public static SigScheme forNumber(int value) {
            if (value == 1) {
                return HMAC_SHA256;
            }
            if (value == 2) {
                return ECDSA_P256_SHA256;
            }
            if (value == 3) {
                return RSA2048_SHA256;
            }
            return null;
        }

        public static Internal.EnumLiteMap<SigScheme> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SigSchemeVerifier.INSTANCE;
        }

        private static final class SigSchemeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SigSchemeVerifier();

            private SigSchemeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return SigScheme.forNumber(number) != null;
            }
        }

        SigScheme(int value) {
            this.value = value;
        }
    }

    public enum EncScheme implements Internal.EnumLite {
        NONE(1),
        AES_256_CBC(2);

        public static final int AES_256_CBC_VALUE = 2;
        public static final int NONE_VALUE = 1;
        private static final Internal.EnumLiteMap<EncScheme> internalValueMap = new Internal.EnumLiteMap<EncScheme>() { // from class: com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EncScheme.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EncScheme findValueByNumber(int number) {
                return EncScheme.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static EncScheme valueOf(int value) {
            return forNumber(value);
        }

        public static EncScheme forNumber(int value) {
            if (value == 1) {
                return NONE;
            }
            if (value == 2) {
                return AES_256_CBC;
            }
            return null;
        }

        public static Internal.EnumLiteMap<EncScheme> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return EncSchemeVerifier.INSTANCE;
        }

        private static final class EncSchemeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new EncSchemeVerifier();

            private EncSchemeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return EncScheme.forNumber(number) != null;
            }
        }

        EncScheme(int value) {
            this.value = value;
        }
    }

    public enum PublicKeyType implements Internal.EnumLite {
        EC_P256(1),
        RSA2048(2),
        DH2048_MODP(3);

        public static final int DH2048_MODP_VALUE = 3;
        public static final int EC_P256_VALUE = 1;
        public static final int RSA2048_VALUE = 2;
        private static final Internal.EnumLiteMap<PublicKeyType> internalValueMap = new Internal.EnumLiteMap<PublicKeyType>() { // from class: com.google.security.cryptauth.lib.securemessage.SecureMessageProto.PublicKeyType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PublicKeyType findValueByNumber(int number) {
                return PublicKeyType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PublicKeyType valueOf(int value) {
            return forNumber(value);
        }

        public static PublicKeyType forNumber(int value) {
            if (value == 1) {
                return EC_P256;
            }
            if (value == 2) {
                return RSA2048;
            }
            if (value == 3) {
                return DH2048_MODP;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PublicKeyType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PublicKeyTypeVerifier.INSTANCE;
        }

        private static final class PublicKeyTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new PublicKeyTypeVerifier();

            private PublicKeyTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return PublicKeyType.forNumber(number) != null;
            }
        }

        PublicKeyType(int value) {
            this.value = value;
        }
    }

    public static final class SecureMessage extends GeneratedMessageLite<SecureMessage, Builder> implements SecureMessageOrBuilder {
        private static final SecureMessage DEFAULT_INSTANCE;
        public static final int HEADER_AND_BODY_FIELD_NUMBER = 1;
        private static volatile Parser<SecureMessage> PARSER = null;
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private ByteString headerAndBody_ = ByteString.EMPTY;
        private ByteString signature_ = ByteString.EMPTY;

        private SecureMessage() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
        public boolean hasHeaderAndBody() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
        public ByteString getHeaderAndBody() {
            return this.headerAndBody_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeaderAndBody(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.headerAndBody_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeaderAndBody() {
            this.bitField0_ &= -2;
            this.headerAndBody_ = getDefaultInstance().getHeaderAndBody();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSignature(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.signature_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSignature() {
            this.bitField0_ &= -3;
            this.signature_ = getDefaultInstance().getSignature();
        }

        public static SecureMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SecureMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SecureMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SecureMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SecureMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SecureMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SecureMessage parseFrom(InputStream input) throws IOException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SecureMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SecureMessage parseDelimitedFrom(InputStream input) throws IOException {
            return (SecureMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SecureMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SecureMessage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SecureMessage parseFrom(CodedInputStream input) throws IOException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SecureMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SecureMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(SecureMessage prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SecureMessage, Builder> implements SecureMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(SecureMessage.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
            public boolean hasHeaderAndBody() {
                return ((SecureMessage) this.instance).hasHeaderAndBody();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
            public ByteString getHeaderAndBody() {
                return ((SecureMessage) this.instance).getHeaderAndBody();
            }

            public Builder setHeaderAndBody(ByteString value) {
                copyOnWrite();
                ((SecureMessage) this.instance).setHeaderAndBody(value);
                return this;
            }

            public Builder clearHeaderAndBody() {
                copyOnWrite();
                ((SecureMessage) this.instance).clearHeaderAndBody();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
            public boolean hasSignature() {
                return ((SecureMessage) this.instance).hasSignature();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SecureMessageOrBuilder
            public ByteString getSignature() {
                return ((SecureMessage) this.instance).getSignature();
            }

            public Builder setSignature(ByteString value) {
                copyOnWrite();
                ((SecureMessage) this.instance).setSignature(value);
                return this;
            }

            public Builder clearSignature() {
                copyOnWrite();
                ((SecureMessage) this.instance).clearSignature();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new SecureMessage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "headerAndBody_", "signature_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001Ԋ\u0000\u0002Ԋ\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<SecureMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (SecureMessage.class) {
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
            SecureMessage defaultInstance = new SecureMessage();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(SecureMessage.class, defaultInstance);
        }

        public static SecureMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SecureMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securemessage.SecureMessageProto$1, reason: invalid class name */
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

    public static final class Header extends GeneratedMessageLite<Header, Builder> implements HeaderOrBuilder {
        public static final int ASSOCIATED_DATA_LENGTH_FIELD_NUMBER = 7;
        public static final int DECRYPTION_KEY_ID_FIELD_NUMBER = 4;
        private static final Header DEFAULT_INSTANCE;
        public static final int ENCRYPTION_SCHEME_FIELD_NUMBER = 2;
        public static final int IV_FIELD_NUMBER = 5;
        private static volatile Parser<Header> PARSER = null;
        public static final int PUBLIC_METADATA_FIELD_NUMBER = 6;
        public static final int SIGNATURE_SCHEME_FIELD_NUMBER = 1;
        public static final int VERIFICATION_KEY_ID_FIELD_NUMBER = 3;
        private int associatedDataLength_;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private int signatureScheme_ = 1;
        private int encryptionScheme_ = 1;
        private ByteString verificationKeyId_ = ByteString.EMPTY;
        private ByteString decryptionKeyId_ = ByteString.EMPTY;
        private ByteString iv_ = ByteString.EMPTY;
        private ByteString publicMetadata_ = ByteString.EMPTY;

        private Header() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasSignatureScheme() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public SigScheme getSignatureScheme() {
            SigScheme result = SigScheme.forNumber(this.signatureScheme_);
            return result == null ? SigScheme.HMAC_SHA256 : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSignatureScheme(SigScheme value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.signatureScheme_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSignatureScheme() {
            this.bitField0_ &= -2;
            this.signatureScheme_ = 1;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasEncryptionScheme() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public EncScheme getEncryptionScheme() {
            EncScheme result = EncScheme.forNumber(this.encryptionScheme_);
            return result == null ? EncScheme.NONE : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEncryptionScheme(EncScheme value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.encryptionScheme_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEncryptionScheme() {
            this.bitField0_ &= -3;
            this.encryptionScheme_ = 1;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasVerificationKeyId() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public ByteString getVerificationKeyId() {
            return this.verificationKeyId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerificationKeyId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.verificationKeyId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVerificationKeyId() {
            this.bitField0_ &= -5;
            this.verificationKeyId_ = getDefaultInstance().getVerificationKeyId();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasDecryptionKeyId() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public ByteString getDecryptionKeyId() {
            return this.decryptionKeyId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDecryptionKeyId(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 8;
            this.decryptionKeyId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDecryptionKeyId() {
            this.bitField0_ &= -9;
            this.decryptionKeyId_ = getDefaultInstance().getDecryptionKeyId();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasIv() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public ByteString getIv() {
            return this.iv_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIv(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 16;
            this.iv_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIv() {
            this.bitField0_ &= -17;
            this.iv_ = getDefaultInstance().getIv();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasPublicMetadata() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public ByteString getPublicMetadata() {
            return this.publicMetadata_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublicMetadata(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 32;
            this.publicMetadata_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublicMetadata() {
            this.bitField0_ &= -33;
            this.publicMetadata_ = getDefaultInstance().getPublicMetadata();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public boolean hasAssociatedDataLength() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
        public int getAssociatedDataLength() {
            return this.associatedDataLength_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAssociatedDataLength(int value) {
            this.bitField0_ |= 64;
            this.associatedDataLength_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAssociatedDataLength() {
            this.bitField0_ &= -65;
            this.associatedDataLength_ = 0;
        }

        public static Header parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Header parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Header parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Header parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Header parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Header parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Header parseFrom(InputStream input) throws IOException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Header parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Header parseDelimitedFrom(InputStream input) throws IOException {
            return (Header) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Header parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Header) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Header parseFrom(CodedInputStream input) throws IOException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Header parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Header) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Header prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Header, Builder> implements HeaderOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Header.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasSignatureScheme() {
                return ((Header) this.instance).hasSignatureScheme();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public SigScheme getSignatureScheme() {
                return ((Header) this.instance).getSignatureScheme();
            }

            public Builder setSignatureScheme(SigScheme value) {
                copyOnWrite();
                ((Header) this.instance).setSignatureScheme(value);
                return this;
            }

            public Builder clearSignatureScheme() {
                copyOnWrite();
                ((Header) this.instance).clearSignatureScheme();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasEncryptionScheme() {
                return ((Header) this.instance).hasEncryptionScheme();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public EncScheme getEncryptionScheme() {
                return ((Header) this.instance).getEncryptionScheme();
            }

            public Builder setEncryptionScheme(EncScheme value) {
                copyOnWrite();
                ((Header) this.instance).setEncryptionScheme(value);
                return this;
            }

            public Builder clearEncryptionScheme() {
                copyOnWrite();
                ((Header) this.instance).clearEncryptionScheme();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasVerificationKeyId() {
                return ((Header) this.instance).hasVerificationKeyId();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public ByteString getVerificationKeyId() {
                return ((Header) this.instance).getVerificationKeyId();
            }

            public Builder setVerificationKeyId(ByteString value) {
                copyOnWrite();
                ((Header) this.instance).setVerificationKeyId(value);
                return this;
            }

            public Builder clearVerificationKeyId() {
                copyOnWrite();
                ((Header) this.instance).clearVerificationKeyId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasDecryptionKeyId() {
                return ((Header) this.instance).hasDecryptionKeyId();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public ByteString getDecryptionKeyId() {
                return ((Header) this.instance).getDecryptionKeyId();
            }

            public Builder setDecryptionKeyId(ByteString value) {
                copyOnWrite();
                ((Header) this.instance).setDecryptionKeyId(value);
                return this;
            }

            public Builder clearDecryptionKeyId() {
                copyOnWrite();
                ((Header) this.instance).clearDecryptionKeyId();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasIv() {
                return ((Header) this.instance).hasIv();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public ByteString getIv() {
                return ((Header) this.instance).getIv();
            }

            public Builder setIv(ByteString value) {
                copyOnWrite();
                ((Header) this.instance).setIv(value);
                return this;
            }

            public Builder clearIv() {
                copyOnWrite();
                ((Header) this.instance).clearIv();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasPublicMetadata() {
                return ((Header) this.instance).hasPublicMetadata();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public ByteString getPublicMetadata() {
                return ((Header) this.instance).getPublicMetadata();
            }

            public Builder setPublicMetadata(ByteString value) {
                copyOnWrite();
                ((Header) this.instance).setPublicMetadata(value);
                return this;
            }

            public Builder clearPublicMetadata() {
                copyOnWrite();
                ((Header) this.instance).clearPublicMetadata();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public boolean hasAssociatedDataLength() {
                return ((Header) this.instance).hasAssociatedDataLength();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderOrBuilder
            public int getAssociatedDataLength() {
                return ((Header) this.instance).getAssociatedDataLength();
            }

            public Builder setAssociatedDataLength(int value) {
                copyOnWrite();
                ((Header) this.instance).setAssociatedDataLength(value);
                return this;
            }

            public Builder clearAssociatedDataLength() {
                copyOnWrite();
                ((Header) this.instance).clearAssociatedDataLength();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Header();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "signatureScheme_", SigScheme.internalGetVerifier(), "encryptionScheme_", EncScheme.internalGetVerifier(), "verificationKeyId_", "decryptionKeyId_", "iv_", "publicMetadata_", "associatedDataLength_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0002\u0001Ԍ\u0000\u0002Ԍ\u0001\u0003\n\u0002\u0004\n\u0003\u0005\n\u0004\u0006\n\u0005\u0007\u000b\u0006", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Header> parser = PARSER;
                    if (parser == null) {
                        synchronized (Header.class) {
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
            Header defaultInstance = new Header();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Header.class, defaultInstance);
        }

        public static Header getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Header> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class HeaderAndBody extends GeneratedMessageLite<HeaderAndBody, Builder> implements HeaderAndBodyOrBuilder {
        public static final int BODY_FIELD_NUMBER = 2;
        private static final HeaderAndBody DEFAULT_INSTANCE;
        public static final int HEADER_FIELD_NUMBER = 1;
        private static volatile Parser<HeaderAndBody> PARSER;
        private int bitField0_;
        private Header header_;
        private byte memoizedIsInitialized = 2;
        private ByteString body_ = ByteString.EMPTY;

        private HeaderAndBody() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
        public boolean hasHeader() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
        public Header getHeader() {
            Header header = this.header_;
            return header == null ? Header.getDefaultInstance() : header;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeader(Header value) {
            if (value == null) {
                throw null;
            }
            this.header_ = value;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeader(Header.Builder builderForValue) {
            this.header_ = builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeHeader(Header value) {
            if (value == null) {
                throw null;
            }
            Header header = this.header_;
            if (header != null && header != Header.getDefaultInstance()) {
                this.header_ = Header.newBuilder(this.header_).mergeFrom(value).buildPartial();
            } else {
                this.header_ = value;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeader() {
            this.header_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
        public boolean hasBody() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
        public ByteString getBody() {
            return this.body_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBody(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.body_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBody() {
            this.bitField0_ &= -3;
            this.body_ = getDefaultInstance().getBody();
        }

        public static HeaderAndBody parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBody parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBody parseFrom(InputStream input) throws IOException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBody parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeaderAndBody parseDelimitedFrom(InputStream input) throws IOException {
            return (HeaderAndBody) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBody parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBody) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeaderAndBody parseFrom(CodedInputStream input) throws IOException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBody parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBody) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(HeaderAndBody prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HeaderAndBody, Builder> implements HeaderAndBodyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(HeaderAndBody.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
            public boolean hasHeader() {
                return ((HeaderAndBody) this.instance).hasHeader();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
            public Header getHeader() {
                return ((HeaderAndBody) this.instance).getHeader();
            }

            public Builder setHeader(Header value) {
                copyOnWrite();
                ((HeaderAndBody) this.instance).setHeader(value);
                return this;
            }

            public Builder setHeader(Header.Builder builderForValue) {
                copyOnWrite();
                ((HeaderAndBody) this.instance).setHeader(builderForValue);
                return this;
            }

            public Builder mergeHeader(Header value) {
                copyOnWrite();
                ((HeaderAndBody) this.instance).mergeHeader(value);
                return this;
            }

            public Builder clearHeader() {
                copyOnWrite();
                ((HeaderAndBody) this.instance).clearHeader();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
            public boolean hasBody() {
                return ((HeaderAndBody) this.instance).hasBody();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyOrBuilder
            public ByteString getBody() {
                return ((HeaderAndBody) this.instance).getBody();
            }

            public Builder setBody(ByteString value) {
                copyOnWrite();
                ((HeaderAndBody) this.instance).setBody(value);
                return this;
            }

            public Builder clearBody() {
                copyOnWrite();
                ((HeaderAndBody) this.instance).clearBody();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new HeaderAndBody();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "header_", "body_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ԉ\u0000\u0002Ԋ\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<HeaderAndBody> parser = PARSER;
                    if (parser == null) {
                        synchronized (HeaderAndBody.class) {
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
            HeaderAndBody defaultInstance = new HeaderAndBody();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(HeaderAndBody.class, defaultInstance);
        }

        public static HeaderAndBody getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeaderAndBody> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class HeaderAndBodyInternal extends GeneratedMessageLite<HeaderAndBodyInternal, Builder> implements HeaderAndBodyInternalOrBuilder {
        public static final int BODY_FIELD_NUMBER = 2;
        private static final HeaderAndBodyInternal DEFAULT_INSTANCE;
        public static final int HEADER_FIELD_NUMBER = 1;
        private static volatile Parser<HeaderAndBodyInternal> PARSER;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private ByteString header_ = ByteString.EMPTY;
        private ByteString body_ = ByteString.EMPTY;

        private HeaderAndBodyInternal() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
        public boolean hasHeader() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
        public ByteString getHeader() {
            return this.header_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeader(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.header_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeader() {
            this.bitField0_ &= -2;
            this.header_ = getDefaultInstance().getHeader();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
        public boolean hasBody() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
        public ByteString getBody() {
            return this.body_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBody(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.body_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBody() {
            this.bitField0_ &= -3;
            this.body_ = getDefaultInstance().getBody();
        }

        public static HeaderAndBodyInternal parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBodyInternal parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBodyInternal parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBodyInternal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBodyInternal parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeaderAndBodyInternal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeaderAndBodyInternal parseFrom(InputStream input) throws IOException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBodyInternal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeaderAndBodyInternal parseDelimitedFrom(InputStream input) throws IOException {
            return (HeaderAndBodyInternal) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBodyInternal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBodyInternal) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeaderAndBodyInternal parseFrom(CodedInputStream input) throws IOException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeaderAndBodyInternal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeaderAndBodyInternal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(HeaderAndBodyInternal prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HeaderAndBodyInternal, Builder> implements HeaderAndBodyInternalOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(HeaderAndBodyInternal.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
            public boolean hasHeader() {
                return ((HeaderAndBodyInternal) this.instance).hasHeader();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
            public ByteString getHeader() {
                return ((HeaderAndBodyInternal) this.instance).getHeader();
            }

            public Builder setHeader(ByteString value) {
                copyOnWrite();
                ((HeaderAndBodyInternal) this.instance).setHeader(value);
                return this;
            }

            public Builder clearHeader() {
                copyOnWrite();
                ((HeaderAndBodyInternal) this.instance).clearHeader();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
            public boolean hasBody() {
                return ((HeaderAndBodyInternal) this.instance).hasBody();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.HeaderAndBodyInternalOrBuilder
            public ByteString getBody() {
                return ((HeaderAndBodyInternal) this.instance).getBody();
            }

            public Builder setBody(ByteString value) {
                copyOnWrite();
                ((HeaderAndBodyInternal) this.instance).setBody(value);
                return this;
            }

            public Builder clearBody() {
                copyOnWrite();
                ((HeaderAndBodyInternal) this.instance).clearBody();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new HeaderAndBodyInternal();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "header_", "body_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001Ԋ\u0000\u0002Ԋ\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<HeaderAndBodyInternal> parser = PARSER;
                    if (parser == null) {
                        synchronized (HeaderAndBodyInternal.class) {
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
            HeaderAndBodyInternal defaultInstance = new HeaderAndBodyInternal();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(HeaderAndBodyInternal.class, defaultInstance);
        }

        public static HeaderAndBodyInternal getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeaderAndBodyInternal> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class EcP256PublicKey extends GeneratedMessageLite<EcP256PublicKey, Builder> implements EcP256PublicKeyOrBuilder {
        private static final EcP256PublicKey DEFAULT_INSTANCE;
        private static volatile Parser<EcP256PublicKey> PARSER = null;
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private ByteString x_ = ByteString.EMPTY;
        private ByteString y_ = ByteString.EMPTY;

        private EcP256PublicKey() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
        public boolean hasX() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
        public ByteString getX() {
            return this.x_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setX(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.x_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearX() {
            this.bitField0_ &= -2;
            this.x_ = getDefaultInstance().getX();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
        public boolean hasY() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
        public ByteString getY() {
            return this.y_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setY(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.y_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearY() {
            this.bitField0_ &= -3;
            this.y_ = getDefaultInstance().getY();
        }

        public static EcP256PublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcP256PublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcP256PublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcP256PublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcP256PublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static EcP256PublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static EcP256PublicKey parseFrom(InputStream input) throws IOException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EcP256PublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EcP256PublicKey parseDelimitedFrom(InputStream input) throws IOException {
            return (EcP256PublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static EcP256PublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcP256PublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static EcP256PublicKey parseFrom(CodedInputStream input) throws IOException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static EcP256PublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (EcP256PublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(EcP256PublicKey prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<EcP256PublicKey, Builder> implements EcP256PublicKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(EcP256PublicKey.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
            public boolean hasX() {
                return ((EcP256PublicKey) this.instance).hasX();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
            public ByteString getX() {
                return ((EcP256PublicKey) this.instance).getX();
            }

            public Builder setX(ByteString value) {
                copyOnWrite();
                ((EcP256PublicKey) this.instance).setX(value);
                return this;
            }

            public Builder clearX() {
                copyOnWrite();
                ((EcP256PublicKey) this.instance).clearX();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
            public boolean hasY() {
                return ((EcP256PublicKey) this.instance).hasY();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.EcP256PublicKeyOrBuilder
            public ByteString getY() {
                return ((EcP256PublicKey) this.instance).getY();
            }

            public Builder setY(ByteString value) {
                copyOnWrite();
                ((EcP256PublicKey) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((EcP256PublicKey) this.instance).clearY();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new EcP256PublicKey();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "x_", "y_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001Ԋ\u0000\u0002Ԋ\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<EcP256PublicKey> parser = PARSER;
                    if (parser == null) {
                        synchronized (EcP256PublicKey.class) {
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
            EcP256PublicKey defaultInstance = new EcP256PublicKey();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(EcP256PublicKey.class, defaultInstance);
        }

        public static EcP256PublicKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EcP256PublicKey> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SimpleRsaPublicKey extends GeneratedMessageLite<SimpleRsaPublicKey, Builder> implements SimpleRsaPublicKeyOrBuilder {
        private static final SimpleRsaPublicKey DEFAULT_INSTANCE;
        public static final int E_FIELD_NUMBER = 2;
        public static final int N_FIELD_NUMBER = 1;
        private static volatile Parser<SimpleRsaPublicKey> PARSER;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private ByteString n_ = ByteString.EMPTY;
        private int e_ = 65537;

        private SimpleRsaPublicKey() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
        public boolean hasN() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
        public ByteString getN() {
            return this.n_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setN(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.n_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearN() {
            this.bitField0_ &= -2;
            this.n_ = getDefaultInstance().getN();
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
        public boolean hasE() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
        public int getE() {
            return this.e_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setE(int value) {
            this.bitField0_ |= 2;
            this.e_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearE() {
            this.bitField0_ &= -3;
            this.e_ = 65537;
        }

        public static SimpleRsaPublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimpleRsaPublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimpleRsaPublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimpleRsaPublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimpleRsaPublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SimpleRsaPublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SimpleRsaPublicKey parseFrom(InputStream input) throws IOException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleRsaPublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimpleRsaPublicKey parseDelimitedFrom(InputStream input) throws IOException {
            return (SimpleRsaPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleRsaPublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleRsaPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SimpleRsaPublicKey parseFrom(CodedInputStream input) throws IOException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SimpleRsaPublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SimpleRsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(SimpleRsaPublicKey prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SimpleRsaPublicKey, Builder> implements SimpleRsaPublicKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(SimpleRsaPublicKey.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
            public boolean hasN() {
                return ((SimpleRsaPublicKey) this.instance).hasN();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
            public ByteString getN() {
                return ((SimpleRsaPublicKey) this.instance).getN();
            }

            public Builder setN(ByteString value) {
                copyOnWrite();
                ((SimpleRsaPublicKey) this.instance).setN(value);
                return this;
            }

            public Builder clearN() {
                copyOnWrite();
                ((SimpleRsaPublicKey) this.instance).clearN();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
            public boolean hasE() {
                return ((SimpleRsaPublicKey) this.instance).hasE();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.SimpleRsaPublicKeyOrBuilder
            public int getE() {
                return ((SimpleRsaPublicKey) this.instance).getE();
            }

            public Builder setE(int value) {
                copyOnWrite();
                ((SimpleRsaPublicKey) this.instance).setE(value);
                return this;
            }

            public Builder clearE() {
                copyOnWrite();
                ((SimpleRsaPublicKey) this.instance).clearE();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new SimpleRsaPublicKey();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "n_", "e_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԋ\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<SimpleRsaPublicKey> parser = PARSER;
                    if (parser == null) {
                        synchronized (SimpleRsaPublicKey.class) {
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
            SimpleRsaPublicKey defaultInstance = new SimpleRsaPublicKey();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(SimpleRsaPublicKey.class, defaultInstance);
        }

        public static SimpleRsaPublicKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SimpleRsaPublicKey> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DhPublicKey extends GeneratedMessageLite<DhPublicKey, Builder> implements DhPublicKeyOrBuilder {
        private static final DhPublicKey DEFAULT_INSTANCE;
        private static volatile Parser<DhPublicKey> PARSER = null;
        public static final int Y_FIELD_NUMBER = 1;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private ByteString y_ = ByteString.EMPTY;

        private DhPublicKey() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.DhPublicKeyOrBuilder
        public boolean hasY() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.DhPublicKeyOrBuilder
        public ByteString getY() {
            return this.y_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setY(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.y_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearY() {
            this.bitField0_ &= -2;
            this.y_ = getDefaultInstance().getY();
        }

        public static DhPublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DhPublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DhPublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DhPublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DhPublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DhPublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DhPublicKey parseFrom(InputStream input) throws IOException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DhPublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DhPublicKey parseDelimitedFrom(InputStream input) throws IOException {
            return (DhPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DhPublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DhPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DhPublicKey parseFrom(CodedInputStream input) throws IOException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DhPublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DhPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DhPublicKey prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DhPublicKey, Builder> implements DhPublicKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DhPublicKey.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.DhPublicKeyOrBuilder
            public boolean hasY() {
                return ((DhPublicKey) this.instance).hasY();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.DhPublicKeyOrBuilder
            public ByteString getY() {
                return ((DhPublicKey) this.instance).getY();
            }

            public Builder setY(ByteString value) {
                copyOnWrite();
                ((DhPublicKey) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((DhPublicKey) this.instance).clearY();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new DhPublicKey();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "y_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001Ԋ\u0000", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DhPublicKey> parser = PARSER;
                    if (parser == null) {
                        synchronized (DhPublicKey.class) {
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
            DhPublicKey defaultInstance = new DhPublicKey();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(DhPublicKey.class, defaultInstance);
        }

        public static DhPublicKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DhPublicKey> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class GenericPublicKey extends GeneratedMessageLite<GenericPublicKey, Builder> implements GenericPublicKeyOrBuilder {
        private static final GenericPublicKey DEFAULT_INSTANCE;
        public static final int DH2048_PUBLIC_KEY_FIELD_NUMBER = 4;
        public static final int EC_P256_PUBLIC_KEY_FIELD_NUMBER = 2;
        private static volatile Parser<GenericPublicKey> PARSER = null;
        public static final int RSA2048_PUBLIC_KEY_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private DhPublicKey dh2048PublicKey_;
        private EcP256PublicKey ecP256PublicKey_;
        private SimpleRsaPublicKey rsa2048PublicKey_;
        private byte memoizedIsInitialized = 2;
        private int type_ = 1;

        private GenericPublicKey() {
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public PublicKeyType getType() {
            PublicKeyType result = PublicKeyType.forNumber(this.type_);
            return result == null ? PublicKeyType.EC_P256 : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(PublicKeyType value) {
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

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public boolean hasEcP256PublicKey() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public EcP256PublicKey getEcP256PublicKey() {
            EcP256PublicKey ecP256PublicKey = this.ecP256PublicKey_;
            return ecP256PublicKey == null ? EcP256PublicKey.getDefaultInstance() : ecP256PublicKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEcP256PublicKey(EcP256PublicKey value) {
            if (value == null) {
                throw null;
            }
            this.ecP256PublicKey_ = value;
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEcP256PublicKey(EcP256PublicKey.Builder builderForValue) {
            this.ecP256PublicKey_ = builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEcP256PublicKey(EcP256PublicKey value) {
            if (value == null) {
                throw null;
            }
            EcP256PublicKey ecP256PublicKey = this.ecP256PublicKey_;
            if (ecP256PublicKey != null && ecP256PublicKey != EcP256PublicKey.getDefaultInstance()) {
                this.ecP256PublicKey_ = EcP256PublicKey.newBuilder(this.ecP256PublicKey_).mergeFrom(value).buildPartial();
            } else {
                this.ecP256PublicKey_ = value;
            }
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEcP256PublicKey() {
            this.ecP256PublicKey_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public boolean hasRsa2048PublicKey() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public SimpleRsaPublicKey getRsa2048PublicKey() {
            SimpleRsaPublicKey simpleRsaPublicKey = this.rsa2048PublicKey_;
            return simpleRsaPublicKey == null ? SimpleRsaPublicKey.getDefaultInstance() : simpleRsaPublicKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRsa2048PublicKey(SimpleRsaPublicKey value) {
            if (value == null) {
                throw null;
            }
            this.rsa2048PublicKey_ = value;
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRsa2048PublicKey(SimpleRsaPublicKey.Builder builderForValue) {
            this.rsa2048PublicKey_ = builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRsa2048PublicKey(SimpleRsaPublicKey value) {
            if (value == null) {
                throw null;
            }
            SimpleRsaPublicKey simpleRsaPublicKey = this.rsa2048PublicKey_;
            if (simpleRsaPublicKey != null && simpleRsaPublicKey != SimpleRsaPublicKey.getDefaultInstance()) {
                this.rsa2048PublicKey_ = SimpleRsaPublicKey.newBuilder(this.rsa2048PublicKey_).mergeFrom(value).buildPartial();
            } else {
                this.rsa2048PublicKey_ = value;
            }
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRsa2048PublicKey() {
            this.rsa2048PublicKey_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public boolean hasDh2048PublicKey() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
        public DhPublicKey getDh2048PublicKey() {
            DhPublicKey dhPublicKey = this.dh2048PublicKey_;
            return dhPublicKey == null ? DhPublicKey.getDefaultInstance() : dhPublicKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDh2048PublicKey(DhPublicKey value) {
            if (value == null) {
                throw null;
            }
            this.dh2048PublicKey_ = value;
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDh2048PublicKey(DhPublicKey.Builder builderForValue) {
            this.dh2048PublicKey_ = builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDh2048PublicKey(DhPublicKey value) {
            if (value == null) {
                throw null;
            }
            DhPublicKey dhPublicKey = this.dh2048PublicKey_;
            if (dhPublicKey != null && dhPublicKey != DhPublicKey.getDefaultInstance()) {
                this.dh2048PublicKey_ = DhPublicKey.newBuilder(this.dh2048PublicKey_).mergeFrom(value).buildPartial();
            } else {
                this.dh2048PublicKey_ = value;
            }
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDh2048PublicKey() {
            this.dh2048PublicKey_ = null;
            this.bitField0_ &= -9;
        }

        public static GenericPublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GenericPublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GenericPublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GenericPublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GenericPublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static GenericPublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static GenericPublicKey parseFrom(InputStream input) throws IOException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GenericPublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GenericPublicKey parseDelimitedFrom(InputStream input) throws IOException {
            return (GenericPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static GenericPublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GenericPublicKey) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static GenericPublicKey parseFrom(CodedInputStream input) throws IOException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static GenericPublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (GenericPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GenericPublicKey prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<GenericPublicKey, Builder> implements GenericPublicKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(GenericPublicKey.DEFAULT_INSTANCE);
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public boolean hasType() {
                return ((GenericPublicKey) this.instance).hasType();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public PublicKeyType getType() {
                return ((GenericPublicKey) this.instance).getType();
            }

            public Builder setType(PublicKeyType value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((GenericPublicKey) this.instance).clearType();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public boolean hasEcP256PublicKey() {
                return ((GenericPublicKey) this.instance).hasEcP256PublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public EcP256PublicKey getEcP256PublicKey() {
                return ((GenericPublicKey) this.instance).getEcP256PublicKey();
            }

            public Builder setEcP256PublicKey(EcP256PublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setEcP256PublicKey(value);
                return this;
            }

            public Builder setEcP256PublicKey(EcP256PublicKey.Builder builderForValue) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setEcP256PublicKey(builderForValue);
                return this;
            }

            public Builder mergeEcP256PublicKey(EcP256PublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).mergeEcP256PublicKey(value);
                return this;
            }

            public Builder clearEcP256PublicKey() {
                copyOnWrite();
                ((GenericPublicKey) this.instance).clearEcP256PublicKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public boolean hasRsa2048PublicKey() {
                return ((GenericPublicKey) this.instance).hasRsa2048PublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public SimpleRsaPublicKey getRsa2048PublicKey() {
                return ((GenericPublicKey) this.instance).getRsa2048PublicKey();
            }

            public Builder setRsa2048PublicKey(SimpleRsaPublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setRsa2048PublicKey(value);
                return this;
            }

            public Builder setRsa2048PublicKey(SimpleRsaPublicKey.Builder builderForValue) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setRsa2048PublicKey(builderForValue);
                return this;
            }

            public Builder mergeRsa2048PublicKey(SimpleRsaPublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).mergeRsa2048PublicKey(value);
                return this;
            }

            public Builder clearRsa2048PublicKey() {
                copyOnWrite();
                ((GenericPublicKey) this.instance).clearRsa2048PublicKey();
                return this;
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public boolean hasDh2048PublicKey() {
                return ((GenericPublicKey) this.instance).hasDh2048PublicKey();
            }

            @Override // com.google.security.cryptauth.lib.securemessage.SecureMessageProto.GenericPublicKeyOrBuilder
            public DhPublicKey getDh2048PublicKey() {
                return ((GenericPublicKey) this.instance).getDh2048PublicKey();
            }

            public Builder setDh2048PublicKey(DhPublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setDh2048PublicKey(value);
                return this;
            }

            public Builder setDh2048PublicKey(DhPublicKey.Builder builderForValue) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).setDh2048PublicKey(builderForValue);
                return this;
            }

            public Builder mergeDh2048PublicKey(DhPublicKey value) {
                copyOnWrite();
                ((GenericPublicKey) this.instance).mergeDh2048PublicKey(value);
                return this;
            }

            public Builder clearDh2048PublicKey() {
                copyOnWrite();
                ((GenericPublicKey) this.instance).clearDh2048PublicKey();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new GenericPublicKey();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "type_", PublicKeyType.internalGetVerifier(), "ecP256PublicKey_", "rsa2048PublicKey_", "dh2048PublicKey_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0004\u0001Ԍ\u0000\u0002Љ\u0001\u0003Љ\u0002\u0004Љ\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GenericPublicKey> parser = PARSER;
                    if (parser == null) {
                        synchronized (GenericPublicKey.class) {
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
            GenericPublicKey defaultInstance = new GenericPublicKey();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(GenericPublicKey.class, defaultInstance);
        }

        public static GenericPublicKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GenericPublicKey> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class DiagnosticInformation extends GeneratedMessageLite<DiagnosticInformation, Builder> implements DiagnosticInformationOrBuilder {
    private static final DiagnosticInformation DEFAULT_INSTANCE;
    public static final int FQDN_FIELD_NUMBER = 2;
    private static volatile Parser<DiagnosticInformation> PARSER = null;
    public static final int SESSION_ID_FIELD_NUMBER = 1;
    private String sessionId_ = "";
    private String fqdn_ = "";

    private DiagnosticInformation() {
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
    public String getSessionId() {
        return this.sessionId_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
    public ByteString getSessionIdBytes() {
        return ByteString.copyFromUtf8(this.sessionId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSessionId(String str) {
        str.getClass();
        this.sessionId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSessionId() {
        this.sessionId_ = getDefaultInstance().getSessionId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSessionIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.sessionId_ = byteString.toStringUtf8();
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
    public String getFqdn() {
        return this.fqdn_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
    public ByteString getFqdnBytes() {
        return ByteString.copyFromUtf8(this.fqdn_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFqdn(String str) {
        str.getClass();
        this.fqdn_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFqdn() {
        this.fqdn_ = getDefaultInstance().getFqdn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFqdnBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.fqdn_ = byteString.toStringUtf8();
    }

    public static DiagnosticInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DiagnosticInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DiagnosticInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DiagnosticInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DiagnosticInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DiagnosticInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DiagnosticInformation parseFrom(InputStream inputStream) throws IOException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DiagnosticInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DiagnosticInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DiagnosticInformation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DiagnosticInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DiagnosticInformation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DiagnosticInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DiagnosticInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DiagnosticInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DiagnosticInformation diagnosticInformation) {
        return DEFAULT_INSTANCE.createBuilder(diagnosticInformation);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DiagnosticInformation, Builder> implements DiagnosticInformationOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DiagnosticInformation.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
        public String getSessionId() {
            return ((DiagnosticInformation) this.instance).getSessionId();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
        public ByteString getSessionIdBytes() {
            return ((DiagnosticInformation) this.instance).getSessionIdBytes();
        }

        public Builder setSessionId(String str) {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).setSessionId(str);
            return this;
        }

        public Builder clearSessionId() {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).clearSessionId();
            return this;
        }

        public Builder setSessionIdBytes(ByteString byteString) {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).setSessionIdBytes(byteString);
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
        public String getFqdn() {
            return ((DiagnosticInformation) this.instance).getFqdn();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DiagnosticInformationOrBuilder
        public ByteString getFqdnBytes() {
            return ((DiagnosticInformation) this.instance).getFqdnBytes();
        }

        public Builder setFqdn(String str) {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).setFqdn(str);
            return this;
        }

        public Builder clearFqdn() {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).clearFqdn();
            return this;
        }

        public Builder setFqdnBytes(ByteString byteString) {
            copyOnWrite();
            ((DiagnosticInformation) this.instance).setFqdnBytes(byteString);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.DiagnosticInformation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DiagnosticInformation();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"sessionId_", "fqdn_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DiagnosticInformation> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DiagnosticInformation.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        DiagnosticInformation diagnosticInformation = new DiagnosticInformation();
        DEFAULT_INSTANCE = diagnosticInformation;
        GeneratedMessageLite.registerDefaultInstance(DiagnosticInformation.class, diagnosticInformation);
    }

    public static DiagnosticInformation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiagnosticInformation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

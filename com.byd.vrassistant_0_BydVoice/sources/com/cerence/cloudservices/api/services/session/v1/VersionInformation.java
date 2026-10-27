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
public final class VersionInformation extends GeneratedMessageLite<VersionInformation, Builder> implements VersionInformationOrBuilder {
    public static final int CERENCE_CLOUD_VERSION_FIELD_NUMBER = 1;
    private static final VersionInformation DEFAULT_INSTANCE;
    private static volatile Parser<VersionInformation> PARSER;
    private String cerenceCloudVersion_ = "";

    private VersionInformation() {
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.VersionInformationOrBuilder
    public String getCerenceCloudVersion() {
        return this.cerenceCloudVersion_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.VersionInformationOrBuilder
    public ByteString getCerenceCloudVersionBytes() {
        return ByteString.copyFromUtf8(this.cerenceCloudVersion_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCerenceCloudVersion(String str) {
        str.getClass();
        this.cerenceCloudVersion_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCerenceCloudVersion() {
        this.cerenceCloudVersion_ = getDefaultInstance().getCerenceCloudVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCerenceCloudVersionBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.cerenceCloudVersion_ = byteString.toStringUtf8();
    }

    public static VersionInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static VersionInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static VersionInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static VersionInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static VersionInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static VersionInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static VersionInformation parseFrom(InputStream inputStream) throws IOException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static VersionInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static VersionInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (VersionInformation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static VersionInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VersionInformation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static VersionInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static VersionInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VersionInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(VersionInformation versionInformation) {
        return DEFAULT_INSTANCE.createBuilder(versionInformation);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<VersionInformation, Builder> implements VersionInformationOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(VersionInformation.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.VersionInformationOrBuilder
        public String getCerenceCloudVersion() {
            return ((VersionInformation) this.instance).getCerenceCloudVersion();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.VersionInformationOrBuilder
        public ByteString getCerenceCloudVersionBytes() {
            return ((VersionInformation) this.instance).getCerenceCloudVersionBytes();
        }

        public Builder setCerenceCloudVersion(String str) {
            copyOnWrite();
            ((VersionInformation) this.instance).setCerenceCloudVersion(str);
            return this;
        }

        public Builder clearCerenceCloudVersion() {
            copyOnWrite();
            ((VersionInformation) this.instance).clearCerenceCloudVersion();
            return this;
        }

        public Builder setCerenceCloudVersionBytes(ByteString byteString) {
            copyOnWrite();
            ((VersionInformation) this.instance).setCerenceCloudVersionBytes(byteString);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.VersionInformation$1, reason: invalid class name */
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
                return new VersionInformation();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"cerenceCloudVersion_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<VersionInformation> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (VersionInformation.class) {
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
        VersionInformation versionInformation = new VersionInformation();
        DEFAULT_INSTANCE = versionInformation;
        GeneratedMessageLite.registerDefaultInstance(VersionInformation.class, versionInformation);
    }

    public static VersionInformation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<VersionInformation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

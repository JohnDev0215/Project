package com.cerence.cloudservices.api.services.session.v1;

import com.cerence.cloudservices.api.services.session.v1.DiagnosticInformation;
import com.cerence.cloudservices.api.services.session.v1.VersionInformation;
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
public final class Metadata extends GeneratedMessageLite<Metadata, Builder> implements MetadataOrBuilder {
    private static final Metadata DEFAULT_INSTANCE;
    public static final int DIAGNOSTIC_INFO_FIELD_NUMBER = 2;
    private static volatile Parser<Metadata> PARSER = null;
    public static final int VERSION_INFO_FIELD_NUMBER = 1;
    private DiagnosticInformation diagnosticInfo_;
    private VersionInformation versionInfo_;

    private Metadata() {
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
    public boolean hasVersionInfo() {
        return this.versionInfo_ != null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
    public VersionInformation getVersionInfo() {
        VersionInformation versionInformation = this.versionInfo_;
        return versionInformation == null ? VersionInformation.getDefaultInstance() : versionInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersionInfo(VersionInformation versionInformation) {
        versionInformation.getClass();
        this.versionInfo_ = versionInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeVersionInfo(VersionInformation versionInformation) {
        versionInformation.getClass();
        VersionInformation versionInformation2 = this.versionInfo_;
        if (versionInformation2 != null && versionInformation2 != VersionInformation.getDefaultInstance()) {
            this.versionInfo_ = VersionInformation.newBuilder(this.versionInfo_).mergeFrom(versionInformation).buildPartial();
        } else {
            this.versionInfo_ = versionInformation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersionInfo() {
        this.versionInfo_ = null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
    public boolean hasDiagnosticInfo() {
        return this.diagnosticInfo_ != null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
    public DiagnosticInformation getDiagnosticInfo() {
        DiagnosticInformation diagnosticInformation = this.diagnosticInfo_;
        return diagnosticInformation == null ? DiagnosticInformation.getDefaultInstance() : diagnosticInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDiagnosticInfo(DiagnosticInformation diagnosticInformation) {
        diagnosticInformation.getClass();
        this.diagnosticInfo_ = diagnosticInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDiagnosticInfo(DiagnosticInformation diagnosticInformation) {
        diagnosticInformation.getClass();
        DiagnosticInformation diagnosticInformation2 = this.diagnosticInfo_;
        if (diagnosticInformation2 != null && diagnosticInformation2 != DiagnosticInformation.getDefaultInstance()) {
            this.diagnosticInfo_ = DiagnosticInformation.newBuilder(this.diagnosticInfo_).mergeFrom(diagnosticInformation).buildPartial();
        } else {
            this.diagnosticInfo_ = diagnosticInformation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDiagnosticInfo() {
        this.diagnosticInfo_ = null;
    }

    public static Metadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Metadata parseFrom(InputStream inputStream) throws IOException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Metadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Metadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Metadata metadata) {
        return DEFAULT_INSTANCE.createBuilder(metadata);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Metadata, Builder> implements MetadataOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Metadata.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
        public boolean hasVersionInfo() {
            return ((Metadata) this.instance).hasVersionInfo();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
        public VersionInformation getVersionInfo() {
            return ((Metadata) this.instance).getVersionInfo();
        }

        public Builder setVersionInfo(VersionInformation versionInformation) {
            copyOnWrite();
            ((Metadata) this.instance).setVersionInfo(versionInformation);
            return this;
        }

        public Builder setVersionInfo(VersionInformation.Builder builder) {
            copyOnWrite();
            ((Metadata) this.instance).setVersionInfo(builder.build());
            return this;
        }

        public Builder mergeVersionInfo(VersionInformation versionInformation) {
            copyOnWrite();
            ((Metadata) this.instance).mergeVersionInfo(versionInformation);
            return this;
        }

        public Builder clearVersionInfo() {
            copyOnWrite();
            ((Metadata) this.instance).clearVersionInfo();
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
        public boolean hasDiagnosticInfo() {
            return ((Metadata) this.instance).hasDiagnosticInfo();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.MetadataOrBuilder
        public DiagnosticInformation getDiagnosticInfo() {
            return ((Metadata) this.instance).getDiagnosticInfo();
        }

        public Builder setDiagnosticInfo(DiagnosticInformation diagnosticInformation) {
            copyOnWrite();
            ((Metadata) this.instance).setDiagnosticInfo(diagnosticInformation);
            return this;
        }

        public Builder setDiagnosticInfo(DiagnosticInformation.Builder builder) {
            copyOnWrite();
            ((Metadata) this.instance).setDiagnosticInfo(builder.build());
            return this;
        }

        public Builder mergeDiagnosticInfo(DiagnosticInformation diagnosticInformation) {
            copyOnWrite();
            ((Metadata) this.instance).mergeDiagnosticInfo(diagnosticInformation);
            return this;
        }

        public Builder clearDiagnosticInfo() {
            copyOnWrite();
            ((Metadata) this.instance).clearDiagnosticInfo();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.Metadata$1, reason: invalid class name */
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
                return new Metadata();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"versionInfo_", "diagnosticInfo_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Metadata> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Metadata.class) {
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
        Metadata metadata = new Metadata();
        DEFAULT_INSTANCE = metadata;
        GeneratedMessageLite.registerDefaultInstance(Metadata.class, metadata);
    }

    public static Metadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Metadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

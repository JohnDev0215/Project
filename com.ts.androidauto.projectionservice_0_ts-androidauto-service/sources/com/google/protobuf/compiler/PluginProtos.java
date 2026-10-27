package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PluginProtos {

    public interface CodeGeneratorRequestOrBuilder extends MessageLiteOrBuilder {
        Version getCompilerVersion();

        String getFileToGenerate(int index);

        ByteString getFileToGenerateBytes(int index);

        int getFileToGenerateCount();

        List<String> getFileToGenerateList();

        String getParameter();

        ByteString getParameterBytes();

        DescriptorProtos.FileDescriptorProto getProtoFile(int index);

        int getProtoFileCount();

        List<DescriptorProtos.FileDescriptorProto> getProtoFileList();

        boolean hasCompilerVersion();

        boolean hasParameter();
    }

    public interface CodeGeneratorResponseOrBuilder extends MessageLiteOrBuilder {
        String getError();

        ByteString getErrorBytes();

        CodeGeneratorResponse.File getFile(int index);

        int getFileCount();

        List<CodeGeneratorResponse.File> getFileList();

        long getSupportedFeatures();

        boolean hasError();

        boolean hasSupportedFeatures();
    }

    public interface VersionOrBuilder extends MessageLiteOrBuilder {
        int getMajor();

        int getMinor();

        int getPatch();

        String getSuffix();

        ByteString getSuffixBytes();

        boolean hasMajor();

        boolean hasMinor();

        boolean hasPatch();

        boolean hasSuffix();
    }

    private PluginProtos() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class Version extends GeneratedMessageLite<Version, Builder> implements VersionOrBuilder {
        private static final Version DEFAULT_INSTANCE;
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        private static volatile Parser<Version> PARSER = null;
        public static final int PATCH_FIELD_NUMBER = 3;
        public static final int SUFFIX_FIELD_NUMBER = 4;
        private int bitField0_;
        private int major_;
        private int minor_;
        private int patch_;
        private String suffix_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

        private Version() {
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasMajor() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getMajor() {
            return this.major_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMajor(int value) {
            this.bitField0_ |= 1;
            this.major_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMajor() {
            this.bitField0_ &= -2;
            this.major_ = 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasMinor() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getMinor() {
            return this.minor_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinor(int value) {
            this.bitField0_ |= 2;
            this.minor_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinor() {
            this.bitField0_ &= -3;
            this.minor_ = 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasPatch() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getPatch() {
            return this.patch_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPatch(int value) {
            this.bitField0_ |= 4;
            this.patch_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPatch() {
            this.bitField0_ &= -5;
            this.patch_ = 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasSuffix() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public String getSuffix() {
            return this.suffix_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public ByteString getSuffixBytes() {
            return ByteString.copyFromUtf8(this.suffix_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuffix(String value) {
            value.getClass();
            this.bitField0_ |= 8;
            this.suffix_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSuffix() {
            this.bitField0_ &= -9;
            this.suffix_ = getDefaultInstance().getSuffix();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuffixBytes(ByteString value) {
            this.suffix_ = value.toStringUtf8();
            this.bitField0_ |= 8;
        }

        public static Version parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Version parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Version parseFrom(InputStream input) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Version parseDelimitedFrom(InputStream input) throws IOException {
            return (Version) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Version parseFrom(CodedInputStream input) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Version) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Version prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Version, Builder> implements VersionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Version.DEFAULT_INSTANCE);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasMajor() {
                return ((Version) this.instance).hasMajor();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getMajor() {
                return ((Version) this.instance).getMajor();
            }

            public Builder setMajor(int value) {
                copyOnWrite();
                ((Version) this.instance).setMajor(value);
                return this;
            }

            public Builder clearMajor() {
                copyOnWrite();
                ((Version) this.instance).clearMajor();
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasMinor() {
                return ((Version) this.instance).hasMinor();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getMinor() {
                return ((Version) this.instance).getMinor();
            }

            public Builder setMinor(int value) {
                copyOnWrite();
                ((Version) this.instance).setMinor(value);
                return this;
            }

            public Builder clearMinor() {
                copyOnWrite();
                ((Version) this.instance).clearMinor();
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasPatch() {
                return ((Version) this.instance).hasPatch();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getPatch() {
                return ((Version) this.instance).getPatch();
            }

            public Builder setPatch(int value) {
                copyOnWrite();
                ((Version) this.instance).setPatch(value);
                return this;
            }

            public Builder clearPatch() {
                copyOnWrite();
                ((Version) this.instance).clearPatch();
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasSuffix() {
                return ((Version) this.instance).hasSuffix();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public String getSuffix() {
                return ((Version) this.instance).getSuffix();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public ByteString getSuffixBytes() {
                return ((Version) this.instance).getSuffixBytes();
            }

            public Builder setSuffix(String value) {
                copyOnWrite();
                ((Version) this.instance).setSuffix(value);
                return this;
            }

            public Builder clearSuffix() {
                copyOnWrite();
                ((Version) this.instance).clearSuffix();
                return this;
            }

            public Builder setSuffixBytes(ByteString value) {
                copyOnWrite();
                ((Version) this.instance).setSuffixBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Version();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "major_", "minor_", "patch_", "suffix_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Version> parser = PARSER;
                    if (parser == null) {
                        synchronized (Version.class) {
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
            Version defaultInstance = new Version();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Version.class, defaultInstance);
        }

        public static Version getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Version> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.compiler.PluginProtos$1, reason: invalid class name */
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

    public static final class CodeGeneratorRequest extends GeneratedMessageLite<CodeGeneratorRequest, Builder> implements CodeGeneratorRequestOrBuilder {
        public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
        private static final CodeGeneratorRequest DEFAULT_INSTANCE;
        public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
        public static final int PARAMETER_FIELD_NUMBER = 2;
        private static volatile Parser<CodeGeneratorRequest> PARSER = null;
        public static final int PROTO_FILE_FIELD_NUMBER = 15;
        private int bitField0_;
        private Version compilerVersion_;
        private byte memoizedIsInitialized = 2;
        private Internal.ProtobufList<String> fileToGenerate_ = GeneratedMessageLite.emptyProtobufList();
        private String parameter_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private Internal.ProtobufList<DescriptorProtos.FileDescriptorProto> protoFile_ = emptyProtobufList();

        private CodeGeneratorRequest() {
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public List<String> getFileToGenerateList() {
            return this.fileToGenerate_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public int getFileToGenerateCount() {
            return this.fileToGenerate_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public String getFileToGenerate(int index) {
            return this.fileToGenerate_.get(index);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public ByteString getFileToGenerateBytes(int index) {
            return ByteString.copyFromUtf8(this.fileToGenerate_.get(index));
        }

        private void ensureFileToGenerateIsMutable() {
            Internal.ProtobufList<String> tmp = this.fileToGenerate_;
            if (!tmp.isModifiable()) {
                this.fileToGenerate_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFileToGenerate(int index, String value) {
            value.getClass();
            ensureFileToGenerateIsMutable();
            this.fileToGenerate_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFileToGenerate(String value) {
            value.getClass();
            ensureFileToGenerateIsMutable();
            this.fileToGenerate_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFileToGenerate(Iterable<String> values) {
            ensureFileToGenerateIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.fileToGenerate_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFileToGenerate() {
            this.fileToGenerate_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFileToGenerateBytes(ByteString value) {
            ensureFileToGenerateIsMutable();
            this.fileToGenerate_.add(value.toStringUtf8());
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public boolean hasParameter() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public String getParameter() {
            return this.parameter_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public ByteString getParameterBytes() {
            return ByteString.copyFromUtf8(this.parameter_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParameter(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.parameter_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearParameter() {
            this.bitField0_ &= -2;
            this.parameter_ = getDefaultInstance().getParameter();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParameterBytes(ByteString value) {
            this.parameter_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
            return this.protoFile_;
        }

        public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
            return this.protoFile_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public int getProtoFileCount() {
            return this.protoFile_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
            return this.protoFile_.get(index);
        }

        public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
            return this.protoFile_.get(index);
        }

        private void ensureProtoFileIsMutable() {
            Internal.ProtobufList<DescriptorProtos.FileDescriptorProto> tmp = this.protoFile_;
            if (!tmp.isModifiable()) {
                this.protoFile_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
            value.getClass();
            ensureProtoFileIsMutable();
            this.protoFile_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProtoFile(DescriptorProtos.FileDescriptorProto value) {
            value.getClass();
            ensureProtoFileIsMutable();
            this.protoFile_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
            value.getClass();
            ensureProtoFileIsMutable();
            this.protoFile_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllProtoFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> values) {
            ensureProtoFileIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.protoFile_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtoFile() {
            this.protoFile_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeProtoFile(int index) {
            ensureProtoFileIsMutable();
            this.protoFile_.remove(index);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public boolean hasCompilerVersion() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public Version getCompilerVersion() {
            Version version = this.compilerVersion_;
            return version == null ? Version.getDefaultInstance() : version;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompilerVersion(Version value) {
            value.getClass();
            this.compilerVersion_ = value;
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCompilerVersion(Version value) {
            value.getClass();
            Version version = this.compilerVersion_;
            if (version != null && version != Version.getDefaultInstance()) {
                this.compilerVersion_ = Version.newBuilder(this.compilerVersion_).mergeFrom(value).buildPartial();
            } else {
                this.compilerVersion_ = value;
            }
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCompilerVersion() {
            this.compilerVersion_ = null;
            this.bitField0_ &= -3;
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorRequest parseFrom(InputStream input) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (CodeGeneratorRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream input) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CodeGeneratorRequest prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CodeGeneratorRequest, Builder> implements CodeGeneratorRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(CodeGeneratorRequest.DEFAULT_INSTANCE);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public List<String> getFileToGenerateList() {
                return Collections.unmodifiableList(((CodeGeneratorRequest) this.instance).getFileToGenerateList());
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public int getFileToGenerateCount() {
                return ((CodeGeneratorRequest) this.instance).getFileToGenerateCount();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public String getFileToGenerate(int index) {
                return ((CodeGeneratorRequest) this.instance).getFileToGenerate(index);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public ByteString getFileToGenerateBytes(int index) {
                return ((CodeGeneratorRequest) this.instance).getFileToGenerateBytes(index);
            }

            public Builder setFileToGenerate(int index, String value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setFileToGenerate(index, value);
                return this;
            }

            public Builder addFileToGenerate(String value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addFileToGenerate(value);
                return this;
            }

            public Builder addAllFileToGenerate(Iterable<String> values) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addAllFileToGenerate(values);
                return this;
            }

            public Builder clearFileToGenerate() {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).clearFileToGenerate();
                return this;
            }

            public Builder addFileToGenerateBytes(ByteString value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addFileToGenerateBytes(value);
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public boolean hasParameter() {
                return ((CodeGeneratorRequest) this.instance).hasParameter();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public String getParameter() {
                return ((CodeGeneratorRequest) this.instance).getParameter();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public ByteString getParameterBytes() {
                return ((CodeGeneratorRequest) this.instance).getParameterBytes();
            }

            public Builder setParameter(String value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setParameter(value);
                return this;
            }

            public Builder clearParameter() {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).clearParameter();
                return this;
            }

            public Builder setParameterBytes(ByteString value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setParameterBytes(value);
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
                return Collections.unmodifiableList(((CodeGeneratorRequest) this.instance).getProtoFileList());
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public int getProtoFileCount() {
                return ((CodeGeneratorRequest) this.instance).getProtoFileCount();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
                return ((CodeGeneratorRequest) this.instance).getProtoFile(index);
            }

            public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setProtoFile(index, value);
                return this;
            }

            public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setProtoFile(index, builderForValue.build());
                return this;
            }

            public Builder addProtoFile(DescriptorProtos.FileDescriptorProto value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addProtoFile(value);
                return this;
            }

            public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addProtoFile(index, value);
                return this;
            }

            public Builder addProtoFile(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addProtoFile(builderForValue.build());
                return this;
            }

            public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addProtoFile(index, builderForValue.build());
                return this;
            }

            public Builder addAllProtoFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> values) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).addAllProtoFile(values);
                return this;
            }

            public Builder clearProtoFile() {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).clearProtoFile();
                return this;
            }

            public Builder removeProtoFile(int index) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).removeProtoFile(index);
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public boolean hasCompilerVersion() {
                return ((CodeGeneratorRequest) this.instance).hasCompilerVersion();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public Version getCompilerVersion() {
                return ((CodeGeneratorRequest) this.instance).getCompilerVersion();
            }

            public Builder setCompilerVersion(Version value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setCompilerVersion(value);
                return this;
            }

            public Builder setCompilerVersion(Version.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).setCompilerVersion(builderForValue.build());
                return this;
            }

            public Builder mergeCompilerVersion(Version value) {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).mergeCompilerVersion(value);
                return this;
            }

            public Builder clearCompilerVersion() {
                copyOnWrite();
                ((CodeGeneratorRequest) this.instance).clearCompilerVersion();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new CodeGeneratorRequest();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "fileToGenerate_", "parameter_", "compilerVersion_", "protoFile_", DescriptorProtos.FileDescriptorProto.class};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u000f\u0004\u0000\u0002\u0001\u0001\u001a\u0002ဈ\u0000\u0003ဉ\u0001\u000fЛ", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CodeGeneratorRequest> parser = PARSER;
                    if (parser == null) {
                        synchronized (CodeGeneratorRequest.class) {
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
            CodeGeneratorRequest defaultInstance = new CodeGeneratorRequest();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(CodeGeneratorRequest.class, defaultInstance);
        }

        public static CodeGeneratorRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CodeGeneratorRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class CodeGeneratorResponse extends GeneratedMessageLite<CodeGeneratorResponse, Builder> implements CodeGeneratorResponseOrBuilder {
        private static final CodeGeneratorResponse DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 1;
        public static final int FILE_FIELD_NUMBER = 15;
        private static volatile Parser<CodeGeneratorResponse> PARSER = null;
        public static final int SUPPORTED_FEATURES_FIELD_NUMBER = 2;
        private int bitField0_;
        private String error_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        private Internal.ProtobufList<File> file_ = emptyProtobufList();
        private long supportedFeatures_;

        public interface FileOrBuilder extends MessageLiteOrBuilder {
            String getContent();

            ByteString getContentBytes();

            DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo();

            String getInsertionPoint();

            ByteString getInsertionPointBytes();

            String getName();

            ByteString getNameBytes();

            boolean hasContent();

            boolean hasGeneratedCodeInfo();

            boolean hasInsertionPoint();

            boolean hasName();
        }

        private CodeGeneratorResponse() {
        }

        public enum Feature implements Internal.EnumLite {
            FEATURE_NONE(0),
            FEATURE_PROTO3_OPTIONAL(1);

            public static final int FEATURE_NONE_VALUE = 0;
            public static final int FEATURE_PROTO3_OPTIONAL_VALUE = 1;
            private static final Internal.EnumLiteMap<Feature> internalValueMap = new Internal.EnumLiteMap<Feature>() { // from class: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.Feature.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Feature findValueByNumber(int number) {
                    return Feature.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Feature valueOf(int value) {
                return forNumber(value);
            }

            public static Feature forNumber(int value) {
                if (value == 0) {
                    return FEATURE_NONE;
                }
                if (value == 1) {
                    return FEATURE_PROTO3_OPTIONAL;
                }
                return null;
            }

            public static Internal.EnumLiteMap<Feature> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return FeatureVerifier.INSTANCE;
            }

            private static final class FeatureVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new FeatureVerifier();

                private FeatureVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return Feature.forNumber(number) != null;
                }
            }

            Feature(int value) {
                this.value = value;
            }
        }

        public static final class File extends GeneratedMessageLite<File, Builder> implements FileOrBuilder {
            public static final int CONTENT_FIELD_NUMBER = 15;
            private static final File DEFAULT_INSTANCE;
            public static final int GENERATED_CODE_INFO_FIELD_NUMBER = 16;
            public static final int INSERTION_POINT_FIELD_NUMBER = 2;
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<File> PARSER;
            private int bitField0_;
            private DescriptorProtos.GeneratedCodeInfo generatedCodeInfo_;
            private String name_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
            private String insertionPoint_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;
            private String content_ = AndroidAutoConst.DEFAULT_SCREEN_NAME;

            private File() {
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setName(String value) {
                value.getClass();
                this.bitField0_ |= 1;
                this.name_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearName() {
                this.bitField0_ &= -2;
                this.name_ = getDefaultInstance().getName();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setNameBytes(ByteString value) {
                this.name_ = value.toStringUtf8();
                this.bitField0_ |= 1;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasInsertionPoint() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getInsertionPoint() {
                return this.insertionPoint_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public ByteString getInsertionPointBytes() {
                return ByteString.copyFromUtf8(this.insertionPoint_);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setInsertionPoint(String value) {
                value.getClass();
                this.bitField0_ |= 2;
                this.insertionPoint_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearInsertionPoint() {
                this.bitField0_ &= -3;
                this.insertionPoint_ = getDefaultInstance().getInsertionPoint();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setInsertionPointBytes(ByteString value) {
                this.insertionPoint_ = value.toStringUtf8();
                this.bitField0_ |= 2;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasContent() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getContent() {
                return this.content_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public ByteString getContentBytes() {
                return ByteString.copyFromUtf8(this.content_);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setContent(String value) {
                value.getClass();
                this.bitField0_ |= 4;
                this.content_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearContent() {
                this.bitField0_ &= -5;
                this.content_ = getDefaultInstance().getContent();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setContentBytes(ByteString value) {
                this.content_ = value.toStringUtf8();
                this.bitField0_ |= 4;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasGeneratedCodeInfo() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() {
                DescriptorProtos.GeneratedCodeInfo generatedCodeInfo = this.generatedCodeInfo_;
                return generatedCodeInfo == null ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : generatedCodeInfo;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
                value.getClass();
                this.generatedCodeInfo_ = value;
                this.bitField0_ |= 8;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
                value.getClass();
                DescriptorProtos.GeneratedCodeInfo generatedCodeInfo = this.generatedCodeInfo_;
                if (generatedCodeInfo != null && generatedCodeInfo != DescriptorProtos.GeneratedCodeInfo.getDefaultInstance()) {
                    this.generatedCodeInfo_ = DescriptorProtos.GeneratedCodeInfo.newBuilder(this.generatedCodeInfo_).mergeFrom(value).buildPartial();
                } else {
                    this.generatedCodeInfo_ = value;
                }
                this.bitField0_ |= 8;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearGeneratedCodeInfo() {
                this.generatedCodeInfo_ = null;
                this.bitField0_ &= -9;
            }

            public static File parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static File parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static File parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static File parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static File parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static File parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static File parseFrom(InputStream input) throws IOException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static File parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static File parseDelimitedFrom(InputStream input) throws IOException {
                return (File) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static File parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (File) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static File parseFrom(CodedInputStream input) throws IOException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static File parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(File prototype) {
                return DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<File, Builder> implements FileOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(File.DEFAULT_INSTANCE);
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasName() {
                    return ((File) this.instance).hasName();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getName() {
                    return ((File) this.instance).getName();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getNameBytes() {
                    return ((File) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((File) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((File) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((File) this.instance).setNameBytes(value);
                    return this;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasInsertionPoint() {
                    return ((File) this.instance).hasInsertionPoint();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getInsertionPoint() {
                    return ((File) this.instance).getInsertionPoint();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getInsertionPointBytes() {
                    return ((File) this.instance).getInsertionPointBytes();
                }

                public Builder setInsertionPoint(String value) {
                    copyOnWrite();
                    ((File) this.instance).setInsertionPoint(value);
                    return this;
                }

                public Builder clearInsertionPoint() {
                    copyOnWrite();
                    ((File) this.instance).clearInsertionPoint();
                    return this;
                }

                public Builder setInsertionPointBytes(ByteString value) {
                    copyOnWrite();
                    ((File) this.instance).setInsertionPointBytes(value);
                    return this;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasContent() {
                    return ((File) this.instance).hasContent();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getContent() {
                    return ((File) this.instance).getContent();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getContentBytes() {
                    return ((File) this.instance).getContentBytes();
                }

                public Builder setContent(String value) {
                    copyOnWrite();
                    ((File) this.instance).setContent(value);
                    return this;
                }

                public Builder clearContent() {
                    copyOnWrite();
                    ((File) this.instance).clearContent();
                    return this;
                }

                public Builder setContentBytes(ByteString value) {
                    copyOnWrite();
                    ((File) this.instance).setContentBytes(value);
                    return this;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasGeneratedCodeInfo() {
                    return ((File) this.instance).hasGeneratedCodeInfo();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() {
                    return ((File) this.instance).getGeneratedCodeInfo();
                }

                public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
                    copyOnWrite();
                    ((File) this.instance).setGeneratedCodeInfo(value);
                    return this;
                }

                public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo.Builder builderForValue) {
                    copyOnWrite();
                    ((File) this.instance).setGeneratedCodeInfo(builderForValue.build());
                    return this;
                }

                public Builder mergeGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
                    copyOnWrite();
                    ((File) this.instance).mergeGeneratedCodeInfo(value);
                    return this;
                }

                public Builder clearGeneratedCodeInfo() {
                    copyOnWrite();
                    ((File) this.instance).clearGeneratedCodeInfo();
                    return this;
                }
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                AnonymousClass1 anonymousClass1 = null;
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                    case 1:
                        return new File();
                    case 2:
                        return new Builder(anonymousClass1);
                    case 3:
                        Object[] objects = {"bitField0_", "name_", "insertionPoint_", "content_", "generatedCodeInfo_"};
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0010\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u000fဈ\u0002\u0010ဉ\u0003", objects);
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<File> parser = PARSER;
                        if (parser == null) {
                            synchronized (File.class) {
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
                File defaultInstance = new File();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(File.class, defaultInstance);
            }

            public static File getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<File> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public boolean hasError() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public String getError() {
            return this.error_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public ByteString getErrorBytes() {
            return ByteString.copyFromUtf8(this.error_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(String value) {
            value.getClass();
            this.bitField0_ |= 1;
            this.error_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.bitField0_ &= -2;
            this.error_ = getDefaultInstance().getError();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorBytes(ByteString value) {
            this.error_ = value.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public boolean hasSupportedFeatures() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public long getSupportedFeatures() {
            return this.supportedFeatures_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedFeatures(long value) {
            this.bitField0_ |= 2;
            this.supportedFeatures_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedFeatures() {
            this.bitField0_ &= -3;
            this.supportedFeatures_ = 0L;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public List<File> getFileList() {
            return this.file_;
        }

        public List<? extends FileOrBuilder> getFileOrBuilderList() {
            return this.file_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public int getFileCount() {
            return this.file_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public File getFile(int index) {
            return this.file_.get(index);
        }

        public FileOrBuilder getFileOrBuilder(int index) {
            return this.file_.get(index);
        }

        private void ensureFileIsMutable() {
            Internal.ProtobufList<File> tmp = this.file_;
            if (!tmp.isModifiable()) {
                this.file_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFile(int index, File value) {
            value.getClass();
            ensureFileIsMutable();
            this.file_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFile(File value) {
            value.getClass();
            ensureFileIsMutable();
            this.file_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFile(int index, File value) {
            value.getClass();
            ensureFileIsMutable();
            this.file_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFile(Iterable<? extends File> values) {
            ensureFileIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.file_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFile() {
            this.file_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFile(int index) {
            ensureFileIsMutable();
            this.file_.remove(index);
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CodeGeneratorResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CodeGeneratorResponse parseFrom(InputStream input) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (CodeGeneratorResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream input) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CodeGeneratorResponse prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CodeGeneratorResponse, Builder> implements CodeGeneratorResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(CodeGeneratorResponse.DEFAULT_INSTANCE);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public boolean hasError() {
                return ((CodeGeneratorResponse) this.instance).hasError();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public String getError() {
                return ((CodeGeneratorResponse) this.instance).getError();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public ByteString getErrorBytes() {
                return ((CodeGeneratorResponse) this.instance).getErrorBytes();
            }

            public Builder setError(String value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).setError(value);
                return this;
            }

            public Builder clearError() {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).clearError();
                return this;
            }

            public Builder setErrorBytes(ByteString value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).setErrorBytes(value);
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public boolean hasSupportedFeatures() {
                return ((CodeGeneratorResponse) this.instance).hasSupportedFeatures();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public long getSupportedFeatures() {
                return ((CodeGeneratorResponse) this.instance).getSupportedFeatures();
            }

            public Builder setSupportedFeatures(long value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).setSupportedFeatures(value);
                return this;
            }

            public Builder clearSupportedFeatures() {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).clearSupportedFeatures();
                return this;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public List<File> getFileList() {
                return Collections.unmodifiableList(((CodeGeneratorResponse) this.instance).getFileList());
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public int getFileCount() {
                return ((CodeGeneratorResponse) this.instance).getFileCount();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public File getFile(int index) {
                return ((CodeGeneratorResponse) this.instance).getFile(index);
            }

            public Builder setFile(int index, File value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).setFile(index, value);
                return this;
            }

            public Builder setFile(int index, File.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).setFile(index, builderForValue.build());
                return this;
            }

            public Builder addFile(File value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).addFile(value);
                return this;
            }

            public Builder addFile(int index, File value) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).addFile(index, value);
                return this;
            }

            public Builder addFile(File.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).addFile(builderForValue.build());
                return this;
            }

            public Builder addFile(int index, File.Builder builderForValue) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).addFile(index, builderForValue.build());
                return this;
            }

            public Builder addAllFile(Iterable<? extends File> values) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).addAllFile(values);
                return this;
            }

            public Builder clearFile() {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).clearFile();
                return this;
            }

            public Builder removeFile(int index) {
                copyOnWrite();
                ((CodeGeneratorResponse) this.instance).removeFile(index);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new CodeGeneratorResponse();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "error_", "supportedFeatures_", "file_", File.class};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u000f\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဃ\u0001\u000f\u001b", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CodeGeneratorResponse> parser = PARSER;
                    if (parser == null) {
                        synchronized (CodeGeneratorResponse.class) {
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
            CodeGeneratorResponse defaultInstance = new CodeGeneratorResponse();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(CodeGeneratorResponse.class, defaultInstance);
        }

        public static CodeGeneratorResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CodeGeneratorResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}

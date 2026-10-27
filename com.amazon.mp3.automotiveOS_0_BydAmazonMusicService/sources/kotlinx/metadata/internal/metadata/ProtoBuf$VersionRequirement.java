package kotlinx.metadata.internal.metadata;

import java.io.IOException;
import kotlinx.metadata.internal.protobuf.AbstractMessageLite;
import kotlinx.metadata.internal.protobuf.AbstractParser;
import kotlinx.metadata.internal.protobuf.ByteString;
import kotlinx.metadata.internal.protobuf.CodedInputStream;
import kotlinx.metadata.internal.protobuf.CodedOutputStream;
import kotlinx.metadata.internal.protobuf.ExtensionRegistryLite;
import kotlinx.metadata.internal.protobuf.GeneratedMessageLite;
import kotlinx.metadata.internal.protobuf.Internal;
import kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException;
import kotlinx.metadata.internal.protobuf.Parser;

/* JADX INFO: loaded from: classes7.dex */
public final class ProtoBuf$VersionRequirement extends GeneratedMessageLite implements ProtoBuf$VersionRequirementOrBuilder {
    public static Parser<ProtoBuf$VersionRequirement> PARSER = new AbstractParser<ProtoBuf$VersionRequirement>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$VersionRequirement parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$VersionRequirement(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$VersionRequirement defaultInstance;
    private int bitField0_;
    private int errorCode_;
    private Level level_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int message_;
    private final ByteString unknownFields;
    private int versionFull_;
    private VersionKind versionKind_;
    private int version_;

    private ProtoBuf$VersionRequirement(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private ProtoBuf$VersionRequirement(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$VersionRequirement getDefaultInstance() {
        return defaultInstance;
    }

    private ProtoBuf$VersionRequirement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.version_ = codedInputStream.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.versionFull_ = codedInputStream.readInt32();
                            } else if (tag == 24) {
                                int i = codedInputStream.readEnum();
                                Level levelValueOf = Level.valueOf(i);
                                if (levelValueOf == null) {
                                    codedOutputStreamNewInstance.writeRawVarint32(tag);
                                    codedOutputStreamNewInstance.writeRawVarint32(i);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.level_ = levelValueOf;
                                }
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.errorCode_ = codedInputStream.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.message_ = codedInputStream.readInt32();
                            } else if (tag != 48) {
                                if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                }
                            } else {
                                int i2 = codedInputStream.readEnum();
                                VersionKind versionKindValueOf = VersionKind.valueOf(i2);
                                if (versionKindValueOf == null) {
                                    codedOutputStreamNewInstance.writeRawVarint32(tag);
                                    codedOutputStreamNewInstance.writeRawVarint32(i2);
                                } else {
                                    this.bitField0_ |= 32;
                                    this.versionKind_ = versionKindValueOf;
                                }
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                try {
                    codedOutputStreamNewInstance.flush();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.unknownFields = outputNewOutput.toByteString();
                    throw th2;
                }
                this.unknownFields = outputNewOutput.toByteString();
                makeExtensionsImmutable();
                throw th;
            }
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.unknownFields = outputNewOutput.toByteString();
            throw th3;
        }
        this.unknownFields = outputNewOutput.toByteString();
        makeExtensionsImmutable();
    }

    static {
        ProtoBuf$VersionRequirement protoBuf$VersionRequirement = new ProtoBuf$VersionRequirement(true);
        defaultInstance = protoBuf$VersionRequirement;
        protoBuf$VersionRequirement.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$VersionRequirement> getParserForType() {
        return PARSER;
    }

    public enum Level implements Internal.EnumLite {
        WARNING(0, 0),
        ERROR(1, 1),
        HIDDEN(2, 2);

        public static final int ERROR_VALUE = 1;
        public static final int HIDDEN_VALUE = 2;
        public static final int WARNING_VALUE = 0;
        private static Internal.EnumLiteMap<Level> internalValueMap = new Internal.EnumLiteMap<Level>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.VersionRequirement.Level.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public Level findValueByNumber(int i) {
                return Level.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Level valueOf(int i) {
            if (i == 0) {
                return WARNING;
            }
            if (i == 1) {
                return ERROR;
            }
            if (i != 2) {
                return null;
            }
            return HIDDEN;
        }

        public static Internal.EnumLiteMap<Level> internalGetValueMap() {
            return internalValueMap;
        }

        Level(int i, int i2) {
            this.value = i2;
        }
    }

    public enum VersionKind implements Internal.EnumLite {
        LANGUAGE_VERSION(0, 0),
        COMPILER_VERSION(1, 1),
        API_VERSION(2, 2);

        public static final int API_VERSION_VALUE = 2;
        public static final int COMPILER_VERSION_VALUE = 1;
        public static final int LANGUAGE_VERSION_VALUE = 0;
        private static Internal.EnumLiteMap<VersionKind> internalValueMap = new Internal.EnumLiteMap<VersionKind>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.VersionRequirement.VersionKind.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public VersionKind findValueByNumber(int i) {
                return VersionKind.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VersionKind valueOf(int i) {
            if (i == 0) {
                return LANGUAGE_VERSION;
            }
            if (i == 1) {
                return COMPILER_VERSION;
            }
            if (i != 2) {
                return null;
            }
            return API_VERSION;
        }

        public static Internal.EnumLiteMap<VersionKind> internalGetValueMap() {
            return internalValueMap;
        }

        VersionKind(int i, int i2) {
            this.value = i2;
        }
    }

    public boolean hasVersion() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public boolean hasVersionFull() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
    public int getVersionFull() {
        return this.versionFull_;
    }

    public boolean hasLevel() {
        return (this.bitField0_ & 4) == 4;
    }

    public Level getLevel() {
        return this.level_;
    }

    public boolean hasErrorCode() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
    public int getErrorCode() {
        return this.errorCode_;
    }

    public boolean hasMessage() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
    public int getMessage() {
        return this.message_;
    }

    public boolean hasVersionKind() {
        return (this.bitField0_ & 32) == 32;
    }

    public VersionKind getVersionKind() {
        return this.versionKind_;
    }

    private void initFields() {
        this.version_ = 0;
        this.versionFull_ = 0;
        this.level_ = Level.ERROR;
        this.errorCode_ = 0;
        this.message_ = 0;
        this.versionKind_ = VersionKind.LANGUAGE_VERSION;
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.AbstractMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.version_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.versionFull_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeEnumSize(3, this.level_.getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(4, this.errorCode_);
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(5, this.message_);
        }
        if ((this.bitField0_ & 32) == 32) {
            iComputeInt32Size += CodedOutputStream.computeEnumSize(6, this.versionKind_.getNumber());
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$VersionRequirement protoBuf$VersionRequirement) {
        return newBuilder().mergeFrom(protoBuf$VersionRequirement);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProtoBuf$VersionRequirement, Builder> implements ProtoBuf$VersionRequirementOrBuilder {
        private int bitField0_;
        private int errorCode_;
        private int message_;
        private int versionFull_;
        private int version_;
        private Level level_ = Level.ERROR;
        private VersionKind versionKind_ = VersionKind.LANGUAGE_VERSION;

        private void maybeForceBuilderInitialization() {
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder, kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder
        public Builder clone() {
            return create().mergeFrom(buildPartial());
        }

        @Override // kotlinx.metadata.internal.protobuf.MessageLite.Builder
        public ProtoBuf$VersionRequirement build() {
            ProtoBuf$VersionRequirement protoBuf$VersionRequirementBuildPartial = buildPartial();
            if (protoBuf$VersionRequirementBuildPartial.isInitialized()) {
                return protoBuf$VersionRequirementBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$VersionRequirementBuildPartial);
        }

        public ProtoBuf$VersionRequirement buildPartial() {
            ProtoBuf$VersionRequirement protoBuf$VersionRequirement = new ProtoBuf$VersionRequirement(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$VersionRequirement.version_ = this.version_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$VersionRequirement.versionFull_ = this.versionFull_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$VersionRequirement.level_ = this.level_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            protoBuf$VersionRequirement.errorCode_ = this.errorCode_;
            if ((i & 16) == 16) {
                i2 |= 16;
            }
            protoBuf$VersionRequirement.message_ = this.message_;
            if ((i & 32) == 32) {
                i2 |= 32;
            }
            protoBuf$VersionRequirement.versionKind_ = this.versionKind_;
            protoBuf$VersionRequirement.bitField0_ = i2;
            return protoBuf$VersionRequirement;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$VersionRequirement protoBuf$VersionRequirement) {
            if (protoBuf$VersionRequirement == ProtoBuf$VersionRequirement.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$VersionRequirement.hasVersion()) {
                setVersion(protoBuf$VersionRequirement.getVersion());
            }
            if (protoBuf$VersionRequirement.hasVersionFull()) {
                setVersionFull(protoBuf$VersionRequirement.getVersionFull());
            }
            if (protoBuf$VersionRequirement.hasLevel()) {
                setLevel(protoBuf$VersionRequirement.getLevel());
            }
            if (protoBuf$VersionRequirement.hasErrorCode()) {
                setErrorCode(protoBuf$VersionRequirement.getErrorCode());
            }
            if (protoBuf$VersionRequirement.hasMessage()) {
                setMessage(protoBuf$VersionRequirement.getMessage());
            }
            if (protoBuf$VersionRequirement.hasVersionKind()) {
                setVersionKind(protoBuf$VersionRequirement.getVersionKind());
            }
            setUnknownFields(getUnknownFields().concat(protoBuf$VersionRequirement.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                if (r3 == 0) goto Le
                r2.mergeFrom(r3)
            Le:
                return r2
            Lf:
                r3 = move-exception
                goto L1b
            L11:
                r3 = move-exception
                kotlinx.metadata.internal.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement) r4     // Catch: java.lang.Throwable -> Lf
                throw r3     // Catch: java.lang.Throwable -> L19
            L19:
                r3 = move-exception
                r0 = r4
            L1b:
                if (r0 == 0) goto L20
                r2.mergeFrom(r0)
            L20:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
        public int getVersion() {
            return this.version_;
        }

        public Builder setVersion(int i) {
            this.bitField0_ |= 1;
            this.version_ = i;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
        public int getVersionFull() {
            return this.versionFull_;
        }

        public Builder setVersionFull(int i) {
            this.bitField0_ |= 2;
            this.versionFull_ = i;
            return this;
        }

        public Builder setLevel(Level level) {
            level.getClass();
            this.bitField0_ |= 4;
            this.level_ = level;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        public Builder setErrorCode(int i) {
            this.bitField0_ |= 8;
            this.errorCode_ = i;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementOrBuilder
        public int getMessage() {
            return this.message_;
        }

        public Builder setMessage(int i) {
            this.bitField0_ |= 16;
            this.message_ = i;
            return this;
        }

        public Builder setVersionKind(VersionKind versionKind) {
            versionKind.getClass();
            this.bitField0_ |= 32;
            this.versionKind_ = versionKind;
            return this;
        }
    }
}

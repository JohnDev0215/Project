package kotlinx.metadata.internal.metadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.metadata.internal.protobuf.AbstractMessageLite;
import kotlinx.metadata.internal.protobuf.AbstractParser;
import kotlinx.metadata.internal.protobuf.ByteString;
import kotlinx.metadata.internal.protobuf.CodedInputStream;
import kotlinx.metadata.internal.protobuf.CodedOutputStream;
import kotlinx.metadata.internal.protobuf.ExtensionRegistryLite;
import kotlinx.metadata.internal.protobuf.GeneratedMessageLite;
import kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException;
import kotlinx.metadata.internal.protobuf.Parser;

/* JADX INFO: loaded from: classes7.dex */
public final class ProtoBuf$Constructor extends GeneratedMessageLite.ExtendableMessage<ProtoBuf$Constructor> implements ProtoBuf$ConstructorOrBuilder {
    public static Parser<ProtoBuf$Constructor> PARSER = new AbstractParser<ProtoBuf$Constructor>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$Constructor.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$Constructor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$Constructor(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Constructor defaultInstance;
    private int bitField0_;
    private int flags_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;
    private List<ProtoBuf$ValueParameter> valueParameter_;
    private List<Integer> versionRequirement_;

    private ProtoBuf$Constructor(GeneratedMessageLite.ExtendableBuilder<ProtoBuf$Constructor, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$Constructor(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Constructor getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder
    public ProtoBuf$Constructor getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$Constructor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream.readInt32();
                            } else if (tag == 18) {
                                if ((i & 2) != 2) {
                                    this.valueParameter_ = new ArrayList();
                                    i |= 2;
                                }
                                this.valueParameter_.add((ProtoBuf$ValueParameter) codedInputStream.readMessage(ProtoBuf$ValueParameter.PARSER, extensionRegistryLite));
                            } else if (tag == 248) {
                                if ((i & 4) != 4) {
                                    this.versionRequirement_ = new ArrayList();
                                    i |= 4;
                                }
                                this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (tag != 250) {
                                if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                }
                            } else {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 4) != 4 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    i |= 4;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit);
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
                if ((i & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if ((i & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
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
        if ((i & 2) == 2) {
            this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
        }
        if ((i & 4) == 4) {
            this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
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
        ProtoBuf$Constructor protoBuf$Constructor = new ProtoBuf$Constructor(true);
        defaultInstance = protoBuf$Constructor;
        protoBuf$Constructor.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$Constructor> getParserForType() {
        return PARSER;
    }

    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    public List<ProtoBuf$ValueParameter> getValueParameterList() {
        return this.valueParameter_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
    public int getValueParameterCount() {
        return this.valueParameter_.size();
    }

    public ProtoBuf$ValueParameter getValueParameter(int i) {
        return this.valueParameter_.get(i);
    }

    public List<Integer> getVersionRequirementList() {
        return this.versionRequirement_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
    public int getVersionRequirementCount() {
        return this.versionRequirement_.size();
    }

    private void initFields() {
        this.flags_ = 6;
        this.valueParameter_ = Collections.emptyList();
        this.versionRequirement_ = Collections.emptyList();
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
        for (int i = 0; i < getValueParameterCount(); i++) {
            if (!getValueParameter(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (!extensionsAreInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.ExtendableMessage, kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.AbstractMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
        for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(2, this.valueParameter_.get(i2));
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i3).intValue());
        }
        int size = iComputeInt32Size + iComputeInt32SizeNoTag + (getVersionRequirementList().size() * 2) + extensionsSerializedSize() + this.unknownFields.size();
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

    public static Builder newBuilder(ProtoBuf$Constructor protoBuf$Constructor) {
        return newBuilder().mergeFrom(protoBuf$Constructor);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ProtoBuf$Constructor, Builder> implements ProtoBuf$ConstructorOrBuilder {
        private int bitField0_;
        private int flags_ = 6;
        private List<ProtoBuf$ValueParameter> valueParameter_ = Collections.emptyList();
        private List<Integer> versionRequirement_ = Collections.emptyList();

        private void maybeForceBuilderInitialization() {
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder, kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder
        public Builder clone() {
            return create().mergeFrom(buildPartial());
        }

        @Override // kotlinx.metadata.internal.protobuf.MessageLite.Builder
        public ProtoBuf$Constructor build() {
            ProtoBuf$Constructor protoBuf$ConstructorBuildPartial = buildPartial();
            if (protoBuf$ConstructorBuildPartial.isInitialized()) {
                return protoBuf$ConstructorBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$ConstructorBuildPartial);
        }

        public ProtoBuf$Constructor buildPartial() {
            ProtoBuf$Constructor protoBuf$Constructor = new ProtoBuf$Constructor(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            protoBuf$Constructor.flags_ = this.flags_;
            if ((this.bitField0_ & 2) == 2) {
                this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                this.bitField0_ &= -3;
            }
            protoBuf$Constructor.valueParameter_ = this.valueParameter_;
            if ((this.bitField0_ & 4) == 4) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                this.bitField0_ &= -5;
            }
            protoBuf$Constructor.versionRequirement_ = this.versionRequirement_;
            protoBuf$Constructor.bitField0_ = i;
            return protoBuf$Constructor;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Constructor protoBuf$Constructor) {
            if (protoBuf$Constructor == ProtoBuf$Constructor.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$Constructor.hasFlags()) {
                setFlags(protoBuf$Constructor.getFlags());
            }
            if (!protoBuf$Constructor.valueParameter_.isEmpty()) {
                if (this.valueParameter_.isEmpty()) {
                    this.valueParameter_ = protoBuf$Constructor.valueParameter_;
                    this.bitField0_ &= -3;
                } else {
                    ensureValueParameterIsMutable();
                    this.valueParameter_.addAll(protoBuf$Constructor.valueParameter_);
                }
            }
            if (!protoBuf$Constructor.versionRequirement_.isEmpty()) {
                if (this.versionRequirement_.isEmpty()) {
                    this.versionRequirement_ = protoBuf$Constructor.versionRequirement_;
                    this.bitField0_ &= -5;
                } else {
                    ensureVersionRequirementIsMutable();
                    this.versionRequirement_.addAll(protoBuf$Constructor.versionRequirement_);
                }
            }
            mergeExtensionFields(protoBuf$Constructor);
            setUnknownFields(getUnknownFields().concat(protoBuf$Constructor.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$Constructor.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$Constructor> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$Constructor.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$Constructor r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$Constructor) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$Constructor r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$Constructor) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$Constructor.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$Constructor$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        public Builder setFlags(int i) {
            this.bitField0_ |= 1;
            this.flags_ = i;
            return this;
        }

        private void ensureValueParameterIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.valueParameter_ = new ArrayList(this.valueParameter_);
                this.bitField0_ |= 2;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        private void ensureVersionRequirementIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                this.bitField0_ |= 4;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ConstructorOrBuilder
        public int getVersionRequirementCount() {
            return this.versionRequirement_.size();
        }
    }
}

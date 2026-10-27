package kotlinx.metadata.internal.metadata;

import java.io.IOException;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
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
public final class ProtoBuf$ValueParameter extends GeneratedMessageLite.ExtendableMessage<ProtoBuf$ValueParameter> implements ProtoBuf$ValueParameterOrBuilder {
    public static Parser<ProtoBuf$ValueParameter> PARSER = new AbstractParser<ProtoBuf$ValueParameter>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$ValueParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$ValueParameter(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$ValueParameter defaultInstance;
    private int bitField0_;
    private int flags_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int name_;
    private int typeId_;
    private ProtoBuf$Type type_;
    private final ByteString unknownFields;
    private int varargElementTypeId_;
    private ProtoBuf$Type varargElementType_;

    private ProtoBuf$ValueParameter(GeneratedMessageLite.ExtendableBuilder<ProtoBuf$ValueParameter, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$ValueParameter(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$ValueParameter getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder
    public ProtoBuf$ValueParameter getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$ValueParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        ProtoBuf$Type.Builder builder;
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
                                this.flags_ = codedInputStream.readInt32();
                            } else if (tag != 16) {
                                if (tag == 26) {
                                    builder = (this.bitField0_ & 4) == 4 ? this.type_.toBuilder() : null;
                                    ProtoBuf$Type protoBuf$Type = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                                    this.type_ = protoBuf$Type;
                                    if (builder != null) {
                                        builder.mergeFrom(protoBuf$Type);
                                        this.type_ = builder.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                } else if (tag == 34) {
                                    builder = (this.bitField0_ & 16) == 16 ? this.varargElementType_.toBuilder() : null;
                                    ProtoBuf$Type protoBuf$Type2 = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                                    this.varargElementType_ = protoBuf$Type2;
                                    if (builder != null) {
                                        builder.mergeFrom(protoBuf$Type2);
                                        this.varargElementType_ = builder.buildPartial();
                                    }
                                    this.bitField0_ |= 16;
                                } else if (tag == 40) {
                                    this.bitField0_ |= 8;
                                    this.typeId_ = codedInputStream.readInt32();
                                } else if (tag != 48) {
                                    if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 32;
                                    this.varargElementTypeId_ = codedInputStream.readInt32();
                                }
                            } else {
                                this.bitField0_ |= 2;
                                this.name_ = codedInputStream.readInt32();
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
        ProtoBuf$ValueParameter protoBuf$ValueParameter = new ProtoBuf$ValueParameter(true);
        defaultInstance = protoBuf$ValueParameter;
        protoBuf$ValueParameter.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$ValueParameter> getParserForType() {
        return PARSER;
    }

    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
    public int getName() {
        return this.name_;
    }

    public boolean hasType() {
        return (this.bitField0_ & 4) == 4;
    }

    public ProtoBuf$Type getType() {
        return this.type_;
    }

    public boolean hasTypeId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
    public int getTypeId() {
        return this.typeId_;
    }

    public boolean hasVarargElementType() {
        return (this.bitField0_ & 16) == 16;
    }

    public ProtoBuf$Type getVarargElementType() {
        return this.varargElementType_;
    }

    public boolean hasVarargElementTypeId() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
    public int getVarargElementTypeId() {
        return this.varargElementTypeId_;
    }

    private void initFields() {
        this.flags_ = 0;
        this.name_ = 0;
        this.type_ = ProtoBuf$Type.getDefaultInstance();
        this.typeId_ = 0;
        this.varargElementType_ = ProtoBuf$Type.getDefaultInstance();
        this.varargElementTypeId_ = 0;
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
        if (!hasName()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasType() && !getType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasVarargElementType() && !getVarargElementType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
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
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(3, this.type_);
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(4, this.varargElementType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(5, this.typeId_);
        }
        if ((this.bitField0_ & 32) == 32) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(6, this.varargElementTypeId_);
        }
        int iExtensionsSerializedSize = iComputeInt32Size + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iExtensionsSerializedSize;
        return iExtensionsSerializedSize;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$ValueParameter protoBuf$ValueParameter) {
        return newBuilder().mergeFrom(protoBuf$ValueParameter);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ProtoBuf$ValueParameter, Builder> implements ProtoBuf$ValueParameterOrBuilder {
        private int bitField0_;
        private int flags_;
        private int name_;
        private int typeId_;
        private int varargElementTypeId_;
        private ProtoBuf$Type type_ = ProtoBuf$Type.getDefaultInstance();
        private ProtoBuf$Type varargElementType_ = ProtoBuf$Type.getDefaultInstance();

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
        public ProtoBuf$ValueParameter build() {
            ProtoBuf$ValueParameter protoBuf$ValueParameterBuildPartial = buildPartial();
            if (protoBuf$ValueParameterBuildPartial.isInitialized()) {
                return protoBuf$ValueParameterBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$ValueParameterBuildPartial);
        }

        public ProtoBuf$ValueParameter buildPartial() {
            ProtoBuf$ValueParameter protoBuf$ValueParameter = new ProtoBuf$ValueParameter(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$ValueParameter.flags_ = this.flags_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$ValueParameter.name_ = this.name_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$ValueParameter.type_ = this.type_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            protoBuf$ValueParameter.typeId_ = this.typeId_;
            if ((i & 16) == 16) {
                i2 |= 16;
            }
            protoBuf$ValueParameter.varargElementType_ = this.varargElementType_;
            if ((i & 32) == 32) {
                i2 |= 32;
            }
            protoBuf$ValueParameter.varargElementTypeId_ = this.varargElementTypeId_;
            protoBuf$ValueParameter.bitField0_ = i2;
            return protoBuf$ValueParameter;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$ValueParameter protoBuf$ValueParameter) {
            if (protoBuf$ValueParameter == ProtoBuf$ValueParameter.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$ValueParameter.hasFlags()) {
                setFlags(protoBuf$ValueParameter.getFlags());
            }
            if (protoBuf$ValueParameter.hasName()) {
                setName(protoBuf$ValueParameter.getName());
            }
            if (protoBuf$ValueParameter.hasType()) {
                mergeType(protoBuf$ValueParameter.getType());
            }
            if (protoBuf$ValueParameter.hasTypeId()) {
                setTypeId(protoBuf$ValueParameter.getTypeId());
            }
            if (protoBuf$ValueParameter.hasVarargElementType()) {
                mergeVarargElementType(protoBuf$ValueParameter.getVarargElementType());
            }
            if (protoBuf$ValueParameter.hasVarargElementTypeId()) {
                setVarargElementTypeId(protoBuf$ValueParameter.getVarargElementTypeId());
            }
            mergeExtensionFields(protoBuf$ValueParameter);
            setUnknownFields(getUnknownFields().concat(protoBuf$ValueParameter.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        public Builder setFlags(int i) {
            this.bitField0_ |= 1;
            this.flags_ = i;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
        public int getName() {
            return this.name_;
        }

        public Builder setName(int i) {
            this.bitField0_ |= 2;
            this.name_ = i;
            return this;
        }

        public Builder mergeType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & 4) == 4 && this.type_ != ProtoBuf$Type.getDefaultInstance()) {
                this.type_ = ProtoBuf$Type.newBuilder(this.type_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.type_ = protoBuf$Type;
            }
            this.bitField0_ |= 4;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
        public int getTypeId() {
            return this.typeId_;
        }

        public Builder setTypeId(int i) {
            this.bitField0_ |= 8;
            this.typeId_ = i;
            return this;
        }

        public Builder mergeVarargElementType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & 16) == 16 && this.varargElementType_ != ProtoBuf$Type.getDefaultInstance()) {
                this.varargElementType_ = ProtoBuf$Type.newBuilder(this.varargElementType_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.varargElementType_ = protoBuf$Type;
            }
            this.bitField0_ |= 16;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameterOrBuilder
        public int getVarargElementTypeId() {
            return this.varargElementTypeId_;
        }

        public Builder setVarargElementTypeId(int i) {
            this.bitField0_ |= 32;
            this.varargElementTypeId_ = i;
            return this;
        }
    }
}

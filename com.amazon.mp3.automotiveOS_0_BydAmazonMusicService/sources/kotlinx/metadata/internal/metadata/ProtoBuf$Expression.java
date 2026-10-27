package kotlinx.metadata.internal.metadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
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
public final class ProtoBuf$Expression extends GeneratedMessageLite implements ProtoBuf$ExpressionOrBuilder {
    public static Parser<ProtoBuf$Expression> PARSER = new AbstractParser<ProtoBuf$Expression>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$Expression.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$Expression parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$Expression(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Expression defaultInstance;
    private List<ProtoBuf$Expression> andArgument_;
    private int bitField0_;
    private ConstantValue constantValue_;
    private int flags_;
    private int isInstanceTypeId_;
    private ProtoBuf$Type isInstanceType_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private List<ProtoBuf$Expression> orArgument_;
    private final ByteString unknownFields;
    private int valueParameterReference_;

    private ProtoBuf$Expression(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private ProtoBuf$Expression(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Expression getDefaultInstance() {
        return defaultInstance;
    }

    private ProtoBuf$Expression(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.flags_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.valueParameterReference_ = codedInputStream.readInt32();
                        } else if (tag == 24) {
                            int i2 = codedInputStream.readEnum();
                            ConstantValue constantValueValueOf = ConstantValue.valueOf(i2);
                            if (constantValueValueOf == null) {
                                codedOutputStreamNewInstance.writeRawVarint32(tag);
                                codedOutputStreamNewInstance.writeRawVarint32(i2);
                            } else {
                                this.bitField0_ |= 4;
                                this.constantValue_ = constantValueValueOf;
                            }
                        } else if (tag == 34) {
                            ProtoBuf$Type.Builder builder = (this.bitField0_ & 8) == 8 ? this.isInstanceType_.toBuilder() : null;
                            ProtoBuf$Type protoBuf$Type = (ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite);
                            this.isInstanceType_ = protoBuf$Type;
                            if (builder != null) {
                                builder.mergeFrom(protoBuf$Type);
                                this.isInstanceType_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.isInstanceTypeId_ = codedInputStream.readInt32();
                        } else if (tag == 50) {
                            if ((i & 32) != 32) {
                                this.andArgument_ = new ArrayList();
                                i |= 32;
                            }
                            this.andArgument_.add((ProtoBuf$Expression) codedInputStream.readMessage(PARSER, extensionRegistryLite));
                        } else if (tag != 58) {
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        } else {
                            if ((i & 64) != 64) {
                                this.orArgument_ = new ArrayList();
                                i |= 64;
                            }
                            this.orArgument_.add((ProtoBuf$Expression) codedInputStream.readMessage(PARSER, extensionRegistryLite));
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if ((i & 32) == 32) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                }
                if ((i & 64) == 64) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
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
        if ((i & 32) == 32) {
            this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
        }
        if ((i & 64) == 64) {
            this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
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
        ProtoBuf$Expression protoBuf$Expression = new ProtoBuf$Expression(true);
        defaultInstance = protoBuf$Expression;
        protoBuf$Expression.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$Expression> getParserForType() {
        return PARSER;
    }

    public enum ConstantValue implements Internal.EnumLite {
        TRUE(0, 0),
        FALSE(1, 1),
        NULL(2, 2);

        public static final int FALSE_VALUE = 1;
        public static final int NULL_VALUE = 2;
        public static final int TRUE_VALUE = 0;
        private static Internal.EnumLiteMap<ConstantValue> internalValueMap = new Internal.EnumLiteMap<ConstantValue>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.Expression.ConstantValue.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public ConstantValue findValueByNumber(int i) {
                return ConstantValue.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static ConstantValue valueOf(int i) {
            if (i == 0) {
                return TRUE;
            }
            if (i == 1) {
                return FALSE;
            }
            if (i != 2) {
                return null;
            }
            return NULL;
        }

        public static Internal.EnumLiteMap<ConstantValue> internalGetValueMap() {
            return internalValueMap;
        }

        ConstantValue(int i, int i2) {
            this.value = i2;
        }
    }

    public boolean hasFlags() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    public boolean hasValueParameterReference() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
    public int getValueParameterReference() {
        return this.valueParameterReference_;
    }

    public boolean hasConstantValue() {
        return (this.bitField0_ & 4) == 4;
    }

    public ConstantValue getConstantValue() {
        return this.constantValue_;
    }

    public boolean hasIsInstanceType() {
        return (this.bitField0_ & 8) == 8;
    }

    public ProtoBuf$Type getIsInstanceType() {
        return this.isInstanceType_;
    }

    public boolean hasIsInstanceTypeId() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
    public int getIsInstanceTypeId() {
        return this.isInstanceTypeId_;
    }

    public List<ProtoBuf$Expression> getAndArgumentList() {
        return this.andArgument_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
    public int getAndArgumentCount() {
        return this.andArgument_.size();
    }

    public ProtoBuf$Expression getAndArgument(int i) {
        return this.andArgument_.get(i);
    }

    public List<ProtoBuf$Expression> getOrArgumentList() {
        return this.orArgument_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
    public int getOrArgumentCount() {
        return this.orArgument_.size();
    }

    public ProtoBuf$Expression getOrArgument(int i) {
        return this.orArgument_.get(i);
    }

    private void initFields() {
        this.flags_ = 0;
        this.valueParameterReference_ = 0;
        this.constantValue_ = ConstantValue.TRUE;
        this.isInstanceType_ = ProtoBuf$Type.getDefaultInstance();
        this.isInstanceTypeId_ = 0;
        this.andArgument_ = Collections.emptyList();
        this.orArgument_ = Collections.emptyList();
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
        if (hasIsInstanceType() && !getIsInstanceType().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i = 0; i < getAndArgumentCount(); i++) {
            if (!getAndArgument(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < getOrArgumentCount(); i2++) {
            if (!getOrArgument(i2).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
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
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.valueParameterReference_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeEnumSize(3, this.constantValue_.getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(4, this.isInstanceType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(5, this.isInstanceTypeId_);
        }
        for (int i2 = 0; i2 < this.andArgument_.size(); i2++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(6, this.andArgument_.get(i2));
        }
        for (int i3 = 0; i3 < this.orArgument_.size(); i3++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(7, this.orArgument_.get(i3));
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

    public static Builder newBuilder(ProtoBuf$Expression protoBuf$Expression) {
        return newBuilder().mergeFrom(protoBuf$Expression);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProtoBuf$Expression, Builder> implements ProtoBuf$ExpressionOrBuilder {
        private int bitField0_;
        private int flags_;
        private int isInstanceTypeId_;
        private int valueParameterReference_;
        private ConstantValue constantValue_ = ConstantValue.TRUE;
        private ProtoBuf$Type isInstanceType_ = ProtoBuf$Type.getDefaultInstance();
        private List<ProtoBuf$Expression> andArgument_ = Collections.emptyList();
        private List<ProtoBuf$Expression> orArgument_ = Collections.emptyList();

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
        public ProtoBuf$Expression build() {
            ProtoBuf$Expression protoBuf$ExpressionBuildPartial = buildPartial();
            if (protoBuf$ExpressionBuildPartial.isInitialized()) {
                return protoBuf$ExpressionBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$ExpressionBuildPartial);
        }

        public ProtoBuf$Expression buildPartial() {
            ProtoBuf$Expression protoBuf$Expression = new ProtoBuf$Expression(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$Expression.flags_ = this.flags_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$Expression.valueParameterReference_ = this.valueParameterReference_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$Expression.constantValue_ = this.constantValue_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            protoBuf$Expression.isInstanceType_ = this.isInstanceType_;
            if ((i & 16) == 16) {
                i2 |= 16;
            }
            protoBuf$Expression.isInstanceTypeId_ = this.isInstanceTypeId_;
            if ((this.bitField0_ & 32) == 32) {
                this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                this.bitField0_ &= -33;
            }
            protoBuf$Expression.andArgument_ = this.andArgument_;
            if ((this.bitField0_ & 64) == 64) {
                this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                this.bitField0_ &= -65;
            }
            protoBuf$Expression.orArgument_ = this.orArgument_;
            protoBuf$Expression.bitField0_ = i2;
            return protoBuf$Expression;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Expression protoBuf$Expression) {
            if (protoBuf$Expression == ProtoBuf$Expression.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$Expression.hasFlags()) {
                setFlags(protoBuf$Expression.getFlags());
            }
            if (protoBuf$Expression.hasValueParameterReference()) {
                setValueParameterReference(protoBuf$Expression.getValueParameterReference());
            }
            if (protoBuf$Expression.hasConstantValue()) {
                setConstantValue(protoBuf$Expression.getConstantValue());
            }
            if (protoBuf$Expression.hasIsInstanceType()) {
                mergeIsInstanceType(protoBuf$Expression.getIsInstanceType());
            }
            if (protoBuf$Expression.hasIsInstanceTypeId()) {
                setIsInstanceTypeId(protoBuf$Expression.getIsInstanceTypeId());
            }
            if (!protoBuf$Expression.andArgument_.isEmpty()) {
                if (this.andArgument_.isEmpty()) {
                    this.andArgument_ = protoBuf$Expression.andArgument_;
                    this.bitField0_ &= -33;
                } else {
                    ensureAndArgumentIsMutable();
                    this.andArgument_.addAll(protoBuf$Expression.andArgument_);
                }
            }
            if (!protoBuf$Expression.orArgument_.isEmpty()) {
                if (this.orArgument_.isEmpty()) {
                    this.orArgument_ = protoBuf$Expression.orArgument_;
                    this.bitField0_ &= -65;
                } else {
                    ensureOrArgumentIsMutable();
                    this.orArgument_.addAll(protoBuf$Expression.orArgument_);
                }
            }
            setUnknownFields(getUnknownFields().concat(protoBuf$Expression.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$Expression.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$Expression> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$Expression.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$Expression r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$Expression) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$Expression r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$Expression) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$Expression.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$Expression$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        public Builder setFlags(int i) {
            this.bitField0_ |= 1;
            this.flags_ = i;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
        public int getValueParameterReference() {
            return this.valueParameterReference_;
        }

        public Builder setValueParameterReference(int i) {
            this.bitField0_ |= 2;
            this.valueParameterReference_ = i;
            return this;
        }

        public Builder setConstantValue(ConstantValue constantValue) {
            constantValue.getClass();
            this.bitField0_ |= 4;
            this.constantValue_ = constantValue;
            return this;
        }

        public Builder mergeIsInstanceType(ProtoBuf$Type protoBuf$Type) {
            if ((this.bitField0_ & 8) == 8 && this.isInstanceType_ != ProtoBuf$Type.getDefaultInstance()) {
                this.isInstanceType_ = ProtoBuf$Type.newBuilder(this.isInstanceType_).mergeFrom(protoBuf$Type).buildPartial();
            } else {
                this.isInstanceType_ = protoBuf$Type;
            }
            this.bitField0_ |= 8;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
        public int getIsInstanceTypeId() {
            return this.isInstanceTypeId_;
        }

        public Builder setIsInstanceTypeId(int i) {
            this.bitField0_ |= 16;
            this.isInstanceTypeId_ = i;
            return this;
        }

        private void ensureAndArgumentIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.andArgument_ = new ArrayList(this.andArgument_);
                this.bitField0_ |= 32;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
        public int getAndArgumentCount() {
            return this.andArgument_.size();
        }

        private void ensureOrArgumentIsMutable() {
            if ((this.bitField0_ & 64) != 64) {
                this.orArgument_ = new ArrayList(this.orArgument_);
                this.bitField0_ |= 64;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$ExpressionOrBuilder
        public int getOrArgumentCount() {
            return this.orArgument_.size();
        }
    }
}

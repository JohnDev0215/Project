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
import kotlinx.metadata.internal.protobuf.Internal;
import kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException;
import kotlinx.metadata.internal.protobuf.Parser;

/* JADX INFO: loaded from: classes7.dex */
public final class ProtoBuf$TypeParameter extends GeneratedMessageLite.ExtendableMessage<ProtoBuf$TypeParameter> implements ProtoBuf$TypeParameterOrBuilder {
    public static Parser<ProtoBuf$TypeParameter> PARSER = new AbstractParser<ProtoBuf$TypeParameter>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$TypeParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$TypeParameter(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$TypeParameter defaultInstance;
    private int bitField0_;
    private int id_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int name_;
    private boolean reified_;
    private final ByteString unknownFields;
    private int upperBoundIdMemoizedSerializedSize;
    private List<Integer> upperBoundId_;
    private List<ProtoBuf$Type> upperBound_;
    private Variance variance_;

    private ProtoBuf$TypeParameter(GeneratedMessageLite.ExtendableBuilder<ProtoBuf$TypeParameter, ?> extendableBuilder) {
        super(extendableBuilder);
        this.upperBoundIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$TypeParameter(boolean z) {
        this.upperBoundIdMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$TypeParameter getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder
    public ProtoBuf$TypeParameter getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$TypeParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.upperBoundIdMemoizedSerializedSize = -1;
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
                                this.id_ = codedInputStream.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.name_ = codedInputStream.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.reified_ = codedInputStream.readBool();
                            } else if (tag == 32) {
                                int i2 = codedInputStream.readEnum();
                                Variance varianceValueOf = Variance.valueOf(i2);
                                if (varianceValueOf == null) {
                                    codedOutputStreamNewInstance.writeRawVarint32(tag);
                                    codedOutputStreamNewInstance.writeRawVarint32(i2);
                                } else {
                                    this.bitField0_ |= 8;
                                    this.variance_ = varianceValueOf;
                                }
                            } else if (tag == 42) {
                                if ((i & 16) != 16) {
                                    this.upperBound_ = new ArrayList();
                                    i |= 16;
                                }
                                this.upperBound_.add((ProtoBuf$Type) codedInputStream.readMessage(ProtoBuf$Type.PARSER, extensionRegistryLite));
                            } else if (tag == 48) {
                                if ((i & 32) != 32) {
                                    this.upperBoundId_ = new ArrayList();
                                    i |= 32;
                                }
                                this.upperBoundId_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (tag != 50) {
                                if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                }
                            } else {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 32) != 32 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.upperBoundId_ = new ArrayList();
                                    i |= 32;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.upperBoundId_.add(Integer.valueOf(codedInputStream.readInt32()));
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
                if ((i & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                }
                if ((i & 32) == 32) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
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
        if ((i & 16) == 16) {
            this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
        }
        if ((i & 32) == 32) {
            this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
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
        ProtoBuf$TypeParameter protoBuf$TypeParameter = new ProtoBuf$TypeParameter(true);
        defaultInstance = protoBuf$TypeParameter;
        protoBuf$TypeParameter.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$TypeParameter> getParserForType() {
        return PARSER;
    }

    public enum Variance implements Internal.EnumLite {
        IN(0, 0),
        OUT(1, 1),
        INV(2, 2);

        public static final int INV_VALUE = 2;
        public static final int IN_VALUE = 0;
        public static final int OUT_VALUE = 1;
        private static Internal.EnumLiteMap<Variance> internalValueMap = new Internal.EnumLiteMap<Variance>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.TypeParameter.Variance.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public Variance findValueByNumber(int i) {
                return Variance.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Variance valueOf(int i) {
            if (i == 0) {
                return IN;
            }
            if (i == 1) {
                return OUT;
            }
            if (i != 2) {
                return null;
            }
            return INV;
        }

        public static Internal.EnumLiteMap<Variance> internalGetValueMap() {
            return internalValueMap;
        }

        Variance(int i, int i2) {
            this.value = i2;
        }
    }

    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
    public int getId() {
        return this.id_;
    }

    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
    public int getName() {
        return this.name_;
    }

    public boolean hasReified() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean getReified() {
        return this.reified_;
    }

    public boolean hasVariance() {
        return (this.bitField0_ & 8) == 8;
    }

    public Variance getVariance() {
        return this.variance_;
    }

    public List<ProtoBuf$Type> getUpperBoundList() {
        return this.upperBound_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
    public int getUpperBoundCount() {
        return this.upperBound_.size();
    }

    public ProtoBuf$Type getUpperBound(int i) {
        return this.upperBound_.get(i);
    }

    public List<Integer> getUpperBoundIdList() {
        return this.upperBoundId_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
    public int getUpperBoundIdCount() {
        return this.upperBoundId_.size();
    }

    private void initFields() {
        this.id_ = 0;
        this.name_ = 0;
        this.reified_ = false;
        this.variance_ = Variance.INV;
        this.upperBound_ = Collections.emptyList();
        this.upperBoundId_ = Collections.emptyList();
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
        if (!hasId()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (!hasName()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        for (int i = 0; i < getUpperBoundCount(); i++) {
            if (!getUpperBound(i).isInitialized()) {
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
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.id_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.name_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeBoolSize(3, this.reified_);
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt32Size += CodedOutputStream.computeEnumSize(4, this.variance_.getNumber());
        }
        for (int i2 = 0; i2 < this.upperBound_.size(); i2++) {
            iComputeInt32Size += CodedOutputStream.computeMessageSize(5, this.upperBound_.get(i2));
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < this.upperBoundId_.size(); i3++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.upperBoundId_.get(i3).intValue());
        }
        int iComputeInt32SizeNoTag2 = iComputeInt32Size + iComputeInt32SizeNoTag;
        if (!getUpperBoundIdList().isEmpty()) {
            iComputeInt32SizeNoTag2 = iComputeInt32SizeNoTag2 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.upperBoundIdMemoizedSerializedSize = iComputeInt32SizeNoTag;
        int iExtensionsSerializedSize = iComputeInt32SizeNoTag2 + extensionsSerializedSize() + this.unknownFields.size();
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

    public static Builder newBuilder(ProtoBuf$TypeParameter protoBuf$TypeParameter) {
        return newBuilder().mergeFrom(protoBuf$TypeParameter);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ProtoBuf$TypeParameter, Builder> implements ProtoBuf$TypeParameterOrBuilder {
        private int bitField0_;
        private int id_;
        private int name_;
        private boolean reified_;
        private Variance variance_ = Variance.INV;
        private List<ProtoBuf$Type> upperBound_ = Collections.emptyList();
        private List<Integer> upperBoundId_ = Collections.emptyList();

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
        public ProtoBuf$TypeParameter build() {
            ProtoBuf$TypeParameter protoBuf$TypeParameterBuildPartial = buildPartial();
            if (protoBuf$TypeParameterBuildPartial.isInitialized()) {
                return protoBuf$TypeParameterBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$TypeParameterBuildPartial);
        }

        public ProtoBuf$TypeParameter buildPartial() {
            ProtoBuf$TypeParameter protoBuf$TypeParameter = new ProtoBuf$TypeParameter(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$TypeParameter.id_ = this.id_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            protoBuf$TypeParameter.name_ = this.name_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            protoBuf$TypeParameter.reified_ = this.reified_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            protoBuf$TypeParameter.variance_ = this.variance_;
            if ((this.bitField0_ & 16) == 16) {
                this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                this.bitField0_ &= -17;
            }
            protoBuf$TypeParameter.upperBound_ = this.upperBound_;
            if ((this.bitField0_ & 32) == 32) {
                this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                this.bitField0_ &= -33;
            }
            protoBuf$TypeParameter.upperBoundId_ = this.upperBoundId_;
            protoBuf$TypeParameter.bitField0_ = i2;
            return protoBuf$TypeParameter;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$TypeParameter protoBuf$TypeParameter) {
            if (protoBuf$TypeParameter == ProtoBuf$TypeParameter.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$TypeParameter.hasId()) {
                setId(protoBuf$TypeParameter.getId());
            }
            if (protoBuf$TypeParameter.hasName()) {
                setName(protoBuf$TypeParameter.getName());
            }
            if (protoBuf$TypeParameter.hasReified()) {
                setReified(protoBuf$TypeParameter.getReified());
            }
            if (protoBuf$TypeParameter.hasVariance()) {
                setVariance(protoBuf$TypeParameter.getVariance());
            }
            if (!protoBuf$TypeParameter.upperBound_.isEmpty()) {
                if (this.upperBound_.isEmpty()) {
                    this.upperBound_ = protoBuf$TypeParameter.upperBound_;
                    this.bitField0_ &= -17;
                } else {
                    ensureUpperBoundIsMutable();
                    this.upperBound_.addAll(protoBuf$TypeParameter.upperBound_);
                }
            }
            if (!protoBuf$TypeParameter.upperBoundId_.isEmpty()) {
                if (this.upperBoundId_.isEmpty()) {
                    this.upperBoundId_ = protoBuf$TypeParameter.upperBoundId_;
                    this.bitField0_ &= -33;
                } else {
                    ensureUpperBoundIdIsMutable();
                    this.upperBoundId_.addAll(protoBuf$TypeParameter.upperBoundId_);
                }
            }
            mergeExtensionFields(protoBuf$TypeParameter);
            setUnknownFields(getUnknownFields().concat(protoBuf$TypeParameter.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
        public int getId() {
            return this.id_;
        }

        public Builder setId(int i) {
            this.bitField0_ |= 1;
            this.id_ = i;
            return this;
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
        public int getName() {
            return this.name_;
        }

        public Builder setName(int i) {
            this.bitField0_ |= 2;
            this.name_ = i;
            return this;
        }

        public Builder setReified(boolean z) {
            this.bitField0_ |= 4;
            this.reified_ = z;
            return this;
        }

        public Builder setVariance(Variance variance) {
            variance.getClass();
            this.bitField0_ |= 8;
            this.variance_ = variance;
            return this;
        }

        private void ensureUpperBoundIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.upperBound_ = new ArrayList(this.upperBound_);
                this.bitField0_ |= 16;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
        public int getUpperBoundCount() {
            return this.upperBound_.size();
        }

        private void ensureUpperBoundIdIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.upperBoundId_ = new ArrayList(this.upperBoundId_);
                this.bitField0_ |= 32;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameterOrBuilder
        public int getUpperBoundIdCount() {
            return this.upperBoundId_.size();
        }
    }
}

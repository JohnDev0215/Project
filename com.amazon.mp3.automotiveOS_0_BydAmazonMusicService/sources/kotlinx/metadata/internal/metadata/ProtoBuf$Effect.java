package kotlinx.metadata.internal.metadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.metadata.internal.metadata.ProtoBuf$Expression;
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
public final class ProtoBuf$Effect extends GeneratedMessageLite implements ProtoBuf$EffectOrBuilder {
    public static Parser<ProtoBuf$Effect> PARSER = new AbstractParser<ProtoBuf$Effect>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$Effect.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$Effect parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$Effect(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$Effect defaultInstance;
    private int bitField0_;
    private ProtoBuf$Expression conclusionOfConditionalEffect_;
    private List<ProtoBuf$Expression> effectConstructorArgument_;
    private EffectType effectType_;
    private InvocationKind kind_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    private ProtoBuf$Effect(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private ProtoBuf$Effect(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$Effect getDefaultInstance() {
        return defaultInstance;
    }

    private ProtoBuf$Effect(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            int i2 = codedInputStream.readEnum();
                            EffectType effectTypeValueOf = EffectType.valueOf(i2);
                            if (effectTypeValueOf == null) {
                                codedOutputStreamNewInstance.writeRawVarint32(tag);
                                codedOutputStreamNewInstance.writeRawVarint32(i2);
                            } else {
                                this.bitField0_ |= 1;
                                this.effectType_ = effectTypeValueOf;
                            }
                        } else if (tag == 18) {
                            if ((i & 2) != 2) {
                                this.effectConstructorArgument_ = new ArrayList();
                                i |= 2;
                            }
                            this.effectConstructorArgument_.add((ProtoBuf$Expression) codedInputStream.readMessage(ProtoBuf$Expression.PARSER, extensionRegistryLite));
                        } else if (tag == 26) {
                            ProtoBuf$Expression.Builder builder = (this.bitField0_ & 2) == 2 ? this.conclusionOfConditionalEffect_.toBuilder() : null;
                            ProtoBuf$Expression protoBuf$Expression = (ProtoBuf$Expression) codedInputStream.readMessage(ProtoBuf$Expression.PARSER, extensionRegistryLite);
                            this.conclusionOfConditionalEffect_ = protoBuf$Expression;
                            if (builder != null) {
                                builder.mergeFrom(protoBuf$Expression);
                                this.conclusionOfConditionalEffect_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag != 32) {
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        } else {
                            int i3 = codedInputStream.readEnum();
                            InvocationKind invocationKindValueOf = InvocationKind.valueOf(i3);
                            if (invocationKindValueOf == null) {
                                codedOutputStreamNewInstance.writeRawVarint32(tag);
                                codedOutputStreamNewInstance.writeRawVarint32(i3);
                            } else {
                                this.bitField0_ |= 4;
                                this.kind_ = invocationKindValueOf;
                            }
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if ((i & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
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
            this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
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
        ProtoBuf$Effect protoBuf$Effect = new ProtoBuf$Effect(true);
        defaultInstance = protoBuf$Effect;
        protoBuf$Effect.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$Effect> getParserForType() {
        return PARSER;
    }

    public enum EffectType implements Internal.EnumLite {
        RETURNS_CONSTANT(0, 0),
        CALLS(1, 1),
        RETURNS_NOT_NULL(2, 2);

        public static final int CALLS_VALUE = 1;
        public static final int RETURNS_CONSTANT_VALUE = 0;
        public static final int RETURNS_NOT_NULL_VALUE = 2;
        private static Internal.EnumLiteMap<EffectType> internalValueMap = new Internal.EnumLiteMap<EffectType>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.Effect.EffectType.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public EffectType findValueByNumber(int i) {
                return EffectType.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static EffectType valueOf(int i) {
            if (i == 0) {
                return RETURNS_CONSTANT;
            }
            if (i == 1) {
                return CALLS;
            }
            if (i != 2) {
                return null;
            }
            return RETURNS_NOT_NULL;
        }

        public static Internal.EnumLiteMap<EffectType> internalGetValueMap() {
            return internalValueMap;
        }

        EffectType(int i, int i2) {
            this.value = i2;
        }
    }

    public enum InvocationKind implements Internal.EnumLite {
        AT_MOST_ONCE(0, 0),
        EXACTLY_ONCE(1, 1),
        AT_LEAST_ONCE(2, 2);

        public static final int AT_LEAST_ONCE_VALUE = 2;
        public static final int AT_MOST_ONCE_VALUE = 0;
        public static final int EXACTLY_ONCE_VALUE = 1;
        private static Internal.EnumLiteMap<InvocationKind> internalValueMap = new Internal.EnumLiteMap<InvocationKind>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf.Effect.InvocationKind.1
            @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
            public InvocationKind findValueByNumber(int i) {
                return InvocationKind.valueOf(i);
            }
        };
        private final int value;

        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static InvocationKind valueOf(int i) {
            if (i == 0) {
                return AT_MOST_ONCE;
            }
            if (i == 1) {
                return EXACTLY_ONCE;
            }
            if (i != 2) {
                return null;
            }
            return AT_LEAST_ONCE;
        }

        public static Internal.EnumLiteMap<InvocationKind> internalGetValueMap() {
            return internalValueMap;
        }

        InvocationKind(int i, int i2) {
            this.value = i2;
        }
    }

    public boolean hasEffectType() {
        return (this.bitField0_ & 1) == 1;
    }

    public EffectType getEffectType() {
        return this.effectType_;
    }

    public List<ProtoBuf$Expression> getEffectConstructorArgumentList() {
        return this.effectConstructorArgument_;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$EffectOrBuilder
    public int getEffectConstructorArgumentCount() {
        return this.effectConstructorArgument_.size();
    }

    public ProtoBuf$Expression getEffectConstructorArgument(int i) {
        return this.effectConstructorArgument_.get(i);
    }

    public boolean hasConclusionOfConditionalEffect() {
        return (this.bitField0_ & 2) == 2;
    }

    public ProtoBuf$Expression getConclusionOfConditionalEffect() {
        return this.conclusionOfConditionalEffect_;
    }

    public boolean hasKind() {
        return (this.bitField0_ & 4) == 4;
    }

    public InvocationKind getKind() {
        return this.kind_;
    }

    private void initFields() {
        this.effectType_ = EffectType.RETURNS_CONSTANT;
        this.effectConstructorArgument_ = Collections.emptyList();
        this.conclusionOfConditionalEffect_ = ProtoBuf$Expression.getDefaultInstance();
        this.kind_ = InvocationKind.AT_MOST_ONCE;
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
        for (int i = 0; i < getEffectConstructorArgumentCount(); i++) {
            if (!getEffectConstructorArgument(i).isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (hasConclusionOfConditionalEffect() && !getConclusionOfConditionalEffect().isInitialized()) {
            this.memoizedIsInitialized = (byte) 0;
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
        int iComputeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.effectType_.getNumber()) + 0 : 0;
        for (int i2 = 0; i2 < this.effectConstructorArgument_.size(); i2++) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(2, this.effectConstructorArgument_.get(i2));
        }
        if ((this.bitField0_ & 2) == 2) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(3, this.conclusionOfConditionalEffect_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeEnumSize += CodedOutputStream.computeEnumSize(4, this.kind_.getNumber());
        }
        int size = iComputeEnumSize + this.unknownFields.size();
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

    public static Builder newBuilder(ProtoBuf$Effect protoBuf$Effect) {
        return newBuilder().mergeFrom(protoBuf$Effect);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProtoBuf$Effect, Builder> implements ProtoBuf$EffectOrBuilder {
        private int bitField0_;
        private EffectType effectType_ = EffectType.RETURNS_CONSTANT;
        private List<ProtoBuf$Expression> effectConstructorArgument_ = Collections.emptyList();
        private ProtoBuf$Expression conclusionOfConditionalEffect_ = ProtoBuf$Expression.getDefaultInstance();
        private InvocationKind kind_ = InvocationKind.AT_MOST_ONCE;

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
        public ProtoBuf$Effect build() {
            ProtoBuf$Effect protoBuf$EffectBuildPartial = buildPartial();
            if (protoBuf$EffectBuildPartial.isInitialized()) {
                return protoBuf$EffectBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$EffectBuildPartial);
        }

        public ProtoBuf$Effect buildPartial() {
            ProtoBuf$Effect protoBuf$Effect = new ProtoBuf$Effect(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            protoBuf$Effect.effectType_ = this.effectType_;
            if ((this.bitField0_ & 2) == 2) {
                this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                this.bitField0_ &= -3;
            }
            protoBuf$Effect.effectConstructorArgument_ = this.effectConstructorArgument_;
            if ((i & 4) == 4) {
                i2 |= 2;
            }
            protoBuf$Effect.conclusionOfConditionalEffect_ = this.conclusionOfConditionalEffect_;
            if ((i & 8) == 8) {
                i2 |= 4;
            }
            protoBuf$Effect.kind_ = this.kind_;
            protoBuf$Effect.bitField0_ = i2;
            return protoBuf$Effect;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$Effect protoBuf$Effect) {
            if (protoBuf$Effect == ProtoBuf$Effect.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$Effect.hasEffectType()) {
                setEffectType(protoBuf$Effect.getEffectType());
            }
            if (!protoBuf$Effect.effectConstructorArgument_.isEmpty()) {
                if (this.effectConstructorArgument_.isEmpty()) {
                    this.effectConstructorArgument_ = protoBuf$Effect.effectConstructorArgument_;
                    this.bitField0_ &= -3;
                } else {
                    ensureEffectConstructorArgumentIsMutable();
                    this.effectConstructorArgument_.addAll(protoBuf$Effect.effectConstructorArgument_);
                }
            }
            if (protoBuf$Effect.hasConclusionOfConditionalEffect()) {
                mergeConclusionOfConditionalEffect(protoBuf$Effect.getConclusionOfConditionalEffect());
            }
            if (protoBuf$Effect.hasKind()) {
                setKind(protoBuf$Effect.getKind());
            }
            setUnknownFields(getUnknownFields().concat(protoBuf$Effect.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$Effect.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$Effect> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$Effect.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$Effect r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$Effect) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$Effect r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$Effect) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$Effect.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$Effect$Builder");
        }

        public Builder setEffectType(EffectType effectType) {
            effectType.getClass();
            this.bitField0_ |= 1;
            this.effectType_ = effectType;
            return this;
        }

        private void ensureEffectConstructorArgumentIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.effectConstructorArgument_ = new ArrayList(this.effectConstructorArgument_);
                this.bitField0_ |= 2;
            }
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$EffectOrBuilder
        public int getEffectConstructorArgumentCount() {
            return this.effectConstructorArgument_.size();
        }

        public Builder mergeConclusionOfConditionalEffect(ProtoBuf$Expression protoBuf$Expression) {
            if ((this.bitField0_ & 4) == 4 && this.conclusionOfConditionalEffect_ != ProtoBuf$Expression.getDefaultInstance()) {
                this.conclusionOfConditionalEffect_ = ProtoBuf$Expression.newBuilder(this.conclusionOfConditionalEffect_).mergeFrom(protoBuf$Expression).buildPartial();
            } else {
                this.conclusionOfConditionalEffect_ = protoBuf$Expression;
            }
            this.bitField0_ |= 4;
            return this;
        }

        public Builder setKind(InvocationKind invocationKind) {
            invocationKind.getClass();
            this.bitField0_ |= 8;
            this.kind_ = invocationKind;
            return this;
        }
    }
}

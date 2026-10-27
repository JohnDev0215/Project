package kotlinx.metadata.internal.metadata;

import java.io.IOException;
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
public final class ProtoBuf$EnumEntry extends GeneratedMessageLite.ExtendableMessage<ProtoBuf$EnumEntry> implements ProtoBuf$EnumEntryOrBuilder {
    public static Parser<ProtoBuf$EnumEntry> PARSER = new AbstractParser<ProtoBuf$EnumEntry>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry.1
        @Override // kotlinx.metadata.internal.protobuf.Parser
        public ProtoBuf$EnumEntry parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProtoBuf$EnumEntry(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ProtoBuf$EnumEntry defaultInstance;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int name_;
    private final ByteString unknownFields;

    private ProtoBuf$EnumEntry(GeneratedMessageLite.ExtendableBuilder<ProtoBuf$EnumEntry, ?> extendableBuilder) {
        super(extendableBuilder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = extendableBuilder.getUnknownFields();
    }

    private ProtoBuf$EnumEntry(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }

    public static ProtoBuf$EnumEntry getDefaultInstance() {
        return defaultInstance;
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder
    public ProtoBuf$EnumEntry getDefaultInstanceForType() {
        return defaultInstance;
    }

    private ProtoBuf$EnumEntry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag != 8) {
                            if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.name_ = codedInputStream.readInt32();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
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
        ProtoBuf$EnumEntry protoBuf$EnumEntry = new ProtoBuf$EnumEntry(true);
        defaultInstance = protoBuf$EnumEntry;
        protoBuf$EnumEntry.initFields();
    }

    @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite, kotlinx.metadata.internal.protobuf.MessageLite
    public Parser<ProtoBuf$EnumEntry> getParserForType() {
        return PARSER;
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntryOrBuilder
    public int getName() {
        return this.name_;
    }

    private void initFields() {
        this.name_ = 0;
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
        int iComputeInt32Size = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.name_) : 0) + extensionsSerializedSize() + this.unknownFields.size();
        this.memoizedSerializedSize = iComputeInt32Size;
        return iComputeInt32Size;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(ProtoBuf$EnumEntry protoBuf$EnumEntry) {
        return newBuilder().mergeFrom(protoBuf$EnumEntry);
    }

    @Override // kotlinx.metadata.internal.protobuf.MessageLite
    public Builder toBuilder() {
        return newBuilder(this);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ProtoBuf$EnumEntry, Builder> implements ProtoBuf$EnumEntryOrBuilder {
        private int bitField0_;
        private int name_;

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
        public ProtoBuf$EnumEntry build() {
            ProtoBuf$EnumEntry protoBuf$EnumEntryBuildPartial = buildPartial();
            if (protoBuf$EnumEntryBuildPartial.isInitialized()) {
                return protoBuf$EnumEntryBuildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(protoBuf$EnumEntryBuildPartial);
        }

        public ProtoBuf$EnumEntry buildPartial() {
            ProtoBuf$EnumEntry protoBuf$EnumEntry = new ProtoBuf$EnumEntry(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            protoBuf$EnumEntry.name_ = this.name_;
            protoBuf$EnumEntry.bitField0_ = i;
            return protoBuf$EnumEntry;
        }

        @Override // kotlinx.metadata.internal.protobuf.GeneratedMessageLite.Builder
        public Builder mergeFrom(ProtoBuf$EnumEntry protoBuf$EnumEntry) {
            if (protoBuf$EnumEntry == ProtoBuf$EnumEntry.getDefaultInstance()) {
                return this;
            }
            if (protoBuf$EnumEntry.hasName()) {
                setName(protoBuf$EnumEntry.getName());
            }
            mergeExtensionFields(protoBuf$EnumEntry);
            setUnknownFields(getUnknownFields().concat(protoBuf$EnumEntry.unknownFields));
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
        @Override // kotlinx.metadata.internal.protobuf.AbstractMessageLite.Builder, kotlinx.metadata.internal.protobuf.MessageLite.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry.Builder mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream r3, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                kotlinx.metadata.internal.protobuf.Parser<kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry> r1 = kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry.PARSER     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
                kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry r3 = (kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry) r3     // Catch: java.lang.Throwable -> Lf kotlinx.metadata.internal.protobuf.InvalidProtocolBufferException -> L11
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
                kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry r4 = (kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry) r4     // Catch: java.lang.Throwable -> Lf
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
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry.Builder.mergeFrom(kotlinx.metadata.internal.protobuf.CodedInputStream, kotlinx.metadata.internal.protobuf.ExtensionRegistryLite):kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry$Builder");
        }

        @Override // kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntryOrBuilder
        public int getName() {
            return this.name_;
        }

        public Builder setName(int i) {
            this.bitField0_ |= 1;
            this.name_ = i;
            return this;
        }
    }
}

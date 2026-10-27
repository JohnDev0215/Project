package com.amazon.avod.drm.widevine;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings(justification = "Auto-generated file", value = {""})
public final class WidevineHeader {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_widevine_drm_WidevineCencHeader_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_widevine_drm_WidevineCencHeader_fieldAccessorTable;

    public interface WidevineCencHeaderOrBuilder extends MessageOrBuilder {
        int getKeyIdCount();

        int getKeyPeriod();
    }

    private WidevineHeader() {
    }

    public static final class WidevineCencHeader extends GeneratedMessage implements WidevineCencHeaderOrBuilder {
        public static Parser<WidevineCencHeader> PARSER = new AbstractParser<WidevineCencHeader>() { // from class: com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader.1
            @Override // com.google.protobuf.Parser
            public WidevineCencHeader parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new WidevineCencHeader(codedInputStream, extensionRegistryLite);
            }
        };
        private static final WidevineCencHeader defaultInstance;
        private Algorithm algorithm_;
        private int bitField0_;
        private ByteString contentId_;
        private List<ByteString> keyId_;
        private int keyPeriod_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object policy_;
        private Object provider_;
        private Object trackType_;
        private final UnknownFieldSet unknownFields;

        private WidevineCencHeader(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private WidevineCencHeader(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static WidevineCencHeader getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public WidevineCencHeader getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private WidevineCencHeader(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (true) {
                if (z) {
                    break;
                }
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    int i2 = codedInputStream.readEnum();
                                    Algorithm algorithmValueOf = Algorithm.valueOf(i2);
                                    if (algorithmValueOf == null) {
                                        builderNewBuilder.mergeVarintField(1, i2);
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.algorithm_ = algorithmValueOf;
                                    }
                                } else if (tag == 18) {
                                    if ((i & 2) != 2) {
                                        this.keyId_ = new ArrayList();
                                        i |= 2;
                                    }
                                    this.keyId_.add(codedInputStream.readBytes());
                                } else if (tag == 26) {
                                    ByteString bytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.provider_ = bytes;
                                } else if (tag == 34) {
                                    this.bitField0_ |= 4;
                                    this.contentId_ = codedInputStream.readBytes();
                                } else if (tag == 42) {
                                    ByteString bytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 8;
                                    this.trackType_ = bytes2;
                                } else if (tag == 50) {
                                    ByteString bytes3 = codedInputStream.readBytes();
                                    this.bitField0_ |= 16;
                                    this.policy_ = bytes3;
                                } else if (tag != 56) {
                                    if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 32;
                                    this.keyPeriod_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 2) == 2) {
                        this.keyId_ = Collections.unmodifiableList(this.keyId_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WidevineHeader.internal_static_widevine_drm_WidevineCencHeader_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return WidevineHeader.internal_static_widevine_drm_WidevineCencHeader_fieldAccessorTable.ensureFieldAccessorsInitialized(WidevineCencHeader.class, Builder.class);
        }

        static {
            WidevineCencHeader widevineCencHeader = new WidevineCencHeader(true);
            defaultInstance = widevineCencHeader;
            widevineCencHeader.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite
        public Parser<WidevineCencHeader> getParserForType() {
            return PARSER;
        }

        public enum Algorithm implements ProtocolMessageEnum {
            UNENCRYPTED(0, 0),
            AESCTR(1, 1);

            public static final int AESCTR_VALUE = 1;
            public static final int UNENCRYPTED_VALUE = 0;
            private final int index;
            private final int value;
            private static Internal.EnumLiteMap<Algorithm> internalValueMap = new Internal.EnumLiteMap<Algorithm>() { // from class: com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader.Algorithm.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Algorithm findValueByNumber(int i) {
                    return Algorithm.valueOf(i);
                }
            };
            private static final Algorithm[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Algorithm valueOf(int i) {
                if (i == 0) {
                    return UNENCRYPTED;
                }
                if (i != 1) {
                    return null;
                }
                return AESCTR;
            }

            public static Internal.EnumLiteMap<Algorithm> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return WidevineCencHeader.getDescriptor().getEnumTypes().get(0);
            }

            public static Algorithm valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            Algorithm(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        public boolean hasAlgorithm() {
            return (this.bitField0_ & 1) == 1;
        }

        public Algorithm getAlgorithm() {
            return this.algorithm_;
        }

        public List<ByteString> getKeyIdList() {
            return this.keyId_;
        }

        @Override // com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeaderOrBuilder
        public int getKeyIdCount() {
            return this.keyId_.size();
        }

        public ByteString getKeyId(int i) {
            return this.keyId_.get(i);
        }

        public boolean hasProvider() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getProviderBytes() {
            Object obj = this.provider_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.provider_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public boolean hasContentId() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getContentId() {
            return this.contentId_;
        }

        public boolean hasTrackType() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getTrackTypeBytes() {
            Object obj = this.trackType_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.trackType_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public boolean hasPolicy() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getPolicyBytes() {
            Object obj = this.policy_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.policy_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public boolean hasKeyPeriod() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeaderOrBuilder
        public int getKeyPeriod() {
            return this.keyPeriod_;
        }

        private void initFields() {
            this.algorithm_ = Algorithm.UNENCRYPTED;
            this.keyId_ = Collections.emptyList();
            this.provider_ = "";
            this.contentId_ = ByteString.EMPTY;
            this.trackType_ = "";
            this.policy_ = "";
            this.keyPeriod_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
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

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.algorithm_.getNumber());
            }
            for (int i = 0; i < this.keyId_.size(); i++) {
                codedOutputStream.writeBytes(2, this.keyId_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(3, getProviderBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(4, this.contentId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(5, getTrackTypeBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(6, getPolicyBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeUInt32(7, this.keyPeriod_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.algorithm_.getNumber()) + 0 : 0;
            int iComputeBytesSizeNoTag = 0;
            for (int i2 = 0; i2 < this.keyId_.size(); i2++) {
                iComputeBytesSizeNoTag += CodedOutputStream.computeBytesSizeNoTag(this.keyId_.get(i2));
            }
            int size = iComputeEnumSize + iComputeBytesSizeNoTag + (getKeyIdList().size() * 1);
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(3, getProviderBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(4, this.contentId_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBytesSize(5, getTrackTypeBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size += CodedOutputStream.computeBytesSize(6, getPolicyBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                size += CodedOutputStream.computeUInt32Size(7, this.keyPeriod_);
            }
            int serializedSize = size + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static WidevineCencHeader parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(WidevineCencHeader widevineCencHeader) {
            return newBuilder().mergeFrom(widevineCencHeader);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements WidevineCencHeaderOrBuilder {
            private Algorithm algorithm_;
            private int bitField0_;
            private ByteString contentId_;
            private List<ByteString> keyId_;
            private int keyPeriod_;
            private Object policy_;
            private Object provider_;
            private Object trackType_;

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return WidevineHeader.internal_static_widevine_drm_WidevineCencHeader_fieldAccessorTable.ensureFieldAccessorsInitialized(WidevineCencHeader.class, Builder.class);
            }

            private Builder() {
                this.algorithm_ = Algorithm.UNENCRYPTED;
                this.keyId_ = Collections.emptyList();
                this.provider_ = "";
                this.contentId_ = ByteString.EMPTY;
                this.trackType_ = "";
                this.policy_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.algorithm_ = Algorithm.UNENCRYPTED;
                this.keyId_ = Collections.emptyList();
                this.provider_ = "";
                this.contentId_ = ByteString.EMPTY;
                this.trackType_ = "";
                this.policy_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessage.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo76clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return WidevineHeader.internal_static_widevine_drm_WidevineCencHeader_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public WidevineCencHeader getDefaultInstanceForType() {
                return WidevineCencHeader.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public WidevineCencHeader build() {
                WidevineCencHeader widevineCencHeaderBuildPartial = buildPartial();
                if (widevineCencHeaderBuildPartial.isInitialized()) {
                    return widevineCencHeaderBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) widevineCencHeaderBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public WidevineCencHeader buildPartial() {
                WidevineCencHeader widevineCencHeader = new WidevineCencHeader(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                widevineCencHeader.algorithm_ = this.algorithm_;
                if ((this.bitField0_ & 2) == 2) {
                    this.keyId_ = Collections.unmodifiableList(this.keyId_);
                    this.bitField0_ &= -3;
                }
                widevineCencHeader.keyId_ = this.keyId_;
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                widevineCencHeader.provider_ = this.provider_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                widevineCencHeader.contentId_ = this.contentId_;
                if ((i & 16) == 16) {
                    i2 |= 8;
                }
                widevineCencHeader.trackType_ = this.trackType_;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                widevineCencHeader.policy_ = this.policy_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                widevineCencHeader.keyPeriod_ = this.keyPeriod_;
                widevineCencHeader.bitField0_ = i2;
                onBuilt();
                return widevineCencHeader;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof WidevineCencHeader) {
                    return mergeFrom((WidevineCencHeader) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(WidevineCencHeader widevineCencHeader) {
                if (widevineCencHeader == WidevineCencHeader.getDefaultInstance()) {
                    return this;
                }
                if (widevineCencHeader.hasAlgorithm()) {
                    setAlgorithm(widevineCencHeader.getAlgorithm());
                }
                if (!widevineCencHeader.keyId_.isEmpty()) {
                    if (this.keyId_.isEmpty()) {
                        this.keyId_ = widevineCencHeader.keyId_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureKeyIdIsMutable();
                        this.keyId_.addAll(widevineCencHeader.keyId_);
                    }
                    onChanged();
                }
                if (widevineCencHeader.hasProvider()) {
                    this.bitField0_ |= 4;
                    this.provider_ = widevineCencHeader.provider_;
                    onChanged();
                }
                if (widevineCencHeader.hasContentId()) {
                    setContentId(widevineCencHeader.getContentId());
                }
                if (widevineCencHeader.hasTrackType()) {
                    this.bitField0_ |= 16;
                    this.trackType_ = widevineCencHeader.trackType_;
                    onChanged();
                }
                if (widevineCencHeader.hasPolicy()) {
                    this.bitField0_ |= 32;
                    this.policy_ = widevineCencHeader.policy_;
                    onChanged();
                }
                if (widevineCencHeader.hasKeyPeriod()) {
                    setKeyPeriod(widevineCencHeader.getKeyPeriod());
                }
                mergeUnknownFields(widevineCencHeader.getUnknownFields());
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.avod.drm.widevine.WidevineHeader$WidevineCencHeader> r1 = com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.avod.drm.widevine.WidevineHeader$WidevineCencHeader r3 = (com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1b
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.avod.drm.widevine.WidevineHeader$WidevineCencHeader r4 = (com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeader.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.avod.drm.widevine.WidevineHeader$WidevineCencHeader$Builder");
            }

            public Builder setAlgorithm(Algorithm algorithm) {
                algorithm.getClass();
                this.bitField0_ |= 1;
                this.algorithm_ = algorithm;
                onChanged();
                return this;
            }

            private void ensureKeyIdIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.keyId_ = new ArrayList(this.keyId_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeaderOrBuilder
            public int getKeyIdCount() {
                return this.keyId_.size();
            }

            public Builder setContentId(ByteString byteString) {
                byteString.getClass();
                this.bitField0_ |= 8;
                this.contentId_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.amazon.avod.drm.widevine.WidevineHeader.WidevineCencHeaderOrBuilder
            public int getKeyPeriod() {
                return this.keyPeriod_;
            }

            public Builder setKeyPeriod(int i) {
                this.bitField0_ |= 64;
                this.keyPeriod_ = i;
                onChanged();
                return this;
            }
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0015widevine_header.proto\u0012\fwidevine_drm\"ë\u0001\n\u0012WidevineCencHeader\u0012=\n\talgorithm\u0018\u0001 \u0001(\u000e2*.widevine_drm.WidevineCencHeader.Algorithm\u0012\u000e\n\u0006key_id\u0018\u0002 \u0003(\f\u0012\u0010\n\bprovider\u0018\u0003 \u0001(\t\u0012\u0012\n\ncontent_id\u0018\u0004 \u0001(\f\u0012\u0012\n\ntrack_type\u0018\u0005 \u0001(\t\u0012\u000e\n\u0006policy\u0018\u0006 \u0001(\t\u0012\u0012\n\nkey_period\u0018\u0007 \u0001(\r\"(\n\tAlgorithm\u0012\u000f\n\u000bUNENCRYPTED\u0010\u0000\u0012\n\n\u0006AESCTR\u0010\u0001B\u001e\n\u001ccom.amazon.avod.drm.widevine"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.avod.drm.widevine.WidevineHeader.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = WidevineHeader.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_widevine_drm_WidevineCencHeader_descriptor = descriptor2;
        internal_static_widevine_drm_WidevineCencHeader_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor2, new String[]{"Algorithm", "KeyId", "Provider", "ContentId", "TrackType", "Policy", "KeyPeriod"});
    }
}

package com.google.rpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ErrorInfo extends GeneratedMessageV3 implements ErrorInfoOrBuilder {
    public static final int DOMAIN_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static final int REASON_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object domain_;
    private byte memoizedIsInitialized;
    private MapField<String, String> metadata_;
    private volatile Object reason_;
    private static final ErrorInfo DEFAULT_INSTANCE = new ErrorInfo();
    private static final Parser<ErrorInfo> PARSER = new AbstractParser<ErrorInfo>() { // from class: com.google.rpc.ErrorInfo.1
        @Override // com.google.protobuf.Parser
        public ErrorInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ErrorInfo(codedInputStream, extensionRegistryLite);
        }
    };

    private ErrorInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ErrorInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.reason_ = "";
        this.domain_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ErrorInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ErrorInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.reason_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.domain_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                if (!(z2 & true)) {
                                    this.metadata_ = MapField.newMapField(MetadataDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(MetadataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.metadata_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e3) {
                    throw e3.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 3) {
            return internalGetMetadata();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorInfo.class, Builder.class);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getReason() {
        Object obj = this.reason_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.reason_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getReasonBytes() {
        Object obj = this.reason_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.reason_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getDomain() {
        Object obj = this.domain_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.domain_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getDomainBytes() {
        Object obj = this.domain_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.domain_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    private static final class MetadataDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_MetadataEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private MetadataDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetMetadata() {
        MapField<String, String> mapField = this.metadata_;
        return mapField == null ? MapField.emptyMapField(MetadataDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public int getMetadataCount() {
        return internalGetMetadata().getMap().size();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public boolean containsMetadata(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        return internalGetMetadata().getMap().containsKey(str);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    @Deprecated
    public Map<String, String> getMetadata() {
        return getMetadataMap();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public Map<String, String> getMetadataMap() {
        return internalGetMetadata().getMap();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetMetadata().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetMetadata().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
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

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!GeneratedMessageV3.isStringEmpty(this.reason_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.reason_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.domain_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.domain_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetMetadata(), MetadataDefaultEntryHolder.defaultEntry, 3);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.reason_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.reason_);
        if (!GeneratedMessageV3.isStringEmpty(this.domain_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.domain_);
        }
        for (Map.Entry<String, String> entry : internalGetMetadata().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, MetadataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErrorInfo)) {
            return super.equals(obj);
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        return getReason().equals(errorInfo.getReason()) && getDomain().equals(errorInfo.getDomain()) && internalGetMetadata().equals(errorInfo.internalGetMetadata()) && this.unknownFields.equals(errorInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getReason().hashCode()) * 37) + 2) * 53) + getDomain().hashCode();
        if (!internalGetMetadata().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + internalGetMetadata().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ErrorInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ErrorInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(InputStream inputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ErrorInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ErrorInfo errorInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(errorInfo);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ErrorInfoOrBuilder {
        private int bitField0_;
        private Object domain_;
        private MapField<String, String> metadata_;
        private Object reason_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 3) {
                return internalGetMutableMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorInfo.class, Builder.class);
        }

        private Builder() {
            this.reason_ = "";
            this.domain_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.reason_ = "";
            this.domain_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ErrorInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.reason_ = "";
            this.domain_ = "";
            internalGetMutableMetadata().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ErrorInfo getDefaultInstanceForType() {
            return ErrorInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ErrorInfo build() {
            ErrorInfo errorInfoBuildPartial = buildPartial();
            if (errorInfoBuildPartial.isInitialized()) {
                return errorInfoBuildPartial;
            }
            throw newUninitializedMessageException((Message) errorInfoBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ErrorInfo buildPartial() {
            ErrorInfo errorInfo = new ErrorInfo(this);
            errorInfo.reason_ = this.reason_;
            errorInfo.domain_ = this.domain_;
            errorInfo.metadata_ = internalGetMetadata();
            errorInfo.metadata_.makeImmutable();
            onBuilt();
            return errorInfo;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public Builder mo240clone() {
            return (Builder) super.mo240clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof ErrorInfo) {
                return mergeFrom((ErrorInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ErrorInfo errorInfo) {
            if (errorInfo == ErrorInfo.getDefaultInstance()) {
                return this;
            }
            if (!errorInfo.getReason().isEmpty()) {
                this.reason_ = errorInfo.reason_;
                onChanged();
            }
            if (!errorInfo.getDomain().isEmpty()) {
                this.domain_ = errorInfo.domain_;
                onChanged();
            }
            internalGetMutableMetadata().mergeFrom(errorInfo.internalGetMetadata());
            mergeUnknownFields(errorInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.rpc.ErrorInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.ErrorInfo.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.ErrorInfo r3 = (com.google.rpc.ErrorInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.google.rpc.ErrorInfo r4 = (com.google.rpc.ErrorInfo) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.ErrorInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.ErrorInfo$Builder");
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getReason() {
            Object obj = this.reason_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.reason_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getReasonBytes() {
            Object obj = this.reason_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.reason_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setReason(String str) {
            str.getClass();
            this.reason_ = str;
            onChanged();
            return this;
        }

        public Builder clearReason() {
            this.reason_ = ErrorInfo.getDefaultInstance().getReason();
            onChanged();
            return this;
        }

        public Builder setReasonBytes(ByteString byteString) {
            byteString.getClass();
            ErrorInfo.checkByteStringIsUtf8(byteString);
            this.reason_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getDomain() {
            Object obj = this.domain_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.domain_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getDomainBytes() {
            Object obj = this.domain_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.domain_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDomain(String str) {
            str.getClass();
            this.domain_ = str;
            onChanged();
            return this;
        }

        public Builder clearDomain() {
            this.domain_ = ErrorInfo.getDefaultInstance().getDomain();
            onChanged();
            return this;
        }

        public Builder setDomainBytes(ByteString byteString) {
            byteString.getClass();
            ErrorInfo.checkByteStringIsUtf8(byteString);
            this.domain_ = byteString;
            onChanged();
            return this;
        }

        private MapField<String, String> internalGetMetadata() {
            MapField<String, String> mapField = this.metadata_;
            return mapField == null ? MapField.emptyMapField(MetadataDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableMetadata() {
            onChanged();
            if (this.metadata_ == null) {
                this.metadata_ = MapField.newMapField(MetadataDefaultEntryHolder.defaultEntry);
            }
            if (!this.metadata_.isMutable()) {
                this.metadata_ = this.metadata_.copy();
            }
            return this.metadata_;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public int getMetadataCount() {
            return internalGetMetadata().getMap().size();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public boolean containsMetadata(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            return internalGetMetadata().getMap().containsKey(str);
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        @Deprecated
        public Map<String, String> getMetadata() {
            return getMetadataMap();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public Map<String, String> getMetadataMap() {
            return internalGetMetadata().getMap();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetMetadata().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetMetadata().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearMetadata() {
            internalGetMutableMetadata().getMutableMap().clear();
            return this;
        }

        public Builder removeMetadata(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableMetadata().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableMetadata() {
            return internalGetMutableMetadata().getMutableMap();
        }

        public Builder putMetadata(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            if (str2 == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableMetadata().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllMetadata(Map<String, String> map) {
            internalGetMutableMetadata().getMutableMap().putAll(map);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static ErrorInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ErrorInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ErrorInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ErrorInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

package com.google.api;

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

/* JADX INFO: loaded from: classes2.dex */
public final class MonitoredResource extends GeneratedMessageV3 implements MonitoredResourceOrBuilder {
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private MapField<String, String> labels_;
    private byte memoizedIsInitialized;
    private volatile Object type_;
    private static final MonitoredResource DEFAULT_INSTANCE = new MonitoredResource();
    private static final Parser<MonitoredResource> PARSER = new AbstractParser<MonitoredResource>() { // from class: com.google.api.MonitoredResource.1
        @Override // com.google.protobuf.Parser
        public MonitoredResource parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResource(codedInputStream, extensionRegistryLite);
        }
    };

    private MonitoredResource(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MonitoredResource() {
        this.memoizedIsInitialized = (byte) -1;
        this.type_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new MonitoredResource();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MonitoredResource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.type_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if (!(z2 & true)) {
                                    this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.labels_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                    }
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetLabels();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResource_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResource.class, Builder.class);
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getType() {
        Object obj = this.type_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.type_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public ByteString getTypeBytes() {
        Object obj = this.type_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.type_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.internal_static_google_api_MonitoredResource_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetLabels() {
        MapField<String, String> mapField = this.labels_;
        return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public int getLabelsCount() {
        return internalGetLabels().getMap().size();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public boolean containsLabels(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        return internalGetLabels().getMap().containsKey(str);
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public Map<String, String> getLabelsMap() {
        return internalGetLabels().getMap();
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetLabels().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // com.google.api.MonitoredResourceOrBuilder
    public String getLabelsOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetLabels().getMap();
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
        if (!GeneratedMessageV3.isStringEmpty(this.type_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.type_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.type_);
        for (Map.Entry<String, String> entry : internalGetLabels().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, LabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof MonitoredResource)) {
            return super.equals(obj);
        }
        MonitoredResource monitoredResource = (MonitoredResource) obj;
        return getType().equals(monitoredResource.getType()) && internalGetLabels().equals(monitoredResource.internalGetLabels()) && this.unknownFields.equals(monitoredResource.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode();
        if (!internalGetLabels().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetLabels().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static MonitoredResource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static MonitoredResource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResource monitoredResource) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResource);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoredResourceOrBuilder {
        private int bitField0_;
        private MapField<String, String> labels_;
        private Object type_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResource.class, Builder.class);
        }

        private Builder() {
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MonitoredResource.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.type_ = "";
            internalGetMutableLabels().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MonitoredResource getDefaultInstanceForType() {
            return MonitoredResource.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResource build() {
            MonitoredResource monitoredResourceBuildPartial = buildPartial();
            if (monitoredResourceBuildPartial.isInitialized()) {
                return monitoredResourceBuildPartial;
            }
            throw newUninitializedMessageException((Message) monitoredResourceBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResource buildPartial() {
            MonitoredResource monitoredResource = new MonitoredResource(this);
            monitoredResource.type_ = this.type_;
            monitoredResource.labels_ = internalGetLabels();
            monitoredResource.labels_.makeImmutable();
            onBuilt();
            return monitoredResource;
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
            if (message instanceof MonitoredResource) {
                return mergeFrom((MonitoredResource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResource monitoredResource) {
            if (monitoredResource == MonitoredResource.getDefaultInstance()) {
                return this;
            }
            if (!monitoredResource.getType().isEmpty()) {
                this.type_ = monitoredResource.type_;
                onChanged();
            }
            internalGetMutableLabels().mergeFrom(monitoredResource.internalGetLabels());
            mergeUnknownFields(monitoredResource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.MonitoredResource.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MonitoredResource.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.MonitoredResource r3 = (com.google.api.MonitoredResource) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.MonitoredResource r4 = (com.google.api.MonitoredResource) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MonitoredResource.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MonitoredResource$Builder");
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.type_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setType(String str) {
            str.getClass();
            this.type_ = str;
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = MonitoredResource.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            byteString.getClass();
            MonitoredResource.checkByteStringIsUtf8(byteString);
            this.type_ = byteString;
            onChanged();
            return this;
        }

        private MapField<String, String> internalGetLabels() {
            MapField<String, String> mapField = this.labels_;
            return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableLabels() {
            onChanged();
            if (this.labels_ == null) {
                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.copy();
            }
            return this.labels_;
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public int getLabelsCount() {
            return internalGetLabels().getMap().size();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public boolean containsLabels(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            return internalGetLabels().getMap().containsKey(str);
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public Map<String, String> getLabelsMap() {
            return internalGetLabels().getMap();
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetLabels().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // com.google.api.MonitoredResourceOrBuilder
        public String getLabelsOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetLabels().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearLabels() {
            internalGetMutableLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeLabels(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableLabels().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableLabels() {
            return internalGetMutableLabels().getMutableMap();
        }

        public Builder putLabels(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            if (str2 == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableLabels().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllLabels(Map<String, String> map) {
            internalGetMutableLabels().getMutableMap().putAll(map);
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

    public static MonitoredResource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResource> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<MonitoredResource> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public MonitoredResource getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

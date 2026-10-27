package com.google.cloud.location;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class ListLocationsRequest extends GeneratedMessageV3 implements ListLocationsRequestOrBuilder {
    public static final int FILTER_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PAGE_SIZE_FIELD_NUMBER = 3;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private volatile Object filter_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int pageSize_;
    private volatile Object pageToken_;
    private static final ListLocationsRequest DEFAULT_INSTANCE = new ListLocationsRequest();
    private static final Parser<ListLocationsRequest> PARSER = new AbstractParser<ListLocationsRequest>() { // from class: com.google.cloud.location.ListLocationsRequest.1
        @Override // com.google.protobuf.Parser
        public ListLocationsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListLocationsRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private ListLocationsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListLocationsRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.filter_ = "";
        this.pageToken_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListLocationsRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLocationsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.filter_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 24) {
                                this.pageSize_ = codedInputStream.readInt32();
                            } else if (tag == 34) {
                                this.pageToken_ = codedInputStream.readStringRequireUtf8();
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
        return LocationsProto.internal_static_google_cloud_location_ListLocationsRequest_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LocationsProto.internal_static_google_cloud_location_ListLocationsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLocationsRequest.class, Builder.class);
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public String getFilter() {
        Object obj = this.filter_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.filter_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public ByteString getFilterBytes() {
        Object obj = this.filter_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.filter_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public String getPageToken() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
    public ByteString getPageTokenBytes() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pageToken_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
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
        if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.filter_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.filter_);
        }
        int i = this.pageSize_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.pageToken_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.pageToken_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.name_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (!GeneratedMessageV3.isStringEmpty(this.filter_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.filter_);
        }
        int i2 = this.pageSize_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(3, i2);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.pageToken_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.pageToken_);
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
        if (!(obj instanceof ListLocationsRequest)) {
            return super.equals(obj);
        }
        ListLocationsRequest listLocationsRequest = (ListLocationsRequest) obj;
        return getName().equals(listLocationsRequest.getName()) && getFilter().equals(listLocationsRequest.getFilter()) && getPageSize() == listLocationsRequest.getPageSize() && getPageToken().equals(listLocationsRequest.getPageToken()) && this.unknownFields.equals(listLocationsRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getFilter().hashCode()) * 37) + 3) * 53) + getPageSize()) * 37) + 4) * 53) + getPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    public static ListLocationsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListLocationsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListLocationsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListLocationsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListLocationsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListLocationsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListLocationsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListLocationsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListLocationsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListLocationsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListLocationsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListLocationsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLocationsRequest listLocationsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listLocationsRequest);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListLocationsRequestOrBuilder {
        private Object filter_;
        private Object name_;
        private int pageSize_;
        private Object pageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsRequest_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLocationsRequest.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.filter_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.filter_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ListLocationsRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.filter_ = "";
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsRequest_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListLocationsRequest getDefaultInstanceForType() {
            return ListLocationsRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListLocationsRequest build() {
            ListLocationsRequest listLocationsRequestBuildPartial = buildPartial();
            if (listLocationsRequestBuildPartial.isInitialized()) {
                return listLocationsRequestBuildPartial;
            }
            throw newUninitializedMessageException((Message) listLocationsRequestBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListLocationsRequest buildPartial() {
            ListLocationsRequest listLocationsRequest = new ListLocationsRequest(this);
            listLocationsRequest.name_ = this.name_;
            listLocationsRequest.filter_ = this.filter_;
            listLocationsRequest.pageSize_ = this.pageSize_;
            listLocationsRequest.pageToken_ = this.pageToken_;
            onBuilt();
            return listLocationsRequest;
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
            if (message instanceof ListLocationsRequest) {
                return mergeFrom((ListLocationsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListLocationsRequest listLocationsRequest) {
            if (listLocationsRequest == ListLocationsRequest.getDefaultInstance()) {
                return this;
            }
            if (!listLocationsRequest.getName().isEmpty()) {
                this.name_ = listLocationsRequest.name_;
                onChanged();
            }
            if (!listLocationsRequest.getFilter().isEmpty()) {
                this.filter_ = listLocationsRequest.filter_;
                onChanged();
            }
            if (listLocationsRequest.getPageSize() != 0) {
                setPageSize(listLocationsRequest.getPageSize());
            }
            if (!listLocationsRequest.getPageToken().isEmpty()) {
                this.pageToken_ = listLocationsRequest.pageToken_;
                onChanged();
            }
            mergeUnknownFields(listLocationsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.cloud.location.ListLocationsRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.location.ListLocationsRequest.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.location.ListLocationsRequest r3 = (com.google.cloud.location.ListLocationsRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.location.ListLocationsRequest r4 = (com.google.cloud.location.ListLocationsRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.location.ListLocationsRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.location.ListLocationsRequest$Builder");
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = ListLocationsRequest.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            ListLocationsRequest.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public String getFilter() {
            Object obj = this.filter_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.filter_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public ByteString getFilterBytes() {
            Object obj = this.filter_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.filter_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setFilter(String str) {
            str.getClass();
            this.filter_ = str;
            onChanged();
            return this;
        }

        public Builder clearFilter() {
            this.filter_ = ListLocationsRequest.getDefaultInstance().getFilter();
            onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString byteString) {
            byteString.getClass();
            ListLocationsRequest.checkByteStringIsUtf8(byteString);
            this.filter_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public int getPageSize() {
            return this.pageSize_;
        }

        public Builder setPageSize(int i) {
            this.pageSize_ = i;
            onChanged();
            return this;
        }

        public Builder clearPageSize() {
            this.pageSize_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public String getPageToken() {
            Object obj = this.pageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.ListLocationsRequestOrBuilder
        public ByteString getPageTokenBytes() {
            Object obj = this.pageToken_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pageToken_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPageToken(String str) {
            str.getClass();
            this.pageToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearPageToken() {
            this.pageToken_ = ListLocationsRequest.getDefaultInstance().getPageToken();
            onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            byteString.getClass();
            ListLocationsRequest.checkByteStringIsUtf8(byteString);
            this.pageToken_ = byteString;
            onChanged();
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

    public static ListLocationsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLocationsRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListLocationsRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListLocationsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

package com.google.cloud.location;

import com.google.cloud.location.Location;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ListLocationsResponse extends GeneratedMessageV3 implements ListLocationsResponseOrBuilder {
    public static final int LOCATIONS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<Location> locations_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final ListLocationsResponse DEFAULT_INSTANCE = new ListLocationsResponse();
    private static final Parser<ListLocationsResponse> PARSER = new AbstractParser<ListLocationsResponse>() { // from class: com.google.cloud.location.ListLocationsResponse.1
        @Override // com.google.protobuf.Parser
        public ListLocationsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListLocationsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListLocationsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListLocationsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.locations_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListLocationsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListLocationsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.locations_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.locations_.add((Location) codedInputStream.readMessage(Location.parser(), extensionRegistryLite));
                            } else if (tag == 18) {
                                this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
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
                if (z2 & true) {
                    this.locations_ = Collections.unmodifiableList(this.locations_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LocationsProto.internal_static_google_cloud_location_ListLocationsResponse_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LocationsProto.internal_static_google_cloud_location_ListLocationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLocationsResponse.class, Builder.class);
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public List<Location> getLocationsList() {
        return this.locations_;
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public List<? extends LocationOrBuilder> getLocationsOrBuilderList() {
        return this.locations_;
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public int getLocationsCount() {
        return this.locations_.size();
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public Location getLocations(int i) {
        return this.locations_.get(i);
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public LocationOrBuilder getLocationsOrBuilder(int i) {
        return this.locations_.get(i);
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nextPageToken_ = byteStringCopyFromUtf8;
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
        for (int i = 0; i < this.locations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.locations_.get(i));
        }
        if (!GeneratedMessageV3.isStringEmpty(this.nextPageToken_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.nextPageToken_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = 0;
        for (int i2 = 0; i2 < this.locations_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(1, this.locations_.get(i2));
        }
        if (!GeneratedMessageV3.isStringEmpty(this.nextPageToken_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
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
        if (!(obj instanceof ListLocationsResponse)) {
            return super.equals(obj);
        }
        ListLocationsResponse listLocationsResponse = (ListLocationsResponse) obj;
        return getLocationsList().equals(listLocationsResponse.getLocationsList()) && getNextPageToken().equals(listLocationsResponse.getNextPageToken()) && this.unknownFields.equals(listLocationsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getLocationsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getLocationsList().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static ListLocationsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListLocationsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListLocationsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListLocationsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListLocationsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListLocationsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListLocationsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListLocationsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListLocationsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListLocationsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListLocationsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListLocationsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListLocationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListLocationsResponse listLocationsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listLocationsResponse);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListLocationsResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> locationsBuilder_;
        private List<Location> locations_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsResponse_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListLocationsResponse.class, Builder.class);
        }

        private Builder() {
            this.locations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.locations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListLocationsResponse.alwaysUseFieldBuilders) {
                getLocationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.locations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LocationsProto.internal_static_google_cloud_location_ListLocationsResponse_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListLocationsResponse getDefaultInstanceForType() {
            return ListLocationsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListLocationsResponse build() {
            ListLocationsResponse listLocationsResponseBuildPartial = buildPartial();
            if (listLocationsResponseBuildPartial.isInitialized()) {
                return listLocationsResponseBuildPartial;
            }
            throw newUninitializedMessageException((Message) listLocationsResponseBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListLocationsResponse buildPartial() {
            ListLocationsResponse listLocationsResponse = new ListLocationsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.locations_ = Collections.unmodifiableList(this.locations_);
                    this.bitField0_ &= -2;
                }
                listLocationsResponse.locations_ = this.locations_;
            } else {
                listLocationsResponse.locations_ = repeatedFieldBuilderV3.build();
            }
            listLocationsResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listLocationsResponse;
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
            if (message instanceof ListLocationsResponse) {
                return mergeFrom((ListLocationsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListLocationsResponse listLocationsResponse) {
            if (listLocationsResponse == ListLocationsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.locationsBuilder_ == null) {
                if (!listLocationsResponse.locations_.isEmpty()) {
                    if (this.locations_.isEmpty()) {
                        this.locations_ = listLocationsResponse.locations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLocationsIsMutable();
                        this.locations_.addAll(listLocationsResponse.locations_);
                    }
                    onChanged();
                }
            } else if (!listLocationsResponse.locations_.isEmpty()) {
                if (!this.locationsBuilder_.isEmpty()) {
                    this.locationsBuilder_.addAllMessages(listLocationsResponse.locations_);
                } else {
                    this.locationsBuilder_.dispose();
                    this.locationsBuilder_ = null;
                    this.locations_ = listLocationsResponse.locations_;
                    this.bitField0_ &= -2;
                    this.locationsBuilder_ = ListLocationsResponse.alwaysUseFieldBuilders ? getLocationsFieldBuilder() : null;
                }
            }
            if (!listLocationsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listLocationsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listLocationsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.cloud.location.ListLocationsResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.location.ListLocationsResponse.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.location.ListLocationsResponse r3 = (com.google.cloud.location.ListLocationsResponse) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.location.ListLocationsResponse r4 = (com.google.cloud.location.ListLocationsResponse) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.location.ListLocationsResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.location.ListLocationsResponse$Builder");
        }

        private void ensureLocationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.locations_ = new ArrayList(this.locations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public List<Location> getLocationsList() {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.locations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public int getLocationsCount() {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.locations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public Location getLocations(int i) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.locations_.get(i);
            }
            return (Location) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLocations(int i, Location location) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                location.getClass();
                ensureLocationsIsMutable();
                this.locations_.set(i, location);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, location);
            }
            return this;
        }

        public Builder setLocations(int i, Location.Builder builder) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocationsIsMutable();
                this.locations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLocations(Location location) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                location.getClass();
                ensureLocationsIsMutable();
                this.locations_.add(location);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(location);
            }
            return this;
        }

        public Builder addLocations(int i, Location location) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                location.getClass();
                ensureLocationsIsMutable();
                this.locations_.add(i, location);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, location);
            }
            return this;
        }

        public Builder addLocations(Location.Builder builder) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocationsIsMutable();
                this.locations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLocations(int i, Location.Builder builder) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocationsIsMutable();
                this.locations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLocations(Iterable<? extends Location> iterable) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.locations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLocations() {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.locations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLocations(int i) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocationsIsMutable();
                this.locations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Location.Builder getLocationsBuilder(int i) {
            return (Location.Builder) getLocationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public LocationOrBuilder getLocationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.locations_.get(i);
            }
            return (LocationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public List<? extends LocationOrBuilder> getLocationsOrBuilderList() {
            RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.locations_);
        }

        public Location.Builder addLocationsBuilder() {
            return (Location.Builder) getLocationsFieldBuilder().addBuilder(Location.getDefaultInstance());
        }

        public Location.Builder addLocationsBuilder(int i) {
            return (Location.Builder) getLocationsFieldBuilder().addBuilder(i, Location.getDefaultInstance());
        }

        public List<Location.Builder> getLocationsBuilderList() {
            return getLocationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> getLocationsFieldBuilder() {
            if (this.locationsBuilder_ == null) {
                this.locationsBuilder_ = new RepeatedFieldBuilderV3<>(this.locations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.locations_ = null;
            }
            return this.locationsBuilder_;
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.ListLocationsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            Object obj = this.nextPageToken_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nextPageToken_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNextPageToken(String str) {
            str.getClass();
            this.nextPageToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListLocationsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            byteString.getClass();
            ListLocationsResponse.checkByteStringIsUtf8(byteString);
            this.nextPageToken_ = byteString;
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

    public static ListLocationsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListLocationsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListLocationsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListLocationsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

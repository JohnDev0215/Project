package com.google.cloud.location;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class Location extends GeneratedMessageV3 implements LocationOrBuilder {
    public static final int DISPLAY_NAME_FIELD_NUMBER = 5;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int LOCATION_ID_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object displayName_;
    private MapField<String, String> labels_;
    private volatile Object locationId_;
    private byte memoizedIsInitialized;
    private Any metadata_;
    private volatile Object name_;
    private static final Location DEFAULT_INSTANCE = new Location();
    private static final Parser<Location> PARSER = new AbstractParser<Location>() { // from class: com.google.cloud.location.Location.1
        @Override // com.google.protobuf.Parser
        public Location parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Location(codedInputStream, extensionRegistryLite);
        }
    };

    private Location(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Location() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.locationId_ = "";
        this.displayName_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Location();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Location(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if (!(z2 & true)) {
                                    this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.labels_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            } else if (tag == 26) {
                                Any any = this.metadata_;
                                Any.Builder builder = any != null ? any.toBuilder() : null;
                                Any any2 = (Any) codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.metadata_ = any2;
                                if (builder != null) {
                                    builder.mergeFrom(any2);
                                    this.metadata_ = builder.buildPartial();
                                }
                            } else if (tag == 34) {
                                this.locationId_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 42) {
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
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
        return LocationsProto.internal_static_google_cloud_location_Location_descriptor;
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
        return LocationsProto.internal_static_google_cloud_location_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public String getLocationId() {
        Object obj = this.locationId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.locationId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public ByteString getLocationIdBytes() {
        Object obj = this.locationId_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.locationId_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(LocationsProto.internal_static_google_cloud_location_Location_LabelsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetLabels() {
        MapField<String, String> mapField = this.labels_;
        return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public int getLabelsCount() {
        return internalGetLabels().getMap().size();
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public boolean containsLabels(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        return internalGetLabels().getMap().containsKey(str);
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public Map<String, String> getLabelsMap() {
        return internalGetLabels().getMap();
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetLabels().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
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

    @Override // com.google.cloud.location.LocationOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // com.google.cloud.location.LocationOrBuilder
    public AnyOrBuilder getMetadataOrBuilder() {
        return getMetadata();
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
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 2);
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(3, getMetadata());
        }
        if (!GeneratedMessageV3.isStringEmpty(this.locationId_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.locationId_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.displayName_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.displayName_);
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
        for (Map.Entry<String, String> entry : internalGetLabels().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, LabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        if (this.metadata_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getMetadata());
        }
        if (!GeneratedMessageV3.isStringEmpty(this.locationId_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.locationId_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.displayName_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.displayName_);
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
        if (!(obj instanceof Location)) {
            return super.equals(obj);
        }
        Location location = (Location) obj;
        if (getName().equals(location.getName()) && getLocationId().equals(location.getLocationId()) && getDisplayName().equals(location.getDisplayName()) && internalGetLabels().equals(location.internalGetLabels()) && hasMetadata() == location.hasMetadata()) {
            return (!hasMetadata() || getMetadata().equals(location.getMetadata())) && this.unknownFields.equals(location.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 4) * 53) + getLocationId().hashCode()) * 37) + 5) * 53) + getDisplayName().hashCode();
        if (!internalGetLabels().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetLabels().hashCode();
        }
        if (hasMetadata()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getMetadata().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static Location parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Location parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Location parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Location parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Location parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Location parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Location parseFrom(InputStream inputStream) throws IOException {
        return (Location) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Location parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Location) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Location parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Location) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Location parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Location) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Location parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Location) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Location parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Location) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Location location) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(location);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LocationOrBuilder {
        private int bitField0_;
        private Object displayName_;
        private MapField<String, String> labels_;
        private Object locationId_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> metadataBuilder_;
        private Any metadata_;
        private Object name_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LocationsProto.internal_static_google_cloud_location_Location_descriptor;
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
            return LocationsProto.internal_static_google_cloud_location_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.locationId_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.locationId_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Location.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.locationId_ = "";
            this.displayName_ = "";
            internalGetMutableLabels().clear();
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LocationsProto.internal_static_google_cloud_location_Location_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Location getDefaultInstanceForType() {
            return Location.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Location build() {
            Location locationBuildPartial = buildPartial();
            if (locationBuildPartial.isInitialized()) {
                return locationBuildPartial;
            }
            throw newUninitializedMessageException((Message) locationBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Location buildPartial() {
            Location location = new Location(this);
            location.name_ = this.name_;
            location.locationId_ = this.locationId_;
            location.displayName_ = this.displayName_;
            location.labels_ = internalGetLabels();
            location.labels_.makeImmutable();
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                location.metadata_ = this.metadata_;
            } else {
                location.metadata_ = (Any) singleFieldBuilderV3.build();
            }
            onBuilt();
            return location;
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
            if (message instanceof Location) {
                return mergeFrom((Location) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Location location) {
            if (location == Location.getDefaultInstance()) {
                return this;
            }
            if (!location.getName().isEmpty()) {
                this.name_ = location.name_;
                onChanged();
            }
            if (!location.getLocationId().isEmpty()) {
                this.locationId_ = location.locationId_;
                onChanged();
            }
            if (!location.getDisplayName().isEmpty()) {
                this.displayName_ = location.displayName_;
                onChanged();
            }
            internalGetMutableLabels().mergeFrom(location.internalGetLabels());
            if (location.hasMetadata()) {
                mergeMetadata(location.getMetadata());
            }
            mergeUnknownFields(location.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.cloud.location.Location.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.location.Location.access$1100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.location.Location r3 = (com.google.cloud.location.Location) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.location.Location r4 = (com.google.cloud.location.Location) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.location.Location.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.location.Location$Builder");
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
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
            this.name_ = Location.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            Location.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public String getLocationId() {
            Object obj = this.locationId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.locationId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public ByteString getLocationIdBytes() {
            Object obj = this.locationId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.locationId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocationId(String str) {
            str.getClass();
            this.locationId_ = str;
            onChanged();
            return this;
        }

        public Builder clearLocationId() {
            this.locationId_ = Location.getDefaultInstance().getLocationId();
            onChanged();
            return this;
        }

        public Builder setLocationIdBytes(ByteString byteString) {
            byteString.getClass();
            Location.checkByteStringIsUtf8(byteString);
            this.locationId_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.displayName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDisplayName(String str) {
            str.getClass();
            this.displayName_ = str;
            onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = Location.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            byteString.getClass();
            Location.checkByteStringIsUtf8(byteString);
            this.displayName_ = byteString;
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

        @Override // com.google.cloud.location.LocationOrBuilder
        public int getLabelsCount() {
            return internalGetLabels().getMap().size();
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public boolean containsLabels(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            return internalGetLabels().getMap().containsKey(str);
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public Map<String, String> getLabelsMap() {
            return internalGetLabels().getMap();
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetLabels().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
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

        @Override // com.google.cloud.location.LocationOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public Any getMetadata() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any = this.metadata_;
                return any == null ? Any.getDefaultInstance() : any;
            }
            return (Any) singleFieldBuilderV3.getMessage();
        }

        public Builder setMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                any.getClass();
                this.metadata_ = any;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(any);
            }
            return this;
        }

        public Builder setMetadata(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.metadata_;
                if (any2 != null) {
                    this.metadata_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.metadata_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getMetadataBuilder() {
            onChanged();
            return (Any.Builder) getMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.location.LocationOrBuilder
        public AnyOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AnyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.metadata_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
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

    public static Location getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Location> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Location> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Location getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

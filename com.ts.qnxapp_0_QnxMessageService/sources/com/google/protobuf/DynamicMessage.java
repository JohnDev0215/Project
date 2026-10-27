package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Message;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class DynamicMessage extends AbstractMessage {
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private int memoizedSize = -1;
    private final Descriptors.FieldDescriptor[] oneofCases;
    private final Descriptors.Descriptor type;
    private final UnknownFieldSet unknownFields;

    DynamicMessage(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields, Descriptors.FieldDescriptor[] oneofCases, UnknownFieldSet unknownFields) {
        this.type = type;
        this.fields = fields;
        this.oneofCases = oneofCases;
        this.unknownFields = unknownFields;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor type) {
        int oneofDeclCount = type.toProto().getOneofDeclCount();
        Descriptors.FieldDescriptor[] oneofCases = new Descriptors.FieldDescriptor[oneofDeclCount];
        return new DynamicMessage(type, FieldSet.emptySet(), oneofCases, UnknownFieldSet.getDefaultInstance());
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input) throws IOException {
        return newBuilder(type).mergeFrom(input).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input, ExtensionRegistry extensionRegistry) throws IOException {
        return newBuilder(type).mergeFrom(input, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data) throws InvalidProtocolBufferException {
        return newBuilder(type).mergeFrom(data).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return newBuilder(type).mergeFrom(data, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data) throws InvalidProtocolBufferException {
        return newBuilder(type).mergeFrom(data).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return newBuilder(type).mergeFrom(data, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input) throws IOException {
        return newBuilder(type).mergeFrom(input).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input, ExtensionRegistry extensionRegistry) throws IOException {
        return newBuilder(type).mergeFrom(input, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static Builder newBuilder(Descriptors.Descriptor type) {
        return new Builder(type);
    }

    public static Builder newBuilder(Message prototype) {
        return new Builder(prototype.getDescriptorForType()).mergeFrom(prototype);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public DynamicMessage getDefaultInstanceForType() {
        return getDefaultInstance(this.type);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.getAllFields();
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        verifyOneofContainingType(oneof);
        Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
        if (field == null) {
            return false;
        }
        return true;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        verifyOneofContainingType(oneof);
        return this.oneofCases[oneof.getIndex()];
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor field) {
        verifyContainingType(field);
        return this.fields.hasField(field);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor field) {
        verifyContainingType(field);
        Object result = this.fields.getField(field);
        if (result == null) {
            if (field.isRepeated()) {
                return Collections.emptyList();
            }
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return getDefaultInstance(field.getMessageType());
            }
            return field.getDefaultValue();
        }
        return result;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
        verifyContainingType(field);
        return this.fields.getRepeatedFieldCount(field);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
        verifyContainingType(field);
        return this.fields.getRepeatedField(field, index);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    static boolean isInitialized(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields) {
        for (Descriptors.FieldDescriptor field : type.getFields()) {
            if (field.isRequired() && !fields.hasField(field)) {
                return false;
            }
        }
        return fields.isInitialized();
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return isInitialized(this.type, this.fields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(output);
            this.unknownFields.writeAsMessageSetTo(output);
        } else {
            this.fields.writeTo(output);
            this.unknownFields.writeTo(output);
        }
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size;
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            size = this.fields.getMessageSetSerializedSize() + this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            size = this.fields.getSerializedSize() + this.unknownFields.getSerializedSize();
        }
        this.memoizedSize = size;
        return size;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilderForType().mergeFrom((Message) this);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<DynamicMessage> getParserForType() {
        return new AbstractParser<DynamicMessage>() { // from class: com.google.protobuf.DynamicMessage.1
            @Override // com.google.protobuf.Parser
            public DynamicMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builder = DynamicMessage.newBuilder(DynamicMessage.this.type);
                try {
                    builder.mergeFrom(input, extensionRegistry);
                    return builder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builder.buildPartial());
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(builder.buildPartial());
                }
            }
        };
    }

    private void verifyContainingType(Descriptors.FieldDescriptor field) {
        if (field.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
        if (oneof.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    public static final class Builder extends AbstractMessage.Builder<Builder> {
        private FieldSet.Builder<Descriptors.FieldDescriptor> fields;
        private final Descriptors.FieldDescriptor[] oneofCases;
        private final Descriptors.Descriptor type;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptors.Descriptor type) {
            this.type = type;
            this.fields = FieldSet.newBuilder();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new Descriptors.FieldDescriptor[type.toProto().getOneofDeclCount()];
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            this.fields = FieldSet.newBuilder();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message other) {
            if (other instanceof DynamicMessage) {
                DynamicMessage otherDynamicMessage = (DynamicMessage) other;
                if (otherDynamicMessage.type == this.type) {
                    this.fields.mergeFrom(otherDynamicMessage.fields);
                    mergeUnknownFields(otherDynamicMessage.unknownFields);
                    int i = 0;
                    while (true) {
                        Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                        if (i < fieldDescriptorArr.length) {
                            if (fieldDescriptorArr[i] == null) {
                                fieldDescriptorArr[i] = otherDynamicMessage.oneofCases[i];
                            } else if (otherDynamicMessage.oneofCases[i] != null && this.oneofCases[i] != otherDynamicMessage.oneofCases[i]) {
                                this.fields.clearField(this.oneofCases[i]);
                                this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
                            }
                            i++;
                        } else {
                            return this;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
                }
            } else {
                return (Builder) super.mergeFrom(other);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage build() {
            if (!isInitialized()) {
                Descriptors.Descriptor descriptor = this.type;
                FieldSet<T> fieldSetBuild = this.fields.build();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                throw newUninitializedMessageException((Message) new DynamicMessage(descriptor, fieldSetBuild, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields));
            }
            return buildPartial();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DynamicMessage buildParsed() throws InvalidProtocolBufferException {
            if (!isInitialized()) {
                Descriptors.Descriptor descriptor = this.type;
                FieldSet<T> fieldSetBuild = this.fields.build();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                throw newUninitializedMessageException((Message) new DynamicMessage(descriptor, fieldSetBuild, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields)).asInvalidProtocolBufferException();
            }
            return buildPartial();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage buildPartial() {
            if (this.type.getOptions().getMapEntry()) {
                for (Descriptors.FieldDescriptor field : this.type.getFields()) {
                    if (field.isOptional() && !this.fields.hasField(field)) {
                        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            this.fields.setField(field, DynamicMessage.getDefaultInstance(field.getMessageType()));
                        } else {
                            this.fields.setField(field, field.getDefaultValue());
                        }
                    }
                }
            }
            Descriptors.Descriptor descriptor = this.type;
            FieldSet<T> fieldSetBuild = this.fields.build();
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            DynamicMessage result = new DynamicMessage(descriptor, fieldSetBuild, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields);
            return result;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public Builder mo0clone() {
            Builder result = new Builder(this.type);
            result.fields.mergeFrom(this.fields.build());
            result.mergeUnknownFields(this.unknownFields);
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            System.arraycopy(fieldDescriptorArr, 0, result.oneofCases, 0, fieldDescriptorArr.length);
            return result;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            for (Descriptors.FieldDescriptor field : this.type.getFields()) {
                if (field.isRequired() && !this.fields.hasField(field)) {
                    return false;
                }
            }
            return this.fields.isInitialized();
        }

        @Override // com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.getAllFields();
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder newBuilderForField(Descriptors.FieldDescriptor field) {
            verifyContainingType(field);
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
            }
            return new Builder(field.getMessageType());
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
            verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
            if (field == null) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
            verifyOneofContainingType(oneof);
            return this.oneofCases[oneof.getIndex()];
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
            if (field != null) {
                clearField(field);
            }
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor field) {
            verifyContainingType(field);
            return this.fields.hasField(field);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor field) {
            verifyContainingType(field);
            Object result = this.fields.getField(field);
            if (result == null) {
                if (field.isRepeated()) {
                    return Collections.emptyList();
                }
                if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return DynamicMessage.getDefaultInstance(field.getMessageType());
                }
                return field.getDefaultValue();
            }
            return result;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor field, Object value) {
            verifyContainingType(field);
            verifyType(field, value);
            Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
            if (oneofDescriptor != null) {
                int index = oneofDescriptor.getIndex();
                Descriptors.FieldDescriptor oldField = this.oneofCases[index];
                if (oldField != null && oldField != field) {
                    this.fields.clearField(oldField);
                }
                this.oneofCases[index] = field;
            } else if (field.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 && !field.isRepeated() && field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE && value.equals(field.getDefaultValue())) {
                this.fields.clearField(field);
                return this;
            }
            this.fields.setField(field, value);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor field) {
            verifyContainingType(field);
            Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
            if (oneofDescriptor != null) {
                int index = oneofDescriptor.getIndex();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                if (fieldDescriptorArr[index] == field) {
                    fieldDescriptorArr[index] = null;
                }
            }
            this.fields.clearField(field);
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
            verifyContainingType(field);
            return this.fields.getRepeatedFieldCount(field);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
            verifyContainingType(field);
            return this.fields.getRepeatedField(field, index);
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            verifyContainingType(field);
            verifySingularValueType(field, value);
            this.fields.setRepeatedField(field, index, value);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            verifyContainingType(field);
            verifySingularValueType(field, value);
            this.fields.addRepeatedField(field, value);
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = unknownFields;
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
            return this;
        }

        private void verifyContainingType(Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
            if (oneof.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        private void verifySingularValueType(Descriptors.FieldDescriptor field, Object value) {
            int i = AnonymousClass2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[field.getType().ordinal()];
            if (i == 1) {
                Internal.checkNotNull(value);
                if (!(value instanceof Descriptors.EnumValueDescriptor)) {
                    throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                }
            } else if (i == 2 && (value instanceof Message.Builder)) {
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(field.getNumber()), field.getLiteType().getJavaType(), value.getClass().getName()));
            }
        }

        private void verifyType(Descriptors.FieldDescriptor field, Object value) {
            if (field.isRepeated()) {
                for (Object item : (List) value) {
                    verifySingularValueType(field, item);
                }
                return;
            }
            verifySingularValueType(field, value);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
            Message.Builder builder;
            verifyContainingType(field);
            if (field.isMapField()) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
            Object existingValue = this.fields.getFieldAllowBuilders(field);
            if (existingValue == null) {
                builder = new Builder(field.getMessageType());
            } else {
                builder = toMessageBuilder(existingValue);
            }
            this.fields.setField(field, builder);
            return builder;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index) {
            verifyContainingType(field);
            if (field.isMapField()) {
                throw new UnsupportedOperationException("Map fields cannot be repeated");
            }
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            }
            Message.Builder builder = toMessageBuilder(this.fields.getRepeatedFieldAllowBuilders(field, index));
            this.fields.setRepeatedField(field, index, builder);
            return builder;
        }

        private static Message.Builder toMessageBuilder(Object o) {
            if (o instanceof Message.Builder) {
                return (Message.Builder) o;
            }
            if (o instanceof LazyField) {
                o = ((LazyField) o).getValue();
            }
            if (o instanceof Message) {
                return ((Message) o).toBuilder();
            }
            throw new IllegalArgumentException(String.format("Cannot convert %s to Message.Builder", o.getClass()));
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.DynamicMessage$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}

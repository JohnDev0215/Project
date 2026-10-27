package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    protected int memoizedSize = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public interface BuilderParent {
        void markDirty();
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return MessageReflection.isInitialized(this);
    }

    protected Message.Builder newBuilderForType(BuilderParent parent) {
        throw new UnsupportedOperationException("Nested builder is not supported for this type.");
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public List<String> findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    @Override // com.google.protobuf.Message
    public final String toString() {
        return TextFormat.printer().printToString(this);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        MessageReflection.writeMessageTo(this, getAllFields(), output, false);
    }

    @Override // com.google.protobuf.AbstractMessageLite
    int getMemoizedSerializedSize() {
        return this.memoizedSize;
    }

    @Override // com.google.protobuf.AbstractMessageLite
    void setMemoizedSerializedSize(int size) {
        this.memoizedSize = size;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int serializedSize = MessageReflection.getSerializedSize(this, getAllFields());
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.Message
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        }
        Message otherMessage = (Message) other;
        if (getDescriptorForType() != otherMessage.getDescriptorForType()) {
            return false;
        }
        return compareFields(getAllFields(), otherMessage.getAllFields()) && getUnknownFields().equals(otherMessage.getUnknownFields());
    }

    @Override // com.google.protobuf.Message
    public int hashCode() {
        int hash = this.memoizedHashCode;
        if (hash == 0) {
            int hash2 = (hashFields((41 * 19) + getDescriptorForType().hashCode(), getAllFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }
        return hash;
    }

    private static ByteString toByteString(Object value) {
        if (value instanceof byte[]) {
            return ByteString.copyFrom((byte[]) value);
        }
        return (ByteString) value;
    }

    private static boolean compareBytes(Object a, Object b) {
        if ((a instanceof byte[]) && (b instanceof byte[])) {
            return Arrays.equals((byte[]) a, (byte[]) b);
        }
        return toByteString(a).equals(toByteString(b));
    }

    private static Map convertMapEntryListToMap(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map result = new HashMap();
        Iterator iterator = list.iterator();
        Message entry = (Message) iterator.next();
        Descriptors.Descriptor descriptor = entry.getDescriptorForType();
        Descriptors.FieldDescriptor key = descriptor.findFieldByName("key");
        Descriptors.FieldDescriptor value = descriptor.findFieldByName("value");
        Object fieldValue = entry.getField(value);
        if (fieldValue instanceof Descriptors.EnumValueDescriptor) {
            fieldValue = Integer.valueOf(((Descriptors.EnumValueDescriptor) fieldValue).getNumber());
        }
        result.put(entry.getField(key), fieldValue);
        while (iterator.hasNext()) {
            Message entry2 = (Message) iterator.next();
            Object fieldValue2 = entry2.getField(value);
            if (fieldValue2 instanceof Descriptors.EnumValueDescriptor) {
                fieldValue2 = Integer.valueOf(((Descriptors.EnumValueDescriptor) fieldValue2).getNumber());
            }
            result.put(entry2.getField(key), fieldValue2);
        }
        return result;
    }

    private static boolean compareMapField(Object a, Object b) {
        Map ma = convertMapEntryListToMap((List) a);
        Map mb = convertMapEntryListToMap((List) b);
        return MapFieldLite.equals(ma, mb);
    }

    static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> a, Map<Descriptors.FieldDescriptor, Object> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (Descriptors.FieldDescriptor descriptor : a.keySet()) {
            if (!b.containsKey(descriptor)) {
                return false;
            }
            Object value1 = a.get(descriptor);
            Object value2 = b.get(descriptor);
            if (descriptor.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
                if (descriptor.isRepeated()) {
                    List<?> list1 = (List) value1;
                    List<?> list2 = (List) value2;
                    if (list1.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list1.size(); i++) {
                        if (!compareBytes(list1.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                } else if (!compareBytes(value1, value2)) {
                    return false;
                }
            } else if (descriptor.isMapField()) {
                if (!compareMapField(value1, value2)) {
                    return false;
                }
            } else if (!value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    private static int hashMapField(Object value) {
        return MapFieldLite.calculateHashCodeForMap(convertMapEntryListToMap((List) value));
    }

    protected static int hashFields(int hash, Map<Descriptors.FieldDescriptor, Object> map) {
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
            Descriptors.FieldDescriptor field = entry.getKey();
            Object value = entry.getValue();
            int hash2 = (hash * 37) + field.getNumber();
            if (field.isMapField()) {
                hash = (hash2 * 53) + hashMapField(value);
            } else if (field.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
                hash = (hash2 * 53) + value.hashCode();
            } else if (field.isRepeated()) {
                List<? extends Internal.EnumLite> list = (List) value;
                hash = (hash2 * 53) + Internal.hashEnumList(list);
            } else {
                hash = (hash2 * 53) + Internal.hashEnum((Internal.EnumLite) value);
            }
        }
        return hash;
    }

    @Override // com.google.protobuf.AbstractMessageLite
    UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException((Message) this);
    }

    public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessageLite.Builder implements Message.Builder {
        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public BuilderType mo0clone() {
            throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType clearOneof(Descriptors.OneofDescriptor oneof) {
            throw new UnsupportedOperationException("clearOneof() is not implemented.");
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType clear() {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
                clearField(entry.getKey());
            }
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public List<String> findInitializationErrors() {
            return MessageReflection.findMissingFields(this);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public String getInitializationErrorString() {
            return MessageReflection.delimitWithCommas(findInitializationErrors());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public BuilderType internalMergeFrom(AbstractMessageLite abstractMessageLite) {
            return (BuilderType) mergeFrom((Message) abstractMessageLite);
        }

        public BuilderType mergeFrom(Message message) {
            return (BuilderType) mergeFrom(message, message.getAllFields());
        }

        BuilderType mergeFrom(Message other, Map<Descriptors.FieldDescriptor, Object> allFields) {
            if (other.getDescriptorForType() != getDescriptorForType()) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet()) {
                Descriptors.FieldDescriptor field = entry.getKey();
                if (field.isRepeated()) {
                    for (Object element : (List) entry.getValue()) {
                        addRepeatedField(field, element);
                    }
                } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    Message existingValue = (Message) getField(field);
                    if (existingValue == existingValue.getDefaultInstanceForType()) {
                        setField(field, entry.getValue());
                    } else {
                        setField(field, existingValue.newBuilderForType().mergeFrom(existingValue).mergeFrom((Message) entry.getValue()).build());
                    }
                } else {
                    setField(field, entry.getValue());
                }
            }
            mergeUnknownFields(other.getUnknownFields());
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) throws IOException {
            return (BuilderType) mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            int tag;
            MessageReflection.BuilderAdapter builderAdapter;
            boolean discardUnknown = input.shouldDiscardUnknownFields();
            UnknownFieldSet.Builder unknownFields = discardUnknown ? null : UnknownFieldSet.newBuilder(getUnknownFields());
            do {
                tag = input.readTag();
                if (tag == 0) {
                    break;
                }
                builderAdapter = new MessageReflection.BuilderAdapter(this);
            } while (MessageReflection.mergeFieldFrom(input, unknownFields, extensionRegistry, getDescriptorForType(), builderAdapter, tag));
            if (unknownFields != null) {
                setUnknownFields(unknownFields.build());
            }
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFields) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFields).build());
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on an unsupported message type.");
        }

        public String toString() {
            return TextFormat.printer().printToString(this);
        }

        protected static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(MessageReflection.findMissingFields(message));
        }

        void markClean() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        void dispose() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data, off, len);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data, off, len, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream input) throws IOException {
            return (BuilderType) super.mergeFrom(input);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BuilderType) super.mergeFrom(input, extensionRegistry);
        }
    }

    @Deprecated
    protected static int hashLong(long n) {
        return (int) ((n >>> 32) ^ n);
    }

    @Deprecated
    protected static int hashBoolean(boolean b) {
        return b ? 1231 : 1237;
    }

    @Deprecated
    protected static int hashEnum(Internal.EnumLite e) {
        return e.getNumber();
    }

    @Deprecated
    protected static int hashEnumList(List<? extends Internal.EnumLite> list) {
        int hash = 1;
        for (Internal.EnumLite e : list) {
            hash = (hash * 31) + hashEnum(e);
        }
        return hash;
    }
}

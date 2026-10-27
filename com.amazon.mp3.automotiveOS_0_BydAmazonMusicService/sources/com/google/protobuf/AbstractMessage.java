package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
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

    public final String toString() {
        return TextFormat.printer().printToString(this);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(final CodedOutputStream output) throws IOException {
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

    @Override // com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int serializedSize = MessageReflection.getSerializedSize(this, getAllFields());
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        }
        Message message = (Message) other;
        if (getDescriptorForType() != message.getDescriptorForType()) {
            return false;
        }
        return compareFields(getAllFields(), message.getAllFields()) && getUnknownFields().equals(message.getUnknownFields());
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int iHashFields = (hashFields(779 + getDescriptorForType().hashCode(), getAllFields()) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = iHashFields;
        return iHashFields;
    }

    private static ByteString toByteString(Object value) {
        if (value instanceof byte[]) {
            return ByteString.copyFrom((byte[]) value);
        }
        return (ByteString) value;
    }

    private static boolean compareBytes(Object a2, Object b) {
        if ((a2 instanceof byte[]) && (b instanceof byte[])) {
            return Arrays.equals((byte[]) a2, (byte[]) b);
        }
        return toByteString(a2).equals(toByteString(b));
    }

    private static Map convertMapEntryListToMap(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        Message message = (Message) it.next();
        Descriptors.Descriptor descriptorForType = message.getDescriptorForType();
        Descriptors.FieldDescriptor fieldDescriptorFindFieldByName = descriptorForType.findFieldByName("key");
        Descriptors.FieldDescriptor fieldDescriptorFindFieldByName2 = descriptorForType.findFieldByName("value");
        Object field = message.getField(fieldDescriptorFindFieldByName2);
        if (field instanceof Descriptors.EnumValueDescriptor) {
            field = Integer.valueOf(((Descriptors.EnumValueDescriptor) field).getNumber());
        }
        map.put(message.getField(fieldDescriptorFindFieldByName), field);
        while (it.hasNext()) {
            Message message2 = (Message) it.next();
            Object field2 = message2.getField(fieldDescriptorFindFieldByName2);
            if (field2 instanceof Descriptors.EnumValueDescriptor) {
                field2 = Integer.valueOf(((Descriptors.EnumValueDescriptor) field2).getNumber());
            }
            map.put(message2.getField(fieldDescriptorFindFieldByName), field2);
        }
        return map;
    }

    private static boolean compareMapField(Object a2, Object b) {
        return MapFieldLite.equals(convertMapEntryListToMap((List) a2), convertMapEntryListToMap((List) b));
    }

    static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> a2, Map<Descriptors.FieldDescriptor, Object> b) {
        if (a2.size() != b.size()) {
            return false;
        }
        for (Descriptors.FieldDescriptor fieldDescriptor : a2.keySet()) {
            if (!b.containsKey(fieldDescriptor)) {
                return false;
            }
            Object obj = a2.get(fieldDescriptor);
            Object obj2 = b.get(fieldDescriptor);
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) obj;
                    List list2 = (List) obj2;
                    if (list.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (!compareBytes(list.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                } else if (!compareBytes(obj, obj2)) {
                    return false;
                }
            } else if (fieldDescriptor.isMapField()) {
                if (!compareMapField(obj, obj2)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private static int hashMapField(Object value) {
        return MapFieldLite.calculateHashCodeForMap(convertMapEntryListToMap((List) value));
    }

    protected static int hashFields(int hash, Map<Descriptors.FieldDescriptor, Object> map) {
        int i;
        int iHashEnum;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            int number = (hash * 37) + key.getNumber();
            if (key.isMapField()) {
                i = number * 53;
                iHashEnum = hashMapField(value);
            } else if (key.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
                i = number * 53;
                iHashEnum = value.hashCode();
            } else if (key.isRepeated()) {
                i = number * 53;
                iHashEnum = Internal.hashEnumList((List) value);
            } else {
                i = number * 53;
                iHashEnum = Internal.hashEnum((Internal.EnumLite) value);
            }
            hash = i + iHashEnum;
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
        public BuilderType mo76clone() {
            throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
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

        BuilderType mergeFrom(final Message other, Map<Descriptors.FieldDescriptor, Object> allFields) {
            if (other.getDescriptorForType() != getDescriptorForType()) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet()) {
                Descriptors.FieldDescriptor key = entry.getKey();
                if (key.isRepeated()) {
                    Iterator it = ((List) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        addRepeatedField(key, it.next());
                    }
                } else if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    Message message = (Message) getField(key);
                    if (message == message.getDefaultInstanceForType()) {
                        setField(key, entry.getValue());
                    } else {
                        setField(key, message.newBuilderForType().mergeFrom(message).mergeFrom((Message) entry.getValue()).build());
                    }
                } else {
                    setField(key, entry.getValue());
                }
            }
            mergeUnknownFields(other.getUnknownFields());
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo2730mergeFrom(CodedInputStream codedInputStream) throws IOException {
            return (BuilderType) mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(final CodedInputStream input, final ExtensionRegistryLite extensionRegistry) throws IOException {
            UnknownFieldSet.Builder unknownFieldSetBuilder = input.shouldDiscardUnknownFields() ? null : getUnknownFieldSetBuilder();
            MessageReflection.mergeMessageFrom(this, unknownFieldSetBuilder, input, extensionRegistry);
            if (unknownFieldSetBuilder != null) {
                setUnknownFieldSetBuilder(unknownFieldSetBuilder);
            }
            return this;
        }

        protected UnknownFieldSet.Builder getUnknownFieldSetBuilder() {
            return UnknownFieldSet.newBuilder(getUnknownFields());
        }

        protected void setUnknownFieldSetBuilder(final UnknownFieldSet.Builder builder) {
            setUnknownFields(builder.build());
        }

        public BuilderType mergeUnknownFields(final UnknownFieldSet unknownFields) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFields).build());
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(final Descriptors.FieldDescriptor field) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
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

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(final ByteString data) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(final ByteString data, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mergeFrom(data, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo2733mergeFrom(final byte[] data) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo2733mergeFrom(data);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom */
        public BuilderType mo2734mergeFrom(final byte[] data, final int off, final int len) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo2734mergeFrom(data, off, len);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo2736mergeFrom(final byte[] data, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo2736mergeFrom(data, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom */
        public BuilderType mo2735mergeFrom(final byte[] data, final int off, final int len, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo2735mergeFrom(data, off, len, extensionRegistry);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo2731mergeFrom(final InputStream input) throws IOException {
            return (BuilderType) super.mo2731mergeFrom(input);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo2732mergeFrom(final InputStream input, final ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BuilderType) super.mo2732mergeFrom(input, extensionRegistry);
        }
    }
}

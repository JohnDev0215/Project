package com.google.protobuf;

import com.amplifyframework.core.model.ModelIdentifier;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Message;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes7.dex */
public final class MapEntry<K, V> extends AbstractMessage {
    private volatile int cachedSerializedSize;
    private final K key;
    private final V value;

    /* JADX INFO: Access modifiers changed from: private */
    static final class Metadata<K, V> extends MapEntryLite.Metadata<K, V> {
    }

    final Metadata<K, V> getMetadata() {
        return null;
    }

    private MapEntry(Metadata metadata, K key, V value) {
        this.cachedSerializedSize = -1;
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public int getSerializedSize() {
        if (this.cachedSerializedSize != -1) {
            return this.cachedSerializedSize;
        }
        int iComputeSerializedSize = MapEntryLite.computeSerializedSize(null, this.key, this.value);
        this.cachedSerializedSize = iComputeSerializedSize;
        return iComputeSerializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        MapEntryLite.writeTo(output, null, this.key, this.value);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return isInitialized(null, this.value);
    }

    @Override // com.google.protobuf.MessageLite
    public Parser<MapEntry<K, V>> getParserForType() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder<K, V> newBuilderForType() {
        return new Builder<>(null);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder<K, V> toBuilder() {
        return new Builder<>(null, this.key, this.value, true, true);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public MapEntry<K, V> getDefaultInstanceForType() {
        throw null;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        throw null;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        new TreeMap();
        throw null;
    }

    private void checkFieldDescriptor(Descriptors.FieldDescriptor field) {
        field.getContainingType();
        throw null;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor field) {
        checkFieldDescriptor(field);
        return true;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor field) {
        checkFieldDescriptor(field);
        Object key = field.getNumber() == 1 ? getKey() : getValue();
        return field.getType() == Descriptors.FieldDescriptor.Type.ENUM ? field.getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue()) : key;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getUnknownFields() {
        return UnknownFieldSet.getDefaultInstance();
    }

    public static class Builder<K, V> extends AbstractMessage.Builder<Builder<K, V>> {
        private boolean hasKey;
        private boolean hasValue;
        private K key;
        private V value;

        @Override // com.google.protobuf.Message.Builder
        public Builder<K, V> setUnknownFields(UnknownFieldSet unknownFields) {
            return this;
        }

        private Builder(Metadata<K, V> metadata) {
            throw null;
        }

        private Builder(Metadata<K, V> metadata, K key, V value, boolean hasKey, boolean hasValue) {
            this.key = key;
            this.value = value;
            this.hasKey = hasKey;
            this.hasValue = hasValue;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public Builder<K, V> setKey(K key) {
            this.key = key;
            this.hasKey = true;
            return this;
        }

        public Builder<K, V> setValue(V value) {
            this.value = value;
            this.hasValue = true;
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MapEntry<K, V> build() {
            MapEntry<K, V> mapEntryBuildPartial = buildPartial();
            if (mapEntryBuildPartial.isInitialized()) {
                return mapEntryBuildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mapEntryBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MapEntry<K, V> buildPartial() {
            return new MapEntry<>(null, this.key, this.value);
        }

        @Override // com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            throw null;
        }

        private void checkFieldDescriptor(Descriptors.FieldDescriptor field) {
            field.getContainingType();
            throw null;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
            checkFieldDescriptor(field);
            if (field.getNumber() != 2 || field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new RuntimeException(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + field.getFullName() + "\" is not a message value field.");
            }
            return ((Message) this.value).newBuilderForType();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.Message.Builder
        public Builder<K, V> setField(Descriptors.FieldDescriptor field, Object value) {
            checkFieldDescriptor(field);
            if (value == null) {
                throw new NullPointerException(field.getFullName() + " is null");
            }
            if (field.getNumber() == 1) {
                setKey(value);
            } else {
                if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                    value = Integer.valueOf(((Descriptors.EnumValueDescriptor) value).getNumber());
                } else if (field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) {
                    throw null;
                }
                setValue(value);
            }
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder<K, V> addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MapEntry<K, V> getDefaultInstanceForType() {
            throw null;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return MapEntry.isInitialized(null, this.value);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            new TreeMap();
            throw null;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor field) {
            checkFieldDescriptor(field);
            return field.getNumber() == 1 ? this.hasKey : this.hasValue;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor field) {
            checkFieldDescriptor(field);
            Object key = field.getNumber() == 1 ? getKey() : getValue();
            return field.getType() == Descriptors.FieldDescriptor.Type.ENUM ? field.getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue()) : key;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getUnknownFields() {
            return UnknownFieldSet.getDefaultInstance();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public Builder<K, V> mo76clone() {
            return new Builder<>(null, this.key, this.value, this.hasKey, this.hasValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <V> boolean isInitialized(Metadata metadata, V value) {
        throw null;
    }
}

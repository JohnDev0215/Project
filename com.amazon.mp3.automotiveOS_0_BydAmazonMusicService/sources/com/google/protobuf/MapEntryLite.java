package com.google.protobuf;

import com.google.protobuf.WireFormat;
import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class MapEntryLite<K, V> {
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType keyType, K defaultKey, WireFormat.FieldType valueType, V defaultValue) {
            this.keyType = keyType;
            this.defaultKey = defaultKey;
            this.valueType = valueType;
            this.defaultValue = defaultValue;
        }
    }

    private MapEntryLite(WireFormat.FieldType keyType, K defaultKey, WireFormat.FieldType valueType, V defaultValue) {
        this.metadata = new Metadata<>(keyType, defaultKey, valueType, defaultValue);
        this.key = defaultKey;
        this.value = defaultValue;
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType keyType, K defaultKey, WireFormat.FieldType valueType, V defaultValue) {
        return new MapEntryLite<>(keyType, defaultKey, valueType, defaultValue);
    }

    static <K, V> void writeTo(CodedOutputStream output, Metadata<K, V> metadata, K key, V value) throws IOException {
        FieldSet.writeElement(output, metadata.keyType, 1, key);
        FieldSet.writeElement(output, metadata.valueType, 2, value);
    }

    static <K, V> int computeSerializedSize(Metadata<K, V> metadata, K key, V value) {
        return FieldSet.computeElementSize(metadata.keyType, 1, key) + FieldSet.computeElementSize(metadata.valueType, 2, value);
    }

    public int computeMessageSize(int fieldNumber, K key, V value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, key, value));
    }

    Metadata<K, V> getMetadata() {
        return this.metadata;
    }
}

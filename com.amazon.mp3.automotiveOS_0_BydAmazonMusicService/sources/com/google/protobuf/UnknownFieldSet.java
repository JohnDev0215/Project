package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes7.dex */
public final class UnknownFieldSet implements MessageLite {
    private final TreeMap<Integer, Field> fields;
    private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(new TreeMap());
    private static final Parser PARSER = new Parser();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return true;
    }

    private UnknownFieldSet(TreeMap<Integer, Field> fields) {
        this.fields = fields;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static Builder newBuilder(UnknownFieldSet copyFrom) {
        return newBuilder().mergeFrom(copyFrom);
    }

    public static UnknownFieldSet getDefaultInstance() {
        return defaultInstance;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getDefaultInstanceForType() {
        return defaultInstance;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UnknownFieldSet) && this.fields.equals(((UnknownFieldSet) other).fields);
    }

    public int hashCode() {
        if (this.fields.isEmpty()) {
            return 0;
        }
        return this.fields.hashCode();
    }

    public Map<Integer, Field> asMap() {
        return (Map) this.fields.clone();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeTo(entry.getKey().intValue(), output);
        }
    }

    public String toString() {
        return TextFormat.printer().printToString(this);
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(codedBuilderNewCodedBuilder.getCodedOutput());
            return codedBuilderNewCodedBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public int getSerializedSize() {
        int serializedSize = 0;
        if (!this.fields.isEmpty()) {
            for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
                serializedSize += entry.getValue().getSerializedSize(entry.getKey().intValue());
            }
        }
        return serializedSize;
    }

    public void writeAsMessageSetTo(CodedOutputStream output) throws IOException {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeAsMessageSetExtensionTo(entry.getKey().intValue(), output);
        }
    }

    void writeTo(Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            for (Map.Entry<Integer, Field> entry : this.fields.descendingMap().entrySet()) {
                entry.getValue().writeTo(entry.getKey().intValue(), writer);
            }
            return;
        }
        for (Map.Entry<Integer, Field> entry2 : this.fields.entrySet()) {
            entry2.getValue().writeTo(entry2.getKey().intValue(), writer);
        }
    }

    void writeAsMessageSetTo(Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            for (Map.Entry<Integer, Field> entry : this.fields.descendingMap().entrySet()) {
                entry.getValue().writeAsMessageSetExtensionTo(entry.getKey().intValue(), writer);
            }
            return;
        }
        for (Map.Entry<Integer, Field> entry2 : this.fields.entrySet()) {
            entry2.getValue().writeAsMessageSetExtensionTo(entry2.getKey().intValue(), writer);
        }
    }

    public int getSerializedSizeAsMessageSet() {
        int serializedSizeAsMessageSetExtension = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            serializedSizeAsMessageSetExtension += entry.getValue().getSerializedSizeAsMessageSetExtension(entry.getKey().intValue());
        }
        return serializedSizeAsMessageSetExtension;
    }

    public static UnknownFieldSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data).build();
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilder().mergeFrom(this);
    }

    public static final class Builder implements MessageLite.Builder {
        private TreeMap<Integer, Field.Builder> fieldBuilders = new TreeMap<>();

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return true;
        }

        private Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        private Field.Builder getFieldBuilder(int number) {
            if (number == 0) {
                return null;
            }
            Field.Builder builder = this.fieldBuilders.get(Integer.valueOf(number));
            if (builder != null) {
                return builder;
            }
            Field.Builder builderNewBuilder = Field.newBuilder();
            this.fieldBuilders.put(Integer.valueOf(number), builderNewBuilder);
            return builderNewBuilder;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UnknownFieldSet build() {
            if (this.fieldBuilders.isEmpty()) {
                return UnknownFieldSet.getDefaultInstance();
            }
            TreeMap treeMap = new TreeMap();
            for (Map.Entry<Integer, Field.Builder> entry : this.fieldBuilders.entrySet()) {
                treeMap.put(entry.getKey(), entry.getValue().build());
            }
            return new UnknownFieldSet(treeMap);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UnknownFieldSet buildPartial() {
            return build();
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Builder m2739clone() {
            Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            for (Map.Entry<Integer, Field.Builder> entry : this.fieldBuilders.entrySet()) {
                builderNewBuilder.fieldBuilders.put(entry.getKey(), entry.getValue().m2740clone());
            }
            return builderNewBuilder;
        }

        public Builder mergeFrom(UnknownFieldSet other) {
            if (other != UnknownFieldSet.getDefaultInstance()) {
                for (Map.Entry entry : other.fields.entrySet()) {
                    mergeField(((Integer) entry.getKey()).intValue(), (Field) entry.getValue());
                }
            }
            return this;
        }

        public Builder mergeField(int number, final Field field) {
            if (number <= 0) {
                throw new IllegalArgumentException(number + " is not a valid field number.");
            }
            if (hasField(number)) {
                getFieldBuilder(number).mergeFrom(field);
            } else {
                addField(number, field);
            }
            return this;
        }

        public Builder mergeVarintField(int number, int value) {
            if (number <= 0) {
                throw new IllegalArgumentException(number + " is not a valid field number.");
            }
            getFieldBuilder(number).addVarint(value);
            return this;
        }

        public boolean hasField(int number) {
            return this.fieldBuilders.containsKey(Integer.valueOf(number));
        }

        public Builder addField(int number, Field field) {
            if (number <= 0) {
                throw new IllegalArgumentException(number + " is not a valid field number.");
            }
            this.fieldBuilders.put(Integer.valueOf(number), Field.newBuilder(field));
            return this;
        }

        public Builder mergeFrom(CodedInputStream input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    break;
                }
            } while (mergeFieldFrom(tag, input));
            return this;
        }

        public boolean mergeFieldFrom(int tag, CodedInputStream input) throws IOException {
            int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
            int tagWireType = WireFormat.getTagWireType(tag);
            if (tagWireType == 0) {
                getFieldBuilder(tagFieldNumber).addVarint(input.readInt64());
                return true;
            }
            if (tagWireType == 1) {
                getFieldBuilder(tagFieldNumber).addFixed64(input.readFixed64());
                return true;
            }
            if (tagWireType == 2) {
                getFieldBuilder(tagFieldNumber).addLengthDelimited(input.readBytes());
                return true;
            }
            if (tagWireType == 3) {
                Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                input.readGroup(tagFieldNumber, builderNewBuilder, ExtensionRegistry.getEmptyRegistry());
                getFieldBuilder(tagFieldNumber).addGroup(builderNewBuilder.build());
                return true;
            }
            if (tagWireType == 4) {
                return false;
            }
            if (tagWireType == 5) {
                getFieldBuilder(tagFieldNumber).addFixed32(input.readFixed32());
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = data.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return mergeFrom(input);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public Builder mergeFrom(MessageLite m) {
            if (m instanceof UnknownFieldSet) {
                return mergeFrom((UnknownFieldSet) m);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
    }

    public static final class Field {
        private static final Field fieldDefaultInstance = newBuilder().build();
        private List<Integer> fixed32;
        private List<Long> fixed64;
        private List<UnknownFieldSet> group;
        private List<ByteString> lengthDelimited;
        private List<Long> varint;

        private Field() {
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(Field copyFrom) {
            return newBuilder().mergeFrom(copyFrom);
        }

        public List<Long> getVarintList() {
            return this.varint;
        }

        public List<Integer> getFixed32List() {
            return this.fixed32;
        }

        public List<Long> getFixed64List() {
            return this.fixed64;
        }

        public List<ByteString> getLengthDelimitedList() {
            return this.lengthDelimited;
        }

        public List<UnknownFieldSet> getGroupList() {
            return this.group;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Field) {
                return Arrays.equals(getIdentityArray(), ((Field) other).getIdentityArray());
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(getIdentityArray());
        }

        private Object[] getIdentityArray() {
            return new Object[]{this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group};
        }

        public void writeTo(int fieldNumber, CodedOutputStream output) throws IOException {
            Iterator<Long> it = this.varint.iterator();
            while (it.hasNext()) {
                output.writeUInt64(fieldNumber, it.next().longValue());
            }
            Iterator<Integer> it2 = this.fixed32.iterator();
            while (it2.hasNext()) {
                output.writeFixed32(fieldNumber, it2.next().intValue());
            }
            Iterator<Long> it3 = this.fixed64.iterator();
            while (it3.hasNext()) {
                output.writeFixed64(fieldNumber, it3.next().longValue());
            }
            Iterator<ByteString> it4 = this.lengthDelimited.iterator();
            while (it4.hasNext()) {
                output.writeBytes(fieldNumber, it4.next());
            }
            Iterator<UnknownFieldSet> it5 = this.group.iterator();
            while (it5.hasNext()) {
                output.writeGroup(fieldNumber, it5.next());
            }
        }

        public int getSerializedSize(int fieldNumber) {
            Iterator<Long> it = this.varint.iterator();
            int iComputeGroupSize = 0;
            while (it.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeUInt64Size(fieldNumber, it.next().longValue());
            }
            Iterator<Integer> it2 = this.fixed32.iterator();
            while (it2.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeFixed32Size(fieldNumber, it2.next().intValue());
            }
            Iterator<Long> it3 = this.fixed64.iterator();
            while (it3.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeFixed64Size(fieldNumber, it3.next().longValue());
            }
            Iterator<ByteString> it4 = this.lengthDelimited.iterator();
            while (it4.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeBytesSize(fieldNumber, it4.next());
            }
            Iterator<UnknownFieldSet> it5 = this.group.iterator();
            while (it5.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeGroupSize(fieldNumber, it5.next());
            }
            return iComputeGroupSize;
        }

        public void writeAsMessageSetExtensionTo(int fieldNumber, CodedOutputStream output) throws IOException {
            Iterator<ByteString> it = this.lengthDelimited.iterator();
            while (it.hasNext()) {
                output.writeRawMessageSetExtension(fieldNumber, it.next());
            }
        }

        void writeTo(int fieldNumber, Writer writer) throws IOException {
            writer.writeInt64List(fieldNumber, this.varint, false);
            writer.writeFixed32List(fieldNumber, this.fixed32, false);
            writer.writeFixed64List(fieldNumber, this.fixed64, false);
            writer.writeBytesList(fieldNumber, this.lengthDelimited);
            if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
                for (int i = 0; i < this.group.size(); i++) {
                    writer.writeStartGroup(fieldNumber);
                    this.group.get(i).writeTo(writer);
                    writer.writeEndGroup(fieldNumber);
                }
                return;
            }
            for (int size = this.group.size() - 1; size >= 0; size--) {
                writer.writeEndGroup(fieldNumber);
                this.group.get(size).writeTo(writer);
                writer.writeStartGroup(fieldNumber);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeAsMessageSetExtensionTo(int fieldNumber, Writer writer) throws IOException {
            if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
                List<ByteString> list = this.lengthDelimited;
                ListIterator<ByteString> listIterator = list.listIterator(list.size());
                while (listIterator.hasPrevious()) {
                    writer.writeMessageSetItem(fieldNumber, listIterator.previous());
                }
                return;
            }
            Iterator<ByteString> it = this.lengthDelimited.iterator();
            while (it.hasNext()) {
                writer.writeMessageSetItem(fieldNumber, it.next());
            }
        }

        public int getSerializedSizeAsMessageSetExtension(int fieldNumber) {
            Iterator<ByteString> it = this.lengthDelimited.iterator();
            int iComputeRawMessageSetExtensionSize = 0;
            while (it.hasNext()) {
                iComputeRawMessageSetExtensionSize += CodedOutputStream.computeRawMessageSetExtensionSize(fieldNumber, it.next());
            }
            return iComputeRawMessageSetExtensionSize;
        }

        public static final class Builder {
            private Field result = new Field();

            private Builder() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public Builder m2740clone() {
                Field field = new Field();
                if (this.result.varint == null) {
                    field.varint = null;
                } else {
                    field.varint = new ArrayList(this.result.varint);
                }
                if (this.result.fixed32 == null) {
                    field.fixed32 = null;
                } else {
                    field.fixed32 = new ArrayList(this.result.fixed32);
                }
                if (this.result.fixed64 == null) {
                    field.fixed64 = null;
                } else {
                    field.fixed64 = new ArrayList(this.result.fixed64);
                }
                if (this.result.lengthDelimited == null) {
                    field.lengthDelimited = null;
                } else {
                    field.lengthDelimited = new ArrayList(this.result.lengthDelimited);
                }
                if (this.result.group == null) {
                    field.group = null;
                } else {
                    field.group = new ArrayList(this.result.group);
                }
                Builder builder = new Builder();
                builder.result = field;
                return builder;
            }

            public Field build() {
                Field field = new Field();
                if (this.result.varint == null) {
                    field.varint = Collections.emptyList();
                } else {
                    field.varint = Collections.unmodifiableList(new ArrayList(this.result.varint));
                }
                if (this.result.fixed32 == null) {
                    field.fixed32 = Collections.emptyList();
                } else {
                    field.fixed32 = Collections.unmodifiableList(new ArrayList(this.result.fixed32));
                }
                if (this.result.fixed64 == null) {
                    field.fixed64 = Collections.emptyList();
                } else {
                    field.fixed64 = Collections.unmodifiableList(new ArrayList(this.result.fixed64));
                }
                if (this.result.lengthDelimited == null) {
                    field.lengthDelimited = Collections.emptyList();
                } else {
                    field.lengthDelimited = Collections.unmodifiableList(new ArrayList(this.result.lengthDelimited));
                }
                if (this.result.group == null) {
                    field.group = Collections.emptyList();
                } else {
                    field.group = Collections.unmodifiableList(new ArrayList(this.result.group));
                }
                return field;
            }

            public Builder mergeFrom(Field other) {
                if (!other.varint.isEmpty()) {
                    if (this.result.varint == null) {
                        this.result.varint = new ArrayList();
                    }
                    this.result.varint.addAll(other.varint);
                }
                if (!other.fixed32.isEmpty()) {
                    if (this.result.fixed32 == null) {
                        this.result.fixed32 = new ArrayList();
                    }
                    this.result.fixed32.addAll(other.fixed32);
                }
                if (!other.fixed64.isEmpty()) {
                    if (this.result.fixed64 == null) {
                        this.result.fixed64 = new ArrayList();
                    }
                    this.result.fixed64.addAll(other.fixed64);
                }
                if (!other.lengthDelimited.isEmpty()) {
                    if (this.result.lengthDelimited == null) {
                        this.result.lengthDelimited = new ArrayList();
                    }
                    this.result.lengthDelimited.addAll(other.lengthDelimited);
                }
                if (!other.group.isEmpty()) {
                    if (this.result.group == null) {
                        this.result.group = new ArrayList();
                    }
                    this.result.group.addAll(other.group);
                }
                return this;
            }

            public Builder addVarint(long value) {
                if (this.result.varint == null) {
                    this.result.varint = new ArrayList();
                }
                this.result.varint.add(Long.valueOf(value));
                return this;
            }

            public Builder addFixed32(int value) {
                if (this.result.fixed32 == null) {
                    this.result.fixed32 = new ArrayList();
                }
                this.result.fixed32.add(Integer.valueOf(value));
                return this;
            }

            public Builder addFixed64(long value) {
                if (this.result.fixed64 == null) {
                    this.result.fixed64 = new ArrayList();
                }
                this.result.fixed64.add(Long.valueOf(value));
                return this;
            }

            public Builder addLengthDelimited(ByteString value) {
                if (this.result.lengthDelimited == null) {
                    this.result.lengthDelimited = new ArrayList();
                }
                this.result.lengthDelimited.add(value);
                return this;
            }

            public Builder addGroup(UnknownFieldSet value) {
                if (this.result.group == null) {
                    this.result.group = new ArrayList();
                }
                this.result.group.add(value);
                return this;
            }
        }
    }

    public static final class Parser extends AbstractParser<UnknownFieldSet> {
        @Override // com.google.protobuf.Parser
        public UnknownFieldSet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            try {
                builderNewBuilder.mergeFrom(input);
                return builderNewBuilder.buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(builderNewBuilder.buildPartial());
            }
        }
    }

    @Override // com.google.protobuf.MessageLite
    public final Parser getParserForType() {
        return PARSER;
    }
}

package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class ExtensionSchemaFull extends ExtensionSchema<Descriptors.FieldDescriptor> {
    private static final long EXTENSION_FIELD_OFFSET = getExtensionsFieldOffset();

    ExtensionSchemaFull() {
    }

    private static <T> long getExtensionsFieldOffset() {
        try {
            java.lang.reflect.Field field = GeneratedMessageV3.ExtendableMessage.class.getDeclaredField("extensions");
            return UnsafeUtil.objectFieldOffset(field);
        } catch (Throwable th) {
            throw new IllegalStateException("Unable to lookup extension field offset");
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    boolean hasExtensions(MessageLite prototype) {
        return prototype instanceof GeneratedMessageV3.ExtendableMessage;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public FieldSet<Descriptors.FieldDescriptor> getExtensions(Object message) {
        return (FieldSet) UnsafeUtil.getObject(message, EXTENSION_FIELD_OFFSET);
    }

    @Override // com.google.protobuf.ExtensionSchema
    void setExtensions(Object message, FieldSet<Descriptors.FieldDescriptor> extensions) {
        UnsafeUtil.putObject(message, EXTENSION_FIELD_OFFSET, extensions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.ExtensionSchema
    FieldSet<Descriptors.FieldDescriptor> getMutableExtensions(Object message) {
        FieldSet<Descriptors.FieldDescriptor> extensions = getExtensions(message);
        if (extensions.isImmutable()) {
            FieldSet fieldSetM1clone = extensions.m1clone();
            setExtensions(message, fieldSetM1clone);
            return fieldSetM1clone;
        }
        return extensions;
    }

    @Override // com.google.protobuf.ExtensionSchema
    void makeImmutable(Object message) {
        getExtensions(message).makeImmutable();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.ExtensionSchema
    <UT, UB> UB parseExtension(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> fieldSet, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) throws IOException {
        Object field;
        ArrayList arrayList;
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) obj;
        int number = extensionInfo.descriptor.getNumber();
        if (extensionInfo.descriptor.isRepeated() && extensionInfo.descriptor.isPacked()) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extensionInfo.descriptor.getLiteType().ordinal()]) {
                case 1:
                    ArrayList arrayList2 = new ArrayList();
                    reader.readDoubleList(arrayList2);
                    arrayList = arrayList2;
                    break;
                case 2:
                    ArrayList arrayList3 = new ArrayList();
                    reader.readFloatList(arrayList3);
                    arrayList = arrayList3;
                    break;
                case 3:
                    ArrayList arrayList4 = new ArrayList();
                    reader.readInt64List(arrayList4);
                    arrayList = arrayList4;
                    break;
                case 4:
                    ArrayList arrayList5 = new ArrayList();
                    reader.readUInt64List(arrayList5);
                    arrayList = arrayList5;
                    break;
                case 5:
                    ArrayList arrayList6 = new ArrayList();
                    reader.readInt32List(arrayList6);
                    arrayList = arrayList6;
                    break;
                case 6:
                    ArrayList arrayList7 = new ArrayList();
                    reader.readFixed64List(arrayList7);
                    arrayList = arrayList7;
                    break;
                case 7:
                    ArrayList arrayList8 = new ArrayList();
                    reader.readFixed32List(arrayList8);
                    arrayList = arrayList8;
                    break;
                case 8:
                    ArrayList arrayList9 = new ArrayList();
                    reader.readBoolList(arrayList9);
                    arrayList = arrayList9;
                    break;
                case 9:
                    ArrayList arrayList10 = new ArrayList();
                    reader.readUInt32List(arrayList10);
                    arrayList = arrayList10;
                    break;
                case 10:
                    ArrayList arrayList11 = new ArrayList();
                    reader.readSFixed32List(arrayList11);
                    arrayList = arrayList11;
                    break;
                case 11:
                    ArrayList arrayList12 = new ArrayList();
                    reader.readSFixed64List(arrayList12);
                    arrayList = arrayList12;
                    break;
                case 12:
                    ArrayList arrayList13 = new ArrayList();
                    reader.readSInt32List(arrayList13);
                    arrayList = arrayList13;
                    break;
                case 13:
                    ArrayList arrayList14 = new ArrayList();
                    reader.readSInt64List(arrayList14);
                    arrayList = arrayList14;
                    break;
                case 14:
                    ArrayList arrayList15 = new ArrayList();
                    reader.readEnumList(arrayList15);
                    ArrayList arrayList16 = new ArrayList();
                    Iterator<Integer> it = arrayList15.iterator();
                    while (it.hasNext()) {
                        int iIntValue = it.next().intValue();
                        Descriptors.EnumValueDescriptor enumValueDescriptorFindValueByNumber = extensionInfo.descriptor.getEnumType().findValueByNumber(iIntValue);
                        if (enumValueDescriptorFindValueByNumber != null) {
                            arrayList16.add(enumValueDescriptorFindValueByNumber);
                        } else {
                            ub = (UB) SchemaUtil.storeUnknownEnum(number, iIntValue, ub, unknownFieldSchema);
                        }
                    }
                    arrayList = arrayList16;
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + extensionInfo.descriptor.getLiteType());
            }
            fieldSet.setField(extensionInfo.descriptor, arrayList);
        } else {
            Object objValueOf = null;
            if (extensionInfo.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                int int32 = reader.readInt32();
                Descriptors.EnumValueDescriptor enumValueDescriptorFindValueByNumber2 = extensionInfo.descriptor.getEnumType().findValueByNumber(int32);
                if (enumValueDescriptorFindValueByNumber2 == null) {
                    return (UB) SchemaUtil.storeUnknownEnum(number, int32, ub, unknownFieldSchema);
                }
                objValueOf = enumValueDescriptorFindValueByNumber2;
            } else {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extensionInfo.descriptor.getLiteType().ordinal()]) {
                    case 1:
                        objValueOf = Double.valueOf(reader.readDouble());
                        break;
                    case 2:
                        objValueOf = Float.valueOf(reader.readFloat());
                        break;
                    case 3:
                        objValueOf = Long.valueOf(reader.readInt64());
                        break;
                    case 4:
                        objValueOf = Long.valueOf(reader.readUInt64());
                        break;
                    case 5:
                        objValueOf = Integer.valueOf(reader.readInt32());
                        break;
                    case 6:
                        objValueOf = Long.valueOf(reader.readFixed64());
                        break;
                    case 7:
                        objValueOf = Integer.valueOf(reader.readFixed32());
                        break;
                    case 8:
                        objValueOf = Boolean.valueOf(reader.readBool());
                        break;
                    case 9:
                        objValueOf = Integer.valueOf(reader.readUInt32());
                        break;
                    case 10:
                        objValueOf = Integer.valueOf(reader.readSFixed32());
                        break;
                    case 11:
                        objValueOf = Long.valueOf(reader.readSFixed64());
                        break;
                    case 12:
                        objValueOf = Integer.valueOf(reader.readSInt32());
                        break;
                    case 13:
                        objValueOf = Long.valueOf(reader.readSInt64());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        objValueOf = reader.readBytes();
                        break;
                    case 16:
                        objValueOf = reader.readString();
                        break;
                    case 17:
                        objValueOf = reader.readGroup(extensionInfo.defaultInstance.getClass(), extensionRegistryLite);
                        break;
                    case 18:
                        objValueOf = reader.readMessage(extensionInfo.defaultInstance.getClass(), extensionRegistryLite);
                        break;
                }
            }
            if (extensionInfo.descriptor.isRepeated()) {
                fieldSet.addRepeatedField(extensionInfo.descriptor, objValueOf);
            } else {
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extensionInfo.descriptor.getLiteType().ordinal()];
                if ((i == 17 || i == 18) && (field = fieldSet.getField(extensionInfo.descriptor)) != null) {
                    objValueOf = Internal.mergeMessage(field, objValueOf);
                }
                fieldSet.setField(extensionInfo.descriptor, objValueOf);
            }
        }
        return ub;
    }

    /* JADX INFO: renamed from: com.google.protobuf.ExtensionSchemaFull$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    int extensionNumber(Map.Entry<?, ?> extension) {
        Descriptors.FieldDescriptor descriptor = (Descriptors.FieldDescriptor) extension.getKey();
        return descriptor.getNumber();
    }

    @Override // com.google.protobuf.ExtensionSchema
    void serializeExtension(Writer writer, Map.Entry<?, ?> extension) throws IOException {
        Descriptors.FieldDescriptor descriptor = (Descriptors.FieldDescriptor) extension.getKey();
        if (descriptor.isRepeated()) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
                case 1:
                    SchemaUtil.writeDoubleList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 2:
                    SchemaUtil.writeFloatList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 3:
                    SchemaUtil.writeInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 4:
                    SchemaUtil.writeUInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 5:
                    SchemaUtil.writeInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 6:
                    SchemaUtil.writeFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 7:
                    SchemaUtil.writeFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 8:
                    SchemaUtil.writeBoolList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 9:
                    SchemaUtil.writeUInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 10:
                    SchemaUtil.writeSFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 11:
                    SchemaUtil.writeSFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 12:
                    SchemaUtil.writeSInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 13:
                    SchemaUtil.writeSInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 14:
                    List<Descriptors.EnumValueDescriptor> enumList = (List) extension.getValue();
                    List<Integer> list = new ArrayList<>();
                    for (Descriptors.EnumValueDescriptor d : enumList) {
                        list.add(Integer.valueOf(d.getNumber()));
                    }
                    SchemaUtil.writeInt32List(descriptor.getNumber(), list, writer, descriptor.isPacked());
                    break;
                case 15:
                    SchemaUtil.writeBytesList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
                case 16:
                    SchemaUtil.writeStringList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
                case 17:
                    SchemaUtil.writeGroupList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
                case 18:
                    SchemaUtil.writeMessageList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
            }
        }
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
            case 1:
                writer.writeDouble(descriptor.getNumber(), ((Double) extension.getValue()).doubleValue());
                break;
            case 2:
                writer.writeFloat(descriptor.getNumber(), ((Float) extension.getValue()).floatValue());
                break;
            case 3:
                writer.writeInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 4:
                writer.writeUInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 5:
                writer.writeInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 6:
                writer.writeFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 7:
                writer.writeFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 8:
                writer.writeBool(descriptor.getNumber(), ((Boolean) extension.getValue()).booleanValue());
                break;
            case 9:
                writer.writeUInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 10:
                writer.writeSFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 11:
                writer.writeSFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 12:
                writer.writeSInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 13:
                writer.writeSInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 14:
                writer.writeInt32(descriptor.getNumber(), ((Descriptors.EnumValueDescriptor) extension.getValue()).getNumber());
                break;
            case 15:
                writer.writeBytes(descriptor.getNumber(), (ByteString) extension.getValue());
                break;
            case 16:
                writer.writeString(descriptor.getNumber(), (String) extension.getValue());
                break;
            case 17:
                writer.writeGroup(descriptor.getNumber(), extension.getValue());
                break;
            case 18:
                writer.writeMessage(descriptor.getNumber(), extension.getValue());
                break;
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number) {
        return ((ExtensionRegistry) extensionRegistry).findImmutableExtensionByNumber(((Message) defaultInstance).getDescriptorForType(), number);
    }

    @Override // com.google.protobuf.ExtensionSchema
    void parseLengthPrefixedMessageSetItem(Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet<Descriptors.FieldDescriptor> extensions) throws IOException {
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) extension;
        if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            Object value = reader.readMessage(extensionInfo.defaultInstance.getClass(), extensionRegistry);
            extensions.setField(extensionInfo.descriptor, value);
        } else {
            extensions.setField(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistry, reader.readBytes()));
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    void parseMessageSetItem(ByteString data, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet<Descriptors.FieldDescriptor> extensions) throws IOException {
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) extension;
        Message messageBuildPartial = extensionInfo.defaultInstance.newBuilderForType().buildPartial();
        if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            Reader reader = BinaryReader.newInstance(ByteBuffer.wrap(data.toByteArray()), true);
            Protobuf.getInstance().mergeFrom(messageBuildPartial, reader, extensionRegistry);
            extensions.setField(extensionInfo.descriptor, messageBuildPartial);
            if (reader.getFieldNumber() != Integer.MAX_VALUE) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
            return;
        }
        extensions.setField(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistry, data));
    }
}

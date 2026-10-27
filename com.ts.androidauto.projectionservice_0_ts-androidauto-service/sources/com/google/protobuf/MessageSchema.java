package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;
import ts.car.dcm.common.data.ConnDevice;

/* JADX INFO: loaded from: classes.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.proto3 = proto3;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int objectsPosition;
        int[] intArray;
        int oneofCount;
        int minFieldNumber;
        int maxFieldNumber;
        int numEntries;
        int mapFieldCount;
        int checkInitialized;
        int i;
        int next;
        int i2;
        int next2;
        int i3;
        int next3;
        int i4;
        int next4;
        int i5;
        int next5;
        int i6;
        int next6;
        int i7;
        int next7;
        int i8;
        int next8;
        int fieldNumber;
        boolean z;
        int fieldOffset;
        String info;
        int oneofCount2;
        int presenceFieldOffset;
        int next9;
        int i9;
        int presenceMaskShift;
        java.lang.reflect.Field hasBitsField;
        int i10;
        int next10;
        java.lang.reflect.Field oneofField;
        java.lang.reflect.Field oneofCaseField;
        int i11;
        int next11;
        int i12;
        int next12;
        int i13;
        int next13;
        int i14;
        int next14;
        int i15;
        int next15;
        boolean isProto3 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String info2 = messageInfo.getStringInfo();
        int length = info2.length();
        int i16 = 0 + 1;
        int next16 = info2.charAt(0);
        int i17 = 55296;
        if (next16 >= 55296) {
            int result = next16 & 8191;
            int shift = 13;
            while (true) {
                i15 = i16 + 1;
                next15 = info2.charAt(i16);
                if (next15 < 55296) {
                    break;
                }
                result |= (next15 & 8191) << shift;
                shift += 13;
                i16 = i15;
            }
            next16 = result | (next15 << shift);
            i16 = i15;
        }
        int i18 = i16 + 1;
        int next17 = info2.charAt(i16);
        if (next17 >= 55296) {
            int result2 = next17 & 8191;
            int shift2 = 13;
            while (true) {
                i14 = i18 + 1;
                next14 = info2.charAt(i18);
                if (next14 < 55296) {
                    break;
                }
                result2 |= (next14 & 8191) << shift2;
                shift2 += 13;
                i18 = i14;
            }
            next17 = result2 | (next14 << shift2);
            i18 = i14;
        }
        int fieldCount = next17;
        if (fieldCount == 0) {
            int[] intArray2 = EMPTY_INT_ARRAY;
            oneofCount = 0;
            minFieldNumber = 0;
            maxFieldNumber = 0;
            numEntries = 0;
            mapFieldCount = 0;
            checkInitialized = 0;
            intArray = intArray2;
            objectsPosition = 0;
        } else {
            int i19 = i18 + 1;
            int next18 = info2.charAt(i18);
            if (next18 >= 55296) {
                int result3 = next18 & 8191;
                int shift3 = 13;
                while (true) {
                    i8 = i19 + 1;
                    next8 = info2.charAt(i19);
                    if (next8 < 55296) {
                        break;
                    }
                    result3 |= (next8 & 8191) << shift3;
                    shift3 += 13;
                    i19 = i8;
                }
                next18 = result3 | (next8 << shift3);
                i19 = i8;
            }
            int result4 = next18;
            int i20 = i19 + 1;
            int next19 = info2.charAt(i19);
            if (next19 >= 55296) {
                int result5 = next19 & 8191;
                int shift4 = 13;
                while (true) {
                    i7 = i20 + 1;
                    next7 = info2.charAt(i20);
                    if (next7 < 55296) {
                        break;
                    }
                    result5 |= (next7 & 8191) << shift4;
                    shift4 += 13;
                    i20 = i7;
                }
                next19 = result5 | (next7 << shift4);
                i20 = i7;
            }
            int result6 = next19;
            int i21 = i20 + 1;
            int next20 = info2.charAt(i20);
            if (next20 >= 55296) {
                int result7 = next20 & 8191;
                int shift5 = 13;
                while (true) {
                    i6 = i21 + 1;
                    next6 = info2.charAt(i21);
                    if (next6 < 55296) {
                        break;
                    }
                    result7 |= (next6 & 8191) << shift5;
                    shift5 += 13;
                    i21 = i6;
                }
                next20 = result7 | (next6 << shift5);
                i21 = i6;
            }
            int result8 = next20;
            int i22 = i21 + 1;
            int next21 = info2.charAt(i21);
            if (next21 >= 55296) {
                int result9 = next21 & 8191;
                int shift6 = 13;
                while (true) {
                    i5 = i22 + 1;
                    next5 = info2.charAt(i22);
                    if (next5 < 55296) {
                        break;
                    }
                    result9 |= (next5 & 8191) << shift6;
                    shift6 += 13;
                    i22 = i5;
                }
                next21 = result9 | (next5 << shift6);
                i22 = i5;
            }
            int result10 = next21;
            int i23 = i22 + 1;
            int next22 = info2.charAt(i22);
            if (next22 >= 55296) {
                int result11 = next22 & 8191;
                int shift7 = 13;
                while (true) {
                    i4 = i23 + 1;
                    next4 = info2.charAt(i23);
                    if (next4 < 55296) {
                        break;
                    }
                    result11 |= (next4 & 8191) << shift7;
                    shift7 += 13;
                    i23 = i4;
                }
                next22 = result11 | (next4 << shift7);
                i23 = i4;
            }
            int result12 = next22;
            int i24 = i23 + 1;
            int next23 = info2.charAt(i23);
            if (next23 >= 55296) {
                int result13 = next23 & 8191;
                int shift8 = 13;
                while (true) {
                    i3 = i24 + 1;
                    next3 = info2.charAt(i24);
                    if (next3 < 55296) {
                        break;
                    }
                    result13 |= (next3 & 8191) << shift8;
                    shift8 += 13;
                    i24 = i3;
                }
                next23 = result13 | (next3 << shift8);
                i24 = i3;
            }
            int result14 = next23;
            int i25 = i24 + 1;
            int next24 = info2.charAt(i24);
            if (next24 >= 55296) {
                int result15 = next24 & 8191;
                int shift9 = 13;
                while (true) {
                    i2 = i25 + 1;
                    next2 = info2.charAt(i25);
                    if (next2 < 55296) {
                        break;
                    }
                    result15 |= (next2 & 8191) << shift9;
                    shift9 += 13;
                    i25 = i2;
                }
                next24 = result15 | (next2 << shift9);
                i25 = i2;
            }
            int result16 = next24;
            int i26 = i25 + 1;
            next17 = info2.charAt(i25);
            if (next17 >= 55296) {
                int result17 = next17 & 8191;
                int shift10 = 13;
                while (true) {
                    i = i26 + 1;
                    next = info2.charAt(i26);
                    if (next < 55296) {
                        break;
                    }
                    result17 |= (next & 8191) << shift10;
                    shift10 += 13;
                    i26 = i;
                }
                next17 = result17 | (next << shift10);
                i26 = i;
            }
            int result18 = next17;
            int[] intArray3 = new int[result18 + result14 + result16];
            objectsPosition = (result4 * 2) + result6;
            intArray = intArray3;
            oneofCount = result4;
            minFieldNumber = result8;
            maxFieldNumber = result10;
            numEntries = result12;
            mapFieldCount = result14;
            checkInitialized = result18;
            i18 = i26;
        }
        Unsafe unsafe = UNSAFE;
        Object[] messageInfoObjects = messageInfo.getObjects();
        Class<?> messageClass = messageInfo.getDefaultInstance().getClass();
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldIndex = checkInitialized;
        int repeatedFieldIndex = checkInitialized + mapFieldCount;
        int checkInitializedPosition = 0;
        int mapFieldIndex2 = mapFieldIndex;
        int repeatedFieldIndex2 = repeatedFieldIndex;
        int bufferIndex = 0;
        int objectsPosition2 = objectsPosition;
        int i27 = i18;
        while (i27 < length) {
            int i28 = i27 + 1;
            int next25 = info2.charAt(i27);
            if (next25 >= i17) {
                int result19 = next25 & 8191;
                int shift11 = 13;
                while (true) {
                    i13 = i28 + 1;
                    next13 = info2.charAt(i28);
                    if (next13 < i17) {
                        break;
                    }
                    result19 |= (next13 & 8191) << shift11;
                    shift11 += 13;
                    i28 = i13;
                }
                next25 = result19 | (next13 << shift11);
                i28 = i13;
            }
            int result20 = next25;
            int i29 = i28 + 1;
            int next26 = info2.charAt(i28);
            if (next26 >= i17) {
                int result21 = next26 & 8191;
                int shift12 = 13;
                while (true) {
                    i12 = i29 + 1;
                    next12 = info2.charAt(i29);
                    if (next12 < i17) {
                        break;
                    }
                    result21 |= (next12 & 8191) << shift12;
                    shift12 += 13;
                    i29 = i12;
                }
                next26 = result21 | (next12 << shift12);
                i29 = i12;
            }
            int result22 = next26;
            int fieldType = result22 & 255;
            if ((result22 & 1024) != 0) {
                intArray[checkInitializedPosition] = bufferIndex;
                checkInitializedPosition++;
            }
            if (fieldType >= 51) {
                i27 = i29 + 1;
                int next27 = info2.charAt(i29);
                if (next27 >= i17) {
                    int result23 = next27 & 8191;
                    int shift13 = 13;
                    while (true) {
                        i11 = i27 + 1;
                        next11 = info2.charAt(i27);
                        if (next11 < i17) {
                            break;
                        }
                        result23 |= (next11 & 8191) << shift13;
                        shift13 += 13;
                        i27 = i11;
                    }
                    next27 = result23 | (next11 << shift13);
                    i27 = i11;
                }
                int result24 = next27;
                int oneofFieldType = fieldType - 51;
                if (oneofFieldType == 9 || oneofFieldType == 17) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition2];
                    objectsPosition2++;
                } else if (oneofFieldType == 12 && !isProto3) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition2];
                    objectsPosition2++;
                }
                int index = result24 * 2;
                Object o = messageInfoObjects[index];
                if (o instanceof java.lang.reflect.Field) {
                    oneofField = (java.lang.reflect.Field) o;
                } else {
                    oneofField = reflectField(messageClass, (String) o);
                    messageInfoObjects[index] = oneofField;
                }
                fieldNumber = result20;
                fieldOffset = (int) unsafe.objectFieldOffset(oneofField);
                int index2 = index + 1;
                Object o2 = messageInfoObjects[index2];
                if (o2 instanceof java.lang.reflect.Field) {
                    oneofCaseField = (java.lang.reflect.Field) o2;
                } else {
                    oneofCaseField = reflectField(messageClass, (String) o2);
                    messageInfoObjects[index2] = oneofCaseField;
                }
                presenceFieldOffset = (int) unsafe.objectFieldOffset(oneofCaseField);
                presenceMaskShift = 0;
                info = info2;
                oneofCount2 = oneofCount;
            } else {
                fieldNumber = result20;
                int objectsPosition3 = objectsPosition2 + 1;
                java.lang.reflect.Field field = reflectField(messageClass, (String) messageInfoObjects[objectsPosition2]);
                if (fieldType == 9 || fieldType == 17) {
                    z = true;
                    objects[((bufferIndex / 3) * 2) + 1] = field.getType();
                } else if (fieldType == 27 || fieldType == 49) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                    objectsPosition3++;
                    z = true;
                } else if (fieldType == 12 || fieldType == 30 || fieldType == 44) {
                    if (isProto3) {
                        z = true;
                    } else {
                        objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                        objectsPosition3++;
                        z = true;
                    }
                } else if (fieldType != 50) {
                    z = true;
                } else {
                    int mapFieldIndex3 = mapFieldIndex2 + 1;
                    intArray[mapFieldIndex2] = bufferIndex;
                    int objectsPosition4 = objectsPosition3 + 1;
                    objects[(bufferIndex / 3) * 2] = messageInfoObjects[objectsPosition3];
                    if ((result22 & 2048) == 0) {
                        mapFieldIndex2 = mapFieldIndex3;
                        objectsPosition3 = objectsPosition4;
                        z = true;
                    } else {
                        objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition4];
                        mapFieldIndex2 = mapFieldIndex3;
                        objectsPosition3 = objectsPosition4 + 1;
                        z = true;
                    }
                }
                int i30 = i29;
                fieldOffset = (int) unsafe.objectFieldOffset(field);
                int objectsPosition5 = objectsPosition3;
                boolean hasHasBit = (result22 & 4096) == 4096 ? z : false;
                if (!hasHasBit || fieldType > 17) {
                    info = info2;
                    oneofCount2 = oneofCount;
                    presenceFieldOffset = 1048575;
                    next9 = next26;
                    i9 = i30;
                    presenceMaskShift = 0;
                } else {
                    i9 = i30 + 1;
                    int next28 = info2.charAt(i30);
                    if (next28 < 55296) {
                        info = info2;
                    } else {
                        int result25 = next28 & 8191;
                        int shift14 = 13;
                        while (true) {
                            i10 = i9 + 1;
                            next10 = info2.charAt(i9);
                            info = info2;
                            if (next10 < 55296) {
                                break;
                            }
                            result25 |= (next10 & 8191) << shift14;
                            shift14 += 13;
                            i9 = i10;
                            info2 = info;
                        }
                        next28 = result25 | (next10 << shift14);
                        i9 = i10;
                    }
                    int hasBitsIndex = next28;
                    int index3 = (oneofCount * 2) + (hasBitsIndex / 32);
                    Object o3 = messageInfoObjects[index3];
                    oneofCount2 = oneofCount;
                    if (o3 instanceof java.lang.reflect.Field) {
                        hasBitsField = (java.lang.reflect.Field) o3;
                    } else {
                        hasBitsField = reflectField(messageClass, (String) o3);
                        messageInfoObjects[index3] = hasBitsField;
                    }
                    next9 = next28;
                    presenceFieldOffset = (int) unsafe.objectFieldOffset(hasBitsField);
                    int presenceMaskShift2 = hasBitsIndex % 32;
                    presenceMaskShift = presenceMaskShift2;
                }
                if (fieldType >= 18 && fieldType <= 49) {
                    intArray[repeatedFieldIndex2] = fieldOffset;
                    repeatedFieldIndex2++;
                    i27 = i9;
                    objectsPosition2 = objectsPosition5;
                } else {
                    i27 = i9;
                    objectsPosition2 = objectsPosition5;
                }
            }
            int bufferIndex2 = bufferIndex + 1;
            buffer[bufferIndex] = fieldNumber;
            int bufferIndex3 = bufferIndex2 + 1;
            buffer[bufferIndex2] = ((result22 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | ((result22 & 256) != 0 ? REQUIRED_MASK : 0) | (fieldType << 20) | fieldOffset;
            bufferIndex = bufferIndex3 + 1;
            buffer[bufferIndex3] = (presenceMaskShift << 20) | presenceFieldOffset;
            info2 = info;
            oneofCount = oneofCount2;
            i17 = 55296;
        }
        int length2 = checkInitialized + mapFieldCount;
        return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, messageInfo.getDefaultInstance(), isProto3, false, intArray, checkInitialized, length2, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            java.lang.reflect.Field[] fields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:34:0x0087 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <T> com.google.protobuf.MessageSchema<T> newSchemaForMessageInfo(com.google.protobuf.StructuralMessageInfo r32, com.google.protobuf.NewInstanceSchema r33, com.google.protobuf.ListFieldSchema r34, com.google.protobuf.UnknownFieldSchema<?, ?> r35, com.google.protobuf.ExtensionSchema<?> r36, com.google.protobuf.MapFieldSchema r37) {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchemaForMessageInfo(com.google.protobuf.StructuralMessageInfo, com.google.protobuf.NewInstanceSchema, com.google.protobuf.ListFieldSchema, com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, com.google.protobuf.MapFieldSchema):com.google.protobuf.MessageSchema");
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int fieldOffset;
        int typeId;
        int typeId2;
        int presenceFieldOffset;
        int presenceFieldOffset2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId = fi.getType().id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                if (presenceField == null) {
                    presenceFieldOffset2 = 1048575;
                } else {
                    presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(presenceField);
                }
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
                typeId = typeId3;
                typeId2 = presenceFieldOffset2;
            } else if (fi.getCachedSizeField() == null) {
                typeId = typeId3;
                typeId2 = 0;
                presenceFieldOffset = 0;
            } else {
                int presenceFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset3;
                presenceFieldOffset = 0;
            }
        }
        buffer[bufferIndex] = fi.getFieldNumber();
        buffer[bufferIndex + 1] = (fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (fi.isRequired() ? REQUIRED_MASK : 0) | (typeId << 20) | fieldOffset;
        buffer[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId2;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        if (messageFieldClass != null) {
            objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                if (arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset))) {
                    break;
                }
                break;
            case 1:
                if (arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset))) {
                    break;
                }
                break;
            case 2:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 3:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 4:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 5:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 6:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 7:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset)) {
                    break;
                }
                break;
            case 8:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 9:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 10:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 11:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 12:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 13:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 14:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 15:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 16:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 17:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 51:
            case 52:
            case 53:
            case KEYCODE_Z_VALUE:
            case KEYCODE_COMMA_VALUE:
            case 56:
            case KEYCODE_ALT_LEFT_VALUE:
            case KEYCODE_ALT_RIGHT_VALUE:
            case KEYCODE_SHIFT_LEFT_VALUE:
            case KEYCODE_SHIFT_RIGHT_VALUE:
            case KEYCODE_TAB_VALUE:
            case KEYCODE_SPACE_VALUE:
            case 63:
            case 64:
            case KEYCODE_ENVELOPE_VALUE:
            case KEYCODE_ENTER_VALUE:
            case KEYCODE_DEL_VALUE:
            case KEYCODE_GRAVE_VALUE:
                if (isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    break;
                case 8:
                    int protoHash = hashCode * 53;
                    int hashCode2 = protoHash + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    hashCode = hashCode2;
                    break;
                case 9:
                    int protoHash2 = 37;
                    Object submessage = UnsafeUtil.getObject(message, offset);
                    if (submessage != null) {
                        protoHash2 = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 16:
                    int protoHash3 = hashCode * 53;
                    int hashCode3 = protoHash3 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    hashCode = hashCode3;
                    break;
                case 17:
                    int protoHash4 = 37;
                    Object submessage2 = UnsafeUtil.getObject(message, offset);
                    if (submessage2 != null) {
                        protoHash4 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                    }
                    break;
                case 52:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                    }
                    break;
                case 53:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_Z_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_COMMA_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_ALT_LEFT_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case KEYCODE_ALT_RIGHT_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                    }
                    break;
                case KEYCODE_SHIFT_LEFT_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    }
                    break;
                case KEYCODE_SHIFT_RIGHT_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
                case KEYCODE_TAB_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
                case KEYCODE_SPACE_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 63:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case KEYCODE_ENVELOPE_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_ENTER_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                    }
                    break;
                case KEYCODE_DEL_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_GRAVE_VALUE:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    }
                    break;
            }
        }
        int pos2 = hashCode * 53;
        int hashCode4 = pos2 + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode4 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode4;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, T other) {
        if (other == null) {
            throw null;
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case 51:
            case 52:
            case 53:
            case KEYCODE_Z_VALUE:
            case KEYCODE_COMMA_VALUE:
            case 56:
            case KEYCODE_ALT_LEFT_VALUE:
            case KEYCODE_ALT_RIGHT_VALUE:
            case KEYCODE_SHIFT_LEFT_VALUE:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                }
                break;
            case KEYCODE_SHIFT_RIGHT_VALUE:
                mergeOneofMessage(message, other, pos);
                break;
            case KEYCODE_TAB_VALUE:
            case KEYCODE_SPACE_VALUE:
            case 63:
            case 64:
            case KEYCODE_ENVELOPE_VALUE:
            case KEYCODE_ENTER_VALUE:
            case KEYCODE_DEL_VALUE:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                }
                break;
            case KEYCODE_GRAVE_VALUE:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    private void mergeMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        if (!isFieldPresent(other, pos)) {
            return;
        }
        Object mine = UnsafeUtil.getObject(message, offset);
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setFieldPresent(message, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setFieldPresent(message, pos);
        }
    }

    private void mergeOneofMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        int number = numberAt(pos);
        long offset = offset(typeAndOffset);
        if (!isOneofPresent(other, number, pos)) {
            return;
        }
        Object mine = null;
        if (isOneofPresent(message, number, pos)) {
            mine = UnsafeUtil.getObject(message, offset);
        }
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setOneofPresent(message, number, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setOneofPresent(message, number, pos);
        }
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    private int getSerializedSizeProto2(T message) {
        int currentPresenceFieldOffset;
        int size = 0;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField = 0;
        int i = 0;
        while (i < this.buffer.length) {
            int typeAndOffset = typeAndOffsetAt(i);
            int number = numberAt(i);
            int fieldType = type(typeAndOffset);
            int presenceMaskAndOffset = 0;
            int presenceMask = 0;
            if (fieldType > 17) {
                if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                    presenceMaskAndOffset = this.buffer[i + 2] & 1048575;
                }
            } else {
                presenceMaskAndOffset = this.buffer[i + 2];
                int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                }
            }
            long offset = offset(typeAndOffset);
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset3 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset3 != 0) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset4 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset4 != 0) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset5 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset5 != 0) {
                        size += CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset6 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset6 != 0) {
                        size += CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset7 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset7 != 0) {
                        size += CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset8 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset8 != 0) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset9 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset9 != 0) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset10 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset10 != 0) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset11 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset11 != 0) {
                        Object value = unsafe.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                        }
                    }
                    break;
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset12 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset12 != 0) {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset13 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset13 != 0) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset14 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset14 != 0) {
                        size += CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset15 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset15 != 0) {
                        size += CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset16 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset16 != 0) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset17 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset17 != 0) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset18 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset18 != 0) {
                        size += CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset19 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset19 != 0) {
                        size += CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset20 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset20 != 0) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                    }
                    break;
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                    }
                    break;
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                    }
                    break;
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                    }
                    break;
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                    }
                    break;
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                    }
                    break;
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                    }
                    break;
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                    }
                    break;
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                    }
                    break;
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                    }
                    break;
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                    }
                    break;
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                    }
                    break;
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                    }
                    break;
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                    }
                    break;
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case 52:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case 53:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_Z_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_COMMA_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case 56:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_ALT_LEFT_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_ALT_RIGHT_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_SHIFT_LEFT_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_SHIFT_RIGHT_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_TAB_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_SPACE_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case 63:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case 64:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_ENVELOPE_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_ENTER_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_DEL_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                case KEYCODE_GRAVE_VALUE:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    break;
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    break;
            }
            i += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
        }
        int size2 = size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size2 + this.extensionSchema.getExtensions(message).getSerializedSize();
        }
        return size2;
    }

    private int getSerializedSizeProto3(T message) {
        int cachedSizeOffset;
        Unsafe unsafe = UNSAFE;
        int size = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i);
            long offset = offset(typeAndOffset);
            if (fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                cachedSizeOffset = this.buffer[i + 2] & 1048575;
            } else {
                cachedSizeOffset = 0;
            }
            switch (fieldType) {
                case 0:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case 1:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case 2:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 3:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 4:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 5:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case 6:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case 7:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case 8:
                    if (isFieldPresent(message, i)) {
                        Object value = UnsafeUtil.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                        }
                    }
                    break;
                case 9:
                    if (isFieldPresent(message, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 10:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                    }
                    break;
                case 11:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 12:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 13:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case 14:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case 15:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt(message, offset));
                    }
                    break;
                case 16:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong(message, offset));
                    }
                    break;
                case 17:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, listAt(message, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, listAt(message, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, listAt(message, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, listAt(message, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, listAt(message, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, listAt(message, offset));
                    break;
                case 29:
                    size += SchemaUtil.computeSizeUInt32List(number, listAt(message, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, listAt(message, offset), false);
                    break;
                case 31:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, listAt(message, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, listAt(message, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                    }
                    break;
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                    }
                    break;
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                    }
                    break;
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                    }
                    break;
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                    }
                    break;
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                    }
                    break;
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                    }
                    break;
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                    }
                    break;
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                    }
                    break;
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                    }
                    break;
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                    }
                    break;
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                    }
                    break;
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                    }
                    break;
                case 48:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                    }
                    break;
                case 49:
                    size += SchemaUtil.computeSizeGroupList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                    }
                    break;
                case 52:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                    }
                    break;
                case 53:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_Z_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_COMMA_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                    }
                    break;
                case KEYCODE_ALT_LEFT_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                    }
                    break;
                case KEYCODE_ALT_RIGHT_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                    }
                    break;
                case KEYCODE_SHIFT_LEFT_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        Object value2 = UnsafeUtil.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                    }
                    break;
                case KEYCODE_SHIFT_RIGHT_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
                case KEYCODE_TAB_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                    }
                    break;
                case KEYCODE_SPACE_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case 63:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                    }
                    break;
                case KEYCODE_ENVELOPE_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                    }
                    break;
                case KEYCODE_ENTER_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                    }
                    break;
                case KEYCODE_DEL_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_GRAVE_VALUE:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                    }
                    break;
            }
        }
        return size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSize(unknowns);
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        Map.Entry<?, ?> entry;
        int currentPresenceFieldOffset;
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry2 = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry2 = nextExtension;
            }
        }
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField = 0;
        int bufferLength = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int pos = 0;
        while (pos < bufferLength) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            int fieldType = type(typeAndOffset);
            int presenceMask = 0;
            Map.Entry<?, ?> entry3 = entry2;
            if (fieldType > 17) {
                entry = entry3;
            } else {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = 1048575 & presenceMaskAndOffset;
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                }
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                entry = entry3;
            }
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= number) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
            }
            Map.Entry<?, ?> entry4 = entry;
            int currentPresenceFieldOffset3 = currentPresenceFieldOffset2;
            long offset = offset(typeAndOffset);
            int bufferLength2 = bufferLength;
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeDouble(number, doubleAt(message, offset));
                    }
                    break;
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFloat(number, floatAt(message, offset));
                    }
                    break;
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeUInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFixed64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeFixed32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeBool(number, booleanAt(message, offset));
                    }
                    break;
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writeString(number, unsafe.getObject(message, offset), writer);
                    }
                    break;
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        Object value = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                    }
                    break;
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeUInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeEnum(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSFixed32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSFixed64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSInt32(number, unsafe.getInt(message, offset));
                    }
                    break;
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeSInt64(number, unsafe.getLong(message, offset));
                    }
                    break;
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) != 0) {
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                    }
                    break;
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset4 = numberAt(pos);
                    SchemaUtil.writeDoubleList(currentPresenceFieldOffset4, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset5 = numberAt(pos);
                    SchemaUtil.writeFloatList(currentPresenceFieldOffset5, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset6 = numberAt(pos);
                    SchemaUtil.writeInt64List(currentPresenceFieldOffset6, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset7 = numberAt(pos);
                    SchemaUtil.writeUInt64List(currentPresenceFieldOffset7, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset8 = numberAt(pos);
                    SchemaUtil.writeInt32List(currentPresenceFieldOffset8, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset9 = numberAt(pos);
                    SchemaUtil.writeFixed64List(currentPresenceFieldOffset9, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset10 = numberAt(pos);
                    SchemaUtil.writeFixed32List(currentPresenceFieldOffset10, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset11 = numberAt(pos);
                    SchemaUtil.writeBoolList(currentPresenceFieldOffset11, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset12 = numberAt(pos);
                    SchemaUtil.writeUInt32List(currentPresenceFieldOffset12, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset13 = numberAt(pos);
                    SchemaUtil.writeEnumList(currentPresenceFieldOffset13, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset14 = numberAt(pos);
                    SchemaUtil.writeSFixed32List(currentPresenceFieldOffset14, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset15 = numberAt(pos);
                    SchemaUtil.writeSFixed64List(currentPresenceFieldOffset15, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset16 = numberAt(pos);
                    SchemaUtil.writeSInt32List(currentPresenceFieldOffset16, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset17 = numberAt(pos);
                    SchemaUtil.writeSInt64List(currentPresenceFieldOffset17, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
                    break;
                case 51:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeDouble(number, oneofDoubleAt(message, offset));
                    }
                    break;
                case 52:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFloat(number, oneofFloatAt(message, offset));
                    }
                    break;
                case 53:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt64(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_Z_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt64(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_COMMA_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt32(number, oneofIntAt(message, offset));
                    }
                    break;
                case 56:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed64(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_ALT_LEFT_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed32(number, oneofIntAt(message, offset));
                    }
                    break;
                case KEYCODE_ALT_RIGHT_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBool(number, oneofBooleanAt(message, offset));
                    }
                    break;
                case KEYCODE_SHIFT_LEFT_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, unsafe.getObject(message, offset), writer);
                    }
                    break;
                case KEYCODE_SHIFT_RIGHT_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        Object value2 = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                    }
                    break;
                case KEYCODE_TAB_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                    }
                    break;
                case KEYCODE_SPACE_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt32(number, oneofIntAt(message, offset));
                    }
                    break;
                case 63:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeEnum(number, oneofIntAt(message, offset));
                    }
                    break;
                case 64:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed32(number, oneofIntAt(message, offset));
                    }
                    break;
                case KEYCODE_ENVELOPE_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed64(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_ENTER_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt32(number, oneofIntAt(message, offset));
                    }
                    break;
                case KEYCODE_DEL_VALUE:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt64(number, oneofLongAt(message, offset));
                    }
                    break;
                case KEYCODE_GRAVE_VALUE:
                    if (!isOneofPresent(message, number, pos)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    } else {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                    }
                    break;
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    break;
            }
            pos += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            entry2 = entry4;
            bufferLength = bufferLength2;
        }
        while (entry2 != null) {
            this.extensionSchema.serializeExtension(writer, entry2);
            entry2 = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry = nextExtension;
            }
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= number) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
            }
            switch (type(typeAndOffset)) {
                case 0:
                    if (isFieldPresent(message, pos)) {
                        writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 1:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 2:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 3:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 4:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 5:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 6:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 7:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 8:
                    if (isFieldPresent(message, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(message, pos)) {
                        Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                    }
                    break;
                case 10:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 12:
                    if (isFieldPresent(message, pos)) {
                        writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 13:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 14:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 15:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 16:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 17:
                    if (isFieldPresent(message, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                    break;
                case 51:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 52:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 53:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_Z_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_COMMA_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_ALT_LEFT_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_ALT_RIGHT_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_SHIFT_LEFT_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    }
                    break;
                case KEYCODE_SHIFT_RIGHT_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                    }
                    break;
                case KEYCODE_TAB_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_SPACE_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 63:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_ENVELOPE_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_ENTER_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_DEL_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                    }
                    break;
                case KEYCODE_GRAVE_VALUE:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry entry = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.descendingIterator();
                Map.Entry nextExtension = extensionIterator.next();
                entry = nextExtension;
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (entry != null && this.extensionSchema.extensionNumber(entry) > number) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (isFieldPresent(message, pos)) {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 1:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 2:
                        if (isFieldPresent(message, pos)) {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 3:
                        if (isFieldPresent(message, pos)) {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 4:
                        if (isFieldPresent(message, pos)) {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 5:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 6:
                        if (isFieldPresent(message, pos)) {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 7:
                        if (isFieldPresent(message, pos)) {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 8:
                        if (isFieldPresent(message, pos)) {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        }
                        break;
                    case 9:
                        if (isFieldPresent(message, pos)) {
                            Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        }
                        break;
                    case 10:
                        if (isFieldPresent(message, pos)) {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        }
                        break;
                    case 11:
                        if (isFieldPresent(message, pos)) {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 12:
                        if (isFieldPresent(message, pos)) {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 13:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 14:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 15:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 16:
                        if (isFieldPresent(message, pos)) {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 17:
                        if (isFieldPresent(message, pos)) {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        }
                        break;
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 29:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 48:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 49:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 52:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 53:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_Z_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_COMMA_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 56:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_ALT_LEFT_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_ALT_RIGHT_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_SHIFT_LEFT_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        }
                        break;
                    case KEYCODE_SHIFT_RIGHT_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        }
                        break;
                    case KEYCODE_TAB_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_SPACE_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 63:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case 64:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_ENVELOPE_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_ENTER_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_DEL_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        }
                        break;
                    case KEYCODE_GRAVE_VALUE:
                        if (isOneofPresent(message, number, pos)) {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        }
                        break;
                }
            } else {
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = extensionIterator.hasNext() ? (Map.Entry) extensionIterator.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws Throwable {
        if (extensionRegistry == null) {
            throw null;
        }
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /* JADX WARN: Code restructure failed: missing block: B:247:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0091, code lost:
    
        r1 = r18.checkInitializedCount;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0095, code lost:
    
        if (r1 >= r18.repeatedFieldOffsetStart) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0097, code lost:
    
        r13 = filterMapUnknownEnumValues(r21, r18.intArray[r1], r13, r19);
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a2, code lost:
    
        if (r13 == null) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a4, code lost:
    
        r19.setBuilderToMessage(r21, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a7, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:175:0x06ac A[Catch: all -> 0x06f1, TRY_LEAVE, TryCatch #0 {all -> 0x06f1, blocks: (B:27:0x0066, B:28:0x006e, B:30:0x0077, B:34:0x0082, B:35:0x0087, B:46:0x00ab, B:48:0x00b0, B:52:0x00ba, B:161:0x0685, B:173:0x06a6, B:175:0x06ac, B:185:0x06cb, B:186:0x06d0, B:56:0x00d6, B:57:0x00ec, B:58:0x0102, B:59:0x0118, B:60:0x012e, B:62:0x0138, B:65:0x013f, B:66:0x0148, B:67:0x0159, B:68:0x016f, B:69:0x0180, B:71:0x0187, B:73:0x01b6, B:72:0x01a3, B:74:0x01bc, B:75:0x01c5, B:76:0x01db, B:77:0x01f1, B:78:0x0207, B:79:0x021d, B:80:0x0233, B:81:0x0249, B:82:0x025f, B:83:0x0275, B:93:0x02ac, B:94:0x02bf, B:95:0x02d2, B:96:0x02e5, B:97:0x02f8, B:98:0x0315, B:99:0x0328, B:100:0x033b, B:101:0x034e, B:102:0x0361, B:103:0x0374, B:104:0x0387, B:105:0x039a, B:106:0x03ad, B:107:0x03c0, B:108:0x03d3, B:109:0x03e6, B:110:0x03f9, B:111:0x040c, B:112:0x0429, B:113:0x043c, B:114:0x044f, B:119:0x0470, B:120:0x0475, B:121:0x0485, B:122:0x0495, B:123:0x04a5, B:124:0x04b5, B:125:0x04c5, B:126:0x04d5, B:127:0x04e5, B:128:0x04f5, B:130:0x04fd, B:131:0x051a, B:132:0x052f, B:133:0x0540, B:134:0x0551, B:135:0x0562, B:136:0x0573, B:138:0x057e, B:141:0x0585, B:142:0x058d, B:143:0x0599, B:144:0x05aa, B:145:0x05bb, B:147:0x05c3, B:148:0x05e0, B:149:0x05f5, B:150:0x05fe, B:151:0x060f, B:152:0x0620, B:153:0x0631, B:154:0x0641, B:155:0x0651, B:156:0x0661, B:157:0x0671, B:55:0x00c1, B:88:0x028e, B:90:0x02a0), top: B:207:0x0066 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0700 A[LOOP:5: B:201:0x06fc->B:203:0x0700, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:251:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r16v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r16v100 */
    /* JADX WARN: Type inference failed for: r16v101 */
    /* JADX WARN: Type inference failed for: r16v102 */
    /* JADX WARN: Type inference failed for: r16v103 */
    /* JADX WARN: Type inference failed for: r16v104 */
    /* JADX WARN: Type inference failed for: r16v105 */
    /* JADX WARN: Type inference failed for: r16v106 */
    /* JADX WARN: Type inference failed for: r16v107 */
    /* JADX WARN: Type inference failed for: r16v108 */
    /* JADX WARN: Type inference failed for: r16v109 */
    /* JADX WARN: Type inference failed for: r16v110 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v31 */
    /* JADX WARN: Type inference failed for: r16v32 */
    /* JADX WARN: Type inference failed for: r16v33 */
    /* JADX WARN: Type inference failed for: r16v34 */
    /* JADX WARN: Type inference failed for: r16v35 */
    /* JADX WARN: Type inference failed for: r16v36 */
    /* JADX WARN: Type inference failed for: r16v37 */
    /* JADX WARN: Type inference failed for: r16v38 */
    /* JADX WARN: Type inference failed for: r16v39 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v40 */
    /* JADX WARN: Type inference failed for: r16v41 */
    /* JADX WARN: Type inference failed for: r16v42 */
    /* JADX WARN: Type inference failed for: r16v43 */
    /* JADX WARN: Type inference failed for: r16v44 */
    /* JADX WARN: Type inference failed for: r16v45 */
    /* JADX WARN: Type inference failed for: r16v46 */
    /* JADX WARN: Type inference failed for: r16v47 */
    /* JADX WARN: Type inference failed for: r16v48 */
    /* JADX WARN: Type inference failed for: r16v49 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v50 */
    /* JADX WARN: Type inference failed for: r16v51 */
    /* JADX WARN: Type inference failed for: r16v52 */
    /* JADX WARN: Type inference failed for: r16v53 */
    /* JADX WARN: Type inference failed for: r16v54 */
    /* JADX WARN: Type inference failed for: r16v55 */
    /* JADX WARN: Type inference failed for: r16v56 */
    /* JADX WARN: Type inference failed for: r16v57 */
    /* JADX WARN: Type inference failed for: r16v58 */
    /* JADX WARN: Type inference failed for: r16v59 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v60 */
    /* JADX WARN: Type inference failed for: r16v61 */
    /* JADX WARN: Type inference failed for: r16v62 */
    /* JADX WARN: Type inference failed for: r16v63 */
    /* JADX WARN: Type inference failed for: r16v64 */
    /* JADX WARN: Type inference failed for: r16v65 */
    /* JADX WARN: Type inference failed for: r16v66 */
    /* JADX WARN: Type inference failed for: r16v67 */
    /* JADX WARN: Type inference failed for: r16v68 */
    /* JADX WARN: Type inference failed for: r16v69 */
    /* JADX WARN: Type inference failed for: r16v7, types: [int] */
    /* JADX WARN: Type inference failed for: r16v70 */
    /* JADX WARN: Type inference failed for: r16v71 */
    /* JADX WARN: Type inference failed for: r16v72 */
    /* JADX WARN: Type inference failed for: r16v73 */
    /* JADX WARN: Type inference failed for: r16v74 */
    /* JADX WARN: Type inference failed for: r16v75 */
    /* JADX WARN: Type inference failed for: r16v76 */
    /* JADX WARN: Type inference failed for: r16v77 */
    /* JADX WARN: Type inference failed for: r16v78 */
    /* JADX WARN: Type inference failed for: r16v79 */
    /* JADX WARN: Type inference failed for: r16v80 */
    /* JADX WARN: Type inference failed for: r16v81 */
    /* JADX WARN: Type inference failed for: r16v82 */
    /* JADX WARN: Type inference failed for: r16v83 */
    /* JADX WARN: Type inference failed for: r16v84 */
    /* JADX WARN: Type inference failed for: r16v85 */
    /* JADX WARN: Type inference failed for: r16v86 */
    /* JADX WARN: Type inference failed for: r16v87 */
    /* JADX WARN: Type inference failed for: r16v88 */
    /* JADX WARN: Type inference failed for: r16v89 */
    /* JADX WARN: Type inference failed for: r16v90 */
    /* JADX WARN: Type inference failed for: r16v91 */
    /* JADX WARN: Type inference failed for: r16v92 */
    /* JADX WARN: Type inference failed for: r16v93 */
    /* JADX WARN: Type inference failed for: r16v94 */
    /* JADX WARN: Type inference failed for: r16v95 */
    /* JADX WARN: Type inference failed for: r16v96 */
    /* JADX WARN: Type inference failed for: r16v97 */
    /* JADX WARN: Type inference failed for: r16v98 */
    /* JADX WARN: Type inference failed for: r16v99 */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.google.protobuf.UnknownFieldSchema, com.google.protobuf.UnknownFieldSchema<UT, UB>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r19, com.google.protobuf.ExtensionSchema<ET> r20, T r21, com.google.protobuf.Reader r22, com.google.protobuf.ExtensionRegistryLite r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1952
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite) message).unknownFields = unknownFields2;
            return unknownFields2;
        }
        return unknownFields;
    }

    /* JADX INFO: renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int position3;
        int length;
        int position4 = ArrayDecoders.decodeVarint32(data, position, registers);
        int wireType = registers.int1;
        if (wireType < 0 || wireType > limit - position4) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int end = position4 + wireType;
        K key = metadata.defaultKey;
        Object obj = key;
        Object obj2 = metadata.defaultValue;
        while (position4 < end) {
            int position5 = position4 + 1;
            int tag2 = data[position4];
            if (tag2 >= 0) {
                tag = tag2;
                position2 = position5;
            } else {
                int position6 = ArrayDecoders.decodeVarint32(tag2, data, position5, registers);
                tag = registers.int1;
                position2 = position6;
            }
            int fieldNumber = tag >>> 3;
            int wireType2 = tag & 7;
            if (fieldNumber != 1) {
                if (fieldNumber != 2) {
                    position3 = position2;
                    length = wireType;
                } else if (wireType2 == metadata.valueType.getWireType()) {
                    int length2 = wireType;
                    position4 = decodeMapEntryValue(data, position2, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                    wireType = length2;
                } else {
                    position3 = position2;
                    length = wireType;
                }
                position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                wireType = length;
            } else {
                position3 = position2;
                length = wireType;
                if (wireType2 != metadata.keyType.getWireType()) {
                    position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                    wireType = length;
                } else {
                    position4 = decodeMapEntryValue(data, position3, limit, metadata.keyType, null, registers);
                    obj = registers.object1;
                    wireType = length;
                }
            }
        }
        if (position4 != end) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return end;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) UNSAFE.getObject(message, fieldOffset);
        if (list2.isModifiable()) {
            list = list2;
        } else {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(message, fieldOffset, list3);
            list = list3;
        }
        switch (fieldType) {
            case 18:
            case 35:
                Internal.ProtobufList<?> list4 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, list4, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list4, registers);
                }
                break;
            case 19:
            case 36:
                Internal.ProtobufList<?> list5 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, list5, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list5, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                Internal.ProtobufList<?> list6 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, list6, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list6, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                Internal.ProtobufList<?> list7 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, list7, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list7, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                Internal.ProtobufList<?> list8 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, list8, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list8, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                Internal.ProtobufList<?> list9 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, list9, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list9, registers);
                }
                break;
            case 25:
            case 42:
                Internal.ProtobufList<?> list10 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, list10, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list10, registers);
                }
                break;
            case 26:
                Internal.ProtobufList<?> list11 = list;
                if (wireType == 2) {
                    if ((typeAndOffset & ConnDevice.MASK_BT_DEVICE_PAIRED_TIME) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, list11, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list11, registers);
                }
                break;
            case 27:
                Internal.ProtobufList<?> list12 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list12, registers);
                }
                break;
            case 28:
                Internal.ProtobufList<?> list13 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list13, registers);
                }
                break;
            case 30:
            case 44:
                Internal.ProtobufList<?> list14 = list;
                if (wireType == 2) {
                    position2 = ArrayDecoders.decodePackedVarint32List(data, position, list14, registers);
                } else {
                    if (wireType == 0) {
                        position2 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list14, registers);
                    }
                    break;
                }
                UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
                if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFields = null;
                }
                UnknownFieldSetLite unknownFields2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(number, (List<Integer>) list14, getEnumFieldVerifier(bufferPosition), unknownFields, (UnknownFieldSchema<UT, UnknownFieldSetLite>) this.unknownFieldSchema);
                if (unknownFields2 == null) {
                    return position2;
                }
                ((GeneratedMessageLite) message).unknownFields = unknownFields2;
                return position2;
            case 33:
            case 47:
                Internal.ProtobufList<?> list15 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, list15, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list15, registers);
                }
                break;
                break;
            case 34:
            case 48:
                Internal.ProtobufList<?> list16 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, list16, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list16, registers);
                }
                break;
                break;
            case 49:
                if (wireType == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
                break;
        }
        return position;
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(message, fieldOffset);
        if (!this.mapFieldSchema.isImmutable(mapField2)) {
            mapField = mapField2;
        } else {
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, mapField2);
            unsafe.putObject(message, fieldOffset, mapField3);
            mapField = mapField3;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        int position2;
        Unsafe unsafe;
        Unsafe unsafe2 = UNSAFE;
        long oneofCaseOffset = this.buffer[bufferPosition + 2] & 1048575;
        Object object = null;
        switch (fieldType) {
            case 51:
                position2 = position;
                if (wireType == 1) {
                    unsafe2.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                    int position3 = position2 + 8;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position3;
                }
                break;
            case 52:
                position2 = position;
                if (wireType == 5) {
                    unsafe2.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                    int position4 = position2 + 4;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position4;
                }
                break;
            case 53:
            case KEYCODE_Z_VALUE:
                position2 = position;
                if (wireType == 0) {
                    int position5 = ArrayDecoders.decodeVarint64(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position5;
                }
                break;
            case KEYCODE_COMMA_VALUE:
            case KEYCODE_SPACE_VALUE:
                position2 = position;
                if (wireType == 0) {
                    int position6 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position6;
                }
                break;
            case 56:
            case KEYCODE_ENVELOPE_VALUE:
                position2 = position;
                if (wireType == 1) {
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                    int position7 = position2 + 8;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position7;
                }
                break;
            case KEYCODE_ALT_LEFT_VALUE:
            case 64:
                position2 = position;
                if (wireType == 5) {
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                    int position8 = position2 + 4;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position8;
                }
                break;
            case KEYCODE_ALT_RIGHT_VALUE:
                position2 = position;
                if (wireType == 0) {
                    int position9 = ArrayDecoders.decodeVarint64(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position9;
                }
                break;
            case KEYCODE_SHIFT_LEFT_VALUE:
                position2 = position;
                if (wireType == 2) {
                    int position10 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    int length = registers.int1;
                    if (length == 0) {
                        unsafe2.putObject(message, fieldOffset, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    } else {
                        if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, position10, position10 + length)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        String value = new String(data, position10, length, Internal.UTF_8);
                        unsafe2.putObject(message, fieldOffset, value);
                        position10 += length;
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position10;
                }
                break;
            case KEYCODE_SHIFT_RIGHT_VALUE:
                position2 = position;
                if (wireType == 2) {
                    int position11 = ArrayDecoders.decodeMessageField(getMessageFieldSchema(bufferPosition), data, position2, limit, registers);
                    if (unsafe2.getInt(message, oneofCaseOffset) == number) {
                        object = unsafe2.getObject(message, fieldOffset);
                    }
                    Object oldValue = object;
                    if (oldValue == null) {
                        unsafe2.putObject(message, fieldOffset, registers.object1);
                    } else {
                        unsafe2.putObject(message, fieldOffset, Internal.mergeMessage(oldValue, registers.object1));
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position11;
                }
                break;
            case KEYCODE_TAB_VALUE:
                position2 = position;
                if (wireType == 2) {
                    int position12 = ArrayDecoders.decodeBytes(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, registers.object1);
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position12;
                }
                break;
            case 63:
                position2 = position;
                if (wireType == 0) {
                    int position13 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    int enumValue = registers.int1;
                    Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
                    if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                        unsafe = unsafe2;
                        unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
                        unsafe.putInt(message, oneofCaseOffset, number);
                        return position13;
                    }
                    unsafe = unsafe2;
                    getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
                    return position13;
                }
                break;
            case KEYCODE_ENTER_VALUE:
                position2 = position;
                if (wireType == 0) {
                    int position14 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position14;
                }
                break;
            case KEYCODE_DEL_VALUE:
                if (wireType == 0) {
                    int position15 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position15;
                }
                position2 = position;
                break;
            case KEYCODE_GRAVE_VALUE:
                if (wireType == 3) {
                    int endTag = (tag & (-8)) | 4;
                    int position16 = ArrayDecoders.decodeGroupField(getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
                    if (unsafe2.getInt(message, oneofCaseOffset) == number) {
                        object = unsafe2.getObject(message, fieldOffset);
                    }
                    Object oldValue2 = object;
                    if (oldValue2 == null) {
                        unsafe2.putObject(message, fieldOffset, registers.object1);
                    } else {
                        unsafe2.putObject(message, fieldOffset, Internal.mergeMessage(oldValue2, registers.object1));
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position16;
                }
                position2 = position;
                break;
            default:
                position2 = position;
                break;
        }
        return position2;
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0094. Please report as an issue. */
    int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i;
        MessageSchema<T> messageSchema;
        T t;
        int tag;
        int position2;
        int pos;
        int tag2;
        int pos2;
        int position3;
        int currentPresenceField;
        ArrayDecoders.Registers registers2;
        int position4;
        int position5;
        int currentPresenceFieldOffset;
        int currentPresenceFieldOffset2;
        int pos3;
        Unsafe unsafe2;
        int tag3;
        MessageSchema<T> messageSchema2 = this;
        T t2 = message;
        byte[] bArr = data;
        int position6 = limit;
        int i2 = endGroup;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe3 = UNSAFE;
        int tag4 = 0;
        int oldNumber = -1;
        int pos4 = 0;
        int currentPresenceFieldOffset3 = 1048575;
        int currentPresenceField2 = 0;
        int position7 = position;
        while (true) {
            if (position7 < position6) {
                int position8 = position7 + 1;
                int tag5 = bArr[position7];
                if (tag5 >= 0) {
                    tag = tag5;
                    position2 = position8;
                } else {
                    int position9 = ArrayDecoders.decodeVarint32(tag5, bArr, position8, registers3);
                    int tag6 = registers3.int1;
                    tag = tag6;
                    position2 = position9;
                }
                int position10 = tag >>> 3;
                int wireType = tag & 7;
                if (position10 > oldNumber) {
                    pos = messageSchema2.positionForFieldNumber(position10, pos4 / 3);
                } else {
                    int pos5 = messageSchema2.positionForFieldNumber(position10);
                    pos = pos5;
                }
                if (pos == -1) {
                    tag2 = tag;
                    pos2 = 0;
                    position3 = position2;
                    currentPresenceField = currentPresenceField2;
                    unsafe = unsafe3;
                } else {
                    int typeAndOffset = messageSchema2.buffer[pos + 1];
                    int fieldType = type(typeAndOffset);
                    long fieldOffset = offset(typeAndOffset);
                    int tag7 = tag;
                    if (fieldType <= 17) {
                        int presenceMaskAndOffset = messageSchema2.buffer[pos + 2];
                        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                        int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                        if (presenceFieldOffset == currentPresenceFieldOffset3) {
                            position4 = position2;
                            position5 = fieldType;
                        } else {
                            if (currentPresenceFieldOffset3 == 1048575) {
                                position4 = position2;
                            } else {
                                position4 = position2;
                                unsafe3.putInt(t2, currentPresenceFieldOffset3, currentPresenceField2);
                            }
                            position5 = fieldType;
                            currentPresenceField2 = unsafe3.getInt(t2, presenceFieldOffset);
                            currentPresenceFieldOffset3 = presenceFieldOffset;
                        }
                        switch (position5) {
                            case 0:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 == 1) {
                                    UnsafeUtil.putDouble(t2, fieldOffset, ArrayDecoders.decodeDouble(bArr, position3));
                                    position7 = position3 + 8;
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    pos4 = pos3;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    messageSchema2 = this;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 1:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 == 5) {
                                    UnsafeUtil.putFloat(t2, fieldOffset, ArrayDecoders.decodeFloat(bArr, position3));
                                    position7 = position3 + 4;
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    pos4 = pos3;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    messageSchema2 = this;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 2:
                            case 3:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 == 0) {
                                    int position11 = ArrayDecoders.decodeVarint64(bArr, position3, registers3);
                                    unsafe2.putLong(message, fieldOffset, registers3.long1);
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    position7 = position11;
                                    pos4 = pos3;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    messageSchema2 = this;
                                    position6 = limit;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 4:
                            case 11:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 == 0) {
                                    position7 = ArrayDecoders.decodeVarint32(bArr, position3, registers3);
                                    unsafe2.putInt(t2, fieldOffset, registers3.int1);
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    pos4 = pos3;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    messageSchema2 = this;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 5:
                            case 14:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe4 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                int fieldType2 = pos;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 != 1) {
                                    pos3 = fieldType2;
                                    tag2 = tag7;
                                    unsafe2 = unsafe4;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    unsafe4.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(bArr, position3));
                                    position7 = position3 + 8;
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag7;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe4;
                                    pos4 = fieldType2;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    messageSchema2 = this;
                                }
                                break;
                            case 6:
                            case 13:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe5 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                int fieldType3 = pos;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 != 5) {
                                    pos3 = fieldType3;
                                    tag2 = tag7;
                                    unsafe2 = unsafe5;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    unsafe5.putInt(t2, fieldOffset, ArrayDecoders.decodeFixed32(bArr, position3));
                                    position7 = position3 + 4;
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    position6 = limit;
                                    unsafe3 = unsafe5;
                                    pos4 = fieldType3;
                                    tag4 = tag7;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 7:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe6 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                int fieldType4 = pos;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 != 0) {
                                    pos3 = fieldType4;
                                    tag2 = tag7;
                                    unsafe2 = unsafe6;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    position7 = ArrayDecoders.decodeVarint64(bArr, position3, registers3);
                                    UnsafeUtil.putBoolean(t2, fieldOffset, registers3.long1 != 0);
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    position6 = limit;
                                    unsafe3 = unsafe6;
                                    pos4 = fieldType4;
                                    tag4 = tag7;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 8:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe7 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                int fieldType5 = pos;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 != 2) {
                                    pos3 = fieldType5;
                                    tag2 = tag7;
                                    unsafe2 = unsafe7;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    if ((typeAndOffset & ENFORCE_UTF8_MASK) == 0) {
                                        position7 = ArrayDecoders.decodeString(bArr, position3, registers3);
                                    } else {
                                        position7 = ArrayDecoders.decodeStringRequireUtf8(bArr, position3, registers3);
                                    }
                                    unsafe7.putObject(t2, fieldOffset, registers3.object1);
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    position6 = limit;
                                    unsafe3 = unsafe7;
                                    pos4 = fieldType5;
                                    tag4 = tag7;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 9:
                                int pos6 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe8 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 == 2) {
                                    position7 = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(pos6), bArr, position3, limit, registers3);
                                    if ((currentPresenceField2 & presenceMask) == 0) {
                                        unsafe8.putObject(t2, fieldOffset, registers3.object1);
                                    } else {
                                        unsafe8.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe8.getObject(t2, fieldOffset), registers3.object1));
                                    }
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    position6 = limit;
                                    unsafe3 = unsafe8;
                                    pos4 = pos6;
                                    tag4 = tag7;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    pos3 = pos6;
                                    tag2 = tag7;
                                    unsafe2 = unsafe8;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 10:
                                int pos7 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe9 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                if (currentPresenceFieldOffset2 != 2) {
                                    tag2 = tag7;
                                    pos3 = pos7;
                                    unsafe2 = unsafe9;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    position7 = ArrayDecoders.decodeBytes(bArr, position3, registers3);
                                    unsafe9.putObject(t2, fieldOffset, registers3.object1);
                                    currentPresenceField2 |= presenceMask;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe9;
                                    tag4 = tag7;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos7;
                                }
                                break;
                            case 12:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe10 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 != 0) {
                                    pos3 = pos;
                                    unsafe2 = unsafe10;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    position7 = ArrayDecoders.decodeVarint32(bArr, position3, registers3);
                                    int enumValue = registers3.int1;
                                    Internal.EnumVerifier enumVerifier = messageSchema2.getEnumFieldVerifier(pos);
                                    if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                                        unsafe10.putInt(t2, fieldOffset, enumValue);
                                        currentPresenceField2 |= presenceMask;
                                        position6 = limit;
                                        i2 = endGroup;
                                        unsafe3 = unsafe10;
                                        tag4 = tag2;
                                        oldNumber = position10;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                        pos4 = pos;
                                    } else {
                                        getMutableUnknownFields(message).storeField(tag2, Long.valueOf(enumValue));
                                        position6 = limit;
                                        i2 = endGroup;
                                        unsafe3 = unsafe10;
                                        tag4 = tag2;
                                        oldNumber = position10;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                        pos4 = pos;
                                    }
                                }
                                break;
                            case 15:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe11 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 != 0) {
                                    pos3 = pos;
                                    unsafe2 = unsafe11;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    position7 = ArrayDecoders.decodeVarint32(bArr, position3, registers3);
                                    unsafe11.putInt(t2, fieldOffset, CodedInputStream.decodeZigZag32(registers3.int1));
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    position6 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe11;
                                    pos4 = pos;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            case 16:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                if (currentPresenceFieldOffset2 != 0) {
                                    pos3 = pos;
                                    unsafe2 = unsafe3;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                } else {
                                    bArr = data;
                                    int position12 = ArrayDecoders.decodeVarint64(bArr, position3, registers3);
                                    unsafe3.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers3.long1));
                                    currentPresenceField2 |= presenceMask;
                                    tag4 = tag2;
                                    i2 = endGroup;
                                    pos4 = pos;
                                    position7 = position12;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    unsafe3 = unsafe3;
                                    position6 = limit;
                                }
                                break;
                            case 17:
                                if (wireType == 3) {
                                    int endTag = (position10 << 3) | 4;
                                    int currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                    position7 = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(pos), data, position4, limit, endTag, registers);
                                    if ((currentPresenceField2 & presenceMask) == 0) {
                                        unsafe3.putObject(t2, fieldOffset, registers3.object1);
                                    } else {
                                        unsafe3.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe3.getObject(t2, fieldOffset), registers3.object1));
                                    }
                                    currentPresenceField2 |= presenceMask;
                                    bArr = data;
                                    tag4 = tag7;
                                    position6 = limit;
                                    i2 = endGroup;
                                    pos4 = pos;
                                    oldNumber = position10;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset4;
                                } else {
                                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                    currentPresenceFieldOffset2 = wireType;
                                    position3 = position4;
                                    tag2 = tag7;
                                    pos3 = pos;
                                    unsafe2 = unsafe3;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    pos2 = pos3;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                                break;
                            default:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = position4;
                                tag2 = tag7;
                                unsafe = unsafe2;
                                currentPresenceField = currentPresenceField2;
                                pos2 = pos3;
                                currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                break;
                        }
                    } else {
                        int position13 = position2;
                        int pos8 = pos;
                        int currentPresenceFieldOffset5 = currentPresenceFieldOffset3;
                        Unsafe unsafe12 = unsafe3;
                        tag2 = tag7;
                        if (fieldType != 27) {
                            pos2 = pos8;
                            if (fieldType <= 49) {
                                currentPresenceField = currentPresenceField2;
                                unsafe = unsafe12;
                                tag2 = tag2;
                                position7 = parseRepeatedField(message, data, position13, limit, tag2, position10, wireType, pos2, typeAndOffset, fieldType, fieldOffset, registers);
                                if (position7 != position13) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr = data;
                                    tag4 = tag2;
                                    position6 = limit;
                                    i2 = endGroup;
                                    registers3 = registers;
                                    oldNumber = position10;
                                    currentPresenceField2 = currentPresenceField;
                                    pos4 = pos2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    unsafe3 = unsafe;
                                } else {
                                    position3 = position7;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                }
                            } else {
                                currentPresenceField = currentPresenceField2;
                                unsafe = unsafe12;
                                tag2 = tag2;
                                tag3 = position13;
                                if (fieldType == 50) {
                                    if (wireType == 2) {
                                        position7 = parseMapField(message, data, tag3, limit, pos2, fieldOffset, registers);
                                        if (position7 != tag3) {
                                            messageSchema2 = this;
                                            t2 = message;
                                            bArr = data;
                                            tag4 = tag2;
                                            position6 = limit;
                                            i2 = endGroup;
                                            registers3 = registers;
                                            oldNumber = position10;
                                            currentPresenceField2 = currentPresenceField;
                                            pos4 = pos2;
                                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                            unsafe3 = unsafe;
                                        } else {
                                            position3 = position7;
                                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                        }
                                    } else {
                                        position3 = tag3;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    }
                                } else {
                                    position7 = parseOneofField(message, data, tag3, limit, tag2, position10, wireType, typeAndOffset, fieldType, fieldOffset, pos2, registers);
                                    if (position7 == tag3) {
                                        position3 = position7;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    } else {
                                        messageSchema2 = this;
                                        t2 = message;
                                        bArr = data;
                                        tag4 = tag2;
                                        position6 = limit;
                                        i2 = endGroup;
                                        registers3 = registers;
                                        oldNumber = position10;
                                        currentPresenceField2 = currentPresenceField;
                                        pos4 = pos2;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                        unsafe3 = unsafe;
                                    }
                                }
                            }
                        } else if (wireType == 2) {
                            Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe12.getObject(t2, fieldOffset);
                            if (!list.isModifiable()) {
                                int size = list.size();
                                list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe12.putObject(t2, fieldOffset, list);
                            }
                            messageSchema2 = this;
                            position7 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(pos8), tag2, data, position13, limit, list, registers);
                            bArr = data;
                            position6 = limit;
                            i2 = endGroup;
                            oldNumber = position10;
                            unsafe3 = unsafe12;
                            tag4 = tag2;
                            pos4 = pos8;
                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                        } else {
                            pos2 = pos8;
                            currentPresenceField = currentPresenceField2;
                            unsafe = unsafe12;
                            tag3 = position13;
                            position3 = tag3;
                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                        }
                    }
                }
                int tag8 = tag2;
                i = endGroup;
                if (tag8 == i && i != 0) {
                    messageSchema = this;
                    tag4 = tag8;
                    position7 = position3;
                    currentPresenceField2 = currentPresenceField;
                } else {
                    if (!this.hasExtensions) {
                        registers2 = registers;
                    } else {
                        registers2 = registers;
                        if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                            position7 = ArrayDecoders.decodeExtensionOrUnknownField(tag8, data, position3, limit, message, this.defaultInstance, this.unknownFieldSchema, registers);
                        }
                        t2 = message;
                        position6 = limit;
                        tag4 = tag8;
                        messageSchema2 = this;
                        oldNumber = position10;
                        currentPresenceField2 = currentPresenceField;
                        pos4 = pos2;
                        unsafe3 = unsafe;
                        i2 = i;
                        registers3 = registers2;
                        bArr = data;
                    }
                    position7 = ArrayDecoders.decodeUnknownField(tag8, data, position3, limit, getMutableUnknownFields(message), registers);
                    t2 = message;
                    position6 = limit;
                    tag4 = tag8;
                    messageSchema2 = this;
                    oldNumber = position10;
                    currentPresenceField2 = currentPresenceField;
                    pos4 = pos2;
                    unsafe3 = unsafe;
                    i2 = i;
                    registers3 = registers2;
                    bArr = data;
                }
            } else {
                unsafe = unsafe3;
                i = i2;
                messageSchema = messageSchema2;
            }
        }
        if (currentPresenceFieldOffset3 == 1048575) {
            t = message;
        } else {
            t = message;
            unsafe.putInt(t, currentPresenceFieldOffset3, currentPresenceField2);
        }
        UnknownFieldSetLite unknownFields = null;
        for (int i3 = messageSchema.checkInitializedCount; i3 < messageSchema.repeatedFieldOffsetStart; i3++) {
            unknownFields = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t, messageSchema.intArray[i3], unknownFields, messageSchema.unknownFieldSchema);
        }
        if (unknownFields != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t, unknownFields);
        }
        if (i == 0) {
            if (position7 != limit) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (position7 > limit || tag4 != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position7;
    }

    private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int pos;
        int currentPresenceFieldOffset;
        Unsafe unsafe;
        int typeAndOffset;
        int typeAndOffset2;
        int currentPresenceFieldOffset2;
        int position3;
        int presenceFieldOffset;
        int currentPresenceField;
        int position4;
        int currentPresenceFieldOffset3;
        MessageSchema<T> messageSchema = this;
        T t = message;
        byte[] bArr = data;
        int position5 = limit;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int oldNumber = -1;
        int pos2 = 0;
        int currentPresenceFieldOffset4 = 1048575;
        int currentPresenceField2 = 0;
        int position6 = position;
        while (position6 < position5) {
            int position7 = position6 + 1;
            int tag2 = bArr[position6];
            if (tag2 >= 0) {
                tag = tag2;
                position2 = position7;
            } else {
                int position8 = ArrayDecoders.decodeVarint32(tag2, bArr, position7, registers2);
                tag = registers2.int1;
                position2 = position8;
            }
            int number = tag >>> 3;
            int wireType = tag & 7;
            if (number > oldNumber) {
                pos = messageSchema.positionForFieldNumber(number, pos2 / 3);
            } else {
                int pos3 = messageSchema.positionForFieldNumber(number);
                pos = pos3;
            }
            if (pos == -1) {
                currentPresenceFieldOffset = 0;
                unsafe = unsafe2;
            } else {
                int typeAndOffset3 = messageSchema.buffer[pos + 1];
                int fieldType = type(typeAndOffset3);
                int position9 = position2;
                long fieldOffset = offset(typeAndOffset3);
                if (fieldType <= 17) {
                    int presenceMaskAndOffset = messageSchema.buffer[pos + 2];
                    int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                    int presenceFieldOffset2 = presenceMaskAndOffset & 1048575;
                    if (presenceFieldOffset2 == currentPresenceFieldOffset4) {
                        typeAndOffset = typeAndOffset3;
                    } else {
                        if (currentPresenceFieldOffset4 == 1048575) {
                            typeAndOffset = typeAndOffset3;
                        } else {
                            typeAndOffset = typeAndOffset3;
                            unsafe2.putInt(t, currentPresenceFieldOffset4, currentPresenceField2);
                        }
                        if (presenceFieldOffset2 != 1048575) {
                            currentPresenceField2 = unsafe2.getInt(t, presenceFieldOffset2);
                        }
                        currentPresenceFieldOffset4 = presenceFieldOffset2;
                    }
                    switch (fieldType) {
                        case 0:
                            presenceFieldOffset = position9;
                            position3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 == 1) {
                                UnsafeUtil.putDouble(t, fieldOffset, ArrayDecoders.decodeDouble(bArr, presenceFieldOffset));
                                position6 = presenceFieldOffset + 8;
                                currentPresenceField2 |= presenceMask;
                                pos2 = position3;
                                position5 = limit;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                            } else {
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            }
                            break;
                        case 1:
                            presenceFieldOffset = position9;
                            position3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 == 5) {
                                UnsafeUtil.putFloat(t, fieldOffset, ArrayDecoders.decodeFloat(bArr, presenceFieldOffset));
                                position6 = presenceFieldOffset + 4;
                                currentPresenceField2 |= presenceMask;
                                pos2 = position3;
                                position5 = limit;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                            } else {
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            }
                            break;
                        case 2:
                        case 3:
                            presenceFieldOffset = position9;
                            position3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 == 0) {
                                int position10 = ArrayDecoders.decodeVarint64(bArr, presenceFieldOffset, registers2);
                                unsafe2.putLong(message, fieldOffset, registers2.long1);
                                currentPresenceField2 |= presenceMask;
                                pos2 = position3;
                                position6 = position10;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                            } else {
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            }
                            break;
                        case 4:
                        case 11:
                            presenceFieldOffset = position9;
                            position3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 == 0) {
                                position6 = ArrayDecoders.decodeVarint32(bArr, presenceFieldOffset, registers2);
                                unsafe2.putInt(t, fieldOffset, registers2.int1);
                                currentPresenceField2 |= presenceMask;
                                pos2 = position3;
                                position5 = limit;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                            } else {
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            }
                            break;
                        case 5:
                        case 14:
                            int presenceFieldOffset3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 1) {
                                position3 = presenceFieldOffset3;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                unsafe2.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(bArr, position9));
                                position6 = position9 + 8;
                                currentPresenceField2 |= presenceMask;
                                pos2 = presenceFieldOffset3;
                                position5 = limit;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                            }
                            break;
                        case 6:
                        case 13:
                            int presenceFieldOffset4 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 5) {
                                position3 = presenceFieldOffset4;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                unsafe2.putInt(t, fieldOffset, ArrayDecoders.decodeFixed32(bArr, position9));
                                position6 = position9 + 4;
                                currentPresenceField2 |= presenceMask;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                                pos2 = presenceFieldOffset4;
                            }
                            break;
                        case 7:
                            int presenceFieldOffset5 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 0) {
                                position3 = presenceFieldOffset5;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                position6 = ArrayDecoders.decodeVarint64(bArr, position9, registers2);
                                UnsafeUtil.putBoolean(t, fieldOffset, registers2.long1 != 0);
                                currentPresenceField2 |= presenceMask;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                                pos2 = presenceFieldOffset5;
                            }
                            break;
                        case 8:
                            int typeAndOffset4 = typeAndOffset;
                            int presenceFieldOffset6 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 2) {
                                position3 = presenceFieldOffset6;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                if ((typeAndOffset4 & ENFORCE_UTF8_MASK) == 0) {
                                    position6 = ArrayDecoders.decodeString(bArr, position9, registers2);
                                } else {
                                    position6 = ArrayDecoders.decodeStringRequireUtf8(bArr, position9, registers2);
                                }
                                unsafe2.putObject(t, fieldOffset, registers2.object1);
                                currentPresenceField2 |= presenceMask;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                                pos2 = presenceFieldOffset6;
                            }
                            break;
                        case 9:
                            int presenceFieldOffset7 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 == 2) {
                                position6 = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(presenceFieldOffset7), bArr, position9, limit, registers2);
                                Object oldValue = unsafe2.getObject(t, fieldOffset);
                                if (oldValue == null) {
                                    unsafe2.putObject(t, fieldOffset, registers2.object1);
                                } else {
                                    unsafe2.putObject(t, fieldOffset, Internal.mergeMessage(oldValue, registers2.object1));
                                }
                                currentPresenceField2 |= presenceMask;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                                pos2 = presenceFieldOffset7;
                            } else {
                                position3 = presenceFieldOffset7;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            }
                            break;
                        case 10:
                            int presenceFieldOffset8 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 2) {
                                position3 = presenceFieldOffset8;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                position6 = ArrayDecoders.decodeBytes(bArr, position9, registers2);
                                unsafe2.putObject(t, fieldOffset, registers2.object1);
                                currentPresenceField2 |= presenceMask;
                                pos2 = presenceFieldOffset8;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                            }
                            break;
                        case 12:
                            int presenceFieldOffset9 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 0) {
                                position3 = presenceFieldOffset9;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                position6 = ArrayDecoders.decodeVarint32(bArr, position9, registers2);
                                unsafe2.putInt(t, fieldOffset, registers2.int1);
                                currentPresenceField2 |= presenceMask;
                                pos2 = presenceFieldOffset9;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                            }
                            break;
                        case 15:
                            int presenceFieldOffset10 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            if (currentPresenceFieldOffset2 != 0) {
                                position3 = presenceFieldOffset10;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                position6 = ArrayDecoders.decodeVarint32(bArr, position9, registers2);
                                unsafe2.putInt(t, fieldOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                currentPresenceField2 |= presenceMask;
                                pos2 = presenceFieldOffset10;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                position5 = limit;
                            }
                            break;
                        case 16:
                            if (wireType != 0) {
                                typeAndOffset2 = currentPresenceFieldOffset4;
                                currentPresenceFieldOffset2 = wireType;
                                position3 = pos;
                                presenceFieldOffset = position9;
                                unsafe = unsafe2;
                                position2 = presenceFieldOffset;
                                currentPresenceFieldOffset4 = typeAndOffset2;
                                currentPresenceFieldOffset = position3;
                            } else {
                                int position11 = ArrayDecoders.decodeVarint64(bArr, position9, registers2);
                                unsafe2.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                currentPresenceField2 |= presenceMask;
                                pos2 = pos;
                                oldNumber = number;
                                currentPresenceFieldOffset4 = currentPresenceFieldOffset4;
                                position6 = position11;
                                position5 = limit;
                            }
                            break;
                        default:
                            presenceFieldOffset = position9;
                            position3 = pos;
                            typeAndOffset2 = currentPresenceFieldOffset4;
                            currentPresenceFieldOffset2 = wireType;
                            unsafe = unsafe2;
                            position2 = presenceFieldOffset;
                            currentPresenceFieldOffset4 = typeAndOffset2;
                            currentPresenceFieldOffset = position3;
                            break;
                    }
                } else {
                    int position12 = pos;
                    int currentPresenceFieldOffset5 = currentPresenceFieldOffset4;
                    if (fieldType != 27) {
                        Unsafe unsafe3 = unsafe2;
                        if (fieldType <= 49) {
                            int currentPresenceField3 = currentPresenceField2;
                            unsafe = unsafe3;
                            currentPresenceFieldOffset = position12;
                            position6 = parseRepeatedField(message, data, position9, limit, tag, number, wireType, position12, typeAndOffset3, fieldType, fieldOffset, registers);
                            if (position6 != position9) {
                                messageSchema = this;
                                t = message;
                                bArr = data;
                                position5 = limit;
                                registers2 = registers;
                                oldNumber = number;
                                pos2 = currentPresenceFieldOffset;
                                currentPresenceFieldOffset4 = currentPresenceFieldOffset5;
                                currentPresenceField2 = currentPresenceField3;
                                unsafe2 = unsafe;
                            } else {
                                position2 = position6;
                                currentPresenceFieldOffset4 = currentPresenceFieldOffset5;
                                currentPresenceField2 = currentPresenceField3;
                            }
                        } else {
                            currentPresenceField = currentPresenceField2;
                            position4 = position9;
                            unsafe = unsafe3;
                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                            currentPresenceFieldOffset = position12;
                            if (fieldType == 50) {
                                if (wireType == 2) {
                                    position6 = parseMapField(message, data, position4, limit, currentPresenceFieldOffset, fieldOffset, registers);
                                    if (position6 != position4) {
                                        messageSchema = this;
                                        t = message;
                                        bArr = data;
                                        position5 = limit;
                                        registers2 = registers;
                                        oldNumber = number;
                                        pos2 = currentPresenceFieldOffset;
                                        currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                        currentPresenceField2 = currentPresenceField;
                                        unsafe2 = unsafe;
                                    } else {
                                        position2 = position6;
                                        currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                        currentPresenceField2 = currentPresenceField;
                                    }
                                } else {
                                    currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                    position2 = position4;
                                    currentPresenceField2 = currentPresenceField;
                                }
                            } else {
                                position6 = parseOneofField(message, data, position4, limit, tag, number, wireType, typeAndOffset3, fieldType, fieldOffset, currentPresenceFieldOffset, registers);
                                if (position6 == position4) {
                                    position2 = position6;
                                    currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                    currentPresenceField2 = currentPresenceField;
                                } else {
                                    messageSchema = this;
                                    t = message;
                                    bArr = data;
                                    position5 = limit;
                                    registers2 = registers;
                                    oldNumber = number;
                                    pos2 = currentPresenceFieldOffset;
                                    currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                    currentPresenceField2 = currentPresenceField;
                                    unsafe2 = unsafe;
                                }
                            }
                        }
                    } else if (wireType != 2) {
                        currentPresenceField = currentPresenceField2;
                        position4 = position9;
                        unsafe = unsafe2;
                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                        currentPresenceFieldOffset = position12;
                        currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                        position2 = position4;
                        currentPresenceField2 = currentPresenceField;
                    } else {
                        Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe2.getObject(t, fieldOffset);
                        if (!list.isModifiable()) {
                            int size = list.size();
                            list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe2.putObject(t, fieldOffset, list);
                        }
                        position6 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(position12), tag, data, position9, limit, list, registers);
                        position5 = limit;
                        oldNumber = number;
                        currentPresenceFieldOffset4 = currentPresenceFieldOffset5;
                        pos2 = position12;
                        unsafe2 = unsafe2;
                    }
                }
            }
            position6 = ArrayDecoders.decodeUnknownField(tag, data, position2, limit, getMutableUnknownFields(message), registers);
            messageSchema = this;
            t = message;
            bArr = data;
            position5 = limit;
            registers2 = registers;
            oldNumber = number;
            pos2 = currentPresenceFieldOffset;
            unsafe2 = unsafe;
        }
        int currentPresenceField4 = currentPresenceField2;
        Unsafe unsafe4 = unsafe2;
        if (currentPresenceFieldOffset4 != 1048575) {
            unsafe4.putInt(message, currentPresenceFieldOffset4, currentPresenceField4);
        }
        if (position6 != limit) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position6;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(T message) {
        for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
            long offset = offset(typeAndOffsetAt(this.intArray[i]));
            Object mapField = UnsafeUtil.getObject(message, offset);
            if (mapField != null) {
                UnsafeUtil.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField));
            }
        }
        int length = this.intArray.length;
        for (int i2 = this.repeatedFieldOffsetStart; i2 < length; i2++) {
            this.listFieldSchema.makeImmutableListAt(message, this.intArray[i2]);
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        if (mapField == null) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (this.mapFieldSchema.isImmutable(mapField)) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, mapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) {
            return ub;
        }
        return (UB) filterUnknownEnumMap(i, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0095  */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInitialized(T r19) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.isInitialized(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
        return schema.isInitialized(nested);
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            N nested = list.get(i);
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.google.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            SchemaFor = SchemaFor;
            if (SchemaFor == 0) {
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        long offset = offset(typeAndOffset);
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static long offset(int value) {
        return 1048575 & value;
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = presenceMaskAndOffset & 1048575;
        if (presenceFieldOffset == 1048575) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    return UnsafeUtil.getDouble(message, offset) != 0.0d;
                case 1:
                    return UnsafeUtil.getFloat(message, offset) != 0.0f;
                case 2:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject(message, offset);
                    if (value instanceof String) {
                        return !((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return !ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(message, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                case 11:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject(message, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        return (UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) & presenceMask) != 0;
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = 1048575 & presenceMaskAndOffset;
        if (presenceFieldOffset == 1048575) {
            return;
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        UnsafeUtil.putInt(message, presenceFieldOffset, UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffset & 1048575)) == UnsafeUtil.getInt(other, (long) (1048575 & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        UnsafeUtil.putInt(message, 1048575 & presenceMaskAndOffset, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, 0);
        }
        return -1;
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, min);
        }
        return -1;
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}

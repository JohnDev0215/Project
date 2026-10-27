package io.objectbox.converter;

import io.objectbox.flatbuffers.ArrayReadWriteBuf;
import io.objectbox.flatbuffers.FlexBuffers;
import io.objectbox.flatbuffers.FlexBuffersBuilder;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public class FlexObjectConverter implements PropertyConverter<Object, byte[]> {
    private static final AtomicReference<FlexBuffersBuilder> cachedBuilder = new AtomicReference<>();

    Object convertToKey(String str) {
        return str;
    }

    @Override // io.objectbox.converter.PropertyConverter
    public byte[] convertToDatabaseValue(Object obj) {
        if (obj == null) {
            return null;
        }
        FlexBuffersBuilder andSet = cachedBuilder.getAndSet(null);
        if (andSet == null) {
            andSet = new FlexBuffersBuilder(new ArrayReadWriteBuf(512), 3);
        }
        addValue(andSet, obj);
        ByteBuffer byteBufferFinish = andSet.finish();
        byte[] bArr = new byte[byteBufferFinish.limit()];
        byteBufferFinish.get(bArr);
        if (byteBufferFinish.limit() <= 262144) {
            andSet.clear();
            cachedBuilder.getAndSet(andSet);
        }
        return bArr;
    }

    private void addValue(FlexBuffersBuilder flexBuffersBuilder, Object obj) {
        if (obj instanceof Map) {
            addMap(flexBuffersBuilder, null, (Map) obj);
            return;
        }
        if (obj instanceof List) {
            addVector(flexBuffersBuilder, null, (List) obj);
            return;
        }
        if (obj instanceof String) {
            flexBuffersBuilder.putString((String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            flexBuffersBuilder.putBoolean(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Byte) {
            flexBuffersBuilder.putInt(((Byte) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            flexBuffersBuilder.putInt(((Short) obj).intValue());
            return;
        }
        if (obj instanceof Integer) {
            flexBuffersBuilder.putInt(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            flexBuffersBuilder.putInt(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Float) {
            flexBuffersBuilder.putFloat(((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            flexBuffersBuilder.putFloat(((Double) obj).doubleValue());
        } else {
            if (obj instanceof byte[]) {
                flexBuffersBuilder.putBlob((byte[]) obj);
                return;
            }
            throw new IllegalArgumentException("Values of this type are not supported: " + obj.getClass().getSimpleName());
        }
    }

    protected void checkMapKeyType(Object obj) {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Map keys must be String");
        }
    }

    private void addMap(FlexBuffersBuilder flexBuffersBuilder, String str, Map<Object, Object> map) {
        int iStartMap = flexBuffersBuilder.startMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || value == null) {
                throw new IllegalArgumentException("Map keys or values must not be null");
            }
            checkMapKeyType(key);
            String string = key.toString();
            if (value instanceof Map) {
                addMap(flexBuffersBuilder, string, (Map) value);
            } else if (value instanceof List) {
                addVector(flexBuffersBuilder, string, (List) value);
            } else if (value instanceof String) {
                flexBuffersBuilder.putString(string, (String) value);
            } else if (value instanceof Boolean) {
                flexBuffersBuilder.putBoolean(string, ((Boolean) value).booleanValue());
            } else if (value instanceof Byte) {
                flexBuffersBuilder.putInt(string, ((Byte) value).intValue());
            } else if (value instanceof Short) {
                flexBuffersBuilder.putInt(string, ((Short) value).intValue());
            } else if (value instanceof Integer) {
                flexBuffersBuilder.putInt(string, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                flexBuffersBuilder.putInt(string, ((Long) value).longValue());
            } else if (value instanceof Float) {
                flexBuffersBuilder.putFloat(string, ((Float) value).floatValue());
            } else if (value instanceof Double) {
                flexBuffersBuilder.putFloat(string, ((Double) value).doubleValue());
            } else if (value instanceof byte[]) {
                flexBuffersBuilder.putBlob(string, (byte[]) value);
            } else {
                throw new IllegalArgumentException("Map values of this type are not supported: " + value.getClass().getSimpleName());
            }
        }
        flexBuffersBuilder.endMap(str, iStartMap);
    }

    private void addVector(FlexBuffersBuilder flexBuffersBuilder, String str, List<Object> list) {
        int iStartVector = flexBuffersBuilder.startVector();
        for (Object obj : list) {
            if (obj == null) {
                throw new IllegalArgumentException("List elements must not be null");
            }
            if (obj instanceof Map) {
                addMap(flexBuffersBuilder, null, (Map) obj);
            } else if (obj instanceof List) {
                addVector(flexBuffersBuilder, null, (List) obj);
            } else if (obj instanceof String) {
                flexBuffersBuilder.putString((String) obj);
            } else if (obj instanceof Boolean) {
                flexBuffersBuilder.putBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                flexBuffersBuilder.putInt(((Byte) obj).intValue());
            } else if (obj instanceof Short) {
                flexBuffersBuilder.putInt(((Short) obj).intValue());
            } else if (obj instanceof Integer) {
                flexBuffersBuilder.putInt(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                flexBuffersBuilder.putInt(((Long) obj).longValue());
            } else if (obj instanceof Float) {
                flexBuffersBuilder.putFloat(((Float) obj).floatValue());
            } else if (obj instanceof Double) {
                flexBuffersBuilder.putFloat(((Double) obj).doubleValue());
            } else if (obj instanceof byte[]) {
                flexBuffersBuilder.putBlob((byte[]) obj);
            } else {
                throw new IllegalArgumentException("List values of this type are not supported: " + obj.getClass().getSimpleName());
            }
        }
        flexBuffersBuilder.endVector(str, iStartVector, false, false);
    }

    @Override // io.objectbox.converter.PropertyConverter
    public Object convertToEntityProperty(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        FlexBuffers.Reference root = FlexBuffers.getRoot(new ArrayReadWriteBuf(bArr, bArr.length));
        if (root.isMap()) {
            return buildMap(root.asMap());
        }
        if (root.isVector()) {
            return buildList(root.asVector());
        }
        if (root.isString()) {
            return root.asString();
        }
        if (root.isBoolean()) {
            return Boolean.valueOf(root.asBoolean());
        }
        if (root.isInt()) {
            if (shouldRestoreAsLong(root)) {
                return Long.valueOf(root.asLong());
            }
            return Integer.valueOf(root.asInt());
        }
        if (root.isFloat()) {
            return Double.valueOf(root.asFloat());
        }
        if (root.isBlob()) {
            return root.asBlob().getBytes();
        }
        throw new IllegalArgumentException("FlexBuffers type is not supported: " + root.getType());
    }

    protected boolean shouldRestoreAsLong(FlexBuffers.Reference reference) {
        try {
            Field declaredField = reference.getClass().getDeclaredField("parentWidth");
            declaredField.setAccessible(true);
            return ((Integer) declaredField.get(reference)).intValue() == 8;
        } catch (Exception e) {
            throw new RuntimeException("FlexMapConverter could not determine FlexBuffers integer bit width.", e);
        }
    }

    private Map<Object, Object> buildMap(FlexBuffers.Map map) {
        int size = map.size();
        FlexBuffers.KeyVector keyVectorKeys = map.keys();
        FlexBuffers.Vector vectorValues = map.values();
        HashMap map2 = new HashMap((int) ((((double) size) / 0.75d) + 1.0d));
        for (int i = 0; i < size; i++) {
            Object objConvertToKey = convertToKey(keyVectorKeys.get(i).toString());
            FlexBuffers.Reference reference = vectorValues.get(i);
            if (reference.isMap()) {
                map2.put(objConvertToKey, buildMap(reference.asMap()));
            } else if (reference.isVector()) {
                map2.put(objConvertToKey, buildList(reference.asVector()));
            } else if (reference.isString()) {
                map2.put(objConvertToKey, reference.asString());
            } else if (reference.isBoolean()) {
                map2.put(objConvertToKey, Boolean.valueOf(reference.asBoolean()));
            } else if (reference.isInt()) {
                if (shouldRestoreAsLong(reference)) {
                    map2.put(objConvertToKey, Long.valueOf(reference.asLong()));
                } else {
                    map2.put(objConvertToKey, Integer.valueOf(reference.asInt()));
                }
            } else if (reference.isFloat()) {
                map2.put(objConvertToKey, Double.valueOf(reference.asFloat()));
            } else if (reference.isBlob()) {
                map2.put(objConvertToKey, reference.asBlob().getBytes());
            } else {
                throw new IllegalArgumentException("Map values of this type are not supported: " + reference.getClass().getSimpleName());
            }
        }
        return map2;
    }

    private List<Object> buildList(FlexBuffers.Vector vector) {
        int size = vector.size();
        ArrayList arrayList = new ArrayList(size);
        Boolean boolValueOf = null;
        for (int i = 0; i < size; i++) {
            FlexBuffers.Reference reference = vector.get(i);
            if (reference.isMap()) {
                arrayList.add(buildMap(reference.asMap()));
            } else if (reference.isVector()) {
                arrayList.add(buildList(reference.asVector()));
            } else if (reference.isString()) {
                arrayList.add(reference.asString());
            } else if (reference.isBoolean()) {
                arrayList.add(Boolean.valueOf(reference.asBoolean()));
            } else if (reference.isInt()) {
                if (boolValueOf == null) {
                    boolValueOf = Boolean.valueOf(shouldRestoreAsLong(reference));
                }
                if (boolValueOf.booleanValue()) {
                    arrayList.add(Long.valueOf(reference.asLong()));
                } else {
                    arrayList.add(Integer.valueOf(reference.asInt()));
                }
            } else if (reference.isFloat()) {
                arrayList.add(Double.valueOf(reference.asFloat()));
            } else if (reference.isBlob()) {
                arrayList.add(reference.asBlob().getBytes());
            } else {
                throw new IllegalArgumentException("List values of this type are not supported: " + reference.getClass().getSimpleName());
            }
        }
        return arrayList;
    }
}

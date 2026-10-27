package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static int mJniCallCounter;

    @Nullable
    private String[] mKeys;

    @Nullable
    private HashMap<String, Object> mLocalMap;

    @Nullable
    private HashMap<String, ReadableType> mLocalTypeMap;

    private native String[] importKeys();

    private native Object[] importTypes();

    private native Object[] importValues();

    static {
        ReactBridge.staticInit();
    }

    protected ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, Object> getLocalMap() {
        HashMap<String, Object> map = this.mLocalMap;
        if (map != null) {
            return map;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalMap == null) {
                Object[] objArr = (Object[]) Assertions.assertNotNull(importValues());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalMap.put(this.mKeys[i], objArr[i]);
                }
            }
        }
        return this.mLocalMap;
    }

    @NonNull
    private HashMap<String, ReadableType> getLocalTypeMap() {
        HashMap<String, ReadableType> map = this.mLocalTypeMap;
        if (map != null) {
            return map;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalTypeMap == null) {
                Object[] objArr = (Object[]) Assertions.assertNotNull(importTypes());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalTypeMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalTypeMap.put(this.mKeys[i], (ReadableType) objArr[i]);
                }
            }
        }
        return this.mLocalTypeMap;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(@NonNull String str) {
        return getLocalMap().containsKey(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(@NonNull String str) {
        if (getLocalMap().containsKey(str)) {
            return getLocalMap().get(str) == null;
        }
        throw new NoSuchKeyException(str);
    }

    @NonNull
    private Object getValue(@NonNull String str) {
        if (hasKey(str) && !isNull(str)) {
            return Assertions.assertNotNull(getLocalMap().get(str));
        }
        throw new NoSuchKeyException(str);
    }

    private <T> T getValue(String str, Class<T> cls) {
        T t = (T) getValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    @Nullable
    private Object getNullableValue(String str) {
        if (hasKey(str)) {
            return getLocalMap().get(str);
        }
        return null;
    }

    @Nullable
    private <T> T getNullableValue(String str, Class<T> cls) {
        T t = (T) getNullableValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    private void checkInstance(String str, Object obj, Class cls) {
        if (obj == null || cls.isInstance(obj)) {
            return;
        }
        throw new UnexpectedNativeTypeException("Value for " + str + " cannot be cast from " + obj.getClass().getSimpleName() + " to " + cls.getSimpleName());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(@NonNull String str) {
        return ((Boolean) getValue(str, Boolean.class)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(@NonNull String str) {
        return ((Double) getValue(str, Double.class)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(@NonNull String str) {
        return ((Double) getValue(str, Double.class)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public String getString(@NonNull String str) {
        return (String) getNullableValue(str, String.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public ReadableArray getArray(@NonNull String str) {
        return (ReadableArray) getNullableValue(str, ReadableArray.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public ReadableNativeMap getMap(@NonNull String str) {
        return (ReadableNativeMap) getNullableValue(str, ReadableNativeMap.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableType getType(@NonNull String str) {
        if (getLocalTypeMap().containsKey(str)) {
            return (ReadableType) Assertions.assertNotNull(getLocalTypeMap().get(str));
        }
        throw new NoSuchKeyException(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Dynamic getDynamic(@NonNull String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        if (this.mKeys == null) {
            this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
        }
        final String[] strArr = this.mKeys;
        final Object[] objArr = (Object[]) Assertions.assertNotNull(importValues());
        return new Iterator<Map.Entry<String, Object>>() { // from class: com.facebook.react.bridge.ReadableNativeMap.1
            int currentIndex = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentIndex < strArr.length;
            }

            @Override // java.util.Iterator
            public Map.Entry<String, Object> next() {
                final int i = this.currentIndex;
                this.currentIndex = i + 1;
                return new Map.Entry<String, Object>() { // from class: com.facebook.react.bridge.ReadableNativeMap.1.1
                    @Override // java.util.Map.Entry
                    public String getKey() {
                        return strArr[i];
                    }

                    @Override // java.util.Map.Entry
                    public Object getValue() {
                        return objArr[i];
                    }

                    @Override // java.util.Map.Entry
                    public Object setValue(Object obj) {
                        throw new UnsupportedOperationException("Can't set a value while iterating over a ReadableNativeMap");
                    }
                };
            }
        };
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableMapKeySetIterator keySetIterator() {
        if (this.mKeys == null) {
            this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
        }
        final String[] strArr = this.mKeys;
        return new ReadableMapKeySetIterator() { // from class: com.facebook.react.bridge.ReadableNativeMap.2
            int currentIndex = 0;

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public boolean hasNextKey() {
                return this.currentIndex < strArr.length;
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public String nextKey() {
                String[] strArr2 = strArr;
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                return strArr2[i];
            }
        };
    }

    public int hashCode() {
        return getLocalMap().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ReadableNativeMap) {
            return getLocalMap().equals(((ReadableNativeMap) obj).getLocalMap());
        }
        return false;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>(getLocalMap());
        for (String str : map.keySet()) {
            switch (AnonymousClass3.$SwitchMap$com$facebook$react$bridge$ReadableType[getType(str).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    map.put(str, ((ReadableNativeMap) Assertions.assertNotNull(getMap(str))).toHashMap());
                    break;
                case 6:
                    map.put(str, ((ReadableArray) Assertions.assertNotNull(getArray(str))).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + str + ".");
            }
        }
        return map;
    }

    /* JADX INFO: renamed from: com.facebook.react.bridge.ReadableNativeMap$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        private final Iterator<String> mIterator;

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mIterator = readableNativeMap.getLocalMap().keySet().iterator();
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public boolean hasNextKey() {
            return this.mIterator.hasNext();
        }

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public String nextKey() {
            return this.mIterator.next();
        }
    }
}

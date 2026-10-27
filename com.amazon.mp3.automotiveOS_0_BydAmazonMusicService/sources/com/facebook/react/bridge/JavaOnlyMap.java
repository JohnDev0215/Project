package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class JavaOnlyMap implements ReadableMap, WritableMap {
    private final Map mBackingMap;

    public static JavaOnlyMap of(Object... objArr) {
        return new JavaOnlyMap(objArr);
    }

    public static JavaOnlyMap from(Map<String, Object> map) {
        return new JavaOnlyMap(map);
    }

    public static JavaOnlyMap deepClone(ReadableMap readableMap) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                case 1:
                    javaOnlyMap.putNull(strNextKey);
                    break;
                case 2:
                    javaOnlyMap.putBoolean(strNextKey, readableMap.getBoolean(strNextKey));
                    break;
                case 3:
                    javaOnlyMap.putDouble(strNextKey, readableMap.getDouble(strNextKey));
                    break;
                case 4:
                    javaOnlyMap.putString(strNextKey, readableMap.getString(strNextKey));
                    break;
                case 5:
                    javaOnlyMap.putMap(strNextKey, deepClone(readableMap.getMap(strNextKey)));
                    break;
                case 6:
                    javaOnlyMap.putArray(strNextKey, JavaOnlyArray.deepClone(readableMap.getArray(strNextKey)));
                    break;
            }
        }
        return javaOnlyMap;
    }

    /* JADX INFO: renamed from: com.facebook.react.bridge.JavaOnlyMap$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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

    private JavaOnlyMap(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("You must provide the same number of keys and values");
        }
        this.mBackingMap = new HashMap();
        for (int i = 0; i < objArr.length; i += 2) {
            Object objValueOf = objArr[i + 1];
            if (objValueOf instanceof Number) {
                objValueOf = Double.valueOf(((Number) objValueOf).doubleValue());
            }
            this.mBackingMap.put(objArr[i], objValueOf);
        }
    }

    public JavaOnlyMap() {
        this.mBackingMap = new HashMap();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(@NonNull String str) {
        return this.mBackingMap.containsKey(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(@NonNull String str) {
        return this.mBackingMap.get(str) == null;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(@NonNull String str) {
        return ((Boolean) this.mBackingMap.get(str)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(@NonNull String str) {
        return ((Number) this.mBackingMap.get(str)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(@NonNull String str) {
        return ((Number) this.mBackingMap.get(str)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(@NonNull String str) {
        return (String) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableMap getMap(@NonNull String str) {
        return (ReadableMap) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(@NonNull String str) {
        return (ReadableArray) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Dynamic getDynamic(@NonNull String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableType getType(@NonNull String str) {
        Object obj = this.mBackingMap.get(str);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Number) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof Dynamic) {
            return ((Dynamic) obj).getType();
        }
        throw new IllegalArgumentException("Invalid value " + obj.toString() + " for key " + str + "contained in JavaOnlyMap");
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        return this.mBackingMap.entrySet().iterator();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableMapKeySetIterator() { // from class: com.facebook.react.bridge.JavaOnlyMap.1
            Iterator<Map.Entry<String, Object>> mIterator;

            {
                this.mIterator = JavaOnlyMap.this.mBackingMap.entrySet().iterator();
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public boolean hasNextKey() {
                return this.mIterator.hasNext();
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public String nextKey() {
                return this.mIterator.next().getKey();
            }
        };
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putBoolean(@NonNull String str, boolean z) {
        this.mBackingMap.put(str, Boolean.valueOf(z));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putDouble(@NonNull String str, double d) {
        this.mBackingMap.put(str, Double.valueOf(d));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putInt(@NonNull String str, int i) {
        this.mBackingMap.put(str, new Double(i));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putString(@NonNull String str, @Nullable String str2) {
        this.mBackingMap.put(str, str2);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putNull(@NonNull String str) {
        this.mBackingMap.put(str, null);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(@NonNull String str, @Nullable ReadableMap readableMap) {
        this.mBackingMap.put(str, readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(@NonNull ReadableMap readableMap) {
        this.mBackingMap.putAll(((JavaOnlyMap) readableMap).mBackingMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public WritableMap copy() {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.merge(this);
        return javaOnlyMap;
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(@NonNull String str, @Nullable ReadableArray readableArray) {
        this.mBackingMap.put(str, readableArray);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public HashMap<String, Object> toHashMap() {
        return new HashMap<>(this.mBackingMap);
    }

    public String toString() {
        return this.mBackingMap.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Map map = this.mBackingMap;
        Map map2 = ((JavaOnlyMap) obj).mBackingMap;
        return map == null ? map2 == null : map.equals(map2);
    }

    public int hashCode() {
        Map map = this.mBackingMap;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }
}

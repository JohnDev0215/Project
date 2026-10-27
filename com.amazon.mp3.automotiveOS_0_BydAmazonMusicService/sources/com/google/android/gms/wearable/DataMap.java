package com.google.android.gms.wearable;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.wearable.zzcc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public class DataMap {

    @RecentlyNonNull
    public static final String TAG = "DataMap";
    private final HashMap<String, Object> zza = new HashMap<>();

    @RecentlyNonNull
    public static ArrayList<DataMap> arrayListFromBundleArrayList(@RecentlyNonNull ArrayList<Bundle> arrayList) {
        ArrayList<DataMap> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(fromBundle(arrayList.get(i)));
        }
        return arrayList2;
    }

    @RecentlyNonNull
    public static DataMap fromBundle(@RecentlyNonNull Bundle bundle) {
        bundle.setClassLoader((ClassLoader) Preconditions.checkNotNull(Asset.class.getClassLoader()));
        DataMap dataMap = new DataMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                dataMap.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                dataMap.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                dataMap.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                dataMap.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                dataMap.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                dataMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                dataMap.putByte(str, ((Byte) obj).byteValue());
            } else if (obj instanceof byte[]) {
                dataMap.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof String[]) {
                dataMap.putStringArray(str, (String[]) obj);
            } else if (obj instanceof long[]) {
                dataMap.putLongArray(str, (long[]) obj);
            } else if (obj instanceof float[]) {
                dataMap.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof Asset) {
                dataMap.putAsset(str, (Asset) obj);
            } else if (obj instanceof Bundle) {
                dataMap.putDataMap(str, fromBundle((Bundle) obj));
            } else if (obj instanceof ArrayList) {
                ArrayList<String> arrayList = (ArrayList) obj;
                int iZza = zza(arrayList);
                if (iZza == 0) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (iZza == 1) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (iZza == 2) {
                    dataMap.putIntegerArrayList(str, arrayList);
                } else if (iZza == 3) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (iZza == 5) {
                    dataMap.putDataMapArrayList(str, arrayListFromBundleArrayList(arrayList));
                }
            }
        }
        return dataMap;
    }

    @RecentlyNonNull
    public static DataMap fromByteArray(@RecentlyNonNull byte[] bArr) {
        try {
            return com.google.android.gms.internal.wearable.zzk.zzb(new com.google.android.gms.internal.wearable.zzj(com.google.android.gms.internal.wearable.zzw.zzb(bArr), new ArrayList()));
        } catch (zzcc e) {
            throw new IllegalArgumentException("Unable to convert data", e);
        }
    }

    private static int zza(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            return 0;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj != null) {
                if (obj instanceof Integer) {
                    return 2;
                }
                if (obj instanceof String) {
                    return 3;
                }
                if (obj instanceof DataMap) {
                    return 4;
                }
                if (obj instanceof Bundle) {
                    return 5;
                }
            }
        }
        return 1;
    }

    private static final void zzb(String str, Object obj, String str2, Object obj2, ClassCastException classCastException) {
        Log.w(TAG, "Key " + str + " expected " + str2 + " but value was a " + obj.getClass().getName() + ".  The default value " + obj2 + " was returned.");
        Log.w(TAG, "Attempt to cast generated internal exception:", classCastException);
    }

    public void clear() {
        this.zza.clear();
    }

    public boolean containsKey(@RecentlyNonNull String str) {
        return this.zza.containsKey(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof DataMap)) {
            return false;
        }
        DataMap dataMap = (DataMap) obj;
        if (size() != dataMap.size()) {
            return false;
        }
        for (String str : keySet()) {
            Object obj2 = get(str);
            Object obj3 = dataMap.get(str);
            if (obj2 instanceof Asset) {
                if (!(obj3 instanceof Asset)) {
                    return false;
                }
                Asset asset = (Asset) obj2;
                Asset asset2 = (Asset) obj3;
                if (asset == null || asset2 == null) {
                    if (asset != asset2) {
                        return false;
                    }
                } else {
                    if (!(!TextUtils.isEmpty(asset.getDigest()) ? ((String) Preconditions.checkNotNull(asset.getDigest())).equals(asset2.getDigest()) : Arrays.equals(asset.zza(), asset2.zza()))) {
                        return false;
                    }
                }
            } else if (obj2 instanceof String[]) {
                if (!(obj3 instanceof String[]) || !Arrays.equals((String[]) obj2, (String[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof long[]) {
                if (!(obj3 instanceof long[]) || !Arrays.equals((long[]) obj2, (long[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof float[]) {
                if (!(obj3 instanceof float[]) || !Arrays.equals((float[]) obj2, (float[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof byte[]) {
                if (!(obj3 instanceof byte[]) || !Arrays.equals((byte[]) obj2, (byte[]) obj3)) {
                    return false;
                }
            } else {
                if (obj2 == null || obj3 == null) {
                    return obj2 == obj3;
                }
                if (!obj2.equals(obj3)) {
                    return false;
                }
            }
        }
        return true;
    }

    @RecentlyNullable
    public <T> T get(@RecentlyNonNull String str) {
        return (T) this.zza.get(str);
    }

    @RecentlyNullable
    public Asset getAsset(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (Asset) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "Asset", "<null>", e);
            return null;
        }
    }

    public boolean getBoolean(@RecentlyNonNull String str) {
        return getBoolean(str, false);
    }

    public byte getByte(@RecentlyNonNull String str) {
        return getByte(str, (byte) 0);
    }

    @RecentlyNullable
    public byte[] getByteArray(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (byte[]) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "byte[]", "<null>", e);
            return null;
        }
    }

    @RecentlyNullable
    public DataMap getDataMap(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (DataMap) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, TAG, "<null>", e);
            return null;
        }
    }

    @RecentlyNullable
    public ArrayList<DataMap> getDataMapArrayList(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "ArrayList<DataMap>", "<null>", e);
            return null;
        }
    }

    public double getDouble(@RecentlyNonNull String str) {
        return getDouble(str, 0.0d);
    }

    public float getFloat(@RecentlyNonNull String str) {
        return getFloat(str, 0.0f);
    }

    @RecentlyNullable
    public float[] getFloatArray(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (float[]) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "float[]", "<null>", e);
            return null;
        }
    }

    public int getInt(@RecentlyNonNull String str) {
        return getInt(str, 0);
    }

    @RecentlyNullable
    public ArrayList<Integer> getIntegerArrayList(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "ArrayList<Integer>", "<null>", e);
            return null;
        }
    }

    public long getLong(@RecentlyNonNull String str) {
        return getLong(str, 0L);
    }

    @RecentlyNullable
    public long[] getLongArray(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (long[]) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "long[]", "<null>", e);
            return null;
        }
    }

    @RecentlyNullable
    public String getString(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (String) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "String", "<null>", e);
            return null;
        }
    }

    @RecentlyNullable
    public String[] getStringArray(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (String[]) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "String[]", "<null>", e);
            return null;
        }
    }

    @RecentlyNullable
    public ArrayList<String> getStringArrayList(@RecentlyNonNull String str) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zzb(str, obj, "ArrayList<String>", "<null>", e);
            return null;
        }
    }

    public int hashCode() {
        return this.zza.hashCode() * 29;
    }

    public boolean isEmpty() {
        return this.zza.isEmpty();
    }

    @RecentlyNonNull
    public Set<String> keySet() {
        return this.zza.keySet();
    }

    public void putAll(@RecentlyNonNull DataMap dataMap) {
        for (String str : dataMap.keySet()) {
            this.zza.put(str, dataMap.get(str));
        }
    }

    public void putAsset(@RecentlyNonNull String str, @RecentlyNonNull Asset asset) {
        this.zza.put(str, asset);
    }

    public void putBoolean(@RecentlyNonNull String str, boolean z) {
        this.zza.put(str, Boolean.valueOf(z));
    }

    public void putByte(@RecentlyNonNull String str, byte b) {
        this.zza.put(str, Byte.valueOf(b));
    }

    public void putByteArray(@RecentlyNonNull String str, @RecentlyNonNull byte[] bArr) {
        this.zza.put(str, bArr);
    }

    public void putDataMap(@RecentlyNonNull String str, @RecentlyNonNull DataMap dataMap) {
        this.zza.put(str, dataMap);
    }

    public void putDataMapArrayList(@RecentlyNonNull String str, @RecentlyNonNull ArrayList<DataMap> arrayList) {
        this.zza.put(str, arrayList);
    }

    public void putDouble(@RecentlyNonNull String str, double d) {
        this.zza.put(str, Double.valueOf(d));
    }

    public void putFloat(@RecentlyNonNull String str, float f) {
        this.zza.put(str, Float.valueOf(f));
    }

    public void putFloatArray(@RecentlyNonNull String str, @RecentlyNonNull float[] fArr) {
        this.zza.put(str, fArr);
    }

    public void putInt(@RecentlyNonNull String str, int i) {
        this.zza.put(str, Integer.valueOf(i));
    }

    public void putIntegerArrayList(@RecentlyNonNull String str, @RecentlyNonNull ArrayList<Integer> arrayList) {
        this.zza.put(str, arrayList);
    }

    public void putLong(@RecentlyNonNull String str, long j) {
        this.zza.put(str, Long.valueOf(j));
    }

    public void putLongArray(@RecentlyNonNull String str, @RecentlyNonNull long[] jArr) {
        this.zza.put(str, jArr);
    }

    public void putString(@RecentlyNonNull String str, @RecentlyNonNull String str2) {
        this.zza.put(str, str2);
    }

    public void putStringArray(@RecentlyNonNull String str, @RecentlyNonNull String[] strArr) {
        this.zza.put(str, strArr);
    }

    public void putStringArrayList(@RecentlyNonNull String str, @RecentlyNonNull ArrayList<String> arrayList) {
        this.zza.put(str, arrayList);
    }

    @RecentlyNullable
    public Object remove(@RecentlyNonNull String str) {
        return this.zza.remove(str);
    }

    public int size() {
        return this.zza.size();
    }

    @RecentlyNonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        for (String str : this.zza.keySet()) {
            Object obj = this.zza.get(str);
            if (obj instanceof String) {
                bundle.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                bundle.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(str, ((Byte) obj).byteValue());
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof String[]) {
                bundle.putStringArray(str, (String[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(str, (long[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof Asset) {
                bundle.putParcelable(str, (Asset) obj);
            } else if (obj instanceof DataMap) {
                bundle.putParcelable(str, ((DataMap) obj).toBundle());
            } else if (obj instanceof ArrayList) {
                ArrayList<String> arrayList = (ArrayList) obj;
                int iZza = zza(arrayList);
                if (iZza == 0) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (iZza == 1) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (iZza == 2) {
                    bundle.putIntegerArrayList(str, arrayList);
                } else if (iZza == 3) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (iZza == 4) {
                    ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        arrayList2.add(((DataMap) arrayList.get(i)).toBundle());
                    }
                    bundle.putParcelableArrayList(str, arrayList2);
                }
            }
        }
        return bundle;
    }

    @RecentlyNonNull
    public byte[] toByteArray() {
        return com.google.android.gms.internal.wearable.zzk.zza(this).zza.zzI();
    }

    @RecentlyNonNull
    public String toString() {
        return this.zza.toString();
    }

    public boolean getBoolean(@RecentlyNonNull String str, boolean z) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return z;
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "Boolean", Boolean.valueOf(z), e);
            return z;
        }
    }

    public byte getByte(@RecentlyNonNull String str, byte b) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return b;
        }
        try {
            return ((Byte) obj).byteValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "Byte", Byte.valueOf(b), e);
            return b;
        }
    }

    public double getDouble(@RecentlyNonNull String str, double d) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return d;
        }
        try {
            return ((Double) obj).doubleValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "Double", Double.valueOf(d), e);
            return d;
        }
    }

    public float getFloat(@RecentlyNonNull String str, float f) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return f;
        }
        try {
            return ((Float) obj).floatValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "Float", Float.valueOf(f), e);
            return f;
        }
    }

    public int getInt(@RecentlyNonNull String str, int i) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return i;
        }
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "Integer", "<null>", e);
            return i;
        }
    }

    public long getLong(@RecentlyNonNull String str, long j) {
        Object obj = this.zza.get(str);
        if (obj == null) {
            return j;
        }
        try {
            return ((Long) obj).longValue();
        } catch (ClassCastException e) {
            zzb(str, obj, "long", "<null>", e);
            return j;
        }
    }

    @RecentlyNonNull
    public String getString(@RecentlyNonNull String str, @RecentlyNonNull String str2) {
        String string = getString(str);
        return string == null ? str2 : string;
    }
}

package com.spotify.connectivity.flags;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.a;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LoadedFlags implements Flags {
    private final SparseIntArray flagHashToValueIndex;
    private final SparseArray<Object> overridden;
    private final SparseArray<WeakReference<ReadCallback>> readCallbacks;
    private final String[] values;
    public static final Companion Companion = new Companion(null);
    private static final Map<String, Serializable> internalReadFlags = Collections.synchronizedMap(new LinkedHashMap(64));
    public static final Parcelable.Creator<LoadedFlags> CREATOR = new Parcelable.Creator<LoadedFlags>() { // from class: com.spotify.connectivity.flags.LoadedFlags$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoadedFlags createFromParcel(Parcel parcel) {
            SparseIntArray sparseIntArray;
            v03.j(parcel, "in");
            int i = parcel.readInt();
            if (i < 0) {
                sparseIntArray = null;
            } else {
                SparseIntArray sparseIntArray2 = new SparseIntArray(i);
                while (i > 0) {
                    sparseIntArray2.append(parcel.readInt(), parcel.readInt());
                    i--;
                }
                sparseIntArray = sparseIntArray2;
            }
            sparseIntArray.getClass();
            return new LoadedFlags(sparseIntArray, parcel.createStringArray(), parcel.readSparseArray(LoadedFlags$Companion$CREATOR$1.class.getClassLoader()), new SparseArray(), null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoadedFlags[] newArray(int i) {
            return new LoadedFlags[i];
        }
    };

    public static final class Builder {
        private int nextIndex;
        private SparseIntArray flagHashToValueIndex = new SparseIntArray();
        private Map<String, Integer> valueToValueIndex = new HashMap(100);
        private SparseArray<Object> overrides = new SparseArray<>();
        private SparseArray<WeakReference<ReadCallback>> readCallbacks = new SparseArray<>();

        public final LoadedFlags build() {
            Map<String, Integer> map = this.valueToValueIndex;
            v03.h(map);
            String[] strArr = new String[map.size()];
            Map<String, Integer> map2 = this.valueToValueIndex;
            v03.h(map2);
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                strArr[entry.getValue().intValue()] = entry.getKey();
            }
            LoadedFlags loadedFlags = new LoadedFlags(this.flagHashToValueIndex, strArr, this.overrides, this.readCallbacks, null);
            this.flagHashToValueIndex = null;
            this.valueToValueIndex = null;
            this.overrides = null;
            this.readCallbacks = null;
            return loadedFlags;
        }

        public final Builder override(Flag<?> flag, Serializable serializable) {
            v03.j(flag, "flag");
            v03.j(serializable, "value");
            SparseArray<Object> sparseArray = this.overrides;
            v03.h(sparseArray);
            sparseArray.put(flag.getHash(), serializable);
            return this;
        }

        public final Builder set(Flag<?> flag, String str) {
            v03.j(flag, "flag");
            if (str != null) {
                Map<String, Integer> map = this.valueToValueIndex;
                v03.h(map);
                Integer numValueOf = map.get(str);
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(this.nextIndex);
                    this.nextIndex++;
                    Map<String, Integer> map2 = this.valueToValueIndex;
                    v03.h(map2);
                    map2.put(str, numValueOf);
                }
                SparseIntArray sparseIntArray = this.flagHashToValueIndex;
                v03.h(sparseIntArray);
                sparseIntArray.put(flag.getHash(), numValueOf.intValue());
            }
            return this;
        }

        public final Builder setReadCallback(Flag<?> flag, ReadCallback readCallback) {
            v03.j(flag, "flag");
            v03.j(readCallback, "readCallback");
            SparseArray<WeakReference<ReadCallback>> sparseArray = this.readCallbacks;
            v03.h(sparseArray);
            sparseArray.put(flag.getHash(), new WeakReference<>(readCallback));
            return this;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getReadFlags$annotations() {
        }

        public final Map<String, Serializable> getReadFlags() {
            Map<String, Serializable> map = LoadedFlags.internalReadFlags;
            v03.i(map, "internalReadFlags");
            return map;
        }
    }

    public /* synthetic */ LoadedFlags(SparseIntArray sparseIntArray, String[] strArr, SparseArray sparseArray, SparseArray sparseArray2, DefaultConstructorMarker defaultConstructorMarker) {
        this(sparseIntArray, strArr, sparseArray, sparseArray2);
    }

    private final <T extends Serializable> T getInternal(Flag<T> flag) {
        SparseArray<Object> sparseArray = this.overridden;
        v03.h(sparseArray);
        String str = null;
        T t = (T) sparseArray.get(flag.getHash(), null);
        if (t == null) {
            SparseIntArray sparseIntArray = this.flagHashToValueIndex;
            v03.h(sparseIntArray);
            int i = sparseIntArray.get(flag.getHash(), -1);
            if (i != -1) {
                String[] strArr = this.values;
                v03.h(strArr);
                str = strArr[i];
            }
        }
        if (t == null && str == null) {
            throw new IllegalStateException("Value for " + flag.getIdentifier() + " has not been set");
        }
        if (t != null) {
            return t;
        }
        if (str == null) {
            str = "";
        }
        try {
            return (T) flag.mapValue(str);
        } catch (UnmappableValueException e) {
            throw new AssertionError(e);
        }
    }

    public static final Map<String, Serializable> getReadFlags() {
        return Companion.getReadFlags();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> T get(Flag<T> flag) {
        ReadCallback readCallback;
        SparseArray<Object> sparseArray = this.overridden;
        v03.h(sparseArray);
        v03.h(flag);
        String str = null;
        T t = (T) sparseArray.get(flag.getHash(), null);
        if (t == null) {
            SparseIntArray sparseIntArray = this.flagHashToValueIndex;
            v03.h(sparseIntArray);
            int i = sparseIntArray.get(flag.getHash(), -1);
            if (i != -1) {
                String[] strArr = this.values;
                v03.h(strArr);
                str = strArr[i];
            }
        } else {
            Map<String, Serializable> map = internalReadFlags;
            map.remove(flag.getIdentifier());
            map.put(flag.getIdentifier(), t);
        }
        if (t == null && str == null) {
            throw new IllegalStateException(a.X("Value for " + flag.getIdentifier() + " has not been set.\n                Don't panic and check if you have registered\n                your FlagProvider in the FeatureFlags class.\n                ").toString());
        }
        if (str == null) {
            str = "";
        }
        if (t == null) {
            try {
                t = (T) flag.mapValue(str);
                SparseArray<WeakReference<ReadCallback>> sparseArray2 = this.readCallbacks;
                v03.h(sparseArray2);
                WeakReference<ReadCallback> weakReference = sparseArray2.get(flag.getHash());
                if (weakReference != null && (readCallback = weakReference.get()) != null) {
                    readCallback.onFlagRead(flag, str);
                }
                Map<String, Serializable> map2 = internalReadFlags;
                map2.remove(flag.getIdentifier());
                map2.put(flag.getIdentifier(), t);
            } catch (UnmappableValueException e) {
                throw new AssertionError(e);
            }
        }
        return t;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> T getNonOverriddenValue(Flag<T> flag) {
        String str;
        v03.j(flag, "flag");
        try {
            SparseIntArray sparseIntArray = this.flagHashToValueIndex;
            v03.h(sparseIntArray);
            int i = sparseIntArray.get(flag.getHash(), -1);
            if (i == -1) {
                str = null;
            } else {
                String[] strArr = this.values;
                v03.h(strArr);
                str = strArr[i];
            }
            if (str == null) {
                str = "";
            }
            return (T) flag.mapValue(str);
        } catch (UnmappableValueException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> boolean isEnabled(Flag<T> flag) {
        v03.j(flag, "flag");
        return flag.isEnabled(get(flag));
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isLoaded() {
        return true;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isOverridden(Flag<?> flag) {
        v03.j(flag, "flag");
        SparseArray<Object> sparseArray = this.overridden;
        v03.h(sparseArray);
        return sparseArray.get(flag.getHash(), null) != null;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isSame(Flags flags, Flag<?> flag) {
        v03.j(flags, "otherFlags");
        v03.j(flag, "flag");
        return flags instanceof LoadedFlags ? v03.b(getInternal(flag), ((LoadedFlags) flags).getInternal(flag)) : v03.b(getInternal(flag), flags.get(flag));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "dest");
        SparseIntArray sparseIntArray = this.flagHashToValueIndex;
        if (sparseIntArray == null) {
            parcel.writeInt(-1);
        } else {
            int size = sparseIntArray.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(sparseIntArray.keyAt(i2));
                parcel.writeInt(sparseIntArray.valueAt(i2));
            }
        }
        parcel.writeStringArray(this.values);
        parcel.writeSparseArray(this.overridden);
    }

    private LoadedFlags(SparseIntArray sparseIntArray, String[] strArr, SparseArray<Object> sparseArray, SparseArray<WeakReference<ReadCallback>> sparseArray2) {
        this.flagHashToValueIndex = sparseIntArray;
        this.values = strArr;
        this.overridden = sparseArray;
        this.readCallbacks = sparseArray2;
    }
}

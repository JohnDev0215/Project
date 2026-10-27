package com.byd.car.property;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.auto_emanual.fragment.ZipInfo;

/* JADX INFO: loaded from: classes.dex */
public class CarPropertyValue<V> implements Parcelable {
    public static final Parcelable.Creator<CarPropertyValue> CREATOR = new Parcelable.Creator<CarPropertyValue>() { // from class: com.byd.car.property.CarPropertyValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue createFromParcel(Parcel in) {
            return new CarPropertyValue(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue[] newArray(int size) {
            return new CarPropertyValue[size];
        }
    };
    public String mPropertyId;
    public final String mPropertyKey;
    public V mValue;

    public CarPropertyValue(String propertyKey, V value) {
        this.mPropertyKey = propertyKey;
        this.mValue = value;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean getBooleanValue() {
        V v = this.mValue;
        if (v instanceof Boolean) {
            return ((Boolean) v).booleanValue();
        }
        if (v instanceof String) {
            String str = (String) v;
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return true;
            }
            if ("false".equalsIgnoreCase(str) || ZipInfo.NO_UPLOAD_TIME.equals(str)) {
                return false;
            }
        }
        return false;
    }

    public int getIntValue() {
        V v = this.mValue;
        if (v instanceof Integer) {
            return ((Integer) v).intValue();
        }
        if (v instanceof String) {
            try {
                return Integer.parseInt((String) v);
            } catch (Throwable unused) {
                return Integer.MIN_VALUE;
            }
        }
        if (v instanceof Double) {
            return ((Double) v).intValue();
        }
        if (v instanceof Float) {
            return ((Float) v).intValue();
        }
        return Integer.MIN_VALUE;
    }

    public String getPropertyId() {
        return this.mPropertyId;
    }

    public String getPropertyKey() {
        return this.mPropertyKey;
    }

    public V getValue() {
        return this.mValue;
    }

    public void setPropertyId(String propertyId) {
        this.mPropertyId = propertyId;
    }

    public void setValue(V value) {
        this.mValue = value;
    }

    public String toString() {
        return "CarPropertyValue{ mPropertyId=" + this.mPropertyId + ", mValue=" + this.mValue + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPropertyKey);
        parcel.writeString(this.mPropertyId);
        V v = this.mValue;
        Class<?> cls = v == null ? null : v.getClass();
        parcel.writeString(cls != null ? cls.getName() : null);
        if (String.class.equals(cls)) {
            parcel.writeString((String) this.mValue);
            return;
        }
        if (byte[].class.equals(cls)) {
            parcel.writeByteArray((byte[]) this.mValue);
            return;
        }
        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls)) {
            parcel.writeInt(((Number) this.mValue).intValue());
            return;
        }
        if (Long.TYPE.equals(cls) || Long.class.equals(cls)) {
            parcel.writeLong(((Number) this.mValue).longValue());
            return;
        }
        if (Float.TYPE.equals(cls) || Float.class.equals(cls)) {
            parcel.writeFloat(((Number) this.mValue).floatValue());
            return;
        }
        if (Double.TYPE.equals(cls) || Double.class.equals(cls)) {
            parcel.writeDouble(((Number) this.mValue).doubleValue());
            return;
        }
        if (Boolean.TYPE.equals(cls) || Boolean.class.equals(cls)) {
            parcel.writeInt(((Boolean) this.mValue).booleanValue() ? 1 : 0);
            return;
        }
        if (cls.equals(int[].class)) {
            int[] iArr = (int[]) this.mValue;
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(iArr);
            return;
        }
        if (float[].class.equals(cls)) {
            float[] fArr = (float[]) this.mValue;
            parcel.writeInt(fArr.length);
            parcel.writeFloatArray(fArr);
        } else {
            if (!long[].class.equals(cls)) {
                parcel.writeParcelable((Parcelable) this.mValue, i);
                return;
            }
            long[] jArr = (long[]) this.mValue;
            parcel.writeInt(jArr.length);
            for (long j : jArr) {
                parcel.writeLong(j);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v22, types: [V, long[]] */
    public CarPropertyValue(Parcel parcel) {
        this.mPropertyKey = parcel.readString();
        this.mPropertyId = parcel.readString();
        String string = parcel.readString();
        if ("java.lang.String".equals(string)) {
            this.mValue = (V) parcel.readString();
            return;
        }
        if ("[B".equals(string)) {
            this.mValue = (V) parcel.createByteArray();
            return;
        }
        if ("java.lang.Integer".equals(string)) {
            this.mValue = (V) Integer.valueOf(parcel.readInt());
            return;
        }
        if ("java.lang.Long".equals(string)) {
            this.mValue = (V) Long.valueOf(parcel.readLong());
            return;
        }
        if ("java.lang.Float".equals(string)) {
            this.mValue = (V) Float.valueOf(parcel.readFloat());
            return;
        }
        if ("java.lang.Double".equals(string)) {
            this.mValue = (V) Double.valueOf(parcel.readDouble());
            return;
        }
        if ("java.lang.Boolean".equals(string)) {
            this.mValue = (V) Boolean.valueOf(parcel.readInt() != 0);
            return;
        }
        if ("[I".equals(string)) {
            parcel.readInt();
            this.mValue = (V) parcel.createIntArray();
            return;
        }
        if ("[F".equals(string)) {
            parcel.readInt();
            this.mValue = (V) parcel.createFloatArray();
            return;
        }
        if ("[J".equals(string)) {
            int i = parcel.readInt();
            ?? r1 = (V) new long[i];
            for (int i2 = 0; i2 < i; i2++) {
                r1[i2] = parcel.readLong();
            }
            this.mValue = r1;
            return;
        }
        if (Parcelable.class.getName().equals(string)) {
            this.mValue = (V) parcel.readParcelable(getClass().getClassLoader());
        }
    }
}

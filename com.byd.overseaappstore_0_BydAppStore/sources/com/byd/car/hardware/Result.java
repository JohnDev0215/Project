package com.byd.car.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class Result<T> implements Parcelable {
    public static final Parcelable.Creator<Result<?>> CREATOR = new Parcelable.Creator<Result<?>>() { // from class: com.byd.car.hardware.Result.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result<?> createFromParcel(Parcel parcel) {
            return new Result<>(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result<?>[] newArray(int i) {
            return new Result[i];
        }
    };
    public static final int FAIL_ARGUMENT_INVALID = 1;
    public static final int FAIL_FEATURE_EXCEPTION = 4;
    public static final int FAIL_FEATURE_UNAVAILABLE = 2;
    public static final int FAIL_SERVICE_UNSUPPORTED = 3;
    public static final int FAIL_UNKNOWN = 5;
    public static final int SUCCESS = 0;

    @Nullable
    public final T data;
    public final String message;
    public final int statusCode;

    @Retention(RetentionPolicy.CLASS)
    public @interface StatusCode {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, long[]] */
    public Result(Parcel parcel) {
        this.statusCode = parcel.readInt();
        this.message = parcel.readString();
        String string = parcel.readString();
        if ("java.lang.String".equals(string)) {
            this.data = (T) parcel.readString();
            return;
        }
        if ("[B".equals(string)) {
            this.data = (T) parcel.createByteArray();
            return;
        }
        if ("java.lang.Integer".equals(string)) {
            this.data = (T) Integer.valueOf(parcel.readInt());
            return;
        }
        if ("java.lang.Long".equals(string)) {
            this.data = (T) Long.valueOf(parcel.readLong());
            return;
        }
        if ("java.lang.Float".equals(string)) {
            this.data = (T) Float.valueOf(parcel.readFloat());
            return;
        }
        if ("java.lang.Double".equals(string)) {
            this.data = (T) Double.valueOf(parcel.readDouble());
            return;
        }
        if ("java.lang.Boolean".equals(string)) {
            this.data = (T) Boolean.valueOf(parcel.readInt() != 0);
            return;
        }
        if ("[I".equals(string)) {
            parcel.readInt();
            this.data = (T) parcel.createIntArray();
            return;
        }
        if ("[F".equals(string)) {
            parcel.readInt();
            this.data = (T) parcel.createFloatArray();
        } else {
            if (!"[J".equals(string)) {
                this.data = (T) parcel.readParcelable(getClass().getClassLoader());
                return;
            }
            int i = parcel.readInt();
            ?? r1 = (T) new long[i];
            for (int i2 = 0; i2 < i; i2++) {
                r1[i2] = parcel.readLong();
            }
            this.data = r1;
        }
    }

    public Result(@NonNull T t, @StatusCode int i, String str) {
        this.data = t;
        this.message = str;
        this.statusCode = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iIntValue;
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.message);
        T t = this.data;
        Class<?> cls = t == null ? null : t.getClass();
        parcel.writeString(cls != null ? cls.getName() : null);
        if (String.class.equals(cls)) {
            parcel.writeString((String) this.data);
            return;
        }
        if (byte[].class.equals(cls)) {
            parcel.writeByteArray((byte[]) this.data);
            return;
        }
        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls)) {
            iIntValue = ((Number) this.data).intValue();
        } else {
            if (Long.TYPE.equals(cls) || Long.class.equals(cls)) {
                parcel.writeLong(((Number) this.data).longValue());
                return;
            }
            if (Float.TYPE.equals(cls) || Float.class.equals(cls)) {
                parcel.writeFloat(((Number) this.data).floatValue());
                return;
            }
            if (Double.TYPE.equals(cls) || Double.class.equals(cls)) {
                parcel.writeDouble(((Number) this.data).doubleValue());
                return;
            }
            if (!Boolean.TYPE.equals(cls) && !Boolean.class.equals(cls)) {
                if (cls.equals(int[].class)) {
                    int[] iArr = (int[]) this.data;
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(iArr);
                    return;
                }
                if (float[].class.equals(cls)) {
                    float[] fArr = (float[]) this.data;
                    parcel.writeInt(fArr.length);
                    parcel.writeFloatArray(fArr);
                    return;
                } else {
                    if (!long[].class.equals(cls)) {
                        parcel.writeParcelable((Parcelable) this.data, i);
                        return;
                    }
                    long[] jArr = (long[]) this.data;
                    parcel.writeInt(jArr.length);
                    for (long j : jArr) {
                        parcel.writeLong(j);
                    }
                    return;
                }
            }
            iIntValue = ((Boolean) this.data).booleanValue();
        }
        parcel.writeInt(iIntValue);
    }
}

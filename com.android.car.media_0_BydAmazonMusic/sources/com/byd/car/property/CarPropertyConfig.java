package com.byd.car.property;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class CarPropertyConfig<T> implements Parcelable {
    public static final int ACCESS_READ = 1;
    public static final int ACCESS_READ_WRITE = 3;
    public static final int ACCESS_WRITE = 2;
    public static final Parcelable.Creator<CarPropertyConfig> CREATOR = new Parcelable.Creator<CarPropertyConfig>() { // from class: com.byd.car.property.CarPropertyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig createFromParcel(Parcel parcel) {
            return new CarPropertyConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig[] newArray(int i) {
            return new CarPropertyConfig[i];
        }
    };
    public int mAccess;
    public long mFeatureId;
    public T mProvider;
    public String mReadPermission;
    public Class<?> mType;
    public String mWritePermission;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccessType {
    }

    public CarPropertyConfig(int i, long j, Class<?> cls, T t, String str, String str2) {
        this.mAccess = i;
        this.mFeatureId = j;
        this.mType = cls;
        this.mProvider = t;
        this.mReadPermission = str;
        this.mWritePermission = str2;
    }

    public CarPropertyConfig(Parcel parcel) {
        this.mAccess = parcel.readInt();
        this.mFeatureId = parcel.readLong();
        try {
            this.mType = Class.forName(parcel.readString());
            this.mProvider = (T) Class.forName(parcel.readString());
        } catch (Throwable unused) {
        }
        this.mReadPermission = parcel.readString();
        this.mWritePermission = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAccess() {
        return this.mAccess;
    }

    public long getFeatureId() {
        return this.mFeatureId;
    }

    @NonNull
    public Class<?> getFeatureType() {
        return this.mType;
    }

    @NonNull
    public T getProvider() {
        return this.mProvider;
    }

    public String getReadPermission() {
        return this.mReadPermission;
    }

    public String getWritePermission() {
        return this.mWritePermission;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mAccess);
        parcel.writeLong(this.mFeatureId);
        parcel.writeString(this.mType.getName());
        parcel.writeString(this.mProvider.getClass().getName());
        parcel.writeString(this.mReadPermission);
        parcel.writeString(this.mWritePermission);
    }
}

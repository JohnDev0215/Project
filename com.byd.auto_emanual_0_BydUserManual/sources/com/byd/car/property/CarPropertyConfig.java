package com.byd.car.property;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class CarPropertyConfig<T> implements Parcelable {
    public static final int ACCESS_READ = 1;
    public static final int ACCESS_READ_WRITE = 3;
    public static final int ACCESS_WRITE = 2;
    public static final Parcelable.Creator<CarPropertyConfig> CREATOR = new Parcelable.Creator<CarPropertyConfig>() { // from class: com.byd.car.property.CarPropertyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig createFromParcel(Parcel in) {
            return new CarPropertyConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig[] newArray(int size) {
            return new CarPropertyConfig[size];
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

    public CarPropertyConfig(int access, long featureId, Class<?> type, T provider, String readPermission, String writePermission) {
        this.mAccess = access;
        this.mFeatureId = featureId;
        this.mType = type;
        this.mProvider = provider;
        this.mReadPermission = readPermission;
        this.mWritePermission = writePermission;
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

    public Class<?> getFeatureType() {
        return this.mType;
    }

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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAccess);
        dest.writeLong(this.mFeatureId);
        dest.writeString(this.mType.getName());
        dest.writeString(this.mProvider.getClass().getName());
        dest.writeString(this.mReadPermission);
        dest.writeString(this.mWritePermission);
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
}

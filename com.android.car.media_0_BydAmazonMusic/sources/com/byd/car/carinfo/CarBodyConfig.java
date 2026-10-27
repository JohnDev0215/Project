package com.byd.car.carinfo;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.car.WheelHub;
import spi.c;

/* JADX INFO: loaded from: classes2.dex */
public class CarBodyConfig implements Parcelable {
    public static final Parcelable.Creator<CarBodyConfig> CREATOR = new Parcelable.Creator<CarBodyConfig>() { // from class: com.byd.car.carinfo.CarBodyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarBodyConfig createFromParcel(Parcel parcel) {
            return new CarBodyConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarBodyConfig[] newArray(int i) {
            return new CarBodyConfig[i];
        }
    };
    public static final int ORIGIN_DEFAULT = 0;
    public static final int ORIGIN_MES = 1;
    public String mCarModel;
    public String mColor;
    public String mInteriorColor;
    public int mMirrorType;
    public int mOrigin;
    public int mRearDoor;
    public int mRoof;
    public int mSeats;
    public int mSpoiler;
    public int mTire;
    public int mTrailer;
    public WheelHub mWheelHub;

    public CarBodyConfig() {
        this.mOrigin = 0;
        this.mTire = 1;
        this.mWheelHub = new WheelHub();
    }

    public CarBodyConfig(Parcel parcel) {
        this.mOrigin = parcel.readInt();
        this.mRoof = parcel.readInt();
        this.mRearDoor = parcel.readInt();
        this.mTire = parcel.readInt();
        this.mTrailer = parcel.readInt();
        this.mSpoiler = parcel.readInt();
        this.mColor = parcel.readString();
        this.mInteriorColor = parcel.readString();
        this.mCarModel = parcel.readString();
        this.mSeats = parcel.readInt();
        this.mWheelHub = (WheelHub) parcel.readParcelable(WheelHub.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sbK = c.k("CarBodyConfig{mOrigin=");
        sbK.append(this.mOrigin);
        sbK.append(", mRoof=");
        sbK.append(this.mRoof);
        sbK.append(", mRearDoor=");
        sbK.append(this.mRearDoor);
        sbK.append(", mTire=");
        sbK.append(this.mTire);
        sbK.append(", mTrailer=");
        sbK.append(this.mTrailer);
        sbK.append(", mSpoiler=");
        sbK.append(this.mSpoiler);
        sbK.append(", mColor='");
        sbK.append(this.mColor);
        sbK.append('\'');
        sbK.append(", mInteriorColor='");
        sbK.append(this.mInteriorColor);
        sbK.append('\'');
        sbK.append(", mCarModel='");
        sbK.append(this.mCarModel);
        sbK.append('\'');
        sbK.append(", mSeats=");
        sbK.append(this.mSeats);
        sbK.append(", mMirrorType=");
        sbK.append(this.mMirrorType);
        sbK.append(", mWheelHub=");
        sbK.append(this.mWheelHub);
        sbK.append('}');
        return sbK.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mOrigin);
        parcel.writeInt(this.mRoof);
        parcel.writeInt(this.mRearDoor);
        parcel.writeInt(this.mTire);
        parcel.writeInt(this.mTrailer);
        parcel.writeInt(this.mSpoiler);
        parcel.writeString(this.mColor);
        parcel.writeString(this.mInteriorColor);
        parcel.writeString(this.mCarModel);
        parcel.writeInt(this.mSeats);
        parcel.writeParcelable(this.mWheelHub, i);
    }
}

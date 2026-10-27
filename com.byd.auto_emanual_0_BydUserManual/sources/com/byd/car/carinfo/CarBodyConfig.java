package com.byd.car.carinfo;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.car.WheelHub;

/* JADX INFO: loaded from: classes.dex */
public class CarBodyConfig implements Parcelable {
    public static final Parcelable.Creator<CarBodyConfig> CREATOR = new Parcelable.Creator<CarBodyConfig>() { // from class: com.byd.car.carinfo.CarBodyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarBodyConfig createFromParcel(Parcel in) {
            return new CarBodyConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarBodyConfig[] newArray(int size) {
            return new CarBodyConfig[size];
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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "CarBodyConfig{mOrigin=" + this.mOrigin + ", mRoof=" + this.mRoof + ", mRearDoor=" + this.mRearDoor + ", mTire=" + this.mTire + ", mTrailer=" + this.mTrailer + ", mSpoiler=" + this.mSpoiler + ", mColor='" + this.mColor + "', mInteriorColor='" + this.mInteriorColor + "', mCarModel='" + this.mCarModel + "', mSeats=" + this.mSeats + ", mMirrorType=" + this.mMirrorType + ", mWheelHub=" + this.mWheelHub + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mOrigin);
        dest.writeInt(this.mRoof);
        dest.writeInt(this.mRearDoor);
        dest.writeInt(this.mTire);
        dest.writeInt(this.mTrailer);
        dest.writeInt(this.mSpoiler);
        dest.writeString(this.mColor);
        dest.writeString(this.mInteriorColor);
        dest.writeString(this.mCarModel);
        dest.writeInt(this.mSeats);
        dest.writeParcelable(this.mWheelHub, flags);
    }

    public CarBodyConfig(Parcel in) {
        this.mOrigin = in.readInt();
        this.mRoof = in.readInt();
        this.mRearDoor = in.readInt();
        this.mTire = in.readInt();
        this.mTrailer = in.readInt();
        this.mSpoiler = in.readInt();
        this.mColor = in.readString();
        this.mInteriorColor = in.readString();
        this.mCarModel = in.readString();
        this.mSeats = in.readInt();
        this.mWheelHub = (WheelHub) in.readParcelable(WheelHub.class.getClassLoader());
    }
}

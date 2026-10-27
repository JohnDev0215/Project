package com.ts.androidauto.sdk.aidl.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationData implements Parcelable {
    public static final Parcelable.Creator<NavigationData> CREATOR = new Parcelable.Creator<NavigationData>() { // from class: com.ts.androidauto.sdk.aidl.data.NavigationData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationData createFromParcel(Parcel in) {
            return new NavigationData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationData[] newArray(int size) {
            return new NavigationData[size];
        }
    };
    private int mEvent;
    private byte[] mImage;
    private String mRoad;
    private int mTurnAngle;
    private int mTurnNumber;
    private int mTurnSide;

    public NavigationData() {
    }

    public byte[] getImage() {
        return this.mImage;
    }

    public int getEvent() {
        return this.mEvent;
    }

    public int getTurnAngle() {
        return this.mTurnAngle;
    }

    public int getTurnNumber() {
        return this.mTurnNumber;
    }

    public int getTurnSide() {
        return this.mTurnSide;
    }

    public String getRoad() {
        return this.mRoad;
    }

    public void setEvent(int event) {
        this.mEvent = event;
    }

    public void setImage(byte[] image) {
        this.mImage = image;
    }

    public void setRoad(String road) {
        this.mRoad = road;
    }

    public void setTurnAngle(int turnAngle) {
        this.mTurnAngle = turnAngle;
    }

    public void setTurnNumber(int turnNumber) {
        this.mTurnNumber = turnNumber;
    }

    public void setTurnSide(int turnSide) {
        this.mTurnSide = turnSide;
    }

    public NavigationData(Parcel in) {
        this.mRoad = in.readString();
        this.mTurnSide = in.readInt();
        this.mEvent = in.readInt();
        int len = in.readInt();
        if (len <= 0) {
            this.mImage = null;
        } else {
            byte[] bArr = new byte[len];
            this.mImage = bArr;
            in.readByteArray(bArr);
        }
        this.mTurnAngle = in.readInt();
        this.mTurnNumber = in.readInt();
    }

    public String toString() {
        return this.mRoad + " " + this.mTurnSide + " " + this.mEvent + " " + this.mImage + " " + this.mTurnAngle + " " + this.mTurnNumber;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRoad);
        parcel.writeInt(this.mTurnSide);
        parcel.writeInt(this.mEvent);
        byte[] bArr = this.mImage;
        if (bArr != null && bArr.length > 0) {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(this.mImage);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mTurnAngle);
        parcel.writeInt(this.mTurnNumber);
    }
}

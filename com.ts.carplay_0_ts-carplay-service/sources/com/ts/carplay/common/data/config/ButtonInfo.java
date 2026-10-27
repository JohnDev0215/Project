package com.ts.carplay.common.data.config;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ButtonInfo implements Parcelable {
    public static final Parcelable.Creator<ButtonInfo> CREATOR = new Parcelable.Creator<ButtonInfo>() { // from class: com.ts.carplay.common.data.config.ButtonInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ButtonInfo createFromParcel(Parcel in) {
            return new ButtonInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ButtonInfo[] newArray(int size) {
            return new ButtonInfo[size];
        }
    };
    private int mButtonLocation;
    private int mButtonPressDuration;
    private int mButtonType;

    public ButtonInfo(int buttonType, int buttonLocation, int buttonPressDuration) {
        this.mButtonType = buttonType;
        this.mButtonLocation = buttonLocation;
        this.mButtonPressDuration = buttonPressDuration;
    }

    protected ButtonInfo(Parcel in) {
        this.mButtonType = in.readInt();
        this.mButtonLocation = in.readInt();
        this.mButtonPressDuration = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mButtonType);
        parcel.writeInt(this.mButtonLocation);
        parcel.writeInt(this.mButtonPressDuration);
    }

    public String toString() {
        return "ButtonInfo{mButtonType=" + this.mButtonType + ", mButtonLocation=" + this.mButtonLocation + ", mButtonPressDuration=" + this.mButtonPressDuration + '}';
    }

    public int getButtonType() {
        return this.mButtonType;
    }

    public void setButtonType(int buttonType) {
        this.mButtonType = buttonType;
    }

    public int getButtonLocation() {
        return this.mButtonLocation;
    }

    public void setButtonLocation(int buttonLocation) {
        this.mButtonLocation = buttonLocation;
    }

    public int getButtonPressDuration() {
        return this.mButtonPressDuration;
    }

    public void setButtonPressDuration(int buttonPressDuration) {
        this.mButtonPressDuration = buttonPressDuration;
    }
}

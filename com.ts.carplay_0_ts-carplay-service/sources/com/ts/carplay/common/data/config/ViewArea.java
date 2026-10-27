package com.ts.carplay.common.data.config;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ViewArea implements Parcelable {
    public static final Parcelable.Creator<ViewArea> CREATOR = new Parcelable.Creator<ViewArea>() { // from class: com.ts.carplay.common.data.config.ViewArea.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ViewArea createFromParcel(Parcel in) {
            return new ViewArea(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ViewArea[] newArray(int size) {
            return new ViewArea[size];
        }
    };
    private int mHeightPixel;
    private int mOriginX;
    private int mOriginY;
    private int mWidthPixel;

    public ViewArea(int widthPixel, int heightPixel, int originX, int originY) {
        this.mWidthPixel = widthPixel;
        this.mHeightPixel = heightPixel;
        this.mOriginX = originX;
        this.mOriginY = originY;
    }

    public ViewArea(Parcel in) {
        this.mWidthPixel = in.readInt();
        this.mHeightPixel = in.readInt();
        this.mOriginX = in.readInt();
        this.mOriginY = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mWidthPixel);
        parcel.writeInt(this.mHeightPixel);
        parcel.writeInt(this.mOriginX);
        parcel.writeInt(this.mOriginY);
    }

    public String toString() {
        return "ViewArea{mWidthPixel=" + this.mWidthPixel + ", mHeightPixel=" + this.mHeightPixel + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + '}';
    }

    public int getWidthPixel() {
        return this.mWidthPixel;
    }

    public void setWidthPixel(int widthPixel) {
        this.mWidthPixel = widthPixel;
    }

    public int getHeightPixel() {
        return this.mHeightPixel;
    }

    public void setHeightPixel(int heightPixel) {
        this.mHeightPixel = heightPixel;
    }

    public int getOriginX() {
        return this.mOriginX;
    }

    public void setOriginX(int originX) {
        this.mOriginX = originX;
    }

    public int getOriginY() {
        return this.mOriginY;
    }

    public void setOriginY(int originY) {
        this.mOriginY = originY;
    }
}

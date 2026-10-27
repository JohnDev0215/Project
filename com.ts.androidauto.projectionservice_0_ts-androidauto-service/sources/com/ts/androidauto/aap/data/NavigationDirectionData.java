package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationDirectionData implements Parcelable {
    public static final Parcelable.Creator<NavigationDirectionData> CREATOR = new Parcelable.Creator<NavigationDirectionData>() { // from class: com.ts.androidauto.aap.data.NavigationDirectionData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationDirectionData createFromParcel(Parcel in) {
            return new NavigationDirectionData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationDirectionData[] newArray(int size) {
            return new NavigationDirectionData[size];
        }
    };
    private int mIsHighlighted;
    private int mShape;

    public NavigationDirectionData() {
    }

    public NavigationDirectionData(Parcel in) {
        this.mShape = in.readInt();
        this.mIsHighlighted = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mShape);
        parcel.writeInt(this.mIsHighlighted);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getShape() {
        return this.mShape;
    }

    public void setShape(int shape) {
        this.mShape = shape;
    }

    public int getHighlighted() {
        return this.mIsHighlighted;
    }

    public void setHighlighted(int highlighted) {
        this.mIsHighlighted = highlighted;
    }

    public String toString() {
        return "NavigationDirectionData{mShape=" + this.mShape + ", mIsHighlighted=" + this.mIsHighlighted + '}';
    }
}

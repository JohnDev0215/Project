package com.byd.card.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.card.drag.MoreAppFolderInfo;

/* JADX INFO: loaded from: classes.dex */
public class FolderState implements Parcelable {
    public static final Parcelable.Creator<FolderState> CREATOR = new Parcelable.Creator<FolderState>() { // from class: com.byd.card.bean.FolderState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FolderState createFromParcel(Parcel in) {
            return new FolderState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FolderState[] newArray(int size) {
            return new FolderState[size];
        }
    };
    private MoreAppFolderInfo moreAppFolderInfo;
    private boolean state;
    private float x;
    private float y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FolderState() {
    }

    public FolderState(boolean state, MoreAppFolderInfo moreAppFolderInfo, float x, float y) {
        this.state = state;
        this.moreAppFolderInfo = moreAppFolderInfo;
        this.x = x;
        this.y = y;
    }

    protected FolderState(Parcel in) {
        this.state = in.readByte() != 0;
        this.moreAppFolderInfo = (MoreAppFolderInfo) in.readParcelable(MoreAppFolderInfo.class.getClassLoader());
        this.x = in.readFloat();
        this.y = in.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.state ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.moreAppFolderInfo, i);
        parcel.writeFloat(this.x);
        parcel.writeFloat(this.y);
    }

    public boolean isState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public MoreAppFolderInfo getMoreAppFolderInfo() {
        return this.moreAppFolderInfo;
    }

    public void setMoreAppFolderInfo(MoreAppFolderInfo moreAppFolderInfo) {
        this.moreAppFolderInfo = moreAppFolderInfo;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

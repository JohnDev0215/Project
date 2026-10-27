package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class LaneInfo implements Parcelable {
    public static final Parcelable.Creator<LaneInfo> CREATOR = new Parcelable.Creator<LaneInfo>() { // from class: com.neusoft.na.ocservice.LaneInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInfo createFromParcel(Parcel parcel) {
            return new LaneInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInfo[] newArray(int i) {
            return new LaneInfo[i];
        }
    };
    private int laneIconId;
    private int laneIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getLaneIndex() {
        return this.laneIndex;
    }

    public void setLaneIndex(int i) {
        this.laneIndex = i;
    }

    public int getLaneIconId() {
        return this.laneIconId;
    }

    public void setLaneIconId(int i) {
        this.laneIconId = i;
    }

    public LaneInfo() {
        this.laneIndex = -1;
        this.laneIconId = -1;
    }

    protected LaneInfo(Parcel parcel) {
        this.laneIndex = -1;
        this.laneIconId = -1;
        this.laneIndex = parcel.readInt();
        this.laneIconId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.laneIndex);
        parcel.writeInt(this.laneIconId);
    }
}

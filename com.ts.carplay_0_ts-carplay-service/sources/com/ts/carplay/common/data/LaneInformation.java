package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LaneInformation implements Parcelable {
    public static final Parcelable.Creator<LaneInformation> CREATOR = new Parcelable.Creator<LaneInformation>() { // from class: com.ts.carplay.common.data.LaneInformation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInformation createFromParcel(Parcel source) {
            return new LaneInformation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInformation[] newArray(int size) {
            return new LaneInformation[size];
        }
    };
    public static final int LANE_STATES_NOT_PRESENT = -1;
    private int mIndex;
    private int mLaneAngleHighlight;
    private List<Integer> mLaneAngles;
    private int mLaneStatus;

    public static final class LaneAtates {
        public static final int LANE_STATES_GOOD = 1;
        public static final int LANE_STATES_NOT_GOOD = 0;
        public static final int LANE_STATES_PREFERRED = 2;
    }

    public LaneInformation() {
        this.mLaneAngles = new ArrayList();
        this.mIndex = -1;
        this.mLaneStatus = -1;
        this.mLaneAngles = new ArrayList();
        this.mLaneAngleHighlight = -1;
    }

    public LaneInformation(int index, int laneStatus, List<Integer> laneAngles, int laneAngleHighlight) {
        this.mLaneAngles = new ArrayList();
        this.mIndex = index;
        this.mLaneStatus = laneStatus;
        this.mLaneAngles = laneAngles;
        this.mLaneAngleHighlight = laneAngleHighlight;
    }

    public void clear() {
        this.mIndex = -1;
        this.mLaneStatus = -1;
        this.mLaneAngles = new ArrayList();
        this.mLaneAngleHighlight = -1;
    }

    public LaneInformation(Parcel in) {
        this.mLaneAngles = new ArrayList();
        this.mIndex = in.readInt();
        this.mLaneStatus = in.readInt();
        List<Integer> list = new ArrayList<>();
        in.readList(list, LaneInformation.class.getClassLoader());
        this.mLaneAngles = list;
        this.mLaneAngleHighlight = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mIndex);
        dest.writeInt(this.mLaneStatus);
        dest.writeList(this.mLaneAngles);
        dest.writeInt(this.mLaneAngleHighlight);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "LaneInformation{mIndex=" + this.mIndex + ", mLaneStatus=" + this.mLaneStatus + ", mLaneAngles=" + this.mLaneAngles + ", mLaneAngleHighlight=" + this.mLaneAngleHighlight + '}';
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public int getLaneStatus() {
        return this.mLaneStatus;
    }

    public void setLaneStatus(int laneStatus) {
        this.mLaneStatus = laneStatus;
    }

    public List<Integer> getLaneAngles() {
        return this.mLaneAngles;
    }

    public void setLaneAgnle(List<Integer> laneAngle) {
        this.mLaneAngles = laneAngle;
    }

    public int getLaneAngleHighlight() {
        return this.mLaneAngleHighlight;
    }

    public void setLaneAngleHighlight(int laneAngleHighlight) {
        this.mLaneAngleHighlight = laneAngleHighlight;
    }
}

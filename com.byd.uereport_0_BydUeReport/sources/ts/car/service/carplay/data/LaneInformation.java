package ts.car.service.carplay.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LaneInformation {
    public static final int LANE_STATES_GOOD = 1;
    public static final int LANE_STATES_NOT_GOOD = 0;
    public static final int LANE_STATES_NOT_PRESENT = -1;
    public static final int LANE_STATES_PREFERRED = 2;
    private int mIndex;
    private int mLaneAngleHighlight;
    private List<Integer> mLaneAngles;
    private int mLaneStatus;

    public LaneInformation() {
        this.mLaneAngles = new ArrayList();
        this.mIndex = -1;
        this.mLaneStatus = -1;
        this.mLaneAngles = null;
        this.mLaneAngleHighlight = -1;
    }

    public void clear() {
        this.mIndex = -1;
        this.mLaneStatus = -1;
        this.mLaneAngles = null;
        this.mLaneAngleHighlight = -1;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getLaneAngleHighlight() {
        return this.mLaneAngleHighlight;
    }

    public List<Integer> getLaneAngles() {
        return this.mLaneAngles;
    }

    public int getLaneStatus() {
        return this.mLaneStatus;
    }

    public void setIndex(int i8) {
        this.mIndex = i8;
    }

    public void setLaneAgnles(List<Integer> list) {
        this.mLaneAngles = list;
    }

    public void setLaneAngleHighlight(int i8) {
        this.mLaneAngleHighlight = i8;
    }

    public void setLaneStatus(int i8) {
        this.mLaneStatus = i8;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LaneInformation{");
        stringBuffer.append("mIndex = ");
        stringBuffer.append(this.mIndex);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneStatus = ");
        stringBuffer.append(this.mLaneStatus);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneAngles = ");
        stringBuffer.append(this.mLaneAngles);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneAngleHighlight = ");
        stringBuffer.append(this.mLaneAngleHighlight);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public LaneInformation(int i8, int i9, List<Integer> list, int i10) {
        this.mLaneAngles = new ArrayList();
        this.mIndex = i8;
        this.mLaneStatus = i9;
        this.mLaneAngles = list;
        this.mLaneAngleHighlight = i10;
    }
}

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

    public LaneInformation(int i, int i2, List<Integer> list, int i3) {
        this.mLaneAngles = new ArrayList();
        this.mIndex = i;
        this.mLaneStatus = i2;
        this.mLaneAngles = list;
        this.mLaneAngleHighlight = i3;
    }

    public void clear() {
        this.mIndex = -1;
        this.mLaneStatus = -1;
        this.mLaneAngles = null;
        this.mLaneAngleHighlight = -1;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LaneInformation{").append("mIndex = ").append(this.mIndex).append(" ").append("mLaneStatus = ").append(this.mLaneStatus).append(" ").append("mLaneAngles = ").append(this.mLaneAngles).append(" ").append("mLaneAngleHighlight = ").append(this.mLaneAngleHighlight);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }

    public int getLaneStatus() {
        return this.mLaneStatus;
    }

    public void setLaneStatus(int i) {
        this.mLaneStatus = i;
    }

    public List<Integer> getLaneAngles() {
        return this.mLaneAngles;
    }

    public void setLaneAgnles(List<Integer> list) {
        this.mLaneAngles = list;
    }

    public int getLaneAngleHighlight() {
        return this.mLaneAngleHighlight;
    }

    public void setLaneAngleHighlight(int i) {
        this.mLaneAngleHighlight = i;
    }
}

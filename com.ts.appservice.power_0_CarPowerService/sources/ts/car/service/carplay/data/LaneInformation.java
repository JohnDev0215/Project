package ts.car.service.carplay.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
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
        this.mLaneAngles = null;
        this.mLaneAngleHighlight = -1;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("LaneInformation{");
        sb.append("mIndex = ");
        sb.append(this.mIndex);
        sb.append(" ");
        sb.append("mLaneStatus = ");
        sb.append(this.mLaneStatus);
        sb.append(" ");
        sb.append("mLaneAngles = ");
        sb.append(this.mLaneAngles);
        sb.append(" ");
        sb.append("mLaneAngleHighlight = ");
        sb.append(this.mLaneAngleHighlight);
        sb.append("}");
        return sb.toString();
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

    public void setLaneAgnles(List<Integer> laneAngles) {
        this.mLaneAngles = laneAngles;
    }

    public int getLaneAngleHighlight() {
        return this.mLaneAngleHighlight;
    }

    public void setLaneAngleHighlight(int laneAngleHighlight) {
        this.mLaneAngleHighlight = laneAngleHighlight;
    }
}

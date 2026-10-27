package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LaneInformation {
    private int mIndex;
    private int mLaneAngleHighlight;
    private boolean mLaneAngleHighlightValid;
    private final List<Integer> mLaneAngles = new ArrayList();
    private LaneStatus mStatus;

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setLaneStatus(LaneStatus status) {
        this.mStatus = status;
    }

    public LaneStatus getLaneStatus() {
        return this.mStatus;
    }

    public void addLaneAgnle(int laneAngle) {
        this.mLaneAngles.add(Integer.valueOf(laneAngle));
    }

    public List<Integer> getLaneAgnles() {
        return this.mLaneAngles;
    }

    public void setLaneAngleHighlightValid(boolean valid) {
        this.mLaneAngleHighlightValid = valid;
    }

    public boolean getLaneAgnlesHighlightValid() {
        return this.mLaneAngleHighlightValid;
    }

    public void setLaneAngleHighlight(int laneAngleHighLight) {
        this.mLaneAngleHighlight = laneAngleHighLight;
        this.mLaneAngleHighlightValid = true;
    }

    public int getLaneAgnlesHighlight() {
        return this.mLaneAngleHighlight;
    }

    public String toString() {
        return "LaneInformation [mIndex=" + this.mIndex + ", mLaneAngleHighlight=" + this.mLaneAngleHighlight + ", mLaneAngleHighlightValid=" + this.mLaneAngleHighlightValid + ", mLaneAngles=" + this.mLaneAngles + ", mStatus=" + this.mStatus + "]";
    }
}

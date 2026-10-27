package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LaneGuidanceInformation {
    private String mInstructionTextStr;
    private boolean mInstructionTextStrValid;
    private int mLaneGuidanceIndex;
    private final List<Integer> mRouteGuidanceDisplayComponentIDs = new ArrayList();
    private final List<LaneInformation> mLaneInformations = new ArrayList();

    public void addRouteGuidanceDisplayComponentId(int componentId) {
        this.mRouteGuidanceDisplayComponentIDs.add(Integer.valueOf(componentId));
    }

    public List<Integer> getRouteGuidanceDisplayComponentIds() {
        return this.mRouteGuidanceDisplayComponentIDs;
    }

    public void setLaneGuidanceIndex(int laneGuidanceIndex) {
        this.mLaneGuidanceIndex = laneGuidanceIndex;
    }

    public int getLaneGuidanceIndex() {
        return this.mLaneGuidanceIndex;
    }

    public void addLaneInformation(LaneInformation info) {
        this.mLaneInformations.add(info);
    }

    public List<LaneInformation> getLaneInformationList() {
        return this.mLaneInformations;
    }

    public void setInstructionTextStrValid(boolean valid) {
        this.mInstructionTextStrValid = valid;
    }

    public boolean getInstructionTextStrValid() {
        return this.mInstructionTextStrValid;
    }

    public String getInstructTextStr() {
        return this.mInstructionTextStr;
    }

    public void setInstructionTextStr(String textString) {
        this.mInstructionTextStr = textString;
        this.mInstructionTextStrValid = true;
    }

    public String toString() {
        return "LaneGuidanceInformation [mInstructionTextStr=" + this.mInstructionTextStr + ", mInstructionTextStrValid=" + this.mInstructionTextStrValid + ", mLaneGuidanceIndex=" + this.mLaneGuidanceIndex + ", mLaneInformations=" + this.mLaneInformations + ", mRouteGuidanceDisplayComponentIDs=" + this.mRouteGuidanceDisplayComponentIDs + "]";
    }
}

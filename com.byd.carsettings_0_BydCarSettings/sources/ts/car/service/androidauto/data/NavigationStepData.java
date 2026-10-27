package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class NavigationStepData {
    private List<String> mCueData;
    private List<NavigationLaneData> mLaneData;
    private int mManeuverType;
    private String mRoadName;
    private int mRoundaboutExitAngle;
    private int mRoundaboutExitNumber;

    public List<NavigationLaneData> getLaneData() {
        return this.mLaneData;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public List<String> getNavigationCue() {
        return this.mCueData;
    }

    public String getRoad() {
        return this.mRoadName;
    }

    public int getRoundaboutExitAngle() {
        return this.mRoundaboutExitAngle;
    }

    public int getRoundaboutExitNumber() {
        return this.mRoundaboutExitNumber;
    }

    public void setLaneData(List<NavigationLaneData> list) {
        this.mLaneData = list;
    }

    public void setManeuverType(int i) {
        this.mManeuverType = i;
    }

    public void setNavigationCue(List<String> list) {
        this.mCueData = list;
    }

    public void setRoad(String str) {
        this.mRoadName = str;
    }

    public void setRoundaboutExitAngle(int i) {
        this.mRoundaboutExitAngle = i;
    }

    public void setRoundaboutExitNumber(int i) {
        this.mRoundaboutExitNumber = i;
    }

    public String toString() {
        return "IfNavigationStepData{mRoadName='" + this.mRoadName + ", mManeuverType=" + this.mManeuverType + ", mRoundaboutExitAngle=" + this.mRoundaboutExitAngle + ", mRoundaboutExitNumber=" + this.mRoundaboutExitNumber + ", mLaneData=" + this.mLaneData + ", mCueData=" + this.mCueData + '}';
    }
}

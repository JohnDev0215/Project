package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationStepData {
    private List<String> mCueData;
    private List<NavigationLaneData> mLaneData;
    private int mManeuverType;
    private String mRoadName;
    private int mRoundaboutExitAngle;
    private int mRoundaboutExitNumber;

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public void setManeuverType(int maneuverType) {
        this.mManeuverType = maneuverType;
    }

    public int getRoundaboutExitNumber() {
        return this.mRoundaboutExitNumber;
    }

    public void setRoundaboutExitNumber(int exitNumber) {
        this.mRoundaboutExitNumber = exitNumber;
    }

    public int getRoundaboutExitAngle() {
        return this.mRoundaboutExitAngle;
    }

    public void setRoundaboutExitAngle(int exitAngle) {
        this.mRoundaboutExitAngle = exitAngle;
    }

    public String getRoad() {
        return this.mRoadName;
    }

    public void setRoad(String road) {
        this.mRoadName = road;
    }

    public List<NavigationLaneData> getLaneData() {
        return this.mLaneData;
    }

    public void setLaneData(List<NavigationLaneData> laneData) {
        this.mLaneData = laneData;
    }

    public List<String> getNavigationCue() {
        return this.mCueData;
    }

    public void setNavigationCue(List<String> navigationCue) {
        this.mCueData = navigationCue;
    }

    public String toString() {
        return "IfNavigationStepData{mRoadName='" + this.mRoadName + ", mManeuverType=" + this.mManeuverType + ", mRoundaboutExitAngle=" + this.mRoundaboutExitAngle + ", mRoundaboutExitNumber=" + this.mRoundaboutExitNumber + ", mLaneData=" + this.mLaneData + ", mCueData=" + this.mCueData + '}';
    }
}

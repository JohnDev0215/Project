package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationPositionData {
    private String mCurrentRoad;
    private int mDisplayUnits;
    private String mDisplayValue;
    private int mMeters;
    private List<NavigationDestDistanceData> mNavigationDestDistanceData;
    private long mTimeToStepSeconds;

    public String getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public int getDisplayUnits() {
        return this.mDisplayUnits;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public int getMeters() {
        return this.mMeters;
    }

    public List<NavigationDestDistanceData> getNavigationDestDistanceData() {
        return this.mNavigationDestDistanceData;
    }

    public long getTimeToStepSeconds() {
        return this.mTimeToStepSeconds;
    }

    public void setCurrentRoad(String str) {
        this.mCurrentRoad = str;
    }

    public void setDisplayUnits(int i8) {
        this.mDisplayUnits = i8;
    }

    public void setDisplayValue(String str) {
        this.mDisplayValue = str;
    }

    public void setMeters(int i8) {
        this.mMeters = i8;
    }

    public void setNavigationDestDistanceData(List<NavigationDestDistanceData> list) {
        this.mNavigationDestDistanceData = list;
    }

    public void setTimeToStepSeconds(long j8) {
        this.mTimeToStepSeconds = j8;
    }

    public String toString() {
        return "IfNavigationPositionData{mDisplayValue='" + this.mDisplayValue + "', mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mTimeToStepSeconds=" + this.mTimeToStepSeconds + ", mCurrentRoad=" + this.mCurrentRoad + ", mIfNavigationDestDistanceData=" + this.mNavigationDestDistanceData + '}';
    }
}

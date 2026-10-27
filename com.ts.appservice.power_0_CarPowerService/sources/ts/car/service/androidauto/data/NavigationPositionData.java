package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationPositionData {
    private String mCurrentRoad;
    private int mDisplayUnits;
    private String mDisplayValue;
    private int mMeters;
    private List<NavigationDestDistanceData> mNavigationDestDistanceData;
    private long mTimeToStepSeconds;

    public int getMeters() {
        return this.mMeters;
    }

    public void setMeters(int meters) {
        this.mMeters = meters;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.mDisplayValue = displayValue;
    }

    public int getDisplayUnits() {
        return this.mDisplayUnits;
    }

    public void setDisplayUnits(int displayUnits) {
        this.mDisplayUnits = displayUnits;
    }

    public long getTimeToStepSeconds() {
        return this.mTimeToStepSeconds;
    }

    public void setTimeToStepSeconds(long timeSeconds) {
        this.mTimeToStepSeconds = timeSeconds;
    }

    public List<NavigationDestDistanceData> getNavigationDestDistanceData() {
        return this.mNavigationDestDistanceData;
    }

    public void setNavigationDestDistanceData(List<NavigationDestDistanceData> navigationDestDistanceData) {
        this.mNavigationDestDistanceData = navigationDestDistanceData;
    }

    public String getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public void setCurrentRoad(String currentRoad) {
        this.mCurrentRoad = currentRoad;
    }

    public String toString() {
        return "IfNavigationPositionData{mDisplayValue='" + this.mDisplayValue + "', mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mTimeToStepSeconds=" + this.mTimeToStepSeconds + ", mCurrentRoad=" + this.mCurrentRoad + ", mIfNavigationDestDistanceData=" + this.mNavigationDestDistanceData + '}';
    }
}

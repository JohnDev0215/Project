package ts.car.service.androidauto.data;

import android.text.format.DateFormat;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
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

    public void setMeters(int i) {
        this.mMeters = i;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public void setDisplayValue(String str) {
        this.mDisplayValue = str;
    }

    public int getDisplayUnits() {
        return this.mDisplayUnits;
    }

    public void setDisplayUnits(int i) {
        this.mDisplayUnits = i;
    }

    public long getTimeToStepSeconds() {
        return this.mTimeToStepSeconds;
    }

    public void setTimeToStepSeconds(long j) {
        this.mTimeToStepSeconds = j;
    }

    public List<NavigationDestDistanceData> getNavigationDestDistanceData() {
        return this.mNavigationDestDistanceData;
    }

    public void setNavigationDestDistanceData(List<NavigationDestDistanceData> list) {
        this.mNavigationDestDistanceData = list;
    }

    public String getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public void setCurrentRoad(String str) {
        this.mCurrentRoad = str;
    }

    public String toString() {
        return "IfNavigationPositionData{mDisplayValue='" + this.mDisplayValue + DateFormat.QUOTE + ", mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mTimeToStepSeconds=" + this.mTimeToStepSeconds + ", mCurrentRoad=" + this.mCurrentRoad + ", mIfNavigationDestDistanceData=" + this.mNavigationDestDistanceData + '}';
    }
}

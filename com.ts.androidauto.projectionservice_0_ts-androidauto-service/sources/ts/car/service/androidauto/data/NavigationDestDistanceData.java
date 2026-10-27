package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationDestDistanceData {
    private int mDisplayUnits;
    private String mDisplayValue;
    private String mEstimatedTimeAtArrival;
    private int mMeters;
    private long mTimeToArrivalSeconds;

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

    public String getEstimatedTime() {
        return this.mEstimatedTimeAtArrival;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.mEstimatedTimeAtArrival = estimatedTime;
    }

    public long getTimeSeconds() {
        return this.mTimeToArrivalSeconds;
    }

    public void setTimeSeconds(long timeSeconds) {
        this.mTimeToArrivalSeconds = timeSeconds;
    }

    public String toString() {
        return "NavigationDestDistanceData{mDisplayValue='" + this.mDisplayValue + ", mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mEstimatedTimeAtArrival='" + this.mEstimatedTimeAtArrival + ", mTimeToArrivalSeconds=" + this.mTimeToArrivalSeconds + '}';
    }
}

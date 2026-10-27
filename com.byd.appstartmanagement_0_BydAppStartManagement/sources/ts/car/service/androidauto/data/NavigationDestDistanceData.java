package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationDestDistanceData {
    private int mDisplayUnits;
    private String mDisplayValue;
    private String mEstimatedTimeAtArrival;
    private int mMeters;
    private long mTimeToArrivalSeconds;

    public int getDisplayUnits() {
        return this.mDisplayUnits;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public String getEstimatedTime() {
        return this.mEstimatedTimeAtArrival;
    }

    public int getMeters() {
        return this.mMeters;
    }

    public long getTimeSeconds() {
        return this.mTimeToArrivalSeconds;
    }

    public void setDisplayUnits(int i) {
        this.mDisplayUnits = i;
    }

    public void setDisplayValue(String str) {
        this.mDisplayValue = str;
    }

    public void setEstimatedTime(String str) {
        this.mEstimatedTimeAtArrival = str;
    }

    public void setMeters(int i) {
        this.mMeters = i;
    }

    public void setTimeSeconds(long j) {
        this.mTimeToArrivalSeconds = j;
    }

    public String toString() {
        return "NavigationDestDistanceData{mDisplayValue='" + this.mDisplayValue + ", mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mEstimatedTimeAtArrival='" + this.mEstimatedTimeAtArrival + ", mTimeToArrivalSeconds=" + this.mTimeToArrivalSeconds + '}';
    }
}

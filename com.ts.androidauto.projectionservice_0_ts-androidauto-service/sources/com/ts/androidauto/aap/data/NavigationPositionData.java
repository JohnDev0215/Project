package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationPositionData implements Parcelable {
    public static final Parcelable.Creator<NavigationPositionData> CREATOR = new Parcelable.Creator<NavigationPositionData>() { // from class: com.ts.androidauto.aap.data.NavigationPositionData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationPositionData createFromParcel(Parcel in) {
            return new NavigationPositionData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationPositionData[] newArray(int size) {
            return new NavigationPositionData[size];
        }
    };
    private String mCurrentRoad;
    private int mDisplayUnits;
    private String mDisplayValue;
    private int mMeters;
    private List<NavigationDestDistanceData> mNavigationDestDistanceData;
    private long mTimeSeconds;

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.mDisplayValue = displayValue;
    }

    public int getMeters() {
        return this.mMeters;
    }

    public void setMeters(int meters) {
        this.mMeters = meters;
    }

    public int getDisplayUnits() {
        return this.mDisplayUnits;
    }

    public void setDisplayUnits(int displayUnits) {
        this.mDisplayUnits = displayUnits;
    }

    public long getTimeSeconds() {
        return this.mTimeSeconds;
    }

    public void setTimeSeconds(long timeSeconds) {
        this.mTimeSeconds = timeSeconds;
    }

    public String getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public void setCurrentRoad(String currentRoad) {
        this.mCurrentRoad = currentRoad;
    }

    public List<NavigationDestDistanceData> getNavigationDestDistanceData() {
        return this.mNavigationDestDistanceData;
    }

    public void setNavigationDestDistanceData(List<NavigationDestDistanceData> navigationDestDistanceData) {
        this.mNavigationDestDistanceData = navigationDestDistanceData;
    }

    public NavigationPositionData() {
    }

    public NavigationPositionData(Parcel in) {
        this.mDisplayValue = in.readString();
        this.mMeters = in.readInt();
        this.mDisplayUnits = in.readInt();
        this.mTimeSeconds = in.readLong();
        this.mCurrentRoad = in.readString();
        int sizeDestDistance = in.readInt();
        if (sizeDestDistance <= 0) {
            this.mNavigationDestDistanceData = null;
        } else {
            this.mNavigationDestDistanceData = in.createTypedArrayList(NavigationDestDistanceData.CREATOR);
        }
    }

    public String toString() {
        return "NavigationPositionData{mDisplayValue='" + this.mDisplayValue + "', mMeters=" + this.mMeters + ", mDisplayUnits=" + this.mDisplayUnits + ", mTimeSeconds=" + this.mTimeSeconds + ", mCurrentRoad=" + this.mCurrentRoad + ", mNavigationDestDistanceData=" + this.mNavigationDestDistanceData + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDisplayValue);
        parcel.writeInt(this.mMeters);
        parcel.writeInt(this.mDisplayUnits);
        parcel.writeLong(this.mTimeSeconds);
        parcel.writeString(this.mCurrentRoad);
        List<NavigationDestDistanceData> list = this.mNavigationDestDistanceData;
        if (list != null && list.size() > 0) {
            parcel.writeInt(this.mNavigationDestDistanceData.size());
            parcel.writeTypedList(this.mNavigationDestDistanceData);
        } else {
            parcel.writeInt(0);
        }
    }
}

package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationStateData implements Parcelable {
    public static final Parcelable.Creator<NavigationStateData> CREATOR = new Parcelable.Creator<NavigationStateData>() { // from class: com.ts.androidauto.aap.data.NavigationStateData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationStateData createFromParcel(Parcel in) {
            return new NavigationStateData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationStateData[] newArray(int size) {
            return new NavigationStateData[size];
        }
    };
    private List<String> mDestinationData;
    private List<NavigationStepData> mStepData;

    public NavigationStateData() {
    }

    public List<NavigationStepData> getStepData() {
        return this.mStepData;
    }

    public void setStepData(List<NavigationStepData> stepData) {
        this.mStepData = stepData;
    }

    public List<String> getNavigationDestinations() {
        return this.mDestinationData;
    }

    public void setNavigationDestinations(List<String> navigationDestinations) {
        this.mDestinationData = navigationDestinations;
    }

    public NavigationStateData(Parcel in) {
        int sizeDest = in.readInt();
        if (sizeDest <= 0) {
            this.mDestinationData = null;
        } else {
            this.mDestinationData = in.createStringArrayList();
        }
        int sizeStep = in.readInt();
        if (sizeStep <= 0) {
            this.mStepData = null;
        } else {
            this.mStepData = in.createTypedArrayList(NavigationStepData.CREATOR);
        }
    }

    public String toString() {
        return "IfNavigationStateData{mStepData=" + this.mStepData + ", mDestinationData=" + this.mDestinationData + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        List<String> list = this.mDestinationData;
        if (list != null && list.size() > 0) {
            parcel.writeInt(this.mDestinationData.size());
            parcel.writeStringList(this.mDestinationData);
        } else {
            parcel.writeInt(0);
        }
        List<NavigationStepData> list2 = this.mStepData;
        if (list2 != null && list2.size() > 0) {
            parcel.writeInt(this.mStepData.size());
            parcel.writeTypedList(this.mStepData);
        } else {
            parcel.writeInt(0);
        }
    }
}

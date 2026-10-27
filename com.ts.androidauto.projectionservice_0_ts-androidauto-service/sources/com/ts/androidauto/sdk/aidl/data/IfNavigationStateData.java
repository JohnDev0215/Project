package com.ts.androidauto.sdk.aidl.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class IfNavigationStateData implements Parcelable {
    public static final Parcelable.Creator<IfNavigationStateData> CREATOR = new Parcelable.Creator<IfNavigationStateData>() { // from class: com.ts.androidauto.sdk.aidl.data.IfNavigationStateData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IfNavigationStateData createFromParcel(Parcel in) {
            return new IfNavigationStateData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IfNavigationStateData[] newArray(int size) {
            return new IfNavigationStateData[size];
        }
    };
    private List<String> mDestinationData;
    private List<IfNavigationStepData> mStepData;

    public IfNavigationStateData() {
    }

    public List<IfNavigationStepData> getStepData() {
        return this.mStepData;
    }

    public void setStepData(List<IfNavigationStepData> stepData) {
        this.mStepData = stepData;
    }

    public List<String> getNavigationDestinations() {
        return this.mDestinationData;
    }

    public void setNavigationDestinations(List<String> navigationDestinations) {
        this.mDestinationData = navigationDestinations;
    }

    public IfNavigationStateData(Parcel in) {
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
            this.mStepData = in.createTypedArrayList(IfNavigationStepData.CREATOR);
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
        List<IfNavigationStepData> list2 = this.mStepData;
        if (list2 != null && list2.size() > 0) {
            parcel.writeInt(this.mStepData.size());
            parcel.writeTypedList(this.mStepData);
        } else {
            parcel.writeInt(0);
        }
    }
}

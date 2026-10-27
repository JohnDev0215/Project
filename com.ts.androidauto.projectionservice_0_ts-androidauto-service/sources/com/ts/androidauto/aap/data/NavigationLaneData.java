package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationLaneData implements Parcelable {
    public static final Parcelable.Creator<NavigationLaneData> CREATOR = new Parcelable.Creator<NavigationLaneData>() { // from class: com.ts.androidauto.aap.data.NavigationLaneData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationLaneData createFromParcel(Parcel in) {
            return new NavigationLaneData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NavigationLaneData[] newArray(int size) {
            return new NavigationLaneData[size];
        }
    };
    private List<NavigationDirectionData> mNavigationDirectionData;

    public NavigationLaneData() {
    }

    public NavigationLaneData(Parcel in) {
        int sizeDirection = in.readInt();
        if (sizeDirection <= 0) {
            this.mNavigationDirectionData = null;
        } else {
            this.mNavigationDirectionData = in.createTypedArrayList(NavigationDirectionData.CREATOR);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        List<NavigationDirectionData> list = this.mNavigationDirectionData;
        if (list != null && list.size() > 0) {
            parcel.writeInt(this.mNavigationDirectionData.size());
            parcel.writeTypedList(this.mNavigationDirectionData);
        } else {
            parcel.writeInt(0);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<NavigationDirectionData> getNavigationDirectionData() {
        return this.mNavigationDirectionData;
    }

    public void setNavigationDirectionData(List<NavigationDirectionData> navigationDirectionData) {
        this.mNavigationDirectionData = navigationDirectionData;
    }

    public String toString() {
        return "NavigationLaneData{mNavigationDirectionData=" + this.mNavigationDirectionData + '}';
    }
}

package com.amazon.avod.connectivity;

import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum NetworkState implements Parcelable, MetricParameter {
    FULL_ACCESS,
    NO_ACCESS;

    public static final Parcelable.Creator<NetworkState> CREATOR = new Parcelable.Creator<NetworkState>() { // from class: com.amazon.avod.connectivity.NetworkState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkState createFromParcel(Parcel parcel) {
            return NetworkState.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkState[] newArray(int i) {
            return new NetworkState[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static NetworkState getStateByNetworkInfo(@Nonnull NetworkInfo networkInfo) {
        if (networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
            return FULL_ACCESS;
        }
        return NO_ACCESS;
    }

    public boolean isFullNetworkAccessState() {
        return this == FULL_ACCESS;
    }

    public boolean isNoAccessState() {
        return this == NO_ACCESS;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}

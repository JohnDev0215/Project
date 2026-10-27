package com.amazon.avod.connectivity;

import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DetailedNetworkInfo implements Parcelable {
    public static final Parcelable.Creator<DetailedNetworkInfo> CREATOR = new Parcelable.Creator<DetailedNetworkInfo>() { // from class: com.amazon.avod.connectivity.DetailedNetworkInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DetailedNetworkInfo createFromParcel(Parcel parcel) {
            return new DetailedNetworkInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DetailedNetworkInfo[] newArray(int i) {
            return new DetailedNetworkInfo[i];
        }
    };
    private final String mExtraInfo;
    private final MobileNetworkClass mMobileNetworkClass;
    private final NetworkState mNetworkState;
    private final NetworkType mNetworkType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nonnull
    public static DetailedNetworkInfo createFromNetworkInfo(@Nonnull NetworkInfo networkInfo) {
        Preconditions.checkNotNull(networkInfo, "networkInfo");
        return new DetailedNetworkInfo(NetworkType.getTypeByNetworkInfo(networkInfo), MobileNetworkClass.getClassByNetworkInfo(networkInfo), NetworkState.getStateByNetworkInfo(networkInfo), networkInfo.getExtraInfo());
    }

    public DetailedNetworkInfo(@Nonnull NetworkType networkType, @Nonnull MobileNetworkClass mobileNetworkClass, @Nonnull NetworkState networkState) {
        this(networkType, mobileNetworkClass, networkState, null);
    }

    public DetailedNetworkInfo(@Nonnull NetworkType networkType, @Nonnull MobileNetworkClass mobileNetworkClass, @Nonnull NetworkState networkState, @Nullable String str) {
        Preconditions.checkNotNull(networkType, "networkType");
        Preconditions.checkNotNull(mobileNetworkClass, "mobileNetworkClass");
        Preconditions.checkNotNull(networkState, "networkState");
        this.mNetworkState = networkState;
        if (networkState.isNoAccessState()) {
            this.mNetworkType = NetworkType.NO_CONNECTION;
            this.mExtraInfo = "";
        } else {
            this.mNetworkType = networkType;
            this.mExtraInfo = str == null ? "" : str;
        }
        if (this.mNetworkType != NetworkType.WAN) {
            this.mMobileNetworkClass = MobileNetworkClass.NONE;
        } else {
            this.mMobileNetworkClass = mobileNetworkClass;
        }
    }

    private DetailedNetworkInfo(Parcel parcel) {
        this.mNetworkType = (NetworkType) parcel.readParcelable(NetworkType.class.getClassLoader());
        this.mMobileNetworkClass = (MobileNetworkClass) parcel.readParcelable(MobileNetworkClass.class.getClassLoader());
        this.mNetworkState = (NetworkState) parcel.readParcelable(NetworkState.class.getClassLoader());
        this.mExtraInfo = parcel.readString();
    }

    @Nonnull
    public NetworkType getNetworkType() {
        return this.mNetworkType;
    }

    @Nonnull
    public NetworkState getNetworkState() {
        return this.mNetworkState;
    }

    public boolean hasWifiConnection() {
        return this.mNetworkType == NetworkType.WIFI && hasFullNetworkAccess();
    }

    public boolean hasWanConnection() {
        return this.mNetworkType == NetworkType.WAN && hasFullNetworkAccess();
    }

    public boolean hasFullNetworkAccess() {
        return this.mNetworkState == NetworkState.FULL_ACCESS;
    }

    public boolean hasNoNetworkAccess() {
        return this.mNetworkState == NetworkState.NO_ACCESS;
    }

    public String toString() {
        return String.format(Locale.US, "DetailedNetworkInfo[type=%s, class=%s, state=%s, extra=%s]", this.mNetworkType, this.mMobileNetworkClass, this.mNetworkState, Integer.valueOf(this.mExtraInfo.hashCode()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DetailedNetworkInfo)) {
            return false;
        }
        DetailedNetworkInfo detailedNetworkInfo = (DetailedNetworkInfo) obj;
        return this.mNetworkType.equals(detailedNetworkInfo.mNetworkType) && this.mMobileNetworkClass.equals(detailedNetworkInfo.mMobileNetworkClass) && this.mNetworkState.equals(detailedNetworkInfo.mNetworkState) && this.mExtraInfo.equals(detailedNetworkInfo.mExtraInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mNetworkType, this.mMobileNetworkClass, this.mNetworkState, this.mExtraInfo});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mNetworkType, i);
        parcel.writeParcelable(this.mMobileNetworkClass, i);
        parcel.writeParcelable(this.mNetworkState, i);
        parcel.writeString(this.mExtraInfo);
    }
}

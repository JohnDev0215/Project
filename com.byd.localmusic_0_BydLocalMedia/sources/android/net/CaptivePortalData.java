package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class CaptivePortalData implements Parcelable {
    public static final Parcelable.Creator<CaptivePortalData> CREATOR = new Parcelable.Creator<CaptivePortalData>() { // from class: android.net.CaptivePortalData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptivePortalData createFromParcel(Parcel parcel) {
            return new CaptivePortalData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CaptivePortalData[] newArray(int i) {
            return new CaptivePortalData[i];
        }
    };
    private final long mByteLimit;
    private final boolean mCaptive;
    private final long mExpiryTimeMillis;
    private final boolean mIsSessionExtendable;
    private final long mRefreshTimeMillis;
    private final Uri mUserPortalUrl;
    private final Uri mVenueInfoUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CaptivePortalData(long j, Uri uri, Uri uri2, boolean z, long j2, long j3, boolean z2) {
        this.mRefreshTimeMillis = j;
        this.mUserPortalUrl = uri;
        this.mVenueInfoUrl = uri2;
        this.mIsSessionExtendable = z;
        this.mByteLimit = j2;
        this.mExpiryTimeMillis = j3;
        this.mCaptive = z2;
    }

    private CaptivePortalData(Parcel parcel) {
        this(parcel.readLong(), (Uri) parcel.readParcelable(null), (Uri) parcel.readParcelable(null), parcel.readBoolean(), parcel.readLong(), parcel.readLong(), parcel.readBoolean());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mRefreshTimeMillis);
        parcel.writeParcelable(this.mUserPortalUrl, 0);
        parcel.writeParcelable(this.mVenueInfoUrl, 0);
        parcel.writeBoolean(this.mIsSessionExtendable);
        parcel.writeLong(this.mByteLimit);
        parcel.writeLong(this.mExpiryTimeMillis);
        parcel.writeBoolean(this.mCaptive);
    }

    public static class Builder {
        private boolean mCaptive;
        private boolean mIsSessionExtendable;
        private long mRefreshTime;
        private Uri mUserPortalUrl;
        private Uri mVenueInfoUrl;
        private long mBytesRemaining = -1;
        private long mExpiryTime = -1;

        public Builder() {
        }

        public Builder(CaptivePortalData captivePortalData) {
            if (captivePortalData == null) {
                return;
            }
            setRefreshTime(captivePortalData.mRefreshTimeMillis).setUserPortalUrl(captivePortalData.mUserPortalUrl).setVenueInfoUrl(captivePortalData.mVenueInfoUrl).setSessionExtendable(captivePortalData.mIsSessionExtendable).setBytesRemaining(captivePortalData.mByteLimit).setExpiryTime(captivePortalData.mExpiryTimeMillis).setCaptive(captivePortalData.mCaptive);
        }

        public Builder setRefreshTime(long j) {
            this.mRefreshTime = j;
            return this;
        }

        public Builder setUserPortalUrl(Uri uri) {
            this.mUserPortalUrl = uri;
            return this;
        }

        public Builder setVenueInfoUrl(Uri uri) {
            this.mVenueInfoUrl = uri;
            return this;
        }

        public Builder setSessionExtendable(boolean z) {
            this.mIsSessionExtendable = z;
            return this;
        }

        public Builder setBytesRemaining(long j) {
            this.mBytesRemaining = j;
            return this;
        }

        public Builder setExpiryTime(long j) {
            this.mExpiryTime = j;
            return this;
        }

        public Builder setCaptive(boolean z) {
            this.mCaptive = z;
            return this;
        }

        public CaptivePortalData build() {
            return new CaptivePortalData(this.mRefreshTime, this.mUserPortalUrl, this.mVenueInfoUrl, this.mIsSessionExtendable, this.mBytesRemaining, this.mExpiryTime, this.mCaptive);
        }
    }

    public long getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    public Uri getUserPortalUrl() {
        return this.mUserPortalUrl;
    }

    public Uri getVenueInfoUrl() {
        return this.mVenueInfoUrl;
    }

    public boolean isSessionExtendable() {
        return this.mIsSessionExtendable;
    }

    public long getByteLimit() {
        return this.mByteLimit;
    }

    public long getExpiryTimeMillis() {
        return this.mExpiryTimeMillis;
    }

    public boolean isCaptive() {
        return this.mCaptive;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mRefreshTimeMillis), this.mUserPortalUrl, this.mVenueInfoUrl, Boolean.valueOf(this.mIsSessionExtendable), Long.valueOf(this.mByteLimit), Long.valueOf(this.mExpiryTimeMillis), Boolean.valueOf(this.mCaptive));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CaptivePortalData)) {
            return false;
        }
        CaptivePortalData captivePortalData = (CaptivePortalData) obj;
        return this.mRefreshTimeMillis == captivePortalData.mRefreshTimeMillis && Objects.equals(this.mUserPortalUrl, captivePortalData.mUserPortalUrl) && Objects.equals(this.mVenueInfoUrl, captivePortalData.mVenueInfoUrl) && this.mIsSessionExtendable == captivePortalData.mIsSessionExtendable && this.mByteLimit == captivePortalData.mByteLimit && this.mExpiryTimeMillis == captivePortalData.mExpiryTimeMillis && this.mCaptive == captivePortalData.mCaptive;
    }

    public String toString() {
        return "CaptivePortalData {refreshTime: " + this.mRefreshTimeMillis + ", userPortalUrl: " + this.mUserPortalUrl + ", venueInfoUrl: " + this.mVenueInfoUrl + ", isSessionExtendable: " + this.mIsSessionExtendable + ", byteLimit: " + this.mByteLimit + ", expiryTime: " + this.mExpiryTimeMillis + ", captive: " + this.mCaptive + "}";
    }
}

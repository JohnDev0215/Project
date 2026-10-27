package android.telephony.data;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class DataProfile implements Parcelable {
    public static final Parcelable.Creator<DataProfile> CREATOR = new Parcelable.Creator<DataProfile>() { // from class: android.telephony.data.DataProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataProfile createFromParcel(Parcel parcel) {
            return new DataProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataProfile[] newArray(int i) {
            return new DataProfile[i];
        }
    };
    public static final int TYPE_3GPP = 1;
    public static final int TYPE_3GPP2 = 2;
    public static final int TYPE_COMMON = 0;
    private final String mApn;
    private final int mAuthType;
    private final int mBearerBitmask;
    private final boolean mEnabled;
    private final int mMaxConnections;
    private final int mMaxConnectionsTime;
    private final int mMtuV4;
    private final int mMtuV6;
    private final String mPassword;
    private final boolean mPersistent;
    private final boolean mPreferred;
    private final int mProfileId;
    private final int mProtocolType;
    private final int mRoamingProtocolType;
    private final int mSupportedApnTypesBitmask;
    private final int mType;
    private final String mUserName;
    private final int mWaitTime;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DataProfile(int i, String str, int i2, int i3, String str2, String str3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, int i10, int i11, int i12, boolean z2, boolean z3) {
        int i13;
        this.mProfileId = i;
        this.mApn = str;
        this.mProtocolType = i2;
        if (i3 == -1) {
            i13 = TextUtils.isEmpty(str2) ? 0 : 3;
        } else {
            i13 = i3;
        }
        this.mAuthType = i13;
        this.mUserName = str2;
        this.mPassword = str3;
        this.mType = i4;
        this.mMaxConnectionsTime = i5;
        this.mMaxConnections = i6;
        this.mWaitTime = i7;
        this.mEnabled = z;
        this.mSupportedApnTypesBitmask = i8;
        this.mRoamingProtocolType = i9;
        this.mBearerBitmask = i10;
        this.mMtuV4 = i11;
        this.mMtuV6 = i12;
        this.mPersistent = z2;
        this.mPreferred = z3;
    }

    private DataProfile(Parcel parcel) {
        this.mProfileId = parcel.readInt();
        this.mApn = parcel.readString();
        this.mProtocolType = parcel.readInt();
        this.mAuthType = parcel.readInt();
        this.mUserName = parcel.readString();
        this.mPassword = parcel.readString();
        this.mType = parcel.readInt();
        this.mMaxConnectionsTime = parcel.readInt();
        this.mMaxConnections = parcel.readInt();
        this.mWaitTime = parcel.readInt();
        this.mEnabled = parcel.readBoolean();
        this.mSupportedApnTypesBitmask = parcel.readInt();
        this.mRoamingProtocolType = parcel.readInt();
        this.mBearerBitmask = parcel.readInt();
        this.mMtuV4 = parcel.readInt();
        this.mMtuV6 = parcel.readInt();
        this.mPersistent = parcel.readBoolean();
        this.mPreferred = parcel.readBoolean();
    }

    public int getProfileId() {
        return this.mProfileId;
    }

    public String getApn() {
        return this.mApn;
    }

    public int getProtocolType() {
        return this.mProtocolType;
    }

    public int getAuthType() {
        return this.mAuthType;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public int getType() {
        return this.mType;
    }

    public int getMaxConnectionsTime() {
        return this.mMaxConnectionsTime;
    }

    public int getMaxConnections() {
        return this.mMaxConnections;
    }

    public int getWaitTime() {
        return this.mWaitTime;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public int getSupportedApnTypesBitmask() {
        return this.mSupportedApnTypesBitmask;
    }

    public int getRoamingProtocolType() {
        return this.mRoamingProtocolType;
    }

    public int getBearerBitmask() {
        return this.mBearerBitmask;
    }

    @Deprecated
    public int getMtu() {
        return this.mMtuV4;
    }

    public int getMtuV4() {
        return this.mMtuV4;
    }

    public int getMtuV6() {
        return this.mMtuV6;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public boolean isPreferred() {
        return this.mPreferred;
    }

    public String toString() {
        return "DataProfile=" + this.mProfileId + "/" + this.mProtocolType + "/" + this.mAuthType + "/" + (TelephonyUtils.IS_USER ? "***/***/***" : this.mApn + "/" + this.mUserName + "/" + this.mPassword) + "/" + this.mType + "/" + this.mMaxConnectionsTime + "/" + this.mMaxConnections + "/" + this.mWaitTime + "/" + this.mEnabled + "/" + this.mSupportedApnTypesBitmask + "/" + this.mRoamingProtocolType + "/" + this.mBearerBitmask + "/" + this.mMtuV4 + "/" + this.mMtuV6 + "/" + this.mPersistent + "/" + this.mPreferred;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mProfileId);
        parcel.writeString(this.mApn);
        parcel.writeInt(this.mProtocolType);
        parcel.writeInt(this.mAuthType);
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mPassword);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mMaxConnectionsTime);
        parcel.writeInt(this.mMaxConnections);
        parcel.writeInt(this.mWaitTime);
        parcel.writeBoolean(this.mEnabled);
        parcel.writeInt(this.mSupportedApnTypesBitmask);
        parcel.writeInt(this.mRoamingProtocolType);
        parcel.writeInt(this.mBearerBitmask);
        parcel.writeInt(this.mMtuV4);
        parcel.writeInt(this.mMtuV6);
        parcel.writeBoolean(this.mPersistent);
        parcel.writeBoolean(this.mPreferred);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DataProfile dataProfile = (DataProfile) obj;
        return this.mProfileId == dataProfile.mProfileId && this.mProtocolType == dataProfile.mProtocolType && this.mAuthType == dataProfile.mAuthType && this.mType == dataProfile.mType && this.mMaxConnectionsTime == dataProfile.mMaxConnectionsTime && this.mMaxConnections == dataProfile.mMaxConnections && this.mWaitTime == dataProfile.mWaitTime && this.mEnabled == dataProfile.mEnabled && this.mSupportedApnTypesBitmask == dataProfile.mSupportedApnTypesBitmask && this.mRoamingProtocolType == dataProfile.mRoamingProtocolType && this.mBearerBitmask == dataProfile.mBearerBitmask && this.mMtuV4 == dataProfile.mMtuV4 && this.mMtuV6 == dataProfile.mMtuV6 && this.mPersistent == dataProfile.mPersistent && this.mPreferred == dataProfile.mPreferred && Objects.equals(this.mApn, dataProfile.mApn) && Objects.equals(this.mUserName, dataProfile.mUserName) && Objects.equals(this.mPassword, dataProfile.mPassword);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mProfileId), this.mApn, Integer.valueOf(this.mProtocolType), Integer.valueOf(this.mAuthType), this.mUserName, this.mPassword, Integer.valueOf(this.mType), Integer.valueOf(this.mMaxConnectionsTime), Integer.valueOf(this.mMaxConnections), Integer.valueOf(this.mWaitTime), Boolean.valueOf(this.mEnabled), Integer.valueOf(this.mSupportedApnTypesBitmask), Integer.valueOf(this.mRoamingProtocolType), Integer.valueOf(this.mBearerBitmask), Integer.valueOf(this.mMtuV4), Integer.valueOf(this.mMtuV6), Boolean.valueOf(this.mPersistent), Boolean.valueOf(this.mPreferred));
    }

    public static final class Builder {
        private String mApn;
        private int mAuthType;
        private int mBearerBitmask;
        private boolean mEnabled;
        private int mMaxConnections;
        private int mMaxConnectionsTime;
        private int mMtuV4;
        private int mMtuV6;
        private String mPassword;
        private boolean mPersistent;
        private boolean mPreferred;
        private int mProfileId;
        private int mProtocolType;
        private int mRoamingProtocolType;
        private int mSupportedApnTypesBitmask;
        private int mType;
        private String mUserName;
        private int mWaitTime;

        public Builder setProfileId(int i) {
            this.mProfileId = i;
            return this;
        }

        public Builder setApn(String str) {
            this.mApn = str;
            return this;
        }

        public Builder setProtocolType(int i) {
            this.mProtocolType = i;
            return this;
        }

        public Builder setAuthType(int i) {
            this.mAuthType = i;
            return this;
        }

        public Builder setUserName(String str) {
            this.mUserName = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.mPassword = str;
            return this;
        }

        public Builder setType(int i) {
            this.mType = i;
            return this;
        }

        public Builder setMaxConnectionsTime(int i) {
            this.mMaxConnectionsTime = i;
            return this;
        }

        public Builder setMaxConnections(int i) {
            this.mMaxConnections = i;
            return this;
        }

        public Builder setWaitTime(int i) {
            this.mWaitTime = i;
            return this;
        }

        public Builder enable(boolean z) {
            this.mEnabled = z;
            return this;
        }

        public Builder setSupportedApnTypesBitmask(int i) {
            this.mSupportedApnTypesBitmask = i;
            return this;
        }

        public Builder setRoamingProtocolType(int i) {
            this.mRoamingProtocolType = i;
            return this;
        }

        public Builder setBearerBitmask(int i) {
            this.mBearerBitmask = i;
            return this;
        }

        public Builder setMtu(int i) {
            this.mMtuV6 = i;
            this.mMtuV4 = i;
            return this;
        }

        public Builder setMtuV4(int i) {
            this.mMtuV4 = i;
            return this;
        }

        public Builder setMtuV6(int i) {
            this.mMtuV6 = i;
            return this;
        }

        public Builder setPreferred(boolean z) {
            this.mPreferred = z;
            return this;
        }

        public Builder setPersistent(boolean z) {
            this.mPersistent = z;
            return this;
        }

        public DataProfile build() {
            return new DataProfile(this.mProfileId, this.mApn, this.mProtocolType, this.mAuthType, this.mUserName, this.mPassword, this.mType, this.mMaxConnectionsTime, this.mMaxConnections, this.mWaitTime, this.mEnabled, this.mSupportedApnTypesBitmask, this.mRoamingProtocolType, this.mBearerBitmask, this.mMtuV4, this.mMtuV6, this.mPersistent, this.mPreferred);
        }
    }
}

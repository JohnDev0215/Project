package cerence.ark.assistant.api.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RecoveryConfig implements Parcelable {
    public static final Parcelable.Creator<RecoveryConfig> CREATOR = new Parcelable.Creator<RecoveryConfig>() { // from class: cerence.ark.assistant.api.data.RecoveryConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecoveryConfig createFromParcel(Parcel parcel) {
            return new RecoveryConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecoveryConfig[] newArray(int i) {
            return new RecoveryConfig[i];
        }
    };
    private final String mAttribute;
    private final String mDeviceId;
    private final String mRecoveryConfigDir;
    private final String mUpdateStorageDir;
    private final String mUserId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private RecoveryConfig(Builder builder) {
        this.mRecoveryConfigDir = builder.mRecoveryConfigDir;
        this.mUpdateStorageDir = builder.mUpdateStorageDir;
        this.mDeviceId = builder.mDeviceId;
        this.mUserId = builder.mUserId;
        this.mAttribute = builder.mAttribute;
    }

    protected RecoveryConfig(Parcel parcel) {
        this.mRecoveryConfigDir = parcel.readString();
        this.mUpdateStorageDir = parcel.readString();
        this.mDeviceId = parcel.readString();
        this.mUserId = parcel.readString();
        this.mAttribute = parcel.readString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getRecoveryConfigDir() {
        return this.mRecoveryConfigDir;
    }

    public String getUpdateStorageDir() {
        return this.mUpdateStorageDir;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAttribute() {
        return this.mAttribute;
    }

    public String toString() {
        return "RecoveryConfig: \nmRecoveryConfigDir  = " + this.mRecoveryConfigDir + "\nmUpdateStorageDir   = " + this.mUpdateStorageDir + "\nmDeviceId           = " + this.mDeviceId + "\nmUserId             = " + this.mUserId + "\nmAttribute          = " + this.mAttribute;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRecoveryConfigDir);
        parcel.writeString(this.mUpdateStorageDir);
        parcel.writeString(this.mDeviceId);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mAttribute);
    }

    public static final class Builder {
        private String mAttribute;
        private String mDeviceId;
        private String mRecoveryConfigDir;
        private String mUpdateStorageDir;
        private String mUserId;

        private Builder() {
        }

        public Builder setRecoveryConfigDir(String str) {
            this.mRecoveryConfigDir = str;
            return this;
        }

        public Builder setUpdateStorageDir(String str) {
            this.mUpdateStorageDir = str;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.mUserId = str;
            return this;
        }

        public Builder setAttribute(String str) {
            this.mAttribute = str;
            return this;
        }

        public RecoveryConfig build() {
            return new RecoveryConfig(this);
        }
    }
}

package cerence.ark.assistant.framework.updater;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateInfo implements Parcelable {
    public static final Parcelable.Creator<UpdateInfo> CREATOR = new Parcelable.Creator<UpdateInfo>() { // from class: cerence.ark.assistant.framework.updater.UpdateInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateInfo createFromParcel(Parcel parcel) {
            return new UpdateInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateInfo[] newArray(int i) {
            return new UpdateInfo[i];
        }
    };
    private final String mAttribute;
    private final String mDescription;
    private final String mNewVersion;
    private final String mOldVersion;
    private final long mUpdateSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    private UpdateInfo(Builder builder) {
        this.mAttribute = builder.mAttribute;
        this.mOldVersion = builder.mOldVersion;
        this.mNewVersion = builder.mNewVersion;
        this.mDescription = builder.mDescription;
        this.mUpdateSize = builder.mUpdateSize;
    }

    protected UpdateInfo(Parcel parcel) {
        this.mAttribute = parcel.readString();
        this.mOldVersion = parcel.readString();
        this.mNewVersion = parcel.readString();
        this.mDescription = parcel.readString();
        this.mUpdateSize = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAttribute);
        parcel.writeString(this.mOldVersion);
        parcel.writeString(this.mNewVersion);
        parcel.writeString(this.mDescription);
        parcel.writeLong(this.mUpdateSize);
    }

    public String getAttribute() {
        return this.mAttribute;
    }

    public String getOldVersion() {
        return this.mOldVersion;
    }

    public String getNewVersion() {
        return this.mNewVersion;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public long getUpdateSize() {
        return this.mUpdateSize;
    }

    public String toString() {
        return "UpdateInfo{mAttribute='" + this.mAttribute + "', mOldVersion='" + this.mOldVersion + "', mNewVersion='" + this.mNewVersion + "', mDescription='" + this.mDescription + "', mUpdateSize=" + this.mUpdateSize + '}';
    }

    public static final class Builder {
        private String mAttribute;
        private String mDescription;
        private String mNewVersion;
        private String mOldVersion;
        private long mUpdateSize;

        private Builder() {
        }

        public Builder setAttribute(String str) {
            this.mAttribute = str;
            return this;
        }

        public Builder setOldVersion(String str) {
            this.mOldVersion = str;
            return this;
        }

        public Builder setNewVersion(String str) {
            this.mNewVersion = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder setUpdateSize(long j) {
            this.mUpdateSize = j;
            return this;
        }

        public UpdateInfo build() {
            return new UpdateInfo(this);
        }
    }
}

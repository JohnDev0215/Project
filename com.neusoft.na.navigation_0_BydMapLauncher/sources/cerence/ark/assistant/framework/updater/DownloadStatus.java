package cerence.ark.assistant.framework.updater;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadStatus implements Parcelable {
    public static final Parcelable.Creator<DownloadStatus> CREATOR = new Parcelable.Creator<DownloadStatus>() { // from class: cerence.ark.assistant.framework.updater.DownloadStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadStatus createFromParcel(Parcel parcel) {
            return new DownloadStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadStatus[] newArray(int i) {
            return new DownloadStatus[i];
        }
    };
    private final String mAttribute;
    private final long mCurrentSize;
    private final String mDescription;
    private final boolean mIsDownloading;
    private final String mNewVersion;
    private final String mOldVersion;
    private final long mTotalSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    private DownloadStatus(Builder builder) {
        this.mAttribute = builder.mAttribute;
        this.mOldVersion = builder.mOldVersion;
        this.mNewVersion = builder.mNewVersion;
        this.mDescription = builder.mDescription;
        this.mCurrentSize = builder.mCurrentSize;
        this.mTotalSize = builder.mTotalSize;
        this.mIsDownloading = builder.mIsDownloading;
    }

    protected DownloadStatus(Parcel parcel) {
        this.mAttribute = parcel.readString();
        this.mOldVersion = parcel.readString();
        this.mNewVersion = parcel.readString();
        this.mDescription = parcel.readString();
        this.mCurrentSize = parcel.readLong();
        this.mTotalSize = parcel.readLong();
        this.mIsDownloading = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAttribute);
        parcel.writeString(this.mOldVersion);
        parcel.writeString(this.mNewVersion);
        parcel.writeString(this.mDescription);
        parcel.writeLong(this.mCurrentSize);
        parcel.writeLong(this.mTotalSize);
        parcel.writeByte(this.mIsDownloading ? (byte) 1 : (byte) 0);
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

    public long getCurrentSize() {
        return this.mCurrentSize;
    }

    public long getTotalSize() {
        return this.mTotalSize;
    }

    public boolean isDownloading() {
        return this.mIsDownloading;
    }

    public String toString() {
        return "DownloadStatus{mAttribute='" + this.mAttribute + "', mOldVersion='" + this.mOldVersion + "', mNewVersion='" + this.mNewVersion + "', mDescription='" + this.mDescription + "', mCurrentSize=" + this.mCurrentSize + ", mTotalSize=" + this.mTotalSize + ", mIsDownloading=" + this.mIsDownloading + '}';
    }

    public static final class Builder {
        private String mAttribute;
        private long mCurrentSize;
        private String mDescription;
        private boolean mIsDownloading;
        private String mNewVersion;
        private String mOldVersion;
        private long mTotalSize;

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

        public Builder setCurrentSize(long j) {
            this.mCurrentSize = j;
            return this;
        }

        public Builder setTotalSize(long j) {
            this.mTotalSize = j;
            return this;
        }

        public Builder setDownloading(boolean z) {
            this.mIsDownloading = z;
            return this;
        }

        public DownloadStatus build() {
            return new DownloadStatus(this);
        }
    }
}

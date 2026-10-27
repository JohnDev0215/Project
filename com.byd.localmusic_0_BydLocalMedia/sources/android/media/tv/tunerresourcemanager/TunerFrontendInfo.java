package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TunerFrontendInfo implements Parcelable {
    public static final Parcelable.Creator<TunerFrontendInfo> CREATOR = new Parcelable.Creator<TunerFrontendInfo>() { // from class: android.media.tv.tunerresourcemanager.TunerFrontendInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendInfo createFromParcel(Parcel parcel) {
            try {
                return new TunerFrontendInfo(parcel);
            } catch (Exception e) {
                Log.e(TunerFrontendInfo.TAG, "Exception creating TunerFrontendInfo from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendInfo[] newArray(int i) {
            return new TunerFrontendInfo[i];
        }
    };
    static final String TAG = "TunerFrontendInfo";
    private final int mExclusiveGroupId;
    private final int mFrontendType;
    private final int mId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TunerFrontendInfo(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mFrontendType = parcel.readInt();
        this.mExclusiveGroupId = parcel.readInt();
    }

    public TunerFrontendInfo(int i, int i2, int i3) {
        this.mId = i;
        this.mFrontendType = i2;
        this.mExclusiveGroupId = i3;
    }

    public int getId() {
        return this.mId;
    }

    public int getFrontendType() {
        return this.mFrontendType;
    }

    public int getExclusiveGroupId() {
        return this.mExclusiveGroupId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("TunerFrontendInfo {id=");
        sb.append(this.mId);
        sb.append(", frontendType=");
        sb.append(this.mFrontendType);
        sb.append(", exclusiveGroupId=");
        sb.append(this.mExclusiveGroupId);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mFrontendType);
        parcel.writeInt(this.mExclusiveGroupId);
    }
}

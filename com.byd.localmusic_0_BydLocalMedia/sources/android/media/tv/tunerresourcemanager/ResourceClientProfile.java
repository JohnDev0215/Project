package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class ResourceClientProfile implements Parcelable {
    public static final Parcelable.Creator<ResourceClientProfile> CREATOR = new Parcelable.Creator<ResourceClientProfile>() { // from class: android.media.tv.tunerresourcemanager.ResourceClientProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResourceClientProfile createFromParcel(Parcel parcel) {
            try {
                return new ResourceClientProfile(parcel);
            } catch (Exception e) {
                Log.e(ResourceClientProfile.TAG, "Exception creating ResourceClientProfile from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResourceClientProfile[] newArray(int i) {
            return new ResourceClientProfile[i];
        }
    };
    static final String TAG = "ResourceClientProfile";
    private final String mTvInputSessionId;
    private final int mUseCase;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ResourceClientProfile(Parcel parcel) {
        this.mTvInputSessionId = parcel.readString();
        this.mUseCase = parcel.readInt();
    }

    public ResourceClientProfile(String str, int i) {
        this.mTvInputSessionId = str;
        this.mUseCase = i;
    }

    public String getTvInputSessionId() {
        return this.mTvInputSessionId;
    }

    public int getUseCase() {
        return this.mUseCase;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("ResourceClientProfile {tvInputSessionId=");
        sb.append(this.mTvInputSessionId);
        sb.append(", useCase=");
        sb.append(this.mUseCase);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTvInputSessionId);
        parcel.writeInt(this.mUseCase);
    }
}

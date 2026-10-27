package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TunerFrontendRequest implements Parcelable {
    public static final Parcelable.Creator<TunerFrontendRequest> CREATOR = new Parcelable.Creator<TunerFrontendRequest>() { // from class: android.media.tv.tunerresourcemanager.TunerFrontendRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendRequest createFromParcel(Parcel parcel) {
            try {
                return new TunerFrontendRequest(parcel);
            } catch (Exception e) {
                Log.e(TunerFrontendRequest.TAG, "Exception creating TunerFrontendRequest from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendRequest[] newArray(int i) {
            return new TunerFrontendRequest[i];
        }
    };
    static final String TAG = "TunerFrontendRequest";
    private final int mClientId;
    private final int mFrontendType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TunerFrontendRequest(Parcel parcel) {
        this.mClientId = parcel.readInt();
        this.mFrontendType = parcel.readInt();
    }

    public TunerFrontendRequest(int i, int i2) {
        this.mClientId = i;
        this.mFrontendType = i2;
    }

    public int getClientId() {
        return this.mClientId;
    }

    public int getFrontendType() {
        return this.mFrontendType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("TunerFrontendRequest {clientId=").append(this.mClientId);
        sb.append(", frontendType=").append(this.mFrontendType);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mClientId);
        parcel.writeInt(this.mFrontendType);
    }
}

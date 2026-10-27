package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TunerLnbRequest implements Parcelable {
    public static final Parcelable.Creator<TunerLnbRequest> CREATOR = new Parcelable.Creator<TunerLnbRequest>() { // from class: android.media.tv.tunerresourcemanager.TunerLnbRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerLnbRequest createFromParcel(Parcel parcel) {
            try {
                return new TunerLnbRequest(parcel);
            } catch (Exception e) {
                Log.e(TunerLnbRequest.TAG, "Exception creating TunerLnbRequest from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerLnbRequest[] newArray(int i) {
            return new TunerLnbRequest[i];
        }
    };
    static final String TAG = "TunerLnbRequest";
    private final int mClientId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TunerLnbRequest(Parcel parcel) {
        this.mClientId = parcel.readInt();
    }

    public TunerLnbRequest(int i) {
        this.mClientId = i;
    }

    public int getClientId() {
        return this.mClientId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("TunerLnbRequest {clientId=").append(this.mClientId);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mClientId);
    }
}

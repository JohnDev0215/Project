package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TunerDemuxRequest implements Parcelable {
    public static final Parcelable.Creator<TunerDemuxRequest> CREATOR = new Parcelable.Creator<TunerDemuxRequest>() { // from class: android.media.tv.tunerresourcemanager.TunerDemuxRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerDemuxRequest createFromParcel(Parcel parcel) {
            try {
                return new TunerDemuxRequest(parcel);
            } catch (Exception e) {
                Log.e(TunerDemuxRequest.TAG, "Exception creating TunerDemuxRequest from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerDemuxRequest[] newArray(int i) {
            return new TunerDemuxRequest[i];
        }
    };
    static final String TAG = "TunerDemuxRequest";
    private final int mClientId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TunerDemuxRequest(Parcel parcel) {
        this.mClientId = parcel.readInt();
    }

    public TunerDemuxRequest(int i) {
        this.mClientId = i;
    }

    public int getClientId() {
        return this.mClientId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("TunerDemuxRequest {clientId=");
        sb.append(this.mClientId);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mClientId);
    }
}

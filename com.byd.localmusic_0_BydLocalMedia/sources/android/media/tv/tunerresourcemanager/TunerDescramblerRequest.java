package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class TunerDescramblerRequest implements Parcelable {
    public static final Parcelable.Creator<TunerDescramblerRequest> CREATOR = new Parcelable.Creator<TunerDescramblerRequest>() { // from class: android.media.tv.tunerresourcemanager.TunerDescramblerRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerDescramblerRequest createFromParcel(Parcel parcel) {
            try {
                return new TunerDescramblerRequest(parcel);
            } catch (Exception e) {
                Log.e(TunerDescramblerRequest.TAG, "Exception creating TunerDescramblerRequest from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerDescramblerRequest[] newArray(int i) {
            return new TunerDescramblerRequest[i];
        }
    };
    static final String TAG = "TunerDescramblerRequest";
    private final int mClientId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TunerDescramblerRequest(Parcel parcel) {
        this.mClientId = parcel.readInt();
    }

    public TunerDescramblerRequest(int i) {
        this.mClientId = i;
    }

    public int getClientId() {
        return this.mClientId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("TunerDescramblerRequest {clientId=");
        sb.append(this.mClientId);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mClientId);
    }
}

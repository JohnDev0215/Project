package android.media.tv.tunerresourcemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class CasSessionRequest implements Parcelable {
    public static final Parcelable.Creator<CasSessionRequest> CREATOR = new Parcelable.Creator<CasSessionRequest>() { // from class: android.media.tv.tunerresourcemanager.CasSessionRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CasSessionRequest createFromParcel(Parcel parcel) {
            try {
                return new CasSessionRequest(parcel);
            } catch (Exception e) {
                Log.e(CasSessionRequest.TAG, "Exception creating CasSessionRequest from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CasSessionRequest[] newArray(int i) {
            return new CasSessionRequest[i];
        }
    };
    static final String TAG = "CasSessionRequest";
    private final int mCasSystemId;
    private final int mClientId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CasSessionRequest(Parcel parcel) {
        this.mClientId = parcel.readInt();
        this.mCasSystemId = parcel.readInt();
    }

    public CasSessionRequest(int i, int i2) {
        this.mClientId = i;
        this.mCasSystemId = i2;
    }

    public int getClientId() {
        return this.mClientId;
    }

    public int getCasSystemId() {
        return this.mCasSystemId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("CasSessionRequest {clientId=").append(this.mClientId);
        sb.append(", casSystemId=").append(this.mCasSystemId);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mClientId);
        parcel.writeInt(this.mCasSystemId);
    }
}

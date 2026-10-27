package android.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class GnssRequest implements Parcelable {
    public static final Parcelable.Creator<GnssRequest> CREATOR = new Parcelable.Creator<GnssRequest>() { // from class: android.location.GnssRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssRequest createFromParcel(Parcel parcel) {
            return new GnssRequest(parcel.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssRequest[] newArray(int i) {
            return new GnssRequest[i];
        }
    };
    private final boolean mFullTracking;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private GnssRequest(boolean z) {
        this.mFullTracking = z;
    }

    public boolean isFullTracking() {
        return this.mFullTracking;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GnssRequest[");
        sb.append("FullTracking=").append(this.mFullTracking);
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && (obj instanceof GnssRequest) && this.mFullTracking == ((GnssRequest) obj).mFullTracking;
    }

    public int hashCode() {
        return this.mFullTracking ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.mFullTracking);
    }

    public static final class Builder {
        private boolean mFullTracking;

        public Builder() {
        }

        public Builder(GnssRequest gnssRequest) {
            this.mFullTracking = gnssRequest.isFullTracking();
        }

        public Builder setFullTracking(boolean z) {
            this.mFullTracking = z;
            return this;
        }

        public GnssRequest build() {
            return new GnssRequest(this.mFullTracking);
        }
    }
}

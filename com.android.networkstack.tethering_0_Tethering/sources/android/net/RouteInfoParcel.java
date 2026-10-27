package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RouteInfoParcel implements Parcelable {
    public static final Parcelable.Creator<RouteInfoParcel> CREATOR = new Parcelable.Creator<RouteInfoParcel>() { // from class: android.net.RouteInfoParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfoParcel createFromParcel(Parcel parcel) {
            RouteInfoParcel routeInfoParcel = new RouteInfoParcel();
            routeInfoParcel.readFromParcel(parcel);
            return routeInfoParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfoParcel[] newArray(int i) {
            return new RouteInfoParcel[i];
        }
    };
    public String destination;
    public String ifName;
    public int mtu;
    public String nextHop;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.destination);
        parcel.writeString(this.ifName);
        parcel.writeString(this.nextHop);
        parcel.writeInt(this.mtu);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.destination = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.ifName = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.nextHop = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.mtu = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DataStallReportParcelable implements Parcelable {
    public static final Parcelable.Creator<DataStallReportParcelable> CREATOR = new Parcelable.Creator<DataStallReportParcelable>() { // from class: android.net.DataStallReportParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataStallReportParcelable createFromParcel(Parcel parcel) {
            DataStallReportParcelable dataStallReportParcelable = new DataStallReportParcelable();
            dataStallReportParcelable.readFromParcel(parcel);
            return dataStallReportParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataStallReportParcelable[] newArray(int i) {
            return new DataStallReportParcelable[i];
        }
    };
    public long timestampMillis = 0;
    public int detectionMethod = 1;
    public int tcpPacketFailRate = 2;
    public int tcpMetricsCollectionPeriodMillis = 3;
    public int dnsConsecutiveTimeouts = 4;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeLong(this.timestampMillis);
        parcel.writeInt(this.detectionMethod);
        parcel.writeInt(this.tcpPacketFailRate);
        parcel.writeInt(this.tcpMetricsCollectionPeriodMillis);
        parcel.writeInt(this.dnsConsecutiveTimeouts);
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
            this.timestampMillis = parcel.readLong();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.detectionMethod = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.tcpPacketFailRate = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.tcpMetricsCollectionPeriodMillis = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.dnsConsecutiveTimeouts = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}

package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class GsiProgress implements Parcelable {
    public static final Parcelable.Creator<GsiProgress> CREATOR = new Parcelable.Creator<GsiProgress>() { // from class: android.gsi.GsiProgress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GsiProgress createFromParcel(Parcel parcel) {
            GsiProgress gsiProgress = new GsiProgress();
            gsiProgress.readFromParcel(parcel);
            return gsiProgress;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GsiProgress[] newArray(int i) {
            return new GsiProgress[i];
        }
    };
    public long bytes_processed;
    public int status;
    public String step;
    public long total_bytes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.step);
        parcel.writeInt(this.status);
        parcel.writeLong(this.bytes_processed);
        parcel.writeLong(this.total_bytes);
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
            this.step = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.status = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.bytes_processed = parcel.readLong();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.total_bytes = parcel.readLong();
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

package android.net.apf;

import android.annotation.SystemApi;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.R;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class ApfCapabilities implements Parcelable {
    public static final Parcelable.Creator<ApfCapabilities> CREATOR = new Parcelable.Creator<ApfCapabilities>() { // from class: android.net.apf.ApfCapabilities.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApfCapabilities createFromParcel(Parcel parcel) {
            return new ApfCapabilities(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApfCapabilities[] newArray(int i) {
            return new ApfCapabilities[i];
        }
    };
    public final int apfPacketFormat;
    public final int apfVersionSupported;
    public final int maximumApfProgramSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ApfCapabilities(int i, int i2, int i3) {
        this.apfVersionSupported = i;
        this.maximumApfProgramSize = i2;
        this.apfPacketFormat = i3;
    }

    private ApfCapabilities(Parcel parcel) {
        this.apfVersionSupported = parcel.readInt();
        this.maximumApfProgramSize = parcel.readInt();
        this.apfPacketFormat = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.apfVersionSupported);
        parcel.writeInt(this.maximumApfProgramSize);
        parcel.writeInt(this.apfPacketFormat);
    }

    public String toString() {
        return String.format("%s{version: %d, maxSize: %d, format: %d}", getClass().getSimpleName(), Integer.valueOf(this.apfVersionSupported), Integer.valueOf(this.maximumApfProgramSize), Integer.valueOf(this.apfPacketFormat));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ApfCapabilities)) {
            return false;
        }
        ApfCapabilities apfCapabilities = (ApfCapabilities) obj;
        return this.apfVersionSupported == apfCapabilities.apfVersionSupported && this.maximumApfProgramSize == apfCapabilities.maximumApfProgramSize && this.apfPacketFormat == apfCapabilities.apfPacketFormat;
    }

    public boolean hasDataAccess() {
        return this.apfVersionSupported >= 4;
    }

    public static boolean getApfDrop8023Frames() {
        return Resources.getSystem().getBoolean(R.bool.config_apfDrop802_3Frames);
    }

    public static int[] getApfEtherTypeBlackList() {
        return Resources.getSystem().getIntArray(R.array.config_apfEthTypeBlackList);
    }
}

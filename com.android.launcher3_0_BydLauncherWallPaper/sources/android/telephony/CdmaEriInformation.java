package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public final class CdmaEriInformation implements Parcelable {
    public static final Parcelable.Creator<CdmaEriInformation> CREATOR = new Parcelable.Creator<CdmaEriInformation>() { // from class: android.telephony.CdmaEriInformation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CdmaEriInformation createFromParcel(Parcel parcel) {
            return new CdmaEriInformation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CdmaEriInformation[] newArray(int i) {
            return new CdmaEriInformation[i];
        }
    };
    public static final int ERI_FLASH = 2;
    public static final int ERI_ICON_MODE_FLASH = 1;
    public static final int ERI_ICON_MODE_NORMAL = 0;
    public static final int ERI_OFF = 1;
    public static final int ERI_ON = 0;
    private int mIconIndex;
    private int mIconMode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EriIconIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EriIconMode {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CdmaEriInformation(int i, int i2) {
        this.mIconIndex = i;
        this.mIconMode = i2;
    }

    public int getEriIconIndex() {
        return this.mIconIndex;
    }

    public void setEriIconIndex(int i) {
        this.mIconIndex = i;
    }

    public int getEriIconMode() {
        return this.mIconMode;
    }

    public void setEriIconMode(int i) {
        this.mIconMode = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIconIndex);
        parcel.writeInt(this.mIconMode);
    }

    private CdmaEriInformation(Parcel parcel) {
        this.mIconIndex = parcel.readInt();
        this.mIconMode = parcel.readInt();
    }
}

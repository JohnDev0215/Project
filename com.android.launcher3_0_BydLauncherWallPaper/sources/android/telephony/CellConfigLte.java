package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CellConfigLte implements Parcelable {
    public static final Parcelable.Creator<CellConfigLte> CREATOR = new Parcelable.Creator<CellConfigLte>() { // from class: android.telephony.CellConfigLte.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellConfigLte[] newArray(int i) {
            return new CellConfigLte[0];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellConfigLte createFromParcel(Parcel parcel) {
            return new CellConfigLte(parcel);
        }
    };
    private final boolean mIsEndcAvailable;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellConfigLte() {
        this.mIsEndcAvailable = false;
    }

    public CellConfigLte(android.hardware.radio.V1_4.CellConfigLte cellConfigLte) {
        this.mIsEndcAvailable = cellConfigLte.isEndcAvailable;
    }

    public CellConfigLte(boolean z) {
        this.mIsEndcAvailable = z;
    }

    public CellConfigLte(CellConfigLte cellConfigLte) {
        this.mIsEndcAvailable = cellConfigLte.mIsEndcAvailable;
    }

    boolean isEndcAvailable() {
        return this.mIsEndcAvailable;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsEndcAvailable));
    }

    public boolean equals(Object obj) {
        return (obj instanceof CellConfigLte) && this.mIsEndcAvailable == ((CellConfigLte) obj).mIsEndcAvailable;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.mIsEndcAvailable);
    }

    public String toString() {
        return getClass().getName() + " :{" + (" isEndcAvailable = " + this.mIsEndcAvailable) + " }";
    }

    private CellConfigLte(Parcel parcel) {
        this.mIsEndcAvailable = parcel.readBoolean();
    }
}

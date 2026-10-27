package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PhoneCapability implements Parcelable {
    public static final Parcelable.Creator<PhoneCapability> CREATOR;
    public static final PhoneCapability DEFAULT_DSDS_CAPABILITY;
    public static final PhoneCapability DEFAULT_SSSS_CAPABILITY;
    public final List<ModemInfo> logicalModemList;
    public final int max5G;
    public final int maxActiveData;
    public final int maxActiveVoiceCalls;
    public final boolean validationBeforeSwitchSupported;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        ModemInfo modemInfo = new ModemInfo(0, 0, true, true);
        ModemInfo modemInfo2 = new ModemInfo(1, 0, true, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(modemInfo);
        arrayList.add(modemInfo2);
        DEFAULT_DSDS_CAPABILITY = new PhoneCapability(1, 1, 0, arrayList, false);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(modemInfo);
        DEFAULT_SSSS_CAPABILITY = new PhoneCapability(1, 1, 0, arrayList2, false);
        CREATOR = new Parcelable.Creator() { // from class: android.telephony.PhoneCapability.1
            @Override // android.os.Parcelable.Creator
            public PhoneCapability createFromParcel(Parcel parcel) {
                return new PhoneCapability(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public PhoneCapability[] newArray(int i) {
                return new PhoneCapability[i];
            }
        };
    }

    public PhoneCapability(int i, int i2, int i3, List<ModemInfo> list, boolean z) {
        this.maxActiveVoiceCalls = i;
        this.maxActiveData = i2;
        this.max5G = i3;
        this.logicalModemList = list == null ? new ArrayList<>() : list;
        this.validationBeforeSwitchSupported = z;
    }

    public String toString() {
        return "maxActiveVoiceCalls=" + this.maxActiveVoiceCalls + " maxActiveData=" + this.maxActiveData + " max5G=" + this.max5G + "logicalModemList:" + Arrays.toString(this.logicalModemList.toArray());
    }

    private PhoneCapability(Parcel parcel) {
        this.maxActiveVoiceCalls = parcel.readInt();
        this.maxActiveData = parcel.readInt();
        this.max5G = parcel.readInt();
        this.validationBeforeSwitchSupported = parcel.readBoolean();
        ArrayList arrayList = new ArrayList();
        this.logicalModemList = arrayList;
        parcel.readList(arrayList, ModemInfo.class.getClassLoader());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.maxActiveVoiceCalls), Integer.valueOf(this.maxActiveData), Integer.valueOf(this.max5G), this.logicalModemList, Boolean.valueOf(this.validationBeforeSwitchSupported));
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PhoneCapability) || hashCode() != obj.hashCode()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PhoneCapability phoneCapability = (PhoneCapability) obj;
        return this.maxActiveVoiceCalls == phoneCapability.maxActiveVoiceCalls && this.maxActiveData == phoneCapability.maxActiveData && this.max5G == phoneCapability.max5G && this.validationBeforeSwitchSupported == phoneCapability.validationBeforeSwitchSupported && this.logicalModemList.equals(phoneCapability.logicalModemList);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.maxActiveVoiceCalls);
        parcel.writeInt(this.maxActiveData);
        parcel.writeInt(this.max5G);
        parcel.writeBoolean(this.validationBeforeSwitchSupported);
        parcel.writeList(this.logicalModemList);
    }
}

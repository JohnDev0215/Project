package android.telephony;

import android.bluetooth.BluetoothHidDevice;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.midi.MidiConstants;
import com.android.internal.telephony.DctConstants;
import com.android.internal.telephony.RILConstants;

/* JADX INFO: loaded from: classes3.dex */
public class RadioAccessFamily implements Parcelable {
    private static final int CDMA = 72;
    public static final Parcelable.Creator<RadioAccessFamily> CREATOR = new Parcelable.Creator<RadioAccessFamily>() { // from class: android.telephony.RadioAccessFamily.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioAccessFamily createFromParcel(Parcel parcel) {
            return new RadioAccessFamily(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioAccessFamily[] newArray(int i) {
            return new RadioAccessFamily[i];
        }
    };
    private static final int EVDO = 10288;
    private static final int GSM = 32771;
    private static final int HS = 17280;
    private static final int LTE = 266240;
    private static final int NR = 524288;
    public static final int RAF_1xRTT = 64;
    public static final int RAF_EDGE = 2;
    public static final int RAF_EHRPD = 8192;
    public static final int RAF_EVDO_0 = 16;
    public static final int RAF_EVDO_A = 32;
    public static final int RAF_EVDO_B = 2048;
    public static final int RAF_GPRS = 1;
    public static final int RAF_GSM = 32768;
    public static final int RAF_HSDPA = 128;
    public static final int RAF_HSPA = 512;
    public static final int RAF_HSPAP = 16384;
    public static final int RAF_HSUPA = 256;
    public static final int RAF_IS95A = 8;
    public static final int RAF_IS95B = 8;
    public static final int RAF_LTE = 4096;
    public static final int RAF_LTE_CA = 262144;
    public static final int RAF_NR = 524288;
    public static final int RAF_TD_SCDMA = 65536;
    public static final int RAF_UMTS = 4;
    public static final int RAF_UNKNOWN = 0;
    private static final int WCDMA = 17284;
    private int mPhoneId;
    private int mRadioAccessFamily;

    private static int getAdjustedRaf(int i) {
        if ((i & 32771) > 0) {
            i |= 32771;
        }
        if ((i & WCDMA) > 0) {
            i |= WCDMA;
        }
        if ((i & 72) > 0) {
            i |= 72;
        }
        if ((i & EVDO) > 0) {
            i |= EVDO;
        }
        if ((i & 266240) > 0) {
            i |= 266240;
        }
        return (i & 524288) > 0 ? i | 524288 : i;
    }

    public static int getRafFromNetworkType(int i) {
        switch (i) {
        }
        return 50055;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RadioAccessFamily(int i, int i2) {
        this.mPhoneId = i;
        this.mRadioAccessFamily = i2;
    }

    public int getPhoneId() {
        return this.mPhoneId;
    }

    public int getRadioAccessFamily() {
        return this.mRadioAccessFamily;
    }

    public String toString() {
        return "{ mPhoneId = " + this.mPhoneId + ", mRadioAccessFamily = " + this.mRadioAccessFamily + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPhoneId);
        parcel.writeInt(this.mRadioAccessFamily);
    }

    public static int getNetworkTypeFromRaf(int i) {
        switch (getAdjustedRaf(i)) {
            case 72:
                return 5;
            case EVDO /* 10288 */:
                return 6;
            case 10360:
                return 4;
            case WCDMA /* 17284 */:
                return 2;
            case 32771:
                return 1;
            case 50055:
                return 0;
            case 60415:
                return 7;
            case 65536:
                return 13;
            case 82820:
                return 14;
            case 98307:
                return 16;
            case 115591:
                return 18;
            case 125951:
                return 21;
            case 266240:
                return 11;
            case 276600:
                return 8;
            case 283524:
                return 12;
            case 316295:
                return 9;
            case 326655:
                return 10;
            case 331776:
                return 15;
            case 349060:
                return 19;
            case 364547:
                return 17;
            case 381831:
                return 20;
            case 392191:
                return 22;
            case 524288:
                return 23;
            case 790528:
                return 24;
            case 800888:
                return 25;
            case 807812:
                return 28;
            case 840583:
                return 26;
            case 850943:
                return 27;
            case 856064:
                return 29;
            case 873348:
                return 31;
            case 888835:
                return 30;
            case 906119:
                return 32;
            case 916479:
                return 33;
            default:
                return RILConstants.PREFERRED_NETWORK_MODE;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int singleRafTypeFromString(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2039427040:
                if (str.equals("LTE_CA")) {
                    b = 0;
                }
                break;
            case -908593671:
                if (str.equals("TD_SCDMA")) {
                    b = 1;
                }
                break;
            case 2315:
                if (str.equals("HS")) {
                    b = 2;
                }
                break;
            case 2500:
                if (str.equals("NR")) {
                    b = 3;
                }
                break;
            case 70881:
                if (str.equals("GSM")) {
                    b = 4;
                }
                break;
            case 75709:
                if (str.equals(DctConstants.RAT_NAME_LTE)) {
                    b = 5;
                }
                break;
            case 2063797:
                if (str.equals("CDMA")) {
                    b = 6;
                }
                break;
            case 2123197:
                if (str.equals("EDGE")) {
                    b = 7;
                }
                break;
            case 2140412:
                if (str.equals("EVDO")) {
                    b = 8;
                }
                break;
            case 2194666:
                if (str.equals("GPRS")) {
                    b = 9;
                }
                break;
            case 2227260:
                if (str.equals("HSPA")) {
                    b = 10;
                }
                break;
            case 2608919:
                if (str.equals("UMTS")) {
                    b = 11;
                }
                break;
            case 47955627:
                if (str.equals("1XRTT")) {
                    b = 12;
                }
                break;
            case 65949251:
                if (str.equals("EHRPD")) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                }
                break;
            case 69034058:
                if (str.equals("HSDPA")) {
                    b = BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                }
                break;
            case 69045140:
                if (str.equals("HSPAP")) {
                    b = MidiConstants.STATUS_CHANNEL_MASK;
                }
                break;
            case 69050395:
                if (str.equals("HSUPA")) {
                    b = 16;
                }
                break;
            case 69946171:
                if (str.equals("IS95A")) {
                    b = 17;
                }
                break;
            case 69946172:
                if (str.equals("IS95B")) {
                    b = 18;
                }
                break;
            case 82410124:
                if (str.equals("WCDMA")) {
                    b = 19;
                }
                break;
            case 2056938925:
                if (str.equals("EVDO_0")) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT;
                }
                break;
            case 2056938942:
                if (str.equals("EVDO_A")) {
                    b = 21;
                }
                break;
            case 2056938943:
                if (str.equals("EVDO_B")) {
                    b = 22;
                }
                break;
        }
        switch (b) {
            case 0:
                return 262144;
            case 1:
                return 65536;
            case 2:
                return HS;
            case 3:
                return 524288;
            case 4:
                return 32768;
            case 5:
                return 4096;
            case 6:
                return 72;
            case 7:
                return 2;
            case 8:
                return EVDO;
            case 9:
                return 1;
            case 10:
                return 512;
            case 11:
                return 4;
            case 12:
                return 64;
            case 13:
                return 8192;
            case 14:
                return 128;
            case 15:
                return 16384;
            case 16:
                return 256;
            case 17:
            case 18:
                return 8;
            case 19:
                return WCDMA;
            case 20:
                return 16;
            case 21:
                return 32;
            case 22:
                return 2048;
            default:
                return 0;
        }
    }

    public static int rafTypeFromString(String str) {
        int i = 0;
        for (String str2 : str.toUpperCase().split("\\|")) {
            int iSingleRafTypeFromString = singleRafTypeFromString(str2.trim());
            if (iSingleRafTypeFromString == 0) {
                return iSingleRafTypeFromString;
            }
            i |= iSingleRafTypeFromString;
        }
        return i;
    }

    public static int compare(long j, long j2) {
        long[] jArr = {524288, TelephonyManager.NETWORK_CLASS_BITMASK_4G, TelephonyManager.NETWORK_CLASS_BITMASK_3G, TelephonyManager.NETWORK_CLASS_BITMASK_2G};
        long j3 = (~j2) & j;
        long j4 = (~j) & j2;
        for (int i = 0; i < 4; i++) {
            long j5 = jArr[i];
            int i2 = (j3 & j5) != 0 ? 1 : 0;
            if ((j5 & j4) != 0) {
                i2--;
            }
            if (i2 != 0) {
                return i2;
            }
        }
        return Long.bitCount(j) - Long.bitCount(j2);
    }
}

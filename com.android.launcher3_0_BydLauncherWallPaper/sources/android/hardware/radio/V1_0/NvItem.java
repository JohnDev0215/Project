package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class NvItem {
    public static final int CDMA_1X_ADVANCED_ENABLED = 57;
    public static final int CDMA_ACCOLC = 4;
    public static final int CDMA_BC10 = 52;
    public static final int CDMA_BC14 = 53;
    public static final int CDMA_EHRPD_ENABLED = 58;
    public static final int CDMA_EHRPD_FORCED = 59;
    public static final int CDMA_MDN = 3;
    public static final int CDMA_MEID = 1;
    public static final int CDMA_MIN = 2;
    public static final int CDMA_PRL_VERSION = 51;
    public static final int CDMA_SO68 = 54;
    public static final int CDMA_SO73_COP0 = 55;
    public static final int CDMA_SO73_COP1TO7 = 56;
    public static final int DEVICE_MSL = 11;
    public static final int LTE_BAND_ENABLE_25 = 71;
    public static final int LTE_BAND_ENABLE_26 = 72;
    public static final int LTE_BAND_ENABLE_41 = 73;
    public static final int LTE_HIDDEN_BAND_PRIORITY_25 = 77;
    public static final int LTE_HIDDEN_BAND_PRIORITY_26 = 78;
    public static final int LTE_HIDDEN_BAND_PRIORITY_41 = 79;
    public static final int LTE_SCAN_PRIORITY_25 = 74;
    public static final int LTE_SCAN_PRIORITY_26 = 75;
    public static final int LTE_SCAN_PRIORITY_41 = 76;
    public static final int MIP_PROFILE_AAA_AUTH = 33;
    public static final int MIP_PROFILE_AAA_SPI = 39;
    public static final int MIP_PROFILE_HA_AUTH = 34;
    public static final int MIP_PROFILE_HA_SPI = 38;
    public static final int MIP_PROFILE_HOME_ADDRESS = 32;
    public static final int MIP_PROFILE_MN_AAA_SS = 41;
    public static final int MIP_PROFILE_MN_HA_SS = 40;
    public static final int MIP_PROFILE_NAI = 31;
    public static final int MIP_PROFILE_PRI_HA_ADDR = 35;
    public static final int MIP_PROFILE_REV_TUN_PREF = 37;
    public static final int MIP_PROFILE_SEC_HA_ADDR = 36;
    public static final int OMADM_HFA_LEVEL = 18;
    public static final int RTN_ACTIVATION_DATE = 13;
    public static final int RTN_LIFE_CALLS = 15;
    public static final int RTN_LIFE_DATA_RX = 17;
    public static final int RTN_LIFE_DATA_TX = 16;
    public static final int RTN_LIFE_TIMER = 14;
    public static final int RTN_RECONDITIONED_STATUS = 12;

    public static final String toString(int i) {
        return i == 1 ? "CDMA_MEID" : i == 2 ? "CDMA_MIN" : i == 3 ? "CDMA_MDN" : i == 4 ? "CDMA_ACCOLC" : i == 11 ? "DEVICE_MSL" : i == 12 ? "RTN_RECONDITIONED_STATUS" : i == 13 ? "RTN_ACTIVATION_DATE" : i == 14 ? "RTN_LIFE_TIMER" : i == 15 ? "RTN_LIFE_CALLS" : i == 16 ? "RTN_LIFE_DATA_TX" : i == 17 ? "RTN_LIFE_DATA_RX" : i == 18 ? "OMADM_HFA_LEVEL" : i == 31 ? "MIP_PROFILE_NAI" : i == 32 ? "MIP_PROFILE_HOME_ADDRESS" : i == 33 ? "MIP_PROFILE_AAA_AUTH" : i == 34 ? "MIP_PROFILE_HA_AUTH" : i == 35 ? "MIP_PROFILE_PRI_HA_ADDR" : i == 36 ? "MIP_PROFILE_SEC_HA_ADDR" : i == 37 ? "MIP_PROFILE_REV_TUN_PREF" : i == 38 ? "MIP_PROFILE_HA_SPI" : i == 39 ? "MIP_PROFILE_AAA_SPI" : i == 40 ? "MIP_PROFILE_MN_HA_SS" : i == 41 ? "MIP_PROFILE_MN_AAA_SS" : i == 51 ? "CDMA_PRL_VERSION" : i == 52 ? "CDMA_BC10" : i == 53 ? "CDMA_BC14" : i == 54 ? "CDMA_SO68" : i == 55 ? "CDMA_SO73_COP0" : i == 56 ? "CDMA_SO73_COP1TO7" : i == 57 ? "CDMA_1X_ADVANCED_ENABLED" : i == 58 ? "CDMA_EHRPD_ENABLED" : i == 59 ? "CDMA_EHRPD_FORCED" : i == 71 ? "LTE_BAND_ENABLE_25" : i == 72 ? "LTE_BAND_ENABLE_26" : i == 73 ? "LTE_BAND_ENABLE_41" : i == 74 ? "LTE_SCAN_PRIORITY_25" : i == 75 ? "LTE_SCAN_PRIORITY_26" : i == 76 ? "LTE_SCAN_PRIORITY_41" : i == 77 ? "LTE_HIDDEN_BAND_PRIORITY_25" : i == 78 ? "LTE_HIDDEN_BAND_PRIORITY_26" : i == 79 ? "LTE_HIDDEN_BAND_PRIORITY_41" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CDMA_MEID");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CDMA_MIN");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CDMA_MDN");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CDMA_ACCOLC");
            i2 |= 4;
        }
        if ((i & 11) == 11) {
            arrayList.add("DEVICE_MSL");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("RTN_RECONDITIONED_STATUS");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("RTN_ACTIVATION_DATE");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("RTN_LIFE_TIMER");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("RTN_LIFE_CALLS");
            i2 |= 15;
        }
        if ((i & 16) == 16) {
            arrayList.add("RTN_LIFE_DATA_TX");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("RTN_LIFE_DATA_RX");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("OMADM_HFA_LEVEL");
            i2 |= 18;
        }
        if ((i & 31) == 31) {
            arrayList.add("MIP_PROFILE_NAI");
            i2 |= 31;
        }
        if ((i & 32) == 32) {
            arrayList.add("MIP_PROFILE_HOME_ADDRESS");
            i2 |= 32;
        }
        if ((i & 33) == 33) {
            arrayList.add("MIP_PROFILE_AAA_AUTH");
            i2 |= 33;
        }
        if ((i & 34) == 34) {
            arrayList.add("MIP_PROFILE_HA_AUTH");
            i2 |= 34;
        }
        if ((i & 35) == 35) {
            arrayList.add("MIP_PROFILE_PRI_HA_ADDR");
            i2 |= 35;
        }
        if ((i & 36) == 36) {
            arrayList.add("MIP_PROFILE_SEC_HA_ADDR");
            i2 |= 36;
        }
        if ((i & 37) == 37) {
            arrayList.add("MIP_PROFILE_REV_TUN_PREF");
            i2 |= 37;
        }
        if ((i & 38) == 38) {
            arrayList.add("MIP_PROFILE_HA_SPI");
            i2 |= 38;
        }
        if ((i & 39) == 39) {
            arrayList.add("MIP_PROFILE_AAA_SPI");
            i2 |= 39;
        }
        if ((i & 40) == 40) {
            arrayList.add("MIP_PROFILE_MN_HA_SS");
            i2 |= 40;
        }
        if ((i & 41) == 41) {
            arrayList.add("MIP_PROFILE_MN_AAA_SS");
            i2 |= 41;
        }
        if ((i & 51) == 51) {
            arrayList.add("CDMA_PRL_VERSION");
            i2 |= 51;
        }
        if ((i & 52) == 52) {
            arrayList.add("CDMA_BC10");
            i2 |= 52;
        }
        if ((i & 53) == 53) {
            arrayList.add("CDMA_BC14");
            i2 |= 53;
        }
        if ((i & 54) == 54) {
            arrayList.add("CDMA_SO68");
            i2 |= 54;
        }
        if ((i & 55) == 55) {
            arrayList.add("CDMA_SO73_COP0");
            i2 |= 55;
        }
        if ((i & 56) == 56) {
            arrayList.add("CDMA_SO73_COP1TO7");
            i2 |= 56;
        }
        if ((i & 57) == 57) {
            arrayList.add("CDMA_1X_ADVANCED_ENABLED");
            i2 |= 57;
        }
        if ((i & 58) == 58) {
            arrayList.add("CDMA_EHRPD_ENABLED");
            i2 |= 58;
        }
        if ((i & 59) == 59) {
            arrayList.add("CDMA_EHRPD_FORCED");
            i2 |= 59;
        }
        if ((i & 71) == 71) {
            arrayList.add("LTE_BAND_ENABLE_25");
            i2 |= 71;
        }
        if ((i & 72) == 72) {
            arrayList.add("LTE_BAND_ENABLE_26");
            i2 |= 72;
        }
        if ((i & 73) == 73) {
            arrayList.add("LTE_BAND_ENABLE_41");
            i2 |= 73;
        }
        if ((i & 74) == 74) {
            arrayList.add("LTE_SCAN_PRIORITY_25");
            i2 |= 74;
        }
        if ((i & 75) == 75) {
            arrayList.add("LTE_SCAN_PRIORITY_26");
            i2 |= 75;
        }
        if ((i & 76) == 76) {
            arrayList.add("LTE_SCAN_PRIORITY_41");
            i2 |= 76;
        }
        if ((i & 77) == 77) {
            arrayList.add("LTE_HIDDEN_BAND_PRIORITY_25");
            i2 |= 77;
        }
        if ((i & 78) == 78) {
            arrayList.add("LTE_HIDDEN_BAND_PRIORITY_26");
            i2 |= 78;
        }
        if ((i & 79) == 79) {
            arrayList.add("LTE_HIDDEN_BAND_PRIORITY_41");
            i2 |= 79;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}

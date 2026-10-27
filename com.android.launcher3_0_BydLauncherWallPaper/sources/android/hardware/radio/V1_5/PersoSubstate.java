package android.hardware.radio.V1_5;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class PersoSubstate {
    public static final int IN_PROGRESS = 1;
    public static final int READY = 2;
    public static final int RUIM_CORPORATE = 16;
    public static final int RUIM_CORPORATE_PUK = 22;
    public static final int RUIM_HRPD = 15;
    public static final int RUIM_HRPD_PUK = 21;
    public static final int RUIM_NETWORK1 = 13;
    public static final int RUIM_NETWORK1_PUK = 19;
    public static final int RUIM_NETWORK2 = 14;
    public static final int RUIM_NETWORK2_PUK = 20;
    public static final int RUIM_RUIM = 18;
    public static final int RUIM_RUIM_PUK = 24;
    public static final int RUIM_SERVICE_PROVIDER = 17;
    public static final int RUIM_SERVICE_PROVIDER_PUK = 23;
    public static final int SIM_CORPORATE = 5;
    public static final int SIM_CORPORATE_PUK = 10;
    public static final int SIM_ICCID = 29;
    public static final int SIM_ICCID_PUK = 30;
    public static final int SIM_IMPI = 31;
    public static final int SIM_IMPI_PUK = 32;
    public static final int SIM_NETWORK = 3;
    public static final int SIM_NETWORK_PUK = 8;
    public static final int SIM_NETWORK_SUBSET = 4;
    public static final int SIM_NETWORK_SUBSET_PUK = 9;
    public static final int SIM_NS_SP = 33;
    public static final int SIM_NS_SP_PUK = 34;
    public static final int SIM_SERVICE_PROVIDER = 6;
    public static final int SIM_SERVICE_PROVIDER_PUK = 11;
    public static final int SIM_SIM = 7;
    public static final int SIM_SIM_PUK = 12;
    public static final int SIM_SPN = 25;
    public static final int SIM_SPN_PUK = 26;
    public static final int SIM_SP_EHPLMN = 27;
    public static final int SIM_SP_EHPLMN_PUK = 28;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "IN_PROGRESS" : i == 2 ? "READY" : i == 3 ? "SIM_NETWORK" : i == 4 ? "SIM_NETWORK_SUBSET" : i == 5 ? "SIM_CORPORATE" : i == 6 ? "SIM_SERVICE_PROVIDER" : i == 7 ? "SIM_SIM" : i == 8 ? "SIM_NETWORK_PUK" : i == 9 ? "SIM_NETWORK_SUBSET_PUK" : i == 10 ? "SIM_CORPORATE_PUK" : i == 11 ? "SIM_SERVICE_PROVIDER_PUK" : i == 12 ? "SIM_SIM_PUK" : i == 13 ? "RUIM_NETWORK1" : i == 14 ? "RUIM_NETWORK2" : i == 15 ? "RUIM_HRPD" : i == 16 ? "RUIM_CORPORATE" : i == 17 ? "RUIM_SERVICE_PROVIDER" : i == 18 ? "RUIM_RUIM" : i == 19 ? "RUIM_NETWORK1_PUK" : i == 20 ? "RUIM_NETWORK2_PUK" : i == 21 ? "RUIM_HRPD_PUK" : i == 22 ? "RUIM_CORPORATE_PUK" : i == 23 ? "RUIM_SERVICE_PROVIDER_PUK" : i == 24 ? "RUIM_RUIM_PUK" : i == 25 ? "SIM_SPN" : i == 26 ? "SIM_SPN_PUK" : i == 27 ? "SIM_SP_EHPLMN" : i == 28 ? "SIM_SP_EHPLMN_PUK" : i == 29 ? "SIM_ICCID" : i == 30 ? "SIM_ICCID_PUK" : i == 31 ? "SIM_IMPI" : i == 32 ? "SIM_IMPI_PUK" : i == 33 ? "SIM_NS_SP" : i == 34 ? "SIM_NS_SP_PUK" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("IN_PROGRESS");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("READY");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SIM_NETWORK");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SIM_NETWORK_SUBSET");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SIM_CORPORATE");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("SIM_SERVICE_PROVIDER");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("SIM_SIM");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("SIM_NETWORK_PUK");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("SIM_NETWORK_SUBSET_PUK");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("SIM_CORPORATE_PUK");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("SIM_SERVICE_PROVIDER_PUK");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("SIM_SIM_PUK");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("RUIM_NETWORK1");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("RUIM_NETWORK2");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("RUIM_HRPD");
            i2 |= 15;
        }
        if ((i & 16) == 16) {
            arrayList.add("RUIM_CORPORATE");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("RUIM_SERVICE_PROVIDER");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("RUIM_RUIM");
            i2 |= 18;
        }
        if ((i & 19) == 19) {
            arrayList.add("RUIM_NETWORK1_PUK");
            i2 |= 19;
        }
        if ((i & 20) == 20) {
            arrayList.add("RUIM_NETWORK2_PUK");
            i2 |= 20;
        }
        if ((i & 21) == 21) {
            arrayList.add("RUIM_HRPD_PUK");
            i2 |= 21;
        }
        if ((i & 22) == 22) {
            arrayList.add("RUIM_CORPORATE_PUK");
            i2 |= 22;
        }
        if ((i & 23) == 23) {
            arrayList.add("RUIM_SERVICE_PROVIDER_PUK");
            i2 |= 23;
        }
        if ((i & 24) == 24) {
            arrayList.add("RUIM_RUIM_PUK");
            i2 |= 24;
        }
        if ((i & 25) == 25) {
            arrayList.add("SIM_SPN");
            i2 |= 25;
        }
        if ((i & 26) == 26) {
            arrayList.add("SIM_SPN_PUK");
            i2 |= 26;
        }
        if ((i & 27) == 27) {
            arrayList.add("SIM_SP_EHPLMN");
            i2 |= 27;
        }
        if ((i & 28) == 28) {
            arrayList.add("SIM_SP_EHPLMN_PUK");
            i2 |= 28;
        }
        if ((i & 29) == 29) {
            arrayList.add("SIM_ICCID");
            i2 |= 29;
        }
        if ((i & 30) == 30) {
            arrayList.add("SIM_ICCID_PUK");
            i2 |= 30;
        }
        if ((i & 31) == 31) {
            arrayList.add("SIM_IMPI");
            i2 |= 31;
        }
        if ((i & 32) == 32) {
            arrayList.add("SIM_IMPI_PUK");
            i2 |= 32;
        }
        if ((i & 33) == 33) {
            arrayList.add("SIM_NS_SP");
            i2 |= 33;
        }
        if ((i & 34) == 34) {
            arrayList.add("SIM_NS_SP_PUK");
            i2 |= 34;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}

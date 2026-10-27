package android.telephony;

/* JADX INFO: loaded from: classes3.dex */
public class AccessNetworkUtils {
    public static final int INVALID_BAND = -1;

    public static int getDuplexModeForEutranBand(int i) {
        if (i == -1 || i > 88) {
            return 0;
        }
        if (i >= 65) {
            return 1;
        }
        if (i >= 33) {
            return 2;
        }
        return i >= 1 ? 1 : 0;
    }

    public static int getOperatingBandForEarfcn(int i) {
        if (i > 70645) {
            return -1;
        }
        if (i >= 70596) {
            return 88;
        }
        if (i >= 70546) {
            return 87;
        }
        if (i >= 70366) {
            return 85;
        }
        if (i > 69465) {
            return -1;
        }
        if (i >= 69036) {
            return 74;
        }
        if (i >= 68986) {
            return 73;
        }
        if (i >= 68936) {
            return 72;
        }
        if (i >= 68586) {
            return 71;
        }
        if (i >= 68336) {
            return 70;
        }
        if (i > 67835) {
            return -1;
        }
        if (i >= 67536) {
            return 68;
        }
        if (i >= 67366) {
            return -1;
        }
        if (i >= 66436) {
            return 66;
        }
        if (i >= 65536) {
            return 65;
        }
        if (i > 60254) {
            return -1;
        }
        if (i >= 60140) {
            return 53;
        }
        if (i >= 59140) {
            return 52;
        }
        if (i >= 59090) {
            return 51;
        }
        if (i >= 58240) {
            return 50;
        }
        if (i >= 56740) {
            return 49;
        }
        if (i >= 55240) {
            return 48;
        }
        if (i >= 54540) {
            return 47;
        }
        if (i >= 46790) {
            return 46;
        }
        if (i >= 46590) {
            return 45;
        }
        if (i >= 45590) {
            return 44;
        }
        if (i >= 43590) {
            return 43;
        }
        if (i >= 41590) {
            return 42;
        }
        if (i >= 39650) {
            return 41;
        }
        if (i >= 38650) {
            return 40;
        }
        if (i >= 38250) {
            return 39;
        }
        if (i >= 37750) {
            return 38;
        }
        if (i >= 37550) {
            return 37;
        }
        if (i >= 36950) {
            return 36;
        }
        if (i >= 36350) {
            return 35;
        }
        if (i >= 36200) {
            return 34;
        }
        if (i >= 36000) {
            return 33;
        }
        if (i > 10359 || i >= 9920) {
            return -1;
        }
        if (i >= 9870) {
            return 31;
        }
        if (i >= 9770) {
            return 30;
        }
        if (i >= 9660) {
            return -1;
        }
        if (i >= 9210) {
            return 28;
        }
        if (i >= 9040) {
            return 27;
        }
        if (i >= 8690) {
            return 26;
        }
        if (i >= 8040) {
            return 25;
        }
        if (i >= 7700) {
            return 24;
        }
        if (i >= 7500) {
            return 23;
        }
        if (i >= 6600) {
            return 22;
        }
        if (i >= 6450) {
            return 21;
        }
        if (i >= 6150) {
            return 20;
        }
        if (i >= 6000) {
            return 19;
        }
        if (i >= 5850) {
            return 18;
        }
        if (i >= 5730) {
            return 17;
        }
        if (i > 5379) {
            return -1;
        }
        if (i >= 5280) {
            return 14;
        }
        if (i >= 5180) {
            return 13;
        }
        if (i >= 5010) {
            return 12;
        }
        if (i >= 4750) {
            return 11;
        }
        if (i >= 4150) {
            return 10;
        }
        if (i >= 3800) {
            return 9;
        }
        if (i >= 3450) {
            return 8;
        }
        if (i >= 2750) {
            return 7;
        }
        if (i >= 2650) {
            return 6;
        }
        if (i >= 2400) {
            return 5;
        }
        if (i >= 1950) {
            return 4;
        }
        if (i >= 1200) {
            return 3;
        }
        if (i >= 600) {
            return 2;
        }
        return i >= 0 ? 1 : -1;
    }

    private AccessNetworkUtils() {
    }
}

package android.hardware.radio.V1_5;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class NgranBands {
    public static final int BAND_1 = 1;
    public static final int BAND_12 = 12;
    public static final int BAND_14 = 14;
    public static final int BAND_18 = 18;
    public static final int BAND_2 = 2;
    public static final int BAND_20 = 20;
    public static final int BAND_25 = 25;
    public static final int BAND_257 = 257;
    public static final int BAND_258 = 258;
    public static final int BAND_260 = 260;
    public static final int BAND_261 = 261;
    public static final int BAND_28 = 28;
    public static final int BAND_29 = 29;
    public static final int BAND_3 = 3;
    public static final int BAND_30 = 30;
    public static final int BAND_34 = 34;
    public static final int BAND_38 = 38;
    public static final int BAND_39 = 39;
    public static final int BAND_40 = 40;
    public static final int BAND_41 = 41;
    public static final int BAND_48 = 48;
    public static final int BAND_5 = 5;
    public static final int BAND_50 = 50;
    public static final int BAND_51 = 51;
    public static final int BAND_65 = 65;
    public static final int BAND_66 = 66;
    public static final int BAND_7 = 7;
    public static final int BAND_70 = 70;
    public static final int BAND_71 = 71;
    public static final int BAND_74 = 74;
    public static final int BAND_75 = 75;
    public static final int BAND_76 = 76;
    public static final int BAND_77 = 77;
    public static final int BAND_78 = 78;
    public static final int BAND_79 = 79;
    public static final int BAND_8 = 8;
    public static final int BAND_80 = 80;
    public static final int BAND_81 = 81;
    public static final int BAND_82 = 82;
    public static final int BAND_83 = 83;
    public static final int BAND_84 = 84;
    public static final int BAND_86 = 86;
    public static final int BAND_89 = 89;
    public static final int BAND_90 = 90;
    public static final int BAND_91 = 91;
    public static final int BAND_92 = 92;
    public static final int BAND_93 = 93;
    public static final int BAND_94 = 94;
    public static final int BAND_95 = 95;

    public static final String toString(int i) {
        return i == 1 ? "BAND_1" : i == 2 ? "BAND_2" : i == 3 ? "BAND_3" : i == 5 ? "BAND_5" : i == 7 ? "BAND_7" : i == 8 ? "BAND_8" : i == 12 ? "BAND_12" : i == 14 ? "BAND_14" : i == 18 ? "BAND_18" : i == 20 ? "BAND_20" : i == 25 ? "BAND_25" : i == 28 ? "BAND_28" : i == 29 ? "BAND_29" : i == 30 ? "BAND_30" : i == 34 ? "BAND_34" : i == 38 ? "BAND_38" : i == 39 ? "BAND_39" : i == 40 ? "BAND_40" : i == 41 ? "BAND_41" : i == 48 ? "BAND_48" : i == 50 ? "BAND_50" : i == 51 ? "BAND_51" : i == 65 ? "BAND_65" : i == 66 ? "BAND_66" : i == 70 ? "BAND_70" : i == 71 ? "BAND_71" : i == 74 ? "BAND_74" : i == 75 ? "BAND_75" : i == 76 ? "BAND_76" : i == 77 ? "BAND_77" : i == 78 ? "BAND_78" : i == 79 ? "BAND_79" : i == 80 ? "BAND_80" : i == 81 ? "BAND_81" : i == 82 ? "BAND_82" : i == 83 ? "BAND_83" : i == 84 ? "BAND_84" : i == 86 ? "BAND_86" : i == 89 ? "BAND_89" : i == 90 ? "BAND_90" : i == 91 ? "BAND_91" : i == 92 ? "BAND_92" : i == 93 ? "BAND_93" : i == 94 ? "BAND_94" : i == 95 ? "BAND_95" : i == 257 ? "BAND_257" : i == 258 ? "BAND_258" : i == 260 ? "BAND_260" : i == 261 ? "BAND_261" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("BAND_1");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("BAND_2");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("BAND_3");
            i2 |= 3;
        }
        if ((i & 5) == 5) {
            arrayList.add("BAND_5");
            i2 |= 5;
        }
        if ((i & 7) == 7) {
            arrayList.add("BAND_7");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("BAND_8");
            i2 |= 8;
        }
        if ((i & 12) == 12) {
            arrayList.add("BAND_12");
            i2 |= 12;
        }
        if ((i & 14) == 14) {
            arrayList.add("BAND_14");
            i2 |= 14;
        }
        if ((i & 18) == 18) {
            arrayList.add("BAND_18");
            i2 |= 18;
        }
        if ((i & 20) == 20) {
            arrayList.add("BAND_20");
            i2 |= 20;
        }
        if ((i & 25) == 25) {
            arrayList.add("BAND_25");
            i2 |= 25;
        }
        if ((i & 28) == 28) {
            arrayList.add("BAND_28");
            i2 |= 28;
        }
        if ((i & 29) == 29) {
            arrayList.add("BAND_29");
            i2 |= 29;
        }
        if ((i & 30) == 30) {
            arrayList.add("BAND_30");
            i2 |= 30;
        }
        if ((i & 34) == 34) {
            arrayList.add("BAND_34");
            i2 |= 34;
        }
        if ((i & 38) == 38) {
            arrayList.add("BAND_38");
            i2 |= 38;
        }
        if ((i & 39) == 39) {
            arrayList.add("BAND_39");
            i2 |= 39;
        }
        if ((i & 40) == 40) {
            arrayList.add("BAND_40");
            i2 |= 40;
        }
        if ((i & 41) == 41) {
            arrayList.add("BAND_41");
            i2 |= 41;
        }
        if ((i & 48) == 48) {
            arrayList.add("BAND_48");
            i2 |= 48;
        }
        if ((i & 50) == 50) {
            arrayList.add("BAND_50");
            i2 |= 50;
        }
        if ((i & 51) == 51) {
            arrayList.add("BAND_51");
            i2 |= 51;
        }
        if ((i & 65) == 65) {
            arrayList.add("BAND_65");
            i2 |= 65;
        }
        if ((i & 66) == 66) {
            arrayList.add("BAND_66");
            i2 |= 66;
        }
        if ((i & 70) == 70) {
            arrayList.add("BAND_70");
            i2 |= 70;
        }
        if ((i & 71) == 71) {
            arrayList.add("BAND_71");
            i2 |= 71;
        }
        if ((i & 74) == 74) {
            arrayList.add("BAND_74");
            i2 |= 74;
        }
        if ((i & 75) == 75) {
            arrayList.add("BAND_75");
            i2 |= 75;
        }
        if ((i & 76) == 76) {
            arrayList.add("BAND_76");
            i2 |= 76;
        }
        if ((i & 77) == 77) {
            arrayList.add("BAND_77");
            i2 |= 77;
        }
        if ((i & 78) == 78) {
            arrayList.add("BAND_78");
            i2 |= 78;
        }
        if ((i & 79) == 79) {
            arrayList.add("BAND_79");
            i2 |= 79;
        }
        if ((i & 80) == 80) {
            arrayList.add("BAND_80");
            i2 |= 80;
        }
        if ((i & 81) == 81) {
            arrayList.add("BAND_81");
            i2 |= 81;
        }
        if ((i & 82) == 82) {
            arrayList.add("BAND_82");
            i2 |= 82;
        }
        if ((i & 83) == 83) {
            arrayList.add("BAND_83");
            i2 |= 83;
        }
        if ((i & 84) == 84) {
            arrayList.add("BAND_84");
            i2 |= 84;
        }
        if ((i & 86) == 86) {
            arrayList.add("BAND_86");
            i2 |= 86;
        }
        if ((i & 89) == 89) {
            arrayList.add("BAND_89");
            i2 |= 89;
        }
        if ((i & 90) == 90) {
            arrayList.add("BAND_90");
            i2 |= 90;
        }
        if ((i & 91) == 91) {
            arrayList.add("BAND_91");
            i2 |= 91;
        }
        if ((i & 92) == 92) {
            arrayList.add("BAND_92");
            i2 |= 92;
        }
        if ((i & 93) == 93) {
            arrayList.add("BAND_93");
            i2 |= 93;
        }
        if ((i & 94) == 94) {
            arrayList.add("BAND_94");
            i2 |= 94;
        }
        if ((i & 95) == 95) {
            arrayList.add("BAND_95");
            i2 |= 95;
        }
        if ((i & 257) == 257) {
            arrayList.add("BAND_257");
            i2 |= 257;
        }
        if ((i & 258) == 258) {
            arrayList.add("BAND_258");
            i2 |= 258;
        }
        if ((i & 260) == 260) {
            arrayList.add("BAND_260");
            i2 |= 260;
        }
        if ((i & 261) == 261) {
            arrayList.add("BAND_261");
            i2 |= 261;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}

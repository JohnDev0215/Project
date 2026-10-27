package android.telephony;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public final class AccessNetworkConstants {

    @SystemApi
    public static final int TRANSPORT_TYPE_INVALID = -1;
    public static final int TRANSPORT_TYPE_WLAN = 2;
    public static final int TRANSPORT_TYPE_WWAN = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RadioAccessNetworkType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransportType {
    }

    public static String transportTypeToString(int i) {
        return i != 1 ? i != 2 ? Integer.toString(i) : "WLAN" : "WWAN";
    }

    public static final class AccessNetworkType {
        public static final int CDMA2000 = 4;
        public static final int EUTRAN = 3;
        public static final int GERAN = 1;
        public static final int IWLAN = 5;
        public static final int NGRAN = 6;
        public static final int UNKNOWN = 0;
        public static final int UTRAN = 2;

        private AccessNetworkType() {
        }

        public static String toString(int i) {
            switch (i) {
                case 0:
                    return "UNKNOWN";
                case 1:
                    return "GERAN";
                case 2:
                    return "UTRAN";
                case 3:
                    return "EUTRAN";
                case 4:
                    return "CDMA2000";
                case 5:
                    return "IWLAN";
                case 6:
                    return "NGRAN";
                default:
                    return Integer.toString(i);
            }
        }
    }

    public static final class GeranBand {
        public static final int BAND_450 = 3;
        public static final int BAND_480 = 4;
        public static final int BAND_710 = 5;
        public static final int BAND_750 = 6;
        public static final int BAND_850 = 8;
        public static final int BAND_DCS1800 = 12;
        public static final int BAND_E900 = 10;
        public static final int BAND_ER900 = 14;
        public static final int BAND_P900 = 9;
        public static final int BAND_PCS1900 = 13;
        public static final int BAND_R900 = 11;
        public static final int BAND_T380 = 1;
        public static final int BAND_T410 = 2;
        public static final int BAND_T810 = 7;

        private GeranBand() {
        }
    }

    public static final class UtranBand {
        public static final int BAND_1 = 1;
        public static final int BAND_10 = 10;
        public static final int BAND_11 = 11;
        public static final int BAND_12 = 12;
        public static final int BAND_13 = 13;
        public static final int BAND_14 = 14;
        public static final int BAND_19 = 19;
        public static final int BAND_2 = 2;
        public static final int BAND_20 = 20;
        public static final int BAND_21 = 21;
        public static final int BAND_22 = 22;
        public static final int BAND_25 = 25;
        public static final int BAND_26 = 26;
        public static final int BAND_3 = 3;
        public static final int BAND_4 = 4;
        public static final int BAND_5 = 5;
        public static final int BAND_6 = 6;
        public static final int BAND_7 = 7;
        public static final int BAND_8 = 8;
        public static final int BAND_9 = 9;
        public static final int BAND_A = 101;
        public static final int BAND_B = 102;
        public static final int BAND_C = 103;
        public static final int BAND_D = 104;
        public static final int BAND_E = 105;
        public static final int BAND_F = 106;

        private UtranBand() {
        }
    }

    public static final class EutranBand {
        public static final int BAND_1 = 1;
        public static final int BAND_10 = 10;
        public static final int BAND_11 = 11;
        public static final int BAND_12 = 12;
        public static final int BAND_13 = 13;
        public static final int BAND_14 = 14;
        public static final int BAND_17 = 17;
        public static final int BAND_18 = 18;
        public static final int BAND_19 = 19;
        public static final int BAND_2 = 2;
        public static final int BAND_20 = 20;
        public static final int BAND_21 = 21;
        public static final int BAND_22 = 22;
        public static final int BAND_23 = 23;
        public static final int BAND_24 = 24;
        public static final int BAND_25 = 25;
        public static final int BAND_26 = 26;
        public static final int BAND_27 = 27;
        public static final int BAND_28 = 28;
        public static final int BAND_3 = 3;
        public static final int BAND_30 = 30;
        public static final int BAND_31 = 31;
        public static final int BAND_33 = 33;
        public static final int BAND_34 = 34;
        public static final int BAND_35 = 35;
        public static final int BAND_36 = 36;
        public static final int BAND_37 = 37;
        public static final int BAND_38 = 38;
        public static final int BAND_39 = 39;
        public static final int BAND_4 = 4;
        public static final int BAND_40 = 40;
        public static final int BAND_41 = 41;
        public static final int BAND_42 = 42;
        public static final int BAND_43 = 43;
        public static final int BAND_44 = 44;
        public static final int BAND_45 = 45;
        public static final int BAND_46 = 46;
        public static final int BAND_47 = 47;
        public static final int BAND_48 = 48;
        public static final int BAND_49 = 49;
        public static final int BAND_5 = 5;
        public static final int BAND_50 = 50;
        public static final int BAND_51 = 51;
        public static final int BAND_52 = 52;
        public static final int BAND_53 = 53;
        public static final int BAND_6 = 6;
        public static final int BAND_65 = 65;
        public static final int BAND_66 = 66;
        public static final int BAND_68 = 68;
        public static final int BAND_7 = 7;
        public static final int BAND_70 = 70;
        public static final int BAND_71 = 71;
        public static final int BAND_72 = 72;
        public static final int BAND_73 = 73;
        public static final int BAND_74 = 74;
        public static final int BAND_8 = 8;
        public static final int BAND_85 = 85;
        public static final int BAND_87 = 87;
        public static final int BAND_88 = 88;
        public static final int BAND_9 = 9;

        private EutranBand() {
        }
    }

    public static final class CdmaBands {
        public static final int BAND_0 = 1;
        public static final int BAND_1 = 2;
        public static final int BAND_10 = 11;
        public static final int BAND_11 = 12;
        public static final int BAND_12 = 13;
        public static final int BAND_13 = 14;
        public static final int BAND_14 = 15;
        public static final int BAND_15 = 16;
        public static final int BAND_16 = 17;
        public static final int BAND_17 = 18;
        public static final int BAND_18 = 19;
        public static final int BAND_19 = 20;
        public static final int BAND_2 = 3;
        public static final int BAND_20 = 21;
        public static final int BAND_21 = 22;
        public static final int BAND_3 = 4;
        public static final int BAND_4 = 5;
        public static final int BAND_5 = 6;
        public static final int BAND_6 = 7;
        public static final int BAND_7 = 8;
        public static final int BAND_8 = 9;
        public static final int BAND_9 = 10;

        private CdmaBands() {
        }
    }

    public static final class NgranBands {
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

        @SystemApi
        public static final int FREQUENCY_RANGE_GROUP_1 = 1;

        @SystemApi
        public static final int FREQUENCY_RANGE_GROUP_2 = 2;

        @SystemApi
        public static final int FREQUENCY_RANGE_GROUP_UNKNOWN = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface FrequencyRangeGroup {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface NgranBand {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:35:0x003f A[FALL_THROUGH, RETURN] */
        @android.annotation.SystemApi
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getFrequencyRangeGroup(int r3) {
            /*
                r0 = 1
                if (r3 == r0) goto L3f
                r1 = 2
                if (r3 == r1) goto L3f
                r2 = 3
                if (r3 == r2) goto L3f
                r2 = 7
                if (r3 == r2) goto L3f
                r2 = 8
                if (r3 == r2) goto L3f
                r2 = 50
                if (r3 == r2) goto L3f
                r2 = 51
                if (r3 == r2) goto L3f
                r2 = 65
                if (r3 == r2) goto L3f
                r2 = 66
                if (r3 == r2) goto L3f
                r2 = 70
                if (r3 == r2) goto L3f
                r2 = 71
                if (r3 == r2) goto L3f
                r2 = 257(0x101, float:3.6E-43)
                if (r3 == r2) goto L3e
                r2 = 258(0x102, float:3.62E-43)
                if (r3 == r2) goto L3e
                switch(r3) {
                    case 5: goto L3f;
                    case 12: goto L3f;
                    case 14: goto L3f;
                    case 18: goto L3f;
                    case 20: goto L3f;
                    case 25: goto L3f;
                    case 34: goto L3f;
                    case 48: goto L3f;
                    case 74: goto L3f;
                    case 75: goto L3f;
                    case 76: goto L3f;
                    case 77: goto L3f;
                    case 78: goto L3f;
                    case 79: goto L3f;
                    case 80: goto L3f;
                    case 81: goto L3f;
                    case 82: goto L3f;
                    case 83: goto L3f;
                    case 84: goto L3f;
                    case 86: goto L3f;
                    case 260: goto L3e;
                    case 261: goto L3e;
                    default: goto L33;
                }
            L33:
                switch(r3) {
                    case 28: goto L3f;
                    case 29: goto L3f;
                    case 30: goto L3f;
                    default: goto L36;
                }
            L36:
                switch(r3) {
                    case 38: goto L3f;
                    case 39: goto L3f;
                    case 40: goto L3f;
                    case 41: goto L3f;
                    default: goto L39;
                }
            L39:
                switch(r3) {
                    case 89: goto L3f;
                    case 90: goto L3f;
                    case 91: goto L3f;
                    case 92: goto L3f;
                    case 93: goto L3f;
                    case 94: goto L3f;
                    case 95: goto L3f;
                    default: goto L3c;
                }
            L3c:
                r3 = 0
                return r3
            L3e:
                return r1
            L3f:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.telephony.AccessNetworkConstants.NgranBands.getFrequencyRangeGroup(int):int");
        }

        private NgranBands() {
        }
    }

    private AccessNetworkConstants() {
    }
}

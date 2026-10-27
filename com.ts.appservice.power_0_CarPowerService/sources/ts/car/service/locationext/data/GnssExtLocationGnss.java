package ts.car.service.locationext.data;

/* JADX INFO: loaded from: classes.dex */
public class GnssExtLocationGnss {
    public static final int LOC_MODE_BEIDOU = 2;
    public static final int LOC_MODE_FUSED = 3;
    public static final int LOC_MODE_GPS = 1;
    public static final int LOC_MODE_UNKNOWN = 0;

    public enum LocationGnss {
        UNKNOWN(0),
        GPS(1),
        BEIDOU(2),
        FUSED(3);

        private int mLocMode;

        LocationGnss(int mode) {
            this.mLocMode = mode;
        }

        public static LocationGnss get(int mode) {
            if (mode == 0) {
                return UNKNOWN;
            }
            if (mode == 1) {
                return GPS;
            }
            if (mode == 2) {
                return BEIDOU;
            }
            return FUSED;
        }

        public int getmode() {
            return this.mLocMode;
        }
    }
}

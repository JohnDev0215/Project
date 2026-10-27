package android.location;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class GnssStatus {
    public static final int CONSTELLATION_BEIDOU = 5;
    public static final int CONSTELLATION_COUNT = 8;
    public static final int CONSTELLATION_GALILEO = 6;
    public static final int CONSTELLATION_GLONASS = 3;
    public static final int CONSTELLATION_GPS = 1;
    public static final int CONSTELLATION_IRNSS = 7;
    public static final int CONSTELLATION_QZSS = 4;
    public static final int CONSTELLATION_SBAS = 2;
    private static final int CONSTELLATION_TYPE_MASK = 15;
    private static final int CONSTELLATION_TYPE_SHIFT_WIDTH = 8;
    public static final int CONSTELLATION_UNKNOWN = 0;
    private static final int SVID_FLAGS_HAS_ALMANAC_DATA = 2;
    private static final int SVID_FLAGS_HAS_BASEBAND_CN0 = 16;
    private static final int SVID_FLAGS_HAS_CARRIER_FREQUENCY = 8;
    private static final int SVID_FLAGS_HAS_EPHEMERIS_DATA = 1;
    private static final int SVID_FLAGS_NONE = 0;
    private static final int SVID_FLAGS_USED_IN_FIX = 4;
    private static final int SVID_SHIFT_WIDTH = 12;
    private final float[] mAzimuths;
    private final float[] mBasebandCn0DbHzs;
    private final float[] mCarrierFrequencies;
    private final float[] mCn0DbHzs;
    private final float[] mElevations;
    private final int mSvCount;
    private final int[] mSvidWithFlags;

    public static abstract class Callback {
        public void onFirstFix(int i) {
        }

        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConstellationType {
    }

    public static GnssStatus wrap(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) {
        return new GnssStatus(i, iArr, fArr, fArr2, fArr3, fArr4, fArr5);
    }

    private GnssStatus(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) {
        this.mSvCount = i;
        this.mSvidWithFlags = iArr;
        this.mCn0DbHzs = fArr;
        this.mElevations = fArr2;
        this.mAzimuths = fArr3;
        this.mCarrierFrequencies = fArr4;
        this.mBasebandCn0DbHzs = fArr5;
    }

    public int getSatelliteCount() {
        return this.mSvCount;
    }

    public int getConstellationType(int i) {
        return (this.mSvidWithFlags[i] >> 8) & 15;
    }

    public int getSvid(int i) {
        return this.mSvidWithFlags[i] >> 12;
    }

    public float getCn0DbHz(int i) {
        return this.mCn0DbHzs[i];
    }

    public float getElevationDegrees(int i) {
        return this.mElevations[i];
    }

    public float getAzimuthDegrees(int i) {
        return this.mAzimuths[i];
    }

    public boolean hasEphemerisData(int i) {
        return (this.mSvidWithFlags[i] & 1) != 0;
    }

    public boolean hasAlmanacData(int i) {
        return (this.mSvidWithFlags[i] & 2) != 0;
    }

    public boolean usedInFix(int i) {
        return (this.mSvidWithFlags[i] & 4) != 0;
    }

    public boolean hasCarrierFrequencyHz(int i) {
        return (this.mSvidWithFlags[i] & 8) != 0;
    }

    public float getCarrierFrequencyHz(int i) {
        return this.mCarrierFrequencies[i];
    }

    public boolean hasBasebandCn0DbHz(int i) {
        return (this.mSvidWithFlags[i] & 16) != 0;
    }

    public float getBasebandCn0DbHz(int i) {
        return this.mBasebandCn0DbHzs[i];
    }

    public static String constellationTypeToString(int i) {
        switch (i) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "GPS";
            case 2:
                return "SBAS";
            case 3:
                return "GLONASS";
            case 4:
                return "QZSS";
            case 5:
                return "BEIDOU";
            case 6:
                return "GALILEO";
            case 7:
                return "IRNSS";
            default:
                return Integer.toString(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GnssStatus)) {
            return false;
        }
        GnssStatus gnssStatus = (GnssStatus) obj;
        return this.mSvCount == gnssStatus.mSvCount && Arrays.equals(this.mSvidWithFlags, gnssStatus.mSvidWithFlags) && Arrays.equals(this.mCn0DbHzs, gnssStatus.mCn0DbHzs) && Arrays.equals(this.mElevations, gnssStatus.mElevations) && Arrays.equals(this.mAzimuths, gnssStatus.mAzimuths) && Arrays.equals(this.mCarrierFrequencies, gnssStatus.mCarrierFrequencies) && Arrays.equals(this.mBasebandCn0DbHzs, gnssStatus.mBasebandCn0DbHzs);
    }

    public int hashCode() {
        return (((Objects.hash(Integer.valueOf(this.mSvCount)) * 31) + Arrays.hashCode(this.mSvidWithFlags)) * 31) + Arrays.hashCode(this.mCn0DbHzs);
    }

    public static final class Builder {
        private final ArrayList<GnssSvInfo> mSatellites = new ArrayList<>();

        public Builder addSatellite(int i, int i2, float f, float f2, float f3, boolean z, boolean z2, boolean z3, boolean z4, float f4, boolean z5, float f5) {
            this.mSatellites.add(new GnssSvInfo(i, i2, f, f2, f3, z, z2, z3, z4, f4, z5, f5));
            return this;
        }

        public Builder clearSatellites() {
            this.mSatellites.clear();
            return this;
        }

        public GnssStatus build() {
            int size = this.mSatellites.size();
            int[] iArr = new int[size];
            float[] fArr = new float[size];
            float[] fArr2 = new float[size];
            float[] fArr3 = new float[size];
            float[] fArr4 = new float[size];
            float[] fArr5 = new float[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = this.mSatellites.get(i).mSvidWithFlags;
            }
            for (int i2 = 0; i2 < size; i2++) {
                fArr[i2] = this.mSatellites.get(i2).mCn0DbHz;
            }
            for (int i3 = 0; i3 < size; i3++) {
                fArr2[i3] = this.mSatellites.get(i3).mElevation;
            }
            for (int i4 = 0; i4 < size; i4++) {
                fArr3[i4] = this.mSatellites.get(i4).mAzimuth;
            }
            for (int i5 = 0; i5 < size; i5++) {
                fArr4[i5] = this.mSatellites.get(i5).mCarrierFrequency;
            }
            for (int i6 = 0; i6 < size; i6++) {
                fArr5[i6] = this.mSatellites.get(i6).mBasebandCn0DbHz;
            }
            return GnssStatus.wrap(size, iArr, fArr, fArr2, fArr3, fArr4, fArr5);
        }
    }

    private static class GnssSvInfo {
        private final float mAzimuth;
        private final float mBasebandCn0DbHz;
        private final float mCarrierFrequency;
        private final float mCn0DbHz;
        private final float mElevation;
        private final int mSvidWithFlags;

        private GnssSvInfo(int i, int i2, float f, float f2, float f3, boolean z, boolean z2, boolean z3, boolean z4, float f4, boolean z5, float f5) {
            this.mSvidWithFlags = ((i & 15) << 8) | (i2 << 12) | (z ? 1 : 0) | (z2 ? 2 : 0) | (z3 ? 4 : 0) | (z4 ? 8 : 0) | (z5 ? 16 : 0);
            this.mCn0DbHz = f;
            this.mElevation = f2;
            this.mAzimuth = f3;
            this.mCarrierFrequency = z4 ? f4 : 0.0f;
            this.mBasebandCn0DbHz = z5 ? f5 : 0.0f;
        }
    }
}

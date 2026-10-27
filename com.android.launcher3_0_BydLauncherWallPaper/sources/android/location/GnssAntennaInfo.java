package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class GnssAntennaInfo implements Parcelable {
    public static final Parcelable.Creator<GnssAntennaInfo> CREATOR = new Parcelable.Creator<GnssAntennaInfo>() { // from class: android.location.GnssAntennaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssAntennaInfo createFromParcel(Parcel parcel) {
            double d = parcel.readDouble();
            ClassLoader classLoader = getClass().getClassLoader();
            return new GnssAntennaInfo(d, (PhaseCenterOffset) parcel.readParcelable(classLoader), (SphericalCorrections) parcel.readParcelable(classLoader), (SphericalCorrections) parcel.readParcelable(classLoader));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssAntennaInfo[] newArray(int i) {
            return new GnssAntennaInfo[i];
        }
    };
    private final double mCarrierFrequencyMHz;
    private final PhaseCenterOffset mPhaseCenterOffset;
    private final SphericalCorrections mPhaseCenterVariationCorrections;
    private final SphericalCorrections mSignalGainCorrections;

    public interface Listener {
        void onGnssAntennaInfoReceived(List<GnssAntennaInfo> list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static final class PhaseCenterOffset implements Parcelable {
        public static final Parcelable.Creator<PhaseCenterOffset> CREATOR = new Parcelable.Creator<PhaseCenterOffset>() { // from class: android.location.GnssAntennaInfo.PhaseCenterOffset.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PhaseCenterOffset createFromParcel(Parcel parcel) {
                return new PhaseCenterOffset(parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PhaseCenterOffset[] newArray(int i) {
                return new PhaseCenterOffset[i];
            }
        };
        private final double mOffsetXMm;
        private final double mOffsetXUncertaintyMm;
        private final double mOffsetYMm;
        private final double mOffsetYUncertaintyMm;
        private final double mOffsetZMm;
        private final double mOffsetZUncertaintyMm;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PhaseCenterOffset(double d, double d2, double d3, double d4, double d5, double d6) {
            this.mOffsetXMm = d;
            this.mOffsetYMm = d3;
            this.mOffsetZMm = d5;
            this.mOffsetXUncertaintyMm = d2;
            this.mOffsetYUncertaintyMm = d4;
            this.mOffsetZUncertaintyMm = d6;
        }

        public double getXOffsetMm() {
            return this.mOffsetXMm;
        }

        public double getXOffsetUncertaintyMm() {
            return this.mOffsetXUncertaintyMm;
        }

        public double getYOffsetMm() {
            return this.mOffsetYMm;
        }

        public double getYOffsetUncertaintyMm() {
            return this.mOffsetYUncertaintyMm;
        }

        public double getZOffsetMm() {
            return this.mOffsetZMm;
        }

        public double getZOffsetUncertaintyMm() {
            return this.mOffsetZUncertaintyMm;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(this.mOffsetXMm);
            parcel.writeDouble(this.mOffsetXUncertaintyMm);
            parcel.writeDouble(this.mOffsetYMm);
            parcel.writeDouble(this.mOffsetYUncertaintyMm);
            parcel.writeDouble(this.mOffsetZMm);
            parcel.writeDouble(this.mOffsetZUncertaintyMm);
        }

        public String toString() {
            return "PhaseCenterOffset{OffsetXMm=" + this.mOffsetXMm + " +/-" + this.mOffsetXUncertaintyMm + ", OffsetYMm=" + this.mOffsetYMm + " +/-" + this.mOffsetYUncertaintyMm + ", OffsetZMm=" + this.mOffsetZMm + " +/-" + this.mOffsetZUncertaintyMm + '}';
        }
    }

    public static final class SphericalCorrections implements Parcelable {
        public static final Parcelable.Creator<SphericalCorrections> CREATOR = new Parcelable.Creator<SphericalCorrections>() { // from class: android.location.GnssAntennaInfo.SphericalCorrections.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SphericalCorrections createFromParcel(Parcel parcel) {
                return new SphericalCorrections(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SphericalCorrections[] newArray(int i) {
                return new SphericalCorrections[i];
            }
        };
        private final double[][] mCorrectionUncertainties;
        private final double[][] mCorrections;
        private final double mDeltaPhi;
        private final double mDeltaTheta;
        private final int mNumColumns;
        private final int mNumRows;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SphericalCorrections(double[][] dArr, double[][] dArr2) {
            if (dArr.length != dArr2.length || dArr[0].length != dArr2[0].length) {
                throw new IllegalArgumentException("Correction and correction uncertainty arrays must have the same dimensions.");
            }
            int length = dArr.length;
            this.mNumRows = length;
            if (length < 1) {
                throw new IllegalArgumentException("Arrays must have at least one row.");
            }
            int length2 = dArr[0].length;
            this.mNumColumns = length2;
            if (length2 < 2) {
                throw new IllegalArgumentException("Arrays must have at least two columns.");
            }
            this.mCorrections = dArr;
            this.mCorrectionUncertainties = dArr2;
            this.mDeltaTheta = 360.0d / ((double) length);
            this.mDeltaPhi = 180.0d / ((double) (length2 - 1));
        }

        SphericalCorrections(Parcel parcel) {
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, i, i2);
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, i, i2);
            for (int i3 = 0; i3 < i; i3++) {
                parcel.readDoubleArray(dArr[i3]);
            }
            for (int i4 = 0; i4 < i; i4++) {
                parcel.readDoubleArray(dArr2[i4]);
            }
            this.mNumRows = i;
            this.mNumColumns = i2;
            this.mCorrections = dArr;
            this.mCorrectionUncertainties = dArr2;
            this.mDeltaTheta = 360.0d / ((double) i);
            this.mDeltaPhi = 180.0d / ((double) (i2 - 1));
        }

        public double[][] getCorrectionsArray() {
            return this.mCorrections;
        }

        public double[][] getCorrectionUncertaintiesArray() {
            return this.mCorrectionUncertainties;
        }

        public double getDeltaTheta() {
            return this.mDeltaTheta;
        }

        public double getDeltaPhi() {
            return this.mDeltaPhi;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mNumRows);
            parcel.writeInt(this.mNumColumns);
            for (double[] dArr : this.mCorrections) {
                parcel.writeDoubleArray(dArr);
            }
            for (double[] dArr2 : this.mCorrectionUncertainties) {
                parcel.writeDoubleArray(dArr2);
            }
        }

        public String toString() {
            return "SphericalCorrections{Corrections=" + Arrays.toString(this.mCorrections) + ", CorrectionUncertainties=" + Arrays.toString(this.mCorrectionUncertainties) + ", DeltaTheta=" + this.mDeltaTheta + ", DeltaPhi=" + this.mDeltaPhi + '}';
        }
    }

    private GnssAntennaInfo(double d, PhaseCenterOffset phaseCenterOffset, SphericalCorrections sphericalCorrections, SphericalCorrections sphericalCorrections2) {
        if (phaseCenterOffset == null) {
            throw new IllegalArgumentException("Phase Center Offset Coordinates cannot be null.");
        }
        this.mCarrierFrequencyMHz = d;
        this.mPhaseCenterOffset = phaseCenterOffset;
        this.mPhaseCenterVariationCorrections = sphericalCorrections;
        this.mSignalGainCorrections = sphericalCorrections2;
    }

    public static class Builder {
        private double mCarrierFrequencyMHz;
        private PhaseCenterOffset mPhaseCenterOffset;
        private SphericalCorrections mPhaseCenterVariationCorrections;
        private SphericalCorrections mSignalGainCorrections;

        public Builder setCarrierFrequencyMHz(double d) {
            this.mCarrierFrequencyMHz = d;
            return this;
        }

        public Builder setPhaseCenterOffset(PhaseCenterOffset phaseCenterOffset) {
            this.mPhaseCenterOffset = (PhaseCenterOffset) Objects.requireNonNull(phaseCenterOffset);
            return this;
        }

        public Builder setPhaseCenterVariationCorrections(SphericalCorrections sphericalCorrections) {
            this.mPhaseCenterVariationCorrections = sphericalCorrections;
            return this;
        }

        public Builder setSignalGainCorrections(SphericalCorrections sphericalCorrections) {
            this.mSignalGainCorrections = sphericalCorrections;
            return this;
        }

        public GnssAntennaInfo build() {
            return new GnssAntennaInfo(this.mCarrierFrequencyMHz, this.mPhaseCenterOffset, this.mPhaseCenterVariationCorrections, this.mSignalGainCorrections);
        }
    }

    public double getCarrierFrequencyMHz() {
        return this.mCarrierFrequencyMHz;
    }

    public PhaseCenterOffset getPhaseCenterOffset() {
        return this.mPhaseCenterOffset;
    }

    public SphericalCorrections getPhaseCenterVariationCorrections() {
        return this.mPhaseCenterVariationCorrections;
    }

    public SphericalCorrections getSignalGainCorrections() {
        return this.mSignalGainCorrections;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mCarrierFrequencyMHz);
        parcel.writeParcelable(this.mPhaseCenterOffset, i);
        parcel.writeParcelable(this.mPhaseCenterVariationCorrections, i);
        parcel.writeParcelable(this.mSignalGainCorrections, i);
    }

    public String toString() {
        return "GnssAntennaInfo{CarrierFrequencyMHz=" + this.mCarrierFrequencyMHz + ", PhaseCenterOffset=" + this.mPhaseCenterOffset + ", PhaseCenterVariationCorrections=" + this.mPhaseCenterVariationCorrections + ", SignalGainCorrections=" + this.mSignalGainCorrections + '}';
    }
}

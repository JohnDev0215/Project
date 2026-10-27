package com.byd.music.view.spectrum;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class DynamicWavelet implements PitchDetector {
    int[] distances;
    int[] maxs;
    int[] mins;
    private final float sampleRate;
    private final int maxFLWTlevels = 6;
    private final double maxF = 3000.0d;
    private final int differenceLevelsN = 3;
    private final double maximaThresholdRatio = 0.75d;
    private final PitchDetectionResult result = new PitchDetectionResult();

    public DynamicWavelet(float f, int i) {
        this.sampleRate = f;
        this.distances = new int[i];
        this.mins = new int[i];
        this.maxs = new int[i];
    }

    @Override // com.byd.music.view.spectrum.PitchDetector
    public PitchDetectionResult getPitch(float[] fArr) {
        float fPow;
        int i;
        double d;
        int i2;
        float[] fArr2 = fArr;
        int length = fArr2.length;
        int[] iArr = this.distances;
        if (iArr.length == fArr2.length) {
            Arrays.fill(iArr, 0);
            Arrays.fill(this.mins, 0);
            Arrays.fill(this.maxs, 0);
        } else {
            this.distances = new int[fArr2.length];
            this.mins = new int[fArr2.length];
            this.maxs = new int[fArr2.length];
        }
        double d2 = 0.0d;
        double dMax = 0.0d;
        double dMin = 0.0d;
        for (double d3 : fArr2) {
            d2 += d3;
            dMax = Math.max(dMax, d3);
            dMin = Math.min(d3, dMin);
        }
        double length2 = d2 / ((double) fArr2.length);
        double d4 = dMax - length2;
        double d5 = -(dMin - length2);
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = d4 * 0.75d;
        int i3 = 0;
        double d7 = -1.0d;
        while (true) {
            double d8 = length2;
            int iPow = (int) (((double) this.sampleRate) / (Math.pow(2.0d, i3) * 3000.0d));
            if (length < 2) {
                break;
            }
            int i4 = -1000000;
            int i5 = 2;
            int i6 = -1000000;
            double d9 = -1000.0d;
            int i7 = 0;
            int i8 = 0;
            boolean z = false;
            boolean z2 = false;
            while (i5 < length) {
                double d10 = ((double) fArr2[i5]) - d8;
                int i9 = i4;
                double d11 = ((double) fArr2[i5 - 1]) - d8;
                if (d11 <= 0.0d && d10 > 0.0d) {
                    z2 = true;
                }
                if (d11 >= 0.0d && d10 < 0.0d) {
                    z = true;
                }
                double d12 = d10 - d11;
                if (d9 > -1000.0d) {
                    if (z && d9 < 0.0d && d12 >= 0.0d && Math.abs(d10) >= d6 && i5 > i9 + iPow) {
                        this.mins[i7] = i5;
                        i9 = i5;
                        i7++;
                        z = false;
                    }
                    if (z2 && d9 > 0.0d && d12 <= 0.0d && Math.abs(d10) >= d6 && i5 > i6 + iPow) {
                        this.maxs[i8] = i5;
                        i8++;
                        i6 = i5;
                        z2 = false;
                    }
                }
                i5++;
                d9 = d12;
                i4 = i9;
            }
            if (i7 == 0 && i8 == 0) {
                break;
            }
            int i10 = 0;
            Arrays.fill(this.distances, 0);
            for (int i11 = 0; i11 < i7; i11++) {
                for (int i12 = 1; i12 < 3; i12++) {
                    int i13 = i11 + i12;
                    if (i13 < i7) {
                        int[] iArr2 = this.mins;
                        int iAbs = Math.abs(iArr2[i11] - iArr2[i13]);
                        int[] iArr3 = this.distances;
                        iArr3[iAbs] = iArr3[iAbs] + 1;
                    }
                }
            }
            int i14 = -1;
            int i15 = -1;
            int i16 = 0;
            while (i16 < length) {
                int i17 = i10;
                for (int i18 = -iPow; i18 <= iPow; i18++) {
                    int i19 = i16 + i18;
                    if (i19 >= 0 && i19 < length) {
                        i17 += this.distances[i19];
                    }
                }
                if (i17 == i15) {
                    if (i16 == i14 * 2) {
                        i14 = i16;
                    }
                } else if (i17 > i15) {
                    i14 = i16;
                    i15 = i17;
                }
                i16++;
                i10 = 0;
            }
            int i20 = -iPow;
            double d13 = 0.0d;
            double d14 = 0.0d;
            while (i20 <= iPow) {
                int i21 = i14 + i20;
                int i22 = i14;
                if (i21 < 0 || i21 >= fArr2.length || (i2 = this.distances[i21]) <= 0) {
                    d = d6;
                } else {
                    d = d6;
                    d14 += (double) i2;
                    d13 += (double) (i21 * i2);
                }
                i20++;
                i14 = i22;
                d6 = d;
            }
            double d15 = d6;
            double d16 = d13 / d14;
            if (d7 > -1.0d && Math.abs((d16 * 2.0d) - d7) <= iPow * 2) {
                fPow = (float) (((double) this.sampleRate) / (Math.pow(2.0d, i3 - 1) * d7));
                break;
            }
            i3++;
            if (i3 >= 6 || length < 2) {
                break;
            }
            float[] fArr3 = length == this.distances.length ? new float[length / 2] : fArr2;
            int i23 = 0;
            while (true) {
                i = length / 2;
                if (i23 < i) {
                    int i24 = i23 * 2;
                    fArr3[i23] = (fArr2[i24] + fArr2[i24 + 1]) / 2.0f;
                    i23++;
                }
            }
            fArr2 = fArr3;
            length = i;
            d7 = d16;
            length2 = d8;
            d6 = d15;
        }
        fPow = -1.0f;
        this.result.setPitch(fPow);
        this.result.setPitched(-1.0f != fPow);
        this.result.setProbability(-1.0f);
        return this.result;
    }
}

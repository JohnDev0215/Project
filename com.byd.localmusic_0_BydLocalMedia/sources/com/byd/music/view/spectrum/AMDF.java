package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public class AMDF implements PitchDetector {
    private static final double DEFAULT_MAX_FREQUENCY = 1000.0d;
    private static final double DEFAULT_MIN_FREQUENCY = 82.0d;
    private static final double DEFAULT_RATIO = 5.0d;
    private static final double DEFAULT_SENSITIVITY = 0.1d;
    private final double[] amd;
    private final long maxPeriod;
    private final long minPeriod;
    private final double ratio;
    private final PitchDetectionResult result;
    private final float sampleRate;
    private final double sensitivity;

    public AMDF(float f, int i) {
        this(f, i, DEFAULT_MIN_FREQUENCY, DEFAULT_MAX_FREQUENCY);
    }

    public AMDF(float f, int i, double d, double d2) {
        this.sampleRate = f;
        this.amd = new double[i];
        this.ratio = DEFAULT_RATIO;
        this.sensitivity = DEFAULT_SENSITIVITY;
        double d3 = f;
        this.maxPeriod = Math.round((d3 / d) + 0.5d);
        this.minPeriod = Math.round((d3 / d2) + 0.5d);
        this.result = new PitchDetectionResult();
    }

    @Override // com.byd.music.view.spectrum.PitchDetector
    public PitchDetectionResult getPitch(float[] fArr) {
        int i;
        double[] dArr;
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = length - i2;
            int i4 = i3 + 1;
            double[] dArr2 = new double[i4];
            double[] dArr3 = new double[i4];
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                i5++;
                dArr2[i5] = fArr[i6];
            }
            int i7 = 0;
            for (int i8 = i2; i8 < length; i8++) {
                i7++;
                dArr3[i7] = fArr[i8];
            }
            double[] dArr4 = new double[i4];
            for (int i9 = 0; i9 < i4; i9++) {
                dArr4[i9] = dArr2[i9] - dArr3[i9];
            }
            double dAbs = 0.0d;
            for (int i10 = 0; i10 < i4; i10++) {
                dAbs += Math.abs(dArr4[i10]);
            }
            this.amd[i2] = dAbs;
        }
        double d = Double.POSITIVE_INFINITY;
        double d2 = Double.NEGATIVE_INFINITY;
        for (int i11 = (int) this.minPeriod; i11 < ((int) this.maxPeriod); i11++) {
            double[] dArr5 = this.amd;
            if (dArr5[i11] < d) {
                d = dArr5[i11];
            }
            double[] dArr6 = this.amd;
            if (dArr6[i11] > d2) {
                d2 = dArr6[i11];
            }
        }
        int iRound = (int) Math.round((this.sensitivity * (d2 - d)) + d);
        int i12 = (int) this.minPeriod;
        while (i12 <= ((int) this.maxPeriod) && this.amd[i12] > iRound) {
            i12++;
        }
        double d3 = this.minPeriod / 2;
        double d4 = this.amd[i12];
        int i13 = i12;
        loop7: while (true) {
            i = i13;
            while (i13 < ((double) i12) + d3 && i13 <= this.maxPeriod) {
                i13++;
                dArr = this.amd;
                if (dArr[i13] < d4) {
                    break;
                }
            }
            d4 = dArr[i13];
        }
        float f = ((double) Math.round(this.amd[i] * this.ratio)) < d2 ? this.sampleRate / i : -1.0f;
        this.result.setPitch(f);
        this.result.setPitched(-1.0f != f);
        this.result.setProbability(-1.0f);
        return this.result;
    }
}

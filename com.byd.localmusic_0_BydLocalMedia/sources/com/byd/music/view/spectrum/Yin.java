package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public final class Yin implements PitchDetector {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int DEFAULT_OVERLAP = 1536;
    private static final double DEFAULT_THRESHOLD = 0.2d;
    private final PitchDetectionResult result;
    private final float sampleRate;
    private final double threshold;
    private final float[] yinBuffer;

    public Yin(float f, int i) {
        this(f, i, DEFAULT_THRESHOLD);
    }

    public Yin(float f, int i, double d) {
        this.sampleRate = f;
        this.threshold = d;
        this.yinBuffer = new float[i / 2];
        this.result = new PitchDetectionResult();
    }

    @Override // com.byd.music.view.spectrum.PitchDetector
    public PitchDetectionResult getPitch(float[] fArr) {
        float fParabolicInterpolation;
        difference(fArr);
        cumulativeMeanNormalizedDifference();
        int iAbsoluteThreshold = absoluteThreshold();
        if (iAbsoluteThreshold != -1) {
            fParabolicInterpolation = this.sampleRate / parabolicInterpolation(iAbsoluteThreshold);
        } else {
            fParabolicInterpolation = -1.0f;
        }
        this.result.setPitch(fParabolicInterpolation);
        return this.result;
    }

    private void difference(float[] fArr) {
        int i = 0;
        while (true) {
            float[] fArr2 = this.yinBuffer;
            if (i >= fArr2.length) {
                break;
            }
            fArr2[i] = 0.0f;
            i++;
        }
        for (int i2 = 1; i2 < this.yinBuffer.length; i2++) {
            int i3 = 0;
            while (true) {
                float[] fArr3 = this.yinBuffer;
                if (i3 < fArr3.length) {
                    float f = fArr[i3] - fArr[i3 + i2];
                    fArr3[i2] = fArr3[i2] + (f * f);
                    i3++;
                }
            }
        }
    }

    private void cumulativeMeanNormalizedDifference() {
        this.yinBuffer[0] = 1.0f;
        int i = 1;
        float f = 0.0f;
        while (true) {
            float[] fArr = this.yinBuffer;
            if (i >= fArr.length) {
                return;
            }
            f += fArr[i];
            fArr[i] = fArr[i] * (i / f);
            i++;
        }
    }

    private int absoluteThreshold() {
        int i = 2;
        while (true) {
            if (i >= this.yinBuffer.length) {
                break;
            }
            if (r1[i] < this.threshold) {
                while (true) {
                    int i2 = i + 1;
                    float[] fArr = this.yinBuffer;
                    if (i2 >= fArr.length || fArr[i2] >= fArr[i]) {
                        break;
                    }
                    i = i2;
                }
                this.result.setProbability(1.0f - this.yinBuffer[i]);
            } else {
                i++;
            }
        }
        if (i == this.yinBuffer.length || r1[i] >= this.threshold) {
            this.result.setProbability(0.0f);
            this.result.setPitched(false);
            return -1;
        }
        this.result.setPitched(true);
        return i;
    }

    private float parabolicInterpolation(int i) {
        int i2 = i < 1 ? i : i - 1;
        int i3 = i + 1;
        if (i3 >= this.yinBuffer.length) {
            i3 = i;
        }
        if (i2 == i) {
            float[] fArr = this.yinBuffer;
            if (fArr[i] > fArr[i3]) {
                return i3;
            }
        } else if (i3 == i) {
            float[] fArr2 = this.yinBuffer;
            if (fArr2[i] > fArr2[i2]) {
                return i2;
            }
        } else {
            float[] fArr3 = this.yinBuffer;
            float f = fArr3[i2];
            float f2 = fArr3[i];
            float f3 = fArr3[i3];
            return i + ((f3 - f) / ((((f2 * 2.0f) - f3) - f) * 2.0f));
        }
        return i;
    }
}

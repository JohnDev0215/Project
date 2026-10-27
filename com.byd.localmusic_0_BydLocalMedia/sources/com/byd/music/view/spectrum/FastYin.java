package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public final class FastYin implements PitchDetector {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int DEFAULT_OVERLAP = 1536;
    private static final double DEFAULT_THRESHOLD = 0.2d;
    private final float[] audioBufferFFT;
    private final FloatFFT fft;
    private final float[] kernel;
    private final PitchDetectionResult result;
    private final float sampleRate;
    private final double threshold;
    private final float[] yinBuffer;
    private final float[] yinStyleACF;

    public FastYin(float f, int i) {
        this(f, i, DEFAULT_THRESHOLD);
    }

    public FastYin(float f, int i, double d) {
        this.sampleRate = f;
        this.threshold = d;
        this.yinBuffer = new float[i / 2];
        int i2 = i * 2;
        this.audioBufferFFT = new float[i2];
        this.kernel = new float[i2];
        this.yinStyleACF = new float[i2];
        this.fft = new FloatFFT(i);
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
        float[] fArr2 = new float[this.yinBuffer.length];
        for (int i = 0; i < this.yinBuffer.length; i++) {
            fArr2[0] = fArr2[0] + (fArr[i] * fArr[i]);
        }
        int i2 = 1;
        while (true) {
            float[] fArr3 = this.yinBuffer;
            if (i2 >= fArr3.length) {
                break;
            }
            int i3 = i2 - 1;
            fArr2[i2] = (fArr2[i3] - (fArr[i3] * fArr[i3])) + (fArr[fArr3.length + i2] * fArr[fArr3.length + i2]);
            i2++;
        }
        for (int i4 = 0; i4 < fArr.length; i4++) {
            float[] fArr4 = this.audioBufferFFT;
            int i5 = i4 * 2;
            fArr4[i5] = fArr[i4];
            fArr4[i5 + 1] = 0.0f;
        }
        this.fft.complexForward(this.audioBufferFFT);
        int i6 = 0;
        while (true) {
            float[] fArr5 = this.yinBuffer;
            if (i6 >= fArr5.length) {
                break;
            }
            float[] fArr6 = this.kernel;
            int i7 = i6 * 2;
            fArr6[i7] = fArr[(fArr5.length - 1) - i6];
            fArr6[i7 + 1] = 0.0f;
            fArr6[fArr.length + i7] = 0.0f;
            fArr6[i7 + fArr.length + 1] = 0.0f;
            i6++;
        }
        this.fft.complexForward(this.kernel);
        for (int i8 = 0; i8 < fArr.length; i8++) {
            float[] fArr7 = this.yinStyleACF;
            int i9 = i8 * 2;
            float[] fArr8 = this.audioBufferFFT;
            float f = fArr8[i9];
            float[] fArr9 = this.kernel;
            int i10 = i9 + 1;
            fArr7[i9] = (f * fArr9[i9]) - (fArr8[i10] * fArr9[i10]);
            fArr7[i10] = (fArr8[i10] * fArr9[i9]) + (fArr8[i9] * fArr9[i10]);
        }
        this.fft.complexInverse(this.yinStyleACF, true);
        int i11 = 0;
        while (true) {
            float[] fArr10 = this.yinBuffer;
            if (i11 >= fArr10.length) {
                return;
            }
            fArr10[i11] = (fArr2[0] + fArr2[i11]) - (this.yinStyleACF[((fArr10.length - 1) + i11) * 2] * 2.0f);
            i11++;
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
        if (i == this.yinBuffer.length || r1[i] >= this.threshold || this.result.getProbability() > 1.0d) {
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

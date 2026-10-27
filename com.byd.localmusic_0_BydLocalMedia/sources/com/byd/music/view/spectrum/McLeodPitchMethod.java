package com.byd.music.view.spectrum;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class McLeodPitchMethod implements PitchDetector {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final double DEFAULT_CUTOFF = 0.97d;
    public static final int DEFAULT_OVERLAP = 768;
    private static final double LOWER_PITCH_CUTOFF = 80.0d;
    private static final double SMALL_CUTOFF = 0.5d;
    private final List<Float> ampEstimates;
    private final double cutoff;
    private final List<Integer> maxPositions;
    private final float[] nsdf;
    private final List<Float> periodEstimates;
    private final PitchDetectionResult result;
    private final float sampleRate;
    private float turningPointX;
    private float turningPointY;

    public McLeodPitchMethod(float f) {
        this(f, 1024, DEFAULT_CUTOFF);
    }

    public McLeodPitchMethod(float f, int i) {
        this(f, i, DEFAULT_CUTOFF);
    }

    public McLeodPitchMethod(float f, int i, double d) {
        this.maxPositions = new ArrayList();
        this.periodEstimates = new ArrayList();
        this.ampEstimates = new ArrayList();
        this.sampleRate = f;
        this.nsdf = new float[i];
        this.cutoff = d;
        this.result = new PitchDetectionResult();
    }

    private void normalizedSquareDifference(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            float f = 0.0f;
            float f2 = 0.0f;
            for (int i2 = 0; i2 < fArr.length - i; i2++) {
                int i3 = i2 + i;
                f += fArr[i2] * fArr[i3];
                f2 += (fArr[i2] * fArr[i2]) + (fArr[i3] * fArr[i3]);
            }
            this.nsdf[i] = (f * 2.0f) / f2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0075  */
    @Override // com.byd.music.view.spectrum.PitchDetector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.byd.music.view.spectrum.PitchDetectionResult getPitch(float[] r9) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.spectrum.McLeodPitchMethod.getPitch(float[]):com.byd.music.view.spectrum.PitchDetectionResult");
    }

    private void parabolicInterpolation(int i) {
        float[] fArr = this.nsdf;
        float f = fArr[i - 1];
        float f2 = fArr[i];
        float f3 = fArr[i + 1];
        float f4 = i;
        float f5 = (f3 + f) - (f2 * 2.0f);
        if (f5 == 0.0d) {
            this.turningPointX = f4;
            this.turningPointY = f2;
        } else {
            float f6 = f - f3;
            this.turningPointX = f4 + (f6 / (2.0f * f5));
            this.turningPointY = f2 - ((f6 * f6) / (f5 * 8.0f));
        }
    }

    private void peakPicking() {
        int i = 0;
        while (true) {
            float[] fArr = this.nsdf;
            if (i >= (fArr.length - 1) / 3 || fArr[i] <= 0.0f) {
                break;
            } else {
                i++;
            }
        }
        while (true) {
            if (i >= this.nsdf.length - 1 || r2[i] > 0.0d) {
                break;
            } else {
                i++;
            }
        }
        int i2 = 0;
        if (i == 0) {
            i = 1;
        }
        while (true) {
            float[] fArr2 = this.nsdf;
            if (i >= fArr2.length - 1) {
                break;
            }
            if (fArr2[i] > fArr2[i - 1] && fArr2[i] >= fArr2[i + 1] && (i2 == 0 || fArr2[i] > fArr2[i2])) {
                i2 = i;
            }
            i++;
            float[] fArr3 = this.nsdf;
            if (i < fArr3.length - 1 && fArr3[i] <= 0.0f) {
                if (i2 > 0) {
                    this.maxPositions.add(Integer.valueOf(i2));
                    i2 = 0;
                }
                while (true) {
                    float[] fArr4 = this.nsdf;
                    if (i >= fArr4.length - 1 || fArr4[i] > 0.0f) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        if (i2 > 0) {
            this.maxPositions.add(Integer.valueOf(i2));
        }
    }
}

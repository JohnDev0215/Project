package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public class FFTPitch implements PitchDetector {
    private final PitchDetectionResult result = new PitchDetectionResult();

    public FFTPitch(int i, int i2) {
    }

    @Override // com.byd.music.view.spectrum.PitchDetector
    public PitchDetectionResult getPitch(float[] fArr) {
        return this.result;
    }
}

package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public class PitchDetectionResult {
    private float pitch;
    private boolean pitched;
    private float probability;

    public PitchDetectionResult() {
        this.pitch = -1.0f;
        this.probability = -1.0f;
        this.pitched = false;
    }

    public PitchDetectionResult(PitchDetectionResult pitchDetectionResult) {
        this.pitch = pitchDetectionResult.pitch;
        this.probability = pitchDetectionResult.probability;
        this.pitched = pitchDetectionResult.pitched;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public PitchDetectionResult m79clone() {
        return new PitchDetectionResult(this);
    }

    public float getProbability() {
        return this.probability;
    }

    public void setProbability(float f) {
        this.probability = f;
    }

    public boolean isPitched() {
        return this.pitched;
    }

    public void setPitched(boolean z) {
        this.pitched = z;
    }
}

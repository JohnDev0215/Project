package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public class PitchProcessor implements AudioProcessor {
    private final PitchDetector detector;
    private final PitchDetectionHandler handler;

    @Override // com.byd.music.view.spectrum.AudioProcessor
    public void processingFinished() {
    }

    public enum PitchEstimationAlgorithm {
        YIN,
        MPM,
        FFT_YIN,
        DYNAMIC_WAVELET,
        FFT_PITCH,
        AMDF;

        public PitchDetector getDetector(float f, int i) {
            if (this == MPM) {
                return new McLeodPitchMethod(f, i);
            }
            if (this == DYNAMIC_WAVELET) {
                return new DynamicWavelet(f, i);
            }
            if (this == FFT_YIN) {
                return new FastYin(f, i);
            }
            if (this == AMDF) {
                return new AMDF(f, i);
            }
            if (this == FFT_PITCH) {
                return new FFTPitch(Math.round(f), i);
            }
            return new Yin(f, i);
        }
    }

    public PitchProcessor(PitchEstimationAlgorithm pitchEstimationAlgorithm, float f, int i, PitchDetectionHandler pitchDetectionHandler) {
        this.detector = pitchEstimationAlgorithm.getDetector(f, i);
        this.handler = pitchDetectionHandler;
    }

    @Override // com.byd.music.view.spectrum.AudioProcessor
    public boolean process(AudioEvent audioEvent) {
        this.handler.handlePitch(this.detector.getPitch(audioEvent.getFloatBuffer()), audioEvent);
        return true;
    }
}

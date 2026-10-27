package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class ConfidenceEvaluator {
    private float mConfidence;
    private final float mConfidenceStep;
    private final float mMaxConfidence;
    private final float mMinConfidence;

    ConfidenceEvaluator() {
        this(ShortBufferHeuristicsConfig.getInstance());
    }

    ConfidenceEvaluator(ShortBufferHeuristicsConfig shortBufferHeuristicsConfig) {
        this.mMinConfidence = shortBufferHeuristicsConfig.getMinConfidence();
        this.mMaxConfidence = shortBufferHeuristicsConfig.getMaxConfidence();
        this.mConfidenceStep = shortBufferHeuristicsConfig.getConfidenceStep();
        this.mConfidence = shortBufferHeuristicsConfig.getStartConfidence();
    }

    void downloadSuccess() {
        this.mConfidence = Math.min(this.mMaxConfidence, this.mConfidence + this.mConfidenceStep);
    }

    void downloadFailure() {
        this.mConfidence = this.mMinConfidence;
    }

    float getConfidence() {
        return this.mConfidence;
    }
}

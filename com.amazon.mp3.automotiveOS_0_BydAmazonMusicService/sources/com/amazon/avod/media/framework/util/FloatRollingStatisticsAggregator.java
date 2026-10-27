package com.amazon.avod.media.framework.util;

import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: compiled from: FloatRollingStatisticsAggregator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/avod/media/framework/util/FloatRollingStatisticsAggregator;", "", "size", "", "(I)V", "<set-?>", "", "average", "getAverage", "()F", "rollingCounter", "sampleCount", "getSampleCount", "()I", "samples", "", "getSize", "total", "addSample", "", "sample", "calculateAverage", "oldValue", "newValue", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FloatRollingStatisticsAggregator {
    private float average;
    private int rollingCounter;
    private int sampleCount;
    private final float[] samples;
    private final int size;
    private float total;

    public FloatRollingStatisticsAggregator(int i) {
        this.size = i;
        this.samples = new float[i];
    }

    public final int getSize() {
        return this.size;
    }

    public final int getSampleCount() {
        return this.sampleCount;
    }

    public final float getAverage() {
        return this.average;
    }

    public final void addSample(float sample) {
        synchronized (this.samples) {
            float[] fArr = this.samples;
            int i = this.rollingCounter;
            float f = fArr[i];
            fArr[i] = sample;
            int i2 = i + 1;
            this.rollingCounter = i2;
            if (i2 >= getSize()) {
                this.rollingCounter = 0;
            }
            if (getSampleCount() < getSize()) {
                this.sampleCount = getSampleCount() + 1;
            }
            this.average = calculateAverage(f, sample);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final float calculateAverage(float oldValue, float newValue) {
        float f = this.total + (newValue - oldValue);
        this.total = f;
        return f / this.sampleCount;
    }
}
